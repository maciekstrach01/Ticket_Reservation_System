package reservations;

import seats.Seat;

public abstract class Ticket {
    private final String ticketId;
    protected final Reservation reservation;
    protected Discount discount;
    protected Seat seat;

    public Ticket(String ticketId, Reservation reservation) {
        this.ticketId = ticketId;
        this.reservation = reservation;
    }

    public String getTicketId() {
        return this.ticketId;
    }

    public Reservation getReservation() {
        return this.reservation;
    }

    public Discount getDiscount() {
        return this.discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    public Seat getSeat() {
        return this.seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public abstract Double getPrice();
}
