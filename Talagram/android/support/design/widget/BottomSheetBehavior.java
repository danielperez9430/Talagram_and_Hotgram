package android.support.design.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build$VERSION;
import android.os.Parcel;
import android.os.Parcelable$ClassLoaderCreator;
import android.os.Parcelable$Creator;
import android.os.Parcelable;
import android.support.design.a$d;
import android.support.design.a$k;
import android.support.v4.view.AbsSavedState;
import android.support.v4.view.t;
import android.support.v4.widget.t$a;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

public class BottomSheetBehavior extends b {
    class android.support.design.widget.BottomSheetBehavior$1 extends a {
        android.support.design.widget.BottomSheetBehavior$1(BottomSheetBehavior arg1) {
            this.a = arg1;
            super();
        }

        public int a(View arg1) {
            if(this.a.d) {
                return this.a.g;
            }

            return this.a.c;
        }

        public int a(View arg1, int arg2, int arg3) {
            int v1 = BottomSheetBehavior.b(this.a);
            arg3 = this.a.d ? this.a.g : this.a.c;
            return android.support.v4.b.a.a(arg2, v1, arg3);
        }

        public void a(int arg2) {
            if(arg2 == 1) {
                this.a.b(1);
            }
        }

        public void a(View arg8, float arg9, float arg10) {
            // Method was not decompiled
        }

        public void a(View arg1, int arg2, int arg3, int arg4, int arg5) {
            this.a.c(arg3);
        }

        public boolean a(View arg5, int arg6) {
            boolean v1 = true;
            if(this.a.e == 1) {
                return 0;
            }

            if(this.a.k) {
                return 0;
            }

            if(this.a.e == 3 && this.a.j == arg6) {
                Object v6 = this.a.i.get();
                if(v6 != null && (((View)v6).canScrollVertically(-1))) {
                    return 0;
                }
            }

            if(this.a.h == null || this.a.h.get() != arg5) {
                v1 = false;
            }
            else {
            }

            return v1;
        }

        public int b(View arg1, int arg2, int arg3) {
            return arg1.getLeft();
        }
    }

    public class SavedState extends AbsSavedState {
        final class android.support.design.widget.BottomSheetBehavior$SavedState$1 implements Parcelable$ClassLoaderCreator {
            android.support.design.widget.BottomSheetBehavior$SavedState$1() {
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
        final int a;

        static {
            SavedState.CREATOR = new android.support.design.widget.BottomSheetBehavior$SavedState$1();
        }

        public SavedState(Parcelable arg1, int arg2) {
            super(arg1);
            this.a = arg2;
        }

        public SavedState(Parcel arg1, ClassLoader arg2) {
            super(arg1, arg2);
            this.a = arg1.readInt();
        }

        public void writeToParcel(Parcel arg1, int arg2) {
            super.writeToParcel(arg1, arg2);
            arg1.writeInt(this.a);
        }
    }

    public abstract class android.support.design.widget.BottomSheetBehavior$a {
        public abstract void a(View arg1, int arg2);

        public abstract void a(View arg1, float arg2);
    }

    class android.support.design.widget.BottomSheetBehavior$b implements Runnable {
        private final View b;
        private final int c;

        android.support.design.widget.BottomSheetBehavior$b(BottomSheetBehavior arg1, View arg2, int arg3) {
            this.a = arg1;
            super();
            this.b = arg2;
            this.c = arg3;
        }

        public void run() {
            if(this.a.f == null || !this.a.f.a(true)) {
                this.a.b(this.c);
            }
            else {
                t.a(this.b, ((Runnable)this));
            }
        }
    }

    int a;
    int b;
    int c;
    boolean d;
    int e;
    android.support.v4.widget.t f;
    int g;
    WeakReference h;
    WeakReference i;
    int j;
    boolean k;
    private boolean l;
    private float m;
    private int n;
    private boolean o;
    private int p;
    private int q;
    private boolean r;
    private boolean s;
    private int t;
    private boolean u;
    private android.support.design.widget.BottomSheetBehavior$a v;
    private VelocityTracker w;
    private int x;
    private Map y;
    private final a z;

    public BottomSheetBehavior() {
        super();
        this.l = true;
        this.e = 4;
        this.z = new android.support.design.widget.BottomSheetBehavior$1(this);
    }

