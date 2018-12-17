package android.support.v7.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build$VERSION;
import android.os.Bundle;
import android.support.v4.app.ah$a;
import android.support.v4.app.ah;
import android.support.v4.app.h;
import android.support.v4.app.u;
import android.support.v7.view.b;
import android.support.v7.widget.bp;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.view.Window;

public class c extends h implements a, d {
    private e k;
    private int l;
    private Resources m;

    public c() {
        super();
        this.l = 0;
    }

    private boolean a(int arg2, KeyEvent arg3) {
        if(Build$VERSION.SDK_INT < 26 && !arg3.isCtrlPressed() && !KeyEvent.metaStateHasNoModifiers(arg3.getMetaState()) && arg3.getRepeatCount() == 0 && !KeyEvent.isModifierKey(arg3.getKeyCode())) {
            Window v2 = this.getWindow();
            if(v2 != null && v2.getDecorView() != null && (v2.getDecorView().dispatchKeyShortcutEvent(arg3))) {
                return 1;
            }
        }

        return 0;
    }

    public Intent a() {
        return u.a(((Activity)this));
    }

    public b a(android.support.v7.view.b$a arg1) {
        return null;
    }

    public void a(ah arg1) {
        arg1.a(((Activity)this));
    }

    public void a(b arg1) {
    }

    public boolean a(Intent arg1) {
        return u.a(((Activity)this), arg1);
    }

    public void addContentView(View arg2, ViewGroup$LayoutParams arg3) {
        this.h().b(arg2, arg3);
    }

    public void b(Intent arg1) {
        u.b(((Activity)this), arg1);
    }

    public void b(ah arg1) {
    }

    public void b(b arg1) {
    }

    public void c() {
        this.h().f();
    }

    public void closeOptionsMenu() {
        android.support.v7.app.a v0 = this.e();
        if((this.getWindow().hasFeature(0)) && (v0 == null || !v0.d())) {
            super.closeOptionsMenu();
        }
    }

    public boolean dispatchKeyEvent(KeyEvent arg4) {
        int v0 = arg4.getKeyCode();
        android.support.v7.app.a v1 = this.e();
        if(v0 == 82 && v1 != null && (v1.a(arg4))) {
            return 1;
        }

        return super.dispatchKeyEvent(arg4);
    }

    public android.support.v7.app.a e() {
        return this.h().a();
    }

    public boolean f() {
        Intent v0 = this.a();
        if(v0 != null) {
            if(this.a(v0)) {
                ah v0_1 = ah.a(((Context)this));
                this.a(v0_1);
                this.b(v0_1);
                v0_1.a();
                try {
                    android.support.v4.app.a.a(((Activity)this));
                }
                catch(IllegalStateException ) {
                    this.finish();
                }
            }
            else {
                this.b(v0);
            }

            return 1;
        }

        return 0;
    }

    public View findViewById(int arg2) {
        return this.h().a(arg2);
    }

    @Deprecated public void g() {
    }

    public MenuInflater getMenuInflater() {
        return this.h().b();
    }

    public Resources getResources() {
        if(this.m == null && (bp.a())) {
            this.m = new bp(((Context)this), super.getResources());
        }

        Resources v0 = this.m == null ? super.getResources() : this.m;
        return v0;
    }

    public e h() {
        if(this.k == null) {
            this.k = e.a(((Activity)this), ((d)this));
        }

        return this.k;
    }

    public void invalidateOptionsMenu() {
        this.h().f();
    }

    public void onConfigurationChanged(Configuration arg3) {
        super.onConfigurationChanged(arg3);
        this.h().a(arg3);
        if(this.m != null) {
            this.m.updateConfiguration(arg3, super.getResources().getDisplayMetrics());
        }
    }

    public void onContentChanged() {
        this.g();
    }

    protected void onCreate(Bundle arg4) {
        e v0 = this.h();
        v0.h();
        v0.a(arg4);
        if((v0.i()) && this.l != 0) {
            if(Build$VERSION.SDK_INT >= 23) {
                this.onApplyThemeResource(this.getTheme(), this.l, false);
            }
            else {
                this.setTheme(this.l);
            }
        }

        super.onCreate(arg4);
    }

    protected void onDestroy() {
        super.onDestroy();
        this.h().g();
    }

    public boolean onKeyDown(int arg2, KeyEvent arg3) {
        if(this.a(arg2, arg3)) {
            return 1;
        }

        return super.onKeyDown(arg2, arg3);
    }

    public final boolean onMenuItemSelected(int arg2, MenuItem arg3) {
        if(super.onMenuItemSelected(arg2, arg3)) {
            return 1;
        }

        android.support.v7.app.a v2 = this.e();
        if(arg3.getItemId() == 16908332 && v2 != null && (v2.a() & 4) != 0) {
            return this.f();
        }

        return 0;
    }

    public boolean onMenuOpened(int arg1, Menu arg2) {
        return super.onMenuOpened(arg1, arg2);
    }

    public void onPanelClosed(int arg1, Menu arg2) {
        super.onPanelClosed(arg1, arg2);
    }

    protected void onPostCreate(Bundle arg2) {
        super.onPostCreate(arg2);
        this.h().b(arg2);
    }

    protected void onPostResume() {
        super.onPostResume();
        this.h().e();
    }

    protected void onSaveInstanceState(Bundle arg2) {
        super.onSaveInstanceState(arg2);
        this.h().c(arg2);
    }

    protected void onStart() {
        super.onStart();
        this.h().c();
    }

    protected void onStop() {
        super.onStop();
        this.h().d();
    }

    protected void onTitleChanged(CharSequence arg1, int arg2) {
        super.onTitleChanged(arg1, arg2);
        this.h().a(arg1);
    }

    public void openOptionsMenu() {
        android.support.v7.app.a v0 = this.e();
        if((this.getWindow().hasFeature(0)) && (v0 == null || !v0.c())) {
            super.openOptionsMenu();
        }
    }

    public void setContentView(int arg2) {
        this.h().b(arg2);
    }

    public void setContentView(View arg2) {
        this.h().a(arg2);
    }

    public void setContentView(View arg2, ViewGroup$LayoutParams arg3) {
        this.h().a(arg2, arg3);
    }

    public void setTheme(int arg1) {
        super.setTheme(arg1);
        this.l = arg1;
    }
}

