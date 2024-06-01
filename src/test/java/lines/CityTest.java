package lines;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CityTest {

    @Test
    void testGetVisibleName() {
        assertEquals("Nowy Sącz", City.NOWY_SACZ.getVisibleName());
        assertEquals("Brzesko", City.BRZESKO.getVisibleName());
        assertEquals("Tarnów", City.TARNOW.getVisibleName());
        assertEquals("Kraków", City.KRAKOW.getVisibleName());
        assertEquals("Katowice", City.KATOWICE.getVisibleName());
        assertEquals("Zawiercie", City.ZAWIERCIE.getVisibleName());
        assertEquals("Warszawa", City.WARSZAWA.getVisibleName());
        assertEquals("Bochnia", City.BOCHNIA.getVisibleName());
        assertEquals("Krynica-Zdrój", City.KRYNICA_ZDROJ.getVisibleName());
    }

    @Test
    void testEnumValues() {
        City[] expectedValues = {
                City.NOWY_SACZ,
                City.BRZESKO,
                City.TARNOW,
                City.KRAKOW,
                City.KATOWICE,
                City.ZAWIERCIE,
                City.WARSZAWA,
                City.BOCHNIA,
                City.KRYNICA_ZDROJ
        };
        assertArrayEquals(expectedValues, City.values());
    }

    @Test
    void testValueOf() {
        assertEquals(City.NOWY_SACZ, City.valueOf("NOWY_SACZ"));
        assertEquals(City.BRZESKO, City.valueOf("BRZESKO"));
        assertEquals(City.TARNOW, City.valueOf("TARNOW"));
        assertEquals(City.KRAKOW, City.valueOf("KRAKOW"));
        assertEquals(City.KATOWICE, City.valueOf("KATOWICE"));
        assertEquals(City.ZAWIERCIE, City.valueOf("ZAWIERCIE"));
        assertEquals(City.WARSZAWA, City.valueOf("WARSZAWA"));
        assertEquals(City.BOCHNIA, City.valueOf("BOCHNIA"));
        assertEquals(City.KRYNICA_ZDROJ, City.valueOf("KRYNICA_ZDROJ"));
    }
}
