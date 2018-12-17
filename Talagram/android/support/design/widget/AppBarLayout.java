package android.support.design.widget;

import android.animation.ValueAnimator$AnimatorUpdateListener;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.os.Build$VERSION;
import android.os.Parcel;
import android.os.Parcelable$ClassLoaderCreator;
import android.os.Parcelable$Creator;
import android.os.Parcelable;
import android.support.design.a$k;
import android.support.v4.view.AbsSavedState;
import android.support.v4.view.ab;
import android.support.v4.view.j;
import android.support.v4.view.p;
import android.support.v4.view.t;
import android.util.AttributeSet;
import android.view.View$MeasureSpec;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.view.ViewGroup$MarginLayoutParams;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.LinearLayout$LayoutParams;
import android.widget.LinearLayout;
import java.lang.ref.WeakReference;
import java.util.List;

@c(a=Behavior.class) public class AppBarLayout extends LinearLayout {
    public class BaseBehavior extends i {
        public class SavedState extends AbsSavedState {
            final class android.support.design.widget.AppBarLayout$BaseBehavior$SavedState$1 implements Parcelable$ClassLoaderCreator {
                android.support.design.widget.AppBarLayout$BaseBehavior$SavedState$1() {
                    super();
                }

                public SavedState a(Parcel arg3) {
                    return new SavedState(arg3, null);
                }

                public SavedState a(Parcel arg2, ClassLoader arg3) {
                    return new SavedState(arg2, arg3);
                }

                public SavedState[] a(int arg1) {
                    return new SavedState[arg1];
                }

                public Object createFromParcel(Parcel arg1) {
                    return this.a(arg1);
                }

                public Object createFromParcel(Parcel arg1, ClassLoader arg2) {
                    return this.a(arg1, arg2);
                }

                public Object[] newArray(int arg1) {
                    return this.a(arg1);
                }
            }

            public static final Parcelable$Creator CREATOR;
            int a;
            float b;
            boolean c;

            static {
                SavedState.CREATOR = new android.support.design.widget.AppBarLayout$BaseBehavior$SavedState$1();
            }

            public SavedState(Parcelable arg1) {
                super(arg1);
            }

            public SavedState(Parcel arg1, ClassLoader arg2) {
                super(arg1, arg2);
                this.a = arg1.readInt();
                this.b = arg1.readFloat();
                boolean v1 = arg1.readByte() != 0 ? true : false;
                this.c = v1;
            }

            public void writeToParcel(Parcel arg1, int arg2) {
                super.writeToParcel(arg1, arg2);
                arg1.writeInt(this.a);
                arg1.writeFloat(this.b);
                arg1.writeByte(((byte)this.c));
            }
        }

        public abstract class a {
            public abstract boolean a(AppBarLayout arg1);
        }

        private int b;
        private int c;
        private ValueAnimator d;
        private int e;
        private boolean f;
        private float g;
        private WeakReference h;
        private a i;

        public BaseBehavior() {
            super();
            this.e = -1;
        }

        public BaseBehavior(Context arg1, AttributeSet arg2) {
            super(arg1, arg2);
            this.e = -1;
        }

        static int a(BaseBehavior arg0) {
            return arg0.b;
        }

        private int a(AppBarLayout arg8, int arg9) {
            int v0 = arg8.getChildCount();
            int v1;
            for(v1 = 0; v1 < v0; ++v1) {
                View v2 = arg8.getChildAt(v1);
                int v3 = v2.getTop();
                int v4 = v2.getBottom();
                ViewGroup$LayoutParams v2_1 = v2.getLayoutParams();
                if(BaseBehavior.a(((b)v2_1).a(), 32)) {
                    v3 -= ((b)v2_1).topMargin;
                    v4 += ((b)v2_1).bottomMargin;
                }

                int v2_2 = -arg9;
                if(v3 <= v2_2 && v4 >= v2_2) {
                    return v1;
                }
            }

            return -1;
        }

        private static boolean a(int arg0, int arg1) {
            boolean v0 = (arg0 & arg1) == arg1 ? true : false;
            return v0;
        }

        private View a(CoordinatorLayout arg5) {
            int v0 = arg5.getChildCount();
            int v1;
            for(v1 = 0; v1 < v0; ++v1) {
                View v2 = arg5.getChildAt(v1);
                if((v2 instanceof j)) {
                    return v2;
                }
            }

            return null;
        }

        private void a(int arg2, AppBarLayout arg3, View arg4, int arg5) {
            if(arg5 == 1) {
                arg5 = this.a();
                if(arg2 >= 0 || arg5 != 0) {
                    if(arg2 <= 0) {
                    }
                    else if(arg5 == -arg3.getDownNestedScrollRange()) {
                        goto label_9;
                    }

                    return;
                }

            label_9:
                t.d(arg4, 1);
            }
        }

        int a() {
            return this.b() + this.b;
        }

