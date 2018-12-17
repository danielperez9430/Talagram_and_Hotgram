package org.telegram.ui.Components;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint$Cap;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.view.animation.DecelerateInterpolator;
import org.telegram.messenger.AndroidUtilities;

public class CloseProgressDrawable extends Drawable {
    private int currentAnimationTime;
    private int currentSegment;
    private DecelerateInterpolator interpolator;
    private long lastFrameTime;
    private Paint paint;

    public CloseProgressDrawable() {
        super();
        this.paint = new Paint(1);
        this.interpolator = new DecelerateInterpolator();
        this.paint.setColor(-9079435);
        this.paint.setStrokeWidth(((float)AndroidUtilities.dp(2f)));
        this.paint.setStrokeCap(Paint$Cap.ROUND);
    }

    public void draw(Canvas arg13) {
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

    public void setColorFilter(ColorFilter arg1) {
    }
}

