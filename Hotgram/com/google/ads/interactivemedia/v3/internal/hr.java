package com.google.ads.interactivemedia.v3.internal;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class hr extends gp {
    final class com.google.ads.interactivemedia.v3.internal.hr$1 implements gq {
        com.google.ads.interactivemedia.v3.internal.hr$1() {
            super();
        }

        public gp a(fz arg1, hw arg2) {
            gp v1_1;
            if(arg2.a() == Time.class) {
                hr v1 = new hr();
            }
            else {
                v1_1 = null;
            }

            return v1_1;
        }
    }

    public static final gq a;
    private final DateFormat b;

    static {
        hr.a = new com.google.ads.interactivemedia.v3.internal.hr$1();
    }

    public hr() {
        super();
        this.b = new SimpleDateFormat("hh:mm:ss a");
    }

    public Time a(hx arg4) {
        Time v0;
        __monitor_enter(this);
        try {
            if(arg4.f() != hy.i) {
                goto label_8;
            }

            arg4.j();
        }
        catch(Throwable v4) {
            goto label_21;
        }

        __monitor_exit(this);
        return null;
        try {
        label_8:
            v0 = new Time(this.b.parse(arg4.h()).getTime());
        }
        catch(Throwable v4) {
        }
        catch(ParseException v4_1) {
            try {
                throw new gn(((Throwable)v4_1));
            }
            catch(Throwable v4) {
            label_21:
                __monitor_exit(this);
                throw v4;
            }
        }

        __monitor_exit(this);
        return v0;
    }

    public void a(hz arg2, Time arg3) {
        String v3;
        __monitor_enter(this);
        if(arg3 == null) {
            v3 = null;
        }
        else {
            try {
                v3 = this.b.format(((Date)arg3));
            label_6:
                arg2.b(v3);
                goto label_7;
            }
            catch(Throwable v2) {
                __monitor_exit(this);
                throw v2;
            }
        }

        goto label_6;
    label_7:
        __monitor_exit(this);
    }

    public Object read(hx arg1) {
        return this.a(arg1);
    }

    public void write(hz arg1, Object arg2) {
        this.a(arg1, ((Time)arg2));
    }
}

