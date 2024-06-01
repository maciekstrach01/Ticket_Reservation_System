package seats;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PlaneSeatTest {

    private PlaneSeat planeSeat;

    @BeforeEach
    void setUp() {
        planeSeat = new PlaneSeat(SeatClass.FIRST, 1, true);
    }

    @Test
    void testGetSeatClass() {
        assertEquals(SeatClass.FIRST, planeSeat.getSeatClass());
    }

    @Test
    void testGetSeatNum() {
        assertEquals(1, planeSeat.getSeatNum());
    }

    @Test
    void testHasMoreSpace() {
        assertTrue(planeSeat.hasMoreSpace());
    }

    @Test
    void testConstructor() {
        PlaneSeat anotherPlaneSeat = new PlaneSeat(SeatClass.SECOND, 2, false);
        assertEquals(SeatClass.SECOND, anotherPlaneSeat.getSeatClass());
        assertEquals(2, anotherPlaneSeat.getSeatNum());
        assertFalse(anotherPlaneSeat.hasMoreSpace());
    }
}
