package reservations;

import seats.Seat;
import seats.SeatClass;
import seats.PlaneSeat;
import lines.PlaneLine;
import carriers.PlaneCarrier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TicketTest {

    private Ticket ticket;
    private Reservation reservation;
    private PlaneSeat planeSeat;

    // Concrete subclass for testing
    class TestTicket extends Ticket {
        public TestTicket(String ticketId, Reservation reservation) {
            super(ticketId, reservation);
        }

        @Override
        public Double getPrice() {
            return 100.0;
        }
    }

    @BeforeEach
    void setUp() {
        planeSeat = new PlaneSeat(SeatClass.FIRST, 1, true);
        List<PlaneSeat> seats = List.of(planeSeat);
        PlaneLine planeLine = new PlaneLine(seats, 200.0, 100.0, PlaneCarrier.EMIRATES_AIRLINE);
        reservation = new Reservation("RES123", planeLine);
        ticket = new TestTicket("TICKET123", reservation);
        ticket.setSeat(planeSeat);
    }

    @Test
    void testGetTicketId() {
        assertEquals("TICKET123", ticket.getTicketId());
    }

    @Test
    void testGetReservation() {
        assertEquals(reservation, ticket.getReservation());
    }

    @Test
    void testGetDiscount() {
        assertNull(ticket.getDiscount());
        ticket.setDiscount(Discount.SENIOR);
        assertEquals(Discount.SENIOR, ticket.getDiscount());
    }

    @Test
    void testSetDiscount() {
        ticket.setDiscount(Discount.SENIOR);
        assertEquals(Discount.SENIOR, ticket.getDiscount());
    }

    @Test
    void testGetSeat() {
        assertEquals(planeSeat, ticket.getSeat());
    }

    @Test
    void testSetSeat() {
        PlaneSeat newSeat = new PlaneSeat(SeatClass.SECOND, 2, false);
        ticket.setSeat(newSeat);
        assertEquals(newSeat, ticket.getSeat());
    }

    @Test
    void testGetPrice() {
        assertEquals(100.0, ticket.getPrice());
    }
}
