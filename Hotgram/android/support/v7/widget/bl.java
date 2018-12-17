package android.support.v7.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.view.t;
import android.support.v4.view.x;
import android.support.v4.view.z;
import android.support.v7.a.a$a;
import android.support.v7.a.a$e;
import android.support.v7.a.a$f;
import android.support.v7.a.a$h;
import android.support.v7.a.a$j;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View$OnClickListener;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.view.ViewGroup;
import android.view.Window$Callback;

public class bl implements ag {
    Toolbar a;
    CharSequence b;
    Window$Callback c;
    boolean d;
    private int e;
    private View f;
    private View g;
    private Drawable h;
    private Drawable i;
    private Drawable j;
    private boolean k;
    private CharSequence l;
    private CharSequence m;
    private c n;
    private int o;
    private int p;
    private Drawable q;

    public bl(Toolbar arg3, boolean arg4) {
        this(arg3, arg4, h.abc_action_bar_up_description, e.abc_ic_ab_back_material);
    }

    public bl(Toolbar arg4, boolean arg5, int arg6, int arg7) {
        super();
        this.o = 0;
        this.p = 0;
        this.a = arg4;
        this.b = arg4.getTitle();
        this.l = arg4.getSubtitle();
        boolean v0 = this.b != null ? true : false;
        this.k = v0;
        this.j = arg4.getNavigationIcon();
        bk v4 = bk.a(arg4.getContext(), null, j.ActionBar, a.actionBarStyle, 0);
        this.q = v4.a(j.ActionBar_homeAsUpIndicator);
        if(arg5) {
            CharSequence v5 = v4.c(j.ActionBar_title);
            if(!TextUtils.isEmpty(v5)) {
                this.b(v5);
            }

            v5 = v4.c(j.ActionBar_subtitle);
            if(!TextUtils.isEmpty(v5)) {
                this.c(v5);
            }

            Drawable v5_1 = v4.a(j.ActionBar_logo);
            if(v5_1 != null) {
                this.b(v5_1);
            }

            v5_1 = v4.a(j.ActionBar_icon);
            if(v5_1 != null) {
                this.a(v5_1);
            }

            if(this.j == null && this.q != null) {
                this.c(this.q);
            }

            this.c(v4.a(j.ActionBar_displayOptions, 0));
            int v5_2 = v4.g(j.ActionBar_customNavigationLayout, 0);
            if(v5_2 != 0) {
                this.a(LayoutInflater.from(this.a.getContext()).inflate(v5_2, this.a, false));
                this.c(this.e | 16);
            }

            v5_2 = v4.f(j.ActionBar_height, 0);
            if(v5_2 > 0) {
                ViewGroup$LayoutParams v0_1 = this.a.getLayoutParams();
                v0_1.height = v5_2;
                this.a.setLayoutParams(v0_1);
            }

            v5_2 = v4.d(j.ActionBar_contentInsetStart, -1);
            int v0_2 = v4.d(j.ActionBar_contentInsetEnd, -1);
            if(v5_2 >= 0 || v0_2 >= 0) {
                this.a.a(Math.max(v5_2, 0), Math.max(v0_2, 0));
            }

            v5_2 = v4.g(j.ActionBar_titleTextStyle, 0);
            if(v5_2 != 0) {
                this.a.a(this.a.getContext(), v5_2);
            }

            v5_2 = v4.g(j.ActionBar_subtitleTextStyle, 0);
            if(v5_2 != 0) {
                this.a.b(this.a.getContext(), v5_2);
            }

            v5_2 = v4.g(j.ActionBar_popupTheme, 0);
            if(v5_2 == 0) {
                goto label_106;
            }

            this.a.setPopupTheme(v5_2);
        }
        else {
            this.e = this.r();
        }

    label_106:
        v4.a();
        this.e(arg6);
        this.m = this.a.getNavigationContentDescription();
        this.a.setNavigationOnClickListener(new View$OnClickListener() {
            final android.support.v7.view.menu.a a;

            public void onClick(View arg3) {
                if(this.b.c != null && (this.b.d)) {
                    this.b.c.onMenuItemSelected(0, this.a);
                }
            }
        });
    }

    public void a(Drawable arg1) {
        this.h = arg1;
        this.s();
    }

    public void a(View arg3) {
        if(this.g != null && (this.e & 16) != 0) {
            this.a.removeView(this.g);
        }

        this.g = arg3;
        if(arg3 != null && (this.e & 16) != 0) {
            this.a.addView(this.g);
        }
    }

    public x a(int arg3, long arg4) {
        x v0 = t.m(this.a);
        float v1 = arg3 == 0 ? 1f : 0f;
        return v0.a(v1).a(arg4).a(new z(arg3) {
            private boolean c;

            public void a(View arg2) {
                this.b.a.setVisibility(0);
            }

            public void b(View arg2) {
                if(!this.c) {
                    this.b.a.setVisibility(this.a);
                }
            }

            public void c(View arg1) {
                this.c = true;
            }
        });
    }

    public ViewGroup a() {
        return this.a;
    }

    public void a(int arg2) {
        Drawable v2 = arg2 != 0 ? android.support.v7.c.a.a.b(this.b(), arg2) : null;
        this.a(v2);
    }

    public void a(android.support.v7.view.menu.o$a arg2, android.support.v7.view.menu.h$a arg3) {
        this.a.a(arg2, arg3);
    }

