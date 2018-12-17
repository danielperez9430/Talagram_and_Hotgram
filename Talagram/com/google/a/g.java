package com.google.a;

import com.google.a.b.a.n;
import com.google.a.b.d;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class g {
    private d a;
    private u b;
    private e c;
    private final Map d;
    private final List e;
    private final List f;
    private boolean g;
    private String h;
    private int i;
    private int j;
    private boolean k;
    private boolean l;
    private boolean m;
    private boolean n;
    private boolean o;
    private boolean p;

    public g() {
        super();
        this.a = d.a;
        this.b = u.a;
        this.c = com.google.a.d.a;
        this.d = new HashMap();
        this.e = new ArrayList();
        this.f = new ArrayList();
        this.g = false;
        this.i = 2;
        this.j = 2;
        this.k = false;
        this.l = false;
        this.m = true;
        this.n = false;
        this.o = false;
        this.p = false;
    }

    private void a(String arg4, int arg5, int arg6, List arg7) {
        a v4;
        a v0;
        a v6;
        if(arg4 == null || ("".equals(arg4.trim()))) {
            int v4_1 = 2;
            if(arg5 != v4_1 && arg6 != v4_1) {
                v4 = new a(Date.class, arg5, arg6);
                v0 = new a(Timestamp.class, arg5, arg6);
                a v1 = new a(java.sql.Date.class, arg5, arg6);
                v6 = v0;
                v0 = v1;
            label_30:
                arg7.add(n.a(Date.class, ((v)v4)));
                arg7.add(n.a(Timestamp.class, ((v)v6)));
                arg7.add(n.a(java.sql.Date.class, ((v)v0)));
            }
        }
        else {
            a v5 = new a(Date.class, arg4);
            v6 = new a(Timestamp.class, arg4);
            v0 = new a(java.sql.Date.class, arg4);
            v4 = v5;
            goto label_30;
        }
    }

    public g a() {
        this.m = false;
        return this;
    }

    public g a(w arg2) {
        this.e.add(arg2);
        return this;
    }

    public f b() {
        ArrayList v12 = new ArrayList(this.e.size() + this.f.size() + 3);
        ((List)v12).addAll(this.e);
        Collections.reverse(((List)v12));
        ArrayList v0 = new ArrayList(this.f);
        Collections.reverse(((List)v0));
        ((List)v12).addAll(((Collection)v0));
        this.a(this.h, this.i, this.j, ((List)v12));
        return new f(this.a, this.c, this.d, this.g, this.k, this.o, this.m, this.n, this.p, this.l, this.b, ((List)v12));
    }
}

