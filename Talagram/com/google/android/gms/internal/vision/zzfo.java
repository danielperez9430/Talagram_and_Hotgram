package com.google.android.gms.internal.vision;

abstract class zzfo {
    zzfo() {
        super();
    }

    abstract int zzb(CharSequence arg1, byte[] arg2, int arg3, int arg4);

    abstract int zzb(int arg1, byte[] arg2, int arg3, int arg4);

    final boolean zze(byte[] arg2, int arg3, int arg4) {
        if(this.zzb(0, arg2, arg3, arg4) == 0) {
            return 1;
        }

        return 0;
    }
}

