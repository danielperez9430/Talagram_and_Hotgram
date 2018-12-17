package com.google.ads.interactivemedia.v3.internal;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public final class hq extends gp {
    final class com.google.ads.interactivemedia.v3.internal.hq$1 implements gq {
        com.google.ads.interactivemedia.v3.internal.hq$1() {
            super();
        }

        public gp a(fz arg1, hw arg2) {
            hq v1;
            if(arg2.a() == Date.class) {
                v1 = new hq();
            }
            else {
                gp v1_1 = null;
            }

            return ((gp)v1);
        }
    }

    public static final gq a;
    private final DateFormat b;

    static {
        hq.a = new com.google.ads.interactivemedia.v3.internal.hq$1();
    }

    public hq() {
        super();
        this.b = new SimpleDateFormat("MMM d, yyyy");
    }

    public Date a(hx arg3) {
        Date v3_2;
        __monitor_enter(this);
        try {
            if(arg3.f() != hy.i) {
                goto label_8;
            }

            arg3.j();
        }
        catch(Throwable v3) {
            goto label_21;
        }

        __monitor_exit(this);
        return null;
        try {
        label_8:
            v3_2 = new Date(this.b.parse(arg3.h()).getTime());
        }
        catch(Throwable v3) {
        }
        catch(ParseException v3_1) {
            try {
                throw new gn(((Throwable)v3_1));
            }
            catch(Throwable v3) {
            label_21:
                __monitor_exit(this);
                throw v3;
            }
        }

        __monitor_exit(this);
        return v3_2;
    }

    public void a(hz arg2, Date arg3) {
        String v3;
        __monitor_enter(this);
        if(arg3 == null) {
            v3 = null;
        }
        else {
            try {
                v3 = this.b.format(((java.util.Date)arg3));
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
        this.a(arg1, ((Date)arg2));
    }
}

