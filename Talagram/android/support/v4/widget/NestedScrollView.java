package android.support.v4.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Build$VERSION;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;
import android.support.v4.view.a.c;
import android.support.v4.view.a.e;
import android.support.v4.view.k;
import android.support.v4.view.l;
import android.support.v4.view.n;
import android.support.v4.view.o;
import android.support.v4.view.t;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.FocusFinder;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View$BaseSavedState;
import android.view.View$MeasureSpec;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup$LayoutParams;
import android.view.ViewGroup$MarginLayoutParams;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityRecord;
import android.view.animation.AnimationUtils;
import android.widget.EdgeEffect;
import android.widget.FrameLayout$LayoutParams;
import android.widget.FrameLayout;
import android.widget.OverScroller;
import android.widget.ScrollView;
import java.util.ArrayList;
import java.util.List;

public class NestedScrollView extends FrameLayout implements k, n {
    class SavedState extends View$BaseSavedState {
        final class android.support.v4.widget.NestedScrollView$SavedState$1 implements Parcelable$Creator {
            android.support.v4.widget.NestedScrollView$SavedState$1() {
                super();
            }

            public SavedState a(Parcel arg2) {
                return new SavedState(arg2);
            }

            public SavedState[] a(int arg1) {
                return new SavedState[arg1];
            }

            public Object createFromParcel(Parcel arg1) {
                return this.a(arg1);
            }

            public Object[] newArray(int arg1) {
                return this.a(arg1);
            }
        }

        public static final Parcelable$Creator CREATOR;
        public int a;

        static {
            SavedState.CREATOR = new android.support.v4.widget.NestedScrollView$SavedState$1();
        }

        SavedState(Parcelable arg1) {
            super(arg1);
        }

        SavedState(Parcel arg1) {
            super(arg1);
            this.a = arg1.readInt();
        }

        public String toString() {
            return "HorizontalScrollView.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " scrollPosition=" + this.a + "}";
        }

