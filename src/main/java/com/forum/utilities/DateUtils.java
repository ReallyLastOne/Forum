package com.forum.utilities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {
    public static String convertToString(LocalDateTime date) {
        return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public static String withHours(LocalDateTime date) {
        return date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
    }

    /* "dd-MM-yyyy HH:mm:ss" -> ""dd-MM-yyyy"
     * */
    public static String withoutHours(String date) {
        return date.split(" ")[0];
    }
}
