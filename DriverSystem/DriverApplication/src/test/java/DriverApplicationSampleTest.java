import com.proftaak.driverapplication.models.DriverUser;
import com.proftaak.driverapplication.models.LoginAttempt;
import com.proftaak.driverapplication.service.DriverApplicationService;
import com.proftaak.invoicesystem.shared.Invoice;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import java.beans.Transient;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

class DriverApplicationSampleTest {

    @Inject
    private DriverApplicationService service;

    @Test
    void getUserById(){
        long id = 1;
        DriverUser driverUser = service.getUserById(id);
        assertThat("Driver is not null", driverUser != null);
    }

    @Test
    void getUserByIdIsNull(){
        long id = 0;
        DriverUser driverUser = service.getUserById(id);
        assertThat("Driver is null", driverUser == null);
    }

    @Test
    void saveUser()throws Exception {
        DriverUser driverUser = service.saveNewUser("test@test.nl", "test123");
        assertThat("Driver is not null", driverUser != null);

        //duplicate email
        DriverUser userFail = service.saveNewUser("test@test.nl", "test123");
        assertThat("Driver is null", userFail == null);
    }

    @Test
    void getUserStatistics(){
        long id = 1;
        List<LoginAttempt> attempts = service.getUserStatistics(id);
        assertThat("Loginattempts is not null", !attempts.isEmpty());
    }

    @Test
    void validateUser() throws Exception{
        DriverUser user = service.validateUser("test@test.nl", "Test123");
        assertThat("Driver is not null", user != null);
    }

    @Test
    void validateUserNull() throws Exception{
        DriverUser user = service.validateUser("", "");
        assertThat("Driver is null", user == null);
    }

    @Test
    void getUserInvoices() throws Exception{
        List<Invoice> invoices = service.getUserInvoices();
        assertThat("Invoices is not null", invoices != null);
    }

}
