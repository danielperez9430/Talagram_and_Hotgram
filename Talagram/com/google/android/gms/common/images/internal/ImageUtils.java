package com.google.android.gms.common.images.internal;

import android.content.res.Resources;
import android.graphics.Bitmap$Config;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff$Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

public final class ImageUtils {
    public ImageUtils() {
        super();
    }

    public static Bitmap frameBitmapInCircle(Bitmap arg7) {
        if(arg7 == null) {
            return null;
        }

        int v0 = arg7.getWidth();
        int v1 = arg7.getHeight();
        int v2 = 0;
        if(v0 >= v1) {
            v2 = (v1 - v0) / 2;
            v0 = v1;
            v1 = 0;
        }
        else {
            v1 = (v0 - v1) / 2;
        }

        Bitmap v3 = Bitmap.createBitmap(v0, v0, Bitmap$Config.ARGB_8888);
        Canvas v4 = new Canvas(v3);
        Paint v5 = new Paint(1);
        v5.setColor(-16777216);
        float v0_1 = ((float)(v0 / 2));
        v4.drawCircle(v0_1, v0_1, v0_1, v5);
        v5.setXfermode(new PorterDuffXfermode(PorterDuff$Mode.SRC_IN));
        v4.drawBitmap(arg7, ((float)v2), ((float)v1), v5);
        return v3;
    }

    public static Drawable frameDrawableInCircle(Resources arg5, Drawable arg6) {
        Bitmap v6;
        if(arg6 == null) {
            v6 = null;
        }
        else if((arg6 instanceof BitmapDrawable)) {
            v6 = ((BitmapDrawable)arg6).getBitmap();
        }
        else {
            Bitmap v0 = Bitmap.createBitmap(arg6.getIntrinsicWidth(), arg6.getIntrinsicHeight(), Bitmap$Config.ARGB_8888);
            Canvas v1 = new Canvas(v0);
            arg6.setBounds(0, 0, v1.getWidth(), v1.getHeight());
            arg6.draw(v1);
            v6 = v0;
        }

        return new BitmapDrawable(arg5, ImageUtils.frameBitmapInCircle(v6));
    }
}

