package utils;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EnumVisualiserTest {

    @Test
    void testGetVisibleName() {
        // Given
        EnumVisualiser enumInstance = SampleEnum.EXAMPLE;

        // When
        String visibleName = enumInstance.getVisibleName();

        // Then
        assertEquals("Example Visible Name", visibleName, "The visible name should be 'Example Visible Name'");
    }
}
