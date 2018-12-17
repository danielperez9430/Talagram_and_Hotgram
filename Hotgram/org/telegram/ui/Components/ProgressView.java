package org.telegram.ui.Components;

import android.graphics.Canvas;
import android.graphics.Paint;
import org.telegram.messenger.AndroidUtilities;

public class ProgressView {
    public float currentProgress;
    public int height;
    private Paint innerPaint;
    private Paint outerPaint;
    public float progressHeight;
    public int width;

    public ProgressView() {
        super();
        this.currentProgress = 0f;
        this.progressHeight = ((float)AndroidUtilities.dp(2f));
        this.innerPaint = new Paint();
        this.outerPaint = new Paint();
    }

    public void draw(Canvas arg10) {
        arg10.drawRect(0f, (((float)(this.height / 2))) - this.progressHeight / 2f, ((float)this.width), (((float)(this.height / 2))) + this.progressHeight / 2f, this.innerPaint);
        arg10.drawRect(0f, (((float)(this.height / 2))) - this.progressHeight / 2f, (((float)this.width)) * this.currentProgress, (((float)(this.height / 2))) + this.progressHeight / 2f, this.outerPaint);
    }

    public void setProgress(float arg2) {
        this.currentProgress = arg2;
        float v0 = 0f;
        if(this.currentProgress >= 0f) {
            v0 = 1f;
            if(this.currentProgress > v0) {
                goto label_4;
            }
        }
        else {
        label_4:
            this.currentProgress = v0;
        }
    }

    public void setProgressColors(int arg2, int arg3) {
        this.innerPaint.setColor(arg2);
        this.outerPaint.setColor(arg3);
    }
}

