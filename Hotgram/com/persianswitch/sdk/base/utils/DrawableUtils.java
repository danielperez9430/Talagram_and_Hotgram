package com.persianswitch.sdk.base.utils;

import android.graphics.drawable.Drawable;
import android.os.Build$VERSION;
import android.view.View;

public class DrawableUtils {
    public DrawableUtils() {
        super();
    }

    public static void a(View arg1, Drawable arg2, boolean arg3) {
        if(arg3) {
            arg2 = arg2.getConstantState().newDrawable().mutate();
        }

        if(Build$VERSION.SDK_INT < 16) {
            arg1.setBackgroundDrawable(arg2);
        }
        else {
            arg1.setBackground(arg2);
        }
    }
}

