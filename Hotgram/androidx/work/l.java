package androidx.work;

import android.os.Build$VERSION;

public final class l extends r {
    public final class a extends androidx.work.r$a {
        public a(Class arg2) {
            super(arg2);
            this.c.d = OverwritingInputMerger.class.getName();
        }

        l a() {
            if((this.a) && Build$VERSION.SDK_INT >= 23) {
                if(!this.c.j.c()) {
                }
                else {
                    throw new IllegalArgumentException("Cannot set backoff criteria on an idle mode job");
                }
            }

            return new l(this);
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

    l(a arg3) {
        super(arg3.b, arg3.c, arg3.d);
    }
}

