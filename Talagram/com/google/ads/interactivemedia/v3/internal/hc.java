package com.google.ads.interactivemedia.v3.internal;

import java.io.Serializable;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map$Entry;
import java.util.NoSuchElementException;
import java.util.Set;

public final class hc extends AbstractMap implements Serializable {
    final class com.google.ads.interactivemedia.v3.internal.hc$1 implements Comparator {
        com.google.ads.interactivemedia.v3.internal.hc$1() {
            super();
        }

        public int a(Comparable arg1, Comparable arg2) {
            return arg1.compareTo(arg2);
        }

        public int compare(Object arg1, Object arg2) {
            return this.a(((Comparable)arg1), ((Comparable)arg2));
        }
    }

    class a extends AbstractSet {
        a(hc arg1) {
            this.a = arg1;
            super();
        }

        public void clear() {
            this.a.clear();
        }

        public boolean contains(Object arg2) {
            boolean v2 = !(arg2 instanceof Map$Entry) || this.a.a(((Map$Entry)arg2)) == null ? false : true;
            return v2;
        }

        public Iterator iterator() {
            return new c() {
                public Map$Entry a() {
                    return this.b();
                }

                public Object next() {
                    return this.a();
                }
            };
        }

        public boolean remove(Object arg3) {
            if(!(arg3 instanceof Map$Entry)) {
                return 0;
            }

            d v3 = this.a.a(((Map$Entry)arg3));
            if(v3 == null) {
                return 0;
            }

            this.a.a(v3, true);
            return 1;
        }

        public int size() {
            return this.a.c;
        }
    }

    final class b extends AbstractSet {
        b(hc arg1) {
            this.a = arg1;
            super();
        }

        public void clear() {
            this.a.clear();
        }

        public boolean contains(Object arg2) {
            return this.a.containsKey(arg2);
        }

        public Iterator iterator() {
            return new c() {
                public Object next() {
                    return this.b().f;
                }
            };
        }

        public boolean remove(Object arg2) {
            boolean v2 = this.a.b(arg2) != null ? true : false;
            return v2;
        }

        public int size() {
            return this.a.c;
        }
    }

    abstract class c implements Iterator {
        d b;
        d c;
        int d;

        c(hc arg1) {
            this.e = arg1;
            super();
            this.b = this.e.e.d;
            this.c = null;
            this.d = this.e.d;
        }

        final d b() {
            d v0 = this.b;
            if(v0 != this.e.e) {
                if(this.e.d == this.d) {
                    this.b = v0.d;
                    this.c = v0;
                    return v0;
                }

                throw new ConcurrentModificationException();
            }

            throw new NoSuchElementException();
        }

        public final boolean hasNext() {
            boolean v0 = this.b != this.e.e ? true : false;
            return v0;
        }

        public final void remove() {
            if(this.c != null) {
                this.e.a(this.c, true);
                this.c = null;
                this.d = this.e.d;
                return;
            }

            throw new IllegalStateException();
        }
    }

    final class d implements Map$Entry {
        d a;
        d b;
        d c;
        d d;
        d e;
        final Object f;
        Object g;
        int h;

        d() {
            super();
            this.f = null;
            this.e = this;
            this.d = this;
        }

        d(d arg1, Object arg2, d arg3, d arg4) {
            super();
            this.a = arg1;
            this.f = arg2;
            this.h = 1;
            this.d = arg3;
            this.e = arg4;
            arg4.d = this;
            arg3.e = this;
        }

        public d a() {
            d v0 = this.b;
            d v1 = this;
            while(v0 != null) {
                v1 = v0;
                v0 = v0.b;
            }

            return v1;
        }

        public d b() {
            d v0 = this.c;
            d v1 = this;
            while(v0 != null) {
                v1 = v0;
                v0 = v0.c;
            }

            return v1;
        }

        public boolean equals(Object arg4) {
            boolean v1 = false;
            if((arg4 instanceof Map$Entry)) {
                if(this.f != null) {
                    if(this.f.equals(((Map$Entry)arg4).getKey())) {
                        goto label_12;
                    }

                    return v1;
                }
                else if(((Map$Entry)arg4).getKey() == null) {
                }
                else {
                    return v1;
                }

            label_12:
                if(this.g != null) {
                    if(this.g.equals(((Map$Entry)arg4).getValue())) {
                        goto label_21;
                    }

                    return v1;
                }
                else if(((Map$Entry)arg4).getValue() == null) {
                }
                else {
                    return v1;
                }

            label_21:
                v1 = true;
            }

            return v1;
        }

        public Object getKey() {
            return this.f;
        }

        public Object getValue() {
            return this.g;
        }

        public int hashCode() {
            int v1 = 0;
            int v0 = this.f == null ? 0 : this.f.hashCode();
            if(this.g == null) {
            }
            else {
                v1 = this.g.hashCode();
            }

            return v0 ^ v1;
        }

