package androidx.work;

import android.content.Context;
import android.support.annotation.Keep;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.Executor;

public abstract class NonBlockingWorker {
    private Context a;
    private u b;
    private volatile boolean c;
    private volatile boolean d;
    private volatile e e;
    private volatile a f;
    private boolean g;

    @Deprecated public NonBlockingWorker() {
        super();
        this.e = e.a;
        this.f = a.b;
    }

    public final Context a() {
        return this.a;
    }

    public void a(e arg1) {
        this.e = arg1;
    }

    public void a(a arg1) {
        this.f = arg1;
    }

    public final void a(boolean arg2) {
        this.c = true;
        this.d = arg2;
        this.b(arg2);
    }

    public void b(boolean arg1) {
    }

    public final UUID b() {
        return this.b.a();
    }

    public final e c() {
        return this.b.b();
    }

    public final Set d() {
        return this.b.c();
    }

    public final int e() {
        return this.b.d();
    }

    public abstract com.google.common.a.a.a f();

    public e g() {
        return this.e;
    }

    public final boolean h() {
        return this.g;
    }

    public final void i() {
        this.g = true;
    }

    @Keep @Deprecated protected void internalInit(Context arg1, u arg2) {
        this.a = arg1;
        this.b = arg2;
    }

    public androidx.work.u$a j() {
        return this.b.e();
    }

    public Executor k() {
        return this.b.f();
    }

    public t l() {
        return this.b.g();
    }
}

