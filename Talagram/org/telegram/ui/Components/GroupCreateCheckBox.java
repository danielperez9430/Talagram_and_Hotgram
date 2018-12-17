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
import org.telegram.ui.ActionBar.Theme;

public class GroupCreateCheckBox extends View {
    private boolean attachedToWindow;
    private Paint backgroundInnerPaint;
    private String backgroundKey;
    private Paint backgroundPaint;
    private Canvas bitmapCanvas;
    private ObjectAnimator checkAnimator;
    private String checkKey;
    private Paint checkPaint;
    private float checkScale;
    private Bitmap drawBitmap;
    private static Paint eraser = null;
    private static Paint eraser2 = null;
    private String innerKey;
    private int innerRadDiff;
    private boolean isCheckAnimation;
    private boolean isChecked;
    private float progress;
    private static final float progressBounceDiff = 0.2f;

    public GroupCreateCheckBox(Context arg5) {
        super(arg5);
        this.isCheckAnimation = true;
        this.checkScale = 1f;
        this.backgroundKey = "groupcreate_checkboxCheck";
        this.checkKey = "groupcreate_checkboxCheck";
        this.innerKey = "groupcreate_checkbox";
        if(GroupCreateCheckBox.eraser == null) {
            GroupCreateCheckBox.eraser = new Paint(1);
            GroupCreateCheckBox.eraser.setColor(0);
            GroupCreateCheckBox.eraser.setXfermode(new PorterDuffXfermode(PorterDuff$Mode.CLEAR));
            GroupCreateCheckBox.eraser2 = new Paint(1);
            GroupCreateCheckBox.eraser2.setColor(0);
            GroupCreateCheckBox.eraser2.setStyle(Paint$Style.STROKE);
            GroupCreateCheckBox.eraser2.setXfermode(new PorterDuffXfermode(PorterDuff$Mode.CLEAR));
        }

        this.backgroundPaint = new Paint(1);
        this.backgroundInnerPaint = new Paint(1);
        this.checkPaint = new Paint(1);
        this.checkPaint.setStyle(Paint$Style.STROKE);
        this.innerRadDiff = AndroidUtilities.dp(2f);
        this.checkPaint.setStrokeWidth(((float)AndroidUtilities.dp(1.5f)));
        GroupCreateCheckBox.eraser2.setStrokeWidth(((float)AndroidUtilities.dp(28f)));
        this.drawBitmap = Bitmap.createBitmap(AndroidUtilities.dp(24f), AndroidUtilities.dp(24f), Bitmap$Config.ARGB_4444);
        this.bitmapCanvas = new Canvas(this.drawBitmap);
        this.updateColors();
    }

    private void animateToCheckedState(boolean arg4) {
        this.isCheckAnimation = arg4;
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
        this.updateColors();
        this.attachedToWindow = true;
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.attachedToWindow = false;
    }

    protected void onDraw(Canvas arg13) {
        // Method was not decompiled
    }

    public void setCheckScale(float arg1) {
        this.checkScale = arg1;
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

    public void setColorKeysOverrides(String arg1, String arg2, String arg3) {
        this.checkKey = arg1;
        this.innerKey = arg2;
        this.backgroundKey = arg3;
        this.updateColors();
    }

    public void setInnerRadDiff(int arg1) {
        this.innerRadDiff = arg1;
    }

    @Keep public void setProgress(float arg2) {
        if(this.progress == arg2) {
            return;
        }

        this.progress = arg2;
        this.invalidate();
    }

    public void updateColors() {
        this.backgroundInnerPaint.setColor(Theme.getColor(this.innerKey));
        this.backgroundPaint.setColor(Theme.getColor(this.backgroundKey));
        this.checkPaint.setColor(Theme.getColor(this.checkKey));
        this.invalidate();
    }
}

