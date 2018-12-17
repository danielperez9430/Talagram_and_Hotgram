package com.google.ads.interactivemedia.v3.internal;

import java.util.Arrays;

public final class fk {
    private int a;
    private long[] b;

    public fk() {
        this(32);
    }

    public fk(int arg1) {
        super();
        this.b = new long[arg1];
    }

    public int a() {
        return this.a;
    }

    public long a(int arg5) {
        if(arg5 >= 0 && arg5 < this.a) {
            return this.b[arg5];
        }

        int v1 = this.a;
        StringBuilder v3 = new StringBuilder(45);
        v3.append("Invalid size ");
        v3.append(arg5);
        v3.append(", size is ");
        v3.append(v1);
        throw new IndexOutOfBoundsException(v3.toString());
    }

    public void a(long arg4) {
        if(this.a == this.b.length) {
            this.b = Arrays.copyOf(this.b, this.a * 2);
        }

        long[] v0 = this.b;
        int v1 = this.a;
        this.a = v1 + 1;
        v0[v1] = arg4;
    }
}

