package lines;

import utils.EnumVisualiser;

public enum City implements EnumVisualiser {
    NOWY_SACZ("Nowy Sącz"),
    BRZESKO("Brzesko"),
    TARNOW("Tarnów"),
    KRAKOW("Kraków"),
    KATOWICE("Katowice"),
    ZAWIERCIE("Zawiercie"),
    WARSZAWA("Warszawa"),
    BOCHNIA("Bochnia"),
    KRYNICA_ZDROJ("Krynica-Zdrój");

    private final String visibleName;

    City(String visibleName) {
        this.visibleName = visibleName;
    }

    @Override
    public String getVisibleName() {
        return this.visibleName;
    }
}
