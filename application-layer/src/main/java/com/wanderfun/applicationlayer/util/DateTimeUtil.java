package com.wanderfun.applicationlayer.util;

import java.time.LocalDateTime;
import java.util.Date;

public interface DateTimeUtil {
    Date convertLocalDateTimeToDate(LocalDateTime localDateTime);
    LocalDateTime convertDateToLocalDateTime(Date date);
    String convertDateToString(Date date);
    Date convertStringToDate(String dateString);
    String convertLocalDateTimeToString(LocalDateTime localDateTime);
    LocalDateTime convertStringToLocalDateTime(String localDateTimeString);
}
