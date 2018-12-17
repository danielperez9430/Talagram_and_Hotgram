package android.support.v7.view.menu;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Build$VERSION;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.view.t;
import android.support.v7.a.a$d;
import android.support.v7.a.a$g;
import android.support.v7.widget.ar;
import android.support.v7.widget.as;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View$OnAttachStateChangeListener;
import android.view.View$OnKeyListener;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import android.view.ViewTreeObserver;
import android.widget.AdapterView$OnItemClickListener;
import android.widget.FrameLayout;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow$OnDismissListener;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

final class e extends m implements o, View$OnKeyListener, PopupWindow$OnDismissListener {
    class android.support.v7.view.menu.e$1 implements ViewTreeObserver$OnGlobalLayoutListener {
        android.support.v7.view.menu.e$1(e arg1) {
            this.a = arg1;
            super();
        }

        public void onGlobalLayout() {
            if((this.a.d()) && this.a.b.size() > 0 && !this.a.b.get(0).a.g()) {
                View v0 = this.a.d;
                if(v0 != null) {
                    if(!v0.isShown()) {
                    }
                    else {
                        Iterator v0_1 = this.a.b.iterator();
                        while(true) {
                            if(v0_1.hasNext()) {
                                v0_1.next().a.a();
                                continue;
                            }
                            else {
                                return;
                            }
                        }
                    }
                }

                this.a.c();
            }
        }
    }

    class android.support.v7.view.menu.e$2 implements View$OnAttachStateChangeListener {
        android.support.v7.view.menu.e$2(e arg1) {
            this.a = arg1;
            super();
        }

        public void onViewAttachedToWindow(View arg1) {
        }

        public void onViewDetachedFromWindow(View arg3) {
            if(this.a.e != null) {
                if(!this.a.e.isAlive()) {
                    this.a.e = arg3.getViewTreeObserver();
                }

                this.a.e.removeGlobalOnLayoutListener(this.a.c);
            }

            arg3.removeOnAttachStateChangeListener(((View$OnAttachStateChangeListener)this));
        }
    }

    class android.support.v7.view.menu.e$3 implements ar {
        android.support.v7.view.menu.e$3(e arg1) {
            this.a = arg1;
            super();
        }

        public void a(h arg1, MenuItem arg2) {
            this.a.a.removeCallbacksAndMessages(arg1);
        }

        public void b(h arg6, MenuItem arg7) {
            Object v1 = null;
            this.a.a.removeCallbacksAndMessages(v1);
            int v0 = this.a.b.size();
            int v2 = 0;
            while(true) {
                int v3 = -1;
                if(v2 >= v0) {
                    break;
                }
                else if(arg6 == this.a.b.get(v2).b) {
                }
                else {
                    ++v2;
                    continue;
                }

                goto label_19;
            }

            v2 = -1;
        label_19:
            if(v2 == v3) {
                return;
            }

            ++v2;
            if(v2 < this.a.b.size()) {
                v1 = this.a.b.get(v2);
            }

            this.a.a.postAtTime(new Runnable(((a)v1), arg7, arg6) {
                public void run() {
                    if(this.a != null) {
                        this.d.a.f = true;
                        this.a.b.a(false);
                        this.d.a.f = false;
                    }

                    if((this.b.isEnabled()) && (this.b.hasSubMenu())) {
                        this.c.a(this.b, 4);
                    }
                }
            }, arg6, SystemClock.uptimeMillis() + 200);
        }
    }

    class a {
        public final as a;
        public final h b;
        public final int c;

        public a(as arg1, h arg2, int arg3) {
            super();
            this.a = arg1;
            this.b = arg2;
            this.c = arg3;
        }

        public ListView a() {
            return this.a.e();
        }
    }

