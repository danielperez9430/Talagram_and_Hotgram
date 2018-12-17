package android.support.v7.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.t;
import android.support.v7.a.a$f;
import android.support.v7.a.a$j;
import android.util.AttributeSet;
import android.view.View$MeasureSpec;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.widget.LinearLayout$LayoutParams;
import android.widget.LinearLayout;

public class ButtonBarLayout extends LinearLayout {
    private boolean a;
    private int b;
    private int c;

    public ButtonBarLayout(Context arg2, AttributeSet arg3) {
        super(arg2, arg3);
        this.b = -1;
        this.c = 0;
        TypedArray v2 = arg2.obtainStyledAttributes(arg3, j.ButtonBarLayout);
        this.a = v2.getBoolean(j.ButtonBarLayout_allowStacking, true);
        v2.recycle();
    }

    private int a(int arg3) {
        int v0 = this.getChildCount();
        while(arg3 < v0) {
            if(this.getChildAt(arg3).getVisibility() == 0) {
                return arg3;
            }

            ++arg3;
        }

        return -1;
    }

    private boolean a() {
        boolean v1 = true;
        if(this.getOrientation() == 1) {
        }
        else {
            v1 = false;
        }

        return v1;
    }

    public int getMinimumHeight() {
        return Math.max(this.c, super.getMinimumHeight());
    }

    protected void onMeasure(int arg6, int arg7) {
        int v1;
        int v0 = View$MeasureSpec.getSize(arg6);
        int v2 = 0;
        if(this.a) {
            if(v0 > this.b && (this.a())) {
                this.setStacked(false);
            }

            this.b = v0;
        }

        if((this.a()) || View$MeasureSpec.getMode(arg6) != 1073741824) {
            v0 = arg6;
            v1 = 0;
        }
        else {
            v0 = View$MeasureSpec.makeMeasureSpec(v0, -2147483648);
            v1 = 1;
        }

        super.onMeasure(v0, arg7);
        if((this.a) && !this.a()) {
            v0 = (this.getMeasuredWidthAndState() & -16777216) == 16777216 ? 1 : 0;
            if(v0 == 0) {
                goto label_38;
            }

            this.setStacked(true);
            v1 = 1;
        }

    label_38:
        if(v1 != 0) {
            super.onMeasure(arg6, arg7);
        }

        arg6 = this.a(0);
        if(arg6 >= 0) {
            View v7 = this.getChildAt(arg6);
            ViewGroup$LayoutParams v0_1 = v7.getLayoutParams();
            v1 = this.getPaddingTop() + v7.getMeasuredHeight() + ((LinearLayout$LayoutParams)v0_1).topMargin + ((LinearLayout$LayoutParams)v0_1).bottomMargin;
            if(this.a()) {
                arg6 = this.a(arg6 + 1);
                if(arg6 >= 0) {
                    v1 += this.getChildAt(arg6).getPaddingTop() + (((int)(this.getResources().getDisplayMetrics().density * 16f)));
                }

                v2 = v1;
            }
            else {
                v2 = v1 + this.getPaddingBottom();
            }
        }

        if(t.l(((View)this)) != v2) {
            this.setMinimumHeight(v2);
        }
    }

    public void setAllowStacking(boolean arg2) {
        if(this.a != arg2) {
            this.a = arg2;
            if(!this.a && this.getOrientation() == 1) {
                this.setStacked(false);
            }

            this.requestLayout();
        }
    }

    private void setStacked(boolean arg2) {
        int v2;
        this.setOrientation(((int)arg2));
        int v0 = arg2 ? 5 : 80;
        this.setGravity(v0);
        View v0_1 = this.findViewById(f.spacer);
        if(v0_1 != null) {
            v2 = arg2 ? 8 : 4;
            v0_1.setVisibility(v2);
        }

        for(v2 = this.getChildCount() - 2; v2 >= 0; --v2) {
            this.bringChildToFront(this.getChildAt(v2));
        }
    }
}

