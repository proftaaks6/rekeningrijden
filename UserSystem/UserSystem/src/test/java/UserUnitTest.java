import com.proftaak.usersystem.converters.ClientUserConverter;
import com.proftaak.usersystem.converters.UserVehicleConverter;
import com.proftaak.usersystem.converters.VehicleConverter;
import com.proftaak.usersystem.models.ClientUser;
import com.proftaak.usersystem.models.UserVehicle;
import com.proftaak.usersystem.models.Vehicle;
import com.proftaak.usersystem.util.RestCommuncationHelper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class UserUnitTest {
    private ClientUser user;
    private UserVehicle userVehicle;
    private Vehicle vehicle;

    @Before
    public void setup() {
        userVehicle = new UserVehicle();
        userVehicle.setId(1);

        user = new ClientUser();
//        user.setName("Rick Jeurissen");
//        user.setAddress("Goeiestraat 911");
//        user.setResidence("Uden");
//        user.addOwnedVehicle(userVehicle);
//        user.setEmail("rick@mail.com");

        vehicle = new Vehicle();
        vehicle.setChassisNumber("893as9das9d");
        vehicle.addOwner(userVehicle);

        userVehicle.setVehicle(vehicle);
        userVehicle.setUser(user);
    }

    @Test
    public void getVehicleChassisNumber() {
        vehicle.setChassisNumber("s89sd8fs9d8");

        Assert.assertEquals("s89sd8fs9d8", vehicle.getChassisNumber());
    }

    @Test
    public void ClientUserConverterTest() {
        com.proftaak.usersystem.shared.ClientUser sharedClientUser = new ClientUserConverter().toShared(user);

        Assert.assertTrue(sharedClientUser.getName().equals("Rick Jeurissen"));
        Assert.assertTrue(sharedClientUser.getEmail().equals("rick@mail.com"));
        Assert.assertTrue(sharedClientUser.getAddress().equals("Goeiestraat 911"));
    }

    @Test
    public void RestCommunicationHelperTest() {
        String response = "";
        try {
            response = RestCommuncationHelper.getRequest("https://jsonplaceholder.typicode.com/todos/1", "");
            if (!response.equals("")) {
                Assert.assertTrue(true);
                return;
            }
        } catch (IOException e) {
            Assert.assertTrue(false);
            return;
        }

        Assert.assertTrue(false);
    }
}
