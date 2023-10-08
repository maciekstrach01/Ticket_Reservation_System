package reservations;

import lines.TrainLine;
import seats.SeatClass;
import seats.TrainSeat;

public class TrainTicket extends Ticket {
    private final boolean pet;
    private final boolean bicycle;

    public TrainTicket(String ticketId, Reservation reservation, boolean pet, boolean bicycle) {
        super(ticketId, reservation);

        this.pet = pet;
        this.bicycle = bicycle;
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

        if (((TrainSeat) this.seat).hasTable())
            price += ((TrainLine) this.reservation.getLine()).getTableSeatPrice();

        if (this.pet)
            price += ((TrainLine) this.reservation.getLine()).getPetPrice();

        if (this.bicycle)
            price += ((TrainLine) this.reservation.getLine()).getBicyclePrice();

        return price;
    }
}
