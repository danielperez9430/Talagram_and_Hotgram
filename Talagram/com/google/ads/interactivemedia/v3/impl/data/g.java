package com.google.ads.interactivemedia.v3.impl.data;

final class g extends n {
    private final double end;
    private final boolean played;
    private final double start;

    g(double arg1, double arg3, boolean arg5) {
        super();
        this.start = arg1;
        this.end = arg3;
        this.played = arg5;
    }

    public double end() {
        return this.end;
    }

    public boolean equals(Object arg8) {
        boolean v0 = true;
        if((((g)arg8)) == this) {
            return 1;
        }

        if((arg8 instanceof n)) {
            if(Double.doubleToLongBits(this.start) != Double.doubleToLongBits(((n)arg8).start()) || Double.doubleToLongBits(this.end) != Double.doubleToLongBits(((n)arg8).end()) || this.played != ((n)arg8).played()) {
                v0 = false;
            }
            else {
            }

            return v0;
        }

        return 0;
    }

    public int hashCode() {
        int v0 = (((((int)(Double.doubleToLongBits(this.start) >>> 32 ^ Double.doubleToLongBits(this.start)))) ^ 1000003) * 1000003 ^ (((int)(Double.doubleToLongBits(this.end) >>> 32 ^ Double.doubleToLongBits(this.end))))) * 1000003;
        int v1 = this.played ? 1231 : 1237;
        return v0 ^ v1;
    }

    public boolean played() {
        return this.played;
    }

    public double start() {
        return this.start;
    }

    public String toString() {
        double v0 = this.start;
        double v2 = this.end;
        boolean v4 = this.played;
        StringBuilder v5 = new StringBuilder(88);
        v5.append("CuePointData{start=");
        v5.append(v0);
        v5.append(", end=");
        v5.append(v2);
        v5.append(", played=");
        v5.append(v4);
        v5.append("}");
        return v5.toString();
    }
}

