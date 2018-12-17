package android.support.v4.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.f.l;
import android.view.LayoutInflater;
import android.view.View;
import java.io.FileDescriptor;
import java.io.PrintWriter;

public abstract class k extends i {
    private final Activity a;
    final m b;
    private final Context c;
    private final Handler d;
    private final int e;

    k(Activity arg2, Context arg3, Handler arg4, int arg5) {
        super();
        this.b = new m();
        this.a = arg2;
        this.c = l.a(arg3, "context == null");
        this.d = l.a(arg4, "handler == null");
        this.e = arg5;
    }

    k(h arg3) {
        this(((Activity)arg3), ((Context)arg3), arg3.a, 0);
    }

    public void a(Fragment arg1, String[] arg2, int arg3) {
    }

    public boolean a(String arg1) {
        return 0;
    }

    public void a(Fragment arg1, Intent arg2, int arg3, Bundle arg4) {
        if(arg3 == -1) {
            this.c.startActivity(arg2);
            return;
        }

        throw new IllegalStateException("Starting activity with a requestCode requires a FragmentActivity host");
    }

    public void a(Fragment arg10, IntentSender arg11, int arg12, Intent arg13, int arg14, int arg15, int arg16, Bundle arg17) {
        if(arg12 == -1) {
            a.a(this.a, arg11, arg12, arg13, arg14, arg15, arg16, arg17);
            return;
        }

        throw new IllegalStateException("Starting intent sender with a requestCode requires a FragmentActivity host");
    }

    public View a(int arg1) {
        return null;
    }

    public void a(String arg1, FileDescriptor arg2, PrintWriter arg3, String[] arg4) {
    }

    public boolean a() {
        return 1;
    }

    public boolean a(Fragment arg1) {
        return 1;
    }

    public LayoutInflater b() {
        return LayoutInflater.from(this.c);
    }

    void b(Fragment arg1) {
    }

    public void d() {
    }

    public boolean e() {
        return 1;
    }

    public int f() {
        return this.e;
    }

    public abstract Object g();

    Activity h() {
        return this.a;
    }

    Context i() {
        return this.c;
    }

    Handler j() {
        return this.d;
    }

    m k() {
        return this.b;
    }
}

