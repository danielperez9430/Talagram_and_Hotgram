package org.telegram.messenger.time;

import java.text.FieldPosition;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public interface DatePrinter {
    String format(long arg1);

    String format(Calendar arg1);

    String format(Date arg1);

    StringBuffer format(long arg1, StringBuffer arg2);

    StringBuffer format(Object arg1, StringBuffer arg2, FieldPosition arg3);

    StringBuffer format(Calendar arg1, StringBuffer arg2);

    StringBuffer format(Date arg1, StringBuffer arg2);

    Locale getLocale();

    String getPattern();

    TimeZone getTimeZone();
}

