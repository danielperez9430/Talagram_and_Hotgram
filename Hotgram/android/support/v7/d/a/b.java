package android.support.v7.d.a;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.res.ColorStateList;
import android.content.res.Resources$Theme;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Outline;
import android.graphics.PorterDuff$Mode;
import android.graphics.Rect;
import android.graphics.drawable.Drawable$Callback;
import android.graphics.drawable.Drawable$ConstantState;
import android.graphics.drawable.Drawable;
import android.os.Build$VERSION;
import android.os.SystemClock;
import android.util.SparseArray;

class b extends Drawable implements Drawable$Callback {
    class a implements Drawable$Callback {
        private Drawable$Callback a;

        a() {
            super();
        }

        public a a(Drawable$Callback arg1) {
            this.a = arg1;
            return this;
        }

        public Drawable$Callback a() {
            Drawable$Callback v0 = this.a;
            this.a = null;
            return v0;
        }

        public void invalidateDrawable(Drawable arg1) {
        }

        public void scheduleDrawable(Drawable arg2, Runnable arg3, long arg4) {
            if(this.a != null) {
                this.a.scheduleDrawable(arg2, arg3, arg4);
            }
        }

        public void unscheduleDrawable(Drawable arg2, Runnable arg3) {
            if(this.a != null) {
                this.a.unscheduleDrawable(arg2, arg3);
            }
        }
    }

    abstract class android.support.v7.d.a.b$b extends Drawable$ConstantState {
        boolean A;
        int B;
        int C;
        int D;
        boolean E;
        ColorFilter F;
        boolean G;
        ColorStateList H;
        PorterDuff$Mode I;
        boolean J;
        boolean K;
        final b c;
        Resources d;
        int e;
        int f;
        int g;
        SparseArray h;
        Drawable[] i;
        int j;
        boolean k;
        boolean l;
        Rect m;
        boolean n;
        boolean o;
        int p;
        int q;
        int r;
        int s;
        boolean t;
        int u;
        boolean v;
        boolean w;
        boolean x;
        boolean y;
        boolean z;

        android.support.v7.d.a.b$b(android.support.v7.d.a.b$b arg3, b arg4, Resources arg5) {
            Resources v4;
            super();
            this.e = 160;
            int v0 = 0;
            this.k = false;
            this.n = false;
            this.z = true;
            this.C = 0;
            this.D = 0;
            this.c = arg4;
            if(arg5 != null) {
                v4 = arg5;
            }
            else if(arg3 != null) {
                v4 = arg3.d;
            }
            else {
                v4 = null;
            }

            this.d = v4;
            int v4_1 = arg3 != null ? arg3.e : 0;
            this.e = b.a(arg5, v4_1);
            if(arg3 != null) {
                this.f = arg3.f;
                this.g = arg3.g;
                this.x = true;
                this.y = true;
                this.k = arg3.k;
                this.n = arg3.n;
                this.z = arg3.z;
                this.A = arg3.A;
                this.B = arg3.B;
                this.C = arg3.C;
                this.D = arg3.D;
                this.E = arg3.E;
                this.F = arg3.F;
                this.G = arg3.G;
                this.H = arg3.H;
                this.I = arg3.I;
                this.J = arg3.J;
                this.K = arg3.K;
                if(arg3.e == this.e) {
                    if(arg3.l) {
                        this.m = new Rect(arg3.m);
                        this.l = true;
                    }

                    if(!arg3.o) {
                        goto label_81;
                    }

                    this.p = arg3.p;
                    this.q = arg3.q;
                    this.r = arg3.r;
                    this.s = arg3.s;
                    this.o = true;
                }

            label_81:
                if(arg3.t) {
                    this.u = arg3.u;
                    this.t = true;
                }

                if(arg3.v) {
                    this.w = arg3.w;
                    this.v = true;
                }

                Drawable[] v4_2 = arg3.i;
                this.i = new Drawable[v4_2.length];
                this.j = arg3.j;
                SparseArray v3 = arg3.h;
                v3 = v3 != null ? v3.clone() : new SparseArray(this.j);
                this.h = v3;
                int v3_1 = this.j;
                while(v0 < v3_1) {
                    if(v4_2[v0] != null) {
                        Drawable$ConstantState v5 = v4_2[v0].getConstantState();
                        if(v5 != null) {
                            this.h.put(v0, v5);
                        }
                        else {
                            this.i[v0] = v4_2[v0];
                        }
                    }

                    ++v0;
                }
            }
            else {
                this.i = new Drawable[10];
                this.j = 0;
            }
        }

