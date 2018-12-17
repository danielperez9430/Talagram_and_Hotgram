package c.a.a.a.a.e;

import c.a.a.a.l;
import java.util.Locale;
import java.util.Map;
import javax.net.ssl.SSLSocketFactory;

public class b implements e {
    class c.a.a.a.a.e.b$1 {
        static {
            c.a.a.a.a.e.b$1.a = new int[c.values().length];
            try {
                c.a.a.a.a.e.b$1.a[c.a.ordinal()] = 1;
                goto label_9;
            }
            catch(NoSuchFieldError ) {
                try {
                label_9:
                    c.a.a.a.a.e.b$1.a[c.b.ordinal()] = 2;
                    goto label_14;
                }
                catch(NoSuchFieldError ) {
                    try {
                    label_14:
                        c.a.a.a.a.e.b$1.a[c.c.ordinal()] = 3;
                        goto label_19;
                    }
                    catch(NoSuchFieldError ) {
                        try {
                        label_19:
                            c.a.a.a.a.e.b$1.a[c.d.ordinal()] = 4;
                            return;
                        }
                        catch(NoSuchFieldError ) {
                            return;
                        }
                    }
                }
            }
        }
    }

    private final l a;
    private g b;
    private SSLSocketFactory c;
    private boolean d;

    public b() {
        this(new c.a.a.a.b());
    }

    public b(l arg1) {
        super();
        this.a = arg1;
    }

    private void a() {
        __monitor_enter(this);
        try {
            this.d = false;
            this.c = null;
        }
        catch(Throwable v0) {
            __monitor_exit(this);
            throw v0;
        }

        __monitor_exit(this);
    }

    private boolean a(String arg2) {
        boolean v2 = arg2 == null || !arg2.toLowerCase(Locale.US).startsWith("https") ? false : true;
        return v2;
    }

    public d a(c arg2, String arg3, Map arg4) {
        switch(c.a.a.a.a.e.b$1.a[arg2.ordinal()]) {
            case 1: {
                goto label_15;
            }
            case 2: {
                goto label_13;
            }
            case 3: {
                goto label_11;
            }
            case 4: {
                goto label_9;
            }
        }

        throw new IllegalArgumentException("Unsupported HTTP method!");
    label_9:
        d v2 = d.e(((CharSequence)arg3));
        goto label_16;
    label_11:
        v2 = d.d(((CharSequence)arg3));
        goto label_16;
    label_13:
        v2 = d.b(((CharSequence)arg3), arg4, true);
        goto label_16;
    label_15:
        v2 = d.a(((CharSequence)arg3), arg4, true);
    label_16:
        if((this.a(arg3)) && this.b != null) {
            SSLSocketFactory v3 = this.b();
            if(v3 != null) {
                v2.a().setSSLSocketFactory(v3);
            }
        }

        return v2;
    }

    public void a(g arg2) {
        if(this.b != arg2) {
            this.b = arg2;
            this.a();
        }
    }

    private SSLSocketFactory b() {
        SSLSocketFactory v0_1;
        __monitor_enter(this);
        try {
            if(this.c == null && !this.d) {
                this.c = this.c();
            }

            v0_1 = this.c;
        }
        catch(Throwable v0) {
            __monitor_exit(this);
            throw v0;
        }

        __monitor_exit(this);
        return v0_1;
    }

    private SSLSocketFactory c() {
        SSLSocketFactory v0_2;
        __monitor_enter(this);
        try {
            this.d = true;
            try {
                v0_2 = f.a(this.b);
                this.a.a("Fabric", "Custom SSL pinning enabled");
                goto label_9;
            }
            catch(Exception v0_1) {
                try {
                    this.a.e("Fabric", "Exception while validating pinned certs", ((Throwable)v0_1));
                }
                catch(Throwable v0) {
                    goto label_20;
                }

                __monitor_exit(this);
                return null;
            }
        }
        catch(Throwable v0) {
        }

    label_20:
        __monitor_exit(this);
        throw v0;
    label_9:
        __monitor_exit(this);
        return v0_2;
    }
}

