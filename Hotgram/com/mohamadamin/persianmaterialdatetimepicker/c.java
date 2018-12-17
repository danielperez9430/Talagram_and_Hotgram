package com.mohamadamin.persianmaterialdatetimepicker;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.f.m;

public class c {
    private static final m a;

    static {
        c.a = new m();
    }

    public static Typeface a(Context arg4, String arg5) {
        m v0 = c.a;
        __monitor_enter(v0);
        try {
            if(!c.a.containsKey(arg5)) {
                Typeface v4_1 = Typeface.createFromAsset(arg4.getAssets(), String.format("fonts/%s.ttf", arg5));
                c.a.put(arg5, v4_1);
                __monitor_exit(v0);
                return v4_1;
            }

            __monitor_exit(v0);
            return c.a.get(arg5);
        label_22:
            __monitor_exit(v0);
        }
        catch(Throwable v4) {
            goto label_22;
        }

        throw v4;
    }
}

