package com.proftaak.invoicesystem.generator;

import com.proftaak.invoicesystem.converters.LocationPointConverter;
import com.proftaak.invoicesystem.models.Invoice;
import com.proftaak.invoicesystem.models.PriceRow;
import com.proftaak.invoicesystem.models.SquareRegion;
import com.proftaak.invoicesystem.services.InvoiceProcessingService;
import com.proftaak.invoicesystem.services.LocationPointService;
import com.proftaak.invoicesystem.services.RegionService;
import com.proftaak.invoicesystem.services.UserVehiclesService;
import com.proftaak.movementregistrationservice.shared.LocationPoint;

import com.proftaak.usersystem.shared.SimpleUserVehicle;
import javax.ejb.*;
import javax.inject.Inject;
import java.util.*;

@Startup
@Singleton
public class InvoiceGenerator {

    @Inject
    private NextUserGenerator generator;

    @Inject
    private LocationPointService locationPointService;

    @Inject
    private UserVehiclesService userVehiclesService;

    @Inject
    private RegionService regionService;

    @Inject
    private InvoiceProcessingService processingService;

    @Schedule(hour = "*", minute = "*", second = "*/30", persistent = false)
    public void generatePeriodically() {
        processingService.addInvoices(generateInvoices());
    }

    public Invoice regenerateInvoice(Invoice invoice){
        // Invoice regeneratedInvoice = generateInvoice(invoice);
        // return regeneratedInvoice;
        return invoice;
    }

    private List<Invoice> generateInvoices(){
        // Subtract 1 month from date
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -1);
        Date from = cal.getTime();

        Date now = new Date();

        long userId = generator.getNextUserId(from, now);

        if(userId == -1){
            return Collections.emptyList();
        }

        System.out.println("Processing " + userId);
        ArrayList<Invoice> invoices = new ArrayList<>();

        List<SimpleUserVehicle> vehicleLinks = userVehiclesService.getVehicleChassisForUser(userId, from, now);

        for (SimpleUserVehicle vehicleLink :
                vehicleLinks)
        {
            Invoice invoice = new Invoice();
            List<LocationPoint> locationPoints = locationPointService.getLocationPoints(vehicleLink.getChassisNumber(), vehicleLink.getStartDate(), vehicleLink.getEndDate());

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

            invoice.setUserId(userId);
            invoice.setPriceRowList(rows);
            invoice.setDate(now);
            invoice.setPaid(false);
            invoice.setTotalDistance(rows.stream().mapToDouble(PriceRow::getDistance).sum());
            invoice.setTotalPrice(rows.stream().mapToDouble(PriceRow::getPrice).sum());
            invoice.setVehicleChassis(vehicleLink.getChassisNumber());

            invoices.add(invoice);
        }

        return invoices;
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
