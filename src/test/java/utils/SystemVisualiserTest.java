//package utils;
//
//import carriers.BusCarrier;
//import carriers.PlaneCarrier;
//import carriers.TrainCarrier;
//import lines.BusLine;
//import lines.City;
//import lines.PlaneLine;
//import lines.TrainLine;
//import reservations.*;
//import seats.BusSeat;
//import seats.PlaneSeat;
//import seats.SeatClass;
//import seats.TrainSeat;
//import users.User;
//import users.UserType;
//import utils.Dates;
//import utils.Hashing;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class SystemVisualiserTest {
//
//    private PlaneLine planeLine;
//    private TrainLine trainLine;
//    private BusLine busLine;
//    private PlaneSeat planeSeat;
//    private TrainSeat trainSeat;
//    private BusSeat busSeat;
//    private PlaneTicket planeTicket;
//    private TrainTicket trainTicket;
//    private BusTicket busTicket;
//    private Reservation reservation;
//    private User user;
//    private User administrator;
//
//    @BeforeEach
//    void setUp() throws Exception {
//        // Create plane seats
//        planeSeat = new PlaneSeat(SeatClass.FIRST, 1, true);
//        List<PlaneSeat> planeSeats = new ArrayList<>();
//        for (int i = 1; i <= 50; i++) {
//            planeSeats.add(new PlaneSeat(SeatClass.FIRST, i, false));
//            planeSeats.add(new PlaneSeat(SeatClass.SECOND, i + 50, false));
//        }
//
//        // Create train seats
//        trainSeat = new TrainSeat(SeatClass.FIRST, 1, true, 1);
//        List<TrainSeat> trainSeats = new ArrayList<>();
//        for (int i = 1; i <= 30; i++) {
//            trainSeats.add(new TrainSeat(SeatClass.FIRST, i, false, i % 10));
//            trainSeats.add(new TrainSeat(SeatClass.SECOND, i + 30, false, i % 10));
//        }
//
//        // Create bus seats
//        busSeat = new BusSeat(SeatClass.FIRST, 1, true);
//        List<BusSeat> busSeats = new ArrayList<>();
//        for (int i = 1; i <= 30; i++) {
//            busSeats.add(new BusSeat(SeatClass.FIRST, i, false));
//        }
//
//        // Create lines
//        planeLine = new PlaneLine(planeSeats, 360.0, 280.0, PlaneCarrier.RYANAIR);
//        planeLine.setDepartCity(City.KRAKOW);
//        planeLine.setArrivalCity(City.WARSZAWA);
//        planeLine.setDepartDate(Dates.dateFromString("2023-02-02 17:30:00"));
//        planeLine.setArrivalDate(Dates.dateFromString("2023-02-02 17:57:00"));
//
//        trainLine = new TrainLine(trainSeats, 64.0, 42.0, TrainCarrier.KOLEJE_SLASKIE);
//        trainLine.setDepartCity(City.KRAKOW);
//        trainLine.setArrivalCity(City.ZAWIERCIE);
//        trainLine.setDepartDate(Dates.dateFromString("2023-02-02 16:23:00"));
//        trainLine.setArrivalDate(Dates.dateFromString("2023-02-02 17:26:00"));
//
//        busLine = new BusLine(busSeats, 30.0, 15.0, BusCarrier.SZWAGROPOL);
//        busLine.setDepartCity(City.NOWY_SACZ);
//        busLine.setArrivalCity(City.KRAKOW);
//        busLine.setDepartDate(Dates.dateFromString("2023-02-02 12:05:00"));
//        busLine.setArrivalDate(Dates.dateFromString("2023-02-02 14:59:00"));
//
//        // Create a reservation
//        reservation = new Reservation("2023/01/31/1", busLine);
//        reservation.setPaid(true);
//
//        // Create a bus ticket
//        BusSeat reservedSeat = busSeats.get(0);
//        reservedSeat.setAvailable(false);
//        busTicket = new BusTicket("2023/01/31/1/1", reservation);
//        busTicket.setDiscount(Discount.STUDENT);
//        busTicket.setSeat(reservedSeat);
//        reservation.addTicket(busTicket);
//
//        // Create a plane ticket
//        planeTicket = new PlaneTicket("TICKET123", reservation, true, true, true);
//        planeTicket.setSeat(planeSeat);
//
//        // Create a train ticket
//        trainTicket = new TrainTicket("TICKET456", reservation, true, true);
//        trainTicket.setSeat(trainSeat);
//
//        // Create users
//        user = new User("jan.kowalski@gmail.com", Hashing.hashPassword("hackme123"));
//        user.setFirstname("Jan");
//        user.setLastname("Kowalski");
//        user.setUserType(UserType.PASSENGER);
//        user.addReservation(reservation);
//
//        administrator = new User("edyta.nowak@gmail.com", Hashing.hashPassword("hackme123"));
//        administrator.setFirstname("Edyta");
//        administrator.setLastname("Nowak");
//        administrator.setUserType(UserType.ADMINISTRATOR);
//    }
//
//    @Test
//    void testPlaneLineVisualiserFormat() {
//        String expectedFormat = "#          Skąd #              Wyjazd #          Dokąd #            Przyjazd #      Pojazd #                 Przewoźnik #   Cena w 1 kl. #   Cena w 2 kl. #  Opłata za dodatk. bagaż #  Opłata za zwierzę w kabinie # Opłata za zwierzę w lufcie #";
//        assertEquals(expectedFormat, SystemVisualiser.planeLineVisualiserFormat());
//    }
//
//    @Test
//    void testTrainLineVisualiserFormat() {
//        String expectedFormat = "#          Skąd #              Wyjazd #          Dokąd #            Przyjazd #      Pojazd #                 Przewoźnik #   Cena w 1 kl. #   Cena w 2 kl. #      Opłata za zwierzę #   Opłata za rower #  Opłata za stolik #";
//        assertEquals(expectedFormat, SystemVisualiser.trainLineVisualiserFormat());
//    }
//
//    @Test
//    void testBusLineVisualiserFormat() {
//        String expectedFormat = "#          Skąd #              Wyjazd #          Dokąd #            Przyjazd #      Pojazd #                 Przewoźnik #   Cena w 1 kl. #   Cena w 2 kl. #    Opłata za gniazdko #";
//        assertEquals(expectedFormat, SystemVisualiser.busLineVisualiserFormat());
//    }
//
//
//    @Test
//    void testUserVisualiserFormat() {
//        String expectedFormat = "#     Typ konta #                    Adres email #                 Imię #             Nazwisko # Liczba rezerwacji #";
//        assertEquals(expectedFormat, SystemVisualiser.userVisualiserFormat());
//    }
//
//    @Test
//    void testPlaneSeatVisualiserFormat() {
//        String expectedFormat = "# Numer miejsca # Czy więcej miejsca? #";
//        assertEquals(expectedFormat, SystemVisualiser.planeSeatVisualiserFormat());
//    }
//
//    @Test
//    void testTrainSeatVisualiserFormat() {
//        String expectedFormat = "# Numer miejsca # Numer przedziału # Czy ma stolik? #";
//        assertEquals(expectedFormat, SystemVisualiser.trainSeatVisualiserFormat());
//    }
//
//    @Test
//    void testBusSeatVisualiserFormat() {
//        String expectedFormat = "# Numer miejsca #   Czy jest gniazdko? #";
//        assertEquals(expectedFormat, SystemVisualiser.busSeatVisualiserFormat());
//    }
//
//    @Test
//    void testPlaneTicketVisualiserFormat() {
//        String expectedFormat = "#           Numer #          Skąd #              Wyjazd #         Dokąd #            Przyjazd # Zniżka # Numer miejsca #       Cena #";
//        assertEquals(expectedFormat, SystemVisualiser.planeTicketVisualiserFormat());
//    }
//
//    @Test
//    void testTrainTicketVisualiserFormat() {
//        String expectedFormat = "#           Numer #          Skąd #              Wyjazd #         Dokąd #            Przyjazd # Zniżka # Numer miejsca #    Numer przedziału #       Cena #";
//        assertEquals(expectedFormat, SystemVisualiser.trainTicketVisualiserFormat());
//    }
//
//    @Test
//    void testBusTicketVisualiserFormat() {
//        String expectedFormat = "#           Numer #          Skąd #              Wyjazd #         Dokąd #            Przyjazd # Zniżka # Numer miejsca #       Cena #";
//        assertEquals(expectedFormat, SystemVisualiser.busTicketVisualiserFormat());
//    }
//
//    @Test
//    void testReservationVisualiserFormat() {
//        String expectedFormat = "#           Numer #          Skąd #              Wyjazd #         Dokąd #            Przyjazd # Liczba biletów # Czy opłacony? #          Rezerwacja #              Wygasa #";
//        assertEquals(expectedFormat, SystemVisualiser.reservationVisualiserFormat());
//    }
//
//    @Test
//    void testPlaneLineVisualiser() {
//        String expectedOutput = String.format("# %13s # %19s # %13s # %19s # %10s # %26s # %12s # %12s # %23s # %27s # %26s #",
//                planeLine.getDepartCity().getVisibleName(), Dates.dateToString(planeLine.getDepartDate()),
//                planeLine.getArrivalCity().getVisibleName(), Dates.dateToString(planeLine.getArrivalDate()), "Samolot",
//                planeLine.getCarrier().getVisibleName(), planeLine.getFirstClassPrice() + " PLN",
//                planeLine.getSecondClassPrice() + " PLN", planeLine.getAdditionalLuggagePrice() + " PLN",
//                planeLine.getInCabinPetPrice() + " PLN", planeLine.getInBaggageHoldPetPrice() + " PLN");
//        assertEquals(expectedOutput, SystemVisualiser.planeLineVisualiser(planeLine));
//    }
//
//    @Test
//    void testTrainLineVisualiser() {
//        String expectedOutput = String.format("# %13s # %19s # %13s # %19s # %10s # %26s # %12s # %12s # %17s # %15s # %15s #",
//                trainLine.getDepartCity().getVisibleName(), Dates.dateToString(trainLine.getDepartDate()),
//                trainLine.getArrivalCity().getVisibleName(), Dates.dateToString(trainLine.getArrivalDate()), "Pociąg",
//                trainLine.getCarrier().getVisibleName(), trainLine.getFirstClassPrice() + " PLN",
//                trainLine.getSecondClassPrice() + " PLN", trainLine.getPetPrice() + " PLN",
//                trainLine.getBicyclePrice() + " PLN", trainLine.getTableSeatPrice() + " PLN");
//        assertEquals(expectedOutput, SystemVisualiser.trainLineVisualiser(trainLine));
//    }
//
//    @Test
//    void testBusLineVisualiser() {
//        String expectedOutput = String.format("# %13s # %19s # %13s # %19s # %10s # %26s # %12s # %12s # %18s #",
//                busLine.getDepartCity().getVisibleName(), Dates.dateToString(busLine.getDepartDate()),
//                busLine.getArrivalCity().getVisibleName(), Dates.dateToString(busLine.getArrivalDate()), "Autobus",
//                busLine.getCarrier().getVisibleName(), busLine.getFirstClassPrice() + " PLN",
//                busLine.getSecondClassPrice() + " PLN", busLine.getElectricalOutletSeatPrice() + " PLN");
//        assertEquals(expectedOutput, SystemVisualiser.busLineVisualiser(busLine));
//    }
//
//    @Test
//    void testUserVisualiser() {
//        String expectedOutput = String.format("# %13s # %30s # %20s # %20s # %17s #",
//                user.getUserType().getVisibleName(), user.getEmail(), user.getFirstname(), user.getLastname(),
//                user.getReservations().size());
//        assertEquals(expectedOutput, SystemVisualiser.userVisualiser(user));
//    }
//
//    @Test
//    void testPlaneSeatVisualiser() {
//        String expectedOutput = String.format("# %13s # %19s #",
//                planeSeat.getSeatNum(), "TAK");
//        assertEquals(expectedOutput, SystemVisualiser.planeSeatVisualiser(planeSeat));
//    }
//
//    @Test
//    void testTrainSeatVisualiser() {
//        String expectedOutput = String.format("# %13s # %16s # %14s #",
//                trainSeat.getSeatNum(), trainSeat.getCarNum(), "TAK");
//        assertEquals(expectedOutput, SystemVisualiser.trainSeatVisualiser(trainSeat));
//    }
//    @Test
//    void testBusSeatVisualiser() {
//        BusSeat busSeat = new BusSeat(SeatClass.FIRST, 1, false); // Upewnij się, że gniazdko jest ustawione na false
//        String expectedOutput = "#             1 #                NIE #";
//        assertEquals(expectedOutput, SystemVisualiser.busSeatVisualiser(busSeat));
//    }
//
//    @Test
//    void testPlaneTicketVisualiser() {
//        String expectedOutput = String.format("# %15s # %13s # %19s # %13s # %19s # %6s # %13s # %10s #",
//                planeTicket.getTicketId(), planeTicket.getReservation().getLine().getDepartCity().getVisibleName(),
//                Dates.dateToString(planeTicket.getReservation().getLine().getDepartDate()),
//                planeTicket.getReservation().getLine().getArrivalCity().getVisibleName(),
//                Dates.dateToString(planeTicket.getReservation().getLine().getArrivalDate()),
//                planeTicket.getDiscount().getDiscountValue() * 100 + "%", planeTicket.getSeat().getSeatNum(),
//                planeTicket.getPrice() + " PLN");
//        assertEquals(expectedOutput, SystemVisualiser.planeTicketVisualiser(planeTicket));
//    }
//
//    @Test
//    void testTrainTicketVisualiser() {
//        String expectedOutput = String.format("# %15s # %13s # %19s # %13s # %19s # %6s # %13s # %19s # %10s #",
//                trainTicket.getTicketId(), trainTicket.getReservation().getLine().getDepartCity().getVisibleName(),
//                Dates.dateToString(trainTicket.getReservation().getLine().getDepartDate()),
//                trainTicket.getReservation().getLine().getArrivalCity().getVisibleName(),
//                Dates.dateToString(trainTicket.getReservation().getLine().getArrivalDate()),
//                trainTicket.getDiscount().getDiscountValue() * 100 + "%", trainTicket.getSeat().getSeatNum(),
//                ((TrainSeat) trainTicket.getSeat()).getCarNum(), trainTicket.getPrice() + " PLN");
//        assertEquals(expectedOutput, SystemVisualiser.trainTicketVisualiser(trainTicket));
//    }
//    @Test
//    void testBusTicketVisualiser() {
//        String expectedOutput = String.format("# %15s # %13s # %19s # %13s # %19s # %6s # %13s # %10s #",
//                busTicket.getTicketId(), busTicket.getReservation().getLine().getDepartCity().getVisibleName(),
//                Dates.dateToString(busTicket.getReservation().getLine().getDepartDate()),
//                busTicket.getReservation().getLine().getArrivalCity().getVisibleName(),
//                Dates.dateToString(busTicket.getReservation().getLine().getArrivalDate()),
//                busTicket.getDiscount().getDiscountValue() * 100 + "%",
//                busTicket.getSeat().getSeatNum(), busTicket.getPrice() + " PLN");
//        assertEquals(expectedOutput, SystemVisualiser.busTicketVisualiser(busTicket));
//    }
//
//    @Test
//    void testReservationVisualiser() {
//        String expectedOutput = String.format("# %15s # %13s # %19s # %13s # %19s # %14s # %13s # %19s # %19s #",
//                reservation.getReservationId(), reservation.getLine().getDepartCity().getVisibleName(),
//                Dates.dateToString(reservation.getLine().getDepartDate()),
//                reservation.getLine().getArrivalCity().getVisibleName(),
//                Dates.dateToString(reservation.getLine().getArrivalDate()), reservation.getTickets().size(), "TAK",
//                Dates.dateToString(reservation.getBookingDate()), Dates.dateToString(reservation.getExpirationDate()));
//        assertEquals(expectedOutput, SystemVisualiser.reservationVisualiser(reservation));
//    }
//}

