package reservations;

import carriers.BusCarrier;
import lines.BusLine;
import seats.BusSeat;
import seats.SeatClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BusTicketTest {

    private Reservation reservation;
    private BusLine busLine;
    private BusSeat busSeat;
    private BusTicket busTicket;

    @BeforeEach
    void setUp() {
        busSeat = new BusSeat(SeatClass.FIRST, 1, true);  // Assuming BusSeat has a constructor with SeatClass, seat number, and availability
        List<BusSeat> seats = Arrays.asList(busSeat);
        busLine = new BusLine(seats, 100.0, 50.0, BusCarrier.FLIXBUS);
        busLine.setElectricalOutletSeatPrice(10.0);  // Make sure to set the price for electrical outlet seats
        reservation = new Reservation("RES123", busLine);

        busTicket = new BusTicket("TICKET123", reservation);
        busTicket.setSeat(busSeat);
    }

    @Test
    void testGetPriceWithoutDiscount() {
        Double price = busTicket.getPrice();
        assertEquals(110.0, price);  // 100.0 (first class) + 10.0 (electrical outlet)
    }

    @Test
    void testGetPriceWithDiscount() {
        busTicket.setDiscount(Discount.SENIOR);  // Using an enum value for Discount

        Double price = busTicket.getPrice();
        assertEquals(80.0, price);  // (100.0 (first class) + 10.0 (electrical outlet)) * 0.8 (20% discount for seniors)
    }


    @Test
    void testGetPriceWithSecondClass() {
        busSeat = new BusSeat(SeatClass.SECOND, 2, true);  // Second class seat
        busTicket.setSeat(busSeat);

        Double price = busTicket.getPrice();
        assertEquals(60.0, price);  // 50.0 (second class) + 10.0 (electrical outlet)
    }

    @Test
    void testGetPriceWithoutElectricalOutlet() {
        busSeat = new BusSeat(SeatClass.FIRST, 1, false);  // No electrical outlet
        busTicket.setSeat(busSeat);

        Double price = busTicket.getPrice();
        assertEquals(100.0, price);  // 100.0 (first class) without electrical outlet
    }
}
