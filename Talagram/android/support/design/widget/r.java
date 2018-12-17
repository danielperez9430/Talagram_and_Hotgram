package android.support.design.widget;

import android.animation.Animator$AnimatorListener;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator$AnimatorUpdateListener;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff$Mode;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build$VERSION;
import android.support.design.a$j;
import android.support.design.a$k;
import android.support.v4.view.ViewPager$a;
import android.support.v4.view.ViewPager$e;
import android.support.v4.view.ViewPager;
import android.support.v4.view.q;
import android.support.v4.view.t;
import android.support.v7.widget.bm;
import android.text.Layout;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View$MeasureSpec;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.view.ViewGroup$MarginLayoutParams;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout$LayoutParams;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout$LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;

@a public class r extends HorizontalScrollView {
    class android.support.design.widget.r$a implements e {
        private boolean b;

        android.support.design.widget.r$a(r arg1) {
            this.a = arg1;
            super();
        }

        void a(boolean arg1) {
            this.b = arg1;
        }

        public void a(ViewPager arg1, q arg2, q arg3) {
            if(this.a.v == arg1) {
                this.a.a(arg3, this.b);
            }
        }
    }

    public interface b {
        void onTabReselected(f arg1);

        void onTabSelected(f arg1);

        void onTabUnselected(f arg1);
    }

    public interface c extends b {
    }

    class d extends DataSetObserver {
        d(r arg1) {
            this.a = arg1;
            super();
        }

        public void onChanged() {
            this.a.d();
        }

        public void onInvalidated() {
            this.a.d();
        }
    }

    class android.support.design.widget.r$e extends LinearLayout {
        int a;
        float b;
        private int d;
        private final Paint e;
        private final GradientDrawable f;
        private int g;
        private int h;
        private int i;
        private ValueAnimator j;

        android.support.design.widget.r$e(r arg1, Context arg2) {
            this.c = arg1;
            super(arg2);
            this.a = -1;
            this.g = -1;
            this.h = -1;
            this.i = -1;
            this.setWillNotDraw(false);
            this.e = new Paint();
            this.f = new GradientDrawable();
        }

        void a(int arg2) {
            if(this.e.getColor() != arg2) {
                this.e.setColor(arg2);
                t.d(((View)this));
            }
        }

        boolean a() {
            int v0 = this.getChildCount();
            int v2;
            for(v2 = 0; v2 < v0; ++v2) {
                if(this.getChildAt(v2).getWidth() <= 0) {
                    return 1;
                }
            }

            return 0;
        }

        void a(int arg2, float arg3) {
            if(this.j != null && (this.j.isRunning())) {
                this.j.cancel();
            }

            this.a = arg2;
            this.b = arg3;
            this.b();
        }

        private void a(h arg4, RectF arg5) {
            int v0 = h.a(arg4);
            int v2 = 24;
            if(v0 < this.c.b(v2)) {
                v0 = this.c.b(v2);
            }

            int v1 = (arg4.getLeft() + arg4.getRight()) / 2;
            v0 /= 2;
            arg5.set(((float)(v1 - v0)), 0f, ((float)(v1 + v0)), 0f);
        }

        void a(int arg2, int arg3) {
            if(arg2 != this.h || arg3 != this.i) {
                this.h = arg2;
                this.i = arg3;
                t.d(((View)this));
            }
        }

        void b(int arg2) {
            if(this.d != arg2) {
                this.d = arg2;
                t.d(((View)this));
            }
        }

        void b(int arg10, int arg11) {
            if(this.j != null && (this.j.isRunning())) {
                this.j.cancel();
            }

            View v0 = this.getChildAt(arg10);
            if(v0 == null) {
                this.b();
                return;
            }

            int v1 = v0.getLeft();
            int v2 = v0.getRight();
            if(!this.c.t && ((v0 instanceof h))) {
                this.a(((h)v0), r.a(this.c));
                v1 = ((int)r.a(this.c).left);
                v2 = ((int)r.a(this.c).right);
            }

            int v6 = v1;
            int v8 = v2;
            int v5 = this.h;
            int v7 = this.i;
            if(v5 != v6 || v7 != v8) {
                ValueAnimator v0_1 = new ValueAnimator();
                this.j = v0_1;
                v0_1.setInterpolator(android.support.design.a.a.b);
                v0_1.setDuration(((long)arg11));
                v0_1.setFloatValues(new float[]{0f, 1f});
                v0_1.addUpdateListener(new ValueAnimator$AnimatorUpdateListener(v5, v6, v7, v8) {
                    public void onAnimationUpdate(ValueAnimator arg5) {
                        float v5 = arg5.getAnimatedFraction();
                        this.e.a(android.support.design.a.a.a(this.a, this.b, v5), android.support.design.a.a.a(this.c, this.d, v5));
                    }
                });
                v0_1.addListener(new AnimatorListenerAdapter(arg10) {
                    public void onAnimationEnd(Animator arg2) {
                        this.b.a = this.a;
                        this.b.b = 0f;
                    }
                });
                v0_1.start();
            }
        }

        private void b() {
            int v2;
            int v1;
            View v0 = this.getChildAt(this.a);
            if(v0 == null || v0.getWidth() <= 0) {
                v1 = -1;
                v2 = -1;
            }
            else {
                v1 = v0.getLeft();
                v2 = v0.getRight();
                if(!this.c.t && ((v0 instanceof h))) {
                    this.a(((h)v0), r.a(this.c));
                    v1 = ((int)r.a(this.c).left);
                    v2 = ((int)r.a(this.c).right);
                }

                if(this.b <= 0f) {
                    goto label_73;
                }

                if(this.a >= this.getChildCount() - 1) {
                    goto label_73;
                }

                v0 = this.getChildAt(this.a + 1);
                int v3 = v0.getLeft();
                int v4 = v0.getRight();
                if(!this.c.t && ((v0 instanceof h))) {
                    this.a(((h)v0), r.a(this.c));
                    v3 = ((int)r.a(this.c).left);
                    v4 = ((int)r.a(this.c).right);
                }

                v1 = ((int)(this.b * (((float)v3)) + (1f - this.b) * (((float)v1))));
                v2 = ((int)(this.b * (((float)v4)) + (1f - this.b) * (((float)v2))));
            }

        label_73:
            this.a(v1, v2);
        }

