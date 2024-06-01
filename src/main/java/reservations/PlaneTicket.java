////package reservations;
////
////import lines.PlaneLine;
////import seats.PlaneSeat;
////import seats.SeatClass;
////
////public class PlaneTicket extends Ticket {
////    private final boolean additionalLuggage;
////    private final boolean inCabinPet;
////    private final boolean inBaggageHoldPet;
////
////    public PlaneTicket(String ticketId, Reservation reservation, boolean additionalLuggage, boolean inCabinPet, boolean inBaggageHoldPet) {
////        super(ticketId, reservation);
////
////        this.additionalLuggage = additionalLuggage;
////        this.inCabinPet = inCabinPet;
////        this.inBaggageHoldPet = inBaggageHoldPet;
////    }
////
////    @Override
////    public Double getPrice() {
////        double price = 0.0;
////
////        if (this.seat.getSeatClass() == SeatClass.FIRST)
////            price += this.reservation.getLine().getFirstClassPrice();
////        else if (this.seat.getSeatClass() == SeatClass.SECOND)
////            price += this.reservation.getLine().getSecondClassPrice();
////
////        if (this.discount != null)
////            price *= 1 - this.discount.getDiscountValue();
////
////        if (((PlaneSeat) this.seat).hasMoreSpace())
////            price += ((PlaneLine) this.reservation.getLine()).getMoreSpaceSeatPrice();
////
////        if (this.additionalLuggage)
////            price += ((PlaneLine) this.reservation.getLine()).getAdditionalLuggagePrice();
////
////        if (this.inCabinPet)
////            price += ((PlaneLine) this.reservation.getLine()).getInCabinPetPrice();
////
////        if (this.inBaggageHoldPet)
////            price += ((PlaneLine) this.reservation.getLine()).getInBaggageHoldPetPrice();
////
////        return price;
////    }
////}
//
//
//package reservations;
//
//import lines.PlaneLine;
//import seats.PlaneSeat;
//import seats.SeatClass;
//
//public class PlaneTicket extends Ticket {
//    private final boolean additionalLuggage;
//    private final boolean inCabinPet;
//    private final boolean inBaggageHoldPet;
//
//    public PlaneTicket(String ticketId, Reservation reservation, boolean additionalLuggage, boolean inCabinPet, boolean inBaggageHoldPet) {
//        super(ticketId, reservation);
//
//        this.additionalLuggage = additionalLuggage;
//        this.inCabinPet = inCabinPet;
//        this.inBaggageHoldPet = inBaggageHoldPet;
//    }
//
//    @Override
//    public Double getPrice() {
//        double price = 0.0;
//
//        if (this.seat.getSeatClass() == SeatClass.FIRST)
//            price += this.reservation.getLine().getFirstClassPrice();
//        else if (this.seat.getSeatClass() == SeatClass.SECOND)
//            price += this.reservation.getLine().getSecondClassPrice();
//
//        if (((PlaneSeat) this.seat).hasMoreSpace())
//            price += ((PlaneLine) this.reservation.getLine()).getMoreSpaceSeatPrice();
//
//        if (this.additionalLuggage)
//            price += ((PlaneLine) this.reservation.getLine()).getAdditionalLuggagePrice();
//
//        if (this.inCabinPet)
//            price += ((PlaneLine) this.reservation.getLine()).getInCabinPetPrice();
//
//        if (this.inBaggageHoldPet)
//            price += ((PlaneLine) this.reservation.getLine()).getInBaggageHoldPetPrice();
//
//        if (this.discount != null)
//            price *= (1 - this.discount.getDiscountValue());
//
//        return price;
//    }
//}
package reservations;

import lines.PlaneLine;
import seats.PlaneSeat;
import seats.SeatClass;

public class PlaneTicket extends Ticket {
    private final boolean additionalLuggage;
    private final boolean inCabinPet;
    private final boolean inBaggageHoldPet;

    public PlaneTicket(String ticketId, Reservation reservation, boolean additionalLuggage, boolean inCabinPet, boolean inBaggageHoldPet) {
        super(ticketId, reservation);
        this.additionalLuggage = additionalLuggage;
        this.inCabinPet = inCabinPet;
        this.inBaggageHoldPet = inBaggageHoldPet;
        this.discount = Discount.NO_DISCOUNT; // Użycie nowej wartości dla braku zniżki
    }

    @Override
    public Double getPrice() {
        double price = 0.0;

        if (this.seat.getSeatClass() == SeatClass.FIRST)
            price += this.reservation.getLine().getFirstClassPrice();
        else if (this.seat.getSeatClass() == SeatClass.SECOND)
            price += this.reservation.getLine().getSecondClassPrice();

        if (((PlaneSeat) this.seat).hasMoreSpace())
            price += ((PlaneLine) this.reservation.getLine()).getMoreSpaceSeatPrice();

        if (this.additionalLuggage)
            price += ((PlaneLine) this.reservation.getLine()).getAdditionalLuggagePrice();

        if (this.inCabinPet)
            price += ((PlaneLine) this.reservation.getLine()).getInCabinPetPrice();

        if (this.inBaggageHoldPet)
            price += ((PlaneLine) this.reservation.getLine()).getInBaggageHoldPetPrice();

        if (this.discount != null)
            price *= (1 - this.discount.getDiscountValue());

        return price;
    }
}