        final void a(Resources arg2) {
            if(arg2 != null) {
                this.d = arg2;
                int v2 = b.a(arg2, this.e);
                int v0 = this.e;
                this.e = v2;
                if(v0 != v2) {
                    this.o = false;
                    this.l = false;
                }
            }
        }

        final void a(Resources$Theme arg6) {
            if(arg6 != null) {
                this.o();
                int v0 = this.j;
                Drawable[] v1 = this.i;
                int v2;
                for(v2 = 0; v2 < v0; ++v2) {
                    if(v1[v2] != null && (v1[v2].canApplyTheme())) {
                        v1[v2].applyTheme(arg6);
                        this.g |= v1[v2].getChangingConfigurations();
                    }
                }

                this.a(arg6.getResources());
            }
        }

        void a() {
            int v0 = this.j;
            Drawable[] v1 = this.i;
            int v2;
            for(v2 = 0; v2 < v0; ++v2) {
                if(v1[v2] != null) {
                    v1[v2].mutate();
                }
            }

            this.A = true;
        }

        public final int a(Drawable arg5) {
            int v0 = this.j;
            if(v0 >= this.i.length) {
                this.e(v0, v0 + 10);
            }

            arg5.mutate();
            arg5.setVisible(false, true);
            arg5.setCallback(this.c);
            this.i[v0] = arg5;
            ++this.j;
            this.g |= arg5.getChangingConfigurations();
            this.b();
            this.m = null;
            this.l = false;
            this.o = false;
            this.x = false;
            return v0;
        }

        public final void a(boolean arg1) {
            this.k = arg1;
        }

        public final Drawable b(int arg5) {
            Drawable v0 = this.i[arg5];
            if(v0 != null) {
                return v0;
            }

            SparseArray v1 = null;
            if(this.h != null) {
                int v0_1 = this.h.indexOfKey(arg5);
                if(v0_1 >= 0) {
                    Drawable v2 = this.b(this.h.valueAt(v0_1).newDrawable(this.d));
                    this.i[arg5] = v2;
                    this.h.removeAt(v0_1);
                    if(this.h.size() == 0) {
                        this.h = v1;
                    }

                    return v2;
                }
            }

            return ((Drawable)v1);
        }

        void b() {
            this.t = false;
            this.v = false;
        }

        private Drawable b(Drawable arg3) {
            if(Build$VERSION.SDK_INT >= 23) {
                arg3.setLayoutDirection(this.B);
            }

            arg3 = arg3.mutate();
            arg3.setCallback(this.c);
            return arg3;
        }

        public final void b(boolean arg1) {
            this.n = arg1;
        }

        final int c() {
            return this.i.length;
        }

        public final void c(int arg1) {
            this.C = arg1;
        }

        public boolean canApplyTheme() {
            int v0 = this.j;
            Drawable[] v1 = this.i;
            int v3;
            for(v3 = 0; v3 < v0; ++v3) {
                Drawable v4 = v1[v3];
                if(v4 == null) {
                    Object v4_1 = this.h.get(v3);
                    if(v4_1 != null && (((Drawable$ConstantState)v4_1).canApplyTheme())) {
                        return 1;
                    }
                }
                else if(v4.canApplyTheme()) {
                    return 1;
                }
            }

            return 0;
        }

        final boolean d(int arg8, int arg9) {
            int v0 = this.j;
            Drawable[] v1 = this.i;
            int v3 = 0;
            boolean v4 = false;
            while(v3 < v0) {
                if(v1[v3] != null) {
                    boolean v5 = Build$VERSION.SDK_INT >= 23 ? v1[v3].setLayoutDirection(arg8) : false;
                    if(v3 != arg9) {
                        goto label_17;
                    }

                    v4 = v5;
                }

            label_17:
                ++v3;
            }

            this.B = arg8;
            return v4;
        }

