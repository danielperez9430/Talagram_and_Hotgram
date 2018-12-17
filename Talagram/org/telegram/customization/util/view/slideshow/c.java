package org.telegram.customization.util.view.slideshow;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.widget.LinearLayout$LayoutParams;
import android.widget.LinearLayout;

class c extends LinearLayout {
    private static final int[] a;
    private Drawable b;
    private int c;
    private int d;
    private int e;
    private int f;

    static {
        c.a = new int[]{16843049, 16843561, 16843562};
    }

    public c(Context arg4, int arg5) {
        super(arg4);
        TypedArray v4 = arg4.obtainStyledAttributes(null, c.a, arg5, 0);
        this.setDividerDrawable(v4.getDrawable(0));
        this.f = v4.getDimensionPixelSize(2, 0);
        this.e = v4.getInteger(1, 0);
        v4.recycle();
    }

    private void a(Canvas arg6) {
        int v0 = this.getChildCount();
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            View v2 = this.getChildAt(v1);
            if(v2 != null && v2.getVisibility() != 8 && (this.a(v1))) {
                this.a(arg6, v2.getTop() - v2.getLayoutParams().topMargin);
            }
        }

        if(this.a(v0)) {
            View v0_1 = this.getChildAt(v0 - 1);
            v0 = v0_1 == null ? this.getHeight() - this.getPaddingBottom() - this.d : v0_1.getBottom();
            this.a(arg6, v0);
        }
    }

    private boolean a(int arg5) {
        boolean v0 = false;
        if(arg5 != 0) {
            if(arg5 == this.getChildCount()) {
            }
            else if((this.e & 2) != 0) {
                --arg5;
                while(arg5 >= 0) {
                    if(this.getChildAt(arg5).getVisibility() != 8) {
                        v0 = true;
                    }
                    else {
                        --arg5;
                        continue;
                    }

                    return v0;
                }
            }
        }

        return v0;
    }

    private void a(Canvas arg5, int arg6) {
        this.b.setBounds(this.getPaddingLeft() + this.f, arg6, this.getWidth() - this.getPaddingRight() - this.f, this.d + arg6);
        this.b.draw(arg5);
    }

    private void b(Canvas arg6) {
        int v0 = this.getChildCount();
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            View v2 = this.getChildAt(v1);
            if(v2 != null && v2.getVisibility() != 8 && (this.a(v1))) {
                this.b(arg6, v2.getLeft() - v2.getLayoutParams().leftMargin);
            }
        }

        if(this.a(v0)) {
            View v0_1 = this.getChildAt(v0 - 1);
            v0 = v0_1 == null ? this.getWidth() - this.getPaddingRight() - this.c : v0_1.getRight();
            this.b(arg6, v0);
        }
    }

    private void b(Canvas arg6, int arg7) {
        this.b.setBounds(arg7, this.getPaddingTop() + this.f, this.c + arg7, this.getHeight() - this.getPaddingBottom() - this.f);
        this.b.draw(arg6);
    }

    protected void measureChildWithMargins(View arg7, int arg8, int arg9, int arg10, int arg11) {
        int v0 = this.indexOfChild(arg7);
        int v1 = this.getOrientation();
        ViewGroup$LayoutParams v2 = arg7.getLayoutParams();
        if(this.a(v0)) {
            if(v1 == 1) {
                ((LinearLayout$LayoutParams)v2).topMargin = this.d;
            }
            else {
                ((LinearLayout$LayoutParams)v2).leftMargin = this.c;
            }
        }

        int v3 = this.getChildCount();
        if(v0 == v3 - 1 && (this.a(v3))) {
            if(v1 == 1) {
                ((LinearLayout$LayoutParams)v2).bottomMargin = this.d;
            }
            else {
                ((LinearLayout$LayoutParams)v2).rightMargin = this.c;
            }
        }

        super.measureChildWithMargins(arg7, arg8, arg9, arg10, arg11);
    }

    protected void onDraw(Canvas arg3) {
        if(this.b != null) {
            if(this.getOrientation() == 1) {
                this.a(arg3);
            }
            else {
                this.b(arg3);
            }
        }

        super.onDraw(arg3);
    }

    public void setDividerDrawable(Drawable arg3) {
        if(arg3 == this.b) {
            return;
        }

        this.b = arg3;
        boolean v0 = false;
        if(arg3 != null) {
            this.c = arg3.getIntrinsicWidth();
            this.d = arg3.getIntrinsicHeight();
        }
        else {
            this.c = 0;
            this.d = 0;
        }

        if(arg3 == null) {
            v0 = true;
        }

        this.setWillNotDraw(v0);
        this.requestLayout();
    }
}

