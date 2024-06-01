package utils;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DatesTest {

    @Test
    void testDateFromStringValid() {
        String dateString = "2023-05-25 14:30:00";
        LocalDateTime expectedDate = LocalDateTime.of(2023, 5, 25, 14, 30, 0);

        LocalDateTime actualDate = Dates.dateFromString(dateString);

        assertEquals(expectedDate, actualDate);
    }

    @Test
    void testDateFromStringInvalid() {
        String invalidDateString = "invalid-date-string";

        assertThrows(java.time.format.DateTimeParseException.class, () -> {
            Dates.dateFromString(invalidDateString);
        });
    }

    @Test
    void testDateToString() {
        LocalDateTime date = LocalDateTime.of(2023, 5, 25, 14, 30, 0);
        String expectedDateString = "2023-05-25 14:30:00";

        String actualDateString = Dates.dateToString(date);

        assertEquals(expectedDateString, actualDateString);
    }
}
