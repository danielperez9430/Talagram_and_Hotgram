package android.support.v7.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v4.view.b$a;
import android.support.v7.a.a$g;
import android.support.v7.view.menu.ActionMenuItemView;
import android.support.v7.view.menu.b;
import android.support.v7.view.menu.h;
import android.support.v7.view.menu.j;
import android.support.v7.view.menu.m;
import android.support.v7.view.menu.n;
import android.support.v7.view.menu.p;
import android.support.v7.view.menu.s;
import android.support.v7.view.menu.u;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View$MeasureSpec;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.view.ViewGroup;
import android.view.ViewParent;
import java.util.ArrayList;

class c extends b implements a {
    class android.support.v7.widget.c$a extends n {
        public android.support.v7.widget.c$a(c arg7, Context arg8, u arg9, View arg10) {
            d v8_1;
            this.a = arg7;
            super(arg8, arg9, arg10, false, android.support.v7.a.a$a.actionOverflowMenuStyle);
            if(!arg9.getItem().j()) {
                if(arg7.g == null) {
                    p v8 = c.c(arg7);
                }
                else {
                    v8_1 = arg7.g;
                }

                this.a(((View)v8_1));
            }

            this.a(arg7.k);
        }

        protected void e() {
            this.a.i = null;
            this.a.l = 0;
            super.e();
        }
    }

    class android.support.v7.widget.c$b extends android.support.v7.view.menu.ActionMenuItemView$b {
        android.support.v7.widget.c$b(c arg1) {
            this.a = arg1;
            super();
        }

        public s a() {
            s v0_1;
            if(this.a.i != null) {
                m v0 = this.a.i.b();
            }
            else {
                v0_1 = null;
            }

            return v0_1;
        }
    }

    class android.support.v7.widget.c$c implements Runnable {
        private e b;

        public android.support.v7.widget.c$c(c arg1, e arg2) {
            this.a = arg1;
            super();
            this.b = arg2;
        }

        public void run() {
            if(c.d(this.a) != null) {
                c.e(this.a).g();
            }

            p v0 = c.f(this.a);
            if(v0 != null && ((View)v0).getWindowToken() != null && (this.b.c())) {
                this.a.h = this.b;
            }

            this.a.j = null;
        }
    }

    class d extends AppCompatImageView implements android.support.v7.widget.ActionMenuView$a {
        private final float[] b;

        public d(c arg3, Context arg4) {
            this.a = arg3;
            super(arg4, null, android.support.v7.a.a$a.actionOverflowButtonStyle);
            this.b = new float[2];
            this.setClickable(true);
            this.setFocusable(true);
            this.setVisibility(0);
            this.setEnabled(true);
            bm.a(((View)this), this.getContentDescription());
            this.setOnTouchListener(new am(((View)this), arg3) {
                public s a() {
                    if(this.b.a.h == null) {
                        return null;
                    }

                    return this.b.a.h.b();
                }

                public boolean b() {
                    this.b.a.d();
                    return 1;
                }

                public boolean c() {
                    if(this.b.a.j != null) {
                        return 0;
                    }

                    this.b.a.e();
                    return 1;
                }
            });
        }

        public boolean c() {
            return 0;
        }

        public boolean d() {
            return 0;
        }

        public boolean performClick() {
            if(super.performClick()) {
                return 1;
            }

            this.playSoundEffect(0);
            this.a.d();
            return 1;
        }

        protected boolean setFrame(int arg5, int arg6, int arg7, int arg8) {
            boolean v5 = super.setFrame(arg5, arg6, arg7, arg8);
            Drawable v6 = this.getDrawable();
            Drawable v7 = this.getBackground();
            if(v6 != null && v7 != null) {
                arg6 = this.getWidth();
                arg8 = this.getHeight();
                int v0 = Math.max(arg6, arg8) / 2;
                arg6 = (arg6 + (this.getPaddingLeft() - this.getPaddingRight())) / 2;
                arg8 = (arg8 + (this.getPaddingTop() - this.getPaddingBottom())) / 2;
                android.support.v4.graphics.drawable.a.a(v7, arg6 - v0, arg8 - v0, arg6 + v0, arg8 + v0);
            }

            return v5;
        }
    }

    class e extends n {
        public e(c arg7, Context arg8, h arg9, View arg10, boolean arg11) {
            this.a = arg7;
            super(arg8, arg9, arg10, arg11, android.support.v7.a.a$a.actionOverflowMenuStyle);
            this.a(8388613);
            this.a(arg7.k);
        }

        protected void e() {
            if(c.a(this.a) != null) {
                c.b(this.a).close();
            }

            this.a.h = null;
            super.e();
        }
    }

