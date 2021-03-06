package ua.com.okonsergei.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.TimeZone;

public class ConverterLocalDateTime {

    public static Long convertLocalDateTimeToLong(LocalDateTime dataLocalTime) {
        if (dataLocalTime == null) {
            return null;
        }
        return dataLocalTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    public static LocalDateTime convertLongToLocalDateTime(Long dataLong) {
        if (dataLong == null) {
            return null;
        }
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(dataLong),
                TimeZone.getDefault().toZoneId());
    }
}
