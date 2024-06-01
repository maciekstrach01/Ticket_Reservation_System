package reservations;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DiscountTest {

    @Test
    void testGetDiscountValue() {
        assertEquals(1.0, Discount.CHILDREN_UNDER_4.getDiscountValue());
        assertEquals(0.3, Discount.SENIOR.getDiscountValue());
        assertEquals(0.33, Discount.HONORARY_BLOOD_DONOR.getDiscountValue());
        assertEquals(0.37, Discount.PENSIONER.getDiscountValue());
        assertEquals(0.37, Discount.BLIND_PERSON.getDiscountValue());
        assertEquals(0.37, Discount.CHILDREN_YOUTH.getDiscountValue());
        assertEquals(0.37, Discount.WAR_DISABLED_PERSON.getDiscountValue());
        assertEquals(0.37, Discount.POLISH_CHARTER.getDiscountValue());
        assertEquals(0.37, Discount.INCAPABLE_PERSON.getDiscountValue());
        assertEquals(0.37, Discount.VETERAN.getDiscountValue());
        assertEquals(0.5, Discount.AGREEMENT.getDiscountValue());
        assertEquals(0.51, Discount.ANTI_COMMUNIST_OPPOSITION_ACTIVIST.getDiscountValue());
        assertEquals(0.51, Discount.STUDENT.getDiscountValue());
        assertEquals(0.78, Discount.DISABLED_CHILDREN.getDiscountValue());
        assertEquals(0.78, Discount.COMBATANT.getDiscountValue());
        assertEquals(0.78, Discount.SOLDIER.getDiscountValue());
        assertEquals(0.95, Discount.GUIDE.getDiscountValue());
    }

    @Test
    void testGetVisibleName() {
        assertEquals("Dla dziecka do lat 4", Discount.CHILDREN_UNDER_4.getVisibleName());
        assertEquals("Bilet dla Seniora", Discount.SENIOR.getVisibleName());
        assertEquals("Honorowy Dawca Krwi", Discount.HONORARY_BLOOD_DONOR.getVisibleName());
        assertEquals("2 przejazdy w roku (emeryt/rencista)", Discount.PENSIONER.getVisibleName());
        assertEquals("Osoby niewidome", Discount.BLIND_PERSON.getVisibleName());
        assertEquals("Dzieci/Młodzież", Discount.CHILDREN_YOUTH.getVisibleName());
        assertEquals("Inwalidzi wojenni i wojskowi", Discount.WAR_DISABLED_PERSON.getVisibleName());
        assertEquals("Karta Polaka", Discount.POLISH_CHARTER.getVisibleName());
        assertEquals("Osoby niezdolne do sam. egzyst.", Discount.INCAPABLE_PERSON.getVisibleName());
        assertEquals("Weterani", Discount.VETERAN.getVisibleName());
        assertEquals("Umowa", Discount.AGREEMENT.getVisibleName());
        assertEquals("Działacze opozycji antykomunistycznej", Discount.ANTI_COMMUNIST_OPPOSITION_ACTIVIST.getVisibleName());
        assertEquals("Studenci do 26 lat/Doktoranci do 35 lat", Discount.STUDENT.getVisibleName());
        assertEquals("Dzieci/Młodzież/Studenci niepełnosprawni", Discount.DISABLED_CHILDREN.getVisibleName());
        assertEquals("Kombatant/Inwalida", Discount.COMBATANT.getVisibleName());
        assertEquals("Żołnierze", Discount.SOLDIER.getVisibleName());
        assertEquals("Przewodnik/Opiekun", Discount.GUIDE.getVisibleName());
    }
}
