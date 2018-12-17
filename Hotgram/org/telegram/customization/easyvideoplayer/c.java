package org.telegram.customization.easyvideoplayer;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

class c {
    static int a(Context arg1, int arg2) {
        return c.a(arg1, arg2, 0);
    }

    static int a(int arg2, float arg3) {
        return Color.argb(Math.round((((float)Color.alpha(arg2))) * arg3), Color.red(arg2), Color.green(arg2), Color.blue(arg2));
    }

    static boolean a(int arg6) {
        double v0 = ((double)Color.red(arg6));
        Double.isNaN(v0);
        double v2 = ((double)Color.green(arg6));
        Double.isNaN(v2);
        v0 = v0 * 0.299 + v2 * 0.587;
        v2 = ((double)Color.blue(arg6));
        Double.isNaN(v2);
        boolean v6 = 1 - (v0 + v2 * 0.114) / 255 >= 0.5 ? true : false;
        return v6;
    }

    static String a(long arg7, boolean arg9) {
        Locale v0 = Locale.getDefault();
        String v1 = "%s%02d:%02d";
        Object[] v2 = new Object[3];
        String v9 = arg9 ? "-" : "";
        v2[0] = v9;
        v2[1] = Long.valueOf(TimeUnit.MILLISECONDS.toMinutes(arg7));
        v2[2] = Long.valueOf(TimeUnit.MILLISECONDS.toSeconds(arg7) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(arg7)));
        return String.format(v0, v1, v2);
    }

    private static int a(Context arg2, int arg3, int arg4) {
        TypedArray v2 = arg2.getTheme().obtainStyledAttributes(new int[]{arg3});
        try {
            arg3 = v2.getColor(0, arg4);
        }
        catch(Throwable v3) {
            v2.recycle();
            throw v3;
        }

        v2.recycle();
        return arg3;
    }

    private static Drawable a(Context arg2, int arg3, Drawable arg4) {
        Drawable v3_1;
        TypedArray v2 = arg2.getTheme().obtainStyledAttributes(new int[]{arg3});
        try {
            v3_1 = v2.getDrawable(0);
            if(v3_1 != null) {
                goto label_10;
            }
        }
        catch(Throwable v3) {
            v2.recycle();
            throw v3;
        }

        if(arg4 != null) {
            v3_1 = arg4;
        }

    label_10:
        v2.recycle();
        return v3_1;
    }

    static Drawable b(Context arg1, int arg2) {
        return c.a(arg1, arg2, null);
    }
}

