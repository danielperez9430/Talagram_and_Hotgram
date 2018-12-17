package com.mikhaellopez.circularprogressbar;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint$Style;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

public class CircularProgressBar extends View {
    private float a;
    private float b;
    private float c;
    private int d;
    private int e;
    private int f;
    private RectF g;
    private Paint h;
    private Paint i;

    public CircularProgressBar(Context arg3, AttributeSet arg4) {
        super(arg3, arg4);
        this.a = 0f;
        this.b = this.getResources().getDimension(a.default_stroke_width);
        this.c = this.getResources().getDimension(a.default_background_stroke_width);
        this.d = -16777216;
        this.e = -7829368;
        this.f = -90;
        this.a(arg3, arg4);
    }

    private void a(Context arg3, AttributeSet arg4) {
        this.g = new RectF();
        TypedArray v3 = arg3.getTheme().obtainStyledAttributes(arg4, b.CircularProgressBar, 0, 0);
        try {
            this.a = v3.getFloat(b.CircularProgressBar_cpb_progress, this.a);
            this.b = v3.getDimension(b.CircularProgressBar_cpb_progressbar_width, this.b);
            this.c = v3.getDimension(b.CircularProgressBar_cpb_background_progressbar_width, this.c);
            this.d = v3.getInt(b.CircularProgressBar_cpb_progressbar_color, this.d);
            this.e = v3.getInt(b.CircularProgressBar_cpb_background_progressbar_color, this.e);
        }
        catch(Throwable v4) {
            v3.recycle();
            throw v4;
        }

        v3.recycle();
        this.h = new Paint(1);
        this.h.setColor(this.e);
        this.h.setStyle(Paint$Style.STROKE);
        this.h.setStrokeWidth(this.c);
        this.i = new Paint(1);
        this.i.setColor(this.d);
        this.i.setStyle(Paint$Style.STROKE);
        this.i.setStrokeWidth(this.b);
    }

    public void a(float arg4, int arg5) {
        ObjectAnimator v4 = ObjectAnimator.ofFloat(this, "progress", new float[]{arg4});
        v4.setDuration(((long)arg5));
        v4.setInterpolator(new DecelerateInterpolator());
        v4.start();
    }

    public int getBackgroundColor() {
        return this.e;
    }

    public float getBackgroundProgressBarWidth() {
        return this.c;
    }

    public int getColor() {
        return this.d;
    }

    public float getProgress() {
        return this.a;
    }

    public float getProgressBarWidth() {
        return this.b;
    }

    protected void onDraw(Canvas arg9) {
        super.onDraw(arg9);
        arg9.drawOval(this.g, this.h);
        arg9.drawArc(this.g, ((float)this.f), this.a * 360f / 100f, false, this.i);
    }

    protected void onMeasure(int arg4, int arg5) {
        arg4 = Math.min(CircularProgressBar.getDefaultSize(this.getSuggestedMinimumWidth(), arg4), CircularProgressBar.getDefaultSize(this.getSuggestedMinimumHeight(), arg5));
        this.setMeasuredDimension(arg4, arg4);
        float v5 = this.b > this.c ? this.b : this.c;
        v5 /= 2f;
        float v1 = 0f + v5;
        float v4 = (((float)arg4)) - v5;
        this.g.set(v1, v1, v4, v4);
    }

    public void setBackgroundColor(int arg2) {
        this.e = arg2;
        this.h.setColor(arg2);
        this.invalidate();
        this.requestLayout();
    }

    public void setBackgroundProgressBarWidth(float arg2) {
        this.c = arg2;
        this.h.setStrokeWidth(arg2);
        this.requestLayout();
        this.invalidate();
    }

    public void setColor(int arg2) {
        this.d = arg2;
        this.i.setColor(arg2);
        this.invalidate();
        this.requestLayout();
    }

    public void setProgress(float arg3) {
        if(arg3 <= 100f) {
        }
        else {
            arg3 = 100f;
        }

        this.a = arg3;
        this.invalidate();
    }

    public void setProgressBarWidth(float arg2) {
        this.b = arg2;
        this.i.setStrokeWidth(arg2);
        this.requestLayout();
        this.invalidate();
    }

    public void setProgressWithAnimation(float arg2) {
        this.a(arg2, 1500);
    }
}

