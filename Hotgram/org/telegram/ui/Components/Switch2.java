package org.telegram.ui.Components;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap$Config;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint$Cap;
import android.graphics.Paint$Style;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Keep;
import android.view.View;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.ActionBar.Theme;

public class Switch2 extends View {
    private boolean attachedToWindow;
    private ObjectAnimator checkAnimator;
    private static Bitmap drawBitmap;
    private boolean isChecked;
    private Paint paint;
    private Paint paint2;
    private float progress;
    private RectF rectF;
    private Paint shadowPaint;

    public Switch2(Context arg7) {
        super(arg7);
        this.rectF = new RectF();
        float v0 = 2f;
        float v2 = 24f;
        if(Switch2.drawBitmap == null || Switch2.drawBitmap.getWidth() != AndroidUtilities.dp(v2)) {
            Switch2.drawBitmap = Bitmap.createBitmap(AndroidUtilities.dp(v2), AndroidUtilities.dp(v2), Bitmap$Config.ARGB_8888);
            Canvas v7 = new Canvas(Switch2.drawBitmap);
            Paint v2_1 = new Paint(1);
            v2_1.setShadowLayer(((float)AndroidUtilities.dp(v0)), 0f, 0f, 2130706432);
            v7.drawCircle(((float)AndroidUtilities.dp(12f)), ((float)AndroidUtilities.dp(12f)), ((float)AndroidUtilities.dp(9f)), v2_1);
            Bitmap v2_2 = null;
            try {
                v7.setBitmap(v2_2);
                goto label_39;
            }
            catch(Exception ) {
            label_39:
                this.shadowPaint = new Paint(2);
                this.paint = new Paint(1);
                this.paint2 = new Paint(1);
                this.paint2.setStyle(Paint$Style.STROKE);
                this.paint2.setStrokeCap(Paint$Cap.ROUND);
                this.paint2.setStrokeWidth(((float)AndroidUtilities.dp(v0)));
                return;
            }
        }

        goto label_39;
    }

    private void animateToCheckedState(boolean arg4) {
        String v0 = "progress";
        float[] v1 = new float[1];
        float v4 = arg4 ? 1f : 0f;
        v1[0] = v4;
        this.checkAnimator = ObjectAnimator.ofFloat(this, v0, v1);
        this.checkAnimator.setDuration(250);
        this.checkAnimator.start();
    }

    private void cancelCheckAnimator() {
        if(this.checkAnimator != null) {
            this.checkAnimator.cancel();
        }
    }

    public float getProgress() {
        return this.progress;
    }

    public boolean isChecked() {
        return this.isChecked;
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.attachedToWindow = true;
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.attachedToWindow = false;
    }

