package android.support.design.bottomappbar;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator$AnimatorUpdateListener;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable$ClassLoaderCreator;
import android.os.Parcelable$Creator;
import android.os.Parcelable;
import android.support.design.behavior.HideBottomViewOnScrollBehavior;
import android.support.design.g.c;
import android.support.design.widget.CoordinatorLayout$a;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.AbsSavedState;
import android.support.v4.view.t;
import android.support.v7.widget.ActionMenuView;
import android.support.v7.widget.Toolbar$b;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class BottomAppBar extends Toolbar implements a {
    public class Behavior extends HideBottomViewOnScrollBehavior {
        private final Rect a;

        public Behavior() {
            super();
            this.a = new Rect();
        }

        public Behavior(Context arg1, AttributeSet arg2) {
            super(arg1, arg2);
            this.a = new Rect();
        }

        private boolean a(FloatingActionButton arg3, BottomAppBar arg4) {
            arg3.getLayoutParams().d = 17;
            BottomAppBar.a(arg4, arg3);
            return 1;
        }

        protected void a(BottomAppBar arg3) {
            super.a(((View)arg3));
            FloatingActionButton v0 = BottomAppBar.c(arg3);
            if(v0 != null) {
                v0.clearAnimation();
                v0.animate().translationY(BottomAppBar.f(arg3)).setInterpolator(android.support.design.a.a.d).setDuration(225);
            }
        }

        protected void a(View arg1) {
            this.a(((BottomAppBar)arg1));
        }

        public boolean a(CoordinatorLayout arg3, BottomAppBar arg4, int arg5) {
            FloatingActionButton v0 = BottomAppBar.c(arg4);
            if(v0 != null) {
                this.a(v0, arg4);
                v0.b(this.a);
                arg4.setFabDiameter(this.a.height());
            }

            if(!BottomAppBar.d(arg4)) {
                BottomAppBar.e(arg4);
            }

            arg3.a(((View)arg4), arg5);
            return super.a(arg3, ((View)arg4), arg5);
        }

        public boolean a(CoordinatorLayout arg2, BottomAppBar arg3, View arg4, View arg5, int arg6, int arg7) {
            boolean v2 = !arg3.getHideOnScroll() || !super.a(arg2, ((View)arg3), arg4, arg5, arg6, arg7) ? false : true;
            return v2;
        }

        public boolean a(CoordinatorLayout arg1, View arg2, int arg3) {
            return this.a(arg1, ((BottomAppBar)arg2), arg3);
        }

        public boolean a(CoordinatorLayout arg1, View arg2, View arg3, View arg4, int arg5, int arg6) {
            return this.a(arg1, ((BottomAppBar)arg2), arg3, arg4, arg5, arg6);
        }

        protected void b(BottomAppBar arg3) {
            super.b(((View)arg3));
            FloatingActionButton v3 = BottomAppBar.c(arg3);
            if(v3 != null) {
                v3.a(this.a);
                float v0 = ((float)(v3.getMeasuredHeight() - this.a.height()));
                v3.clearAnimation();
                v3.animate().translationY((((float)(-v3.getPaddingBottom()))) + v0).setInterpolator(android.support.design.a.a.c).setDuration(175);
            }
        }

        protected void b(View arg1) {
            this.b(((BottomAppBar)arg1));
        }
    }

    class SavedState extends AbsSavedState {
        final class android.support.design.bottomappbar.BottomAppBar$SavedState$1 implements Parcelable$ClassLoaderCreator {
            android.support.design.bottomappbar.BottomAppBar$SavedState$1() {
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
        boolean b;

        static {
            SavedState.CREATOR = new android.support.design.bottomappbar.BottomAppBar$SavedState$1();
        }

        public SavedState(Parcelable arg1) {
            super(arg1);
        }

        public SavedState(Parcel arg1, ClassLoader arg2) {
            super(arg1, arg2);
            this.a = arg1.readInt();
            boolean v1 = arg1.readInt() != 0 ? true : false;
            this.b = v1;
        }

        public void writeToParcel(Parcel arg1, int arg2) {
            super.writeToParcel(arg1, arg2);
            arg1.writeInt(this.a);
            arg1.writeInt(this.b);
        }
    }

    AnimatorListenerAdapter a;
    private final int f;
    private final c g;
    private final android.support.design.bottomappbar.a h;
    private Animator i;
    private Animator j;
    private Animator k;
    private int l;
    private boolean m;
    private boolean n;

    private float a(boolean arg7) {
        FloatingActionButton v0 = this.m();
        if(v0 == null) {
            return 0;
        }

        Rect v2 = new Rect();
        v0.a(v2);
        float v3 = ((float)v2.height());
        if(v3 == 0f) {
            v3 = ((float)v0.getMeasuredHeight());
        }

        float v1 = ((float)(v0.getHeight() - v2.bottom));
        float v2_1 = ((float)(v0.getHeight() - v2.height()));
        float v4 = -this.getCradleVerticalOffset() + v3 / 2f + v1;
        float v0_1 = v2_1 - (((float)v0.getPaddingBottom()));
        v1 = ((float)(-this.getMeasuredHeight()));
        if(arg7) {
            v0_1 = v4;
        }

        return v1 + v0_1;
    }

    static Animator a(BottomAppBar arg0, Animator arg1) {
        arg0.j = arg1;
        return arg1;
    }

    static android.support.design.bottomappbar.a a(BottomAppBar arg0) {
        return arg0.h;
    }

    private void a(int arg2) {
        if(this.l != arg2) {
            if(!t.A(((View)this))) {
            }
            else {
                if(this.j != null) {
                    this.j.cancel();
                }

                ArrayList v0 = new ArrayList();
                this.a(arg2, ((List)v0));
                this.b(arg2, ((List)v0));
                AnimatorSet v2 = new AnimatorSet();
                v2.playTogether(((Collection)v0));
                this.j = ((Animator)v2);
                this.j.addListener(new AnimatorListenerAdapter() {
                    public void onAnimationEnd(Animator arg2) {
                        BottomAppBar.a(this.a, null);
                    }
                });
                this.j.start();
            }
        }
    }

    private void a(int arg4, List arg5) {
        if(!this.n) {
            return;
        }

        ValueAnimator v4 = ValueAnimator.ofFloat(new float[]{this.h.a(), ((float)this.b(arg4))});
        v4.addUpdateListener(new ValueAnimator$AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator arg2) {
                BottomAppBar.a(this.a).a(arg2.getAnimatedValue().floatValue());
                BottomAppBar.b(this.a).invalidateSelf();
            }
        });
        v4.setDuration(300);
        arg5.add(v4);
    }

    private void a(int arg4, boolean arg5) {
        if(!t.A(((View)this))) {
            return;
        }

        if(this.k != null) {
            this.k.cancel();
        }

        ArrayList v0 = new ArrayList();
        this.a(0, false, ((List)v0));
        AnimatorSet v4 = new AnimatorSet();
        v4.playTogether(((Collection)v0));
        this.k = ((Animator)v4);
        this.k.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator arg2) {
                BottomAppBar.b(this.a, null);
            }
        });
        this.k.start();
    }

    private void a(int arg9, boolean arg10, List arg11) {
        ActionMenuView v0 = this.getActionMenuView();
        if(v0 == null) {
            return;
        }

        float[] v3 = new float[1];
        float v4 = 1f;
        v3[0] = v4;
        ObjectAnimator v1 = ObjectAnimator.ofFloat(v0, "alpha", v3);
        if((this.n) || (this.n())) {
            if(this.l != 1) {
                if(arg9 == 1) {
                }
                else {
                label_19:
                    if(v0.getAlpha() < v4) {
                        arg11.add(v1);
                    }
                    else {
                    }

                    return;
                }
            }

            ObjectAnimator v3_1 = ObjectAnimator.ofFloat(v0, "alpha", new float[]{0f});
            ((Animator)v3_1).addListener(new AnimatorListenerAdapter(v0, arg9, arg10) {
                public boolean a;

                public void onAnimationCancel(Animator arg1) {
                    this.a = true;
                }

                public void onAnimationEnd(Animator arg4) {
                    if(!this.a) {
                        BottomAppBar.a(this.e, this.b, this.c, this.d);
                    }
                }
            });
            AnimatorSet v9 = new AnimatorSet();
            v9.setDuration(150);
            v9.playSequentially(new Animator[]{v3_1, v1});
            arg11.add(v9);
        }
        else if(!arg10) {
            goto label_19;
        }
        else {
            goto label_19;
        }
    }

    static void a(BottomAppBar arg0, ActionMenuView arg1, int arg2, boolean arg3) {
        arg0.a(arg1, arg2, arg3);
    }

    static void a(BottomAppBar arg0, FloatingActionButton arg1) {
        arg0.a(arg1);
    }

    private void a(FloatingActionButton arg2) {
        this.b(arg2);
        arg2.c(this.a);
        arg2.a(this.a);
    }

    private void a(ActionMenuView arg9, int arg10, boolean arg11) {
        int v0 = t.f(((View)this)) == 1 ? 1 : 0;
        int v3 = 0;
        int v4 = 0;
        while(v3 < this.getChildCount()) {
            View v5 = this.getChildAt(v3);
            int v6 = !(v5.getLayoutParams() instanceof b) || (v5.getLayoutParams().a & 8388615) != 8388611 ? 0 : 1;
            if(v6 != 0) {
                int v5_1 = v0 != 0 ? v5.getLeft() : v5.getRight();
                v4 = Math.max(v4, v5_1);
            }

            ++v3;
        }

        v0 = v0 != 0 ? arg9.getRight() : arg9.getLeft();
        v4 -= v0;
        float v10 = arg10 != 1 || !arg11 ? 0f : ((float)v4);
        arg9.setTranslationX(v10);
    }

    private void b(int arg5, List arg6) {
        ObjectAnimator v5 = ObjectAnimator.ofFloat(this.m(), "translationX", new float[]{((float)this.b(arg5))});
        v5.setDuration(300);
        arg6.add(v5);
    }

    private int b(int arg4) {
        int v1 = 0;
        int v2 = 1;
        int v0 = t.f(((View)this)) == 1 ? 1 : 0;
        if(arg4 == 1) {
            arg4 = this.getMeasuredWidth() / 2 - this.f;
            if(v0 != 0) {
                v2 = -1;
            }

            v1 = arg4 * v2;
        }

        return v1;
    }

    static c b(BottomAppBar arg0) {
        return arg0.g;
    }

    static Animator b(BottomAppBar arg0, Animator arg1) {
        arg0.k = arg1;
        return arg1;
    }

    private void b(FloatingActionButton arg2) {
        arg2.d(this.a);
        arg2.b(this.a);
    }

    static FloatingActionButton c(BottomAppBar arg0) {
        return arg0.m();
    }

    static boolean d(BottomAppBar arg0) {
        return arg0.p();
    }

    static void e(BottomAppBar arg0) {
        arg0.q();
    }

    static float f(BottomAppBar arg0) {
        return arg0.getFabTranslationY();
    }

    private ActionMenuView getActionMenuView() {
        int v0;
        for(v0 = 0; v0 < this.getChildCount(); ++v0) {
            View v1 = this.getChildAt(v0);
            if((v1 instanceof ActionMenuView)) {
                return ((ActionMenuView)v1);
            }
        }

        return null;
    }

    public ColorStateList getBackgroundTint() {
        return this.g.a();
    }

    public android.support.design.widget.CoordinatorLayout$b getBehavior() {
        return new Behavior();
    }

    public float getCradleVerticalOffset() {
        return this.h.b();
    }

    public int getFabAlignmentMode() {
        return this.l;
    }

    public float getFabCradleMargin() {
        return this.h.d();
    }

    public float getFabCradleRoundedCornerRadius() {
        return this.h.e();
    }

    private float getFabTranslationX() {
        return ((float)this.b(this.l));
    }

    private float getFabTranslationY() {
        return this.a(this.n);
    }

    public boolean getHideOnScroll() {
        return this.m;
    }

    private FloatingActionButton m() {
        Object v2;
        FloatingActionButton v1 = null;
        if(!(this.getParent() instanceof CoordinatorLayout)) {
            return v1;
        }

        Iterator v0 = this.getParent().d(((View)this)).iterator();
        do {
            if(!v0.hasNext()) {
                return v1;
            }

            v2 = v0.next();
        }
        while(!(v2 instanceof FloatingActionButton));

        return ((FloatingActionButton)v2);
    }

    private boolean n() {
        FloatingActionButton v0 = this.m();
        boolean v0_1 = v0 == null || !v0.b() ? false : true;
        return v0_1;
    }

    private void o() {
        if(this.i != null) {
            this.i.cancel();
        }

        if(this.k != null) {
            this.k.cancel();
        }

        if(this.j != null) {
            this.j.cancel();
        }
    }

    protected void onLayout(boolean arg1, int arg2, int arg3, int arg4, int arg5) {
        super.onLayout(arg1, arg2, arg3, arg4, arg5);
        this.o();
        this.q();
    }

    protected void onRestoreInstanceState(Parcelable arg2) {
        if(!(arg2 instanceof SavedState)) {
            super.onRestoreInstanceState(arg2);
            return;
        }

        super.onRestoreInstanceState(((SavedState)arg2).getSuperState());
        this.l = ((SavedState)arg2).a;
        this.n = ((SavedState)arg2).b;
    }

    protected Parcelable onSaveInstanceState() {
        SavedState v1 = new SavedState(super.onSaveInstanceState());
        v1.a = this.l;
        v1.b = this.n;
        return ((Parcelable)v1);
    }

    private boolean p() {
        boolean v0;
        if(this.i == null || !this.i.isRunning()) {
            if(this.k != null && (this.k.isRunning())) {
                goto label_15;
            }

            if(this.j != null && (this.j.isRunning())) {
            label_15:
                v0 = true;
                return v0;
            }

            v0 = false;
        }
        else {
            goto label_15;
        }

        return v0;
    }

    private void q() {
        this.h.a(this.getFabTranslationX());
        FloatingActionButton v0 = this.m();
        c v1 = this.g;
        float v3 = 1f;
        float v2 = !this.n || !this.n() ? 0f : 1f;
        v1.a(v2);
        if(v0 != null) {
            v0.setTranslationY(this.getFabTranslationY());
            v0.setTranslationX(this.getFabTranslationX());
        }

        ActionMenuView v0_1 = this.getActionMenuView();
        if(v0_1 != null) {
            v0_1.setAlpha(v3);
            if(!this.n()) {
                this.a(v0_1, 0, false);
            }
            else {
                this.a(v0_1, this.l, this.n);
            }
        }
    }

    public void setBackgroundTint(ColorStateList arg2) {
        android.support.v4.graphics.drawable.a.a(this.g, arg2);
    }

    public void setCradleVerticalOffset(float arg2) {
        if(arg2 != this.getCradleVerticalOffset()) {
            this.h.b(arg2);
            this.g.invalidateSelf();
        }
    }

    public void setFabAlignmentMode(int arg2) {
        this.a(arg2);
        this.a(arg2, this.n);
        this.l = arg2;
    }

    public void setFabCradleMargin(float arg2) {
        if(arg2 != this.getFabCradleMargin()) {
            this.h.d(arg2);
            this.g.invalidateSelf();
        }
    }

    public void setFabCradleRoundedCornerRadius(float arg2) {
        if(arg2 != this.getFabCradleRoundedCornerRadius()) {
            this.h.e(arg2);
            this.g.invalidateSelf();
        }
    }

    void setFabDiameter(int arg2) {
        float v2 = ((float)arg2);
        if(v2 != this.h.c()) {
            this.h.c(v2);
            this.g.invalidateSelf();
        }
    }

    public void setHideOnScroll(boolean arg1) {
        this.m = arg1;
    }

    public void setSubtitle(CharSequence arg1) {
    }

    public void setTitle(CharSequence arg1) {
    }
}

