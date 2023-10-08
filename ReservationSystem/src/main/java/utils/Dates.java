package utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Dates {
    public static LocalDateTime dateFromString(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        return LocalDateTime.parse(date, formatter);
    }

    public static String dateToString(LocalDateTime date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        return date.format(formatter);
    }
}