        public void draw(Canvas arg6) {
            GradientDrawable v2_1;
            Drawable v2;
            int v1 = 0;
            int v0 = this.c.i != null ? this.c.i.getIntrinsicHeight() : 0;
            if(this.d >= 0) {
                v0 = this.d;
            }

            switch(this.c.q) {
                case 0: {
                    goto label_24;
                }
                case 1: {
                    goto label_17;
                }
                case 2: {
                    goto label_27;
                }
                case 3: {
                    goto label_26;
                }
            }

            v0 = 0;
            goto label_27;
        label_17:
            v1 = (this.getHeight() - v0) / 2;
            v0 = (this.getHeight() + v0) / 2;
            goto label_27;
        label_24:
            v1 = this.getHeight() - v0;
        label_26:
            v0 = this.getHeight();
        label_27:
            if(this.h >= 0 && this.i > this.h) {
                if(this.c.i != null) {
                    v2 = this.c.i;
                }
                else {
                    v2_1 = this.f;
                }

                v2 = android.support.v4.graphics.drawable.a.g(((Drawable)v2_1));
                v2.setBounds(this.h, v1, this.i, v0);
                if(this.e != null) {
                    if(Build$VERSION.SDK_INT == 21) {
                        v2.setColorFilter(this.e.getColor(), PorterDuff$Mode.SRC_IN);
                    }
                    else {
                        android.support.v4.graphics.drawable.a.a(v2, this.e.getColor());
                    }
                }

                v2.draw(arg6);
            }

            super.draw(arg6);
        }

        protected void onLayout(boolean arg1, int arg2, int arg3, int arg4, int arg5) {
            super.onLayout(arg1, arg2, arg3, arg4, arg5);
            if(this.j == null || !this.j.isRunning()) {
                this.b();
            }
            else {
                this.j.cancel();
                this.b(this.a, Math.round((1f - this.j.getAnimatedFraction()) * (((float)this.j.getDuration()))));
            }
        }

        protected void onMeasure(int arg9, int arg10) {
            super.onMeasure(arg9, arg10);
            if(View$MeasureSpec.getMode(arg9) != 1073741824) {
                return;
            }

            int v1 = 1;
            if(this.c.r == 1 && this.c.o == 1) {
                int v0 = this.getChildCount();
                int v2 = 0;
                int v3 = 0;
                int v4 = 0;
                while(v3 < v0) {
                    View v5 = this.getChildAt(v3);
                    if(v5.getVisibility() == 0) {
                        v4 = Math.max(v4, v5.getMeasuredWidth());
                    }

                    ++v3;
                }

                if(v4 <= 0) {
                    return;
                }

                if(v4 * v0 <= this.getMeasuredWidth() - this.c.b(16) * 2) {
                    v3 = 0;
                    goto label_35;
                }
                else {
                    this.c.o = 0;
                    this.c.a(false);
                    goto label_54;
                label_35:
                    while(v2 < v0) {
                        ViewGroup$LayoutParams v5_1 = this.getChildAt(v2).getLayoutParams();
                        if(((LinearLayout$LayoutParams)v5_1).width != v4 || ((LinearLayout$LayoutParams)v5_1).weight != 0f) {
                            ((LinearLayout$LayoutParams)v5_1).width = v4;
                            ((LinearLayout$LayoutParams)v5_1).weight = 0f;
                            v3 = 1;
                        }

                        ++v2;
                    }

                    v1 = v3;
                }

            label_54:
                if(v1 == 0) {
                    return;
                }

                super.onMeasure(arg9, arg10);
            }
        }

        public void onRtlPropertiesChanged(int arg3) {
            super.onRtlPropertiesChanged(arg3);
            if(Build$VERSION.SDK_INT < 23 && this.g != arg3) {
                this.requestLayout();
                this.g = arg3;
            }
        }
    }

    public class f {
        public r a;
        public h b;
        private Object c;
        private Drawable d;
        private CharSequence e;
        private CharSequence f;
        private int g;
        private View h;

        public f() {
            super();
            this.g = -1;
        }

        public f a(CharSequence arg2) {
            if((TextUtils.isEmpty(this.f)) && !TextUtils.isEmpty(arg2)) {
                this.b.setContentDescription(arg2);
            }

            this.e = arg2;
            this.h();
            return this;
        }

        public f a(Drawable arg1) {
            this.d = arg1;
            this.h();
            return this;
        }

        public f a(int arg4) {
            return this.a(LayoutInflater.from(this.b.getContext()).inflate(arg4, this.b, false));
        }

        static CharSequence a(f arg0) {
            return arg0.f;
        }

        public f a(View arg1) {
            this.h = arg1;
            this.h();
            return this;
        }

        public f a(Object arg1) {
            this.c = arg1;
            return this;
        }

        public Object a() {
            return this.c;
        }

        public f b(CharSequence arg1) {
            this.f = arg1;
            this.h();
            return this;
        }

        void b(int arg1) {
            this.g = arg1;
        }

        static CharSequence b(f arg0) {
            return arg0.e;
        }

        public View b() {
            return this.h;
        }

        public Drawable c() {
            return this.d;
        }

        public int d() {
            return this.g;
        }

        public CharSequence e() {
            return this.e;
        }

        public void f() {
            if(this.a != null) {
                this.a.c(this);
                return;
            }

            throw new IllegalArgumentException("Tab not attached to a TabLayout");
        }

        public boolean g() {
            if(this.a != null) {
                boolean v0 = this.a.getSelectedTabPosition() == this.g ? true : false;
                return v0;
            }

            throw new IllegalArgumentException("Tab not attached to a TabLayout");
        }

        void h() {
            if(this.b != null) {
                this.b.b();
            }
        }

        void i() {
            this.a = null;
            this.b = null;
            this.c = null;
            this.d = null;
            this.e = null;
            this.f = null;
            this.g = -1;
            this.h = null;
        }
    }

    public class g implements android.support.v4.view.ViewPager$f {
        private final WeakReference a;
        private int b;
        private int c;

        public g(r arg2) {
            super();
            this.a = new WeakReference(arg2);
        }

        void a() {
            this.c = 0;
            this.b = 0;
        }

        public void onPageScrollStateChanged(int arg2) {
            this.b = this.c;
            this.c = arg2;
        }

        public void onPageScrolled(int arg6, float arg7, int arg8) {
            Object v8 = this.a.get();
            if(v8 != null) {
                boolean v1 = false;
                int v2 = 2;
                boolean v0 = this.c != v2 || this.b == 1 ? true : false;
                if(this.c != v2 || this.b != 0) {
                    v1 = true;
                }

                ((r)v8).a(arg6, arg7, v0, v1);
            }
        }

