package com.google.ads.interactivemedia.v3.internal;

import java.io.Serializable;
import java.util.AbstractMap$SimpleImmutableEntry;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Map$Entry;
import java.util.Map;
import java.util.Set;

public abstract class lb implements Serializable, Map {
    public class a {
        Comparator a;
        Object[] b;
        int c;
        boolean d;

        public a() {
            this(4);
        }

        a(int arg1) {
            super();
            this.b = new Object[arg1 * 2];
            this.c = 0;
            this.d = false;
        }

        public a a(Object arg3, Object arg4) {
            this.a(this.c + 1);
            kv.a(arg3, arg4);
            this.b[this.c * 2] = arg3;
            this.b[this.c * 2 + 1] = arg4;
            ++this.c;
            return this;
        }

        public lb a() {
            this.b();
            this.d = true;
            return lk.a(this.c, this.b);
        }

        private void a(int arg3) {
            arg3 *= 2;
            if(arg3 > this.b.length) {
                this.b = Arrays.copyOf(this.b, com.google.ads.interactivemedia.v3.internal.kz$a.a(this.b.length, arg3));
                this.d = false;
            }
        }

        void b() {
            if(this.a != null) {
                if(this.d) {
                    this.b = Arrays.copyOf(this.b, this.c * 2);
                }

                Map$Entry[] v0 = new Map$Entry[this.c];
                int v1 = 0;
                int v2;
                for(v2 = 0; v2 < this.c; ++v2) {
                    int v5 = v2 * 2;
                    v0[v2] = new AbstractMap$SimpleImmutableEntry(this.b[v5], this.b[v5 + 1]);
                }

                Arrays.sort(((Object[])v0), 0, this.c, lh.a(this.a).a(lf.a()));
                while(v1 < this.c) {
                    int v3 = v1 * 2;
                    this.b[v3] = v0[v1].getKey();
                    this.b[v3 + 1] = v0[v1].getValue();
                    ++v1;
                }
            }
        }
    }

    static final Map$Entry[] a;
    private transient lc b;
    private transient lc c;
    private transient kz d;

    static {
        lb.a = new Map$Entry[0];
    }

    lb() {
        super();
    }

    public lc a() {
        lc v0 = this.b;
        if(v0 == null) {
            v0 = this.b();
            this.b = v0;
        }

        return v0;
    }

    abstract lc b();

    public lc c() {
        lc v0 = this.c;
        if(v0 == null) {
            v0 = this.d();
            this.c = v0;
        }

        return v0;
    }

    @Deprecated public final void clear() {
        throw new UnsupportedOperationException();
    }

    public boolean containsKey(Object arg1) {
        boolean v1 = this.get(arg1) != null ? true : false;
        return v1;
    }

    public boolean containsValue(Object arg2) {
        return this.e().contains(arg2);
    }

    abstract lc d();

    public kz e() {
        kz v0 = this.d;
        if(v0 == null) {
            v0 = this.f();
            this.d = v0;
        }

        return v0;
    }

    public Set entrySet() {
        return this.a();
    }

    public boolean equals(Object arg1) {
        return lf.a(((Map)this), arg1);
    }

    abstract kz f();

    public abstract Object get(Object arg1);

    public final Object getOrDefault(Object arg1, Object arg2) {
        arg1 = this.get(arg1);
        if(arg1 != null) {
        }
        else {
            arg1 = arg2;
        }

        return arg1;
    }

    public int hashCode() {
        return ll.a(this.a());
    }

    public boolean isEmpty() {
        boolean v0 = this.size() == 0 ? true : false;
        return v0;
    }

    public Set keySet() {
        return this.c();
    }

    @Deprecated public final Object put(Object arg1, Object arg2) {
        throw new UnsupportedOperationException();
    }

    @Deprecated public final void putAll(Map arg1) {
        throw new UnsupportedOperationException();
    }

    @Deprecated public final Object remove(Object arg1) {
        throw new UnsupportedOperationException();
    }

    public String toString() {
        return lf.a(((Map)this));
    }

    public Collection values() {
        return this.e();
    }
}