        private void a(CoordinatorLayout arg3, AppBarLayout arg4, int arg5, float arg6) {
            int v0 = Math.abs(this.a() - arg5);
            arg6 = Math.abs(arg6);
            int v6 = arg6 > 0f ? Math.round((((float)v0)) / arg6 * 1000f) * 3 : ((int)(((((float)v0)) / (((float)arg4.getHeight())) + 1f) * 150f));
            this.a(arg3, arg4, arg5, v6);
        }

        private void a(CoordinatorLayout arg4, AppBarLayout arg5, int arg6, int arg7) {
            int v0 = this.a();
            if(v0 == arg6) {
                if(this.d != null && (this.d.isRunning())) {
                    this.d.cancel();
                }

                return;
            }

            if(this.d == null) {
                this.d = new ValueAnimator();
                this.d.setInterpolator(android.support.design.a.a.e);
                this.d.addUpdateListener(new ValueAnimator$AnimatorUpdateListener(arg4, arg5) {
                    public void onAnimationUpdate(ValueAnimator arg4) {
                        this.c.a_(this.a, this.b, arg4.getAnimatedValue().intValue());
                    }
                });
            }
            else {
                this.d.cancel();
            }

            this.d.setDuration(((long)Math.min(arg7, 600)));
            this.d.setIntValues(new int[]{v0, arg6});
            this.d.start();
        }

        private void a(CoordinatorLayout arg6, AppBarLayout arg7, int arg8, int arg9, boolean arg10) {
            boolean v8;
            View v0 = BaseBehavior.c(arg7, arg8);
            if(v0 != null) {
                int v1 = v0.getLayoutParams().a();
                if((v1 & 1) != 0) {
                    int v2 = t.l(v0);
                    if(arg9 <= 0 || (v1 & 12) == 0) {
                        if((v1 & 2) == 0) {
                            goto label_29;
                        }
                        else if(-arg8 >= v0.getBottom() - v2 - arg7.getTopInset()) {
                        }
                        else {
                            goto label_29;
                        }
                    }
                    else if(-arg8 < v0.getBottom() - v2 - arg7.getTopInset()) {
                        goto label_29;
                    }

                    v8 = true;
                }
                else {
                label_29:
                    v8 = false;
                }

                if(arg7.d()) {
                    View v9 = this.a(arg6);
                    if(v9 != null) {
                        v8 = v9.getScrollY() > 0 ? true : false;
                    }
                }

                v8 = arg7.a(v8);
                if(Build$VERSION.SDK_INT < 11) {
                    return;
                }

                if(!arg10) {
                    if(!v8) {
                    }
                    else if(this.d(arg6, arg7)) {
                        goto label_47;
                    }

                    return;
                }

            label_47:
                arg7.jumpDrawablesToCurrentState();
            }
        }

        private boolean a(CoordinatorLayout arg2, AppBarLayout arg3, View arg4) {
            boolean v2 = !arg3.c() || arg2.getHeight() - arg4.getHeight() > arg3.getHeight() ? false : true;
            return v2;
        }

        int a(CoordinatorLayout arg9, AppBarLayout arg10, int arg11, int arg12, int arg13) {
            int v0 = this.a();
            int v1 = 0;
            if(arg12 == 0 || v0 < arg12 || v0 > arg13) {
                this.b = 0;
            }
            else {
                int v5 = android.support.v4.b.a.a(arg11, arg12, arg13);
                if(v0 != v5) {
                    arg11 = arg10.b() ? this.b(arg10, v5) : v5;
                    boolean v12 = this.a(arg11);
                    v1 = v0 - v5;
                    this.b = v5 - arg11;
                    if(!v12 && (arg10.b())) {
                        arg9.b(((View)arg10));
                    }

                    arg10.a(this.b());
                    int v6 = v5 < v0 ? -1 : 1;
                    this.a(arg9, arg10, v5, v6, false);
                }
            }

            return v1;
        }

        int a(CoordinatorLayout arg1, View arg2, int arg3, int arg4, int arg5) {
            return this.a(arg1, ((AppBarLayout)arg2), arg3, arg4, arg5);
        }

        int a(View arg1) {
            return this.c(((AppBarLayout)arg1));
        }

        void a(CoordinatorLayout arg1, AppBarLayout arg2) {
            this.c(arg1, arg2);
        }

        public void a(CoordinatorLayout arg2, AppBarLayout arg3, Parcelable arg4) {
            if((arg4 instanceof SavedState)) {
                super.a(arg2, ((View)arg3), ((SavedState)arg4).getSuperState());
                this.e = ((SavedState)arg4).a;
                this.g = ((SavedState)arg4).b;
                this.f = ((SavedState)arg4).c;
            }
            else {
                super.a(arg2, ((View)arg3), arg4);
                this.e = -1;
            }
        }

        public void a(CoordinatorLayout arg2, AppBarLayout arg3, View arg4, int arg5) {
            if(this.c == 0 || arg5 == 1) {
                this.c(arg2, arg3);
            }

            this.h = new WeakReference(arg4);
        }

