package com.google.ads.interactivemedia.v3.internal;

import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

final class fu implements ge, gm {
    private final DateFormat a;
    private final DateFormat b;

    fu(String arg3) {
        this(new SimpleDateFormat(arg3, Locale.US), new SimpleDateFormat(arg3));
    }

    public fu(int arg2, int arg3) {
        this(DateFormat.getDateTimeInstance(arg2, arg3, Locale.US), DateFormat.getDateTimeInstance(arg2, arg3));
    }

    fu() {
        this(DateFormat.getDateTimeInstance(2, 2, Locale.US), DateFormat.getDateTimeInstance(2, 2));
    }

    fu(DateFormat arg1, DateFormat arg2) {
        super();
        this.a = arg1;
        this.b = arg2;
    }

    private Date a(gf arg5) {
        Date v1;
        DateFormat v0 = this.b;
        __monitor_enter(v0);
        try {
            v1 = this.b.parse(arg5.b());
        }
        catch(ParseException ) {
            try {
                v1 = this.a.parse(arg5.b());
            }
            catch(ParseException ) {
                try {
                    v1 = hv.a(arg5.b(), new ParsePosition(0));
                }
                catch(ParseException v1_1) {
                    throw new gn(arg5.b(), ((Throwable)v1_1));
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
        catch(Throwable v5) {
        label_8:
            try {
                __monitor_exit(v0);
            }
            catch(Throwable v5) {
                goto label_8;
            }

            throw v5;
        }
    }

    public gf a(Object arg1, Type arg2, gl arg3) {
        return this.a(((Date)arg1), arg2, arg3);
    }

    public gf a(Date arg1, Type arg2, gl arg3) {
        DateFormat v2 = this.b;
        __monitor_enter(v2);
        try {
            __monitor_exit(v2);
            return new gk(this.a.format(arg1));
        label_9:
            __monitor_exit(v2);
        }
        catch(Throwable v1) {
            goto label_9;
        }

        throw v1;
    }

    public Date a(gf arg3, Type arg4, gd arg5) {
        if((arg3 instanceof gk)) {
            Date v3 = this.a(arg3);
            if(arg4 == Date.class) {
                return v3;
            }

            if(arg4 == Timestamp.class) {
                return new Timestamp(v3.getTime());
            }

            if(arg4 == java.sql.Date.class) {
                return new java.sql.Date(v3.getTime());
            }

            StringBuilder v5 = new StringBuilder();
            v5.append(this.getClass());
            v5.append(" cannot deserialize to ");
            v5.append(arg4);
            throw new IllegalArgumentException(v5.toString());
        }

        throw new gj("The date should be a string value");
    }

    public Object b(gf arg1, Type arg2, gd arg3) {
        return this.a(arg1, arg2, arg3);
    }

    public String toString() {
        return fu.class.getSimpleName() + '(' + this.b.getClass().getSimpleName() + ')';
    }
}

