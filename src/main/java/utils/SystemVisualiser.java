//package utils;
//
//import lines.BusLine;
//import lines.PlaneLine;
//import lines.TrainLine;
//import reservations.BusTicket;
//import reservations.PlaneTicket;
//import reservations.Reservation;
//import reservations.TrainTicket;
//import seats.BusSeat;
//import seats.PlaneSeat;
//import seats.TrainSeat;
//import users.User;
//
//public class SystemVisualiser {
//    public static String planeLineVisualiserFormat() {
//        return String.format("# %13s # %19s # %13s # %19s # %10s # %26s # %12s # %12s # %23s # %27s # %26s #",
//                "Skąd", "Wyjazd", "Dokąd", "Przyjazd", "Pojazd", "Przewoźnik", "Cena w 1 kl.", "Cena w 2 kl.",
//                "Opłata za dodatk. bagaż", "Opłata za zwierzę w kabinie", "Opłata za zwierzę w lufcie");
//    }
//
//    public static String trainLineVisualiserFormat() {
//        return String.format("# %13s # %19s # %13s # %19s # %10s # %26s # %12s # %12s # %17s # %15s # %15s #",
//                "Skąd", "Wyjazd", "Dokąd", "Przyjazd", "Pojazd", "Przewoźnik", "Cena w 1 kl.", "Cena w 2 kl.",
//                "Opłata za zwierzę", "Opłata za rower", "Opłata za stolik");
//    }
//
//    public static String busLineVisualiserFormat() {
//        return String.format("# %13s # %19s # %13s # %19s # %10s # %26s # %12s # %12s # %18s #",
//                "Skąd", "Wyjazd", "Dokąd", "Przyjazd", "Pojazd", "Przewoźnik", "Cena w 1 kl.", "Cena w 2 kl.",
//                "Opłata za gniazdko");
//    }
//
//    public static String userVisualiserFormat() {
//        return String.format("# %13s # %30s # %20s # %20s # %17s #",
//                "Typ konta", "Adres email", "Imię", "Nazwisko", "Liczba rezerwacji");
//    }
//
//    public static String planeSeatVisualiserFormat() {
//        return String.format("# %13s # %19s #",
//                "Numer miejsca", "Czy więcej miejsca?");
//    }
//
//    public static String trainSeatVisualiserFormat() {
//        return String.format("# %13s # %16s # %14s #",
//                "Numer miejsca", "Numer przedziału", "Czy ma stolik?");
//    }
//
//    public static String busSeatVisualiserFormat() {
//        return String.format("# %13s # %18s #",
//                "Numer miejsca", "Czy jest gniazdko?");
//    }
//
//    public static String planeTicketVisualiserFormat() {
//        return String.format("# %15s # %13s # %19s # %13s # %19s # %6s # %13s # %10s #",
//                "Numer", "Skąd", "Wyjazd", "Dokąd", "Przyjazd", "Zniżka", "Numer miejsca", "Cena");
//    }
//
//    public static String trainTicketVisualiserFormat() {
//        return String.format("# %15s # %13s # %19s # %13s # %19s # %6s # %13s # %19s # %10s #",
//                "Numer", "Skąd", "Wyjazd", "Dokąd", "Przyjazd", "Zniżka", "Numer miejsca", "Numer przedziału", "Cena");
//    }
//
//    public static String busTicketVisualiserFormat() {
//        return String.format("# %15s # %13s # %19s # %13s # %19s # %6s # %13s # %10s #",
//                "Numer", "Skąd", "Wyjazd", "Dokąd", "Przyjazd", "Zniżka", "Numer miejsca", "Cena");
//    }
//
//    public static String reservationVisualiserFormat() {
//        return String.format("# %15s # %13s # %19s # %13s # %19s # %14s # %13s # %19s # %19s #",
//                "Numer", "Skąd", "Wyjazd", "Dokąd", "Przyjazd", "Liczba biletów", "Czy opłacony?", "Rezerwacja", "Wygasa");
//    }
//
//    public static String planeLineVisualiser(PlaneLine line) {
//        String secondClassPrice = "Nie dot.";
//        if (line.getSecondClassPrice() != null)
//            secondClassPrice = line.getSecondClassPrice() + " PLN";
//
//        return String.format("# %13s # %19s # %13s # %19s # %10s # %26s # %12s # %12s # %23s # %27s # %26s #",
//                line.getDepartCity().getVisibleName(), Dates.dateToString(line.getDepartDate()),
//                line.getArrivalCity().getVisibleName(), Dates.dateToString(line.getArrivalDate()), "Samolot",
//                line.getCarrier().getVisibleName(), line.getFirstClassPrice() + " PLN",
//                secondClassPrice, line.getAdditionalLuggagePrice() + " PLN",
//                line.getInCabinPetPrice() + " PLN", line.getInBaggageHoldPetPrice() + " PLN");
//    }
//
//    public static String trainLineVisualiser(TrainLine line) {
//        String secondClassPrice = "Nie dot.";
//        if (line.getSecondClassPrice() != null)
//            secondClassPrice = line.getSecondClassPrice() + " PLN";
//
//        return String.format("# %13s # %19s # %13s # %19s # %10s # %26s # %12s # %12s # %17s # %15s # %15s #",
//                line.getDepartCity().getVisibleName(), Dates.dateToString(line.getDepartDate()),
//                line.getArrivalCity().getVisibleName(), Dates.dateToString(line.getArrivalDate()), "Pociąg",
//                line.getCarrier().getVisibleName(), line.getFirstClassPrice() + " PLN",
//                secondClassPrice, line.getPetPrice() + " PLN", line.getBicyclePrice() + " PLN",
//                line.getTableSeatPrice() + " PLN");
//    }
//
//    public static String busLineVisualiser(BusLine line) {
//        String secondClassPrice = "Nie dot.";
//        if (line.getSecondClassPrice() != null)
//            secondClassPrice = line.getSecondClassPrice() + " PLN";
//
//        return String.format("# %13s # %19s # %13s # %19s # %10s # %26s # %12s # %12s # %18s #",
//                line.getDepartCity().getVisibleName(), Dates.dateToString(line.getDepartDate()),
//                line.getArrivalCity().getVisibleName(), Dates.dateToString(line.getArrivalDate()), "Autobus",
//                line.getCarrier().getVisibleName(), line.getFirstClassPrice() + " PLN",
//                secondClassPrice, line.getElectricalOutletSeatPrice() + " PLN");
//    }
//
//    public static String userVisualiser(User user) {
//        return String.format("# %13s # %30s # %20s # %20s # %17s #",
//                user.getUserType().getVisibleName(), user.getEmail(), user.getFirstname(), user.getLastname(),
//                user.getReservations().size());
//    }
//
//    public static String planeSeatVisualiser(PlaneSeat seat) {
//        String hasMoreSpace = "NIE";
//        if (seat.hasMoreSpace())
//            hasMoreSpace = "TAK";
//
//        return String.format("# %13s # %19s #",
//                seat.getSeatNum(), hasMoreSpace);
//    }
//
//    public static String trainSeatVisualiser(TrainSeat seat) {
//        String hasTable = "NIE";
//        if (seat.hasTable())
//            hasTable = "TAK";
//
//        return String.format("# %13s # %16s # %14s #",
//                seat.getSeatNum(), seat.getCarNum(), hasTable);
//    }
//
//    public static String busSeatVisualiser(BusSeat seat) {
//        String hasElectricalOutlet = "NIE";
//        if (seat.hasElectricalOutlet())
//            hasElectricalOutlet = "TAK";
//
//        return String.format("# %13s # %18s #",
//                seat.getSeatNum(), hasElectricalOutlet);
//    }
//
//    public static String planeTicketVisualiser(PlaneTicket ticket) {
//        return String.format("# %15s # %13s # %19s # %13s # %19s # %6s # %13s # %10s #",
//                ticket.getTicketId(), ticket.getReservation().getLine().getDepartCity().getVisibleName(),
//                Dates.dateToString(ticket.getReservation().getLine().getDepartDate()),
//                ticket.getReservation().getLine().getArrivalCity().getVisibleName(),
//                Dates.dateToString(ticket.getReservation().getLine().getArrivalDate()),
//                ticket.getDiscount().getDiscountValue() * 100 + "%",
//                ticket.getSeat().getSeatNum(), ticket.getPrice() + " PLN");
//    }
//
//    public static String trainTicketVisualiser(TrainTicket ticket) {
//        return String.format("# %15s # %13s # %19s # %13s # %19s # %6s # %13s # %19s # %10s #",
//                ticket.getTicketId(), ticket.getReservation().getLine().getDepartCity().getVisibleName(),
//                Dates.dateToString(ticket.getReservation().getLine().getDepartDate()),
//                ticket.getReservation().getLine().getArrivalCity().getVisibleName(),
//                Dates.dateToString(ticket.getReservation().getLine().getArrivalDate()),
//                ticket.getDiscount().getDiscountValue() * 100 + "%", ticket.getSeat().getSeatNum(),
//                ((TrainSeat) ticket.getSeat()).getCarNum(), ticket.getPrice() + " PLN");
//    }
//
//    public static String busTicketVisualiser(BusTicket ticket) {
//        return String.format("# %15s # %13s # %19s # %13s # %19s # %6s # %13s # %10s #",
//                ticket.getTicketId(), ticket.getReservation().getLine().getDepartCity().getVisibleName(),
//                Dates.dateToString(ticket.getReservation().getLine().getDepartDate()),
//                ticket.getReservation().getLine().getArrivalCity().getVisibleName(),
//                Dates.dateToString(ticket.getReservation().getLine().getArrivalDate()),
//                ticket.getDiscount().getDiscountValue() * 100 + "%",
//                ticket.getSeat().getSeatNum(), ticket.getPrice() + " PLN");
//    }
//
//    public static String reservationVisualiser(Reservation reservation) {
//        String isPaid = "NIE";
//        if (reservation.isPaid())
//            isPaid = "TAK";
//
//        return String.format("# %15s # %13s # %19s # %13s # %19s # %14s # %13s # %19s # %19s #",
//                reservation.getReservationId(), reservation.getLine().getDepartCity().getVisibleName(),
//                Dates.dateToString(reservation.getLine().getDepartDate()),
//                reservation.getLine().getArrivalCity().getVisibleName(),
//                Dates.dateToString(reservation.getLine().getArrivalDate()), reservation.getTickets().size(), isPaid,
//                Dates.dateToString(reservation.getBookingDate()), Dates.dateToString(reservation.getExpirationDate()));
//    }
//}


