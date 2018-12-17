package com.google.android.exoplayer2.util;

import java.util.Arrays;

public final class LongArray {
    private static final int DEFAULT_INITIAL_CAPACITY = 32;
    private int size;
    private long[] values;

    public LongArray() {
        this(32);
    }

    public LongArray(int arg1) {
        super();
        this.values = new long[arg1];
    }

    public void add(long arg4) {
        if(this.size == this.values.length) {
            this.values = Arrays.copyOf(this.values, this.size * 2);
        }

        long[] v0 = this.values;
        int v1 = this.size;
        this.size = v1 + 1;
        v0[v1] = arg4;
    }

    public long get(int arg4) {
        if(arg4 >= 0 && arg4 < this.size) {
            return this.values[arg4];
        }

        StringBuilder v1 = new StringBuilder();
        v1.append("Invalid index ");
        v1.append(arg4);
        v1.append(", size is ");
        v1.append(this.size);
        throw new IndexOutOfBoundsException(v1.toString());
    }

    public int size() {
        return this.size;
    }

    public long[] toArray() {
        return Arrays.copyOf(this.values, this.size);
    }
}