        public Object setValue(Object arg2) {
            Object v0 = this.g;
            this.g = arg2;
            return v0;
        }

        public String toString() {
            return this.f + "=" + this.g;
        }
    }

    Comparator a;
    d b;
    int c;
    int d;
    final d e;
    private static final Comparator g;
    private a h;
    private b i;

    static {
        hc.f = hc.class.desiredAssertionStatus() ^ 1;
        hc.g = new com.google.ads.interactivemedia.v3.internal.hc$1();
    }

    public hc() {
        this(hc.g);
    }

    public hc(Comparator arg2) {
        super();
        this.c = 0;
        this.d = 0;
        this.e = new d();
        if(arg2 != null) {
        }
        else {
            arg2 = hc.g;
        }

        this.a = arg2;
    }

    private void a(d arg6) {
        d v0 = arg6.b;
        d v1 = arg6.c;
        d v2 = v1.b;
        d v3 = v1.c;
        arg6.c = v2;
        if(v2 != null) {
            v2.a = arg6;
        }

        this.a(arg6, v1);
        v1.b = arg6;
        arg6.a = v1;
        int v4 = 0;
        int v0_1 = v0 != null ? v0.h : 0;
        int v2_1 = v2 != null ? v2.h : 0;
        arg6.h = Math.max(v0_1, v2_1) + 1;
        int v6 = arg6.h;
        if(v3 != null) {
            v4 = v3.h;
        }

        v1.h = Math.max(v6, v4) + 1;
    }

    private void a(d arg3, d arg4) {
        d v0 = arg3.a;
        arg3.a = null;
        if(arg4 != null) {
            arg4.a = v0;
        }

        if(v0 == null) {
            this.b = arg4;
        }
        else if(v0.b == arg3) {
            v0.b = arg4;
        }
        else {
            if(!hc.f) {
                if(v0.c == arg3) {
                }
                else {
                    throw new AssertionError();
                }
            }

            v0.c = arg4;
        }
    }

    private boolean a(Object arg1, Object arg2) {
        boolean v1;
        if(arg1 != arg2) {
            if(arg1 != null && (arg1.equals(arg2))) {
                goto label_7;
            }

            v1 = false;
        }
        else {
        label_7:
            v1 = true;
        }

        return v1;
    }

    d a(Object arg3) {
        d v3;
        d v0 = null;
        if(arg3 != null) {
            try {
                v3 = this.a(arg3, false);
            }
            catch(ClassCastException ) {
                return v0;
            }
        }
        else {
            v3 = v0;
        }

        return v3;
    }

    d a(Object arg7, boolean arg8) {
        d v0_2;
        int v4;
        Comparator v0 = this.a;
        d v1 = this.b;
        d v2 = null;
        if(v1 != null) {
            Object v3 = v0 == hc.g ? arg7 : v2;
            while(true) {
                v4 = v3 != null ? ((Comparable)v3).compareTo(v1.f) : v0.compare(arg7, v1.f);
                if(v4 == 0) {
                    return v1;
                }

                d v5 = v4 < 0 ? v1.b : v1.c;
                if(v5 == null) {
                    break;
                }

                v1 = v5;
            }
        }
        else {
            v4 = 0;
        }

        if(!arg8) {
            return v2;
        }

        d v8 = this.e;
        if(v1 == null) {
            if(v0 == hc.g) {
                if((arg7 instanceof Comparable)) {
                }
                else {
                    StringBuilder v0_1 = new StringBuilder();
                    v0_1.append(arg7.getClass().getName());
                    v0_1.append(" is not Comparable");
                    throw new ClassCastException(v0_1.toString());
                }
            }

            v0_2 = new d(v1, arg7, v8, v8.e);
            this.b = v0_2;
        }
        else {
            v0_2 = new d(v1, arg7, v8, v8.e);
            if(v4 < 0) {
                v1.b = v0_2;
            }
            else {
                v1.c = v0_2;
            }

            this.b(v1, true);
        }

        ++this.c;
        ++this.d;
        return v0_2;
    }

    d a(Map$Entry arg3) {
        d v0 = this.a(arg3.getKey());
        int v3 = v0 == null || !this.a(v0.g, arg3.getValue()) ? 0 : 1;
        if(v3 != 0) {
        }
        else {
            v0 = null;
        }

        return v0;
    }

