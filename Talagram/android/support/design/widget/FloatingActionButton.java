package android.support.design.widget;

import android.animation.Animator$AnimatorListener;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.PorterDuff$Mode;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build$VERSION;
import android.os.Parcelable;
import android.support.design.a$d;
import android.support.design.a$j;
import android.support.design.a$k;
import android.support.design.a.h;
import android.support.design.d.a;
import android.support.design.stateful.ExtendableSavedState;
import android.support.v4.view.s;
import android.support.v4.view.t;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View$MeasureSpec;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.view.ViewGroup;
import android.widget.ImageView$ScaleType;
import android.widget.ImageView;
import java.util.List;

@c(a=Behavior.class) public class FloatingActionButton extends w implements a, s, android.support.v4.widget.s {
    public class BaseBehavior extends b {
        private Rect a;
        private android.support.design.widget.FloatingActionButton$a b;
        private boolean c;

        public BaseBehavior() {
            super();
            this.c = true;
        }

        public BaseBehavior(Context arg2, AttributeSet arg3) {
            super(arg2, arg3);
            TypedArray v2 = arg2.obtainStyledAttributes(arg3, k.FloatingActionButton_Behavior_Layout);
            this.c = v2.getBoolean(k.FloatingActionButton_Behavior_Layout_behavior_autoHide, true);
            v2.recycle();
        }

        private void a(CoordinatorLayout arg7, FloatingActionButton arg8) {
            int v2;
            Rect v0 = arg8.b;
            if(v0 != null && v0.centerX() > 0 && v0.centerY() > 0) {
                ViewGroup$LayoutParams v1 = arg8.getLayoutParams();
                int v4 = 0;
                if(arg8.getRight() >= arg7.getWidth() - ((e)v1).rightMargin) {
                    v2 = v0.right;
                }
                else if(arg8.getLeft() <= ((e)v1).leftMargin) {
                    v2 = -v0.left;
                }
                else {
                    v2 = 0;
                }

                if(arg8.getBottom() >= arg7.getHeight() - ((e)v1).bottomMargin) {
                    v4 = v0.bottom;
                }
                else if(arg8.getTop() <= ((e)v1).topMargin) {
                    v4 = -v0.top;
                }

                if(v4 != 0) {
                    t.e(((View)arg8), v4);
                }

                if(v2 == 0) {
                    return;
                }

                t.f(((View)arg8), v2);
            }
        }

        private boolean a(CoordinatorLayout arg3, AppBarLayout arg4, FloatingActionButton arg5) {
            if(!this.a(((View)arg4), arg5)) {
                return 0;
            }

            if(this.a == null) {
                this.a = new Rect();
            }

            Rect v0 = this.a;
            android.support.design.widget.e.b(((ViewGroup)arg3), ((View)arg4), v0);
            if(v0.bottom <= arg4.getMinimumHeightForVisibleOverlappingContent()) {
                arg5.b(this.b, false);
            }
            else {
                arg5.a(this.b, false);
            }

            return 1;
        }

        private boolean a(View arg4, FloatingActionButton arg5) {
            ViewGroup$LayoutParams v0 = arg5.getLayoutParams();
            if(!this.c) {
                return 0;
            }

            if(((e)v0).a() != arg4.getId()) {
                return 0;
            }

            if(arg5.getUserSetVisibility() != 0) {
                return 0;
            }

            return 1;
        }

        private static boolean a(View arg1) {
            ViewGroup$LayoutParams v1 = arg1.getLayoutParams();
            if((v1 instanceof e)) {
                return ((e)v1).b() instanceof BottomSheetBehavior;
            }

            return 0;
        }

        public void a(e arg2) {
            if(arg2.h == 0) {
                arg2.h = 80;
            }
        }

        public boolean a(CoordinatorLayout arg6, FloatingActionButton arg7, int arg8) {
            List v0 = arg6.c(((View)arg7));
            int v1 = v0.size();
            int v2;
            for(v2 = 0; v2 < v1; ++v2) {
                Object v3 = v0.get(v2);
                if((v3 instanceof AppBarLayout)) {
                    if(this.a(arg6, ((AppBarLayout)v3), arg7)) {
                        break;
                    }
                }
                else if((BaseBehavior.a(((View)v3))) && (this.b(((View)v3), arg7))) {
                    break;
                }
            }

            arg6.a(((View)arg7), arg8);
            this.a(arg6, arg7);
            return 1;
        }

