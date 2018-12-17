package com.google.android.gms.internal.stable;

final class zzo extends zzl {
    private final zzm zzahm;

    zzo() {
        super();
        this.zzahm = new zzm();
    }

    public final void zza(Throwable arg3, Throwable arg4) {
        if(arg4 != arg3) {
            if(arg4 != null) {
                this.zzahm.zza(arg3, true).add(arg4);
                return;
            }

            throw new NullPointerException("The suppressed exception cannot be null.");
        }

        throw new IllegalArgumentException("Self suppression is not allowed.", arg4);
    }
}

