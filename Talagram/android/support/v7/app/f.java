package android.support.v7.app;

import android.app.Activity;
import android.app.Dialog;
import android.app.UiModeManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager$NameNotFoundException;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources$NotFoundException;
import android.content.res.Resources$Theme;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.os.Build$VERSION;
import android.os.Bundle;
import android.support.v4.app.u;
import android.support.v4.view.ab;
import android.support.v4.view.t;
import android.support.v4.view.x;
import android.support.v4.view.z;
import android.support.v4.widget.o;
import android.support.v7.a.a$g;
import android.support.v7.a.a$j;
import android.support.v7.view.i;
import android.support.v7.view.menu.h$a;
import android.support.v7.view.menu.h;
import android.support.v7.view.menu.p;
import android.support.v7.widget.ActionBarContextView;
import android.support.v7.widget.ContentFrameLayout;
import android.support.v7.widget.ViewStubCompat;
import android.support.v7.widget.af;
import android.support.v7.widget.bk;
import android.support.v7.widget.bp;
import android.support.v7.widget.bs;
import android.text.TextUtils;
import android.util.AndroidRuntimeException;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.ActionMode$Callback;
import android.view.ActionMode;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.LayoutInflater$Factory2;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup$LayoutParams;
import android.view.ViewGroup$MarginLayoutParams;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window$Callback;
import android.view.Window;
import android.view.WindowManager$LayoutParams;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;

class f extends e implements a, LayoutInflater$Factory2 {
    final class android.support.v7.app.f$1 implements Thread$UncaughtExceptionHandler {
        android.support.v7.app.f$1(Thread$UncaughtExceptionHandler arg1) {
            this.a = arg1;
            super();
        }

        private boolean a(Throwable arg3) {
            boolean v1 = false;
            if((arg3 instanceof Resources$NotFoundException)) {
                String v3 = arg3.getMessage();
                if(v3 != null) {
                    if(!v3.contains("drawable") && !v3.contains("Drawable")) {
                        return v1;
                    }

                    v1 = true;
                }
            }

            return v1;
        }

        public void uncaughtException(Thread arg4, Throwable arg5) {
            if(this.a(arg5)) {
                StringBuilder v1 = new StringBuilder();
                v1.append(arg5.getMessage());
                v1.append(". If the resource you are trying to use is a vector resource, you may be referencing it in an unsupported way. See AppCompatDelegate.setCompatVectorFromResourcesEnabled() for more info.");
                Resources$NotFoundException v0 = new Resources$NotFoundException(v1.toString());
                ((Throwable)v0).initCause(arg5.getCause());
                ((Throwable)v0).setStackTrace(arg5.getStackTrace());
                this.a.uncaughtException(arg4, ((Throwable)v0));
            }
            else {
                this.a.uncaughtException(arg4, arg5);
            }
        }
    }

    class android.support.v7.app.f$2 implements Runnable {
        android.support.v7.app.f$2(f arg1) {
            this.a = arg1;
            super();
        }

        public void run() {
            if((this.a.t & 1) != 0) {
                this.a.g(0);
            }

            if((this.a.t & 4096) != 0) {
                this.a.g(108);
            }

            this.a.s = false;
            this.a.t = 0;
        }
    }

    final class android.support.v7.app.f$a implements android.support.v7.view.menu.o$a {
        android.support.v7.app.f$a(f arg1) {
            this.a = arg1;
            super();
        }

        public void a(h arg1, boolean arg2) {
            this.a.b(arg1);
        }

        public boolean a(h arg3) {
            Window$Callback v0 = this.a.l();
            if(v0 != null) {
                v0.onMenuOpened(108, ((Menu)arg3));
            }

            return 1;
        }
    }

    class b implements android.support.v7.view.b$a {
        private android.support.v7.view.b$a b;

        public b(f arg1, android.support.v7.view.b$a arg2) {
            this.a = arg1;
            super();
            this.b = arg2;
        }

        public void a(android.support.v7.view.b arg3) {
            this.b.a(arg3);
            if(this.a.j != null) {
                this.a.b.getDecorView().removeCallbacks(this.a.k);
            }

            if(this.a.i != null) {
                this.a.q();
                this.a.l = t.m(this.a.i).a(0f);
                this.a.l.a(new z() {
                    public void b(View arg2) {
                        this.a.a.i.setVisibility(8);
                        if(this.a.a.j != null) {
                            this.a.a.j.dismiss();
                        }
                        else if((this.a.a.i.getParent() instanceof View)) {
                            t.s(this.a.a.i.getParent());
                        }

                        this.a.a.i.removeAllViews();
                        this.a.a.l.a(null);
                        this.a.a.l = null;
                    }
                });
            }

            if(this.a.e != null) {
                this.a.e.b(this.a.h);
            }

            this.a.h = null;
        }

        public boolean a(android.support.v7.view.b arg2, Menu arg3) {
            return this.b.a(arg2, arg3);
        }

        public boolean a(android.support.v7.view.b arg2, MenuItem arg3) {
            return this.b.a(arg2, arg3);
        }

        public boolean b(android.support.v7.view.b arg2, Menu arg3) {
            return this.b.b(arg2, arg3);
        }
    }

    class c extends i {
        c(f arg1, Window$Callback arg2) {
            this.a = arg1;
            super(arg2);
        }

        final ActionMode a(ActionMode$Callback arg3) {
            android.support.v7.view.f$a v0 = new android.support.v7.view.f$a(this.a.a, arg3);
            android.support.v7.view.b v3 = this.a.a(((android.support.v7.view.b$a)v0));
            if(v3 != null) {
                return v0.b(v3);
            }

            return null;
        }

        public boolean dispatchKeyEvent(KeyEvent arg2) {
            boolean v2 = (this.a.a(arg2)) || (super.dispatchKeyEvent(arg2)) ? true : false;
            return v2;
        }

        public boolean dispatchKeyShortcutEvent(KeyEvent arg3) {
            boolean v3 = (super.dispatchKeyShortcutEvent(arg3)) || (this.a.a(arg3.getKeyCode(), arg3)) ? true : false;
            return v3;
        }

        public void onContentChanged() {
        }

        public boolean onCreatePanelMenu(int arg2, Menu arg3) {
            if(arg2 == 0 && !(arg3 instanceof h)) {
                return 0;
            }

            return super.onCreatePanelMenu(arg2, arg3);
        }

        public boolean onMenuOpened(int arg1, Menu arg2) {
            super.onMenuOpened(arg1, arg2);
            this.a.e(arg1);
            return 1;
        }

        public void onPanelClosed(int arg1, Menu arg2) {
            super.onPanelClosed(arg1, arg2);
            this.a.d(arg1);
        }

        public boolean onPreparePanel(int arg4, View arg5, Menu arg6) {
            Menu v0 = (arg6 instanceof h) ? arg6 : null;
            if(arg4 == 0 && v0 == null) {
                return 0;
            }

            if(v0 != null) {
                ((h)v0).c(true);
            }

            boolean v4 = super.onPreparePanel(arg4, arg5, arg6);
            if(v0 != null) {
                ((h)v0).c(false);
            }

            return v4;
        }

        public void onProvideKeyboardShortcuts(List arg4, Menu arg5, int arg6) {
            h v5;
            android.support.v7.app.f$f v0 = this.a.a(0, true);
            if(v0 != null && v0.j != null) {
                v5 = v0.j;
            }

            super.onProvideKeyboardShortcuts(arg4, ((Menu)v5), arg6);
        }

