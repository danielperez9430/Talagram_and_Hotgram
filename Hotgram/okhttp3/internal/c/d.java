package okhttp3.internal.c;

import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import okhttp3.internal.c;

public final class d {
    final class okhttp3.internal.c.d$1 extends ThreadLocal {
        okhttp3.internal.c.d$1() {
            super();
        }

        protected DateFormat a() {
            SimpleDateFormat v0 = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss \'GMT\'", Locale.US);
            ((DateFormat)v0).setLenient(false);
            ((DateFormat)v0).setTimeZone(c.g);
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
        d.a = new okhttp3.internal.c.d$1();
        d.b = new String[]{"EEE, dd MMM yyyy HH:mm:ss zzz", "EEEE, dd-MMM-yy HH:mm:ss zzz", "EEE MMM d HH:mm:ss yyyy", "EEE, dd-MMM-yyyy HH:mm:ss z", "EEE, dd-MMM-yyyy HH-mm-ss z", "EEE, dd MMM yy HH:mm:ss z", "EEE dd-MMM-yyyy HH:mm:ss z", "EEE dd MMM yyyy HH:mm:ss z", "EEE dd-MMM-yyyy HH-mm-ss z", "EEE dd-MMM-yy HH:mm:ss z", "EEE dd MMM yy HH:mm:ss z", "EEE,dd-MMM-yy HH:mm:ss z", "EEE,dd-MMM-yyyy HH:mm:ss z", "EEE, dd-MM-yyyy HH:mm:ss z", "EEE MMM d yyyy HH:mm:ss z"};
        d.c = new DateFormat[d.b.length];
    }

    public static Date a(String arg9) {
        Date v1 = null;
        if(arg9.length() == 0) {
            return v1;
        }

        ParsePosition v0 = new ParsePosition(0);
        Date v3 = d.a.get().parse(arg9, v0);
        if(v0.getIndex() == arg9.length()) {
            return v3;
        }

        String[] v3_1 = d.b;
        __monitor_enter(v3_1);
        try {
            int v4 = d.b.length;
            int v5;
            for(v5 = 0; v5 < v4; ++v5) {
                DateFormat v6 = d.c[v5];
                if(v6 == null) {
                    SimpleDateFormat v6_1 = new SimpleDateFormat(d.b[v5], Locale.US);
                    ((DateFormat)v6_1).setTimeZone(c.g);
                    d.c[v5] = v6_1;
                }

                v0.setIndex(0);
                Date v6_2 = v6.parse(arg9, v0);
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
        return d.a.get().format(arg1);
    }
}

