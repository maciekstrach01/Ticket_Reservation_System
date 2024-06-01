package users;

import reservations.Reservation;
import reservations.Ticket;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class User {
    private final String email;
    private final String hashedPassword;
    private String firstname;
    private String lastname;
    private final List<Reservation> reservations = new ArrayList<>();
    private UserType userType;

    public User(String email, String hashedPassword) {
        this.email = email;
        this.hashedPassword = hashedPassword;
    }

    public String getEmail() {
        return this.email;
    }

    public String getFirstname() {
        return this.firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return this.lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public List<Reservation> getReservations() {
        return this.reservations;
    }

    public void addReservation(Reservation reservation) {
        this.reservations.add(reservation);
    }

    public void removeReservation(Reservation reservation) {
        this.reservations.remove(reservation);
    }

    public List<Ticket> getValidTickets() {
        List<Ticket> myTickets = new ArrayList<>();

        for (Reservation reservation : this.reservations)
            if (reservation.isPaid() && LocalDateTime.now().isBefore(reservation.getLine().getDepartDate()))
                myTickets.addAll(reservation.getTickets());

        return myTickets;
    }

    public boolean doPasswordsMatch(String hashedPassword) {
        return this.hashedPassword.equals(hashedPassword);
    }

    public UserType getUserType() {
        return this.userType;
    }

    public void setUserType(UserType type) {
        this.userType = type;
    }
}
