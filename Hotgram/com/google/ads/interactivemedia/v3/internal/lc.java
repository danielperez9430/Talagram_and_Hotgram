package com.google.ads.interactivemedia.v3.internal;

import java.util.Iterator;
import java.util.Set;

public abstract class lc extends kz implements Set {
    private transient la a;

    lc() {
        super();
    }

    static int a(int arg6) {
        arg6 = Math.max(arg6, 2);
        boolean v0 = true;
        if(arg6 < 751619276) {
            int v0_1;
            for(v0_1 = Integer.highestOneBit(arg6 - 1) << 1; true; v0_1 <<= 1) {
                double v1 = ((double)v0_1);
                Double.isNaN(v1);
                if(v1 * 0.7 >= (((double)arg6))) {
                    return v0_1;
                }
            }

            return v0_1;
        }

        int v1_1 = 1073741824;
        if(arg6 < v1_1) {
        }
        else {
            v0 = false;
        }

        kr.a(v0, "collection too large");
        return v1_1;
    }

    public abstract ln a();

    public la e() {
        la v0 = this.a;
        if(v0 == null) {
            v0 = this.h();
            this.a = v0;
        }

        return v0;
    }

    public boolean equals(Object arg3) {
        if((((lc)arg3)) == this) {
            return 1;
        }

        if(((arg3 instanceof lc)) && (this.g()) && (arg3.g()) && this.hashCode() != arg3.hashCode()) {
            return 0;
        }

        return ll.a(((Set)this), arg3);
    }

    boolean g() {
        return 0;
    }

    la h() {
        return la.a(this.toArray());
    }

    public int hashCode() {
        return ll.a(((Set)this));
    }

    public Iterator iterator() {
        return this.a();
    }
}

