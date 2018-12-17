package com.google.ads.interactivemedia.v3.internal;

import java.util.Comparator;

public abstract class lh implements Comparator {
    protected lh() {
        super();
    }

    public lh a(kn arg2) {
        return new ku(arg2, this);
    }

    public static lh a(Comparator arg1) {
        kx v1;
        if((arg1 instanceof lh)) {
        }
        else {
            v1 = new kx(arg1);
        }

        return ((Comparator)v1);
    }

    public abstract int compare(Object arg1, Object arg2);
}

