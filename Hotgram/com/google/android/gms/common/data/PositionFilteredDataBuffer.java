package com.google.android.gms.common.data;

import java.util.ArrayList;
import java.util.HashSet;

public final class PositionFilteredDataBuffer extends FilteredDataBuffer {
    private final ArrayList zzob;
    private final HashSet zzoe;

    public PositionFilteredDataBuffer(AbstractDataBuffer arg1) {
        super(((DataBuffer)arg1));
        this.zzoe = new HashSet();
        this.zzob = new ArrayList();
        this.zzcl();
    }

    public final void clearFilters() {
        this.zzoe.clear();
        this.zzcl();
    }

    public final int computeRealPosition(int arg4) {
        if(arg4 >= 0 && arg4 < this.getCount()) {
            return this.zzob.get(arg4).intValue();
        }

        StringBuilder v2 = new StringBuilder(53);
        v2.append("Position ");
        v2.append(arg4);
        v2.append(" is out of bounds for this buffer");
        throw new IllegalArgumentException(v2.toString());
    }

    public final void filterOut(int arg2) {
        if(arg2 >= 0 && arg2 <= this.mDataBuffer.getCount()) {
            this.zzoe.add(Integer.valueOf(arg2));
            this.zzcl();
        }
    }

    public final int getCount() {
        return this.mDataBuffer.getCount() - this.zzoe.size();
    }

    public final void unfilter(int arg2) {
        this.zzoe.remove(Integer.valueOf(arg2));
        this.zzcl();
    }

    private final void zzcl() {
        this.zzob.clear();
        int v0 = this.mDataBuffer.getCount();
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            if(!this.zzoe.contains(Integer.valueOf(v1))) {
                this.zzob.add(Integer.valueOf(v1));
            }
        }
    }
}

