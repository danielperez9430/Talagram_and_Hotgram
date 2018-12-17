package com.mohamadamin.persianmaterialdatetimepicker.a;

public class d {
    public static long a(double arg2, double arg4) {
        return ((long)(arg2 - arg4 * Math.floor(arg2 / arg4)));
    }

    public static long a(long arg12) {
        // Method was not decompiled
    }

    public static long a(long arg8, int arg10, int arg11) {
        double v8 = ((double)(arg8 - 474));
        long v4 = (d.a(v8, 2820) + 474 - 1) * 365;
        double v0 = ((double)((d.a(v8, 2820) + 474) * 682 - 110));
        Double.isNaN(v0);
        v4 += ((long)Math.floor(v0 / 2816));
        Double.isNaN(v8);
        v4 = v4 + 1948320 + (((long)Math.floor(v8 / 2820))) * 1029983;
        if(arg10 < 7) {
            arg10 *= 31;
        }
        else {
            arg10 = arg10 * 30 + 6;
        }

        return v4 + (((long)arg10)) + (((long)arg11));
    }

    public static boolean a(int arg6) {
        double v0 = ((double)(d.a(((double)((((long)arg6)) - 474)), 2820) + 474));
        Double.isNaN(v0);
        boolean v6 = d.a((v0 + 38) * 682, 2816) < 682 ? true : false;
        return v6;
    }
}

