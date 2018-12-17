package com.persianswitch.a.a.b;

import com.persianswitch.a.a.l;
import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public final class g {
    final class com.persianswitch.a.a.b.g$1 extends ThreadLocal {
        com.persianswitch.a.a.b.g$1() {
            super();
        }

        protected DateFormat a() {
            SimpleDateFormat v0 = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss \'GMT\'", Locale.US);
            ((DateFormat)v0).setLenient(false);
            ((DateFormat)v0).setTimeZone(l.d);
            return ((DateFormat)v0);
        }

        protected Object initialValue() {
            return this.a();
        }
    }

    private static final ThreadLocal a;
    private static final String[] b;
    private static final DateFormat[] c;

    static {
        g.a = new com.persianswitch.a.a.b.g$1();
        g.b = new String[]{"EEE, dd MMM yyyy HH:mm:ss zzz", "EEEE, dd-MMM-yy HH:mm:ss zzz", "EEE MMM d HH:mm:ss yyyy", "EEE, dd-MMM-yyyy HH:mm:ss z", "EEE, dd-MMM-yyyy HH-mm-ss z", "EEE, dd MMM yy HH:mm:ss z", "EEE dd-MMM-yyyy HH:mm:ss z", "EEE dd MMM yyyy HH:mm:ss z", "EEE dd-MMM-yyyy HH-mm-ss z", "EEE dd-MMM-yy HH:mm:ss z", "EEE dd MMM yy HH:mm:ss z", "EEE,dd-MMM-yy HH:mm:ss z", "EEE,dd-MMM-yyyy HH:mm:ss z", "EEE, dd-MM-yyyy HH:mm:ss z", "EEE MMM d yyyy HH:mm:ss z"};
        g.c = new DateFormat[g.b.length];
    }

    public static Date a(String arg9) {
        SimpleDateFormat v6_1;
        Date v1 = null;
        if(arg9.length() == 0) {
            return v1;
        }

        ParsePosition v0 = new ParsePosition(0);
        Date v3 = g.a.get().parse(arg9, v0);
        if(v0.getIndex() == arg9.length()) {
            return v3;
        }

        String[] v3_1 = g.b;
        __monitor_enter(v3_1);
        try {
            int v4 = g.b.length;
            int v5;
            for(v5 = 0; v5 < v4; ++v5) {
                DateFormat v6 = g.c[v5];
                if(v6 == null) {
                    v6_1 = new SimpleDateFormat(g.b[v5], Locale.US);
                    ((DateFormat)v6_1).setTimeZone(l.d);
                    g.c[v5] = v6_1;
                }

                v0.setIndex(0);
                Date v6_2 = ((DateFormat)v6_1).parse(arg9, v0);
                if(v0.getIndex() != 0) {
                    __monitor_exit(v3_1);
                    return v6_2;
                }
            }

            __monitor_exit(v3_1);
            return v1;
        label_43:
            __monitor_exit(v3_1);
        }
        catch(Throwable v9) {
            goto label_43;
        }

        throw v9;
    }

    public static String a(Date arg1) {
        return g.a.get().format(arg1);
    }
}

