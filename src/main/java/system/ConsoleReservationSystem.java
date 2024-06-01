package system;

import lines.*;
import reservations.*;
import seats.BusSeat;
import seats.PlaneSeat;
import seats.Seat;
import seats.TrainSeat;
import users.User;
import users.UserType;
import utils.Hashing;
import utils.SystemVisualiser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;

public class ConsoleReservationSystem {
    private final ReservationSystem reservationSystem;
    private User loggedUser;

    public ConsoleReservationSystem() {
        this.reservationSystem = new ReservationSystem();
        this.loggedUser = null;
    }

    public void addUser(User user) {
        this.reservationSystem.addUser(user);
    }

    public void addLine(Line<?, ?> line) {
        this.reservationSystem.addLine(line);
    }

    public void startSystem() throws IOException, NoSuchAlgorithmException {
        System.out.println("Cześć! Witaj w systemie rezerwacji biletów!\n");

        this.showLoginMenu();

        if (this.loggedUser.getUserType() == UserType.ADMINISTRATOR)
            this.showAdministratorMenu();
        else if (this.loggedUser.getUserType() == UserType.PASSENGER)
            this.showPassengerMenu();
    }

    private void showLoginMenu() throws IOException, NoSuchAlgorithmException {
        System.out.println("Aby wejść do systemu, najpierw musisz się zalogować!");

        while (this.loggedUser == null) {
            System.out.print("Podaj adres email: ");

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String email = reader.readLine();

            List<User> foundUser = this.reservationSystem.getUsers().stream()
                    .filter(user -> user.getEmail().equals(email))
                    .toList();

            if (foundUser.size() == 0)
                System.out.println("W systemie nie ma takiego użytkownika!\n");
            else {
                System.out.print("Podaj hasło: ");

                String password = reader.readLine();

                if (foundUser.get(0).doPasswordsMatch(Hashing.hashPassword(password))) {
                    this.loggedUser = foundUser.get(0);
                    System.out.println("\nWitaj " + this.loggedUser.getFirstname() + " " + this.loggedUser.getLastname() + "!\n");
                } else
                    System.out.println("Błędne hasło!\n");
            }
        }
    }

    private void showAdministratorMenu() throws IOException {
        boolean hasExited = false;

        while (!hasExited) {
            List<String> mainOptions = Arrays.asList("Wyjdź z systemu", "Pokaż wszystkie rezerwacje",
                    "Pokaż wszystkie połączenia", "Pokaż wszystkich użytkowników");
            int chosenMainOption = this.getOption(mainOptions, "");

            switch (chosenMainOption) {
                case 0 -> hasExited = true;
                case 1 -> this.showAllReservations();
                case 2 -> this.showAllLines();
                case 3 -> this.showAllUsers();
            }
        }

        this.exitSystem();
    }

    private void showAllReservations() {
        // Show list of all reservations made by each user
        for (User user : this.reservationSystem.getUsers()) {
            System.out.println("\nRezerwacje użytkownika " + user.getFirstname() + " " + user.getLastname() + " <" +
                    user.getEmail() + ">:");

            if (user.getReservations().size() > 0) {
                System.out.println(SystemVisualiser.reservationVisualiserFormat());

                for (Reservation reservation : user.getReservations())
                    System.out.println(SystemVisualiser.reservationVisualiser(reservation));
            } else
                System.out.println("Brak rezerwacji!");
        }

        System.out.println();
    }

    private void showAllLines() {
        // Show list of all PlaneLines in the system
        System.out.println("\nLista wszystkich połączeń lotniczych:");
        List<PlaneLine> planeLines = this.reservationSystem.getLines().stream()
                .filter(line -> line instanceof PlaneLine)
                .map(line -> (PlaneLine) line)
                .toList();

        if (planeLines.size() > 0) {
            System.out.println(SystemVisualiser.planeLineVisualiserFormat());

            for (PlaneLine line : planeLines)
                System.out.println(SystemVisualiser.planeLineVisualiser(line));
        } else {
            System.out.println("Brak połączeń lotniczych!");
        }

        // Show list of all TrainLines in the system
        System.out.println("\nLista wszystkich połączeń kolejowych:");

        List<TrainLine> trainLines = this.reservationSystem.getLines().stream()
                .filter(line -> line instanceof TrainLine)
                .map(line -> (TrainLine) line)
                .toList();

        if (trainLines.size() > 0) {
            System.out.println(SystemVisualiser.trainLineVisualiserFormat());

            for (TrainLine line : trainLines)
                System.out.println(SystemVisualiser.trainLineVisualiser(line));
        } else {
            System.out.println("Brak połączeń kolejowych!");
        }

        // Show list of all BusLines in the system
        System.out.println("\nLista wszystkich połączeń autobusowych:");

        List<BusLine> busLines = this.reservationSystem.getLines().stream()
                .filter(line -> line instanceof BusLine)
                .map(line -> (BusLine) line)
                .toList();

        if (busLines.size() > 0) {
            System.out.println(SystemVisualiser.busLineVisualiserFormat());

            for (BusLine line : busLines)
                System.out.println(SystemVisualiser.busLineVisualiser(line));
        } else {
            System.out.println("Brak połączeń autobusowych!");
        }

        System.out.println();
    }

