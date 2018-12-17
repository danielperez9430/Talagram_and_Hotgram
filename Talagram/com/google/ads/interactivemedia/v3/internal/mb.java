package com.google.ads.interactivemedia.v3.internal;

final class mb {
    private final Object a;
    private final int b;

    mb(Object arg2) {
        super();
        this.b = System.identityHashCode(arg2);
        this.a = arg2;
    }

    public boolean equals(Object arg4) {
        boolean v1 = false;
        if(!(arg4 instanceof mb)) {
            return 0;
        }

        if(this.b != ((mb)arg4).b) {
            return 0;
        }

        if(this.a == ((mb)arg4).a) {
            v1 = true;
        }

        return v1;
    }

    public int hashCode() {
        return this.b;
    }
}

