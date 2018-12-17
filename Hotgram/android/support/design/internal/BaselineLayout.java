package android.support.design.internal;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class BaselineLayout extends ViewGroup {
    private int a;

    public BaselineLayout(Context arg3) {
        super(arg3, null, 0);
        this.a = -1;
    }

    public BaselineLayout(Context arg2, AttributeSet arg3) {
        super(arg2, arg3, 0);
        this.a = -1;
    }

    public BaselineLayout(Context arg1, AttributeSet arg2, int arg3) {
        super(arg1, arg2, arg3);
        this.a = -1;
    }

    public int getBaseline() {
        return this.a;
    }

    protected void onLayout(boolean arg7, int arg8, int arg9, int arg10, int arg11) {
        int v7 = this.getChildCount();
        arg9 = this.getPaddingLeft();
        arg10 = arg10 - arg8 - this.getPaddingRight() - arg9;
        arg8 = this.getPaddingTop();
        for(arg11 = 0; arg11 < v7; ++arg11) {
            View v0 = this.getChildAt(arg11);
            if(v0.getVisibility() == 8) {
            }
            else {
                int v1 = v0.getMeasuredWidth();
                int v2 = v0.getMeasuredHeight();
                int v3 = (arg10 - v1) / 2 + arg9;
                int v5 = -1;
                int v4 = this.a == v5 || v0.getBaseline() == v5 ? arg8 : this.a + arg8 - v0.getBaseline();
                v0.layout(v3, v4, v1 + v3, v2 + v4);
            }
        }
    }

    protected void onMeasure(int arg12, int arg13) {
        int v0 = this.getChildCount();
        int v1 = -1;
        int v2 = 0;
        int v3 = -1;
        int v4 = -1;
        int v5 = 0;
        int v6 = 0;
        int v7 = 0;
        while(v2 < v0) {
            View v8 = this.getChildAt(v2);
            if(v8.getVisibility() == 8) {
            }
            else {
                this.measureChild(v8, arg12, arg13);
                int v9 = v8.getBaseline();
                if(v9 != v1) {
                    v3 = Math.max(v3, v9);
                    v4 = Math.max(v4, v8.getMeasuredHeight() - v9);
                }

                v6 = Math.max(v6, v8.getMeasuredWidth());
                v5 = Math.max(v5, v8.getMeasuredHeight());
                v7 = View.combineMeasuredStates(v7, v8.getMeasuredState());
            }

            ++v2;
        }

        if(v3 != v1) {
            v5 = Math.max(v5, Math.max(v4, this.getPaddingBottom()) + v3);
            this.a = v3;
        }

        this.setMeasuredDimension(View.resolveSizeAndState(Math.max(v6, this.getSuggestedMinimumWidth()), arg12, v7), View.resolveSizeAndState(Math.max(v5, this.getSuggestedMinimumHeight()), arg13, v7 << 16));
    }
}

