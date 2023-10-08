package reservations;

import utils.EnumVisualiser;

public enum Discount implements EnumVisualiser {
    CHILDREN_UNDER_4(1.0,"Dla dziecka do lat 4"),
    SENIOR(0.3,"Bilet dla Seniora"),
    HONORARY_BLOOD_DONOR(0.33,"Honorowy Dawca Krwi"),
    PENSIONER(0.37,"2 przejazdy w roku (emeryt/rencista)"),
    BLIND_PERSON(0.37, "Osoby niewidome"),
    CHILDREN_YOUTH(0.37, "Dzieci/Młodzież"),
    WAR_DISABLED_PERSON(0.37,"Inwalidzi wojenni i wojskowi"),
    POLISH_CHARTER(0.37,"Karta Polaka"),
    INCAPABLE_PERSON(0.37,"Osoby niezdolne do sam. egzyst."),
    VETERAN(0.37, "Weterani"),
    AGREEMENT(0.5,"Umowa"),
    ANTI_COMMUNIST_OPPOSITION_ACTIVIST(0.51, "Działacze opozycji antykomunistycznej"),
    STUDENT(0.51,"Studenci do 26 lat/Doktoranci do 35 lat"),
    DISABLED_CHILDREN(0.78,"Dzieci/Młodzież/Studenci niepełnosprawni"),
    COMBATANT(0.78,"Kombatant/Inwalida"),
    SOLDIER(0.78,"Żołnierze"),
    GUIDE(0.95,"Przewodnik/Opiekun");

    private final Double discount;
    private final String visibleName;

    Discount(Double discount, String visibleName) {
        this.discount = discount;
        this.visibleName = visibleName;
    }

    public Double getDiscountValue() {
        return this.discount;
    }

    @Override
    public String getVisibleName() {
        return this.visibleName;
    }
}
