package seats;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SeatTest {

    private Seat seat;

    // Concrete subclass for testing
    class TestSeat extends Seat {
        public TestSeat(SeatClass seatClass, int seatNum) {
            super(seatClass, seatNum);
        }
    }

    @BeforeEach
    void setUp() {
        seat = new TestSeat(SeatClass.FIRST, 1);
    }

    @Test
    void testGetSeatClass() {
        assertEquals(SeatClass.FIRST, seat.getSeatClass());
    }

    @Test
    void testGetSeatNum() {
        assertEquals(1, seat.getSeatNum());
    }

    @Test
    void testIsAvailable() {
        assertTrue(seat.isAvailable());
    }

    @Test
    void testSetAvailable() {
        seat.setAvailable(false);
        assertFalse(seat.isAvailable());
        seat.setAvailable(true);
        assertTrue(seat.isAvailable());
    }

    @Test
    void testConstructor() {
        Seat anotherSeat = new TestSeat(SeatClass.SECOND, 2);
        assertEquals(SeatClass.SECOND, anotherSeat.getSeatClass());
        assertEquals(2, anotherSeat.getSeatNum());
        assertTrue(anotherSeat.isAvailable());
    }
}
