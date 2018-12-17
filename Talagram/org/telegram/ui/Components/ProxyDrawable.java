package org.telegram.ui.Components;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint$Cap;
import android.graphics.Paint$Style;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.ActionBar.Theme;

public class ProxyDrawable extends Drawable {
    private RectF cicleRect;
    private boolean connected;
    private float connectedAnimationProgress;
    private int currentColorType;
    private Drawable emptyDrawable;
    private Drawable fullDrawable;
    private boolean isEnabled;
    private long lastUpdateTime;
    private Paint outerPaint;
    private int radOffset;

    public ProxyDrawable(Context arg3) {
        super();
        this.outerPaint = new Paint(1);
        this.cicleRect = new RectF();
        this.radOffset = 0;
        this.emptyDrawable = arg3.getResources().getDrawable(2131231512);
        this.fullDrawable = arg3.getResources().getDrawable(2131231513);
        this.outerPaint.setStyle(Paint$Style.STROKE);
        this.outerPaint.setStrokeWidth(((float)AndroidUtilities.dp(2f)));
        this.outerPaint.setStrokeCap(Paint$Cap.ROUND);
        this.lastUpdateTime = SystemClock.elapsedRealtime();
    }

    public void draw(Canvas arg12) {
        long v0 = SystemClock.elapsedRealtime();
        long v6 = v0 - this.lastUpdateTime;
        this.lastUpdateTime = v0;
        float v8 = 255f;
        float v9 = 1f;
        if(!this.isEnabled) {
            this.emptyDrawable.setBounds(this.getBounds());
            this.emptyDrawable.draw(arg12);
        }
        else {
            if((this.connected) && this.connectedAnimationProgress == v9) {
                goto label_75;
            }

            this.emptyDrawable.setBounds(this.getBounds());
            this.emptyDrawable.draw(arg12);
            this.outerPaint.setColor(Theme.getColor("contextProgressOuter2"));
            this.outerPaint.setAlpha(((int)((v9 - this.connectedAnimationProgress) * v8)));
            this.radOffset = ((int)((((float)this.radOffset)) + (((float)(360 * v6))) / 1000f));
            int v0_1 = this.getBounds().width();
            int v1 = this.getBounds().height();
            v0_1 = v0_1 / 2 - AndroidUtilities.dp(3f);
            v1 = v1 / 2 - AndroidUtilities.dp(3f);
            this.cicleRect.set(((float)v0_1), ((float)v1), ((float)(v0_1 + AndroidUtilities.dp(6f))), ((float)(v1 + AndroidUtilities.dp(6f))));
            arg12.drawArc(this.cicleRect, ((float)(this.radOffset - 90)), 90f, false, this.outerPaint);
            this.invalidateSelf();
        }

    label_75:
        if((this.isEnabled) && ((this.connected) || this.connectedAnimationProgress != 0f)) {
            this.fullDrawable.setAlpha(((int)(this.connectedAnimationProgress * v8)));
            this.fullDrawable.setBounds(this.getBounds());
            this.fullDrawable.draw(arg12);
        }

        float v2 = 300f;
        if((this.connected) && this.connectedAnimationProgress != v9) {
            this.connectedAnimationProgress += (((float)v6)) / v2;
            if(this.connectedAnimationProgress > v9) {
                this.connectedAnimationProgress = v9;
            }
            else {
            }

            goto label_105;
        }
        else if(!this.connected && this.connectedAnimationProgress != 0f) {
            this.connectedAnimationProgress -= (((float)v6)) / v2;
            if(this.connectedAnimationProgress < 0f) {
                this.connectedAnimationProgress = 0f;
                goto label_105;
            }
            else {
            label_105:
                this.invalidateSelf();
            }
        }
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
        this.emptyDrawable.setColorFilter(arg2);
        this.fullDrawable.setColorFilter(arg2);
    }

    public void setConnected(boolean arg1, boolean arg2, boolean arg3) {
        this.isEnabled = arg1;
        this.connected = arg2;
        this.lastUpdateTime = SystemClock.elapsedRealtime();
        if(!arg3) {
            float v1 = this.connected ? 1f : 0f;
            this.connectedAnimationProgress = v1;
        }

        this.invalidateSelf();
    }
}

