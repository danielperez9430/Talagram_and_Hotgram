package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build$VERSION;
import android.support.v7.b.a$a;
import android.support.v7.b.a$b;
import android.support.v7.b.a$d;
import android.support.v7.b.a$e;
import android.util.AttributeSet;
import android.view.View$MeasureSpec;
import android.view.View;
import android.widget.FrameLayout;

public class CardView extends FrameLayout {
    class android.support.v7.widget.CardView$1 implements ac {
        private Drawable b;

        android.support.v7.widget.CardView$1(CardView arg1) {
            this.a = arg1;
            super();
        }

        public void a(int arg2, int arg3) {
            if(arg2 > this.a.a) {
                CardView.a(this.a, arg2);
            }

            if(arg3 > this.a.b) {
                CardView.b(this.a, arg3);
            }
        }

        public void a(int arg3, int arg4, int arg5, int arg6) {
            this.a.d.set(arg3, arg4, arg5, arg6);
            CardView.a(this.a, arg3 + this.a.c.left, arg4 + this.a.c.top, arg5 + this.a.c.right, arg6 + this.a.c.bottom);
        }

        public void a(Drawable arg2) {
            this.b = arg2;
            this.a.setBackgroundDrawable(arg2);
        }

        public boolean a() {
            return this.a.getUseCompatPadding();
        }

        public boolean b() {
            return this.a.getPreventCornerOverlap();
        }

        public Drawable c() {
            return this.b;
        }

        public View d() {
            return this.a;
        }
    }

    int a;
    int b;
    final Rect c;
    final Rect d;
    private static final int[] e;
    private static final ad f;
    private boolean g;
    private boolean h;
    private final ac i;

    static {
        z v0_1;
        CardView.e = new int[]{16842801};
        if(Build$VERSION.SDK_INT >= 21) {
            aa v0 = new aa();
        }
        else if(Build$VERSION.SDK_INT >= 17) {
            v0_1 = new z();
        }
        else {
            ab v0_2 = new ab();
        }

        CardView.f = ((ad)v0_1);
        CardView.f.a();
    }

    public CardView(Context arg2) {
        this(arg2, null);
    }

    public CardView(Context arg2, AttributeSet arg3) {
        this(arg2, arg3, a.cardViewStyle);
    }

    public CardView(Context arg9, AttributeSet arg10, int arg11) {
        Resources v11_3;
        int v1;
        ColorStateList v11;
        super(arg9, arg10, arg11);
        this.c = new Rect();
        this.d = new Rect();
        this.i = new android.support.v7.widget.CardView$1(this);
        TypedArray v10 = arg9.obtainStyledAttributes(arg10, e.CardView, arg11, d.CardView);
        if(v10.hasValue(e.CardView_cardBackgroundColor)) {
            v11 = v10.getColorStateList(e.CardView_cardBackgroundColor);
        }
        else {
            TypedArray v11_1 = this.getContext().obtainStyledAttributes(CardView.e);
            v1 = v11_1.getColor(0, 0);
            v11_1.recycle();
            float[] v11_2 = new float[3];
            Color.colorToHSV(v1, v11_2);
            if(v11_2[2] > 0.5f) {
                v11_3 = this.getResources();
                v1 = b.cardview_light_background;
            }
            else {
                v11_3 = this.getResources();
                v1 = b.cardview_dark_background;
            }

            arg11 = v11_3.getColor(v1);
            v11 = ColorStateList.valueOf(arg11);
        }

        ColorStateList v4 = v11;
        float v5 = v10.getDimension(e.CardView_cardCornerRadius, 0f);
        float v6 = v10.getDimension(e.CardView_cardElevation, 0f);
        float v11_4 = v10.getDimension(e.CardView_cardMaxElevation, 0f);
        this.g = v10.getBoolean(e.CardView_cardUseCompatPadding, false);
        this.h = v10.getBoolean(e.CardView_cardPreventCornerOverlap, true);
        v1 = v10.getDimensionPixelSize(e.CardView_contentPadding, 0);
        this.c.left = v10.getDimensionPixelSize(e.CardView_contentPaddingLeft, v1);
        this.c.top = v10.getDimensionPixelSize(e.CardView_contentPaddingTop, v1);
        this.c.right = v10.getDimensionPixelSize(e.CardView_contentPaddingRight, v1);
        this.c.bottom = v10.getDimensionPixelSize(e.CardView_contentPaddingBottom, v1);
        float v7 = v6 > v11_4 ? v6 : v11_4;
        this.a = v10.getDimensionPixelSize(e.CardView_android_minWidth, 0);
        this.b = v10.getDimensionPixelSize(e.CardView_android_minHeight, 0);
        v10.recycle();
        CardView.f.a(this.i, arg9, v4, v5, v6, v7);
    }

