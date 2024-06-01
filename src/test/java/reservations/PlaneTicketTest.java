//package reservations;
//
//import carriers.PlaneCarrier;
//import lines.PlaneLine;
//import seats.PlaneSeat;
//import seats.SeatClass;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.util.Arrays;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class PlaneTicketTest {
//
//    private Reservation reservation;
//    private PlaneLine planeLine;
//    private PlaneSeat planeSeat;
//    private PlaneTicket planeTicket;
//
//    @BeforeEach
//    void setUp() {
//        planeSeat = new PlaneSeat(SeatClass.FIRST, 1, true);  // Assuming PlaneSeat has a constructor with SeatClass, seat number, and availability
//        List<PlaneSeat> seats = Arrays.asList(planeSeat);
//        planeLine = new PlaneLine(seats, 200.0, 100.0, PlaneCarrier.EMIRATES_AIRLINE);
//        planeLine.setAdditionalLuggagePrice(50.0);
//        planeLine.setInCabinPetPrice(30.0);
//        planeLine.setInBaggageHoldPetPrice(40.0);
//        planeLine.setMoreSpaceSeatPrice(20.0);
//        reservation = new Reservation("RES123", planeLine);
//
//        planeTicket = new PlaneTicket("TICKET123", reservation, true, true, true);
//        planeTicket.setSeat(planeSeat);
//    }
//
//    @Test
//    void testGetPriceWithoutDiscount() {
//        Double price = planeTicket.getPrice();
//        assertEquals(340.0, price);  // 200.0 (first class) + 20.0 (more space) + 50.0 (additional luggage) + 30.0 (in cabin pet) + 40.0 (in baggage hold pet)
//    }
//
//    @Test
//    void testGetPriceWithDiscount() {
//        planeTicket.setDiscount(Discount.SENIOR);  // Assuming 30% discount
//
//        Double price = planeTicket.getPrice();
//        assertEquals(238.0, price, 0.0001);  // Adding a delta to account for floating-point precision issues
//    }
//
//
//    @Test
//    void testGetPriceWithSecondClass() {
//        planeSeat = new PlaneSeat(SeatClass.SECOND, 2, true);  // Second class seat
//        planeTicket.setSeat(planeSeat);
//
//        Double price = planeTicket.getPrice();
//        assertEquals(240.0, price);  // 100.0 (second class) + 20.0 (more space) + 50.0 (additional luggage) + 30.0 (in cabin pet) + 40.0 (in baggage hold pet)
//    }
//
//    @Test
//    void testGetPriceWithoutAdditionalServices() {
//        planeTicket = new PlaneTicket("TICKET123", reservation, false, false, false);
//        planeTicket.setSeat(planeSeat);
//
//        Double price = planeTicket.getPrice();
//        assertEquals(220.0, price);  // 200.0 (first class) + 20.0 (more space)
//    }
//
//    @Test
//    void testGetPriceWithoutAdditionalLuggage() {
//        planeTicket = new PlaneTicket("TICKET123", reservation, false, true, true);
//        planeTicket.setSeat(planeSeat);
//
//        Double price = planeTicket.getPrice();
//        assertEquals(290.0, price);  // 200.0 (first class) + 20.0 (more space) + 30.0 (in cabin pet) + 40.0 (in baggage hold pet)
//    }
//
//    @Test
//    void testGetPriceWithoutInCabinPet() {
//        planeTicket = new PlaneTicket("TICKET123", reservation, true, false, true);
//        planeTicket.setSeat(planeSeat);
//
//        Double price = planeTicket.getPrice();
//        assertEquals(310.0, price);  // 200.0 (first class) + 20.0 (more space) + 50.0 (additional luggage) + 40.0 (in baggage hold pet)
//    }
//
//    @Test
//    void testGetPriceWithoutInBaggageHoldPet() {
//        planeTicket = new PlaneTicket("TICKET123", reservation, true, true, false);
//        planeTicket.setSeat(planeSeat);
//
//        Double price = planeTicket.getPrice();
//        assertEquals(300.0, price);  // 200.0 (first class) + 20.0 (more space) + 50.0 (additional luggage) + 30.0 (in cabin pet)
//    }
//}

