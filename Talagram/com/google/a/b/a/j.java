package com.google.a.b.a;

import com.google.a.c.a;
import com.google.a.d.b;
import com.google.a.d.c;
import com.google.a.f;
import com.google.a.t;
import com.google.a.v;
import com.google.a.w;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public final class j extends v {
    final class com.google.a.b.a.j$1 implements w {
        com.google.a.b.a.j$1() {
            super();
        }

        public v create(f arg1, a arg2) {
            j v1;
            if(arg2.a() == Date.class) {
                v1 = new j();
            }
            else {
                v v1_1 = null;
            }

            return ((v)v1);
        }
    }

    public static final w a;
    private final DateFormat b;

    static {
        j.a = new com.google.a.b.a.j$1();
    }

    public j() {
        super();
        this.b = new SimpleDateFormat("MMM d, yyyy");
    }

    public Date a(com.google.a.d.a arg3) {
        Date v3_2;
        __monitor_enter(this);
        try {
            if(arg3.f() != b.i) {
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
                throw new t(((Throwable)v3_1));
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

    public void a(c arg2, Date arg3) {
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

    public Object read(com.google.a.d.a arg1) {
        return this.a(arg1);
    }

    public void write(c arg1, Object arg2) {
        this.a(arg1, ((Date)arg2));
    }
}

