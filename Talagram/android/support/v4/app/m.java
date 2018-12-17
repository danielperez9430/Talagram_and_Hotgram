package android.support.v4.app;

import android.animation.Animator$AnimatorListener;
import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.arch.lifecycle.u;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources$NotFoundException;
import android.content.res.TypedArray;
import android.os.Build$VERSION;
import android.os.Bundle;
import android.os.Looper;
import android.os.Parcelable;
import android.support.v4.view.t;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater$Factory2;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation$AnimationListener;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.Transformation;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

final class m extends l implements LayoutInflater$Factory2 {
    class android.support.v4.app.m$1 implements Runnable {
        android.support.v4.app.m$1(m arg1) {
            this.a = arg1;
            super();
        }

        public void run() {
            this.a.g();
        }
    }

    class a extends b {
        View a;

        a(View arg1, Animation$AnimationListener arg2) {
            super(arg2);
            this.a = arg1;
        }

        public void onAnimationEnd(Animation arg4) {
            if((t.D(this.a)) || Build$VERSION.SDK_INT >= 24) {
                this.a.post(new Runnable() {
                    public void run() {
                        this.a.a.setLayerType(0, null);
                    }
                });
            }
            else {
                this.a.setLayerType(0, null);
            }

            super.onAnimationEnd(arg4);
        }
    }

    class b implements Animation$AnimationListener {
        private final Animation$AnimationListener a;

        b(Animation$AnimationListener arg1) {
            super();
            this.a = arg1;
        }

        public void onAnimationEnd(Animation arg2) {
            if(this.a != null) {
                this.a.onAnimationEnd(arg2);
            }
        }

        public void onAnimationRepeat(Animation arg2) {
            if(this.a != null) {
                this.a.onAnimationRepeat(arg2);
            }
        }

        public void onAnimationStart(Animation arg2) {
            if(this.a != null) {
                this.a.onAnimationStart(arg2);
            }
        }
    }

    class c {
        public final Animation a;
        public final Animator b;

        c(Animation arg2) {
            super();
            this.a = arg2;
            this.b = null;
            if(arg2 != null) {
                return;
            }

            throw new IllegalStateException("Animation cannot be null");
        }

        c(Animator arg2) {
            super();
            this.a = null;
            this.b = arg2;
            if(arg2 != null) {
                return;
            }

            throw new IllegalStateException("Animator cannot be null");
        }
    }

    class d extends AnimatorListenerAdapter {
        View a;

        d(View arg1) {
            super();
            this.a = arg1;
        }

        public void onAnimationEnd(Animator arg4) {
            this.a.setLayerType(0, null);
            arg4.removeListener(((Animator$AnimatorListener)this));
        }

        public void onAnimationStart(Animator arg3) {
            this.a.setLayerType(2, null);
        }
    }

    class e extends AnimationSet implements Runnable {
        private final ViewGroup a;
        private final View b;
        private boolean c;
        private boolean d;
        private boolean e;

        e(Animation arg2, ViewGroup arg3, View arg4) {
            super(false);
            this.e = true;
            this.a = arg3;
            this.b = arg4;
            this.addAnimation(arg2);
            this.a.post(((Runnable)this));
        }

        public boolean getTransformation(long arg3, Transformation arg5) {
            this.e = true;
            if(this.c) {
                return this.d ^ 1;
            }

            if(!super.getTransformation(arg3, arg5)) {
                this.c = true;
                aa.a(this.a, ((Runnable)this));
            }

            return 1;
        }

        public boolean getTransformation(long arg3, Transformation arg5, float arg6) {
            this.e = true;
            if(this.c) {
                return this.d ^ 1;
            }

            if(!super.getTransformation(arg3, arg5, arg6)) {
                this.c = true;
                aa.a(this.a, ((Runnable)this));
            }

            return 1;
        }

        public void run() {
            if((this.c) || !this.e) {
                this.a.endViewTransition(this.b);
                this.d = true;
            }
            else {
                this.e = false;
                this.a.post(((Runnable)this));
            }
        }
    }

    final class f {
        final android.support.v4.app.l$a a;
        final boolean b;

        f(android.support.v4.app.l$a arg1, boolean arg2) {
            super();
            this.a = arg1;
            this.b = arg2;
        }
    }

    class g {
        public static final int[] a;

        static {
            g.a = new int[]{16842755, 16842960, 16842961};
        }
    }

    interface h {
        boolean a(ArrayList arg1, ArrayList arg2);
    }

    class i implements h {
        final String a;
        final int b;
        final int c;

        i(m arg1, String arg2, int arg3, int arg4) {
            this.d = arg1;
            super();
            this.a = arg2;
            this.b = arg3;
            this.c = arg4;
        }

        public boolean a(ArrayList arg7, ArrayList arg8) {
            if(this.d.p != null && this.b < 0 && this.a == null) {
                l v0 = this.d.p.peekChildFragmentManager();
                if(v0 != null && (v0.b())) {
                    return 0;
                }
            }

            return this.d.a(arg7, arg8, this.a, this.b, this.c);
        }
    }

    class j implements android.support.v4.app.Fragment$c {
        final boolean a;
        final android.support.v4.app.e b;
        private int c;

        j(android.support.v4.app.e arg1, boolean arg2) {
            super();
            this.a = arg2;
            this.b = arg1;
        }

        public void a() {
            --this.c;
            if(this.c != 0) {
                return;
            }

            this.b.a.f();
        }

        public void b() {
            ++this.c;
        }

        public boolean c() {
            boolean v0 = this.c == 0 ? true : false;
            return v0;
        }

        public void d() {
            int v1 = 0;
            int v0 = this.c > 0 ? 1 : 0;
            m v3 = this.b.a;
            int v4 = v3.e.size();
            while(v1 < v4) {
                Object v5 = v3.e.get(v1);
                ((Fragment)v5).setOnStartEnterTransitionListener(null);
                if(v0 != 0 && (((Fragment)v5).isPostponed())) {
                    ((Fragment)v5).startPostponedEnterTransition();
                }

                ++v1;
            }

            this.b.a.a(this.b, this.a, v0 ^ 1, true);
        }

        public void e() {
            this.b.a.a(this.b, this.a, false, false);
        }
    }

    Bundle A;
    SparseArray B;
    ArrayList C;
    n D;
    Runnable E;
    static final Interpolator F = null;
    static final Interpolator G = null;
    static final Interpolator H = null;
    static final Interpolator I = null;
    private final CopyOnWriteArrayList J;
    static boolean a = false;
    ArrayList b;
    boolean c;
    int d;
    final ArrayList e;
    SparseArray f;
    ArrayList g;
    ArrayList h;
    ArrayList i;
    ArrayList j;
    ArrayList k;
    int l;
    k m;
    android.support.v4.app.i n;
    Fragment o;
    Fragment p;
    static Field q;
    boolean r;
    boolean s;
    boolean t;
    boolean u;
    String v;
    boolean w;
    ArrayList x;
    ArrayList y;
    ArrayList z;

    static {
        m.F = new DecelerateInterpolator(2.5f);
        m.G = new DecelerateInterpolator(1.5f);
        m.H = new AccelerateInterpolator(2.5f);
        m.I = new AccelerateInterpolator(1.5f);
    }

    m() {
        super();
        this.d = 0;
        this.e = new ArrayList();
        this.J = new CopyOnWriteArrayList();
        this.l = 0;
        this.A = null;
        this.B = null;
        this.E = new android.support.v4.app.m$1(this);
    }

    private void A() {
        if(this.C != null) {
            while(!this.C.isEmpty()) {
                this.C.remove(0).d();
            }
        }
    }

    private void B() {
        int v1 = 0;
        int v0 = this.f == null ? 0 : this.f.size();
        while(v1 < v0) {
            Object v4 = this.f.valueAt(v1);
            if(v4 != null) {
                if(((Fragment)v4).getAnimatingAway() != null) {
                    int v5 = ((Fragment)v4).getStateAfterAnimating();
                    View v2 = ((Fragment)v4).getAnimatingAway();
                    Animation v3 = v2.getAnimation();
                    if(v3 != null) {
                        v3.cancel();
                        v2.clearAnimation();
                    }

                    ((Fragment)v4).setAnimatingAway(null);
                    this.a(((Fragment)v4), v5, 0, 0, false);
                }
                else {
                    if(((Fragment)v4).getAnimator() == null) {
                        goto label_32;
                    }

                    ((Fragment)v4).getAnimator().end();
                }
            }

        label_32:
            ++v1;
        }
    }

    private void C() {
        if(this.f != null) {
            int v0;
            for(v0 = this.f.size() - 1; v0 >= 0; --v0) {
                if(this.f.valueAt(v0) == null) {
                    this.f.delete(this.f.keyAt(v0));
                }
            }
        }
    }

    public void a(Configuration arg3) {
        int v0;
        for(v0 = 0; v0 < this.e.size(); ++v0) {
            Object v1 = this.e.get(v0);
            if(v1 != null) {
                ((Fragment)v1).performConfigurationChanged(arg3);
            }
        }
    }

    void a(Parcelable arg13, n arg14) {
        ArrayList v3_2;
        int v14_1;
        Object v11;
        Object v10_1;
        List v4;
        List v3;
        if(arg13 == null) {
            return;
        }

        if(((FragmentManagerState)arg13).a == null) {
            return;
        }

        SparseArray v0 = null;
        if(arg14 != null) {
            List v2 = arg14.a();
            v3 = arg14.b();
            v4 = arg14.c();
            int v5 = v2 != null ? v2.size() : 0;
            int v6;
            for(v6 = 0; v6 < v5; ++v6) {
                Object v7 = v2.get(v6);
                if(m.a) {
                    Log.v("FragmentManager", "restoreAllState: re-attaching retained " + v7);
                }

                int v8;
                for(v8 = 0; v8 < ((FragmentManagerState)arg13).a.length; ++v8) {
                    if(((FragmentManagerState)arg13).a[v8].b == ((Fragment)v7).mIndex) {
                        break;
                    }
                }

                if(v8 == ((FragmentManagerState)arg13).a.length) {
                    StringBuilder v10 = new StringBuilder();
                    v10.append("Could not find active fragment with index ");
                    v10.append(((Fragment)v7).mIndex);
                    this.a(new IllegalStateException(v10.toString()));
                }

                FragmentState v8_1 = ((FragmentManagerState)arg13).a[v8];
                v8_1.l = ((Fragment)v7);
                ((Fragment)v7).mSavedViewState = v0;
                ((Fragment)v7).mBackStackNesting = 0;
                ((Fragment)v7).mInLayout = false;
                ((Fragment)v7).mAdded = false;
                ((Fragment)v7).mTarget = ((Fragment)v0);
                if(v8_1.k != null) {
                    v8_1.k.setClassLoader(this.m.i().getClassLoader());
                    ((Fragment)v7).mSavedViewState = v8_1.k.getSparseParcelableArray("android:view_state");
                    ((Fragment)v7).mSavedFragmentState = v8_1.k;
                }
            }
        }
        else {
            v3 = ((List)v0);
            v4 = v3;
        }

        this.f = new SparseArray(((FragmentManagerState)arg13).a.length);
        int v2_1;
        for(v2_1 = 0; v2_1 < ((FragmentManagerState)arg13).a.length; ++v2_1) {
            FragmentState v5_1 = ((FragmentManagerState)arg13).a[v2_1];
            if(v5_1 != null) {
                if(v3 == null || v2_1 >= v3.size()) {
                    n v10_2 = ((n)v0);
                }
                else {
                    v10_1 = v3.get(v2_1);
                }

                if(v4 == null || v2_1 >= v4.size()) {
                    u v11_1 = ((u)v0);
                }
                else {
                    v11 = v4.get(v2_1);
                }

                Fragment v6_1 = v5_1.a(this.m, this.n, this.o, ((n)v10_1), ((u)v11));
                if(m.a) {
                    Log.v("FragmentManager", "restoreAllState: active #" + v2_1 + ": " + v6_1);
                }

                this.f.put(v6_1.mIndex, v6_1);
                v5_1.l = ((Fragment)v0);
            }
        }

        if(arg14 != null) {
            List v14 = arg14.a();
            v2_1 = v14 != null ? v14.size() : 0;
            int v3_1;
            for(v3_1 = 0; v3_1 < v2_1; ++v3_1) {
                Object v4_1 = v14.get(v3_1);
                if(((Fragment)v4_1).mTargetIndex >= 0) {
                    ((Fragment)v4_1).mTarget = this.f.get(((Fragment)v4_1).mTargetIndex);
                    if(((Fragment)v4_1).mTarget == null) {
                        Log.w("FragmentManager", "Re-attaching retained fragment " + v4_1 + " target no longer exists: " + ((Fragment)v4_1).mTargetIndex);
                    }
                }
            }
        }

        this.e.clear();
        if(((FragmentManagerState)arg13).b != null) {
            v14_1 = 0;
            while(true) {
                if(v14_1 < ((FragmentManagerState)arg13).b.length) {
                    Object v2_2 = this.f.get(((FragmentManagerState)arg13).b[v14_1]);
                    if(v2_2 == null) {
                        StringBuilder v4_2 = new StringBuilder();
                        v4_2.append("No instantiated fragment for index #");
                        v4_2.append(((FragmentManagerState)arg13).b[v14_1]);
                        this.a(new IllegalStateException(v4_2.toString()));
                    }

                    ((Fragment)v2_2).mAdded = true;
                    if(m.a) {
                        Log.v("FragmentManager", "restoreAllState: added #" + v14_1 + ": " + v2_2);
                    }

                    if(this.e.contains(v2_2)) {
                        break;
                    }

                    v3_2 = this.e;
                    __monitor_enter(v3_2);
                    try {
                        this.e.add(v2_2);
                        __monitor_exit(v3_2);
                        ++v14_1;
                        continue;
                    }
                    catch(Throwable v13) {
                        goto label_208;
                    }
                }

                goto label_214;
            }

            throw new IllegalStateException("Already added!");
            try {
            label_208:
                __monitor_exit(v3_2);
            }
            catch(Throwable v13) {
                goto label_208;
            }

            throw v13;
        }

    label_214:
        if(((FragmentManagerState)arg13).c != null) {
            this.g = new ArrayList(((FragmentManagerState)arg13).c.length);
            for(v14_1 = 0; v14_1 < ((FragmentManagerState)arg13).c.length; ++v14_1) {
                android.support.v4.app.e v0_1 = ((FragmentManagerState)arg13).c[v14_1].a(this);
                if(m.a) {
                    Log.v("FragmentManager", "restoreAllState: back stack #" + v14_1 + " (index " + v0_1.m + "): " + v0_1);
                    PrintWriter v3_4 = new PrintWriter(new android.support.v4.f.e("FragmentManager"));
                    v0_1.a("  ", v3_4, false);
                    v3_4.close();
                }

                this.g.add(v0_1);
                if(v0_1.m >= 0) {
                    this.a(v0_1.m, v0_1);
                }
            }
        }
        else {
            this.g = ((ArrayList)v0);
        }

        if(((FragmentManagerState)arg13).d >= 0) {
            this.p = this.f.get(((FragmentManagerState)arg13).d);
        }

        this.d = ((FragmentManagerState)arg13).e;
    }

