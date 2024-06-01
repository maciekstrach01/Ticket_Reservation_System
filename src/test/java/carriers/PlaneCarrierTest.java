package carriers;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PlaneCarrierTest {

    @Test
    void testGetVisibleName() {
        assertEquals("Emirates Airline", PlaneCarrier.EMIRATES_AIRLINE.getVisibleName());
        assertEquals("Qatar Airways", PlaneCarrier.QATAR_AIRWAYS.getVisibleName());
        assertEquals("Air China", PlaneCarrier.AIR_CHINA.getVisibleName());
        assertEquals("Jet Airways", PlaneCarrier.JET_AIRWAYS.getVisibleName());
        assertEquals("British Airways", PlaneCarrier.BRITISH_AIRWAYS.getVisibleName());
        assertEquals("easyJet", PlaneCarrier.EASYJET.getVisibleName());
        assertEquals("Lufthansa", PlaneCarrier.LUFTHANSA.getVisibleName());
        assertEquals("Norwegian", PlaneCarrier.NORWEGIAN.getVisibleName());
        assertEquals("Ryanair", PlaneCarrier.RYANAIR.getVisibleName());
    }

    @Test
    void testEnumValues() {
        PlaneCarrier[] expectedValues = {
                PlaneCarrier.EMIRATES_AIRLINE,
                PlaneCarrier.QATAR_AIRWAYS,
                PlaneCarrier.AIR_CHINA,
                PlaneCarrier.JET_AIRWAYS,
                PlaneCarrier.BRITISH_AIRWAYS,
                PlaneCarrier.EASYJET,
                PlaneCarrier.LUFTHANSA,
                PlaneCarrier.NORWEGIAN,
                PlaneCarrier.RYANAIR
        };
        assertArrayEquals(expectedValues, PlaneCarrier.values());
    }

    @Test
    void testValueOf() {
        assertEquals(PlaneCarrier.EMIRATES_AIRLINE, PlaneCarrier.valueOf("EMIRATES_AIRLINE"));
        assertEquals(PlaneCarrier.QATAR_AIRWAYS, PlaneCarrier.valueOf("QATAR_AIRWAYS"));
        assertEquals(PlaneCarrier.AIR_CHINA, PlaneCarrier.valueOf("AIR_CHINA"));
        assertEquals(PlaneCarrier.JET_AIRWAYS, PlaneCarrier.valueOf("JET_AIRWAYS"));
        assertEquals(PlaneCarrier.BRITISH_AIRWAYS, PlaneCarrier.valueOf("BRITISH_AIRWAYS"));
        assertEquals(PlaneCarrier.EASYJET, PlaneCarrier.valueOf("EASYJET"));
        assertEquals(PlaneCarrier.LUFTHANSA, PlaneCarrier.valueOf("LUFTHANSA"));
        assertEquals(PlaneCarrier.NORWEGIAN, PlaneCarrier.valueOf("NORWEGIAN"));
        assertEquals(PlaneCarrier.RYANAIR, PlaneCarrier.valueOf("RYANAIR"));
    }
}