    public BottomSheetBehavior(Context arg5, AttributeSet arg6) {
        super(arg5, arg6);
        this.l = true;
        this.e = 4;
        this.z = new android.support.design.widget.BottomSheetBehavior$1(this);
        TypedArray v6 = arg5.obtainStyledAttributes(arg6, k.BottomSheetBehavior_Layout);
        TypedValue v1 = v6.peekValue(k.BottomSheetBehavior_Layout_behavior_peekHeight);
        int v2 = -1;
        int v1_1 = v1 == null || v1.data != v2 ? v6.getDimensionPixelSize(k.BottomSheetBehavior_Layout_behavior_peekHeight, v2) : v1.data;
        this.a(v1_1);
        this.b(v6.getBoolean(k.BottomSheetBehavior_Layout_behavior_hideable, false));
        this.a(v6.getBoolean(k.BottomSheetBehavior_Layout_behavior_fitToContents, true));
        this.c(v6.getBoolean(k.BottomSheetBehavior_Layout_behavior_skipCollapsed, false));
        v6.recycle();
        this.m = ((float)ViewConfiguration.get(arg5).getScaledMaximumFlingVelocity());
    }

    public final void a(int arg4) {
        int v0 = 1;
        if(arg4 != -1) {
            if(!this.o) {
                if(this.n != arg4) {
                }
                else {
                label_13:
                    v0 = 0;
                    goto label_21;
                }
            }

            this.o = false;
            this.n = Math.max(0, arg4);
            this.c = this.g - arg4;
        }
        else if(!this.o) {
            this.o = true;
        }
        else {
            goto label_13;
        }

    label_21:
        if(v0 != 0 && this.e == 4 && this.h != null) {
            Object v4 = this.h.get();
            if(v4 != null) {
                ((View)v4).requestLayout();
            }
        }
    }

    public void a(boolean arg2) {
        if(this.l == arg2) {
            return;
        }

        this.l = arg2;
        if(this.h != null) {
            this.a();
        }

        int v2 = !this.l || this.e != 6 ? this.e : 3;
        this.b(v2);
    }

    private void a() {
        int v0 = this.l ? Math.max(this.g - this.q, this.a) : this.g - this.q;
        this.c = v0;
    }

    static boolean a(BottomSheetBehavior arg0) {
        return arg0.l;
    }

    View a(View arg4) {
        if(t.y(arg4)) {
            return arg4;
        }

        if((arg4 instanceof ViewGroup)) {
            int v0 = 0;
            int v1 = ((ViewGroup)arg4).getChildCount();
            while(v0 < v1) {
                View v2 = this.a(((ViewGroup)arg4).getChildAt(v0));
                if(v2 != null) {
                    return v2;
                }
                else {
                    ++v0;
                    continue;
                }

                return null;
            }
        }

        return null;
    }

    public void a(CoordinatorLayout arg2, View arg3, Parcelable arg4) {
        super.a(arg2, arg3, ((SavedState)arg4).getSuperState());
        int v2 = ((SavedState)arg4).a == 1 || ((SavedState)arg4).a == 2 ? 4 : ((SavedState)arg4).a;
        this.e = v2;
    }

    public void a(CoordinatorLayout arg4, View arg5, View arg6, int arg7) {
        int v4;
        int v0 = 3;
        if(arg5.getTop() == this.e()) {
            this.b(v0);
            return;
        }

        if(arg6 == this.i.get()) {
            if(!this.u) {
            }
            else {
                if(this.t > 0) {
                    v4 = this.e();
                }
                else {
                    if((this.d) && (this.a(arg5, this.d()))) {
                        v4 = this.g;
                        v0 = 5;
                        goto label_62;
                    }

                    if(this.t == 0) {
                        v4 = arg5.getTop();
                        if(!this.l) {
                            if(v4 < this.b) {
                                if(v4 < Math.abs(v4 - this.c)) {
                                    v4 = 0;
                                    goto label_62;
                                }
                            }
                            else if(Math.abs(v4 - this.b) < Math.abs(v4 - this.c)) {
                            }
                            else {
                                goto label_60;
                            }

                            v4 = this.b;
                            v0 = 6;
                            goto label_62;
                        }
                        else if(Math.abs(v4 - this.a) < Math.abs(v4 - this.c)) {
                            v4 = this.a;
                            goto label_62;
                        }
                    }

                label_60:
                    v4 = this.c;
                    v0 = 4;
                }

            label_62:
                if(this.f.a(arg5, arg5.getLeft(), v4)) {
                    this.b(2);
                    t.a(arg5, new android.support.design.widget.BottomSheetBehavior$b(this, arg5, v0));
                }
                else {
                    this.b(v0);
                }

                this.u = false;
            }
        }
    }

    boolean a(View arg5, float arg6) {
        boolean v1 = true;
        if(this.r) {
            return 1;
        }

        if(arg5.getTop() < this.c) {
            return 0;
        }

        if(Math.abs((((float)arg5.getTop())) + arg6 * 0.1f - (((float)this.c))) / (((float)this.n)) > 0.5f) {
        }
        else {
            v1 = false;
        }

        return v1;
    }

