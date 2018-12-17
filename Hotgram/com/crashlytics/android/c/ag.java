package com.crashlytics.android.c;

import android.app.ActivityManager$RunningAppProcessInfo;
import c.a.a.a.a.b.p$a;
import c.a.a.a.c;
import java.util.Iterator;
import java.util.List;
import java.util.Map$Entry;
import java.util.Map;

class ag {
    private static final b a;
    private static final b b;

    static {
        ag.a = b.a("0");
        ag.b = b.a("Unity");
    }

    private static int a() {
        return e.b(1, ag.a) + e.b(2, ag.a) + e.b(3, 0);
    }

    private static int a(int arg2, b arg3, int arg4, long arg5, long arg7, boolean arg9, Map arg10, int arg11, b arg12, b arg13) {
        int v0 = 0;
        arg2 = e.e(3, arg2);
        int v3 = arg3 == null ? 0 : e.b(4, arg3);
        arg2 = arg2 + v3 + e.d(5, arg4) + e.b(6, arg5) + e.b(7, arg7) + e.b(10, arg9);
        if(arg10 != null) {
            Iterator v3_1 = arg10.entrySet().iterator();
            while(v3_1.hasNext()) {
                Object v4 = v3_1.next();
                arg4 = ag.a(((Map$Entry)v4).getKey(), ((Map$Entry)v4).getValue());
                arg2 += e.j(11) + e.l(arg4) + arg4;
            }
        }

        arg2 += e.d(12, arg11);
        v3 = arg12 == null ? 0 : e.b(13, arg12);
        arg2 += v3;
        if(arg13 == null) {
        }
        else {
            v0 = e.b(14, arg13);
        }

        return arg2 + v0;
    }

    private static int a(a arg1, String arg2) {
        return e.e(1, arg1.h) + e.b(2, b.a(arg2));
    }

    private static int a(long arg12, String arg14, aj arg15, Thread arg16, StackTraceElement[] arg17, Thread[] arg18, List arg19, int arg20, Map arg21, ActivityManager$RunningAppProcessInfo arg22, int arg23, b arg24, b arg25, Float arg26, int arg27, boolean arg28, long arg29, long arg31, b arg33) {
        int v0 = e.b(1, arg12) + e.b(2, b.a(arg14));
        int v1 = ag.a(arg15, arg16, arg17, arg18, arg19, arg20, arg24, arg25, arg21, arg22, arg23);
        v0 += e.j(3) + e.l(v1) + v1;
        v1 = ag.a(arg26, arg27, arg28, arg23, arg29, arg31);
        v0 += e.j(5) + e.l(v1) + v1;
        if(arg33 != null) {
            v1 = ag.b(arg33);
            v0 += e.j(6) + e.l(v1) + v1;
        }

        return v0;
    }

    private static int a(aj arg0, Thread arg1, StackTraceElement[] arg2, Thread[] arg3, List arg4, int arg5, b arg6, b arg7, Map arg8, ActivityManager$RunningAppProcessInfo arg9, int arg10) {
        int v0 = ag.a(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7);
        int v2 = e.j(1) + e.l(v0) + v0;
        boolean v0_1 = false;
        v2 = v2;
        if(arg8 != null) {
            Iterator v3 = arg8.entrySet().iterator();
            while(v3.hasNext()) {
                Object v4 = v3.next();
                int v4_1 = ag.a(((Map$Entry)v4).getKey(), ((Map$Entry)v4).getValue());
                v2 += e.j(2) + e.l(v4_1) + v4_1;
            }
        }

        if(arg9 != null) {
            int v3_1 = 3;
            if(arg9.importance != 100) {
                v0_1 = true;
            }

            v2 += e.b(v3_1, v0_1);
        }

        return v2 + e.d(4, arg10);
    }

    private static int a(Float arg2, int arg3, boolean arg4, int arg5, long arg6, long arg8) {
        int v0 = 0;
        if(arg2 != null) {
            v0 = e.b(1, arg2.floatValue());
        }

        return v0 + e.f(2, arg3) + e.b(3, arg4) + e.d(4, arg5) + e.b(5, arg6) + e.b(6, arg8);
    }

    private static int a(aj arg9, int arg10, int arg11) {
        int v2 = 0;
        int v0 = e.b(1, b.a(arg9.b));
        String v3 = arg9.a;
        if(v3 != null) {
            v0 += e.b(3, b.a(v3));
        }

        StackTraceElement[] v3_1 = arg9.c;
        int v4 = v3_1.length;
        int v5 = v0;
        for(v0 = 0; v0 < v4; ++v0) {
            int v6 = ag.a(v3_1[v0], true);
            v5 += e.j(4) + e.l(v6) + v6;
        }

        arg9 = arg9.d;
        if(arg9 != null) {
            if(arg10 < arg11) {
                int v9 = ag.a(arg9, arg10 + 1, arg11);
                return e.j(6) + e.l(v9) + v9;
            }

            while(arg9 != null) {
                arg9 = arg9.d;
                ++v2;
            }

            v5 += e.d(7, v2);
        }

        return v5;
    }