        public ActionMode onWindowStartingActionMode(ActionMode$Callback arg3) {
            if(Build$VERSION.SDK_INT >= 23) {
                return null;
            }

            if(this.a.p()) {
                return this.a(arg3);
            }

            return super.onWindowStartingActionMode(arg3);
        }

        public ActionMode onWindowStartingActionMode(ActionMode$Callback arg2, int arg3) {
            if(this.a.p()) {
                if(arg3 != 0) {
                }
                else {
                    return this.a(arg2);
                }
            }

            return super.onWindowStartingActionMode(arg2, arg3);
        }
    }

    final class d {
        private k b;
        private boolean c;
        private BroadcastReceiver d;
        private IntentFilter e;

        d(f arg1, k arg2) {
            this.a = arg1;
            super();
            this.b = arg2;
            this.c = arg2.a();
        }

        int a() {
            this.c = this.b.a();
            int v0 = this.c ? 2 : 1;
            return v0;
        }

        void b() {
            boolean v0 = this.b.a();
            if(v0 != this.c) {
                this.c = v0;
                this.a.i();
            }
        }

        void c() {
            this.d();
            if(this.d == null) {
                this.d = new BroadcastReceiver() {
                    public void onReceive(Context arg1, Intent arg2) {
                        this.a.b();
                    }
                };
            }

            if(this.e == null) {
                this.e = new IntentFilter();
                this.e.addAction("android.intent.action.TIME_SET");
                this.e.addAction("android.intent.action.TIMEZONE_CHANGED");
                this.e.addAction("android.intent.action.TIME_TICK");
            }

            this.a.a.registerReceiver(this.d, this.e);
        }

        void d() {
            if(this.d != null) {
                this.a.a.unregisterReceiver(this.d);
                this.d = null;
            }
        }
    }

    class android.support.v7.app.f$e extends ContentFrameLayout {
        public android.support.v7.app.f$e(f arg1, Context arg2) {
            this.a = arg1;
            super(arg2);
        }

        private boolean a(int arg2, int arg3) {
            int v0 = -5;
            boolean v2 = arg2 < v0 || arg3 < v0 || arg2 > this.getWidth() + 5 || arg3 > this.getHeight() + 5 ? true : false;
            return v2;
        }

        public boolean dispatchKeyEvent(KeyEvent arg2) {
            boolean v2 = (this.a.a(arg2)) || (super.dispatchKeyEvent(arg2)) ? true : false;
            return v2;
        }

        public boolean onInterceptTouchEvent(MotionEvent arg3) {
            if(arg3.getAction() == 0 && (this.a(((int)arg3.getX()), ((int)arg3.getY())))) {
                this.a.f(0);
                return 1;
            }

            return super.onInterceptTouchEvent(arg3);
        }

        public void setBackgroundResource(int arg2) {
            this.setBackgroundDrawable(android.support.v7.c.a.a.b(this.getContext(), arg2));
        }
    }

    public final class android.support.v7.app.f$f {
        int a;
        int b;
        int c;
        int d;
        int e;
        int f;
        ViewGroup g;
        View h;
        View i;
        h j;
        android.support.v7.view.menu.f k;
        Context l;
        boolean m;
        boolean n;
        boolean o;
        public boolean p;
        boolean q;
        boolean r;
        Bundle s;

        android.support.v7.app.f$f(int arg1) {
            super();
            this.a = arg1;
            this.q = false;
        }

        public boolean a() {
            boolean v1 = false;
            if(this.h == null) {
                return 0;
            }

            if(this.i != null) {
                return 1;
            }

            if(this.k.a().getCount() > 0) {
                v1 = true;
            }

            return v1;
        }

        void a(Context arg5) {
            TypedValue v0 = new TypedValue();
            Resources$Theme v1 = arg5.getResources().newTheme();
            v1.setTo(arg5.getTheme());
            v1.resolveAttribute(android.support.v7.a.a$a.actionBarPopupTheme, v0, true);
            if(v0.resourceId != 0) {
                v1.applyStyle(v0.resourceId, true);
            }

            v1.resolveAttribute(android.support.v7.a.a$a.panelMenuListTheme, v0, true);
            int v0_1 = v0.resourceId != 0 ? v0.resourceId : android.support.v7.a.a$i.Theme_AppCompat_CompactMenu;
            v1.applyStyle(v0_1, true);
            android.support.v7.view.d v0_2 = new android.support.v7.view.d(arg5, 0);
            ((Context)v0_2).getTheme().setTo(v1);
            this.l = ((Context)v0_2);
            TypedArray v5 = ((Context)v0_2).obtainStyledAttributes(j.AppCompatTheme);
            this.b = v5.getResourceId(j.AppCompatTheme_panelBackground, 0);
            this.f = v5.getResourceId(j.AppCompatTheme_android_windowAnimationStyle, 0);
            v5.recycle();
        }

        void a(h arg3) {
            if(arg3 == this.j) {
                return;
            }

            if(this.j != null) {
                this.j.b(this.k);
            }

            this.j = arg3;
            if(arg3 != null && this.k != null) {
                arg3.a(this.k);
            }
        }

        p a(android.support.v7.view.menu.o$a arg4) {
            if(this.j == null) {
                return null;
            }

            if(this.k == null) {
                this.k = new android.support.v7.view.menu.f(this.l, g.abc_list_menu_item_layout);
                this.k.a(arg4);
                this.j.a(this.k);
            }

            return this.k.a(this.g);
        }
    }

    final class android.support.v7.app.f$g implements android.support.v7.view.menu.o$a {
        android.support.v7.app.f$g(f arg1) {
            this.a = arg1;
            super();
        }

        public void a(h arg5, boolean arg6) {
            h v0 = arg5.q();
            int v2 = v0 != arg5 ? 1 : 0;
            f v3 = this.a;
            if(v2 != 0) {
                arg5 = v0;
            }

            android.support.v7.app.f$f v5 = v3.a(((Menu)arg5));
            if(v5 != null) {
                if(v2 != 0) {
                    this.a.a(v5.a, v5, ((Menu)v0));
                    this.a.a(v5, true);
                }
                else {
                    this.a.a(v5, arg6);
                }
            }
        }

        public boolean a(h arg3) {
            if(arg3 == null && (this.a.m)) {
                Window$Callback v0 = this.a.l();
                if(v0 != null && !this.a.r) {
                    v0.onMenuOpened(108, ((Menu)arg3));
                }
            }

            return 1;
        }
    }

    private android.support.v7.app.f$g A;
    private boolean B;
    private boolean C;
    private ViewGroup D;
    private TextView E;
    private View F;
    private boolean G;
    private boolean H;
    private boolean I;
    private android.support.v7.app.f$f[] J;
    private android.support.v7.app.f$f K;
    private boolean L;
    private int M;
    private boolean N;
    private d O;
    private final Runnable P;
    private boolean Q;
    private Rect R;
    private Rect S;
    private AppCompatViewInflater T;
    final Context a;
    final Window b;
    final Window$Callback c;
    final Window$Callback d;
    final android.support.v7.app.d e;
    android.support.v7.app.a f;
    MenuInflater g;
    android.support.v7.view.b h;
    ActionBarContextView i;
    PopupWindow j;
    Runnable k;
    x l;
    boolean m;
    boolean n;
    boolean o;
    boolean p;
    boolean q;
    boolean r;
    boolean s;
    int t;
    private static final boolean u;
    private static final int[] v;
    private static boolean w;
    private CharSequence x;
    private af y;
    private android.support.v7.app.f$a z;

