package utils;

public enum SampleEnum implements EnumVisualiser {
    EXAMPLE("Example Visible Name");

    private final String visibleName;

    SampleEnum(String visibleName) {
        this.visibleName = visibleName;
    }

    @Override
    public String getVisibleName() {
        return visibleName;
    }
}
