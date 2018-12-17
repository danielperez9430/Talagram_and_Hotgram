package android.support.design.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.design.a$d;
import android.support.design.a$f;
import android.support.design.a$k;
import android.support.design.h.a;
import android.support.v4.view.t;
import android.util.AttributeSet;
import android.view.View$MeasureSpec;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SnackbarContentLayout extends LinearLayout implements a {
    private TextView a;
    private Button b;
    private int c;
    private int d;

    public SnackbarContentLayout(Context arg2) {
        this(arg2, null);
    }

    public SnackbarContentLayout(Context arg2, AttributeSet arg3) {
        super(arg2, arg3);
        TypedArray v2 = arg2.obtainStyledAttributes(arg3, k.SnackbarLayout);
        this.c = v2.getDimensionPixelSize(k.SnackbarLayout_android_maxWidth, -1);
        this.d = v2.getDimensionPixelSize(k.SnackbarLayout_maxActionInlineWidth, -1);
        v2.recycle();
    }

    private static void a(View arg2, int arg3, int arg4) {
        if(t.v(arg2)) {
            t.a(arg2, t.h(arg2), arg3, t.i(arg2), arg4);
        }
        else {
            arg2.setPadding(arg2.getPaddingLeft(), arg3, arg2.getPaddingRight(), arg4);
        }
    }

    private boolean a(int arg3, int arg4, int arg5) {
        boolean v3;
        if(arg3 != this.getOrientation()) {
            this.setOrientation(arg3);
            v3 = true;
        }
        else {
            v3 = false;
        }

        if(this.a.getPaddingTop() != arg4 || this.a.getPaddingBottom() != arg5) {
            SnackbarContentLayout.a(this.a, arg4, arg5);
            v3 = true;
        }

        return v3;
    }

    public void a(int arg8, int arg9) {
        this.a.setAlpha(0f);
        float v2 = 1f;
        long v3 = ((long)arg9);
        long v5 = ((long)arg8);
        this.a.animate().alpha(v2).setDuration(v3).setStartDelay(v5).start();
        if(this.b.getVisibility() == 0) {
            this.b.setAlpha(0f);
            this.b.animate().alpha(v2).setDuration(v3).setStartDelay(v5).start();
        }
    }

    public void b(int arg8, int arg9) {
        float v1 = 1f;
        this.a.setAlpha(v1);
        long v3 = ((long)arg9);
        long v5 = ((long)arg8);
        this.a.animate().alpha(0f).setDuration(v3).setStartDelay(v5).start();
        if(this.b.getVisibility() == 0) {
            this.b.setAlpha(v1);
            this.b.animate().alpha(0f).setDuration(v3).setStartDelay(v5).start();
        }
    }

    public Button getActionView() {
        return this.b;
    }

    public TextView getMessageView() {
        return this.a;
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.a = this.findViewById(f.snackbar_text);
        this.b = this.findViewById(f.snackbar_action);
    }

    protected void onMeasure(int arg8, int arg9) {
        super.onMeasure(arg8, arg9);
        if(this.c > 0 && this.getMeasuredWidth() > this.c) {
            arg8 = View$MeasureSpec.makeMeasureSpec(this.c, 1073741824);
            super.onMeasure(arg8, arg9);
        }

        int v0 = this.getResources().getDimensionPixelSize(d.design_snackbar_padding_vertical_2lines);
        int v1 = this.getResources().getDimensionPixelSize(d.design_snackbar_padding_vertical);
        int v4 = 1;
        int v2 = this.a.getLayout().getLineCount() > 1 ? 1 : 0;
        if(v2 == 0 || this.d <= 0 || this.b.getMeasuredWidth() <= this.d) {
            if(v2 != 0) {
            }
            else {
                v0 = v1;
            }

            if(this.a(0, v0, v0)) {
                goto label_43;
            }

        label_42:
            v4 = 0;
        }
        else if(this.a(1, v0, v0 - v1)) {
        }
        else {
            goto label_42;
        }

    label_43:
        if(v4 != 0) {
            super.onMeasure(arg8, arg9);
        }
    }
}