    protected void onDraw(Canvas arg18) {
        Switch2 v0 = this;
        Canvas v7 = arg18;
        if(this.getVisibility() != 0) {
            return;
        }

        int v1 = AndroidUtilities.dp(36f);
        AndroidUtilities.dp(20f);
        int v2 = (this.getMeasuredWidth() - v1) / 2;
        int v3 = (this.getMeasuredHeight() - AndroidUtilities.dp(14f)) / 2;
        int v5 = (((int)((((float)(v1 - AndroidUtilities.dp(14f)))) * v0.progress))) + v2 + AndroidUtilities.dp(7f);
        int v6 = this.getMeasuredHeight() / 2;
        int v9 = Theme.getColor("switch2Track");
        int v10 = Theme.getColor("switch2TrackChecked");
        int v11 = Color.red(v9);
        int v12 = Color.red(v10);
        int v13 = Color.green(v9);
        int v14 = Color.green(v10);
        int v15 = Color.blue(v9);
        int v16 = Color.blue(v10);
        v9 = Color.alpha(v9);
        v0.paint.setColor(((((int)((((float)v11)) + (((float)(v12 - v11))) * v0.progress))) & 255) << 16 | ((((int)((((float)v9)) + (((float)(Color.alpha(v10) - v9))) * v0.progress))) & 255) << 24 | ((((int)((((float)v13)) + (((float)(v14 - v13))) * v0.progress))) & 255) << 8 | (((int)((((float)v15)) + (((float)(v16 - v15))) * v0.progress))) & 255);
        v0.rectF.set(((float)v2), ((float)v3), ((float)(v2 + v1)), ((float)(v3 + AndroidUtilities.dp(14f))));
        v7.drawRoundRect(v0.rectF, ((float)AndroidUtilities.dp(7f)), ((float)AndroidUtilities.dp(7f)), v0.paint);
        v1 = Theme.getColor("switch2Thumb");
        v2 = Theme.getColor("switch2ThumbChecked");
        v3 = Color.red(v1);
        int v4 = Color.red(v2);
        int v8 = Color.green(v1);
        v9 = Color.green(v2);
        v10 = Color.blue(v1);
        v11 = Color.blue(v2);
        v1 = Color.alpha(v1);
        v2 = Color.alpha(v2);
        v3 = ((int)((((float)v3)) + (((float)(v4 - v3))) * v0.progress));
        v4 = ((int)((((float)v8)) + (((float)(v9 - v8))) * v0.progress));
        v8 = ((int)((((float)v10)) + (((float)(v11 - v10))) * v0.progress));
        v1 = ((int)((((float)v1)) + (((float)(v2 - v1))) * v0.progress));
        v0.paint.setColor((v3 & 255) << 16 | (v1 & 255) << 24 | (v4 & 255) << 8 | v8 & 255);
        v0.shadowPaint.setAlpha(v1);
        v7.drawBitmap(Switch2.drawBitmap, ((float)(v5 - AndroidUtilities.dp(12f))), ((float)(v6 - AndroidUtilities.dp(11f))), v0.shadowPaint);
        float v1_1 = ((float)v5);
        float v2_1 = ((float)v6);
        v7.drawCircle(v1_1, v2_1, ((float)AndroidUtilities.dp(10f)), v0.paint);
        v0.paint2.setColor(Theme.getColor("switch2Check"));
        v8 = ((int)(v1_1 - ((((float)AndroidUtilities.dp(10.8f))) - (((float)AndroidUtilities.dp(1.3f))) * v0.progress)));
        v9 = ((int)(v2_1 - ((((float)AndroidUtilities.dp(8.5f))) - (((float)AndroidUtilities.dp(0.5f))) * v0.progress)));
        v1 = (((int)AndroidUtilities.dpf2(4.6f))) + v8;
        v2 = ((int)(AndroidUtilities.dpf2(9.5f) + (((float)v9))));
        v4 = AndroidUtilities.dp(2f) + v1;
        v3 = AndroidUtilities.dp(2f) + v2;
        v5 = (((int)AndroidUtilities.dpf2(7.5f))) + v8;
        v6 = (((int)AndroidUtilities.dpf2(5.4f))) + v9;
        v12 = AndroidUtilities.dp(7f) + v5;
        v13 = AndroidUtilities.dp(7f) + v6;
        arg18.drawLine(((float)(((int)((((float)v5)) + (((float)(v1 - v5))) * v0.progress)))), ((float)(((int)((((float)v6)) + (((float)(v2 - v6))) * v0.progress)))), ((float)(((int)((((float)v12)) + (((float)(v4 - v12))) * v0.progress)))), ((float)(((int)((((float)v13)) + (((float)(v3 - v13))) * v0.progress)))), v0.paint2);
        v1 = (((int)AndroidUtilities.dpf2(7.5f))) + v8;
        v2 = (((int)AndroidUtilities.dpf2(12.5f))) + v9;
        arg18.drawLine(((float)v1), ((float)v2), ((float)(AndroidUtilities.dp(7f) + v1)), ((float)(v2 - AndroidUtilities.dp(7f))), v0.paint2);
    }

    public void setChecked(boolean arg2, boolean arg3) {
        if(arg2 == this.isChecked) {
            return;
        }

        this.isChecked = arg2;
        if(!this.attachedToWindow || !arg3) {
            this.cancelCheckAnimator();
            float v2 = arg2 ? 1f : 0f;
            this.setProgress(v2);
        }
        else {
            this.animateToCheckedState(arg2);
        }
    }

    @Keep public void setProgress(float arg2) {
        if(this.progress == arg2) {
            return;
        }

        this.progress = arg2;
        this.invalidate();
    }
}

