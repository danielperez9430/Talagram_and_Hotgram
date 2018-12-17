package com.f.a.e;

import java.util.Calendar;
import java.util.Locale;

public class a {
    public static boolean a(int arg3, int arg4) {
        boolean v1 = true;
        if(a.a(arg3)) {
            return 1;
        }

        Calendar v0 = com.f.a.d.a.b();
        if(a.b(arg3) != v0.get(1) || arg4 >= v0.get(2) + 1) {
            v1 = false;
        }
        else {
        }

        return v1;
    }

    public static boolean a(int arg2) {
        boolean v1 = true;
        if(a.b(arg2) < com.f.a.d.a.b().get(1)) {
        }
        else {
            v1 = false;
        }

        return v1;
    }

    private static int b(int arg6) {
        if(arg6 < 100 && arg6 >= 0) {
            String v0 = String.valueOf(com.f.a.d.a.b().get(1));
            arg6 = Integer.parseInt(String.format(Locale.US, "%s%02d", v0.substring(0, v0.length() - 2), Integer.valueOf(arg6)));
        }

        return arg6;
    }
}