    private static int a(StackTraceElement arg6, boolean arg7) {
        int v2 = 0;
        long v3 = arg6.isNativeMethod() ? ((long)Math.max(arg6.getLineNumber(), 0)) : 0;
        int v0 = e.b(1, v3);
        StringBuilder v1 = new StringBuilder();
        v1.append(arg6.getClassName());
        v1.append(".");
        v1.append(arg6.getMethodName());
        v0 += e.b(2, b.a(v1.toString()));
        if(arg6.getFileName() != null) {
            v0 += e.b(3, b.a(arg6.getFileName()));
        }

        if(!arg6.isNativeMethod() && arg6.getLineNumber() > 0) {
            v0 += e.b(4, ((long)arg6.getLineNumber()));
        }

        int v6 = 5;
        if(arg7) {
            v2 = 2;
        }

        return v0 + e.d(v6, v2);
    }

    private static int a(aj arg6, Thread arg7, StackTraceElement[] arg8, Thread[] arg9, List arg10, int arg11, b arg12, b arg13) {
        int v7 = ag.a(arg7, arg8, 4, true);
        int v8 = e.j(1) + e.l(v7) + v7;
        int v1 = arg9.length;
        int v2 = v8;
        for(v8 = 0; v8 < v1; ++v8) {
            int v3 = ag.a(arg9[v8], arg10.get(v8), 0, false);
            v2 += e.j(1) + e.l(v3) + v3;
        }

        int v6 = ag.a(arg6, 1, arg11);
        v2 += e.j(2) + e.l(v6) + v6;
        v6 = ag.a();
        v2 += e.j(3) + e.l(v6) + v6;
        v6 = ag.a(arg12, arg13);
        return v2 + (e.j(3) + e.l(v6) + v6);
    }

    private static int a(Thread arg4, StackTraceElement[] arg5, int arg6, boolean arg7) {
        int v4 = e.b(1, b.a(arg4.getName())) + e.d(2, arg6);
        arg6 = arg5.length;
        int v0;
        for(v0 = 0; v0 < arg6; ++v0) {
            int v1 = ag.a(arg5[v0], arg7);
            v4 += e.j(3) + e.l(v1) + v1;
        }

        return v4;
    }

    private static int a(b arg4, b arg5) {
        int v2 = e.b(1, 0) + e.b(2, 0) + e.b(3, arg4);
        if(arg5 != null) {
            v2 += e.b(4, arg5);
        }

        return v2;
    }

    private static int a(String arg1, String arg2) {
        int v1 = e.b(1, b.a(arg1));
        if(arg2 == null) {
            arg2 = "";
        }

        return v1 + e.b(2, b.a(arg2));
    }

    private static int a(b arg1) {
        return e.b(1, arg1);
    }

    private static int a(b arg1, b arg2, b arg3, b arg4, b arg5, int arg6, b arg7) {
        int v1 = e.b(1, arg1) + e.b(2, arg3) + e.b(3, arg4);
        int v2 = ag.a(arg2);
        v1 = v1 + (e.j(5) + e.l(v2) + v2) + e.b(6, arg5);
        if(arg7 != null) {
            v1 = v1 + e.b(8, ag.b) + e.b(9, arg7);
        }

        return v1 + e.e(10, arg6);
    }

    private static int a(b arg3, b arg4, boolean arg5) {
        return e.e(1, 3) + e.b(2, arg3) + e.b(3, arg4) + e.b(4, arg5);
    }

    private static b a(String arg0) {
        b v0 = arg0 == null ? null : b.a(arg0);
        return v0;
    }

    private static void a(e arg5, int arg6, StackTraceElement arg7, boolean arg8) {
        int v0 = 2;
        arg5.g(arg6, v0);
        arg5.k(ag.a(arg7, arg8));
        long v3 = arg7.isNativeMethod() ? ((long)Math.max(arg7.getLineNumber(), 0)) : 0;
        arg5.a(1, v3);
        StringBuilder v6 = new StringBuilder();
        v6.append(arg7.getClassName());
        v6.append(".");
        v6.append(arg7.getMethodName());
        arg5.a(v0, b.a(v6.toString()));
        if(arg7.getFileName() != null) {
            arg5.a(3, b.a(arg7.getFileName()));
        }

        v0 = 4;
        if(!arg7.isNativeMethod() && arg7.getLineNumber() > 0) {
            arg5.a(v0, ((long)arg7.getLineNumber()));
        }

        arg6 = 5;
        if(arg8) {
        }
        else {
            v0 = 0;
        }

        arg5.a(arg6, v0);
    }

