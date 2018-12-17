package android.support.v7.view.menu;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.a.a$d;
import android.support.v7.widget.as;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View$OnAttachStateChangeListener;
import android.view.View$OnKeyListener;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import android.view.ViewTreeObserver;
import android.widget.AdapterView$OnItemClickListener;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.PopupWindow$OnDismissListener;
import android.widget.TextView;

final class t extends m implements o, View$OnKeyListener, AdapterView$OnItemClickListener, PopupWindow$OnDismissListener {
    class android.support.v7.view.menu.t$1 implements ViewTreeObserver$OnGlobalLayoutListener {
        android.support.v7.view.menu.t$1(t arg1) {
            this.a = arg1;
            super();
        }

        public void onGlobalLayout() {
            if((this.a.d()) && !this.a.a.g()) {
                View v0 = this.a.c;
                if(v0 != null) {
                    if(!v0.isShown()) {
                    }
                    else {
                        this.a.a.a();
                        return;
                    }
                }

                this.a.c();
            }
        }
    }

    class android.support.v7.view.menu.t$2 implements View$OnAttachStateChangeListener {
        android.support.v7.view.menu.t$2(t arg1) {
            this.a = arg1;
            super();
        }

        public void onViewAttachedToWindow(View arg1) {
        }

        public void onViewDetachedFromWindow(View arg3) {
            if(this.a.d != null) {
                if(!this.a.d.isAlive()) {
                    this.a.d = arg3.getViewTreeObserver();
                }

                this.a.d.removeGlobalOnLayoutListener(this.a.b);
            }

            arg3.removeOnAttachStateChangeListener(((View$OnAttachStateChangeListener)this));
        }
    }

    final as a;
    final ViewTreeObserver$OnGlobalLayoutListener b;
    View c;
    ViewTreeObserver d;
    private static final int e;
    private final Context f;
    private final h g;
    private final g h;
    private final boolean i;
    private final int j;
    private final int k;
    private final int l;
    private final View$OnAttachStateChangeListener m;
    private PopupWindow$OnDismissListener n;
    private View o;
    private a p;
    private boolean q;
    private boolean r;
    private int s;
    private int t;
    private boolean u;

    static {
        t.e = android.support.v7.a.a$g.abc_popup_menu_item_layout;
    }

    public t(Context arg4, h arg5, View arg6, int arg7, int arg8, boolean arg9) {
        super();
        this.b = new android.support.v7.view.menu.t$1(this);
        this.m = new android.support.v7.view.menu.t$2(this);
        this.t = 0;
        this.f = arg4;
        this.g = arg5;
        this.i = arg9;
        this.h = new g(arg5, LayoutInflater.from(arg4), this.i, t.e);
        this.k = arg7;
        this.l = arg8;
        Resources v7 = arg4.getResources();
        this.j = Math.max(v7.getDisplayMetrics().widthPixels / 2, v7.getDimensionPixelSize(d.abc_config_prefDialogWidth));
        this.o = arg6;
        this.a = new as(this.f, null, this.k, this.l);
        arg5.a(((o)this), arg4);
    }

    public void a() {
        if(this.h()) {
            return;
        }

        throw new IllegalStateException("StandardMenuPopup cannot be used without an anchor");
    }

    public void a(int arg1) {
        this.t = arg1;
    }

    public void a(h arg1) {
    }

    public void a(h arg2, boolean arg3) {
        if(arg2 != this.g) {
            return;
        }

        this.c();
        if(this.p != null) {
            this.p.a(arg2, arg3);
        }
    }

    public void a(a arg1) {
        this.p = arg1;
    }

    public void a(View arg1) {
        this.o = arg1;
    }

    public void a(PopupWindow$OnDismissListener arg1) {
        this.n = arg1;
    }

    public void a(boolean arg2) {
        this.h.a(arg2);
    }

    public boolean a(u arg10) {
        if(arg10.hasVisibleItems()) {
            n v0 = new n(this.f, arg10, this.c, this.i, this.k, this.l);
            v0.a(this.p);
            v0.a(m.b(((h)arg10)));
            v0.a(this.n);
            this.n = null;
            this.g.a(false);
            int v2 = this.a.j();
            int v3 = this.a.k();
            if((Gravity.getAbsoluteGravity(this.t, android.support.v4.view.t.f(this.o)) & 7) == 5) {
                v2 += this.o.getWidth();
            }

            if(!v0.a(v2, v3)) {
                return 0;
            }

            if(this.p != null) {
                this.p.a(((h)arg10));
            }

            return 1;
        }

        return 0;
    }

    public void b(int arg2) {
        this.a.c(arg2);
    }

    public void b(boolean arg1) {
        this.r = false;
        if(this.h != null) {
            this.h.notifyDataSetChanged();
        }
    }

    public boolean b() {
        return 0;
    }

    public void c() {
        if(this.d()) {
            this.a.c();
        }
    }

    public void c(int arg2) {
        this.a.d(arg2);
    }

    public void c(boolean arg1) {
        this.u = arg1;
    }

    public boolean d() {
        boolean v0 = (this.q) || !this.a.d() ? false : true;
        return v0;
    }

    public ListView e() {
        return this.a.e();
    }

    private boolean h() {
        if(this.d()) {
            return 1;
        }

        if(!this.q) {
            if(this.o == null) {
            }
            else {
                this.c = this.o;
                this.a.a(((PopupWindow$OnDismissListener)this));
                this.a.a(((AdapterView$OnItemClickListener)this));
                this.a.a(true);
                View v0 = this.c;
                int v3 = this.d == null ? 1 : 0;
                this.d = v0.getViewTreeObserver();
                if(v3 != 0) {
                    this.d.addOnGlobalLayoutListener(this.b);
                }

                v0.addOnAttachStateChangeListener(this.m);
                this.a.b(v0);
                this.a.e(this.t);
                ViewGroup v3_1 = null;
                if(!this.r) {
                    this.s = t.a(this.h, v3_1, this.f, this.j);
                    this.r = true;
                }

                this.a.g(this.s);
                this.a.h(2);
                this.a.a(this.g());
                this.a.a();
                ListView v0_1 = this.a.e();
                v0_1.setOnKeyListener(((View$OnKeyListener)this));
                if((this.u) && this.g.n() != null) {
                    View v4 = LayoutInflater.from(this.f).inflate(android.support.v7.a.a$g.abc_popup_menu_header_item_layout, ((ViewGroup)v0_1), false);
                    View v5 = ((FrameLayout)v4).findViewById(16908310);
                    if(v5 != null) {
                        ((TextView)v5).setText(this.g.n());
                    }

                    ((FrameLayout)v4).setEnabled(false);
                    v0_1.addHeaderView(v4, v3_1, false);
                }

                this.a.a(this.h);
                this.a.a();
                return 1;
            }
        }

        return 0;
    }

    public void onDismiss() {
        this.q = true;
        this.g.close();
        if(this.d != null) {
            if(!this.d.isAlive()) {
                this.d = this.c.getViewTreeObserver();
            }

            this.d.removeGlobalOnLayoutListener(this.b);
            this.d = null;
        }

        this.c.removeOnAttachStateChangeListener(this.m);
        if(this.n != null) {
            this.n.onDismiss();
        }
    }

    public boolean onKey(View arg1, int arg2, KeyEvent arg3) {
        if(arg3.getAction() == 1 && arg2 == 82) {
            this.c();
            return 1;
        }

        return 0;
    }
}

