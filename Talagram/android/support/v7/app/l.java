package android.support.v7.app;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.support.v4.view.aa;
import android.support.v4.view.t;
import android.support.v4.view.x;
import android.support.v4.view.y;
import android.support.v4.view.z;
import android.support.v7.a.a$f;
import android.support.v7.a.a$j;
import android.support.v7.view.b;
import android.support.v7.view.g;
import android.support.v7.view.menu.h;
import android.support.v7.widget.ActionBarContainer;
import android.support.v7.widget.ActionBarContextView;
import android.support.v7.widget.ActionBarOverlayLayout;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.ag;
import android.support.v7.widget.bc;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class l extends a implements android.support.v7.widget.ActionBarOverlayLayout$a {
    class android.support.v7.app.l$1 extends z {
        android.support.v7.app.l$1(l arg1) {
            this.a = arg1;
            super();
        }

        public void b(View arg2) {
            if((this.a.k) && this.a.f != null) {
                this.a.f.setTranslationY(0f);
                this.a.c.setTranslationY(0f);
            }

            this.a.c.setVisibility(8);
            this.a.c.setTransitioning(false);
            this.a.n = null;
            this.a.h();
            if(this.a.b != null) {
                t.s(this.a.b);
            }
        }
    }

    class android.support.v7.app.l$2 extends z {
        android.support.v7.app.l$2(l arg1) {
            this.a = arg1;
            super();
        }

        public void b(View arg2) {
            this.a.n = null;
            this.a.c.requestLayout();
        }
    }

    class android.support.v7.app.l$3 implements aa {
        android.support.v7.app.l$3(l arg1) {
            this.a = arg1;
            super();
        }

        public void a(View arg1) {
            this.a.c.getParent().invalidate();
        }
    }

    public class android.support.v7.app.l$a extends b implements android.support.v7.view.menu.h$a {
        private final Context b;
        private final h c;
        private android.support.v7.view.b$a d;
        private WeakReference e;

        public android.support.v7.app.l$a(l arg1, Context arg2, android.support.v7.view.b$a arg3) {
            this.a = arg1;
            super();
            this.b = arg2;
            this.d = arg3;
            this.c = new h(arg2).a(1);
            this.c.a(((android.support.v7.view.menu.h$a)this));
        }

        public MenuInflater a() {
            return new g(this.b);
        }

        public void a(int arg2) {
            this.b(this.a.a.getResources().getString(arg2));
        }

        public void a(h arg1) {
            if(this.d == null) {
                return;
            }

            this.d();
            this.a.e.a();
        }

        public void a(View arg2) {
            this.a.e.setCustomView(arg2);
            this.e = new WeakReference(arg2);
        }

        public void a(CharSequence arg2) {
            this.a.e.setSubtitle(arg2);
        }

        public void a(boolean arg2) {
            super.a(arg2);
            this.a.e.setTitleOptional(arg2);
        }

        public boolean a(h arg1, MenuItem arg2) {
            if(this.d != null) {
                return this.d.a(((b)this), arg2);
            }

            return 0;
        }

        public Menu b() {
            return this.c;
        }

        public void b(CharSequence arg2) {
            this.a.e.setTitle(arg2);
        }

        public void b(int arg2) {
            this.a(this.a.a.getResources().getString(arg2));
        }

        public void c() {
            if(this.a.h != this) {
                return;
            }

            if(!l.a(this.a.l, this.a.m, false)) {
                this.a.i = ((b)this);
                this.a.j = this.d;
            }
            else {
                this.d.a(((b)this));
            }

            this.d = null;
            this.a.j(false);
            this.a.e.b();
            this.a.d.a().sendAccessibilityEvent(32);
            this.a.b.setHideOnContentScrollEnabled(this.a.o);
            this.a.h = null;
        }

        public void d() {
            if(this.a.h != this) {
                return;
            }

            this.c.h();
            try {
                this.d.b(((b)this), this.c);
            }
            catch(Throwable v0) {
                this.c.i();
                throw v0;
            }

            this.c.i();
        }

        public boolean e() {
            boolean v0_1;
            this.c.h();
            try {
                v0_1 = this.d.a(((b)this), this.c);
            }
            catch(Throwable v0) {
                this.c.i();
                throw v0;
            }

            this.c.i();
            return v0_1;
        }

        public CharSequence f() {
            return this.a.e.getTitle();
        }

        public CharSequence g() {
            return this.a.e.getSubtitle();
        }

        public boolean h() {
            return this.a.e.d();
        }

        public View i() {
            View v0_1;
            if(this.e != null) {
                Object v0 = this.e.get();
            }
            else {
                v0_1 = null;
            }

            return v0_1;
        }
    }

    private boolean A;
    private boolean B;
    private ArrayList C;
    private boolean D;
    private int E;
    private boolean F;
    private boolean G;
    private boolean H;
    Context a;
    ActionBarOverlayLayout b;
    ActionBarContainer c;
    ag d;
    ActionBarContextView e;
    View f;
    bc g;
    android.support.v7.app.l$a h;
    b i;
    android.support.v7.view.b$a j;
    boolean k;
    boolean l;
    boolean m;
    android.support.v7.view.h n;
    boolean o;
    final y p;
    final y q;
    final aa r;
    private static final Interpolator t;
    private static final Interpolator u;
    private Context v;
    private Activity w;
    private Dialog x;
    private ArrayList y;
    private int z;

    static {
        l.s = l.class.desiredAssertionStatus() ^ 1;
        l.t = new AccelerateInterpolator();
        l.u = new DecelerateInterpolator();
    }

    public l(Activity arg2, boolean arg3) {
        super();
        this.y = new ArrayList();
        this.z = -1;
        this.C = new ArrayList();
        this.E = 0;
        this.k = true;
        this.G = true;
        this.p = new android.support.v7.app.l$1(this);
        this.q = new android.support.v7.app.l$2(this);
        this.r = new android.support.v7.app.l$3(this);
        this.w = arg2;
        View v2 = arg2.getWindow().getDecorView();
        this.a(v2);
        if(!arg3) {
            this.f = v2.findViewById(16908290);
        }
    }

    public l(Dialog arg2) {
        super();
        this.y = new ArrayList();
        this.z = -1;
        this.C = new ArrayList();
        this.E = 0;
        this.k = true;
        this.G = true;
        this.p = new android.support.v7.app.l$1(this);
        this.q = new android.support.v7.app.l$2(this);
        this.r = new android.support.v7.app.l$3(this);
        this.x = arg2;
        this.a(arg2.getWindow().getDecorView());
    }

    private void a(View arg6) {
        this.b = arg6.findViewById(f.decor_content_parent);
        if(this.b != null) {
            this.b.setActionBarVisibilityCallback(((android.support.v7.widget.ActionBarOverlayLayout$a)this));
        }

        this.d = this.b(arg6.findViewById(f.action_bar));
        this.e = arg6.findViewById(f.action_context_bar);
        this.c = arg6.findViewById(f.action_bar_container);
        if(this.d != null && this.e != null && this.c != null) {
            this.a = this.d.b();
            int v6 = (this.d.o() & 4) != 0 ? 1 : 0;
            if(v6 != 0) {
                this.A = true;
            }

            android.support.v7.view.a v2 = android.support.v7.view.a.a(this.a);
            boolean v6_1 = (v2.f()) || v6 != 0 ? true : false;
            this.a(v6_1);
            this.k(v2.d());
            TypedArray v6_2 = this.a.obtainStyledAttributes(null, j.ActionBar, android.support.v7.a.a$a.actionBarStyle, 0);
            if(v6_2.getBoolean(j.ActionBar_hideOnContentScroll, false)) {
                this.b(true);
            }

            int v0 = v6_2.getDimensionPixelSize(j.ActionBar_elevation, 0);
            if(v0 != 0) {
                this.a(((float)v0));
            }

            v6_2.recycle();
            return;
        }

        StringBuilder v0_1 = new StringBuilder();
        v0_1.append(this.getClass().getSimpleName());
        v0_1.append(" can only be used ");
        v0_1.append("with a compatible window decor layout");
        throw new IllegalStateException(v0_1.toString());
    }

    public void a(boolean arg2) {
        this.d.b(arg2);
    }

    public void a(float arg2) {
        t.k(this.c, arg2);
    }

    static boolean a(boolean arg1, boolean arg2, boolean arg3) {
        if(arg3) {
            return 1;
        }

        if(!arg1) {
            if(arg2) {
            }
            else {
                return 1;
            }
        }

        return 0;
    }

    public int a() {
        return this.d.o();
    }

    public b a(android.support.v7.view.b$a arg3) {
        if(this.h != null) {
            this.h.c();
        }

        this.b.setHideOnContentScrollEnabled(false);
        this.e.c();
        android.support.v7.app.l$a v0 = new android.support.v7.app.l$a(this, this.e.getContext(), arg3);
        if(v0.e()) {
            this.h = v0;
            v0.d();
            this.e.a(((b)v0));
            this.j(true);
            this.e.sendAccessibilityEvent(32);
            return ((b)v0);
        }

        return null;
    }

    public void a(int arg1) {
        this.E = arg1;
    }

    public void a(int arg3, int arg4) {
        int v0 = this.d.o();
        if((arg4 & 4) != 0) {
            this.A = true;
        }

        this.d.c(arg3 & arg4 | (arg4 ^ -1) & v0);
    }

    public void a(Configuration arg1) {
        this.k(android.support.v7.view.a.a(this.a).d());
    }

    public void a(CharSequence arg2) {
        this.d.a(arg2);
    }

    public boolean a(int arg5, KeyEvent arg6) {
        if(this.h == null) {
            return 0;
        }

        Menu v0 = this.h.b();
        if(v0 != null) {
            int v2 = arg6 != null ? arg6.getDeviceId() : -1;
            boolean v3 = true;
            if(KeyCharacterMap.load(v2).getKeyboardType() != 1) {
            }
            else {
                v3 = false;
            }

            v0.setQwertyMode(v3);
            return v0.performShortcut(arg5, arg6, 0);
        }

        return 0;
    }

    private ag b(View arg4) {
        if((arg4 instanceof ag)) {
            return arg4;
        }

        if((arg4 instanceof Toolbar)) {
            return ((Toolbar)arg4).getWrapper();
        }

        StringBuilder v1 = new StringBuilder();
        v1.append("Can\'t make a decor toolbar out of ");
        String v4 = arg4 != null ? arg4.getClass().getSimpleName() : "null";
        v1.append(v4);
        throw new IllegalStateException(v1.toString());
    }

    public void b(boolean arg2) {
        if(arg2) {
            if(this.b.a()) {
            }
            else {
                throw new IllegalStateException("Action bar must be in overlay mode (Window.FEATURE_OVERLAY_ACTION_BAR) to enable hide on content scroll");
            }
        }

        this.o = arg2;
        this.b.setHideOnContentScrollEnabled(arg2);
    }

    public Context b() {
        if(this.v == null) {
            TypedValue v0 = new TypedValue();
            this.a.getTheme().resolveAttribute(android.support.v7.a.a$a.actionBarWidgetTheme, v0, true);
            int v0_1 = v0.resourceId;
            this.v = v0_1 != 0 ? new ContextThemeWrapper(this.a, v0_1) : this.a;
        }

        return this.v;
    }

    public void c(boolean arg2) {
        if(!this.A) {
            this.f(arg2);
        }
    }

    public void d(boolean arg1) {
        this.H = arg1;
        if(!arg1 && this.n != null) {
            this.n.c();
        }
    }

    public void e(boolean arg4) {
        if(arg4 == this.B) {
            return;
        }

        this.B = arg4;
        int v0 = this.C.size();
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            this.C.get(v1).a(arg4);
        }
    }

    public void f(boolean arg2) {
        int v0 = 4;
        int v2 = arg2 ? 4 : 0;
        this.a(v2, v0);
    }

    public boolean f() {
        if(this.d != null && (this.d.c())) {
            this.d.d();
            return 1;
        }

        return 0;
    }

    public void g(boolean arg1) {
        this.k = arg1;
    }

    public void h(boolean arg5) {
        if(this.n != null) {
            this.n.c();
        }

        this.c.setVisibility(0);
        if(this.E == 0) {
            if(!this.H && !arg5) {
                goto label_57;
            }

            this.c.setTranslationY(0f);
            float v0 = ((float)(-this.c.getHeight()));
            if(arg5) {
                int[] v5 = new int[]{0, 0};
                this.c.getLocationInWindow(v5);
                v0 -= ((float)v5[1]);
            }

            this.c.setTranslationY(v0);
            android.support.v7.view.h v5_1 = new android.support.v7.view.h();
            x v2 = t.m(this.c).b(0f);
            v2.a(this.r);
            v5_1.a(v2);
            if((this.k) && this.f != null) {
                this.f.setTranslationY(v0);
                v5_1.a(t.m(this.f).b(0f));
            }

            v5_1.a(l.u);
            v5_1.a(250);
            v5_1.a(this.q);
            this.n = v5_1;
            v5_1.a();
        }
        else {
        label_57:
            this.c.setAlpha(1f);
            this.c.setTranslationY(0f);
            if((this.k) && this.f != null) {
                this.f.setTranslationY(0f);
            }

            this.q.b(null);
        }

        if(this.b != null) {
            t.s(this.b);
        }
    }

    void h() {
        if(this.j != null) {
            this.j.a(this.i);
            this.i = null;
            this.j = null;
        }
    }

    public int i() {
        return this.d.p();
    }

    public void i(boolean arg5) {
        if(this.n != null) {
            this.n.c();
        }

        if(this.E == 0) {
            if(!this.H && !arg5) {
                goto label_52;
            }

            this.c.setAlpha(1f);
            this.c.setTransitioning(true);
            android.support.v7.view.h v0 = new android.support.v7.view.h();
            float v2 = ((float)(-this.c.getHeight()));
            if(arg5) {
                int[] v5 = new int[]{0, 0};
                this.c.getLocationInWindow(v5);
                v2 -= ((float)v5[1]);
            }

            x v5_1 = t.m(this.c).b(v2);
            v5_1.a(this.r);
            v0.a(v5_1);
            if((this.k) && this.f != null) {
                v0.a(t.m(this.f).b(v2));
            }

            v0.a(l.t);
            v0.a(250);
            v0.a(this.p);
            this.n = v0;
            v0.a();
        }
        else {
        label_52:
            this.p.b(null);
        }
    }

    public void j(boolean arg9) {
        x v0;
        x v9;
        if(arg9) {
            this.n();
        }
        else {
            this.o();
        }

        int v1 = 8;
        int v2 = 4;
        if(this.p()) {
            long v4 = 200;
            long v6 = 100;
            if(arg9) {
                v9 = this.d.a(v2, v6);
                v0 = this.e.a(0, v4);
            }
            else {
                v0 = this.d.a(0, v4);
                v9 = this.e.a(v1, v6);
            }

            android.support.v7.view.h v1_1 = new android.support.v7.view.h();
            v1_1.a(v9, v0);
            v1_1.a();
        }
        else {
            if(arg9) {
                this.d.d(v2);
                this.e.setVisibility(0);
                return;
            }

            this.d.d(0);
            this.e.setVisibility(v1);
        }
    }

    public void j() {
        if(this.m) {
            this.m = false;
            this.l(true);
        }
    }

    private void k(boolean arg5) {
        this.D = arg5;
        bc v0 = null;
        if(!this.D) {
            this.d.a(v0);
            this.c.setTabContainer(this.g);
        }
        else {
            this.c.setTabContainer(v0);
            this.d.a(this.g);
        }

        boolean v1 = true;
        int v5 = this.i() == 2 ? 1 : 0;
        if(this.g != null) {
            if(v5 != 0) {
                this.g.setVisibility(0);
                if(this.b != null) {
                    t.s(this.b);
                }
            }
            else {
                this.g.setVisibility(8);
            }
        }

        ag v0_1 = this.d;
        boolean v3 = (this.D) || v5 == 0 ? false : true;
        v0_1.a(v3);
        ActionBarOverlayLayout v0_2 = this.b;
        if((this.D) || v5 == 0) {
            v1 = false;
        }
        else {
        }

        v0_2.setHasNonEmbeddedTabs(v1);
    }

    public void k() {
        if(!this.m) {
            this.m = true;
            this.l(true);
        }
    }

    private void l(boolean arg4) {
        if(l.a(this.l, this.m, this.F)) {
            if(!this.G) {
                this.G = true;
                this.h(arg4);
            }
        }
        else if(this.G) {
            this.G = false;
            this.i(arg4);
        }
    }

    public void l() {
        if(this.n != null) {
            this.n.c();
            this.n = null;
        }
    }

    public void m() {
    }

    private void n() {
        if(!this.F) {
            this.F = true;
            if(this.b != null) {
                this.b.setShowingForActionMode(true);
            }

            this.l(false);
        }
    }

    private void o() {
        if(this.F) {
            this.F = false;
            if(this.b != null) {
                this.b.setShowingForActionMode(false);
            }

            this.l(false);
        }
    }

    private boolean p() {
        return t.A(this.c);
    }
}

