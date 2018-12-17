package android.support.design.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Region$Op;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable$Callback;
import android.graphics.drawable.Drawable;
import android.os.Build$VERSION;
import android.os.Parcel;
import android.os.Parcelable$ClassLoaderCreator;
import android.os.Parcelable$Creator;
import android.os.Parcelable;
import android.os.SystemClock;
import android.support.v4.f.i;
import android.support.v4.view.AbsSavedState;
import android.support.v4.view.ab;
import android.support.v4.view.n;
import android.support.v4.view.o;
import android.support.v4.view.p;
import android.support.v4.view.t;
import android.support.v4.widget.h;
import android.support.v4.widget.u;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View$BaseSavedState;
import android.view.View$MeasureSpec;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.view.ViewGroup$MarginLayoutParams;
import android.view.ViewGroup$OnHierarchyChangeListener;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver$OnPreDrawListener;
import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CoordinatorLayout extends ViewGroup implements n {
    public class SavedState extends AbsSavedState {
        final class android.support.design.widget.CoordinatorLayout$SavedState$1 implements Parcelable$ClassLoaderCreator {
            android.support.design.widget.CoordinatorLayout$SavedState$1() {
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
        SparseArray a;

        static {
            SavedState.CREATOR = new android.support.design.widget.CoordinatorLayout$SavedState$1();
        }

        public SavedState(Parcelable arg1) {
            super(arg1);
        }

        public SavedState(Parcel arg6, ClassLoader arg7) {
            super(arg6, arg7);
            int v0 = arg6.readInt();
            int[] v1 = new int[v0];
            arg6.readIntArray(v1);
            Parcelable[] v6 = arg6.readParcelableArray(arg7);
            this.a = new SparseArray(v0);
            int v7;
            for(v7 = 0; v7 < v0; ++v7) {
                this.a.append(v1[v7], v6[v7]);
            }
        }

        public void writeToParcel(Parcel arg6, int arg7) {
            super.writeToParcel(arg6, arg7);
            int v1 = 0;
            int v0 = this.a != null ? this.a.size() : 0;
            arg6.writeInt(v0);
            int[] v2 = new int[v0];
            Parcelable[] v3 = new Parcelable[v0];
            while(v1 < v0) {
                v2[v1] = this.a.keyAt(v1);
                v3[v1] = this.a.valueAt(v1);
                ++v1;
            }

            arg6.writeIntArray(v2);
            arg6.writeParcelableArray(v3, arg7);
        }
    }

    public interface a {
        b getBehavior();
    }

    public abstract class b {
        public b() {
            super();
        }

        public b(Context arg1, AttributeSet arg2) {
            super();
        }

        public boolean a(CoordinatorLayout arg1, View arg2, int arg3) {
            return 0;
        }

        public void a(CoordinatorLayout arg1, View arg2, Parcelable arg3) {
        }

        public boolean a(CoordinatorLayout arg1, View arg2, View arg3, float arg4, float arg5) {
            return 0;
        }

        public boolean a(CoordinatorLayout arg1, View arg2, Rect arg3) {
            return 0;
        }

        public boolean a(CoordinatorLayout arg1, View arg2, MotionEvent arg3) {
            return 0;
        }

        public ab a(CoordinatorLayout arg1, View arg2, ab arg3) {
            return arg3;
        }

        public boolean a(CoordinatorLayout arg1, View arg2, View arg3) {
            return 0;
        }

        public void a(CoordinatorLayout arg1, View arg2, View arg3, int arg4, int arg5, int arg6, int arg7, int arg8) {
            if(arg8 == 0) {
                this.a(arg1, arg2, arg3, arg4, arg5, arg6, arg7);
            }
        }

        public void a(CoordinatorLayout arg1, View arg2, View arg3, int arg4, int arg5, int[] arg6, int arg7) {
            if(arg7 == 0) {
                this.a(arg1, arg2, arg3, arg4, arg5, arg6);
            }
        }

        public boolean a(CoordinatorLayout arg1, View arg2, View arg3, View arg4, int arg5, int arg6) {
            if(arg6 == 0) {
                return this.a(arg1, arg2, arg3, arg4, arg5);
            }

            return 0;
        }

        public void a(CoordinatorLayout arg1, View arg2, View arg3, int arg4) {
            if(arg4 == 0) {
                this.d(arg1, arg2, arg3);
            }
        }

        public boolean a(CoordinatorLayout arg1, View arg2, int arg3, int arg4, int arg5, int arg6) {
            return 0;
        }

        public boolean a(CoordinatorLayout arg1, View arg2, View arg3, float arg4, float arg5, boolean arg6) {
            return 0;
        }

        public boolean a(CoordinatorLayout arg1, View arg2, Rect arg3, boolean arg4) {
            return 0;
        }

        public void a(e arg1) {
        }

        @Deprecated public void a(CoordinatorLayout arg1, View arg2, View arg3, int arg4, int arg5, int arg6, int arg7) {
        }

        @Deprecated public void a(CoordinatorLayout arg1, View arg2, View arg3, int arg4, int arg5, int[] arg6) {
        }

        @Deprecated public boolean a(CoordinatorLayout arg1, View arg2, View arg3, View arg4, int arg5) {
            return 0;
        }

        public Parcelable b(CoordinatorLayout arg1, View arg2) {
            return View$BaseSavedState.EMPTY_STATE;
        }

        public boolean b(CoordinatorLayout arg1, View arg2, MotionEvent arg3) {
            return 0;
        }

        public boolean b(CoordinatorLayout arg1, View arg2, View arg3) {
            return 0;
        }

        public void b(CoordinatorLayout arg1, View arg2, View arg3, View arg4, int arg5, int arg6) {
            if(arg6 == 0) {
                this.b(arg1, arg2, arg3, arg4, arg5);
            }
        }

        @Deprecated public void b(CoordinatorLayout arg1, View arg2, View arg3, View arg4, int arg5) {
        }

        public void c(CoordinatorLayout arg1, View arg2, View arg3) {
        }

        public int c(CoordinatorLayout arg1, View arg2) {
            return -16777216;
        }

        public void c() {
        }

        public float d(CoordinatorLayout arg1, View arg2) {
            return 0;
        }

        @Deprecated public void d(CoordinatorLayout arg1, View arg2, View arg3) {
        }

        public boolean e(CoordinatorLayout arg1, View arg2) {
            boolean v1 = this.d(arg1, arg2) > 0f ? true : false;
            return v1;
        }
    }

    @Deprecated @Retention(value=RetentionPolicy.RUNTIME) @public interface c {
        Class a();
    }

    class d implements ViewGroup$OnHierarchyChangeListener {
        d(CoordinatorLayout arg1) {
            this.a = arg1;
            super();
        }

        public void onChildViewAdded(View arg2, View arg3) {
            if(this.a.e != null) {
                this.a.e.onChildViewAdded(arg2, arg3);
            }
        }

        public void onChildViewRemoved(View arg3, View arg4) {
            this.a.a(2);
            if(this.a.e != null) {
                this.a.e.onChildViewRemoved(arg3, arg4);
            }
        }
    }

    public class e extends ViewGroup$MarginLayoutParams {
        b a;
        boolean b;
        public int c;
        public int d;
        public int e;
        int f;
        public int g;
        public int h;
        int i;
        int j;
        View k;
        View l;
        final Rect m;
        Object n;
        private boolean o;
        private boolean p;
        private boolean q;
        private boolean r;

        e(Context arg5, AttributeSet arg6) {
            super(arg5, arg6);
            this.b = false;
            this.c = 0;
            this.d = 0;
            this.e = -1;
            this.f = -1;
            this.g = 0;
            this.h = 0;
            this.m = new Rect();
            TypedArray v2 = arg5.obtainStyledAttributes(arg6, android.support.b.a$c.CoordinatorLayout_Layout);
            this.c = v2.getInteger(android.support.b.a$c.CoordinatorLayout_Layout_android_layout_gravity, 0);
            this.f = v2.getResourceId(android.support.b.a$c.CoordinatorLayout_Layout_layout_anchor, -1);
            this.d = v2.getInteger(android.support.b.a$c.CoordinatorLayout_Layout_layout_anchorGravity, 0);
            this.e = v2.getInteger(android.support.b.a$c.CoordinatorLayout_Layout_layout_keyline, -1);
            this.g = v2.getInt(android.support.b.a$c.CoordinatorLayout_Layout_layout_insetEdge, 0);
            this.h = v2.getInt(android.support.b.a$c.CoordinatorLayout_Layout_layout_dodgeInsetEdges, 0);
            this.b = v2.hasValue(android.support.b.a$c.CoordinatorLayout_Layout_layout_behavior);
            if(this.b) {
                this.a = CoordinatorLayout.a(arg5, arg6, v2.getString(android.support.b.a$c.CoordinatorLayout_Layout_layout_behavior));
            }

            v2.recycle();
            if(this.a != null) {
                this.a.a(this);
            }
        }

        public e(e arg2) {
            super(((ViewGroup$MarginLayoutParams)arg2));
            this.b = false;
            this.c = 0;
            this.d = 0;
            this.e = -1;
            this.f = -1;
            this.g = 0;
            this.h = 0;
            this.m = new Rect();
        }

        public e(ViewGroup$MarginLayoutParams arg2) {
            super(arg2);
            this.b = false;
            this.c = 0;
            this.d = 0;
            this.e = -1;
            this.f = -1;
            this.g = 0;
            this.h = 0;
            this.m = new Rect();
        }

        public e(ViewGroup$LayoutParams arg2) {
            super(arg2);
            this.b = false;
            this.c = 0;
            this.d = 0;
            this.e = -1;
            this.f = -1;
            this.g = 0;
            this.h = 0;
            this.m = new Rect();
        }

        public e(int arg1, int arg2) {
            super(arg1, arg2);
            this.b = false;
            this.c = 0;
            this.d = 0;
            this.e = -1;
            this.f = -1;
            this.g = 0;
            this.h = 0;
            this.m = new Rect();
        }

        public void a(b arg2) {
            if(this.a != arg2) {
                if(this.a != null) {
                    this.a.c();
                }

                this.a = arg2;
                this.n = null;
                this.b = true;
                if(arg2 == null) {
                    return;
                }

                arg2.a(this);
            }
        }

        boolean a(CoordinatorLayout arg3, View arg4) {
            int v3_1;
            boolean v3;
            if(this.o) {
                return 1;
            }

            boolean v0 = this.o;
            if(this.a != null) {
                v3 = this.a.e(arg3, arg4);
            }
            else {
                v3_1 = 0;
            }

            v3_1 = (((int)v3)) | (((int)v0));
            this.o = ((boolean)v3_1);
            return ((boolean)v3_1);
        }

        boolean a(CoordinatorLayout arg2, View arg3, View arg4) {
            boolean v2;
            if(arg4 == this.l || (this.a(arg4, t.f(((View)arg2))))) {
            label_13:
                v2 = true;
            }
            else {
                if(this.a != null && (this.a.a(arg2, arg3, arg4))) {
                    goto label_13;
                }

                v2 = false;
            }

            return v2;
        }

        void a(boolean arg1) {
            this.r = arg1;
        }

        void a(int arg1, boolean arg2) {
            switch(arg1) {
                case 0: {
                    this.p = arg2;
                    break;
                }
                case 1: {
                    this.q = arg2;
                    break;
                }
                default: {
                    break;
                }
            }
        }

        void a(Rect arg2) {
            this.m.set(arg2);
        }

        void a(int arg2) {
            this.a(arg2, false);
        }

        private void a(View arg5, CoordinatorLayout arg6) {
            ViewParent v0_1;
            this.k = arg6.findViewById(this.f);
            View v1 = null;
            if(this.k != null) {
                if(this.k != arg6) {
                    View v0 = this.k;
                    ViewParent v2 = this.k.getParent();
                    while((((CoordinatorLayout)v2)) != arg6) {
                        if(v2 == null) {
                            break;
                        }

                        if((((View)v2)) != arg5) {
                            if((v2 instanceof View)) {
                                v0_1 = v2;
                            }

                            v2 = v2.getParent();
                            continue;
                        }
                        else if(arg6.isInEditMode()) {
                            goto label_10;
                        }
                        else {
                            throw new IllegalStateException("Anchor must not be a descendant of the anchored view");
                        }

                        break;
                    }

                    this.l = ((View)v0_1);
                    return;
                }
                else if(!arg6.isInEditMode()) {
                    throw new IllegalStateException("View can not be anchored to the the parent CoordinatorLayout");
                }
            }
            else if(!arg6.isInEditMode()) {
                goto label_40;
            }

        label_10:
            this.l = v1;
            this.k = v1;
            return;
        label_40:
            StringBuilder v1_1 = new StringBuilder();
            v1_1.append("Could not find CoordinatorLayout descendant view with id ");
            v1_1.append(arg6.getResources().getResourceName(this.f));
            v1_1.append(" to anchor view ");
            v1_1.append(arg5);
            throw new IllegalStateException(v1_1.toString());
        }

        private boolean a(View arg2, int arg3) {
            int v2 = android.support.v4.view.d.a(arg2.getLayoutParams().g, arg3);
            boolean v2_1 = v2 == 0 || (android.support.v4.view.d.a(this.h, arg3) & v2) != v2 ? false : true;
            return v2_1;
        }

        public int a() {
            return this.f;
        }

        public b b() {
            return this.a;
        }

        View b(CoordinatorLayout arg3, View arg4) {
            if(this.f == -1) {
                this.l = null;
                this.k = null;
                return null;
            }

            if(this.k == null || !this.b(arg4, arg3)) {
                this.a(arg4, arg3);
            }

            return this.k;
        }

        boolean b(int arg1) {
            switch(arg1) {
                case 0: {
                    goto label_5;
                }
                case 1: {
                    goto label_3;
                }
            }

            return 0;
        label_3:
            return this.q;
        label_5:
            return this.p;
        }

        private boolean b(View arg5, CoordinatorLayout arg6) {
            if(this.k.getId() != this.f) {
                return 0;
            }

            View v0 = this.k;
            ViewParent v1 = this.k.getParent();
            while(true) {
                if((((CoordinatorLayout)v1)) == arg6) {
                    goto label_22;
                }

                if(v1 != null) {
                    if((((View)v1)) == arg5) {
                    }
                    else {
                        if((v1 instanceof View)) {
                            ViewParent v0_1 = v1;
                        }

                        v1 = v1.getParent();
                        continue;
                    }
                }

                break;
            }

            this.l = null;
            this.k = null;
            return 0;
        label_22:
            this.l = v0;
            return 1;
        }

        Rect c() {
            return this.m;
        }

        boolean d() {
            boolean v0 = this.k != null || this.f == -1 ? false : true;
            return v0;
        }

        boolean e() {
            if(this.a == null) {
                this.o = false;
            }

            return this.o;
        }

        void f() {
            this.o = false;
        }

        boolean g() {
            return this.r;
        }

        void h() {
            this.r = false;
        }
    }

    class f implements ViewTreeObserver$OnPreDrawListener {
        f(CoordinatorLayout arg1) {
            this.a = arg1;
            super();
        }

        public boolean onPreDraw() {
            this.a.a(0);
            return 1;
        }
    }

    class g implements Comparator {
        g() {
            super();
        }

        public int a(View arg2, View arg3) {
            float v2 = t.B(arg2);
            float v3 = t.B(arg3);
            if(v2 > v3) {
                return -1;
            }

            if(v2 < v3) {
                return 1;
            }

            return 0;
        }

        public int compare(Object arg1, Object arg2) {
            return this.a(((View)arg1), ((View)arg2));
        }
    }

    static final String a;
    static final Class[] b;
    static final ThreadLocal c;
    static final Comparator d;
    ViewGroup$OnHierarchyChangeListener e;
    private static final android.support.v4.f.k$a f;
    private final List g;
    private final h h;
    private final List i;
    private final List j;
    private final int[] k;
    private Paint l;
    private boolean m;
    private boolean n;
    private int[] o;
    private View p;
    private View q;
    private f r;
    private boolean s;
    private ab t;
    private boolean u;
    private Drawable v;
    private p w;
    private final o x;

    static {
        Package v0 = CoordinatorLayout.class.getPackage();
        Comparator v1 = null;
        String v0_1 = v0 != null ? v0.getName() : ((String)v1);
        CoordinatorLayout.a = v0_1;
        CoordinatorLayout.d = Build$VERSION.SDK_INT >= 21 ? new g() : v1;
        CoordinatorLayout.b = new Class[]{Context.class, AttributeSet.class};
        CoordinatorLayout.c = new ThreadLocal();
        CoordinatorLayout.f = new android.support.v4.f.k$c(12);
    }

    public CoordinatorLayout(Context arg2) {
        this(arg2, null);
    }

    public CoordinatorLayout(Context arg2, AttributeSet arg3) {
        this(arg2, arg3, android.support.b.a$a.coordinatorLayoutStyle);
    }

    public CoordinatorLayout(Context arg4, AttributeSet arg5, int arg6) {
        super(arg4, arg5, arg6);
        this.g = new ArrayList();
        this.h = new h();
        this.i = new ArrayList();
        this.j = new ArrayList();
        this.k = new int[2];
        this.x = new o(((ViewGroup)this));
        int v0 = 0;
        TypedArray v5 = arg6 == 0 ? arg4.obtainStyledAttributes(arg5, android.support.b.a$c.CoordinatorLayout, 0, android.support.b.a$b.Widget_Support_CoordinatorLayout) : arg4.obtainStyledAttributes(arg5, android.support.b.a$c.CoordinatorLayout, arg6, 0);
        arg6 = v5.getResourceId(android.support.b.a$c.CoordinatorLayout_keylines, 0);
        if(arg6 != 0) {
            Resources v4 = arg4.getResources();
            this.o = v4.getIntArray(arg6);
            float v4_1 = v4.getDisplayMetrics().density;
            arg6 = this.o.length;
            while(v0 < arg6) {
                this.o[v0] = ((int)((((float)this.o[v0])) * v4_1));
                ++v0;
            }
        }

        this.v = v5.getDrawable(android.support.b.a$c.CoordinatorLayout_statusBarBackground);
        v5.recycle();
        this.g();
        super.setOnHierarchyChangeListener(new d(this));
    }

    public void a(View arg1, int arg2, int arg3, int arg4, int arg5) {
        this.measureChildWithMargins(arg1, arg2, arg3, arg4, arg5);
    }

    public boolean a(View arg2, int arg3, int arg4) {
        boolean v2_1;
        Rect v0 = CoordinatorLayout.e();
        this.a(arg2, v0);
        try {
            v2_1 = v0.contains(arg3, arg4);
        }
        catch(Throwable v2) {
            CoordinatorLayout.a(v0);
            throw v2;
        }

        CoordinatorLayout.a(v0);
        return v2_1;
    }

    public void a(View arg3, int arg4) {
        ViewGroup$LayoutParams v0 = arg3.getLayoutParams();
        if(!((e)v0).d()) {
            if(((e)v0).k != null) {
                this.a(arg3, ((e)v0).k, arg4);
            }
            else if(((e)v0).e >= 0) {
                this.b(arg3, ((e)v0).e, arg4);
            }
            else {
                this.d(arg3, arg4);
            }

            return;
        }

        throw new IllegalStateException("An anchor may not be changed after CoordinatorLayout measurement begins before layout is complete.");
    }

    private static int a(int arg0, int arg1, int arg2) {
        if(arg0 < arg1) {
            return arg1;
        }

        if(arg0 > arg2) {
            return arg2;
        }

        return arg0;
    }

    static b a(Context arg4, AttributeSet arg5, String arg6) {
        Constructor v1_2;
        HashMap v0_3;
        if(TextUtils.isEmpty(((CharSequence)arg6))) {
            return null;
        }

        if(arg6.startsWith(".")) {
            arg6 = arg4.getPackageName() + arg6;
        }
        else {
            char v0_1 = '.';
            if(arg6.indexOf(v0_1) >= 0) {
            }
            else if(!TextUtils.isEmpty(CoordinatorLayout.a)) {
                arg6 = CoordinatorLayout.a + v0_1 + arg6;
            }
        }

        try {
            Object v0_2 = CoordinatorLayout.c.get();
            if(v0_2 == null) {
                v0_3 = new HashMap();
                CoordinatorLayout.c.set(v0_3);
            }

            Object v1_1 = ((Map)v0_3).get(arg6);
            if(v1_1 == null) {
                v1_2 = arg4.getClassLoader().loadClass(arg6).getConstructor(CoordinatorLayout.b);
                v1_2.setAccessible(true);
                ((Map)v0_3).put(arg6, v1_2);
            }

            return v1_2.newInstance(arg4, arg5);
        }
        catch(Exception v4) {
            v0 = new StringBuilder();
            v0.append("Could not inflate Behavior subclass ");
            v0.append(arg6);
            throw new RuntimeException(v0.toString(), ((Throwable)v4));
        }
    }

    private static void a(Rect arg1) {
        arg1.setEmpty();
        CoordinatorLayout.f.a(arg1);
    }

    private void a(e arg6, Rect arg7, int arg8, int arg9) {
        int v0 = this.getWidth();
        int v1 = this.getHeight();
        v0 = Math.max(this.getPaddingLeft() + arg6.leftMargin, Math.min(arg7.left, v0 - this.getPaddingRight() - arg8 - arg6.rightMargin));
        int v6 = Math.max(this.getPaddingTop() + arg6.topMargin, Math.min(arg7.top, v1 - this.getPaddingBottom() - arg9 - arg6.bottomMargin));
        arg7.set(v0, v6, arg8 + v0, arg9 + v6);
    }

    private void a(View arg6, int arg7, Rect arg8, Rect arg9, e arg10, int arg11, int arg12) {
        int v6 = android.support.v4.view.d.a(CoordinatorLayout.e(arg10.c), arg7);
        arg7 = android.support.v4.view.d.a(CoordinatorLayout.c(arg10.d), arg7);
        int v10 = v6 & 7;
        v6 &= 112;
        int v0 = arg7 & 7;
        arg7 &= 112;
        int v1 = 5;
        if(v0 == 1) {
            v0 = arg8.left + arg8.width() / 2;
        }
        else if(v0 != v1) {
            v0 = arg8.left;
        }
        else {
            v0 = arg8.right;
        }

        int v3 = 80;
        int v4 = 16;
        if(arg7 == v4) {
            arg7 = arg8.top + arg8.height() / 2;
        }
        else if(arg7 != v3) {
            arg7 = arg8.top;
        }
        else {
            arg7 = arg8.bottom;
        }

        if(v10 == 1) {
            v0 -= arg11 / 2;
        }
        else if(v10 != v1) {
            v0 -= arg11;
        }

        if(v6 == v4) {
            arg7 -= arg12 / 2;
        }
        else if(v6 != v3) {
            arg7 -= arg12;
        }

        arg9.set(v0, arg7, arg11 + v0, arg12 + arg7);
    }

    private void a(View arg9, Rect arg10, int arg11) {
        int v1_1;
        if(!t.A(arg9)) {
            return;
        }

        if(arg9.getWidth() > 0) {
            if(arg9.getHeight() <= 0) {
            }
            else {
                ViewGroup$LayoutParams v0 = arg9.getLayoutParams();
                b v1 = ((e)v0).b();
                Rect v2 = CoordinatorLayout.e();
                Rect v3 = CoordinatorLayout.e();
                v3.set(arg9.getLeft(), arg9.getTop(), arg9.getRight(), arg9.getBottom());
                if(v1 == null || !v1.a(this, arg9, v2)) {
                    v2.set(v3);
                }
                else if(v3.contains(v2)) {
                }
                else {
                    StringBuilder v10 = new StringBuilder();
                    v10.append("Rect should be within the child\'s bounds. Rect:");
                    v10.append(v2.toShortString());
                    v10.append(" | Bounds:");
                    v10.append(v3.toShortString());
                    throw new IllegalArgumentException(v10.toString());
                }

                CoordinatorLayout.a(v3);
                if(v2.isEmpty()) {
                    CoordinatorLayout.a(v2);
                    return;
                }

                arg11 = android.support.v4.view.d.a(((e)v0).h, arg11);
                if((arg11 & 48) == 48) {
                    v1_1 = v2.top - ((e)v0).topMargin - ((e)v0).j;
                    if(v1_1 < arg10.top) {
                        this.f(arg9, arg10.top - v1_1);
                        v1_1 = 1;
                    }
                    else {
                        goto label_62;
                    }
                }
                else {
                label_62:
                    v1_1 = 0;
                }

                if((arg11 & 80) == 80) {
                    int v3_1 = this.getHeight() - v2.bottom - ((e)v0).bottomMargin + ((e)v0).j;
                    if(v3_1 < arg10.bottom) {
                        this.f(arg9, v3_1 - arg10.bottom);
                        v1_1 = 1;
                    }
                }

                if(v1_1 == 0) {
                    this.f(arg9, 0);
                }

                if((arg11 & 3) == 3) {
                    v1_1 = v2.left - ((e)v0).leftMargin - ((e)v0).i;
                    if(v1_1 < arg10.left) {
                        this.e(arg9, arg10.left - v1_1);
                        v1_1 = 1;
                    }
                    else {
                        goto label_96;
                    }
                }
                else {
                label_96:
                    v1_1 = 0;
                }

                if((arg11 & 5) == 5) {
                    arg11 = this.getWidth() - v2.right - ((e)v0).rightMargin + ((e)v0).i;
                    if(arg11 < arg10.right) {
                        this.e(arg9, arg11 - arg10.right);
                        v1_1 = 1;
                    }
                }

                if(v1_1 == 0) {
                    this.e(arg9, 0);
                }

                CoordinatorLayout.a(v2);
            }
        }
    }

    private void a(View arg5, View arg6, int arg7) {
        Rect v0 = CoordinatorLayout.e();
        Rect v1 = CoordinatorLayout.e();
        try {
            this.a(arg6, v0);
            this.a(arg5, arg7, v0, v1);
            arg5.layout(v1.left, v1.top, v1.right, v1.bottom);
        }
        catch(Throwable v5) {
            CoordinatorLayout.a(v0);
            CoordinatorLayout.a(v1);
            throw v5;
        }

        CoordinatorLayout.a(v0);
        CoordinatorLayout.a(v1);
    }

    void a(View arg1, Rect arg2) {
        u.b(((ViewGroup)this), arg1, arg2);
    }

    void a(View arg12, int arg13, Rect arg14, Rect arg15) {
        ViewGroup$LayoutParams v0 = arg12.getLayoutParams();
        int v9 = arg12.getMeasuredWidth();
        int v10 = arg12.getMeasuredHeight();
        this.a(arg12, arg13, arg14, arg15, v0, v9, v10);
        this.a(((e)v0), arg15, v9, v10);
    }

    private void a(List arg5) {
        arg5.clear();
        boolean v0 = this.isChildrenDrawingOrderEnabled();
        int v1 = this.getChildCount();
        int v2;
        for(v2 = v1 - 1; v2 >= 0; --v2) {
            int v3 = v0 ? this.getChildDrawingOrder(v1, v2) : v2;
            arg5.add(this.getChildAt(v3));
        }

        if(CoordinatorLayout.d != null) {
            Collections.sort(arg5, CoordinatorLayout.d);
        }
    }

    private void a(boolean arg14) {
        int v0 = this.getChildCount();
        int v2;
        for(v2 = 0; v2 < v0; ++v2) {
            View v3 = this.getChildAt(v2);
            b v4 = v3.getLayoutParams().b();
            if(v4 != null) {
                long v7 = SystemClock.uptimeMillis();
                MotionEvent v5 = MotionEvent.obtain(v7, v7, 3, 0f, 0f, 0);
                if(arg14) {
                    v4.b(this, v3, v5);
                }
                else {
                    v4.a(this, v3, v5);
                }

                v5.recycle();
            }
        }

        int v14;
        for(v14 = 0; v14 < v0; ++v14) {
            this.getChildAt(v14).getLayoutParams().f();
        }

        this.p = null;
        this.m = false;
    }

    private boolean a(MotionEvent arg22, int arg23) {
        CoordinatorLayout v0 = this;
        MotionEvent v1 = arg22;
        int v2 = arg22.getActionMasked();
        List v3 = v0.i;
        v0.a(v3);
        int v4 = v3.size();
        MotionEvent v9 = null;
        int v6 = 0;
        boolean v7 = false;
        int v8 = 0;
        while(v6 < v4) {
            Object v10 = v3.get(v6);
            ViewGroup$LayoutParams v11 = ((View)v10).getLayoutParams();
            b v12 = ((e)v11).b();
            if(!v7 && v8 == 0 || v2 == 0) {
                if(!v7 && v12 != null) {
                    switch(arg23) {
                        case 0: {
                            v7 = v12.b(v0, ((View)v10), v1);
                            break;
                        }
                        case 1: {
                            v7 = v12.a(v0, ((View)v10), v1);
                            break;
                        }
                        default: {
                            break;
                        }
                    }

                    if(v7) {
                        v0.p = ((View)v10);
                    }
                }

                boolean v8_1 = ((e)v11).e();
                boolean v10_1 = ((e)v11).a(v0, ((View)v10));
                v8 = !v10_1 || (v8_1) ? 0 : 1;
                if(!v10_1) {
                    goto label_53;
                }

                if(v8 != 0) {
                    goto label_53;
                }

                break;
            }
            else if(v12 != null) {
                if(v9 == null) {
                    long v15 = SystemClock.uptimeMillis();
                    v9 = MotionEvent.obtain(v15, v15, 3, 0f, 0f, 0);
                }

                switch(arg23) {
                    case 0: {
                        goto label_32;
                    }
                    case 1: {
                        goto label_30;
                    }
                }

                goto label_53;
            label_30:
                v12.a(v0, ((View)v10), v9);
                goto label_53;
            label_32:
                v12.b(v0, ((View)v10), v9);
            }

        label_53:
            ++v6;
        }

        v3.clear();
        return v7;
    }

    e a(View arg7) {
        ViewGroup$LayoutParams v0 = arg7.getLayoutParams();
        if(!((e)v0).b) {
            if((arg7 instanceof a)) {
                b v7 = ((a)arg7).getBehavior();
                if(v7 == null) {
                    Log.e("CoordinatorLayout", "Attached behavior class is null");
                }

                ((e)v0).a(v7);
                goto label_12;
            }

            Class v7_1 = arg7.getClass();
            Annotation v1 = null;
            while(v7_1 != null) {
                v1 = v7_1.getAnnotation(c.class);
                if(v1 != null) {
                    break;
                }

                v7_1 = v7_1.getSuperclass();
            }

            if(v1 != null) {
                try {
                    ((e)v0).a(((c)v1).a().getDeclaredConstructor().newInstance());
                }
                catch(Exception v7_2) {
                    Log.e("CoordinatorLayout", "Default behavior class " + ((c)v1).a().getName() + " could not be instantiated. Did you forget" + " a default constructor?", ((Throwable)v7_2));
                }
            }

        label_12:
            ((e)v0).b = true;
        }

        return ((e)v0);
    }

    final ab a(ab arg4) {
        if(!i.a(this.t, arg4)) {
            this.t = arg4;
            boolean v0 = true;
            boolean v2 = arg4 == null || arg4.b() <= 0 ? false : true;
            this.u = v2;
            if((this.u) || this.getBackground() != null) {
                v0 = false;
            }
            else {
            }

            this.setWillNotDraw(v0);
            arg4 = this.b(arg4);
            this.requestLayout();
        }

        return arg4;
    }

    public e a(AttributeSet arg3) {
        return new e(this.getContext(), arg3);
    }

    protected e a(ViewGroup$LayoutParams arg2) {
        if((arg2 instanceof e)) {
            return new e(((e)arg2));
        }

        if((arg2 instanceof ViewGroup$MarginLayoutParams)) {
            return new e(((ViewGroup$MarginLayoutParams)arg2));
        }

        return new e(arg2);
    }

    void a() {
        int v0 = this.getChildCount();
        boolean v1 = false;
        int v2 = 0;
        while(v2 < v0) {
            if(this.e(this.getChildAt(v2))) {
                v1 = true;
            }
            else {
                ++v2;
                continue;
            }

            break;
        }

        if(v1 != this.s) {
            if(v1) {
                this.b();
            }
            else {
                this.c();
            }
        }
    }

    final void a(int arg18) {
        boolean v13_2;
        int v12;
        CoordinatorLayout v0 = this;
        int v1 = arg18;
        int v2 = t.f(((View)this));
        int v3 = v0.g.size();
        Rect v4 = CoordinatorLayout.e();
        Rect v5 = CoordinatorLayout.e();
        Rect v6 = CoordinatorLayout.e();
        int v8;
        for(v8 = 0; v8 < v3; ++v8) {
            Object v9 = v0.g.get(v8);
            ViewGroup$LayoutParams v10 = ((View)v9).getLayoutParams();
            if(v1 != 0 || ((View)v9).getVisibility() != 8) {
                int v11;
                for(v11 = 0; v11 < v8; ++v11) {
                    if(((e)v10).l == v0.g.get(v11)) {
                        v0.b(((View)v9), v2);
                    }
                }

                v0.a(((View)v9), true, v5);
                if(((e)v10).g != 0 && !v5.isEmpty()) {
                    v12 = android.support.v4.view.d.a(((e)v10).g, v2);
                    int v13 = v12 & 112;
                    if(v13 == 48) {
                        v4.top = Math.max(v4.top, v5.bottom);
                    }
                    else if(v13 != 80) {
                    }
                    else {
                        v4.bottom = Math.max(v4.bottom, this.getHeight() - v5.top);
                    }

                    v12 &= 7;
                    if(v12 != 3) {
                        if(v12 != 5) {
                            goto label_70;
                        }

                        v4.right = Math.max(v4.right, this.getWidth() - v5.left);
                        goto label_70;
                    }

                    v4.left = Math.max(v4.left, v5.right);
                }

            label_70:
                if(((e)v10).h != 0 && ((View)v9).getVisibility() == 0) {
                    v0.a(((View)v9), v4, v2);
                }

                int v10_1 = 2;
                if(v1 != v10_1) {
                    v0.c(((View)v9), v6);
                    if(v6.equals(v5)) {
                        goto label_105;
                    }
                    else {
                        v0.b(((View)v9), v5);
                    }
                }

                for(v12 = v8 + 1; v12 < v3; ++v12) {
                    Object v13_1 = v0.g.get(v12);
                    ViewGroup$LayoutParams v14 = ((View)v13_1).getLayoutParams();
                    b v15 = ((e)v14).b();
                    if(v15 != null && (v15.a(v0, ((View)v13_1), ((View)v9)))) {
                        if(v1 == 0 && (((e)v14).g())) {
                            ((e)v14).h();
                            goto label_103;
                        }

                        if(v1 != v10_1) {
                            v13_2 = v15.b(v0, ((View)v13_1), ((View)v9));
                        }
                        else {
                            v15.c(v0, ((View)v13_1), ((View)v9));
                            v13_2 = true;
                        }

                        if(v1 != 1) {
                            goto label_103;
                        }

                        ((e)v14).a(v13_2);
                    }

                label_103:
                }
            }
            else {
            }

        label_105:
        }

        CoordinatorLayout.a(v4);
        CoordinatorLayout.a(v5);
        CoordinatorLayout.a(v6);
    }

    void a(View arg3, boolean arg4, Rect arg5) {
        if(!arg3.isLayoutRequested()) {
            if(arg3.getVisibility() == 8) {
            }
            else {
                if(arg4) {
                    this.a(arg3, arg5);
                }
                else {
                    arg5.set(arg3.getLeft(), arg3.getTop(), arg3.getRight(), arg3.getBottom());
                }

                return;
            }
        }

        arg5.setEmpty();
    }

    public void a(View arg15, int arg16, int arg17, int arg18, int arg19, int arg20) {
        int v10 = this.getChildCount();
        int v0 = 0;
        int v11;
        for(v11 = 0; v11 < v10; ++v11) {
            View v2 = this.getChildAt(v11);
            if(v2.getVisibility() == 8) {
            }
            else {
                ViewGroup$LayoutParams v1 = v2.getLayoutParams();
                if(!((e)v1).b(arg20)) {
                }
                else {
                    b v1_1 = ((e)v1).b();
                    if(v1_1 != null) {
                        v1_1.a(this, v2, arg15, arg16, arg17, arg18, arg19, arg20);
                        v0 = 1;
                    }
                }
            }
        }

        if(v0 != 0) {
            this.a(1);
        }
    }

    public void a(View arg16, int arg17, int arg18, int[] arg19, int arg20) {
        CoordinatorLayout v8 = this;
        int v9 = this.getChildCount();
        int v0 = 0;
        int v11 = 0;
        int v12 = 0;
        int v13 = 0;
        while(v11 < v9) {
            View v2 = this.getChildAt(v11);
            if(v2.getVisibility() == 8) {
            }
            else {
                ViewGroup$LayoutParams v1 = v2.getLayoutParams();
                if(!((e)v1).b(arg20)) {
                }
                else {
                    b v1_1 = ((e)v1).b();
                    if(v1_1 != null) {
                        int[] v0_1 = v8.k;
                        v8.k[1] = 0;
                        v0_1[0] = 0;
                        v1_1.a(this, v2, arg16, arg17, arg18, v8.k, arg20);
                        v0 = arg17 > 0 ? Math.max(v12, v8.k[0]) : Math.min(v12, v8.k[0]);
                        int v3 = arg18 > 0 ? Math.max(v13, v8.k[1]) : Math.min(v13, v8.k[1]);
                        v12 = v0;
                        v13 = v3;
                        v0 = 1;
                    }
                }
            }

            ++v11;
        }

        arg19[0] = v12;
        arg19[1] = v13;
        if(v0 != 0) {
            this.a(1);
        }
    }

    public boolean a(View arg15, View arg16, int arg17, int arg18) {
        int v7 = arg18;
        int v8 = this.getChildCount();
        int v10 = 0;
        int v11 = 0;
        while(v10 < v8) {
            View v2 = this.getChildAt(v10);
            if(v2.getVisibility() == 8) {
            }
            else {
                ViewGroup$LayoutParams v13 = v2.getLayoutParams();
                b v0 = ((e)v13).b();
                if(v0 != null) {
                    boolean v0_1 = v0.a(this, v2, arg15, arg16, arg17, arg18);
                    ((e)v13).a(v7, v0_1);
                    v11 |= ((int)v0_1);
                }
                else {
                    ((e)v13).a(v7, false);
                }
            }

            ++v10;
        }

        return ((boolean)v11);
    }

    public void b(View arg5) {
        List v0 = this.h.c(arg5);
        if(v0 != null && !v0.isEmpty()) {
            int v1;
            for(v1 = 0; v1 < v0.size(); ++v1) {
                Object v2 = v0.get(v1);
                b v3 = ((View)v2).getLayoutParams().b();
                if(v3 != null) {
                    v3.b(this, ((View)v2), arg5);
                }
            }
        }
    }

    private int b(int arg5) {
        StringBuilder v2;
        String v0;
        if(this.o == null) {
            v0 = "CoordinatorLayout";
            v2 = new StringBuilder();
            v2.append("No keylines defined for ");
            v2.append(this);
            v2.append(" - attempted index lookup ");
            v2.append(arg5);
        }
        else {
            if(arg5 >= 0) {
                if(arg5 >= this.o.length) {
                }
                else {
                    return this.o[arg5];
                }
            }

            v0 = "CoordinatorLayout";
            v2 = new StringBuilder();
            v2.append("Keyline index ");
            v2.append(arg5);
            v2.append(" out of range for ");
            v2.append(this);
        }

        Log.e(v0, v2.toString());
        return 0;
    }

    private ab b(ab arg5) {
        if(arg5.e()) {
            return arg5;
        }

        int v0 = 0;
        int v1 = this.getChildCount();
        while(v0 < v1) {
            View v2 = this.getChildAt(v0);
            if(t.t(v2)) {
                b v3 = v2.getLayoutParams().b();
                if(v3 != null) {
                    arg5 = v3.a(this, v2, arg5);
                    if(arg5.e()) {
                        return arg5;
                    }
                }
            }

            ++v0;
        }

        return arg5;
    }

    private void b(View arg9, int arg10, int arg11) {
        ViewGroup$LayoutParams v0 = arg9.getLayoutParams();
        int v1 = android.support.v4.view.d.a(CoordinatorLayout.d(((e)v0).c), arg11);
        int v2 = v1 & 7;
        v1 &= 112;
        int v3 = this.getWidth();
        int v4 = this.getHeight();
        int v5 = arg9.getMeasuredWidth();
        int v6 = arg9.getMeasuredHeight();
        if(arg11 == 1) {
            arg10 = v3 - arg10;
        }

        arg10 = this.b(arg10) - v5;
        arg11 = 0;
        if(v2 == 1) {
            arg10 += v5 / 2;
        }
        else if(v2 != 5) {
        }
        else {
            arg10 += v5;
        }

        if(v1 == 16) {
            arg11 = v6 / 2;
        }
        else if(v1 != 80) {
        }
        else {
            arg11 = v6;
        }

        arg10 = Math.max(this.getPaddingLeft() + ((e)v0).leftMargin, Math.min(arg10, v3 - this.getPaddingRight() - v5 - ((e)v0).rightMargin));
        arg11 = Math.max(this.getPaddingTop() + ((e)v0).topMargin, Math.min(arg11, v4 - this.getPaddingBottom() - v6 - ((e)v0).bottomMargin));
        arg9.layout(arg10, arg11, v5 + arg10, v6 + arg11);
    }

    void b() {
        if(this.n) {
            if(this.r == null) {
                this.r = new f(this);
            }

            this.getViewTreeObserver().addOnPreDrawListener(this.r);
        }

        this.s = true;
    }

    void b(View arg18, int arg19) {
        CoordinatorLayout v8 = this;
        View v9 = arg18;
        ViewGroup$LayoutParams v10 = arg18.getLayoutParams();
        if(((e)v10).k != null) {
            Rect v11 = CoordinatorLayout.e();
            Rect v12 = CoordinatorLayout.e();
            Rect v13 = CoordinatorLayout.e();
            v8.a(((e)v10).k, v11);
            int v14 = 0;
            v8.a(v9, false, v12);
            int v15 = arg18.getMeasuredWidth();
            int v7 = arg18.getMeasuredHeight();
            int v16 = v7;
            this.a(arg18, arg19, v11, v13, v10, v15, v7);
            if(v13.left != v12.left || v13.top != v12.top) {
                v14 = 1;
            }

            v8.a(((e)v10), v13, v15, v16);
            int v0 = v13.left - v12.left;
            int v1 = v13.top - v12.top;
            if(v0 != 0) {
                t.f(v9, v0);
            }

            if(v1 != 0) {
                t.e(v9, v1);
            }

            if(v14 != 0) {
                b v0_1 = ((e)v10).b();
                if(v0_1 != null) {
                    v0_1.b(v8, v9, ((e)v10).k);
                }
            }

            CoordinatorLayout.a(v11);
            CoordinatorLayout.a(v12);
            CoordinatorLayout.a(v13);
        }
    }

    void b(View arg1, Rect arg2) {
        arg1.getLayoutParams().a(arg2);
    }

    public void b(View arg10, View arg11, int arg12, int arg13) {
        this.x.a(arg10, arg11, arg12, arg13);
        this.q = arg11;
        int v0 = this.getChildCount();
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            View v4 = this.getChildAt(v1);
            ViewGroup$LayoutParams v2 = v4.getLayoutParams();
            if(!((e)v2).b(arg13)) {
            }
            else {
                b v2_1 = ((e)v2).b();
                if(v2_1 != null) {
                    v2_1.b(this, v4, arg10, arg11, arg12, arg13);
                }
            }
        }
    }

    public List c(View arg2) {
        List v2 = this.h.d(arg2);
        this.j.clear();
        if(v2 != null) {
            this.j.addAll(((Collection)v2));
        }

        return this.j;
    }

    private static int c(int arg1) {
        if((arg1 & 7) == 0) {
            arg1 |= 8388611;
        }

        if((arg1 & 112) == 0) {
            arg1 |= 48;
        }

        return arg1;
    }

    void c() {
        if((this.n) && this.r != null) {
            this.getViewTreeObserver().removeOnPreDrawListener(this.r);
        }

        this.s = false;
    }

    void c(View arg1, Rect arg2) {
        arg2.set(arg1.getLayoutParams().c());
    }

    public void c(View arg6, int arg7) {
        this.x.a(arg6, arg7);
        int v0 = this.getChildCount();
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            View v2 = this.getChildAt(v1);
            ViewGroup$LayoutParams v3 = v2.getLayoutParams();
            if(!((e)v3).b(arg7)) {
            }
            else {
                b v4 = ((e)v3).b();
                if(v4 != null) {
                    v4.a(this, v2, arg6, arg7);
                }

                ((e)v3).a(arg7);
                ((e)v3).h();
            }
        }

        this.q = null;
    }

    protected boolean checkLayoutParams(ViewGroup$LayoutParams arg2) {
        boolean v2 = !(arg2 instanceof e) || !super.checkLayoutParams(arg2) ? false : true;
        return v2;
    }

    public List d(View arg2) {
        List v2 = this.h.c(arg2);
        this.j.clear();
        if(v2 != null) {
            this.j.addAll(((Collection)v2));
        }

        return this.j;
    }

    private static int d(int arg0) {
        if(arg0 == 0) {
            arg0 = 8388661;
        }

        return arg0;
    }

    private void d(View arg10, int arg11) {
        ViewGroup$LayoutParams v0 = arg10.getLayoutParams();
        Rect v7 = CoordinatorLayout.e();
        v7.set(this.getPaddingLeft() + ((e)v0).leftMargin, this.getPaddingTop() + ((e)v0).topMargin, this.getWidth() - this.getPaddingRight() - ((e)v0).rightMargin, this.getHeight() - this.getPaddingBottom() - ((e)v0).bottomMargin);
        if(this.t != null && (t.t(((View)this))) && !t.t(arg10)) {
            v7.left += this.t.a();
            v7.top += this.t.b();
            v7.right -= this.t.c();
            v7.bottom -= this.t.d();
        }

        Rect v8 = CoordinatorLayout.e();
        android.support.v4.view.d.a(CoordinatorLayout.c(((e)v0).c), arg10.getMeasuredWidth(), arg10.getMeasuredHeight(), v7, v8, arg11);
        arg10.layout(v8.left, v8.top, v8.right, v8.bottom);
        CoordinatorLayout.a(v7);
        CoordinatorLayout.a(v8);
    }

    protected e d() {
        return new e(-2, -2);
    }

    protected boolean drawChild(Canvas arg9, View arg10, long arg11) {
        ViewGroup$LayoutParams v0 = arg10.getLayoutParams();
        if(((e)v0).a != null) {
            float v1 = ((e)v0).a.d(this, arg10);
            if(v1 > 0f) {
                if(this.l == null) {
                    this.l = new Paint();
                }

                this.l.setColor(((e)v0).a.c(this, arg10));
                this.l.setAlpha(CoordinatorLayout.a(Math.round(v1 * 255f), 0, 255));
                int v0_1 = arg9.save();
                if(arg10.isOpaque()) {
                    arg9.clipRect(((float)arg10.getLeft()), ((float)arg10.getTop()), ((float)arg10.getRight()), ((float)arg10.getBottom()), Region$Op.DIFFERENCE);
                }

                arg9.drawRect(((float)this.getPaddingLeft()), ((float)this.getPaddingTop()), ((float)(this.getWidth() - this.getPaddingRight())), ((float)(this.getHeight() - this.getPaddingBottom())), this.l);
                arg9.restoreToCount(v0_1);
            }
        }

        return super.drawChild(arg9, arg10, arg11);
    }

    protected void drawableStateChanged() {
        super.drawableStateChanged();
        int[] v0 = this.getDrawableState();
        Drawable v1 = this.v;
        int v2 = 0;
        if(v1 != null && (v1.isStateful())) {
            v2 = 0 | v1.setState(v0);
        }

        if(v2 != 0) {
            this.invalidate();
        }
    }

    private static int e(int arg0) {
        if(arg0 == 0) {
            arg0 = 17;
        }

        return arg0;
    }

    private static Rect e() {
        Object v0 = CoordinatorLayout.f.a();
        if(v0 == null) {
            Rect v0_1 = new Rect();
        }

        return ((Rect)v0);
    }

    private void e(View arg3, int arg4) {
        ViewGroup$LayoutParams v0 = arg3.getLayoutParams();
        if(((e)v0).i != arg4) {
            t.f(arg3, arg4 - ((e)v0).i);
            ((e)v0).i = arg4;
        }
    }

    private boolean e(View arg2) {
        return this.h.e(arg2);
    }

    private void f(View arg3, int arg4) {
        ViewGroup$LayoutParams v0 = arg3.getLayoutParams();
        if(((e)v0).j != arg4) {
            t.e(arg3, arg4 - ((e)v0).j);
            ((e)v0).j = arg4;
        }
    }

    private void f() {
        this.g.clear();
        this.h.a();
        int v0 = this.getChildCount();
        int v2;
        for(v2 = 0; v2 < v0; ++v2) {
            View v3 = this.getChildAt(v2);
            e v4 = this.a(v3);
            v4.b(this, v3);
            this.h.a(v3);
            int v5;
            for(v5 = 0; v5 < v0; ++v5) {
                if(v5 == v2) {
                }
                else {
                    View v6 = this.getChildAt(v5);
                    if(v4.a(this, v3, v6)) {
                        if(!this.h.b(v6)) {
                            this.h.a(v6);
                        }

                        this.h.a(v6, v3);
                    }
                }
            }
        }

        this.g.addAll(this.h.b());
        Collections.reverse(this.g);
    }

    private void g() {
        if(Build$VERSION.SDK_INT < 21) {
            return;
        }

        if(t.t(((View)this))) {
            if(this.w == null) {
                this.w = new p() {
                    public ab a(View arg1, ab arg2) {
                        return this.a.a(arg2);
                    }
                };
            }

            t.a(((View)this), this.w);
            this.setSystemUiVisibility(1280);
        }
        else {
            t.a(((View)this), null);
        }
    }

    protected ViewGroup$LayoutParams generateDefaultLayoutParams() {
        return this.d();
    }

    public ViewGroup$LayoutParams generateLayoutParams(AttributeSet arg1) {
        return this.a(arg1);
    }

    protected ViewGroup$LayoutParams generateLayoutParams(ViewGroup$LayoutParams arg1) {
        return this.a(arg1);
    }

    final List getDependencySortedChildren() {
        this.f();
        return Collections.unmodifiableList(this.g);
    }

    public final ab getLastWindowInsets() {
        return this.t;
    }

    public int getNestedScrollAxes() {
        return this.x.a();
    }

    public Drawable getStatusBarBackground() {
        return this.v;
    }

    protected int getSuggestedMinimumHeight() {
        return Math.max(super.getSuggestedMinimumHeight(), this.getPaddingTop() + this.getPaddingBottom());
    }

    protected int getSuggestedMinimumWidth() {
        return Math.max(super.getSuggestedMinimumWidth(), this.getPaddingLeft() + this.getPaddingRight());
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.a(false);
        if(this.s) {
            if(this.r == null) {
                this.r = new f(this);
            }

            this.getViewTreeObserver().addOnPreDrawListener(this.r);
        }

        if(this.t == null && (t.t(((View)this)))) {
            t.s(((View)this));
        }

        this.n = true;
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.a(false);
        if((this.s) && this.r != null) {
            this.getViewTreeObserver().removeOnPreDrawListener(this.r);
        }

        if(this.q != null) {
            this.onStopNestedScroll(this.q);
        }

        this.n = false;
    }

    public void onDraw(Canvas arg5) {
        super.onDraw(arg5);
        if((this.u) && this.v != null) {
            int v0 = this.t != null ? this.t.b() : 0;
            if(v0 <= 0) {
                return;
            }

            this.v.setBounds(0, 0, this.getWidth(), v0);
            this.v.draw(arg5);
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent arg4) {
        int v0 = arg4.getActionMasked();
        if(v0 == 0) {
            this.a(true);
        }

        boolean v4 = this.a(arg4, 0);
        if(v0 == 1 || v0 == 3) {
            this.a(true);
        }

        return v4;
    }

    protected void onLayout(boolean arg2, int arg3, int arg4, int arg5, int arg6) {
        int v2 = t.f(((View)this));
        arg3 = this.g.size();
        for(arg4 = 0; arg4 < arg3; ++arg4) {
            Object v5 = this.g.get(arg4);
            if(((View)v5).getVisibility() == 8) {
            }
            else {
                b v6 = ((View)v5).getLayoutParams().b();
                if(v6 != null && (v6.a(this, ((View)v5), v2))) {
                    goto label_17;
                }

                this.a(((View)v5), v2);
            }

        label_17:
        }
    }

    protected void onMeasure(int arg31, int arg32) {
        int v28;
        int v27;
        int v25;
        ViewGroup$LayoutParams v26;
        int v23;
        int v21;
        int v11;
        int v29;
        int v22;
        CoordinatorLayout v7 = this;
        this.f();
        this.a();
        int v8 = this.getPaddingLeft();
        int v0 = this.getPaddingTop();
        int v9 = this.getPaddingRight();
        int v1 = this.getPaddingBottom();
        int v10 = t.f(((View)this));
        int v12 = v10 == 1 ? 1 : 0;
        int v13 = View$MeasureSpec.getMode(arg31);
        int v14 = View$MeasureSpec.getSize(arg31);
        int v15 = View$MeasureSpec.getMode(arg32);
        int v16 = View$MeasureSpec.getSize(arg32);
        int v17 = v8 + v9;
        int v18 = v0 + v1;
        v0 = this.getSuggestedMinimumWidth();
        v1 = this.getSuggestedMinimumHeight();
        int v19 = v7.t == null || !t.t(((View)this)) ? 0 : 1;
        int v6 = v7.g.size();
        int v4 = v0;
        int v2 = v1;
        int v3 = 0;
        int v5 = 0;
        while(v5 < v6) {
            Object v20 = v7.g.get(v5);
            if(((View)v20).getVisibility() == 8) {
                v22 = v5;
                v29 = v6;
            }
            else {
                ViewGroup$LayoutParams v1_1 = ((View)v20).getLayoutParams();
                if(((e)v1_1).e < 0 || v13 == 0) {
                    v22 = v2;
                label_80:
                    v21 = 0;
                }
                else {
                    v0 = v7.b(((e)v1_1).e);
                    v11 = android.support.v4.view.d.a(CoordinatorLayout.d(((e)v1_1).c), v10) & 7;
                    v22 = v2;
                    if(v11 != 3 || v12 != 0) {
                        v2 = 5;
                        if(v11 == v2 && v12 != 0) {
                            goto label_62;
                        }
                    }
                    else {
                    label_62:
                        v21 = Math.max(0, v14 - v9 - v0);
                        goto label_82;
                    }

                    if(v11 != v2 || v12 != 0) {
                        if(v11 != 3) {
                        }
                        else if(v12 != 0) {
                            goto label_74;
                        }

                        goto label_80;
                    }

                label_74:
                    v21 = Math.max(0, v0 - v8);
                }

            label_82:
                if(v19 == 0 || (t.t(((View)v20)))) {
                    v11 = arg31;
                    v23 = arg32;
                }
                else {
                    v0 = v7.t.a() + v7.t.c();
                    v2 = v7.t.b() + v7.t.d();
                    v11 = View$MeasureSpec.makeMeasureSpec(v14 - v0, v13);
                    v23 = View$MeasureSpec.makeMeasureSpec(v16 - v2, v15);
                }

                b v0_1 = ((e)v1_1).b();
                if(v0_1 != null) {
                    v26 = v1_1;
                    v25 = v22;
                    v27 = v3;
                    v28 = v4;
                    v22 = v5;
                    v29 = v6;
                    if(!v0_1.a(this, v20, v11, v21, v23, 0)) {
                        goto label_129;
                    }
                }
                else {
                    v26 = v1_1;
                    v27 = v3;
                    v28 = v4;
                    v29 = v6;
                    v25 = v22;
                    v22 = v5;
                label_129:
                    this.a(v20, v11, v21, v23, 0);
                }

                v0 = Math.max(v28, v17 + ((View)v20).getMeasuredWidth() + v26.leftMargin + v26.rightMargin);
                v1 = Math.max(v25, v18 + ((View)v20).getMeasuredHeight() + v26.topMargin + v26.bottomMargin);
                v4 = v0;
                v3 = View.combineMeasuredStates(v27, ((View)v20).getMeasuredState());
                v2 = v1;
            }

            v5 = v22 + 1;
            v6 = v29;
        }

        v7.setMeasuredDimension(View.resolveSizeAndState(v4, arg31, -16777216 & v3), View.resolveSizeAndState(v2, arg32, v3 << 16));
    }

    public boolean onNestedFling(View arg12, float arg13, float arg14, boolean arg15) {
        int v0 = this.getChildCount();
        int v2 = 0;
        int v3 = 0;
        while(v2 < v0) {
            View v6 = this.getChildAt(v2);
            if(v6.getVisibility() == 8) {
            }
            else {
                ViewGroup$LayoutParams v4 = v6.getLayoutParams();
                if(!((e)v4).b(0)) {
                }
                else {
                    b v4_1 = ((e)v4).b();
                    if(v4_1 != null) {
                        v3 |= v4_1.a(this, v6, arg12, arg13, arg14, arg15);
                    }
                }
            }

            ++v2;
        }

        if(v3 != 0) {
            this.a(1);
        }

        return ((boolean)v3);
    }

    public boolean onNestedPreFling(View arg11, float arg12, float arg13) {
        int v0 = this.getChildCount();
        int v2 = 0;
        int v3 = 0;
        while(v2 < v0) {
            View v6 = this.getChildAt(v2);
            if(v6.getVisibility() == 8) {
            }
            else {
                ViewGroup$LayoutParams v4 = v6.getLayoutParams();
                if(!((e)v4).b(0)) {
                }
                else {
                    b v4_1 = ((e)v4).b();
                    if(v4_1 != null) {
                        v3 |= v4_1.a(this, v6, arg11, arg12, arg13);
                    }
                }
            }

            ++v2;
        }

        return ((boolean)v3);
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

    protected void onRestoreInstanceState(Parcelable arg7) {
        if(!(arg7 instanceof SavedState)) {
            super.onRestoreInstanceState(arg7);
            return;
        }

        super.onRestoreInstanceState(((SavedState)arg7).getSuperState());
        SparseArray v7 = ((SavedState)arg7).a;
        int v0 = 0;
        int v1 = this.getChildCount();
        while(v0 < v1) {
            View v2 = this.getChildAt(v0);
            int v3 = v2.getId();
            b v4 = this.a(v2).b();
            if(v3 != -1 && v4 != null) {
                Object v3_1 = v7.get(v3);
                if(v3_1 != null) {
                    v4.a(this, v2, ((Parcelable)v3_1));
                }
            }

            ++v0;
        }
    }

    protected Parcelable onSaveInstanceState() {
        SavedState v0 = new SavedState(super.onSaveInstanceState());
        SparseArray v1 = new SparseArray();
        int v2 = this.getChildCount();
        int v3;
        for(v3 = 0; v3 < v2; ++v3) {
            View v4 = this.getChildAt(v3);
            int v5 = v4.getId();
            b v6 = v4.getLayoutParams().b();
            if(v5 != -1 && v6 != null) {
                Parcelable v4_1 = v6.b(this, v4);
                if(v4_1 != null) {
                    v1.append(v5, v4_1);
                }
            }
        }

        v0.a = v1;
        return ((Parcelable)v0);
    }

    public boolean onStartNestedScroll(View arg2, View arg3, int arg4) {
        return this.a(arg2, arg3, arg4, 0);
    }

    public void onStopNestedScroll(View arg2) {
        this.c(arg2, 0);
    }

    public boolean onTouchEvent(MotionEvent arg18) {
        int v6_2;
        boolean v3;
        CoordinatorLayout v0 = this;
        MotionEvent v1 = arg18;
        int v2 = arg18.getActionMasked();
        if(v0.p == null) {
            v3 = v0.a(v1, 1);
            if(v3) {
                goto label_13;
            }
            else {
                goto label_10;
            }
        }
        else {
            v3 = false;
        label_13:
            b v6 = v0.p.getLayoutParams().b();
            if(v6 != null) {
                boolean v6_1 = v6.a(v0, v0.p, v1);
            }
            else {
            label_10:
                v6_2 = 0;
            }
        }

        MotionEvent v8 = null;
        if(v0.p == null) {
            v6_2 |= super.onTouchEvent(arg18);
        }
        else if(v3) {
            long v11 = SystemClock.uptimeMillis();
            v8 = MotionEvent.obtain(v11, v11, 3, 0f, 0f, 0);
            super.onTouchEvent(v8);
        }

        if(v8 != null) {
            v8.recycle();
        }

        if(v2 == 1 || v2 == 3) {
            v0.a(false);
        }

        return ((boolean)v6_2);
    }

    public boolean requestChildRectangleOnScreen(View arg2, Rect arg3, boolean arg4) {
        b v0 = arg2.getLayoutParams().b();
        if(v0 != null && (v0.a(this, arg2, arg3, arg4))) {
            return 1;
        }

        return super.requestChildRectangleOnScreen(arg2, arg3, arg4);
    }

    public void requestDisallowInterceptTouchEvent(boolean arg1) {
        super.requestDisallowInterceptTouchEvent(arg1);
        if((arg1) && !this.m) {
            this.a(false);
            this.m = true;
        }
    }

    public void setFitsSystemWindows(boolean arg1) {
        super.setFitsSystemWindows(arg1);
        this.g();
    }

    public void setOnHierarchyChangeListener(ViewGroup$OnHierarchyChangeListener arg1) {
        this.e = arg1;
    }

    public void setStatusBarBackground(Drawable arg3) {
        Drawable v1_1;
        if(this.v != arg3) {
            Drawable$Callback v1 = null;
            if(this.v != null) {
                this.v.setCallback(v1);
            }

            if(arg3 != null) {
                v1_1 = arg3.mutate();
            }

            this.v = v1_1;
            if(this.v != null) {
                if(this.v.isStateful()) {
                    this.v.setState(this.getDrawableState());
                }

                android.support.v4.graphics.drawable.a.b(this.v, t.f(((View)this)));
                arg3 = this.v;
                boolean v0 = this.getVisibility() == 0 ? true : false;
                arg3.setVisible(v0, false);
                this.v.setCallback(((Drawable$Callback)this));
            }

            t.d(((View)this));
        }
    }

    public void setStatusBarBackgroundColor(int arg2) {
        this.setStatusBarBackground(new ColorDrawable(arg2));
    }

    public void setStatusBarBackgroundResource(int arg2) {
        Drawable v2 = arg2 != 0 ? android.support.v4.content.a.a(this.getContext(), arg2) : null;
        this.setStatusBarBackground(v2);
    }

    public void setVisibility(int arg3) {
        super.setVisibility(arg3);
        boolean v3 = arg3 == 0 ? true : false;
        if(this.v != null && this.v.isVisible() != v3) {
            this.v.setVisible(v3, false);
        }
    }

    protected boolean verifyDrawable(Drawable arg2) {
        boolean v2 = (super.verifyDrawable(arg2)) || arg2 == this.v ? true : false;
        return v2;
    }
}

