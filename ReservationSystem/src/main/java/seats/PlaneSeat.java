package seats;

public class PlaneSeat extends Seat {
    private final boolean hasMoreSpace;

    public PlaneSeat(SeatClass seatClass, int seatNum, boolean hasMoreSpace) {
        super(seatClass, seatNum);

        this.hasMoreSpace = hasMoreSpace;
    }

    public boolean hasMoreSpace() {
        return this.hasMoreSpace;
    }
}
