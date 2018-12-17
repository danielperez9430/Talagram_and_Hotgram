package com.crashlytics.android.c;

import java.util.HashMap;
import java.util.Map;

class ad implements ai {
    private final int a;

    public ad(int arg1) {
        super();
        this.a = arg1;
    }

    public ad() {
        this(1);
    }

    private static boolean a(StackTraceElement[] arg5, int arg6, int arg7) {
        int v0 = arg7 - arg6;
        if(arg7 + v0 > arg5.length) {
            return 0;
        }

        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            if(!arg5[arg6 + v1].equals(arg5[arg7 + v1])) {
                return 0;
            }
        }

        return 1;
    }

    private static StackTraceElement[] a(StackTraceElement[] arg10, int arg11) {
        int v8_1;
        HashMap v0 = new HashMap();
        StackTraceElement[] v1 = new StackTraceElement[arg10.length];
        int v4 = 0;
        int v5 = 0;
        int v6 = 1;
        while(v4 < arg10.length) {
            StackTraceElement v7 = arg10[v4];
            Object v8 = ((Map)v0).get(v7);
            if(v8 == null || !ad.a(arg10, ((Integer)v8).intValue(), v4)) {
                v1[v5] = arg10[v4];
                ++v5;
                v8_1 = v4;
                v6 = 1;
            }
            else {
                v8_1 = v4 - ((Integer)v8).intValue();
                if(v6 < arg11) {
                    System.arraycopy(arg10, v4, v1, v5, v8_1);
                    v5 += v8_1;
                    ++v6;
                }

                v8_1 = v8_1 - 1 + v4;
            }

            ((Map)v0).put(v7, Integer.valueOf(v4));
            v4 = v8_1 + 1;
        }

        arg10 = new StackTraceElement[v5];
        System.arraycopy(v1, 0, arg10, 0, arg10.length);
        return arg10;
    }

    public StackTraceElement[] a(StackTraceElement[] arg4) {
        StackTraceElement[] v0 = ad.a(arg4, this.a);
        if(v0.length < arg4.length) {
            return v0;
        }

        return arg4;
    }
}