        public void a(CoordinatorLayout arg7, AppBarLayout arg8, View arg9, int arg10, int arg11, int arg12, int arg13, int arg14) {
            if(arg13 < 0) {
                this.b(arg7, arg8, arg13, -arg8.getDownNestedScrollRange(), 0);
                this.a(arg13, arg8, arg9, arg14);
            }

            if(arg8.d()) {
                boolean v7 = arg9.getScrollY() > 0 ? true : false;
                arg8.a(v7);
            }
        }

        public void a(CoordinatorLayout arg8, AppBarLayout arg9, View arg10, int arg11, int arg12, int[] arg13, int arg14) {
            int v6;
            int v5;
            if(arg12 != 0) {
                if(arg12 < 0) {
                    arg11 = -arg9.getTotalScrollRange();
                    v5 = arg11;
                    v6 = arg9.getDownNestedPreScrollRange() + arg11;
                }
                else {
                    v5 = -arg9.getUpNestedPreScrollRange();
                    v6 = 0;
                }

                if(v5 == v6) {
                    return;
                }

                arg13[1] = this.b(arg8, arg9, arg12, v5, v6);
                this.a(arg12, arg9, arg10, arg14);
            }
        }

        void a(CoordinatorLayout arg1, View arg2) {
            this.a(arg1, ((AppBarLayout)arg2));
        }

        public void a(CoordinatorLayout arg1, View arg2, Parcelable arg3) {
            this.a(arg1, ((AppBarLayout)arg2), arg3);
        }

        public void a(CoordinatorLayout arg1, View arg2, View arg3, int arg4) {
            this.a(arg1, ((AppBarLayout)arg2), arg3, arg4);
        }

        public void a(CoordinatorLayout arg1, View arg2, View arg3, int arg4, int arg5, int arg6, int arg7, int arg8) {
            this.a(arg1, ((AppBarLayout)arg2), arg3, arg4, arg5, arg6, arg7, arg8);
        }

        public void a(CoordinatorLayout arg1, View arg2, View arg3, int arg4, int arg5, int[] arg6, int arg7) {
            this.a(arg1, ((AppBarLayout)arg2), arg3, arg4, arg5, arg6, arg7);
        }

        boolean a(AppBarLayout arg3) {
            if(this.i != null) {
                return this.i.a(arg3);
            }

            boolean v0 = true;
            if(this.h != null) {
                Object v3 = this.h.get();
                if(v3 != null && (((View)v3).isShown()) && !((View)v3).canScrollVertically(-1)) {
                    return v0;
                }

                v0 = false;
            }

            return v0;
        }

        public boolean a(CoordinatorLayout arg8, AppBarLayout arg9, int arg10) {
            int v1;
            boolean v10 = super.a(arg8, ((View)arg9), arg10);
            int v0 = arg9.getPendingAction();
            if(this.e < 0 || (v0 & 8) != 0) {
                if(v0 == 0) {
                    goto label_49;
                }

                v1 = (v0 & 4) != 0 ? 1 : 0;
                if((v0 & 2) != 0) {
                    v0 = -arg9.getUpNestedPreScrollRange();
                    if(v1 != 0) {
                        this.a(arg8, arg9, v0, 0f);
                        goto label_49;
                    }

                    this.a_(arg8, ((View)arg9), v0);
                    goto label_49;
                }

                if((v0 & 1) == 0) {
                    goto label_49;
                }

                if(v1 != 0) {
                    this.a(arg8, arg9, 0, 0f);
                    goto label_49;
                }

                this.a_(arg8, ((View)arg9), 0);
            }
            else {
                View v0_1 = arg9.getChildAt(this.e);
                v1 = -v0_1.getBottom();
                v0 = this.f ? t.l(v0_1) + arg9.getTopInset() : Math.round((((float)v0_1.getHeight())) * this.g);
                v1 += v0;
                this.a_(arg8, ((View)arg9), v1);
            }

        label_49:
            arg9.e();
            this.e = -1;
            this.a(android.support.v4.b.a.a(this.b(), -arg9.getTotalScrollRange(), 0));
            this.a(arg8, arg9, this.b(), 0, true);
            arg9.a(this.b());
            return v10;
        }

        public boolean a(CoordinatorLayout arg7, AppBarLayout arg8, int arg9, int arg10, int arg11, int arg12) {
            if(arg8.getLayoutParams().height == -2) {
                arg7.a(arg8, arg9, arg10, View$MeasureSpec.makeMeasureSpec(0, 0), arg12);
                return 1;
            }

            return super.a(arg7, ((View)arg8), arg9, arg10, arg11, arg12);
        }

        public boolean a(CoordinatorLayout arg1, AppBarLayout arg2, View arg3, View arg4, int arg5, int arg6) {
            boolean v1;
            if((arg5 & 2) != 0) {
                if(!arg2.d() && !this.a(arg1, arg2, arg3)) {
                    goto label_8;
                }

                v1 = true;
            }
            else {
            label_8:
                v1 = false;
            }

            if((v1) && this.d != null) {
                this.d.cancel();
            }

            this.h = null;
            this.c = arg6;
            return v1;
        }