package utils;

import lines.BusLine;
import lines.PlaneLine;
import lines.TrainLine;
import reservations.BusTicket;
import reservations.PlaneTicket;
import reservations.Reservation;
import reservations.TrainTicket;
import seats.BusSeat;
import seats.PlaneSeat;
import seats.TrainSeat;
import users.User;

public class SystemVisualiser {
    public static String planeLineVisualiserFormat() {
        return "#          Skąd #              Wyjazd #          Dokąd #            Przyjazd #      Pojazd #                 Przewoźnik #   Cena w 1 kl. #   Cena w 2 kl. #  Opłata za dodatk. bagaż #  Opłata za zwierzę w kabinie # Opłata za zwierzę w lufcie #";
    }

    public static String trainLineVisualiserFormat() {
        return "#          Skąd #              Wyjazd #          Dokąd #            Przyjazd #      Pojazd #                 Przewoźnik #   Cena w 1 kl. #   Cena w 2 kl. #      Opłata za zwierzę #   Opłata za rower #  Opłata za stolik #";
    }

    public static String busLineVisualiserFormat() {
        return "#          Skąd #              Wyjazd #          Dokąd #            Przyjazd #      Pojazd #                 Przewoźnik #   Cena w 1 kl. #   Cena w 2 kl. #    Opłata za gniazdko #";
    }