    class f implements android.support.v7.view.menu.o$a {
        f(c arg1) {
            this.a = arg1;
            super();
        }

        public void a(h arg3, boolean arg4) {
            if((arg3 instanceof u)) {
                arg3.q().a(false);
            }

            android.support.v7.view.menu.o$a v0 = this.a.a();
            if(v0 != null) {
                v0.a(arg3, arg4);
            }
        }

        public boolean a(h arg4) {
            boolean v0 = false;
            if(arg4 == null) {
                return 0;
            }

            this.a.l = arg4.getItem().getItemId();
            android.support.v7.view.menu.o$a v1 = this.a.a();
            if(v1 != null) {
                v0 = v1.a(arg4);
            }

            return v0;
        }
    }

    private android.support.v7.widget.c$b A;
    d g;
    e h;
    android.support.v7.widget.c$a i;
    android.support.v7.widget.c$c j;
    final f k;
    int l;
    private Drawable m;
    private boolean n;
    private boolean o;
    private boolean p;
    private int q;
    private int r;
    private int s;
    private boolean t;
    private boolean u;
    private boolean v;
    private boolean w;
    private int x;
    private final SparseBooleanArray y;
    private View z;

    public c(Context arg3) {
        super(arg3, g.abc_action_menu_layout, g.abc_action_menu_item_layout);
        this.y = new SparseBooleanArray();
        this.k = new f(this);
    }

    public void a(Context arg5, h arg6) {
        super.a(arg5, arg6);
        Resources v6 = arg5.getResources();
        android.support.v7.view.a v5 = android.support.v7.view.a.a(arg5);
        if(!this.p) {
            this.o = v5.b();
        }

        if(!this.v) {
            this.q = v5.c();
        }

        if(!this.t) {
            this.s = v5.a();
        }

        int v5_1 = this.q;
        Drawable v1 = null;
        if(this.o) {
            if(this.g == null) {
                this.g = new d(this, this.a);
                if(this.n) {
                    this.g.setImageDrawable(this.m);
                    this.m = v1;
                    this.n = false;
                }

                int v0 = View$MeasureSpec.makeMeasureSpec(0, 0);
                this.g.measure(v0, v0);
            }

            v5_1 -= this.g.getMeasuredWidth();
        }
        else {
            this.g = ((d)v1);
        }

        this.r = v5_1;
        this.x = ((int)(v6.getDisplayMetrics().density * 56f));
        this.z = ((View)v1);
    }

    public void a(Configuration arg2) {
        if(!this.t) {
            this.s = android.support.v7.view.a.a(this.b).a();
        }

        if(this.c != null) {
            this.c.b(true);
        }
    }

    public p a(ViewGroup arg2) {
        p v0 = this.f;
        p v2 = super.a(arg2);
        if(v0 != v2) {
            v2.setPresenter(this);
        }

        return v2;
    }

    public void a(ActionMenuView arg2) {
        this.f = ((p)arg2);
        arg2.a(this.c);
    }

    public void a(Drawable arg2) {
        if(this.g != null) {
            this.g.setImageDrawable(arg2);
        }
        else {
            this.n = true;
            this.m = arg2;
        }
    }

    static h a(c arg0) {
        return arg0.c;
    }

    private View a(MenuItem arg7) {
        p v0 = this.f;
        View v1 = null;
        if(v0 == null) {
            return v1;
        }

        int v2 = ((ViewGroup)v0).getChildCount();
        int v3;
        for(v3 = 0; v3 < v2; ++v3) {
            View v4 = ((ViewGroup)v0).getChildAt(v3);
            if(((v4 instanceof android.support.v7.view.menu.p$a)) && v4.getItemData() == arg7) {
                return v4;
            }
        }

        return v1;
    }

    public View a(j arg3, View arg4, ViewGroup arg5) {
        View v0 = arg3.getActionView();
        if(v0 == null || (arg3.n())) {
            v0 = super.a(arg3, arg4, arg5);
        }

        int v3 = arg3.isActionViewExpanded() ? 8 : 0;
        v0.setVisibility(v3);
        ViewGroup$LayoutParams v3_1 = v0.getLayoutParams();
        if(!((ActionMenuView)arg5).checkLayoutParams(v3_1)) {
            v0.setLayoutParams(((ActionMenuView)arg5).a(v3_1));
        }

        return v0;
    }

    public void a(h arg1, boolean arg2) {
        this.f();
        super.a(arg1, arg2);
    }

