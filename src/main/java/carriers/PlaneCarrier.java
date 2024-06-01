package carriers;

import utils.EnumVisualiser;

public enum PlaneCarrier implements EnumVisualiser {
    EMIRATES_AIRLINE("Emirates Airline"),
    QATAR_AIRWAYS("Qatar Airways"),
    AIR_CHINA("Air China"),
    JET_AIRWAYS("Jet Airways"),
    BRITISH_AIRWAYS("British Airways"),
    EASYJET("easyJet"),
    LUFTHANSA("Lufthansa"),
    NORWEGIAN("Norwegian"),
    RYANAIR("Ryanair");

    private final String visibleName;

    PlaneCarrier(String visibleName) {
        this.visibleName = visibleName;
    }

    @Override
    public String getVisibleName() {
        return this.visibleName;
    }
}
