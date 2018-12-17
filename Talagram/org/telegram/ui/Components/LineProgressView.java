package org.telegram.ui.Components;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint$Cap;
import android.graphics.Paint;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import org.telegram.messenger.AndroidUtilities;

public class LineProgressView extends View {
    private float animatedAlphaValue;
    private float animatedProgressValue;
    private float animationProgressStart;
    private int backColor;
    private float currentProgress;
    private long currentProgressTime;
    private static DecelerateInterpolator decelerateInterpolator;
    private long lastUpdateTime;
    private int progressColor;
    private static Paint progressPaint;

    static {
    }

    public LineProgressView(Context arg3) {
        super(arg3);
        this.lastUpdateTime = 0;
        this.currentProgress = 0f;
        this.animationProgressStart = 0f;
        this.currentProgressTime = 0;
        this.animatedProgressValue = 0f;
        this.animatedAlphaValue = 1f;
        if(LineProgressView.decelerateInterpolator == null) {
            LineProgressView.decelerateInterpolator = new DecelerateInterpolator();
            LineProgressView.progressPaint = new Paint(1);
            LineProgressView.progressPaint.setStrokeCap(Paint$Cap.ROUND);
            LineProgressView.progressPaint.setStrokeWidth(((float)AndroidUtilities.dp(2f)));
        }
    }

    public void onDraw(Canvas arg9) {
        float v1 = 255f;
        if(this.backColor != 0 && this.animatedProgressValue != 1f) {
            LineProgressView.progressPaint.setColor(this.backColor);
            LineProgressView.progressPaint.setAlpha(((int)(this.animatedAlphaValue * v1)));
            arg9.drawRect(((float)(((int)((((float)this.getWidth())) * this.animatedProgressValue)))), 0f, ((float)this.getWidth()), ((float)this.getHeight()), LineProgressView.progressPaint);
        }

        LineProgressView.progressPaint.setColor(this.progressColor);
        LineProgressView.progressPaint.setAlpha(((int)(this.animatedAlphaValue * v1)));
        arg9.drawRect(0f, 0f, (((float)this.getWidth())) * this.animatedProgressValue, ((float)this.getHeight()), LineProgressView.progressPaint);
        this.updateAnimation();
    }

    public void setBackColor(int arg1) {
        this.backColor = arg1;
    }

    public void setProgress(float arg2, boolean arg3) {
        if(!arg3) {
            this.animatedProgressValue = arg2;
            this.animationProgressStart = arg2;
        }
        else {
            this.animationProgressStart = this.animatedProgressValue;
        }

        float v3 = 1f;
        if(arg2 != v3) {
            this.animatedAlphaValue = v3;
        }

        this.currentProgress = arg2;
        this.currentProgressTime = 0;
        this.lastUpdateTime = System.currentTimeMillis();
        this.invalidate();
    }

    public void setProgressColor(int arg1) {
        this.progressColor = arg1;
    }

    private void updateAnimation() {
        // Method was not decompiled
    }
}

