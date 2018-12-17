package android.support.v7.app;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.e$a;
import android.support.v7.view.b;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.view.Window$Callback;

public class g extends Dialog implements d {
    class android.support.v7.app.g$1 implements a {
        android.support.v7.app.g$1(g arg1) {
            this.a = arg1;
            super();
        }

        public boolean a(KeyEvent arg2) {
            return this.a.a(arg2);
        }
    }

    private e a;
    private final a b;

    public g(Context arg1, int arg2) {
        super(arg1, g.a(arg1, arg2));
        this.b = new android.support.v7.app.g$1(this);
        this.a().a(null);
        this.a().i();
    }

    public boolean a(int arg2) {
        return this.a().c(arg2);
    }

    private static int a(Context arg2, int arg3) {
        if(arg3 == 0) {
            TypedValue v3 = new TypedValue();
            arg2.getTheme().resolveAttribute(android.support.v7.a.a$a.dialogTheme, v3, true);
            arg3 = v3.resourceId;
        }

        return arg3;
    }

    public e a() {
        if(this.a == null) {
            this.a = e.a(((Dialog)this), ((d)this));
        }

        return this.a;
    }

    public b a(android.support.v7.view.b$a arg1) {
        return null;
    }

    public void a(b arg1) {
    }

    boolean a(KeyEvent arg1) {
        return super.dispatchKeyEvent(arg1);
    }

    public void addContentView(View arg2, ViewGroup$LayoutParams arg3) {
        this.a().b(arg2, arg3);
    }

    public void b(b arg1) {
    }

    public boolean dispatchKeyEvent(KeyEvent arg3) {
        return android.support.v4.view.e.a(this.b, this.getWindow().getDecorView(), ((Window$Callback)this), arg3);
    }

    public View findViewById(int arg2) {
        return this.a().a(arg2);
    }

    public void invalidateOptionsMenu() {
        this.a().f();
    }

    protected void onCreate(Bundle arg2) {
        this.a().h();
        super.onCreate(arg2);
        this.a().a(arg2);
    }

    protected void onStop() {
        super.onStop();
        this.a().d();
    }

    public void setContentView(int arg2) {
        this.a().b(arg2);
    }

    public void setContentView(View arg2) {
        this.a().a(arg2);
    }

    public void setContentView(View arg2, ViewGroup$LayoutParams arg3) {
        this.a().a(arg2, arg3);
    }

    public void setTitle(int arg3) {
        super.setTitle(arg3);
        this.a().a(this.getContext().getString(arg3));
    }

    public void setTitle(CharSequence arg2) {
        super.setTitle(arg2);
        this.a().a(arg2);
    }
}

