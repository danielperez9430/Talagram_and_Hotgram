package android.support.design.e;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;

public class a {
    public static ColorStateList a(Context arg1, TypedArray arg2, int arg3) {
        if(arg2.hasValue(arg3)) {
            int v0 = arg2.getResourceId(arg3, 0);
            if(v0 != 0) {
                ColorStateList v1 = android.support.v7.c.a.a.a(arg1, v0);
                if(v1 != null) {
                    return v1;
                }
            }
        }

        return arg2.getColorStateList(arg3);
    }

    public static Drawable b(Context arg1, TypedArray arg2, int arg3) {
        if(arg2.hasValue(arg3)) {
            int v0 = arg2.getResourceId(arg3, 0);
            if(v0 != 0) {
                Drawable v1 = android.support.v7.c.a.a.b(arg1, v0);
                if(v1 != null) {
                    return v1;
                }
            }
        }

        return arg2.getDrawable(arg3);
    }
}