    public static String userVisualiserFormat() {
        return "#     Typ konta #                    Adres email #                 Imię #             Nazwisko # Liczba rezerwacji #";
    }

    public static String planeSeatVisualiserFormat() {
        return "# Numer miejsca # Czy więcej miejsca? #";
    }

    public static String trainSeatVisualiserFormat() {
        return "# Numer miejsca # Numer przedziału # Czy ma stolik? #";
    }

    public static String busSeatVisualiserFormat() {
        return "# Numer miejsca #   Czy jest gniazdko? #";
    }

    public static String planeTicketVisualiserFormat() {
        return "#           Numer #          Skąd #              Wyjazd #         Dokąd #            Przyjazd # Zniżka # Numer miejsca #       Cena #";
    }

    public static String trainTicketVisualiserFormat() {
        return "#           Numer #          Skąd #              Wyjazd #         Dokąd #            Przyjazd # Zniżka # Numer miejsca #    Numer przedziału #       Cena #";
    }

    public static String busTicketVisualiserFormat() {
        return "#           Numer #          Skąd #              Wyjazd #         Dokąd #            Przyjazd # Zniżka # Numer miejsca #       Cena #";
    }

    public static String reservationVisualiserFormat() {
        return "#           Numer #          Skąd #              Wyjazd #         Dokąd #            Przyjazd # Liczba biletów # Czy opłacony? #          Rezerwacja #              Wygasa #";
    }

    public static String planeLineVisualiser(PlaneLine line) {
        return String.format("# %13s # %19s # %13s # %19s # %10s # %26s # %12s # %12s # %23s # %27s # %26s #",
                line.getDepartCity().getVisibleName(), Dates.dateToString(line.getDepartDate()),
                line.getArrivalCity().getVisibleName(), Dates.dateToString(line.getArrivalDate()), "Samolot",
                line.getCarrier().getVisibleName(), line.getFirstClassPrice() + " PLN",
                line.getSecondClassPrice() + " PLN", line.getAdditionalLuggagePrice() + " PLN",
                line.getInCabinPetPrice() + " PLN", line.getInBaggageHoldPetPrice() + " PLN");
    }