        public boolean a(CoordinatorLayout arg1, View arg2, int arg3) {
            return this.a(arg1, ((AppBarLayout)arg2), arg3);
        }

        public boolean a(CoordinatorLayout arg1, View arg2, int arg3, int arg4, int arg5, int arg6) {
            return this.a(arg1, ((AppBarLayout)arg2), arg3, arg4, arg5, arg6);
        }

        public boolean a(CoordinatorLayout arg1, View arg2, View arg3, View arg4, int arg5, int arg6) {
            return this.a(arg1, ((AppBarLayout)arg2), arg3, arg4, arg5, arg6);
        }

        private int b(AppBarLayout arg9, int arg10) {
            int v0 = Math.abs(arg10);
            int v1 = arg9.getChildCount();
            int v2 = 0;
            int v3;
            for(v3 = 0; v3 < v1; ++v3) {
                View v4 = arg9.getChildAt(v3);
                ViewGroup$LayoutParams v5 = v4.getLayoutParams();
                Interpolator v6 = ((b)v5).b();
                if(v0 >= v4.getTop() && v0 <= v4.getBottom()) {
                    if(v6 != null) {
                        v1 = ((b)v5).a();
                        if((v1 & 1) != 0) {
                            v2 = v4.getHeight() + ((b)v5).topMargin + ((b)v5).bottomMargin;
                            if((v1 & 2) != 0) {
                                v2 -= t.l(v4);
                            }
                        }

                        if(t.t(v4)) {
                            v2 -= arg9.getTopInset();
                        }

                        if(v2 <= 0) {
                            return arg10;
                        }

                        float v9 = ((float)v2);
                        return Integer.signum(arg10) * (v4.getTop() + Math.round(v9 * v6.getInterpolation((((float)(v0 - v4.getTop()))) / v9)));
                    }
                    else {
                        return arg10;
                    }
                }
            }

            return arg10;
        }

        int b(AppBarLayout arg1) {
            return -arg1.getDownNestedScrollRange();
        }

        int b(View arg1) {
            return this.b(((AppBarLayout)arg1));
        }

        public Parcelable b(CoordinatorLayout arg8, AppBarLayout arg9) {
            Parcelable v8 = super.b(arg8, ((View)arg9));
            int v0 = this.b();
            int v1 = arg9.getChildCount();
            boolean v2 = false;
            int v3;
            for(v3 = 0; v3 < v1; ++v3) {
                View v4 = arg9.getChildAt(v3);
                int v5 = v4.getBottom() + v0;
                if(v4.getTop() + v0 <= 0 && v5 >= 0) {
                    SavedState v0_1 = new SavedState(v8);
                    v0_1.a = v3;
                    if(v5 == t.l(v4) + arg9.getTopInset()) {
                        v2 = true;
                    }

                    v0_1.c = v2;
                    v0_1.b = (((float)v5)) / (((float)v4.getHeight()));
                    return ((Parcelable)v0_1);
                }
            }

            return v8;
        }

        public Parcelable b(CoordinatorLayout arg1, View arg2) {
            return this.b(arg1, ((AppBarLayout)arg2));
        }

        private static View c(AppBarLayout arg4, int arg5) {
            arg5 = Math.abs(arg5);
            int v0 = arg4.getChildCount();
            int v1;
            for(v1 = 0; v1 < v0; ++v1) {
                View v2 = arg4.getChildAt(v1);
                if(arg5 >= v2.getTop() && arg5 <= v2.getBottom()) {
                    return v2;
                }
            }

            return null;
        }

        private void c(CoordinatorLayout arg9, AppBarLayout arg10) {
            int v0 = this.a();
            int v1 = this.a(arg10, v0);
            if(v1 >= 0) {
                View v2 = arg10.getChildAt(v1);
                ViewGroup$LayoutParams v3 = v2.getLayoutParams();
                int v4 = ((b)v3).a();
                if((v4 & 17) == 17) {
                    int v5 = -v2.getTop();
                    int v6 = -v2.getBottom();
                    if(v1 == arg10.getChildCount() - 1) {
                        v6 += arg10.getTopInset();
                    }

                    v1 = 2;
                    if(BaseBehavior.a(v4, v1)) {
                        v6 += t.l(v2);
                    }
                    else if(BaseBehavior.a(v4, 5)) {
                        int v2_1 = t.l(v2) + v6;
                        if(v0 < v2_1) {
                            v5 = v2_1;
                        }
                        else {
                            v6 = v2_1;
                        }
                    }

                    if(BaseBehavior.a(v4, 32)) {
                        v5 += ((b)v3).topMargin;
                        v6 -= ((b)v3).bottomMargin;
                    }

                    if(v0 < (v6 + v5) / v1) {
                        v5 = v6;
                    }

                    this.a(arg9, arg10, android.support.v4.b.a.a(v5, -arg10.getTotalScrollRange(), 0), 0f);
                }
            }
        }

