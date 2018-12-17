package org.telegram.messenger.time;

import java.util.Calendar;
import java.util.TimeZone;

public class SunDate {
    private static final double DEGRAD = 0.017453;
    private static final double INV360 = 0.002778;
    private static final double RADEG = 57.29578;

    public SunDate() {
        super();
    }

    private static double GMST0(double arg2) {
        return SunDate.revolution(arg2 * 0.985647 + 818.9874);
    }

    private static double acosd(double arg2) {
        return Math.acos(arg2) * 57.29578;
    }

    private static double atan2d(double arg0, double arg2) {
        return Math.atan2(arg0, arg2) * 57.29578;
    }

    public static int[] calculateSunriseSunset(double arg12, double arg14) {
        Calendar v0 = Calendar.getInstance();
        v0.setTimeInMillis(System.currentTimeMillis());
        int v1 = 2;
        double[] v10 = new double[v1];
        SunDate.sunRiseSetForYear(v0.get(1), v0.get(v1) + 1, v0.get(5), arg14, arg12, v10);
        int v12 = TimeZone.getDefault().getOffset(System.currentTimeMillis()) / 1000 / 60;
        int v14 = (((int)(v10[0] * 60))) + v12;
        int v15 = (((int)(v10[1] * 60))) + v12;
        v12 = 1440;
        if(v14 < 0) {
            v14 += 1440;
        }
        else if(v14 > v12) {
            v14 += -1440;
        }

        if(v15 < 0 || v15 > v12) {
            v15 += 1440;
        }

        int[] v12_1 = new int[v1];
        v12_1[0] = v14;
        v12_1[1] = v15;
        return v12_1;
    }

    private static double cosd(double arg2) {
        return Math.cos(arg2 * 0.017453);
    }

    private static long days_since_2000_Jan_0(int arg4, int arg5, int arg6) {
        return (((long)arg4)) * 367 - (((long)((arg4 + (arg5 + 9) / 12) * 7 / 4))) + (((long)(arg5 * 275 / 9))) + (((long)arg6)) - 730530;
    }

    private static double rev180(double arg4) {
        return arg4 - Math.floor(0.002778 * arg4 + 0.5) * 360;
    }

    private static double revolution(double arg4) {
        return arg4 - Math.floor(0.002778 * arg4) * 360;
    }

    private static double sind(double arg2) {
        return Math.sin(arg2 * 0.017453);
    }

    private static int sunRiseSetForYear(int arg11, int arg12, int arg13, double arg14, double arg16, double[] arg18) {
        return SunDate.sunRiseSetHelperForYear(arg11, arg12, arg13, arg14, arg16, -0.583333, 1, arg18);
    }

    private static int sunRiseSetHelperForYear(int arg17, int arg18, int arg19, double arg20, double arg22, double arg24, int arg26, double[] arg27) {
        int v3_1;
        double[] v3 = new double[1];
        double[] v4 = new double[1];
        double[] v5 = new double[1];
        double v6 = ((double)SunDate.days_since_2000_Jan_0(arg17, arg18, arg19));
        Double.isNaN(v6);
        v6 = v6 + 0.5 - arg20 / 360;
        double v0 = SunDate.revolution(SunDate.GMST0(v6) + 180 + arg20);
        SunDate.sun_RA_decAtDay(v6, v3, v4, v5);
        double v7 = 15;
        double v9 = 12;
        v0 = v9 - SunDate.rev180(v0 - v3[0]) / v7;
        double v13 = 0.2666 / v5[0];
        double v11 = arg26 != 0 ? arg24 - v13 : arg24;
        v11 = (SunDate.sind(v11) - SunDate.sind(arg22) * SunDate.sind(v4[0])) / (SunDate.cosd(arg22) * SunDate.cosd(v4[0]));
        if(v11 >= 1) {
            v3_1 = -1;
            v9 = 0;
        }
        else if(v11 <= -1) {
            v3_1 = 1;
        }
        else {
            v9 = SunDate.acosd(v11) / v7;
            v3_1 = 0;
        }

        arg27[0] = v0 - v9;
        arg27[1] = v0 + v9;
        return v3_1;
    }

    private static void sun_RA_decAtDay(double arg8, double[] arg10, double[] arg11, double[] arg12) {
        double[] v0 = new double[1];
        SunDate.sunposAtDay(arg8, v0, arg12);
        double v2 = arg12[0] * SunDate.cosd(v0[0]);
        double v4 = arg12[0] * SunDate.sind(v0[0]);
        double v6 = 23.4393 - arg8 * 0;
        arg8 = SunDate.cosd(v6) * v4;
        v4 *= SunDate.sind(v6);
        arg10[0] = SunDate.atan2d(arg8, v2);
        arg11[0] = SunDate.atan2d(v4, Math.sqrt(v2 * v2 + arg8 * arg8));
    }

    private static void sunposAtDay(double arg10, double[] arg12, double[] arg13) {
        double v0 = SunDate.revolution(0.9856 * arg10 + 356.047);
        double v2 = 0.000047 * arg10 + 282.9404;
        double v4 = 0.016709 - arg10 * 0;
        arg10 = 57.29578 * v4 * SunDate.sind(v0) * (SunDate.cosd(v0) * v4 + 1) + v0;
        v0 = SunDate.cosd(arg10) - v4;
        v4 = Math.sqrt(1 - v4 * v4) * SunDate.sind(arg10);
        arg13[0] = Math.sqrt(v0 * v0 + v4 * v4);
        arg12[0] = SunDate.atan2d(v4, v0) + v2;
        v0 = 360;
        if(arg12[0] >= v0) {
            arg12[0] -= v0;
        }
    }

    private static double tand(double arg2) {
        return Math.tan(arg2 * 0.017453);
    }
}

