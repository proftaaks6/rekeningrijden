package com.proftaak.invoicesystem.generator;

import com.proftaak.invoicesystem.converters.LocationPointConverter;
import com.proftaak.invoicesystem.models.Invoice;
import com.proftaak.invoicesystem.models.PriceRow;
import com.proftaak.invoicesystem.models.SquareRegion;
import com.proftaak.invoicesystem.services.LocationPointService;
import com.proftaak.invoicesystem.services.RegionService;
import com.proftaak.movementregistrationservice.shared.LocationPoint;

import javax.ejb.*;
import javax.inject.Inject;
import java.util.*;
import java.util.stream.Collectors;

@Startup
@Singleton
public class InvoiceGenerator {

    @Inject
    private NextVehicleGenerator generator;

    @Inject
    private LocationPointService locationPointService;

    @Inject
    private RegionService regionService;

    @Schedule(hour = "*", minute = "*", second = "*/1",  persistent = false)
    public void generatePeriodically() {
        Date from = new Date(); //todo: change new Date() into the current date minus one month
        Date now = new Date(); //don't touch this
        int vehicleId = generator.getNextVehicleId(from, now);

        if (vehicleId <= 0) {
            return; //there are no vehicles to process
        }
        List<LocationPoint> locationPoints = locationPointService.getLocationPoints(vehicleId, from, now);

        Map<SquareRegion, PriceRow> priceRows = new HashMap<>();
        LocationPoint last = null;
        regionService.reloadRegionsInMemory();
        for (LocationPoint locationPoint : locationPoints) {
            SquareRegion region = regionService.getRegionContainingPoint(locationPoint.getLongitude(), locationPoint.getLatitude());
            priceRows.putIfAbsent(region, new PriceRow(region));
            PriceRow row = priceRows.get(region);
            if(last != null){
                double km = distanceInKmBetweenEarthCoordinates(last.getLatitude(), last.getLongitude(), locationPoint.getLatitude(), locationPoint.getLongitude());
                double total = row.getDistance() + km;
                row.setDistance(total);
                row.getLocationPoints().add(new LocationPointConverter().toEntity(locationPoint));
            }
            last = locationPoint;
        }

        Invoice invoice = new Invoice();

        ArrayList<PriceRow> rows = new ArrayList<>(priceRows.values());
        rows.forEach(PriceRow::calculatePriceBasedOnDistance);
        rows = (ArrayList<PriceRow>) rows.stream().filter(x->x.getPrice() > 0).collect(Collectors.toList());

        invoice.setPriceRowList(rows);
        invoice.setDate(now);
        invoice.setPaid(false);
        invoice.setTotalDistance(rows.stream().mapToDouble(PriceRow::getDistance).sum());
        invoice.setTotalPrice(rows.stream().mapToDouble(PriceRow::getPrice).sum());
        invoice.setVehicleId(vehicleId);

        //todo: add invoice to database
    }

    private double degreesToRadians(double degrees) {
        return degrees * Math.PI / 180;
    }

    /**
     * Taken from https://stackoverflow.com/a/365853/3605947
     * @param lat1
     * @param lon1
     * @param lat2
     * @param lon2
     * @return
     */
    private double distanceInKmBetweenEarthCoordinates(double lat1, double lon1, double lat2, double lon2) {
        int earthRadiusKm = 6371;

        double dLat = degreesToRadians(lat2-lat1);
        double dLon = degreesToRadians(lon2-lon1);

        lat1 = degreesToRadians(lat1);
        lat2 = degreesToRadians(lat2);

        double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
                Math.sin(dLon/2) * Math.sin(dLon/2) * Math.cos(lat1) * Math.cos(lat2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        return earthRadiusKm * c;
    }


}
