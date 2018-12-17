package com.google.android.gms.common.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class ShuffleFilteredDataBuffer extends FilteredDataBuffer {
    private final List zzoh;
    private final int zzoi;

    public ShuffleFilteredDataBuffer(DataBuffer arg5, int arg6) {
        super(arg5);
        this.zzoi = arg6;
        int v5 = this.zzoi;
        arg6 = this.mDataBuffer.getCount();
        if(v5 <= arg6) {
            ArrayList v0 = new ArrayList(arg6);
            int v2;
            for(v2 = 0; v2 < arg6; ++v2) {
                v0.add(Integer.valueOf(v2));
            }

            Collections.shuffle(((List)v0));
            this.zzoh = v0.subList(0, v5);
            return;
        }

        throw new IllegalArgumentException("numIndexes must be smaller or equal to max");
    }

    public final int computeRealPosition(int arg4) {
        if(arg4 >= 0 && arg4 < this.getCount()) {
            return this.zzoh.get(arg4).intValue();
        }

        StringBuilder v2 = new StringBuilder(53);
        v2.append("Position ");
        v2.append(arg4);
        v2.append(" is out of bounds for this buffer");
        throw new IllegalArgumentException(v2.toString());
    }

    public final int getCount() {
        return Math.min(this.zzoi, this.mDataBuffer.getCount());
    }
}

