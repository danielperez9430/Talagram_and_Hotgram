package com.google.ads.interactivemedia.v3.internal;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

final class cn extends co {
    public cn(ck arg1) {
        super(arg1);
    }

    private static Object a(fp arg1, int arg2) {
        if(arg2 == 8) {
            goto label_18;
        }

        switch(arg2) {
            case 0: {
                goto label_16;
            }
            case 1: {
                goto label_14;
            }
            case 2: {
                goto label_12;
            }
            case 3: {
                goto label_10;
            }
        }

        switch(arg2) {
            case 10: {
                goto label_8;
            }
            case 11: {
                goto label_6;
            }
        }

        return null;
    label_6:
        return cn.i(arg1);
    label_8:
        return cn.f(arg1);
    label_10:
        return cn.g(arg1);
    label_12:
        return cn.e(arg1);
    label_14:
        return cn.c(arg1);
    label_16:
        return cn.d(arg1);
    label_18:
        return cn.h(arg1);
    }

    protected void a(fp arg3, long arg4) {
        if(cn.b(arg3) == 2) {
            if(!"onMetaData".equals(cn.e(arg3))) {
                return;
            }

            if(cn.b(arg3) == 8) {
                HashMap v3 = cn.h(arg3);
                if(((Map)v3).containsKey("duration")) {
                    double v3_1 = ((Map)v3).get("duration").doubleValue();
                    if(v3_1 > 0) {
                        this.a(((long)(v3_1 * 1000000)));
                    }
                }

                return;
            }

            throw new bl();
        }

        throw new bl();
    }

    protected boolean a(fp arg1) {
        return 1;
    }

    private static int b(fp arg0) {
        return arg0.f();
    }

    private static Boolean c(fp arg1) {
        boolean v0 = true;
        if(arg1.f() == 1) {
        }
        else {
            v0 = false;
        }

        return Boolean.valueOf(v0);
    }

    private static Double d(fp arg2) {
        return Double.valueOf(Double.longBitsToDouble(arg2.o()));
    }

    private static String e(fp arg3) {
        int v0 = arg3.g();
        int v1 = arg3.d();
        arg3.d(v0);
        return new String(arg3.a, v1, v0);
    }

    private static ArrayList f(fp arg4) {
        int v0 = arg4.s();
        ArrayList v1 = new ArrayList(v0);
        int v2;
        for(v2 = 0; v2 < v0; ++v2) {
            v1.add(cn.a(arg4, cn.b(arg4)));
        }

        return v1;
    }

    private static HashMap g(fp arg4) {
        HashMap v0 = new HashMap();
        while(true) {
            String v1 = cn.e(arg4);
            int v2 = cn.b(arg4);
            if(v2 == 9) {
                return v0;
            }

            v0.put(v1, cn.a(arg4, v2));
        }

        return v0;
    }

    private static HashMap h(fp arg5) {
        int v0 = arg5.s();
        HashMap v1 = new HashMap(v0);
        int v2;
        for(v2 = 0; v2 < v0; ++v2) {
            v1.put(cn.e(arg5), cn.a(arg5, cn.b(arg5)));
        }

        return v1;
    }

    private static Date i(fp arg3) {
        Date v0 = new Date(((long)cn.d(arg3).doubleValue()));
        arg3.d(2);
        return v0;
    }
}