    public void a(k arg2, android.support.v4.app.i arg3, Fragment arg4) {
        if(this.m == null) {
            this.m = arg2;
            this.n = arg3;
            this.o = arg4;
            return;
        }

        throw new IllegalStateException("Already attached");
    }

    public void a(boolean arg3) {
        int v0;
        for(v0 = this.e.size() - 1; v0 >= 0; --v0) {
            Object v1 = this.e.get(v0);
            if(v1 != null) {
                ((Fragment)v1).performMultiWindowModeChanged(arg3);
            }
        }
    }

    public boolean a(Menu arg5) {
        int v1 = 0;
        if(this.l < 1) {
            return 0;
        }

        boolean v0 = false;
        while(v1 < this.e.size()) {
            Object v3 = this.e.get(v1);
            if(v3 != null && (((Fragment)v3).performPrepareOptionsMenu(arg5))) {
                v0 = true;
            }

            ++v1;
        }

        return v0;
    }

    public boolean a(Menu arg8, MenuInflater arg9) {
        int v1 = 0;
        if(this.l < 1) {
            return 0;
        }

        ArrayList v3 = null;
        int v0 = 0;
        boolean v4 = false;
        while(v0 < this.e.size()) {
            Object v5 = this.e.get(v0);
            if(v5 != null && (((Fragment)v5).performCreateOptionsMenu(arg8, arg9))) {
                if(v3 == null) {
                    v3 = new ArrayList();
                }

                v3.add(v5);
                v4 = true;
            }

            ++v0;
        }

        if(this.h != null) {
            while(v1 < this.h.size()) {
                Object v8 = this.h.get(v1);
                if(v3 == null || !v3.contains(v8)) {
                    ((Fragment)v8).onDestroyOptionsMenu();
                }

                ++v1;
            }
        }

        this.h = v3;
        return v4;
    }

    public boolean a(MenuItem arg5) {
        if(this.l < 1) {
            return 0;
        }

        int v0;
        for(v0 = 0; v0 < this.e.size(); ++v0) {
            Object v3 = this.e.get(v0);
            if(v3 != null && (((Fragment)v3).performOptionsItemSelected(arg5))) {
                return 1;
            }
        }

        return 0;
    }

    void a(Fragment arg15, int arg16, int arg17, int arg18, boolean arg19) {
        String v1_1;
        View v0_2;
        int v0;
        m v6 = this;
        Fragment v7 = arg15;
        boolean v8 = true;
        if(!v7.mAdded || (v7.mDetached)) {
            v0 = arg16;
            if(v0 > 1) {
                v0 = 1;
            }
        }
        else {
            v0 = arg16;
        }

        if((v7.mRemoving) && v0 > v7.mState) {
            if(v7.mState == 0 && (arg15.isInBackStack())) {
                v0 = 1;
                goto label_24;
            }

            v0 = v7.mState;
        }

    label_24:
        int v9 = 3;
        int v10 = 2;
        int v11 = !v7.mDeferStart || v7.mState >= v9 || v0 <= v10 ? v0 : 2;
        View v12 = null;
        if(v7.mState > v11) {
            goto label_330;
        }

        if((v7.mFromLayout) && !v7.mInLayout) {
            return;
        }

        if(arg15.getAnimatingAway() != null || arg15.getAnimator() != null) {
            arg15.setAnimatingAway(v12);
            arg15.setAnimator(((Animator)v12));
            this.a(arg15, arg15.getStateAfterAnimating(), 0, 0, true);
        }

        switch(v7.mState) {
            case 0: {
                goto label_59;
            }
            case 1: {
                goto label_197;
            }
            case 2: {
                goto label_301;
            }
            case 3: {
                goto label_314;
            }
        }

        goto label_468;
    label_59:
        if(v11 > 0) {
            if(m.a) {
                Log.v("FragmentManager", "moveto CREATED: " + arg15);
            }

            if(v7.mSavedFragmentState != null) {
                v7.mSavedFragmentState.setClassLoader(v6.m.i().getClassLoader());
                v7.mSavedViewState = v7.mSavedFragmentState.getSparseParcelableArray("android:view_state");
                v7.mTarget = this.a(v7.mSavedFragmentState, "android:target_state");
                if(v7.mTarget != null) {
                    v7.mTargetRequestCode = v7.mSavedFragmentState.getInt("android:target_req_state", 0);
                }

                if(v7.mSavedUserVisibleHint != null) {
                    v7.mUserVisibleHint = v7.mSavedUserVisibleHint.booleanValue();
                    v7.mSavedUserVisibleHint = ((Boolean)v12);
                }
                else {
                    v7.mUserVisibleHint = v7.mSavedFragmentState.getBoolean("android:user_visible_hint", true);
                }

                if(v7.mUserVisibleHint) {
                    goto label_107;
                }

                v7.mDeferStart = true;
                if(v11 <= v10) {
                    goto label_107;
                }

                v11 = 2;
            }

        label_107:
            v7.mHost = v6.m;
            v7.mParentFragment = v6.o;
            m v0_1 = v6.o != null ? v6.o.mChildFragmentManager : v6.m.k();
            v7.mFragmentManager = v0_1;
            if(v7.mTarget != null) {
                if(v6.f.get(v7.mTarget.mIndex) != v7.mTarget) {
                    v1 = new StringBuilder();
                    v1.append("Fragment ");
                    v1.append(arg15);
                    v1.append(" declared target fragment ");
                    v1.append(v7.mTarget);
                    v1.append(" that does not belong to this FragmentManager!");
                    throw new IllegalStateException(v1.toString());
                }
                else if(v7.mTarget.mState < 1) {
                    this.a(v7.mTarget, 1, 0, 0, true);
                }
            }

            this.a(arg15, v6.m.i(), false);
            v7.mCalled = false;
            arg15.onAttach(v6.m.i());
            if(v7.mCalled) {
                if(v7.mParentFragment == null) {
                    v6.m.b(arg15);
                }
                else {
                    v7.mParentFragment.onAttachFragment(arg15);
                }

                this.b(arg15, v6.m.i(), false);
                if(!v7.mIsCreated) {
                    this.a(arg15, v7.mSavedFragmentState, false);
                    arg15.performCreate(v7.mSavedFragmentState);
                    this.b(arg15, v7.mSavedFragmentState, false);
                }
                else {
                    arg15.restoreChildFragmentState(v7.mSavedFragmentState);
                    v7.mState = 1;
                }

                v7.mRetaining = false;
            }
            else {
                v1 = new StringBuilder();
                v1.append("Fragment ");
                v1.append(arg15);
                v1.append(" did not call through to super.onAttach()");
                throw new af(v1.toString());
            }
        }

    label_197:
        this.c(arg15);
        if(v11 > 1) {
            if(m.a) {
                Log.v("FragmentManager", "moveto ACTIVITY_CREATED: " + arg15);
            }

            if(!v7.mFromLayout) {
                if(v7.mContainerId != 0) {
                    if(v7.mContainerId == -1) {
                        v1 = new StringBuilder();
                        v1.append("Cannot create fragment ");
                        v1.append(arg15);
                        v1.append(" for a container view with no id");
                        this.a(new IllegalArgumentException(v1.toString()));
                    }

                    v0_2 = v6.n.a(v7.mContainerId);
                    if(v0_2 != null) {
                        goto label_257;
                    }

                    if(v7.mRestored) {
                        goto label_257;
                    }

                    try {
                        v1_1 = arg15.getResources().getResourceName(v7.mContainerId);
                    }
                    catch(Resources$NotFoundException ) {
                        v1_1 = "unknown";
                    }

                    StringBuilder v3 = new StringBuilder();
                    v3.append("No view found for id 0x");
                    v3.append(Integer.toHexString(v7.mContainerId));
                    v3.append(" (");
                    v3.append(v1_1);
                    v3.append(") for fragment ");
                    v3.append(arg15);
                    this.a(new IllegalArgumentException(v3.toString()));
                }
                else {
                    ViewGroup v0_3 = ((ViewGroup)v12);
                }

            label_257:
                v7.mContainer = ((ViewGroup)v0_2);
                arg15.performCreateView(arg15.performGetLayoutInflater(v7.mSavedFragmentState), ((ViewGroup)v0_2), v7.mSavedFragmentState);
                if(v7.mView != null) {
                    v7.mInnerView = v7.mView;
                    v7.mView.setSaveFromParentEnabled(false);
                    if(v0_2 != null) {
                        ((ViewGroup)v0_2).addView(v7.mView);
                    }

                    if(v7.mHidden) {
                        v7.mView.setVisibility(8);
                    }

                    arg15.onViewCreated(v7.mView, v7.mSavedFragmentState);
                    this.a(arg15, v7.mView, v7.mSavedFragmentState, false);
                    if(v7.mView.getVisibility() != 0 || v7.mContainer == null) {
                        v8 = false;
                    }
                    else {
                    }

                    v7.mIsNewlyAdded = v8;
                }
                else {
                    v7.mInnerView = v12;
                }
            }

            arg15.performActivityCreated(v7.mSavedFragmentState);
            this.c(arg15, v7.mSavedFragmentState, false);
            if(v7.mView != null) {
                arg15.restoreViewState(v7.mSavedFragmentState);
            }

            v7.mSavedFragmentState = ((Bundle)v12);
        }

    label_301:
        if(v11 > v10) {
            if(m.a) {
                Log.v("FragmentManager", "moveto STARTED: " + arg15);
            }

            arg15.performStart();
            this.b(arg15, false);
        }

    label_314:
        if(v11 <= v9) {
            goto label_468;
        }

        if(m.a) {
            Log.v("FragmentManager", "moveto RESUMED: " + arg15);
        }

        arg15.performResume();
        this.c(arg15, false);
        v7.mSavedFragmentState = ((Bundle)v12);
        v7.mSavedViewState = ((SparseArray)v12);
        goto label_468;
    label_330:
        if(v7.mState <= v11) {
            goto label_468;
        }

        switch(v7.mState) {
            case 1: {
                goto label_420;
            }
            case 2: {
                goto label_362;
            }
            case 3: {
                goto label_349;
            }
            case 4: {
                goto label_335;
            }
        }

        goto label_468;
    label_335:
        if(v11 < 4) {
            if(m.a) {
                Log.v("FragmentManager", "movefrom RESUMED: " + arg15);
            }

            arg15.performPause();
            this.d(arg15, false);
        }

    label_349:
        if(v11 < v9) {
            if(m.a) {
                Log.v("FragmentManager", "movefrom STARTED: " + arg15);
            }

            arg15.performStop();
            this.e(arg15, false);
        }

    label_362:
        if(v11 < v10) {
            if(m.a) {
                Log.v("FragmentManager", "movefrom ACTIVITY_CREATED: " + arg15);
            }

            if(v7.mView != null && (v6.m.a(arg15)) && v7.mSavedViewState == null) {
                this.m(arg15);
            }

            arg15.performDestroyView();
            this.f(arg15, false);
            if(v7.mView != null && v7.mContainer != null) {
                v7.mContainer.endViewTransition(v7.mView);
                v7.mView.clearAnimation();
                c v0_4 = v6.l <= 0 || (v6.u) || v7.mView.getVisibility() != 0 || v7.mPostponedAlpha < 0f ? ((c)v12) : this.a(arg15, arg17, false, arg18);
                v7.mPostponedAlpha = 0f;
                if(v0_4 != null) {
                    this.a(arg15, v0_4, v11);
                }

                v7.mContainer.removeView(v7.mView);
            }

            v7.mContainer = ((ViewGroup)v12);
            v7.mView = v12;
            v7.mViewLifecycleOwner = ((android.arch.lifecycle.g)v12);
            v7.mViewLifecycleOwnerLiveData.a(v12);
            v7.mInnerView = v12;
            v7.mInLayout = false;
        }

    label_420:
        if(v11 < 1) {
            if(v6.u) {
                if(arg15.getAnimatingAway() != null) {
                    v0_2 = arg15.getAnimatingAway();
                    arg15.setAnimatingAway(v12);
                    v0_2.clearAnimation();
                }
                else if(arg15.getAnimator() != null) {
                    Animator v0_5 = arg15.getAnimator();
                    arg15.setAnimator(((Animator)v12));
                    v0_5.cancel();
                }
            }

            if(arg15.getAnimatingAway() == null) {
                if(arg15.getAnimator() != null) {
                }
                else {
                    if(m.a) {
                        Log.v("FragmentManager", "movefrom CREATED: " + arg15);
                    }

                    if(!v7.mRetaining) {
                        arg15.performDestroy();
                        this.g(arg15, false);
                    }
                    else {
                        v7.mState = 0;
                    }

                    arg15.performDetach();
                    this.h(arg15, false);
                    if(arg19) {
                        goto label_468;
                    }

                    if(!v7.mRetaining) {
                        this.g(arg15);
                        goto label_468;
                    }

                    v7.mHost = ((k)v12);
                    v7.mParentFragment = ((Fragment)v12);
                    v7.mFragmentManager = ((m)v12);
                    goto label_468;
                }
            }

            arg15.setStateAfterAnimating(v11);
        }
        else {
        label_468:
            int v8_1 = v11;
        }

        if(v7.mState != (((int)v8))) {
            Log.w("FragmentManager", "moveToState: Fragment state for " + arg15 + " not updated inline; " + "expected state " + (((int)v8)) + " found " + v7.mState);
            v7.mState = ((int)v8);
        }
    }

