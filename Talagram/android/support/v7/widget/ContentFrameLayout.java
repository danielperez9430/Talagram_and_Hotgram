package android.support.v7.widget;

import android.content.Context;
import android.graphics.Rect;
import android.support.v4.view.t;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View$MeasureSpec;
import android.view.View;
import android.widget.FrameLayout;

public class ContentFrameLayout extends FrameLayout {
    public interface a {
        void a();

        void b();
    }

    private TypedValue a;
    private TypedValue b;
    private TypedValue c;
    private TypedValue d;
    private TypedValue e;
    private TypedValue f;
    private final Rect g;
    private a h;

    public ContentFrameLayout(Context arg2) {
        this(arg2, null);
    }

    public ContentFrameLayout(Context arg2, AttributeSet arg3) {
        this(arg2, arg3, 0);
    }

    public ContentFrameLayout(Context arg1, AttributeSet arg2, int arg3) {
        super(arg1, arg2, arg3);
        this.g = new Rect();
    }

    public void a(int arg2, int arg3, int arg4, int arg5) {
        this.g.set(arg2, arg3, arg4, arg5);
        if(t.A(((View)this))) {
            this.requestLayout();
        }
    }

    public void a(Rect arg1) {
        this.fitSystemWindows(arg1);
    }

    public TypedValue getFixedHeightMajor() {
        if(this.e == null) {
            this.e = new TypedValue();
        }

        return this.e;
    }

    public TypedValue getFixedHeightMinor() {
        if(this.f == null) {
            this.f = new TypedValue();
        }

        return this.f;
    }

    public TypedValue getFixedWidthMajor() {
        if(this.c == null) {
            this.c = new TypedValue();
        }

        return this.c;
    }

    public TypedValue getFixedWidthMinor() {
        if(this.d == null) {
            this.d = new TypedValue();
        }

        return this.d;
    }

    public TypedValue getMinWidthMajor() {
        if(this.a == null) {
            this.a = new TypedValue();
        }

        return this.a;
    }

    public TypedValue getMinWidthMinor() {
        if(this.b == null) {
            this.b = new TypedValue();
        }

        return this.b;
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if(this.h != null) {
            this.h.a();
        }
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if(this.h != null) {
            this.h.b();
        }
    }

    protected void onMeasure(int arg14, int arg15) {
        float v14_1;
        float v5_2;
        int v10_2;
        float v10_1;
        DisplayMetrics v0 = this.getContext().getResources().getDisplayMetrics();
        int v3 = 1;
        int v1 = v0.widthPixels < v0.heightPixels ? 1 : 0;
        int v2 = View$MeasureSpec.getMode(arg14);
        int v5 = View$MeasureSpec.getMode(arg15);
        int v6 = 6;
        int v7 = 5;
        int v8 = -2147483648;
        int v9 = 1073741824;
        if(v2 == v8) {
            TypedValue v10 = v1 != 0 ? this.d : this.c;
            if(v10 == null) {
                goto label_52;
            }

            if(v10.type == 0) {
                goto label_52;
            }

            if(v10.type == v7) {
                v10_1 = v10.getDimension(v0);
                goto label_28;
            }
            else if(v10.type == v6) {
                v10_1 = v10.getFraction(((float)v0.widthPixels), ((float)v0.widthPixels));
            label_28:
                v10_2 = ((int)v10_1);
            }
            else {
                v10_2 = 0;
            }

            if(v10_2 <= 0) {
                goto label_52;
            }

            v10_2 = View$MeasureSpec.makeMeasureSpec(Math.min(v10_2 - (this.g.left + this.g.right), View$MeasureSpec.getSize(arg14)), v9);
            arg14 = 1;
        }
        else {
        label_52:
            v10_2 = arg14;
            arg14 = 0;
        }

        if(v5 == v8) {
            TypedValue v5_1 = v1 != 0 ? this.e : this.f;
            if(v5_1 == null) {
                goto label_86;
            }

            if(v5_1.type == 0) {
                goto label_86;
            }

            if(v5_1.type == v7) {
                v5_2 = v5_1.getDimension(v0);
                goto label_65;
            }
            else if(v5_1.type == v6) {
                v5_2 = v5_1.getFraction(((float)v0.heightPixels), ((float)v0.heightPixels));
            label_65:
                v5 = ((int)v5_2);
            }
            else {
                v5 = 0;
            }

            if(v5 <= 0) {
                goto label_86;
            }

            arg15 = View$MeasureSpec.makeMeasureSpec(Math.min(v5 - (this.g.top + this.g.bottom), View$MeasureSpec.getSize(arg15)), v9);
        }

    label_86:
        super.onMeasure(v10_2, arg15);
        v5 = this.getMeasuredWidth();
        v10_2 = View$MeasureSpec.makeMeasureSpec(v5, v9);
        if(arg14 != 0 || v2 != v8) {
        label_122:
            v3 = 0;
        }
        else {
            TypedValue v14 = v1 != 0 ? this.b : this.a;
            if(v14 == null) {
                goto label_122;
            }

            if(v14.type == 0) {
                goto label_122;
            }

            if(v14.type == v7) {
                v14_1 = v14.getDimension(v0);
                goto label_101;
            }
            else if(v14.type == v6) {
                v14_1 = v14.getFraction(((float)v0.widthPixels), ((float)v0.widthPixels));
            label_101:
                arg14 = ((int)v14_1);
            }
            else {
                arg14 = 0;
            }

            if(arg14 > 0) {
                arg14 -= this.g.left + this.g.right;
            }

            if(v5 >= arg14) {
                goto label_122;
            }

            v10_2 = View$MeasureSpec.makeMeasureSpec(arg14, v9);
        }

        if(v3 != 0) {
            super.onMeasure(v10_2, arg15);
        }
    }

    public void setAttachListener(a arg1) {
        this.h = arg1;
    }
}