//
//package utils;
//
//import carriers.BusCarrier;
//import carriers.PlaneCarrier;
//import carriers.TrainCarrier;
//import lines.BusLine;
//import lines.City;
//import lines.PlaneLine;
//import lines.TrainLine;
//import reservations.*;
//import seats.BusSeat;
//import seats.PlaneSeat;
//import seats.SeatClass;
//import seats.TrainSeat;
//import users.User;
//import users.UserType;
//import utils.Dates;
//import utils.Hashing;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//
//class SystemVisualiserTest {
//
//    private PlaneLine planeLine;
//    private TrainLine trainLine;
//    private BusLine busLine;
//    private PlaneSeat planeSeat;
//    private TrainSeat trainSeat;
//    private BusSeat busSeat;
//    private PlaneTicket planeTicket;
//    private TrainTicket trainTicket;
//    private BusTicket busTicket;
//    private Reservation busReservation;
//    private Reservation planeReservation;
//    private Reservation trainReservation;
//    private User user;
//    private User administrator;
//
//    @BeforeEach
//    void setUp() throws Exception {
//        // Create plane seats
//        planeSeat = new PlaneSeat(SeatClass.FIRST, 1, true);
//        List<PlaneSeat> planeSeats = new ArrayList<>();
//        for (int i = 1; i <= 50; i++) {
//            planeSeats.add(new PlaneSeat(SeatClass.FIRST, i, false));
//            planeSeats.add(new PlaneSeat(SeatClass.SECOND, i + 50, false));
//        }
//
//        // Create train seats
//        trainSeat = new TrainSeat(SeatClass.FIRST, 1, true, 1);
//        List<TrainSeat> trainSeats = new ArrayList<>();
//        for (int i = 1; i <= 30; i++) {
//            trainSeats.add(new TrainSeat(SeatClass.FIRST, i, false, i % 10));
//            trainSeats.add(new TrainSeat(SeatClass.SECOND, i + 30, false, i % 10));
//        }
//
//        // Create bus seats
//        busSeat = new BusSeat(SeatClass.FIRST, 1, true);
//        List<BusSeat> busSeats = new ArrayList<>();
//        for (int i = 1; i <= 30; i++) {
//            busSeats.add(new BusSeat(SeatClass.FIRST, i, false));
//        }
//
//        // Create lines
//        planeLine = new PlaneLine(planeSeats, 360.0, 280.0, PlaneCarrier.RYANAIR);
//        planeLine.setDepartCity(City.KRAKOW);
//        planeLine.setArrivalCity(City.WARSZAWA);
//        planeLine.setDepartDate(Dates.dateFromString("2023-02-02 17:30:00"));
//        planeLine.setArrivalDate(Dates.dateFromString("2023-02-02 17:57:00"));
//
//        trainLine = new TrainLine(trainSeats, 64.0, 42.0, TrainCarrier.KOLEJE_SLASKIE);
//        trainLine.setDepartCity(City.KRAKOW);
//        trainLine.setArrivalCity(City.ZAWIERCIE);
//        trainLine.setDepartDate(Dates.dateFromString("2023-02-02 16:23:00"));
//        trainLine.setArrivalDate(Dates.dateFromString("2023-02-02 17:26:00"));
//
//        busLine = new BusLine(busSeats, 30.0, 15.0, BusCarrier.SZWAGROPOL);
//        busLine.setDepartCity(City.NOWY_SACZ);
//        busLine.setArrivalCity(City.KRAKOW);
//        busLine.setDepartDate(Dates.dateFromString("2023-02-02 12:05:00"));
//        busLine.setArrivalDate(Dates.dateFromString("2023-02-02 14:59:00"));
//
//        // Create reservations
//        busReservation = new Reservation("2023/01/31/1", busLine);
//        busReservation.setPaid(true);
//
//        planeReservation = new Reservation("2023/01/31/2", planeLine);
//        planeReservation.setPaid(true);
//
//        trainReservation = new Reservation("2023/01/31/3", trainLine);
//        trainReservation.setPaid(true);
//
//        // Create tickets
//        // Create a bus ticket
//        BusSeat reservedBusSeat = busSeats.get(0);
//        reservedBusSeat.setAvailable(false);
//        busTicket = new BusTicket("2023/01/31/1/1", busReservation);
//        busTicket.setDiscount(Discount.STUDENT);
//        busTicket.setSeat(reservedBusSeat);
//        busReservation.addTicket(busTicket);
//
//        // Create a plane ticket
//        planeTicket = new PlaneTicket("TICKET123", planeReservation, true, true, true);
//        planeTicket.setSeat(planeSeat);
//        planeTicket.setDiscount(Discount.SENIOR);
//        planeReservation.addTicket(planeTicket);
//
//        // Create a train ticket
//        trainTicket = new TrainTicket("TICKET456", trainReservation, true, true);
//        trainTicket.setSeat(trainSeat);
//        trainTicket.setDiscount(Discount.SENIOR);
//        trainReservation.addTicket(trainTicket);
//
//        // Create users
//        user = new User("jan.kowalski@gmail.com", Hashing.hashPassword("hackme123"));
//        user.setFirstname("Jan");
//        user.setLastname("Kowalski");
//        user.setUserType(UserType.PASSENGER);
//        user.addReservation(busReservation);
//        user.addReservation(planeReservation);
//        user.addReservation(trainReservation);
//
//        administrator = new User("edyta.nowak@gmail.com", Hashing.hashPassword("hackme123"));
//        administrator.setFirstname("Edyta");
//        administrator.setLastname("Nowak");
//        administrator.setUserType(UserType.ADMINISTRATOR);
//    }
//
//
//    @Test
//    void testPlaneLineVisualiserFormat() {
//        String expectedFormat = "#          Skąd #              Wyjazd #          Dokąd #            Przyjazd #      Pojazd #                 Przewoźnik #   Cena w 1 kl. #   Cena w 2 kl. #  Opłata za dodatk. bagaż #  Opłata za zwierzę w kabinie # Opłata za zwierzę w lufcie #";
//        assertEquals(expectedFormat, SystemVisualiser.planeLineVisualiserFormat());
//    }
//
//    @Test
//    void testTrainLineVisualiserFormat() {
//        String expectedFormat = "#          Skąd #              Wyjazd #          Dokąd #            Przyjazd #      Pojazd #                 Przewoźnik #   Cena w 1 kl. #   Cena w 2 kl. #      Opłata za zwierzę #   Opłata za rower #  Opłata za stolik #";
//        assertEquals(expectedFormat, SystemVisualiser.trainLineVisualiserFormat());
//    }
//
//    @Test
//    void testBusLineVisualiserFormat() {
//        String expectedFormat = "#          Skąd #              Wyjazd #          Dokąd #            Przyjazd #      Pojazd #                 Przewoźnik #   Cena w 1 kl. #   Cena w 2 kl. #    Opłata za gniazdko #";
//        assertEquals(expectedFormat, SystemVisualiser.busLineVisualiserFormat());
//    }
//
//
//    @Test
//    void testUserVisualiserFormat() {
//        String expectedFormat = "#     Typ konta #                    Adres email #                 Imię #             Nazwisko # Liczba rezerwacji #";
//        assertEquals(expectedFormat, SystemVisualiser.userVisualiserFormat());
//    }
//
//    @Test
//    void testPlaneSeatVisualiserFormat() {
//        String expectedFormat = "# Numer miejsca # Czy więcej miejsca? #";
//        assertEquals(expectedFormat, SystemVisualiser.planeSeatVisualiserFormat());
//    }
//
//    @Test
//    void testTrainSeatVisualiserFormat() {
//        String expectedFormat = "# Numer miejsca # Numer przedziału # Czy ma stolik? #";
//        assertEquals(expectedFormat, SystemVisualiser.trainSeatVisualiserFormat());
//    }
//
//    @Test
//    void testBusSeatVisualiserFormat() {
//        String expectedFormat = "# Numer miejsca #   Czy jest gniazdko? #";
//        assertEquals(expectedFormat, SystemVisualiser.busSeatVisualiserFormat());
//    }
//
//    @Test
//    void testPlaneTicketVisualiserFormat() {
//        String expectedFormat = "#           Numer #          Skąd #              Wyjazd #         Dokąd #            Przyjazd # Zniżka # Numer miejsca #       Cena #";
//        assertEquals(expectedFormat, SystemVisualiser.planeTicketVisualiserFormat());
//    }
//
//    @Test
//    void testTrainTicketVisualiserFormat() {
//        String expectedFormat = "#           Numer #          Skąd #              Wyjazd #         Dokąd #            Przyjazd # Zniżka # Numer miejsca #    Numer przedziału #       Cena #";
//        assertEquals(expectedFormat, SystemVisualiser.trainTicketVisualiserFormat());
//    }
//
//    @Test
//    void testBusTicketVisualiserFormat() {
//        String expectedFormat = "#           Numer #          Skąd #              Wyjazd #         Dokąd #            Przyjazd # Zniżka # Numer miejsca #       Cena #";
//        assertEquals(expectedFormat, SystemVisualiser.busTicketVisualiserFormat());
//    }
//
//    @Test
//    void testReservationVisualiserFormat() {
//        String expectedFormat = "#           Numer #          Skąd #              Wyjazd #         Dokąd #            Przyjazd # Liczba biletów # Czy opłacony? #          Rezerwacja #              Wygasa #";
//        assertEquals(expectedFormat, SystemVisualiser.reservationVisualiserFormat());
//    }
//
//    @Test
//    void testPlaneLineVisualiser() {
//        String expectedOutput = String.format("# %13s # %19s # %13s # %19s # %10s # %26s # %12s # %12s # %23s # %27s # %26s #",
//                planeLine.getDepartCity().getVisibleName(), Dates.dateToString(planeLine.getDepartDate()),
//                planeLine.getArrivalCity().getVisibleName(), Dates.dateToString(planeLine.getArrivalDate()), "Samolot",
//                planeLine.getCarrier().getVisibleName(), planeLine.getFirstClassPrice() + " PLN",
//                planeLine.getSecondClassPrice() + " PLN", planeLine.getAdditionalLuggagePrice() + " PLN",
//                planeLine.getInCabinPetPrice() + " PLN", planeLine.getInBaggageHoldPetPrice() + " PLN");
//        assertEquals(expectedOutput, SystemVisualiser.planeLineVisualiser(planeLine));
//    }
//
//    @Test
//    void testTrainLineVisualiser() {
//        String expectedOutput = String.format("# %13s # %19s # %13s # %19s # %10s # %26s # %12s # %12s # %17s # %15s # %15s #",
//                trainLine.getDepartCity().getVisibleName(), Dates.dateToString(trainLine.getDepartDate()),
//                trainLine.getArrivalCity().getVisibleName(), Dates.dateToString(trainLine.getArrivalDate()), "Pociąg",
//                trainLine.getCarrier().getVisibleName(), trainLine.getFirstClassPrice() + " PLN",
//                trainLine.getSecondClassPrice() + " PLN", trainLine.getPetPrice() + " PLN",
//                trainLine.getBicyclePrice() + " PLN", trainLine.getTableSeatPrice() + " PLN");
//        assertEquals(expectedOutput, SystemVisualiser.trainLineVisualiser(trainLine));
//    }
//
//    @Test
//    void testBusLineVisualiser() {
//        String expectedOutput = String.format("# %13s # %19s # %13s # %19s # %10s # %26s # %12s # %12s # %18s #",
//                busLine.getDepartCity().getVisibleName(), Dates.dateToString(busLine.getDepartDate()),
//                busLine.getArrivalCity().getVisibleName(), Dates.dateToString(busLine.getArrivalDate()), "Autobus",
//                busLine.getCarrier().getVisibleName(), busLine.getFirstClassPrice() + " PLN",
//                busLine.getSecondClassPrice() + " PLN", busLine.getElectricalOutletSeatPrice() + " PLN");
//        assertEquals(expectedOutput, SystemVisualiser.busLineVisualiser(busLine));
//    }
//
//    @Test
//    void testUserVisualiser() {
//        String expectedOutput = String.format("# %13s # %30s # %20s # %20s # %17s #",
//                user.getUserType().getVisibleName(), user.getEmail(), user.getFirstname(), user.getLastname(),
//                user.getReservations().size());
//        assertEquals(expectedOutput, SystemVisualiser.userVisualiser(user));
//    }
//
//    @Test
//    void testPlaneSeatVisualiser() {
//        String expectedOutput = String.format("# %13s # %19s #",
//                planeSeat.getSeatNum(), "TAK");
//        assertEquals(expectedOutput, SystemVisualiser.planeSeatVisualiser(planeSeat));
//    }
//
//    @Test
//    void testTrainSeatVisualiser() {
//        String expectedOutput = String.format("# %13s # %16s # %14s #",
//                trainSeat.getSeatNum(), trainSeat.getCarNum(), "TAK");
//        assertEquals(expectedOutput, SystemVisualiser.trainSeatVisualiser(trainSeat));
//    }
//    @Test
//    void testBusSeatVisualiser() {
//        BusSeat busSeat = new BusSeat(SeatClass.FIRST, 1, false); // Upewnij się, że gniazdko jest ustawione na false
//        String expectedOutput = "#             1 #                NIE #";
//        assertEquals(expectedOutput, SystemVisualiser.busSeatVisualiser(busSeat));
//    }
//    @Test
//    void testTrainTicketVisualiser() {
//        String expectedOutput = String.format("# %15s # %13s # %19s # %13s # %19s # %6s # %13s # %19s # %10s #",
//                trainTicket.getTicketId(), trainTicket.getReservation().getLine().getDepartCity().getVisibleName(),
//                Dates.dateToString(trainTicket.getReservation().getLine().getDepartDate()),
//                trainTicket.getReservation().getLine().getArrivalCity().getVisibleName(),
//                Dates.dateToString(trainTicket.getReservation().getLine().getArrivalDate()),
//                trainTicket.getDiscount().getDiscountValue() * 100 + "%", trainTicket.getSeat().getSeatNum(),
//                ((TrainSeat) trainTicket.getSeat()).getCarNum(), trainTicket.getPrice() + " PLN");
//        assertEquals(expectedOutput, SystemVisualiser.trainTicketVisualiser(trainTicket));
//    }
//
//    @Test
//    void testPlaneTicketVisualiser() {
//        String expectedOutput = String.format("# %15s # %13s # %19s # %13s # %19s # %6s # %13s # %10s #",
//                planeTicket.getTicketId(), planeTicket.getReservation().getLine().getDepartCity().getVisibleName(),
//                Dates.dateToString(planeTicket.getReservation().getLine().getDepartDate()),
//                planeTicket.getReservation().getLine().getArrivalCity().getVisibleName(),
//                Dates.dateToString(planeTicket.getReservation().getLine().getArrivalDate()),
//                planeTicket.getDiscount().getDiscountValue() * 100 + "%", planeTicket.getSeat().getSeatNum(),
//                planeTicket.getPrice() + " PLN");
//        assertEquals(expectedOutput, SystemVisualiser.planeTicketVisualiser(planeTicket));
//    }
//    @Test
//    void testBusTicketVisualiser() {
//        String expectedOutput = String.format("# %15s # %13s # %19s # %13s # %19s # %6s # %13s # %10s #",
//                busTicket.getTicketId(), busTicket.getReservation().getLine().getDepartCity().getVisibleName(),
//                Dates.dateToString(busTicket.getReservation().getLine().getDepartDate()),
//                busTicket.getReservation().getLine().getArrivalCity().getVisibleName(),
//                Dates.dateToString(busTicket.getReservation().getLine().getArrivalDate()),
//                busTicket.getDiscount().getDiscountValue() * 100 + "%",
//                busTicket.getSeat().getSeatNum(), busTicket.getPrice() + " PLN");
//        assertEquals(expectedOutput, SystemVisualiser.busTicketVisualiser(busTicket));
//    }
//
//    @Test
//    void testReservationVisualiser() {
//        String expectedOutput = String.format("# %15s # %13s # %19s # %13s # %19s # %14s # %13s # %19s # %19s #",
//                reservation.getReservationId(), reservation.getLine().getDepartCity().getVisibleName(),
//                Dates.dateToString(reservation.getLine().getDepartDate()),
//                reservation.getLine().getArrivalCity().getVisibleName(),
//                Dates.dateToString(reservation.getLine().getArrivalDate()), reservation.getTickets().size(), "TAK",
//                Dates.dateToString(reservation.getBookingDate()), Dates.dateToString(reservation.getExpirationDate()));
//        assertEquals(expectedOutput, SystemVisualiser.reservationVisualiser(reservation));
//    }
//}
//
//package utils;
//
//import carriers.BusCarrier;
//import carriers.PlaneCarrier;
//import carriers.TrainCarrier;
//import lines.BusLine;
//import lines.City;
//import lines.PlaneLine;
//import lines.TrainLine;
//import reservations.*;
//import seats.BusSeat;
//import seats.PlaneSeat;
//import seats.SeatClass;
//import seats.TrainSeat;
//import users.User;
//import users.UserType;
//import utils.Dates;
//import utils.Hashing;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class SystemVisualiserTest {
//
//    private PlaneLine planeLine;
//    private TrainLine trainLine;
//    private BusLine busLine;
//    private PlaneSeat planeSeat;
//    private TrainSeat trainSeat;
//    private BusSeat busSeat;
//    private PlaneTicket planeTicket;
//    private TrainTicket trainTicket;
//    private BusTicket busTicket;
//    private Reservation busReservation;
//    private Reservation planeReservation;
//    private Reservation trainReservation;
//    private User user;
//    private User administrator;
//
//    @BeforeEach
//    void setUp() throws Exception {
//        // Create plane seats
//        planeSeat = new PlaneSeat(SeatClass.FIRST, 1, true);
//        List<PlaneSeat> planeSeats = new ArrayList<>();
//        for (int i = 1; i <= 50; i++) {
//            planeSeats.add(new PlaneSeat(SeatClass.FIRST, i, false));
//            planeSeats.add(new PlaneSeat(SeatClass.SECOND, i + 50, false));
//        }
//
//        // Create train seats
//        trainSeat = new TrainSeat(SeatClass.FIRST, 1, true, 1);
//        List<TrainSeat> trainSeats = new ArrayList<>();
//        for (int i = 1; i <= 30; i++) {
//            trainSeats.add(new TrainSeat(SeatClass.FIRST, i, false, i % 10));
//            trainSeats.add(new TrainSeat(SeatClass.SECOND, i + 30, false, i % 10));
//        }
//
//        // Create bus seats
//        busSeat = new BusSeat(SeatClass.FIRST, 1, true);
//        List<BusSeat> busSeats = new ArrayList<>();
//        for (int i = 1; i <= 30; i++) {
//            busSeats.add(new BusSeat(SeatClass.FIRST, i, false));
//        }
//
//        // Create lines
//        planeLine = new PlaneLine(planeSeats, 360.0, 280.0, PlaneCarrier.RYANAIR);
//        planeLine.setDepartCity(City.KRAKOW);
//        planeLine.setArrivalCity(City.WARSZAWA);
//        planeLine.setDepartDate(Dates.dateFromString("2023-02-02 17:30:00"));
//        planeLine.setArrivalDate(Dates.dateFromString("2023-02-02 17:57:00"));
//
//        trainLine = new TrainLine(trainSeats, 64.0, 42.0, TrainCarrier.KOLEJE_SLASKIE);
//        trainLine.setDepartCity(City.KRAKOW);
//        trainLine.setArrivalCity(City.ZAWIERCIE);
//        trainLine.setDepartDate(Dates.dateFromString("2023-02-02 16:23:00"));
//        trainLine.setArrivalDate(Dates.dateFromString("2023-02-02 17:26:00"));
//
//        busLine = new BusLine(busSeats, 30.0, 15.0, BusCarrier.SZWAGROPOL);
//        busLine.setDepartCity(City.NOWY_SACZ);
//        busLine.setArrivalCity(City.KRAKOW);
//        busLine.setDepartDate(Dates.dateFromString("2023-02-02 12:05:00"));
//        busLine.setArrivalDate(Dates.dateFromString("2023-02-02 14:59:00"));
//
//        // Create reservations
//        busReservation = new Reservation("2023/01/31/1", busLine);
//        busReservation.setPaid(true);
//
//        planeReservation = new Reservation("2023/01/31/2", planeLine);
//        planeReservation.setPaid(true);
//
//        trainReservation = new Reservation("2023/01/31/3", trainLine);
//        trainReservation.setPaid(true);
//
//        // Create a bus ticket
//        BusSeat reservedBusSeat = busSeats.get(0);
//        reservedBusSeat.setAvailable(false);
//        busTicket = new BusTicket("2023/01/31/1/1", busReservation);
//        busTicket.setDiscount(Discount.STUDENT);
//        busTicket.setSeat(reservedBusSeat);
//        busReservation.addTicket(busTicket);
//
//        // Create a plane ticket
//        PlaneSeat reservedPlaneSeat = planeSeats.get(0);
//        reservedPlaneSeat.setAvailable(false);
//        planeTicket = new PlaneTicket("TICKET123", planeReservation, true, true, true);
//        planeTicket.setSeat(reservedPlaneSeat);
//        planeReservation.addTicket(planeTicket);
//
//        // Create a train ticket
//        TrainSeat reservedTrainSeat = trainSeats.get(0);
//        reservedTrainSeat.setAvailable(false);
//        trainTicket = new TrainTicket("TICKET456", trainReservation, true, true);
//        trainTicket.setSeat(reservedTrainSeat);
//        trainReservation.addTicket(trainTicket);
//
//        // Create users
//        user = new User("jan.kowalski@gmail.com", Hashing.hashPassword("hackme123"));
//        user.setFirstname("Jan");
//        user.setLastname("Kowalski");
//        user.setUserType(UserType.PASSENGER);
//        user.addReservation(busReservation);
//        user.addReservation(planeReservation);
//        user.addReservation(trainReservation);
//
//        administrator = new User("edyta.nowak@gmail.com", Hashing.hashPassword("hackme123"));
//        administrator.setFirstname("Edyta");
//        administrator.setLastname("Nowak");
//        administrator.setUserType(UserType.ADMINISTRATOR);
//    }
//
//    @Test
//    void testPlaneLineVisualiserFormat() {
//        String expectedFormat = "#          Skąd #              Wyjazd #          Dokąd #            Przyjazd #      Pojazd #                 Przewoźnik #   Cena w 1 kl. #   Cena w 2 kl. #  Opłata za dodatk. bagaż #  Opłata za zwierzę w kabinie # Opłata za zwierzę w lufcie #";
//        assertEquals(expectedFormat, SystemVisualiser.planeLineVisualiserFormat());
//    }
//
//    @Test
//    void testTrainLineVisualiserFormat() {
//        String expectedFormat = "#          Skąd #              Wyjazd #          Dokąd #            Przyjazd #      Pojazd #                 Przewoźnik #   Cena w 1 kl. #   Cena w 2 kl. #      Opłata za zwierzę #   Opłata za rower #  Opłata za stolik #";
//        assertEquals(expectedFormat, SystemVisualiser.trainLineVisualiserFormat());
//    }
//
//    @Test
//    void testBusLineVisualiserFormat() {
//        String expectedFormat = "#          Skąd #              Wyjazd #          Dokąd #            Przyjazd #      Pojazd #                 Przewoźnik #   Cena w 1 kl. #   Cena w 2 kl. #    Opłata za gniazdko #";
//        assertEquals(expectedFormat, SystemVisualiser.busLineVisualiserFormat());
//    }
//
//    @Test
//    void testUserVisualiserFormat() {
//        String expectedFormat = "#     Typ konta #                    Adres email #                 Imię #             Nazwisko # Liczba rezerwacji #";
//        assertEquals(expectedFormat, SystemVisualiser.userVisualiserFormat());
//    }
//
//    @Test
//    void testPlaneSeatVisualiserFormat() {
//        String expectedFormat = "# Numer miejsca # Czy więcej miejsca? #";
//        assertEquals(expectedFormat, SystemVisualiser.planeSeatVisualiserFormat());
//    }
//
//    @Test
//    void testTrainSeatVisualiserFormat() {
//        String expectedFormat = "# Numer miejsca # Numer przedziału # Czy ma stolik? #";
//        assertEquals(expectedFormat, SystemVisualiser.trainSeatVisualiserFormat());
//    }
//
//    @Test
//    void testBusSeatVisualiserFormat() {
//        String expectedFormat = "# Numer miejsca #   Czy jest gniazdko? #";
//        assertEquals(expectedFormat, SystemVisualiser.busSeatVisualiserFormat());
//    }
//
//    @Test
//    void testPlaneTicketVisualiserFormat() {
//        String expectedFormat = "#           Numer #          Skąd #              Wyjazd #         Dokąd #            Przyjazd # Zniżka # Numer miejsca #       Cena #";
//        assertEquals(expectedFormat, SystemVisualiser.planeTicketVisualiserFormat());
//    }
//
//    @Test
//    void testTrainTicketVisualiserFormat() {
//        String expectedFormat = "#           Numer #          Skąd #              Wyjazd #         Dokąd #            Przyjazd # Zniżka # Numer miejsca #    Numer przedziału #       Cena #";
//        assertEquals(expectedFormat, SystemVisualiser.trainTicketVisualiserFormat());
//    }
//
//    @Test
//    void testBusTicketVisualiserFormat() {
//        String expectedFormat = "#           Numer #          Skąd #              Wyjazd #         Dokąd #            Przyjazd # Zniżka # Numer miejsca #       Cena #";
//        assertEquals(expectedFormat, SystemVisualiser.busTicketVisualiserFormat());
//    }
//
//    @Test
//    void testReservationVisualiserFormat() {
//        String expectedFormat = "#           Numer #          Skąd #              Wyjazd #         Dokąd #            Przyjazd # Liczba biletów # Czy opłacony? #          Rezerwacja #              Wygasa #";
//        assertEquals(expectedFormat, SystemVisualiser.reservationVisualiserFormat());
//    }
//
//    @Test
//    void testPlaneLineVisualiser() {
//        String expectedOutput = String.format("# %13s # %19s # %13s # %19s # %10s # %26s # %12s # %12s # %23s # %27s # %26s #",
//                planeLine.getDepartCity().getVisibleName(), Dates.dateToString(planeLine.getDepartDate()),
//                planeLine.getArrivalCity().getVisibleName(), Dates.dateToString(planeLine.getArrivalDate()), "Samolot",
//                planeLine.getCarrier().getVisibleName(), planeLine.getFirstClassPrice() + " PLN",
//                planeLine.getSecondClassPrice() + " PLN", planeLine.getAdditionalLuggagePrice() + " PLN",
//                planeLine.getInCabinPetPrice() + " PLN", planeLine.getInBaggageHoldPetPrice() + " PLN");
//        assertEquals(expectedOutput, SystemVisualiser.planeLineVisualiser(planeLine));
//    }
//
//    @Test
//    void testTrainLineVisualiser() {
//        String expectedOutput = String.format("# %13s # %19s # %13s # %19s # %10s # %26s # %12s # %12s # %17s # %15s # %15s #",
//                trainLine.getDepartCity().getVisibleName(), Dates.dateToString(trainLine.getDepartDate()),
//                trainLine.getArrivalCity().getVisibleName(), Dates.dateToString(trainLine.getArrivalDate()), "Pociąg",
//                trainLine.getCarrier().getVisibleName(), trainLine.getFirstClassPrice() + " PLN",
//                trainLine.getSecondClassPrice() + " PLN", trainLine.getPetPrice() + " PLN",
//                trainLine.getBicyclePrice() + " PLN", trainLine.getTableSeatPrice() + " PLN");
//        assertEquals(expectedOutput, SystemVisualiser.trainLineVisualiser(trainLine));
//    }
//
//    @Test
//    void testBusLineVisualiser() {
//        String expectedOutput = String.format("# %13s # %19s # %13s # %19s # %10s # %26s # %12s # %12s # %18s #",
//                busLine.getDepartCity().getVisibleName(), Dates.dateToString(busLine.getDepartDate()),
//                busLine.getArrivalCity().getVisibleName(), Dates.dateToString(busLine.getArrivalDate()), "Autobus",
//                busLine.getCarrier().getVisibleName(), busLine.getFirstClassPrice() + " PLN",
//                busLine.getSecondClassPrice() + " PLN", busLine.getElectricalOutletSeatPrice() + " PLN");
//        assertEquals(expectedOutput, SystemVisualiser.busLineVisualiser(busLine));
//    }
//
//    @Test
//    void testUserVisualiser() {
//        String expectedOutput = String.format("# %13s # %30s # %20s # %20s # %17s #",
//                user.getUserType().getVisibleName(), user.getEmail(), user.getFirstname(), user.getLastname(),
//                user.getReservations().size());
//        assertEquals(expectedOutput, SystemVisualiser.userVisualiser(user));
//    }
//
//    @Test
//    void testPlaneSeatVisualiser() {
//        String expectedOutput = String.format("# %13s # %19s #",
//                planeSeat.getSeatNum(), "TAK");
//        assertEquals(expectedOutput, SystemVisualiser.planeSeatVisualiser(planeSeat));
//    }
//
//    @Test
//    void testTrainSeatVisualiser() {
//        String expectedOutput = String.format("# %13s # %16s # %14s #",
//                trainSeat.getSeatNum(), trainSeat.getCarNum(), "TAK");
//        assertEquals(expectedOutput, SystemVisualiser.trainSeatVisualiser(trainSeat));
//    }
//
//    @Test
//    void testBusSeatVisualiser() {
//        String expectedOutput = String.format("# %13s # %20s #",
//                busSeat.getSeatNum(), "TAK");
//        assertEquals(expectedOutput, SystemVisualiser.busSeatVisualiser(busSeat));
//    }
//
//    @Test
//    void testPlaneTicketVisualiser() {
//        String expectedOutput = String.format("# %15s # %13s # %19s # %13s # %19s # %6s # %13s # %10s #",
//                planeTicket.getTicketId(), planeTicket.getReservation().getLine().getDepartCity().getVisibleName(),
//                Dates.dateToString(planeTicket.getReservation().getLine().getDepartDate()),
//                planeTicket.getReservation().getLine().getArrivalCity().getVisibleName(),
//                Dates.dateToString(planeTicket.getReservation().getLine().getArrivalDate()),
//                planeTicket.getDiscount().getDiscountValue() * 100 + "%", planeTicket.getSeat().getSeatNum(),
//                planeTicket.getPrice() + " PLN");
//        assertEquals(expectedOutput, SystemVisualiser.planeTicketVisualiser(planeTicket));
//    }
//
//    @Test
//    void testTrainTicketVisualiser() {
//        String expectedOutput = String.format("# %15s # %13s # %19s # %13s # %19s # %6s # %13s # %19s # %10s #",
//                trainTicket.getTicketId(), trainTicket.getReservation().getLine().getDepartCity().getVisibleName(),
//                Dates.dateToString(trainTicket.getReservation().getLine().getDepartDate()),
//                trainTicket.getReservation().getLine().getArrivalCity().getVisibleName(),
//                Dates.dateToString(trainTicket.getReservation().getLine().getArrivalDate()),
//                trainTicket.getDiscount().getDiscountValue() * 100 + "%", trainTicket.getSeat().getSeatNum(),
//                ((TrainSeat) trainTicket.getSeat()).getCarNum(), trainTicket.getPrice() + " PLN");
//        assertEquals(expectedOutput, SystemVisualiser.trainTicketVisualiser(trainTicket));
//    }
//
//    @Test
//    void testBusTicketVisualiser() {
//        String expectedOutput = String.format("# %15s # %13s # %19s # %13s # %19s # %6s # %13s # %10s #",
//                busTicket.getTicketId(), busTicket.getReservation().getLine().getDepartCity().getVisibleName(),
//                Dates.dateToString(busTicket.getReservation().getLine().getDepartDate()),
//                busTicket.getReservation().getLine().getArrivalCity().getVisibleName(),
//                Dates.dateToString(busTicket.getReservation().getLine().getArrivalDate()),
//                busTicket.getDiscount().getDiscountValue() * 100 + "%", busTicket.getSeat().getSeatNum(), busTicket.getPrice() + " PLN");
//        assertEquals(expectedOutput, SystemVisualiser.busTicketVisualiser(busTicket));
//    }
//
//    @Test
//    void testReservationVisualiser() {
//        String expectedOutput = String.format("# %15s # %13s # %19s # %13s # %19s # %14s # %13s # %19s # %19s #",
//                busReservation.getReservationId(), busReservation.getLine().getDepartCity().getVisibleName(),
//                Dates.dateToString(busReservation.getLine().getDepartDate()),
//                busReservation.getLine().getArrivalCity().getVisibleName(),
//                Dates.dateToString(busReservation.getLine().getArrivalDate()), busReservation.getTickets().size(), "TAK",
//                Dates.dateToString(busReservation.getBookingDate()), Dates.dateToString(busReservation.getExpirationDate()));
//        assertEquals(expectedOutput, SystemVisualiser.reservationVisualiser(busReservation));
//    }
//} 604-911
package utils;

