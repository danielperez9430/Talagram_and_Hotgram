package android.arch.b.b;

import android.arch.b.a.f;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class i {
    private final AtomicBoolean a;
    private final e b;
    private volatile f c;

    public i(e arg3) {
        super();
        this.a = new AtomicBoolean(false);
        this.b = arg3;
    }

    private f a(boolean arg1) {
        f v1;
        if(arg1) {
            if(this.c == null) {
                this.c = this.d();
            }

            v1 = this.c;
        }
        else {
            v1 = this.d();
        }

        return v1;
    }

    protected abstract String a();

    public void a(f arg2) {
        if(arg2 == this.c) {
            this.a.set(false);
        }
    }

    protected void b() {
        this.b.e();
    }

    public f c() {
        this.b();
        return this.a(this.a.compareAndSet(false, true));
    }

    private f d() {
        return this.b.a(this.a());
    }
}