        public boolean a(CoordinatorLayout arg5, FloatingActionButton arg6, Rect arg7) {
            arg7.set(arg6.getLeft() + arg6.b.left, arg6.getTop() + arg6.b.top, arg6.getRight() - arg6.b.right, arg6.getBottom() - arg6.b.bottom);
            return 1;
        }

        public boolean a(CoordinatorLayout arg2, FloatingActionButton arg3, View arg4) {
            if((arg4 instanceof AppBarLayout)) {
                this.a(arg2, ((AppBarLayout)arg4), arg3);
            }
            else if(BaseBehavior.a(arg4)) {
                this.b(arg4, arg3);
            }

            return 0;
        }

        public boolean a(CoordinatorLayout arg1, View arg2, int arg3) {
            return this.a(arg1, ((FloatingActionButton)arg2), arg3);
        }

        public boolean a(CoordinatorLayout arg1, View arg2, Rect arg3) {
            return this.a(arg1, ((FloatingActionButton)arg2), arg3);
        }

        private boolean b(View arg4, FloatingActionButton arg5) {
            if(!this.a(arg4, arg5)) {
                return 0;
            }

            if(arg4.getTop() < arg5.getHeight() / 2 + arg5.getLayoutParams().topMargin) {
                arg5.b(this.b, false);
            }
            else {
                arg5.a(this.b, false);
            }

            return 1;
        }

        public boolean b(CoordinatorLayout arg1, View arg2, View arg3) {
            return this.a(arg1, ((FloatingActionButton)arg2), arg3);
        }
    }

    public class Behavior extends BaseBehavior {
        public Behavior() {
            super();
        }

        public Behavior(Context arg1, AttributeSet arg2) {
            super(arg1, arg2);
        }

        public void a(e arg1) {
            super.a(arg1);
        }

        public boolean a(CoordinatorLayout arg1, FloatingActionButton arg2, int arg3) {
            return super.a(arg1, arg2, arg3);
        }

        public boolean a(CoordinatorLayout arg1, FloatingActionButton arg2, Rect arg3) {
            return super.a(arg1, arg2, arg3);
        }

        public boolean a(CoordinatorLayout arg1, FloatingActionButton arg2, View arg3) {
            return super.a(arg1, arg2, arg3);
        }
    }

    public abstract class android.support.design.widget.FloatingActionButton$a {
        public void a(FloatingActionButton arg1) {
        }

        public void b(FloatingActionButton arg1) {
        }
    }

    class android.support.design.widget.FloatingActionButton$b implements n {
        android.support.design.widget.FloatingActionButton$b(FloatingActionButton arg1) {
            this.a = arg1;
            super();
        }

        public float a() {
            return (((float)this.a.getSizeDimension())) / 2f;
        }

        public void a(int arg3, int arg4, int arg5, int arg6) {
            this.a.b.set(arg3, arg4, arg5, arg6);
            this.a.setPadding(arg3 + FloatingActionButton.a(this.a), arg4 + FloatingActionButton.a(this.a), arg5 + FloatingActionButton.a(this.a), arg6 + FloatingActionButton.a(this.a));
        }

        public void a(Drawable arg2) {
            FloatingActionButton.a(this.a, arg2);
        }

        public boolean b() {
            return this.a.a;
        }
    }

    boolean a;
    final Rect b;
    private ColorStateList c;
    private PorterDuff$Mode d;
    private ColorStateList e;
    private PorterDuff$Mode f;
    private int g;
    private ColorStateList h;
    private int i;
    private int j;
    private int k;
    private int l;
    private final Rect m;
    private final android.support.v7.widget.n n;
    private final android.support.design.d.c o;
    private g p;

    public FloatingActionButton(Context arg2) {
        this(arg2, null);
    }

    public FloatingActionButton(Context arg2, AttributeSet arg3) {
        this(arg2, arg3, android.support.design.a$b.floatingActionButtonStyle);
    }

