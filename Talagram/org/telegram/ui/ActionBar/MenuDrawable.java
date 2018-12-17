package org.telegram.ui.ActionBar;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.view.animation.DecelerateInterpolator;
import org.telegram.messenger.AndroidUtilities;

public class MenuDrawable extends Drawable {
    private boolean animationInProgress;
    private int currentAnimationTime;
    private float currentRotation;
    private float finalRotation;
    private DecelerateInterpolator interpolator;
    private long lastFrameTime;
    private Paint paint;
    private boolean reverseAngle;

    public MenuDrawable() {
        super();
        this.paint = new Paint(1);
        this.reverseAngle = false;
        this.interpolator = new DecelerateInterpolator();
        this.paint.setColor(-1);
        this.paint.setStrokeWidth(((float)AndroidUtilities.dp(2f)));
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

    public void setColorFilter(ColorFilter arg2) {
        this.paint.setColorFilter(arg2);
    }

    public void setRotation(float arg6, boolean arg7) {
        // Method was not decompiled
    }
}