    private void showAllUsers() {
        // Show list of users in the system
        System.out.println("\nLista wszystkich użytkowników:\n");

        List<User> users = this.reservationSystem.getUsers();

        if (users.size() > 0) {
            System.out.println(SystemVisualiser.userVisualiserFormat());

            for (User user : users)
                System.out.println(SystemVisualiser.userVisualiser(user));
        } else {
            System.out.println("Brak użytkowników!");
        }

        System.out.println();
    }

    private void showPassengerMenu() throws IOException {
        boolean hasExited = false;

        while (!hasExited) {
            List<String> mainOptions = Arrays.asList("Wyjdź z systemu", "Pokaż moje bilety", "Zarezerwuj bilet");
            int chosenMainOption = this.getOption(mainOptions, "");

            switch (chosenMainOption) {
                case 0 -> hasExited = true;
                case 1 -> this.showMyTickets();
                case 2 -> this.makeReservation();
            }
        }

        this.exitSystem();
    }

    private void showMyTickets() {
        // Show list of my plane tickets
        System.out.println("\nLista Twoich biletów lotniczych:");

        List<PlaneTicket> planeTickets = this.loggedUser.getValidTickets().stream()
                .filter(ticket -> ticket instanceof PlaneTicket)
                .map(ticket -> (PlaneTicket) ticket)
                .toList();

        if (planeTickets.size() > 0) {
            System.out.println(SystemVisualiser.planeTicketVisualiserFormat());

            for (PlaneTicket ticket : planeTickets)
                System.out.println(SystemVisualiser.planeTicketVisualiser(ticket));
        } else
            System.out.println("Brak biletów lotniczych!");

        // Show list of my train tickets
        System.out.println("\nLista Twoich biletów kolejowych:");

        List<TrainTicket> trainTickets = this.loggedUser.getValidTickets().stream()
                .filter(ticket -> ticket instanceof TrainTicket)
                .map(ticket -> (TrainTicket) ticket)
                .toList();

        if (trainTickets.size() > 0) {
            System.out.println(SystemVisualiser.trainTicketVisualiserFormat());

            for (TrainTicket ticket : trainTickets)
                System.out.println(SystemVisualiser.trainTicketVisualiser(ticket));
        } else
            System.out.println("Brak biletów kolejowych!");

        // Show list of my bus tickets
        System.out.println("\nLista Twoich biletów autobusowych:");

        List<BusTicket> busTickets = this.loggedUser.getValidTickets().stream()
                .filter(ticket -> ticket instanceof BusTicket)
                .map(ticket -> (BusTicket) ticket)
                .toList();

        if (busTickets.size() > 0) {
            System.out.println(SystemVisualiser.busTicketVisualiserFormat());

            for (BusTicket ticket : busTickets)
                System.out.println(SystemVisualiser.busTicketVisualiser(ticket));
        } else
            System.out.println("Brak biletów autobusowych!");

        System.out.println();
    }

