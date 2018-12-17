package org.telegram.ui.Components;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint$Cap;
import android.graphics.Paint$Style;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.ActionBar.Theme;

public class ContextProgressView extends View {
    private RectF cicleRect;
    private int currentColorType;
    private Paint innerPaint;
    private long lastUpdateTime;
    private Paint outerPaint;
    private int radOffset;

    public ContextProgressView(Context arg3, int arg4) {
        super(arg3);
        this.innerPaint = new Paint(1);
        this.outerPaint = new Paint(1);
        this.cicleRect = new RectF();
        this.radOffset = 0;
        this.innerPaint.setStyle(Paint$Style.STROKE);
        this.innerPaint.setStrokeWidth(((float)AndroidUtilities.dp(2f)));
        this.outerPaint.setStyle(Paint$Style.STROKE);
        this.outerPaint.setStrokeWidth(((float)AndroidUtilities.dp(2f)));
        this.outerPaint.setStrokeCap(Paint$Cap.ROUND);
        this.currentColorType = arg4;
        this.updateColors();
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.lastUpdateTime = System.currentTimeMillis();
        this.invalidate();
    }

    protected void onDraw(Canvas arg11) {
        if(this.getVisibility() != 0) {
            return;
        }

        long v0 = System.currentTimeMillis();
        long v2 = v0 - this.lastUpdateTime;
        this.lastUpdateTime = v0;
        this.radOffset = ((int)((((float)this.radOffset)) + (((float)(v2 * 360))) / 1000f));
        int v0_1 = this.getMeasuredWidth() / 2 - AndroidUtilities.dp(9f);
        int v2_1 = this.getMeasuredHeight() / 2 - AndroidUtilities.dp(9f);
        this.cicleRect.set(((float)v0_1), ((float)v2_1), ((float)(v0_1 + AndroidUtilities.dp(18f))), ((float)(v2_1 + AndroidUtilities.dp(18f))));
        arg11.drawCircle(((float)(this.getMeasuredWidth() / 2)), ((float)(this.getMeasuredHeight() / 2)), ((float)AndroidUtilities.dp(9f)), this.innerPaint);
        arg11.drawArc(this.cicleRect, ((float)(this.radOffset - 90)), 90f, false, this.outerPaint);
        this.invalidate();
    }

    public void setVisibility(int arg3) {
        super.setVisibility(arg3);
        this.lastUpdateTime = System.currentTimeMillis();
        this.invalidate();
    }

    public void updateColors() {
        String v1;
        Paint v0;
        if(this.currentColorType == 0) {
            this.innerPaint.setColor(Theme.getColor("contextProgressInner1"));
            v0 = this.outerPaint;
            v1 = "contextProgressOuter1";
            goto label_8;
        }
        else if(this.currentColorType == 1) {
            this.innerPaint.setColor(Theme.getColor("contextProgressInner2"));
            v0 = this.outerPaint;
            v1 = "contextProgressOuter2";
            goto label_8;
        }
        else if(this.currentColorType == 2) {
            this.innerPaint.setColor(Theme.getColor("contextProgressInner3"));
            v0 = this.outerPaint;
            v1 = "contextProgressOuter3";
        label_8:
            v0.setColor(Theme.getColor(v1));
        }

        this.invalidate();
    }
}

