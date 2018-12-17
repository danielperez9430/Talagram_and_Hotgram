package com.google.ads.interactivemedia.v3.internal;

import java.io.Serializable;
import java.util.Map$Entry;
import java.util.Objects;

public abstract class md implements Serializable, Comparable, Map$Entry {
    public md() {
        super();
    }

    public abstract Object a();

    public int a(md arg4) {
        return new lw().a(this.a(), arg4.a()).a(this.b(), arg4.b()).a();
    }

    public static md b(Object arg1, Object arg2) {
        return new mc(arg1, arg2);
    }

    public abstract Object b();

    public int compareTo(Object arg1) {
        return this.a(((md)arg1));
    }

    public boolean equals(Object arg5) {
        boolean v0 = true;
        if((((md)arg5)) == this) {
            return 1;
        }

        if((arg5 instanceof Map$Entry)) {
            if(!Objects.equals(this.getKey(), ((Map$Entry)arg5).getKey()) || !Objects.equals(this.getValue(), ((Map$Entry)arg5).getValue())) {
                v0 = false;
            }
            else {
            }

            return v0;
        }

        return 0;
    }

    public final Object getKey() {
        return this.a();
    }

    public Object getValue() {
        return this.b();
    }

    public int hashCode() {
        int v1 = 0;
        int v0 = this.getKey() == null ? 0 : this.getKey().hashCode();
        if(this.getValue() == null) {
        }
        else {
            v1 = this.getValue().hashCode();
        }

        return v0 ^ v1;
    }

    public String toString() {
        return '(' + this.a() + ',' + this.b() + ')';
    }
}