    static {
        boolean v0 = Build$VERSION.SDK_INT < 21 ? true : false;
        f.u = v0;
        f.v = new int[]{16842836};
        if((f.u) && !f.w) {
            Thread.setDefaultUncaughtExceptionHandler(new android.support.v7.app.f$1(Thread.getDefaultUncaughtExceptionHandler()));
            f.w = true;
        }
    }

    f(Context arg3, Window arg4, android.support.v7.app.d arg5) {
        super();
        x v0 = null;
        this.l = v0;
        this.B = true;
        this.M = -100;
        this.P = new android.support.v7.app.f$2(this);
        this.a = arg3;
        this.b = arg4;
        this.e = arg5;
        this.c = this.b.getCallback();
        if(!(this.c instanceof c)) {
            this.d = new c(this, this.c);
            this.b.setCallback(this.d);
            bk v3 = bk.a(arg3, ((AttributeSet)v0), f.v);
            Drawable v4 = v3.b(0);
            if(v4 != null) {
                this.b.setBackgroundDrawable(v4);
            }

            v3.a();
            return;
        }

        throw new IllegalStateException("AppCompat has already installed itself into the Window");
    }

    private boolean A() {
        boolean v1 = false;
        if((this.N) && ((this.a instanceof Activity))) {
            PackageManager v0 = this.a.getPackageManager();
            try {
                if((v0.getActivityInfo(new ComponentName(this.a, this.a.getClass()), 0).configChanges & 512) != 0) {
                    return v1;
                }
            }
            catch(PackageManager$NameNotFoundException v0_1) {
                Log.d("AppCompatDelegate", "Exception while getting ActivityInfo", ((Throwable)v0_1));
                return 1;
            }

            return true;
        }

        return 0;
    }

    private void a(android.support.v7.app.f$f arg14, KeyEvent arg15) {
        int v6;
        ViewGroup$LayoutParams v15;
        if(!arg14.o) {
            if(this.r) {
            }
            else {
                if(arg14.a == 0) {
                    int v0 = (this.a.getResources().getConfiguration().screenLayout & 15) == 4 ? 1 : 0;
                    if(v0 == 0) {
                        goto label_21;
                    }

                    return;
                }

            label_21:
                Window$Callback v0_1 = this.l();
                if(v0_1 != null && !v0_1.onMenuOpened(arg14.a, arg14.j)) {
                    this.a(arg14, true);
                    return;
                }

                Object v0_2 = this.a.getSystemService("window");
                if(v0_2 == null) {
                    return;
                }

                if(!this.b(arg14, arg15)) {
                    return;
                }

                int v3 = -1;
                int v4 = -2;
                if(arg14.g == null || (arg14.q)) {
                    if(arg14.g == null) {
                        if((this.a(arg14)) && arg14.g != null) {
                            goto label_67;
                        }

                        return;
                    }
                    else {
                        if(!arg14.q) {
                            goto label_67;
                        }

                        if(arg14.g.getChildCount() <= 0) {
                            goto label_67;
                        }

                        arg14.g.removeAllViews();
                    }

                label_67:
                    if(!this.c(arg14)) {
                        return;
                    }

                    if(!arg14.a()) {
                        return;
                    }

                    v15 = arg14.h.getLayoutParams();
                    if(v15 == null) {
                        v15 = new ViewGroup$LayoutParams(v4, v4);
                    }

                    arg14.g.setBackgroundResource(arg14.b);
                    ViewParent v3_1 = arg14.h.getParent();
                    if(v3_1 != null && ((v3_1 instanceof ViewGroup))) {
                        ((ViewGroup)v3_1).removeView(arg14.h);
                    }

                    arg14.g.addView(arg14.h, v15);
                    if(!arg14.h.hasFocus()) {
                        arg14.h.requestFocus();
                    }

                label_95:
                    v6 = -2;
                }
                else if(arg14.i != null) {
                    v15 = arg14.i.getLayoutParams();
                    if(v15 == null) {
                        goto label_95;
                    }
                    else if(v15.width == v3) {
                        v6 = -1;
                    }
                    else {
                        goto label_95;
                    }
                }
                else {
                    goto label_95;
                }

                arg14.n = false;
                WindowManager$LayoutParams v15_1 = new WindowManager$LayoutParams(v6, -2, arg14.d, arg14.e, 1002, 8519680, -3);
                v15_1.gravity = arg14.c;
                v15_1.windowAnimations = arg14.f;
                ((WindowManager)v0_2).addView(arg14.g, ((ViewGroup$LayoutParams)v15_1));
                arg14.o = true;
            }
        }
    }

    void a(android.support.v7.app.f$f arg4, boolean arg5) {
        if((arg5) && arg4.a == 0 && this.y != null && (this.y.f())) {
            this.b(arg4.j);
            return;
        }

        Object v0 = this.a.getSystemService("window");
        Menu v1 = null;
        if(v0 != null && (arg4.o) && arg4.g != null) {
            ((WindowManager)v0).removeView(arg4.g);
            if(arg5) {
                this.a(arg4.a, arg4, v1);
            }
        }

        arg4.m = false;
        arg4.n = false;
        arg4.o = false;
        arg4.h = ((View)v1);
        arg4.q = true;
        if(this.K == arg4) {
            this.K = ((android.support.v7.app.f$f)v1);
        }
    }

    private boolean a(android.support.v7.app.f$f arg3) {
        arg3.a(this.m());
        arg3.g = new android.support.v7.app.f$e(this, arg3.l);
        arg3.c = 81;
        return 1;
    }

    private void a(h arg5, boolean arg6) {
        if(this.y != null && (this.y.e()) && (!ViewConfiguration.get(this.a).hasPermanentMenuKey() || (this.y.g()))) {
            Window$Callback v5 = this.l();
            int v3 = 108;
            if((this.y.f()) && (arg6)) {
                this.y.i();
                if(!this.r) {
                    v5.onPanelClosed(v3, this.a(0, true).j);
                }
            }
            else if(v5 != null && !this.r) {
                if((this.s) && (this.t & 1) != 0) {
                    this.b.getDecorView().removeCallbacks(this.P);
                    this.P.run();
                }

                android.support.v7.app.f$f v6 = this.a(0, true);
                if(v6.j == null) {
                    return;
                }

                if(v6.r) {
                    return;
                }

                if(!v5.onPreparePanel(0, v6.i, v6.j)) {
                    return;
                }

                v5.onMenuOpened(v3, v6.j);
                this.y.h();
            }

            return;
        }

        android.support.v7.app.f$f v5_1 = this.a(0, true);
        v5_1.q = true;
        this.a(v5_1, false);
        this.a(v5_1, null);
    }

    protected android.support.v7.app.f$f a(int arg4, boolean arg5) {
        android.support.v7.app.f$f[] v5 = this.J;
        if(v5 == null || v5.length <= arg4) {
            android.support.v7.app.f$f[] v0 = new android.support.v7.app.f$f[arg4 + 1];
            if(v5 != null) {
                System.arraycopy(v5, 0, v0, 0, v5.length);
            }

            this.J = v0;
            v5 = v0;
        }

        android.support.v7.app.f$f v0_1 = v5[arg4];
        if(v0_1 == null) {
            v0_1 = new android.support.v7.app.f$f(arg4);
            v5[arg4] = v0_1;
        }

        return v0_1;
    }

    private boolean a(android.support.v7.app.f$f arg3, int arg4, KeyEvent arg5, int arg6) {
        boolean v1 = false;
        if(arg5.isSystem()) {
            return 0;
        }

        if(((arg3.m) || (this.b(arg3, arg5))) && arg3.j != null) {
            v1 = arg3.j.performShortcut(arg4, arg5, arg6);
        }

        if((v1) && (arg6 & 1) == 0 && this.y == null) {
            this.a(arg3, true);
        }

        return v1;
    }

