package lines;

import carriers.PlaneCarrier;
import seats.PlaneSeat;
import seats.SeatClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlaneLineTest {

    private PlaneLine planeLine;
    private PlaneSeat seat1;
    private PlaneSeat seat2;
    private List<PlaneSeat> seats;
    private PlaneCarrier carrier;

    @BeforeEach
    void setUp() {
        seat1 = new PlaneSeat(SeatClass.FIRST, 1, true);  // Assuming PlaneSeat has a constructor with SeatClass, seat number and availability
        seat2 = new PlaneSeat(SeatClass.SECOND, 2, false);
        seats = Arrays.asList(seat1, seat2);
        carrier = PlaneCarrier.EMIRATES_AIRLINE;  // Replace with an actual carrier from your PlaneCarrier enum
        planeLine = new PlaneLine(seats, 1000.0, 500.0, carrier);
    }

    @Test
    void testGetCarrier() {
        assertEquals(carrier, planeLine.getCarrier());
    }

    @Test
    void testGetAdditionalLuggagePriceDefault() {
        assertEquals(0.0, planeLine.getAdditionalLuggagePrice());
    }

    @Test
    void testSetAndGetAdditionalLuggagePrice() {
        double newPrice = 100.0;
        planeLine.setAdditionalLuggagePrice(newPrice);
        assertEquals(newPrice, planeLine.getAdditionalLuggagePrice());
    }

    @Test
    void testGetInCabinPetPriceDefault() {
        assertEquals(0.0, planeLine.getInCabinPetPrice());
    }

    @Test
    void testSetAndGetInCabinPetPrice() {
        double newPrice = 200.0;
        planeLine.setInCabinPetPrice(newPrice);
        assertEquals(newPrice, planeLine.getInCabinPetPrice());
    }

    @Test
    void testGetInBaggageHoldPetPriceDefault() {
        assertEquals(0.0, planeLine.getInBaggageHoldPetPrice());
    }

    @Test
    void testSetAndGetInBaggageHoldPetPrice() {
        double newPrice = 300.0;
        planeLine.setInBaggageHoldPetPrice(newPrice);
        assertEquals(newPrice, planeLine.getInBaggageHoldPetPrice());
    }

    @Test
    void testGetMoreSpaceSeatPriceDefault() {
        assertEquals(0.0, planeLine.getMoreSpaceSeatPrice());
    }

    @Test
    void testSetAndGetMoreSpaceSeatPrice() {
        double newPrice = 400.0;
        planeLine.setMoreSpaceSeatPrice(newPrice);
        assertEquals(newPrice, planeLine.getMoreSpaceSeatPrice());
    }

    @Test
    void testConstructorWithTwoPrices() {
        PlaneLine newPlaneLine = new PlaneLine(seats, 1000.0, 500.0, carrier);
        assertEquals(1000.0, newPlaneLine.getFirstClassPrice());
        assertEquals(500.0, newPlaneLine.getSecondClassPrice());
    }

    @Test
    void testConstructorWithOnePrice() {
        PlaneLine newPlaneLine = new PlaneLine(seats, 1000.0, carrier);
        assertEquals(1000.0, newPlaneLine.getFirstClassPrice());
        assertNull(newPlaneLine.getSecondClassPrice());
    }
}
