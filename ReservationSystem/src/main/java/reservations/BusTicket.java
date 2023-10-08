package reservations;

import lines.BusLine;
import seats.BusSeat;
import seats.SeatClass;

public class BusTicket extends Ticket {
    public BusTicket(String ticketId, Reservation reservation) {
        super(ticketId, reservation);
    }

    @Override
    public Double getPrice() {
        double price = 0.0;

        if (this.seat.getSeatClass() == SeatClass.FIRST)
            price += this.reservation.getLine().getFirstClassPrice();
        else if (this.seat.getSeatClass() == SeatClass.SECOND)
            price += this.reservation.getLine().getSecondClassPrice();

        if (this.discount != null)
            price *= 1 - this.discount.getDiscountValue();

        if (((BusSeat) this.seat).hasElectricalOutlet())
            price += ((BusLine) this.reservation.getLine()).getElectricalOutletSeatPrice();

        return price;
    }
}