    private boolean a(ViewParent arg4) {
        if(arg4 == null) {
            return 0;
        }

        View v1 = this.b.getDecorView();
        while(true) {
            if(arg4 == null) {
                return 1;
            }

            if(arg4 != (((ViewParent)v1)) && ((arg4 instanceof View))) {
                if(t.D(arg4)) {
                }
                else {
                    arg4 = arg4.getParent();
                    continue;
                }
            }

            return 0;
        }

        return 0;
    }

    void a(ViewGroup arg1) {
    }

    public android.support.v7.app.a a() {
        this.t();
        return this.f;
    }

    android.support.v7.app.f$f a(Menu arg6) {
        android.support.v7.app.f$f[] v0 = this.J;
        int v1 = 0;
        int v2 = v0 != null ? v0.length : 0;
        while(v1 < v2) {
            android.support.v7.app.f$f v3 = v0[v1];
            if(v3 != null && v3.j == arg6) {
                return v3;
            }

            ++v1;
        }

        return null;
    }

    public android.support.v7.view.b a(android.support.v7.view.b$a arg3) {
        if(arg3 != null) {
            if(this.h != null) {
                this.h.c();
            }

            b v0 = new b(this, arg3);
            android.support.v7.app.a v3 = this.a();
            if(v3 != null) {
                this.h = v3.a(((android.support.v7.view.b$a)v0));
                if(this.h != null && this.e != null) {
                    this.e.a(this.h);
                }
            }

            if(this.h == null) {
                this.h = this.b(((android.support.v7.view.b$a)v0));
            }

            return this.h;
        }

        throw new IllegalArgumentException("ActionMode callback can not be null.");
    }

    public View a(int arg2) {
        this.u();
        return this.b.findViewById(arg2);
    }

    public View a(View arg12, String arg13, Context arg14, AttributeSet arg15) {
        boolean v7;
        AppCompatViewInflater v0_1;
        boolean v1 = false;
        if(this.T == null) {
            String v0 = this.a.obtainStyledAttributes(j.AppCompatTheme).getString(j.AppCompatTheme_viewInflaterClass);
            if(v0 != null) {
                if(AppCompatViewInflater.class.getName().equals(v0)) {
                }
                else {
                    try {
                        this.T = Class.forName(v0).getDeclaredConstructor().newInstance();
                        goto label_38;
                    }
                    catch(Throwable v2) {
                        Log.i("AppCompatDelegate", "Failed to instantiate custom view inflater " + v0 + ". Falling back to default.", v2);
                        v0_1 = new AppCompatViewInflater();
                        goto label_37;
                    }
                }
            }

            v0_1 = new AppCompatViewInflater();
        label_37:
            this.T = v0_1;
        }

    label_38:
        if(f.u) {
            if(!(arg15 instanceof XmlPullParser)) {
                v1 = this.a(arg12);
            }
            else if(arg15.getDepth() > 1) {
                v1 = true;
            }

            v7 = v1;
        }
        else {
            v7 = false;
        }

        return this.T.createView(arg12, arg13, arg14, arg15, v7, f.u, true, bp.a());
    }

    void a(int arg2, android.support.v7.app.f$f arg3, Menu arg4) {
        h v4;
        if(arg4 == null) {
            if(arg3 == null && arg2 >= 0 && arg2 < this.J.length) {
                arg3 = this.J[arg2];
            }

            if(arg3 == null) {
                goto label_10;
            }

            v4 = arg3.j;
        }

    label_10:
        if(arg3 != null && !arg3.o) {
            return;
        }

        if(!this.r) {
            this.c.onPanelClosed(arg2, ((Menu)v4));
        }
    }

    public void a(Configuration arg2) {
        if((this.m) && (this.C)) {
            android.support.v7.app.a v0 = this.a();
            if(v0 != null) {
                v0.a(arg2);
            }
        }

        android.support.v7.widget.k.a().a(this.a);
        this.i();
    }

    public void a(Bundle arg3) {
        if((this.c instanceof Activity)) {
            try {
                String v0 = u.b(this.c);
                goto label_7;
            }
            catch(IllegalArgumentException ) {
            label_7:
                if(v0 != null) {
                    android.support.v7.app.a v0_1 = this.k();
                    if(v0_1 == null) {
                        this.Q = true;
                    }
                    else {
                        v0_1.c(true);
                    }
                }
            }
        }

        if(arg3 != null) {
            int v1 = -100;
            if(this.M == v1) {
                this.M = arg3.getInt("appcompat:local_night_mode", v1);
            }
        }
    }

    public void a(h arg2) {
        this.a(arg2, true);
    }

    public void a(View arg3) {
        this.u();
        View v0 = this.D.findViewById(16908290);
        ((ViewGroup)v0).removeAllViews();
        ((ViewGroup)v0).addView(arg3);
        this.c.onContentChanged();
    }

    public void a(View arg3, ViewGroup$LayoutParams arg4) {
        this.u();
        View v0 = this.D.findViewById(16908290);
        ((ViewGroup)v0).removeAllViews();
        ((ViewGroup)v0).addView(arg3, arg4);
        this.c.onContentChanged();
    }

    public final void a(CharSequence arg2) {
        this.x = arg2;
        if(this.y != null) {
            this.y.setWindowTitle(arg2);
        }
        else if(this.k() != null) {
            this.k().a(arg2);
        }
        else if(this.E != null) {
            this.E.setText(arg2);
        }
    }

    boolean a(int arg4, KeyEvent arg5) {
        android.support.v7.app.a v0 = this.a();
        if(v0 != null && (v0.a(arg4, arg5))) {
            return 1;
        }

        if(this.K != null && (this.a(this.K, arg5.getKeyCode(), arg5, 1))) {
            if(this.K != null) {
                this.K.n = true;
            }

            return 1;
        }

        if(this.K == null) {
            android.support.v7.app.f$f v4 = this.a(0, true);
            this.b(v4, arg5);
            boolean v5 = this.a(v4, arg5.getKeyCode(), arg5, 1);
            v4.m = false;
            if(v5) {
                return 1;
            }
        }

        return 0;
    }

    public boolean a(h arg3, MenuItem arg4) {
        Window$Callback v0 = this.l();
        if(v0 != null && !this.r) {
            android.support.v7.app.f$f v3 = this.a(arg3.q());
            if(v3 != null) {
                return v0.onMenuItemSelected(v3.a, arg4);
            }
        }

        return 0;
    }

    boolean a(KeyEvent arg4) {
        int v1 = 1;
        if(((this.c instanceof android.support.v4.view.e$a)) || ((this.c instanceof android.support.v7.app.g))) {
            View v0 = this.b.getDecorView();
            if(v0 != null && (android.support.v4.view.e.a(v0, arg4))) {
                return 1;
            }
        }

        if(arg4.getKeyCode() == 82 && (this.c.dispatchKeyEvent(arg4))) {
            return 1;
        }

        int v0_1 = arg4.getKeyCode();
        if(arg4.getAction() == 0) {
        }
        else {
            v1 = 0;
        }

        boolean v4 = v1 != 0 ? this.c(v0_1, arg4) : this.b(v0_1, arg4);
        return v4;
    }

