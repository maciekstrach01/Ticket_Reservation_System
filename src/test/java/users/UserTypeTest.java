package users;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserTypeTest {

    @Test
    void testGetVisibleName() {
        assertEquals("Administrator", UserType.ADMINISTRATOR.getVisibleName());
        assertEquals("Pasażer", UserType.PASSENGER.getVisibleName());
    }
}
