package androidx.work;

import android.os.Build$VERSION;
import java.util.concurrent.TimeUnit;

public final class m extends r {
    public final class a extends androidx.work.r$a {
        public a(Class arg1, long arg2, TimeUnit arg4) {
            super(arg1);
            this.c.a(arg4.toMillis(arg2));
        }

        m a() {
            if((this.a) && Build$VERSION.SDK_INT >= 23) {
                if(!this.c.j.c()) {
                }
                else {
                    throw new IllegalArgumentException("Cannot set backoff criteria on an idle mode job");
                }
            }

            return new m(this);
        }

        a b() {
            return this;
        }

        androidx.work.r$a c() {
            return this.b();
        }

        r d() {
            return this.a();
        }
    }

    m(a arg3) {
        super(arg3.b, arg3.c, arg3.d);
    }
}

