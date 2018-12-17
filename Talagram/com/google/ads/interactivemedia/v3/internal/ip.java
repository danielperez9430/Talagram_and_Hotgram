package com.google.ads.interactivemedia.v3.internal;

final class ip extends a {
    private final String TXXX;

    ip(String arg2) {
        super();
        if(arg2 != null) {
            this.TXXX = arg2;
            return;
        }

        throw new NullPointerException("Null TXXX");
    }

    String TXXX() {
        return this.TXXX;
    }

    public boolean equals(Object arg2) {
        if((((ip)arg2)) == this) {
            return 1;
        }

        if((arg2 instanceof a)) {
            return this.TXXX.equals(((a)arg2).TXXX());
        }

        return 0;
    }

    public int hashCode() {
        return this.TXXX.hashCode() ^ 1000003;
    }

    public String toString() {
        String v0 = this.TXXX;
        StringBuilder v2 = new StringBuilder(String.valueOf(v0).length() + 28);
        v2.append("TimedMetadataWithKeys{TXXX=");
        v2.append(v0);
        v2.append("}");
        return v2.toString();
    }
}

