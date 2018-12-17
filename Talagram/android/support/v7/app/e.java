package android.support.v7.app;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.widget.bp;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup$LayoutParams;

public abstract class e {
    private static int a = -1;

    static {
    }

    e() {
        super();
    }

    public static e a(Activity arg2, d arg3) {
        return new f(((Context)arg2), arg2.getWindow(), arg3);
    }

    public static e a(Dialog arg2, d arg3) {
        return new f(arg2.getContext(), arg2.getWindow(), arg3);
    }

    public static void a(boolean arg0) {
        bp.a(arg0);
    }

    public abstract a a();

    public abstract View a(int arg1);

    public abstract void a(Configuration arg1);

    public abstract void a(Bundle arg1);

    public abstract void a(View arg1);

    public abstract void a(View arg1, ViewGroup$LayoutParams arg2);

    public abstract void a(CharSequence arg1);

    public abstract MenuInflater b();

    public abstract void b(int arg1);

    public abstract void b(Bundle arg1);

    public abstract void b(View arg1, ViewGroup$LayoutParams arg2);

    public abstract void c();

    public abstract void c(Bundle arg1);

    public abstract boolean c(int arg1);

    public abstract void d();

    public abstract void e();

    public abstract void f();

    public abstract void g();

    public abstract void h();

    public abstract boolean i();

    public static int j() {
        return e.a;
    }
}

