package org.telegram.customization.i.b;

import c.b.d.d;
import f.l;
import java.util.HashMap;
import net.a.a.a.a;
import okhttp3.aa;
import okhttp3.ad;
import org.telegram.customization.Model.Ads.VastRequest;

public class h {
    a a;
    org.telegram.customization.i.b.a b;
    b c;
    HashMap d;

    public h(org.telegram.customization.i.b.a arg2) {
        super();
        this.d = new HashMap();
        this.b = arg2;
        this.a = new a();
        this.c = new g();
    }

    public void a(d arg2) {
        try {
            this.a.a(arg2);
            return;
        }
        catch(Exception ) {
            return;
        }
    }

    public void a(f arg3, VastRequest arg4) {
        this.a(arg3, org.telegram.customization.i.b.d.a, this.b.a(arg4));
    }

    protected void a(f arg2, org.telegram.customization.i.b.d arg3, f.b arg4) {
        this.a(arg2, arg3, arg4, null);
    }

    protected void a(f arg11, org.telegram.customization.i.b.d arg12, f.b arg13, Object arg14) {
        aa v7 = arg13.c();
        if(this.c != null) {
            this.c.a(arg12, v7, arg14);
        }

        if(arg11 == null) {
            this.a.a(new c(net.a.b.a.a, arg12, arg14, v7, arg14, null));
        }
        else {
            try {
                arg11.a(new c(net.a.b.a.a, arg12, arg14, v7, arg14, null));
                goto label_30;
            }
            catch(Exception ) {
            label_30:
                this.d.put(arg13, arg14);
                arg13.a(new f.d(arg12, v7, arg14, arg11) {
                    public void a(f.b arg19, l arg20) {
                        org.telegram.customization.i.b.h$1 v0 = this;
                        v0.e.d.remove(arg19);
                        if(arg20.b()) {
                            Object v1 = arg20.c();
                            if(v0.e.c != null) {
                                v0.e.c.a(v0.a, v1, arg20, v0.b, v0.c);
                            }

                            if(v0.d == null) {
                                v0.e.a.a(new c(net.a.b.a.b, v0.a, v1, v0.b, v0.c, arg20));
                                return;
                            }

                            try {
                                v0.d.a(new c(net.a.b.a.b, v0.a, v1, v0.b, v0.c, arg20));
                            }
                            catch(Exception ) {
                            }
                        }
                        else {
                            ad v1_1 = arg20.d();
                            if(v0.e.c != null) {
                                v0.e.c.a(v0.a, v1_1, arg20, v0.b, v0.c);
                            }

                            if(v0.d == null) {
                                v0.e.a.a(new c(net.a.b.a.c, v0.a, v1_1, v0.b, v0.c, arg20));
                                return;
                            }

                            try {
                                v0.d.a(new c(net.a.b.a.c, v0.a, v1_1, v0.b, v0.c, arg20));
                                return;
                            }
                            catch(Exception ) {
                                return;
                            }
                        }
                    }

                    public void a(f.b arg9, Throwable arg10) {
                        Object v6 = this.e.d.remove(arg9);
                        this.e.c.a(this.a, arg10, this.b, v6);
                        if(this.d == null) {
                            this.e.a.a(new c(net.a.b.a.c, this.a, arg10, this.b, v6, null));
                        }
                        else {
                            try {
                                this.d.a(new c(net.a.b.a.c, this.a, arg10, this.b, v6, null));
                                return;
                            }
                            catch(Exception ) {
                                return;
                            }
                        }
                    }
                });
                return;
            }
        }

        goto label_30;
    }

    public void b(d arg2) {
        try {
            this.a.b(arg2);
            return;
        }
        catch(Exception ) {
            return;
        }
    }
}