    private void makeReservation() throws IOException {
        System.out.println("\nRezerwacja biletów!\n");

        // Create available lines and filter them in next steps
        List<Line<?, ?>> availableLines = this.reservationSystem.getLines().stream().toList();

        // Choose a departure and arrival city
        List<String> cities = Arrays.stream(City.values()).map(City::getVisibleName).toList();

        System.out.println("Wybierz miasto początkowe:\n");
        City departureCity = Arrays.stream(City.values()).toList().get(this.getOption(cities, ""));

        System.out.println("\nWybierz miasto docelowe:\n");
        City arrivalCity = Arrays.stream(City.values()).toList().get(this.getOption(cities, ""));

        availableLines = availableLines.stream()
                .filter(line -> line.getDepartCity() == departureCity && line.getArrivalCity() == arrivalCity)
                .toList();

        if (availableLines.size() == 0) {
            System.out.println("\nW systemie nie ma takich połączeń!");
            return;
        }

        // Choose a type of line
        List<String> lineTypes = Arrays.asList("Linia lotnicza", "Linia kolejowa", "Linia autobusowa");

        System.out.println("\nWybierz typ linii:\n");
        int type = this.getOption(lineTypes, "");

        switch (type) {
            case 0 -> availableLines = availableLines.stream().filter(line -> line instanceof PlaneLine).toList();
            case 1 -> availableLines = availableLines.stream().filter(line -> line instanceof TrainLine).toList();
            case 2 -> availableLines = availableLines.stream().filter(line -> line instanceof BusLine).toList();
        }

        if (availableLines.size() == 0) {
            System.out.println("\nW systemie nie ma takich połączeń!");
            return;
        }

        // Choose a specific line
        List<String> lineOptions = switch (type) {
            case 0 -> availableLines.stream().map(line -> SystemVisualiser.planeLineVisualiser((PlaneLine) line)).toList();
            case 1 -> availableLines.stream().map(line -> SystemVisualiser.trainLineVisualiser((TrainLine) line)).toList();
            case 2 -> availableLines.stream().map(line -> SystemVisualiser.busLineVisualiser((BusLine) line)).toList();
            default -> throw new RuntimeException("Unexpected behaviour of the application!");
        };

        String lineOptionsDesc = switch (type) {
            case 0 -> SystemVisualiser.planeLineVisualiserFormat();
            case 1 -> SystemVisualiser.trainLineVisualiserFormat();
            case 2 -> SystemVisualiser.busLineVisualiserFormat();
            default -> throw new RuntimeException("Unexpected behaviour of the application!");
        };

        System.out.println("\nWybierz połączenie:\n");
        Line<?,?> line = availableLines.get(this.getOption(lineOptions, lineOptionsDesc));

        // Choose a specific seat
        List<Seat> availableSeats = switch (type) {
            case 0 -> ((PlaneLine) line).getSeats().stream().filter(Seat::isAvailable).map(seat -> (Seat) seat).toList();
            case 1 -> ((TrainLine) line).getSeats().stream().filter(Seat::isAvailable).map(seat -> (Seat) seat).toList();
            case 2 -> ((BusLine) line).getSeats().stream().filter(Seat::isAvailable).map(seat -> (Seat) seat).toList();
            default -> throw new RuntimeException("Unexpected behaviour of the application!");
        };

        if (availableSeats.size() == 0) {
            System.out.println("\nNa wybrane połączenie nie ma już miejsc!");
            return;
        }

        List<String> seatOptions = switch (type) {
            case 0 -> availableSeats.stream().map(seat -> SystemVisualiser.planeSeatVisualiser((PlaneSeat) seat)).toList();
            case 1 -> availableSeats.stream().map(seat -> SystemVisualiser.trainSeatVisualiser((TrainSeat) seat)).toList();
            case 2 -> availableSeats.stream().map(seat -> SystemVisualiser.busSeatVisualiser((BusSeat) seat)).toList();
            default -> throw new RuntimeException("Unexpected behaviour of the application!");
        };

        String seatOptionsDesc = switch (type) {
            case 0 -> SystemVisualiser.planeSeatVisualiserFormat();
            case 1 -> SystemVisualiser.trainSeatVisualiserFormat();
            case 2 -> SystemVisualiser.busSeatVisualiserFormat();
            default -> throw new RuntimeException("Unexpected behaviour of the application!");
        };

        System.out.println("\nWybierz miejsce:\n");
        Seat seat = availableSeats.get(this.getOption(seatOptions, seatOptionsDesc));

        // Create a new reservation
        Reservation reservation = new Reservation("2023/02/01/1", line);
        reservation.setPaid(true);

        Ticket ticket = switch (type) {
            case 0 -> new PlaneTicket("2023/02/01/1/1", reservation, true, false, false);
            case 1 -> new TrainTicket("2023/02/01/1/1", reservation, false, true);
            case 2 -> new BusTicket("2023/02/01/1/1", reservation);
            default -> throw new RuntimeException("Unexpected behaviour of the application!");
        };

        ticket.setDiscount(Discount.STUDENT);
        ticket.setSeat(seat);

        reservation.addTicket(ticket);

        this.loggedUser.addReservation(reservation);

        System.out.println("\nNowa rezerwacja została dodana!\n");
    }

