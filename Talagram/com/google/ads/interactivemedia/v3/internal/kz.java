package com.google.ads.interactivemedia.v3.internal;

import java.io.Serializable;
import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;

public abstract class kz extends AbstractCollection implements Serializable {
    public abstract class a {
        static int a(int arg1, int arg2) {
            if(arg2 >= 0) {
                arg1 = arg1 + (arg1 >> 1) + 1;
                if(arg1 < arg2) {
                    arg1 = Integer.highestOneBit(arg2 - 1) << 1;
                }

                if(arg1 < 0) {
                    arg1 = 2147483647;
                }

                return arg1;
            }

            throw new AssertionError("cannot store more than MAX_VALUE elements");
        }
    }

    private static final Object[] a;

    static {
        kz.a = new Object[0];
    }

    kz() {
        super();
    }

    int a(Object[] arg4, int arg5) {
        ln v0 = this.a();
        while(((Iterator)v0).hasNext()) {
            arg4[arg5] = ((Iterator)v0).next();
            ++arg5;
        }

        return arg5;
    }

    public abstract ln a();

    @Deprecated public final boolean add(Object arg1) {
        throw new UnsupportedOperationException();
    }

    @Deprecated public final boolean addAll(Collection arg1) {
        throw new UnsupportedOperationException();
    }

    Object[] b() {
        return null;
    }

    int c() {
        throw new UnsupportedOperationException();
    }

    @Deprecated public final void clear() {
        throw new UnsupportedOperationException();
    }

    public abstract boolean contains(Object arg1);

    int d() {
        throw new UnsupportedOperationException();
    }

    public la e() {
        la v0 = this.isEmpty() ? la.g() : la.a(this.toArray());
        return v0;
    }

    abstract boolean f();

    public Iterator iterator() {
        return this.a();
    }

    @Deprecated public final boolean remove(Object arg1) {
        throw new UnsupportedOperationException();
    }

    @Deprecated public final boolean removeAll(Collection arg1) {
        throw new UnsupportedOperationException();
    }

    @Deprecated public final boolean retainAll(Collection arg1) {
        throw new UnsupportedOperationException();
    }

    public final Object[] toArray() {
        return this.toArray(kz.a);
    }

    public final Object[] toArray(Object[] arg4) {
        kr.a(arg4);
        int v0 = this.size();
        if(arg4.length < v0) {
            Object[] v1 = this.b();
            if(v1 != null) {
                return li.a(v1, this.c(), this.d(), arg4);
            }
            else {
                arg4 = lg.a(arg4, v0);
            }
        }
        else if(arg4.length > v0) {
            arg4[v0] = null;
        }

        this.a(arg4, 0);
        return arg4;
    }
}