        public void onPageSelected(int arg4) {
            boolean v1;
            Object v0 = this.a.get();
            if(v0 != null && ((r)v0).getSelectedTabPosition() != arg4 && arg4 < ((r)v0).getTabCount()) {
                if(this.c != 0) {
                    if(this.c == 2 && this.b == 0) {
                        goto label_17;
                    }

                    v1 = false;
                }
                else {
                label_17:
                    v1 = true;
                }

                ((r)v0).b(((r)v0).a(arg4), v1);
            }
        }
    }

    class h extends LinearLayout {
        private f b;
        private TextView c;
        private ImageView d;
        private View e;
        private TextView f;
        private ImageView g;
        private Drawable h;
        private int i;

        public h(r arg4, Context arg5) {
            this.a = arg4;
            super(arg5);
            this.i = 2;
            this.a(arg5);
            t.a(((View)this), arg4.a, arg4.b, arg4.c, arg4.d);
            this.setGravity(17);
            this.setOrientation(arg4.s ^ 1);
            this.setClickable(true);
            t.a(((View)this), android.support.v4.view.r.a(this.getContext(), 1002));
        }

        void a() {
            this.a(null);
            this.setSelected(false);
        }

        void a(f arg2) {
            if(arg2 != this.b) {
                this.b = arg2;
                this.b();
            }
        }

        static void a(h arg0, Canvas arg1) {
            arg0.a(arg1);
        }

        static void a(h arg0, Context arg1) {
            arg0.a(arg1);
        }

        static int a(h arg0) {
            return arg0.d();
        }

        private void a(Context arg7) {
            LayerDrawable v7_3;
            Drawable v0_1;
            Drawable v7_1;
            Drawable v1 = null;
            if(this.a.m != 0) {
                this.h = android.support.v7.c.a.a.b(arg7, this.a.m);
                if(this.h != null && (this.h.isStateful())) {
                    this.h.setState(this.getDrawableState());
                }
            }
            else {
                this.h = v1;
            }

            GradientDrawable v7 = new GradientDrawable();
            v7.setColor(0);
            if(this.a.h != null) {
                GradientDrawable v0 = new GradientDrawable();
                v0.setCornerRadius(0.00001f);
                v0.setColor(-1);
                ColorStateList v3 = android.support.design.f.a.a(this.a.h);
                if(Build$VERSION.SDK_INT >= 21) {
                    if(this.a.u) {
                        v7_1 = v1;
                    }

                    if(this.a.u) {
                        v0_1 = v1;
                    }

                    RippleDrawable v7_2 = new RippleDrawable(v3, v7_1, ((Drawable)v0));
                }
                else {
                    v0_1 = android.support.v4.graphics.drawable.a.g(((Drawable)v0));
                    android.support.v4.graphics.drawable.a.a(v0_1, v3);
                    v7_3 = new LayerDrawable(new Drawable[]{v7, v0_1});
                }
            }

            t.a(((View)this), ((Drawable)v7_3));
            this.a.invalidate();
        }

        private float a(Layout arg1, int arg2, float arg3) {
            return arg1.getLineWidth(arg2) * (arg3 / arg1.getPaint().getTextSize());
        }

        private void a(Canvas arg6) {
            if(this.h != null) {
                this.h.setBounds(this.getLeft(), this.getTop(), this.getRight(), this.getBottom());
                this.h.draw(arg6);
            }
        }

        private void a(TextView arg6, ImageView arg7) {
            Drawable v1 = null;
            Drawable v0 = this.b == null || this.b.c() == null ? v1 : android.support.v4.graphics.drawable.a.g(this.b.c()).mutate();
            CharSequence v2 = this.b != null ? this.b.e() : ((CharSequence)v1);
            int v3 = 8;
            if(arg7 != null) {
                if(v0 != null) {
                    arg7.setImageDrawable(v0);
                    arg7.setVisibility(0);
                    this.setVisibility(0);
                }
                else {
                    arg7.setVisibility(v3);
                    arg7.setImageDrawable(v1);
                }
            }

            int v0_1 = TextUtils.isEmpty(v2) ^ 1;
            if(arg6 != null) {
                if(v0_1 != 0) {
                    arg6.setText(v2);
                    arg6.setVisibility(0);
                    this.setVisibility(0);
                }
                else {
                    arg6.setVisibility(v3);
                    arg6.setText(((CharSequence)v1));
                }
            }

            if(arg7 != null) {
                ViewGroup$LayoutParams v6 = arg7.getLayoutParams();
                int v2_1 = v0_1 == 0 || arg7.getVisibility() != 0 ? 0 : this.a.b(v3);
                if(this.a.s) {
                    if(v2_1 != android.support.v4.view.g.b(((ViewGroup$MarginLayoutParams)v6))) {
                        android.support.v4.view.g.a(((ViewGroup$MarginLayoutParams)v6), v2_1);
                        ((ViewGroup$MarginLayoutParams)v6).bottomMargin = 0;
                    }
                    else {
                        goto label_62;
                    }
                }
                else if(v2_1 != ((ViewGroup$MarginLayoutParams)v6).bottomMargin) {
                    ((ViewGroup$MarginLayoutParams)v6).bottomMargin = v2_1;
                    android.support.v4.view.g.a(((ViewGroup$MarginLayoutParams)v6), 0);
                }
                else {
                    goto label_62;
                }

                arg7.setLayoutParams(v6);
                arg7.requestLayout();
            }

        label_62:
            CharSequence v6_1 = this.b != null ? f.a(this.b) : ((CharSequence)v1);
            if(v0_1 != 0) {
                v6_1 = ((CharSequence)v1);
            }

            bm.a(((View)this), v6_1);
        }