    public FloatingActionButton(Context arg8, AttributeSet arg9, int arg10) {
        super(arg8, arg9, arg10);
        this.b = new Rect();
        this.m = new Rect();
        TypedArray v1 = android.support.design.internal.b.a(arg8, arg9, k.FloatingActionButton, arg10, j.Widget_Design_FloatingActionButton, new int[0]);
        this.c = android.support.design.e.a.a(arg8, v1, k.FloatingActionButton_backgroundTint);
        this.d = android.support.design.internal.c.a(v1.getInt(k.FloatingActionButton_backgroundTintMode, -1), null);
        this.h = android.support.design.e.a.a(arg8, v1, k.FloatingActionButton_rippleColor);
        this.i = v1.getInt(k.FloatingActionButton_fabSize, -1);
        this.j = v1.getDimensionPixelSize(k.FloatingActionButton_fabCustomSize, 0);
        this.g = v1.getDimensionPixelSize(k.FloatingActionButton_borderWidth, 0);
        float v2 = v1.getDimension(k.FloatingActionButton_elevation, 0f);
        float v4 = v1.getDimension(k.FloatingActionButton_hoveredFocusedTranslationZ, 0f);
        float v3 = v1.getDimension(k.FloatingActionButton_pressedTranslationZ, 0f);
        this.a = v1.getBoolean(k.FloatingActionButton_useCompatPadding, false);
        this.l = v1.getDimensionPixelSize(k.FloatingActionButton_maxImageSize, 0);
        h v0 = h.a(arg8, v1, k.FloatingActionButton_showMotionSpec);
        h v8 = h.a(arg8, v1, k.FloatingActionButton_hideMotionSpec);
        v1.recycle();
        this.n = new android.support.v7.widget.n(((ImageView)this));
        this.n.a(arg9, arg10);
        this.o = new android.support.design.d.c(((android.support.design.d.b)this));
        this.getImpl().a(this.c, this.d, this.h, this.g);
        this.getImpl().a(v2);
        this.getImpl().b(v4);
        this.getImpl().c(v3);
        this.getImpl().a(this.l);
        this.getImpl().a(v0);
        this.getImpl().b(v8);
        this.setScaleType(ImageView$ScaleType.MATRIX);
    }

    @Deprecated public boolean a(Rect arg4) {
        if(t.A(((View)this))) {
            arg4.set(0, 0, this.getWidth(), this.getHeight());
            this.c(arg4);
            return 1;
        }

        return 0;
    }

    public void a(Animator$AnimatorListener arg2) {
        this.getImpl().a(arg2);
    }

    private int a(int arg4) {
        if(this.j != 0) {
            return this.j;
        }

        Resources v0 = this.getResources();
        if(arg4 != -1) {
            arg4 = arg4 != 1 ? d.design_fab_size_normal : d.design_fab_size_mini;
            return v0.getDimensionPixelSize(arg4);
        }

        return Math.max(v0.getConfiguration().screenWidthDp, v0.getConfiguration().screenHeightDp) < 470 ? this.a(1) : this.a(0);
    }

    private static int a(int arg2, int arg3) {
        int v0 = View$MeasureSpec.getMode(arg3);
        arg3 = View$MeasureSpec.getSize(arg3);
        if(v0 == -2147483648) {
            arg2 = Math.min(arg2, arg3);
        }
        else if(v0 != 0) {
            if(v0 == 1073741824) {
                arg2 = arg3;
            }
            else {
                throw new IllegalArgumentException();
            }
        }

        return arg2;
    }

    static int a(FloatingActionButton arg0) {
        return arg0.k;
    }

    private android.support.design.widget.g$d a(android.support.design.widget.FloatingActionButton$a arg2) {
        if(arg2 == null) {
            return null;
        }

        return new android.support.design.widget.g$d(arg2) {
            public void a() {
                this.a.a(this.b);
            }

            public void b() {
                this.a.b(this.b);
            }
        };
    }

    static void a(FloatingActionButton arg0, Drawable arg1) {
        super.setBackgroundDrawable(arg1);
    }

    void a(android.support.design.widget.FloatingActionButton$a arg2, boolean arg3) {
        this.getImpl().b(this.a(arg2), arg3);
    }

    public boolean a() {
        return this.o.a();
    }

    public void b(Animator$AnimatorListener arg2) {
        this.getImpl().b(arg2);
    }

    public boolean b() {
        return this.getImpl().r();
    }

