package lines;

import carriers.TrainCarrier;
import seats.TrainSeat;
import seats.SeatClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TrainLineTest {

    private TrainLine trainLine;
    private TrainSeat seat1;
    private TrainSeat seat2;
    private List<TrainSeat> seats;
    private TrainCarrier carrier;

    @BeforeEach
    void setUp() {
        seat1 = new TrainSeat(SeatClass.FIRST, 1, true, 0);  // Assuming TrainSeat has a constructor with SeatClass, seat number, availability, and an additional int
        seat2 = new TrainSeat(SeatClass.SECOND, 2, false, 0);
        seats = Arrays.asList(seat1, seat2);
        carrier = TrainCarrier.PKP_INTERCITY;  // Replace with an actual carrier from your TrainCarrier enum
        trainLine = new TrainLine(seats, 200.0, 100.0, carrier);
    }

    @Test
    void testGetCarrier() {
        assertEquals(carrier, trainLine.getCarrier());
    }

    @Test
    void testGetPetPriceDefault() {
        assertEquals(0.0, trainLine.getPetPrice());
    }

    @Test
    void testSetAndGetPetPrice() {
        double newPrice = 20.0;
        trainLine.setPetPrice(newPrice);
        assertEquals(newPrice, trainLine.getPetPrice());
    }

    @Test
    void testGetBicyclePriceDefault() {
        assertEquals(0.0, trainLine.getBicyclePrice());
    }

    @Test
    void testSetAndGetBicyclePrice() {
        double newPrice = 30.0;
        trainLine.setBicyclePrice(newPrice);
        assertEquals(newPrice, trainLine.getBicyclePrice());
    }

    @Test
    void testGetTableSeatPriceDefault() {
        assertEquals(0.0, trainLine.getTableSeatPrice());
    }

    @Test
    void testSetAndGetTableSeatPrice() {
        double newPrice = 40.0;
        trainLine.setTableSeatPrice(newPrice);
        assertEquals(newPrice, trainLine.getTableSeatPrice());
    }

    @Test
    void testConstructorWithTwoPrices() {
        TrainLine newTrainLine = new TrainLine(seats, 200.0, 100.0, carrier);
        assertEquals(200.0, newTrainLine.getFirstClassPrice());
        assertEquals(100.0, newTrainLine.getSecondClassPrice());
    }

    @Test
    void testConstructorWithOnePrice() {
        TrainLine newTrainLine = new TrainLine(seats, 200.0, carrier);
        assertEquals(200.0, newTrainLine.getFirstClassPrice());
        assertNull(newTrainLine.getSecondClassPrice());
    }
}
