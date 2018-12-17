package org.telegram.ui.Components;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint$Cap;
import android.graphics.Paint$Style;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.support.annotation.Keep;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.ActionBar.Theme;

public class RadialProgressView extends View {
    private AccelerateInterpolator accelerateInterpolator;
    private RectF cicleRect;
    private float currentCircleLength;
    private float currentProgressTime;
    private DecelerateInterpolator decelerateInterpolator;
    private long lastUpdateTime;
    private int progressColor;
    private Paint progressPaint;
    private float radOffset;
    private boolean risingCircleLength;
    private final float risingTime;
    private final float rotationTime;
    private int size;
    private boolean useSelfAlpha;

    public RadialProgressView(Context arg2) {
        super(arg2);
        this.cicleRect = new RectF();
        this.rotationTime = 2000f;
        this.risingTime = 500f;
        this.size = AndroidUtilities.dp(40f);
        this.progressColor = Theme.getColor("progressCircle");
        this.decelerateInterpolator = new DecelerateInterpolator();
        this.accelerateInterpolator = new AccelerateInterpolator();
        this.progressPaint = new Paint(1);
        this.progressPaint.setStyle(Paint$Style.STROKE);
        this.progressPaint.setStrokeCap(Paint$Cap.ROUND);
        this.progressPaint.setStrokeWidth(((float)AndroidUtilities.dp(3f)));
        this.progressPaint.setColor(this.progressColor);
    }

    protected void onDraw(Canvas arg12) {
        int v0 = (this.getMeasuredWidth() - this.size) / 2;
        int v1 = (this.getMeasuredHeight() - this.size) / 2;
        this.cicleRect.set(((float)v0), ((float)v1), ((float)(v0 + this.size)), ((float)(v1 + this.size)));
        arg12.drawArc(this.cicleRect, this.radOffset, this.currentCircleLength, false, this.progressPaint);
        this.updateAnimation();
    }

    @Keep public void setAlpha(float arg3) {
        super.setAlpha(arg3);
        if(this.useSelfAlpha) {
            Drawable v0 = this.getBackground();
            int v3 = ((int)(arg3 * 255f));
            if(v0 != null) {
                v0.setAlpha(v3);
            }

            this.progressPaint.setAlpha(v3);
        }
    }

    public void setProgressColor(int arg2) {
        this.progressColor = arg2;
        this.progressPaint.setColor(this.progressColor);
    }

    public void setSize(int arg1) {
        this.size = arg1;
        this.invalidate();
    }

    public void setUseSelfAlpha(boolean arg1) {
        this.useSelfAlpha = arg1;
    }

    private void updateAnimation() {
        long v0 = System.currentTimeMillis();
        long v2 = v0 - this.lastUpdateTime;
        long v4 = 17;
        if(v2 > v4) {
            v2 = v4;
        }

        this.lastUpdateTime = v0;
        this.radOffset += (((float)(360 * v2))) / 2000f;
        this.radOffset -= ((float)((((int)(this.radOffset / 360f))) * 360));
        this.currentProgressTime += ((float)v2);
        float v1 = 500f;
        if(this.currentProgressTime >= v1) {
            this.currentProgressTime = v1;
        }

        float v2_1 = 270f;
        float v3 = 4f;
        this.currentCircleLength = this.risingCircleLength ? this.accelerateInterpolator.getInterpolation(this.currentProgressTime / v1) * 266f + v3 : v3 - (1f - this.decelerateInterpolator.getInterpolation(this.currentProgressTime / v1)) * v2_1;
        if(this.currentProgressTime == v1) {
            if(this.risingCircleLength) {
                this.radOffset += v2_1;
                this.currentCircleLength = -266f;
            }

            this.risingCircleLength ^= 1;
            this.currentProgressTime = 0f;
        }

        this.invalidate();
    }
}

