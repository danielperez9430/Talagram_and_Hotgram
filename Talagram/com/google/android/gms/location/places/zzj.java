package com.google.android.gms.location.places;

import com.google.android.gms.location.places.internal.zzaj;
import java.util.Comparator;

final class zzj implements Comparator {
    zzj() {
        super();
    }

    public final int compare(Object arg1, Object arg2) {
        return -Float.compare(((zzaj)arg1).getLikelihood(), ((zzaj)arg2).getLikelihood());
    }
}