        public void writeToParcel(Parcel arg1, int arg2) {
            super.writeToParcel(arg1, arg2);
            arg1.writeInt(this.a);
        }
    }

    class a extends android.support.v4.view.a {
        a() {
            super();
        }

        public void onInitializeAccessibilityEvent(View arg2, AccessibilityEvent arg3) {
            super.onInitializeAccessibilityEvent(arg2, arg3);
            arg3.setClassName(ScrollView.class.getName());
            boolean v0 = ((NestedScrollView)arg2).getScrollRange() > 0 ? true : false;
            arg3.setScrollable(v0);
            arg3.setScrollX(((NestedScrollView)arg2).getScrollX());
            arg3.setScrollY(((NestedScrollView)arg2).getScrollY());
            e.a(((AccessibilityRecord)arg3), ((NestedScrollView)arg2).getScrollX());
            e.b(((AccessibilityRecord)arg3), ((NestedScrollView)arg2).getScrollRange());
        }

        public void onInitializeAccessibilityNodeInfo(View arg3, c arg4) {
            super.onInitializeAccessibilityNodeInfo(arg3, arg4);
            arg4.b(ScrollView.class.getName());
            if(((NestedScrollView)arg3).isEnabled()) {
                int v0 = ((NestedScrollView)arg3).getScrollRange();
                if(v0 > 0) {
                    arg4.i(true);
                    if(((NestedScrollView)arg3).getScrollY() > 0) {
                        arg4.a(8192);
                    }

                    if(((NestedScrollView)arg3).getScrollY() >= v0) {
                        return;
                    }

                    arg4.a(4096);
                }
            }
        }

        public boolean performAccessibilityAction(View arg3, int arg4, Bundle arg5) {
            if(super.performAccessibilityAction(arg3, arg4, arg5)) {
                return 1;
            }

            if(!((NestedScrollView)arg3).isEnabled()) {
                return 0;
            }

            if(arg4 != 4096) {
                if(arg4 != 8192) {
                    return 0;
                }

                arg4 = Math.max(((NestedScrollView)arg3).getScrollY() - (((NestedScrollView)arg3).getHeight() - ((NestedScrollView)arg3).getPaddingBottom() - ((NestedScrollView)arg3).getPaddingTop()), 0);
                if(arg4 != ((NestedScrollView)arg3).getScrollY()) {
                    ((NestedScrollView)arg3).c(0, arg4);
                    return 1;
                }

                return 0;
            }

            arg4 = Math.min(((NestedScrollView)arg3).getScrollY() + (((NestedScrollView)arg3).getHeight() - ((NestedScrollView)arg3).getPaddingBottom() - ((NestedScrollView)arg3).getPaddingTop()), ((NestedScrollView)arg3).getScrollRange());
            if(arg4 != ((NestedScrollView)arg3).getScrollY()) {
                ((NestedScrollView)arg3).c(0, arg4);
                return 1;
            }

            return 0;
        }
    }

    public interface b {
        void a(NestedScrollView arg1, int arg2, int arg3, int arg4, int arg5);
    }

    private float A;
    private b B;
    private long a;
    private final Rect b;
    private OverScroller c;
    private EdgeEffect d;
    private EdgeEffect e;
    private int f;
    private boolean g;
    private boolean h;
    private View i;
    private boolean j;
    private VelocityTracker k;
    private boolean l;
    private boolean m;
    private int n;
    private int o;
    private int p;
    private int q;
    private final int[] r;
    private final int[] s;
    private int t;
    private int u;
    private SavedState v;
    private static final a w;
    private static final int[] x;
    private final o y;
    private final l z;

    static {
        NestedScrollView.w = new a();
        NestedScrollView.x = new int[]{16843130};
    }

    public NestedScrollView(Context arg2) {
        this(arg2, null);
    }

    public NestedScrollView(Context arg2, AttributeSet arg3) {
        this(arg2, arg3, 0);
    }

    public NestedScrollView(Context arg5, AttributeSet arg6, int arg7) {
        super(arg5, arg6, arg7);
        this.b = new Rect();
        this.g = true;
        this.h = false;
        this.i = null;
        this.j = false;
        this.m = true;
        this.q = -1;
        this.r = new int[2];
        this.s = new int[2];
        this.a();
        TypedArray v5 = arg5.obtainStyledAttributes(arg6, NestedScrollView.x, arg7, 0);
        this.setFillViewport(v5.getBoolean(0, false));
        v5.recycle();
        this.y = new o(((ViewGroup)this));
        this.z = new l(((View)this));
        this.setNestedScrollingEnabled(true);
        t.a(((View)this), NestedScrollView.w);
    }

    public boolean a(KeyEvent arg6) {
        this.b.setEmpty();
        boolean v1 = false;
        int v2 = 130;
        if(!this.b()) {
            if((this.isFocused()) && arg6.getKeyCode() != 4) {
                View v6 = this.findFocus();
                if((((NestedScrollView)v6)) == this) {
                    v6 = null;
                }

                v6 = FocusFinder.getInstance().findNextFocus(((ViewGroup)this), v6, v2);
                if(v6 == null) {
                    return v1;
                }

                if((((NestedScrollView)v6)) == this) {
                    return v1;
                }

                if(!v6.requestFocus(v2)) {
                    return v1;
                }

                v1 = true;
            }

            return v1;
        }

        if(arg6.getAction() == 0) {
            int v0 = arg6.getKeyCode();
            int v4 = 33;
            if(v0 != 62) {
                switch(v0) {
                    case 19: {
                        goto label_36;
                    }
                    case 20: {
                        goto label_30;
                    }
                }

                return v1;
            label_36:
                if(!arg6.isAltPressed()) {
                    v1 = this.d(v4);
                }
                else {
                    return this.c(v4);
                label_30:
                    v1 = !arg6.isAltPressed() ? this.d(v2) : this.c(v2);
                }
            }
            else {
                if(arg6.isShiftPressed()) {
                    v2 = 33;
                }

                this.b(v2);
            }
        }

        return v1;
    }

    private void a() {
        this.c = new OverScroller(this.getContext());
        this.setFocusable(true);
        this.setDescendantFocusability(262144);
        this.setWillNotDraw(false);
        ViewConfiguration v0 = ViewConfiguration.get(this.getContext());
        this.n = v0.getScaledTouchSlop();
        this.o = v0.getScaledMinimumFlingVelocity();
        this.p = v0.getScaledMaximumFlingVelocity();
    }

    private View a(boolean arg13, int arg14, int arg15) {
        ArrayList v0 = this.getFocusables(2);
        int v1 = ((List)v0).size();
        Object v4 = null;
        int v3 = 0;
        int v5 = 0;
        while(v3 < v1) {
            Object v6 = ((List)v0).get(v3);
            int v7 = ((View)v6).getTop();
            int v8 = ((View)v6).getBottom();
            if(arg14 < v8 && v7 < arg15) {
                int v10 = arg14 >= v7 || v8 >= arg15 ? 0 : 1;
                if(v4 == null) {
                    v4 = v6;
                    v5 = v10;
                    goto label_43;
                }

                if(!arg13 || v7 >= ((View)v4).getTop()) {
                    if(!arg13 && v8 > ((View)v4).getBottom()) {
                    label_30:
                        v7 = 1;
                        goto label_33;
                    }

                    v7 = 0;
                }
                else {
                    goto label_30;
                }

            label_33:
                if(v5 == 0) {
                    if(v10 != 0) {
                        v4 = v6;
                        v5 = 1;
                    }
                    else if(v7 != 0) {
                        goto label_42;
                    }

                    goto label_43;
                }
                else if(v10 == 0) {
                    goto label_43;
                }
                else if(v7 != 0) {
                }
                else {
                    goto label_43;
                }

            label_42:
                v4 = v6;
            }

        label_43:
            ++v3;
        }

        return ((View)v4);
    }

    private void a(MotionEvent arg4) {
        int v0 = arg4.getActionIndex();
        if(arg4.getPointerId(v0) == this.q) {
            v0 = v0 == 0 ? 1 : 0;
            this.f = ((int)arg4.getY(v0));
            this.q = arg4.getPointerId(v0);
            if(this.k == null) {
                return;
            }

            this.k.clear();
        }
    }

    private boolean a(int arg7, int arg8, int arg9) {
        NestedScrollView v5_1;
        int v0 = this.getHeight();
        int v1 = this.getScrollY();
        v0 += v1;
        boolean v2 = false;
        boolean v4 = arg7 == 33 ? true : false;
        View v5 = this.a(v4, arg8, arg9);
        if(v5 == null) {
            v5_1 = this;
        }

        if(arg8 < v1 || arg9 > v0) {
            if(v4) {
                arg8 -= v1;
            }
            else {
                arg8 = arg9 - v0;
            }

            this.f(arg8);
            v2 = true;
        }
        else {
        }

        if((((View)v5_1)) != this.findFocus()) {
            ((View)v5_1).requestFocus(arg7);
        }

        return v2;
    }

    private boolean a(Rect arg3, boolean arg4) {
        int v3 = this.a(arg3);
        boolean v1 = v3 != 0 ? true : false;
        if(v1) {
            if(arg4) {
                this.scrollBy(0, v3);
            }
            else {
                this.b(0, v3);
            }
        }

        return v1;
    }

    protected int a(Rect arg11) {
        int v1 = 0;
        if(this.getChildCount() == 0) {
            return 0;
        }

        int v0 = this.getHeight();
        int v2 = this.getScrollY();
        int v3 = v2 + v0;
        int v4 = this.getVerticalFadingEdgeLength();
        if(arg11.top > 0) {
            v2 += v4;
        }

        View v5 = this.getChildAt(0);
        ViewGroup$LayoutParams v6 = v5.getLayoutParams();
        v4 = arg11.bottom < v5.getHeight() + ((FrameLayout$LayoutParams)v6).topMargin + ((FrameLayout$LayoutParams)v6).bottomMargin ? v3 - v4 : v3;
        if(arg11.bottom <= v4 || arg11.top <= v2) {
            if(arg11.top >= v2) {
                return v1;
            }

            if(arg11.bottom >= v4) {
                return v1;
            }

            v1 = arg11.height() > v0 ? -(v4 - arg11.bottom) : -(v2 - arg11.top);
            v1 = Math.max(v1, -this.getScrollY());
        }
        else {
            int v11 = arg11.height() > v0 ? arg11.top - v2 : arg11.bottom - v4;
            v11 = v11;
            v1 = Math.min(v11, v5.getBottom() + ((FrameLayout$LayoutParams)v6).bottomMargin - v3);
        }

        return v1;
    }

    private boolean a(View arg3) {
        return this.a(arg3, 0, this.getHeight()) ^ 1;
    }

    private boolean a(View arg2, int arg3, int arg4) {
        arg2.getDrawingRect(this.b);
        this.offsetDescendantRectToMyCoords(arg2, this.b);
        boolean v2 = this.b.bottom + arg3 < this.getScrollY() || this.b.top - arg3 > this.getScrollY() + arg4 ? false : true;
        return v2;
    }

    private static boolean a(View arg2, View arg3) {
        boolean v0 = true;
        if(arg2 == arg3) {
            return 1;
        }

        ViewParent v2 = arg2.getParent();
        if(!(v2 instanceof ViewGroup) || !NestedScrollView.a(((View)v2), arg3)) {
            v0 = false;
        }
        else {
        }

        return v0;
    }

    public void a(View arg8, int arg9, int arg10, int arg11, int arg12, int arg13) {
        int v8 = this.getScrollY();
        this.scrollBy(0, arg12);
        int v2 = this.getScrollY() - v8;
        this.a(0, v2, 0, arg12 - v2, null, arg13);
    }

    public boolean a(int arg8, int arg9, int arg10, int arg11, int[] arg12, int arg13) {
        return this.z.a(arg8, arg9, arg10, arg11, arg12, arg13);
    }

    public void a(View arg7, int arg8, int arg9, int[] arg10, int arg11) {
        this.a(arg8, arg9, arg10, null, arg11);
    }

    public boolean a(int arg7, int arg8, int[] arg9, int[] arg10, int arg11) {
        return this.z.a(arg7, arg8, arg9, arg10, arg11);
    }

    public boolean a(int arg2) {
        return this.z.a(arg2);
    }

    public boolean a(int arg2, int arg3) {
        return this.z.a(arg2, arg3);
    }

    boolean a(int arg13, int arg14, int arg15, int arg16, int arg17, int arg18, int arg19, int arg20, boolean arg21) {
        boolean v2_1;
        boolean v1_1;
        int v6;
        NestedScrollView v0 = this;
        int v1 = this.getOverScrollMode();
        boolean v4 = false;
        int v2 = this.computeHorizontalScrollRange() > this.computeHorizontalScrollExtent() ? 1 : 0;
        int v3 = this.computeVerticalScrollRange() > this.computeVerticalScrollExtent() ? 1 : 0;
        if(v1 != 0) {
            if(v1 == 1 && v2 != 0) {
                goto label_22;
            }

            v2 = 0;
        }
        else {
        label_22:
            v2 = 1;
        }

        if(v1 != 0) {
            if(v1 == 1 && v3 != 0) {
                goto label_29;
            }

            v6 = 0;
        }
        else {
        label_29:
            v6 = 1;
        }

        v1 = arg15 + arg13;
        int v7 = v2 == 0 ? 0 : arg19;
        v2 = arg16 + arg14;
        v3 = v6 == 0 ? 0 : arg20;
        v6 = -v7;
        v7 += arg17;
        int v8 = -v3;
        v3 += arg18;
        if(v1 > v7) {
            v6 = v7;
            goto label_46;
        }
        else if(v1 < v6) {
        label_46:
            v1_1 = true;
        }
        else {
            v6 = v1;
            v1_1 = false;
        }

        if(v2 > v3) {
            v8 = v3;
            goto label_54;
        }
        else if(v2 < v8) {
        label_54:
            v2_1 = true;
        }
        else {
            v8 = v2;
            v2_1 = false;
        }

        if((v2_1) && !this.a(1)) {
            v0.c.springBack(v6, v8, 0, 0, 0, this.getScrollRange());
        }

        this.onOverScrolled(v6, v8, v1_1, v2_1);
        if((v1_1) || (v2_1)) {
            v4 = true;
        }

        return v4;
    }

    public boolean a(View arg1, View arg2, int arg3, int arg4) {
        boolean v1 = (arg3 & 2) != 0 ? true : false;
        return v1;
    }

    public void addView(View arg2) {
        if(this.getChildCount() <= 0) {
            super.addView(arg2);
            return;
        }

        throw new IllegalStateException("ScrollView can host only one direct child");
    }

    public void addView(View arg2, int arg3) {
        if(this.getChildCount() <= 0) {
            super.addView(arg2, arg3);
            return;
        }

        throw new IllegalStateException("ScrollView can host only one direct child");
    }

    public void addView(View arg2, int arg3, ViewGroup$LayoutParams arg4) {
        if(this.getChildCount() <= 0) {
            super.addView(arg2, arg3, arg4);
            return;
        }

        throw new IllegalStateException("ScrollView can host only one direct child");
    }

    public void addView(View arg2, ViewGroup$LayoutParams arg3) {
        if(this.getChildCount() <= 0) {
            super.addView(arg2, arg3);
            return;
        }

        throw new IllegalStateException("ScrollView can host only one direct child");
    }

    public final void b(int arg6, int arg7) {
        if(this.getChildCount() == 0) {
            return;
        }

        if(AnimationUtils.currentAnimationTimeMillis() - this.a > 250) {
            View v0 = this.getChildAt(0);
            ViewGroup$LayoutParams v1 = v0.getLayoutParams();
            int v0_1 = v0.getHeight() + ((FrameLayout$LayoutParams)v1).topMargin + ((FrameLayout$LayoutParams)v1).bottomMargin;
            int v1_1 = this.getHeight() - this.getPaddingTop() - this.getPaddingBottom();
            int v2 = this.getScrollY();
            arg7 = Math.max(0, Math.min(arg7 + v2, Math.max(0, v0_1 - v1_1))) - v2;
            this.u = this.getScrollY();
            this.c.startScroll(this.getScrollX(), v2, 0, arg7);
            t.d(((View)this));
        }
        else {
            if(!this.c.isFinished()) {
                this.c.abortAnimation();
            }

            this.scrollBy(arg6, arg7);
        }

        this.a = AnimationUtils.currentAnimationTimeMillis();
    }

    private static int b(int arg1, int arg2, int arg3) {
        if(arg2 < arg3) {
            if(arg1 < 0) {
            }
            else if(arg2 + arg1 > arg3) {
                return arg3 - arg2;
            }
            else {
                return arg1;
            }
        }

        return 0;
    }

    private void b(View arg2) {
        arg2.getDrawingRect(this.b);
        this.offsetDescendantRectToMyCoords(arg2, this.b);
        int v2 = this.a(this.b);
        if(v2 != 0) {
            this.scrollBy(0, v2);
        }
    }

    private boolean b() {
        boolean v1 = false;
        if(this.getChildCount() > 0) {
            View v0 = this.getChildAt(0);
            ViewGroup$LayoutParams v2 = v0.getLayoutParams();
            if(v0.getHeight() + ((FrameLayout$LayoutParams)v2).topMargin + ((FrameLayout$LayoutParams)v2).bottomMargin > this.getHeight() - this.getPaddingTop() - this.getPaddingBottom()) {
                v1 = true;
            }
        }

        return v1;
    }

    public boolean b(int arg5) {
        Rect v1;
        int v0 = 0;
        int v2 = arg5 == 130 ? 1 : 0;
        int v3 = this.getHeight();
        if(v2 != 0) {
            this.b.top = this.getScrollY() + v3;
            v0 = this.getChildCount();
            if(v0 > 0) {
                View v0_1 = this.getChildAt(v0 - 1);
                v0 = v0_1.getBottom() + v0_1.getLayoutParams().bottomMargin + this.getPaddingBottom();
                if(this.b.top + v3 > v0) {
                    v1 = this.b;
                    v0 -= v3;
                    goto label_38;
                }
            }
        }
        else {
            this.b.top = this.getScrollY() - v3;
            if(this.b.top < 0) {
                v1 = this.b;
            label_38:
                v1.top = v0;
            }
        }

        this.b.bottom = this.b.top + v3;
        return this.a(arg5, this.b.top, this.b.bottom);
    }

    public void b(View arg2, View arg3, int arg4, int arg5) {
        this.y.a(arg2, arg3, arg4, arg5);
        this.a(2, arg5);
    }

    private void c() {
        if(this.k == null) {
            this.k = VelocityTracker.obtain();
        }
        else {
            this.k.clear();
        }
    }

    public boolean c(int arg6) {
        int v2 = arg6 == 130 ? 1 : 0;
        int v3 = this.getHeight();
        this.b.top = 0;
        this.b.bottom = v3;
        if(v2 != 0) {
            int v0 = this.getChildCount();
            if(v0 > 0) {
                View v0_1 = this.getChildAt(v0 - 1);
                this.b.bottom = v0_1.getBottom() + v0_1.getLayoutParams().bottomMargin + this.getPaddingBottom();
                this.b.top = this.b.bottom - v3;
            }
        }

        return this.a(arg6, this.b.top, this.b.bottom);
    }

    public final void c(int arg2, int arg3) {
        this.b(arg2 - this.getScrollX(), arg3 - this.getScrollY());
    }

    public void c(View arg2, int arg3) {
        this.y.a(arg2, arg3);
        this.stopNestedScroll(arg3);
    }

    public int computeHorizontalScrollExtent() {
        return super.computeHorizontalScrollExtent();
    }

    public int computeHorizontalScrollOffset() {
        return super.computeHorizontalScrollOffset();
    }

    public int computeHorizontalScrollRange() {
        return super.computeHorizontalScrollRange();
    }

    public void computeScroll() {
        EdgeEffect v0_1;
        int v17;
        NestedScrollView v10 = this;
        if(v10.c.computeScrollOffset()) {
            v10.c.getCurrX();
            int v13 = v10.c.getCurrY();
            int v6 = v13 - v10.u;
            if(this.a(0, v6, v10.s, null, 1)) {
                v6 -= v10.s[1];
            }

            int v14 = v6;
            if(v14 != 0) {
                int v15 = this.getScrollRange();
                int v9 = this.getScrollY();
                int v11 = v9;
                this.a(0, v14, this.getScrollX(), v9, 0, v15, 0, 0, false);
                int v2 = this.getScrollY() - v11;
                if(!this.a(0, v2, 0, v14 - v2, null, 1)) {
                    int v0 = this.getOverScrollMode();
                    if(v0 != 0) {
                        if(v0 == 1 && v15 > 0) {
                            goto label_55;
                        }

                        v17 = 0;
                    }
                    else {
                    label_55:
                        v17 = 1;
                    }

                    if(v17 == 0) {
                        goto label_70;
                    }

                    this.g();
                    if(v13 <= 0 && v11 > 0) {
                        v0_1 = v10.d;
                    }
                    else if(v13 < v15) {
                        goto label_70;
                    }
                    else if(v11 < v15) {
                        v0_1 = v10.e;
                    }
                    else {
                        goto label_70;
                    }

                    v0_1.onAbsorb(((int)v10.c.getCurrVelocity()));
                }
            }

        label_70:
            v10.u = v13;
            t.d(((View)this));
        }
        else {
            if(v10.a(1)) {
                v10.stopNestedScroll(1);
            }

            v10.u = 0;
        }
    }

    public int computeVerticalScrollExtent() {
        return super.computeVerticalScrollExtent();
    }

    public int computeVerticalScrollOffset() {
        return Math.max(0, super.computeVerticalScrollOffset());
    }

    public int computeVerticalScrollRange() {
        int v0 = this.getChildCount();
        int v1 = this.getHeight() - this.getPaddingBottom() - this.getPaddingTop();
        if(v0 == 0) {
            return v1;
        }

        View v2 = this.getChildAt(0);
        int v2_1 = v2.getBottom() + v2.getLayoutParams().bottomMargin;
        int v3 = this.getScrollY();
        v0 = Math.max(0, v2_1 - v1);
        if(v3 < 0) {
            v2_1 -= v3;
        }
        else if(v3 > v0) {
            v2_1 += v3 - v0;
        }

        return v2_1;
    }

    private void d() {
        if(this.k == null) {
            this.k = VelocityTracker.obtain();
        }
    }

    private boolean d(int arg5, int arg6) {
        boolean v1 = false;
        if(this.getChildCount() > 0) {
            int v0 = this.getScrollY();
            View v2 = this.getChildAt(0);
            if(arg6 >= v2.getTop() - v0 && arg6 < v2.getBottom() - v0 && arg5 >= v2.getLeft() && arg5 < v2.getRight()) {
                v1 = true;
            }
        }

        return v1;
    }

    public boolean d(int arg8) {
        View v0 = this.findFocus();
        if((((NestedScrollView)v0)) == this) {
            v0 = null;
        }

        View v1 = FocusFinder.getInstance().findNextFocus(((ViewGroup)this), v0, arg8);
        int v2 = this.getMaxScrollAmount();
        if(v1 == null || !this.a(v1, v2, this.getHeight())) {
            int v4 = 130;
            if(arg8 == 33 && this.getScrollY() < v2) {
                v2 = this.getScrollY();
            }
            else if(arg8 == v4 && this.getChildCount() > 0) {
                v1 = this.getChildAt(0);
                v2 = Math.min(v1.getBottom() + v1.getLayoutParams().bottomMargin - (this.getScrollY() + this.getHeight() - this.getPaddingBottom()), v2);
            }

            if(v2 == 0) {
                return 0;
            }

            if(arg8 == v4) {
            }
            else {
                v2 = -v2;
            }

            this.f(v2);
        }
        else {
            v1.getDrawingRect(this.b);
            this.offsetDescendantRectToMyCoords(v1, this.b);
            this.f(this.a(this.b));
            v1.requestFocus(arg8);
        }

        if(v0 != null && (v0.isFocused()) && (this.a(v0))) {
            arg8 = this.getDescendantFocusability();
            this.setDescendantFocusability(131072);
            this.requestFocus();
            this.setDescendantFocusability(arg8);
        }

        return 1;
    }

    public boolean dispatchKeyEvent(KeyEvent arg2) {
        boolean v2 = (super.dispatchKeyEvent(arg2)) || (this.a(arg2)) ? true : false;
        return v2;
    }

    public boolean dispatchNestedFling(float arg2, float arg3, boolean arg4) {
        return this.z.a(arg2, arg3, arg4);
    }

    public boolean dispatchNestedPreFling(float arg2, float arg3) {
        return this.z.a(arg2, arg3);
    }

    public boolean dispatchNestedPreScroll(int arg7, int arg8, int[] arg9, int[] arg10) {
        return this.a(arg7, arg8, arg9, arg10, 0);
    }

    public boolean dispatchNestedScroll(int arg8, int arg9, int arg10, int arg11, int[] arg12) {
        return this.a(arg8, arg9, arg10, arg11, arg12, 0);
    }

    public void draw(Canvas arg11) {
        int v7;
        int v5;
        int v4;
        int v1;
        super.draw(arg11);
        if(this.d != null) {
            int v0 = this.getScrollY();
            int v2 = 0;
            int v3 = 21;
            if(!this.d.isFinished()) {
                v1 = arg11.save();
                v4 = this.getWidth();
                v5 = this.getHeight();
                int v6 = Math.min(0, v0);
                if(Build$VERSION.SDK_INT < v3 || (this.getClipToPadding())) {
                    v4 -= this.getPaddingLeft() + this.getPaddingRight();
                    v7 = this.getPaddingLeft();
                }
                else {
                    v7 = 0;
                }

                if(Build$VERSION.SDK_INT >= v3 && (this.getClipToPadding())) {
                    v5 -= this.getPaddingTop() + this.getPaddingBottom();
                    v6 += this.getPaddingTop();
                }

                arg11.translate(((float)v7), ((float)v6));
                this.d.setSize(v4, v5);
                if(this.d.draw(arg11)) {
                    t.d(((View)this));
                }

                arg11.restoreToCount(v1);
            }

            if(this.e.isFinished()) {
                return;
            }

            v1 = arg11.save();
            v4 = this.getWidth();
            v5 = this.getHeight();
            v0 = Math.max(this.getScrollRange(), v0) + v5;
            if(Build$VERSION.SDK_INT < v3 || (this.getClipToPadding())) {
                v4 -= this.getPaddingLeft() + this.getPaddingRight();
                v2 = this.getPaddingLeft();
            }

            if(Build$VERSION.SDK_INT >= v3 && (this.getClipToPadding())) {
                v5 -= this.getPaddingTop() + this.getPaddingBottom();
                v0 -= this.getPaddingBottom();
            }

            arg11.translate(((float)(v2 - v4)), ((float)v0));
            arg11.rotate(180f, ((float)v4), 0f);
            this.e.setSize(v4, v5);
            if(this.e.draw(arg11)) {
                t.d(((View)this));
            }

            arg11.restoreToCount(v1);
        }
    }

    private void e() {
        if(this.k != null) {
            this.k.recycle();
            this.k = null;
        }
    }

    public void e(int arg14) {
        if(this.getChildCount() > 0) {
            this.a(2, 1);
            this.c.fling(this.getScrollX(), this.getScrollY(), 0, arg14, 0, 0, -2147483648, 2147483647, 0, 0);
            this.u = this.getScrollY();
            t.d(((View)this));
        }
    }

    private void f(int arg3) {
        if(arg3 != 0) {
            if(this.m) {
                this.b(0, arg3);
            }
            else {
                this.scrollBy(0, arg3);
            }
        }
    }

    private void f() {
        this.j = false;
        this.e();
        this.stopNestedScroll(0);
        if(this.d != null) {
            this.d.onRelease();
            this.e.onRelease();
        }
    }

    private void g() {
        if(this.getOverScrollMode() == 2) {
            this.d = null;
            this.e = null;
        }
        else if(this.d == null) {
            Context v0 = this.getContext();
            this.d = new EdgeEffect(v0);
            this.e = new EdgeEffect(v0);
        }
    }

    private void g(int arg5) {
        boolean v0_1;
        int v0 = this.getScrollY();
        if(v0 > 0 || arg5 > 0) {
            if(v0 >= this.getScrollRange()) {
                if(arg5 < 0) {
                }
                else {
                label_7:
                    v0_1 = false;
                    goto label_10;
                }
            }

            v0_1 = true;
        }
        else {
            goto label_7;
        }

    label_10:
        float v1 = ((float)arg5);
        if(!this.dispatchNestedPreFling(0f, v1)) {
            this.dispatchNestedFling(0f, v1, v0_1);
            this.e(arg5);
        }
    }

    protected float getBottomFadingEdgeStrength() {
        if(this.getChildCount() == 0) {
            return 0;
        }

        View v0 = this.getChildAt(0);
        ViewGroup$LayoutParams v1 = v0.getLayoutParams();
        int v2 = this.getVerticalFadingEdgeLength();
        int v0_1 = v0.getBottom() + ((FrameLayout$LayoutParams)v1).bottomMargin - this.getScrollY() - (this.getHeight() - this.getPaddingBottom());
        if(v0_1 < v2) {
            return (((float)v0_1)) / (((float)v2));
        }

        return 1f;
    }

    public int getMaxScrollAmount() {
        return ((int)((((float)this.getHeight())) * 0.5f));
    }

    public int getNestedScrollAxes() {
        return this.y.a();
    }

    int getScrollRange() {
        int v1 = 0;
        if(this.getChildCount() > 0) {
            View v0 = this.getChildAt(0);
            ViewGroup$LayoutParams v2 = v0.getLayoutParams();
            v1 = Math.max(0, v0.getHeight() + ((FrameLayout$LayoutParams)v2).topMargin + ((FrameLayout$LayoutParams)v2).bottomMargin - (this.getHeight() - this.getPaddingTop() - this.getPaddingBottom()));
        }

        return v1;
    }

    protected float getTopFadingEdgeStrength() {
        if(this.getChildCount() == 0) {
            return 0;
        }

        int v0 = this.getVerticalFadingEdgeLength();
        int v1 = this.getScrollY();
        if(v1 < v0) {
            return (((float)v1)) / (((float)v0));
        }

        return 1f;
    }

    private float getVerticalScrollFactorCompat() {
        if(this.A == 0f) {
            TypedValue v0 = new TypedValue();
            Context v1 = this.getContext();
            if(v1.getTheme().resolveAttribute(16842829, v0, true)) {
                this.A = v0.getDimension(v1.getResources().getDisplayMetrics());
            }
            else {
                throw new IllegalStateException("Expected theme to define listPreferredItemHeight.");
            }
        }

        return this.A;
    }

    public boolean hasNestedScrollingParent() {
        return this.a(0);
    }

    public boolean isNestedScrollingEnabled() {
        return this.z.a();
    }

    protected void measureChild(View arg3, int arg4, int arg5) {
        arg3.measure(NestedScrollView.getChildMeasureSpec(arg4, this.getPaddingLeft() + this.getPaddingRight(), arg3.getLayoutParams().width), View$MeasureSpec.makeMeasureSpec(0, 0));
    }

    protected void measureChildWithMargins(View arg2, int arg3, int arg4, int arg5, int arg6) {
        ViewGroup$LayoutParams v5 = arg2.getLayoutParams();
        arg2.measure(NestedScrollView.getChildMeasureSpec(arg3, this.getPaddingLeft() + this.getPaddingRight() + ((ViewGroup$MarginLayoutParams)v5).leftMargin + ((ViewGroup$MarginLayoutParams)v5).rightMargin + arg4, ((ViewGroup$MarginLayoutParams)v5).width), View$MeasureSpec.makeMeasureSpec(((ViewGroup$MarginLayoutParams)v5).topMargin + ((ViewGroup$MarginLayoutParams)v5).bottomMargin, 0));
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.h = false;
    }

    public boolean onGenericMotionEvent(MotionEvent arg4) {
        if((arg4.getSource() & 2) != 0) {
            if(arg4.getAction() != 8) {
            }
            else if(!this.j) {
                float v4 = arg4.getAxisValue(9);
                if(v4 != 0f) {
                    int v4_1 = ((int)(v4 * this.getVerticalScrollFactorCompat()));
                    int v0 = this.getScrollRange();
                    int v2 = this.getScrollY();
                    v4_1 = v2 - v4_1;
                    if(v4_1 < 0) {
                        v4_1 = 0;
                    }
                    else if(v4_1 > v0) {
                        v4_1 = v0;
                    }

                    if(v4_1 == v2) {
                        return 0;
                    }

                    super.scrollTo(this.getScrollX(), v4_1);
                    return 1;
                }
            }
        }

        return 0;
    }

    public boolean onInterceptTouchEvent(MotionEvent arg13) {
        int v0 = arg13.getAction();
        int v1 = 2;
        if(v0 == v1 && (this.j)) {
            return 1;
        }

        v0 &= 255;
        if(v0 != 6) {
            int v3 = -1;
            switch(v0) {
                case 0: {
                    goto label_65;
                }
                case 2: {
                    goto label_14;
                }
                case 1: 
                case 3: {
                    goto label_50;
                }
            }

            goto label_89;
        label_65:
            v0 = ((int)arg13.getY());
            if(!this.d(((int)arg13.getX()), v0)) {
                this.j = false;
                this.e();
            }
            else {
                this.f = v0;
                this.q = arg13.getPointerId(0);
                this.c();
                this.k.addMovement(arg13);
                this.c.computeScrollOffset();
                this.j = this.c.isFinished() ^ 1;
                this.a(v1, 0);
                goto label_89;
            label_50:
                this.j = false;
                this.q = v3;
                this.e();
                if(this.c.springBack(this.getScrollX(), this.getScrollY(), 0, 0, 0, this.getScrollRange())) {
                    t.d(((View)this));
                }

                this.stopNestedScroll(0);
                goto label_89;
            label_14:
                v0 = this.q;
                if(v0 == v3) {
                    goto label_89;
                }

                int v5 = arg13.findPointerIndex(v0);
                if(v5 == v3) {
                    Log.e("NestedScrollView", "Invalid pointerId=" + v0 + " in onInterceptTouchEvent");
                    goto label_89;
                }

                v0 = ((int)arg13.getY(v5));
                if(Math.abs(v0 - this.f) <= this.n) {
                    goto label_89;
                }

                if((v1 & this.getNestedScrollAxes()) != 0) {
                    goto label_89;
                }

                this.j = true;
                this.f = v0;
                this.d();
                this.k.addMovement(arg13);
                this.t = 0;
                ViewParent v13 = this.getParent();
                if(v13 == null) {
                    goto label_89;
                }

                v13.requestDisallowInterceptTouchEvent(true);
            }
        }
        else {
            this.a(arg13);
        }

    label_89:
        return this.j;
    }

    protected void onLayout(boolean arg2, int arg3, int arg4, int arg5, int arg6) {
        super.onLayout(arg2, arg3, arg4, arg5, arg6);
        int v2 = 0;
        this.g = false;
        if(this.i != null && (NestedScrollView.a(this.i, ((View)this)))) {
            this.b(this.i);
        }

        View v3 = null;
        this.i = v3;
        if(!this.h) {
            if(this.v != null) {
                this.scrollTo(this.getScrollX(), this.v.a);
                this.v = ((SavedState)v3);
            }

            if(this.getChildCount() > 0) {
                View v2_1 = this.getChildAt(0);
                ViewGroup$LayoutParams v3_1 = v2_1.getLayoutParams();
                v2 = v2_1.getMeasuredHeight() + ((FrameLayout$LayoutParams)v3_1).topMargin + ((FrameLayout$LayoutParams)v3_1).bottomMargin;
            }

            arg6 = arg6 - arg4 - this.getPaddingTop() - this.getPaddingBottom();
            arg3 = this.getScrollY();
            v2 = NestedScrollView.b(arg3, arg6, v2);
            if(v2 == arg3) {
                goto label_40;
            }

            this.scrollTo(this.getScrollX(), v2);
        }

    label_40:
        this.scrollTo(this.getScrollX(), this.getScrollY());
        this.h = true;
    }

    protected void onMeasure(int arg5, int arg6) {
        super.onMeasure(arg5, arg6);
        if(!this.l) {
            return;
        }

        if(View$MeasureSpec.getMode(arg6) == 0) {
            return;
        }

        if(this.getChildCount() > 0) {
            View v6 = this.getChildAt(0);
            ViewGroup$LayoutParams v0 = v6.getLayoutParams();
            int v1 = v6.getMeasuredHeight();
            int v2 = this.getMeasuredHeight() - this.getPaddingTop() - this.getPaddingBottom() - ((FrameLayout$LayoutParams)v0).topMargin - ((FrameLayout$LayoutParams)v0).bottomMargin;
            if(v1 < v2) {
                v6.measure(NestedScrollView.getChildMeasureSpec(arg5, this.getPaddingLeft() + this.getPaddingRight() + ((FrameLayout$LayoutParams)v0).leftMargin + ((FrameLayout$LayoutParams)v0).rightMargin, ((FrameLayout$LayoutParams)v0).width), View$MeasureSpec.makeMeasureSpec(v2, 1073741824));
            }
        }
    }

    public boolean onNestedFling(View arg1, float arg2, float arg3, boolean arg4) {
        if(!arg4) {
            this.g(((int)arg3));
            return 1;
        }

        return 0;
    }

    public boolean onNestedPreFling(View arg1, float arg2, float arg3) {
        return this.dispatchNestedPreFling(arg2, arg3);
    }

    public void onNestedPreScroll(View arg7, int arg8, int arg9, int[] arg10) {
        this.a(arg7, arg8, arg9, arg10, 0);
    }

    public void onNestedScroll(View arg8, int arg9, int arg10, int arg11, int arg12) {
        this.a(arg8, arg9, arg10, arg11, arg12, 0);
    }

    public void onNestedScrollAccepted(View arg2, View arg3, int arg4) {
        this.b(arg2, arg3, arg4, 0);
    }

    protected void onOverScrolled(int arg1, int arg2, boolean arg3, boolean arg4) {
        super.scrollTo(arg1, arg2);
    }

    protected boolean onRequestFocusInDescendants(int arg4, Rect arg5) {
        if(arg4 == 2) {
            arg4 = 130;
        }
        else if(arg4 == 1) {
            arg4 = 33;
        }

        View v0 = arg5 == null ? FocusFinder.getInstance().findNextFocus(((ViewGroup)this), null, arg4) : FocusFinder.getInstance().findNextFocusFromRect(((ViewGroup)this), arg5, arg4);
        if(v0 == null) {
            return 0;
        }

        if(this.a(v0)) {
            return 0;
        }

        return v0.requestFocus(arg4, arg5);
    }

    protected void onRestoreInstanceState(Parcelable arg2) {
        if(!(arg2 instanceof SavedState)) {
            super.onRestoreInstanceState(arg2);
            return;
        }

        super.onRestoreInstanceState(((SavedState)arg2).getSuperState());
        this.v = ((SavedState)arg2);
        this.requestLayout();
    }

    protected Parcelable onSaveInstanceState() {
        SavedState v1 = new SavedState(super.onSaveInstanceState());
        v1.a = this.getScrollY();
        return ((Parcelable)v1);
    }

    protected void onScrollChanged(int arg8, int arg9, int arg10, int arg11) {
        super.onScrollChanged(arg8, arg9, arg10, arg11);
        if(this.B != null) {
            this.B.a(this, arg8, arg9, arg10, arg11);
        }
    }

    protected void onSizeChanged(int arg1, int arg2, int arg3, int arg4) {
        super.onSizeChanged(arg1, arg2, arg3, arg4);
        View v1 = this.findFocus();
        if(v1 != null) {
            if(this == (((NestedScrollView)v1))) {
            }
            else if(this.a(v1, 0, arg4)) {
                v1.getDrawingRect(this.b);
                this.offsetDescendantRectToMyCoords(v1, this.b);
                this.f(this.a(this.b));
            }
        }
    }

    public boolean onStartNestedScroll(View arg2, View arg3, int arg4) {
        return this.a(arg2, arg3, arg4, 0);
    }

    public void onStopNestedScroll(View arg2) {
        this.c(arg2, 0);
    }

    public boolean onTouchEvent(MotionEvent arg24) {
        EdgeEffect v0_3;
        int v17;
        ViewParent v0_1;
        NestedScrollView v10 = this;
        MotionEvent v11 = arg24;
        this.d();
        MotionEvent v12 = MotionEvent.obtain(arg24);
        int v0 = arg24.getActionMasked();
        if(v0 == 0) {
            v10.t = 0;
        }

        v12.offsetLocation(0f, ((float)v10.t));
        int v1 = -1;
        switch(v0) {
            case 0: {
                if(this.getChildCount() == 0) {
                    return 0;
                }

                v0 = v10.c.isFinished() ^ 1;
                v10.j = ((boolean)v0);
                if(v0 != 0) {
                    v0_1 = this.getParent();
                    if(v0_1 != null) {
                        v0_1.requestDisallowInterceptTouchEvent(true);
                    }
                }

                if(!v10.c.isFinished()) {
                    v10.c.abortAnimation();
                }

                v10.f = ((int)arg24.getY());
                v10.q = v11.getPointerId(0);
                v10.a(2, 0);
                break;
            }
            case 1: {
                VelocityTracker v0_2 = v10.k;
                v0_2.computeCurrentVelocity(1000, ((float)v10.p));
                v0 = ((int)v0_2.getYVelocity(v10.q));
                if(Math.abs(v0) > v10.o) {
                    v10.g(-v0);
                    goto label_44;
                }

                if(!v10.c.springBack(this.getScrollX(), this.getScrollY(), 0, 0, 0, this.getScrollRange())) {
                    goto label_44;
                }

                goto label_43;
            }
            case 2: {
                int v9 = v11.findPointerIndex(v10.q);
                if(v9 == v1) {
                    Log.e("NestedScrollView", "Invalid pointerId=" + v10.q + " in onTouchEvent");
                    goto label_264;
                }

                int v6 = ((int)v11.getY(v9));
                int v7 = v10.f - v6;
                if(this.a(0, v7, v10.s, v10.r, 0)) {
                    v7 -= v10.s[1];
                    v12.offsetLocation(0f, ((float)v10.r[1]));
                    v10.t += v10.r[1];
                }

                if(!v10.j && Math.abs(v7) > v10.n) {
                    v0_1 = this.getParent();
                    if(v0_1 != null) {
                        v0_1.requestDisallowInterceptTouchEvent(true);
                    }

                    v10.j = true;
                    if(v7 > 0) {
                        v7 -= v10.n;
                        goto label_101;
                    }

                    v7 += v10.n;
                }

            label_101:
                int v8 = v7;
                if(!v10.j) {
                    goto label_264;
                }

                v10.f = v6 - v10.r[1];
                int v16 = this.getScrollY();
                v7 = this.getScrollRange();
                v0 = this.getOverScrollMode();
                if(v0 != 0) {
                    if(v0 == 1 && v7 > 0) {
                        goto label_117;
                    }

                    v17 = 0;
                }
                else {
                label_117:
                    v17 = 1;
                }

                int v21 = v7;
                int v14 = v8;
                int v22 = v9;
                if((this.a(0, v8, 0, this.getScrollY(), 0, v7, 0, 0, true)) && !v10.a(0)) {
                    v10.k.clear();
                }

                int v2 = this.getScrollY() - v16;
                if(this.a(0, v2, 0, v14 - v2, v10.r, 0)) {
                    v10.f -= v10.r[1];
                    v12.offsetLocation(0f, ((float)v10.r[1]));
                    v10.t += v10.r[1];
                    goto label_264;
                }

                if(v17 == 0) {
                    goto label_264;
                }

                this.g();
                v0 = v16 + v14;
                if(v0 < 0) {
                    i.a(v10.d, (((float)v14)) / (((float)this.getHeight())), v11.getX(v22) / (((float)this.getWidth())));
                    if(!v10.e.isFinished()) {
                        v0_3 = v10.e;
                        goto label_185;
                    }
                }
                else {
                    v2 = v22;
                    if(v0 > v21) {
                        i.a(v10.e, (((float)v14)) / (((float)this.getHeight())), 1f - v11.getX(v2) / (((float)this.getWidth())));
                        if(!v10.d.isFinished()) {
                            v0_3 = v10.d;
                        label_185:
                            v0_3.onRelease();
                        }
                    }
                }

                if(v10.d == null) {
                    goto label_264;
                }

                if((v10.d.isFinished()) && (v10.e.isFinished())) {
                    goto label_264;
                }

                t.d(((View)this));
                break;
            }
            case 3: {
                if((v10.j) && this.getChildCount() > 0 && (v10.c.springBack(this.getScrollX(), this.getScrollY(), 0, 0, 0, this.getScrollRange()))) {
                label_43:
                    t.d(((View)this));
                }

            label_44:
                v10.q = v1;
                this.f();
                break;
            }
            case 5: {
                v0 = arg24.getActionIndex();
                v10.f = ((int)v11.getY(v0));
                v10.q = v11.getPointerId(v0);
                break;
            }
            case 6: {
                this.a(arg24);
                v10.f = ((int)v11.getY(v11.findPointerIndex(v10.q)));
                break;
            }
            default: {
                break;
            }
        }

    label_264:
        if(v10.k != null) {
            v10.k.addMovement(v12);
        }

        v12.recycle();
        return 1;
    }

    public void requestChildFocus(View arg2, View arg3) {
        if(!this.g) {
            this.b(arg3);
        }
        else {
            this.i = arg3;
        }

        super.requestChildFocus(arg2, arg3);
    }

    public boolean requestChildRectangleOnScreen(View arg3, Rect arg4, boolean arg5) {
        arg4.offset(arg3.getLeft() - arg3.getScrollX(), arg3.getTop() - arg3.getScrollY());
        return this.a(arg4, arg5);
    }

    public void requestDisallowInterceptTouchEvent(boolean arg1) {
        if(arg1) {
            this.e();
        }

        super.requestDisallowInterceptTouchEvent(arg1);
    }

    public void requestLayout() {
        this.g = true;
        super.requestLayout();
    }

    public void scrollTo(int arg7, int arg8) {
        if(this.getChildCount() > 0) {
            View v0 = this.getChildAt(0);
            ViewGroup$LayoutParams v1 = v0.getLayoutParams();
            int v2 = this.getWidth() - this.getPaddingLeft() - this.getPaddingRight();
            int v3 = v0.getWidth() + ((FrameLayout$LayoutParams)v1).leftMargin + ((FrameLayout$LayoutParams)v1).rightMargin;
            int v4 = this.getHeight() - this.getPaddingTop() - this.getPaddingBottom();
            int v0_1 = v0.getHeight() + ((FrameLayout$LayoutParams)v1).topMargin + ((FrameLayout$LayoutParams)v1).bottomMargin;
            arg7 = NestedScrollView.b(arg7, v2, v3);
            arg8 = NestedScrollView.b(arg8, v4, v0_1);
            if(arg7 == this.getScrollX() && arg8 == this.getScrollY()) {
                return;
            }

            super.scrollTo(arg7, arg8);
        }
    }

    public void setFillViewport(boolean arg2) {
        if(arg2 != this.l) {
            this.l = arg2;
            this.requestLayout();
        }
    }

    public void setNestedScrollingEnabled(boolean arg2) {
        this.z.a(arg2);
    }

    public void setOnScrollChangeListener(b arg1) {
        this.B = arg1;
    }

    public void setSmoothScrollingEnabled(boolean arg1) {
        this.m = arg1;
    }

    public boolean shouldDelayChildPressedState() {
        return 1;
    }

    public boolean startNestedScroll(int arg2) {
        return this.a(arg2, 0);
    }

    public void stopNestedScroll(int arg2) {
        this.z.c(arg2);
    }

    public void stopNestedScroll() {
        this.stopNestedScroll(0);
    }
}

