package reservations;

import lines.TrainLine;
import seats.SeatClass;
import seats.TrainSeat;
import carriers.TrainCarrier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TrainTicketTest {

    private Reservation reservation;
    private TrainLine trainLine;
    private TrainSeat trainSeat;
    private TrainTicket trainTicket;

    @BeforeEach
    void setUp() {
        trainSeat = new TrainSeat(SeatClass.FIRST, 1, true, 1);  // Assuming TrainSeat has a constructor with SeatClass, seat number, table presence, and carriage number
        List<TrainSeat> seats = Arrays.asList(trainSeat);
        trainLine = new TrainLine(seats, 100.0, 50.0, TrainCarrier.PKP_INTERCITY); // Using a valid TrainCarrier value
        trainLine.setTableSeatPrice(10.0);
        trainLine.setPetPrice(20.0);
        trainLine.setBicyclePrice(15.0);
        reservation = new Reservation("RES123", trainLine);

        trainTicket = new TrainTicket("TICKET123", reservation, true, true);
        trainTicket.setSeat(trainSeat);
    }

    @Test
    void testGetPriceWithoutDiscount() {
        Double price = trainTicket.getPrice();
        assertEquals(145.0, price);  // 100.0 (first class) + 10.0 (table) + 20.0 (pet) + 15.0 (bicycle)
    }

    @Test
    void testGetPriceWithDiscount() {
        trainTicket.setDiscount(Discount.SENIOR);  // Assuming 30% discount

        Double price = trainTicket.getPrice();
        assertEquals(101.5, price, 0.0001);  // (100.0 (first class) + 10.0 (table) + 20.0 (pet) + 15.0 (bicycle)) * 0.7
    }

    @Test
    void testGetPriceWithSecondClass() {
        trainSeat = new TrainSeat(SeatClass.SECOND, 2, true, 1);  // Second class seat with carriage number
        trainTicket.setSeat(trainSeat);

        Double price = trainTicket.getPrice();
        assertEquals(95.0, price);  // 50.0 (second class) + 10.0 (table) + 20.0 (pet) + 15.0 (bicycle)
    }

    @Test
    void testGetPriceWithoutTable() {
        trainSeat = new TrainSeat(SeatClass.FIRST, 1, false, 1);  // No table, with carriage number
        trainTicket.setSeat(trainSeat);

        Double price = trainTicket.getPrice();
        assertEquals(135.0, price);  // 100.0 (first class) + 20.0 (pet) + 15.0 (bicycle)
    }

    @Test
    void testGetPriceWithoutPet() {
        trainTicket = new TrainTicket("TICKET123", reservation, false, true);
        trainTicket.setSeat(trainSeat);

        Double price = trainTicket.getPrice();
        assertEquals(125.0, price);  // 100.0 (first class) + 10.0 (table) + 15.0 (bicycle)
    }

    @Test
    void testGetPriceWithoutBicycle() {
        trainTicket = new TrainTicket("TICKET123", reservation, true, false);
        trainTicket.setSeat(trainSeat);

        Double price = trainTicket.getPrice();
        assertEquals(130.0, price);  // 100.0 (first class) + 10.0 (table) + 20.0 (pet)
    }
}