    private PopupWindow$OnDismissListener A;
    final Handler a;
    final List b;
    final ViewTreeObserver$OnGlobalLayoutListener c;
    View d;
    ViewTreeObserver e;
    boolean f;
    private static final int g;
    private final Context h;
    private final int i;
    private final int j;
    private final int k;
    private final boolean l;
    private final List m;
    private final View$OnAttachStateChangeListener n;
    private final ar o;
    private int p;
    private int q;
    private View r;
    private int s;
    private boolean t;
    private boolean u;
    private int v;
    private int w;
    private boolean x;
    private boolean y;
    private android.support.v7.view.menu.o$a z;

    static {
        e.g = g.abc_cascading_menu_item_layout;
    }

    public e(Context arg2, View arg3, int arg4, int arg5, boolean arg6) {
        super();
        this.m = new ArrayList();
        this.b = new ArrayList();
        this.c = new android.support.v7.view.menu.e$1(this);
        this.n = new android.support.v7.view.menu.e$2(this);
        this.o = new android.support.v7.view.menu.e$3(this);
        this.p = 0;
        this.q = 0;
        this.h = arg2;
        this.r = arg3;
        this.j = arg4;
        this.k = arg5;
        this.l = arg6;
        this.x = false;
        this.s = this.i();
        Resources v2 = arg2.getResources();
        this.i = Math.max(v2.getDisplayMetrics().widthPixels / 2, v2.getDimensionPixelSize(d.abc_config_prefDialogWidth));
        this.a = new Handler();
    }

    private MenuItem a(h arg5, h arg6) {
        int v0 = arg5.size();
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            MenuItem v2 = arg5.getItem(v1);
            if((v2.hasSubMenu()) && arg6 == v2.getSubMenu()) {
                return v2;
            }
        }

