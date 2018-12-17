package com.google.ads.interactivemedia.v3.internal;

import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class ga {
    private gz a;
    private go b;
    private fy c;
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

    public ga() {
        super();
        this.a = gz.a;
        this.b = go.a;
        this.c = fx.a;
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

    private void a(String arg3, int arg4, int arg5, List arg6) {
        fu v3;
        if(arg3 == null || ("".equals(arg3.trim()))) {
            int v3_1 = 2;
            if(arg4 != v3_1 && arg5 != v3_1) {
                v3 = new fu(arg4, arg5);
            label_14:
                arg6.add(hs.a(hw.b(Date.class), v3));
                arg6.add(hs.a(hw.b(Timestamp.class), v3));
                arg6.add(hs.a(hw.b(java.sql.Date.class), v3));
            }
        }
        else {
            v3 = new fu(arg3);
            goto label_14;
        }
    }

    public fz a() {
        ArrayList v12 = new ArrayList();
        ((List)v12).addAll(this.e);
        Collections.reverse(((List)v12));
        ((List)v12).addAll(this.f);
        this.a(this.h, this.i, this.j, ((List)v12));
        return new fz(this.a, this.c, this.d, this.g, this.k, this.o, this.m, this.n, this.p, this.l, this.b, ((List)v12));
    }

    public ga a(fv arg4) {
        this.a = this.a.a(arg4, true, false);
        return this;
    }

    public ga a(gq arg2) {
        this.e.add(arg2);
        return this;
    }

    public ga a(Type arg4, Object arg5) {
        boolean v0 = arg5 instanceof gm;
        boolean v1 = (v0) || ((arg5 instanceof ge)) || ((arg5 instanceof gb)) || ((arg5 instanceof gp)) ? true : false;
        gw.a(v1);
        if((arg5 instanceof gb)) {
            this.d.put(arg4, arg5);
        }

        if((v0) || ((arg5 instanceof ge))) {
            this.e.add(hs.b(hw.a(arg4), arg5));
        }

        if((arg5 instanceof gp)) {
            this.e.add(hu.a(hw.a(arg4), ((gp)arg5)));
        }

        return this;
    }
}