    public static void a(e arg17, int arg18, String arg19, int arg20, long arg21, long arg23, boolean arg25, Map arg26, int arg27, String arg28, String arg29) {
        e v0 = arg17;
        b v13 = ag.a(arg19);
        b v14 = ag.a(arg29);
        b v15 = ag.a(arg28);
        v0.g(9, 2);
        b v11 = v15;
        b v16 = v15;
        int v15_1 = 2;
        v0.k(ag.a(arg18, v13, arg20, arg21, arg23, arg25, arg26, arg27, v11, v14));
        v0.b(3, arg18);
        v0.a(4, v13);
        v0.a(5, arg20);
        v0.a(6, arg21);
        v0.a(7, arg23);
        v0.a(10, arg25);
        Iterator v1 = arg26.entrySet().iterator();
        while(v1.hasNext()) {
            Object v2 = v1.next();
            v0.g(11, v15_1);
            v0.k(ag.a(((Map$Entry)v2).getKey(), ((Map$Entry)v2).getValue()));
            v0.b(1, ((Map$Entry)v2).getKey().h);
            v0.a(v15_1, b.a(((Map$Entry)v2).getValue()));
        }

        v0.a(12, arg27);
        if(v16 != null) {
            v0.a(13, v16);
        }

        if(v14 != null) {
            v0.a(14, v14);
        }
    }

    public static void a(e arg35, long arg36, String arg38, aj arg39, Thread arg40, StackTraceElement[] arg41, Thread[] arg42, List arg43, Map arg44, u arg45, ActivityManager$RunningAppProcessInfo arg46, int arg47, String arg48, String arg49, Float arg50, int arg51, boolean arg52, long arg53, long arg55) {
        e v12 = arg35;
        String v0 = arg49;
        b v7 = b.a(arg48);
        b v0_1 = v0 == null ? null : b.a(v0.replace("-", ""));
        b v8 = v0_1;
        b v11 = arg45.a();
        if(v11 == null) {
            c.h().a("CrashlyticsCore", "No log data to include with this event.");
        }

        arg45.b();
        v12.g(10, 2);
        v12.k(ag.a(arg36, arg38, arg39, arg40, arg41, arg42, arg43, 8, arg44, arg46, arg47, v7, v8, arg50, arg51, arg52, arg53, arg55, v11));
        v12.a(1, arg36);
        v12.a(2, b.a(arg38));
        ag.a(arg35, arg39, arg40, arg41, arg42, arg43, 8, v7, v8, arg44, arg46, arg47);
        ag.a(arg35, arg50, arg51, arg52, arg47, arg53, arg55);
        ag.a(v12, v11);
    }

    private static void a(e arg2, aj arg3, Thread arg4, StackTraceElement[] arg5, Thread[] arg6, List arg7, int arg8, b arg9, b arg10, Map arg11, ActivityManager$RunningAppProcessInfo arg12, int arg13) {
        int v0 = 3;
        arg2.g(v0, 2);
        arg2.k(ag.a(arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11, arg12, arg13));
        ag.a(arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10);
        if(arg11 != null && !arg11.isEmpty()) {
            ag.a(arg2, arg11);
        }

        if(arg12 != null) {
            boolean v3 = arg12.importance != 100 ? true : false;
            arg2.a(v0, v3);
        }

        arg2.a(4, arg13);
    }

    private static void a(e arg3, Float arg4, int arg5, boolean arg6, int arg7, long arg8, long arg10) {
        int v0 = 2;
        int v1 = 5;
        arg3.g(v1, v0);
        arg3.k(ag.a(arg4, arg5, arg6, arg7, arg8, arg10));
        if(arg4 != null) {
            arg3.a(1, arg4.floatValue());
        }

        arg3.c(v0, arg5);
        arg3.a(3, arg6);
        arg3.a(4, arg7);
        arg3.a(v1, arg8);
        arg3.a(6, arg10);
    }

    private static void a(e arg2, b arg3) {
        if(arg3 != null) {
            arg2.g(6, 2);
            arg2.k(ag.b(arg3));
            arg2.a(1, arg3);
        }
    }

    private static void a(e arg6, aj arg7, int arg8, int arg9, int arg10) {
        arg6.g(arg10, 2);
        arg6.k(ag.a(arg7, 1, arg9));
        arg6.a(1, b.a(arg7.b));
        String v0 = arg7.a;
        if(v0 != null) {
            arg6.a(3, b.a(v0));
        }

        StackTraceElement[] v0_1 = arg7.c;
        int v1 = v0_1.length;
        int v2 = 0;
        int v3;
        for(v3 = 0; v3 < v1; ++v3) {
            ag.a(arg6, 4, v0_1[v3], true);
        }

        arg7 = arg7.d;
        if(arg7 != null) {
            if(arg8 < arg9) {
                ag.a(arg6, arg7, arg8 + 1, arg9, 6);
                return;
            }

            while(arg7 != null) {
                arg7 = arg7.d;
                ++v2;
            }

            arg6.a(7, v2);
        }
    }

