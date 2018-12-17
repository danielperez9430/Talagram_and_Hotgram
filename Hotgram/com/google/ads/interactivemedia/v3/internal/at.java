package com.google.ads.interactivemedia.v3.internal;

import android.content.Context;
import android.view.View$MeasureSpec;
import android.widget.FrameLayout;

public final class at extends FrameLayout {
    private float a;

    public at(Context arg1) {
        super(arg1);
    }

    public void a(float arg2) {
        if(this.a != arg2) {
            this.a = arg2;
            this.requestLayout();
        }
    }

    protected void onMeasure(int arg7, int arg8) {
        super.onMeasure(arg7, arg8);
        if(this.a == 0f) {
            return;
        }

        arg7 = this.getMeasuredWidth();
        int v0 = this.getMeasuredHeight();
        float v1 = ((float)arg7);
        float v2 = ((float)v0);
        float v4 = this.a / (v1 / v2) - 1f;
        if(Math.abs(v4) <= 0.01f) {
            return;
        }

        if(v4 > 0f) {
            v0 = ((int)(v1 / this.a));
        }
        else {
            arg7 = ((int)(v2 * this.a));
        }

        super.onMeasure(View$MeasureSpec.makeMeasureSpec(arg7, 1073741824), View$MeasureSpec.makeMeasureSpec(v0, 1073741824));
    }
}