        int c(AppBarLayout arg1) {
            return arg1.getTotalScrollRange();
        }

        boolean c(View arg1) {
            return this.a(((AppBarLayout)arg1));
        }

        private boolean d(CoordinatorLayout arg5, AppBarLayout arg6) {
            List v5 = arg5.d(((View)arg6));
            int v6 = v5.size();
            boolean v0 = false;
            int v1;
            for(v1 = 0; v1 < v6; ++v1) {
                android.support.design.widget.CoordinatorLayout$b v2 = v5.get(v1).getLayoutParams().b();
                if((v2 instanceof ScrollingViewBehavior)) {
                    if(((ScrollingViewBehavior)v2).d() != 0) {
                        v0 = true;
                    }

                    return v0;
                }
            }

            return 0;
        }
    }

    public class Behavior extends BaseBehavior {
        public Behavior() {
            super();
        }

        public Behavior(Context arg1, AttributeSet arg2) {
            super(arg1, arg2);
        }

        public void a(CoordinatorLayout arg1, AppBarLayout arg2, Parcelable arg3) {
            super.a(arg1, arg2, arg3);
        }

        public void a(CoordinatorLayout arg1, AppBarLayout arg2, View arg3, int arg4) {
            super.a(arg1, arg2, arg3, arg4);
        }

        public void a(CoordinatorLayout arg1, AppBarLayout arg2, View arg3, int arg4, int arg5, int arg6, int arg7, int arg8) {
            super.a(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8);
        }

        public void a(CoordinatorLayout arg1, AppBarLayout arg2, View arg3, int arg4, int arg5, int[] arg6, int arg7) {
            super.a(arg1, arg2, arg3, arg4, arg5, arg6, arg7);
        }

        public boolean a(int arg1) {
            return super.a(arg1);
        }

        public boolean a(CoordinatorLayout arg1, AppBarLayout arg2, int arg3) {
            return super.a(arg1, arg2, arg3);
        }

        public boolean a(CoordinatorLayout arg1, AppBarLayout arg2, int arg3, int arg4, int arg5, int arg6) {
            return super.a(arg1, arg2, arg3, arg4, arg5, arg6);
        }

        public boolean a(CoordinatorLayout arg1, AppBarLayout arg2, View arg3, View arg4, int arg5, int arg6) {
            return super.a(arg1, arg2, arg3, arg4, arg5, arg6);
        }

        public int b() {
            return super.b();
        }

        public Parcelable b(CoordinatorLayout arg1, AppBarLayout arg2) {
            return super.b(arg1, arg2);
        }
    }

    public class ScrollingViewBehavior extends android.support.design.widget.j {
        public ScrollingViewBehavior() {
            super();
        }

        public ScrollingViewBehavior(Context arg2, AttributeSet arg3) {
            super(arg2, arg3);
            TypedArray v2 = arg2.obtainStyledAttributes(arg3, k.ScrollingViewBehavior_Layout);
            this.b(v2.getDimensionPixelSize(k.ScrollingViewBehavior_Layout_behavior_overlapTop, 0));
            v2.recycle();
        }

        private static int a(AppBarLayout arg1) {
            android.support.design.widget.CoordinatorLayout$b v1 = arg1.getLayoutParams().b();
            if((v1 instanceof BaseBehavior)) {
                return ((BaseBehavior)v1).a();
            }

            return 0;
        }

        private void a(View arg4, View arg5) {
            android.support.design.widget.CoordinatorLayout$b v0 = arg5.getLayoutParams().b();
            if((v0 instanceof BaseBehavior)) {
                t.e(arg4, arg5.getBottom() - arg4.getTop() + BaseBehavior.a(((BaseBehavior)v0)) + this.a() - this.c(arg5));
            }
        }

        float a(View arg5) {
            if((arg5 instanceof AppBarLayout)) {
                int v0 = ((AppBarLayout)arg5).getTotalScrollRange();
                int v2 = ((AppBarLayout)arg5).getDownNestedPreScrollRange();
                int v5 = ScrollingViewBehavior.a(((AppBarLayout)arg5));
                if(v2 != 0 && v0 + v5 <= v2) {
                    return 0;
                }

                v0 -= v2;
                if(v0 == 0) {
                    return 0;
                }

                return (((float)v5)) / (((float)v0)) + 1f;
            }

            return 0;
        }

        AppBarLayout a(List arg5) {
            int v0 = arg5.size();
            int v1;
            for(v1 = 0; v1 < v0; ++v1) {
                Object v2 = arg5.get(v1);
                if((v2 instanceof AppBarLayout)) {
                    return ((AppBarLayout)v2);
                }
            }

            return null;
        }

        public boolean a(int arg1) {
            return super.a(arg1);
        }

