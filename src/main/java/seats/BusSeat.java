package seats;

public class BusSeat extends Seat {
    private final boolean hasElectricalOutlet;

    public BusSeat(SeatClass seatClass, int seatNum, boolean hasElectricalOutlet) {
        super(seatClass, seatNum);

        this.hasElectricalOutlet = hasElectricalOutlet;
    }

    public boolean hasElectricalOutlet() {
        return this.hasElectricalOutlet;
    }
}
