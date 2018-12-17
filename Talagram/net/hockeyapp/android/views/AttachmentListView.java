package net.hockeyapp.android.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View$MeasureSpec;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.view.ViewGroup;
import java.util.ArrayList;
import net.hockeyapp.android.e.e;

public class AttachmentListView extends ViewGroup {
    private int a;

    public AttachmentListView(Context arg1) {
        super(arg1);
    }

    public AttachmentListView(Context arg1, AttributeSet arg2) {
        super(arg1, arg2);
    }

    protected boolean checkLayoutParams(ViewGroup$LayoutParams arg1) {
        boolean v1 = arg1 != null ? true : false;
        return v1;
    }

    protected ViewGroup$LayoutParams generateDefaultLayoutParams() {
        return new ViewGroup$LayoutParams(1, 1);
    }

    public ArrayList getAttachments() {
        ArrayList v0 = new ArrayList();
        int v1;
        for(v1 = 0; v1 < this.getChildCount(); ++v1) {
            v0.add(this.getChildAt(v1).getAttachmentUri());
        }

        return v0;
    }

    protected void onLayout(boolean arg6, int arg7, int arg8, int arg9, int arg10) {
        int v6 = this.getChildCount();
        arg9 -= arg7;
        arg7 = this.getPaddingLeft();
        arg8 = this.getPaddingTop();
        for(arg10 = 0; arg10 < v6; ++arg10) {
            View v0 = this.getChildAt(arg10);
            if(v0.getVisibility() != 8) {
                v0.invalidate();
                int v1 = v0.getMeasuredWidth();
                int v2 = v0.getMeasuredHeight();
                ViewGroup$LayoutParams v3 = v0.getLayoutParams();
                if(arg7 + v1 > arg9) {
                    arg7 = this.getPaddingLeft();
                    arg8 += this.a;
                }

                v0.layout(arg7, arg8, arg7 + v1, v2 + arg8);
                arg7 += v1 + v3.width + ((a)v0).getGap();
            }
        }
    }

    protected void onMeasure(int arg11, int arg12) {
        if(View$MeasureSpec.getMode(arg11) == 0) {
            e.b("AttachmentListView", "Width is unspecified");
        }

        arg11 = View$MeasureSpec.getSize(arg11);
        int v0 = this.getChildCount();
        int v1 = this.getPaddingLeft();
        int v3 = 0;
        int v4 = this.getPaddingTop();
        int v5 = 0;
        int v2 = v1;
        v1 = 0;
        while(true) {
            int v6 = -2147483648;
            if(v3 >= v0) {
                break;
            }

            View v5_1 = this.getChildAt(v3);
            int v7 = v5_1.getPaddingTop() + v5_1.getEffectiveMaxHeight();
            if(v5_1.getVisibility() != 8) {
                ViewGroup$LayoutParams v8 = v5_1.getLayoutParams();
                v5_1.measure(View$MeasureSpec.makeMeasureSpec(arg11, v6), View$MeasureSpec.makeMeasureSpec(v7, v6));
                v6 = v5_1.getMeasuredWidth();
                v1 = Math.max(v1, v5_1.getMeasuredHeight() + v8.height);
                if(v2 + v6 > arg11) {
                    v2 = this.getPaddingLeft();
                    v4 += v1;
                }

                v2 += v6 + v8.width;
            }

            ++v3;
            v5 = v7;
        }

        this.a = v1;
        if(View$MeasureSpec.getMode(arg12) == 0) {
            v4 += v1;
            goto label_47;
        }
        else if(View$MeasureSpec.getMode(arg12) == v6) {
            v4 += v1;
            if(this.getPaddingBottom() + v4 < v5) {
            label_47:
                v5 = v4 + this.getPaddingBottom();
            }
        }

        this.setMeasuredDimension(arg11, v5);
    }
}