import carriers.BusCarrier;
import carriers.PlaneCarrier;
import carriers.TrainCarrier;
import lines.BusLine;
import lines.City;
import lines.PlaneLine;
import lines.TrainLine;
import reservations.*;
import seats.BusSeat;
import seats.PlaneSeat;
import seats.SeatClass;
import seats.TrainSeat;
import users.User;
import users.UserType;
import utils.Dates;
import utils.Hashing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SystemVisualiserTest {

    private PlaneLine planeLine;
    private TrainLine trainLine;
    private BusLine busLine;
    private PlaneSeat planeSeat;
    private TrainSeat trainSeat;
    private BusSeat busSeat;
    private PlaneTicket planeTicket;
    private TrainTicket trainTicket;
    private BusTicket busTicket;
    private Reservation busReservation;
    private Reservation planeReservation;
    private Reservation trainReservation;
    private User user;
    private User administrator;

    @BeforeEach
    void setUp() throws Exception {
        // Create plane seats
        planeSeat = new PlaneSeat(SeatClass.FIRST, 1, true);
        List<PlaneSeat> planeSeats = new ArrayList<>();
        for (int i = 1; i <= 50; i++) {
            planeSeats.add(new PlaneSeat(SeatClass.FIRST, i, false));
            planeSeats.add(new PlaneSeat(SeatClass.SECOND, i + 50, false));
        }

        // Create train seats
        trainSeat = new TrainSeat(SeatClass.FIRST, 1, true, 1);
        List<TrainSeat> trainSeats = new ArrayList<>();
        for (int i = 1; i <= 30; i++) {
            trainSeats.add(new TrainSeat(SeatClass.FIRST, i, false, i % 10));
            trainSeats.add(new TrainSeat(SeatClass.SECOND, i + 30, false, i % 10));
        }

        // Create bus seats
        busSeat = new BusSeat(SeatClass.FIRST, 1, true);
        List<BusSeat> busSeats = new ArrayList<>();
        for (int i = 1; i <= 30; i++) {
            busSeats.add(new BusSeat(SeatClass.FIRST, i, false));
        }

        // Create lines
        planeLine = new PlaneLine(planeSeats, 360.0, 280.0, PlaneCarrier.RYANAIR);
        planeLine.setDepartCity(City.KRAKOW);
        planeLine.setArrivalCity(City.WARSZAWA);
        planeLine.setDepartDate(Dates.dateFromString("2023-02-02 17:30:00"));
        planeLine.setArrivalDate(Dates.dateFromString("2023-02-02 17:57:00"));

        trainLine = new TrainLine(trainSeats, 64.0, 42.0, TrainCarrier.KOLEJE_SLASKIE);
        trainLine.setDepartCity(City.KRAKOW);
        trainLine.setArrivalCity(City.ZAWIERCIE);
        trainLine.setDepartDate(Dates.dateFromString("2023-02-02 16:23:00"));
        trainLine.setArrivalDate(Dates.dateFromString("2023-02-02 17:26:00"));

        busLine = new BusLine(busSeats, 30.0, 15.0, BusCarrier.SZWAGROPOL);
        busLine.setDepartCity(City.NOWY_SACZ);
        busLine.setArrivalCity(City.KRAKOW);
        busLine.setDepartDate(Dates.dateFromString("2023-02-02 12:05:00"));
        busLine.setArrivalDate(Dates.dateFromString("2023-02-02 14:59:00"));

        // Create reservations
        busReservation = new Reservation("2023/01/31/1", busLine);
        busReservation.setPaid(true);

        planeReservation = new Reservation("2023/01/31/2", planeLine);
        planeReservation.setPaid(true);

        trainReservation = new Reservation("2023/01/31/3", trainLine);
        trainReservation.setPaid(true);

        // Create a bus ticket
        BusSeat reservedBusSeat = busSeats.get(0);
        reservedBusSeat.setAvailable(false);
        busTicket = new BusTicket("2023/01/31/1/1", busReservation);
        busTicket.setDiscount(Discount.STUDENT);
        busTicket.setSeat(reservedBusSeat);
        busReservation.addTicket(busTicket);

        // Create a plane ticket
        PlaneSeat reservedPlaneSeat = planeSeats.get(0);
        reservedPlaneSeat.setAvailable(false);
        planeTicket = new PlaneTicket("TICKET123", planeReservation, true, true, true);
        planeTicket.setSeat(reservedPlaneSeat);
        planeReservation.addTicket(planeTicket);

        // Create a train ticket
        TrainSeat reservedTrainSeat = trainSeats.get(0);
        reservedTrainSeat.setAvailable(false);
        trainTicket = new TrainTicket("TICKET456", trainReservation, true, true);
        trainTicket.setSeat(reservedTrainSeat);
        trainReservation.addTicket(trainTicket);

        // Create users
        user = new User("jan.kowalski@gmail.com", Hashing.hashPassword("hackme123"));
        user.setFirstname("Jan");
        user.setLastname("Kowalski");
        user.setUserType(UserType.PASSENGER);
        user.addReservation(busReservation);
        user.addReservation(planeReservation);
        user.addReservation(trainReservation);

        administrator = new User("edyta.nowak@gmail.com", Hashing.hashPassword("hackme123"));
        administrator.setFirstname("Edyta");
        administrator.setLastname("Nowak");
        administrator.setUserType(UserType.ADMINISTRATOR);
    }

    @Test
    void testPlaneLineVisualiserFormat() {
        String expectedFormat = "#          Skąd #              Wyjazd #          Dokąd #            Przyjazd #      Pojazd #                 Przewoźnik #   Cena w 1 kl. #   Cena w 2 kl. #  Opłata za dodatk. bagaż #  Opłata za zwierzę w kabinie # Opłata za zwierzę w lufcie #";
        assertEquals(expectedFormat, SystemVisualiser.planeLineVisualiserFormat());
    }

    @Test
    void testTrainLineVisualiserFormat() {
        String expectedFormat = "#          Skąd #              Wyjazd #          Dokąd #            Przyjazd #      Pojazd #                 Przewoźnik #   Cena w 1 kl. #   Cena w 2 kl. #      Opłata za zwierzę #   Opłata za rower #  Opłata za stolik #";
        assertEquals(expectedFormat, SystemVisualiser.trainLineVisualiserFormat());
    }

    @Test
    void testBusLineVisualiserFormat() {
        String expectedFormat = "#          Skąd #              Wyjazd #          Dokąd #            Przyjazd #      Pojazd #                 Przewoźnik #   Cena w 1 kl. #   Cena w 2 kl. #    Opłata za gniazdko #";
        assertEquals(expectedFormat, SystemVisualiser.busLineVisualiserFormat());
    }

    @Test
    void testUserVisualiserFormat() {
        String expectedFormat = "#     Typ konta #                    Adres email #                 Imię #             Nazwisko # Liczba rezerwacji #";
        assertEquals(expectedFormat, SystemVisualiser.userVisualiserFormat());
    }

    @Test
    void testPlaneSeatVisualiserFormat() {
        String expectedFormat = "# Numer miejsca # Czy więcej miejsca? #";
        assertEquals(expectedFormat, SystemVisualiser.planeSeatVisualiserFormat());
    }

    @Test
    void testTrainSeatVisualiserFormat() {
        String expectedFormat = "# Numer miejsca # Numer przedziału # Czy ma stolik? #";
        assertEquals(expectedFormat, SystemVisualiser.trainSeatVisualiserFormat());
    }

    @Test
    void testBusSeatVisualiserFormat() {
        String expectedFormat = "# Numer miejsca #   Czy jest gniazdko? #";
        assertEquals(expectedFormat, SystemVisualiser.busSeatVisualiserFormat());
    }

    @Test
    void testPlaneTicketVisualiserFormat() {
        String expectedFormat = "#           Numer #          Skąd #              Wyjazd #         Dokąd #            Przyjazd # Zniżka # Numer miejsca #       Cena #";
        assertEquals(expectedFormat, SystemVisualiser.planeTicketVisualiserFormat());
    }

    @Test
    void testTrainTicketVisualiserFormat() {
        String expectedFormat = "#           Numer #          Skąd #              Wyjazd #         Dokąd #            Przyjazd # Zniżka # Numer miejsca #    Numer przedziału #       Cena #";
        assertEquals(expectedFormat, SystemVisualiser.trainTicketVisualiserFormat());
    }

    @Test
    void testBusTicketVisualiserFormat() {
        String expectedFormat = "#           Numer #          Skąd #              Wyjazd #         Dokąd #            Przyjazd # Zniżka # Numer miejsca #       Cena #";
        assertEquals(expectedFormat, SystemVisualiser.busTicketVisualiserFormat());
    }

    @Test
    void testReservationVisualiserFormat() {
        String expectedFormat = "#           Numer #          Skąd #              Wyjazd #         Dokąd #            Przyjazd # Liczba biletów # Czy opłacony? #          Rezerwacja #              Wygasa #";
        assertEquals(expectedFormat, SystemVisualiser.reservationVisualiserFormat());
    }

    @Test
    void testPlaneLineVisualiser() {
        String expectedOutput = String.format("# %13s # %19s # %13s # %19s # %10s # %26s # %12s # %12s # %23s # %27s # %26s #",
                planeLine.getDepartCity().getVisibleName(), Dates.dateToString(planeLine.getDepartDate()),
                planeLine.getArrivalCity().getVisibleName(), Dates.dateToString(planeLine.getArrivalDate()), "Samolot",
                planeLine.getCarrier().getVisibleName(), planeLine.getFirstClassPrice() + " PLN",
                planeLine.getSecondClassPrice() + " PLN", planeLine.getAdditionalLuggagePrice() + " PLN",
                planeLine.getInCabinPetPrice() + " PLN", planeLine.getInBaggageHoldPetPrice() + " PLN");
        assertEquals(expectedOutput, SystemVisualiser.planeLineVisualiser(planeLine));
    }

    @Test
    void testTrainLineVisualiser() {
        String expectedOutput = String.format("# %13s # %19s # %13s # %19s # %10s # %26s # %12s # %12s # %17s # %15s # %15s #",
                trainLine.getDepartCity().getVisibleName(), Dates.dateToString(trainLine.getDepartDate()),
                trainLine.getArrivalCity().getVisibleName(), Dates.dateToString(trainLine.getArrivalDate()), "Pociąg",
                trainLine.getCarrier().getVisibleName(), trainLine.getFirstClassPrice() + " PLN",
                trainLine.getSecondClassPrice() + " PLN", trainLine.getPetPrice() + " PLN",
                trainLine.getBicyclePrice() + " PLN", trainLine.getTableSeatPrice() + " PLN");
        assertEquals(expectedOutput, SystemVisualiser.trainLineVisualiser(trainLine));
    }

    @Test
    void testBusLineVisualiser() {
        String expectedOutput = String.format("# %13s # %19s # %13s # %19s # %10s # %26s # %12s # %12s # %18s #",
                busLine.getDepartCity().getVisibleName(), Dates.dateToString(busLine.getDepartDate()),
                busLine.getArrivalCity().getVisibleName(), Dates.dateToString(busLine.getArrivalDate()), "Autobus",
                busLine.getCarrier().getVisibleName(), busLine.getFirstClassPrice() + " PLN",
                busLine.getSecondClassPrice() + " PLN", busLine.getElectricalOutletSeatPrice() + " PLN");
        assertEquals(expectedOutput, SystemVisualiser.busLineVisualiser(busLine));
    }

    @Test
    void testUserVisualiser() {
        String expectedOutput = String.format("# %13s # %30s # %20s # %20s # %17s #",
                user.getUserType().getVisibleName(), user.getEmail(), user.getFirstname(), user.getLastname(),
                user.getReservations().size());
        assertEquals(expectedOutput, SystemVisualiser.userVisualiser(user));
    }

    @Test
    void testPlaneSeatVisualiser() {
        String expectedOutput = String.format("# %13s # %19s #",
                planeSeat.getSeatNum(), "TAK");
        assertEquals(expectedOutput, SystemVisualiser.planeSeatVisualiser(planeSeat));
    }

    @Test
    void testTrainSeatVisualiser() {
        String expectedOutput = String.format("# %13s # %16s # %14s #",
                trainSeat.getSeatNum(), trainSeat.getCarNum(), "TAK");
        assertEquals(expectedOutput, SystemVisualiser.trainSeatVisualiser(trainSeat));
    }

    @Test
    void testBusSeatVisualiser() {
        String expectedOutput = String.format("# %13s # %18s #",
                busSeat.getSeatNum(), "TAK");
        assertEquals(expectedOutput, SystemVisualiser.busSeatVisualiser(busSeat));
    }

    @Test
    void testPlaneTicketVisualiser() {
        String expectedOutput = String.format("# %15s # %13s # %19s # %13s # %19s # %6s # %13s # %10s #",
                planeTicket.getTicketId(), planeTicket.getReservation().getLine().getDepartCity().getVisibleName(),
                Dates.dateToString(planeTicket.getReservation().getLine().getDepartDate()),
                planeTicket.getReservation().getLine().getArrivalCity().getVisibleName(),
                Dates.dateToString(planeTicket.getReservation().getLine().getArrivalDate()),
                planeTicket.getDiscount().getDiscountValue() * 100 + "%", planeTicket.getSeat().getSeatNum(),
                planeTicket.getPrice() + " PLN");
        assertEquals(expectedOutput, SystemVisualiser.planeTicketVisualiser(planeTicket));
    }

    @Test
    void testTrainTicketVisualiser() {
        String expectedOutput = String.format("# %15s # %13s # %19s # %13s # %19s # %6s # %13s # %19s # %10s #",
                trainTicket.getTicketId(), trainTicket.getReservation().getLine().getDepartCity().getVisibleName(),
                Dates.dateToString(trainTicket.getReservation().getLine().getDepartDate()),
                trainTicket.getReservation().getLine().getArrivalCity().getVisibleName(),
                Dates.dateToString(trainTicket.getReservation().getLine().getArrivalDate()),
                trainTicket.getDiscount().getDiscountValue() * 100 + "%", trainTicket.getSeat().getSeatNum(),
                ((TrainSeat) trainTicket.getSeat()).getCarNum(), trainTicket.getPrice() + " PLN");
        assertEquals(expectedOutput, SystemVisualiser.trainTicketVisualiser(trainTicket));
    }

    @Test
    void testBusTicketVisualiser() {
        String expectedOutput = String.format("# %15s # %13s # %19s # %13s # %19s # %6s # %13s # %10s #",
                busTicket.getTicketId(), busTicket.getReservation().getLine().getDepartCity().getVisibleName(),
                Dates.dateToString(busTicket.getReservation().getLine().getDepartDate()),
                busTicket.getReservation().getLine().getArrivalCity().getVisibleName(),
                Dates.dateToString(busTicket.getReservation().getLine().getArrivalDate()),
                busTicket.getDiscount().getDiscountValue() * 100 + "%", busTicket.getSeat().getSeatNum(), busTicket.getPrice() + " PLN");
        assertEquals(expectedOutput, SystemVisualiser.busTicketVisualiser(busTicket));
    }

    @Test
    void testReservationVisualiser() {
        String expectedOutput = String.format("# %15s # %13s # %19s # %13s # %19s # %14s # %13s # %19s # %19s #",
                busReservation.getReservationId(), busReservation.getLine().getDepartCity().getVisibleName(),
                Dates.dateToString(busReservation.getLine().getDepartDate()),
                busReservation.getLine().getArrivalCity().getVisibleName(),
                Dates.dateToString(busReservation.getLine().getArrivalDate()), busReservation.getTickets().size(), "TAK",
                Dates.dateToString(busReservation.getBookingDate()), Dates.dateToString(busReservation.getExpirationDate()));
        assertEquals(expectedOutput, SystemVisualiser.reservationVisualiser(busReservation));
    }
}
