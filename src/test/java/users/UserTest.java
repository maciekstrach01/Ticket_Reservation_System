package users;

import reservations.Reservation;
import reservations.Ticket;
import lines.PlaneLine;
import seats.PlaneSeat;
import seats.SeatClass;
import carriers.PlaneCarrier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private User user;
    private Reservation reservation;
    private PlaneLine planeLine;

    @BeforeEach
    void setUp() {
        user = new User("test@example.com", "hashedpassword123");

        PlaneSeat planeSeat = new PlaneSeat(SeatClass.FIRST, 1, true);
        List<PlaneSeat> seats = List.of(planeSeat);
        planeLine = new PlaneLine(seats, 200.0, 100.0, PlaneCarrier.EMIRATES_AIRLINE);
        planeLine.setDepartDate(LocalDateTime.now().plusDays(2));
        reservation = new Reservation("RES123", planeLine);
        user.addReservation(reservation);
    }

    @Test
    void testGetEmail() {
        assertEquals("test@example.com", user.getEmail());
    }

    @Test
    void testGetSetFirstname() {
        user.setFirstname("John");
        assertEquals("John", user.getFirstname());
    }

    @Test
    void testGetSetLastname() {
        user.setLastname("Doe");
        assertEquals("Doe", user.getLastname());
    }

    @Test
    void testGetReservations() {
        assertEquals(1, user.getReservations().size());
        assertTrue(user.getReservations().contains(reservation));
    }

    @Test
    void testAddRemoveReservation() {
        Reservation newReservation = new Reservation("RES124", planeLine);
        user.addReservation(newReservation);
        assertEquals(2, user.getReservations().size());
        assertTrue(user.getReservations().contains(newReservation));

        user.removeReservation(newReservation);
        assertEquals(1, user.getReservations().size());
        assertFalse(user.getReservations().contains(newReservation));
    }

    @Test
    void testGetValidTickets() {
        // Create a ticket and add it to the reservation
        Ticket ticket = new Ticket("TICKET123", reservation) {
            @Override
            public Double getPrice() {
                return 100.0;
            }
        };
        reservation.addTicket(ticket);
        reservation.setPaid(true);

        List<Ticket> validTickets = user.getValidTickets();
        assertEquals(1, validTickets.size());
        assertTrue(validTickets.contains(ticket));
    }

    @Test
    void testDoPasswordsMatch() {
        assertTrue(user.doPasswordsMatch("hashedpassword123"));
        assertFalse(user.doPasswordsMatch("wrongpassword"));
    }

    @Test
    void testGetSetUserType() {
        user.setUserType(UserType.ADMINISTRATOR);
        assertEquals(UserType.ADMINISTRATOR, user.getUserType());

        user.setUserType(UserType.PASSENGER);
        assertEquals(UserType.PASSENGER, user.getUserType());
    }
}
