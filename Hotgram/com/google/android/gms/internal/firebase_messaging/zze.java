package com.google.android.gms.internal.firebase_messaging;

final class zze extends zzb {
    private final zzc zzg;

    zze() {
        super();
        this.zzg = new zzc();
    }

    public final void zza(Throwable arg3, Throwable arg4) {
        if(arg4 != arg3) {
            if(arg4 != null) {
                this.zzg.zza(arg3, true).add(arg4);
                return;
            }

            throw new NullPointerException("The suppressed exception cannot be null.");
        }

        throw new IllegalArgumentException("Self suppression is not allowed.", arg4);
    }
}

