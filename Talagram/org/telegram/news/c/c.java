package org.telegram.news.c;

import android.app.Activity;
import android.content.Context;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.telegram.news.b.b;

public class c {
    org.telegram.news.b.c a;
    private ArrayList b;
    private boolean c;
    private boolean d;
    private boolean e;
    private boolean f;

    public c() {
        super();
        this.c = false;
        this.d = false;
        this.e = false;
        this.f = false;
    }

    public void a(Context arg2, ArrayList arg3, a arg4) {
        if(arg3 != null) {
            Iterator v3 = arg3.iterator();
            while(v3.hasNext()) {
                this.a(arg2, v3.next(), arg4);
            }
        }
    }

    public void a(Context arg3, b arg4, a arg5) {
        __monitor_enter(this);
        try {
            if(this.a(arg3, arg4)) {
                arg4.a(this.a(arg4).j());
                arg4.j("");
                arg4.g("");
                if(arg5 != null) {
                    try {
                        if((arg3 instanceof Activity)) {
                            ((Activity)arg3).runOnUiThread(new Runnable(arg4, arg5) {
                                public void run() {
                                    this.c.b().add(this.a);
                                    this.b.a();
                                }
                            });
                        }
                        else {
                            arg5.a();
                        }
                    }
                    catch(Exception ) {
                    }
                }
            }
        }
        catch(Throwable v3) {
            __monitor_exit(this);
            throw v3;
        }

        __monitor_exit(this);
    }

    public void a(boolean arg1) {
        this.c = arg1;
    }

    public void a(a arg2) {
        this.b().clear();
        this.a(false);
        this.a("D81B60");
        if(arg2 != null) {
            arg2.a();
        }
    }

    public b a(int arg2) {
        if(arg2 >= this.e()) {
            return null;
        }

        return this.b().get(arg2);
    }

    public b a(b arg8) {
        if(arg8 != null) {
            long v4 = 1000;
            if(arg8.j() > System.currentTimeMillis() / v4) {
                arg8.a(System.currentTimeMillis() / v4);
            }
        }

        return arg8;
    }

    public org.telegram.news.b.c a() {
        if(this.a == null) {
            this.a = new org.telegram.news.b.c();
            this.a.b("47C653");
            this.a.a(0);
            this.a.a("");
        }

        return this.a;
    }

    public boolean a(Context arg5, b arg6) {
        ArrayList v5 = new ArrayList(this.b());
        if(((List)v5).size() > 0) {
            Iterator v5_1 = ((List)v5).iterator();
            do {
                if(v5_1.hasNext()) {
                    Object v0 = v5_1.next();
                    try {
                        if(!((b)v0).h().equals(arg6.h())) {
                            continue;
                        }

                        break;
                    }
                    catch(Exception v5_2) {
                        v5_2.printStackTrace();
                        return 0;
                    }
                }
                else {
                    goto label_20;
                }

                goto label_21;
            }
            while(true);

            int v5_3 = 1;
            goto label_21;
        label_20:
            v5_3 = 0;
        label_21:
            if(v5_3 == 0) {
                return 1;
            }

            return 0;
        }

        return 1;
    }

    public void a(String arg2) {
        this.a().b(arg2);
    }

    public ArrayList b() {
        if(this.b == null) {
            this.b = new ArrayList();
        }

        return this.b;
    }

    public void b(int arg2) {
        this.a().a(arg2);
    }

    public String c() {
        if(this.d() == null) {
            return "0";
        }

        return this.d().h();
    }

    public b d() {
        if(this.e() == 0) {
            return null;
        }

        return this.a(this.e() - 1);
    }

    public int e() {
        return this.b().size();
    }

    public boolean f() {
        return this.c;
    }
}

