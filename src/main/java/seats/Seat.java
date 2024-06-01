package seats;

public abstract class Seat {
    private final SeatClass seatClass;
    private final int seatNum;
    private boolean isAvailable = true;

    public Seat(SeatClass seatClass, int seatNum) {
        this.seatClass = seatClass;
        this.seatNum = seatNum;
    }

    public SeatClass getSeatClass() {
        return this.seatClass;
    }

    public int getSeatNum() {
        return this.seatNum;
    }

    public boolean isAvailable() {
        return this.isAvailable;
    }

    public void setAvailable(boolean available) {
        this.isAvailable = available;
    }
}