    public void a(bc arg4) {
        if(this.f != null && this.f.getParent() == this.a) {
            this.a.removeView(this.f);
        }

        this.f = ((View)arg4);
        if(arg4 != null && this.o == 2) {
            this.a.addView(this.f, 0);
            ViewGroup$LayoutParams v0 = this.f.getLayoutParams();
            ((b)v0).width = -2;
            ((b)v0).height = -2;
            ((b)v0).a = 8388691;
            arg4.setAllowCollapse(true);
        }
    }

    public void a(Menu arg3, android.support.v7.view.menu.o$a arg4) {
        if(this.n == null) {
            this.n = new c(this.a.getContext());
            this.n.a(f.action_menu_presenter);
        }

        this.n.a(arg4);
        this.a.a(((android.support.v7.view.menu.h)arg3), this.n);
    }

    public void a(Window$Callback arg1) {
        this.c = arg1;
    }

    public void a(CharSequence arg2) {
        if(!this.k) {
            this.e(arg2);
        }
    }

    public void a(boolean arg2) {
        this.a.setCollapsible(arg2);
    }

    public void b(CharSequence arg2) {
        this.k = true;
        this.e(arg2);
    }

    public void b(Drawable arg1) {
        this.i = arg1;
        this.s();
    }

    public Context b() {
        return this.a.getContext();
    }

    public void b(int arg2) {
        Drawable v2 = arg2 != 0 ? android.support.v7.c.a.a.b(this.b(), arg2) : null;
        this.b(v2);
    }

    public void b(boolean arg1) {
    }

    public void c(CharSequence arg2) {
        this.l = arg2;
        if((this.e & 8) != 0) {
            this.a.setSubtitle(arg2);
        }
    }

    public void c(Drawable arg1) {
        this.j = arg1;
        this.t();
    }

    public void c(int arg4) {
        CharSequence v2;
        Toolbar v1;
        int v0 = this.e ^ arg4;
        this.e = arg4;
        if(v0 != 0) {
            if((v0 & 4) != 0) {
                if((arg4 & 4) != 0) {
                    this.u();
                }

                this.t();
            }

            if((v0 & 3) != 0) {
                this.s();
            }

            if((v0 & 8) != 0) {
                if((arg4 & 8) != 0) {
                    this.a.setTitle(this.b);
                    v1 = this.a;
                    v2 = this.l;
                }
                else {
                    v2 = null;
                    this.a.setTitle(v2);
                    v1 = this.a;
                }

                v1.setSubtitle(v2);
            }

            if((v0 & 16) == 0) {
                return;
            }

            if(this.g == null) {
                return;
            }

            if((arg4 & 16) != 0) {
                this.a.addView(this.g);
                return;
            }

            this.a.removeView(this.g);
        }
    }

    public boolean c() {
        return this.a.g();
    }

    public void d() {
        this.a.h();
    }

    public void d(int arg2) {
        this.a.setVisibility(arg2);
    }

    public void d(CharSequence arg1) {
        this.m = arg1;
        this.u();
    }

    public void e(int arg2) {
        if(arg2 == this.p) {
            return;
        }

        this.p = arg2;
        if(TextUtils.isEmpty(this.a.getNavigationContentDescription())) {
            this.f(this.p);
        }
    }

    private void e(CharSequence arg2) {
        this.b = arg2;
        if((this.e & 8) != 0) {
            this.a.setTitle(arg2);
        }
    }

    public CharSequence e() {
        return this.a.getTitle();
    }

    public void f(int arg2) {
        CharSequence v2;
        if(arg2 == 0) {
            v2 = null;
        }
        else {
            String v2_1 = this.b().getString(arg2);
        }

        this.d(v2);
    }

    public void f() {
        Log.i("ToolbarWidgetWrapper", "Progress display unsupported");
    }

    public void g() {
        Log.i("ToolbarWidgetWrapper", "Progress display unsupported");
    }

    public boolean h() {
        return this.a.a();
    }

    public boolean i() {
        return this.a.b();
    }

    public boolean j() {
        return this.a.c();
    }

    public boolean k() {
        return this.a.d();
    }

    public boolean l() {
        return this.a.e();
    }

    public void m() {
        this.d = true;
    }

    public void n() {
        this.a.f();
    }

    public int o() {
        return this.e;
    }

    public int p() {
        return this.o;
    }

    public Menu q() {
        return this.a.getMenu();
    }

    private int r() {
        int v0;
        if(this.a.getNavigationIcon() != null) {
            v0 = 15;
            this.q = this.a.getNavigationIcon();
        }
        else {
            v0 = 11;
        }

        return v0;
    }

    private void s() {
        Drawable v0;
        if((this.e & 2) != 0) {
            if((this.e & 1) != 0 && this.i != null) {
                v0 = this.i;
                goto label_13;
            }

            v0 = this.h;
        }
        else {
            v0 = null;
        }

    label_13:
        this.a.setLogo(v0);
    }

    private void t() {
        Drawable v1;
        Toolbar v0;
        if((this.e & 4) != 0) {
            v0 = this.a;
            v1 = this.j != null ? this.j : this.q;
        }
        else {
            v0 = this.a;
            v1 = null;
        }

        v0.setNavigationIcon(v1);
    }

    private void u() {
        if((this.e & 4) != 0) {
            if(TextUtils.isEmpty(this.m)) {
                this.a.setNavigationContentDescription(this.p);
            }
            else {
                this.a.setNavigationContentDescription(this.m);
            }
        }
    }
}

