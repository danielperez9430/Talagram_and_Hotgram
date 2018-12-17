package android.support.v7.widget;

import android.support.v4.view.t;
import android.support.v4.view.u;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View$OnAttachStateChangeListener;
import android.view.View$OnHoverListener;
import android.view.View$OnLongClickListener;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.accessibility.AccessibilityManager;

class bn implements View$OnAttachStateChangeListener, View$OnHoverListener, View$OnLongClickListener {
    class android.support.v7.widget.bn$1 implements Runnable {
        android.support.v7.widget.bn$1(bn arg1) {
            this.a = arg1;
            super();
        }

        public void run() {
            this.a.a(false);
        }
    }

    class android.support.v7.widget.bn$2 implements Runnable {
        android.support.v7.widget.bn$2(bn arg1) {
            this.a = arg1;
            super();
        }

        public void run() {
            this.a.a();
        }
    }

    private final View a;
    private final CharSequence b;
    private final int c;
    private final Runnable d;
    private final Runnable e;
    private int f;
    private int g;
    private bo h;
    private boolean i;
    private static bn j;
    private static bn k;

    private bn(View arg2, CharSequence arg3) {
        super();
        this.d = new android.support.v7.widget.bn$1(this);
        this.e = new android.support.v7.widget.bn$2(this);
        this.a = arg2;
        this.b = arg3;
        this.c = u.a(ViewConfiguration.get(this.a.getContext()));
        this.d();
        this.a.setOnLongClickListener(((View$OnLongClickListener)this));
        this.a.setOnHoverListener(((View$OnHoverListener)this));
    }

    public static void a(View arg2, CharSequence arg3) {
        bn v1 = null;
        if(bn.j != null && bn.j.a == arg2) {
            bn.a(v1);
        }

        if(TextUtils.isEmpty(arg3)) {
            if(bn.k != null && bn.k.a == arg2) {
                bn.k.a();
            }

            arg2.setOnLongClickListener(((View$OnLongClickListener)v1));
            arg2.setLongClickable(false);
            arg2.setOnHoverListener(((View$OnHoverListener)v1));
        }
        else {
            new bn(arg2, arg3);
        }
    }

    private static void a(bn arg1) {
        if(bn.j != null) {
            bn.j.c();
        }

        bn.j = arg1;
        if(bn.j != null) {
            bn.j.b();
        }
    }

    void a() {
        bn v1 = null;
        if(bn.k == this) {
            bn.k = v1;
            if(this.h != null) {
                this.h.a();
                this.h = ((bo)v1);
                this.d();
                this.a.removeOnAttachStateChangeListener(((View$OnAttachStateChangeListener)this));
            }
            else {
                Log.e("TooltipCompatHandler", "sActiveHandler.mPopup == null");
            }
        }

        if(bn.j == this) {
            bn.a(v1);
        }

        this.a.removeCallbacks(this.e);
    }

    private boolean a(MotionEvent arg4) {
        int v0 = ((int)arg4.getX());
        int v4 = ((int)arg4.getY());
        if(Math.abs(v0 - this.f) <= this.c && Math.abs(v4 - this.g) <= this.c) {
            return 0;
        }

        this.f = v0;
        this.g = v4;
        return 1;
    }

    void a(boolean arg8) {
        long v0;
        if(!t.D(this.a)) {
            return;
        }

        bn.a(null);
        if(bn.k != null) {
            bn.k.a();
        }

        bn.k = this;
        this.i = arg8;
        this.h = new bo(this.a.getContext());
        this.h.a(this.a, this.f, this.g, this.i, this.b);
        this.a.addOnAttachStateChangeListener(((View$OnAttachStateChangeListener)this));
        if(this.i) {
            v0 = 2500;
        }
        else {
            v0 = (t.r(this.a) & 1) == 1 ? 3000 : 15000;
            v0 -= ((long)ViewConfiguration.getLongPressTimeout());
        }

        this.a.removeCallbacks(this.e);
        this.a.postDelayed(this.e, v0);
    }

    private void b() {
        this.a.postDelayed(this.d, ((long)ViewConfiguration.getLongPressTimeout()));
    }

    private void c() {
        this.a.removeCallbacks(this.d);
    }

    private void d() {
        this.f = 2147483647;
        this.g = 2147483647;
    }

    public boolean onHover(View arg3, MotionEvent arg4) {
        if(this.h != null && (this.i)) {
            return 0;
        }

        Object v3 = this.a.getContext().getSystemService("accessibility");
        if((((AccessibilityManager)v3).isEnabled()) && (((AccessibilityManager)v3).isTouchExplorationEnabled())) {
            return 0;
        }

        int v3_1 = arg4.getAction();
        if(v3_1 != 7) {
            if(v3_1 != 10) {
            }
            else {
                this.d();
                this.a();
            }
        }
        else if((this.a.isEnabled()) && this.h == null && (this.a(arg4))) {
            bn.a(this);
        }

        return 0;
    }

    public boolean onLongClick(View arg2) {
        this.f = arg2.getWidth() / 2;
        this.g = arg2.getHeight() / 2;
        this.a(true);
        return 1;
    }

    public void onViewAttachedToWindow(View arg1) {
    }

    public void onViewDetachedFromWindow(View arg1) {
        this.a();
    }
}

