package org.telegram.ui.Components;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap$Config;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.support.annotation.Keep;
import android.view.View;
import org.telegram.messenger.AndroidUtilities;

public class CheckBoxSquare extends View {
    private boolean attachedToWindow;
    private ObjectAnimator checkAnimator;
    private Bitmap drawBitmap;
    private Canvas drawCanvas;
    private boolean isAlert;
    private boolean isChecked;
    private boolean isDisabled;
    private float progress;
    private static final float progressBounceDiff = 0.2f;
    private RectF rectF;

    public CheckBoxSquare(Context arg3, boolean arg4) {
        super(arg3);
        this.rectF = new RectF();
        this.drawBitmap = Bitmap.createBitmap(AndroidUtilities.dp(18f), AndroidUtilities.dp(18f), Bitmap$Config.ARGB_4444);
        this.drawCanvas = new Canvas(this.drawBitmap);
        this.isAlert = arg4;
    }

    private void animateToCheckedState(boolean arg4) {
        String v0 = "progress";
        float[] v1 = new float[1];
        float v4 = arg4 ? 1f : 0f;
        v1[0] = v4;
        this.checkAnimator = ObjectAnimator.ofFloat(this, v0, v1);
        this.checkAnimator.setDuration(300);
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

    protected void onLayout(boolean arg1, int arg2, int arg3, int arg4, int arg5) {
        super.onLayout(arg1, arg2, arg3, arg4, arg5);
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

    public void setDisabled(boolean arg1) {
        this.isDisabled = arg1;
        this.invalidate();
    }

    @Keep public void setProgress(float arg2) {
        if(this.progress == arg2) {
            return;
        }

        this.progress = arg2;
        this.invalidate();
    }
}

