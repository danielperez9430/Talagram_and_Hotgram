package okhttp3.internal.c;

import java.util.List;
import java.util.regex.Pattern;
import okhttp3.ac;
import okhttp3.l;
import okhttp3.m;
import okhttp3.s;
import okhttp3.t;

public final class e {
    private static final Pattern a;

    static {
        e.a = Pattern.compile(" +([^ \"=]*)=(:?\"([^\"]*)\"|([^ \"=]*)) *(:?,|$)");
    }

    public static int a(String arg2, int arg3, String arg4) {
        while(arg3 < arg2.length()) {
            if(arg4.indexOf(arg2.charAt(arg3)) != -1) {
            }
            else {
                ++arg3;
                continue;
            }

            return arg3;
        }

        return arg3;
    }

    public static int a(String arg2, int arg3) {
        while(arg3 < arg2.length()) {
            int v0 = arg2.charAt(arg3);
            if(v0 != 32 && v0 != 9) {
                return arg3;
            }

            ++arg3;
        }

        return arg3;
    }

    public static long a(ac arg2) {
        return e.a(arg2.e());
    }

    private static long a(String arg4) {
        long v0 = -1;
        if(arg4 == null) {
            return v0;
        }

        try {
            return Long.parseLong(arg4);
        }
        catch(NumberFormatException ) {
            return v0;
        }
    }

    public static long a(s arg2) {
        return e.a(arg2.a("Content-Length"));
    }

    public static void a(m arg1, t arg2, s arg3) {
        if(arg1 == m.a) {
            return;
        }

        List v3 = l.a(arg2, arg3);
        if(v3.isEmpty()) {
            return;
        }

        arg1.a(arg2, v3);
    }

    public static int b(String arg3, int arg4) {
        long v0;
        try {
            v0 = Long.parseLong(arg3);
            long v3 = 2147483647;
            if(v0 <= v3) {
                goto label_5;
            }
        }
        catch(NumberFormatException ) {
            return arg4;
        }

        return 2147483647;
    label_5:
        if(v0 < 0) {
            return 0;
        }

        return ((int)v0);
    }

    public static boolean b(ac arg8) {
        if(arg8.a().b().equals("HEAD")) {
            return 0;
        }

        int v0 = arg8.b();
        if((v0 < 100 || v0 >= 200) && (v0 != 204 && v0 != 304)) {
            return 1;
        }

        if(e.a(arg8) == -1) {
            if("chunked".equalsIgnoreCase(arg8.a("Transfer-Encoding"))) {
            }
            else {
                return 0;
            }
        }

        return 1;
    }
}