    public void a(j arg2, android.support.v7.view.menu.p$a arg3) {
        arg3.a(arg2, 0);
        ((ActionMenuItemView)arg3).setItemInvoker(this.f);
        if(this.A == null) {
            this.A = new android.support.v7.widget.c$b(this);
        }

        ((ActionMenuItemView)arg3).setPopupCallback(this.A);
    }

    public void a(boolean arg2) {
        if(arg2) {
            super.a(null);
        }
        else if(this.c != null) {
            this.c.a(false);
        }
    }

    public boolean a(int arg1, j arg2) {
        return arg2.j();
    }

    public boolean a(u arg8) {
        boolean v1 = false;
        if(!arg8.hasVisibleItems()) {
            return 0;
        }

        u v0 = arg8;
        while(v0.t() != this.c) {
            Menu v0_1 = v0.t();
        }

        View v0_2 = this.a(v0.getItem());
        if(v0_2 == null) {
            return 0;
        }

        this.l = arg8.getItem().getItemId();
        int v2 = arg8.size();
        int v3;
        for(v3 = 0; v3 < v2; ++v3) {
            MenuItem v5 = arg8.getItem(v3);
            if((v5.isVisible()) && v5.getIcon() != null) {
                v1 = true;
                break;
            }
        }

        this.i = new android.support.v7.widget.c$a(this, this.b, arg8, v0_2);
        this.i.a(v1);
        this.i.a();
        super.a(arg8);
        return 1;
    }

    public boolean a(ViewGroup arg3, int arg4) {
        if(arg3.getChildAt(arg4) == this.g) {
            return 0;
        }

        return super.a(arg3, arg4);
    }

    public void b(boolean arg5) {
        int v1;
        ArrayList v5;
        super.b(arg5);
        this.f.requestLayout();
        int v0 = 0;
        if(this.c != null) {
            v5 = this.c.l();
            v1 = v5.size();
            int v2;
            for(v2 = 0; v2 < v1; ++v2) {
                android.support.v4.view.b v3 = v5.get(v2).a();
                if(v3 != null) {
                    v3.a(((a)this));
                }
            }
        }

        v5 = this.c != null ? this.c.m() : null;
        if((this.o) && v5 != null) {
            v1 = v5.size();
            if(v1 == 1) {
                v0 = v5.get(0).isActionViewExpanded() ^ 1;
            }
            else if(v1 > 0) {
                v0 = 1;
            }
        }

        if(v0 != 0) {
            if(this.g == null) {
                this.g = new d(this, this.a);
            }

            ViewParent v5_1 = this.g.getParent();
            if(v5_1 == this.f) {
                goto label_63;
            }

            if(v5_1 != null) {
                ((ViewGroup)v5_1).removeView(this.g);
            }

            this.f.addView(this.g, this.f.c());
        }
        else {
            if(this.g == null) {
                goto label_63;
            }

            if(this.g.getParent() != this.f) {
                goto label_63;
            }

            this.f.removeView(this.g);
        }

    label_63:
        this.f.setOverflowReserved(this.o);
    }

    static h b(c arg0) {
        return arg0.c;
    }