        public final int d() {
            return this.j;
        }

        public final void d(int arg1) {
            this.D = arg1;
        }

        public final Rect e() {
            Rect v1 = null;
            if(this.k) {
                return v1;
            }

            if(this.m == null) {
                if(this.l) {
                }
                else {
                    this.o();
                    Rect v0 = new Rect();
                    int v2 = this.j;
                    Drawable[] v3 = this.i;
                    Rect v5 = v1;
                    int v1_1;
                    for(v1_1 = 0; v1_1 < v2; ++v1_1) {
                        if(v3[v1_1].getPadding(v0)) {
                            if(v5 == null) {
                                v5 = new Rect(0, 0, 0, 0);
                            }

                            if(v0.left > v5.left) {
                                v5.left = v0.left;
                            }

                            if(v0.top > v5.top) {
                                v5.top = v0.top;
                            }

                            if(v0.right > v5.right) {
                                v5.right = v0.right;
                            }

                            if(v0.bottom <= v5.bottom) {
                                goto label_44;
                            }

                            v5.bottom = v0.bottom;
                        }

                    label_44:
                    }

                    this.l = true;
                    this.m = v5;
                    return v5;
                }
            }

            return this.m;
        }

        public void e(int arg3, int arg4) {
            Drawable[] v4 = new Drawable[arg4];
            System.arraycopy(this.i, 0, v4, 0, arg3);
            this.i = v4;
        }

        public final boolean f() {
            return this.n;
        }

        public final int g() {
            if(!this.o) {
                this.k();
            }

            return this.p;
        }

        public int getChangingConfigurations() {
            return this.f | this.g;
        }

        public final int h() {
            if(!this.o) {
                this.k();
            }

            return this.q;
        }

        public final int i() {
            if(!this.o) {
                this.k();
            }

            return this.r;
        }

        public final int j() {
            if(!this.o) {
                this.k();
            }

            return this.s;
        }

        protected void k() {
            this.o = true;
            this.o();
            int v0 = this.j;
            Drawable[] v1 = this.i;
            this.q = -1;
            this.p = -1;
            int v2 = 0;
            this.s = 0;
            this.r = 0;
            while(v2 < v0) {
                Drawable v3 = v1[v2];
                int v4 = v3.getIntrinsicWidth();
                if(v4 > this.p) {
                    this.p = v4;
                }

                v4 = v3.getIntrinsicHeight();
                if(v4 > this.q) {
                    this.q = v4;
                }

                v4 = v3.getMinimumWidth();
                if(v4 > this.r) {
                    this.r = v4;
                }

                int v3_1 = v3.getMinimumHeight();
                if(v3_1 > this.s) {
                    this.s = v3_1;
                }

                ++v2;
            }
        }

        public final int l() {
            if(this.t) {
                return this.u;
            }

            this.o();
            int v0 = this.j;
            Drawable[] v1 = this.i;
            int v2 = v0 > 0 ? v1[0].getOpacity() : -2;
            int v4 = v2;
            for(v2 = 1; v2 < v0; ++v2) {
                v4 = Drawable.resolveOpacity(v4, v1[v2].getOpacity());
            }

            this.u = v4;
            this.t = true;
            return v4;
        }

        public final boolean m() {
            if(this.v) {
                return this.w;
            }

            this.o();
            int v0 = this.j;
            Drawable[] v1 = this.i;
            boolean v2 = false;
            int v3 = 0;
            while(v3 < v0) {
                if(v1[v3].isStateful()) {
                    v2 = true;
                }
                else {
                    ++v3;
                    continue;
                }

                break;
            }

            this.w = v2;
            this.v = true;
            return v2;
        }

        public boolean n() {
            int v4;
            boolean v0_1;
            __monitor_enter(this);
            try {
                if(!this.x) {
                    goto label_6;
                }

                v0_1 = this.y;
            }
            catch(Throwable v0) {
                goto label_26;
            }

            __monitor_exit(this);
            return v0_1;
            try {
            label_6:
                this.o();
                this.x = true;
                int v1 = this.j;
                Drawable[] v2 = this.i;
                v4 = 0;
                while(true) {
                label_13:
                    if(v4 >= v1) {
                        goto label_22;
                    }

                    if(v2[v4].getConstantState() != null) {
                        goto label_20;
                    }

                    this.y = false;
                    break;
                }
            }
            catch(Throwable v0) {
                goto label_26;
            }

            __monitor_exit(this);
            return 0;
        label_20:
            ++v4;
            goto label_13;
            try {
            label_22:
                this.y = true;
            }
            catch(Throwable v0) {
                goto label_26;
            }

            __monitor_exit(this);
            return 1;
        label_26:
            __monitor_exit(this);
            throw v0;
        }

