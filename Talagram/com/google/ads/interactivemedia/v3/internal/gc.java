package com.google.ads.interactivemedia.v3.internal;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class gc extends gf implements Iterable {
    private final List a;

    public gc() {
        super();
        this.a = new ArrayList();
    }

    public void a(gf arg2) {
        gh v2;
        if(arg2 == null) {
            v2 = gh.a;
        }

        this.a.add(v2);
    }

    public Number a() {
        if(this.a.size() == 1) {
            return this.a.get(0).a();
        }

        throw new IllegalStateException();
    }

    public String b() {
        if(this.a.size() == 1) {
            return this.a.get(0).b();
        }

        throw new IllegalStateException();
    }

    public double c() {
        if(this.a.size() == 1) {
            return this.a.get(0).c();
        }

        throw new IllegalStateException();
    }

    public long d() {
        if(this.a.size() == 1) {
            return this.a.get(0).d();
        }

        throw new IllegalStateException();
    }

    public int e() {
        if(this.a.size() == 1) {
            return this.a.get(0).e();
        }

        throw new IllegalStateException();
    }

    public boolean equals(Object arg2) {
        boolean v2;
        if((((gc)arg2)) != this) {
            if(((arg2 instanceof gc)) && (((gc)arg2).a.equals(this.a))) {
                goto label_10;
            }

            v2 = false;
        }
        else {
        label_10:
            v2 = true;
        }

        return v2;
    }

    public boolean f() {
        if(this.a.size() == 1) {
            return this.a.get(0).f();
        }

        throw new IllegalStateException();
    }

    public int hashCode() {
        return this.a.hashCode();
    }

    public Iterator iterator() {
        return this.a.iterator();
    }
}

