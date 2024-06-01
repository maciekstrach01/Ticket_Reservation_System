package lines;

import carriers.PlaneCarrier;
import seats.PlaneSeat;

import java.util.List;

public class TestLine extends Line<PlaneSeat, PlaneCarrier> {
    private final PlaneCarrier carrier;

    public TestLine(List<PlaneSeat> seats, Double firstClassPrice, Double secondClassPrice, PlaneCarrier carrier) {
        super(seats, firstClassPrice, secondClassPrice);
        this.carrier = carrier;
    }

    public TestLine(List<PlaneSeat> seats, Double firstClassPrice, PlaneCarrier carrier) {
        super(seats, firstClassPrice);
        this.carrier = carrier;
    }

    @Override
    public PlaneCarrier getCarrier() {
        return this.carrier;
    }
}