        private void o() {
            if(this.h != null) {
                int v0 = this.h.size();
                int v1;
                for(v1 = 0; v1 < v0; ++v1) {
                    this.i[this.h.keyAt(v1)] = this.b(this.h.valueAt(v1).newDrawable(this.d));
                }

                this.h = null;
            }
        }
    }

    private android.support.v7.d.a.b$b a;
    private Rect b;
    private Drawable c;
    private Drawable d;
    private int e;
    private boolean f;
    private int g;
    private int h;
    private boolean i;
    private Runnable j;
    private long k;
    private long l;
    private a m;

    b() {
        super();
        this.e = 255;
        this.g = -1;
        this.h = -1;
    }

    static int a(Resources arg0, int arg1) {
        if(arg0 == null) {
        }
        else {
            arg1 = arg0.getDisplayMetrics().densityDpi;
        }

        if(arg1 == 0) {
            arg1 = 160;
        }

        return arg1;
    }

    private void a(Drawable arg5) {
        if(this.m == null) {
            this.m = new a();
        }

        arg5.setCallback(this.m.a(arg5.getCallback()));
        try {
            if(this.a.C <= 0 && (this.f)) {
                arg5.setAlpha(this.e);
            }

            if(this.a.G) {
                arg5.setColorFilter(this.a.F);
            }
            else {
                if(this.a.J) {
                    android.support.v4.graphics.drawable.a.a(arg5, this.a.H);
                }

                if(!this.a.K) {
                    goto label_35;
                }

                android.support.v4.graphics.drawable.a.a(arg5, this.a.I);
            }

        label_35:
            arg5.setVisible(this.isVisible(), true);
            arg5.setDither(this.a.z);
            arg5.setState(this.getState());
            arg5.setLevel(this.getLevel());
            arg5.setBounds(this.getBounds());
            if(Build$VERSION.SDK_INT >= 23) {
                arg5.setLayoutDirection(this.getLayoutDirection());
            }

            if(Build$VERSION.SDK_INT >= 19) {
                arg5.setAutoMirrored(this.a.E);
            }

            Rect v0_1 = this.b;
            if(Build$VERSION.SDK_INT >= 21 && v0_1 != null) {
                arg5.setHotspotBounds(v0_1.left, v0_1.top, v0_1.right, v0_1.bottom);
            }
        }
        catch(Throwable v0) {
            arg5.setCallback(this.m.a());
            throw v0;
        }

        arg5.setCallback(this.m.a());
    }

    @SuppressLint(value={"WrongConstant"}) @TargetApi(value=23) private boolean a() {
        boolean v1 = true;
        if(!this.isAutoMirrored() || this.getLayoutDirection() != 1) {
            v1 = false;
        }
        else {
        }

        return v1;
    }

    final void a(Resources arg2) {
        this.a.a(arg2);
    }

    protected void a(android.support.v7.d.a.b$b arg2) {
        this.a = arg2;
        if(this.g >= 0) {
            this.c = arg2.b(this.g);
            if(this.c != null) {
                this.a(this.c);
            }
        }

        this.h = -1;
        this.d = null;
    }