        return null;
    }

    private View a(a arg8, h arg9) {
        int v2;
        MenuItem v9 = this.a(arg8.b, arg9);
        View v0 = null;
        if(v9 == null) {
            return v0;
        }

        ListView v8 = arg8.a();
        ListAdapter v1 = v8.getAdapter();
        int v3 = 0;
        if((v1 instanceof HeaderViewListAdapter)) {
            v2 = ((HeaderViewListAdapter)v1).getHeadersCount();
            v1 = ((HeaderViewListAdapter)v1).getWrappedAdapter();
        }
        else {
            v2 = 0;
        }

        int v4 = ((android.support.v7.view.menu.g)v1).getCount();
        while(true) {
            int v5 = -1;
            if(v3 >= v4) {
                break;
            }
            else if(v9 == ((android.support.v7.view.menu.g)v1).a(v3)) {
            }
            else {
                ++v3;
                continue;
            }

            goto label_23;
        }

        v3 = -1;
    label_23:
        if(v3 == v5) {
            return v0;
        }

        v3 = v3 + v2 - v8.getFirstVisiblePosition();
        if(v3 >= 0) {
            if(v3 >= v8.getChildCount()) {
            }
            else {
                return v8.getChildAt(v3);
            }
        }

        return v0;
    }

    public void a() {
        if(this.d()) {
            return;
        }

        Iterator v0 = this.m.iterator();
        while(v0.hasNext()) {
            this.c(v0.next());
        }

        this.m.clear();
        this.d = this.r;
        if(this.d != null) {
            int v0_1 = this.e == null ? 1 : 0;
            this.e = this.d.getViewTreeObserver();
            if(v0_1 != 0) {
                this.e.addOnGlobalLayoutListener(this.c);
            }

            this.d.addOnAttachStateChangeListener(this.n);
        }
    }

    public void a(int arg2) {
        if(this.p != arg2) {
            this.p = arg2;
            this.q = android.support.v4.view.d.a(arg2, t.f(this.r));
        }
    }

    public void a(h arg2) {
        arg2.a(((o)this), this.h);
        if(this.d()) {
            this.c(arg2);
        }
        else {
            this.m.add(arg2);
        }
    }

    public void a(h arg6, boolean arg7) {
        int v0 = this.d(arg6);
        if(v0 < 0) {
            return;
        }

        int v1 = v0 + 1;
        if(v1 < this.b.size()) {
            this.b.get(v1).b.a(false);
        }

        Object v0_1 = this.b.remove(v0);
        ((a)v0_1).b.b(((o)this));
        Object v2 = null;
        if(this.f) {
            ((a)v0_1).a.b(v2);
            ((a)v0_1).a.b(0);
        }

        ((a)v0_1).a.c();
        v0 = this.b.size();
        v1 = v0 > 0 ? this.b.get(v0 - 1).c : this.i();
        this.s = v1;
        if(v0 == 0) {
            this.c();
            if(this.z != null) {
                this.z.a(arg6, true);
            }

            if(this.e != null) {
                if(this.e.isAlive()) {
                    this.e.removeGlobalOnLayoutListener(this.c);
                }

                this.e = ((ViewTreeObserver)v2);
            }

            this.d.removeOnAttachStateChangeListener(this.n);
            this.A.onDismiss();
        }
        else {
            if(!arg7) {
                return;
            }

            this.b.get(0).b.a(false);
        }
    }

    public void a(android.support.v7.view.menu.o$a arg1) {
        this.z = arg1;
    }

    public void a(View arg2) {
        if(this.r != arg2) {
            this.r = arg2;
            this.q = android.support.v4.view.d.a(this.p, t.f(this.r));
        }
    }

    public void a(PopupWindow$OnDismissListener arg1) {
        this.A = arg1;
    }

    public void a(boolean arg1) {
        this.x = arg1;
    }

    public boolean a(u arg5) {
        Object v1;
        Iterator v0 = this.b.iterator();
        do {
            if(!v0.hasNext()) {
                goto label_11;
            }

            v1 = v0.next();
        }
        while(arg5 != ((a)v1).b);

        ((a)v1).a().requestFocus();
        return 1;
    label_11:
        if(arg5.hasVisibleItems()) {
            this.a(((h)arg5));
            if(this.z != null) {
                this.z.a(((h)arg5));
            }

            return 1;
        }

        return 0;
    }

    public void b(int arg2) {
        this.t = true;
        this.v = arg2;
    }

    public void b(boolean arg2) {
        Iterator v2 = this.b.iterator();
        while(v2.hasNext()) {
            e.a(v2.next().a().getAdapter()).notifyDataSetChanged();
        }
    }

    public boolean b() {
        return 0;
    }

    private void c(h arg15) {
        int v12;
        View v6;
        Object v1_1;
        LayoutInflater v0 = LayoutInflater.from(this.h);
        android.support.v7.view.menu.g v1 = new android.support.v7.view.menu.g(arg15, v0, this.l, e.g);
        if(!this.d() && (this.x)) {
            v1.a(true);
        }
        else if(this.d()) {
            v1.a(m.b(arg15));
        }

        ViewGroup v5 = null;
        int v2 = e.a(((ListAdapter)v1), v5, this.h, this.i);
        as v4 = this.h();
        v4.a(((ListAdapter)v1));
        v4.g(v2);
        v4.e(this.q);
        if(this.b.size() > 0) {
            v1_1 = this.b.get(this.b.size() - 1);
            v6 = this.a(((a)v1_1), arg15);
        }
        else {
            v1_1 = v5;
            v6 = ((View)v1_1);
        }

        if(v6 != null) {
            v4.c(false);
            v4.a(v5);
            int v8 = this.d(v2);
            int v9 = v8 == 1 ? 1 : 0;
            this.s = v8;
            int v11 = 5;
            if(Build$VERSION.SDK_INT >= 26) {
                v4.b(v6);
                v8 = 0;
                v12 = 0;
            }
            else {
                int[] v10 = new int[2];
                this.r.getLocationOnScreen(v10);
                int[] v8_1 = new int[2];
                v6.getLocationOnScreen(v8_1);
                if((this.q & 7) == v11) {
                    v10[0] += this.r.getWidth();
                    v8_1[0] += v6.getWidth();
                }

                v12 = v8_1[0] - v10[0];
                v8 = v8_1[1] - v10[1];
            }

            if((this.q & v11) == v11) {
                if(v9 == 0) {
                    v2 = v6.getWidth();
                    goto label_87;
                }
                else {
                    goto label_84;
                }
            }
            else if(v9 != 0) {
                v2 = v6.getWidth();
            label_84:
                v12 += v2;
            }
            else {
            label_87:
                v12 -= v2;
            }

            v4.c(v12);
            v4.b(true);
            v4.d(v8);
        }
        else {
            if(this.t) {
                v4.c(this.v);
            }

            if(this.u) {
                v4.d(this.w);
            }

            v4.a(this.g());
        }

        this.b.add(new a(v4, arg15, this.s));
        v4.a();
        ListView v2_1 = v4.e();
        v2_1.setOnKeyListener(((View$OnKeyListener)this));
        if(v1_1 == null && (this.y) && arg15.n() != null) {
            View v0_1 = v0.inflate(g.abc_popup_menu_header_item_layout, ((ViewGroup)v2_1), false);
            View v1_2 = ((FrameLayout)v0_1).findViewById(16908310);
            ((FrameLayout)v0_1).setEnabled(false);
            ((TextView)v1_2).setText(arg15.n());
            v2_1.addHeaderView(v0_1, v5, false);
            v4.a();
        }
    }

    public void c() {
        int v0 = this.b.size();
        if(v0 > 0) {
            Object[] v1 = this.b.toArray(new a[v0]);
            --v0;
            while(v0 >= 0) {
                Object v2 = v1[v0];
                if(((a)v2).a.d()) {
                    ((a)v2).a.c();
                }

                --v0;
            }
        }
    }

    public void c(int arg2) {
        this.u = true;
        this.w = arg2;
    }

    public void c(boolean arg1) {
        this.y = arg1;
    }

    public boolean d() {
        boolean v1 = false;
        if(this.b.size() > 0 && (this.b.get(0).a.d())) {
            v1 = true;
        }

        return v1;
    }

    private int d(int arg7) {
        ListView v0 = this.b.get(this.b.size() - 1).a();
        int[] v1 = new int[2];
        v0.getLocationOnScreen(v1);
        Rect v3 = new Rect();
        this.d.getWindowVisibleDisplayFrame(v3);
        if(this.s == 1) {
            if(v1[0] + v0.getWidth() + arg7 > v3.right) {
                return 0;
            }

            return 1;
        }

        if(v1[0] - arg7 < 0) {
            return 1;
        }

        return 0;
    }

    private int d(h arg4) {
        int v0 = this.b.size();
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            if(arg4 == this.b.get(v1).b) {
                return v1;
            }
        }

        return -1;
    }

    public ListView e() {
        ListView v0 = this.b.isEmpty() ? null : this.b.get(this.b.size() - 1).a();
        return v0;
    }

    protected boolean f() {
        return 0;
    }

    private as h() {
        as v0 = new as(this.h, null, this.j, this.k);
        v0.a(this.o);
        v0.a(((AdapterView$OnItemClickListener)this));
        v0.a(((PopupWindow$OnDismissListener)this));
        v0.b(this.r);
        v0.e(this.q);
        v0.a(true);
        v0.h(2);
        return v0;
    }

    private int i() {
        int v1 = 1;
        if(t.f(this.r) == 1) {
            v1 = 0;
        }

        return v1;
    }

    public void onDismiss() {
        Object v3;
        int v0 = this.b.size();
        int v2 = 0;
        while(true) {
            if(v2 < v0) {
                v3 = this.b.get(v2);
                if(!((a)v3).a.d()) {
                }
                else {
                    ++v2;
                    continue;
                }
            }
            else {
                break;
            }

            goto label_14;
        }

        v3 = null;
    label_14:
        if(v3 != null) {
            ((a)v3).b.a(false);
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