        public boolean a(CoordinatorLayout arg1, View arg2, int arg3) {
            return super.a(arg1, arg2, arg3);
        }

        public boolean a(CoordinatorLayout arg1, View arg2, int arg3, int arg4, int arg5, int arg6) {
            return super.a(arg1, arg2, arg3, arg4, arg5, arg6);
        }

        public boolean a(CoordinatorLayout arg4, View arg5, Rect arg6, boolean arg7) {
            AppBarLayout v0 = this.a(arg4.c(arg5));
            if(v0 != null) {
                arg6.offset(arg5.getLeft(), arg5.getTop());
                Rect v5 = this.a;
                v5.set(0, 0, arg4.getWidth(), arg4.getHeight());
                if(!v5.contains(arg6)) {
                    v0.a(false, (((int)arg7)) ^ 1);
                    return 1;
                }
            }

            return 0;
        }

        public boolean a(CoordinatorLayout arg1, View arg2, View arg3) {
            return arg3 instanceof AppBarLayout;
        }

        private void b(View arg2, View arg3) {
            if(((arg3 instanceof AppBarLayout)) && (((AppBarLayout)arg3).d())) {
                boolean v2 = arg2.getScrollY() > 0 ? true : false;
                ((AppBarLayout)arg3).a(v2);
            }
        }

        public int b() {
            return super.b();
        }

        int b(View arg2) {
            if((arg2 instanceof AppBarLayout)) {
                return ((AppBarLayout)arg2).getTotalScrollRange();
            }

            return super.b(arg2);
        }

        View b(List arg1) {
            return this.a(arg1);
        }

        public boolean b(CoordinatorLayout arg1, View arg2, View arg3) {
            this.a(arg2, arg3);
            this.b(arg2, arg3);
            return 0;
        }
    }

    public interface android.support.design.widget.AppBarLayout$a {
        void a(AppBarLayout arg1, int arg2);
    }

    public class b extends LinearLayout$LayoutParams {
        int a;
        Interpolator b;

        public b(int arg1, int arg2) {
            super(arg1, arg2);
            this.a = 1;
        }

        public b(Context arg3, AttributeSet arg4) {
            super(arg3, arg4);
            this.a = 1;
            TypedArray v4 = arg3.obtainStyledAttributes(arg4, k.AppBarLayout_Layout);
            this.a = v4.getInt(k.AppBarLayout_Layout_layout_scrollFlags, 0);
            if(v4.hasValue(k.AppBarLayout_Layout_layout_scrollInterpolator)) {
                this.b = AnimationUtils.loadInterpolator(arg3, v4.getResourceId(k.AppBarLayout_Layout_layout_scrollInterpolator, 0));
            }

            v4.recycle();
        }

        public b(LinearLayout$LayoutParams arg1) {
            super(arg1);
            this.a = 1;
        }

        public b(ViewGroup$MarginLayoutParams arg1) {
            super(arg1);
            this.a = 1;
        }

        public b(ViewGroup$LayoutParams arg1) {
            super(arg1);
            this.a = 1;
        }

        public int a() {
            return this.a;
        }

        public Interpolator b() {
            return this.b;
        }

        boolean c() {
            boolean v1 = true;
            if((this.a & 1) != 1 || (this.a & 10) == 0) {
                v1 = false;
            }
            else {
            }

            return v1;
        }
    }

    private int a;
    private int b;
    private int c;
    private boolean d;
    private int e;
    private ab f;
    private List g;
    private boolean h;
    private boolean i;
    private boolean j;
    private boolean k;
    private int[] l;

    public AppBarLayout(Context arg2) {
        this(arg2, null);
    }

    public AppBarLayout(Context arg10, AttributeSet arg11) {
        super(arg10, arg11);
        this.a = -1;
        this.b = -1;
        this.c = -1;
        this.e = 0;
        this.setOrientation(1);
        int v2 = 21;
        if(Build$VERSION.SDK_INT >= v2) {
            v.a(((View)this));
            v.a(((View)this), arg11, 0, android.support.design.a$j.Widget_Design_AppBarLayout);
        }

        TypedArray v10 = android.support.design.internal.b.a(arg10, arg11, k.AppBarLayout, 0, android.support.design.a$j.Widget_Design_AppBarLayout, new int[0]);
        t.a(((View)this), v10.getDrawable(k.AppBarLayout_android_background));
        if(v10.hasValue(k.AppBarLayout_expanded)) {
            this.a(v10.getBoolean(k.AppBarLayout_expanded, false), false, false);
        }

        if(Build$VERSION.SDK_INT >= v2 && (v10.hasValue(k.AppBarLayout_elevation))) {
            v.a(((View)this), ((float)v10.getDimensionPixelSize(k.AppBarLayout_elevation, 0)));
        }

        if(Build$VERSION.SDK_INT >= 26) {
            if(v10.hasValue(k.AppBarLayout_android_keyboardNavigationCluster)) {
                this.setKeyboardNavigationCluster(v10.getBoolean(k.AppBarLayout_android_keyboardNavigationCluster, false));
            }

            if(!v10.hasValue(k.AppBarLayout_android_touchscreenBlocksFocus)) {
                goto label_55;
            }

            this.setTouchscreenBlocksFocus(v10.getBoolean(k.AppBarLayout_android_touchscreenBlocksFocus, false));
        }

    label_55:
        this.k = v10.getBoolean(k.AppBarLayout_liftOnScroll, false);
        v10.recycle();
        t.a(((View)this), new p() {
            public ab a(View arg1, ab arg2) {
                return this.a.a(arg2);
            }
        });
    }

