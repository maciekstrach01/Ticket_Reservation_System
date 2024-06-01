package carriers;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TrainCarrierTest {

    @Test
    void testGetVisibleName() {
        assertEquals("PKP Intercity", TrainCarrier.PKP_INTERCITY.getVisibleName());
        assertEquals("Koleje Dolnośląskie", TrainCarrier.KOLEJE_DOLNOSLASKIE.getVisibleName());
        assertEquals("Koleje Małopolskie", TrainCarrier.KOLEJE_MALOPOLSKIE.getVisibleName());
        assertEquals("Koleje Mazowieckie", TrainCarrier.KOLEJE_MAZOWIECKIE.getVisibleName());
        assertEquals("Koleje Śląskie", TrainCarrier.KOLEJE_SLASKIE.getVisibleName());
        assertEquals("Koleje Wielkopolskie", TrainCarrier.KOLEJE_WIELKOPOLSKIE.getVisibleName());
        assertEquals("Leo Express", TrainCarrier.LEO_EXPRESS.getVisibleName());
        assertEquals("Łódzka Kolej Aglomeracyjna", TrainCarrier.LODZKA_KOLEJ_AGLOMERACYJNA.getVisibleName());
    }

    @Test
    void testEnumValues() {
        TrainCarrier[] expectedValues = {
                TrainCarrier.PKP_INTERCITY,
                TrainCarrier.KOLEJE_DOLNOSLASKIE,
                TrainCarrier.KOLEJE_MALOPOLSKIE,
                TrainCarrier.KOLEJE_MAZOWIECKIE,
                TrainCarrier.KOLEJE_SLASKIE,
                TrainCarrier.KOLEJE_WIELKOPOLSKIE,
                TrainCarrier.LEO_EXPRESS,
                TrainCarrier.LODZKA_KOLEJ_AGLOMERACYJNA
        };
        assertArrayEquals(expectedValues, TrainCarrier.values());
    }

    @Test
    void testValueOf() {
        assertEquals(TrainCarrier.PKP_INTERCITY, TrainCarrier.valueOf("PKP_INTERCITY"));
        assertEquals(TrainCarrier.KOLEJE_DOLNOSLASKIE, TrainCarrier.valueOf("KOLEJE_DOLNOSLASKIE"));
        assertEquals(TrainCarrier.KOLEJE_MALOPOLSKIE, TrainCarrier.valueOf("KOLEJE_MALOPOLSKIE"));
        assertEquals(TrainCarrier.KOLEJE_MAZOWIECKIE, TrainCarrier.valueOf("KOLEJE_MAZOWIECKIE"));
        assertEquals(TrainCarrier.KOLEJE_SLASKIE, TrainCarrier.valueOf("KOLEJE_SLASKIE"));
        assertEquals(TrainCarrier.KOLEJE_WIELKOPOLSKIE, TrainCarrier.valueOf("KOLEJE_WIELKOPOLSKIE"));
        assertEquals(TrainCarrier.LEO_EXPRESS, TrainCarrier.valueOf("LEO_EXPRESS"));
        assertEquals(TrainCarrier.LODZKA_KOLEJ_AGLOMERACYJNA, TrainCarrier.valueOf("LODZKA_KOLEJ_AGLOMERACYJNA"));
    }
}
