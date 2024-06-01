package carriers;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BusCarrierTest {

    @Test
    void testGetVisibleNameForSindbad() {
        assertEquals("Sindbad", BusCarrier.SINDBAD.getVisibleName());
    }

    @Test
    void testGetVisibleNameForEurobus() {
        assertEquals("Eurobus", BusCarrier.EUROBUS.getVisibleName());
    }

    @Test
    void testGetVisibleNameForFlixBus() {
        assertEquals("FlixBus", BusCarrier.FLIXBUS.getVisibleName());
    }

    @Test
    void testGetVisibleNameForPolonus() {
        assertEquals("Polonus", BusCarrier.POLONUS.getVisibleName());
    }

    @Test
    void testGetVisibleNameForSzwagropol() {
        assertEquals("Szwagropol", BusCarrier.SZWAGROPOL.getVisibleName());
    }

    @Test
    void testGetVisibleNameForMarpol() {
        assertEquals("Marpol", BusCarrier.MARPOL.getVisibleName());
    }

    @Test
    void allEnumValuesTested() {
        BusCarrier[] allCarriers = BusCarrier.values();
        assertTrue(allCarriers.length == 6);  // Ensure that all enums are tested.
    }
}
