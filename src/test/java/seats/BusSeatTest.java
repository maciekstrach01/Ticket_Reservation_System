package seats;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BusSeatTest {

    private BusSeat busSeat;

    @BeforeEach
    void setUp() {
        busSeat = new BusSeat(SeatClass.FIRST, 1, true);
    }

    @Test
    void testGetSeatClass() {
        assertEquals(SeatClass.FIRST, busSeat.getSeatClass());
    }

    @Test
    void testGetSeatNum() {
        assertEquals(1, busSeat.getSeatNum());
    }

    @Test
    void testHasElectricalOutlet() {
        assertTrue(busSeat.hasElectricalOutlet());
    }

    @Test
    void testConstructor() {
        BusSeat anotherBusSeat = new BusSeat(SeatClass.SECOND, 2, false);
        assertEquals(SeatClass.SECOND, anotherBusSeat.getSeatClass());
        assertEquals(2, anotherBusSeat.getSeatNum());
        assertFalse(anotherBusSeat.hasElectricalOutlet());
    }
}
