package seats;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SeatClassTest {

    @Test
    void testEnumValues() {
        SeatClass[] expectedValues = { SeatClass.FIRST, SeatClass.SECOND };
        SeatClass[] actualValues = SeatClass.values();
        assertArrayEquals(expectedValues, actualValues);
    }

    @Test
    void testValueOf() {
        assertEquals(SeatClass.FIRST, SeatClass.valueOf("FIRST"));
        assertEquals(SeatClass.SECOND, SeatClass.valueOf("SECOND"));
    }

    @Test
    void testToString() {
        assertEquals("FIRST", SeatClass.FIRST.toString());
        assertEquals("SECOND", SeatClass.SECOND.toString());
    }
}