    void a(boolean arg13) {
        int v3;
        int v0 = 1;
        this.f = true;
        long v1 = SystemClock.uptimeMillis();
        long v4 = 255;
        long v7 = 0;
        if(this.c == null) {
        label_31:
            this.k = v7;
        label_32:
            v3 = 0;
        }
        else if(this.k == v7) {
            goto label_32;
        }
        else if(this.k <= v1) {
            this.c.setAlpha(this.e);
            goto label_31;
        }
        else {
            this.c.setAlpha((255 - (((int)((this.k - v1) * v4))) / this.a.C) * this.e / 255);
            v3 = 1;
        }

        if(this.d == null) {
        label_59:
            this.l = v7;
        label_60:
            v0 = v3;
        }
        else if(this.l == v7) {
            goto label_60;
        }
        else if(this.l <= v1) {
            this.d.setVisible(false, false);
            this.d = null;
            this.h = -1;
            goto label_59;
        }
        else {
            this.d.setAlpha((((int)((this.l - v1) * v4))) / this.a.D * this.e / 255);
        }

        if((arg13) && v0 != 0) {
            this.scheduleSelf(this.j, v1 + 16);
        }
    }

    boolean a(int arg9) {
        // Method was not decompiled
    }

    public void applyTheme(Resources$Theme arg2) {
        this.a.a(arg2);
    }

    android.support.v7.d.a.b$b c() {
        return this.a;
    }

    public boolean canApplyTheme() {
        return this.a.canApplyTheme();
    }

    int d() {
        return this.g;
    }

    public void draw(Canvas arg2) {
        if(this.c != null) {
            this.c.draw(arg2);
        }

        if(this.d != null) {
            this.d.draw(arg2);
        }
    }

    public int getAlpha() {
        return this.e;
    }

    public int getChangingConfigurations() {
        return super.getChangingConfigurations() | this.a.getChangingConfigurations();
    }

    public Drawable$ConstantState getConstantState() {
        if(this.a.n()) {
            this.a.f = this.getChangingConfigurations();
            return this.a;
        }

        return null;
    }

    public Drawable getCurrent() {
        return this.c;
    }

    public void getHotspotBounds(Rect arg2) {
        if(this.b != null) {
            arg2.set(this.b);
        }
        else {
            super.getHotspotBounds(arg2);
        }
    }

    public int getIntrinsicHeight() {
        if(this.a.f()) {
            return this.a.h();
        }

        int v0 = this.c != null ? this.c.getIntrinsicHeight() : -1;
        return v0;
    }

    public int getIntrinsicWidth() {
        if(this.a.f()) {
            return this.a.g();
        }

        int v0 = this.c != null ? this.c.getIntrinsicWidth() : -1;
        return v0;
    }

    public int getMinimumHeight() {
        if(this.a.f()) {
            return this.a.j();
        }

        int v0 = this.c != null ? this.c.getMinimumHeight() : 0;
        return v0;
    }

    public int getMinimumWidth() {
        if(this.a.f()) {
            return this.a.i();
        }

        int v0 = this.c != null ? this.c.getMinimumWidth() : 0;
        return v0;
    }

    public int getOpacity() {
        int v0 = this.c == null || !this.c.isVisible() ? -2 : this.a.l();
        return v0;
    }

    public void getOutline(Outline arg2) {
        if(this.c != null) {
            this.c.getOutline(arg2);
        }
    }

    public boolean getPadding(Rect arg4) {
        boolean v0_1;
        Rect v0 = this.a.e();
        if(v0 != null) {
            arg4.set(v0);
            v0_1 = (v0.right | (v0.left | v0.top | v0.bottom)) != 0 ? true : false;
        }
        else if(this.c != null) {
            v0_1 = this.c.getPadding(arg4);
        }
        else {
            v0_1 = super.getPadding(arg4);
        }

        if(this.a()) {
            int v1 = arg4.left;
            arg4.left = arg4.right;
            arg4.right = v1;
        }

        return v0_1;
    }

    public void invalidateDrawable(Drawable arg2) {
        if(this.a != null) {
            this.a.b();
        }

        if(arg2 == this.c && this.getCallback() != null) {
            this.getCallback().invalidateDrawable(((Drawable)this));
        }
    }

    public boolean isAutoMirrored() {
        return this.a.E;
    }

    public boolean isStateful() {
        return this.a.m();
    }

    public void jumpToCurrentState() {
        int v0;
        if(this.d != null) {
            this.d.jumpToCurrentState();
            this.d = null;
            this.h = -1;
            v0 = 1;
        }
        else {
            v0 = 0;
        }

        if(this.c != null) {
            this.c.jumpToCurrentState();
            if(this.f) {
                this.c.setAlpha(this.e);
            }
        }

        long v4 = 0;
        if(this.l != v4) {
            this.l = v4;
            v0 = 1;
        }

        if(this.k != v4) {
            this.k = v4;
            v0 = 1;
        }

        if(v0 != 0) {
            this.invalidateSelf();
        }
    }