    private boolean b(android.support.v7.app.f$f arg9, KeyEvent arg10) {
        if(this.r) {
            return 0;
        }

        if(arg9.m) {
            return 1;
        }

        if(this.K != null && this.K != arg9) {
            this.a(this.K, false);
        }

        Window$Callback v0 = this.l();
        if(v0 != null) {
            arg9.i = v0.onCreatePanelView(arg9.a);
        }

        int v3 = arg9.a == 0 || arg9.a == 108 ? 1 : 0;
        if(v3 != 0 && this.y != null) {
            this.y.j();
        }

        if(arg9.i == null && (v3 == 0 || !(this.k() instanceof android.support.v7.app.i))) {
            h v5 = null;
            if(arg9.j == null || (arg9.r)) {
                if(arg9.j == null && (!this.b(arg9) || arg9.j == null)) {
                    return 0;
                }

                if(v3 != 0 && this.y != null) {
                    if(this.z == null) {
                        this.z = new android.support.v7.app.f$a(this);
                    }

                    this.y.a(arg9.j, this.z);
                }

                arg9.j.h();
                if(!v0.onCreatePanelMenu(arg9.a, arg9.j)) {
                    arg9.a(v5);
                    if(v3 != 0 && this.y != null) {
                        this.y.a(((Menu)v5), this.z);
                    }

                    return 0;
                }

                arg9.r = false;
            }

            arg9.j.h();
            if(arg9.s != null) {
                arg9.j.b(arg9.s);
                arg9.s = ((Bundle)v5);
            }

            if(!v0.onPreparePanel(0, arg9.i, arg9.j)) {
                if(v3 != 0 && this.y != null) {
                    this.y.a(((Menu)v5), this.z);
                }

                arg9.j.i();
                return 0;
            }

            int v10 = arg10 != null ? arg10.getDeviceId() : -1;
            boolean v10_1 = KeyCharacterMap.load(v10).getKeyboardType() != 1 ? true : false;
            arg9.p = v10_1;
            arg9.j.setQwertyMode(arg9.p);
            arg9.j.i();
        }

        arg9.m = true;
        arg9.n = false;
        this.K = arg9;
        return 1;
    }

    private boolean b(android.support.v7.app.f$f arg7) {
        android.support.v7.view.d v0_1;
        Context v0 = this.a;
        if((arg7.a == 0 || arg7.a == 108) && this.y != null) {
            TypedValue v1 = new TypedValue();
            Resources$Theme v3 = v0.getTheme();
            v3.resolveAttribute(android.support.v7.a.a$a.actionBarTheme, v1, true);
            Resources$Theme v4 = null;
            if(v1.resourceId != 0) {
                v4 = v0.getResources().newTheme();
                v4.setTo(v3);
                v4.applyStyle(v1.resourceId, true);
                v4.resolveAttribute(android.support.v7.a.a$a.actionBarWidgetTheme, v1, true);
            }
            else {
                v3.resolveAttribute(android.support.v7.a.a$a.actionBarWidgetTheme, v1, true);
            }

            if(v1.resourceId != 0) {
                if(v4 == null) {
                    v4 = v0.getResources().newTheme();
                    v4.setTo(v3);
                }

                v4.applyStyle(v1.resourceId, true);
            }

            if(v4 == null) {
                goto label_42;
            }

            android.support.v7.view.d v1_1 = new android.support.v7.view.d(v0, 0);
            ((Context)v1_1).getTheme().setTo(v4);
            v0_1 = v1_1;
        }

    label_42:
        h v1_2 = new h(((Context)v0_1));
        v1_2.a(((a)this));
        arg7.a(v1_2);
        return 1;
    }

    android.support.v7.view.b b(android.support.v7.view.b$a arg8) {
        Context v4_2;
        android.support.v7.view.d v4_1;
        android.support.v7.view.b v0;
        b v8;
        this.q();
        if(this.h != null) {
            this.h.c();
        }

        if(!(arg8 instanceof b)) {
            v8 = new b(this, arg8);
        }

        AttributeSet v1 = null;
        if(this.e == null) {
            goto label_18;
        }
        else if(!this.r) {
            try {
                v0 = this.e.a(((android.support.v7.view.b$a)v8));
            }
            catch(AbstractMethodError ) {
            label_18:
                v0 = ((android.support.v7.view.b)v1);
            }
        }
        else {
            goto label_18;
        }

        if(v0 != null) {
            this.h = v0;
        }
        else {
            boolean v3 = true;
            if(this.i == null) {
                if(this.p) {
                    TypedValue v0_1 = new TypedValue();
                    Resources$Theme v4 = this.a.getTheme();
                    v4.resolveAttribute(android.support.v7.a.a$a.actionBarTheme, v0_1, true);
                    if(v0_1.resourceId != 0) {
                        Resources$Theme v5 = this.a.getResources().newTheme();
                        v5.setTo(v4);
                        v5.applyStyle(v0_1.resourceId, true);
                        v4_1 = new android.support.v7.view.d(this.a, 0);
                        ((Context)v4_1).getTheme().setTo(v5);
                    }
                    else {
                        v4_2 = this.a;
                    }

                    this.i = new ActionBarContextView(((Context)v4_1));
                    this.j = new PopupWindow(((Context)v4_1), v1, android.support.v7.a.a$a.actionModePopupWindowStyle);
                    o.a(this.j, 2);
                    this.j.setContentView(this.i);
                    this.j.setWidth(-1);
                    ((Context)v4_1).getTheme().resolveAttribute(android.support.v7.a.a$a.actionBarSize, v0_1, true);
                    this.i.setContentHeight(TypedValue.complexToDimensionPixelSize(v0_1.data, ((Context)v4_1).getResources().getDisplayMetrics()));
                    this.j.setHeight(-2);
                    this.k = new Runnable() {
                        public void run() {
                            this.a.j.showAtLocation(this.a.i, 55, 0, 0);
                            this.a.q();
                            float v1 = 1f;
                            if(this.a.o()) {
                                this.a.i.setAlpha(0f);
                                this.a.l = t.m(this.a.i).a(v1);
                                this.a.l.a(new z() {
                                    public void a(View arg2) {
                                        this.a.a.i.setVisibility(0);
                                    }

                                    public void b(View arg2) {
                                        this.a.a.i.setAlpha(1f);
                                        this.a.a.l.a(null);
                                        this.a.a.l = null;
                                    }
                                });
                            }
                            else {
                                this.a.i.setAlpha(v1);
                                this.a.i.setVisibility(0);
                            }
                        }
                    };
                }
                else {
                    View v0_2 = this.D.findViewById(android.support.v7.a.a$f.action_mode_bar_stub);
                    if(v0_2 == null) {
                        goto label_90;
                    }

                    ((ViewStubCompat)v0_2).setLayoutInflater(LayoutInflater.from(this.m()));
                    this.i = ((ViewStubCompat)v0_2).a();
                }
            }

        label_90:
            if(this.i == null) {
                goto label_148;
            }

            this.q();
            this.i.c();
            v4_2 = this.i.getContext();
            ActionBarContextView v5_1 = this.i;
            if(this.j == null) {
            }
            else {
                v3 = false;
            }

            android.support.v7.view.e v0_3 = new android.support.v7.view.e(v4_2, v5_1, ((android.support.v7.view.b$a)v8), v3);
            if(((android.support.v7.view.b$a)v8).a(((android.support.v7.view.b)v0_3), ((android.support.v7.view.b)v0_3).b())) {
                ((android.support.v7.view.b)v0_3).d();
                this.i.a(((android.support.v7.view.b)v0_3));
                this.h = ((android.support.v7.view.b)v0_3);
                float v0_4 = 1f;
                if(this.o()) {
                    this.i.setAlpha(0f);
                    this.l = t.m(this.i).a(v0_4);
                    this.l.a(new z() {
                        public void a(View arg2) {
                            this.a.i.setVisibility(0);
                            this.a.i.sendAccessibilityEvent(32);
                            if((this.a.i.getParent() instanceof View)) {
                                t.s(this.a.i.getParent());
                            }
                        }

                        public void b(View arg2) {
                            this.a.i.setAlpha(1f);
                            this.a.l.a(null);
                            this.a.l = null;
                        }
                    });
                }
                else {
                    this.i.setAlpha(v0_4);
                    this.i.setVisibility(0);
                    this.i.sendAccessibilityEvent(32);
                    if((this.i.getParent() instanceof View)) {
                        t.s(this.i.getParent());
                    }
                }

                if(this.j == null) {
                    goto label_148;
                }

                this.b.getDecorView().post(this.k);
                goto label_148;
            }

            this.h = ((android.support.v7.view.b)v1);
        }

    label_148:
        if(this.h != null && this.e != null) {
            this.e.a(this.h);
        }

        return this.h;
    }

