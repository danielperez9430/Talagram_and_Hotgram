package com.google.a.b.a;

import com.google.a.c.a;
import com.google.a.d.b;
import com.google.a.d.c;
import com.google.a.f;
import com.google.a.t;
import com.google.a.v;
import com.google.a.w;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class k extends v {
    final class com.google.a.b.a.k$1 implements w {
        com.google.a.b.a.k$1() {
            super();
        }

        public v create(f arg1, a arg2) {
            v v1_1;
            if(arg2.a() == Time.class) {
                k v1 = new k();
            }
            else {
                v1_1 = null;
            }

            return v1_1;
        }
    }

    public static final w a;
    private final DateFormat b;

    static {
        k.a = new com.google.a.b.a.k$1();
    }

    public k() {
        super();
        this.b = new SimpleDateFormat("hh:mm:ss a");
    }

    public Time a(com.google.a.d.a arg4) {
        Time v0;
        __monitor_enter(this);
        try {
            if(arg4.f() != b.i) {
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
                throw new t(((Throwable)v4_1));
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

    public void a(c arg2, Time arg3) {
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

    public Object read(com.google.a.d.a arg1) {
        return this.a(arg1);
    }

    public void write(c arg1, Object arg2) {
        this.a(arg1, ((Time)arg2));
    }
}

