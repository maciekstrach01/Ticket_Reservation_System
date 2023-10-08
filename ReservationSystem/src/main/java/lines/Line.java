package lines;

import java.time.LocalDateTime;
import java.util.List;

public abstract class Line<S, T> {
    private final List<S> seats;
    private final Double firstClassPrice;
    private final Double secondClassPrice;
    private LocalDateTime departDate;
    private LocalDateTime arrivalDate;
    private City departCity;
    private City arrivalCity;

    public Line(List<S> seats, Double firstClassPrice, Double secondClassPrice) {
        this.seats = seats;
        this.firstClassPrice = firstClassPrice;
        this.secondClassPrice = secondClassPrice;
    }

    public Line(List<S> seats, Double firstClassPrice) {
        this.seats = seats;
        this.firstClassPrice = firstClassPrice;
        this.secondClassPrice = null;
    }

    public List<S> getSeats() {
        return this.seats;
    }

    public Double getFirstClassPrice() {
        return this.firstClassPrice;
    }

    public Double getSecondClassPrice() {
        return this.secondClassPrice;
    }

    public LocalDateTime getDepartDate() {
        return this.departDate;
    }

    public void setDepartDate(LocalDateTime date) {
        this.departDate = date;
    }

    public LocalDateTime getArrivalDate() {
        return this.arrivalDate;
    }

    public void setArrivalDate(LocalDateTime date) {
        this.arrivalDate = date;
    }

    public City getDepartCity() {
        return this.departCity;
    }

    public void setDepartCity(City city) {
        this.departCity = city;
    }

    public City getArrivalCity() {
        return this.arrivalCity;
    }

    public void setArrivalCity(City city) {
        this.arrivalCity = city;
    }

    public abstract T getCarrier();
}
