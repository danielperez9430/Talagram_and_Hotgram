package e;

import javax.annotation.Nullable;

final class p {
    @Nullable static o a;
    static long b;

    private p() {
        super();
    }

    static o a() {
        Class v0 = p.class;
        __monitor_enter(v0);
        try {
            if(p.a != null) {
                o v1_1 = p.a;
                p.a = v1_1.f;
                v1_1.f = null;
                p.b -= 8192;
                __monitor_exit(v0);
                return v1_1;
            }

            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            try {
            label_20:
                __monitor_exit(v0);
            }
            catch(Throwable v1) {
                goto label_20;
            }

            throw v1;
        }

        return new o();
    }

    static void a(o arg8) {
        if(arg8.f == null && arg8.g == null) {
            if(arg8.d) {
                return;
            }
            else {
                Class v0 = p.class;
                __monitor_enter(v0);
                try {
                    long v3 = 8192;
                    if(p.b + v3 > 65536) {
                        __monitor_exit(v0);
                        return;
                    }
                    else {
                        p.b += v3;
                        arg8.f = p.a;
                        arg8.c = 0;
                        arg8.b = 0;
                        p.a = arg8;
                        __monitor_exit(v0);
                        return;
                    label_29:
                        __monitor_exit(v0);
                        goto label_30;
                    }

                    goto label_31;
                }
                catch(Throwable v8) {
                    goto label_29;
                }

            label_30:
                throw v8;
            }
        }

    label_31:
        throw new IllegalArgumentException();
    }
}

