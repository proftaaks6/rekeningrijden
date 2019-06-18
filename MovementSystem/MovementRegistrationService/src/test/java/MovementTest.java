import com.proftaak.movementregistrationservice.converters.LocationPointConverter;
import com.proftaak.movementregistrationservice.converters.TrackerConverter;
import com.proftaak.movementregistrationservice.converters.VehicleConverter;
import com.proftaak.movementregistrationservice.converters.VehicleTrackerConverter;
import com.proftaak.movementregistrationservice.models.*;
import com.proftaak.movementregistrationservice.utils.RestCommuncationHelper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class MovementTest {
    private Tracker tracker;
    private Vehicle vehicle;
    private VehicleTracker vehicleTracker;

    @Before
    public void setup() {
        tracker = new Tracker();
        tracker.setActive(false);
        LocationPoint p1 = new LocationPoint();
        p1.setTracker(tracker);
        p1.setDate(new Date());
        p1.setId(1);
        p1.setLatitude(54.22);
        p1.setLongitude(93.11);

        List<LocationPoint> points = Arrays.asList(p1);
        tracker.setLocationPoints(points);

        vehicle = new Vehicle();

        vehicleTracker = new VehicleTracker();
        vehicleTracker.setId(2);
        vehicleTracker.setStartDate(new Date());
        vehicleTracker.setTracker(tracker);
        vehicleTracker.setVehicle(vehicle);
        List<VehicleTracker> trackers = Arrays.asList(vehicleTracker);
        tracker.setVehicleTrackers(trackers);
    }



    @Test
    public void LocationPointConverterTest() {
        LocationPointConverter c1 = new LocationPointConverter();

        com.proftaak.movementregistrationservice.shared.LocationPoint sharedLocationPoint = c1.toShared(new LocationPoint(1, 20, 20));
        Assert.assertEquals(1, sharedLocationPoint.getId());
    }

    @Test
    public void TrackerConverter() {
        TrackerConverter tc = new TrackerConverter();

        com.proftaak.movementregistrationservice.shared.Tracker sharedTracker = tc.toShared(tracker);

        Assert.assertFalse(sharedTracker.isActive());
    }



    @Test
    public void GetRecentLocationPointsTest() {
        Date datenow = new Date();
        LocationPoint p = new LocationPoint(59.22, 52.22, datenow);
        tracker.setLocationPoints(new ArrayList<>());
        tracker.addLocationPoint(p);

        Assert.assertEquals(datenow, tracker.getMostRecentLocationPoint().getDate());
    }

    @Test
    public void RestCommunicationHelperTest() {
        String response = "";
        try {
            response = RestCommuncationHelper.getResponseString("https://jsonplaceholder.typicode.com/todos/1", "GET");
            if (!response.equals("")) {
                Assert.assertTrue(true);
                return;
            } else {
                Assert.fail();
                return;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Assert.fail();
    }

    @Test
    public void VehicleTrackerConverter() {
        VehicleTrackerConverter converter = new VehicleTrackerConverter();

        Assert.assertTrue(true);
        // todo
//        com.proftaak.movementregistrationservice.shared.VehicleTracker vt = converter.toShared(vehicleTracker);

    }

    @Test
    public void VehicleConverterTest() {
        VehicleConverter vc = new VehicleConverter();

        Assert.assertTrue(true);
        // todo
//        com.proftaak.movementregistrationservice.shared.Vehicle vehicleShared = vc.toShared(vehicle);

    }
}
