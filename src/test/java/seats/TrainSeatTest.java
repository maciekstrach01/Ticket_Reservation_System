package seats;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TrainSeatTest {

    private TrainSeat trainSeat;

    @BeforeEach
    void setUp() {
        trainSeat = new TrainSeat(SeatClass.FIRST, 1, true, 5);
    }

    @Test
    void testGetSeatClass() {
        assertEquals(SeatClass.FIRST, trainSeat.getSeatClass());
    }

    @Test
    void testGetSeatNum() {
        assertEquals(1, trainSeat.getSeatNum());
    }

    @Test
    void testHasTable() {
        assertTrue(trainSeat.hasTable());
    }

    @Test
    void testGetCarNum() {
        assertEquals(5, trainSeat.getCarNum());
    }

    @Test
    void testConstructor() {
        TrainSeat anotherTrainSeat = new TrainSeat(SeatClass.SECOND, 2, false, 3);
        assertEquals(SeatClass.SECOND, anotherTrainSeat.getSeatClass());
        assertEquals(2, anotherTrainSeat.getSeatNum());
        assertFalse(anotherTrainSeat.hasTable());
        assertEquals(3, anotherTrainSeat.getCarNum());
    }
}