    public void a(String arg7, FileDescriptor arg8, PrintWriter arg9, String[] arg10) {
        int v8;
        Object v4;
        int v1;
        String v0_1 = arg7 + "    ";
        int v2 = 0;
        if(this.f != null) {
            v1 = this.f.size();
            if(v1 > 0) {
                arg9.print(arg7);
                arg9.print("Active Fragments in ");
                arg9.print(Integer.toHexString(System.identityHashCode(this)));
                arg9.println(":");
                int v3;
                for(v3 = 0; v3 < v1; ++v3) {
                    v4 = this.f.valueAt(v3);
                    arg9.print(arg7);
                    arg9.print("  #");
                    arg9.print(v3);
                    arg9.print(": ");
                    arg9.println(v4);
                    if(v4 != null) {
                        ((Fragment)v4).dump(v0_1, arg8, arg9, arg10);
                    }
                }
            }
        }

        v1 = this.e.size();
        if(v1 > 0) {
            arg9.print(arg7);
            arg9.println("Added Fragments:");
            for(v3 = 0; v3 < v1; ++v3) {
                v4 = this.e.get(v3);
                arg9.print(arg7);
                arg9.print("  #");
                arg9.print(v3);
                arg9.print(": ");
                arg9.println(((Fragment)v4).toString());
            }
        }

        if(this.h != null) {
            v1 = this.h.size();
            if(v1 > 0) {
                arg9.print(arg7);
                arg9.println("Fragments Created Menus:");
                for(v3 = 0; v3 < v1; ++v3) {
                    v4 = this.h.get(v3);
                    arg9.print(arg7);
                    arg9.print("  #");
                    arg9.print(v3);
                    arg9.print(": ");
                    arg9.println(((Fragment)v4).toString());
                }
            }
        }

        if(this.g != null) {
            v1 = this.g.size();
            if(v1 > 0) {
                arg9.print(arg7);
                arg9.println("Back Stack:");
                for(v3 = 0; v3 < v1; ++v3) {
                    v4 = this.g.get(v3);
                    arg9.print(arg7);
                    arg9.print("  #");
                    arg9.print(v3);
                    arg9.print(": ");
                    arg9.println(((android.support.v4.app.e)v4).toString());
                    ((android.support.v4.app.e)v4).a(v0_1, arg8, arg9, arg10);
                }
            }
        }

        __monitor_enter(this);
        try {
            if(this.i != null) {
                v8 = this.i.size();
                if(v8 > 0) {
                    arg9.print(arg7);
                    arg9.println("Back Stack Indices:");
                    int v10;
                    for(v10 = 0; v10 < v8; ++v10) {
                        Object v0_2 = this.i.get(v10);
                        arg9.print(arg7);
                        arg9.print("  #");
                        arg9.print(v10);
                        arg9.print(": ");
                        arg9.println(v0_2);
                    }
                }
            }

            if(this.j != null && this.j.size() > 0) {
                arg9.print(arg7);
                arg9.print("mAvailBackStackIndices: ");
                arg9.println(Arrays.toString(this.j.toArray()));
            }

            __monitor_exit(this);
        }
        catch(Throwable v7) {
            try {
            label_208:
                __monitor_exit(this);
            }
            catch(Throwable v7) {
                goto label_208;
            }

            throw v7;
        }

        if(this.b != null) {
            v8 = this.b.size();
            if(v8 > 0) {
                arg9.print(arg7);
                arg9.println("Pending Actions:");
                while(v2 < v8) {
                    Object v10_1 = this.b.get(v2);
                    arg9.print(arg7);
                    arg9.print("  #");
                    arg9.print(v2);
                    arg9.print(": ");
                    arg9.println(v10_1);
                    ++v2;
                }
            }
        }

        arg9.print(arg7);
        arg9.println("FragmentManager misc state:");
        arg9.print(arg7);
        arg9.print("  mHost=");
        arg9.println(this.m);
        arg9.print(arg7);
        arg9.print("  mContainer=");
        arg9.println(this.n);
        if(this.o != null) {
            arg9.print(arg7);
            arg9.print("  mParent=");
            arg9.println(this.o);
        }

        arg9.print(arg7);
        arg9.print("  mCurState=");
        arg9.print(this.l);
        arg9.print(" mStateSaved=");
        arg9.print(this.s);
        arg9.print(" mStopped=");
        arg9.print(this.t);
        arg9.print(" mDestroyed=");
        arg9.println(this.u);
        if(this.r) {
            arg9.print(arg7);
            arg9.print("  mNeedMenuInvalidate=");
            arg9.println(this.r);
        }

        if(this.v != null) {
            arg9.print(arg7);
            arg9.print("  mNoTransactionsBecause=");
            arg9.println(this.v);
        }
    }

    boolean a(int arg2) {
        boolean v2 = this.l >= arg2 ? true : false;
        return v2;
    }

    public void a(Fragment arg8) {
        if(arg8.mDeferStart) {
            if(this.c) {
                this.w = true;
                return;
            }
            else {
                arg8.mDeferStart = false;
                this.a(arg8, this.l, 0, 0, false);
            }
        }
    }

    public int a(android.support.v4.app.e arg5) {
        int v0;
        __monitor_enter(this);
        try {
            if(this.j != null) {
                if(this.j.size() <= 0) {
                }
                else {
                    v0 = this.j.remove(this.j.size() - 1).intValue();
                    if(m.a) {
                        Log.v("FragmentManager", "Adding back stack index " + v0 + " with " + arg5);
                    }

                    this.i.set(v0, arg5);
                    __monitor_exit(this);
                    return v0;
                }
            }

            if(this.i == null) {
                this.i = new ArrayList();
            }

            v0 = this.i.size();
            if(m.a) {
                Log.v("FragmentManager", "Setting back stack index " + v0 + " to " + arg5);
            }

            this.i.add(arg5);
            __monitor_exit(this);
            return v0;
        label_55:
            __monitor_exit(this);
        }
        catch(Throwable v5) {
            goto label_55;
        }

        throw v5;
    }

    public void a(h arg2, boolean arg3) {
        if(!arg3) {
            this.y();
        }

        __monitor_enter(this);
        try {
            if(!this.u) {
                if(this.m == null) {
                }
                else {
                    if(this.b == null) {
                        this.b = new ArrayList();
                    }

                    this.b.add(arg2);
                    this.f();
                    __monitor_exit(this);
                    return;
                }
            }

            if(arg3) {
                __monitor_exit(this);
                return;
            }

            throw new IllegalStateException("Activity has been destroyed");
        label_26:
            __monitor_exit(this);
        }
        catch(Throwable v2) {
            goto label_26;
        }

        throw v2;
    }

    public void a(Fragment arg4, boolean arg5) {
        if(m.a) {
            Log.v("FragmentManager", "add: " + arg4);
        }

        this.f(arg4);
        if(!arg4.mDetached) {
            if(!this.e.contains(arg4)) {
                ArrayList v0 = this.e;
                __monitor_enter(v0);
                try {
                    this.e.add(arg4);
                    __monitor_exit(v0);
                }
                catch(Throwable v4) {
                    try {
                    label_37:
                        __monitor_exit(v0);
                    }
                    catch(Throwable v4) {
                        goto label_37;
                    }

                    throw v4;
                }

                arg4.mAdded = true;
                arg4.mRemoving = false;
                if(arg4.mView == null) {
                    arg4.mHiddenChanged = false;
                }

                if((arg4.mHasMenu) && (arg4.mMenuVisible)) {
                    this.r = true;
                }

                if(!arg5) {
                    return;
                }

                this.b(arg4);
            }
            else {
                StringBuilder v0_1 = new StringBuilder();
                v0_1.append("Fragment already added: ");
                v0_1.append(arg4);
                throw new IllegalStateException(v0_1.toString());
            }
        }
    }

    void a(int arg4, boolean arg5) {
        if(this.m == null) {
            if(arg4 == 0) {
            }
            else {
                throw new IllegalStateException("No activity");
            }
        }

        if(!arg5 && arg4 == this.l) {
            return;
        }

        this.l = arg4;
        if(this.f != null) {
            arg4 = this.e.size();
            int v0;
            for(v0 = 0; v0 < arg4; ++v0) {
                this.e(this.e.get(v0));
            }

            arg4 = this.f.size();
            for(v0 = 0; v0 < arg4; ++v0) {
                Object v1 = this.f.valueAt(v0);
                if(v1 != null && ((((Fragment)v1).mRemoving) || (((Fragment)v1).mDetached)) && !((Fragment)v1).mIsNewlyAdded) {
                    this.e(((Fragment)v1));
                }
            }

            this.e();
            if(!this.r) {
                return;
            }

            if(this.m == null) {
                return;
            }

            if(this.l != 4) {
                return;
            }

            this.m.d();
            this.r = false;
        }
    }