        final void b() {
            ImageView v2_1;
            TextView v1_2;
            f v0 = this.b;
            Drawable v1 = null;
            View v2 = v0 != null ? v0.b() : ((View)v1);
            if(v2 != null) {
                ViewParent v3 = v2.getParent();
                if((((h)v3)) != this) {
                    if(v3 != null) {
                        ((ViewGroup)v3).removeView(v2);
                    }

                    this.addView(v2);
                }

                this.e = v2;
                int v4 = 8;
                if(this.c != null) {
                    this.c.setVisibility(v4);
                }

                if(this.d != null) {
                    this.d.setVisibility(v4);
                    this.d.setImageDrawable(v1);
                }

                this.f = v2.findViewById(16908308);
                if(this.f != null) {
                    this.i = android.support.v4.widget.q.a(this.f);
                }

                this.g = v2.findViewById(16908294);
            }
            else {
                if(this.e != null) {
                    this.removeView(this.e);
                    this.e = ((View)v1);
                }

                this.f = ((TextView)v1);
                this.g = ((ImageView)v1);
            }

            boolean v3_1 = false;
            if(this.e == null) {
                if(this.d == null) {
                    v2 = LayoutInflater.from(this.getContext()).inflate(android.support.design.a$h.design_layout_tab_icon, ((ViewGroup)this), false);
                    this.addView(v2, 0);
                    this.d = ((ImageView)v2);
                }

                if(v0 != null && v0.c() != null) {
                    v1 = android.support.v4.graphics.drawable.a.g(v0.c()).mutate();
                }

                if(v1 != null) {
                    android.support.v4.graphics.drawable.a.a(v1, this.a.g);
                    if(this.a.j != null) {
                        android.support.v4.graphics.drawable.a.a(v1, this.a.j);
                    }
                }

                if(this.c == null) {
                    View v1_1 = LayoutInflater.from(this.getContext()).inflate(android.support.design.a$h.design_layout_tab_text, ((ViewGroup)this), false);
                    this.addView(v1_1);
                    this.c = ((TextView)v1_1);
                    this.i = android.support.v4.widget.q.a(this.c);
                }

                android.support.v4.widget.q.a(this.c, this.a.e);
                if(this.a.f != null) {
                    this.c.setTextColor(this.a.f);
                }

                v1_2 = this.c;
                v2_1 = this.d;
                goto label_101;
            }
            else {
                if(this.f == null && this.g == null) {
                    goto label_102;
                }

                v1_2 = this.f;
                v2_1 = this.g;
            label_101:
                this.a(v1_2, v2_1);
            }

        label_102:
            if(v0 != null && !TextUtils.isEmpty(f.a(v0))) {
                this.setContentDescription(f.a(v0));
            }

            if(v0 != null && (v0.g())) {
                v3_1 = true;
            }

            this.setSelected(v3_1);
        }

        final void c() {
            ImageView v1;
            TextView v0;
            this.setOrientation(this.a.s ^ 1);
            if(this.f != null || this.g != null) {
                v0 = this.f;
                v1 = this.g;
            }
            else {
                v0 = this.c;
                v1 = this.d;
            }

            this.a(v0, v1);
        }

        private int d() {
            View[] v0 = new View[3];
            int v2 = 0;
            v0[0] = this.c;
            v0[1] = this.d;
            v0[2] = this.e;
            int v1 = v0.length;
            int v4 = 0;
            int v5 = 0;
            int v6 = 0;
            while(v2 < v1) {
                View v7 = v0[v2];
                if(v7 != null && v7.getVisibility() == 0) {
                    v5 = v6 != 0 ? Math.min(v5, v7.getLeft()) : v7.getLeft();
                    v4 = v6 != 0 ? Math.max(v4, v7.getRight()) : v7.getRight();
                    v6 = 1;
                }

                ++v2;
            }

            return v4 - v5;
        }

        protected void drawableStateChanged() {
            super.drawableStateChanged();
            int[] v0 = this.getDrawableState();
            int v2 = 0;
            if(this.h != null && (this.h.isStateful())) {
                v2 = 0 | this.h.setState(v0);
            }

            if(v2 != 0) {
                this.invalidate();
                this.a.invalidate();
            }
        }

        public void onInitializeAccessibilityEvent(AccessibilityEvent arg2) {
            super.onInitializeAccessibilityEvent(arg2);
            arg2.setClassName(android.support.v7.app.a$c.class.getName());
        }

        @TargetApi(value=14) public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo arg2) {
            super.onInitializeAccessibilityNodeInfo(arg2);
            arg2.setClassName(android.support.v7.app.a$c.class.getName());
        }

        public void onMeasure(int arg8, int arg9) {
            int v0 = View$MeasureSpec.getSize(arg8);
            int v1 = View$MeasureSpec.getMode(arg8);
            int v2 = this.a.getTabMaxWidth();
            if(v2 > 0 && (v1 == 0 || v0 > v2)) {
                arg8 = View$MeasureSpec.makeMeasureSpec(this.a.n, -2147483648);
            }

            super.onMeasure(arg8, arg9);
            if(this.c != null) {
                float v0_1 = this.a.k;
                v1 = this.i;
                int v3 = 1;
                if(this.d != null && this.d.getVisibility() == 0) {
                    v1 = 1;
                }
                else if(this.c != null && this.c.getLineCount() > 1) {
                    v0_1 = this.a.l;
                }

                float v2_1 = this.c.getTextSize();
                int v4 = this.c.getLineCount();
                int v5 = android.support.v4.widget.q.a(this.c);
                if(v0_1 == v2_1) {
                    if(v5 < 0) {
                    }
                    else if(v1 != v5) {
                        goto label_41;
                    }

                    return;
                }

            label_41:
                if(this.a.r == 1 && v0_1 > v2_1 && v4 == 1) {
                    Layout v2_2 = this.c.getLayout();
                    if(v2_2 != null && this.a(v2_2, 0, v0_1) <= (((float)(this.getMeasuredWidth() - this.getPaddingLeft() - this.getPaddingRight())))) {
                        goto label_59;
                    }

                    v3 = 0;
                }

            label_59:
                if(v3 == 0) {
                    return;
                }

                this.c.setTextSize(0, v0_1);
                this.c.setMaxLines(v1);
                super.onMeasure(arg8, arg9);
            }
        }

        public boolean performClick() {
            boolean v0 = super.performClick();
            if(this.b != null) {
                if(!v0) {
                    this.playSoundEffect(0);
                }

                this.b.f();
                v0 = true;
            }

            return v0;
        }

