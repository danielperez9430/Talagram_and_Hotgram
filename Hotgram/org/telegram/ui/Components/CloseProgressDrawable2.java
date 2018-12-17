package org.telegram.ui.Components;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint$Cap;
import android.graphics.Paint$Style;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.view.animation.DecelerateInterpolator;
import org.telegram.messenger.AndroidUtilities;

public class CloseProgressDrawable2 extends Drawable {
    private float angle;
    private boolean animating;
    private DecelerateInterpolator interpolator;
    private long lastFrameTime;
    private Paint paint;
    private RectF rect;

    public CloseProgressDrawable2() {
        super();
        this.paint = new Paint(1);
        this.interpolator = new DecelerateInterpolator();
        this.rect = new RectF();
        this.paint.setColor(-1);
        this.paint.setStrokeWidth(((float)AndroidUtilities.dp(2f)));
        this.paint.setStrokeCap(Paint$Cap.ROUND);
        this.paint.setStyle(Paint$Style.STROKE);
    }

    public void draw(Canvas arg19) {
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

    public void setColor(int arg2) {
        this.paint.setColor(arg2);
    }

    public void setColorFilter(ColorFilter arg2) {
        this.paint.setColorFilter(arg2);
    }

    public void startAnimation() {
        this.animating = true;
        this.lastFrameTime = System.currentTimeMillis();
        this.invalidateSelf();
    }

    public void stopAnimation() {
        this.animating = false;
    }
}