    void a(d arg6, boolean arg7) {
        int v1_1;
        if(arg7) {
            arg6.e.d = arg6.d;
            arg6.d.e = arg6.e;
        }

        d v7 = arg6.b;
        d v0 = arg6.c;
        d v1 = arg6.a;
        int v2 = 0;
        d v3 = null;
        if(v7 != null && v0 != null) {
            v7 = v7.h > v0.h ? v7.b() : v0.a();
            this.a(v7, false);
            v0 = arg6.b;
            if(v0 != null) {
                v1_1 = v0.h;
                v7.b = v0;
                v0.a = v7;
                arg6.b = v3;
            }
            else {
                v1_1 = 0;
            }

            v0 = arg6.c;
            if(v0 != null) {
                v2 = v0.h;
                v7.c = v0;
                v0.a = v7;
                arg6.c = v3;
            }

            v7.h = Math.max(v1_1, v2) + 1;
            this.a(arg6, v7);
            return;
        }

        if(v7 != null) {
            this.a(arg6, v7);
            arg6.b = v3;
        }
        else if(v0 != null) {
            this.a(arg6, v0);
            arg6.c = v3;
        }
        else {
            this.a(arg6, v3);
        }

        this.b(v1, false);
        --this.c;
        ++this.d;
    }

    private void b(d arg6) {
        d v0 = arg6.b;
        d v1 = arg6.c;
        d v2 = v0.b;
        d v3 = v0.c;
        arg6.b = v3;
        if(v3 != null) {
            v3.a = arg6;
        }

        this.a(arg6, v0);
        v0.c = arg6;
        arg6.a = v0;
        int v4 = 0;
        int v1_1 = v1 != null ? v1.h : 0;
        int v3_1 = v3 != null ? v3.h : 0;
        arg6.h = Math.max(v1_1, v3_1) + 1;
        int v6 = arg6.h;
        if(v2 != null) {
            v4 = v2.h;
        }

        v0.h = Math.max(v6, v4) + 1;
    }

    private void b(d arg10, boolean arg11) {
        d v3_1;
        while(arg10 != null) {
            d v0 = arg10.b;
            d v1 = arg10.c;
            int v2 = 0;
            int v3 = v0 != null ? v0.h : 0;
            int v4 = v1 != null ? v1.h : 0;
            int v5 = v3 - v4;
            int v7 = -1;
            if(v5 == -2) {
                v0 = v1.b;
                v3_1 = v1.c;
                v3 = v3_1 != null ? v3_1.h : 0;
                if(v0 != null) {
                    v2 = v0.h;
                }

                v2 -= v3;
                if(v2 != v7 && (v2 != 0 || (arg11))) {
                    if(!hc.f) {
                        if(v2 == 1) {
                        }
                        else {
                            throw new AssertionError();
                        }
                    }

                    this.b(v1);
                }

                this.a(arg10);
                if(!arg11) {
                    goto label_85;
                }
            }
            else {
                if(v5 == 2) {
                    v1 = v0.b;
                    v3_1 = v0.c;
                    v3 = v3_1 != null ? v3_1.h : 0;
                    if(v1 != null) {
                        v2 = v1.h;
                    }

                    v2 -= v3;
                    if(v2 != 1 && (v2 != 0 || (arg11))) {
                        if(!hc.f) {
                            if(v2 == v7) {
                            }
                            else {
                                throw new AssertionError();
                            }
                        }

                        this.a(v0);
                    }

                    this.b(arg10);
                    if(!arg11) {
                        goto label_85;
                    }

                    return;
                }

                if(v5 == 0) {
                    arg10.h = v3 + 1;
                    if(!arg11) {
                        goto label_85;
                    }

                    return;
                }

                if(!hc.f && v5 != v7) {
                    if(v5 == 1) {
                    }
                    else {
                        throw new AssertionError();
                    }
                }

                arg10.h = Math.max(v3, v4) + 1;
                if(arg11) {
                    goto label_85;
                }
            }

            return;
        label_85:
            arg10 = arg10.a;
        }
    }

    d b(Object arg2) {
        d v2 = this.a(arg2);
        if(v2 != null) {
            this.a(v2, true);
        }

        return v2;
    }

    public void clear() {
        // Method was not decompiled
    }

    public boolean containsKey(Object arg1) {
        boolean v1 = this.a(arg1) != null ? true : false;
        return v1;
    }

    public Set entrySet() {
        a v0 = this.h;
        if(v0 != null) {
        }
        else {
            v0 = new a(this);
            this.h = v0;
        }

        return ((Set)v0);
    }

    public Object get(Object arg1) {
        d v1 = this.a(arg1);
        return v1 != null ? v1.g : null;
    }

    public Set keySet() {
        b v0 = this.i;
        if(v0 != null) {
        }
        else {
            v0 = new b(this);
            this.i = v0;
        }

        return ((Set)v0);
    }

    public Object put(Object arg2, Object arg3) {
        if(arg2 != null) {
            d v2 = this.a(arg2, true);
            Object v0 = v2.g;
            v2.g = arg3;
            return v0;
        }

        throw new NullPointerException("key == null");
    }

    public Object remove(Object arg1) {
        d v1 = this.b(arg1);
        return v1 != null ? v1.g : null;
    }

    public int size() {
        return this.c;
    }
}

