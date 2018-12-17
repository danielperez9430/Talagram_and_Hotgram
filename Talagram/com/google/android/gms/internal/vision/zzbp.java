package com.google.android.gms.internal.vision;

import java.util.Iterator;
import java.util.NoSuchElementException;

final class zzbp implements Iterator {
    private final int limit;
    private int position;

    zzbp(zzbo arg1) {
        this.zzgw = arg1;
        super();
        this.position = 0;
        this.limit = this.zzgw.size();
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

    private final byte nextByte() {
        try {
            zzbo v0_1 = this.zzgw;
            int v1 = this.position;
            this.position = v1 + 1;
            return v0_1.zzl(v1);
        }
        catch(IndexOutOfBoundsException v0) {
            throw new NoSuchElementException(v0.getMessage());
        }
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }
}

