package com.google.a;

import com.google.a.d.b;
import com.google.a.d.c;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

final class a extends v {
    private final Class a;
    private final DateFormat b;
    private final DateFormat c;

    a(Class arg3, String arg4) {
        this(arg3, new SimpleDateFormat(arg4, Locale.US), new SimpleDateFormat(arg4));
    }

    public a(Class arg2, int arg3, int arg4) {
        this(arg2, DateFormat.getDateTimeInstance(arg3, arg4, Locale.US), DateFormat.getDateTimeInstance(arg3, arg4));
    }

    a(Class arg2, DateFormat arg3, DateFormat arg4) {
        super();
        if(arg2 != Date.class && arg2 != java.sql.Date.class) {
            if(arg2 == Timestamp.class) {
            }
            else {
                StringBuilder v4 = new StringBuilder();
                v4.append("Date type must be one of ");
                v4.append(Date.class);
                v4.append(", ");
                v4.append(Timestamp.class);
                v4.append(", or ");
                v4.append(java.sql.Date.class);
                v4.append(" but was ");
                v4.append(arg2);
                throw new IllegalArgumentException(v4.toString());
            }
        }

        this.a = arg2;
        this.b = arg3;
        this.c = arg4;
    }

    private Date a(String arg4) {
        Date v1;
        DateFormat v0 = this.c;
        __monitor_enter(v0);
        try {
            v1 = this.c.parse(arg4);
        }
        catch(ParseException ) {
            try {
                v1 = this.b.parse(arg4);
            }
            catch(ParseException ) {
                try {
                    v1 = com.google.a.b.a.a.a.a(arg4, new ParsePosition(0));
                }
                catch(ParseException v1_1) {
                    throw new t(arg4, ((Throwable)v1_1));
                }

                __monitor_exit(v0);
                return v1;
            }

            __monitor_exit(v0);
            return v1;
        }

        try {
            __monitor_exit(v0);
            return v1;
        }
        catch(Throwable v4) {
        label_7:
            try {
                __monitor_exit(v0);
            }
            catch(Throwable v4) {
                goto label_7;
            }

            throw v4;
        }
    }

    public Date a(com.google.a.d.a arg4) {
        if(arg4.f() == b.i) {
            arg4.j();
            return null;
        }

        Date v4 = this.a(arg4.h());
        if(this.a == Date.class) {
            return v4;
        }

        if(this.a == Timestamp.class) {
            return new Timestamp(v4.getTime());
        }

        if(this.a == java.sql.Date.class) {
            return new java.sql.Date(v4.getTime());
        }

        throw new AssertionError();
    }

    public void a(c arg3, Date arg4) {
        if(arg4 == null) {
            arg3.f();
            return;
        }

        DateFormat v0 = this.c;
        __monitor_enter(v0);
        try {
            arg3.b(this.b.format(arg4));
            __monitor_exit(v0);
            return;
        label_11:
            __monitor_exit(v0);
        }
        catch(Throwable v3) {
            goto label_11;
        }

        throw v3;
    }

    public Object read(com.google.a.d.a arg1) {
        return this.a(arg1);
    }

    public String toString() {
        return "DefaultDateTypeAdapter" + '(' + this.c.getClass().getSimpleName() + ')';
    }

    public void write(c arg1, Object arg2) {
        this.a(arg1, ((Date)arg2));
    }
}

