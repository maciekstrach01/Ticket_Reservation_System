package lines;

import carriers.BusCarrier;
import seats.BusSeat;

import java.util.List;

public class BusLine extends Line<BusSeat, BusCarrier> {
    private final BusCarrier carrier;
    private Double electricalOutletSeatPrice = 0.0;

    public BusLine(List<BusSeat> seats, Double firstClassPrice, Double secondClassPrice, BusCarrier carrier) {
        super(seats, firstClassPrice, secondClassPrice);

        this.carrier = carrier;
    }

    public BusLine(List<BusSeat> seats, Double firstClassPrice, BusCarrier carrier) {
        super(seats, firstClassPrice);

        this.carrier = carrier;
    }

    @Override
    public BusCarrier getCarrier() {
        return this.carrier;
    }

    public double getElectricalOutletSeatPrice() {
        return this.electricalOutletSeatPrice;
    }

    public void setElectricalOutletSeatPrice(Double price) {
        this.electricalOutletSeatPrice = price;
    }
}
