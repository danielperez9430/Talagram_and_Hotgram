package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;

final class zzz {
    final String name;
    final long zzaie;
    final long zzaif;
    final long zzaig;
    final long zzaih;
    final Long zzaii;
    final Long zzaij;
    final Long zzaik;
    final Boolean zzail;
    final String zztt;

    zzz(String arg13, String arg14, long arg15, long arg17, long arg19, long arg21, Long arg23, Long arg24, Long arg25, Boolean arg26) {
        zzz v0 = this;
        long v1 = arg15;
        long v3 = arg17;
        long v5 = arg21;
        super();
        Preconditions.checkNotEmpty(arg13);
        Preconditions.checkNotEmpty(arg14);
        long v7 = 0;
        boolean v10 = false;
        boolean v9 = Long.compare(v1, v7) >= 0 ? true : false;
        Preconditions.checkArgument(v9);
        v9 = v3 >= v7 ? true : false;
        Preconditions.checkArgument(v9);
        if(v5 >= v7) {
            v10 = true;
        }

        Preconditions.checkArgument(v10);
        v0.zztt = arg13;
        v0.name = arg14;
        v0.zzaie = v1;
        v0.zzaif = v3;
        v0.zzaig = arg19;
        v0.zzaih = v5;
        v0.zzaii = arg23;
        v0.zzaij = arg24;
        v0.zzaik = arg25;
        v0.zzail = arg26;
    }

    final zzz zza(long arg18, long arg20) {
        return new zzz(this.zztt, this.name, this.zzaie, this.zzaif, this.zzaig, arg18, Long.valueOf(arg20), this.zzaij, this.zzaik, this.zzail);
    }

    final zzz zza(Long arg18, Long arg19, Boolean arg20) {
        zzz v0 = this;
        Boolean v16 = arg20 == null || (arg20.booleanValue()) ? arg20 : null;
        return new zzz(v0.zztt, v0.name, v0.zzaie, v0.zzaif, v0.zzaig, v0.zzaih, v0.zzaii, arg18, arg19, v16);
    }

    final zzz zzai(long arg18) {
        return new zzz(this.zztt, this.name, this.zzaie, this.zzaif, arg18, this.zzaih, this.zzaii, this.zzaij, this.zzaik, this.zzail);
    }

    final zzz zziu() {
        return new zzz(this.zztt, this.name, this.zzaie + 1, 1 + this.zzaif, this.zzaig, this.zzaih, this.zzaii, this.zzaij, this.zzaik, this.zzail);
    }
}

