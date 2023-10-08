package lines;

import carriers.TrainCarrier;
import seats.TrainSeat;

import java.util.List;

public class TrainLine extends Line<TrainSeat, TrainCarrier> {
    private final TrainCarrier carrier;
    private Double petPrice = 0.0;
    private Double bicyclePrice = 0.0;
    private Double tableSeatPrice = 0.0;

    public TrainLine(List<TrainSeat> seats, Double firstClassPrice, Double secondClassPrice, TrainCarrier carrier) {
        super(seats, firstClassPrice, secondClassPrice);

        this.carrier = carrier;
    }

    public TrainLine(List<TrainSeat> seats, Double firstClassPrice, TrainCarrier carrier) {
        super(seats, firstClassPrice);

        this.carrier = carrier;
    }

    @Override
    public TrainCarrier getCarrier() {
        return this.carrier;
    }

    public Double getPetPrice() {
        return this.petPrice;
    }

    public void setPetPrice(Double price) {
        this.petPrice = price;
    }

    public Double getBicyclePrice() {
        return this.bicyclePrice;
    }

    public void setBicyclePrice(Double price) {
        this.bicyclePrice = price;
    }

    public Double getTableSeatPrice() {
        return this.tableSeatPrice;
    }

    public void setTableSeatPrice(Double price) {
        this.tableSeatPrice = price;
    }
}
