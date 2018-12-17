package org.telegram.messenger.time;

import java.text.ParsePosition;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public interface DateParser {
    Locale getLocale();

    String getPattern();

    TimeZone getTimeZone();

    Date parse(String arg1);

    Date parse(String arg1, ParsePosition arg2);

    Object parseObject(String arg1);

    Object parseObject(String arg1, ParsePosition arg2);
}

