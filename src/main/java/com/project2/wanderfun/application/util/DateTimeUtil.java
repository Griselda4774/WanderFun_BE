package com.project2.wanderfun.application.util;

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
