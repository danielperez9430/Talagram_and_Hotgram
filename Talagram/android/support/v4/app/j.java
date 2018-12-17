package android.support.v4.app;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class j {
    private final k a;

    private j(k arg1) {
        super();
        this.a = arg1;
    }

    public static j a(k arg1) {
        return new j(arg1);
    }

    public Fragment a(String arg2) {
        return this.a.b.b(arg2);
    }

    public l a() {
        return this.a.k();
    }

    public View a(View arg2, String arg3, Context arg4, AttributeSet arg5) {
        return this.a.b.onCreateView(arg2, arg3, arg4, arg5);
    }

    public void a(Configuration arg2) {
        this.a.b.a(arg2);
    }

    public void a(Parcelable arg2, n arg3) {
        this.a.b.a(arg2, arg3);
    }

    public void a(Fragment arg4) {
        this.a.b.a(this.a, this.a, arg4);
    }

    public void a(boolean arg2) {
        this.a.b.a(arg2);
    }

    public boolean a(Menu arg2) {
        return this.a.b.a(arg2);
    }

    public boolean a(Menu arg2, MenuInflater arg3) {
        return this.a.b.a(arg2, arg3);
    }

    public boolean a(MenuItem arg2) {
        return this.a.b.a(arg2);
    }

    public void b() {
        this.a.b.m();
    }

    public void b(Menu arg2) {
        this.a.b.b(arg2);
    }

    public void b(boolean arg2) {
        this.a.b.b(arg2);
    }

    public boolean b(MenuItem arg2) {
        return this.a.b.b(arg2);
    }

    public Parcelable c() {
        return this.a.b.l();
    }

    public n d() {
        return this.a.b.j();
    }

    public void e() {
        this.a.b.n();
    }

    public void f() {
        this.a.b.o();
    }

    public void g() {
        this.a.b.p();
    }

    public void h() {
        this.a.b.q();
    }

    public void i() {
        this.a.b.r();
    }

    public void j() {
        this.a.b.s();
    }

    public void k() {
        this.a.b.u();
    }

    public void l() {
        this.a.b.v();
    }

    public boolean m() {
        return this.a.b.g();
    }
}

