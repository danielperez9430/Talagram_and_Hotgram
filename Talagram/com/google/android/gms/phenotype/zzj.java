package com.google.android.gms.phenotype;

import java.util.Comparator;

final class zzj implements Comparator {
    zzj() {
        super();
    }

    public final int compare(Object arg3, Object arg4) {
        if(((zzi)arg3).zzah == ((zzi)arg4).zzah) {
            return ((zzi)arg3).name.compareTo(((zzi)arg4).name);
        }

        return ((zzi)arg3).zzah - ((zzi)arg4).zzah;
    }
}

