package com.persianswitch.sdk.base.utils;

import com.persianswitch.sdk.base.utils.pdate.DateTime;
import com.persianswitch.sdk.base.utils.pdate.DateTimeConverter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public final class DateFormatUtils {
    public DateFormatUtils() {
        super();
    }

    public static String a(Date arg7, boolean arg8) {
        if(arg7 == null) {
            return "";
        }

        DateTime v0 = arg8 ? DateTimeConverter.a().a(DateTime.a(arg7)) : DateTime.a(arg7);
        int v1 = v0.a();
        int v2 = v0.b();
        int v0_1 = v0.c();
        if(arg8) {
        }
        else {
            new SimpleDateFormat("MMMM", Locale.US).format(arg7);
        }

        Calendar v8 = Calendar.getInstance();
        v8.setTimeInMillis(arg7.getTime());
        return String.format(Locale.US, "%04d/%02d/%02d %02d:%02d", Integer.valueOf(v1), Integer.valueOf(v2), Integer.valueOf(v0_1), Integer.valueOf(v8.get(11)), Integer.valueOf(v8.get(12)));
    }
}