    public void b(Rect arg4) {
        arg4.set(0, 0, this.getMeasuredWidth(), this.getMeasuredHeight());
        this.c(arg4);
    }

    void b(android.support.design.widget.FloatingActionButton$a arg2, boolean arg3) {
        this.getImpl().a(this.a(arg2), arg3);
    }

    public void c(Animator$AnimatorListener arg2) {
        this.getImpl().c(arg2);
    }

    private void c() {
        Drawable v0 = this.getDrawable();
        if(v0 == null) {
            return;
        }

        if(this.e == null) {
            android.support.v4.graphics.drawable.a.f(v0);
            return;
        }

        int v1 = this.e.getColorForState(this.getDrawableState(), 0);
        PorterDuff$Mode v2 = this.f;
        if(v2 == null) {
            v2 = PorterDuff$Mode.SRC_IN;
        }

        v0.mutate().setColorFilter(android.support.v7.widget.k.a(v1, v2));
    }

    private void c(Rect arg3) {
        arg3.left += this.b.left;
        arg3.top += this.b.top;
        arg3.right -= this.b.right;
        arg3.bottom -= this.b.bottom;
    }

    public void d(Animator$AnimatorListener arg2) {
        this.getImpl().d(arg2);
    }

    private g d() {
        if(Build$VERSION.SDK_INT >= 21) {
            return new android.support.design.widget.h(((w)this), new android.support.design.widget.FloatingActionButton$b(this));
        }

        return new g(((w)this), new android.support.design.widget.FloatingActionButton$b(this));
    }

    protected void drawableStateChanged() {
        super.drawableStateChanged();
        this.getImpl().a(this.getDrawableState());
    }

    public ColorStateList getBackgroundTintList() {
        return this.c;
    }

    public PorterDuff$Mode getBackgroundTintMode() {
        return this.d;
    }

    public float getCompatElevation() {
        return this.getImpl().a();
    }

    public float getCompatHoveredFocusedTranslationZ() {
        return this.getImpl().b();
    }

    public float getCompatPressedTranslationZ() {
        return this.getImpl().c();
    }

    public Drawable getContentBackground() {
        return this.getImpl().h();
    }

    public int getCustomSize() {
        return this.j;
    }

    public int getExpandedComponentIdHint() {
        return this.o.c();
    }

    public h getHideMotionSpec() {
        return this.getImpl().f();
    }

    private g getImpl() {
        if(this.p == null) {
            this.p = this.d();
        }

        return this.p;
    }

    @Deprecated public int getRippleColor() {
        int v0 = this.h != null ? this.h.getDefaultColor() : 0;
        return v0;
    }

    public ColorStateList getRippleColorStateList() {
        return this.h;
    }

    public h getShowMotionSpec() {
        return this.getImpl().e();
    }

    public int getSize() {
        return this.i;
    }

    int getSizeDimension() {
        return this.a(this.i);
    }

    public ColorStateList getSupportBackgroundTintList() {
        return this.getBackgroundTintList();
    }

    public PorterDuff$Mode getSupportBackgroundTintMode() {
        return this.getBackgroundTintMode();
    }

    public ColorStateList getSupportImageTintList() {
        return this.e;
    }

    public PorterDuff$Mode getSupportImageTintMode() {
        return this.f;
    }

