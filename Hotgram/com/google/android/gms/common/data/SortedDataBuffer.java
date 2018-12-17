package com.google.android.gms.common.data;

import android.os.Bundle;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;

public final class SortedDataBuffer implements DataBuffer {
    private final DataBuffer zzok;
    private final Integer[] zzol;

    public SortedDataBuffer(DataBuffer arg3, Comparator arg4) {
        super();
        this.zzok = arg3;
        this.zzol = new Integer[arg3.getCount()];
        int v3;
        for(v3 = 0; v3 < this.zzol.length; ++v3) {
            this.zzol[v3] = Integer.valueOf(v3);
        }

        Arrays.sort(this.zzol, new zzb(this, arg4));
    }

    public final void close() {
        this.zzok.release();
    }

    public final Object get(int arg3) {
        return this.zzok.get(this.zzol[arg3].intValue());
    }

    public final int getCount() {
        return this.zzol.length;
    }

    public final Bundle getMetadata() {
        return this.zzok.getMetadata();
    }

    public final boolean isClosed() {
        return this.zzok.isClosed();
    }

    public final Iterator iterator() {
        return new DataBufferIterator(((DataBuffer)this));
    }

    public final void release() {
        this.zzok.release();
    }

    public final Iterator singleRefIterator() {
        return this.iterator();
    }

    static DataBuffer zza(SortedDataBuffer arg0) {
        return arg0.zzok;
    }
}

