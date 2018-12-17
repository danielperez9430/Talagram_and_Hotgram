package com.google.android.gms.common.data;

import java.util.Comparator;

final class zzb implements Comparator {
    zzb(SortedDataBuffer arg1, Comparator arg2) {
        this.zzon = arg1;
        this.zzom = arg2;
        super();
    }

    public final int compare(Object arg3, Object arg4) {
        return this.zzom.compare(SortedDataBuffer.zza(this.zzon).get(((Integer)arg3).intValue()), SortedDataBuffer.zza(this.zzon).get(((Integer)arg4).intValue()));
    }
}