    void b(h arg3) {
        if(this.I) {
            return;
        }

        this.I = true;
        this.y.k();
        Window$Callback v0 = this.l();
        if(v0 != null && !this.r) {
            v0.onPanelClosed(108, ((Menu)arg3));
        }

        this.I = false;
    }

    boolean b(int arg4, KeyEvent arg5) {
        if(arg4 == 4) {
            boolean v4 = this.L;
            this.L = false;
            android.support.v7.app.f$f v5 = this.a(0, false);
            if(v5 != null && (v5.o)) {
                if(!v4) {
                    this.a(v5, true);
                }

                return 1;
            }

            if(!this.r()) {
                return 0;
            }

            return 1;
        }
        else if(arg4 != 82) {
        }
        else {
            this.e(0, arg5);
            return 1;
        }

        return 0;
    }

    public MenuInflater b() {
        if(this.g == null) {
            this.t();
            Context v1 = this.f != null ? this.f.b() : this.a;
            this.g = new android.support.v7.view.g(v1);
        }

        return this.g;
    }

    public void b(int arg3) {
        this.u();
        View v0 = this.D.findViewById(16908290);
        ((ViewGroup)v0).removeAllViews();
        LayoutInflater.from(this.a).inflate(arg3, ((ViewGroup)v0));
        this.c.onContentChanged();
    }

    public void b(Bundle arg1) {
        this.u();
    }

    public void b(View arg3, ViewGroup$LayoutParams arg4) {
        this.u();
        this.D.findViewById(16908290).addView(arg3, arg4);
        this.c.onContentChanged();
    }

    private boolean c(android.support.v7.app.f$f arg4) {
        boolean v1 = true;
        if(arg4.i != null) {
            arg4.h = arg4.i;
            return 1;
        }

        if(arg4.j == null) {
            return 0;
        }

        if(this.A == null) {
            this.A = new android.support.v7.app.f$g(this);
        }

        arg4.h = arg4.a(this.A);
        if(arg4.h != null) {
        }
        else {
            v1 = false;
        }

        return v1;
    }

    public boolean c(int arg4) {
        arg4 = this.k(arg4);
        if((this.q) && arg4 == 108) {
            return 0;
        }

        if((this.m) && arg4 == 1) {
            this.m = false;
        }

        switch(arg4) {
            case 1: {
                goto label_31;
            }
            case 2: {
                goto label_28;
            }
            case 5: {
                goto label_25;
            }
            case 10: {
                goto label_22;
            }
            case 108: {
                goto label_19;
            }
            case 109: {
                goto label_16;
            }
        }

        return this.b.requestFeature(arg4);
    label_19:
        this.x();
        this.m = true;
        return 1;
    label_22:
        this.x();
        this.o = true;
        return 1;
    label_25:
        this.x();
        this.H = true;
        return 1;
    label_28:
        this.x();
        this.G = true;
        return 1;
    label_31:
        this.x();
        this.q = true;
        return 1;
    label_16:
        this.x();
        this.n = true;
        return 1;
    }

    boolean c(int arg4, KeyEvent arg5) {
        boolean v1 = true;
        if(arg4 == 4) {
            if((arg5.getFlags() & 128) != 0) {
            }
            else {
                v1 = false;
            }

            this.L = v1;
        }
        else if(arg4 != 82) {
        }
        else {
            this.d(0, arg5);
            return 1;
        }

        return 0;
    }

    public void c() {
        this.i();
    }

    public void c(Bundle arg3) {
        if(this.M != -100) {
            arg3.putInt("appcompat:local_night_mode", this.M);
        }
    }

    private boolean d(int arg2, KeyEvent arg3) {
        if(arg3.getRepeatCount() == 0) {
            android.support.v7.app.f$f v2 = this.a(arg2, true);
            if(!v2.o) {
                return this.b(v2, arg3);
            }
        }

        return 0;
    }

    public void d() {
        android.support.v7.app.a v0 = this.a();
        if(v0 != null) {
            v0.d(false);
        }

        if(this.O != null) {
            this.O.d();
        }
    }

    void d(int arg3) {
        if(arg3 == 108) {
            android.support.v7.app.a v3 = this.a();
            if(v3 != null) {
                v3.e(false);
            }
        }
        else if(arg3 == 0) {
            android.support.v7.app.f$f v3_1 = this.a(arg3, true);
            if(v3_1.o) {
                this.a(v3_1, false);
            }
        }
    }

    private boolean e(int arg4, KeyEvent arg5) {
        boolean v4;
        if(this.h != null) {
            return 0;
        }

        android.support.v7.app.f$f v2 = this.a(arg4, true);
        if(arg4 != 0 || this.y == null || !this.y.e() || (ViewConfiguration.get(this.a).hasPermanentMenuKey())) {
            if(!v2.o) {
                if(v2.n) {
                }
                else {
                    if(v2.m) {
                        if(v2.r) {
                            v2.m = false;
                            v4 = this.b(v2, arg5);
                        }
                        else {
                            v4 = true;
                        }

                        if(!v4) {
                            goto label_46;
                        }

                        this.a(v2, arg5);
                        v4 = true;
                    }
                    else {
                    label_46:
                        v4 = false;
                    }

                    goto label_50;
                }
            }

            v4 = v2.o;
            this.a(v2, true);
        }
        else if(this.y.f()) {
            v4 = this.y.i();
        }
        else if(this.r) {
            goto label_46;
        }
        else if(this.b(v2, arg5)) {
            v4 = this.y.h();
        }
        else {
            goto label_46;
        }

    label_50:
        if(v4) {
            Object v5 = this.a.getSystemService("audio");
            if(v5 != null) {
                ((AudioManager)v5).playSoundEffect(0);
            }
            else {
                Log.w("AppCompatDelegate", "Couldn\'t get audio manager");
            }
        }

        return v4;
    }

    public void e() {
        android.support.v7.app.a v0 = this.a();
        if(v0 != null) {
            v0.d(true);
        }
    }

    void e(int arg2) {
        if(arg2 == 108) {
            android.support.v7.app.a v2 = this.a();
            if(v2 != null) {
                v2.e(true);
            }
        }
    }

    public void f() {
        android.support.v7.app.a v0 = this.a();
        if(v0 != null && (v0.e())) {
            return;
        }

        this.j(0);
    }

    void f(int arg2) {
        this.a(this.a(arg2, true), true);
    }

    public void g() {
        if(this.s) {
            this.b.getDecorView().removeCallbacks(this.P);
        }

        this.r = true;
        if(this.f != null) {
            this.f.g();
        }

        if(this.O != null) {
            this.O.d();
        }
    }

