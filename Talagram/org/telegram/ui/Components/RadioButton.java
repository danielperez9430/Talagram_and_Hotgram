package org.telegram.ui.Components;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap$Config;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint$Style;
import android.graphics.Paint;
import android.graphics.PorterDuff$Mode;
import android.graphics.PorterDuffXfermode;
import android.support.annotation.Keep;
import android.view.View;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.FileLog;

public class RadioButton extends View {
    private boolean attachedToWindow;
    private Bitmap bitmap;
    private Canvas bitmapCanvas;
    private ObjectAnimator checkAnimator;
    private int checkedColor;
    private static Paint checkedPaint;
    private int color;
    private static Paint eraser;
    private boolean isChecked;
    private static Paint paint;
    private float progress;
    private int size;

    public RadioButton(Context arg3) {
        super(arg3);
        this.size = AndroidUtilities.dp(16f);
        if(RadioButton.paint == null) {
            RadioButton.paint = new Paint(1);
            RadioButton.paint.setStrokeWidth(((float)AndroidUtilities.dp(2f)));
            RadioButton.paint.setStyle(Paint$Style.STROKE);
            RadioButton.checkedPaint = new Paint(1);
            RadioButton.eraser = new Paint(1);
            RadioButton.eraser.setColor(0);
            RadioButton.eraser.setXfermode(new PorterDuffXfermode(PorterDuff$Mode.CLEAR));
        }

        try {
            this.bitmap = Bitmap.createBitmap(AndroidUtilities.dp(((float)this.size)), AndroidUtilities.dp(((float)this.size)), Bitmap$Config.ARGB_4444);
            this.bitmapCanvas = new Canvas(this.bitmap);
        }
        catch(Throwable v3) {
            FileLog.e(v3);
        }
    }

    private void animateToCheckedState(boolean arg4) {
        String v0 = "progress";
        float[] v1 = new float[1];
        float v4 = arg4 ? 1f : 0f;
        v1[0] = v4;
        this.checkAnimator = ObjectAnimator.ofFloat(this, v0, v1);
        this.checkAnimator.setDuration(200);
        this.checkAnimator.start();
    }

    private void cancelCheckAnimator() {
        if(this.checkAnimator != null) {
            this.checkAnimator.cancel();
        }
    }

    public float getProgress() {
        return this.progress;
    }

    public boolean isChecked() {
        return this.isChecked;
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.attachedToWindow = true;
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.attachedToWindow = false;
    }

    protected void onDraw(Canvas arg12) {
        // Method was not decompiled
    }

    public void setBackgroundColor(int arg1) {
        this.color = arg1;
        this.invalidate();
    }

    public void setChecked(boolean arg2, boolean arg3) {
        if(arg2 == this.isChecked) {
            return;
        }

        this.isChecked = arg2;
        if(!this.attachedToWindow || !arg3) {
            this.cancelCheckAnimator();
            float v2 = arg2 ? 1f : 0f;
            this.setProgress(v2);
        }
        else {
            this.animateToCheckedState(arg2);
        }
    }

    public void setCheckedColor(int arg1) {
        this.checkedColor = arg1;
        this.invalidate();
    }

    public void setColor(int arg1, int arg2) {
        this.color = arg1;
        this.checkedColor = arg2;
        this.invalidate();
    }

    @Keep public void setProgress(float arg2) {
        if(this.progress == arg2) {
            return;
        }

        this.progress = arg2;
        this.invalidate();
    }

    public void setSize(int arg2) {
        if(this.size == arg2) {
            return;
        }

        this.size = arg2;
    }
}

