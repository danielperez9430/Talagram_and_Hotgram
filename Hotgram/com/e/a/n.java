package com.e.a;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build$VERSION;
import android.os.SystemClock;
import android.widget.ImageView;

final class n extends BitmapDrawable {
    Drawable a;
    long b;
    boolean c;
    int d;
    private static final Paint e;
    private final boolean f;
    private final float g;
    private final b h;

    static {
        n.e = new Paint();
    }

    n(Context arg2, Bitmap arg3, Drawable arg4, b arg5, boolean arg6, boolean arg7) {
        super(arg2.getResources(), arg3);
        this.d = 255;
        this.f = arg7;
        this.g = arg2.getResources().getDisplayMetrics().density;
        this.h = arg5;
        int v2 = arg5 == b.a || (arg6) ? 0 : 1;
        if(v2 != 0) {
            this.a = arg4;
            this.c = true;
            this.b = SystemClock.uptimeMillis();
        }
    }

    private static Path a(Point arg4, int arg5) {
        Point v0 = new Point(arg4.x + arg5, arg4.y);
        Point v1 = new Point(arg4.x, arg4.y + arg5);
        Path v5 = new Path();
        v5.moveTo(((float)arg4.x), ((float)arg4.y));
        v5.lineTo(((float)v0.x), ((float)v0.y));
        v5.lineTo(((float)v1.x), ((float)v1.y));
        return v5;
    }

    private void a(Canvas arg5) {
        n.e.setColor(-1);
        arg5.drawPath(n.a(new Point(0, 0), ((int)(this.g * 16f))), n.e);
        n.e.setColor(this.h.d);
        arg5.drawPath(n.a(new Point(0, 0), ((int)(this.g * 15f))), n.e);
    }

    static void a(ImageView arg8, Context arg9, Bitmap arg10, b arg11, boolean arg12, boolean arg13) {
        Drawable v3 = arg8.getDrawable();
        if((v3 instanceof AnimationDrawable)) {
            v3.stop();
        }

        arg8.setImageDrawable(new n(arg9, arg10, v3, arg11, arg12, arg13));
    }

    static void a(ImageView arg0, Drawable arg1) {
        arg0.setImageDrawable(arg1);
        if((arg0.getDrawable() instanceof AnimationDrawable)) {
            arg0.getDrawable().start();
        }
    }

    public void draw(Canvas arg5) {
        float v0;
        if(this.c) {
            v0 = (((float)(SystemClock.uptimeMillis() - this.b))) / 200f;
            if(v0 >= 1f) {
                this.c = false;
                this.a = null;
                goto label_2;
            }
            else {
                goto label_17;
            }
        }
        else {
        label_2:
            super.draw(arg5);
            goto label_33;
        label_17:
            if(this.a != null) {
                this.a.draw(arg5);
            }

            super.setAlpha(((int)((((float)this.d)) * v0)));
            super.draw(arg5);
            super.setAlpha(this.d);
            if(Build$VERSION.SDK_INT > 10) {
                goto label_33;
            }

            this.invalidateSelf();
        }

    label_33:
        if(this.f) {
            this.a(arg5);
        }
    }

    protected void onBoundsChange(Rect arg2) {
        if(this.a != null) {
            this.a.setBounds(arg2);
        }

        super.onBoundsChange(arg2);
    }

    public void setAlpha(int arg2) {
        this.d = arg2;
        if(this.a != null) {
            this.a.setAlpha(arg2);
        }

        super.setAlpha(arg2);
    }

    public void setColorFilter(ColorFilter arg2) {
        if(this.a != null) {
            this.a.setColorFilter(arg2);
        }

        super.setColorFilter(arg2);
    }
}

