package seats;

public class TrainSeat extends Seat {
    private final boolean hasTable;
    private final int carNum;

    public TrainSeat(SeatClass seatClass, int seatNum, boolean hasTable, int carNum) {
        super(seatClass, seatNum);

        this.hasTable = hasTable;
        this.carNum = carNum;
    }

    public boolean hasTable() {
        return this.hasTable;
    }

    public int getCarNum() {
        return this.carNum;
    }
}