    public boolean getUseCompatPadding() {
        return this.a;
    }

    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        this.getImpl().g();
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.getImpl().k();
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.getImpl().l();
    }

    protected void onMeasure(int arg3, int arg4) {
        int v0 = this.getSizeDimension();
        this.k = (v0 - this.l) / 2;
        this.getImpl().j();
        arg3 = Math.min(FloatingActionButton.a(v0, arg3), FloatingActionButton.a(v0, arg4));
        this.setMeasuredDimension(this.b.left + arg3 + this.b.right, arg3 + this.b.top + this.b.bottom);
    }

    protected void onRestoreInstanceState(Parcelable arg3) {
        if(!(arg3 instanceof ExtendableSavedState)) {
            super.onRestoreInstanceState(arg3);
            return;
        }

        super.onRestoreInstanceState(((ExtendableSavedState)arg3).getSuperState());
        this.o.a(((ExtendableSavedState)arg3).a.get("expandableWidgetHelper"));
    }

    protected Parcelable onSaveInstanceState() {
        ExtendableSavedState v1 = new ExtendableSavedState(super.onSaveInstanceState());
        v1.a.put("expandableWidgetHelper", this.o.b());
        return ((Parcelable)v1);
    }

    public boolean onTouchEvent(MotionEvent arg4) {
        if(arg4.getAction() == 0 && (this.a(this.m)) && !this.m.contains(((int)arg4.getX()), ((int)arg4.getY()))) {
            return 0;
        }

        return super.onTouchEvent(arg4);
    }

    public void setBackgroundColor(int arg2) {
        Log.i("FloatingActionButton", "Setting a custom background is not supported.");
    }

    public void setBackgroundDrawable(Drawable arg2) {
        Log.i("FloatingActionButton", "Setting a custom background is not supported.");
    }

    public void setBackgroundResource(int arg2) {
        Log.i("FloatingActionButton", "Setting a custom background is not supported.");
    }

    public void setBackgroundTintList(ColorStateList arg2) {
        if(this.c != arg2) {
            this.c = arg2;
            this.getImpl().a(arg2);
        }
    }

    public void setBackgroundTintMode(PorterDuff$Mode arg2) {
        if(this.d != arg2) {
            this.d = arg2;
            this.getImpl().a(arg2);
        }
    }

    public void setCompatElevation(float arg2) {
        this.getImpl().a(arg2);
    }

    public void setCompatElevationResource(int arg2) {
        this.setCompatElevation(this.getResources().getDimension(arg2));
    }

    public void setCompatHoveredFocusedTranslationZ(float arg2) {
        this.getImpl().b(arg2);
    }

    public void setCompatHoveredFocusedTranslationZResource(int arg2) {
        this.setCompatHoveredFocusedTranslationZ(this.getResources().getDimension(arg2));
    }

    public void setCompatPressedTranslationZ(float arg2) {
        this.getImpl().c(arg2);
    }

    public void setCompatPressedTranslationZResource(int arg2) {
        this.setCompatPressedTranslationZ(this.getResources().getDimension(arg2));
    }

    public void setCustomSize(int arg2) {
        if(arg2 >= 0) {
            this.j = arg2;
            return;
        }

        throw new IllegalArgumentException("Custom size must be non-negative");
    }

    public void setExpandedComponentIdHint(int arg2) {
        this.o.a(arg2);
    }

    public void setHideMotionSpec(h arg2) {
        this.getImpl().b(arg2);
    }

    public void setHideMotionSpecResource(int arg2) {
        this.setHideMotionSpec(h.a(this.getContext(), arg2));
    }

    public void setImageDrawable(Drawable arg1) {
        super.setImageDrawable(arg1);
        this.getImpl().d();
    }

    public void setImageResource(int arg2) {
        this.n.a(arg2);
    }

    public void setRippleColor(int arg1) {
        this.setRippleColor(ColorStateList.valueOf(arg1));
    }

    public void setRippleColor(ColorStateList arg2) {
        if(this.h != arg2) {
            this.h = arg2;
            this.getImpl().b(this.h);
        }
    }

    public void setShowMotionSpec(h arg2) {
        this.getImpl().a(arg2);
    }

    public void setShowMotionSpecResource(int arg2) {
        this.setShowMotionSpec(h.a(this.getContext(), arg2));
    }

    public void setSize(int arg2) {
        this.j = 0;
        if(arg2 != this.i) {
            this.i = arg2;
            this.requestLayout();
        }
    }

    public void setSupportBackgroundTintList(ColorStateList arg1) {
        this.setBackgroundTintList(arg1);
    }

    public void setSupportBackgroundTintMode(PorterDuff$Mode arg1) {
        this.setBackgroundTintMode(arg1);
    }

    public void setSupportImageTintList(ColorStateList arg2) {
        if(this.e != arg2) {
            this.e = arg2;
            this.c();
        }
    }

    public void setSupportImageTintMode(PorterDuff$Mode arg2) {
        if(this.f != arg2) {
            this.f = arg2;
            this.c();
        }
    }

    public void setUseCompatPadding(boolean arg2) {
        if(this.a != arg2) {
            this.a = arg2;
            this.getImpl().i();
        }
    }
}