    public static String trainLineVisualiser(TrainLine line) {
        return String.format("# %13s # %19s # %13s # %19s # %10s # %26s # %12s # %12s # %17s # %15s # %15s #",
                line.getDepartCity().getVisibleName(), Dates.dateToString(line.getDepartDate()),
                line.getArrivalCity().getVisibleName(), Dates.dateToString(line.getArrivalDate()), "Pociąg",
                line.getCarrier().getVisibleName(), line.getFirstClassPrice() + " PLN",
                line.getSecondClassPrice() + " PLN", line.getPetPrice() + " PLN", line.getBicyclePrice() + " PLN",
                line.getTableSeatPrice() + " PLN");
    }

    public static String busLineVisualiser(BusLine line) {
        return String.format("# %13s # %19s # %13s # %19s # %10s # %26s # %12s # %12s # %18s #",
                line.getDepartCity().getVisibleName(), Dates.dateToString(line.getDepartDate()),
                line.getArrivalCity().getVisibleName(), Dates.dateToString(line.getArrivalDate()), "Autobus",
                line.getCarrier().getVisibleName(), line.getFirstClassPrice() + " PLN",
                line.getSecondClassPrice() + " PLN", line.getElectricalOutletSeatPrice() + " PLN");
    }

    public static String userVisualiser(User user) {
        return String.format("# %13s # %30s # %20s # %20s # %17s #",
                user.getUserType().getVisibleName(), user.getEmail(), user.getFirstname(), user.getLastname(),
                user.getReservations().size());
    }

    public static String planeSeatVisualiser(PlaneSeat seat) {
        String hasMoreSpace = seat.hasMoreSpace() ? "TAK" : "NIE";
        return String.format("# %13s # %19s #", seat.getSeatNum(), hasMoreSpace);
    }

    public static String trainSeatVisualiser(TrainSeat seat) {
        String hasTable = seat.hasTable() ? "TAK" : "NIE";
        return String.format("# %13s # %16s # %14s #", seat.getSeatNum(), seat.getCarNum(), hasTable);
    }

    public static String busSeatVisualiser(BusSeat seat) {
        String hasElectricalOutlet = seat.hasElectricalOutlet() ? "TAK" : "NIE";
        return String.format("# %13s # %18s #", seat.getSeatNum(), hasElectricalOutlet);
    }

    public static String planeTicketVisualiser(PlaneTicket ticket) {
        return String.format("# %15s # %13s # %19s # %13s # %19s # %6s # %13s # %10s #",
                ticket.getTicketId(), ticket.getReservation().getLine().getDepartCity().getVisibleName(),
                Dates.dateToString(ticket.getReservation().getLine().getDepartDate()),
                ticket.getReservation().getLine().getArrivalCity().getVisibleName(),
                Dates.dateToString(ticket.getReservation().getLine().getArrivalDate()),
                ticket.getDiscount().getDiscountValue() * 100 + "%", ticket.getSeat().getSeatNum(),
                ticket.getPrice() + " PLN");
    }

    public static String trainTicketVisualiser(TrainTicket ticket) {
        return String.format("# %15s # %13s # %19s # %13s # %19s # %6s # %13s # %19s # %10s #",
                ticket.getTicketId(), ticket.getReservation().getLine().getDepartCity().getVisibleName(),
                Dates.dateToString(ticket.getReservation().getLine().getDepartDate()),
                ticket.getReservation().getLine().getArrivalCity().getVisibleName(),
                Dates.dateToString(ticket.getReservation().getLine().getArrivalDate()),
                ticket.getDiscount().getDiscountValue() * 100 + "%", ticket.getSeat().getSeatNum(),
                ((TrainSeat) ticket.getSeat()).getCarNum(), ticket.getPrice() + " PLN");
    }

    public static String busTicketVisualiser(BusTicket ticket) {
        return String.format("# %15s # %13s # %19s # %13s # %19s # %6s # %13s # %10s #",
                ticket.getTicketId(), ticket.getReservation().getLine().getDepartCity().getVisibleName(),
                Dates.dateToString(ticket.getReservation().getLine().getDepartDate()),
                ticket.getReservation().getLine().getArrivalCity().getVisibleName(),
                Dates.dateToString(ticket.getReservation().getLine().getArrivalDate()),
                ticket.getDiscount().getDiscountValue() * 100 + "%", ticket.getSeat().getSeatNum(),
                ticket.getPrice() + " PLN");
    }

    public static String reservationVisualiser(Reservation reservation) {
        String isPaid = reservation.isPaid() ? "TAK" : "NIE";
        return String.format("# %15s # %13s # %19s # %13s # %19s # %14s # %13s # %19s # %19s #",
                reservation.getReservationId(), reservation.getLine().getDepartCity().getVisibleName(),
                Dates.dateToString(reservation.getLine().getDepartDate()),
                reservation.getLine().getArrivalCity().getVisibleName(),
                Dates.dateToString(reservation.getLine().getArrivalDate()), reservation.getTickets().size(), isPaid,
                Dates.dateToString(reservation.getBookingDate()), Dates.dateToString(reservation.getExpirationDate()));
    }
}
