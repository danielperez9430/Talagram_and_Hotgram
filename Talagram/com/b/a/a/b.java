package com.b.a.a;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v8.renderscript.RenderScript;
import android.support.v8.renderscript.a;
import android.support.v8.renderscript.k;

public class b {
    public static Bitmap a(Context arg3, Bitmap arg4, float arg5) {
        arg4 = Bitmap.createScaledBitmap(arg4, arg4.getWidth(), arg4.getHeight(), false);
        Bitmap v0 = Bitmap.createBitmap(arg4);
        RenderScript v3 = RenderScript.a(arg3);
        a v4 = a.b(v3, arg4);
        a v1 = a.b(v3, v0);
        k v3_1 = k.a(v3, v4.a());
        v3_1.a(arg5);
        v3_1.b(v4);
        v3_1.c(v1);
        v1.a(v0);
        return v0;
    }
}