        public void setSelected(boolean arg3) {
            int v0 = this.isSelected() != arg3 ? 1 : 0;
            super.setSelected(arg3);
            if(v0 != 0 && (arg3) && Build$VERSION.SDK_INT < 16) {
                this.sendAccessibilityEvent(4);
            }

            if(this.c != null) {
                this.c.setSelected(arg3);
            }

            if(this.d != null) {
                this.d.setSelected(arg3);
            }

            if(this.e != null) {
                this.e.setSelected(arg3);
            }
        }
    }

    public class i implements c {
        private final ViewPager a;

        public i(ViewPager arg1) {
            super();
            this.a = arg1;
        }

        public void onTabReselected(f arg1) {
        }

        public void onTabSelected(f arg2) {
            this.a.setCurrentItem(arg2.d());
        }

        public void onTabUnselected(f arg1) {
        }
    }

    private final android.support.design.widget.r$e A;
    private final int B;
    private final int C;
    private final int D;
    private int E;
    private b F;
    private final ArrayList G;
    private b H;
    private ValueAnimator I;
    private q J;
    private DataSetObserver K;
    private g L;
    private android.support.design.widget.r$a M;
    private boolean N;
    private final android.support.v4.f.k$a O;
    int a;
    int b;
    int c;
    int d;
    int e;
    ColorStateList f;
    ColorStateList g;
    ColorStateList h;
    Drawable i;
    PorterDuff$Mode j;
    float k;
    float l;
    final int m;
    int n;
    int o;
    int p;
    int q;
    int r;
    boolean s;
    boolean t;
    boolean u;
    ViewPager v;
    private static final android.support.v4.f.k$a w;
    private final ArrayList x;
    private f y;
    private final RectF z;

    static {
        r.w = new android.support.v4.f.k$c(16);
    }

    public r(Context arg2) {
        this(arg2, null);
    }

    public r(Context arg2, AttributeSet arg3) {
        this(arg2, arg3, android.support.design.a$b.tabStyle);
    }

    public r(Context arg12, AttributeSet arg13, int arg14) {
        super(arg12, arg13, arg14);
        this.x = new ArrayList();
        this.z = new RectF();
        this.n = 2147483647;
        this.G = new ArrayList();
        this.O = new android.support.v4.f.k$b(12);
        this.setHorizontalScrollBarEnabled(false);
        this.A = new android.support.design.widget.r$e(this, arg12);
        int v3 = -1;
        super.addView(this.A, 0, new FrameLayout$LayoutParams(-2, v3));
        TypedArray v13 = android.support.design.internal.b.a(arg12, arg13, k.TabLayout, arg14, j.Widget_Design_TabLayout, new int[]{k.TabLayout_tabTextAppearance});
        this.A.b(v13.getDimensionPixelSize(k.TabLayout_tabIndicatorHeight, v3));
        this.A.a(v13.getColor(k.TabLayout_tabIndicatorColor, 0));
        this.setSelectedTabIndicator(android.support.design.e.a.b(arg12, v13, k.TabLayout_tabIndicator));
        this.setSelectedTabIndicatorGravity(v13.getInt(k.TabLayout_tabIndicatorGravity, 0));
        this.setTabIndicatorFullWidth(v13.getBoolean(k.TabLayout_tabIndicatorFullWidth, true));
        arg14 = v13.getDimensionPixelSize(k.TabLayout_tabPadding, 0);
        this.d = arg14;
        this.c = arg14;
        this.b = arg14;
        this.a = arg14;
        this.a = v13.getDimensionPixelSize(k.TabLayout_tabPaddingStart, this.a);
        this.b = v13.getDimensionPixelSize(k.TabLayout_tabPaddingTop, this.b);
        this.c = v13.getDimensionPixelSize(k.TabLayout_tabPaddingEnd, this.c);
        this.d = v13.getDimensionPixelSize(k.TabLayout_tabPaddingBottom, this.d);
        this.e = v13.getResourceId(k.TabLayout_tabTextAppearance, j.TextAppearance_Design_Tab);
        TypedArray v14 = arg12.obtainStyledAttributes(this.e, android.support.v7.a.a$j.TextAppearance);
        try {
            this.k = ((float)v14.getDimensionPixelSize(android.support.v7.a.a$j.TextAppearance_android_textSize, 0));
            this.f = android.support.design.e.a.a(arg12, v14, android.support.v7.a.a$j.TextAppearance_android_textColor);
        }
        catch(Throwable v12) {
            v14.recycle();
            throw v12;
        }

        v14.recycle();
        if(v13.hasValue(k.TabLayout_tabTextColor)) {
            this.f = android.support.design.e.a.a(arg12, v13, k.TabLayout_tabTextColor);
        }

        if(v13.hasValue(k.TabLayout_tabSelectedTextColor)) {
            this.f = r.a(this.f.getDefaultColor(), v13.getColor(k.TabLayout_tabSelectedTextColor, 0));
        }

        this.g = android.support.design.e.a.a(arg12, v13, k.TabLayout_tabIconTint);
        this.j = android.support.design.internal.c.a(v13.getInt(k.TabLayout_tabIconTintMode, v3), null);
        this.h = android.support.design.e.a.a(arg12, v13, k.TabLayout_tabRippleColor);
        this.p = v13.getInt(k.TabLayout_tabIndicatorAnimationDuration, 300);
        this.B = v13.getDimensionPixelSize(k.TabLayout_tabMinWidth, v3);
        this.C = v13.getDimensionPixelSize(k.TabLayout_tabMaxWidth, v3);
        this.m = v13.getResourceId(k.TabLayout_tabBackground, 0);
        this.E = v13.getDimensionPixelSize(k.TabLayout_tabContentStart, 0);
        this.r = v13.getInt(k.TabLayout_tabMode, 1);
        this.o = v13.getInt(k.TabLayout_tabGravity, 0);
        this.s = v13.getBoolean(k.TabLayout_tabInlineLabel, false);
        this.u = v13.getBoolean(k.TabLayout_tabUnboundedRipple, false);
        v13.recycle();
        Resources v12_1 = this.getResources();
        this.l = ((float)v12_1.getDimensionPixelSize(android.support.design.a$d.design_tab_text_size_2line));
        this.D = v12_1.getDimensionPixelSize(android.support.design.a$d.design_tab_scrollable_min_width);
        this.h();
    }

    private static ColorStateList a(int arg4, int arg5) {
        int[][] v1 = new int[2][];
        int[] v0 = new int[2];
        v1[0] = r.SELECTED_STATE_SET;
        v0[0] = arg5;
        v1[1] = r.EMPTY_STATE_SET;
        v0[1] = arg4;
        return new ColorStateList(v1, v0);
    }

    private int a(int arg4, float arg5) {
        int v1 = 0;
        if(this.r == 0) {
            View v0 = this.A.getChildAt(arg4);
            ++arg4;
            View v4 = arg4 < this.A.getChildCount() ? this.A.getChildAt(arg4) : null;
            int v2 = v0 != null ? v0.getWidth() : 0;
            if(v4 != null) {
                v1 = v4.getWidth();
            }

            arg4 = v0.getLeft() + v2 / 2 - this.getWidth() / 2;
            int v5 = ((int)((((float)(v2 + v1))) * 0.5f * arg5));
            if(t.f(((View)this)) == 0) {
                arg4 += v5;
            }
            else {
                arg4 -= v5;
            }

            return arg4;
        }

        return 0;
    }

    static RectF a(r arg0) {
        return arg0.z;
    }

    private void a(android.support.design.widget.q arg3) {
        f v0 = this.a();
        if(arg3.a != null) {
            v0.a(arg3.a);
        }

        if(arg3.b != null) {
            v0.a(arg3.b);
        }

        if(arg3.c != 0) {
            v0.a(arg3.c);
        }

        if(!TextUtils.isEmpty(arg3.getContentDescription())) {
            v0.b(arg3.getContentDescription());
        }

        this.a(v0);
    }

    public f a() {
        f v0 = this.b();
        v0.a = this;
        v0.b = this.d(v0);
        return v0;
    }

    public void a(f arg2) {
        this.a(arg2, this.x.isEmpty());
    }

    private void a(f arg2, int arg3) {
        arg2.b(arg3);
        this.x.add(arg3, arg2);
        int v2 = this.x.size();
        while(true) {
            ++arg3;
            if(arg3 >= v2) {
                return;
            }

            this.x.get(arg3).b(arg3);
        }
    }

    private void a(ViewPager arg3, boolean arg4, boolean arg5) {
        if(this.v != null) {
            if(this.L != null) {
                this.v.removeOnPageChangeListener(this.L);
            }

            if(this.M == null) {
                goto label_12;
            }

            this.v.removeOnAdapterChangeListener(this.M);
        }

    label_12:
        b v1 = null;
        if(this.H != null) {
            this.b(this.H);
            this.H = v1;
        }

        if(arg3 != null) {
            this.v = arg3;
            if(this.L == null) {
                this.L = new g(this);
            }

            this.L.a();
            arg3.addOnPageChangeListener(this.L);
            this.H = new i(arg3);
            this.a(this.H);
            q v0 = arg3.getAdapter();
            if(v0 != null) {
                this.a(v0, arg4);
            }

            if(this.M == null) {
                this.M = new android.support.design.widget.r$a(this);
            }

            this.M.a(arg4);
            arg3.addOnAdapterChangeListener(this.M);
            this.a(arg3.getCurrentItem(), 0f, true);
        }
        else {
            this.v = ((ViewPager)v1);
            this.a(((q)v1), false);
        }

        this.N = arg5;
    }

    public void a(b arg2) {
        if(!this.G.contains(arg2)) {
            this.G.add(arg2);
        }
    }

    void a(q arg3, boolean arg4) {
        if(this.J != null && this.K != null) {
            this.J.unregisterDataSetObserver(this.K);
        }

        this.J = arg3;
        if((arg4) && arg3 != null) {
            if(this.K == null) {
                this.K = new d(this);
            }

            arg3.registerDataSetObserver(this.K);
        }

        this.d();
    }

    public void a(int arg2, float arg3, boolean arg4) {
        this.a(arg2, arg3, arg4, true);
    }

    private void a(View arg2) {
        if((arg2 instanceof android.support.design.widget.q)) {
            this.a(((android.support.design.widget.q)arg2));
            return;
        }

        throw new IllegalArgumentException("Only TabItem instances can be added to TabLayout");
    }

    private void a(LinearLayout$LayoutParams arg3) {
        float v0;
        if(this.r != 1 || this.o != 0) {
            arg3.width = -2;
            v0 = 0f;
        }
        else {
            arg3.width = 0;
            v0 = 1f;
        }

        arg3.weight = v0;
    }

    void a(boolean arg4) {
        int v0;
        for(v0 = 0; v0 < this.A.getChildCount(); ++v0) {
            View v1 = this.A.getChildAt(v0);
            v1.setMinimumWidth(this.getTabMinWidth());
            this.a(v1.getLayoutParams());
            if(arg4) {
                v1.requestLayout();
            }
        }
    }

    public f a(int arg2) {
        f v2_1;
        if(arg2 < 0 || arg2 >= this.getTabCount()) {
            v2_1 = null;
        }
        else {
            Object v2 = this.x.get(arg2);
        }

        return v2_1;
    }

    void a(int arg3, float arg4, boolean arg5, boolean arg6) {
        int v0 = Math.round((((float)arg3)) + arg4);
        if(v0 >= 0) {
            if(v0 >= this.A.getChildCount()) {
            }
            else {
                if(arg6) {
                    this.A.a(arg3, arg4);
                }

                if(this.I != null && (this.I.isRunning())) {
                    this.I.cancel();
                }

                this.scrollTo(this.a(arg3, arg4), 0);
                if(!arg5) {
                    return;
                }

                this.setSelectedTabView(v0);
            }
        }
    }

    public void a(f arg2, boolean arg3) {
        this.a(arg2, this.x.size(), arg3);
    }

    public void a(f arg2, int arg3, boolean arg4) {
        if(arg2.a == this) {
            this.a(arg2, arg3);
            this.e(arg2);
            if(arg4) {
                arg2.f();
            }

            return;
        }

        throw new IllegalArgumentException("Tab belongs to a different TabLayout.");
    }

    public void a(ViewPager arg2, boolean arg3) {
        this.a(arg2, arg3, false);
    }

    public void addView(View arg1) {
        this.a(arg1);
    }

    public void addView(View arg1, int arg2) {
        this.a(arg1);
    }

    public void addView(View arg1, int arg2, ViewGroup$LayoutParams arg3) {
        this.a(arg1);
    }

    public void addView(View arg1, ViewGroup$LayoutParams arg2) {
        this.a(arg1);
    }

    public void b(b arg2) {
        this.G.remove(arg2);
    }

    protected f b() {
        f v0_1;
        Object v0 = r.w.a();
        if(v0 == null) {
            v0_1 = new f();
        }

        return v0_1;
    }

    int b(int arg2) {
        return Math.round(this.getResources().getDisplayMetrics().density * (((float)arg2)));
    }

    void b(f arg5, boolean arg6) {
        f v0 = this.y;
        if(v0 != arg5) {
            int v1 = -1;
            int v2 = arg5 != null ? arg5.d() : -1;
            if(arg6) {
                if(v0 != null && v0.d() != v1) {
                    goto label_21;
                }
                else if(v2 != v1) {
                    this.a(v2, 0f, true);
                }
                else {
                label_21:
                    this.d(v2);
                }

                if(v2 == v1) {
                    goto label_24;
                }

                this.setSelectedTabView(v2);
            }

        label_24:
            this.y = arg5;
            if(v0 != null) {
                this.g(v0);
            }

            if(arg5 == null) {
                return;
            }

            this.f(arg5);
        }
        else if(v0 != null) {
            this.h(arg5);
            this.d(arg5.d());
        }
    }

    protected boolean b(f arg2) {
        return r.w.a(arg2);
    }

    private void c(int arg3) {
        View v0 = this.A.getChildAt(arg3);
        this.A.removeViewAt(arg3);
        if(v0 != null) {
            ((h)v0).a();
            this.O.a(v0);
        }

        this.requestLayout();
    }

    public void c() {
        int v0;
        for(v0 = this.A.getChildCount() - 1; v0 >= 0; --v0) {
            this.c(v0);
        }

        Iterator v0_1 = this.x.iterator();
        while(v0_1.hasNext()) {
            Object v1 = v0_1.next();
            v0_1.remove();
            ((f)v1).i();
            this.b(((f)v1));
        }

        this.y = null;
    }

    void c(f arg2) {
        this.b(arg2, true);
    }

    private h d(f arg3) {
        Object v0 = this.O != null ? this.O.a() : null;
        if(v0 == null) {
            h v0_1 = new h(this, this.getContext());
        }

        ((h)v0).a(arg3);
        ((h)v0).setFocusable(true);
        ((h)v0).setMinimumWidth(this.getTabMinWidth());
        CharSequence v3 = TextUtils.isEmpty(f.a(arg3)) ? f.b(arg3) : f.a(arg3);
        ((h)v0).setContentDescription(v3);
        return ((h)v0);
    }

    private void d(int arg7) {
        if(arg7 == -1) {
            return;
        }

        if(this.getWindowToken() != null && (t.A(((View)this)))) {
            if(this.A.a()) {
            }
            else {
                int v0 = this.getScrollX();
                int v2 = this.a(arg7, 0f);
                if(v0 != v2) {
                    this.g();
                    this.I.setIntValues(new int[]{v0, v2});
                    this.I.start();
                }

                this.A.b(arg7, this.p);
                return;
            }
        }

        this.a(arg7, 0f, true);
    }

    void d() {
        this.c();
        if(this.J != null) {
            int v0 = this.J.getCount();
            int v2;
            for(v2 = 0; v2 < v0; ++v2) {
                this.a(this.a().a(this.J.getPageTitle(v2)), false);
            }

            if(this.v != null && v0 > 0) {
                v0 = this.v.getCurrentItem();
                if(v0 != this.getSelectedTabPosition() && v0 < this.getTabCount()) {
                    this.c(this.a(v0));
                }
            }
        }
    }

    private void e() {
        int v0 = this.x.size();
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            this.x.get(v1).h();
        }
    }

    private void e(f arg4) {
        this.A.addView(arg4.b, arg4.d(), this.f());
    }

    private LinearLayout$LayoutParams f() {
        LinearLayout$LayoutParams v0 = new LinearLayout$LayoutParams(-2, -1);
        this.a(v0);
        return v0;
    }

    private void f(f arg3) {
        int v0;
        for(v0 = this.G.size() - 1; v0 >= 0; --v0) {
            this.G.get(v0).onTabSelected(arg3);
        }
    }

    private void g() {
        if(this.I == null) {
            this.I = new ValueAnimator();
            this.I.setInterpolator(android.support.design.a.a.b);
            this.I.setDuration(((long)this.p));
            this.I.addUpdateListener(new ValueAnimator$AnimatorUpdateListener() {
                public void onAnimationUpdate(ValueAnimator arg3) {
                    this.a.scrollTo(arg3.getAnimatedValue().intValue(), 0);
                }
            });
        }
    }

    private void g(f arg3) {
        int v0;
        for(v0 = this.G.size() - 1; v0 >= 0; --v0) {
            this.G.get(v0).onTabUnselected(arg3);
        }
    }

    public ViewGroup$LayoutParams generateLayoutParams(AttributeSet arg1) {
        return this.generateLayoutParams(arg1);
    }

    public FrameLayout$LayoutParams generateLayoutParams(AttributeSet arg1) {
        return this.generateDefaultLayoutParams();
    }

    private int getDefaultHeight() {
        int v0 = this.x.size();
        int v1 = 0;
        int v2;
        for(v2 = 0; v2 < v0; ++v2) {
            Object v3 = this.x.get(v2);
            if(v3 != null && ((f)v3).c() != null && !TextUtils.isEmpty(((f)v3).e())) {
                v1 = 1;
                break;
            }
        }

        return v1 == 0 || (this.s) ? 48 : 72;
    }

    public int getSelectedTabPosition() {
        int v0 = this.y != null ? this.y.d() : -1;
        return v0;
    }

    public int getTabCount() {
        return this.x.size();
    }

    public int getTabGravity() {
        return this.o;
    }

    public ColorStateList getTabIconTint() {
        return this.g;
    }

    public int getTabIndicatorGravity() {
        return this.q;
    }

    int getTabMaxWidth() {
        return this.n;
    }

    private int getTabMinWidth() {
        if(this.B != -1) {
            return this.B;
        }

        int v0 = this.r == 0 ? this.D : 0;
        return v0;
    }

    public int getTabMode() {
        return this.r;
    }

    public ColorStateList getTabRippleColor() {
        return this.h;
    }

    private int getTabScrollRange() {
        return Math.max(0, this.A.getWidth() - this.getWidth() - this.getPaddingLeft() - this.getPaddingRight());
    }

    public Drawable getTabSelectedIndicator() {
        return this.i;
    }

    public ColorStateList getTabTextColors() {
        return this.f;
    }

    private void h() {
        int v0 = this.r == 0 ? Math.max(0, this.E - this.a) : 0;
        t.a(this.A, v0, 0, 0, 0);
        switch(this.r) {
            case 0: {
                this.A.setGravity(8388611);
                break;
            }
            case 1: {
                this.A.setGravity(1);
                break;
            }
            default: {
                break;
            }
        }

        this.a(true);
    }

    private void h(f arg3) {
        int v0;
        for(v0 = this.G.size() - 1; v0 >= 0; --v0) {
            this.G.get(v0).onTabReselected(arg3);
        }
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if(this.v == null) {
            ViewParent v0 = this.getParent();
            if((v0 instanceof ViewPager)) {
                this.a(((ViewPager)v0), true, true);
            }
        }
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if(this.N) {
            this.setupWithViewPager(null);
            this.N = false;
        }
    }

    protected void onDraw(Canvas arg4) {
        int v0;
        for(v0 = 0; v0 < this.A.getChildCount(); ++v0) {
            View v1 = this.A.getChildAt(v0);
            if((v1 instanceof h)) {
                h.a(((h)v1), arg4);
            }
        }

        super.onDraw(arg4);
    }

    protected void onMeasure(int arg6, int arg7) {
        int v0 = this.b(this.getDefaultHeight()) + this.getPaddingTop() + this.getPaddingBottom();
        int v1 = View$MeasureSpec.getMode(arg7);
        int v3 = 1073741824;
        if(v1 == -2147483648) {
            arg7 = View$MeasureSpec.makeMeasureSpec(Math.min(v0, View$MeasureSpec.getSize(arg7)), v3);
        }
        else if(v1 != 0) {
        }
        else {
            arg7 = View$MeasureSpec.makeMeasureSpec(v0, v3);
        }

        v0 = View$MeasureSpec.getSize(arg6);
        if(View$MeasureSpec.getMode(arg6) != 0) {
            if(this.C > 0) {
                v0 = this.C;
            }
            else {
                v0 -= this.b(56);
            }

            this.n = v0;
        }

        super.onMeasure(arg6, arg7);
        if(this.getChildCount() == 1) {
            arg6 = 0;
            View v1_1 = this.getChildAt(0);
            switch(this.r) {
                case 0: {
                    if(v1_1.getMeasuredWidth() < this.getMeasuredWidth()) {
                    label_40:
                        arg6 = 1;
                    }
                    else {
                    }

                    break;
                }
                case 1: {
                    if(v1_1.getMeasuredWidth() == this.getMeasuredWidth()) {
                        goto label_46;
                    }

                    goto label_40;
                }
                default: {
                    break;
                }
            }

        label_46:
            if(arg6 != 0) {
                v1_1.measure(View$MeasureSpec.makeMeasureSpec(this.getMeasuredWidth(), v3), r.getChildMeasureSpec(arg7, this.getPaddingTop() + this.getPaddingBottom(), v1_1.getLayoutParams().height));
            }
        }
    }

    public void setInlineLabel(boolean arg3) {
        if(this.s != arg3) {
            this.s = arg3;
            int v3;
            for(v3 = 0; v3 < this.A.getChildCount(); ++v3) {
                View v0 = this.A.getChildAt(v3);
                if((v0 instanceof h)) {
                    ((h)v0).c();
                }
            }

            this.h();
        }
    }

    public void setInlineLabelResource(int arg2) {
        this.setInlineLabel(this.getResources().getBoolean(arg2));
    }

    @Deprecated public void setOnTabSelectedListener(b arg2) {
        if(this.F != null) {
            this.b(this.F);
        }

        this.F = arg2;
        if(arg2 != null) {
            this.a(arg2);
        }
    }

    void setScrollAnimatorListener(Animator$AnimatorListener arg2) {
        this.g();
        this.I.addListener(arg2);
    }

    public void setSelectedTabIndicator(Drawable arg2) {
        if(this.i != arg2) {
            this.i = arg2;
            t.d(this.A);
        }
    }

    public void setSelectedTabIndicator(int arg2) {
        Drawable v2 = arg2 != 0 ? android.support.v7.c.a.a.b(this.getContext(), arg2) : null;
        this.setSelectedTabIndicator(v2);
    }

    public void setSelectedTabIndicatorColor(int arg2) {
        this.A.a(arg2);
    }

    public void setSelectedTabIndicatorGravity(int arg2) {
        if(this.q != arg2) {
            this.q = arg2;
            t.d(this.A);
        }
    }

    @Deprecated public void setSelectedTabIndicatorHeight(int arg2) {
        this.A.b(arg2);
    }

    private void setSelectedTabView(int arg7) {
        int v0 = this.A.getChildCount();
        if(arg7 < v0) {
            int v2;
            for(v2 = 0; v2 < v0; ++v2) {
                View v3 = this.A.getChildAt(v2);
                boolean v4 = true;
                boolean v5 = v2 == arg7 ? true : false;
                v3.setSelected(v5);
                if(v2 == arg7) {
                }
                else {
                    v4 = false;
                }

                v3.setActivated(v4);
            }
        }
    }

    public void setTabGravity(int arg2) {
        if(this.o != arg2) {
            this.o = arg2;
            this.h();
        }
    }

    public void setTabIconTint(ColorStateList arg2) {
        if(this.g != arg2) {
            this.g = arg2;
            this.e();
        }
    }

    public void setTabIconTintResource(int arg2) {
        this.setTabIconTint(android.support.v7.c.a.a.a(this.getContext(), arg2));
    }

    public void setTabIndicatorFullWidth(boolean arg1) {
        this.t = arg1;
        t.d(this.A);
    }

    public void setTabMode(int arg2) {
        if(arg2 != this.r) {
            this.r = arg2;
            this.h();
        }
    }

    public void setTabRippleColor(ColorStateList arg3) {
        if(this.h != arg3) {
            this.h = arg3;
            int v3;
            for(v3 = 0; v3 < this.A.getChildCount(); ++v3) {
                View v0 = this.A.getChildAt(v3);
                if((v0 instanceof h)) {
                    h.a(((h)v0), this.getContext());
                }
            }
        }
    }

    public void setTabRippleColorResource(int arg2) {
        this.setTabRippleColor(android.support.v7.c.a.a.a(this.getContext(), arg2));
    }

    public void setTabTextColors(ColorStateList arg2) {
        if(this.f != arg2) {
            this.f = arg2;
            this.e();
        }
    }

    @Deprecated public void setTabsFromPagerAdapter(q arg2) {
        this.a(arg2, false);
    }

    public void setUnboundedRipple(boolean arg3) {
        if(this.u != arg3) {
            this.u = arg3;
            int v3;
            for(v3 = 0; v3 < this.A.getChildCount(); ++v3) {
                View v0 = this.A.getChildAt(v3);
                if((v0 instanceof h)) {
                    h.a(((h)v0), this.getContext());
                }
            }
        }
    }

    public void setUnboundedRippleResource(int arg2) {
        this.setUnboundedRipple(this.getResources().getBoolean(arg2));
    }

    public void setupWithViewPager(ViewPager arg2) {
        this.a(arg2, true);
    }

    public boolean shouldDelayChildPressedState() {
        boolean v0 = this.getTabScrollRange() > 0 ? true : false;
        return v0;
    }
}

