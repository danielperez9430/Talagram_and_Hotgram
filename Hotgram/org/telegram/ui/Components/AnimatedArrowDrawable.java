package org.telegram.ui.Components;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint$Style;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.support.annotation.Keep;
import org.telegram.messenger.AndroidUtilities;

public class AnimatedArrowDrawable extends Drawable {
    private float animProgress;
    private float animateToProgress;
    private long lastUpdateTime;
    private Paint paint;
    private Path path;

    public AnimatedArrowDrawable(int arg3) {
        super();
        this.path = new Path();
        this.paint = new Paint(1);
        this.paint.setStyle(Paint$Style.STROKE);
        this.paint.setStrokeWidth(((float)AndroidUtilities.dp(2f)));
        this.paint.setColor(arg3);
        this.updatePath();
    }

    private void checkAnimation() {
        // Method was not decompiled
    }

    public void draw(Canvas arg3) {
        arg3.drawPath(this.path, this.paint);
        this.checkAnimation();
    }

    public float getAnimationProgress() {
        return this.animProgress;
    }

    public int getIntrinsicHeight() {
        return AndroidUtilities.dp(26f);
    }

    public int getIntrinsicWidth() {
        return AndroidUtilities.dp(26f);
    }

    public int getOpacity() {
        return -2;
    }

    public void setAlpha(int arg1) {
    }

    @Keep public void setAnimationProgress(float arg1) {
        this.animProgress = arg1;
        this.animateToProgress = arg1;
        this.updatePath();
        this.invalidateSelf();
    }

    public void setAnimationProgressAnimated(float arg3) {
        if(this.animateToProgress == arg3) {
            return;
        }

        this.animateToProgress = arg3;
        this.lastUpdateTime = SystemClock.elapsedRealtime();
        this.invalidateSelf();
    }

    public void setColor(int arg2) {
        this.paint.setColor(arg2);
    }

    public void setColorFilter(ColorFilter arg2) {
        this.paint.setColorFilter(arg2);
    }

    private void updatePath() {
        this.path.reset();
        float v0 = this.animProgress * 2f - 1f;
        this.path.moveTo(((float)AndroidUtilities.dp(3f)), (((float)AndroidUtilities.dp(12f))) - (((float)AndroidUtilities.dp(4f))) * v0);
        this.path.lineTo(((float)AndroidUtilities.dp(13f)), (((float)AndroidUtilities.dp(12f))) + (((float)AndroidUtilities.dp(4f))) * v0);
        this.path.lineTo(((float)AndroidUtilities.dp(23f)), (((float)AndroidUtilities.dp(12f))) - (((float)AndroidUtilities.dp(4f))) * v0);
    }
}

