package com.google.ads.interactivemedia.v3.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class p {
    private static p a;
    private final ArrayList b;
    private final ArrayList c;

    static {
        p.a = new p();
    }

    private p() {
        super();
        this.b = new ArrayList();
        this.c = new ArrayList();
    }

    public static p a() {
        return p.a;
    }

    public void a(g arg2) {
        this.b.add(arg2);
    }

    public Collection b() {
        return Collections.unmodifiableCollection(this.b);
    }

    public void b(g arg3) {
        boolean v0 = this.d();
        this.c.add(arg3);
        if(!v0) {
            t.a().b();
        }
    }

    public Collection c() {
        return Collections.unmodifiableCollection(this.c);
    }

    public void c(g arg3) {
        boolean v0 = this.d();
        this.b.remove(arg3);
        this.c.remove(arg3);
        if((v0) && !this.d()) {
            t.a().c();
        }
    }

    public boolean d() {
        boolean v0 = this.c.size() > 0 ? true : false;
        return v0;
    }
}