    public void a(CoordinatorLayout arg2, View arg3, View arg4, int arg5, int arg6, int[] arg7, int arg8) {
        int v4;
        if(arg8 == 1) {
            return;
        }

        if(arg4 != this.i.get()) {
            return;
        }

        arg5 = arg3.getTop();
        arg8 = arg5 - arg6;
        if(arg6 > 0) {
            if(arg8 < this.e()) {
                arg7[1] = arg5 - this.e();
                t.e(arg3, -arg7[1]);
                v4 = 3;
                goto label_19;
            }
            else {
                arg7[1] = arg6;
                goto label_22;
            }
        }
        else if(arg6 < 0 && !arg4.canScrollVertically(-1)) {
            if(arg8 > this.c) {
                if(this.d) {
                }
                else {
                    arg7[1] = arg5 - this.c;
                    t.e(arg3, -arg7[1]);
                    v4 = 4;
                label_19:
                    this.b(v4);
                    goto label_45;
                }
            }

            arg7[1] = arg6;
        label_22:
            t.e(arg3, -arg6);
            this.b(1);
        }

    label_45:
        this.c(arg3.getTop());
        this.t = arg6;
        this.u = true;
    }

    public boolean a(CoordinatorLayout arg5, View arg6, int arg7) {
        if((t.t(((View)arg5))) && !t.t(arg6)) {
            arg6.setFitsSystemWindows(true);
        }

        int v0 = arg6.getTop();
        arg5.a(arg6, arg7);
        this.g = arg5.getHeight();
        if(this.o) {
            if(this.p == 0) {
                this.p = arg5.getResources().getDimensionPixelSize(d.design_bottom_sheet_peek_height_min);
            }

            arg7 = Math.max(this.p, this.g - arg5.getWidth() * 9 / 16);
        }
        else {
            arg7 = this.n;
        }

        this.q = arg7;
        this.a = Math.max(0, this.g - arg6.getHeight());
        int v2 = 2;
        this.b = this.g / v2;
        this.a();
        if(this.e == 3) {
            arg7 = this.e();
            goto label_43;
        }
        else if(this.e == 6) {
            arg7 = this.b;
            goto label_43;
        }
        else {
            if((this.d) && this.e == 5) {
                arg7 = this.g;
                goto label_43;
            }

            if(this.e == 4) {
                arg7 = this.c;
            label_43:
                t.e(arg6, arg7);
                goto label_69;
            }

            if(this.e != 1 && this.e != v2) {
                goto label_69;
            }

            t.e(arg6, v0 - arg6.getTop());
        }

    label_69:
        if(this.f == null) {
            this.f = android.support.v4.widget.t.a(((ViewGroup)arg5), this.z);
        }

        this.h = new WeakReference(arg6);
        this.i = new WeakReference(this.a(arg6));
        return 1;
    }

    public boolean a(CoordinatorLayout arg3, View arg4, MotionEvent arg5) {
        if(!arg4.isShown()) {
            return 0;
        }

        int v3 = arg5.getActionMasked();
        if(this.e == 1 && v3 == 0) {
            return 1;
        }

        if(this.f != null) {
            this.f.b(arg5);
        }

        if(v3 == 0) {
            this.b();
        }

        if(this.w == null) {
            this.w = VelocityTracker.obtain();
        }

        this.w.addMovement(arg5);
        if(v3 == 2 && !this.s && Math.abs((((float)this.x)) - arg5.getY()) > (((float)this.f.a()))) {
            this.f.a(arg4, arg5.getPointerId(arg5.getActionIndex()));
        }

        return this.s ^ 1;
    }

    public boolean a(CoordinatorLayout arg3, View arg4, View arg5, float arg6, float arg7) {
        boolean v3;
        if(arg5 == this.i.get()) {
            if(this.e == 3 && !super.a(arg3, arg4, arg5, arg6, arg7)) {
                goto label_10;
            }

            v3 = true;
        }
        else {
        label_10:
            v3 = false;
        }

        return v3;
    }

    public boolean a(CoordinatorLayout arg1, View arg2, View arg3, View arg4, int arg5, int arg6) {
        boolean v1 = false;
        this.t = 0;
        this.u = false;
        if((arg5 & 2) != 0) {
            v1 = true;
        }

        return v1;
    }

    public void b(boolean arg1) {
        this.d = arg1;
    }

    static int b(BottomSheetBehavior arg0) {
        return arg0.e();
    }

    private void b() {
        this.j = -1;
        if(this.w != null) {
            this.w.recycle();
            this.w = null;
        }
    }

