import carriers.BusCarrier;
import carriers.PlaneCarrier;
import carriers.TrainCarrier;
import lines.BusLine;
import lines.City;
import lines.PlaneLine;
import lines.TrainLine;
import reservations.BusTicket;
import reservations.Discount;
import reservations.Reservation;
import seats.BusSeat;
import seats.PlaneSeat;
import seats.SeatClass;
import seats.TrainSeat;
import system.ConsoleReservationSystem;
import users.User;
import users.UserType;
import utils.Dates;
import utils.Hashing;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
        // Initialize the ReservationSystem
        ConsoleReservationSystem consoleReservationSystem = new ConsoleReservationSystem();

        // Create a passenger
        User passenger = new User("jan.kowalski@gmail.com", Hashing.hashPassword("hackme123"));
        passenger.setFirstname("Jan");
        passenger.setLastname("Kowalski");
        passenger.setUserType(UserType.PASSENGER);

        consoleReservationSystem.addUser(passenger);

        // Create an administrator
        User administrator = new User("edyta.nowak@gmail.com", Hashing.hashPassword("hackme123"));
        administrator.setFirstname("Edyta");
        administrator.setLastname("Nowak");
        administrator.setUserType(UserType.ADMINISTRATOR);

        consoleReservationSystem.addUser(administrator);

        // Create a sample bus line
        List<BusSeat> busSeats = new ArrayList<>();
        for (int i = 1; i <= 30; i++)
            busSeats.add(new BusSeat(SeatClass.FIRST, i, false));

        BusLine NsKrkLine = new BusLine(busSeats, 30.0, BusCarrier.SZWAGROPOL);
        NsKrkLine.setDepartCity(City.NOWY_SACZ);
        NsKrkLine.setArrivalCity(City.KRAKOW);
        NsKrkLine.setDepartDate(Dates.dateFromString("2023-02-02 12:05:00"));
        NsKrkLine.setArrivalDate(Dates.dateFromString("2023-02-02 14:59:00"));

        consoleReservationSystem.addLine(NsKrkLine);

        // Create a sample train line
        List<TrainSeat> trainSeats = new ArrayList<>();
        for (int i = 1; i <= 30; i++) {
            trainSeats.add(new TrainSeat(SeatClass.FIRST, i, false, i % 10));
            trainSeats.add(new TrainSeat(SeatClass.SECOND, i + 30, false, i % 10));
        }

        TrainLine KrkZawLine = new TrainLine(trainSeats, 64.0, 42.0, TrainCarrier.KOLEJE_SLASKIE);
        KrkZawLine.setDepartCity(City.KRAKOW);
        KrkZawLine.setArrivalCity(City.ZAWIERCIE);
        KrkZawLine.setDepartDate(Dates.dateFromString("2023-02-02 16:23:00"));
        KrkZawLine.setArrivalDate(Dates.dateFromString("2023-02-02 17:26:00"));

        consoleReservationSystem.addLine(KrkZawLine);

        // Create a sample plane line
        List<PlaneSeat> planeSeats = new ArrayList<>();
        for (int i = 1; i <= 50; i++) {
            planeSeats.add(new PlaneSeat(SeatClass.FIRST, i, false));
            planeSeats.add(new PlaneSeat(SeatClass.SECOND, i + 50, false));
        }

        PlaneLine KrkWawLine = new PlaneLine(planeSeats, 360.0, 280.0, PlaneCarrier.RYANAIR);
        KrkWawLine.setDepartCity(City.KRAKOW);
        KrkWawLine.setArrivalCity(City.WARSZAWA);
        KrkWawLine.setDepartDate(Dates.dateFromString("2023-02-02 17:30:00"));
        KrkWawLine.setArrivalDate(Dates.dateFromString("2023-02-02 17:57:00"));

        consoleReservationSystem.addLine(KrkWawLine);

        // Create a first reservation
        Reservation reservation = new Reservation("2023/01/31/1", NsKrkLine);
        reservation.setPaid(true);

        BusSeat seat = busSeats.get(0);
        seat.setAvailable(false);

        BusTicket ticket = new BusTicket("2023/01/31/1/1", reservation);
        ticket.setDiscount(Discount.STUDENT);
        ticket.setSeat(seat);

        reservation.addTicket(ticket);

        passenger.addReservation(reservation);

        // Start the system
        consoleReservationSystem.startSystem();
    }
}
