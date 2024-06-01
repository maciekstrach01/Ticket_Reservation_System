package users;

import utils.EnumVisualiser;

public enum UserType implements EnumVisualiser {
    ADMINISTRATOR("Administrator"),
    PASSENGER("Pasażer");

    private final String visibleName;

    UserType(String visibleName) {
        this.visibleName = visibleName;
    }

    @Override
    public String getVisibleName() {
        return this.visibleName;
    }
}