    private int a(ArrayList arg8, ArrayList arg9, int arg10, int arg11, android.support.v4.f.b arg12) {
        int v0 = arg11 - 1;
        int v1 = arg11;
        while(v0 >= arg10) {
            Object v2 = arg8.get(v0);
            boolean v3 = arg9.get(v0).booleanValue();
            int v4 = !((android.support.v4.app.e)v2).g() || (((android.support.v4.app.e)v2).a(arg8, v0 + 1, arg11)) ? 0 : 1;
            if(v4 != 0) {
                if(this.C == null) {
                    this.C = new ArrayList();
                }

                j v4_1 = new j(((android.support.v4.app.e)v2), v3);
                this.C.add(v4_1);
                ((android.support.v4.app.e)v2).a(((android.support.v4.app.Fragment$c)v4_1));
                if(v3) {
                    ((android.support.v4.app.e)v2).f();
                }
                else {
                    ((android.support.v4.app.e)v2).b(false);
                }

                --v1;
                if(v0 != v1) {
                    arg8.remove(v0);
                    arg8.add(v1, v2);
                }

                this.b(arg12);
            }

            --v0;
        }

        return v1;
    }

    static c a(Context arg0, float arg1, float arg2) {
        AlphaAnimation v0 = new AlphaAnimation(arg1, arg2);
        v0.setInterpolator(m.G);
        v0.setDuration(220);
        return new c(((Animation)v0));
    }

    static c a(Context arg10, float arg11, float arg12, float arg13, float arg14) {
        AnimationSet v10 = new AnimationSet(false);
        ScaleAnimation v0 = new ScaleAnimation(arg11, arg12, arg11, arg12, 1, 0.5f, 1, 0.5f);
        v0.setInterpolator(m.F);
        v0.setDuration(220);
        v10.addAnimation(((Animation)v0));
        AlphaAnimation v0_1 = new AlphaAnimation(arg13, arg14);
        v0_1.setInterpolator(m.G);
        v0_1.setDuration(220);
        v10.addAnimation(((Animation)v0_1));
        return new c(((Animation)v10));
    }

    private static Animation$AnimationListener a(Animation arg2) {
        String v1;
        String v0;
        Object v2_2;
        try {
            if(m.q == null) {
                m.q = Animation.class.getDeclaredField("mListener");
                m.q.setAccessible(true);
            }

            v2_2 = m.q.get(arg2);
            goto label_21;
        }
        catch(IllegalAccessException v2) {
            v0 = "FragmentManager";
            v1 = "Cannot access Animation\'s mListener field";
        }
        catch(NoSuchFieldException v2_1) {
            v0 = "FragmentManager";
            v1 = "No field with the name mListener is found in Animation class";
        }

        Log.e(v0, v1, ((Throwable)v2));
        Animation$AnimationListener v2_3 = null;
    label_21:
        return ((Animation$AnimationListener)v2_2);
    }

    private void a(Fragment arg5, c arg6, int arg7) {
        View v0 = arg5.mView;
        ViewGroup v1 = arg5.mContainer;
        v1.startViewTransition(v0);
        arg5.setStateAfterAnimating(arg7);
        if(arg6.a != null) {
            e v7 = new e(arg6.a, v1, v0);
            arg5.setAnimatingAway(arg5.mView);
            ((Animation)v7).setAnimationListener(new b(m.a(((Animation)v7)), v1, arg5) {
                public void onAnimationEnd(Animation arg2) {
                    super.onAnimationEnd(arg2);
                    this.a.post(new Runnable() {
                        public void run() {
                            if(this.a.b.getAnimatingAway() != null) {
                                this.a.b.setAnimatingAway(null);
                                this.a.c.a(this.a.b, this.a.b.getStateAfterAnimating(), 0, 0, false);
                            }
                        }
                    });
                }
            });
            m.b(v0, arg6);
            arg5.mView.startAnimation(((Animation)v7));
        }
        else {
            Animator v7_1 = arg6.b;
            arg5.setAnimator(arg6.b);
            v7_1.addListener(new AnimatorListenerAdapter(v1, v0, arg5) {
                public void onAnimationEnd(Animator arg7) {
                    this.a.endViewTransition(this.b);
                    arg7 = this.c.getAnimator();
                    this.c.setAnimator(null);
                    if(arg7 != null && this.a.indexOfChild(this.b) < 0) {
                        this.d.a(this.c, this.c.getStateAfterAnimating(), 0, 0, false);
                    }
                }
            });
            v7_1.setTarget(arg5.mView);
            m.b(arg5.mView, arg6);
            v7_1.start();
        }
    }

    private static void a(n arg3) {
        if(arg3 == null) {
            return;
        }

        List v0 = arg3.a();
        if(v0 != null) {
            Iterator v0_1 = v0.iterator();
            while(v0_1.hasNext()) {
                v0_1.next().mRetaining = true;
            }
        }

        List v3 = arg3.b();
        if(v3 != null) {
            Iterator v3_1 = v3.iterator();
            while(v3_1.hasNext()) {
                m.a(v3_1.next());
            }
        }
    }

