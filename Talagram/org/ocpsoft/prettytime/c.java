package org.ocpsoft.prettytime;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.ocpsoft.prettytime.c.f;
import org.ocpsoft.prettytime.c.g;
import org.ocpsoft.prettytime.c.h;
import org.ocpsoft.prettytime.c.i;
import org.ocpsoft.prettytime.c.j;
import org.ocpsoft.prettytime.c.k;
import org.ocpsoft.prettytime.c.l;
import org.ocpsoft.prettytime.c.m;

public class c {
    private volatile Date a;
    private volatile Locale b;
    private volatile Map c;
    private volatile List d;

    public c() {
        super();
        this.b = Locale.getDefault();
        this.c = new LinkedHashMap();
        this.c();
    }

    public c(Locale arg2) {
        super();
        this.b = Locale.getDefault();
        this.c = new LinkedHashMap();
        this.a(arg2);
        this.c();
    }

    public c a(Locale arg4) {
        Object v1;
        if(arg4 == null) {
            arg4 = Locale.getDefault();
        }

        this.b = arg4;
        Iterator v0 = this.c.keySet().iterator();
        while(v0.hasNext()) {
            v1 = v0.next();
            if(!(v1 instanceof b)) {
                continue;
            }

            ((b)v1).a(arg4);
        }

        v0 = this.c.values().iterator();
        while(v0.hasNext()) {
            v1 = v0.next();
            if(!(v1 instanceof b)) {
                continue;
            }

            ((b)v1).a(arg4);
        }

        return this;
    }

    private a a(long arg19) {
        long v6;
        int v15;
        long v9;
        Object v8;
        long v2 = Math.abs(arg19);
        List v4 = this.a();
        org.ocpsoft.prettytime.b.a v5 = new org.ocpsoft.prettytime.b.a();
        int v7 = 0;
        while(true) {
            if(v7 < v4.size()) {
                v8 = v4.get(v7);
                v9 = Math.abs(((e)v8).a());
                long v11 = Math.abs(((e)v8).b());
                int v14 = 1;
                if(v7 == v4.size() - 1) {
                    v15 = v7;
                }
                else {
                    v15 = v7;
                    v14 = 0;
                }

                v6 = 0;
                if(v6 == v11 && v14 == 0) {
                    v11 = v4.get(v15 + 1).a() / ((e)v8).a();
                }

                if(v11 * v9 <= v2) {
                    if(v14 != 0) {
                    }
                    else {
                        v7 = v15 + 1;
                        continue;
                    }
                }

                break;
            }

            goto label_46;
        }

        v5.a(((e)v8));
        if(v9 > v2) {
            v5.a(this.b(arg19));
            v5.b(v6);
        }
        else {
            v5.a(arg19 / v9);
            v5.b(arg19 - v5.a() * v9);
        }

    label_46:
        return ((a)v5);
    }

    public List a() {
        if(this.d == null) {
            ArrayList v0 = new ArrayList(this.c.keySet());
            Collections.sort(((List)v0), new k());
            this.d = Collections.unmodifiableList(((List)v0));
        }

        return this.d;
    }

    private void a(org.ocpsoft.prettytime.b.c arg2) {
        this.a(((e)arg2), new org.ocpsoft.prettytime.b.b(arg2));
    }

    public c a(e arg2, d arg3) {
        if(arg2 != null) {
            if(arg3 != null) {
                this.d = null;
                this.c.put(arg2, arg3);
                if((arg2 instanceof b)) {
                    ((b)arg2).a(this.b);
                }

                if((arg3 instanceof b)) {
                    ((b)arg3).a(this.b);
                }

                return this;
            }

            throw new IllegalArgumentException("Format to register must not be null.");
        }

        throw new IllegalArgumentException("Unit to register must not be null.");
    }

    public String a(a arg3) {
        if(arg3 == null) {
            return this.b(this.b());
        }

        d v0 = this.a(arg3.b());
        return v0.a(arg3, v0.a(arg3));
    }

    public d a(e arg2) {
        if(arg2 != null && this.c.get(arg2) != null) {
            return this.c.get(arg2);
        }

        return null;
    }

    public a a(Date arg6) {
        if(arg6 == null) {
            arg6 = this.b();
        }

        Date v0 = this.a;
        if(v0 == null) {
            v0 = this.b();
        }

        return this.a(arg6.getTime() - v0.getTime());
    }

    private long b(long arg4) {
        if(0 > arg4) {
            return -1;
        }

        return 1;
    }

    private Date b() {
        return new Date();
    }

    public String b(Date arg1) {
        if(arg1 == null) {
            arg1 = this.b();
        }

        return this.a(this.a(arg1));
    }

    private void c() {
        this.a(new org.ocpsoft.prettytime.c.e());
        this.a(new g());
        this.a(new j());
        this.a(new h());
        this.a(new org.ocpsoft.prettytime.c.d());
        this.a(new org.ocpsoft.prettytime.c.b());
        this.a(new l());
        this.a(new i());
        this.a(new m());
        this.a(new org.ocpsoft.prettytime.c.c());
        this.a(new org.ocpsoft.prettytime.c.a());
        this.a(new f());
    }

    public String toString() {
        return "PrettyTime [reference=" + this.a + ", locale=" + this.b + "]";
    }
}

