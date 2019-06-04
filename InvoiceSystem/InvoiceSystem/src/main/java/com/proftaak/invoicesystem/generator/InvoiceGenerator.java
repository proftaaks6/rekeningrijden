package com.proftaak.invoicesystem.generator;

import com.proftaak.invoicesystem.converters.LocationPointConverter;
import com.proftaak.invoicesystem.models.Invoice;
import com.proftaak.invoicesystem.models.PriceRow;
import com.proftaak.invoicesystem.models.SquareRegion;
import com.proftaak.invoicesystem.services.InvoiceProcessingService;
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

    @Inject
    private InvoiceProcessingService processingService;

    @Schedule(hour = "*", minute = "*", second = "*/30", persistent = false)
    public void generatePeriodically() {
        System.out.println("tick");
        Invoice invoice = generateInvoice(null);
        if(invoice != null) {
            processingService.addInvoice(invoice);
        }
    }

    public Invoice regenerateInvoice(Invoice invoice){
        Invoice regeneratedInvoice = generateInvoice(invoice);
        return regeneratedInvoice;
    }

    private Invoice generateInvoice(Invoice invoice){

        // Subtract 1 month from date
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -1);
        Date from = cal.getTime();

        Date now = new Date(); //don't touch this

        String vehicleChassis = "";

        if(invoice == null){
            vehicleChassis = generator.getNextVehicleId(from, now);
            invoice = new Invoice();
        } else {
            vehicleChassis = invoice.getVehicleChassis();
        }

        System.out.println("Processing "+ vehicleChassis);

        if(vehicleChassis.equals("")){
            return null;
        }

        List<LocationPoint> locationPoints = locationPointService.getLocationPoints(vehicleChassis, from, now);

        Map<SquareRegion, PriceRow> priceRows = new HashMap<>();
        LocationPoint last = null;
        regionService.reloadRegionsInMemory();
        for(LocationPoint locationPoint : locationPoints){
            SquareRegion region = regionService.getRegionContainingPoint(locationPoint.getLatitude(), locationPoint.getLongitude());
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

        ArrayList<PriceRow> rows = new ArrayList<>(priceRows.values());
        rows.forEach(PriceRow::calculatePriceBasedOnDistance);
        //rows = (ArrayList<PriceRow>) rows.stream().filter(x->x.getPrice() >= 0).collect(Collectors.toList());

        invoice.setPriceRowList(rows);
        invoice.setDate(now);
        invoice.setPaid(false);
        invoice.setTotalDistance(rows.stream().mapToDouble(PriceRow::getDistance).sum());
        invoice.setTotalPrice(rows.stream().mapToDouble(PriceRow::getPrice).sum());
        invoice.setVehicleChassis(vehicleChassis);

        System.out.println("Total price "+ invoice.getTotalPrice());
        return invoice;
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
