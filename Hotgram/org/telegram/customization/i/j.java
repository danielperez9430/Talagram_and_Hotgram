package org.telegram.customization.i;

import f.l;
import java.util.ArrayList;
import java.util.HashMap;
import net.a.a.a.a;
import okhttp3.aa;
import okhttp3.ab;
import okhttp3.ad;
import org.telegram.customization.Model.ProxyServerModel;

public class j {
    a a;
    d b;
    e c;
    HashMap d;

    public j(d arg2) {
        super();
        this.d = new HashMap();
        this.b = arg2;
        this.a = new a();
        this.c = new b();
    }

    public void a(c.b.d.d arg2) {
        try {
            this.a.a(arg2);
            return;
        }
        catch(Exception ) {
            return;
        }
    }

    public void a(ProxyServerModel arg3, String arg4) {
        this.a(g.b, this.b.a(arg3, arg4));
    }

    public void a(Object arg3) {
        this.a(g.i, this.b.a(), arg3);
    }

    public void a(ArrayList arg3) {
        this.a(g.k, this.b.a(arg3));
    }

    public void a(String arg12, String arg13, String arg14, int arg15, int arg16, int arg17, ab arg18, String arg19, Object arg20) {
        this.a(g.f, this.b.a(arg12, arg13, arg14, arg15, arg16, arg17, arg18, arg19), arg20);
    }

    public void a(String arg11, String arg12, String arg13, int arg14, int arg15, int arg16, ab arg17, Object arg18) {
        this.a(g.g, this.b.a(arg11, arg12, arg13, arg14, arg15, arg16, arg17), arg18);
    }

    public void a() {
        this.a(g.i, this.b.a());
    }

    protected void a(g arg2, f.b arg3) {
        this.a(null, arg2, arg3, null);
    }

    public void a(int arg3, int arg4) {
        this.a(g.p, this.b.a(arg3, arg4));
    }

    public void a(int arg3, int arg4, int arg5) {
        this.a(g.c, this.b.a(arg3, arg4, arg5));
    }

    public void a(int arg3, String arg4, String arg5) {
        this.a(g.o, this.b.a(arg3, arg4, arg5));
    }

    protected void a(g arg2, f.b arg3, Object arg4) {
        this.a(null, arg2, arg3, arg4);
    }

    public void a(String arg9, String arg10, int arg11, String arg12, int arg13, String arg14) {
        this.a(g.q, this.b.a(arg9, arg10, arg11, arg12, arg13, arg14));
    }

    public void a(String arg3, String arg4, long arg5) {
        this.a(g.r, this.b.a(arg3, arg4, arg5));
    }

    public void a(String arg14, String arg15, String arg16, int arg17, int arg18, int arg19, ab arg20, String arg21, String arg22, String arg23) {
        this.a(g.a, this.b.a(arg14, arg15, arg16, arg17, arg18, arg19, arg20, arg21, arg22, arg23));
    }

    public void a(ab arg3) {
        this.a(g.e, this.b.a(arg3));
    }

    protected void a(i arg11, g arg12, f.b arg13, Object arg14) {
        aa v7 = arg13.c();
        if(this.c != null) {
            this.c.a(arg12, v7, arg14);
        }

        if(arg11 == null) {
            this.a.a(new f(net.a.b.a.a, arg12, arg14, v7, arg14, null));
        }
        else {
            try {
                arg11.a(new f(net.a.b.a.a, arg12, arg14, v7, arg14, null));
                goto label_30;
            }
            catch(Exception ) {
            label_30:
                this.d.put(arg13, arg14);
                arg13.a(new f.d(arg12, v7, arg14, arg11) {
                    public void a(f.b arg19, l arg20) {
                        org.telegram.customization.i.j$1 v0 = this;
                        v0.e.d.remove(arg19);
                        if(arg20.b()) {
                            Object v1 = arg20.c();
                            if(v0.e.c != null) {
                                v0.e.c.a(v0.a, v1, arg20, v0.b, v0.c);
                            }

                            if(v0.d == null) {
                                v0.e.a.a(new f(net.a.b.a.b, v0.a, v1, v0.b, v0.c, arg20));
                                return;
                            }

                            try {
                                v0.d.a(new f(net.a.b.a.b, v0.a, v1, v0.b, v0.c, arg20));
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
                                v0.e.a.a(new f(net.a.b.a.c, v0.a, v1_1, v0.b, v0.c, arg20));
                                return;
                            }

                            try {
                                v0.d.a(new f(net.a.b.a.c, v0.a, v1_1, v0.b, v0.c, arg20));
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
                            this.e.a.a(new f(net.a.b.a.c, this.a, arg10, this.b, v6, null));
                        }
                        else {
                            try {
                                this.d.a(new f(net.a.b.a.c, this.a, arg10, this.b, v6, null));
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

    public void a(boolean arg3, String arg4, ab arg5) {
        this.a(g.d, this.b.a(arg3, arg4, arg5));
    }

    public void b(c.b.d.d arg2) {
        try {
            this.a.b(arg2);
            return;
        }
        catch(Exception ) {
            return;
        }
    }

    public void b(String arg11, String arg12, String arg13, int arg14, int arg15, int arg16, ab arg17, Object arg18) {
        this.a(g.h, this.b.b(arg11, arg12, arg13, arg14, arg15, arg16, arg17), arg18);
    }

    public void b() {
        this.a(g.l, this.b.c());
    }

    public void b(Object arg3) {
        this.a(g.j, this.b.b(), arg3);
    }

    public void b(ab arg3) {
        this.a(g.m, this.b.b(arg3));
    }
}

