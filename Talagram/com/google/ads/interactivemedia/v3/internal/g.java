package com.google.ads.interactivemedia.v3.internal;

import android.view.View;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

public class g extends c {
    private final e a;
    private final d b;
    private final List c;
    private ar d;
    private y e;
    private boolean f;
    private boolean g;
    private String h;

    g(d arg3, e arg4) {
        z v0;
        super();
        this.c = new ArrayList();
        this.f = false;
        this.g = false;
        this.b = arg3;
        this.a = arg4;
        this.h = UUID.randomUUID().toString();
        this.e(null);
        if(arg4.f() == f.a) {
            v0 = new z(arg4.c());
        }
        else {
            aa v0_1 = new aa(arg4.b(), arg4.e());
        }

        this.e = ((y)v0);
        this.e.a();
        p.a().a(this);
        this.e.a(arg3);
    }

    public void a() {
        if(this.f) {
            return;
        }

        this.f = true;
        p.a().b(this);
        this.e.a(t.a().d());
        this.e.a(this, this.a);
    }

    public void a(View arg2) {
        if(this.g) {
            return;
        }

        af.a(arg2, "AdView is null");
        if(this.g() == arg2) {
            return;
        }

        this.e(arg2);
        this.e().f();
        this.f(arg2);
    }

    public void b() {
        if(this.g) {
            return;
        }

        this.d.clear();
        this.c();
        this.g = true;
        this.e().e();
        p.a().c(this);
        this.e().b();
        this.e = null;
    }

    public void b(View arg3) {
        if(this.g) {
            return;
        }

        this.d(arg3);
        if(this.c(arg3) == null) {
            this.c.add(new ar(arg3));
        }
    }

    private ar c(View arg4) {
        Object v1;
        Iterator v0 = this.c.iterator();
        do {
            if(!v0.hasNext()) {
                return null;
            }

            v1 = v0.next();
        }
        while(((ar)v1).get() != arg4);

        return ((ar)v1);
    }

    public void c() {
        if(this.g) {
            return;
        }

        this.c.clear();
    }

    public List d() {
        return this.c;
    }

    private void d(View arg2) {
        if(arg2 != null) {
            return;
        }

        throw new IllegalArgumentException("FriendlyObstruction is null");
    }

    public y e() {
        return this.e;
    }

    private void e(View arg2) {
        this.d = new ar(arg2);
    }

    public String f() {
        return this.h;
    }

    private void f(View arg4) {
        Collection v0 = p.a().b();
        if(v0 != null && v0.size() > 0) {
            Iterator v0_1 = v0.iterator();
            while(v0_1.hasNext()) {
                Object v1 = v0_1.next();
                if((((g)v1)) == this) {
                    continue;
                }

                if(((g)v1).g() != arg4) {
                    continue;
                }

                ((g)v1).d.clear();
            }
        }
    }

    public View g() {
        return this.d.get();
    }

    public boolean h() {
        boolean v0 = !this.f || (this.g) ? false : true;
        return v0;
    }
}

