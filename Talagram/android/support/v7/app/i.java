package android.support.v7.app;

import android.content.Context;
import android.content.res.Configuration;
import android.support.v4.view.t;
import android.support.v7.view.menu.h;
import android.support.v7.widget.ag;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window$Callback;
import java.util.ArrayList;

class i extends a {
    final class android.support.v7.app.i$a implements android.support.v7.view.menu.o$a {
        private boolean b;

        android.support.v7.app.i$a(i arg1) {
            this.a = arg1;
            super();
        }

        public void a(h arg2, boolean arg3) {
            if(this.b) {
                return;
            }

            this.b = true;
            this.a.a.n();
            if(this.a.b != null) {
                this.a.b.onPanelClosed(108, ((Menu)arg2));
            }

            this.b = false;
        }

        public boolean a(h arg3) {
            if(this.a.b != null) {
                this.a.b.onMenuOpened(108, ((Menu)arg3));
                return 1;
            }

            return 0;
        }
    }

    final class b implements android.support.v7.view.menu.h$a {
        b(i arg1) {
            this.a = arg1;
            super();
        }

        public void a(h arg5) {
            if(this.a.b != null) {
                int v1 = 108;
                if(this.a.a.i()) {
                    this.a.b.onPanelClosed(v1, ((Menu)arg5));
                }
                else if(this.a.b.onPreparePanel(0, null, ((Menu)arg5))) {
                    this.a.b.onMenuOpened(v1, ((Menu)arg5));
                }
            }
        }

        public boolean a(h arg1, MenuItem arg2) {
            return 0;
        }
    }

    ag a;
    Window$Callback b;
    private boolean c;
    private boolean d;
    private ArrayList e;
    private final Runnable f;

    public int a() {
        return this.a.o();
    }

    public void a(float arg2) {
        t.k(this.a.a(), arg2);
    }

    public void a(Configuration arg1) {
        super.a(arg1);
    }

    public void a(CharSequence arg2) {
        this.a.a(arg2);
    }

    public void a(boolean arg1) {
    }

    public boolean a(int arg5, KeyEvent arg6) {
        Menu v0 = this.h();
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

    public boolean a(KeyEvent arg2) {
        if(arg2.getAction() == 1) {
            this.c();
        }

        return 1;
    }

    public Context b() {
        return this.a.b();
    }

    public boolean c() {
        return this.a.k();
    }

    public void c(boolean arg1) {
    }

    public void d(boolean arg1) {
    }

    public boolean d() {
        return this.a.l();
    }

    public void e(boolean arg4) {
        if(arg4 == this.d) {
            return;
        }

        this.d = arg4;
        int v0 = this.e.size();
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            this.e.get(v1).a(arg4);
        }
    }

    public boolean e() {
        this.a.a().removeCallbacks(this.f);
        t.a(this.a.a(), this.f);
        return 1;
    }

    public boolean f() {
        if(this.a.c()) {
            this.a.d();
            return 1;
        }

        return 0;
    }

    void g() {
        this.a.a().removeCallbacks(this.f);
    }

    private Menu h() {
        if(!this.c) {
            this.a.a(new android.support.v7.app.i$a(this), new b(this));
            this.c = true;
        }

        return this.a.q();
    }
}

