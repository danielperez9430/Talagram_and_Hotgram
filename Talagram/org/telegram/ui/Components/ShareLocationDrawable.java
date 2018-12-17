package org.telegram.ui.Components;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import org.telegram.messenger.AndroidUtilities;

public class ShareLocationDrawable extends Drawable {
    private Drawable drawable;
    private Drawable drawableLeft;
    private Drawable drawableRight;
    private boolean isSmall;
    private long lastUpdateTime;
    private float[] progress;

    public ShareLocationDrawable(Context arg3, boolean arg4) {
        int v4;
        Resources v3;
        super();
        this.lastUpdateTime = 0;
        this.progress = new float[]{0f, -0.5f};
        this.isSmall = arg4;
        if(arg4) {
            this.drawable = arg3.getResources().getDrawable(2131231566);
            this.drawableLeft = arg3.getResources().getDrawable(2131231567);
            v3 = arg3.getResources();
            v4 = 2131231568;
        }
        else {
            this.drawable = arg3.getResources().getDrawable(2131230817);
            this.drawableLeft = arg3.getResources().getDrawable(2131230818);
            v3 = arg3.getResources();
            v4 = 2131230819;
        }

        this.drawableRight = v3.getDrawable(v4);
    }

    public void draw(Canvas arg18) {
        ShareLocationDrawable v0 = this;
        Canvas v1 = arg18;
        float v2 = v0.isSmall ? 30f : 120f;
        int v2_1 = AndroidUtilities.dp(v2);
        int v5 = 2;
        int v3 = this.getBounds().top + (this.getIntrinsicHeight() - v2_1) / v5;
        int v4 = this.getBounds().left + (this.getIntrinsicWidth() - v2_1) / v5;
        v0.drawable.setBounds(v4, v3, v0.drawable.getIntrinsicWidth() + v4, v0.drawable.getIntrinsicHeight() + v3);
        v0.drawable.draw(v1);
        int v6 = 0;
        while(v6 < v5) {
            if(v0.progress[v6] < 0f) {
            }
            else {
                float v8 = 0.5f;
                float v7 = v0.progress[v6] * v8 + v8;
                float v9 = v0.isSmall ? 2.5f : 5f;
                int v9_1 = AndroidUtilities.dp(v9 * v7);
                float v10 = v0.isSmall ? 6.5f : 18f;
                int v7_1 = AndroidUtilities.dp(v10 * v7);
                v10 = v0.isSmall ? 6f : 15f;
                int v10_1 = AndroidUtilities.dp(v10 * v0.progress[v6]);
                float v11 = v0.progress[v6] < v8 ? v0.progress[v6] / v8 : 1f - (v0.progress[v6] - v8) / v8;
                float v12 = 42f;
                float v13 = 7f;
                v8 = v0.isSmall ? 7f : 42f;
                int v8_1 = AndroidUtilities.dp(v8) + v4 - v10_1;
                int v14 = v0.drawable.getIntrinsicHeight() / v5 + v3;
                int v15 = v0.isSmall ? 0 : AndroidUtilities.dp(v13);
                v14 -= v15;
                int v11_1 = ((int)(v11 * 255f));
                v0.drawableLeft.setAlpha(v11_1);
                v5 = v14 - v7_1;
                v14 += v7_1;
                v0.drawableLeft.setBounds(v8_1 - v9_1, v5, v8_1 + v9_1, v14);
                v0.drawableLeft.draw(v1);
                v2_1 = v0.drawable.getIntrinsicWidth() + v4;
                if(v0.isSmall) {
                    v12 = 7f;
                }

                v2_1 = v2_1 - AndroidUtilities.dp(v12) + v10_1;
                v0.drawableRight.setAlpha(v11_1);
                v0.drawableRight.setBounds(v2_1 - v9_1, v5, v2_1 + v9_1, v14);
                v0.drawableRight.draw(v1);
            }

            ++v6;
            v5 = 2;
        }

        this.update();
    }

    public int getIntrinsicHeight() {
        float v0 = this.isSmall ? 40f : 180f;
        return AndroidUtilities.dp(v0);
    }

    public int getIntrinsicWidth() {
        float v0 = this.isSmall ? 40f : 120f;
        return AndroidUtilities.dp(v0);
    }

    public int getOpacity() {
        return 0;
    }

    public void setAlpha(int arg1) {
    }

    public void setColorFilter(ColorFilter arg2) {
        this.drawable.setColorFilter(arg2);
        this.drawableLeft.setColorFilter(arg2);
        this.drawableRight.setColorFilter(arg2);
    }

    private void update() {
        long v0 = System.currentTimeMillis();
        long v2 = v0 - this.lastUpdateTime;
        this.lastUpdateTime = v0;
        v0 = 16;
        if(v2 > v0) {
        }
        else {
            v0 = v2;
        }

        int v2_1;
        for(v2_1 = 0; v2_1 < 2; ++v2_1) {
            float v4 = 1f;
            if(this.progress[v2_1] >= v4) {
                this.progress[v2_1] = 0f;
            }

            this.progress[v2_1] += (((float)v0)) / 1300f;
            if(this.progress[v2_1] > v4) {
                this.progress[v2_1] = v4;
            }
        }

        this.invalidateSelf();
    }
}