    public boolean b() {
        int v17;
        boolean v15_1;
        int v12;
        int v3;
        ArrayList v1;
        c v0 = this;
        int v2 = 0;
        if(v0.c != null) {
            v1 = v0.c.j();
            v3 = v1.size();
        }
        else {
            v1 = null;
            v3 = 0;
        }

        int v4 = v0.s;
        int v5 = v0.r;
        int v6 = View$MeasureSpec.makeMeasureSpec(0, 0);
        p v7 = v0.f;
        int v11 = v4;
        v4 = 0;
        int v8 = 0;
        int v9 = 0;
        int v10 = 0;
        while(v4 < v3) {
            Object v13 = v1.get(v4);
            if(((j)v13).l()) {
                ++v8;
            }
            else if(((j)v13).k()) {
                ++v10;
            }
            else {
                v9 = 1;
            }

            if((v0.w) && (((j)v13).isActionViewExpanded())) {
                v11 = 0;
            }

            ++v4;
        }

        if((v0.o) && (v9 != 0 || v10 + v8 > v11)) {
            --v11;
        }

        v11 -= v8;
        SparseBooleanArray v4_1 = v0.y;
        v4_1.clear();
        if(v0.u) {
            v8 = v5 / v0.x;
            v9 = v5 % v0.x / v8 + v0.x;
        }
        else {
            v8 = 0;
            v9 = 0;
        }

        v10 = v5;
        v5 = 0;
        int v13_1 = 0;
        while(v5 < v3) {
            Object v14 = v1.get(v5);
            if(((j)v14).l()) {
                View v15 = v0.a(((j)v14), v0.z, ((ViewGroup)v7));
                if(v0.z == null) {
                    v0.z = v15;
                }

                if(v0.u) {
                    v8 -= ActionMenuView.a(v15, v9, v8, v6, v2);
                }
                else {
                    v15.measure(v6, v6);
                }

                v12 = v15.getMeasuredWidth();
                v10 -= v12;
                if(v13_1 == 0) {
                }
                else {
                    v12 = v13_1;
                }

                v13_1 = ((j)v14).getGroupId();
                if(v13_1 != 0) {
                    v15_1 = true;
                    v4_1.put(v13_1, true);
                }
                else {
                    v15_1 = true;
                }

                ((j)v14).d(v15_1);
                v17 = v3;
                v13_1 = v12;
            }
            else {
                if(((j)v14).k()) {
                    v12 = ((j)v14).getGroupId();
                    v15_1 = v4_1.get(v12);
                    if(v11 <= 0 && !v15_1) {
                        goto label_104;
                    }
                    else if(v10 > 0) {
                        if((v0.u) && v8 <= 0) {
                            goto label_104;
                        }

                        v2 = 1;
                    }
                    else {
                    label_104:
                        v2 = 0;
                    }

                    if(v2 != 0) {
                        int v16 = v2;
                        View v2_1 = v0.a(((j)v14), v0.z, ((ViewGroup)v7));
                        v17 = v3;
                        if(v0.z == null) {
                            v0.z = v2_1;
                        }

                        if(v0.u) {
                            int v18 = ActionMenuView.a(v2_1, v9, v8, v6, 0);
                            v8 -= v18;
                            if(v18 == 0) {
                                v16 = 0;
                            }
                        }
                        else {
                            v2_1.measure(v6, v6);
                        }

                        v2 = v2_1.getMeasuredWidth();
                        v10 -= v2;
                        if(v13_1 == 0) {
                            v13_1 = v2;
                        }

                        if(v0.u) {
                            if(v10 < 0) {
                                goto label_131;
                            }

                            goto label_129;
                        }
                        else if(v10 + v13_1 > 0) {
                        label_129:
                            v2 = 1;
                        }
                        else {
                        label_131:
                            v2 = 0;
                        }

                        v2 &= v16;
                    }
                    else {
                        v17 = v3;
                    }

                    if(v2 != 0 && v12 != 0) {
                        v4_1.put(v12, true);
                    }
                    else if(v15_1) {
                        v4_1.put(v12, false);
                        for(v3 = 0; v3 < v5; ++v3) {
                            Object v15_2 = v1.get(v3);
                            if(((j)v15_2).getGroupId() == v12) {
                                if(((j)v15_2).j()) {
                                    ++v11;
                                }

                                ((j)v15_2).d(false);
                            }
                        }
                    }

                    if(v2 != 0) {
                        --v11;
                    }

                    ((j)v14).d(((boolean)v2));
                    goto label_167;
                }

                v17 = v3;
                ((j)v14).d(false);
            }

        label_167:
            ++v5;
            v3 = v17;
            v0 = this;
            v2 = 0;
        }

        return 1;
    }

    public void c(boolean arg1) {
        this.o = arg1;
        this.p = true;
    }

    public Drawable c() {
        if(this.g != null) {
            return this.g.getDrawable();
        }

        if(this.n) {
            return this.m;
        }

        return null;
    }

    static p c(c arg0) {
        return arg0.f;
    }

    public void d(boolean arg1) {
        this.w = arg1;
    }

    public boolean d() {
        if((this.o) && !this.h() && this.c != null && this.f != null && this.j == null && !this.c.m().isEmpty()) {
            this.j = new android.support.v7.widget.c$c(this, new e(this, this.b, this.c, this.g, true));
            this.f.post(this.j);
            super.a(null);
            return 1;
        }

        return 0;
    }

    static h d(c arg0) {
        return arg0.c;
    }

    public boolean e() {
        if(this.j != null && this.f != null) {
            this.f.removeCallbacks(this.j);
            this.j = null;
            return 1;
        }

        e v0 = this.h;
        if(v0 != null) {
            ((n)v0).d();
            return 1;
        }

        return 0;
    }

    static h e(c arg0) {
        return arg0.c;
    }

    public boolean f() {
        return this.e() | this.g();
    }

    static p f(c arg0) {
        return arg0.f;
    }

    public boolean g() {
        if(this.i != null) {
            this.i.d();
            return 1;
        }

        return 0;
    }

    public boolean h() {
        boolean v0 = this.h == null || !this.h.f() ? false : true;
        return v0;
    }

    public boolean i() {
        boolean v0 = this.j != null || (this.h()) ? true : false;
        return v0;
    }
}

