import com.proftaak.usersystem.converters.ClientUserConverter;
import com.proftaak.usersystem.models.ClientUser;
import com.proftaak.usersystem.models.UserVehicle;
import com.proftaak.usersystem.models.Vehicle;
import com.proftaak.usersystem.util.RestCommuncationHelper;
import org.junit.Before;
import org.junit.Test;
import java.io.IOException;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class UserUnitTest {
    private ClientUser user;
    private UserVehicle userVehicle;
    private Vehicle vehicle;

    @Before
    public void setup() {
        userVehicle = new UserVehicle();
        userVehicle.setId(1);

        user = new ClientUser();
        user.setName("Rick Jeurissen");
        user.setAddress("Goeiestraat 911");
        user.setResidence("Uden");
        user.addOwnedVehicle(userVehicle);
        user.setEmail("rick@mail.com");

        vehicle = new Vehicle();
        vehicle.setChassisNumber("893as9das9d");
        vehicle.addOwner(userVehicle);

        userVehicle.setVehicle(vehicle);
        userVehicle.setUser(user);
    }

    @Test
    public void getVehicleChassisNumber() {
        vehicle.setChassisNumber("s89sd8fs9d8");

        assertEquals("s89sd8fs9d8", vehicle.getChassisNumber());
    }

    @Test
    public void ClientUserConverterTest() {
        com.proftaak.usersystem.shared.ClientUser sharedClientUser = new ClientUserConverter().toShared(user);

        assertEquals("Rick Jeurissen", sharedClientUser.getName());
        assertEquals("rick@mail.com", sharedClientUser.getEmail());
        assertEquals("Goeiestraat 911", sharedClientUser.getAddress());
    }

    @Test
    public void RestCommunicationHelperTest() {
        String response = "";
        try {
            response = RestCommuncationHelper.getRequest("https://jsonplaceholder.typicode.com/todos/1", "");
            if (!response.equals("")) {
                assertTrue(true);
                return;
            }
        } catch (IOException e) {
            assertTrue(false);
            return;
        }

        assertTrue(false);
    }
}
