package system;

import lines.Line;
import lines.PlaneLine;
import reservations.Reservation;
import seats.PlaneSeat;
import seats.SeatClass;
import carriers.PlaneCarrier;
import users.User;
import users.UserType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class ReservationSystemTest {

    private ReservationSystem reservationSystem;

    @BeforeEach
    void setUp() {
        reservationSystem = new ReservationSystem();
    }

    @Test
    void testAddUser() {
        User user = new User("john.doe@example.com", "password");
        user.setFirstname("John");
        user.setLastname("Doe");
        user.setUserType(UserType.PASSENGER);

        reservationSystem.addUser(user);

        assertEquals(1, reservationSystem.getUsers().size());
        assertEquals(user, reservationSystem.getUsers().get(0));
    }

    @Test
    void testRemoveUser() {
        User user = new User("john.doe@example.com", "password");
        user.setFirstname("John");
        user.setLastname("Doe");
        user.setUserType(UserType.PASSENGER);

        reservationSystem.addUser(user);
        reservationSystem.removeUser(user);

        assertEquals(0, reservationSystem.getUsers().size());
    }

    @Test
    void testAddLine() {
        PlaneLine planeLine = new PlaneLine(Collections.emptyList(), 200.0, 100.0, PlaneCarrier.EMIRATES_AIRLINE);

        reservationSystem.addLine(planeLine);

        assertEquals(1, reservationSystem.getLines().size());
        assertEquals(planeLine, reservationSystem.getLines().get(0));
    }

    @Test
    void testRemoveLine() {
        PlaneLine planeLine = new PlaneLine(Collections.emptyList(), 200.0, 100.0, PlaneCarrier.EMIRATES_AIRLINE);

        reservationSystem.addLine(planeLine);
        reservationSystem.removeLine(planeLine);

        assertEquals(0, reservationSystem.getLines().size());
    }

    @Test
    void testAddReservation() {
        PlaneLine planeLine = new PlaneLine(Collections.emptyList(), 200.0, 100.0, PlaneCarrier.EMIRATES_AIRLINE);
        Reservation reservation = new Reservation("RES123", planeLine);

        reservationSystem.addReservation(reservation);

        assertEquals(1, reservationSystem.getReservations().size());
        assertEquals(reservation, reservationSystem.getReservations().get(0));
    }

    @Test
    void testRemoveReservation() {
        PlaneLine planeLine = new PlaneLine(Collections.emptyList(), 200.0, 100.0, PlaneCarrier.EMIRATES_AIRLINE);
        Reservation reservation = new Reservation("RES123", planeLine);

        reservationSystem.addReservation(reservation);
        reservationSystem.removeReservation(reservation);

        assertEquals(0, reservationSystem.getReservations().size());
    }
}
