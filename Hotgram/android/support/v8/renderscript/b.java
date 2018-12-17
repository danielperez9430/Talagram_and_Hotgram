package android.support.v8.renderscript;

import android.renderscript.BaseObj;
import java.util.concurrent.locks.ReentrantReadWriteLock$ReadLock;

public class b {
    private long a;
    private boolean b;
    RenderScript r;

    b(long arg1, RenderScript arg3) {
        super();
        arg3.f();
        this.r = arg3;
        this.a = arg1;
        this.b = false;
    }

    private void a() {
        __monitor_enter(this);
        try {
            int v1 = 1;
            if(!this.b) {
                this.b = true;
            }
            else {
                v1 = 0;
            }

            __monitor_exit(this);
            if(v1 == 0) {
                return;
            }
        }
        catch(Throwable v0) {
            try {
            label_26:
                __monitor_exit(this);
            }
            catch(Throwable v0) {
                goto label_26;
            }

            throw v0;
        }

        ReentrantReadWriteLock$ReadLock v0_1 = this.r.l.readLock();
        v0_1.lock();
        if(this.r.g()) {
            this.r.a(this.a);
        }

        v0_1.unlock();
        this.r = null;
        this.a = 0;
    }

    long a(RenderScript arg6) {
        this.r.f();
        if(!this.b) {
            if(this.a != 0) {
                if(arg6 != null) {
                    if(arg6 == this.r) {
                    }
                    else {
                        throw new g("using object with mismatched context.");
                    }
                }

                return this.a;
            }

            throw new h("Internal error: Object id 0.");
        }

        throw new g("using a destroyed object.");
    }

    BaseObj c() {
        return null;
    }

    void d() {
        if(this.a == 0) {
            if(this.c() != null) {
            }
            else {
                throw new f("Invalid object.");
            }
        }
    }

    public boolean equals(Object arg7) {
        boolean v0 = true;
        if(this == (((b)arg7))) {
            return 1;
        }

        if(arg7 == null) {
            return 0;
        }

        if(this.getClass() != arg7.getClass()) {
            return 0;
        }

        if(this.a == ((b)arg7).a) {
        }
        else {
            v0 = false;
        }

        return v0;
    }

    protected void finalize() {
        this.a();
        super.finalize();
    }

    public int hashCode() {
        return ((int)(this.a & 268435455 ^ this.a >> 32));
    }
}