    void g(int arg5) {
        android.support.v7.app.f$f v1 = this.a(arg5, true);
        if(v1.j != null) {
            Bundle v2 = new Bundle();
            v1.j.a(v2);
            if(v2.size() > 0) {
                v1.s = v2;
            }

            v1.j.h();
            v1.j.clear();
        }

        v1.r = true;
        v1.q = true;
        if((arg5 == 108 || arg5 == 0) && this.y != null) {
            android.support.v7.app.f$f v0 = this.a(0, false);
            if(v0 != null) {
                v0.m = false;
                this.b(v0, null);
            }
        }
    }

    int h(int arg8) {
        int v2_1;
        int v3;
        int v1 = 0;
        if(this.i == null || !(this.i.getLayoutParams() instanceof ViewGroup$MarginLayoutParams)) {
            v3 = 0;
        }
        else {
            ViewGroup$LayoutParams v0 = this.i.getLayoutParams();
            v3 = 1;
            if(this.i.isShown()) {
                if(this.R == null) {
                    this.R = new Rect();
                    this.S = new Rect();
                }

                Rect v2 = this.R;
                Rect v4 = this.S;
                v2.set(0, arg8, 0, 0);
                bs.a(this.D, v2, v4);
                v2_1 = v4.top == 0 ? arg8 : 0;
                if(((ViewGroup$MarginLayoutParams)v0).topMargin != v2_1) {
                    ((ViewGroup$MarginLayoutParams)v0).topMargin = arg8;
                    if(this.F == null) {
                        this.F = new View(this.a);
                        this.F.setBackgroundColor(this.a.getResources().getColor(android.support.v7.a.a$c.abc_input_method_navigation_guard));
                        this.D.addView(this.F, -1, new ViewGroup$LayoutParams(-1, arg8));
                    }
                    else {
                        ViewGroup$LayoutParams v2_2 = this.F.getLayoutParams();
                        if(v2_2.height != arg8) {
                            v2_2.height = arg8;
                            this.F.setLayoutParams(v2_2);
                        }
                    }

                    v2_1 = 1;
                }
                else {
                    v2_1 = 0;
                }

                if(this.F != null) {
                    goto label_79;
                }

                v3 = 0;
            }
            else {
                if(((ViewGroup$MarginLayoutParams)v0).topMargin != 0) {
                    ((ViewGroup$MarginLayoutParams)v0).topMargin = 0;
                    v2_1 = 1;
                }
                else {
                    v2_1 = 0;
                }

                v3 = 0;
            }

        label_79:
            if(v2_1 == 0) {
                goto label_84;
            }

            this.i.setLayoutParams(v0);
        }

    label_84:
        if(this.F != null) {
            View v0_1 = this.F;
            if(v3 != 0) {
            }
            else {
                v1 = 8;
            }

            v0_1.setVisibility(v1);
        }

        return 0;
    }

    public void h() {
        LayoutInflater v0 = LayoutInflater.from(this.a);
        if(v0.getFactory() == null) {
            android.support.v4.view.f.a(v0, ((LayoutInflater$Factory2)this));
        }
        else if(!(v0.getFactory2() instanceof f)) {
            Log.i("AppCompatDelegate", "The Activity\'s LayoutInflater already has a Factory installed so we can not install AppCompat\'s");
        }
    }

    public boolean i() {
        int v0 = this.y();
        int v1 = this.i(v0);
        boolean v1_1 = v1 != -1 ? this.l(v1) : false;
        if(v0 == 0) {
            this.z();
            this.O.c();
        }

        this.N = true;
        return v1_1;
    }

    int i(int arg3) {
        int v1 = -1;
        if(arg3 != -100) {
            if(arg3 != 0) {
                return arg3;
            }

            if(Build$VERSION.SDK_INT >= 23 && this.a.getSystemService(UiModeManager.class).getNightMode() == 0) {
                return v1;
            }

            this.z();
            return this.O.a();
        }

        return v1;
    }

    private void j(int arg3) {
        this.t |= 1 << arg3;
        if(!this.s) {
            t.a(this.b.getDecorView(), this.P);
            this.s = true;
        }
    }

    final android.support.v7.app.a k() {
        return this.f;
    }

    private int k(int arg2) {
        if(arg2 == 8) {
            Log.i("AppCompatDelegate", "You should now use the AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR id when requesting this feature.");
            return 108;
        }

        if(arg2 == 9) {
            Log.i("AppCompatDelegate", "You should now use the AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR_OVERLAY id when requesting this feature.");
            arg2 = 109;
        }

        return arg2;
    }

    final Window$Callback l() {
        return this.b.getCallback();
    }

    private boolean l(int arg5) {
        Resources v0 = this.a.getResources();
        Configuration v1 = v0.getConfiguration();
        int v2 = v1.uiMode & 48;
        arg5 = arg5 == 2 ? 32 : 16;
        if(v2 != arg5) {
            if(this.A()) {
                this.a.recreate();
            }
            else {
                Configuration v2_1 = new Configuration(v1);
                DisplayMetrics v1_1 = v0.getDisplayMetrics();
                v2_1.uiMode = arg5 | v2_1.uiMode & -49;
                v0.updateConfiguration(v2_1, v1_1);
                if(Build$VERSION.SDK_INT < 26) {
                    android.support.v7.app.h.a(v0);
                }
            }

            return 1;
        }

        return 0;
    }

    final Context m() {
        android.support.v7.app.a v0 = this.a();
        Context v0_1 = v0 != null ? v0.b() : null;
        if(v0_1 == null) {
            v0_1 = this.a;
        }

        return v0_1;
    }

    final CharSequence n() {
        if((this.c instanceof Activity)) {
            return this.c.getTitle();
        }

        return this.x;
    }

    final boolean o() {
        boolean v0 = !this.C || this.D == null || !t.A(this.D) ? false : true;
        return v0;
    }

    public final View onCreateView(View arg1, String arg2, Context arg3, AttributeSet arg4) {
        return this.a(arg1, arg2, arg3, arg4);
    }

    public View onCreateView(String arg2, Context arg3, AttributeSet arg4) {
        return this.onCreateView(null, arg2, arg3, arg4);
    }

    public boolean p() {
        return this.B;
    }

    void q() {
        if(this.l != null) {
            this.l.b();
        }
    }

    boolean r() {
        if(this.h != null) {
            this.h.c();
            return 1;
        }

        android.support.v7.app.a v0 = this.a();
        if(v0 != null && (v0.f())) {
            return 1;
        }

        return 0;
    }

    void s() {
        if(this.y != null) {
            this.y.k();
        }

        if(this.j != null) {
            this.b.getDecorView().removeCallbacks(this.k);
            if(this.j.isShowing()) {
                try {
                    this.j.dismiss();
                    goto label_15;
                }
                catch(IllegalArgumentException ) {
                label_15:
                    this.j = null;
                    goto label_17;
                }
            }

            goto label_15;
        }

    label_17:
        this.q();
        android.support.v7.app.f$f v0 = this.a(0, false);
        if(v0 != null && v0.j != null) {
            v0.j.close();
        }
    }

    private void t() {
        l v0;
        this.u();
        if(this.m) {
            if(this.f != null) {
            }
            else {
                if((this.c instanceof Activity)) {
                    v0 = new l(this.c, this.n);
                    goto label_13;
                }
                else if((this.c instanceof Dialog)) {
                    v0 = new l(this.c);
                label_13:
                    this.f = ((android.support.v7.app.a)v0);
                }

                if(this.f == null) {
                    return;
                }

                this.f.c(this.Q);
            }
        }
    }

