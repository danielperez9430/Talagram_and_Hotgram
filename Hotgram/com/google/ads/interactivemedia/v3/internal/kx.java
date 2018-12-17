package com.google.ads.interactivemedia.v3.internal;

import java.io.Serializable;
import java.util.Comparator;

final class kx extends lh implements Serializable {
    final Comparator a;

    kx(Comparator arg1) {
        super();
        this.a = kr.a(arg1);
    }

    public int compare(Object arg2, Object arg3) {
        return this.a.compare(arg2, arg3);
    }

    public boolean equals(Object arg2) {
        if((((kx)arg2)) == this) {
            return 1;
        }

        if((arg2 instanceof kx)) {
            return this.a.equals(((kx)arg2).a);
        }

        return 0;
    }

    public int hashCode() {
        return this.a.hashCode();
    }

    public String toString() {
        return this.a.toString();
    }
}

