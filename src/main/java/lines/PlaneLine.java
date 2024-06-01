package lines;

import carriers.PlaneCarrier;
import seats.PlaneSeat;

import java.util.List;

public class PlaneLine extends Line<PlaneSeat, PlaneCarrier> {
    private final PlaneCarrier carrier;
    private Double additionalLuggagePrice = 0.0;
    private Double inCabinPetPrice = 0.0;
    private Double inBaggageHoldPetPrice = 0.0;
    private Double moreSpaceSeatPrice = 0.0;

    public PlaneLine(List<PlaneSeat> seats, Double firstClassPrice, Double secondClassPrice, PlaneCarrier carrier) {
        super(seats, firstClassPrice, secondClassPrice);

        this.carrier = carrier;
    }

    public PlaneLine(List<PlaneSeat> seats, Double firstClassPrice, PlaneCarrier carrier) {
        super(seats, firstClassPrice);

        this.carrier = carrier;
    }

    @Override
    public PlaneCarrier getCarrier() {
        return this.carrier;
    }

    public Double getAdditionalLuggagePrice() {
        return this.additionalLuggagePrice;
    }

    public void setAdditionalLuggagePrice(Double price) {
        this.additionalLuggagePrice = price;
    }

    public Double getInCabinPetPrice() {
        return this.inCabinPetPrice;
    }

    public void setInCabinPetPrice(Double price) {
        this.inCabinPetPrice = price;
    }

    public Double getInBaggageHoldPetPrice() {
        return this.inBaggageHoldPetPrice;
    }

    public void setInBaggageHoldPetPrice(Double price) {
        this.inBaggageHoldPetPrice = price;
    }

    public Double getMoreSpaceSeatPrice() {
        return this.moreSpaceSeatPrice;
    }

    public void setMoreSpaceSeatPrice(Double price) {
        this.moreSpaceSeatPrice = price;
    }
}