    private void u() {
        if(!this.C) {
            this.D = this.v();
            CharSequence v0 = this.n();
            if(!TextUtils.isEmpty(v0)) {
                if(this.y != null) {
                    this.y.setWindowTitle(v0);
                }
                else if(this.k() != null) {
                    this.k().a(v0);
                }
                else if(this.E != null) {
                    this.E.setText(v0);
                }
            }

            this.w();
            this.a(this.D);
            this.C = true;
            android.support.v7.app.f$f v0_1 = this.a(0, false);
            if(this.r) {
                return;
            }

            if(v0_1 != null && v0_1.j != null) {
                return;
            }

            this.j(108);
        }
    }

    private ViewGroup v() {
        Context v1_1;
        View v0_2;
        TypedArray v0 = this.a.obtainStyledAttributes(j.AppCompatTheme);
        if(v0.hasValue(j.AppCompatTheme_windowActionBar)) {
            if(v0.getBoolean(j.AppCompatTheme_windowNoTitle, false)) {
                this.c(1);
            }
            else if(v0.getBoolean(j.AppCompatTheme_windowActionBar, false)) {
                this.c(108);
            }

            int v4 = 109;
            if(v0.getBoolean(j.AppCompatTheme_windowActionBarOverlay, false)) {
                this.c(v4);
            }

            if(v0.getBoolean(j.AppCompatTheme_windowActionModeOverlay, false)) {
                this.c(10);
            }

            this.p = v0.getBoolean(j.AppCompatTheme_android_windowIsFloating, false);
            v0.recycle();
            this.b.getDecorView();
            LayoutInflater v0_1 = LayoutInflater.from(this.a);
            ViewGroup v5 = null;
            if(this.q) {
                int v1_2 = this.o ? g.abc_screen_simple_overlay_action_mode : g.abc_screen_simple;
                v0_2 = v0_1.inflate(v1_2, v5);
                if(Build$VERSION.SDK_INT >= 21) {
                    t.a(v0_2, new android.support.v4.view.p() {
                        public ab a(View arg5, ab arg6) {
                            int v0 = arg6.b();
                            int v1 = this.a.h(v0);
                            if(v0 != v1) {
                                arg6 = arg6.a(arg6.a(), v1, arg6.c(), arg6.d());
                            }

                            return t.a(arg5, arg6);
                        }
                    });
                    goto label_106;
                }

                v0_2.setOnFitSystemWindowsListener(new android.support.v7.widget.al$a() {
                    public void a(Rect arg3) {
                        arg3.top = this.a.h(arg3.top);
                    }
                });
            }
            else if(this.p) {
                v0_2 = v0_1.inflate(g.abc_dialog_title_material, v5);
                this.n = false;
                this.m = false;
            }
            else if(this.m) {
                TypedValue v0_3 = new TypedValue();
                this.a.getTheme().resolveAttribute(android.support.v7.a.a$a.actionBarTheme, v0_3, true);
                if(v0_3.resourceId != 0) {
                    android.support.v7.view.d v1 = new android.support.v7.view.d(this.a, v0_3.resourceId);
                }
                else {
                    v1_1 = this.a;
                }

                v0_2 = LayoutInflater.from(v1_1).inflate(g.abc_screen_toolbar, v5);
                this.y = ((ViewGroup)v0_2).findViewById(android.support.v7.a.a$f.decor_content_parent);
                this.y.setWindowCallback(this.l());
                if(this.n) {
                    this.y.a(v4);
                }

                if(this.G) {
                    this.y.a(2);
                }

                if(!this.H) {
                    goto label_106;
                }

                this.y.a(5);
            }
            else {
                v0_2 = ((View)v5);
            }

        label_106:
            if(v0_2 != null) {
                if(this.y == null) {
                    this.E = ((ViewGroup)v0_2).findViewById(android.support.v7.a.a$f.title);
                }

                bs.b(v0_2);
                View v1_3 = ((ViewGroup)v0_2).findViewById(android.support.v7.a.a$f.action_bar_activity_content);
                v4 = 16908290;
                View v3 = this.b.findViewById(v4);
                if(v3 != null) {
                    while(((ViewGroup)v3).getChildCount() > 0) {
                        View v6 = ((ViewGroup)v3).getChildAt(0);
                        ((ViewGroup)v3).removeViewAt(0);
                        ((ContentFrameLayout)v1_3).addView(v6);
                    }

                    ((ViewGroup)v3).setId(-1);
                    ((ContentFrameLayout)v1_3).setId(v4);
                    if((v3 instanceof FrameLayout)) {
                        ((FrameLayout)v3).setForeground(((Drawable)v5));
                    }
                }

                this.b.setContentView(v0_2);
                ((ContentFrameLayout)v1_3).setAttachListener(new android.support.v7.widget.ContentFrameLayout$a() {
                    public void a() {
                    }

                    public void b() {
                        this.a.s();
                    }
                });
                return ((ViewGroup)v0_2);
            }

            StringBuilder v1_4 = new StringBuilder();
            v1_4.append("AppCompat does not support the current theme features: { windowActionBar: ");
            v1_4.append(this.m);
            v1_4.append(", windowActionBarOverlay: ");
            v1_4.append(this.n);
            v1_4.append(", android:windowIsFloating: ");
            v1_4.append(this.p);
            v1_4.append(", windowActionModeOverlay: ");
            v1_4.append(this.o);
            v1_4.append(", windowNoTitle: ");
            v1_4.append(this.q);
            v1_4.append(" }");
            throw new IllegalArgumentException(v1_4.toString());
        }

        v0.recycle();
        throw new IllegalStateException("You need to use a Theme.AppCompat theme (or descendant) with this activity.");
    }

    private void w() {
        View v0 = this.D.findViewById(16908290);
        View v1 = this.b.getDecorView();
        ((ContentFrameLayout)v0).a(v1.getPaddingLeft(), v1.getPaddingTop(), v1.getPaddingRight(), v1.getPaddingBottom());
        TypedArray v1_1 = this.a.obtainStyledAttributes(j.AppCompatTheme);
        v1_1.getValue(j.AppCompatTheme_windowMinWidthMajor, ((ContentFrameLayout)v0).getMinWidthMajor());
        v1_1.getValue(j.AppCompatTheme_windowMinWidthMinor, ((ContentFrameLayout)v0).getMinWidthMinor());
        if(v1_1.hasValue(j.AppCompatTheme_windowFixedWidthMajor)) {
            v1_1.getValue(j.AppCompatTheme_windowFixedWidthMajor, ((ContentFrameLayout)v0).getFixedWidthMajor());
        }

        if(v1_1.hasValue(j.AppCompatTheme_windowFixedWidthMinor)) {
            v1_1.getValue(j.AppCompatTheme_windowFixedWidthMinor, ((ContentFrameLayout)v0).getFixedWidthMinor());
        }

        if(v1_1.hasValue(j.AppCompatTheme_windowFixedHeightMajor)) {
            v1_1.getValue(j.AppCompatTheme_windowFixedHeightMajor, ((ContentFrameLayout)v0).getFixedHeightMajor());
        }

        if(v1_1.hasValue(j.AppCompatTheme_windowFixedHeightMinor)) {
            v1_1.getValue(j.AppCompatTheme_windowFixedHeightMinor, ((ContentFrameLayout)v0).getFixedHeightMinor());
        }

        v1_1.recycle();
        ((ContentFrameLayout)v0).requestLayout();
    }

    private void x() {
        if(!this.C) {
            return;
        }

        throw new AndroidRuntimeException("Window feature must be requested before adding content");
    }

    private int y() {
        int v0 = this.M != -100 ? this.M : f.j();
        return v0;
    }

    private void z() {
        if(this.O == null) {
            this.O = new d(this, k.a(this.a));
        }
    }
}

