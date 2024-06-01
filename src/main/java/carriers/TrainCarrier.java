package carriers;

import utils.EnumVisualiser;

public enum TrainCarrier implements EnumVisualiser {
    PKP_INTERCITY("PKP Intercity"),
    KOLEJE_DOLNOSLASKIE("Koleje Dolnośląskie"),
    KOLEJE_MALOPOLSKIE("Koleje Małopolskie"),
    KOLEJE_MAZOWIECKIE("Koleje Mazowieckie"),
    KOLEJE_SLASKIE("Koleje Śląskie"),
    KOLEJE_WIELKOPOLSKIE("Koleje Wielkopolskie"),
    LEO_EXPRESS("Leo Express"),
    LODZKA_KOLEJ_AGLOMERACYJNA("Łódzka Kolej Aglomeracyjna");

    private final String visibleName;

    TrainCarrier(String visibleName) {
        this.visibleName = visibleName;
    }

    @Override
    public String getVisibleName() {
        return this.visibleName;
    }
}