    private void exitSystem() {
        this.loggedUser = null;

        System.out.println("\nDo zobaczenia!");
    }

    private int getOption(List<String> options, String description) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int noDigits = (int) Math.floor(Math.log10(options.size()) + 1) + 3;

        while (true) {
            if (!description.equals("")) {
                for (int i = 0; i < noDigits; i++)
                    System.out.print(" ");

                System.out.println(description);
            }

            for (int i = 0; i < options.size(); i++) {
                String optionNum = "[" + i + "]";

                System.out.print(optionNum);

                for (int j = 0; j < noDigits - optionNum.length(); j++)
                    System.out.print(" ");

                System.out.println(options.get(i));
            }

            System.out.print("\nTwój wybór: ");

            int option = -1;
            try {
                option = Integer.parseInt(reader.readLine());
            } catch (NumberFormatException ignored) { }

            if (option >= 0 && option < options.size())
                return option;
            else
                System.out.println("Błędna opcja!\n");
        }
    }
}
//package system;
//
//import lines.*;
//import reservations.*;
//import seats.BusSeat;
//import seats.PlaneSeat;
//import seats.Seat;
//import seats.TrainSeat;
//import users.User;
//import users.UserType;
//import utils.Hashing;
//import utils.SystemVisualiser;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.security.NoSuchAlgorithmException;
//import java.util.Arrays;
//import java.util.List;
//
//public class ConsoleReservationSystem {
//    private final ReservationSystem reservationSystem;
//    private User loggedUser;
//
//    public ConsoleReservationSystem() {
//        this.reservationSystem = new ReservationSystem();
//        this.loggedUser = null;
//    }
//
//    public void addUser(User user) {
//        this.reservationSystem.addUser(user);
//    }
//
//    public void addLine(Line<?, ?> line) {
//        this.reservationSystem.addLine(line);
//    }
//
//    // Add these getter methods
//    public List<User> getUsers() {
//        return this.reservationSystem.getUsers();
//    }
//
//    public List<Line<?, ?>> getLines() {
//        return this.reservationSystem.getLines();
//    }
//
//    public void startSystem() throws IOException, NoSuchAlgorithmException {
//        System.out.println("Cześć! Witaj w systemie rezerwacji biletów!\n");
//
//        this.showLoginMenu();
//
//        if (this.loggedUser.getUserType() == UserType.ADMINISTRATOR)
//            this.showAdministratorMenu();
//        else if (this.loggedUser.getUserType() == UserType.PASSENGER)
//            this.showPassengerMenu();
//    }
//
//    private void showLoginMenu() throws IOException, NoSuchAlgorithmException {
//        System.out.println("Aby wejść do systemu, najpierw musisz się zalogować!");
//
//        while (this.loggedUser == null) {
//            System.out.print("Podaj adres email: ");
//
//            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//            String email = reader.readLine();
//
//            List<User> foundUser = this.reservationSystem.getUsers().stream()
//                    .filter(user -> user.getEmail().equals(email))
//                    .toList();
//
//            if (foundUser.size() == 0)
//                System.out.println("W systemie nie ma takiego użytkownika!\n");
//            else {
//                System.out.print("Podaj hasło: ");
//
//                String password = reader.readLine();
//
//                if (foundUser.get(0).doPasswordsMatch(Hashing.hashPassword(password))) {
//                    this.loggedUser = foundUser.get(0);
//                    System.out.println("\nWitaj " + this.loggedUser.getFirstname() + " " + this.loggedUser.getLastname() + "!\n");
//                } else
//                    System.out.println("Błędne hasło!\n");
//            }
//        }
//    }
//
//    private void showAdministratorMenu() throws IOException {
//        boolean hasExited = false;
//
//        while (!hasExited) {
//            List<String> mainOptions = Arrays.asList("Wyjdź z systemu", "Pokaż wszystkie rezerwacje",
//                    "Pokaż wszystkie połączenia", "Pokaż wszystkich użytkowników");
//            int chosenMainOption = this.getOption(mainOptions, "");
//
//            switch (chosenMainOption) {
//                case 0 -> hasExited = true;
//                case 1 -> this.showAllReservations();
//                case 2 -> this.showAllLines();
//                case 3 -> this.showAllUsers();
//            }
//        }
//
//        this.exitSystem();
//    }
//
//    private void showAllReservations() {
//        // Show list of all reservations made by each user
//        for (User user : this.reservationSystem.getUsers()) {
//            System.out.println("\nRezerwacje użytkownika " + user.getFirstname() + " " + user.getLastname() + " <" +
//                    user.getEmail() + ">:");
//
//            if (user.getReservations().size() > 0) {
//                System.out.println(SystemVisualiser.reservationVisualiserFormat());
//
//                for (Reservation reservation : user.getReservations())
//                    System.out.println(SystemVisualiser.reservationVisualiser(reservation));
//            } else
//                System.out.println("Brak rezerwacji!");
//        }
//
//        System.out.println();
//    }
//
//    private void showAllLines() {
//        // Show list of all PlaneLines in the system
//        System.out.println("\nLista wszystkich połączeń lotniczych:");
//        List<PlaneLine> planeLines = this.reservationSystem.getLines().stream()
//                .filter(line -> line instanceof PlaneLine)
//                .map(line -> (PlaneLine) line)
//                .toList();
//
//        if (planeLines.size() > 0) {
//            System.out.println(SystemVisualiser.planeLineVisualiserFormat());
//
//            for (PlaneLine line : planeLines)
//                System.out.println(SystemVisualiser.planeLineVisualiser(line));
//        } else {
//            System.out.println("Brak połączeń lotniczych!");
//        }
//
//        // Show list of all TrainLines in the system
//        System.out.println("\nLista wszystkich połączeń kolejowych:");
//
//        List<TrainLine> trainLines = this.reservationSystem.getLines().stream()
//                .filter(line -> line instanceof TrainLine)
//                .map(line -> (TrainLine) line)
//                .toList();
//
//        if (trainLines.size() > 0) {
//            System.out.println(SystemVisualiser.trainLineVisualiserFormat());
//
//            for (TrainLine line : trainLines)
//                System.out.println(SystemVisualiser.trainLineVisualiser(line));
//        } else {
//            System.out.println("Brak połączeń kolejowych!");
//        }
//
//        // Show list of all BusLines in the system
//        System.out.println("\nLista wszystkich połączeń autobusowych:");
//
//        List<BusLine> busLines = this.reservationSystem.getLines().stream()
//                .filter(line -> line instanceof BusLine)
//                .map(line -> (BusLine) line)
//                .toList();
//
//        if (busLines.size() > 0) {
//            System.out.println(SystemVisualiser.busLineVisualiserFormat());
//
//            for (BusLine line : busLines)
//                System.out.println(SystemVisualiser.busLineVisualiser(line));
//        } else {
//            System.out.println("Brak połączeń autobusowych!");
//        }
//
//        System.out.println();
//    }
//
//    private void showAllUsers() {
//        // Show list of users in the system
//        System.out.println("\nLista wszystkich użytkowników:\n");
//
//        List<User> users = this.reservationSystem.getUsers();
//
//        if (users.size() > 0) {
//            System.out.println(SystemVisualiser.userVisualiserFormat());
//
//            for (User user : users)
//                System.out.println(SystemVisualiser.userVisualiser(user));
//        } else {
//            System.out.println("Brak użytkowników!");
//        }
//
//        System.out.println();
//    }
//
//    private void showPassengerMenu() throws IOException {
//        boolean hasExited = false;
//
//        while (!hasExited) {
//            List<String> mainOptions = Arrays.asList("Wyjdź z systemu", "Pokaż moje bilety", "Zarezerwuj bilet");
//            int chosenMainOption = this.getOption(mainOptions, "");
//
//            switch (chosenMainOption) {
//                case 0 -> hasExited = true;
//                case 1 -> this.showMyTickets();
//                case 2 -> this.makeReservation();
//            }
//        }
//
//        this.exitSystem();
//    }
//
//    private void showMyTickets() {
//        // Show list of my plane tickets
//        System.out.println("\nLista Twoich biletów lotniczych:");
//
//        List<PlaneTicket> planeTickets = this.loggedUser.getValidTickets().stream()
//                .filter(ticket -> ticket instanceof PlaneTicket)
//                .map(ticket -> (PlaneTicket) ticket)
//                .toList();
//
//        if (planeTickets.size() > 0) {
//            System.out.println(SystemVisualiser.planeTicketVisualiserFormat());
//
//            for (PlaneTicket ticket : planeTickets)
//                System.out.println(SystemVisualiser.planeTicketVisualiser(ticket));
//        } else
//            System.out.println("Brak biletów lotniczych!");
//
//        // Show list of my train tickets
//        System.out.println("\nLista Twoich biletów kolejowych:");
//
//        List<TrainTicket> trainTickets = this.loggedUser.getValidTickets().stream()
//                .filter(ticket -> ticket instanceof TrainTicket)
//                .map(ticket -> (TrainTicket) ticket)
//                .toList();
//
//        if (trainTickets.size() > 0) {
//            System.out.println(SystemVisualiser.trainTicketVisualiserFormat());
//
//            for (TrainTicket ticket : trainTickets)
//                System.out.println(SystemVisualiser.trainTicketVisualiser(ticket));
//        } else
//            System.out.println("Brak biletów kolejowych!");
//
//        // Show list of my bus tickets
//        System.out.println("\nLista Twoich biletów autobusowych:");
//
//        List<BusTicket> busTickets = this.loggedUser.getValidTickets().stream()
//                .filter(ticket -> ticket instanceof BusTicket)
//                .map(ticket -> (BusTicket) ticket)
//                .toList();
//
//        if (busTickets.size() > 0) {
//            System.out.println(SystemVisualiser.busTicketVisualiserFormat());
//
//            for (BusTicket ticket : busTickets)
//                System.out.println(SystemVisualiser.busTicketVisualiser(ticket));
//        } else
//            System.out.println("Brak biletów autobusowych!");
//
//        System.out.println();
//    }
//
//    private void makeReservation() throws IOException {
//        System.out.println("\nRezerwacja biletów!\n");
//
//        // Create available lines and filter them in next steps
//        List<Line<?, ?>> availableLines = this.reservationSystem.getLines().stream().toList();
//
//        // Choose a departure and arrival city
//        List<String> cities = Arrays.stream(City.values()).map(City::getVisibleName).toList();
//
//        System.out.println("Wybierz miasto początkowe:\n");
//        City departureCity = Arrays.stream(City.values()).toList().get(this.getOption(cities, ""));
//
//        System.out.println("\nWybierz miasto docelowe:\n");
//        City arrivalCity = Arrays.stream(City.values()).toList().get(this.getOption(cities, ""));
//
//        availableLines = availableLines.stream()
//                .filter(line -> line.getDepartCity() == departureCity && line.getArrivalCity() == arrivalCity)
//                .toList();
//
//        if (availableLines.size() == 0) {
//            System.out.println("\nW systemie nie ma takich połączeń!");
//            return;
//        }
//
//        // Choose a type of line
//        List<String> lineTypes = Arrays.asList("Linia lotnicza", "Linia kolejowa", "Linia autobusowa");
//
//        System.out.println("\nWybierz typ linii:\n");
//        int type = this.getOption(lineTypes, "");
//
//        switch (type) {
//            case 0 -> availableLines = availableLines.stream().filter(line -> line instanceof PlaneLine).toList();
//            case 1 -> availableLines = availableLines.stream().filter(line -> line instanceof TrainLine).toList();
//            case 2 -> availableLines = availableLines.stream().filter(line -> line instanceof BusLine).toList();
//        }
//
//        if (availableLines.size() == 0) {
//            System.out.println("\nW systemie nie ma takich połączeń!");
//            return;
//        }
//
//        // Choose a specific line
//        List<String> lineOptions = switch (type) {
//            case 0 -> availableLines.stream().map(line -> SystemVisualiser.planeLineVisualiser((PlaneLine) line)).toList();
//            case 1 -> availableLines.stream().map(line -> SystemVisualiser.trainLineVisualiser((TrainLine) line)).toList();
//            case 2 -> availableLines.stream().map(line -> SystemVisualiser.busLineVisualiser((BusLine) line)).toList();
//            default -> throw new RuntimeException("Unexpected behaviour of the application!");
//        };
//
//        String lineOptionsDesc = switch (type) {
//            case 0 -> SystemVisualiser.planeLineVisualiserFormat();
//            case 1 -> SystemVisualiser.trainLineVisualiserFormat();
//            case 2 -> SystemVisualiser.busLineVisualiserFormat();
//            default -> throw new RuntimeException("Unexpected behaviour of the application!");
//        };
//
//        System.out.println("\nWybierz połączenie:\n");
//        Line<?,?> line = availableLines.get(this.getOption(lineOptions, lineOptionsDesc));
//
//        // Choose a specific seat
//        List<Seat> availableSeats = switch (type) {
//            case 0 -> ((PlaneLine) line).getSeats().stream().filter(Seat::isAvailable).map(seat -> (Seat) seat).toList();
//            case 1 -> ((TrainLine) line).getSeats().stream().filter(Seat::isAvailable).map(seat -> (Seat) seat).toList();
//            case 2 -> ((BusLine) line).getSeats().stream().filter(Seat::isAvailable).map(seat -> (Seat) seat).toList();
//            default -> throw new RuntimeException("Unexpected behaviour of the application!");
//        };
//
//        if (availableSeats.size() == 0) {
//            System.out.println("\nNa wybrane połączenie nie ma już miejsc!");
//            return;
//        }
//
//        List<String> seatOptions = switch (type) {
//            case 0 -> availableSeats.stream().map(seat -> SystemVisualiser.planeSeatVisualiser((PlaneSeat) seat)).toList();
//            case 1 -> availableSeats.stream().map(seat -> SystemVisualiser.trainSeatVisualiser((TrainSeat) seat)).toList();
//            case 2 -> availableSeats.stream().map(seat -> SystemVisualiser.busSeatVisualiser((BusSeat) seat)).toList();
//            default -> throw new RuntimeException("Unexpected behaviour of the application!");
//        };
//
//        String seatOptionsDesc = switch (type) {
//            case 0 -> SystemVisualiser.planeSeatVisualiserFormat();
//            case 1 -> SystemVisualiser.trainSeatVisualiserFormat();
//            case 2 -> SystemVisualiser.busSeatVisualiserFormat();
//            default -> throw new RuntimeException("Unexpected behaviour of the application!");
//        };
//
//        System.out.println("\nWybierz miejsce:\n");
//        Seat seat = availableSeats.get(this.getOption(seatOptions, seatOptionsDesc));
//
//        // Create a new reservation
//        Reservation reservation = new Reservation("2023/02/01/1", line);
//        reservation.setPaid(true);
//
//        Ticket ticket = switch (type) {
//            case 0 -> new PlaneTicket("2023/02/01/1/1", reservation, true, false, false);
//            case 1 -> new TrainTicket("2023/02/01/1/1", reservation, false, true);
//            case 2 -> new BusTicket("2023/02/01/1/1", reservation);
//            default -> throw new RuntimeException("Unexpected behaviour of the application!");
//        };
//
//        ticket.setDiscount(Discount.STUDENT);
//        ticket.setSeat(seat);
//
//        reservation.addTicket(ticket);
//
//        this.loggedUser.addReservation(reservation);
//
//        System.out.println("\nNowa rezerwacja została dodana!\n");
//    }
//
//    private void exitSystem() {
//        this.loggedUser = null;
//
//        System.out.println("\nDo zobaczenia!");
//    }
//
//    private int getOption(List<String> options, String description) throws IOException {
//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//
//        int noDigits = (int) Math.floor(Math.log10(options.size()) + 1) + 3;
//
//        while (true) {
//            if (!description.equals("")) {
//                for (int i = 0; i < noDigits; i++)
//                    System.out.print(" ");
//
//                System.out.println(description);
//            }
//
//            for (int i = 0; i < options.size(); i++) {
//                String optionNum = "[" + i + "]";
//
//                System.out.print(optionNum);
//
//                for (int j = 0; j < noDigits - optionNum.length(); j++)
//                    System.out.print(" ");
//
//                System.out.println(options.get(i));
//            }
//
//            System.out.print("\nTwój wybór: ");
//
//            int option = -1;
//            try {
//                option = Integer.parseInt(reader.readLine());
//            } catch (NumberFormatException ignored) { }
//
//            if (option >= 0 && option < options.size())
//                return option;
//            else
//                System.out.println("Błędna opcja!\n");
//        }
//    }
//}