    private void a(android.support.v4.f.b arg6) {
        int v0 = arg6.size();
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            Object v2 = arg6.b(v1);
            if(!((Fragment)v2).mAdded) {
                View v3 = ((Fragment)v2).getView();
                ((Fragment)v2).mPostponedAlpha = v3.getAlpha();
                v3.setAlpha(0f);
            }
        }
    }

    private void a(RuntimeException arg6) {
        Log.e("FragmentManager", arg6.getMessage());
        Log.e("FragmentManager", "Activity state:");
        PrintWriter v1 = new PrintWriter(new android.support.v4.f.e("FragmentManager"));
        FileDescriptor v3 = null;
        if(this.m == null) {
            goto label_25;
        }

        try {
            this.m.a("  ", v3, v1, new String[0]);
            goto label_28;
        label_25:
            this.a("  ", v3, v1, new String[0]);
        }
        catch(Exception v0) {
            Log.e("FragmentManager", "Failed dumping state", ((Throwable)v0));
        }

    label_28:
        throw arg6;
    }

    private void a(ArrayList arg8, ArrayList arg9) {
        int v5;
        int v0 = this.C == null ? 0 : this.C.size();
        int v2 = v0;
        for(v0 = 0; v0 < v2; ++v0) {
            Object v3 = this.C.get(v0);
            int v4 = -1;
            if(arg8 == null || (((j)v3).a)) {
            label_24:
                if(!((j)v3).c()) {
                    if(arg8 == null) {
                    }
                    else if(((j)v3).b.a(arg8, 0, arg8.size())) {
                        goto label_31;
                    }

                    goto label_46;
                }

            label_31:
                this.C.remove(v0);
                --v0;
                --v2;
                if(arg8 != null && !((j)v3).a) {
                    v5 = arg8.indexOf(((j)v3).b);
                    if(v5 != v4 && (arg9.get(v5).booleanValue())) {
                    label_22:
                        ((j)v3).e();
                        goto label_46;
                    }
                }

                ((j)v3).d();
            }
            else {
                v5 = arg8.indexOf(((j)v3).b);
                if(v5 == v4) {
                    goto label_24;
                }
                else if(!arg9.get(v5).booleanValue()) {
                    goto label_24;
                }
                else {
                    goto label_22;
                }
            }

        label_46:
        }
    }

    private void a(ArrayList arg16, ArrayList arg17, int arg18, int arg19) {
        int v4;
        m v6 = this;
        ArrayList v7 = arg16;
        ArrayList v8 = arg17;
        int v9 = arg18;
        int v10 = arg19;
        boolean v11 = v7.get(v9).t;
        if(v6.z == null) {
            v6.z = new ArrayList();
        }
        else {
            v6.z.clear();
        }

        v6.z.addAll(v6.e);
        Fragment v2 = this.w();
        int v0 = v9;
        int v12 = 0;
        while(v0 < v10) {
            Object v3 = v7.get(v0);
            v2 = !v8.get(v0).booleanValue() ? ((android.support.v4.app.e)v3).a(v6.z, v2) : ((android.support.v4.app.e)v3).b(v6.z, v2);
            v12 = v12 != 0 || (((android.support.v4.app.e)v3).i) ? 1 : 0;
            ++v0;
        }

        v6.z.clear();
        if(!v11) {
            p.a(this, arg16, arg17, arg18, arg19, false);
        }

        m.b(arg16, arg17, arg18, arg19);
        if(v11) {
            android.support.v4.f.b v14 = new android.support.v4.f.b();
            this.b(v14);
            v0 = this.a(arg16, arg17, arg18, arg19, v14);
            this.a(v14);
            v4 = v0;
        }
        else {
            v4 = v10;
        }

        if(v4 != v9 && (v11)) {
            p.a(this, arg16, arg17, arg18, v4, true);
            this.a(v6.l, true);
        }

        while(v9 < v10) {
            Object v0_1 = v7.get(v9);
            if((v8.get(v9).booleanValue()) && ((android.support.v4.app.e)v0_1).m >= 0) {
                this.c(((android.support.v4.app.e)v0_1).m);
                ((android.support.v4.app.e)v0_1).m = -1;
            }

            ((android.support.v4.app.e)v0_1).b();
            ++v9;
        }

        if(v12 != 0) {
            this.i();
        }
    }

    static boolean a(Animator arg5) {
        int v1;
        if(arg5 == null) {
            return 0;
        }

        if((arg5 instanceof ValueAnimator)) {
            PropertyValuesHolder[] v5 = ((ValueAnimator)arg5).getValues();
            v1 = 0;
            while(v1 < v5.length) {
                if("alpha".equals(v5[v1].getPropertyName())) {
                    return 1;
                }
                else {
                    ++v1;
                    continue;
                }

                return 0;
            }
        }
        else if((arg5 instanceof AnimatorSet)) {
            ArrayList v5_1 = ((AnimatorSet)arg5).getChildAnimations();
            v1 = 0;
            while(v1 < ((List)v5_1).size()) {
                if(m.a(((List)v5_1).get(v1))) {
                    return 1;
                }
                else {
                    ++v1;
                    continue;
                }

                return 0;
            }
        }

        return 0;
    }

    static boolean a(c arg4) {
        if((arg4.a instanceof AlphaAnimation)) {
            return 1;
        }

        if((arg4.a instanceof AnimationSet)) {
            List v4 = arg4.a.getAnimations();
            int v2;
            for(v2 = 0; v2 < v4.size(); ++v2) {
                if((v4.get(v2) instanceof AlphaAnimation)) {
                    return 1;
                }
            }

            return 0;
        }

        return m.a(arg4.b);
    }

    static boolean a(View arg3, c arg4) {
        boolean v0 = false;
        if(arg3 != null) {
            if(arg4 == null) {
            }
            else if(Build$VERSION.SDK_INT >= 19 && arg3.getLayerType() == 0 && (t.u(arg3)) && (m.a(arg4))) {
                v0 = true;
            }
        }

        return v0;
    }

    private boolean a(String arg9, int arg10, int arg11) {
        this.g();
        this.c(true);
        if(this.p != null && arg10 < 0 && arg9 == null) {
            l v1 = this.p.peekChildFragmentManager();
            if(v1 != null && (v1.b())) {
                return 1;
            }
        }

        boolean v9 = this.a(this.x, this.y, arg9, arg10, arg11);
        if(v9) {
            this.c = true;
            try {
                this.b(this.x, this.y);
            }
            catch(Throwable v9_1) {
                this.z();
                throw v9_1;
            }

            this.z();
        }

        this.h();
        this.C();
        return v9;
    }

    boolean a(ArrayList arg6, ArrayList arg7, String arg8, int arg9, int arg10) {
        int v8;
        int v2;
        if(this.g == null) {
            return 0;
        }

        if(arg8 != null || arg9 >= 0 || (arg10 & 1) != 0) {
            if(arg8 != null || arg9 >= 0) {
                for(v2 = this.g.size() - 1; v2 >= 0; --v2) {
                    Object v3 = this.g.get(v2);
                    if(arg8 != null && (arg8.equals(((android.support.v4.app.e)v3).h()))) {
                        break;
                    }

                    if(arg9 >= 0 && arg9 == ((android.support.v4.app.e)v3).m) {
                        break;
                    }
                }

                if(v2 < 0) {
                    return 0;
                }

                if((arg10 & 1) == 0) {
                    goto label_59;
                }

                while(true) {
                    --v2;
                    if(v2 < 0) {
                        break;
                    }

                    Object v10 = this.g.get(v2);
                    if(arg8 != null && (arg8.equals(((android.support.v4.app.e)v10).h()))) {
                        continue;
                    }

                    if(arg9 < 0) {
                        break;
                    }

                    if(arg9 != ((android.support.v4.app.e)v10).m) {
                        break;
                    }
                }
            }
            else {
                v2 = -1;
            }

        label_59:
            if(v2 == this.g.size() - 1) {
                return 0;
            }

            for(v8 = this.g.size() - 1; v8 > v2; --v8) {
                arg6.add(this.g.remove(v8));
                arg7.add(Boolean.valueOf(true));
            }
        }
        else {
            v8 = this.g.size() - 1;
            if(v8 < 0) {
                return 0;
            }
            else {
                arg6.add(this.g.remove(v8));
                arg7.add(Boolean.valueOf(true));
            }
        }

        return 1;
    }

    public Fragment a(Bundle arg5, String arg6) {
        int v5 = arg5.getInt(arg6, -1);
        if(v5 == -1) {
            return null;
        }

        Object v0 = this.f.get(v5);
        if(v0 == null) {
            StringBuilder v2 = new StringBuilder();
            v2.append("Fragment no longer exists for key ");
            v2.append(arg6);
            v2.append(": index ");
            v2.append(v5);
            this.a(new IllegalStateException(v2.toString()));
        }

        return ((Fragment)v0);
    }

    public Fragment a(String arg4) {
        Object v1;
        if(arg4 != null) {
            int v0;
            for(v0 = this.e.size() - 1; v0 >= 0; --v0) {
                v1 = this.e.get(v0);
                if(v1 != null && (arg4.equals(((Fragment)v1).mTag))) {
                    return ((Fragment)v1);
                }
            }
        }

        if(this.f != null && arg4 != null) {
            for(v0 = this.f.size() - 1; v0 >= 0; --v0) {
                v1 = this.f.valueAt(v0);
                if(v1 != null && (arg4.equals(((Fragment)v1).mTag))) {
                    return ((Fragment)v1);
                }
            }
        }

        return null;
    }

    c a(Fragment arg5, int arg6, boolean arg7, int arg8) {
        int v0 = arg5.getNextAnim();
        Animation v1 = arg5.onCreateAnimation(arg6, arg7, v0);
        if(v1 != null) {
            return new c(v1);
        }

        Animator v5 = arg5.onCreateAnimator(arg6, arg7, v0);
        if(v5 != null) {
            return new c(v5);
        }

        if(v0 != 0) {
            boolean v5_1 = "anim".equals(this.m.i().getResources().getResourceTypeName(v0));
            int v1_1 = 0;
            if(v5_1) {
                try {
                    Animation v2 = AnimationUtils.loadAnimation(this.m.i(), v0);
                    if(v2 != null) {
                        return new c(v2);
                    }
                    else {
                        goto label_27;
                    }

                    goto label_31;
                }
                catch(RuntimeException ) {
                label_31:
                    if(v1_1 != 0) {
                        goto label_49;
                    }

                    try {
                        Animator v1_3 = AnimatorInflater.loadAnimator(this.m.i(), v0);
                        if(v1_3 == null) {
                            goto label_49;
                        }

                        return new c(v1_3);
                    }
                    catch(RuntimeException v1_2) {
                        if(!v5_1) {
                            Animation v5_3 = AnimationUtils.loadAnimation(this.m.i(), v0);
                            if(v5_3 == null) {
                                goto label_49;
                            }

                            return new c(v5_3);
                        }

                        throw v1_2;
                    }
                }
                catch(Resources$NotFoundException v5_2) {
                    throw v5_2;
                label_27:
                    v1_1 = 1;
                    goto label_31;
                }
            }

            goto label_31;
        }

    label_49:
        c v5_4 = null;
        if(arg6 == 0) {
            return v5_4;
        }

        arg6 = m.b(arg6, arg7);
        if(arg6 < 0) {
            return v5_4;
        }

        float v7 = 0.975f;
        float v1_4 = 1f;
        switch(arg6) {
            case 1: {
                goto label_87;
            }
            case 2: {
                goto label_83;
            }
            case 3: {
                goto label_79;
            }
            case 4: {
                goto label_74;
            }
            case 5: {
                goto label_70;
            }
            case 6: {
                goto label_66;
            }
        }

        if(arg8 == 0 && (this.m.e())) {
            this.m.f();
        }

        return v5_4;
    label_66:
        return m.a(this.m.i(), v1_4, 0f);
    label_83:
        return m.a(this.m.i(), v1_4, v7, v1_4, 0f);
    label_70:
        return m.a(this.m.i(), 0f, v1_4);
    label_87:
        return m.a(this.m.i(), 1.125f, v1_4, 0f, v1_4);
    label_74:
        return m.a(this.m.i(), v1_4, 1.075f, v1_4, 0f);
    label_79:
        return m.a(this.m.i(), v7, v1_4, 0f, v1_4);
    }

    public o a() {
        return new android.support.v4.app.e(this);
    }

    public void a(int arg3, int arg4) {
        if(arg3 >= 0) {
            this.a(new i(this, null, arg3, arg4), false);
            return;
        }

        StringBuilder v0 = new StringBuilder();
        v0.append("Bad id: ");
        v0.append(arg3);
        throw new IllegalArgumentException(v0.toString());
    }

    public void a(int arg5, android.support.v4.app.e arg6) {
        __monitor_enter(this);
        try {
            if(this.i == null) {
                this.i = new ArrayList();
            }

            int v0 = this.i.size();
            if(arg5 < v0) {
                if(m.a) {
                    Log.v("FragmentManager", "Setting back stack index " + arg5 + " to " + arg6);
                }

                this.i.set(arg5, arg6);
            }
            else {
                while(v0 < arg5) {
                    this.i.add(null);
                    if(this.j == null) {
                        this.j = new ArrayList();
                    }

                    if(m.a) {
                        Log.v("FragmentManager", "Adding available back stack index " + v0);
                    }

                    this.j.add(Integer.valueOf(v0));
                    ++v0;
                }

                if(m.a) {
                    Log.v("FragmentManager", "Adding back stack index " + arg5 + " with " + arg6);
                }

                this.i.add(arg6);
            }

            __monitor_exit(this);
            return;
        label_67:
            __monitor_exit(this);
        }
        catch(Throwable v5) {
            goto label_67;
        }

        throw v5;
    }

    public void a(Bundle arg4, String arg5, Fragment arg6) {
        if(arg6.mIndex < 0) {
            StringBuilder v1 = new StringBuilder();
            v1.append("Fragment ");
            v1.append(arg6);
            v1.append(" is not currently in the FragmentManager");
            this.a(new IllegalStateException(v1.toString()));
        }

        arg4.putInt(arg5, arg6.mIndex);
    }

    void a(Fragment arg4, Context arg5, boolean arg6) {
        if(this.o != null) {
            l v0 = this.o.getFragmentManager();
            if((v0 instanceof m)) {
                ((m)v0).a(arg4, arg5, true);
            }
        }

        Iterator v0_1 = this.J.iterator();
        while(v0_1.hasNext()) {
            Object v1 = v0_1.next();
            if((arg6) && !((f)v1).b) {
                continue;
            }

            ((f)v1).a.a(((l)this), arg4, arg5);
        }
    }

    void a(Fragment arg4, Bundle arg5, boolean arg6) {
        if(this.o != null) {
            l v0 = this.o.getFragmentManager();
            if((v0 instanceof m)) {
                ((m)v0).a(arg4, arg5, true);
            }
        }

        Iterator v0_1 = this.J.iterator();
        while(v0_1.hasNext()) {
            Object v1 = v0_1.next();
            if((arg6) && !((f)v1).b) {
                continue;
            }

            ((f)v1).a.b(((l)this), arg4, arg5);
        }
    }

    void a(Fragment arg4, View arg5, Bundle arg6, boolean arg7) {
        if(this.o != null) {
            l v0 = this.o.getFragmentManager();
            if((v0 instanceof m)) {
                ((m)v0).a(arg4, arg5, arg6, true);
            }
        }

        Iterator v0_1 = this.J.iterator();
        while(v0_1.hasNext()) {
            Object v1 = v0_1.next();
            if((arg7) && !((f)v1).b) {
                continue;
            }

            ((f)v1).a.a(((l)this), arg4, arg5, arg6);
        }
    }

    void a(android.support.v4.app.e arg8, boolean arg9, boolean arg10, boolean arg11) {
        if(arg9) {
            arg8.b(arg11);
        }
        else {
            arg8.f();
        }

        ArrayList v1 = new ArrayList(1);
        ArrayList v2 = new ArrayList(1);
        v1.add(arg8);
        v2.add(Boolean.valueOf(arg9));
        if(arg10) {
            p.a(this, v1, v2, 0, 1, true);
        }

        if(arg11) {
            this.a(this.l, true);
        }

        if(this.f != null) {
            int v9 = this.f.size();
            int v0;
            for(v0 = 0; v0 < v9; ++v0) {
                Object v1_1 = this.f.valueAt(v0);
                if(v1_1 != null && ((Fragment)v1_1).mView != null && (((Fragment)v1_1).mIsNewlyAdded) && (arg8.b(((Fragment)v1_1).mContainerId))) {
                    if(((Fragment)v1_1).mPostponedAlpha > 0f) {
                        ((Fragment)v1_1).mView.setAlpha(((Fragment)v1_1).mPostponedAlpha);
                    }

                    if(arg11) {
                        ((Fragment)v1_1).mPostponedAlpha = 0f;
                        goto label_50;
                    }

                    ((Fragment)v1_1).mPostponedAlpha = -1f;
                    ((Fragment)v1_1).mIsNewlyAdded = false;
                }

            label_50:
            }
        }
    }

    public void a(android.support.v4.app.l$a arg3, boolean arg4) {
        this.J.add(new f(arg3, arg4));
    }

    public Fragment b(String arg3) {
        if(this.f != null && arg3 != null) {
            int v0;
            for(v0 = this.f.size() - 1; v0 >= 0; --v0) {
                Object v1 = this.f.valueAt(v0);
                if(v1 != null) {
                    Fragment v1_1 = ((Fragment)v1).findFragmentByWho(arg3);
                    if(v1_1 != null) {
                        return v1_1;
                    }
                }
            }
        }

        return null;
    }

    public void b(Menu arg3) {
        if(this.l < 1) {
            return;
        }

        int v0;
        for(v0 = 0; v0 < this.e.size(); ++v0) {
            Object v1 = this.e.get(v0);
            if(v1 != null) {
                ((Fragment)v1).performOptionsMenuClosed(arg3);
            }
        }
    }

    public void b(boolean arg3) {
        int v0;
        for(v0 = this.e.size() - 1; v0 >= 0; --v0) {
            Object v1 = this.e.get(v0);
            if(v1 != null) {
                ((Fragment)v1).performPictureInPictureModeChanged(arg3);
            }
        }
    }

    public boolean b(MenuItem arg5) {
        if(this.l < 1) {
            return 0;
        }

        int v0;
        for(v0 = 0; v0 < this.e.size(); ++v0) {
            Object v3 = this.e.get(v0);
            if(v3 != null && (((Fragment)v3).performContextItemSelected(arg5))) {
                return 1;
            }
        }

        return 0;
    }

    void b(android.support.v4.app.e arg2) {
        if(this.g == null) {
            this.g = new ArrayList();
        }

        this.g.add(arg2);
    }

    public void b(h arg2, boolean arg3) {
        if((arg3) && (this.m == null || (this.u))) {
            return;
        }

        this.c(arg3);
        if(arg2.a(this.x, this.y)) {
            this.c = true;
            try {
                this.b(this.x, this.y);
            }
            catch(Throwable v2) {
                this.z();
                throw v2;
            }

            this.z();
        }

        this.h();
        this.C();
    }

    private void b(android.support.v4.f.b arg11) {
        if(this.l < 1) {
            return;
        }

        int v0 = Math.min(this.l, 3);
        int v1 = this.e.size();
        int v8;
        for(v8 = 0; v8 < v1; ++v8) {
            Object v9 = this.e.get(v8);
            if(((Fragment)v9).mState < v0) {
                this.a(v9, v0, ((Fragment)v9).getNextAnim(), ((Fragment)v9).getNextTransition(), false);
                if(((Fragment)v9).mView != null && !((Fragment)v9).mHidden && (((Fragment)v9).mIsNewlyAdded)) {
                    arg11.add(v9);
                }
            }
        }
    }

    private static void b(View arg3, c arg4) {
        if(arg3 != null) {
            if(arg4 == null) {
            }
            else if(m.a(arg3, arg4)) {
                if(arg4.b != null) {
                    arg4.b.addListener(new d(arg3));
                }
                else {
                    Animation$AnimationListener v0 = m.a(arg4.a);
                    arg3.setLayerType(2, null);
                    arg4.a.setAnimationListener(new a(arg3, v0));
                }
            }
        }
    }

    private static void b(ArrayList arg3, ArrayList arg4, int arg5, int arg6) {
        while(arg5 < arg6) {
            Object v0 = arg3.get(arg5);
            boolean v2 = true;
            if(arg4.get(arg5).booleanValue()) {
                ((android.support.v4.app.e)v0).a(-1);
                if(arg5 == arg6 - 1) {
                }
                else {
                    v2 = false;
                }

                ((android.support.v4.app.e)v0).b(v2);
            }
            else {
                ((android.support.v4.app.e)v0).a(1);
                ((android.support.v4.app.e)v0).f();
            }

            ++arg5;
        }
    }

    private void b(ArrayList arg5, ArrayList arg6) {
        if(arg5 != null) {
            if(arg5.isEmpty()) {
            }
            else {
                if(arg6 != null && arg5.size() == arg6.size()) {
                    this.a(arg5, arg6);
                    int v0 = arg5.size();
                    int v1 = 0;
                    int v2 = 0;
                    while(v1 < v0) {
                        if(!arg5.get(v1).t) {
                            if(v2 != v1) {
                                this.a(arg5, arg6, v2, v1);
                            }

                            v2 = v1 + 1;
                            if(arg6.get(v1).booleanValue()) {
                                while(v2 < v0) {
                                    if(!arg6.get(v2).booleanValue()) {
                                        break;
                                    }

                                    if(arg5.get(v2).t) {
                                        break;
                                    }

                                    ++v2;
                                }
                            }

                            this.a(arg5, arg6, v1, v2);
                            v1 = v2 - 1;
                        }

                        ++v1;
                    }

                    if(v2 != v0) {
                        this.a(arg5, arg6, v2, v0);
                    }

                    return;
                }

                throw new IllegalStateException("Internal error with the back stack records");
            }
        }
    }

    public static int b(int arg1, boolean arg2) {
        if(arg1 != 4097) {
            if(arg1 != 4099) {
                if(arg1 != 8194) {
                    arg1 = -1;
                }
                else if(arg2) {
                    arg1 = 3;
                }
                else {
                    arg1 = 4;
                }
            }
            else if(arg2) {
                arg1 = 5;
            }
            else {
                arg1 = 6;
            }
        }
        else if(arg2) {
            arg1 = 1;
        }
        else {
            arg1 = 2;
        }

        return arg1;
    }

    void b(Fragment arg4, Context arg5, boolean arg6) {
        if(this.o != null) {
            l v0 = this.o.getFragmentManager();
            if((v0 instanceof m)) {
                ((m)v0).b(arg4, arg5, true);
            }
        }

        Iterator v0_1 = this.J.iterator();
        while(v0_1.hasNext()) {
            Object v1 = v0_1.next();
            if((arg6) && !((f)v1).b) {
                continue;
            }

            ((f)v1).a.b(((l)this), arg4, arg5);
        }
    }

    void b(Fragment arg4, Bundle arg5, boolean arg6) {
        if(this.o != null) {
            l v0 = this.o.getFragmentManager();
            if((v0 instanceof m)) {
                ((m)v0).b(arg4, arg5, true);
            }
        }

        Iterator v0_1 = this.J.iterator();
        while(v0_1.hasNext()) {
            Object v1 = v0_1.next();
            if((arg6) && !((f)v1).b) {
                continue;
            }

            ((f)v1).a.a(((l)this), arg4, arg5);
        }
    }

    void b(Fragment arg4, boolean arg5) {
        if(this.o != null) {
            l v0 = this.o.getFragmentManager();
            if((v0 instanceof m)) {
                ((m)v0).b(arg4, true);
            }
        }

        Iterator v0_1 = this.J.iterator();
        while(v0_1.hasNext()) {
            Object v1 = v0_1.next();
            if((arg5) && !((f)v1).b) {
                continue;
            }

            ((f)v1).a.a(((l)this), arg4);
        }
    }

    void b(Fragment arg7) {
        this.a(arg7, this.l, 0, 0, false);
    }

    public Fragment b(int arg4) {
        Object v1;
        int v0;
        for(v0 = this.e.size() - 1; v0 >= 0; --v0) {
            v1 = this.e.get(v0);
            if(v1 != null && ((Fragment)v1).mFragmentId == arg4) {
                return ((Fragment)v1);
            }
        }

        if(this.f != null) {
            for(v0 = this.f.size() - 1; v0 >= 0; --v0) {
                v1 = this.f.valueAt(v0);
                if(v1 != null && ((Fragment)v1).mFragmentId == arg4) {
                    return ((Fragment)v1);
                }
            }
        }

        return null;
    }

    public boolean b() {
        this.y();
        return this.a(null, -1, 0);
    }

    public void c(int arg4) {
        __monitor_enter(this);
        try {
            this.i.set(arg4, null);
            if(this.j == null) {
                this.j = new ArrayList();
            }

            if(m.a) {
                Log.v("FragmentManager", "Freeing back stack index " + arg4);
            }

            this.j.add(Integer.valueOf(arg4));
            __monitor_exit(this);
            return;
        label_25:
            __monitor_exit(this);
        }
        catch(Throwable v4) {
            goto label_25;
        }

        throw v4;
    }

    private void c(boolean arg3) {
        if(!this.c) {
            if(this.m != null) {
                if(Looper.myLooper() == this.m.j().getLooper()) {
                    if(!arg3) {
                        this.y();
                    }

                    if(this.x == null) {
                        this.x = new ArrayList();
                        this.y = new ArrayList();
                    }

                    this.c = true;
                    ArrayList v0 = null;
                    try {
                        this.a(v0, v0);
                    }
                    catch(Throwable v0_1) {
                        this.c = false;
                        throw v0_1;
                    }

                    this.c = false;
                    return;
                }

                throw new IllegalStateException("Must be called from main thread of fragment host");
            }

            throw new IllegalStateException("Fragment host has been destroyed");
        }

        throw new IllegalStateException("FragmentManager is already executing transactions");
    }

    private boolean c(ArrayList arg5, ArrayList arg6) {
        __monitor_enter(this);
        try {
            int v1 = 0;
            if(this.b != null) {
                if(this.b.size() == 0) {
                }
                else {
                    int v0 = this.b.size();
                    int v2 = 0;
                    while(v1 < v0) {
                        v2 |= this.b.get(v1).a(arg5, arg6);
                        ++v1;
                    }

                    this.b.clear();
                    this.m.j().removeCallbacks(this.E);
                    __monitor_exit(this);
                    return ((boolean)v2);
                }
            }

            __monitor_exit(this);
            return 0;
        label_29:
            __monitor_exit(this);
        }
        catch(Throwable v5) {
            goto label_29;
        }

        throw v5;
    }

    void c(Fragment arg4) {
        if((arg4.mFromLayout) && !arg4.mPerformedCreateView) {
            ViewGroup v2 = null;
            arg4.performCreateView(arg4.performGetLayoutInflater(arg4.mSavedFragmentState), v2, arg4.mSavedFragmentState);
            if(arg4.mView != null) {
                arg4.mInnerView = arg4.mView;
                arg4.mView.setSaveFromParentEnabled(false);
                if(arg4.mHidden) {
                    arg4.mView.setVisibility(8);
                }

                arg4.onViewCreated(arg4.mView, arg4.mSavedFragmentState);
                this.a(arg4, arg4.mView, arg4.mSavedFragmentState, false);
            }
            else {
                arg4.mInnerView = ((View)v2);
            }
        }
    }

    void c(Fragment arg4, Bundle arg5, boolean arg6) {
        if(this.o != null) {
            l v0 = this.o.getFragmentManager();
            if((v0 instanceof m)) {
                ((m)v0).c(arg4, arg5, true);
            }
        }

        Iterator v0_1 = this.J.iterator();
        while(v0_1.hasNext()) {
            Object v1 = v0_1.next();
            if((arg6) && !((f)v1).b) {
                continue;
            }

            ((f)v1).a.c(((l)this), arg4, arg5);
        }
    }

    void c(Fragment arg4, boolean arg5) {
        if(this.o != null) {
            l v0 = this.o.getFragmentManager();
            if((v0 instanceof m)) {
                ((m)v0).c(arg4, true);
            }
        }

        Iterator v0_1 = this.J.iterator();
        while(v0_1.hasNext()) {
            Object v1 = v0_1.next();
            if((arg5) && !((f)v1).b) {
                continue;
            }

            ((f)v1).a.b(((l)this), arg4);
        }
    }

    public List c() {
        if(this.e.isEmpty()) {
            return Collections.emptyList();
        }

        ArrayList v0 = this.e;
        __monitor_enter(v0);
        try {
            __monitor_exit(v0);
            return this.e.clone();
        label_12:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_12;
        }

        throw v1;
    }

    public boolean d() {
        boolean v0 = (this.s) || (this.t) ? true : false;
        return v0;
    }

    public static int d(int arg3) {
        int v0 = 8194;
        int v1 = 4099;
        if(arg3 != 4097) {
            if(arg3 == v1) {
                v0 = 4099;
            }
            else if(arg3 != v0) {
                v0 = 0;
            }
            else {
                v0 = 4097;
            }
        }

        return v0;
    }

    void d(Fragment arg4, boolean arg5) {
        if(this.o != null) {
            l v0 = this.o.getFragmentManager();
            if((v0 instanceof m)) {
                ((m)v0).d(arg4, true);
            }
        }

        Iterator v0_1 = this.J.iterator();
        while(v0_1.hasNext()) {
            Object v1 = v0_1.next();
            if((arg5) && !((f)v1).b) {
                continue;
            }

            ((f)v1).a.c(((l)this), arg4);
        }
    }

    void d(Fragment arg8) {
        if(arg8.mView != null) {
            c v0 = this.a(arg8, arg8.getNextTransition(), arg8.mHidden ^ 1, arg8.getNextTransitionStyle());
            if(v0 != null && v0.b != null) {
                v0.b.setTarget(arg8.mView);
                if(!arg8.mHidden) {
                    arg8.mView.setVisibility(0);
                }
                else if(arg8.isHideReplaced()) {
                    arg8.setHideReplaced(false);
                }
                else {
                    ViewGroup v3 = arg8.mContainer;
                    View v4 = arg8.mView;
                    v3.startViewTransition(v4);
                    v0.b.addListener(new AnimatorListenerAdapter(v3, v4, arg8) {
                        public void onAnimationEnd(Animator arg3) {
                            this.a.endViewTransition(this.b);
                            arg3.removeListener(((Animator$AnimatorListener)this));
                            if(this.c.mView != null) {
                                this.c.mView.setVisibility(8);
                            }
                        }
                    });
                }

                m.b(arg8.mView, v0);
                v0.b.start();
                goto label_56;
            }

            if(v0 != null) {
                m.b(arg8.mView, v0);
                arg8.mView.startAnimation(v0.a);
                v0.a.start();
            }

            int v0_1 = !arg8.mHidden || (arg8.isHideReplaced()) ? 0 : 8;
            arg8.mView.setVisibility(v0_1);
            if(!arg8.isHideReplaced()) {
                goto label_56;
            }

            arg8.setHideReplaced(false);
        }

    label_56:
        if((arg8.mAdded) && (arg8.mHasMenu) && (arg8.mMenuVisible)) {
            this.r = true;
        }

        arg8.mHiddenChanged = false;
        arg8.onHiddenChanged(arg8.mHidden);
    }

    void d(Fragment arg4, Bundle arg5, boolean arg6) {
        if(this.o != null) {
            l v0 = this.o.getFragmentManager();
            if((v0 instanceof m)) {
                ((m)v0).d(arg4, arg5, true);
            }
        }

        Iterator v0_1 = this.J.iterator();
        while(v0_1.hasNext()) {
            Object v1 = v0_1.next();
            if((arg6) && !((f)v1).b) {
                continue;
            }

            ((f)v1).a.d(((l)this), arg4, arg5);
        }
    }

    void e(Fragment arg11) {
        if(arg11 == null) {
            return;
        }

        int v0 = this.l;
        if(arg11.mRemoving) {
            v0 = arg11.isInBackStack() ? Math.min(v0, 1) : Math.min(v0, 0);
        }

        this.a(arg11, v0, arg11.getNextTransition(), arg11.getNextTransitionStyle(), false);
        if(arg11.mView != null) {
            Fragment v0_1 = this.p(arg11);
            if(v0_1 != null) {
                View v0_2 = v0_1.mView;
                ViewGroup v1 = arg11.mContainer;
                v0 = v1.indexOfChild(v0_2);
                int v4 = v1.indexOfChild(arg11.mView);
                if(v4 < v0) {
                    v1.removeViewAt(v4);
                    v1.addView(arg11.mView, v0);
                }
            }

            if(!arg11.mIsNewlyAdded) {
                goto label_61;
            }

            if(arg11.mContainer == null) {
                goto label_61;
            }

            if(arg11.mPostponedAlpha > 0f) {
                arg11.mView.setAlpha(arg11.mPostponedAlpha);
            }

            arg11.mPostponedAlpha = 0f;
            arg11.mIsNewlyAdded = false;
            c v0_3 = this.a(arg11, arg11.getNextTransition(), true, arg11.getNextTransitionStyle());
            if(v0_3 == null) {
                goto label_61;
            }

            m.b(arg11.mView, v0_3);
            if(v0_3.a != null) {
                arg11.mView.startAnimation(v0_3.a);
                goto label_61;
            }

            v0_3.b.setTarget(arg11.mView);
            v0_3.b.start();
        }

    label_61:
        if(arg11.mHiddenChanged) {
            this.d(arg11);
        }
    }

    private void e(int arg3) {
        try {
            this.c = true;
            this.a(arg3, false);
        }
        catch(Throwable v3) {
            this.c = false;
            throw v3;
        }

        this.c = false;
        this.g();
    }

    void e() {
        if(this.f == null) {
            return;
        }

        int v0;
        for(v0 = 0; v0 < this.f.size(); ++v0) {
            Object v1 = this.f.valueAt(v0);
            if(v1 != null) {
                this.a(((Fragment)v1));
            }
        }
    }

    void e(Fragment arg4, boolean arg5) {
        if(this.o != null) {
            l v0 = this.o.getFragmentManager();
            if((v0 instanceof m)) {
                ((m)v0).e(arg4, true);
            }
        }

        Iterator v0_1 = this.J.iterator();
        while(v0_1.hasNext()) {
            Object v1 = v0_1.next();
            if((arg5) && !((f)v1).b) {
                continue;
            }

            ((f)v1).a.d(((l)this), arg4);
        }
    }

    void f(Fragment arg4) {
        if(arg4.mIndex >= 0) {
            return;
        }

        int v0 = this.d;
        this.d = v0 + 1;
        arg4.setIndex(v0, this.o);
        if(this.f == null) {
            this.f = new SparseArray();
        }

        this.f.put(arg4.mIndex, arg4);
        if(m.a) {
            Log.v("FragmentManager", "Allocated fragment index " + arg4);
        }
    }

    void f(Fragment arg4, boolean arg5) {
        if(this.o != null) {
            l v0 = this.o.getFragmentManager();
            if((v0 instanceof m)) {
                ((m)v0).f(arg4, true);
            }
        }

        Iterator v0_1 = this.J.iterator();
        while(v0_1.hasNext()) {
            Object v1 = v0_1.next();
            if((arg5) && !((f)v1).b) {
                continue;
            }

            ((f)v1).a.e(((l)this), arg4);
        }
    }

    void f() {
        __monitor_enter(this);
        try {
            int v1 = 0;
            int v0_1 = this.C == null || (this.C.isEmpty()) ? 0 : 1;
            if(this.b != null && this.b.size() == 1) {
                v1 = 1;
            }

            if(v0_1 != 0 || v1 != 0) {
                this.m.j().removeCallbacks(this.E);
                this.m.j().post(this.E);
            }

            __monitor_exit(this);
            return;
        label_30:
            __monitor_exit(this);
        }
        catch(Throwable v0) {
            goto label_30;
        }

        throw v0;
    }

    public boolean g() {
        this.c(true);
        boolean v1;
        for(v1 = false; this.c(this.x, this.y); v1 = true) {
            this.c = true;
            try {
                this.b(this.x, this.y);
            }
            catch(Throwable v0) {
                this.z();
                throw v0;
            }

            this.z();
        }

        this.h();
        this.C();
        return v1;
    }

    void g(Fragment arg4, boolean arg5) {
        if(this.o != null) {
            l v0 = this.o.getFragmentManager();
            if((v0 instanceof m)) {
                ((m)v0).g(arg4, true);
            }
        }

        Iterator v0_1 = this.J.iterator();
        while(v0_1.hasNext()) {
            Object v1 = v0_1.next();
            if((arg5) && !((f)v1).b) {
                continue;
            }

            ((f)v1).a.f(((l)this), arg4);
        }
    }

    void g(Fragment arg4) {
        if(arg4.mIndex < 0) {
            return;
        }

        if(m.a) {
            Log.v("FragmentManager", "Freeing fragment index " + arg4);
        }

        this.f.put(arg4.mIndex, null);
        arg4.initState();
    }

    public void h(Fragment arg4) {
        if(m.a) {
            Log.v("FragmentManager", "remove: " + arg4 + " nesting=" + arg4.mBackStackNesting);
        }

        int v0 = arg4.isInBackStack() ^ 1;
        if(!arg4.mDetached || v0 != 0) {
            ArrayList v0_1 = this.e;
            __monitor_enter(v0_1);
            try {
                this.e.remove(arg4);
                __monitor_exit(v0_1);
            }
            catch(Throwable v4) {
                try {
                label_35:
                    __monitor_exit(v0_1);
                }
                catch(Throwable v4) {
                    goto label_35;
                }

                throw v4;
            }

            if((arg4.mHasMenu) && (arg4.mMenuVisible)) {
                this.r = true;
            }

            arg4.mAdded = false;
            arg4.mRemoving = true;
        }
    }

    void h() {
        if(this.w) {
            this.w = false;
            this.e();
        }
    }

    void h(Fragment arg4, boolean arg5) {
        if(this.o != null) {
            l v0 = this.o.getFragmentManager();
            if((v0 instanceof m)) {
                ((m)v0).h(arg4, true);
            }
        }

        Iterator v0_1 = this.J.iterator();
        while(v0_1.hasNext()) {
            Object v1 = v0_1.next();
            if((arg5) && !((f)v1).b) {
                continue;
            }

            ((f)v1).a.g(((l)this), arg4);
        }
    }

    public void i(Fragment arg4) {
        if(m.a) {
            Log.v("FragmentManager", "hide: " + arg4);
        }

        if(!arg4.mHidden) {
            arg4.mHidden = true;
            arg4.mHiddenChanged ^= 1;
        }
    }

    void i() {
        if(this.k != null) {
            int v0;
            for(v0 = 0; v0 < this.k.size(); ++v0) {
                this.k.get(v0).a();
            }
        }
    }

    n j() {
        m.a(this.D);
        return this.D;
    }

    public void j(Fragment arg4) {
        if(m.a) {
            Log.v("FragmentManager", "show: " + arg4);
        }

        if(arg4.mHidden) {
            arg4.mHidden = false;
            arg4.mHiddenChanged ^= 1;
        }
    }

    public void k(Fragment arg5) {
        if(m.a) {
            Log.v("FragmentManager", "detach: " + arg5);
        }

        if(!arg5.mDetached) {
            arg5.mDetached = true;
            if(arg5.mAdded) {
                if(m.a) {
                    Log.v("FragmentManager", "remove from detach: " + arg5);
                }

                ArrayList v1_1 = this.e;
                __monitor_enter(v1_1);
                try {
                    this.e.remove(arg5);
                    __monitor_exit(v1_1);
                }
                catch(Throwable v5) {
                    try {
                    label_40:
                        __monitor_exit(v1_1);
                    }
                    catch(Throwable v5) {
                        goto label_40;
                    }

                    throw v5;
                }

                if((arg5.mHasMenu) && (arg5.mMenuVisible)) {
                    this.r = true;
                }

                arg5.mAdded = false;
            }
        }
    }

    void k() {
        n v7_1;
        ArrayList v5;
        ArrayList v4;
        ArrayList v3;
        Object v1 = null;
        if(this.f != null) {
            v3 = ((ArrayList)v1);
            v4 = v3;
            v5 = v4;
            int v2;
            for(v2 = 0; v2 < this.f.size(); ++v2) {
                Object v6 = this.f.valueAt(v2);
                if(v6 != null) {
                    if(((Fragment)v6).mRetainInstance) {
                        if(v3 == null) {
                            v3 = new ArrayList();
                        }

                        v3.add(v6);
                        int v7 = ((Fragment)v6).mTarget != null ? ((Fragment)v6).mTarget.mIndex : -1;
                        ((Fragment)v6).mTargetIndex = v7;
                        if(!m.a) {
                            goto label_37;
                        }

                        Log.v("FragmentManager", "retainNonConfig: keeping retained " + v6);
                    }

                label_37:
                    if(((Fragment)v6).mChildFragmentManager != null) {
                        ((Fragment)v6).mChildFragmentManager.k();
                        v7_1 = ((Fragment)v6).mChildFragmentManager.D;
                    }
                    else {
                        v7_1 = ((Fragment)v6).mChildNonConfig;
                    }

                    if(v4 == null && v7_1 != null) {
                        v4 = new ArrayList(this.f.size());
                        int v8_1;
                        for(v8_1 = 0; v8_1 < v2; ++v8_1) {
                            v4.add(v1);
                        }
                    }

                    if(v4 != null) {
                        v4.add(v7_1);
                    }

                    if(v5 == null && ((Fragment)v6).mViewModelStore != null) {
                        v5 = new ArrayList(this.f.size());
                        for(v7 = 0; v7 < v2; ++v7) {
                            v5.add(v1);
                        }
                    }

                    if(v5 == null) {
                        goto label_73;
                    }

                    v5.add(((Fragment)v6).mViewModelStore);
                }

            label_73:
            }
        }
        else {
            v3 = ((ArrayList)v1);
            v4 = v3;
            v5 = v4;
        }

        this.D = v3 != null || v4 != null || v5 != null ? new n(((List)v3), ((List)v4), ((List)v5)) : ((n)v1);
    }

    Parcelable l() {
        BackStackState[] v1_1;
        int[] v2_1;
        this.A();
        this.B();
        this.g();
        this.s = true;
        n v1 = null;
        this.D = v1;
        if(this.f != null) {
            if(this.f.size() <= 0) {
            }
            else {
                int v2 = this.f.size();
                FragmentState[] v3 = new FragmentState[v2];
                int v4 = 0;
                int v5 = 0;
                int v6 = 0;
                while(v5 < v2) {
                    Object v7 = this.f.valueAt(v5);
                    if(v7 != null) {
                        if(((Fragment)v7).mIndex < 0) {
                            StringBuilder v8 = new StringBuilder();
                            v8.append("Failure saving state: active ");
                            v8.append(v7);
                            v8.append(" has cleared index: ");
                            v8.append(((Fragment)v7).mIndex);
                            this.a(new IllegalStateException(v8.toString()));
                        }

                        FragmentState v6_1 = new FragmentState(((Fragment)v7));
                        v3[v5] = v6_1;
                        if(((Fragment)v7).mState <= 0 || v6_1.k != null) {
                            v6_1.k = ((Fragment)v7).mSavedFragmentState;
                        }
                        else {
                            v6_1.k = this.n(((Fragment)v7));
                            if(((Fragment)v7).mTarget != null) {
                                if(((Fragment)v7).mTarget.mIndex < 0) {
                                    StringBuilder v9 = new StringBuilder();
                                    v9.append("Failure saving state: ");
                                    v9.append(v7);
                                    v9.append(" has target not in fragment manager: ");
                                    v9.append(((Fragment)v7).mTarget);
                                    this.a(new IllegalStateException(v9.toString()));
                                }

                                if(v6_1.k == null) {
                                    v6_1.k = new Bundle();
                                }

                                this.a(v6_1.k, "android:target_state", ((Fragment)v7).mTarget);
                                if(((Fragment)v7).mTargetRequestCode == 0) {
                                    goto label_83;
                                }

                                v6_1.k.putInt("android:target_req_state", ((Fragment)v7).mTargetRequestCode);
                            }
                        }

                    label_83:
                        if(m.a) {
                            Log.v("FragmentManager", "Saved state of " + v7 + ": " + v6_1.k);
                        }

                        v6 = 1;
                    }

                    ++v5;
                }

                if(v6 == 0) {
                    if(m.a) {
                        Log.v("FragmentManager", "saveAllState: no fragments!");
                    }

                    return ((Parcelable)v1);
                }

                int v0 = this.e.size();
                if(v0 > 0) {
                    v2_1 = new int[v0];
                    v5 = 0;
                    goto label_112;
                }
                else {
                    v2_1 = ((int[])v1);
                    goto label_152;
                label_112:
                    while(v5 < v0) {
                        v2_1[v5] = this.e.get(v5).mIndex;
                        if(v2_1[v5] < 0) {
                            StringBuilder v7_1 = new StringBuilder();
                            v7_1.append("Failure saving state: active ");
                            v7_1.append(this.e.get(v5));
                            v7_1.append(" has cleared index: ");
                            v7_1.append(v2_1[v5]);
                            this.a(new IllegalStateException(v7_1.toString()));
                        }

                        if(m.a) {
                            Log.v("FragmentManager", "saveAllState: adding fragment #" + v5 + ": " + this.e.get(v5));
                        }

                        ++v5;
                    }
                }

            label_152:
                if(this.g != null) {
                    v0 = this.g.size();
                    if(v0 > 0) {
                        v1_1 = new BackStackState[v0];
                        while(v4 < v0) {
                            v1_1[v4] = new BackStackState(this.g.get(v4));
                            if(m.a) {
                                Log.v("FragmentManager", "saveAllState: adding back stack #" + v4 + ": " + this.g.get(v4));
                            }

                            ++v4;
                        }
                    }
                }

                FragmentManagerState v0_1 = new FragmentManagerState();
                v0_1.a = v3;
                v0_1.b = v2_1;
                v0_1.c = v1_1;
                if(this.p != null) {
                    v0_1.d = this.p.mIndex;
                }

                v0_1.e = this.d;
                this.k();
                return ((Parcelable)v0_1);
            }
        }

        return ((Parcelable)v1);
    }

    public void l(Fragment arg4) {
        if(m.a) {
            Log.v("FragmentManager", "attach: " + arg4);
        }

        if(arg4.mDetached) {
            arg4.mDetached = false;
            if(!arg4.mAdded) {
                if(!this.e.contains(arg4)) {
                    if(m.a) {
                        Log.v("FragmentManager", "add from attach: " + arg4);
                    }

                    ArrayList v0 = this.e;
                    __monitor_enter(v0);
                    try {
                        this.e.add(arg4);
                        __monitor_exit(v0);
                    }
                    catch(Throwable v4) {
                        try {
                        label_43:
                            __monitor_exit(v0);
                        }
                        catch(Throwable v4) {
                            goto label_43;
                        }

                        throw v4;
                    }

                    arg4.mAdded = true;
                    if(!arg4.mHasMenu) {
                        return;
                    }

                    if(!arg4.mMenuVisible) {
                        return;
                    }

                    this.r = true;
                }
                else {
                    v1 = new StringBuilder();
                    v1.append("Fragment already added: ");
                    v1.append(arg4);
                    throw new IllegalStateException(v1.toString());
                }
            }
        }
    }

    public void m() {
        this.D = null;
        int v0 = 0;
        this.s = false;
        this.t = false;
        int v1 = this.e.size();
        while(v0 < v1) {
            Object v2 = this.e.get(v0);
            if(v2 != null) {
                ((Fragment)v2).noteStateNotSaved();
            }

            ++v0;
        }
    }

    void m(Fragment arg3) {
        if(arg3.mInnerView == null) {
            return;
        }

        if(this.B == null) {
            this.B = new SparseArray();
        }
        else {
            this.B.clear();
        }

        arg3.mInnerView.saveHierarchyState(this.B);
        if(this.B.size() > 0) {
            arg3.mSavedViewState = this.B;
            this.B = null;
        }
    }

    public void n() {
        this.s = false;
        this.t = false;
        this.e(1);
    }

    Bundle n(Fragment arg4) {
        Bundle v0;
        if(this.A == null) {
            this.A = new Bundle();
        }

        arg4.performSaveInstanceState(this.A);
        this.d(arg4, this.A, false);
        Bundle v1 = null;
        if(!this.A.isEmpty()) {
            v0 = this.A;
            this.A = v1;
        }
        else {
            v0 = v1;
        }

        if(arg4.mView != null) {
            this.m(arg4);
        }

        if(arg4.mSavedViewState != null) {
            if(v0 == null) {
                v0 = new Bundle();
            }

            v0.putSparseParcelableArray("android:view_state", arg4.mSavedViewState);
        }

        if(!arg4.mUserVisibleHint) {
            if(v0 == null) {
                v0 = new Bundle();
            }

            v0.putBoolean("android:user_visible_hint", arg4.mUserVisibleHint);
        }

        return v0;
    }

    public void o() {
        this.s = false;
        this.t = false;
        this.e(2);
    }

    public void o(Fragment arg4) {
        if(arg4 != null) {
            if(this.f.get(arg4.mIndex) == arg4) {
                if(arg4.mHost == null) {
                    goto label_22;
                }
                else if(arg4.getFragmentManager() == this) {
                    goto label_22;
                }
            }

            StringBuilder v1 = new StringBuilder();
            v1.append("Fragment ");
            v1.append(arg4);
            v1.append(" is not an active fragment of FragmentManager ");
            v1.append(this);
            throw new IllegalArgumentException(v1.toString());
        }

    label_22:
        this.p = arg4;
    }

    public View onCreateView(View arg14, String arg15, Context arg16, AttributeSet arg17) {
        Fragment v11_1;
        StringBuilder v2_2;
        m v6 = this;
        Context v0 = arg16;
        AttributeSet v1 = arg17;
        View v3 = null;
        if(!"fragment".equals(arg15)) {
            return v3;
        }

        String v2 = v1.getAttributeValue(((String)v3), "class");
        TypedArray v4 = v0.obtainStyledAttributes(v1, g.a);
        int v5 = 0;
        if(v2 == null) {
            v2 = v4.getString(0);
        }

        String v7 = v2;
        int v2_1 = -1;
        int v9 = v4.getResourceId(1, v2_1);
        String v10 = v4.getString(2);
        v4.recycle();
        if(!Fragment.isSupportFragmentClass(v6.m.i(), v7)) {
            return v3;
        }

        if(arg14 != null) {
            v5 = arg14.getId();
        }

        if(v5 == v2_1 && v9 == v2_1) {
            if(v10 != null) {
            }
            else {
                v2_2 = new StringBuilder();
                v2_2.append(arg17.getPositionDescription());
                v2_2.append(": Must specify unique android:id, android:tag, or have a parent with an id for ");
                v2_2.append(v7);
                throw new IllegalArgumentException(v2_2.toString());
            }
        }

        Fragment v4_1 = v9 != v2_1 ? this.b(v9) : ((Fragment)v3);
        if(v4_1 == null && v10 != null) {
            v4_1 = this.a(v10);
        }

        if(v4_1 == null && v5 != v2_1) {
            v4_1 = this.b(v5);
        }

        if(m.a) {
            Log.v("FragmentManager", "onCreateView: id=0x" + Integer.toHexString(v9) + " fname=" + v7 + " existing=" + v4_1);
        }

        if(v4_1 == null) {
            Fragment v0_1 = v6.n.a(v0, v7, ((Bundle)v3));
            v0_1.mFromLayout = true;
            v2_1 = v9 != 0 ? v9 : v5;
            v0_1.mFragmentId = v2_1;
            v0_1.mContainerId = v5;
            v0_1.mTag = v10;
            v0_1.mInLayout = true;
            v0_1.mFragmentManager = v6;
            v0_1.mHost = v6.m;
            v0_1.onInflate(v6.m.i(), v1, v0_1.mSavedFragmentState);
            this.a(v0_1, true);
            v11_1 = v0_1;
        }
        else {
            if(v4_1.mInLayout) {
                goto label_142;
            }

            v4_1.mInLayout = true;
            v4_1.mHost = v6.m;
            if(!v4_1.mRetaining) {
                v4_1.onInflate(v6.m.i(), v1, v4_1.mSavedFragmentState);
            }

            v11_1 = v4_1;
        }

        if(v6.l >= 1 || !v11_1.mFromLayout) {
            this.b(v11_1);
        }
        else {
            this.a(v11_1, 1, 0, 0, false);
        }

        if(v11_1.mView != null) {
            if(v9 != 0) {
                v11_1.mView.setId(v9);
            }

            if(v11_1.mView.getTag() == null) {
                v11_1.mView.setTag(v10);
            }

            return v11_1.mView;
        }

        StringBuilder v1_1 = new StringBuilder();
        v1_1.append("Fragment ");
        v1_1.append(v7);
        v1_1.append(" did not create a view.");
        throw new IllegalStateException(v1_1.toString());
    label_142:
        v2_2 = new StringBuilder();
        v2_2.append(arg17.getPositionDescription());
        v2_2.append(": Duplicate id 0x");
        v2_2.append(Integer.toHexString(v9));
        v2_2.append(", tag ");
        v2_2.append(v10);
        v2_2.append(", or parent id 0x");
        v2_2.append(Integer.toHexString(v5));
        v2_2.append(" with another fragment for ");
        v2_2.append(v7);
        throw new IllegalArgumentException(v2_2.toString());
    }

    public View onCreateView(String arg2, Context arg3, AttributeSet arg4) {
        return this.onCreateView(null, arg2, arg3, arg4);
    }

    public void p() {
        this.s = false;
        this.t = false;
        this.e(3);
    }

    private Fragment p(Fragment arg5) {
        ViewGroup v0 = arg5.mContainer;
        View v1 = arg5.mView;
        Fragment v2 = null;
        if(v0 != null) {
            if(v1 == null) {
            }
            else {
                int v5;
                for(v5 = this.e.indexOf(arg5) - 1; v5 >= 0; --v5) {
                    Object v1_1 = this.e.get(v5);
                    if(((Fragment)v1_1).mContainer == v0 && ((Fragment)v1_1).mView != null) {
                        return ((Fragment)v1_1);
                    }
                }
            }
        }

        return v2;
    }

    public void q() {
        this.s = false;
        this.t = false;
        this.e(4);
    }

    public void r() {
        this.e(3);
    }

    public void s() {
        this.t = true;
        this.e(2);
    }

    public void t() {
        this.e(1);
    }

    public String toString() {
        Fragment v1;
        StringBuilder v0 = new StringBuilder(128);
        v0.append("FragmentManager{");
        v0.append(Integer.toHexString(System.identityHashCode(this)));
        v0.append(" in ");
        if(this.o != null) {
            v1 = this.o;
        }
        else {
            k v1_1 = this.m;
        }

        android.support.v4.f.d.a(v1, v0);
        v0.append("}}");
        return v0.toString();
    }

    public void u() {
        this.u = true;
        this.g();
        this.e(0);
        this.m = null;
        this.n = null;
        this.o = null;
    }

    public void v() {
        int v0;
        for(v0 = 0; v0 < this.e.size(); ++v0) {
            Object v1 = this.e.get(v0);
            if(v1 != null) {
                ((Fragment)v1).performLowMemory();
            }
        }
    }

    public Fragment w() {
        return this.p;
    }

    LayoutInflater$Factory2 x() {
        return this;
    }

    private void y() {
        if(!this.d()) {
            if(this.v == null) {
                return;
            }

            StringBuilder v1 = new StringBuilder();
            v1.append("Can not perform this action inside of ");
            v1.append(this.v);
            throw new IllegalStateException(v1.toString());
        }

        throw new IllegalStateException("Can not perform this action after onSaveInstanceState");
    }

    private void z() {
        this.c = false;
        this.y.clear();
        this.x.clear();
    }
}

