package carriers;

import utils.EnumVisualiser;

public enum BusCarrier implements EnumVisualiser {
    SINDBAD("Sindbad"),
    EUROBUS("Eurobus"),
    FLIXBUS("FlixBus"),
    POLONUS("Polonus"),
    SZWAGROPOL("Szwagropol"),
    MARPOL("Marpol");

    private final String visibleName;

    BusCarrier(String visibleName) {
        this.visibleName = visibleName;
    }

    @Override
    public String getVisibleName() {
        return this.visibleName;
    }
}
