package lines;

import carriers.PlaneCarrier;
import seats.PlaneSeat;
import seats.SeatClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LineTest {

    private TestLine testLine;
    private PlaneSeat seat1;
    private PlaneSeat seat2;
    private List<PlaneSeat> seats;
    private PlaneCarrier carrier;

    @BeforeEach
    void setUp() {
        seat1 = new PlaneSeat(SeatClass.FIRST, 1, true);  // Assuming PlaneSeat has a constructor with SeatClass, seat number, and availability
        seat2 = new PlaneSeat(SeatClass.SECOND, 2, false);
        seats = Arrays.asList(seat1, seat2);
        carrier = PlaneCarrier.EMIRATES_AIRLINE;  // Replace with an actual carrier from your PlaneCarrier enum
        testLine = new TestLine(seats, 1000.0, 500.0, carrier);
    }

    @Test
    void testGetSeats() {
        assertEquals(seats, testLine.getSeats());
    }

    @Test
    void testGetFirstClassPrice() {
        assertEquals(1000.0, testLine.getFirstClassPrice());
    }

    @Test
    void testGetSecondClassPrice() {
        assertEquals(500.0, testLine.getSecondClassPrice());
    }

    @Test
    void testGetCarrier() {
        assertEquals(carrier, testLine.getCarrier());
    }

    @Test
    void testDepartDate() {
        LocalDateTime departDate = LocalDateTime.of(2024, 6, 1, 12, 0);
        testLine.setDepartDate(departDate);
        assertEquals(departDate, testLine.getDepartDate());
    }

    @Test
    void testArrivalDate() {
        LocalDateTime arrivalDate = LocalDateTime.of(2024, 6, 1, 14, 0);
        testLine.setArrivalDate(arrivalDate);
        assertEquals(arrivalDate, testLine.getArrivalDate());
    }

    @Test
    void testDepartCity() {
        City departCity = City.KRAKOW;
        testLine.setDepartCity(departCity);
        assertEquals(departCity, testLine.getDepartCity());
    }

    @Test
    void testArrivalCity() {
        City arrivalCity = City.WARSZAWA;
        testLine.setArrivalCity(arrivalCity);
        assertEquals(arrivalCity, testLine.getArrivalCity());
    }
}
