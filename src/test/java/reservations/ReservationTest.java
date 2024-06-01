package reservations;

import lines.Line;
import lines.PlaneLine;
import seats.PlaneSeat;
import seats.SeatClass;
import carriers.PlaneCarrier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReservationTest {

    private Reservation reservation;
    private Line<PlaneSeat, PlaneCarrier> planeLine;

    @BeforeEach
    void setUp() {
        PlaneSeat planeSeat = new PlaneSeat(SeatClass.FIRST, 1, true);
        List<PlaneSeat> seats = List.of(planeSeat);
        planeLine = new PlaneLine(seats, 200.0, 100.0, PlaneCarrier.EMIRATES_AIRLINE);
        reservation = new Reservation("RES123", planeLine);
    }

    @Test
    void testGetReservationId() {
        assertEquals("RES123", reservation.getReservationId());
    }

    @Test
    void testGetLine() {
        assertEquals(planeLine, reservation.getLine());
    }

    @Test
    void testAddTicket() {
        PlaneTicket planeTicket = new PlaneTicket("TICKET123", reservation, true, true, true);
        reservation.addTicket(planeTicket);
        assertEquals(1, reservation.getTickets().size());
        assertTrue(reservation.getTickets().contains(planeTicket));
    }

    @Test
    void testRemoveTicket() {
        PlaneTicket planeTicket = new PlaneTicket("TICKET123", reservation, true, true, true);
        reservation.addTicket(planeTicket);
        reservation.removeTicket(planeTicket);
        assertEquals(0, reservation.getTickets().size());
        assertFalse(reservation.getTickets().contains(planeTicket));
    }

    @Test
    void testGetBookingDate() {
        LocalDateTime now = LocalDateTime.now();
        assertTrue(reservation.getBookingDate().isBefore(now) || reservation.getBookingDate().isEqual(now));
    }

    @Test
    void testGetExpirationDate() {
        LocalDateTime expirationDate = reservation.getBookingDate().plusDays(1);
        assertEquals(expirationDate, reservation.getExpirationDate());
    }

    @Test
    void testIsPaid() {
        assertFalse(reservation.isPaid());
        reservation.setPaid(true);
        assertTrue(reservation.isPaid());
    }

    @Test
    void testSetPaid() {
        reservation.setPaid(true);
        assertTrue(reservation.isPaid());
        reservation.setPaid(false);
        assertFalse(reservation.isPaid());
    }
}
