package com.google.ads.interactivemedia.v3.internal;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

public abstract class la extends kz implements List, RandomAccess {
    class a extends kt {
        private final la a;

        a(la arg2, int arg3) {
            super(arg2.size(), arg3);
            this.a = arg2;
        }

        protected Object a(int arg2) {
            return this.a.get(arg2);
        }
    }

    class b extends la {
        final transient int a;
        final transient int b;

        b(la arg1, int arg2, int arg3) {
            this.c = arg1;
            super();
            this.a = arg2;
            this.b = arg3;
        }

        public la a(int arg3, int arg4) {
            kr.a(arg3, arg4, this.b);
            return this.c.a(arg3 + this.a, arg4 + this.a);
        }

        Object[] b() {
            return this.c.b();
        }

        int c() {
            return this.c.c() + this.a;
        }

        int d() {
            return this.c.c() + this.a + this.b;
        }

        boolean f() {
            return 1;
        }

        public Object get(int arg3) {
            kr.a(arg3, this.b);
            return this.c.get(arg3 + this.a);
        }

        public int size() {
            return this.b;
        }

        public List subList(int arg1, int arg2) {
            return this.a(arg1, arg2);
        }
    }

    private static final lo a;

    static {
        la.a = new a(lj.a, 0);
    }

    la() {
        super();
    }

    static la a(Object[] arg1) {
        return la.b(arg1, arg1.length);
    }

    public static la a(Collection arg1) {
        if((arg1 instanceof kz)) {
            la v1 = ((kz)arg1).e();
            if(v1.f()) {
                v1 = la.a(v1.toArray());
            }

            return v1;
        }

        return la.b(arg1.toArray());
    }

    int a(Object[] arg5, int arg6) {
        int v0 = this.size();
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            arg5[arg6 + v1] = this.get(v1);
        }

        return arg6 + v0;
    }

    public la a(int arg3, int arg4) {
        kr.a(arg3, arg4, this.size());
        int v0 = arg4 - arg3;
        if(v0 == this.size()) {
            return this;
        }

        if(v0 == 0) {
            return la.g();
        }

        return this.b(arg3, arg4);
    }

    public ln a() {
        return this.h();
    }

    public lo a(int arg2) {
        kr.b(arg2, this.size());
        if(this.isEmpty()) {
            return la.a;
        }

        return new a(this, arg2);
    }

    @Deprecated public final void add(int arg1, Object arg2) {
        throw new UnsupportedOperationException();
    }

    @Deprecated public final boolean addAll(int arg1, Collection arg2) {
        throw new UnsupportedOperationException();
    }

    private static la b(Object[] arg0) {
        return la.a(lg.a(arg0));
    }

    static la b(Object[] arg1, int arg2) {
        if(arg2 == 0) {
            return la.g();
        }

        return new lj(arg1, arg2);
    }

    la b(int arg2, int arg3) {
        return new b(this, arg2, arg3 - arg2);
    }

    public boolean contains(Object arg1) {
        boolean v1 = this.indexOf(arg1) >= 0 ? true : false;
        return v1;
    }

    public final la e() {
        return this;
    }

    public boolean equals(Object arg1) {
        return le.a(((List)this), arg1);
    }

    public static la g() {
        return lj.a;
    }

    public lo h() {
        return this.a(0);
    }

    public int hashCode() {
        int v0 = this.size();
        int v1 = 1;
        int v2;
        for(v2 = 0; v2 < v0; ++v2) {
            v1 = v1 * 31 + this.get(v2).hashCode() ^ -1 ^ -1;
        }

        return v1;
    }

    public int indexOf(Object arg1) {
        int v1 = arg1 == null ? -1 : le.b(((List)this), arg1);
        return v1;
    }

    public Iterator iterator() {
        return this.a();
    }

    public int lastIndexOf(Object arg1) {
        int v1 = arg1 == null ? -1 : le.c(((List)this), arg1);
        return v1;
    }

    public ListIterator listIterator() {
        return this.h();
    }

    public ListIterator listIterator(int arg1) {
        return this.a(arg1);
    }

    @Deprecated public final Object remove(int arg1) {
        throw new UnsupportedOperationException();
    }

    @Deprecated public final Object set(int arg1, Object arg2) {
        throw new UnsupportedOperationException();
    }

    public List subList(int arg1, int arg2) {
        return this.a(arg1, arg2);
    }
}