    private static void a(e arg6, aj arg7, Thread arg8, StackTraceElement[] arg9, Thread[] arg10, List arg11, int arg12, b arg13, b arg14) {
        int v0 = 2;
        arg6.g(1, v0);
        arg6.k(ag.a(arg7, arg8, arg9, arg10, arg11, arg12, arg13, arg14));
        int v2 = 4;
        ag.a(arg6, arg8, arg9, v2, true);
        int v8 = arg10.length;
        int v3;
        for(v3 = 0; v3 < v8; ++v3) {
            ag.a(arg6, arg10[v3], arg11.get(v3), 0, false);
        }

        ag.a(arg6, arg7, 1, arg12, v0);
        arg6.g(3, v0);
        arg6.k(ag.a());
        arg6.a(1, ag.a);
        arg6.a(v0, ag.a);
        arg6.a(3, 0);
        arg6.g(v2, v0);
        arg6.k(ag.a(arg13, arg14));
        arg6.a(1, 0);
        arg6.a(v0, 0);
        arg6.a(3, arg13);
        if(arg14 != null) {
            arg6.a(v2, arg14);
        }
    }

    private static void a(e arg3, Thread arg4, StackTraceElement[] arg5, int arg6, boolean arg7) {
        arg3.g(1, 2);
        arg3.k(ag.a(arg4, arg5, arg6, arg7));
        arg3.a(1, b.a(arg4.getName()));
        arg3.a(2, arg6);
        int v4 = arg5.length;
        for(arg6 = 0; arg6 < v4; ++arg6) {
            ag.a(arg3, 3, arg5[arg6], arg7);
        }
    }

    private static void a(e arg4, Map arg5) {
        Iterator v5 = arg5.entrySet().iterator();
        while(v5.hasNext()) {
            Object v0 = v5.next();
            int v1 = 2;
            arg4.g(v1, v1);
            arg4.k(ag.a(((Map$Entry)v0).getKey(), ((Map$Entry)v0).getValue()));
            arg4.a(1, b.a(((Map$Entry)v0).getKey()));
            v0 = ((Map$Entry)v0).getValue();
            if(v0 == null) {
                String v0_1 = "";
            }

            arg4.a(v1, b.a(((String)v0)));
        }
    }

    public static void a(e arg1, String arg2, String arg3, long arg4) {
        arg1.a(1, b.a(arg3));
        arg1.a(2, b.a(arg2));
        arg1.a(3, arg4);
    }

    public static void a(e arg7, String arg8, String arg9, String arg10) {
        if(arg8 == null) {
            arg8 = "";
        }

        b v8 = b.a(arg8);
        b v0 = ag.a(arg9);
        b v1 = ag.a(arg10);
        int v3 = e.b(1, v8);
        int v4 = 2;
        if(arg9 != null) {
            v3 += e.b(v4, v0);
        }

        int v5 = 3;
        if(arg10 != null) {
            v3 += e.b(v5, v1);
        }

        arg7.g(6, v4);
        arg7.k(v3);
        arg7.a(1, v8);
        if(arg9 != null) {
            arg7.a(v4, v0);
        }

        if(arg10 != null) {
            arg7.a(v5, v1);
        }
    }

    public static void a(e arg8, String arg9, String arg10, String arg11, String arg12, String arg13, int arg14, String arg15) {
        b v9 = b.a(arg9);
        b v10 = b.a(arg10);
        b v11 = b.a(arg11);
        b v12 = b.a(arg12);
        b v13 = b.a(arg13);
        b v15 = arg15 != null ? b.a(arg15) : null;
        arg8.g(7, 2);
        arg8.k(ag.a(v9, v10, v11, v12, v13, arg14, v15));
        arg8.a(1, v9);
        arg8.a(2, v11);
        arg8.a(3, v12);
        arg8.g(5, 2);
        arg8.k(ag.a(v10));
        arg8.a(1, v10);
        arg8.a(6, v13);
        if(v15 != null) {
            arg8.a(8, ag.b);
            arg8.a(9, v15);
        }

        arg8.b(10, arg14);
    }

    public static void a(e arg3, String arg4, String arg5, boolean arg6) {
        b v4 = b.a(arg4);
        b v5 = b.a(arg5);
        arg3.g(8, 2);
        arg3.k(ag.a(v4, v5, arg6));
        arg3.b(1, 3);
        arg3.a(2, v4);
        arg3.a(3, v5);
        arg3.a(4, arg6);
    }

    private static int b(b arg1) {
        return e.b(1, arg1);
    }
}