    private void a(boolean arg2, boolean arg3, boolean arg4) {
        int v2 = arg2 ? 1 : 2;
        int v0 = 0;
        int v3 = arg3 ? 4 : 0;
        v2 |= v3;
        if(arg4) {
            v0 = 8;
        }

        this.e = v2 | v0;
        this.requestLayout();
    }

    ab a(ab arg3) {
        ab v0;
        if(t.t(((View)this))) {
            v0 = arg3;
        }
        else {
            Object v0_1 = null;
        }

        if(!android.support.v4.f.i.a(this.f, v0)) {
            this.f = v0;
            this.g();
        }

        return arg3;
    }

    protected b a() {
        return new b(-1, -2);
    }

    public b a(AttributeSet arg3) {
        return new b(this.getContext(), arg3);
    }

    protected b a(ViewGroup$LayoutParams arg3) {
        if(Build$VERSION.SDK_INT >= 19 && ((arg3 instanceof LinearLayout$LayoutParams))) {
            return new b(((LinearLayout$LayoutParams)arg3));
        }

        if((arg3 instanceof ViewGroup$MarginLayoutParams)) {
            return new b(((ViewGroup$MarginLayoutParams)arg3));
        }

        return new b(arg3);
    }

    void a(int arg4) {
        if(this.g != null) {
            int v0 = 0;
            int v1 = this.g.size();
            while(v0 < v1) {
                Object v2 = this.g.get(v0);
                if(v2 != null) {
                    ((android.support.design.widget.AppBarLayout$a)v2).a(this, arg4);
                }

                ++v0;
            }
        }
    }

    public void a(boolean arg2, boolean arg3) {
        this.a(arg2, arg3, true);
    }

    boolean a(boolean arg2) {
        if(this.j != arg2) {
            this.j = arg2;
            this.refreshDrawableState();
            return 1;
        }

        return 0;
    }

    private boolean b(boolean arg2) {
        if(this.i != arg2) {
            this.i = arg2;
            this.refreshDrawableState();
            return 1;
        }

        return 0;
    }

    boolean b() {
        return this.d;
    }

    boolean c() {
        boolean v0 = this.getTotalScrollRange() != 0 ? true : false;
        return v0;
    }

    protected boolean checkLayoutParams(ViewGroup$LayoutParams arg1) {
        return arg1 instanceof b;
    }

    public boolean d() {
        return this.k;
    }

    void e() {
        this.e = 0;
    }

    private boolean f() {
        int v0 = this.getChildCount();
        int v2;
        for(v2 = 0; v2 < v0; ++v2) {
            if(this.getChildAt(v2).getLayoutParams().c()) {
                return 1;
            }
        }

        return 0;
    }

    private void g() {
        this.a = -1;
        this.b = -1;
        this.c = -1;
    }

    protected ViewGroup$LayoutParams generateDefaultLayoutParams() {
        return this.a();
    }

    protected LinearLayout$LayoutParams generateDefaultLayoutParams() {
        return this.a();
    }

    public ViewGroup$LayoutParams generateLayoutParams(AttributeSet arg1) {
        return this.a(arg1);
    }

    protected ViewGroup$LayoutParams generateLayoutParams(ViewGroup$LayoutParams arg1) {
        return this.a(arg1);
    }

    public LinearLayout$LayoutParams generateLayoutParams(AttributeSet arg1) {
        return this.a(arg1);
    }

    protected LinearLayout$LayoutParams generateLayoutParams(ViewGroup$LayoutParams arg1) {
        return this.a(arg1);
    }

    int getDownNestedPreScrollRange() {
        if(this.b != -1) {
            return this.b;
        }

        int v0 = this.getChildCount() - 1;
        int v2 = 0;
        while(v0 >= 0) {
            View v3 = this.getChildAt(v0);
            ViewGroup$LayoutParams v4 = v3.getLayoutParams();
            int v5 = v3.getMeasuredHeight();
            int v6 = ((b)v4).a;
            if((v6 & 5) == 5) {
                v2 += ((b)v4).topMargin + ((b)v4).bottomMargin;
                if((v6 & 8) != 0) {
                    v2 += t.l(v3);
                }
                else {
                    int v3_1 = (v6 & 2) != 0 ? t.l(v3) : this.getTopInset();
                    v2 += v5 - v3_1;
                }
            }
            else {
                if(v2 <= 0) {
                    goto label_36;
                }

                break;
            }

        label_36:
            --v0;
        }

        v0 = Math.max(0, v2);
        this.b = v0;
        return v0;
    }