    void b(int arg3) {
        boolean v0;
        if(this.e == arg3) {
            return;
        }

        this.e = arg3;
        if(arg3 == 6 || arg3 == 3) {
            v0 = true;
        label_16:
            this.d(v0);
        }
        else {
            if(arg3 != 5 && arg3 != 4) {
                goto label_17;
            }

            v0 = false;
            goto label_16;
        }

    label_17:
        Object v0_1 = this.h.get();
        if(v0_1 != null && this.v != null) {
            this.v.a(((View)v0_1), arg3);
        }
    }

    public Parcelable b(CoordinatorLayout arg2, View arg3) {
        return new SavedState(super.b(arg2, arg3), this.e);
    }

    public boolean b(CoordinatorLayout arg9, View arg10, MotionEvent arg11) {
        boolean v1 = false;
        if(!arg10.isShown()) {
            this.s = true;
            return 0;
        }

        int v0 = arg11.getActionMasked();
        if(v0 == 0) {
            this.b();
        }

        if(this.w == null) {
            this.w = VelocityTracker.obtain();
        }

        this.w.addMovement(arg11);
        Object v4 = null;
        int v5 = -1;
        if(v0 != 3) {
            switch(v0) {
                case 0: {
                    goto label_21;
                }
                case 1: {
                    goto label_50;
                }
            }

            goto label_56;
        label_21:
            int v3 = ((int)arg11.getX());
            this.x = ((int)arg11.getY());
            Object v6 = this.i != null ? this.i.get() : v4;
            if(v6 != null && (arg9.a(((View)v6), v3, this.x))) {
                this.j = arg11.getPointerId(arg11.getActionIndex());
                this.k = true;
            }

            boolean v10 = this.j != v5 || (arg9.a(arg10, v3, this.x)) ? false : true;
            this.s = v10;
        }
        else {
        label_50:
            this.k = false;
            this.j = v5;
            if(!this.s) {
                goto label_56;
            }

            this.s = false;
            return 0;
        }

    label_56:
        if(!this.s && this.f != null && (this.f.a(arg11))) {
            return 1;
        }

        if(this.i != null) {
            v4 = this.i.get();
        }

        if(v0 == 2 && v4 != null && !this.s && this.e != 1 && !arg9.a(((View)v4), ((int)arg11.getX()), ((int)arg11.getY())) && this.f != null && Math.abs((((float)this.x)) - arg11.getY()) > (((float)this.f.a()))) {
            v1 = true;
        }

        return v1;
    }

    public void c(boolean arg1) {
        this.r = arg1;
    }

    void c(int arg5) {
        int v3;
        int v2;
        float v5;
        android.support.design.widget.BottomSheetBehavior$a v1;
        Object v0 = this.h.get();
        if(v0 != null && this.v != null) {
            if(arg5 > this.c) {
                v1 = this.v;
                v5 = ((float)(this.c - arg5));
                v2 = this.g;
                v3 = this.c;
            }
            else {
                v1 = this.v;
                v5 = ((float)(this.c - arg5));
                v2 = this.c;
                v3 = this.e();
            }

            v1.a(((View)v0), v5 / (((float)(v2 - v3))));
        }
    }

    private float d() {
        if(this.w == null) {
            return 0;
        }

        this.w.computeCurrentVelocity(1000, this.m);
        return this.w.getYVelocity(this.j);
    }

    private void d(boolean arg8) {
        int v5;
        if(this.h == null) {
            return;
        }

        ViewParent v0 = this.h.get().getParent();
        if(!(v0 instanceof CoordinatorLayout)) {
            return;
        }

        int v1 = ((CoordinatorLayout)v0).getChildCount();
        int v3 = 16;
        if(Build$VERSION.SDK_INT >= v3 && (arg8)) {
            if(this.y == null) {
                this.y = new HashMap(v1);
            }
            else {
                return;
            }
        }

        int v2;
        for(v2 = 0; v2 < v1; ++v2) {
            View v4 = ((CoordinatorLayout)v0).getChildAt(v2);
            if(v4 == this.h.get()) {
            }
            else {
                if(arg8) {
                    if(Build$VERSION.SDK_INT >= v3) {
                        this.y.put(v4, Integer.valueOf(v4.getImportantForAccessibility()));
                    }

                    v5 = 4;
                }
                else if(this.y == null) {
                    goto label_46;
                }
                else if(this.y.containsKey(v4)) {
                    v5 = this.y.get(v4).intValue();
                }
                else {
                    goto label_46;
                }

                t.b(v4, v5);
            }

        label_46:
        }

        if(!arg8) {
            this.y = null;
        }
    }

    private int e() {
        int v0 = this.l ? this.a : 0;
        return v0;
    }
}

