package android.support.v7.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.Observable;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build$VERSION;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable$ClassLoaderCreator;
import android.os.Parcelable$Creator;
import android.os.Parcelable;
import android.os.SystemClock;
import android.support.v4.os.d;
import android.support.v4.view.AbsSavedState;
import android.support.v4.view.k;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.Display;
import android.view.FocusFinder;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View$MeasureSpec;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup$LayoutParams;
import android.view.ViewGroup$MarginLayoutParams;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.animation.Interpolator;
import android.widget.EdgeEffect;
import android.widget.OverScroller;
import java.lang.ref.WeakReference;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RecyclerView extends ViewGroup implements k {
    class android.support.v7.widget.RecyclerView$1 implements Runnable {
        android.support.v7.widget.RecyclerView$1(RecyclerView arg1) {
            this.a = arg1;
            super();
        }

        public void run() {
            if(this.a.t) {
                if(this.a.isLayoutRequested()) {
                }
                else if(!this.a.q) {
                    this.a.requestLayout();
                    return;
                }
                else if(this.a.v) {
                    this.a.u = true;
                    return;
                }
                else {
                    this.a.d();
                }
            }
        }
    }

    class android.support.v7.widget.RecyclerView$2 implements Runnable {
        android.support.v7.widget.RecyclerView$2(RecyclerView arg1) {
            this.a = arg1;
            super();
        }

        public void run() {
            if(this.a.z != null) {
                this.a.z.a();
            }

            this.a.G = false;
        }
    }

    final class android.support.v7.widget.RecyclerView$3 implements Interpolator {
        android.support.v7.widget.RecyclerView$3() {
            super();
        }

        public float getInterpolation(float arg3) {
            --arg3;
            return arg3 * arg3 * arg3 * arg3 * arg3 + 1f;
        }
    }

    class android.support.v7.widget.RecyclerView$4 implements b {
        android.support.v7.widget.RecyclerView$4(RecyclerView arg1) {
            this.a = arg1;
            super();
        }

        public void a(w arg3) {
            this.a.n.a(arg3.itemView, this.a.e);
        }

        public void a(w arg2, c arg3, c arg4) {
            this.a.e.c(arg2);
            this.a.b(arg2, arg3, arg4);
        }

        public void b(w arg2, c arg3, c arg4) {
            this.a.a(arg2, arg3, arg4);
        }

        public void c(w arg2, c arg3, c arg4) {
            arg2.setIsRecyclable(false);
            if(this.a.x) {
                if(!this.a.z.a(arg2, arg2, arg3, arg4)) {
                    return;
                }

                goto label_9;
            }
            else if(this.a.z.c(arg2, arg3, arg4)) {
            label_9:
                this.a.p();
            }
        }
    }

    public class SavedState extends AbsSavedState {
        final class android.support.v7.widget.RecyclerView$SavedState$1 implements Parcelable$ClassLoaderCreator {
            android.support.v7.widget.RecyclerView$SavedState$1() {
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
        Parcelable a;

        static {
            SavedState.CREATOR = new android.support.v7.widget.RecyclerView$SavedState$1();
        }

        SavedState(Parcelable arg1) {
            super(arg1);
        }

        SavedState(Parcel arg1, ClassLoader arg2) {
            super(arg1, arg2);
            if(arg2 != null) {
            }
            else {
                arg2 = i.class.getClassLoader();
            }

            this.a = arg1.readParcelable(arg2);
        }

        void a(SavedState arg1) {
            this.a = arg1.a;
        }

        public void writeToParcel(Parcel arg2, int arg3) {
            super.writeToParcel(arg2, arg3);
            arg2.writeParcelable(this.a, 0);
        }
    }

    public abstract class a {
        private boolean mHasStableIds;
        private final android.support.v7.widget.RecyclerView$b mObservable;

        public a() {
            super();
            this.mObservable = new android.support.v7.widget.RecyclerView$b();
            this.mHasStableIds = false;
        }

        public final void bindViewHolder(w arg3, int arg4) {
            arg3.mPosition = arg4;
            if(this.hasStableIds()) {
                arg3.mItemId = this.getItemId(arg4);
            }

            arg3.setFlags(1, 519);
            d.a("RV OnBindView");
            this.onBindViewHolder(arg3, arg4, arg3.getUnmodifiedPayloads());
            arg3.clearPayload();
            ViewGroup$LayoutParams v3 = arg3.itemView.getLayoutParams();
            if((v3 instanceof j)) {
                ((j)v3).e = true;
            }

            d.a();
        }

        public final w createViewHolder(ViewGroup arg2, int arg3) {
            w v2_1;
            try {
                d.a("RV CreateView");
                v2_1 = this.onCreateViewHolder(arg2, arg3);
                if(v2_1.itemView.getParent() != null) {
                    goto label_9;
                }

                v2_1.mItemViewType = arg3;
            }
            catch(Throwable v2) {
                goto label_14;
            }

            d.a();
            return v2_1;
            try {
            label_9:
                throw new IllegalStateException("ViewHolder views must not be attached when created. Ensure that you are not passing \'true\' to the attachToRoot parameter of LayoutInflater.inflate(..., boolean attachToRoot)");
            }
            catch(Throwable v2) {
            label_14:
                d.a();
                throw v2;
            }
        }

        public abstract int getItemCount();

        public long getItemId(int arg3) {
            return -1;
        }

        public int getItemViewType(int arg1) {
            return 0;
        }

        public final boolean hasObservers() {
            return this.mObservable.a();
        }

        public final boolean hasStableIds() {
            return this.mHasStableIds;
        }

        public final void notifyDataSetChanged() {
            this.mObservable.b();
        }

        public final void notifyItemChanged(int arg3) {
            this.mObservable.a(arg3, 1);
        }

        public final void notifyItemChanged(int arg3, Object arg4) {
            this.mObservable.a(arg3, 1, arg4);
        }

        public final void notifyItemInserted(int arg3) {
            this.mObservable.b(arg3, 1);
        }

        public final void notifyItemMoved(int arg2, int arg3) {
            this.mObservable.d(arg2, arg3);
        }

        public final void notifyItemRangeChanged(int arg2, int arg3) {
            this.mObservable.a(arg2, arg3);
        }

        public final void notifyItemRangeChanged(int arg2, int arg3, Object arg4) {
            this.mObservable.a(arg2, arg3, arg4);
        }

        public final void notifyItemRangeInserted(int arg2, int arg3) {
            this.mObservable.b(arg2, arg3);
        }

        public final void notifyItemRangeRemoved(int arg2, int arg3) {
            this.mObservable.c(arg2, arg3);
        }

        public final void notifyItemRemoved(int arg3) {
            this.mObservable.c(arg3, 1);
        }

        public void onAttachedToRecyclerView(RecyclerView arg1) {
        }

        public void onBindViewHolder(w arg1, int arg2, List arg3) {
            this.onBindViewHolder(arg1, arg2);
        }

        public abstract void onBindViewHolder(w arg1, int arg2);

        public abstract w onCreateViewHolder(ViewGroup arg1, int arg2);

        public void onDetachedFromRecyclerView(RecyclerView arg1) {
        }

        public boolean onFailedToRecycleView(w arg1) {
            return 0;
        }

        public void onViewAttachedToWindow(w arg1) {
        }

        public void onViewDetachedFromWindow(w arg1) {
        }

        public void onViewRecycled(w arg1) {
        }

        public void registerAdapterDataObserver(android.support.v7.widget.RecyclerView$c arg2) {
            this.mObservable.registerObserver(arg2);
        }

        public void setHasStableIds(boolean arg2) {
            if(!this.hasObservers()) {
                this.mHasStableIds = arg2;
                return;
            }

            throw new IllegalStateException("Cannot change whether this adapter has stable IDs while the adapter has registered observers.");
        }

        public void unregisterAdapterDataObserver(android.support.v7.widget.RecyclerView$c arg2) {
            this.mObservable.unregisterObserver(arg2);
        }
    }

    class android.support.v7.widget.RecyclerView$b extends Observable {
        android.support.v7.widget.RecyclerView$b() {
            super();
        }

        public boolean a() {
            return this.mObservers.isEmpty() ^ 1;
        }

        public void a(int arg2, int arg3) {
            this.a(arg2, arg3, null);
        }

        public void a(int arg3, int arg4, Object arg5) {
            int v0;
            for(v0 = this.mObservers.size() - 1; v0 >= 0; --v0) {
                this.mObservers.get(v0).a(arg3, arg4, arg5);
            }
        }

        public void b() {
            int v0;
            for(v0 = this.mObservers.size() - 1; v0 >= 0; --v0) {
                this.mObservers.get(v0).a();
            }
        }

        public void b(int arg3, int arg4) {
            int v0;
            for(v0 = this.mObservers.size() - 1; v0 >= 0; --v0) {
                this.mObservers.get(v0).b(arg3, arg4);
            }
        }

        public void c(int arg3, int arg4) {
            int v0;
            for(v0 = this.mObservers.size() - 1; v0 >= 0; --v0) {
                this.mObservers.get(v0).c(arg3, arg4);
            }
        }

        public void d(int arg4, int arg5) {
            int v0;
            for(v0 = this.mObservers.size() - 1; v0 >= 0; --v0) {
                this.mObservers.get(v0).a(arg4, arg5, 1);
            }
        }
    }

    public abstract class android.support.v7.widget.RecyclerView$c {
        public android.support.v7.widget.RecyclerView$c() {
            super();
        }

        public void a(int arg1, int arg2, Object arg3) {
            this.a(arg1, arg2);
        }

        public void a() {
        }

        public void a(int arg1, int arg2, int arg3) {
        }

        public void a(int arg1, int arg2) {
        }

        public void b(int arg1, int arg2) {
        }

        public void c(int arg1, int arg2) {
        }
    }

    public interface android.support.v7.widget.RecyclerView$d {
        int a(int arg1, int arg2);
    }

    public class e {
        public e() {
            super();
        }

        protected EdgeEffect a(RecyclerView arg1, int arg2) {
            return new EdgeEffect(arg1.getContext());
        }
    }

    public abstract class f {
        public interface android.support.v7.widget.RecyclerView$f$a {
            void a();
        }

        interface android.support.v7.widget.RecyclerView$f$b {
            void a(w arg1);
        }

        public class c {
            public int a;
            public int b;
            public int c;
            public int d;

            public c() {
                super();
            }

            public c a(w arg2) {
                return this.a(arg2, 0);
            }

            public c a(w arg1, int arg2) {
                View v1 = arg1.itemView;
                this.a = v1.getLeft();
                this.b = v1.getTop();
                this.c = v1.getRight();
                this.d = v1.getBottom();
                return this;
            }
        }

        private android.support.v7.widget.RecyclerView$f$b a;
        private ArrayList b;
        private long c;
        private long d;
        private long e;
        private long f;

        public f() {
            super();
            this.a = null;
            this.b = new ArrayList();
            this.c = 120;
            this.d = 120;
            this.e = 250;
            this.f = 250;
        }

        public final boolean a(android.support.v7.widget.RecyclerView$f$a arg3) {
            boolean v0 = this.b();
            if(arg3 != null) {
                if(!v0) {
                    arg3.a();
                }
                else {
                    this.b.add(arg3);
                }
            }

            return v0;
        }

        void a(android.support.v7.widget.RecyclerView$f$b arg1) {
            this.a = arg1;
        }

        public c a(t arg1, w arg2, int arg3, List arg4) {
            return this.j().a(arg2);
        }

        public c a(t arg1, w arg2) {
            return this.j().a(arg2);
        }

        public abstract boolean a(w arg1, w arg2, c arg3, c arg4);

        public abstract boolean a(w arg1, c arg2, c arg3);

        public boolean a(w arg1, List arg2) {
            return this.h(arg1);
        }

        public abstract void a();

        public abstract boolean b(w arg1, c arg2, c arg3);

        public abstract boolean b();

        public abstract boolean c(w arg1, c arg2, c arg3);

        public abstract void d();

        public abstract void d(w arg1);

        public long e() {
            return this.e;
        }

        static int e(w arg3) {
            int v0 = arg3.mFlags & 14;
            if(arg3.isInvalid()) {
                return 4;
            }

            if((v0 & 4) == 0) {
                int v1 = arg3.getOldPosition();
                int v3 = arg3.getAdapterPosition();
                int v2 = -1;
                if(v1 != v2 && v3 != v2 && v1 != v3) {
                    v0 |= 2048;
                }
            }

            return v0;
        }

        public long f() {
            return this.c;
        }

        public final void f(w arg2) {
            this.g(arg2);
            if(this.a != null) {
                this.a.a(arg2);
            }
        }

        public long g() {
            return this.d;
        }

        public void g(w arg1) {
        }

        public boolean h(w arg1) {
            return 1;
        }

        public long h() {
            return this.f;
        }

        public final void i() {
            int v0 = this.b.size();
            int v1;
            for(v1 = 0; v1 < v0; ++v1) {
                this.b.get(v1).a();
            }

            this.b.clear();
        }

        public c j() {
            return new c();
        }
    }

    class g implements android.support.v7.widget.RecyclerView$f$b {
        g(RecyclerView arg1) {
            this.a = arg1;
            super();
        }

        public void a(w arg3) {
            arg3.setIsRecyclable(true);
            w v1 = null;
            if(arg3.mShadowedHolder != null && arg3.mShadowingHolder == null) {
                arg3.mShadowedHolder = v1;
            }

            arg3.mShadowingHolder = v1;
            if(!arg3.shouldBeKeptAsChild() && !this.a.a(arg3.itemView) && (arg3.isTmpDetached())) {
                this.a.removeDetachedView(arg3.itemView, false);
            }
        }
    }

    public abstract class h {
        public h() {
            super();
        }

        public void a(Canvas arg1, RecyclerView arg2, t arg3) {
            this.b(arg1, arg2);
        }

        public void a(Rect arg1, View arg2, RecyclerView arg3, t arg4) {
            this.a(arg1, arg2.getLayoutParams().f(), arg3);
        }

        @Deprecated public void a(Canvas arg1, RecyclerView arg2) {
        }

        @Deprecated public void a(Rect arg1, int arg2, RecyclerView arg3) {
            arg1.set(0, 0, 0, 0);
        }

        public void b(Canvas arg1, RecyclerView arg2, t arg3) {
            this.a(arg1, arg2);
        }

        @Deprecated public void b(Canvas arg1, RecyclerView arg2) {
        }
    }

    public abstract class i {
        class android.support.v7.widget.RecyclerView$i$1 implements android.support.v7.widget.bq$b {
            android.support.v7.widget.RecyclerView$i$1(i arg1) {
                this.a = arg1;
                super();
            }

            public int a() {
                return this.a.C();
            }

            public int a(View arg3) {
                return this.a.h(arg3) - arg3.getLayoutParams().leftMargin;
            }

            public View a(int arg2) {
                return this.a.i(arg2);
            }

            public int b() {
                return this.a.A() - this.a.E();
            }

            public int b(View arg3) {
                return this.a.j(arg3) + arg3.getLayoutParams().rightMargin;
            }
        }

        class android.support.v7.widget.RecyclerView$i$2 implements android.support.v7.widget.bq$b {
            android.support.v7.widget.RecyclerView$i$2(i arg1) {
                this.a = arg1;
                super();
            }

            public int a() {
                return this.a.D();
            }

            public int a(View arg3) {
                return this.a.i(arg3) - arg3.getLayoutParams().topMargin;
            }

            public View a(int arg2) {
                return this.a.i(arg2);
            }

            public int b() {
                return this.a.B() - this.a.F();
            }

            public int b(View arg3) {
                return this.a.k(arg3) + arg3.getLayoutParams().bottomMargin;
            }
        }

        public interface android.support.v7.widget.RecyclerView$i$a {
            void b(int arg1, int arg2);
        }

        public class android.support.v7.widget.RecyclerView$i$b {
            public int a;
            public int b;
            public boolean c;
            public boolean d;

            public android.support.v7.widget.RecyclerView$i$b() {
                super();
            }
        }

        private final android.support.v7.widget.bq$b a;
        private final android.support.v7.widget.bq$b b;
        private boolean c;
        private boolean d;
        private int e;
        private int f;
        private int g;
        private int h;
        ae p;
        RecyclerView q;
        bq r;
        bq s;
        s t;
        boolean u;
        boolean v;
        boolean w;
        int x;
        boolean y;

        public i() {
            super();
            this.a = new android.support.v7.widget.RecyclerView$i$1(this);
            this.b = new android.support.v7.widget.RecyclerView$i$2(this);
            this.r = new bq(this.a);
            this.s = new bq(this.b);
            this.u = false;
            this.v = false;
            this.w = false;
            this.c = true;
            this.d = true;
        }

        public int A() {
            return this.g;
        }

        public int B() {
            return this.h;
        }

        public int C() {
            int v0 = this.q != null ? this.q.getPaddingLeft() : 0;
            return v0;
        }

        public int D() {
            int v0 = this.q != null ? this.q.getPaddingTop() : 0;
            return v0;
        }

        public int E() {
            int v0 = this.q != null ? this.q.getPaddingRight() : 0;
            return v0;
        }

        public int F() {
            int v0 = this.q != null ? this.q.getPaddingBottom() : 0;
            return v0;
        }

        public View G() {
            View v1 = null;
            if(this.q == null) {
                return v1;
            }

            View v0 = this.q.getFocusedChild();
            if(v0 != null) {
                if(this.p.c(v0)) {
                }
                else {
                    return v0;
                }
            }

            return v1;
        }

        public int H() {
            a v0 = this.q != null ? this.q.getAdapter() : null;
            int v0_1 = v0 != null ? v0.getItemCount() : 0;
            return v0_1;
        }

        public int I() {
            return android.support.v4.view.t.k(this.q);
        }

        public int J() {
            return android.support.v4.view.t.l(this.q);
        }

        void K() {
            if(this.t != null) {
                this.t.b();
            }
        }

        public void L() {
            this.u = true;
        }

        boolean M() {
            int v0 = this.x();
            int v2;
            for(v2 = 0; v2 < v0; ++v2) {
                ViewGroup$LayoutParams v3 = this.i(v2).getLayoutParams();
                if(v3.width < 0 && v3.height < 0) {
                    return 1;
                }
            }

            return 0;
        }

        public void a(int arg1, android.support.v7.widget.RecyclerView$i$a arg2) {
        }

        public void a(int arg1, int arg2, t arg3, android.support.v7.widget.RecyclerView$i$a arg4) {
        }

        public void a(View arg6, boolean arg7, Rect arg8) {
            if(arg7) {
                Rect v7 = arg6.getLayoutParams().d;
                arg8.set(-v7.left, -v7.top, arg6.getWidth() + v7.right, arg6.getHeight() + v7.bottom);
            }
            else {
                arg8.set(0, 0, arg6.getWidth(), arg6.getHeight());
            }

            if(this.q != null) {
                Matrix v7_1 = arg6.getMatrix();
                if(v7_1 != null && !v7_1.isIdentity()) {
                    RectF v0 = this.q.l;
                    v0.set(arg8);
                    v7_1.mapRect(v0);
                    arg8.set(((int)Math.floor(((double)v0.left))), ((int)Math.floor(((double)v0.top))), ((int)Math.ceil(((double)v0.right))), ((int)Math.ceil(((double)v0.bottom))));
                }
            }

            arg8.offset(arg6.getLeft(), arg6.getTop());
        }

        public void a(t arg1) {
        }

        public void a(RecyclerView arg1, p arg2) {
            this.e(arg1);
        }

        public void a(AccessibilityEvent arg3) {
            this.a(this.q.e, this.q.D, arg3);
        }

        public void a(String arg2) {
            if(this.q != null) {
                this.q.a(arg2);
            }
        }

        public int a(p arg1, t arg2) {
            int v2 = 1;
            if(this.q != null) {
                if(this.q.m == null) {
                }
                else if(this.f()) {
                    v2 = this.q.m.getItemCount();
                }
            }

            return v2;
        }

        void a(View arg3, android.support.v4.view.a.c arg4) {
            w v0 = RecyclerView.e(arg3);
            if(v0 != null && !v0.isRemoved() && !this.p.c(v0.itemView)) {
                this.a(this.q.e, this.q.D, arg3, arg4);
            }
        }

        void a(android.support.v4.view.a.c arg3) {
            this.a(this.q.e, this.q.D, arg3);
        }

        boolean a(int arg3, Bundle arg4) {
            return this.a(this.q.e, this.q.D, arg3, arg4);
        }

        boolean a(View arg8, int arg9, Bundle arg10) {
            return this.a(this.q.e, this.q.D, arg8, arg9, arg10);
        }

        public void a(RecyclerView arg1) {
        }

        public void a(a arg1, a arg2) {
        }

        public boolean a(RecyclerView arg3, View arg4, Rect arg5, boolean arg6, boolean arg7) {
            int[] v4 = this.b(arg3, arg4, arg5, arg6);
            int v0 = v4[0];
            int v4_1 = v4[1];
            if((arg7) && !this.d(arg3, v0, v4_1)) {
                return 0;
            }
            else if(v0 == 0) {
                if(v4_1 != 0) {
                }
                else {
                    return 0;
                }
            }

            if(arg6) {
                arg3.scrollBy(v0, v4_1);
            }
            else {
                arg3.a(v0, v4_1);
            }

            return 1;
        }

        public int a(int arg1, p arg2, t arg3) {
            return 0;
        }

        public boolean a(RecyclerView arg1, ArrayList arg2, int arg3, int arg4) {
            return 0;
        }

        public void a(RecyclerView arg1, int arg2, int arg3) {
        }

        public void a(RecyclerView arg1, int arg2, int arg3, int arg4) {
        }

        public void a(RecyclerView arg1, int arg2, int arg3, Object arg4) {
            this.c(arg1, arg2, arg3);
        }

        public boolean a(j arg1) {
            boolean v1 = arg1 != null ? true : false;
            return v1;
        }

        public static int a(int arg2, int arg3, int arg4) {
            int v0 = View$MeasureSpec.getMode(arg2);
            arg2 = View$MeasureSpec.getSize(arg2);
            if(v0 != -2147483648) {
                if(v0 != 1073741824) {
                    arg2 = Math.max(arg3, arg4);
                }

                return arg2;
            }

            return Math.min(arg2, Math.max(arg3, arg4));
        }

        public View a(View arg1, int arg2, p arg3, t arg4) {
            return null;
        }

        public abstract j a();

        public j a(Context arg2, AttributeSet arg3) {
            return new j(arg2, arg3);
        }

        public j a(ViewGroup$LayoutParams arg2) {
            if((arg2 instanceof j)) {
                return new j(((j)arg2));
            }

            if((arg2 instanceof ViewGroup$MarginLayoutParams)) {
                return new j(((ViewGroup$MarginLayoutParams)arg2));
            }

            return new j(arg2);
        }

        public void a(p arg1, t arg2, int arg3, int arg4) {
            this.q.e(arg3, arg4);
        }

        public void a(Parcelable arg1) {
        }

        public boolean a(RecyclerView arg1, t arg2, View arg3, View arg4) {
            return this.a(arg1, arg3, arg4);
        }

        public boolean a(RecyclerView arg7, View arg8, Rect arg9, boolean arg10) {
            return this.a(arg7, arg8, arg9, arg10, false);
        }

        public static int a(int arg4, int arg5, int arg6, int arg7, boolean arg8) {
            arg4 -= arg6;
            arg6 = 0;
            arg4 = Math.max(0, arg4);
            int v0 = -2;
            int v1 = -1;
            int v2 = -2147483648;
            int v3 = 1073741824;
            if(!arg8) {
                if(arg7 >= 0) {
                label_9:
                    arg4 = arg7;
                    arg6 = 1073741824;
                    goto label_34;
                }

                if(arg7 == v1) {
                    arg6 = arg5;
                    goto label_34;
                }

                if(arg7 == v0) {
                    if(arg5 != v2 && arg5 != v3) {
                        goto label_34;
                    }

                    arg6 = -2147483648;
                    goto label_34;
                }

            label_33:
                arg4 = 0;
            }
            else if(arg7 >= 0) {
                goto label_9;
            }
            else if(arg7 == v1) {
                if(arg5 != v2) {
                    if(arg5 != 0 && arg5 == v3) {
                        goto label_19;
                    }

                    arg7 = 0;
                }
                else {
                label_19:
                    arg7 = arg4;
                }

                arg6 = 0;
                arg4 = arg7;
            }
            else {
                goto label_33;
            }

        label_34:
            return View$MeasureSpec.makeMeasureSpec(arg4, arg6);
        }

        public static android.support.v7.widget.RecyclerView$i$b a(Context arg2, AttributeSet arg3, int arg4, int arg5) {
            android.support.v7.widget.RecyclerView$i$b v0 = new android.support.v7.widget.RecyclerView$i$b();
            TypedArray v2 = arg2.obtainStyledAttributes(arg3, android.support.v7.e.a$c.RecyclerView, arg4, arg5);
            v0.a = v2.getInt(android.support.v7.e.a$c.RecyclerView_android_orientation, 1);
            v0.b = v2.getInt(android.support.v7.e.a$c.RecyclerView_spanCount, 1);
            v0.c = v2.getBoolean(android.support.v7.e.a$c.RecyclerView_reverseLayout, false);
            v0.d = v2.getBoolean(android.support.v7.e.a$c.RecyclerView_stackFromEnd, false);
            v2.recycle();
            return v0;
        }

        private void a(int arg1, View arg2) {
            this.p.e(arg1);
        }

        private void a(p arg3, int arg4, View arg5) {
            w v0 = RecyclerView.e(arg5);
            if(v0.shouldIgnore()) {
                return;
            }

            if(!v0.isInvalid() || (v0.isRemoved()) || (this.q.m.hasStableIds())) {
                this.h(arg4);
                arg3.c(arg5);
                this.q.h.h(v0);
            }
            else {
                this.g(arg4);
                arg3.b(v0);
            }
        }

        private void a(View arg5, int arg6, boolean arg7) {
            w v0 = RecyclerView.e(arg5);
            if((arg7) || (v0.isRemoved())) {
                this.q.h.e(v0);
            }
            else {
                this.q.h.f(v0);
            }

            ViewGroup$LayoutParams v7 = arg5.getLayoutParams();
            if((v0.wasReturnedFromScrap()) || (v0.isScrap())) {
                if(v0.isScrap()) {
                    v0.unScrap();
                }
                else {
                    v0.clearReturnedFromScrapFlag();
                }

                this.p.a(arg5, arg6, arg5.getLayoutParams(), false);
            }
            else if(arg5.getParent() == this.q) {
                int v1 = this.p.b(arg5);
                int v3 = -1;
                if(arg6 == v3) {
                    arg6 = this.p.b();
                }

                if(v1 != v3) {
                    if(v1 == arg6) {
                        goto label_68;
                    }

                    this.q.n.f(v1, arg6);
                    goto label_68;
                }

                StringBuilder v7_1 = new StringBuilder();
                v7_1.append("Added View has RecyclerView as parent but view is not a real child. Unfiltered index:");
                v7_1.append(this.q.indexOfChild(arg5));
                v7_1.append(this.q.a());
                throw new IllegalStateException(v7_1.toString());
            }
            else {
                this.p.a(arg5, arg6, false);
                ((j)v7).e = true;
                if(this.t == null) {
                    goto label_68;
                }

                if(!this.t.d()) {
                    goto label_68;
                }

                this.t.b(arg5);
            }

        label_68:
            if(((j)v7).f) {
                v0.itemView.invalidate();
                ((j)v7).f = false;
            }
        }

        public void a(View arg1, Rect arg2) {
            RecyclerView.a(arg1, arg2);
        }

        public void a(int arg2, p arg3) {
            View v0 = this.i(arg2);
            this.g(arg2);
            arg3.a(v0);
        }

        public void a(Rect arg3, int arg4, int arg5) {
            this.g(i.a(arg4, arg3.width() + this.C() + this.E(), this.I()), i.a(arg5, arg3.height() + this.D() + this.F(), this.J()));
        }

        public void a(p arg4, t arg5, android.support.v4.view.a.c arg6) {
            int v1 = -1;
            if((this.q.canScrollVertically(v1)) || (this.q.canScrollHorizontally(v1))) {
                arg6.a(8192);
                arg6.i(true);
            }

            if((this.q.canScrollVertically(1)) || (this.q.canScrollHorizontally(1))) {
                arg6.a(4096);
                arg6.i(true);
            }

            arg6.a(android.support.v4.view.a.c$a.a(this.a(arg4, arg5), this.b(arg4, arg5), this.e(arg4, arg5), this.d(arg4, arg5)));
        }

        public void a(p arg3) {
            int v0;
            for(v0 = this.x() - 1; v0 >= 0; --v0) {
                this.a(arg3, v0, this.i(v0));
            }
        }

        public void a(p arg7, t arg8, View arg9, android.support.v4.view.a.c arg10) {
            int v0 = this.f() ? this.d(arg9) : 0;
            int v2 = this.e() ? this.d(arg9) : 0;
            arg10.b(android.support.v4.view.a.c$b.a(v0, 1, v2, 1, false, false));
        }

        public void a(p arg2, t arg3, AccessibilityEvent arg4) {
            if(this.q != null) {
                if(arg4 == null) {
                }
                else {
                    boolean v3 = true;
                    if(!this.q.canScrollVertically(1)) {
                        int v0 = -1;
                        if(!this.q.canScrollVertically(v0) && !this.q.canScrollHorizontally(v0)) {
                            if(this.q.canScrollHorizontally(1)) {
                            }
                            else {
                                v3 = false;
                            }
                        }
                    }

                    arg4.setScrollable(v3);
                    if(this.q.m == null) {
                        return;
                    }

                    arg4.setItemCount(this.q.m.getItemCount());
                }
            }
        }

        void a(s arg2) {
            if(this.t == arg2) {
                this.t = null;
            }
        }

        public void a(View arg2) {
            this.a(arg2, -1);
        }

        public void a(View arg2, int arg3) {
            this.a(arg2, arg3, true);
        }

        public void a(View arg6, int arg7, int arg8) {
            ViewGroup$LayoutParams v0 = arg6.getLayoutParams();
            Rect v1 = this.q.k(arg6);
            arg7 += v1.left + v1.right;
            arg8 += v1.top + v1.bottom;
            arg7 = i.a(this.A(), this.y(), this.C() + this.E() + ((j)v0).leftMargin + ((j)v0).rightMargin + arg7, ((j)v0).width, this.e());
            arg8 = i.a(this.B(), this.z(), this.D() + this.F() + ((j)v0).topMargin + ((j)v0).bottomMargin + arg8, ((j)v0).height, this.f());
            if(this.b(arg6, arg7, arg8, ((j)v0))) {
                arg6.measure(arg7, arg8);
            }
        }

        public void a(View arg4, int arg5, int arg6, int arg7, int arg8) {
            ViewGroup$LayoutParams v0 = arg4.getLayoutParams();
            arg4.layout(arg5 + ((j)v0).d.left + ((j)v0).leftMargin, arg6 + ((j)v0).d.top + ((j)v0).topMargin, arg7 - ((j)v0).d.right - ((j)v0).rightMargin, arg8 - ((j)v0).d.bottom - ((j)v0).bottomMargin);
        }

        public void a(View arg3, int arg4, j arg5) {
            w v0 = RecyclerView.e(arg3);
            if(v0.isRemoved()) {
                this.q.h.e(v0);
            }
            else {
                this.q.h.f(v0);
            }

            this.p.a(arg3, arg4, ((ViewGroup$LayoutParams)arg5), v0.isRemoved());
        }

        public void a(View arg1, p arg2) {
            this.c(arg1);
            arg2.a(arg1);
        }

        public boolean a(p arg2, t arg3, int arg4, Bundle arg5) {
            int v2;
            if(this.q == null) {
                return 0;
            }

            if(arg4 == 4096) {
                v2 = this.q.canScrollVertically(1) ? this.B() - this.D() - this.F() : 0;
                if(this.q.canScrollHorizontally(1)) {
                    arg4 = this.A() - this.C() - this.E();
                    goto label_52;
                }

            label_10:
                arg4 = 0;
            }
            else if(arg4 != 8192) {
                v2 = 0;
                goto label_10;
            }
            else {
                arg4 = -1;
                v2 = this.q.canScrollVertically(arg4) ? -(this.B() - this.D() - this.F()) : 0;
                if(!this.q.canScrollHorizontally(arg4)) {
                    goto label_10;
                }

                arg4 = -(this.A() - this.C() - this.E());
            }

        label_52:
            if(v2 == 0 && arg4 == 0) {
                return 0;
            }

            this.q.a(arg4, v2);
            return 1;
        }

        public boolean a(p arg1, t arg2, View arg3, int arg4, Bundle arg5) {
            return 0;
        }

        @Deprecated public boolean a(RecyclerView arg1, View arg2, View arg3) {
            boolean v1 = (this.u()) || (arg1.o()) ? true : false;
            return v1;
        }

        boolean a(View arg3, int arg4, int arg5, j arg6) {
            boolean v3 = !this.c || !i.b(arg3.getMeasuredWidth(), arg4, arg6.width) || !i.b(arg3.getMeasuredHeight(), arg5, arg6.height) ? true : false;
            return v3;
        }

        public boolean a(View arg3, boolean arg4, boolean arg5) {
            int v0 = 24579;
            boolean v3 = !this.r.a(arg3, v0) || !this.s.a(arg3, v0) ? false : true;
            if(arg4) {
                return v3;
            }

            return (((int)v3)) ^ 1;
        }

        public boolean a(Runnable arg2) {
            if(this.q != null) {
                return this.q.removeCallbacks(arg2);
            }

            return 0;
        }

        public int b(p arg1, t arg2) {
            int v2 = 1;
            if(this.q != null) {
                if(this.q.m == null) {
                }
                else if(this.e()) {
                    v2 = this.q.m.getItemCount();
                }
            }

            return v2;
        }

        public void b(View arg2, Rect arg3) {
            if(this.q == null) {
                arg3.set(0, 0, 0, 0);
                return;
            }

            arg3.set(this.q.k(arg2));
        }

        public boolean b() {
            return 0;
        }

        void b(p arg7) {
            int v0 = arg7.e();
            int v1;
            for(v1 = v0 - 1; v1 >= 0; --v1) {
                View v2 = arg7.e(v1);
                w v3 = RecyclerView.e(v2);
                if(v3.shouldIgnore()) {
                }
                else {
                    v3.setIsRecyclable(false);
                    if(v3.isTmpDetached()) {
                        this.q.removeDetachedView(v2, false);
                    }

                    if(this.q.z != null) {
                        this.q.z.d(v3);
                    }

                    v3.setIsRecyclable(true);
                    arg7.b(v2);
                }
            }

            arg7.f();
            if(v0 > 0) {
                this.q.invalidate();
            }
        }

        public int b(int arg1, p arg2, t arg3) {
            return 0;
        }

        public void b(RecyclerView arg1, int arg2, int arg3) {
        }

        void b(RecyclerView arg2, p arg3) {
            this.v = false;
            this.a(arg2, arg3);
        }

        void b(RecyclerView arg2) {
            int v2;
            if(arg2 == null) {
                this.q = null;
                this.p = null;
                v2 = 0;
                this.g = 0;
            }
            else {
                this.q = arg2;
                this.p = arg2.g;
                this.g = arg2.getWidth();
                v2 = arg2.getHeight();
            }

            this.h = v2;
            this.e = 1073741824;
            this.f = 1073741824;
        }

        private static boolean b(int arg3, int arg4, int arg5) {
            int v0 = View$MeasureSpec.getMode(arg4);
            arg4 = View$MeasureSpec.getSize(arg4);
            boolean v1 = false;
            if(arg5 > 0 && arg3 != arg5) {
                return 0;
            }

            if(v0 != -2147483648) {
                if(v0 != 0) {
                    if(v0 != 1073741824) {
                        return 0;
                    }

                    if(arg4 == arg3) {
                        v1 = true;
                    }

                    return v1;
                }

                return 1;
            }

            if(arg4 >= arg3) {
                v1 = true;
            }

            return v1;
        }

        private int[] b(RecyclerView arg8, View arg9, Rect arg10, boolean arg11) {
            int[] v8 = new int[2];
            int v11 = this.C();
            int v0 = this.D();
            int v1 = this.A() - this.E();
            int v2 = this.B() - this.F();
            int v3 = arg9.getLeft() + arg10.left - arg9.getScrollX();
            int v4 = arg9.getTop() + arg10.top - arg9.getScrollY();
            int v9 = arg10.width() + v3;
            int v10 = arg10.height() + v4;
            v3 -= v11;
            int v5 = Math.min(0, v3);
            v4 -= v0;
            v0 = Math.min(0, v4);
            v9 -= v1;
            v1 = Math.max(0, v9);
            v10 = Math.max(0, v10 - v2);
            if(this.v() != 1) {
                if(v5 == 0) {
                    v5 = Math.min(v3, v1);
                }

                v1 = v5;
            }
            else if(v1 != 0) {
            }
            else {
                v1 = Math.max(v5, v9);
            }

            if(v0 != 0) {
            }
            else {
                v0 = Math.min(v4, v10);
            }

            v8[0] = v1;
            v8[1] = v0;
            return v8;
        }

        boolean b(View arg3, int arg4, int arg5, j arg6) {
            boolean v3 = (arg3.isLayoutRequested()) || !this.c || !i.b(arg3.getWidth(), arg4, arg6.width) || !i.b(arg3.getHeight(), arg5, arg6.height) ? true : false;
            return v3;
        }

        public void b(View arg2) {
            this.b(arg2, -1);
        }

        public void b(View arg2, int arg3) {
            this.a(arg2, arg3, false);
        }

        public View c(int arg6) {
            int v0 = this.x();
            int v1;
            for(v1 = 0; v1 < v0; ++v1) {
                View v2 = this.i(v1);
                w v3 = RecyclerView.e(v2);
                if(v3 == null) {
                }
                else if(v3.getLayoutPosition() == arg6 && !v3.shouldIgnore()) {
                    if(!this.q.D.a() && (v3.isRemoved())) {
                        goto label_18;
                    }

                    return v2;
                }

            label_18:
            }

            return null;
        }

        public void c(p arg1, t arg2) {
            Log.e("RecyclerView", "You must override onLayoutChildren(Recycler recycler, State state) ");
        }

        public void c(p arg3) {
            int v0;
            for(v0 = this.x() - 1; v0 >= 0; --v0) {
                if(!RecyclerView.e(this.i(v0)).shouldIgnore()) {
                    this.a(v0, arg3);
                }
            }
        }

        public int c(t arg1) {
            return 0;
        }

        void c(RecyclerView arg2) {
            this.v = true;
            this.d(arg2);
        }

        public boolean c() {
            return this.w;
        }

        public void c(RecyclerView arg1, int arg2, int arg3) {
        }

        public void c(View arg2) {
            this.p.a(arg2);
        }

        public void c(View arg2, int arg3) {
            this.a(arg2, arg3, arg2.getLayoutParams());
        }

        public int d(View arg1) {
            return arg1.getLayoutParams().f();
        }

        public int d(t arg1) {
            return 0;
        }

        public View d(View arg1, int arg2) {
            return null;
        }

        void d(int arg2, int arg3) {
            this.g = View$MeasureSpec.getSize(arg2);
            this.e = View$MeasureSpec.getMode(arg2);
            if(this.e == 0 && !RecyclerView.b) {
                this.g = 0;
            }

            this.h = View$MeasureSpec.getSize(arg3);
            this.f = View$MeasureSpec.getMode(arg3);
            if(this.f == 0 && !RecyclerView.b) {
                this.h = 0;
            }
        }

        public Parcelable d() {
            return null;
        }

        private boolean d(RecyclerView arg7, int arg8, int arg9) {
            View v7 = arg7.getFocusedChild();
            if(v7 == null) {
                return 0;
            }

            int v1 = this.C();
            int v2 = this.D();
            int v3 = this.A() - this.E();
            int v4 = this.B() - this.F();
            Rect v5 = this.q.k;
            this.a(v7, v5);
            if(v5.left - arg8 < v3 && v5.right - arg8 > v1 && v5.top - arg9 < v4) {
                if(v5.bottom - arg9 <= v2) {
                }
                else {
                    return 1;
                }
            }

            return 0;
        }

        public int d(p arg1, t arg2) {
            return 0;
        }

        public void d(RecyclerView arg1) {
        }

        public boolean e() {
            return 0;
        }

        public void e(int arg1) {
        }

        public int e(t arg1) {
            return 0;
        }

        void e(int arg9, int arg10) {
            int v0 = this.x();
            if(v0 == 0) {
                this.q.e(arg9, arg10);
                return;
            }

            int v1 = 0;
            int v3 = 2147483647;
            int v2 = 2147483647;
            int v4 = -2147483648;
            int v5 = -2147483648;
            while(v1 < v0) {
                View v6 = this.i(v1);
                Rect v7 = this.q.k;
                this.a(v6, v7);
                if(v7.left < v3) {
                    v3 = v7.left;
                }

                if(v7.right > v4) {
                    v4 = v7.right;
                }

                if(v7.top < v2) {
                    v2 = v7.top;
                }

                if(v7.bottom > v5) {
                    v5 = v7.bottom;
                }

                ++v1;
            }

            this.q.k.set(v3, v2, v4, v5);
            this.a(this.q.k, arg9, arg10);
        }

        public boolean e(p arg1, t arg2) {
            return 0;
        }

        @Deprecated public void e(RecyclerView arg1) {
        }

        public View e(View arg3) {
            View v1 = null;
            if(this.q == null) {
                return v1;
            }

            arg3 = this.q.c(arg3);
            if(arg3 == null) {
                return v1;
            }

            if(this.p.c(arg3)) {
                return v1;
            }

            return arg3;
        }

        public int f(View arg3) {
            Rect v0 = arg3.getLayoutParams().d;
            return arg3.getMeasuredWidth() + v0.left + v0.right;
        }

        public boolean f() {
            return 0;
        }

        public int f(t arg1) {
            return 0;
        }

        void f(RecyclerView arg3) {
            this.d(View$MeasureSpec.makeMeasureSpec(arg3.getWidth(), 1073741824), View$MeasureSpec.makeMeasureSpec(arg3.getHeight(), 1073741824));
        }

        public void f(int arg3, int arg4) {
            View v0 = this.i(arg3);
            if(v0 != null) {
                this.h(arg3);
                this.c(v0, arg4);
                return;
            }

            StringBuilder v0_1 = new StringBuilder();
            v0_1.append("Cannot move a child from non-existing index:");
            v0_1.append(arg3);
            v0_1.append(this.q.toString());
            throw new IllegalArgumentException(v0_1.toString());
        }

        public int g(View arg3) {
            Rect v0 = arg3.getLayoutParams().d;
            return arg3.getMeasuredHeight() + v0.top + v0.bottom;
        }

        public int g(t arg1) {
            return 0;
        }

        public void g(int arg2) {
            if(this.i(arg2) != null) {
                this.p.a(arg2);
            }
        }

        public void g(int arg2, int arg3) {
            RecyclerView.a(this.q, arg2, arg3);
        }

        public int h(View arg2) {
            return arg2.getLeft() - this.n(arg2);
        }

        public int h(t arg1) {
            return 0;
        }

        public void h(int arg2) {
            this.a(arg2, this.i(arg2));
        }

        public int i(View arg2) {
            return arg2.getTop() - this.l(arg2);
        }

        public View i(int arg2) {
            View v2 = this.p != null ? this.p.b(arg2) : null;
            return v2;
        }

        public void j(int arg2) {
            if(this.q != null) {
                this.q.e(arg2);
            }
        }

        public int j(View arg2) {
            return arg2.getRight() + this.o(arg2);
        }

        public void k(int arg2) {
            if(this.q != null) {
                this.q.d(arg2);
            }
        }

        public int k(View arg2) {
            return arg2.getBottom() + this.m(arg2);
        }

        public void l(int arg1) {
        }

        boolean l() {
            return 0;
        }

        public int l(View arg1) {
            return arg1.getLayoutParams().d.top;
        }

        public int m(View arg1) {
            return arg1.getLayoutParams().d.bottom;
        }

        public int n(View arg1) {
            return arg1.getLayoutParams().d.left;
        }

        public int o(View arg1) {
            return arg1.getLayoutParams().d.right;
        }

        public void q() {
            if(this.q != null) {
                this.q.requestLayout();
            }
        }

        public final boolean r() {
            return this.d;
        }

        public boolean s() {
            return this.v;
        }

        public boolean t() {
            boolean v0 = this.q == null || !this.q.i ? false : true;
            return v0;
        }

        public boolean u() {
            boolean v0 = this.t == null || !this.t.d() ? false : true;
            return v0;
        }

        public int v() {
            return android.support.v4.view.t.f(this.q);
        }

        public int w() {
            return -1;
        }

        public int x() {
            int v0 = this.p != null ? this.p.b() : 0;
            return v0;
        }

        public int y() {
            return this.e;
        }

        public int z() {
            return this.f;
        }
    }

    public class j extends ViewGroup$MarginLayoutParams {
        w c;
        final Rect d;
        boolean e;
        boolean f;

        public j(int arg1, int arg2) {
            super(arg1, arg2);
            this.d = new Rect();
            this.e = true;
            this.f = false;
        }

        public j(Context arg1, AttributeSet arg2) {
            super(arg1, arg2);
            this.d = new Rect();
            this.e = true;
            this.f = false;
        }

        public j(ViewGroup$LayoutParams arg1) {
            super(arg1);
            this.d = new Rect();
            this.e = true;
            this.f = false;
        }

        public j(ViewGroup$MarginLayoutParams arg1) {
            super(arg1);
            this.d = new Rect();
            this.e = true;
            this.f = false;
        }

        public j(j arg1) {
            super(((ViewGroup$LayoutParams)arg1));
            this.d = new Rect();
            this.e = true;
            this.f = false;
        }

        public boolean c() {
            return this.c.isInvalid();
        }

        public boolean d() {
            return this.c.isRemoved();
        }

        public boolean e() {
            return this.c.isUpdated();
        }

        public int f() {
            return this.c.getLayoutPosition();
        }
    }

    public interface android.support.v7.widget.RecyclerView$k {
        void a(View arg1);

        void b(View arg1);
    }

    public abstract class l {
        public abstract boolean a(int arg1, int arg2);
    }

    public interface m {
        boolean a(RecyclerView arg1, MotionEvent arg2);

        void a(boolean arg1);

        void b(RecyclerView arg1, MotionEvent arg2);
    }

    public abstract class n {
        public n() {
            super();
        }

        public void a(RecyclerView arg1, int arg2) {
        }

        public void a(RecyclerView arg1, int arg2, int arg3) {
        }
    }

    public class o {
        class android.support.v7.widget.RecyclerView$o$a {
            final ArrayList a;
            int b;
            long c;
            long d;

            android.support.v7.widget.RecyclerView$o$a() {
                super();
                this.a = new ArrayList();
                this.b = 5;
                this.c = 0;
                this.d = 0;
            }
        }

        SparseArray a;
        private int b;

        public o() {
            super();
            this.a = new SparseArray();
            this.b = 0;
        }

        long a(long arg5, long arg7) {
            if(arg5 == 0) {
                return arg7;
            }

            return arg5 / 4 * 3 + arg7 / 4;
        }

        public w a(int arg2) {
            Object v2 = this.a.get(arg2);
            if(v2 != null && !((android.support.v7.widget.RecyclerView$o$a)v2).a.isEmpty()) {
                return ((android.support.v7.widget.RecyclerView$o$a)v2).a.remove(((android.support.v7.widget.RecyclerView$o$a)v2).a.size() - 1);
            }

            return null;
        }

        public void a() {
            int v0;
            for(v0 = 0; v0 < this.a.size(); ++v0) {
                this.a.valueAt(v0).a.clear();
            }
        }

        void a(int arg3, long arg4) {
            android.support.v7.widget.RecyclerView$o$a v3 = this.b(arg3);
            v3.c = this.a(v3.c, arg4);
        }

        void a(a arg1, a arg2, boolean arg3) {
            if(arg1 != null) {
                this.c();
            }

            if(!arg3 && this.b == 0) {
                this.a();
            }

            if(arg2 != null) {
                this.b();
            }
        }

        public void a(w arg4) {
            int v0 = arg4.getItemViewType();
            ArrayList v1 = this.b(v0).a;
            if(this.a.get(v0).b <= v1.size()) {
                return;
            }

            arg4.resetInternal();
            v1.add(arg4);
        }

        boolean a(int arg5, long arg6, long arg8) {
            long v0 = this.b(arg5).c;
            boolean v5 = v0 == 0 || arg6 + v0 < arg8 ? true : false;
            return v5;
        }

        private android.support.v7.widget.RecyclerView$o$a b(int arg3) {
            Object v0 = this.a.get(arg3);
            if(v0 == null) {
                android.support.v7.widget.RecyclerView$o$a v0_1 = new android.support.v7.widget.RecyclerView$o$a();
                this.a.put(arg3, v0_1);
            }

            return ((android.support.v7.widget.RecyclerView$o$a)v0);
        }

        void b() {
            ++this.b;
        }

        void b(int arg3, long arg4) {
            android.support.v7.widget.RecyclerView$o$a v3 = this.b(arg3);
            v3.d = this.a(v3.d, arg4);
        }

        boolean b(int arg5, long arg6, long arg8) {
            long v0 = this.b(arg5).d;
            boolean v5 = v0 == 0 || arg6 + v0 < arg8 ? true : false;
            return v5;
        }

        void c() {
            --this.b;
        }
    }

    public final class p {
        final ArrayList a;
        ArrayList b;
        final ArrayList c;
        int d;
        o e;
        private final List g;
        private int h;
        private u i;

        public p(RecyclerView arg1) {
            this.f = arg1;
            super();
            this.a = new ArrayList();
            this.b = null;
            this.c = new ArrayList();
            this.g = Collections.unmodifiableList(this.a);
            this.h = 2;
            this.d = 2;
        }

        w a(int arg17, boolean arg18, long arg19) {
            w v10;
            StringBuilder v1_1;
            int v9;
            int v4;
            w v1;
            p v6 = this;
            int v3 = arg17;
            boolean v0 = arg18;
            if(v3 >= 0 && v3 < v6.f.D.e()) {
                w v2 = null;
                boolean v7 = true;
                if(v6.f.D.a()) {
                    v1 = this.f(arg17);
                    if(v1 != null) {
                        v4 = 1;
                    }
                    else {
                        goto label_20;
                    }
                }
                else {
                    v1 = v2;
                label_20:
                    v4 = 0;
                }

                if(v1 == null) {
                    v1 = this.b(arg17, arg18);
                    if(v1 != null) {
                        if(!v6.a(v1)) {
                            if(!v0) {
                                v1.addFlags(4);
                                if(v1.isScrap()) {
                                    v6.f.removeDetachedView(v1.itemView, false);
                                    v1.unScrap();
                                }
                                else if(v1.wasReturnedFromScrap()) {
                                    v1.clearReturnedFromScrapFlag();
                                }

                                v6.b(v1);
                            }

                            v1 = v2;
                        }
                        else {
                            v4 = 1;
                        }
                    }
                }

                if(v1 == null) {
                    int v5 = v6.f.f.b(v3);
                    if(v5 >= 0 && v5 < v6.f.m.getItemCount()) {
                        v9 = v6.f.m.getItemViewType(v5);
                        if(v6.f.m.hasStableIds()) {
                            v1 = v6.a(v6.f.m.getItemId(v5), v9, v0);
                            if(v1 != null) {
                                v1.mPosition = v5;
                                v4 = 1;
                            }
                        }

                        if(v1 == null && v6.i != null) {
                            View v0_1 = v6.i.a(v6, v3, v9);
                            if(v0_1 != null) {
                                v1 = v6.f.b(v0_1);
                                if(v1 == null) {
                                    v1_1 = new StringBuilder();
                                    v1_1.append("getViewForPositionAndType returned a view which does not have a ViewHolder");
                                    v1_1.append(v6.f.a());
                                    throw new IllegalArgumentException(v1_1.toString());
                                }
                                else if(!v1.shouldIgnore()) {
                                }
                                else {
                                    v1_1 = new StringBuilder();
                                    v1_1.append("getViewForPositionAndType returned a view that is ignored. You must call stopIgnoring before returning this view.");
                                    v1_1.append(v6.f.a());
                                    throw new IllegalArgumentException(v1_1.toString());
                                }
                            }
                        }

                        if(v1 == null) {
                            v1 = this.g().a(v9);
                            if(v1 != null) {
                                v1.resetInternal();
                                if(RecyclerView.a) {
                                    v6.f(v1);
                                }
                            }
                        }

                        if(v1 != null) {
                            goto label_162;
                        }

                        long v0_2 = v6.f.getNanoTime();
                        if(arg19 != 9223372036854775807L && !v6.e.a(v9, v0_2, arg19)) {
                            return v2;
                        }

                        v2 = v6.f.m.createViewHolder(v6.f, v9);
                        if(RecyclerView.d) {
                            RecyclerView v5_1 = RecyclerView.l(v2.itemView);
                            if(v5_1 != null) {
                                v2.mNestedRecyclerView = new WeakReference(v5_1);
                            }
                        }

                        v6.e.a(v9, v6.f.getNanoTime() - v0_2);
                        v10 = v2;
                        goto label_163;
                    }

                    v1_1 = new StringBuilder();
                    v1_1.append("Inconsistency detected. Invalid item position ");
                    v1_1.append(v3);
                    v1_1.append("(offset:");
                    v1_1.append(v5);
                    v1_1.append(").");
                    v1_1.append("state:");
                    v1_1.append(v6.f.D.e());
                    v1_1.append(v6.f.a());
                    throw new IndexOutOfBoundsException(v1_1.toString());
                }
                else {
                label_162:
                    v10 = v1;
                }

            label_163:
                v9 = v4;
                if(v9 != 0 && !v6.f.D.a()) {
                    int v0_3 = 8192;
                    if(v10.hasAnyOfTheFlags(v0_3)) {
                        v10.setFlags(0, v0_3);
                        if(v6.f.D.j) {
                            v6.f.a(v10, v6.f.z.a(v6.f.D, v10, f.e(v10) | 4096, v10.getUnmodifiedPayloads()));
                        }
                    }
                }

                if(!v6.f.D.a() || !v10.isBound()) {
                    if((v10.isBound()) && !v10.needsUpdate()) {
                        if(v10.isInvalid()) {
                        }
                        else {
                        label_202:
                            v0 = false;
                            goto label_212;
                        }
                    }

                    v0 = this.a(v10, v6.f.f.b(v3), arg17, arg19);
                }
                else {
                    v10.mPreLayoutPosition = v3;
                    goto label_202;
                }

            label_212:
                ViewGroup$LayoutParams v1_2 = v10.itemView.getLayoutParams();
                if(v1_2 == null) {
                    v1_2 = v6.f.generateDefaultLayoutParams();
                    goto label_217;
                }
                else if(!v6.f.checkLayoutParams(v1_2)) {
                    v1_2 = v6.f.generateLayoutParams(v1_2);
                label_217:
                    v10.itemView.setLayoutParams(v1_2);
                }

                ((j)v1_2).c = v10;
                if(v9 == 0 || !v0) {
                    v7 = false;
                }
                else {
                }

                ((j)v1_2).f = v7;
                return v10;
            }

            v1_1 = new StringBuilder();
            v1_1.append("Invalid item position ");
            v1_1.append(v3);
            v1_1.append("(");
            v1_1.append(v3);
            v1_1.append("). Item count:");
            v1_1.append(v6.f.D.e());
            v1_1.append(v6.f.a());
            throw new IndexOutOfBoundsException(v1_1.toString());
        }

        public void a(View arg4) {
            w v0 = RecyclerView.e(arg4);
            if(v0.isTmpDetached()) {
                this.f.removeDetachedView(arg4, false);
            }

            if(v0.isScrap()) {
                v0.unScrap();
            }
            else if(v0.wasReturnedFromScrap()) {
                v0.clearReturnedFromScrapFlag();
            }

            this.b(v0);
        }

        void a(w arg4, boolean arg5) {
            RecyclerView.c(arg4);
            int v0 = 16384;
            android.support.v4.view.a v2 = null;
            if(arg4.hasAnyOfTheFlags(v0)) {
                arg4.setFlags(0, v0);
                android.support.v4.view.t.a(arg4.itemView, v2);
            }

            if(arg5) {
                this.d(arg4);
            }

            arg4.mOwnerRecyclerView = ((RecyclerView)v2);
            this.g().a(arg4);
        }

        public void a() {
            this.a.clear();
            this.d();
        }

        void a(a arg2, a arg3, boolean arg4) {
            this.a();
            this.g().a(arg2, arg3, arg4);
        }

        void a(int arg5, int arg6, boolean arg7) {
            int v0 = arg5 + arg6;
            int v1;
            for(v1 = this.c.size() - 1; v1 >= 0; --v1) {
                Object v2 = this.c.get(v1);
                if(v2 != null) {
                    if(((w)v2).mPosition >= v0) {
                        ((w)v2).offsetPosition(-arg6, arg7);
                    }
                    else if(((w)v2).mPosition >= arg5) {
                        ((w)v2).addFlags(8);
                        this.d(v1);
                    }
                }
            }
        }

        void a(int arg9, int arg10) {
            int v2;
            int v1;
            int v0;
            if(arg9 < arg10) {
                v0 = arg9;
                v1 = arg10;
                v2 = -1;
            }
            else {
                v1 = arg9;
                v0 = arg10;
                v2 = 1;
            }

            int v3 = this.c.size();
            int v5;
            for(v5 = 0; v5 < v3; ++v5) {
                Object v6 = this.c.get(v5);
                if(v6 != null && ((w)v6).mPosition >= v0) {
                    if(((w)v6).mPosition > v1) {
                    }
                    else if(((w)v6).mPosition == arg9) {
                        ((w)v6).offsetPosition(arg10 - arg9, false);
                    }
                    else {
                        ((w)v6).offsetPosition(v2, false);
                    }
                }
            }
        }

        public void a(int arg1) {
            this.h = arg1;
            this.b();
        }

        void a(o arg2) {
            if(this.e != null) {
                this.e.c();
            }

            this.e = arg2;
            if(this.e != null && this.f.getAdapter() != null) {
                this.e.b();
            }
        }

        void a(u arg1) {
            this.i = arg1;
        }

        private void a(ViewGroup arg5, boolean arg6) {
            int v0;
            for(v0 = arg5.getChildCount() - 1; v0 >= 0; --v0) {
                View v2 = arg5.getChildAt(v0);
                if((v2 instanceof ViewGroup)) {
                    this.a(((ViewGroup)v2), true);
                }
            }

            if(!arg6) {
                return;
            }

            v0 = 4;
            if(arg5.getVisibility() == v0) {
                arg5.setVisibility(0);
                arg5.setVisibility(v0);
            }
            else {
                int v6 = arg5.getVisibility();
                arg5.setVisibility(v0);
                arg5.setVisibility(v6);
            }
        }

        private boolean a(w arg10, int arg11, int arg12, long arg13) {
            arg10.mOwnerRecyclerView = this.f;
            int v2 = arg10.getItemViewType();
            long v7 = this.f.getNanoTime();
            if(arg13 != 9223372036854775807L && !this.e.b(v2, v7, arg13)) {
                return 0;
            }

            this.f.m.bindViewHolder(arg10, arg11);
            this.e.b(arg10.getItemViewType(), this.f.getNanoTime() - v7);
            this.e(arg10);
            if(this.f.D.a()) {
                arg10.mPreLayoutPosition = arg12;
            }

            return 1;
        }

        boolean a(w arg8) {
            if(arg8.isRemoved()) {
                return this.f.D.a();
            }

            if(arg8.mPosition >= 0 && arg8.mPosition < this.f.m.getItemCount()) {
                boolean v1 = false;
                if(!this.f.D.a() && this.f.m.getItemViewType(arg8.mPosition) != arg8.getItemViewType()) {
                    return 0;
                }

                if(this.f.m.hasStableIds()) {
                    if(arg8.getItemId() == this.f.m.getItemId(arg8.mPosition)) {
                        v1 = true;
                    }

                    return v1;
                }

                return 1;
            }

            StringBuilder v1_1 = new StringBuilder();
            v1_1.append("Inconsistency detected. Invalid view holder adapter position");
            v1_1.append(arg8);
            v1_1.append(this.f.a());
            throw new IndexOutOfBoundsException(v1_1.toString());
        }

        w a(long arg7, int arg9, boolean arg10) {
            w v1_1;
            int v0;
            for(v0 = this.a.size() - 1; v0 >= 0; --v0) {
                Object v1 = this.a.get(v0);
                if(((w)v1).getItemId() == arg7 && !((w)v1).wasReturnedFromScrap()) {
                    if(arg9 == ((w)v1).getItemViewType()) {
                        ((w)v1).addFlags(32);
                        if((((w)v1).isRemoved()) && !this.f.D.a()) {
                            ((w)v1).setFlags(2, 14);
                        }

                        return ((w)v1);
                    }
                    else {
                        if(arg10) {
                            goto label_33;
                        }

                        this.a.remove(v0);
                        this.f.removeDetachedView(((w)v1).itemView, false);
                        this.b(((w)v1).itemView);
                    }
                }

            label_33:
            }

            for(v0 = this.c.size() - 1; true; --v0) {
                v1_1 = null;
                if(v0 < 0) {
                    return v1_1;
                }

                Object v2 = this.c.get(v0);
                if(((w)v2).getItemId() == arg7) {
                    if(arg9 == ((w)v2).getItemViewType()) {
                        if(!arg10) {
                            this.c.remove(v0);
                        }

                        return ((w)v2);
                    }
                    else {
                        if(arg10) {
                            goto label_53;
                        }

                        this.d(v0);
                        return v1_1;
                    }
                }

            label_53:
            }

            return v1_1;
        }

        View a(int arg3, boolean arg4) {
            return this.a(arg3, arg4, 9223372036854775807L).itemView;
        }

        void b() {
            int v0 = this.f.n != null ? this.f.n.x : 0;
            this.d = this.h + v0;
            for(v0 = this.c.size() - 1; v0 >= 0; --v0) {
                if(this.c.size() <= this.d) {
                    return;
                }

                this.d(v0);
            }
        }

        public int b(int arg4) {
            if(arg4 >= 0 && arg4 < this.f.D.e()) {
                if(!this.f.D.a()) {
                    return arg4;
                }
                else {
                    return this.f.f.b(arg4);
                }
            }

            StringBuilder v1 = new StringBuilder();
            v1.append("invalid position ");
            v1.append(arg4);
            v1.append(". State ");
            v1.append("item count is ");
            v1.append(this.f.D.e());
            v1.append(this.f.a());
            throw new IndexOutOfBoundsException(v1.toString());
        }

        void b(w arg7) {
            boolean v1 = false;
            if(!arg7.isScrap()) {
                if(arg7.itemView.getParent() != null) {
                }
                else if(arg7.isTmpDetached()) {
                    StringBuilder v1_2 = new StringBuilder();
                    v1_2.append("Tmp detached view should be removed from RecyclerView before it can be recycled: ");
                    v1_2.append(arg7);
                    v1_2.append(this.f.a());
                    throw new IllegalArgumentException(v1_2.toString());
                }
                else if(!arg7.shouldIgnore()) {
                    boolean v0 = arg7.doesTransientStatePreventRecycling();
                    int v3 = this.f.m == null || !v0 || !this.f.m.onFailedToRecycleView(arg7) ? 0 : 1;
                    if(v3 != 0 || (arg7.isRecyclable())) {
                        if(this.d <= 0 || (arg7.hasAnyOfTheFlags(526))) {
                            v3 = 0;
                        }
                        else {
                            v3 = this.c.size();
                            if(v3 >= this.d && v3 > 0) {
                                this.d(0);
                                --v3;
                            }

                            if((RecyclerView.d) && v3 > 0 && !this.f.C.a(arg7.mPosition)) {
                                --v3;
                                while(v3 >= 0) {
                                    if(!this.f.C.a(this.c.get(v3).mPosition)) {
                                    }
                                    else {
                                        --v3;
                                        continue;
                                    }

                                    break;
                                }

                                ++v3;
                            }

                            this.c.add(v3, arg7);
                            v3 = 1;
                        }

                        if(v3 != 0) {
                            goto label_71;
                        }

                        this.a(arg7, true);
                        int v1_1 = 1;
                    }
                    else {
                        v3 = 0;
                    }

                label_71:
                    this.f.h.g(arg7);
                    if(v3 == 0 && !(((boolean)v1_1)) && (v0)) {
                        arg7.mOwnerRecyclerView = null;
                    }

                    return;
                }
                else {
                    StringBuilder v0_1 = new StringBuilder();
                    v0_1.append("Trying to recycle an ignored view holder. You should first call stopIgnoringView(view) before calling recycle.");
                    v0_1.append(this.f.a());
                    throw new IllegalArgumentException(v0_1.toString());
                }
            }

            StringBuilder v3_1 = new StringBuilder();
            v3_1.append("Scrapped or attached views may not be recycled. isScrap:");
            v3_1.append(arg7.isScrap());
            v3_1.append(" isAttached:");
            if(arg7.itemView.getParent() != null) {
                v1 = true;
            }

            v3_1.append(v1);
            v3_1.append(this.f.a());
            throw new IllegalArgumentException(v3_1.toString());
        }

        void b(int arg5, int arg6) {
            int v0 = this.c.size();
            int v1;
            for(v1 = 0; v1 < v0; ++v1) {
                Object v2 = this.c.get(v1);
                if(v2 != null && ((w)v2).mPosition >= arg5) {
                    ((w)v2).offsetPosition(arg6, true);
                }
            }
        }

        void b(View arg2) {
            w v2 = RecyclerView.e(arg2);
            v2.mScrapContainer = null;
            v2.mInChangeScrap = false;
            v2.clearReturnedFromScrapFlag();
            this.b(v2);
        }

        w b(int arg6, boolean arg7) {
            int v0 = this.a.size();
            int v1 = 0;
            int v2;
            for(v2 = 0; v2 < v0; ++v2) {
                Object v3 = this.a.get(v2);
                if(!((w)v3).wasReturnedFromScrap() && ((w)v3).getLayoutPosition() == arg6 && !((w)v3).isInvalid() && ((this.f.D.g) || !((w)v3).isRemoved())) {
                    ((w)v3).addFlags(32);
                    return ((w)v3);
                }
            }

            if(!arg7) {
                View v0_1 = this.f.g.c(arg6);
                if(v0_1 != null) {
                    w v6 = RecyclerView.e(v0_1);
                    this.f.g.e(v0_1);
                    int v7 = this.f.g.b(v0_1);
                    if(v7 != -1) {
                        this.f.g.e(v7);
                        this.c(v0_1);
                        v6.addFlags(8224);
                        return v6;
                    }
                    else {
                        StringBuilder v0_2 = new StringBuilder();
                        v0_2.append("layout index should not be -1 after unhiding a view:");
                        v0_2.append(v6);
                        v0_2.append(this.f.a());
                        throw new IllegalStateException(v0_2.toString());
                    }
                }
            }

            v0 = this.c.size();
            while(v1 < v0) {
                Object v2_1 = this.c.get(v1);
                if(!((w)v2_1).isInvalid() && ((w)v2_1).getLayoutPosition() == arg6) {
                    if(!arg7) {
                        this.c.remove(v1);
                    }

                    return ((w)v2_1);
                }

                ++v1;
            }

            return null;
        }

        public View c(int arg2) {
            return this.a(arg2, false);
        }

        public List c() {
            return this.g;
        }

        void c(w arg2) {
            ArrayList v0 = arg2.mInChangeScrap ? this.b : this.a;
            v0.remove(arg2);
            arg2.mScrapContainer = null;
            arg2.mInChangeScrap = false;
            arg2.clearReturnedFromScrapFlag();
        }

        void c(int arg4, int arg5) {
            arg5 += arg4;
            int v0;
            for(v0 = this.c.size() - 1; v0 >= 0; --v0) {
                Object v1 = this.c.get(v0);
                if(v1 == null) {
                }
                else {
                    int v2 = ((w)v1).mPosition;
                    if(v2 >= arg4 && v2 < arg5) {
                        ((w)v1).addFlags(2);
                        this.d(v0);
                    }
                }
            }
        }

        void c(View arg3) {
            ArrayList v0;
            w v3 = RecyclerView.e(arg3);
            if((v3.hasAnyOfTheFlags(12)) || !v3.isUpdated() || (this.f.b(v3))) {
                if((v3.isInvalid()) && !v3.isRemoved()) {
                    if(this.f.m.hasStableIds()) {
                    }
                    else {
                        StringBuilder v0_1 = new StringBuilder();
                        v0_1.append("Called scrap view with an invalid view. Invalid views cannot be reused from scrap, they should rebound from recycler pool.");
                        v0_1.append(this.f.a());
                        throw new IllegalArgumentException(v0_1.toString());
                    }
                }

                v3.setScrapContainer(this, false);
                v0 = this.a;
            }
            else {
                if(this.b == null) {
                    this.b = new ArrayList();
                }

                v3.setScrapContainer(this, true);
                v0 = this.b;
            }

            v0.add(v3);
        }

        void d(int arg3) {
            this.a(this.c.get(arg3), true);
            this.c.remove(arg3);
        }

        void d() {
            int v0;
            for(v0 = this.c.size() - 1; v0 >= 0; --v0) {
                this.d(v0);
            }

            this.c.clear();
            if(RecyclerView.d) {
                this.f.C.a();
            }
        }

        void d(w arg2) {
            if(this.f.o != null) {
                this.f.o.a(arg2);
            }

            if(this.f.m != null) {
                this.f.m.onViewRecycled(arg2);
            }

            if(this.f.D != null) {
                this.f.h.g(arg2);
            }
        }

        int e() {
            return this.a.size();
        }

        View e(int arg2) {
            return this.a.get(arg2).itemView;
        }

        private void e(w arg3) {
            if(this.f.n()) {
                View v0 = arg3.itemView;
                if(android.support.v4.view.t.e(v0) == 0) {
                    android.support.v4.view.t.b(v0, 1);
                }

                if(android.support.v4.view.t.b(v0)) {
                    return;
                }

                arg3.addFlags(16384);
                android.support.v4.view.t.a(v0, this.f.H.b());
            }
        }

        void f() {
            this.a.clear();
            if(this.b != null) {
                this.b.clear();
            }
        }

        private void f(w arg2) {
            if((arg2.itemView instanceof ViewGroup)) {
                this.a(arg2.itemView, false);
            }
        }

        w f(int arg10) {
            int v4;
            w v1 = null;
            if(this.b != null) {
                int v0 = this.b.size();
                if(v0 == 0) {
                }
                else {
                    int v2 = 0;
                    int v3;
                    for(v3 = 0; true; ++v3) {
                        v4 = 32;
                        if(v3 >= v0) {
                            break;
                        }

                        Object v5 = this.b.get(v3);
                        if(!((w)v5).wasReturnedFromScrap() && ((w)v5).getLayoutPosition() == arg10) {
                            ((w)v5).addFlags(v4);
                            return ((w)v5);
                        }
                    }

                    if(!this.f.m.hasStableIds()) {
                        return v1;
                    }

                    arg10 = this.f.f.b(arg10);
                    if(arg10 <= 0) {
                        return v1;
                    }

                    if(arg10 >= this.f.m.getItemCount()) {
                        return v1;
                    }

                    long v5_1 = this.f.m.getItemId(arg10);
                    while(v2 < v0) {
                        Object v10 = this.b.get(v2);
                        if(!((w)v10).wasReturnedFromScrap() && ((w)v10).getItemId() == v5_1) {
                            ((w)v10).addFlags(v4);
                            return ((w)v10);
                        }

                        ++v2;
                    }
                }
            }

            return v1;
        }

        o g() {
            if(this.e == null) {
                this.e = new o();
            }

            return this.e;
        }

        void h() {
            int v0 = this.c.size();
            int v1;
            for(v1 = 0; v1 < v0; ++v1) {
                Object v2 = this.c.get(v1);
                if(v2 != null) {
                    ((w)v2).addFlags(6);
                    ((w)v2).addChangePayload(null);
                }
            }

            if(this.f.m == null || !this.f.m.hasStableIds()) {
                this.d();
            }
        }

        void i() {
            int v0 = this.c.size();
            int v1 = 0;
            int v2;
            for(v2 = 0; v2 < v0; ++v2) {
                this.c.get(v2).clearOldPosition();
            }

            v0 = this.a.size();
            for(v2 = 0; v2 < v0; ++v2) {
                this.a.get(v2).clearOldPosition();
            }

            if(this.b != null) {
                v0 = this.b.size();
                while(v1 < v0) {
                    this.b.get(v1).clearOldPosition();
                    ++v1;
                }
            }
        }

        void j() {
            int v0 = this.c.size();
            int v1;
            for(v1 = 0; v1 < v0; ++v1) {
                ViewGroup$LayoutParams v2 = this.c.get(v1).itemView.getLayoutParams();
                if(v2 != null) {
                    ((j)v2).e = true;
                }
            }
        }
    }

    public interface q {
        void a(w arg1);
    }

    class r extends android.support.v7.widget.RecyclerView$c {
        r(RecyclerView arg1) {
            this.a = arg1;
            super();
        }

        public void a() {
            this.a.a(null);
            this.a.D.f = true;
            this.a.c(true);
            if(!this.a.f.d()) {
                this.a.requestLayout();
            }
        }

        public void a(int arg3, int arg4, int arg5) {
            this.a.a(null);
            if(this.a.f.a(arg3, arg4, arg5)) {
                this.b();
            }
        }

        public void a(int arg3, int arg4, Object arg5) {
            this.a.a(null);
            if(this.a.f.a(arg3, arg4, arg5)) {
                this.b();
            }
        }

        void b() {
            if(!RecyclerView.c || !this.a.r || !this.a.q) {
                this.a.w = true;
                this.a.requestLayout();
            }
            else {
                android.support.v4.view.t.a(this.a, this.a.j);
            }
        }

        public void b(int arg3, int arg4) {
            this.a.a(null);
            if(this.a.f.b(arg3, arg4)) {
                this.b();
            }
        }

        public void c(int arg3, int arg4) {
            this.a.a(null);
            if(this.a.f.c(arg3, arg4)) {
                this.b();
            }
        }
    }

    public abstract class s {
        public class android.support.v7.widget.RecyclerView$s$a {
            private int a;
            private int b;
            private int c;
            private int d;
            private Interpolator e;
            private boolean f;
            private int g;

            void a(RecyclerView arg6) {
                if(this.d >= 0) {
                    int v0 = this.d;
                    this.d = -1;
                    arg6.b(v0);
                    this.f = false;
                    return;
                }

                if(this.f) {
                    this.b();
                    if(this.e != null) {
                        arg6.A.a(this.a, this.b, this.c, this.e);
                    }
                    else if(this.c == -2147483648) {
                        arg6.A.b(this.a, this.b);
                    }
                    else {
                        arg6.A.a(this.a, this.b, this.c);
                    }

                    ++this.g;
                    if(this.g > 10) {
                        Log.e("RecyclerView", "Smooth Scroll action is being updated too frequently. Make sure you are not changing it unless necessary");
                    }

                    this.f = false;
                }
                else {
                    this.g = 0;
                }
            }

            boolean a() {
                boolean v0 = this.d >= 0 ? true : false;
                return v0;
            }

            private void b() {
                if(this.e != null) {
                    if(this.c >= 1) {
                    }
                    else {
                        throw new IllegalStateException("If you provide an interpolator, you must set a positive duration");
                    }
                }

                if(this.c >= 1) {
                    return;
                }

                throw new IllegalStateException("Scroll duration must be a positive number");
            }
        }

        public interface android.support.v7.widget.RecyclerView$s$b {
            PointF d(int arg1);
        }

        private int a;
        private RecyclerView b;
        private i c;
        private boolean d;
        private boolean e;
        private View f;
        private final android.support.v7.widget.RecyclerView$s$a g;

        public int a(View arg2) {
            return this.b.h(arg2);
        }

        public i a() {
            return this.c;
        }

        public void a(int arg1) {
            this.a = arg1;
        }

        void a(int arg6, int arg7) {
            RecyclerView v0 = this.b;
            if(!this.e || this.a == -1 || v0 == null) {
                this.b();
            }

            int[] v2 = null;
            if((this.d) && this.f == null && this.c != null) {
                PointF v1 = this.b(this.a);
                if(v1 != null) {
                    if(v1.x == 0f && v1.y == 0f) {
                        goto label_30;
                    }

                    v0.a(((int)Math.signum(v1.x)), ((int)Math.signum(v1.y)), v2);
                }
            }

        label_30:
            this.d = false;
            if(this.f != null) {
                if(this.a(this.f) == this.a) {
                    this.a(this.f, v0.D, this.g);
                    this.g.a(v0);
                    this.b();
                }
                else {
                    Log.e("RecyclerView", "Passed over target position while smooth scrolling.");
                    this.f = ((View)v2);
                }
            }

            if(this.e) {
                this.a(arg6, arg7, v0.D, this.g);
                boolean v6 = this.g.a();
                this.g.a(v0);
                if(v6) {
                    if(this.e) {
                        this.d = true;
                        v0.A.a();
                    }
                    else {
                        this.b();
                    }
                }
            }
        }

        protected abstract void a(View arg1, t arg2, android.support.v7.widget.RecyclerView$s$a arg3);

        protected abstract void a(int arg1, int arg2, t arg3, android.support.v7.widget.RecyclerView$s$a arg4);

        protected void b(View arg3) {
            if(this.a(arg3) == this.e()) {
                this.f = arg3;
            }
        }

        protected final void b() {
            if(!this.e) {
                return;
            }

            this.e = false;
            this.f();
            this.b.D.a = -1;
            this.f = null;
            this.a = -1;
            this.d = false;
            this.c.a(this);
            this.c = null;
            this.b = null;
        }

        public PointF b(int arg3) {
            i v0 = this.a();
            if((v0 instanceof android.support.v7.widget.RecyclerView$s$b)) {
                return ((android.support.v7.widget.RecyclerView$s$b)v0).d(arg3);
            }

            Log.w("RecyclerView", "You should override computeScrollVectorForPosition when the LayoutManager does not implement " + android.support.v7.widget.RecyclerView$s$b.class.getCanonicalName());
            return null;
        }

        public boolean c() {
            return this.d;
        }

        public boolean d() {
            return this.e;
        }

        public int e() {
            return this.a;
        }

        protected abstract void f();
    }

    public class t {
        int a;
        int b;
        int c;
        int d;
        int e;
        boolean f;
        boolean g;
        boolean h;
        boolean i;
        boolean j;
        boolean k;
        int l;
        long m;
        int n;
        int o;
        int p;
        private SparseArray q;

        public t() {
            super();
            this.a = -1;
            this.b = 0;
            this.c = 0;
            this.d = 1;
            this.e = 0;
            this.f = false;
            this.g = false;
            this.h = false;
            this.i = false;
            this.j = false;
            this.k = false;
        }

        void a(a arg2) {
            this.d = 1;
            this.e = arg2.getItemCount();
            this.g = false;
            this.h = false;
            this.i = false;
        }

        public boolean a() {
            return this.g;
        }

        void a(int arg4) {
            if((this.d & arg4) != 0) {
                return;
            }

            StringBuilder v1 = new StringBuilder();
            v1.append("Layout state should be one of ");
            v1.append(Integer.toBinaryString(arg4));
            v1.append(" but it is ");
            v1.append(Integer.toBinaryString(this.d));
            throw new IllegalStateException(v1.toString());
        }

        public boolean b() {
            return this.k;
        }

        public int c() {
            return this.a;
        }

        public boolean d() {
            boolean v0 = this.a != -1 ? true : false;
            return v0;
        }

        public int e() {
            int v0 = this.g ? this.b - this.c : this.e;
            return v0;
        }

        public String toString() {
            return "State{mTargetPosition=" + this.a + ", mData=" + this.q + ", mItemCount=" + this.e + ", mIsMeasuring=" + this.i + ", mPreviousLayoutItemCount=" + this.b + ", mDeletedInvisibleItemCountSincePreviousLayout=" + this.c + ", mStructureChanged=" + this.f + ", mInPreLayout=" + this.g + ", mRunSimpleAnimations=" + this.j + ", mRunPredictiveAnimations=" + this.k + '}';
        }
    }

    public abstract class u {
        public abstract View a(p arg1, int arg2, int arg3);
    }

    class v implements Runnable {
        OverScroller a;
        Interpolator b;
        private int d;
        private int e;
        private boolean f;
        private boolean g;

        v(RecyclerView arg3) {
            this.c = arg3;
            super();
            this.b = RecyclerView.L;
            this.f = false;
            this.g = false;
            this.a = new OverScroller(arg3.getContext(), RecyclerView.L);
        }

        public void a(int arg2, int arg3, Interpolator arg4) {
            int v0 = this.b(arg2, arg3, 0, 0);
            if(arg4 == null) {
                arg4 = RecyclerView.L;
            }

            this.a(arg2, arg3, v0, arg4);
        }

        public void a(int arg11, int arg12) {
            this.c.setScrollState(2);
            this.e = 0;
            this.d = 0;
            this.a.fling(0, 0, arg11, arg12, -2147483648, 2147483647, -2147483648, 2147483647);
            this.a();
        }

        void a() {
            if(this.f) {
                this.g = true;
            }
            else {
                this.c.removeCallbacks(((Runnable)this));
                android.support.v4.view.t.a(this.c, ((Runnable)this));
            }
        }

        public void a(int arg2, int arg3, int arg4) {
            this.a(arg2, arg3, arg4, RecyclerView.L);
        }

        public void a(int arg7, int arg8, int arg9, Interpolator arg10) {
            if(this.b != arg10) {
                this.b = arg10;
                this.a = new OverScroller(this.c.getContext(), arg10);
            }

            this.c.setScrollState(2);
            this.e = 0;
            this.d = 0;
            this.a.startScroll(0, 0, arg7, arg8, arg9);
            if(Build$VERSION.SDK_INT < 23) {
                this.a.computeScrollOffset();
            }

            this.a();
        }

        private float a(float arg3) {
            return ((float)Math.sin(((double)((arg3 - 0.5f) * 0.471239f))));
        }

        public void a(int arg1, int arg2, int arg3, int arg4) {
            this.a(arg1, arg2, this.b(arg1, arg2, arg3, arg4));
        }

        public void b() {
            this.c.removeCallbacks(((Runnable)this));
            this.a.abortAnimation();
        }

        public void b(int arg2, int arg3) {
            this.a(arg2, arg3, 0, 0);
        }

        private int b(int arg5, int arg6, int arg7, int arg8) {
            int v0 = Math.abs(arg5);
            int v1 = Math.abs(arg6);
            int v2 = v0 > v1 ? 1 : 0;
            arg7 = ((int)Math.sqrt(((double)(arg7 * arg7 + arg8 * arg8))));
            arg5 = ((int)Math.sqrt(((double)(arg5 * arg5 + arg6 * arg6))));
            arg6 = v2 != 0 ? this.c.getWidth() : this.c.getHeight();
            arg8 = arg6 / 2;
            float v3 = 1f;
            float v6 = ((float)arg6);
            float v8 = ((float)arg8);
            v8 += this.a(Math.min(v3, (((float)arg5)) * v3 / v6)) * v8;
            if(arg7 > 0) {
                arg5 = Math.round(Math.abs(v8 / (((float)arg7))) * 1000f) * 4;
            }
            else {
                if(v2 != 0) {
                }
                else {
                    v0 = v1;
                }

                arg5 = ((int)(((((float)v0)) / v6 + v3) * 300f));
            }

            return Math.min(arg5, 2000);
        }

        private void c() {
            this.g = false;
            this.f = true;
        }

        private void d() {
            this.f = false;
            if(this.g) {
                this.a();
            }
        }

        public void run() {
            int v15;
            int v9;
            int v8;
            int v7;
            int v5;
            int v3_1;
            v v0 = this;
            if(v0.c.n == null) {
                this.b();
                return;
            }

            this.c();
            v0.c.d();
            OverScroller v1 = v0.a;
            s v2 = v0.c.n.t;
            if(v1.computeScrollOffset()) {
                int[] v3 = v0.c.I;
                int v11 = v1.getCurrX();
                int v12 = v1.getCurrY();
                int v13 = v11 - v0.d;
                int v14 = v12 - v0.e;
                v0.d = v11;
                v0.e = v12;
                if(v0.c.a(v13, v14, v3, null, 1)) {
                    v13 -= v3[0];
                    v14 -= v3[1];
                }

                if(v0.c.m != null) {
                    v0.c.a(v13, v14, v0.c.J);
                    v3_1 = v0.c.J[0];
                    v5 = v0.c.J[1];
                    v7 = v13 - v3_1;
                    v8 = v14 - v5;
                    if(v2 != null && !v2.c() && (v2.d())) {
                        v9 = v0.c.D.e();
                        if(v9 == 0) {
                            v2.b();
                        }
                        else {
                            if(v2.e() >= v9) {
                                v2.a(v9 - 1);
                            }

                            v2.a(v13 - v7, v14 - v8);
                        }
                    }
                }
                else {
                    v3_1 = 0;
                    v5 = 0;
                    v7 = 0;
                    v8 = 0;
                }

                if(!v0.c.p.isEmpty()) {
                    v0.c.invalidate();
                }

                int v10 = 2;
                if(v0.c.getOverScrollMode() != v10) {
                    v0.c.c(v13, v14);
                }

                if(!v0.c.a(v3_1, v5, v7, v8, null, 1) && (v7 != 0 || v8 != 0)) {
                    v9 = ((int)v1.getCurrVelocity());
                    if(v7 == v11) {
                    label_109:
                        v15 = 0;
                    }
                    else if(v7 < 0) {
                        v15 = -v9;
                    }
                    else if(v7 > 0) {
                        v15 = v9;
                    }
                    else {
                        goto label_109;
                    }

                    if(v8 == v12) {
                    label_116:
                        v9 = 0;
                    }
                    else if(v8 < 0) {
                        v9 = -v9;
                    }
                    else if(v8 > 0) {
                    }
                    else {
                        goto label_116;
                    }

                    if(v0.c.getOverScrollMode() != v10) {
                        v0.c.d(v15, v9);
                    }

                    if(v15 == 0 && v7 != v11 && v1.getFinalX() != 0) {
                        goto label_131;
                    }

                    if(v9 == 0 && v8 != v12 && v1.getFinalY() != 0) {
                        goto label_131;
                    }

                    v1.abortAnimation();
                }

            label_131:
                if(v3_1 != 0 || v5 != 0) {
                    v0.c.i(v3_1, v5);
                }

                if(!RecyclerView.a(v0.c)) {
                    v0.c.invalidate();
                }

                int v4 = v14 == 0 || !v0.c.n.f() || v5 != v14 ? 0 : 1;
                v3_1 = v13 == 0 || !v0.c.n.e() || v3_1 != v13 ? 0 : 1;
                v3_1 = v13 == 0 && v14 == 0 || (v3_1 != 0 || v4 != 0) ? 1 : 0;
                if(!v1.isFinished() && (v3_1 != 0 || (v0.c.h(1)))) {
                    this.a();
                    if(v0.c.B == null) {
                        goto label_192;
                    }

                    v0.c.B.a(v0.c, v13, v14);
                    goto label_192;
                }

                v0.c.setScrollState(0);
                if(RecyclerView.d) {
                    v0.c.C.a();
                }

                v0.c.stopNestedScroll(1);
            }

        label_192:
            if(v2 != null) {
                if(v2.c()) {
                    v2.a(0, 0);
                }

                if(v0.g) {
                    goto label_200;
                }

                v2.b();
            }

        label_200:
            this.d();
        }
    }

    public abstract class w {
        static final int FLAG_ADAPTER_FULLUPDATE = 1024;
        static final int FLAG_ADAPTER_POSITION_UNKNOWN = 512;
        static final int FLAG_APPEARED_IN_PRE_LAYOUT = 4096;
        static final int FLAG_BOUNCED_FROM_HIDDEN_LIST = 8192;
        static final int FLAG_BOUND = 1;
        static final int FLAG_IGNORE = 128;
        static final int FLAG_INVALID = 4;
        static final int FLAG_MOVED = 2048;
        static final int FLAG_NOT_RECYCLABLE = 16;
        static final int FLAG_REMOVED = 8;
        static final int FLAG_RETURNED_FROM_SCRAP = 32;
        static final int FLAG_SET_A11Y_ITEM_DELEGATE = 16384;
        static final int FLAG_TMP_DETACHED = 256;
        static final int FLAG_UPDATE = 2;
        private static final List FULLUPDATE_PAYLOADS = null;
        static final int PENDING_ACCESSIBILITY_STATE_NOT_SET = -1;
        public final View itemView;
        int mFlags;
        boolean mInChangeScrap;
        private int mIsRecyclableCount;
        long mItemId;
        int mItemViewType;
        WeakReference mNestedRecyclerView;
        int mOldPosition;
        RecyclerView mOwnerRecyclerView;
        List mPayloads;
        int mPendingAccessibilityState;
        int mPosition;
        int mPreLayoutPosition;
        p mScrapContainer;
        w mShadowedHolder;
        w mShadowingHolder;
        List mUnmodifiedPayloads;
        private int mWasImportantForAccessibilityBeforeHidden;

        static {
            w.FULLUPDATE_PAYLOADS = Collections.emptyList();
        }

        public w(View arg4) {
            super();
            this.mPosition = -1;
            this.mOldPosition = -1;
            this.mItemId = -1;
            this.mItemViewType = -1;
            this.mPreLayoutPosition = -1;
            this.mShadowedHolder = null;
            this.mShadowingHolder = null;
            this.mPayloads = null;
            this.mUnmodifiedPayloads = null;
            this.mIsRecyclableCount = 0;
            this.mScrapContainer = null;
            this.mInChangeScrap = false;
            this.mWasImportantForAccessibilityBeforeHidden = 0;
            this.mPendingAccessibilityState = -1;
            if(arg4 != null) {
                this.itemView = arg4;
                return;
            }

            throw new IllegalArgumentException("itemView may not be null");
        }

        void addChangePayload(Object arg3) {
            int v0 = 1024;
            if(arg3 == null) {
                this.addFlags(v0);
            }
            else if((v0 & this.mFlags) == 0) {
                this.createPayloadsIfNeeded();
                this.mPayloads.add(arg3);
            }
        }

        void addFlags(int arg2) {
            this.mFlags |= arg2;
        }

        void clearOldPosition() {
            this.mOldPosition = -1;
            this.mPreLayoutPosition = -1;
        }

        void clearPayload() {
            if(this.mPayloads != null) {
                this.mPayloads.clear();
            }

            this.mFlags &= -1025;
        }

        void clearReturnedFromScrapFlag() {
            this.mFlags &= -33;
        }

        void clearTmpDetachFlag() {
            this.mFlags &= -257;
        }

        private void createPayloadsIfNeeded() {
            if(this.mPayloads == null) {
                this.mPayloads = new ArrayList();
                this.mUnmodifiedPayloads = Collections.unmodifiableList(this.mPayloads);
            }
        }

        boolean doesTransientStatePreventRecycling() {
            boolean v0 = (this.mFlags & 16) != 0 || !android.support.v4.view.t.c(this.itemView) ? false : true;
            return v0;
        }

        void flagRemovedAndOffsetPosition(int arg2, int arg3, boolean arg4) {
            this.addFlags(8);
            this.offsetPosition(arg3, arg4);
            this.mPosition = arg2;
        }

        public final int getAdapterPosition() {
            if(this.mOwnerRecyclerView == null) {
                return -1;
            }

            return this.mOwnerRecyclerView.d(this);
        }

        public final long getItemId() {
            return this.mItemId;
        }

        public final int getItemViewType() {
            return this.mItemViewType;
        }

        public final int getLayoutPosition() {
            int v0 = this.mPreLayoutPosition == -1 ? this.mPosition : this.mPreLayoutPosition;
            return v0;
        }

        public final int getOldPosition() {
            return this.mOldPosition;
        }

        @Deprecated public final int getPosition() {
            int v0 = this.mPreLayoutPosition == -1 ? this.mPosition : this.mPreLayoutPosition;
            return v0;
        }

        List getUnmodifiedPayloads() {
            if((this.mFlags & 1024) == 0) {
                if(this.mPayloads != null) {
                    if(this.mPayloads.size() == 0) {
                    }
                    else {
                        return this.mUnmodifiedPayloads;
                    }
                }

                return w.FULLUPDATE_PAYLOADS;
            }

            return w.FULLUPDATE_PAYLOADS;
        }

        boolean hasAnyOfTheFlags(int arg2) {
            boolean v2 = (arg2 & this.mFlags) != 0 ? true : false;
            return v2;
        }

        boolean isAdapterPositionUnknown() {
            boolean v0 = (this.mFlags & 512) != 0 || (this.isInvalid()) ? true : false;
            return v0;
        }

        boolean isBound() {
            boolean v1 = true;
            if((this.mFlags & 1) != 0) {
            }
            else {
                v1 = false;
            }

            return v1;
        }

        boolean isInvalid() {
            boolean v0 = (this.mFlags & 4) != 0 ? true : false;
            return v0;
        }

        public final boolean isRecyclable() {
            boolean v0 = (this.mFlags & 16) != 0 || (android.support.v4.view.t.c(this.itemView)) ? false : true;
            return v0;
        }

        boolean isRemoved() {
            boolean v0 = (this.mFlags & 8) != 0 ? true : false;
            return v0;
        }

        boolean isScrap() {
            boolean v0 = this.mScrapContainer != null ? true : false;
            return v0;
        }

        boolean isTmpDetached() {
            boolean v0 = (this.mFlags & 256) != 0 ? true : false;
            return v0;
        }

        boolean isUpdated() {
            boolean v0 = (this.mFlags & 2) != 0 ? true : false;
            return v0;
        }

        boolean needsUpdate() {
            boolean v0 = (this.mFlags & 2) != 0 ? true : false;
            return v0;
        }

        void offsetPosition(int arg3, boolean arg4) {
            int v1 = -1;
            if(this.mOldPosition == v1) {
                this.mOldPosition = this.mPosition;
            }

            if(this.mPreLayoutPosition == v1) {
                this.mPreLayoutPosition = this.mPosition;
            }

            if(arg4) {
                this.mPreLayoutPosition += arg3;
            }

            this.mPosition += arg3;
            if(this.itemView.getLayoutParams() != null) {
                this.itemView.getLayoutParams().e = true;
            }
        }

        void onEnteredHiddenState(RecyclerView arg3) {
            int v0 = this.mPendingAccessibilityState != -1 ? this.mPendingAccessibilityState : android.support.v4.view.t.e(this.itemView);
            this.mWasImportantForAccessibilityBeforeHidden = v0;
            arg3.a(this, 4);
        }

        void onLeftHiddenState(RecyclerView arg2) {
            arg2.a(this, this.mWasImportantForAccessibilityBeforeHidden);
            this.mWasImportantForAccessibilityBeforeHidden = 0;
        }

        void resetInternal() {
            this.mFlags = 0;
            this.mPosition = -1;
            this.mOldPosition = -1;
            this.mItemId = -1;
            this.mPreLayoutPosition = -1;
            this.mIsRecyclableCount = 0;
            this.mShadowedHolder = null;
            this.mShadowingHolder = null;
            this.clearPayload();
            this.mWasImportantForAccessibilityBeforeHidden = 0;
            this.mPendingAccessibilityState = -1;
            RecyclerView.c(this);
        }

        void saveOldPosition() {
            if(this.mOldPosition == -1) {
                this.mOldPosition = this.mPosition;
            }
        }

        void setFlags(int arg3, int arg4) {
            this.mFlags = arg3 & arg4 | this.mFlags & (arg4 ^ -1);
        }

        public final void setIsRecyclable(boolean arg3) {
            int v3;
            int v1 = arg3 ? this.mIsRecyclableCount - 1 : this.mIsRecyclableCount + 1;
            this.mIsRecyclableCount = v1;
            if(this.mIsRecyclableCount < 0) {
                this.mIsRecyclableCount = 0;
                Log.e("View", "isRecyclable decremented below 0: unmatched pair of setIsRecyable() calls for " + this);
            }
            else {
                if(!arg3 && this.mIsRecyclableCount == 1) {
                    v3 = this.mFlags | 16;
                }
                else if(!arg3) {
                    return;
                }
                else if(this.mIsRecyclableCount == 0) {
                    v3 = this.mFlags & -17;
                }
                else {
                    return;
                }

                this.mFlags = v3;
            }
        }

        void setScrapContainer(p arg1, boolean arg2) {
            this.mScrapContainer = arg1;
            this.mInChangeScrap = arg2;
        }

        boolean shouldBeKeptAsChild() {
            boolean v0 = (this.mFlags & 16) != 0 ? true : false;
            return v0;
        }

        boolean shouldIgnore() {
            boolean v0 = (this.mFlags & 128) != 0 ? true : false;
            return v0;
        }

        void stopIgnoring() {
            this.mFlags &= -129;
        }

        public String toString() {
            StringBuilder v1 = new StringBuilder();
            v1.append("ViewHolder{");
            v1.append(Integer.toHexString(this.hashCode()));
            v1.append(" position=");
            v1.append(this.mPosition);
            v1.append(" id=");
            v1.append(this.mItemId);
            v1.append(", oldPos=");
            v1.append(this.mOldPosition);
            v1.append(", pLpos:");
            v1.append(this.mPreLayoutPosition);
            StringBuilder v0 = new StringBuilder(v1.toString());
            if(this.isScrap()) {
                v0.append(" scrap ");
                String v1_1 = this.mInChangeScrap ? "[changeScrap]" : "[attachedScrap]";
                v0.append(v1_1);
            }

            if(this.isInvalid()) {
                v0.append(" invalid");
            }

            if(!this.isBound()) {
                v0.append(" unbound");
            }

            if(this.needsUpdate()) {
                v0.append(" update");
            }

            if(this.isRemoved()) {
                v0.append(" removed");
            }

            if(this.shouldIgnore()) {
                v0.append(" ignored");
            }

            if(this.isTmpDetached()) {
                v0.append(" tmpDetached");
            }

            if(!this.isRecyclable()) {
                v0.append(" not recyclable(" + this.mIsRecyclableCount + ")");
            }

            if(this.isAdapterPositionUnknown()) {
                v0.append(" undefined adapter position");
            }

            if(this.itemView.getParent() == null) {
                v0.append(" no parent");
            }

            v0.append("}");
            return v0.toString();
        }

        void unScrap() {
            this.mScrapContainer.c(this);
        }

        boolean wasReturnedFromScrap() {
            boolean v0 = (this.mFlags & 32) != 0 ? true : false;
            return v0;
        }
    }

    final v A;
    an B;
    android.support.v7.widget.an$a C;
    final t D;
    boolean E;
    boolean F;
    boolean G;
    aw H;
    final int[] I;
    final int[] J;
    final List K;
    static final Interpolator L;
    private static final int[] M;
    private static final int[] N;
    private static final boolean O;
    private static final boolean P;
    private static final Class[] Q;
    private final r R;
    private SavedState S;
    private final Rect T;
    private final ArrayList U;
    private m V;
    private int W;
    static final boolean a;
    private List aA;
    private android.support.v7.widget.RecyclerView$f$b aB;
    private android.support.v7.widget.RecyclerView$d aC;
    private final int[] aD;
    private android.support.v4.view.l aE;
    private final int[] aF;
    private final int[] aG;
    private Runnable aH;
    private final b aI;
    private boolean aa;
    private int ab;
    private final AccessibilityManager ac;
    private List ad;
    private int ae;
    private int af;
    private e ag;
    private EdgeEffect ah;
    private EdgeEffect ai;
    private EdgeEffect aj;
    private EdgeEffect ak;
    private int al;
    private int am;
    private VelocityTracker an;
    private int ao;
    private int ap;
    private int aq;
    private int ar;
    private int as;
    private l at;
    private final int au;
    private final int av;
    private float aw;
    private float ax;
    private boolean ay;
    private n az;
    static final boolean b;
    static final boolean c;
    static final boolean d;
    final p e;
    android.support.v7.widget.e f;
    ae g;
    final br h;
    boolean i;
    final Runnable j;
    final Rect k;
    final RectF l;
    a m;
    i n;
    q o;
    final ArrayList p;
    boolean q;
    boolean r;
    boolean s;
    boolean t;
    boolean u;
    boolean v;
    boolean w;
    boolean x;
    boolean y;
    f z;

    static {
        RecyclerView.M = new int[]{16843830};
        RecyclerView.N = new int[]{16842987};
        boolean v1 = Build$VERSION.SDK_INT == 18 || Build$VERSION.SDK_INT == 19 || Build$VERSION.SDK_INT == 20 ? true : false;
        RecyclerView.a = v1;
        v1 = Build$VERSION.SDK_INT >= 23 ? true : false;
        RecyclerView.b = v1;
        v1 = Build$VERSION.SDK_INT >= 16 ? true : false;
        RecyclerView.c = v1;
        v1 = Build$VERSION.SDK_INT >= 21 ? true : false;
        RecyclerView.d = v1;
        int v3 = 15;
        v1 = Build$VERSION.SDK_INT <= v3 ? true : false;
        RecyclerView.O = v1;
        v1 = Build$VERSION.SDK_INT <= v3 ? true : false;
        RecyclerView.P = v1;
        RecyclerView.Q = new Class[]{Context.class, AttributeSet.class, Integer.TYPE, Integer.TYPE};
        RecyclerView.L = new android.support.v7.widget.RecyclerView$3();
    }

    public RecyclerView(Context arg2) {
        this(arg2, null);
    }

    public RecyclerView(Context arg2, AttributeSet arg3) {
        this(arg2, arg3, 0);
    }

    public RecyclerView(Context arg13, AttributeSet arg14, int arg15) {
        TypedArray v4;
        super(arg13, arg14, arg15);
        this.R = new r(this);
        this.e = new p(this);
        this.h = new br();
        this.j = new android.support.v7.widget.RecyclerView$1(this);
        this.k = new Rect();
        this.T = new Rect();
        this.l = new RectF();
        this.p = new ArrayList();
        this.U = new ArrayList();
        this.W = 0;
        this.x = false;
        this.y = false;
        this.ae = 0;
        this.af = 0;
        this.ag = new e();
        this.z = new ah();
        this.al = 0;
        int v1 = -1;
        this.am = v1;
        this.aw = 0f;
        this.ax = 0f;
        boolean v2 = true;
        this.ay = true;
        this.A = new v(this);
        android.support.v7.widget.an$a v3 = RecyclerView.d ? new android.support.v7.widget.an$a() : null;
        this.C = v3;
        this.D = new t();
        this.E = false;
        this.F = false;
        this.aB = new g(this);
        this.G = false;
        int v3_1 = 2;
        this.aD = new int[v3_1];
        this.aF = new int[v3_1];
        this.I = new int[v3_1];
        this.aG = new int[v3_1];
        this.J = new int[v3_1];
        this.K = new ArrayList();
        this.aH = new android.support.v7.widget.RecyclerView$2(this);
        this.aI = new android.support.v7.widget.RecyclerView$4(this);
        if(arg14 != null) {
            v4 = arg13.obtainStyledAttributes(arg14, RecyclerView.N, arg15, 0);
            this.i = v4.getBoolean(0, true);
            v4.recycle();
        }
        else {
            this.i = true;
        }

        this.setScrollContainer(true);
        this.setFocusableInTouchMode(true);
        ViewConfiguration v4_1 = ViewConfiguration.get(arg13);
        this.as = v4_1.getScaledTouchSlop();
        this.aw = android.support.v4.view.u.a(v4_1, arg13);
        this.ax = android.support.v4.view.u.b(v4_1, arg13);
        this.au = v4_1.getScaledMinimumFlingVelocity();
        this.av = v4_1.getScaledMaximumFlingVelocity();
        boolean v3_2 = this.getOverScrollMode() == v3_1 ? true : false;
        this.setWillNotDraw(v3_2);
        this.z.a(this.aB);
        this.b();
        this.z();
        this.y();
        if(android.support.v4.view.t.e(((View)this)) == 0) {
            android.support.v4.view.t.b(((View)this), 1);
        }

        this.ac = this.getContext().getSystemService("accessibility");
        this.setAccessibilityDelegateCompat(new aw(this));
        v3_1 = 262144;
        if(arg14 != null) {
            v4 = arg13.obtainStyledAttributes(arg14, android.support.v7.e.a$c.RecyclerView, arg15, 0);
            String v8 = v4.getString(android.support.v7.e.a$c.RecyclerView_layoutManager);
            if(v4.getInt(android.support.v7.e.a$c.RecyclerView_android_descendantFocusability, v1) == v1) {
                this.setDescendantFocusability(v3_1);
            }

            this.s = v4.getBoolean(android.support.v7.e.a$c.RecyclerView_fastScrollEnabled, false);
            if(this.s) {
                this.a(v4.getDrawable(android.support.v7.e.a$c.RecyclerView_fastScrollVerticalThumbDrawable), v4.getDrawable(android.support.v7.e.a$c.RecyclerView_fastScrollVerticalTrackDrawable), v4.getDrawable(android.support.v7.e.a$c.RecyclerView_fastScrollHorizontalThumbDrawable), v4.getDrawable(android.support.v7.e.a$c.RecyclerView_fastScrollHorizontalTrackDrawable));
            }

            v4.recycle();
            this.a(arg13, v8, arg14, arg15, 0);
            if(Build$VERSION.SDK_INT < 21) {
                goto label_171;
            }

            TypedArray v13 = arg13.obtainStyledAttributes(arg14, RecyclerView.M, arg15, 0);
            boolean v14 = v13.getBoolean(0, true);
            v13.recycle();
            v2 = v14;
        }
        else {
            this.setDescendantFocusability(v3_1);
        }

    label_171:
        this.setNestedScrollingEnabled(v2);
    }

    private boolean A() {
        int v0 = this.g.b();
        int v2;
        for(v2 = 0; v2 < v0; ++v2) {
            w v3 = RecyclerView.e(this.g.b(v2));
            if(v3 != null) {
                if(v3.shouldIgnore()) {
                }
                else if(v3.isUpdated()) {
                    return 1;
                }
            }
        }

        return 0;
    }

    private void B() {
        this.A.b();
        if(this.n != null) {
            this.n.K();
        }
    }

    private void C() {
        int v0_1;
        boolean v0;
        if(this.ah != null) {
            this.ah.onRelease();
            v0 = this.ah.isFinished();
        }
        else {
            v0_1 = 0;
        }

        if(this.ai != null) {
            this.ai.onRelease();
            v0_1 = (((int)v0)) | this.ai.isFinished();
        }

        if(this.aj != null) {
            this.aj.onRelease();
            v0_1 = (((int)v0)) | this.aj.isFinished();
        }

        if(this.ak != null) {
            this.ak.onRelease();
            v0_1 = (((int)v0)) | this.ak.isFinished();
        }

        if(v0) {
            android.support.v4.view.t.d(((View)this));
        }
    }

    private void D() {
        if(this.an != null) {
            this.an.clear();
        }

        this.stopNestedScroll(0);
        this.C();
    }

    private void E() {
        this.D();
        this.setScrollState(0);
    }

    private void F() {
        int v0 = this.ab;
        this.ab = 0;
        if(v0 != 0 && (this.n())) {
            AccessibilityEvent v1 = AccessibilityEvent.obtain();
            v1.setEventType(2048);
            android.support.v4.view.a.a.a(v1, v0);
            this.sendAccessibilityEventUnchecked(v1);
        }
    }

    private boolean G() {
        boolean v0 = this.z == null || !this.n.b() ? false : true;
        return v0;
    }

    private void H() {
        boolean v4;
        if(this.x) {
            this.f.a();
            if(this.y) {
                this.n.a(this);
            }
        }

        if(this.G()) {
            this.f.b();
        }
        else {
            this.f.e();
        }

        boolean v1 = false;
        int v0 = (this.E) || (this.F) ? 1 : 0;
        t v3 = this.D;
        if(!this.t || this.z == null) {
        label_43:
            v4 = false;
        }
        else {
            if(!this.x && v0 == 0 && !this.n.u) {
                goto label_43;
            }

            if((this.x) && !this.m.hasStableIds()) {
                goto label_43;
            }

            v4 = true;
        }

        v3.j = v4;
        v3 = this.D;
        if((this.D.j) && v0 != 0 && !this.x && (this.G())) {
            v1 = true;
        }

        v3.k = v1;
    }

    private void I() {
        int v2_1;
        w v1 = null;
        View v0 = !this.ay || !this.hasFocus() || this.m == null ? ((View)v1) : this.getFocusedChild();
        if(v0 == null) {
        }
        else {
            v1 = this.d(v0);
        }

        if(v1 == null) {
            this.J();
        }
        else {
            t v0_1 = this.D;
            long v2 = this.m.hasStableIds() ? v1.getItemId() : -1;
            v0_1.m = v2;
            v0_1 = this.D;
            if(this.x) {
                v2_1 = -1;
            }
            else if(v1.isRemoved()) {
                v2_1 = v1.mOldPosition;
            }
            else {
                v2_1 = v1.getAdapterPosition();
            }

            v0_1.l = v2_1;
            this.D.n = this.o(v1.itemView);
        }
    }

    private void J() {
        this.D.m = -1;
        this.D.l = -1;
        this.D.n = -1;
    }

    private View K() {
        View v1_1;
        int v0 = this.D.l != -1 ? this.D.l : 0;
        int v1 = this.D.e();
        int v2 = v0;
        while(v2 < v1) {
            w v3 = this.c(v2);
            if(v3 == null) {
            }
            else if(v3.itemView.hasFocusable()) {
                return v3.itemView;
            }
            else {
                ++v2;
                continue;
            }

            break;
        }

        for(v0 = Math.min(v1, v0) - 1; true; --v0) {
            v1_1 = null;
            if(v0 < 0) {
                return v1_1;
            }

            w v2_1 = this.c(v0);
            if(v2_1 == null) {
                return v1_1;
            }

            if(v2_1.itemView.hasFocusable()) {
                return v2_1.itemView;
            }
        }

        return v1_1;
    }

    private void L() {
        // Method was not decompiled
    }

    private void M() {
        int v0_1;
        boolean v1 = true;
        this.D.a(1);
        this.a(this.D);
        this.D.i = false;
        this.e();
        this.h.a();
        this.l();
        this.H();
        this.I();
        t v0 = this.D;
        if(!this.D.j || !this.F) {
            v1 = false;
        }
        else {
        }

        v0.h = v1;
        this.F = false;
        this.E = false;
        this.D.g = this.D.k;
        this.D.e = this.m.getItemCount();
        this.a(this.aD);
        if(this.D.j) {
            v0_1 = this.g.b();
            int v1_1;
            for(v1_1 = 0; v1_1 < v0_1; ++v1_1) {
                w v3 = RecyclerView.e(this.g.b(v1_1));
                if(!v3.shouldIgnore() && (!v3.isInvalid() || (this.m.hasStableIds()))) {
                    this.h.a(v3, this.z.a(this.D, v3, f.e(v3), v3.getUnmodifiedPayloads()));
                    if(!this.D.h) {
                        goto label_74;
                    }

                    if(!v3.isUpdated()) {
                        goto label_74;
                    }

                    if(v3.isRemoved()) {
                        goto label_74;
                    }

                    if(v3.shouldIgnore()) {
                        goto label_74;
                    }

                    if(v3.isInvalid()) {
                        goto label_74;
                    }

                    this.h.a(this.a(v3), v3);
                }

            label_74:
            }
        }

        if(this.D.k) {
            this.s();
            boolean v0_2 = this.D.f;
            this.D.f = false;
            this.n.c(this.e, this.D);
            this.D.f = v0_2;
            for(v0_1 = 0; v0_1 < this.g.b(); ++v0_1) {
                w v1_2 = RecyclerView.e(this.g.b(v0_1));
                if(v1_2.shouldIgnore()) {
                }
                else if(!this.h.d(v1_2)) {
                    int v3_1 = f.e(v1_2);
                    boolean v4 = v1_2.hasAnyOfTheFlags(8192);
                    if(!v4) {
                        v3_1 |= 4096;
                    }

                    c v3_2 = this.z.a(this.D, v1_2, v3_1, v1_2.getUnmodifiedPayloads());
                    if(v4) {
                        this.a(v1_2, v3_2);
                        goto label_117;
                    }

                    this.h.b(v1_2, v3_2);
                }

            label_117:
            }
        }

        this.t();
        this.m();
        this.a(false);
        this.D.d = 2;
    }

    private void N() {
        this.e();
        this.l();
        this.D.a(6);
        this.f.e();
        this.D.e = this.m.getItemCount();
        this.D.c = 0;
        this.D.g = false;
        this.n.c(this.e, this.D);
        this.D.f = false;
        this.S = null;
        t v0 = this.D;
        boolean v2 = !this.D.j || this.z == null ? false : true;
        v0.j = v2;
        this.D.d = 4;
        this.m();
        this.a(false);
    }

    private void O() {
        this.D.a(4);
        this.e();
        this.l();
        this.D.d = 1;
        if(this.D.j) {
            int v0;
            for(v0 = this.g.b() - 1; v0 >= 0; --v0) {
                w v5 = RecyclerView.e(this.g.b(v0));
                if(v5.shouldIgnore()) {
                }
                else {
                    long v2 = this.a(v5);
                    c v4 = this.z.a(this.D, v5);
                    w v6 = this.h.a(v2);
                    if(v6 != null && !v6.shouldIgnore()) {
                        boolean v8 = this.h.a(v6);
                        boolean v9 = this.h.a(v5);
                        if((v8) && v6 == v5) {
                            goto label_52;
                        }

                        c v7 = this.h.b(v6);
                        this.h.c(v5, v4);
                        c v10 = this.h.c(v5);
                        if(v7 == null) {
                            this.a(v2, v5, v6);
                            goto label_54;
                        }

                        this.a(v6, v5, v7, v10, v8, v9);
                        goto label_54;
                    }

                label_52:
                    this.h.c(v5, v4);
                }

            label_54:
            }

            this.h.a(this.aI);
        }

        this.n.b(this.e);
        this.D.b = this.D.e;
        this.x = false;
        this.y = false;
        this.D.j = false;
        this.D.k = false;
        this.n.u = false;
        if(this.e.b != null) {
            this.e.b.clear();
        }

        if(this.n.y) {
            this.n.x = 0;
            this.n.y = false;
            this.e.b();
        }

        this.n.a(this.D);
        this.m();
        this.a(false);
        this.h.a();
        if(this.k(this.aD[0], this.aD[1])) {
            this.i(0, 0);
        }

        this.L();
        this.J();
    }

    public void a(h arg2) {
        this.a(arg2, -1);
    }

    public void a(m arg2) {
        this.U.add(arg2);
    }

    public void a(android.support.v7.widget.RecyclerView$k arg2) {
        if(this.ad == null) {
            this.ad = new ArrayList();
        }

        this.ad.add(arg2);
    }

    public View a(float arg6, float arg7) {
        int v0;
        for(v0 = this.g.b() - 1; v0 >= 0; --v0) {
            View v1 = this.g.b(v0);
            float v2 = v1.getTranslationX();
            float v3 = v1.getTranslationY();
            if(arg6 >= (((float)v1.getLeft())) + v2 && arg6 <= (((float)v1.getRight())) + v2 && arg7 >= (((float)v1.getTop())) + v3 && arg7 <= (((float)v1.getBottom())) + v3) {
                return v1;
            }
        }

        return null;
    }

    public void a(int arg2) {
        if(this.v) {
            return;
        }

        this.f();
        if(this.n == null) {
            Log.e("RecyclerView", "Cannot scroll to position a LayoutManager set. Call setLayoutManager with a non-null argument.");
            return;
        }

        this.n.e(arg2);
        this.awakenScrollBars();
    }

    public void a(n arg2) {
        if(this.aA == null) {
            this.aA = new ArrayList();
        }

        this.aA.add(arg2);
    }

    void a(StateListDrawable arg11, Drawable arg12, StateListDrawable arg13, Drawable arg14) {
        if(arg11 != null && arg12 != null && arg13 != null && arg14 != null) {
            Resources v0 = this.getContext().getResources();
            new ak(this, arg11, arg12, arg13, arg14, v0.getDimensionPixelSize(android.support.v7.e.a$a.fastscroll_default_thickness), v0.getDimensionPixelSize(android.support.v7.e.a$a.fastscroll_minimum_range), v0.getDimensionPixelOffset(android.support.v7.e.a$a.fastscroll_margin));
            return;
        }

        StringBuilder v12 = new StringBuilder();
        v12.append("Trying to set fast scroller without both required drawables.");
        v12.append(this.a());
        throw new IllegalArgumentException(v12.toString());
    }

    private void a(Context arg7, String arg8, AttributeSet arg9, int arg10, int arg11) {
        StringBuilder v11;
        Object[] v1;
        Constructor v4;
        Class v0_1;
        if(arg8 != null) {
            arg8 = arg8.trim();
            if(arg8.isEmpty()) {
                return;
            }

            arg8 = this.a(arg7, arg8);
            try {
                ClassLoader v0 = this.isInEditMode() ? this.getClass().getClassLoader() : arg7.getClassLoader();
                v0_1 = v0.loadClass(arg8).asSubclass(i.class);
            }
            catch(ClassNotFoundException v7) {
                goto label_102;
            }
            catch(InvocationTargetException v7_1) {
                goto label_90;
            }
            catch(InstantiationException v7_2) {
                goto label_78;
            }
            catch(IllegalAccessException v7_3) {
                goto label_66;
            }
            catch(ClassCastException v7_4) {
                goto label_54;
            }

            try {
                v4 = v0_1.getConstructor(RecyclerView.Q);
                v1 = new Object[]{arg7, arg9, Integer.valueOf(arg10), Integer.valueOf(arg11)};
                goto label_34;
            }
            catch(ClassCastException v7_4) {
            }
            catch(IllegalAccessException v7_3) {
            }
            catch(InstantiationException v7_2) {
            }
            catch(InvocationTargetException v7_1) {
            }
            catch(ClassNotFoundException v7) {
            }
            catch(NoSuchMethodException v7_5) {
                try {
                    v4 = v0_1.getConstructor();
                    goto label_34;
                }
                catch(ClassCastException v7_4) {
                }
                catch(IllegalAccessException v7_3) {
                }
                catch(InstantiationException v7_2) {
                }
                catch(InvocationTargetException v7_1) {
                }
                catch(ClassNotFoundException v7) {
                }
                catch(NoSuchMethodException v10) {
                    try {
                        v10.initCause(((Throwable)v7_5));
                        v11 = new StringBuilder();
                        v11.append(arg9.getPositionDescription());
                        v11.append(": Error creating LayoutManager ");
                        v11.append(arg8);
                        throw new IllegalStateException(v11.toString(), ((Throwable)v10));
                    label_34:
                        v4.setAccessible(true);
                        this.setLayoutManager(v4.newInstance(v1));
                    }
                    catch(ClassCastException v7_4) {
                    label_54:
                        v11 = new StringBuilder();
                        v11.append(arg9.getPositionDescription());
                        v11.append(": Class is not a LayoutManager ");
                        v11.append(arg8);
                        throw new IllegalStateException(v11.toString(), ((Throwable)v7_4));
                    }
                    catch(IllegalAccessException v7_3) {
                    label_66:
                        v11 = new StringBuilder();
                        v11.append(arg9.getPositionDescription());
                        v11.append(": Cannot access non-public constructor ");
                        v11.append(arg8);
                        throw new IllegalStateException(v11.toString(), ((Throwable)v7_3));
                    }
                    catch(InstantiationException v7_2) {
                    label_78:
                        v11 = new StringBuilder();
                        v11.append(arg9.getPositionDescription());
                        v11.append(": Could not instantiate the LayoutManager: ");
                        v11.append(arg8);
                        throw new IllegalStateException(v11.toString(), ((Throwable)v7_2));
                    }
                    catch(InvocationTargetException v7_1) {
                    label_90:
                        v11 = new StringBuilder();
                        v11.append(arg9.getPositionDescription());
                        v11.append(": Could not instantiate the LayoutManager: ");
                        v11.append(arg8);
                        throw new IllegalStateException(v11.toString(), ((Throwable)v7_1));
                    }
                    catch(ClassNotFoundException v7) {
                    label_102:
                        v11 = new StringBuilder();
                        v11.append(arg9.getPositionDescription());
                        v11.append(": Unable to find LayoutManager ");
                        v11.append(arg8);
                        throw new IllegalStateException(v11.toString(), ((Throwable)v7));
                    }
                }
            }
        }
    }

    final void a(t arg4) {
        if(this.getScrollState() == 2) {
            OverScroller v0 = this.A.a;
            arg4.o = v0.getFinalX() - v0.getCurrX();
            arg4.p = v0.getFinalY() - v0.getCurrY();
        }
        else {
            arg4.o = 0;
            arg4.p = 0;
        }
    }

    private void a(int[] arg9) {
        int v0 = this.g.b();
        if(v0 == 0) {
            arg9[0] = -1;
            arg9[1] = -1;
            return;
        }

        int v3 = 0;
        int v4 = 2147483647;
        int v5 = -2147483648;
        while(v3 < v0) {
            w v6 = RecyclerView.e(this.g.b(v3));
            if(v6.shouldIgnore()) {
            }
            else {
                int v6_1 = v6.getLayoutPosition();
                if(v6_1 < v4) {
                    v4 = v6_1;
                }

                if(v6_1 <= v5) {
                    goto label_26;
                }

                v5 = v6_1;
            }

        label_26:
            ++v3;
        }

        arg9[0] = v4;
        arg9[1] = v5;
    }

    long a(w arg3) {
        long v0 = this.m.hasStableIds() ? arg3.getItemId() : ((long)arg3.mPosition);
        return v0;
    }

    void a(w arg4, c arg5) {
        arg4.setFlags(0, 8192);
        if((this.D.h) && (arg4.isUpdated()) && !arg4.isRemoved() && !arg4.shouldIgnore()) {
            this.h.a(this.a(arg4), arg4);
        }

        this.h.a(arg4, arg5);
    }

    void a(boolean arg4) {
        if(this.W < 1) {
            this.W = 1;
        }

        if(!arg4 && !this.v) {
            this.u = false;
        }

        if(this.W == 1) {
            if((arg4) && (this.u) && !this.v && this.n != null && this.m != null) {
                this.q();
            }

            if(this.v) {
                goto label_24;
            }

            this.u = false;
        }

    label_24:
        --this.W;
    }

    private void a(long arg7, w arg9, w arg10) {
        StringBuilder v8;
        int v0 = this.g.b();
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            w v2 = RecyclerView.e(this.g.b(v1));
            if(v2 == arg9) {
            }
            else if(this.a(v2) == arg7) {
                if(this.m != null && (this.m.hasStableIds())) {
                    v8 = new StringBuilder();
                    v8.append("Two different ViewHolders have the same stable ID. Stable IDs in your adapter MUST BE unique and SHOULD NOT change.\n ViewHolder 1:");
                    v8.append(v2);
                    v8.append(" \n View Holder 2:");
                    v8.append(arg9);
                    v8.append(this.a());
                    throw new IllegalStateException(v8.toString());
                }

                v8 = new StringBuilder();
                v8.append("Two different ViewHolders have the same change ID. This might happen due to inconsistent Adapter update events or if the LayoutManager lays out the same View multiple times.\n ViewHolder 1:");
                v8.append(v2);
                v8.append(" \n View Holder 2:");
                v8.append(arg9);
                v8.append(this.a());
                throw new IllegalStateException(v8.toString());
            }
        }

        Log.e("RecyclerView", "Problem while matching changed view holders with the newones. The pre-layout information for the change holder " + arg10 + " cannot be found but it is necessary for " + arg9 + this.a());
    }

    private void a(w arg2, w arg3, c arg4, c arg5, boolean arg6, boolean arg7) {
        arg2.setIsRecyclable(false);
        if(arg6) {
            this.e(arg2);
        }

        if(arg2 != arg3) {
            if(arg7) {
                this.e(arg3);
            }

            arg2.mShadowedHolder = arg3;
            this.e(arg2);
            this.e.c(arg2);
            arg3.setIsRecyclable(false);
            arg3.mShadowingHolder = arg2;
        }

        if(this.z.a(arg2, arg3, arg4, arg5)) {
            this.p();
        }
    }

    private String a(Context arg3, String arg4) {
        char v1 = '.';
        if(arg4.charAt(0) == v1) {
            return arg3.getPackageName() + arg4;
        }

        if(arg4.contains(".")) {
            return arg4;
        }

        return RecyclerView.class.getPackage().getName() + v1 + arg4;
    }

    private void a(float arg7, float arg8, float arg9, float arg10) {
        // Method was not decompiled
    }

    String a() {
        return " " + super.toString() + ", adapter:" + this.m + ", layout:" + this.n + ", context:" + this.getContext();
    }

    private void a(a arg3, boolean arg4, boolean arg5) {
        if(this.m != null) {
            this.m.unregisterAdapterDataObserver(this.R);
            this.m.onDetachedFromRecyclerView(this);
        }

        if(!arg4 || (arg5)) {
            this.c();
        }

        this.f.a();
        a v5 = this.m;
        this.m = arg3;
        if(arg3 != null) {
            arg3.registerAdapterDataObserver(this.R);
            arg3.onAttachedToRecyclerView(this);
        }

        if(this.n != null) {
            this.n.a(v5, this.m);
        }

        this.e.a(v5, this.m, arg4);
        this.D.f = true;
    }

    static void a(RecyclerView arg0, int arg1) {
        arg0.detachViewFromParent(arg1);
    }

    static void a(RecyclerView arg0, int arg1, int arg2) {
        arg0.setMeasuredDimension(arg1, arg2);
    }

    static void a(RecyclerView arg0, View arg1, int arg2, ViewGroup$LayoutParams arg3) {
        arg0.attachViewToParent(arg1, arg2, arg3);
    }

    static void a(View arg6, Rect arg7) {
        ViewGroup$LayoutParams v0 = arg6.getLayoutParams();
        arg7.set(arg6.getLeft() - ((j)v0).d.left - ((j)v0).leftMargin, arg6.getTop() - ((j)v0).d.top - ((j)v0).topMargin, arg6.getRight() + ((j)v0).d.right + ((j)v0).rightMargin, arg6.getBottom() + ((j)v0).d.bottom + ((j)v0).bottomMargin);
    }

    private void a(View arg12, View arg13) {
        View v0 = arg13 != null ? arg13 : arg12;
        this.k.set(0, 0, v0.getWidth(), v0.getHeight());
        ViewGroup$LayoutParams v0_1 = v0.getLayoutParams();
        if(((v0_1 instanceof j)) && !((j)v0_1).e) {
            Rect v0_2 = ((j)v0_1).d;
            this.k.left -= v0_2.left;
            this.k.right += v0_2.right;
            this.k.top -= v0_2.top;
            this.k.bottom += v0_2.bottom;
        }

        if(arg13 != null) {
            this.offsetDescendantRectToMyCoords(arg13, this.k);
            this.offsetRectIntoDescendantCoords(arg12, this.k);
        }

        i v5 = this.n;
        Rect v8 = this.k;
        int v9 = this.t ^ 1;
        boolean v10 = arg13 == null ? true : false;
        v5.a(this, arg12, v8, ((boolean)v9), v10);
    }

    static boolean a(RecyclerView arg0) {
        return arg0.awakenScrollBars();
    }

    private boolean a(MotionEvent arg8) {
        int v0 = arg8.getAction();
        int v1 = 3;
        if(v0 == v1 || v0 == 0) {
            this.V = null;
        }

        int v2 = this.U.size();
        int v4;
        for(v4 = 0; v4 < v2; ++v4) {
            Object v5 = this.U.get(v4);
            if((((m)v5).a(this, arg8)) && v0 != v1) {
                this.V = ((m)v5);
                return 1;
            }
        }

        return 0;
    }

    private boolean a(View arg6, View arg7, int arg8) {
        int v2;
        boolean v0 = false;
        if(arg7 != null) {
            if((((RecyclerView)arg7)) == this) {
            }
            else if(this.c(arg7) == null) {
                return 0;
            }
            else if(arg6 == null) {
                return 1;
            }
            else if(this.c(arg6) == null) {
                return 1;
            }
            else {
                this.k.set(0, 0, arg6.getWidth(), arg6.getHeight());
                this.T.set(0, 0, arg7.getWidth(), arg7.getHeight());
                this.offsetDescendantRectToMyCoords(arg6, this.k);
                this.offsetDescendantRectToMyCoords(arg7, this.T);
                int v7 = -1;
                int v6 = this.n.v() == 1 ? -1 : 1;
                if(this.k.left >= this.T.left && this.k.right > this.T.left) {
                    goto label_49;
                }
                else if(this.k.right < this.T.right) {
                    v2 = 1;
                }
                else {
                label_49:
                    if((this.k.right > this.T.right || this.k.left >= this.T.right) && this.k.left > this.T.left) {
                        v2 = -1;
                        goto label_67;
                    }

                    v2 = 0;
                }

            label_67:
                if(this.k.top >= this.T.top && this.k.bottom > this.T.top) {
                    goto label_84;
                }
                else if(this.k.bottom < this.T.bottom) {
                    v7 = 1;
                }
                else {
                label_84:
                    if((this.k.bottom > this.T.bottom || this.k.top >= this.T.bottom) && this.k.top > this.T.top) {
                        goto label_101;
                    }

                    v7 = 0;
                }

            label_101:
                if(arg8 == 17) {
                    goto label_142;
                }

                if(arg8 == 33) {
                    goto label_139;
                }

                if(arg8 == 66) {
                    goto label_136;
                }

                if(arg8 == 130) {
                    goto label_133;
                }

                switch(arg8) {
                    case 1: {
                        goto label_127;
                    }
                    case 2: {
                        goto label_121;
                    }
                }

                StringBuilder v7_1 = new StringBuilder();
                v7_1.append("Invalid direction: ");
                v7_1.append(arg8);
                v7_1.append(this.a());
                throw new IllegalArgumentException(v7_1.toString());
            label_121:
                if(v7 > 0 || v7 == 0 && v2 * v6 >= 0) {
                    v0 = true;
                }

                return v0;
            label_127:
                if(v7 < 0 || v7 == 0 && v2 * v6 <= 0) {
                    v0 = true;
                }

                return v0;
            label_133:
                if(v7 > 0) {
                    v0 = true;
                }

                return v0;
            label_136:
                if(v2 > 0) {
                    v0 = true;
                }

                return v0;
            label_139:
                if(v7 < 0) {
                    v0 = true;
                }

                return v0;
            label_142:
                if(v2 >= 0) {
                    return v0;
                }

                v0 = true;
            }
        }

        return v0;
    }

    w a(int arg6, boolean arg7) {
        int v0 = this.g.c();
        w v1 = null;
        int v2;
        for(v2 = 0; v2 < v0; ++v2) {
            w v3 = RecyclerView.e(this.g.d(v2));
            if(v3 != null && !v3.isRemoved()) {
                if(arg7) {
                    if(v3.mPosition != arg6) {
                        goto label_25;
                    }
                }
                else if(v3.getLayoutPosition() != arg6) {
                    goto label_25;
                }

                if(this.g.c(v3.itemView)) {
                    v1 = v3;
                    goto label_25;
                }

                return v3;
            }

        label_25:
        }

        return v1;
    }

    public w a(long arg8) {
        w v1 = null;
        if(this.m != null) {
            if(!this.m.hasStableIds()) {
            }
            else {
                int v0 = this.g.c();
                int v2;
                for(v2 = 0; v2 < v0; ++v2) {
                    w v3 = RecyclerView.e(this.g.d(v2));
                    if(v3 != null && !v3.isRemoved() && v3.getItemId() == arg8) {
                        if(this.g.c(v3.itemView)) {
                            v1 = v3;
                        }
                        else {
                            return v3;
                        }
                    }
                }
            }
        }

        return v1;
    }

    public void a(int arg2, int arg3) {
        this.a(arg2, arg3, null);
    }

    public void a(int arg3, int arg4, Interpolator arg5) {
        if(this.n == null) {
            Log.e("RecyclerView", "Cannot smooth scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
            return;
        }

        if(this.v) {
            return;
        }

        if(0 != 0 || 0 != 0) {
            this.A.a(0, 0, arg5);
        }
    }

    void a(int arg7, int arg8, Object arg9) {
        int v0 = this.g.c();
        int v1 = arg7 + arg8;
        int v2;
        for(v2 = 0; v2 < v0; ++v2) {
            View v3 = this.g.d(v2);
            w v4 = RecyclerView.e(v3);
            if(v4 != null) {
                if(v4.shouldIgnore()) {
                }
                else if(v4.mPosition >= arg7 && v4.mPosition < v1) {
                    v4.addFlags(2);
                    v4.addChangePayload(arg9);
                    v3.getLayoutParams().e = true;
                }
            }
        }

        this.e.c(arg7, arg8);
    }

    void a(int arg8, int arg9, boolean arg10) {
        int v0 = arg8 + arg9;
        int v1 = this.g.c();
        int v2;
        for(v2 = 0; v2 < v1; ++v2) {
            w v3 = RecyclerView.e(this.g.d(v2));
            if(v3 != null && !v3.shouldIgnore()) {
                if(v3.mPosition >= v0) {
                    v3.offsetPosition(-arg9, arg10);
                }
                else if(v3.mPosition >= arg8) {
                    v3.flagRemovedAndOffsetPosition(arg8 - 1, -arg9, arg10);
                }
                else {
                    goto label_25;
                }

                this.D.f = true;
            }

        label_25:
        }

        this.e.a(arg8, arg9, arg10);
        this.requestLayout();
    }

    void a(int arg5, int arg6, int[] arg7) {
        this.e();
        this.l();
        d.a("RV Scroll");
        this.a(this.D);
        arg5 = arg5 != 0 ? this.n.a(arg5, this.e, this.D) : 0;
        arg6 = arg6 != 0 ? this.n.b(arg6, this.e, this.D) : 0;
        d.a();
        this.w();
        this.m();
        this.a(false);
        if(arg7 != null) {
            arg7[0] = arg5;
            arg7[1] = arg6;
        }
    }

    public void a(h arg3, int arg4) {
        if(this.n != null) {
            this.n.a("Cannot add item decoration during a scroll  or layout");
        }

        if(this.p.isEmpty()) {
            this.setWillNotDraw(false);
        }

        if(arg4 < 0) {
            this.p.add(arg3);
        }
        else {
            this.p.add(arg4, arg3);
        }

        this.r();
        this.requestLayout();
    }

    void a(w arg2, c arg3, c arg4) {
        arg2.setIsRecyclable(false);
        if(this.z.b(arg2, arg3, arg4)) {
            this.p();
        }
    }

    void a(String arg5) {
        if(this.o()) {
            if(arg5 == null) {
                StringBuilder v0 = new StringBuilder();
                v0.append("Cannot call this method while RecyclerView is computing a layout or scrolling");
                v0.append(this.a());
                throw new IllegalStateException(v0.toString());
            }

            throw new IllegalStateException(arg5);
        }

        if(this.af > 0) {
            StringBuilder v2 = new StringBuilder();
            v2.append("");
            v2.append(this.a());
            Log.w("RecyclerView", "Cannot call this method in a scroll callback. Scroll callbacks mightbe run during a measure & layout pass where you cannot change theRecyclerView data. Any method call that might change the structureof the RecyclerView or the adapter contents should be postponed tothe next frame.", new IllegalStateException(v2.toString()));
        }
    }

    public boolean a(int arg8, int arg9, int arg10, int arg11, int[] arg12, int arg13) {
        return this.getScrollingChildHelper().a(arg8, arg9, arg10, arg11, arg12, arg13);
    }

    boolean a(int arg19, int arg20, MotionEvent arg21) {
        int v6;
        int v15;
        int v14;
        int v13;
        RecyclerView v7 = this;
        int v8 = arg19;
        int v9 = arg20;
        MotionEvent v10 = arg21;
        this.d();
        boolean v12 = false;
        if(v7.m != null) {
            v7.a(v8, v9, v7.J);
            int v0 = v7.J[0];
            int v3 = v9 - v7.J[1];
            v13 = v0;
            v14 = v7.J[1];
            v15 = v8 - v0;
            v6 = v3;
        }
        else {
            v6 = 0;
            v13 = 0;
            v14 = 0;
            v15 = 0;
        }

        if(!v7.p.isEmpty()) {
            this.invalidate();
        }

        int v17 = v6;
        if(this.a(v13, v14, v15, v6, v7.aF, 0)) {
            v7.aq -= v7.aF[0];
            v7.ar -= v7.aF[1];
            if(v10 != null) {
                v10.offsetLocation(((float)v7.aF[0]), ((float)v7.aF[1]));
            }

            v7.aG[0] += v7.aF[0];
            v7.aG[1] += v7.aF[1];
        }
        else {
            if(this.getOverScrollMode() == 2) {
                goto label_86;
            }

            if(v10 != null && !android.support.v4.view.i.a(v10, 8194)) {
                v7.a(arg21.getX(), ((float)v15), arg21.getY(), ((float)v17));
            }

            this.c(arg19, arg20);
        }

    label_86:
        if(v13 != 0 || v14 != 0) {
            v7.i(v13, v14);
        }

        if(!this.awakenScrollBars()) {
            this.invalidate();
        }

        if(v13 != 0 || v14 != 0) {
            v12 = true;
        }

        return v12;
    }

    public boolean a(int arg7, int arg8, int[] arg9, int[] arg10, int arg11) {
        return this.getScrollingChildHelper().a(arg7, arg8, arg9, arg10, arg11);
    }

    boolean a(w arg2, int arg3) {
        if(this.o()) {
            arg2.mPendingAccessibilityState = arg3;
            this.K.add(arg2);
            return 0;
        }

        android.support.v4.view.t.b(arg2.itemView, arg3);
        return 1;
    }

    boolean a(View arg3) {
        this.e();
        boolean v0 = this.g.f(arg3);
        if(v0) {
            w v3 = RecyclerView.e(arg3);
            this.e.c(v3);
            this.e.b(v3);
        }

        this.a((((int)v0)) ^ 1);
        return v0;
    }

    boolean a(AccessibilityEvent arg3) {
        if(this.o()) {
            int v3 = arg3 != null ? android.support.v4.view.a.a.a(arg3) : 0;
            if(v3 == 0) {
                v3 = 0;
            }

            this.ab |= v3;
            return 1;
        }

        return 0;
    }

    public void addFocusables(ArrayList arg2, int arg3, int arg4) {
        if(this.n == null || !this.n.a(this, arg2, arg3, arg4)) {
            super.addFocusables(arg2, arg3, arg4);
        }
    }

    void b(boolean arg3) {
        --this.ae;
        if(this.ae < 1) {
            this.ae = 0;
            if(arg3) {
                this.F();
                this.x();
            }
        }
    }

    public w b(View arg4) {
        ViewParent v0 = arg4.getParent();
        if(v0 != null) {
            if((((RecyclerView)v0)) == this) {
            }
            else {
                StringBuilder v1 = new StringBuilder();
                v1.append("View ");
                v1.append(arg4);
                v1.append(" is not a direct child of ");
                v1.append(this);
                throw new IllegalArgumentException(v1.toString());
            }
        }

        return RecyclerView.e(arg4);
    }

    public void b(h arg3) {
        if(this.n != null) {
            this.n.a("Cannot remove item decoration during a scroll  or layout");
        }

        this.p.remove(arg3);
        if(this.p.isEmpty()) {
            boolean v3 = this.getOverScrollMode() == 2 ? true : false;
            this.setWillNotDraw(v3);
        }

        this.r();
        this.requestLayout();
    }

    public void b(m arg2) {
        this.U.remove(arg2);
        if(this.V == arg2) {
            this.V = null;
        }
    }

    public void b(android.support.v7.widget.RecyclerView$k arg2) {
        if(this.ad == null) {
            return;
        }

        this.ad.remove(arg2);
    }

    public void b(n arg2) {
        if(this.aA != null) {
            this.aA.remove(arg2);
        }
    }

    void b() {
        this.f = new android.support.v7.widget.e(new android.support.v7.widget.e$a() {
            public w a(int arg4) {
                w v4 = this.a.a(arg4, true);
                w v0 = null;
                if(v4 == null) {
                    return v0;
                }

                if(this.a.g.c(v4.itemView)) {
                    return v0;
                }

                return v4;
            }

            public void a(int arg3, int arg4) {
                this.a.a(arg3, arg4, true);
                this.a.E = true;
                this.a.D.c += arg4;
            }

            public void a(int arg2, int arg3, Object arg4) {
                this.a.a(arg2, arg3, arg4);
                this.a.F = true;
            }

            public void a(android.support.v7.widget.e$b arg1) {
                this.c(arg1);
            }

            public void b(int arg3, int arg4) {
                this.a.a(arg3, arg4, false);
                this.a.E = true;
            }

            public void b(android.support.v7.widget.e$b arg1) {
                this.c(arg1);
            }

            void c(android.support.v7.widget.e$b arg5) {
                int v0 = arg5.a;
                if(v0 == 4) {
                    this.a.n.a(this.a, arg5.b, arg5.d, arg5.c);
                }
                else if(v0 != 8) {
                    switch(v0) {
                        case 1: {
                            goto label_14;
                        }
                        case 2: {
                            goto label_7;
                        }
                    }

                    return;
                label_7:
                    this.a.n.b(this.a, arg5.b, arg5.d);
                    return;
                label_14:
                    this.a.n.a(this.a, arg5.b, arg5.d);
                }
                else {
                    this.a.n.a(this.a, arg5.b, arg5.d, 1);
                }
            }

            public void c(int arg2, int arg3) {
                this.a.g(arg2, arg3);
                this.a.E = true;
            }

            public void d(int arg2, int arg3) {
                this.a.f(arg2, arg3);
                this.a.E = true;
            }
        });
    }

    private boolean b(MotionEvent arg7) {
        int v0 = arg7.getAction();
        if(this.V != null) {
            m v1 = null;
            if(v0 == 0) {
                this.V = v1;
            }
            else {
                this.V.b(this, arg7);
                if(v0 == 3 || v0 == 1) {
                    this.V = v1;
                }

                return 1;
            }
        }

        if(v0 != 0) {
            v0 = this.U.size();
            int v3 = 0;
            while(v3 < v0) {
                Object v4 = this.U.get(v3);
                if(((m)v4).a(this, arg7)) {
                    this.V = ((m)v4);
                    return 1;
                }
                else {
                    ++v3;
                    continue;
                }

                return 0;
            }
        }

        return 0;
    }

    void b(int arg2) {
        if(this.n == null) {
            return;
        }

        this.n.e(arg2);
        this.awakenScrollBars();
    }

    void b(w arg2, c arg3, c arg4) {
        this.e(arg2);
        arg2.setIsRecyclable(false);
        if(this.z.a(arg2, arg3, arg4)) {
            this.p();
        }
    }

    public boolean b(int arg8, int arg9) {
        int v1 = 0;
        if(this.n == null) {
            Log.e("RecyclerView", "Cannot fling without a LayoutManager set. Call setLayoutManager with a non-null argument.");
            return 0;
        }

        if(this.v) {
            return 0;
        }

        boolean v0 = this.n.e();
        boolean v2 = this.n.f();
        if(0 == 0 && 0 == 0) {
            return 0;
        }

        float v3 = ((float)0);
        float v4 = ((float)0);
        if(!this.dispatchNestedPreFling(v3, v4)) {
            boolean v6 = (v0) || (v2) ? true : false;
            this.dispatchNestedFling(v3, v4, v6);
            if(this.at != null && (this.at.a(0, 0))) {
                return 1;
            }

            if(!v6) {
                return 0;
            }

            if(v0) {
                v1 = 1;
            }

            if(v2) {
                v1 |= 2;
            }

            this.j(v1, 1);
            this.A.a(Math.max(-this.av, Math.min(0, this.av)), Math.max(-this.av, Math.min(0, this.av)));
            return 1;
        }

        return 0;
    }

    boolean b(w arg3) {
        boolean v3 = this.z == null || (this.z.a(arg3, arg3.getUnmodifiedPayloads())) ? true : false;
        return v3;
    }

    void c() {
        if(this.z != null) {
            this.z.d();
        }

        if(this.n != null) {
            this.n.c(this.e);
            this.n.b(this.e);
        }

        this.e.a();
    }

    public w c(int arg6) {
        w v1 = null;
        if(this.x) {
            return v1;
        }

        int v0 = this.g.c();
        int v2;
        for(v2 = 0; v2 < v0; ++v2) {
            w v3 = RecyclerView.e(this.g.d(v2));
            if(v3 != null && !v3.isRemoved() && this.d(v3) == arg6) {
                if(this.g.c(v3.itemView)) {
                    v1 = v3;
                }
                else {
                    return v3;
                }
            }
        }

        return v1;
    }

    public View c(View arg3) {
        ViewParent v3;
        while(true) {
            ViewParent v0 = arg3.getParent();
            if(v0 != null && (((RecyclerView)v0)) != this && ((v0 instanceof View))) {
                v3 = v0;
                continue;
            }

            break;
        }

        if((((RecyclerView)v0)) == this) {
        }
        else {
            arg3 = null;
        }

        return ((View)v3);
    }

    static void c(w arg3) {
        Object v1;
        if(arg3.mNestedRecyclerView != null) {
            Object v0 = arg3.mNestedRecyclerView.get();
            while(true) {
                v1 = null;
                if(v0 == null) {
                    break;
                }
                else if(v0 == arg3.itemView) {
                    return;
                }
                else {
                    ViewParent v0_1 = ((View)v0).getParent();
                    if((v0_1 instanceof View)) {
                    }
                    else {
                        v0 = v1;
                    }

                    continue;
                }

                return;
            }

            arg3.mNestedRecyclerView = ((WeakReference)v1);
        }
    }

    private void c(MotionEvent arg4) {
        int v0 = arg4.getActionIndex();
        if(arg4.getPointerId(v0) == this.am) {
            v0 = v0 == 0 ? 1 : 0;
            this.am = arg4.getPointerId(v0);
            int v1 = ((int)(arg4.getX(v0) + 0.5f));
            this.aq = v1;
            this.ao = v1;
            int v4 = ((int)(arg4.getY(v0) + 0.5f));
            this.ar = v4;
            this.ap = v4;
        }
    }

    void c(int arg3, int arg4) {
        int v0_1;
        if(this.ah == null || (this.ah.isFinished()) || arg3 <= 0) {
            v0_1 = 0;
        }
        else {
            this.ah.onRelease();
            boolean v0 = this.ah.isFinished();
        }

        if(this.aj != null && !this.aj.isFinished() && arg3 < 0) {
            this.aj.onRelease();
            v0_1 |= this.aj.isFinished();
        }

        if(this.ai != null && !this.ai.isFinished() && arg4 > 0) {
            this.ai.onRelease();
            v0_1 |= this.ai.isFinished();
        }

        if(this.ak != null && !this.ak.isFinished() && arg4 < 0) {
            this.ak.onRelease();
            v0_1 |= this.ak.isFinished();
        }

        if((((boolean)v0_1))) {
            android.support.v4.view.t.d(((View)this));
        }
    }

    void c(boolean arg2) {
        this.y |= ((int)arg2);
        this.x = true;
        this.u();
    }

    protected boolean checkLayoutParams(ViewGroup$LayoutParams arg2) {
        boolean v2 = !(arg2 instanceof j) || !this.n.a(((j)arg2)) ? false : true;
        return v2;
    }

    public int computeHorizontalScrollExtent() {
        int v1 = 0;
        if(this.n == null) {
            return 0;
        }

        if(this.n.e()) {
            v1 = this.n.e(this.D);
        }

        return v1;
    }

    public int computeHorizontalScrollOffset() {
        int v1 = 0;
        if(this.n == null) {
            return 0;
        }

        if(this.n.e()) {
            v1 = this.n.c(this.D);
        }

        return v1;
    }

    public int computeHorizontalScrollRange() {
        int v1 = 0;
        if(this.n == null) {
            return 0;
        }

        if(this.n.e()) {
            v1 = this.n.g(this.D);
        }

        return v1;
    }

    public int computeVerticalScrollExtent() {
        int v1 = 0;
        if(this.n == null) {
            return 0;
        }

        if(this.n.f()) {
            v1 = this.n.f(this.D);
        }

        return v1;
    }

    public int computeVerticalScrollOffset() {
        int v1 = 0;
        if(this.n == null) {
            return 0;
        }

        if(this.n.f()) {
            v1 = this.n.d(this.D);
        }

        return v1;
    }

    public int computeVerticalScrollRange() {
        int v1 = 0;
        if(this.n == null) {
            return 0;
        }

        if(this.n.f()) {
            v1 = this.n.h(this.D);
        }

        return v1;
    }

    public w d(View arg1) {
        arg1 = this.c(arg1);
        w v1 = arg1 == null ? null : this.b(arg1);
        return v1;
    }

    void d() {
        if(this.t) {
            if(this.x) {
            }
            else if(!this.f.d()) {
                return;
            }
            else {
                if(!this.f.a(4) || (this.f.a(11))) {
                    if(!this.f.d()) {
                        return;
                    }

                    d.a("RV FullInvalidate");
                    this.q();
                label_41:
                    d.a();
                }
                else {
                    d.a("RV PartialInvalidate");
                    this.e();
                    this.l();
                    this.f.b();
                    if(!this.u) {
                        if(this.A()) {
                            this.q();
                        }
                        else {
                            this.f.c();
                        }
                    }

                    this.a(true);
                    this.m();
                    goto label_41;
                }

                return;
            }
        }

        d.a("RV FullInvalidate");
        this.q();
        d.a();
    }

    int d(w arg2) {
        if(!arg2.hasAnyOfTheFlags(524)) {
            if(!arg2.isBound()) {
            }
            else {
                return this.f.c(arg2.mPosition);
            }
        }

        return -1;
    }

    public void d(int arg4) {
        int v0 = this.g.b();
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            this.g.b(v1).offsetTopAndBottom(arg4);
        }
    }

    void d(int arg3, int arg4) {
        if(arg3 < 0) {
            this.g();
            this.ah.onAbsorb(-arg3);
        }
        else if(arg3 > 0) {
            this.h();
            this.aj.onAbsorb(arg3);
        }

        if(arg4 < 0) {
            this.i();
            this.ai.onAbsorb(-arg4);
        }
        else if(arg4 > 0) {
            this.j();
            this.ak.onAbsorb(arg4);
        }

        if(arg3 != 0 || arg4 != 0) {
            android.support.v4.view.t.d(((View)this));
        }
    }

    public boolean dispatchNestedFling(float arg2, float arg3, boolean arg4) {
        return this.getScrollingChildHelper().a(arg2, arg3, arg4);
    }

    public boolean dispatchNestedPreFling(float arg2, float arg3) {
        return this.getScrollingChildHelper().a(arg2, arg3);
    }

    public boolean dispatchNestedPreScroll(int arg2, int arg3, int[] arg4, int[] arg5) {
        return this.getScrollingChildHelper().a(arg2, arg3, arg4, arg5);
    }

    public boolean dispatchNestedScroll(int arg7, int arg8, int arg9, int arg10, int[] arg11) {
        return this.getScrollingChildHelper().a(arg7, arg8, arg9, arg10, arg11);
    }

    protected void dispatchRestoreInstanceState(SparseArray arg1) {
        this.dispatchThawSelfOnly(arg1);
    }

    protected void dispatchSaveInstanceState(SparseArray arg1) {
        this.dispatchFreezeSelfOnly(arg1);
    }

    public void draw(Canvas arg8) {
        float v4_1;
        int v5;
        int v4;
        int v3;
        super.draw(arg8);
        int v0 = this.p.size();
        int v1 = 0;
        int v2;
        for(v2 = 0; v2 < v0; ++v2) {
            this.p.get(v2).a(arg8, this, this.D);
        }

        if(this.ah == null || (this.ah.isFinished())) {
            v3 = 0;
        }
        else {
            v0 = arg8.save();
            v3 = this.i ? this.getPaddingBottom() : 0;
            arg8.rotate(270f);
            arg8.translate(((float)(-this.getHeight() + v3)), 0f);
            v3 = this.ah == null || !this.ah.draw(arg8) ? 0 : 1;
            arg8.restoreToCount(v0);
        }

        if(this.ai != null && !this.ai.isFinished()) {
            v0 = arg8.save();
            if(this.i) {
                arg8.translate(((float)this.getPaddingLeft()), ((float)this.getPaddingTop()));
            }

            v4 = this.ai == null || !this.ai.draw(arg8) ? 0 : 1;
            v3 |= v4;
            arg8.restoreToCount(v0);
        }

        if(this.aj != null && !this.aj.isFinished()) {
            v0 = arg8.save();
            v4 = this.getWidth();
            v5 = this.i ? this.getPaddingTop() : 0;
            arg8.rotate(90f);
            arg8.translate(((float)(-v5)), ((float)(-v4)));
            v4 = this.aj == null || !this.aj.draw(arg8) ? 0 : 1;
            v3 |= v4;
            arg8.restoreToCount(v0);
        }

        if(this.ak == null || (this.ak.isFinished())) {
            v1 = v3;
        }
        else {
            v0 = arg8.save();
            arg8.rotate(180f);
            if(this.i) {
                v4_1 = ((float)(-this.getWidth() + this.getPaddingRight()));
                v5 = -this.getHeight() + this.getPaddingBottom();
            }
            else {
                v4_1 = ((float)(-this.getWidth()));
                v5 = -this.getHeight();
            }

            arg8.translate(v4_1, ((float)v5));
            if(this.ak != null && (this.ak.draw(arg8))) {
                v1 = 1;
            }

            v1 |= v3;
            arg8.restoreToCount(v0);
        }

        if(v1 == 0 && this.z != null && this.p.size() > 0 && (this.z.b())) {
            v1 = 1;
        }

        if(v1 != 0) {
            android.support.v4.view.t.d(((View)this));
        }
    }

    public boolean drawChild(Canvas arg1, View arg2, long arg3) {
        return super.drawChild(arg1, arg2, arg3);
    }

    static w e(View arg0) {
        if(arg0 == null) {
            return null;
        }

        return arg0.getLayoutParams().c;
    }

    void e() {
        ++this.W;
        if(this.W == 1 && !this.v) {
            this.u = false;
        }
    }

    private void e(w arg6) {
        View v0 = arg6.itemView;
        int v1 = v0.getParent() == this ? 1 : 0;
        this.e.c(this.b(v0));
        if(arg6.isTmpDetached()) {
            this.g.a(v0, -1, v0.getLayoutParams(), true);
        }
        else if(v1 == 0) {
            this.g.a(v0, true);
        }
        else {
            this.g.d(v0);
        }
    }

    public void e(int arg4) {
        int v0 = this.g.b();
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            this.g.b(v1).offsetLeftAndRight(arg4);
        }
    }

    void e(int arg3, int arg4) {
        this.setMeasuredDimension(i.a(arg3, this.getPaddingLeft() + this.getPaddingRight(), android.support.v4.view.t.k(((View)this))), i.a(arg4, this.getPaddingTop() + this.getPaddingBottom(), android.support.v4.view.t.l(((View)this))));
    }

    public void f() {
        this.setScrollState(0);
        this.B();
    }

    void f(int arg10, int arg11) {
        int v4;
        int v3;
        int v2;
        int v0 = this.g.c();
        if(arg10 < arg11) {
            v2 = arg10;
            v3 = arg11;
            v4 = -1;
        }
        else {
            v3 = arg10;
            v2 = arg11;
            v4 = 1;
        }

        int v6;
        for(v6 = 0; v6 < v0; ++v6) {
            w v7 = RecyclerView.e(this.g.d(v6));
            if(v7 != null && v7.mPosition >= v2) {
                if(v7.mPosition > v3) {
                }
                else {
                    if(v7.mPosition == arg10) {
                        v7.offsetPosition(arg11 - arg10, false);
                    }
                    else {
                        v7.offsetPosition(v4, false);
                    }

                    this.D.f = true;
                }
            }
        }

        this.e.a(arg10, arg11);
        this.requestLayout();
    }

    @Deprecated public int f(View arg1) {
        return this.g(arg1);
    }

    public void f(int arg1) {
    }

    public View focusSearch(View arg9, int arg10) {
        int v6;
        View v0 = this.n.d(arg9, arg10);
        if(v0 != null) {
            return v0;
        }

        int v0_1 = this.m == null || this.n == null || (this.o()) || (this.v) ? 0 : 1;
        FocusFinder v3 = FocusFinder.getInstance();
        View v4 = null;
        if(v0_1 != 0) {
            int v5 = 2;
            if(arg10 != v5 && arg10 != 1) {
                goto label_80;
            }

            if(this.n.f()) {
                v0_1 = arg10 == v5 ? 130 : 33;
                v6 = v3.findNextFocus(((ViewGroup)this), arg9, v0_1) == null ? 1 : 0;
                if(!RecyclerView.O) {
                    goto label_40;
                }

                arg10 = v0_1;
            }
            else {
                v6 = 0;
            }

        label_40:
            if(v6 == 0 && (this.n.e())) {
                v0_1 = this.n.v() == 1 ? 1 : 0;
                v5 = arg10 == v5 ? 1 : 0;
                v0_1 = (v0_1 ^ v5) != 0 ? 66 : 17;
                v6 = v3.findNextFocus(((ViewGroup)this), arg9, v0_1) == null ? 1 : 0;
                if(!RecyclerView.O) {
                    goto label_67;
                }

                arg10 = v0_1;
            }

        label_67:
            if(v6 != 0) {
                this.d();
                if(this.c(arg9) == null) {
                    return v4;
                }
                else {
                    this.e();
                    this.n.a(arg9, arg10, this.e, this.D);
                    this.a(false);
                }
            }

            v0 = v3.findNextFocus(((ViewGroup)this), arg9, arg10);
        }
        else {
        label_80:
            View v1 = v3.findNextFocus(((ViewGroup)this), arg9, arg10);
            if(v1 == null && v0_1 != 0) {
                this.d();
                if(this.c(arg9) == null) {
                    return v4;
                }
                else {
                    this.e();
                    v0 = this.n.a(arg9, arg10, this.e, this.D);
                    this.a(false);
                    goto label_95;
                }
            }

            v0 = v1;
        }

    label_95:
        if(v0 != null && !v0.hasFocusable()) {
            if(this.getFocusedChild() == null) {
                return super.focusSearch(arg9, arg10);
            }
            else {
                this.a(v0, v4);
                return arg9;
            }
        }

        if(this.a(arg9, v0, arg10)) {
        }
        else {
            v0 = super.focusSearch(arg9, arg10);
        }

        return v0;
    }

    void g(int arg6, int arg7) {
        int v0 = this.g.c();
        int v2;
        for(v2 = 0; v2 < v0; ++v2) {
            w v3 = RecyclerView.e(this.g.d(v2));
            if(v3 != null && !v3.shouldIgnore() && v3.mPosition >= arg6) {
                v3.offsetPosition(arg7, false);
                this.D.f = true;
            }
        }

        this.e.b(arg6, arg7);
        this.requestLayout();
    }

    void g() {
        int v2;
        int v1;
        EdgeEffect v0;
        if(this.ah != null) {
            return;
        }

        this.ah = this.ag.a(this, 0);
        if(this.i) {
            v0 = this.ah;
            v1 = this.getMeasuredHeight() - this.getPaddingTop() - this.getPaddingBottom();
            v2 = this.getMeasuredWidth() - this.getPaddingLeft() - this.getPaddingRight();
        }
        else {
            v0 = this.ah;
            v1 = this.getMeasuredHeight();
            v2 = this.getMeasuredWidth();
        }

        v0.setSize(v1, v2);
    }

    public int g(View arg1) {
        w v1 = RecyclerView.e(arg1);
        int v1_1 = v1 != null ? v1.getAdapterPosition() : -1;
        return v1_1;
    }

    void g(int arg3) {
        if(this.n != null) {
            this.n.l(arg3);
        }

        this.f(arg3);
        if(this.az != null) {
            this.az.a(this, arg3);
        }

        if(this.aA != null) {
            int v0;
            for(v0 = this.aA.size() - 1; v0 >= 0; --v0) {
                this.aA.get(v0).a(this, arg3);
            }
        }
    }

    protected ViewGroup$LayoutParams generateDefaultLayoutParams() {
        if(this.n != null) {
            return this.n.a();
        }

        StringBuilder v1 = new StringBuilder();
        v1.append("RecyclerView has no LayoutManager");
        v1.append(this.a());
        throw new IllegalStateException(v1.toString());
    }

    public ViewGroup$LayoutParams generateLayoutParams(AttributeSet arg3) {
        if(this.n != null) {
            return this.n.a(this.getContext(), arg3);
        }

        StringBuilder v0 = new StringBuilder();
        v0.append("RecyclerView has no LayoutManager");
        v0.append(this.a());
        throw new IllegalStateException(v0.toString());
    }

    protected ViewGroup$LayoutParams generateLayoutParams(ViewGroup$LayoutParams arg3) {
        if(this.n != null) {
            return this.n.a(arg3);
        }

        StringBuilder v0 = new StringBuilder();
        v0.append("RecyclerView has no LayoutManager");
        v0.append(this.a());
        throw new IllegalStateException(v0.toString());
    }

    public a getAdapter() {
        return this.m;
    }

    public int getBaseline() {
        if(this.n != null) {
            return this.n.w();
        }

        return super.getBaseline();
    }

    protected int getChildDrawingOrder(int arg2, int arg3) {
        if(this.aC == null) {
            return super.getChildDrawingOrder(arg2, arg3);
        }

        return this.aC.a(arg2, arg3);
    }

    public boolean getClipToPadding() {
        return this.i;
    }

    public aw getCompatAccessibilityDelegate() {
        return this.H;
    }

    public e getEdgeEffectFactory() {
        return this.ag;
    }

    public f getItemAnimator() {
        return this.z;
    }

    public int getItemDecorationCount() {
        return this.p.size();
    }

    public i getLayoutManager() {
        return this.n;
    }

    public int getMaxFlingVelocity() {
        return this.av;
    }

    public int getMinFlingVelocity() {
        return this.au;
    }

    long getNanoTime() {
        if(RecyclerView.d) {
            return System.nanoTime();
        }

        return 0;
    }

    public l getOnFlingListener() {
        return this.at;
    }

    public boolean getPreserveFocusAfterLayout() {
        return this.ay;
    }

    public o getRecycledViewPool() {
        return this.e.g();
    }

    public int getScrollState() {
        return this.al;
    }

    private android.support.v4.view.l getScrollingChildHelper() {
        if(this.aE == null) {
            this.aE = new android.support.v4.view.l(((View)this));
        }

        return this.aE;
    }

    void h() {
        int v2;
        int v1;
        EdgeEffect v0;
        if(this.aj != null) {
            return;
        }

        this.aj = this.ag.a(this, 2);
        if(this.i) {
            v0 = this.aj;
            v1 = this.getMeasuredHeight() - this.getPaddingTop() - this.getPaddingBottom();
            v2 = this.getMeasuredWidth() - this.getPaddingLeft() - this.getPaddingRight();
        }
        else {
            v0 = this.aj;
            v1 = this.getMeasuredHeight();
            v2 = this.getMeasuredWidth();
        }

        v0.setSize(v1, v2);
    }

    public int h(View arg1) {
        w v1 = RecyclerView.e(arg1);
        int v1_1 = v1 != null ? v1.getLayoutPosition() : -1;
        return v1_1;
    }

    public void h(int arg1, int arg2) {
    }

    public boolean h(int arg2) {
        return this.getScrollingChildHelper().a(arg2);
    }

    public boolean hasNestedScrollingParent() {
        return this.getScrollingChildHelper().b();
    }

    void i(int arg3, int arg4) {
        ++this.af;
        int v0 = this.getScrollX();
        int v1 = this.getScrollY();
        this.onScrollChanged(v0, v1, v0, v1);
        this.h(arg3, arg4);
        if(this.az != null) {
            this.az.a(this, arg3, arg4);
        }

        if(this.aA != null) {
            for(v0 = this.aA.size() - 1; v0 >= 0; --v0) {
                this.aA.get(v0).a(this, arg3, arg4);
            }
        }

        --this.af;
    }

    void i() {
        int v2;
        int v1;
        EdgeEffect v0;
        if(this.ai != null) {
            return;
        }

        this.ai = this.ag.a(this, 1);
        if(this.i) {
            v0 = this.ai;
            v1 = this.getMeasuredWidth() - this.getPaddingLeft() - this.getPaddingRight();
            v2 = this.getMeasuredHeight() - this.getPaddingTop() - this.getPaddingBottom();
        }
        else {
            v0 = this.ai;
            v1 = this.getMeasuredWidth();
            v2 = this.getMeasuredHeight();
        }

        v0.setSize(v1, v2);
    }

    public void i(View arg1) {
    }

    public boolean isAttachedToWindow() {
        return this.q;
    }

    public boolean isNestedScrollingEnabled() {
        return this.getScrollingChildHelper().a();
    }

    public boolean j(int arg2, int arg3) {
        return this.getScrollingChildHelper().a(arg2, arg3);
    }

    void j() {
        int v2;
        int v1;
        EdgeEffect v0;
        if(this.ak != null) {
            return;
        }

        this.ak = this.ag.a(this, 3);
        if(this.i) {
            v0 = this.ak;
            v1 = this.getMeasuredWidth() - this.getPaddingLeft() - this.getPaddingRight();
            v2 = this.getMeasuredHeight() - this.getPaddingTop() - this.getPaddingBottom();
        }
        else {
            v0 = this.ak;
            v1 = this.getMeasuredWidth();
            v2 = this.getMeasuredHeight();
        }

        v0.setSize(v1, v2);
    }

    public void j(View arg1) {
    }

    private boolean k(int arg4, int arg5) {
        this.a(this.aD);
        boolean v1 = false;
        if(this.aD[0] != arg4 || this.aD[1] != arg5) {
            v1 = true;
        }

        return v1;
    }

    Rect k(View arg9) {
        ViewGroup$LayoutParams v0 = arg9.getLayoutParams();
        if(!((j)v0).e) {
            return ((j)v0).d;
        }

        if((this.D.a()) && ((((j)v0).e()) || (((j)v0).c()))) {
            return ((j)v0).d;
        }

        Rect v1 = ((j)v0).d;
        v1.set(0, 0, 0, 0);
        int v3 = this.p.size();
        int v4;
        for(v4 = 0; v4 < v3; ++v4) {
            this.k.set(0, 0, 0, 0);
            this.p.get(v4).a(this.k, arg9, this, this.D);
            v1.left += this.k.left;
            v1.top += this.k.top;
            v1.right += this.k.right;
            v1.bottom += this.k.bottom;
        }

        ((j)v0).e = false;
        return v1;
    }

    void k() {
        this.ak = null;
        this.ai = null;
        this.aj = null;
        this.ah = null;
    }

    void l() {
        ++this.ae;
    }

    static RecyclerView l(View arg4) {
        RecyclerView v1 = null;
        if(!(arg4 instanceof ViewGroup)) {
            return v1;
        }

        if((arg4 instanceof RecyclerView)) {
            return arg4;
        }

        int v0 = ((ViewGroup)arg4).getChildCount();
        int v2;
        for(v2 = 0; v2 < v0; ++v2) {
            RecyclerView v3 = RecyclerView.l(((ViewGroup)arg4).getChildAt(v2));
            if(v3 != null) {
                return v3;
            }
        }

        return v1;
    }

    void m() {
        this.b(true);
    }

    void m(View arg3) {
        w v0 = RecyclerView.e(arg3);
        this.j(arg3);
        if(this.m != null && v0 != null) {
            this.m.onViewDetachedFromWindow(v0);
        }

        if(this.ad != null) {
            int v0_1;
            for(v0_1 = this.ad.size() - 1; v0_1 >= 0; --v0_1) {
                this.ad.get(v0_1).b(arg3);
            }
        }
    }

    boolean n() {
        boolean v0 = this.ac == null || !this.ac.isEnabled() ? false : true;
        return v0;
    }

    void n(View arg3) {
        w v0 = RecyclerView.e(arg3);
        this.i(arg3);
        if(this.m != null && v0 != null) {
            this.m.onViewAttachedToWindow(v0);
        }

        if(this.ad != null) {
            int v0_1;
            for(v0_1 = this.ad.size() - 1; v0_1 >= 0; --v0_1) {
                this.ad.get(v0_1).a(arg3);
            }
        }
    }

    private int o(View arg4) {
        int v0;
        while(true) {
            v0 = arg4.getId();
            do {
                if(!arg4.isFocused() && ((arg4 instanceof ViewGroup)) && (arg4.hasFocus())) {
                    arg4 = ((ViewGroup)arg4).getFocusedChild();
                    if(arg4.getId() == -1) {
                        continue;
                    }

                    break;
                }

                return v0;
            }
            while(true);
        }

        return v0;
    }

    public boolean o() {
        boolean v0 = this.ae > 0 ? true : false;
        return v0;
    }

    protected void onAttachedToWindow() {
        float v0_1;
        super.onAttachedToWindow();
        this.ae = 0;
        boolean v1 = true;
        this.q = true;
        if(!this.t || (this.isLayoutRequested())) {
            v1 = false;
        }
        else {
        }

        this.t = v1;
        if(this.n != null) {
            this.n.c(this);
        }

        this.G = false;
        if(RecyclerView.d) {
            this.B = an.a.get();
            if(this.B == null) {
                this.B = new an();
                Display v0 = android.support.v4.view.t.F(((View)this));
                if((this.isInEditMode()) || v0 == null) {
                label_36:
                    v0_1 = 60f;
                }
                else {
                    v0_1 = v0.getRefreshRate();
                    if(v0_1 >= 30f) {
                    }
                    else {
                        goto label_36;
                    }
                }

                this.B.d = ((long)(1000000000f / v0_1));
                an.a.set(this.B);
            }

            this.B.a(this);
        }
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if(this.z != null) {
            this.z.d();
        }

        this.f();
        this.q = false;
        if(this.n != null) {
            this.n.b(this, this.e);
        }

        this.K.clear();
        this.removeCallbacks(this.aH);
        this.h.b();
        if((RecyclerView.d) && this.B != null) {
            this.B.b(this);
            this.B = null;
        }
    }

    public void onDraw(Canvas arg5) {
        super.onDraw(arg5);
        int v0 = this.p.size();
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            this.p.get(v1).b(arg5, this, this.D);
        }
    }

    public boolean onGenericMotionEvent(MotionEvent arg6) {
        float v3;
        float v0;
        if(this.n == null) {
            return 0;
        }

        if(this.v) {
            return 0;
        }

        if(arg6.getAction() == 8) {
            if((arg6.getSource() & 2) != 0) {
                v0 = this.n.f() ? -arg6.getAxisValue(9) : 0f;
                if(!this.n.e()) {
                    goto label_46;
                }

                v3 = arg6.getAxisValue(10);
            }
            else {
                if((arg6.getSource() & 4194304) != 0) {
                    v0 = arg6.getAxisValue(26);
                    if(this.n.f()) {
                        v0 = -v0;
                        goto label_46;
                    }
                    else if(this.n.e()) {
                        v3 = v0;
                        v0 = 0f;
                        goto label_47;
                    }
                }

                v0 = 0f;
            label_46:
                v3 = 0f;
            }

        label_47:
            if(v0 == 0f && v3 == 0f) {
                return 0;
            }

            this.a(((int)(v3 * this.aw)), ((int)(v0 * this.ax)), arg6);
        }

        return 0;
    }

    public boolean onInterceptTouchEvent(MotionEvent arg8) {
        int v0_2;
        int v8;
        boolean v1 = false;
        if(this.v) {
            return 0;
        }

        if(this.a(arg8)) {
            this.E();
            return 1;
        }

        if(this.n == null) {
            return 0;
        }

        boolean v0 = this.n.e();
        boolean v3 = this.n.f();
        if(this.an == null) {
            this.an = VelocityTracker.obtain();
        }

        this.an.addMovement(arg8);
        int v4 = arg8.getActionMasked();
        int v5 = arg8.getActionIndex();
        float v6 = 0.5f;
        switch(v4) {
            case 0: {
                if(this.aa) {
                    this.aa = false;
                }

                this.am = arg8.getPointerId(0);
                v4 = ((int)(arg8.getX() + v6));
                this.aq = v4;
                this.ao = v4;
                v8 = ((int)(arg8.getY() + v6));
                this.ar = v8;
                this.ap = v8;
                if(this.al == 2) {
                    this.getParent().requestDisallowInterceptTouchEvent(true);
                    this.setScrollState(1);
                }

                int[] v8_1 = this.aG;
                this.aG[1] = 0;
                v8_1[0] = 0;
                v8 = v0 ? 1 : 0;
                if(v3) {
                    v8 |= 2;
                }

                this.j(v8, 0);
                break;
            }
            case 1: {
                this.an.clear();
                this.stopNestedScroll(0);
                break;
            }
            case 2: {
                v4 = arg8.findPointerIndex(this.am);
                if(v4 < 0) {
                    Log.e("RecyclerView", "Error processing scroll; pointer index for id " + this.am + " not found. Did any MotionEvents get skipped?");
                    return 0;
                }

                v5 = ((int)(arg8.getX(v4) + v6));
                v8 = ((int)(arg8.getY(v4) + v6));
                if(this.al == 1) {
                    goto label_124;
                }

                v4 = v5 - this.ao;
                int v6_1 = v8 - this.ap;
                if(!v0 || Math.abs(v4) <= this.as) {
                    v0_2 = 0;
                }
                else {
                    this.aq = v5;
                    v0_2 = 1;
                }

                if((v3) && Math.abs(v6_1) > this.as) {
                    this.ar = v8;
                    v0_2 = 1;
                }

                if(v0_2 == 0) {
                    goto label_124;
                }

                this.setScrollState(1);
                break;
            }
            case 3: {
                this.E();
                break;
            }
            case 5: {
                this.am = arg8.getPointerId(v5);
                v0_2 = ((int)(arg8.getX(v5) + v6));
                this.aq = v0_2;
                this.ao = v0_2;
                v8 = ((int)(arg8.getY(v5) + v6));
                this.ar = v8;
                this.ap = v8;
                break;
            }
            case 6: {
                this.c(arg8);
                break;
            }
            default: {
                break;
            }
        }

    label_124:
        if(this.al == 1) {
            v1 = true;
        }

        return v1;
    }

    protected void onLayout(boolean arg1, int arg2, int arg3, int arg4, int arg5) {
        d.a("RV OnLayout");
        this.q();
        d.a();
        this.t = true;
    }

    protected void onMeasure(int arg8, int arg9) {
        if(this.n == null) {
            this.e(arg8, arg9);
            return;
        }

        int v2 = 0;
        if(this.n.c()) {
            int v0 = View$MeasureSpec.getMode(arg8);
            int v3 = View$MeasureSpec.getMode(arg9);
            this.n.a(this.e, this.D, arg8, arg9);
            int v4 = 1073741824;
            if(v0 == v4 && v3 == v4) {
                v2 = 1;
            }

            if(v2 == 0) {
                if(this.m == null) {
                }
                else {
                    if(this.D.d == 1) {
                        this.M();
                    }

                    this.n.d(arg8, arg9);
                    this.D.i = true;
                    this.N();
                    this.n.e(arg8, arg9);
                    if(!this.n.l()) {
                        return;
                    }

                    this.n.d(View$MeasureSpec.makeMeasureSpec(this.getMeasuredWidth(), v4), View$MeasureSpec.makeMeasureSpec(this.getMeasuredHeight(), v4));
                    this.D.i = true;
                    this.N();
                    this.n.e(arg8, arg9);
                    return;
                }
            }

            return;
        }
        else {
            if(this.r) {
                this.n.a(this.e, this.D, arg8, arg9);
                return;
            }

            if(this.w) {
                this.e();
                this.l();
                this.H();
                this.m();
                if(this.D.k) {
                    this.D.g = true;
                }
                else {
                    this.f.e();
                    this.D.g = false;
                }

                this.w = false;
                this.a(false);
            }
            else {
                if(!this.D.k) {
                    goto label_83;
                }

                this.setMeasuredDimension(this.getMeasuredWidth(), this.getMeasuredHeight());
                return;
            }

        label_83:
            this.D.e = this.m != null ? this.m.getItemCount() : 0;
            this.e();
            this.n.a(this.e, this.D, arg8, arg9);
            this.a(false);
            this.D.g = false;
        }
    }

    protected boolean onRequestFocusInDescendants(int arg2, Rect arg3) {
        if(this.o()) {
            return 0;
        }

        return super.onRequestFocusInDescendants(arg2, arg3);
    }

    protected void onRestoreInstanceState(Parcelable arg2) {
        if(!(arg2 instanceof SavedState)) {
            super.onRestoreInstanceState(arg2);
            return;
        }

        this.S = ((SavedState)arg2);
        super.onRestoreInstanceState(this.S.getSuperState());
        if(this.n != null && this.S.a != null) {
            this.n.a(this.S.a);
        }
    }

    protected Parcelable onSaveInstanceState() {
        SavedState v0 = new SavedState(super.onSaveInstanceState());
        if(this.S != null) {
            v0.a(this.S);
        }
        else {
            Parcelable v1 = this.n != null ? this.n.d() : null;
            v0.a = v1;
        }

        return ((Parcelable)v0);
    }

    protected void onSizeChanged(int arg1, int arg2, int arg3, int arg4) {
        super.onSizeChanged(arg1, arg2, arg3, arg4);
        if(arg1 != arg3 || arg2 != arg4) {
            this.k();
        }
    }

    public boolean onTouchEvent(MotionEvent arg15) {
        int v0_2;
        int v7_2;
        int v15;
        int v1 = 0;
        if(!this.v) {
            if(this.aa) {
            }
            else if(this.b(arg15)) {
                this.E();
                return 1;
            }
            else if(this.n == null) {
                return 0;
            }
            else {
                boolean v0 = this.n.e();
                boolean v3 = this.n.f();
                if(this.an == null) {
                    this.an = VelocityTracker.obtain();
                }

                MotionEvent v4 = MotionEvent.obtain(arg15);
                int v5 = arg15.getActionMasked();
                int v6 = arg15.getActionIndex();
                if(v5 == 0) {
                    int[] v7 = this.aG;
                    this.aG[1] = 0;
                    v7[0] = 0;
                }

                v4.offsetLocation(((float)this.aG[0]), ((float)this.aG[1]));
                float v7_1 = 0.5f;
                switch(v5) {
                    case 0: {
                        this.am = arg15.getPointerId(0);
                        v5 = ((int)(arg15.getX() + v7_1));
                        this.aq = v5;
                        this.ao = v5;
                        v15 = ((int)(arg15.getY() + v7_1));
                        this.ar = v15;
                        this.ap = v15;
                        v15 = v0 ? 1 : 0;
                        if(v3) {
                            v15 |= 2;
                        }

                        this.j(v15, 0);
                        break;
                    }
                    case 1: {
                        this.an.addMovement(v4);
                        this.an.computeCurrentVelocity(1000, ((float)this.av));
                        float v0_3 = v0 ? -this.an.getXVelocity(this.am) : 0f;
                        float v3_1 = v3 ? -this.an.getYVelocity(this.am) : 0f;
                        if(v0_3 == 0f && v3_1 == 0f || !this.b(((int)v0_3), ((int)v3_1))) {
                            this.setScrollState(0);
                        }

                        this.D();
                        v1 = 1;
                        break;
                    }
                    case 2: {
                        v5 = arg15.findPointerIndex(this.am);
                        if(v5 < 0) {
                            Log.e("RecyclerView", "Error processing scroll; pointer index for id " + this.am + " not found. Did any MotionEvents get skipped?");
                            return 0;
                        }

                        v6 = ((int)(arg15.getX(v5) + v7_1));
                        v15 = ((int)(arg15.getY(v5) + v7_1));
                        v5 = this.aq - v6;
                        int v13 = this.ar - v15;
                        if(this.a(v5, v13, this.I, this.aF, 0)) {
                            v5 -= this.I[0];
                            v13 -= this.I[1];
                            v4.offsetLocation(((float)this.aF[0]), ((float)this.aF[1]));
                            this.aG[0] += this.aF[0];
                            this.aG[1] += this.aF[1];
                        }

                        if(this.al != 1) {
                            if(!v0 || Math.abs(v5) <= this.as) {
                                v7_2 = 0;
                            }
                            else {
                                if(v5 > 0) {
                                    v5 -= this.as;
                                }
                                else {
                                    v5 += this.as;
                                }

                                v7_2 = 1;
                            }

                            if((v3) && Math.abs(v13) > this.as) {
                                if(v13 > 0) {
                                    v13 -= this.as;
                                }
                                else {
                                    v13 += this.as;
                                }

                                v7_2 = 1;
                            }

                            if(v7_2 == 0) {
                                goto label_143;
                            }

                            this.setScrollState(1);
                        }

                    label_143:
                        if(this.al != 1) {
                            goto label_223;
                        }

                        this.aq = v6 - this.aF[0];
                        this.ar = v15 - this.aF[1];
                        v15 = v0 ? v5 : 0;
                        v0_2 = v3 ? v13 : 0;
                        if(this.a(v15, v0_2, v4)) {
                            this.getParent().requestDisallowInterceptTouchEvent(true);
                        }

                        if(this.B == null) {
                            goto label_223;
                        }

                        if(v5 == 0 && v13 == 0) {
                            goto label_223;
                        }

                        this.B.a(this, v5, v13);
                        break;
                    }
                    case 3: {
                        this.E();
                        break;
                    }
                    case 5: {
                        this.am = arg15.getPointerId(v6);
                        v0_2 = ((int)(arg15.getX(v6) + v7_1));
                        this.aq = v0_2;
                        this.ao = v0_2;
                        v15 = ((int)(arg15.getY(v6) + v7_1));
                        this.ar = v15;
                        this.ap = v15;
                        break;
                    }
                    case 6: {
                        this.c(arg15);
                        break;
                    }
                    default: {
                        break;
                    }
                }

            label_223:
                if(v1 == 0) {
                    this.an.addMovement(v4);
                }

                v4.recycle();
                return 1;
            }
        }

        return 0;
    }

    void p() {
        if(!this.G && (this.q)) {
            android.support.v4.view.t.a(((View)this), this.aH);
            this.G = true;
        }
    }

    void q() {
        String v1;
        String v0;
        if(this.m == null) {
            v0 = "RecyclerView";
            v1 = "No adapter attached; skipping layout";
        }
        else if(this.n == null) {
            v0 = "RecyclerView";
            v1 = "No layout manager attached; skipping layout";
        }
        else {
            goto label_11;
        }

        Log.e(v0, v1);
        return;
    label_11:
        this.D.i = false;
        if(this.D.d == 1) {
            this.M();
            goto label_19;
        }
        else if((this.f.f()) || this.n.A() != this.getWidth() || this.n.B() != this.getHeight()) {
        label_19:
            this.n.f(this);
            this.N();
        }
        else {
            this.n.f(this);
        }

        this.O();
    }

    void r() {
        int v0 = this.g.c();
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            this.g.d(v1).getLayoutParams().e = true;
        }

        this.e.j();
    }

    protected void removeDetachedView(View arg3, boolean arg4) {
        w v0 = RecyclerView.e(arg3);
        if(v0 != null) {
            if(v0.isTmpDetached()) {
                v0.clearTmpDetachFlag();
            }
            else if(v0.shouldIgnore()) {
            }
            else {
                StringBuilder v4 = new StringBuilder();
                v4.append("Called removeDetachedView with a view which is not flagged as tmp detached.");
                v4.append(v0);
                v4.append(this.a());
                throw new IllegalArgumentException(v4.toString());
            }
        }

        arg3.clearAnimation();
        this.m(arg3);
        super.removeDetachedView(arg3, arg4);
    }

    public void requestChildFocus(View arg3, View arg4) {
        if(!this.n.a(this, this.D, arg3, arg4) && arg4 != null) {
            this.a(arg3, arg4);
        }

        super.requestChildFocus(arg3, arg4);
    }

    public boolean requestChildRectangleOnScreen(View arg2, Rect arg3, boolean arg4) {
        return this.n.a(this, arg2, arg3, arg4);
    }

    public void requestDisallowInterceptTouchEvent(boolean arg4) {
        int v0 = this.U.size();
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            this.U.get(v1).a(arg4);
        }

        super.requestDisallowInterceptTouchEvent(arg4);
    }

    public void requestLayout() {
        if(this.W != 0 || (this.v)) {
            this.u = true;
        }
        else {
            super.requestLayout();
        }
    }

    void s() {
        int v0 = this.g.c();
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            w v2 = RecyclerView.e(this.g.d(v1));
            if(!v2.shouldIgnore()) {
                v2.saveOldPosition();
            }
        }
    }

    public void scrollBy(int arg4, int arg5) {
        if(this.n == null) {
            Log.e("RecyclerView", "Cannot scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
            return;
        }

        if(this.v) {
            return;
        }

        boolean v0 = this.n.e();
        boolean v1 = this.n.f();
        if((v0) || (v1)) {
            this.a(0, 0, null);
        }
    }

    public void scrollTo(int arg1, int arg2) {
        Log.w("RecyclerView", "RecyclerView does not support scrolling to an absolute position. Use scrollToPosition instead");
    }

    public void sendAccessibilityEventUnchecked(AccessibilityEvent arg2) {
        if(this.a(arg2)) {
            return;
        }

        super.sendAccessibilityEventUnchecked(arg2);
    }

    public void setAccessibilityDelegateCompat(aw arg1) {
        this.H = arg1;
        android.support.v4.view.t.a(((View)this), this.H);
    }

    public void setAdapter(a arg3) {
        this.setLayoutFrozen(false);
        this.a(arg3, false, true);
        this.c(false);
        this.requestLayout();
    }

    public void setChildDrawingOrderCallback(android.support.v7.widget.RecyclerView$d arg2) {
        if(arg2 == this.aC) {
            return;
        }

        this.aC = arg2;
        boolean v2 = this.aC != null ? true : false;
        this.setChildrenDrawingOrderEnabled(v2);
    }

    public void setClipToPadding(boolean arg2) {
        if(arg2 != this.i) {
            this.k();
        }

        this.i = arg2;
        super.setClipToPadding(arg2);
        if(this.t) {
            this.requestLayout();
        }
    }

    public void setEdgeEffectFactory(e arg1) {
        android.support.v4.f.l.a(arg1);
        this.ag = arg1;
        this.k();
    }

    public void setHasFixedSize(boolean arg1) {
        this.r = arg1;
    }

    public void setItemAnimator(f arg3) {
        if(this.z != null) {
            this.z.d();
            this.z.a(null);
        }

        this.z = arg3;
        if(this.z != null) {
            this.z.a(this.aB);
        }
    }

    public void setItemViewCacheSize(int arg2) {
        this.e.a(arg2);
    }

    public void setLayoutFrozen(boolean arg10) {
        if(arg10 != this.v) {
            this.a("Do not setLayoutFrozen in layout or scroll");
            if(!arg10) {
                this.v = false;
                if((this.u) && this.n != null && this.m != null) {
                    this.requestLayout();
                }

                this.u = false;
            }
            else {
                long v3 = SystemClock.uptimeMillis();
                this.onTouchEvent(MotionEvent.obtain(v3, v3, 3, 0f, 0f, 0));
                this.v = true;
                this.aa = true;
                this.f();
            }
        }
    }

    public void setLayoutManager(i arg4) {
        if(arg4 == this.n) {
            return;
        }

        this.f();
        if(this.n != null) {
            if(this.z != null) {
                this.z.d();
            }

            this.n.c(this.e);
            this.n.b(this.e);
            this.e.a();
            if(this.q) {
                this.n.b(this, this.e);
            }

            this.n.b(null);
            this.n = null;
        }
        else {
            this.e.a();
        }

        this.g.a();
        this.n = arg4;
        if(arg4 != null) {
            if(arg4.q == null) {
                this.n.b(this);
                if(this.q) {
                    this.n.c(this);
                }
            }
            else {
                StringBuilder v1 = new StringBuilder();
                v1.append("LayoutManager ");
                v1.append(arg4);
                v1.append(" is already attached to a RecyclerView:");
                v1.append(arg4.q.a());
                throw new IllegalArgumentException(v1.toString());
            }
        }

        this.e.b();
        this.requestLayout();
    }

    public void setNestedScrollingEnabled(boolean arg2) {
        this.getScrollingChildHelper().a(arg2);
    }

    public void setOnFlingListener(l arg1) {
        this.at = arg1;
    }

    @Deprecated public void setOnScrollListener(n arg1) {
        this.az = arg1;
    }

    public void setPreserveFocusAfterLayout(boolean arg1) {
        this.ay = arg1;
    }

    public void setRecycledViewPool(o arg2) {
        this.e.a(arg2);
    }

    public void setRecyclerListener(q arg1) {
        this.o = arg1;
    }

    void setScrollState(int arg2) {
        if(arg2 == this.al) {
            return;
        }

        this.al = arg2;
        if(arg2 != 2) {
            this.B();
        }

        this.g(arg2);
    }

    public void setScrollingTouchSlop(int arg5) {
        ViewConfiguration v0 = ViewConfiguration.get(this.getContext());
        switch(arg5) {
            case 0: {
                goto label_16;
            }
            case 1: {
                goto label_14;
            }
        }

        Log.w("RecyclerView", "setScrollingTouchSlop(): bad argument constant " + arg5 + "; using default value");
        goto label_16;
    label_14:
        arg5 = v0.getScaledPagingTouchSlop();
        goto label_17;
    label_16:
        arg5 = v0.getScaledTouchSlop();
    label_17:
        this.as = arg5;
    }

    public void setViewCacheExtension(u arg2) {
        this.e.a(arg2);
    }

    public boolean startNestedScroll(int arg2) {
        return this.getScrollingChildHelper().b(arg2);
    }

    public void stopNestedScroll(int arg2) {
        this.getScrollingChildHelper().c(arg2);
    }

    public void stopNestedScroll() {
        this.getScrollingChildHelper().c();
    }

    void t() {
        int v0 = this.g.c();
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            w v2 = RecyclerView.e(this.g.d(v1));
            if(!v2.shouldIgnore()) {
                v2.clearOldPosition();
            }
        }

        this.e.i();
    }

    void u() {
        int v0 = this.g.c();
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            w v2 = RecyclerView.e(this.g.d(v1));
            if(v2 != null && !v2.shouldIgnore()) {
                v2.addFlags(6);
            }
        }

        this.r();
        this.e.h();
    }

    public boolean v() {
        boolean v0 = !this.t || (this.x) || (this.f.d()) ? true : false;
        return v0;
    }

    void w() {
        int v0 = this.g.b();
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            View v2 = this.g.b(v1);
            w v3 = this.b(v2);
            if(v3 != null && v3.mShadowingHolder != null) {
                View v3_1 = v3.mShadowingHolder.itemView;
                int v4 = v2.getLeft();
                int v2_1 = v2.getTop();
                if(v4 == v3_1.getLeft() && v2_1 == v3_1.getTop()) {
                    goto label_23;
                }

                v3_1.layout(v4, v2_1, v3_1.getWidth() + v4, v3_1.getHeight() + v2_1);
            }

        label_23:
        }
    }

    void x() {
        int v0;
        for(v0 = this.K.size() - 1; v0 >= 0; --v0) {
            Object v1 = this.K.get(v0);
            if(((w)v1).itemView.getParent() == this) {
                if(((w)v1).shouldIgnore()) {
                }
                else {
                    int v2 = ((w)v1).mPendingAccessibilityState;
                    int v3 = -1;
                    if(v2 != v3) {
                        android.support.v4.view.t.b(((w)v1).itemView, v2);
                        ((w)v1).mPendingAccessibilityState = v3;
                    }
                }
            }
        }

        this.K.clear();
    }

    @SuppressLint(value={"InlinedApi"}) private void y() {
        if(android.support.v4.view.t.a(((View)this)) == 0) {
            android.support.v4.view.t.a(((View)this), 8);
        }
    }

    private void z() {
        this.g = new ae(new android.support.v7.widget.ae$b() {
            public int a() {
                return this.a.getChildCount();
            }

            public int a(View arg2) {
                return this.a.indexOfChild(arg2);
            }

            public void a(int arg3) {
                View v0 = this.a.getChildAt(arg3);
                if(v0 != null) {
                    this.a.m(v0);
                    v0.clearAnimation();
                }

                this.a.removeViewAt(arg3);
            }

            public void a(View arg2, int arg3) {
                this.a.addView(arg2, arg3);
                this.a.n(arg2);
            }

            public void a(View arg3, int arg4, ViewGroup$LayoutParams arg5) {
                w v0 = RecyclerView.e(arg3);
                if(v0 != null) {
                    if(!v0.isTmpDetached()) {
                        if(v0.shouldIgnore()) {
                        }
                        else {
                            StringBuilder v4 = new StringBuilder();
                            v4.append("Called attach on a child which is not detached: ");
                            v4.append(v0);
                            v4.append(this.a.a());
                            throw new IllegalArgumentException(v4.toString());
                        }
                    }

                    v0.clearTmpDetachFlag();
                }

                RecyclerView.a(this.a, arg3, arg4, arg5);
            }

            public w b(View arg1) {
                return RecyclerView.e(arg1);
            }

            public View b(int arg2) {
                return this.a.getChildAt(arg2);
            }

            public void b() {
                int v0 = this.a();
                int v1;
                for(v1 = 0; v1 < v0; ++v1) {
                    View v2 = this.b(v1);
                    this.a.m(v2);
                    v2.clearAnimation();
                }

                this.a.removeAllViews();
            }

            public void c(int arg4) {
                View v0 = this.b(arg4);
                if(v0 != null) {
                    w v0_1 = RecyclerView.e(v0);
                    if(v0_1 != null) {
                        if(v0_1.isTmpDetached()) {
                            if(v0_1.shouldIgnore()) {
                            }
                            else {
                                StringBuilder v1 = new StringBuilder();
                                v1.append("called detach on an already detached child ");
                                v1.append(v0_1);
                                v1.append(this.a.a());
                                throw new IllegalArgumentException(v1.toString());
                            }
                        }

                        v0_1.addFlags(256);
                    }
                }

                RecyclerView.a(this.a, arg4);
            }

            public void c(View arg2) {
                w v2 = RecyclerView.e(arg2);
                if(v2 != null) {
                    v2.onEnteredHiddenState(this.a);
                }
            }

            public void d(View arg2) {
                w v2 = RecyclerView.e(arg2);
                if(v2 != null) {
                    v2.onLeftHiddenState(this.a);
                }
            }
        });
    }
}

