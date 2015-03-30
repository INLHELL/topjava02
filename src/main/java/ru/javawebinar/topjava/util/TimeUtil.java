package ru.javawebinar.topjava.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by vladislav.fedootv on 15.03.2015.
 */
public class TimeUtil {
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static String toString(LocalDateTime localDateTime) {
        return localDateTime == null ? "null" : localDateTime.format(DATE_TIME_FORMATTER);
    }
}