    public Drawable mutate() {
        if(!this.i && super.mutate() == this) {
            android.support.v7.d.a.b$b v0 = this.c();
            v0.a();
            this.a(v0);
            this.i = true;
        }

        return this;
    }

    protected void onBoundsChange(Rect arg2) {
        if(this.d != null) {
            this.d.setBounds(arg2);
        }

        if(this.c != null) {
            this.c.setBounds(arg2);
        }
    }

    public boolean onLayoutDirectionChanged(int arg3) {
        return this.a.d(arg3, this.d());
    }

    protected boolean onLevelChange(int arg2) {
        Drawable v0;
        if(this.d != null) {
            v0 = this.d;
        }
        else if(this.c != null) {
            v0 = this.c;
        }
        else {
            return 0;
        }

        return v0.setLevel(arg2);
    }

    protected boolean onStateChange(int[] arg2) {
        Drawable v0;
        if(this.d != null) {
            v0 = this.d;
        }
        else if(this.c != null) {
            v0 = this.c;
        }
        else {
            return 0;
        }

        return v0.setState(arg2);
    }

    public void scheduleDrawable(Drawable arg2, Runnable arg3, long arg4) {
        if(arg2 == this.c && this.getCallback() != null) {
            this.getCallback().scheduleDrawable(((Drawable)this), arg3, arg4);
        }
    }

    public void setAlpha(int arg6) {
        if(!this.f || this.e != arg6) {
            this.f = true;
            this.e = arg6;
            if(this.c != null) {
                if(this.k == 0) {
                    this.c.setAlpha(arg6);
                }
                else {
                    this.a(false);
                }
            }
        }
    }

    public void setAutoMirrored(boolean arg2) {
        if(this.a.E != arg2) {
            this.a.E = arg2;
            if(this.c != null) {
                android.support.v4.graphics.drawable.a.a(this.c, this.a.E);
            }
        }
    }

    public void setColorFilter(ColorFilter arg3) {
        this.a.G = true;
        if(this.a.F != arg3) {
            this.a.F = arg3;
            if(this.c != null) {
                this.c.setColorFilter(arg3);
            }
        }
    }

    public void setDither(boolean arg2) {
        if(this.a.z != arg2) {
            this.a.z = arg2;
            if(this.c != null) {
                this.c.setDither(this.a.z);
            }
        }
    }

    public void setHotspot(float arg2, float arg3) {
        if(this.c != null) {
            android.support.v4.graphics.drawable.a.a(this.c, arg2, arg3);
        }
    }

    public void setHotspotBounds(int arg2, int arg3, int arg4, int arg5) {
        if(this.b == null) {
            this.b = new Rect(arg2, arg3, arg4, arg5);
        }
        else {
            this.b.set(arg2, arg3, arg4, arg5);
        }

        if(this.c != null) {
            android.support.v4.graphics.drawable.a.a(this.c, arg2, arg3, arg4, arg5);
        }
    }

    public void setTintList(ColorStateList arg3) {
        this.a.J = true;
        if(this.a.H != arg3) {
            this.a.H = arg3;
            android.support.v4.graphics.drawable.a.a(this.c, arg3);
        }
    }

    public void setTintMode(PorterDuff$Mode arg3) {
        this.a.K = true;
        if(this.a.I != arg3) {
            this.a.I = arg3;
            android.support.v4.graphics.drawable.a.a(this.c, arg3);
        }
    }

    public boolean setVisible(boolean arg3, boolean arg4) {
        boolean v0 = super.setVisible(arg3, arg4);
        if(this.d != null) {
            this.d.setVisible(arg3, arg4);
        }

        if(this.c != null) {
            this.c.setVisible(arg3, arg4);
        }

        return v0;
    }

    public void unscheduleDrawable(Drawable arg2, Runnable arg3) {
        if(arg2 == this.c && this.getCallback() != null) {
            this.getCallback().unscheduleDrawable(((Drawable)this), arg3);
        }
    }
}

