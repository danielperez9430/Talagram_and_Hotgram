package org.telegram.ui.ActionBar;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.view.animation.DecelerateInterpolator;
import org.telegram.messenger.AndroidUtilities;

public class BackDrawable extends Drawable {
    private boolean alwaysClose;
    private boolean animationInProgress;
    private float animationTime;
    private int arrowRotation;
    private int color;
    private int currentAnimationTime;
    private float currentRotation;
    private float finalRotation;
    private DecelerateInterpolator interpolator;
    private long lastFrameTime;
    private Paint paint;
    private boolean reverseAngle;
    private boolean rotated;
    private int rotatedColor;

    public BackDrawable(boolean arg3) {
        super();
        this.paint = new Paint(1);
        this.reverseAngle = false;
        this.interpolator = new DecelerateInterpolator();
        this.color = -1;
        this.rotatedColor = -9079435;
        this.animationTime = 300f;
        this.rotated = true;
        this.paint.setStrokeWidth(((float)AndroidUtilities.dp(2f)));
        this.alwaysClose = arg3;
    }

    public void draw(Canvas arg11) {
        // Method was not decompiled
    }

    public int getIntrinsicHeight() {
        return AndroidUtilities.dp(24f);
    }

    public int getIntrinsicWidth() {
        return AndroidUtilities.dp(24f);
    }

    public int getOpacity() {
        return -2;
    }

    public void setAlpha(int arg1) {
    }

    public void setAnimationTime(float arg1) {
        this.animationTime = arg1;
    }

    public void setArrowRotation(int arg1) {
        this.arrowRotation = arg1;
        this.invalidateSelf();
    }

    public void setColor(int arg1) {
        this.color = arg1;
        this.invalidateSelf();
    }

    public void setColorFilter(ColorFilter arg1) {
    }

    public void setRotated(boolean arg1) {
        this.rotated = arg1;
    }

    public void setRotatedColor(int arg1) {
        this.rotatedColor = arg1;
        this.invalidateSelf();
    }

    public void setRotation(float arg6, boolean arg7) {
        boolean v2;
        long v0 = 0;
        this.lastFrameTime = v0;
        float v3 = 1f;
        if(this.currentRotation == v3) {
            v2 = true;
            goto label_6;
        }
        else if(this.currentRotation == 0f) {
            v2 = false;
        label_6:
            this.reverseAngle = v2;
        }

        this.lastFrameTime = v0;
        if(arg7) {
            int v7 = this.currentRotation < arg6 ? ((int)(this.currentRotation * this.animationTime)) : ((int)((v3 - this.currentRotation) * this.animationTime));
            this.currentAnimationTime = v7;
            this.lastFrameTime = System.currentTimeMillis();
        }
        else {
            this.currentRotation = arg6;
        }

        this.finalRotation = arg6;
        this.invalidateSelf();
    }
}