    int getDownNestedScrollRange() {
        if(this.c != -1) {
            return this.c;
        }

        int v0 = this.getChildCount();
        int v2 = 0;
        int v3 = 0;
        while(v2 < v0) {
            View v4 = this.getChildAt(v2);
            ViewGroup$LayoutParams v5 = v4.getLayoutParams();
            int v6 = v4.getMeasuredHeight() + (((b)v5).topMargin + ((b)v5).bottomMargin);
            int v5_1 = ((b)v5).a;
            if((v5_1 & 1) == 0) {
                break;
            }

            v3 += v6;
            if((v5_1 & 2) != 0) {
                v3 -= t.l(v4) + this.getTopInset();
            }
            else {
                ++v2;
                continue;
            }

            break;
        }

        v0 = Math.max(0, v3);
        this.c = v0;
        return v0;
    }

    public final int getMinimumHeightForVisibleOverlappingContent() {
        int v0 = this.getTopInset();
        int v1 = t.l(((View)this));
        if(v1 == 0) {
            v1 = this.getChildCount();
            v1 = v1 >= 1 ? t.l(this.getChildAt(v1 - 1)) : 0;
            if(v1 == 0) {
                goto label_16;
            }
        }

        return v1 * 2 + v0;
    label_16:
        return this.getHeight() / 3;
    }

    int getPendingAction() {
        return this.e;
    }

    @Deprecated public float getTargetElevation() {
        return 0;
    }

    final int getTopInset() {
        int v0 = this.f != null ? this.f.b() : 0;
        return v0;
    }

    public final int getTotalScrollRange() {
        if(this.a != -1) {
            return this.a;
        }

        int v0 = this.getChildCount();
        int v2 = 0;
        int v3 = 0;
        while(v2 < v0) {
            View v4 = this.getChildAt(v2);
            ViewGroup$LayoutParams v5 = v4.getLayoutParams();
            int v6 = v4.getMeasuredHeight();
            int v7 = ((b)v5).a;
            if((v7 & 1) == 0) {
                break;
            }

            v3 += v6 + ((b)v5).topMargin + ((b)v5).bottomMargin;
            if((v7 & 2) != 0) {
                v3 -= t.l(v4);
            }
            else {
                ++v2;
                continue;
            }

            break;
        }

        v0 = Math.max(0, v3 - this.getTopInset());
        this.a = v0;
        return v0;
    }

    int getUpNestedPreScrollRange() {
        return this.getTotalScrollRange();
    }

    protected int[] onCreateDrawableState(int arg4) {
        if(this.l == null) {
            this.l = new int[4];
        }

        int[] v0 = this.l;
        int[] v4 = super.onCreateDrawableState(arg4 + v0.length);
        int v2 = this.i ? android.support.design.a$b.state_liftable : -android.support.design.a$b.state_liftable;
        v0[0] = v2;
        v2 = !this.i || !this.j ? -android.support.design.a$b.state_lifted : android.support.design.a$b.state_lifted;
        v0[1] = v2;
        int v1 = 2;
        v2 = this.i ? android.support.design.a$b.state_collapsible : -android.support.design.a$b.state_collapsible;
        v0[v1] = v2;
        v1 = 3;
        v2 = !this.i || !this.j ? -android.support.design.a$b.state_collapsed : android.support.design.a$b.state_collapsed;
        v0[v1] = v2;
        return AppBarLayout.mergeDrawableStates(v4, v0);
    }

    protected void onLayout(boolean arg1, int arg2, int arg3, int arg4, int arg5) {
        super.onLayout(arg1, arg2, arg3, arg4, arg5);
        this.g();
        arg1 = false;
        this.d = false;
        arg2 = this.getChildCount();
        arg3 = 0;
        while(arg3 < arg2) {
            if(this.getChildAt(arg3).getLayoutParams().b() != null) {
                this.d = true;
            }
            else {
                ++arg3;
                continue;
            }

            break;
        }

        if(!this.h) {
            if((this.k) || (this.f())) {
                arg1 = true;
            }

            this.b(arg1);
        }
    }

    protected void onMeasure(int arg1, int arg2) {
        super.onMeasure(arg1, arg2);
        this.g();
    }

    public void setExpanded(boolean arg2) {
        this.a(arg2, t.A(((View)this)));
    }

    public void setLiftOnScroll(boolean arg1) {
        this.k = arg1;
    }

    public void setOrientation(int arg2) {
        if(arg2 == 1) {
            super.setOrientation(arg2);
            return;
        }

        throw new IllegalArgumentException("AppBarLayout is always vertical and does not support horizontal orientation");
    }

    @Deprecated public void setTargetElevation(float arg3) {
        if(Build$VERSION.SDK_INT >= 21) {
            v.a(((View)this), arg3);
        }
    }
}