package reservations;

import carriers.PlaneCarrier;
import lines.PlaneLine;
import seats.PlaneSeat;
import seats.SeatClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlaneTicketTest {

    private Reservation reservation;
    private PlaneLine planeLine;
    private PlaneSeat planeSeat;
    private PlaneTicket planeTicket;

    @BeforeEach
    void setUp() {
        planeSeat = new PlaneSeat(SeatClass.FIRST, 1, true);  // Assuming PlaneSeat has a constructor with SeatClass, seat number, and availability
        List<PlaneSeat> seats = Arrays.asList(planeSeat);
        planeLine = new PlaneLine(seats, 200.0, 100.0, PlaneCarrier.EMIRATES_AIRLINE);
        planeLine.setAdditionalLuggagePrice(50.0);
        planeLine.setInCabinPetPrice(30.0);
        planeLine.setInBaggageHoldPetPrice(40.0);
        planeLine.setMoreSpaceSeatPrice(20.0);
        reservation = new Reservation("RES123", planeLine);

        planeTicket = new PlaneTicket("TICKET123", reservation, true, true, true);
        planeTicket.setSeat(planeSeat);
    }

    @Test
    void testGetPriceWithoutDiscount() {
        Double price = planeTicket.getPrice();
        assertEquals(340.0, price);  // 200.0 (first class) + 20.0 (more space) + 50.0 (additional luggage) + 30.0 (in cabin pet) + 40.0 (in baggage hold pet)
    }

    @Test
    void testGetPriceWithDiscount() {
        planeTicket.setDiscount(Discount.SENIOR);  // Assuming 30% discount

        Double price = planeTicket.getPrice();
        assertEquals(238.0, price, 0.0001);  // Adding a delta to account for floating-point precision issues
    }


    @Test
    void testGetPriceWithSecondClass() {
        planeSeat = new PlaneSeat(SeatClass.SECOND, 2, true);  // Second class seat
        planeTicket.setSeat(planeSeat);

        Double price = planeTicket.getPrice();
        assertEquals(240.0, price);  // 100.0 (second class) + 20.0 (more space) + 50.0 (additional luggage) + 30.0 (in cabin pet) + 40.0 (in baggage hold pet)
    }

    @Test
    void testGetPriceWithoutAdditionalServices() {
        planeTicket = new PlaneTicket("TICKET123", reservation, false, false, false);
        planeTicket.setSeat(planeSeat);

        Double price = planeTicket.getPrice();
        assertEquals(220.0, price);  // 200.0 (first class) + 20.0 (more space)
    }

    @Test
    void testGetPriceWithoutAdditionalLuggage() {
        planeTicket = new PlaneTicket("TICKET123", reservation, false, true, true);
        planeTicket.setSeat(planeSeat);

        Double price = planeTicket.getPrice();
        assertEquals(290.0, price);  // 200.0 (first class) + 20.0 (more space) + 30.0 (in cabin pet) + 40.0 (in baggage hold pet)
    }

    @Test
    void testGetPriceWithoutInCabinPet() {
        planeTicket = new PlaneTicket("TICKET123", reservation, true, false, true);
        planeTicket.setSeat(planeSeat);

        Double price = planeTicket.getPrice();
        assertEquals(310.0, price);  // 200.0 (first class) + 20.0 (more space) + 50.0 (additional luggage) + 40.0 (in baggage hold pet)
    }

    @Test
    void testGetPriceWithoutInBaggageHoldPet() {
        planeTicket = new PlaneTicket("TICKET123", reservation, true, true, false);
        planeTicket.setSeat(planeSeat);

        Double price = planeTicket.getPrice();
        assertEquals(300.0, price);  // 200.0 (first class) + 20.0 (more space) + 50.0 (additional luggage) + 30.0 (in cabin pet)
    }
}
