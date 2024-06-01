package lines;

import carriers.BusCarrier;
import seats.BusSeat;
import seats.SeatClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BusLineTest {

    private BusLine busLine;
    private BusSeat seat1;
    private BusSeat seat2;
    private List<BusSeat> seats;
    private BusCarrier carrier;

    @BeforeEach
    void setUp() {
        seat1 = new BusSeat(SeatClass.FIRST, 1, true);  // Assuming BusSeat has a constructor with SeatClass, seat number and availability
        seat2 = new BusSeat(SeatClass.SECOND, 2, false);
        seats = Arrays.asList(seat1, seat2);
        carrier = BusCarrier.FLIXBUS;  // Replace with an actual carrier from your BusCarrier enum
        busLine = new BusLine(seats, 50.0, 30.0, carrier);
    }

    @Test
    void testGetCarrier() {
        assertEquals(carrier, busLine.getCarrier());
    }

    @Test
    void testGetElectricalOutletSeatPriceDefault() {
        assertEquals(0.0, busLine.getElectricalOutletSeatPrice());
    }

    @Test
    void testSetAndGetElectricalOutletSeatPrice() {
        double newPrice = 5.0;
        busLine.setElectricalOutletSeatPrice(newPrice);
        assertEquals(newPrice, busLine.getElectricalOutletSeatPrice());
    }

    @Test
    void testConstructorWithTwoPrices() {
        BusLine newBusLine = new BusLine(seats, 50.0, 30.0, carrier);
        assertEquals(50.0, newBusLine.getFirstClassPrice());
        assertEquals(30.0, newBusLine.getSecondClassPrice());
    }

    @Test
    void testConstructorWithOnePrice() {
        BusLine newBusLine = new BusLine(seats, 50.0, carrier);
        assertEquals(50.0, newBusLine.getFirstClassPrice());
        assertNull(newBusLine.getSecondClassPrice());
    }
}
