package com.persianswitch.sdk.base.utils.pdate;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class DateTimeConverter {
    private static DateTimeConverter a;
    private Roozh b;
    private Calendar c;

    public DateTimeConverter() {
        super();
        this.b = new Roozh();
        this.c = GregorianCalendar.getInstance();
    }

    public DateTime a(DateTime arg8) {
        __monitor_enter(this);
        try {
            this.b.a(arg8.a(), arg8.b(), arg8.c());
            arg8 = DateTime.a(this.b.c(), this.b.b(), this.b.a(), arg8.d(), arg8.e(), arg8.f()).a(DateFormat.b);
        }
        catch(Throwable v8) {
            __monitor_exit(this);
            throw v8;
        }

        __monitor_exit(this);
        return arg8;
    }

    public static DateTimeConverter a() {
        DateTimeConverter v1_1;
        Class v0 = DateTimeConverter.class;
        __monitor_enter(v0);
        try {
            if(DateTimeConverter.a == null) {
                DateTimeConverter.a = new DateTimeConverter();
            }

            v1_1 = DateTimeConverter.a;
        }
        catch(Throwable v1) {
            __monitor_exit(v0);
            throw v1;
        }

        __monitor_exit(v0);
        return v1_1;
    }
}

