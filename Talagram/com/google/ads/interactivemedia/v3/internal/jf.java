package com.google.ads.interactivemedia.v3.internal;

public class jf {
    private final long a;

    jf(long arg1) {
        super();
        this.a = arg1;
    }

    public long a() {
        return this.a;
    }

    public boolean equals(Object arg8) {
        if(this == (((jf)arg8))) {
            return 1;
        }

        if(!(arg8 instanceof jf)) {
            return 0;
        }

        if(this.a != ((jf)arg8).a) {
            return 0;
        }

        return 1;
    }

    public int hashCode() {
        return ((int)this.a);
    }

    public String toString() {
        long v0 = this.a;
        StringBuilder v2 = new StringBuilder(56);
        v2.append("NativeBridgeConfig [adTimeUpdateMs=");
        v2.append(v0);
        v2.append("]");
        return v2.toString();
    }
}