    static void a(CardView arg0, int arg1) {
        super.setMinimumWidth(arg1);
    }

    static void a(CardView arg0, int arg1, int arg2, int arg3, int arg4) {
        super.setPadding(arg1, arg2, arg3, arg4);
    }

    static void b(CardView arg0, int arg1) {
        super.setMinimumHeight(arg1);
    }

    public ColorStateList getCardBackgroundColor() {
        return CardView.f.i(this.i);
    }

    public float getCardElevation() {
        return CardView.f.e(this.i);
    }

    public int getContentPaddingBottom() {
        return this.c.bottom;
    }

    public int getContentPaddingLeft() {
        return this.c.left;
    }

    public int getContentPaddingRight() {
        return this.c.right;
    }

    public int getContentPaddingTop() {
        return this.c.top;
    }

    public float getMaxCardElevation() {
        return CardView.f.a(this.i);
    }

    public boolean getPreventCornerOverlap() {
        return this.h;
    }

    public float getRadius() {
        return CardView.f.d(this.i);
    }

    public boolean getUseCompatPadding() {
        return this.g;
    }

    protected void onMeasure(int arg6, int arg7) {
        if(!(CardView.f instanceof aa)) {
            int v0 = View$MeasureSpec.getMode(arg6);
            int v1 = 1073741824;
            int v2 = -2147483648;
            if(v0 == v2 || v0 == v1) {
                arg6 = View$MeasureSpec.makeMeasureSpec(Math.max(((int)Math.ceil(((double)CardView.f.b(this.i)))), View$MeasureSpec.getSize(arg6)), v0);
            }
            else {
            }

            v0 = View$MeasureSpec.getMode(arg7);
            if(v0 != v2 && v0 != v1) {
                goto label_31;
            }

            arg7 = View$MeasureSpec.makeMeasureSpec(Math.max(((int)Math.ceil(((double)CardView.f.c(this.i)))), View$MeasureSpec.getSize(arg7)), v0);
        }

    label_31:
        super.onMeasure(arg6, arg7);
    }

    public void setCardBackgroundColor(int arg3) {
        CardView.f.a(this.i, ColorStateList.valueOf(arg3));
    }

    public void setCardBackgroundColor(ColorStateList arg3) {
        CardView.f.a(this.i, arg3);
    }

    public void setCardElevation(float arg3) {
        CardView.f.c(this.i, arg3);
    }

    public void setMaxCardElevation(float arg3) {
        CardView.f.b(this.i, arg3);
    }

    public void setMinimumHeight(int arg1) {
        this.b = arg1;
        super.setMinimumHeight(arg1);
    }

    public void setMinimumWidth(int arg1) {
        this.a = arg1;
        super.setMinimumWidth(arg1);
    }

    public void setPadding(int arg1, int arg2, int arg3, int arg4) {
    }

    public void setPaddingRelative(int arg1, int arg2, int arg3, int arg4) {
    }

    public void setPreventCornerOverlap(boolean arg2) {
        if(arg2 != this.h) {
            this.h = arg2;
            CardView.f.h(this.i);
        }
    }

    public void setRadius(float arg3) {
        CardView.f.a(this.i, arg3);
    }

    public void setUseCompatPadding(boolean arg2) {
        if(this.g != arg2) {
            this.g = arg2;
            CardView.f.g(this.i);
        }
    }
}

