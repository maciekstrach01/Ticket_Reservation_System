package reservations;

import lines.Line;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Reservation {
    private final String reservationId;
    private final Line<?, ?> line;
    private final List<Ticket> tickets = new ArrayList<>();
    private final LocalDateTime bookingDate;
    private final LocalDateTime expirationDate;
    private boolean isPaid;

    public Reservation(String reservationId, Line<?, ?> line) {
        this.reservationId = reservationId;
        this.line = line;

        this.bookingDate = LocalDateTime.now();
        this.expirationDate = this.bookingDate.plusDays(1);
    }

    public String getReservationId() {
        return this.reservationId;
    }

    public Line<?, ?> getLine() {
        return this.line;
    }

    public List<Ticket> getTickets() {
        return this.tickets;
    }

    public void addTicket(Ticket ticket) {
        this.tickets.add(ticket);
    }

    public void removeTicket(Ticket ticket) {
        this.tickets.remove(ticket);
    }

    public LocalDateTime getBookingDate() {
        return this.bookingDate;
    }

    public LocalDateTime getExpirationDate() {
        return this.expirationDate;
    }

    public boolean isPaid() {
        return this.isPaid;
    }

    public void setPaid(boolean paid) {
        this.isPaid = paid;
    }
}
