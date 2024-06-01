//package system;
//
//import lines.PlaneLine;
//import users.User;
//import users.UserType;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.io.ByteArrayInputStream;
//import java.io.ByteArrayOutputStream;
//import java.io.PrintStream;
//import java.lang.reflect.Field;
//import java.util.Collections;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class ConsoleReservationSystemTest {
//
//    private ConsoleReservationSystem consoleReservationSystem;
//    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
//
//    @BeforeEach
//    void setUp() {
//        System.setOut(new PrintStream(outputStreamCaptor));
//        consoleReservationSystem = new ConsoleReservationSystem();
//    }
//
//    private ReservationSystem getReservationSystemFromConsole() throws Exception {
//        Field field = ConsoleReservationSystem.class.getDeclaredField("reservationSystem");
//        field.setAccessible(true);
//        return (ReservationSystem) field.get(consoleReservationSystem);
//    }
//
//    @Test
//    void testAddUser() throws Exception {
//        User user = new User("john.doe@example.com", "password");  // Assuming User constructor takes email and password
//        user.setFirstname("John");
//        user.setLastname("Doe");
//        user.setUserType(UserType.PASSENGER);
//        consoleReservationSystem.addUser(user);
//
//        ReservationSystem reservationSystem = getReservationSystemFromConsole();
//
//        assertEquals(1, reservationSystem.getUsers().size());
//        assertEquals(user, reservationSystem.getUsers().get(0));
//    }
//
//    @Test
//    void testAddLine() throws Exception {
//        PlaneLine planeLine = new PlaneLine(Collections.emptyList(), 200.0, 100.0, null);
//        consoleReservationSystem.addLine(planeLine);
//
//        ReservationSystem reservationSystem = getReservationSystemFromConsole();
//
//        assertEquals(1, reservationSystem.getLines().size());
//        assertEquals(planeLine, reservationSystem.getLines().get(0));
//    }
//
//    @Test
//    void testLoginSuccess() throws Exception {
//        User user = new User("john.doe@example.com", "password");  // Assuming User constructor takes email and password
//        user.setFirstname("John");
//        user.setLastname("Doe");
//        user.setUserType(UserType.PASSENGER);
//        ReservationSystem reservationSystem = getReservationSystemFromConsole();
//        reservationSystem.addUser(user);
//
//        String simulatedInput = "john.doe@example.com\npassword\n";
//        ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
//        System.setIn(inputStream);
//
//        consoleReservationSystem.startSystem();
//
//        String output = outputStreamCaptor.toString().trim();
//        assertTrue(output.contains("Witaj John Doe!"));
//    }
//
//    @Test
//    void testLoginFailure() throws Exception {
//        User user = new User("john.doe@example.com", "password");  // Assuming User constructor takes email and password
//        user.setFirstname("John");
//        user.setLastname("Doe");
//        user.setUserType(UserType.PASSENGER);
//        ReservationSystem reservationSystem = getReservationSystemFromConsole();
//        reservationSystem.addUser(user);
//
//        String simulatedInput = "john.doe@example.com\nwrongpassword\njohn.doe@example.com\npassword\n";
//        ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
//        System.setIn(inputStream);
//
//        consoleReservationSystem.startSystem();
//
//        String output = outputStreamCaptor.toString().trim();
//        assertTrue(output.contains("Błędne hasło!"));
//        assertTrue(output.contains("Witaj John Doe!"));
//    }
//}
package system;

import lines.PlaneLine;
import users.User;
import users.UserType;
import utils.Hashing;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class ConsoleReservationSystemTest {

    private ConsoleReservationSystem consoleReservationSystem;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        consoleReservationSystem = new ConsoleReservationSystem();
    }

    private ReservationSystem getReservationSystemFromConsole() throws Exception {
        Field field = ConsoleReservationSystem.class.getDeclaredField("reservationSystem");
        field.setAccessible(true);
        return (ReservationSystem) field.get(consoleReservationSystem);
    }

    @Test
    void testAddUser() throws Exception {
        User user = new User("jan.kowalski@gmail.com", Hashing.hashPassword("hackme123"));
        user.setFirstname("Jan");
        user.setLastname("Kowalski");
        user.setUserType(UserType.PASSENGER);
        consoleReservationSystem.addUser(user);

        ReservationSystem reservationSystem = getReservationSystemFromConsole();

        assertEquals(1, reservationSystem.getUsers().size());
        assertEquals(user, reservationSystem.getUsers().get(0));
    }

    @Test
    void testAddLine() throws Exception {
        PlaneLine planeLine = new PlaneLine(Collections.emptyList(), 200.0, 100.0, null);
        consoleReservationSystem.addLine(planeLine);

        ReservationSystem reservationSystem = getReservationSystemFromConsole();

        assertEquals(1, reservationSystem.getLines().size());
        assertEquals(planeLine, reservationSystem.getLines().get(0));
    }

    @Test
    void testLoginSuccess() throws Exception {
        System.setOut(new PrintStream(outputStreamCaptor));  // Set output capturing only for this test

        User user = new User("jan.kowalski@gmail.com", Hashing.hashPassword("hackme123"));
        user.setFirstname("Jan");
        user.setLastname("Kowalski");
        user.setUserType(UserType.PASSENGER);
        ReservationSystem reservationSystem = getReservationSystemFromConsole();
        reservationSystem.addUser(user);

        String simulatedInput = "jan.kowalski@gmail.com\nhackme123\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(inputStream);

        consoleReservationSystem.startSystem();

        String output = outputStreamCaptor.toString().trim();
        assertTrue(output.contains("Witaj Jan Kowalski!"));

        System.setOut(System.out);  // Reset output capturing
    }

    @Test
    void testLoginFailure() throws Exception {
        System.setOut(new PrintStream(outputStreamCaptor));  // Set output capturing only for this test

        User user = new User("jan.kowalski@gmail.com", Hashing.hashPassword("hackme123"));
        user.setFirstname("Jan");
        user.setLastname("Kowalski");
        user.setUserType(UserType.PASSENGER);
        ReservationSystem reservationSystem = getReservationSystemFromConsole();
        reservationSystem.addUser(user);

        String simulatedInput = "jan.kowalski@gmail.com\nwrongpassword\njan.kowalski@gmail.com\nhackme123\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(inputStream);

        consoleReservationSystem.startSystem();

        String output = outputStreamCaptor.toString().trim();
        assertTrue(output.contains("Błędne hasło!"));
        assertTrue(output.contains("Witaj Jan Kowalski!"));

        System.setOut(System.out);  // Reset output capturing
    }
}
