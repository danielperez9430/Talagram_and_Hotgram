package com.google.android.gms.internal.measurement;

import java.util.NoSuchElementException;

final class zzue implements zzuj {
    private final int limit;
    private int position;

    zzue(zzud arg1) {
        this.zzbuc = arg1;
        super();
        this.position = 0;
        this.limit = this.zzbuc.size();
    }

    public final boolean hasNext() {
        if(this.position < this.limit) {
            return 1;
        }

        return 0;
    }

    public final Object next() {
        return Byte.valueOf(this.nextByte());
    }

    public final byte nextByte() {
        try {
            zzud v0_1 = this.zzbuc;
            int v1 = this.position;
            this.position = v1 + 1;
            return v0_1.zzal(v1);
        }
        catch(IndexOutOfBoundsException v0) {
            throw new NoSuchElementException(v0.getMessage());
        }
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }
}

