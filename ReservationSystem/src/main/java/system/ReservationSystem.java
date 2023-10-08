package system;

import lines.Line;
import reservations.Reservation;
import users.User;

import java.util.ArrayList;
import java.util.List;

public class ReservationSystem {
    private final List<Reservation> reservations = new ArrayList<>();
    private final List<Line<?, ?>> lines = new ArrayList<>();
    private final List<User> users = new ArrayList<>();

    public ReservationSystem() { }

    public List<Reservation> getReservations() {
        return this.reservations;
    }

    public void addReservation(Reservation reservation) {
        this.reservations.add(reservation);
    }

    public void removeReservation(Reservation reservation) {
        this.reservations.remove(reservation);
    }

    public List<Line<?, ?>> getLines() {
        return this.lines;
    }

    public void addLine(Line<?, ?> line) {
        this.lines.add(line);
    }

    public void removeLine(Line<?, ?> line) {
        this.lines.remove(line);
    }

    public List<User> getUsers() {
        return this.users;
    }

    public void addUser(User user) {
        this.users.add(user);
    }

    public void removeUser(User user) {
        this.users.remove(user);
    }
}
