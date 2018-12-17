package com.google.android.gms.internal.vision;

final class zzcq implements zzdw {
    private static final zzcq zzkq;

    static {
        zzcq.zzkq = new zzcq();
    }

    private zzcq() {
        super();
    }

    public final boolean zza(Class arg2) {
        return zzcr.class.isAssignableFrom(arg2);
    }

    public final zzdv zzb(Class arg5) {
        String v5;
        if(!zzcr.class.isAssignableFrom(arg5)) {
            String v1 = "Unsupported message type: ";
            v5 = String.valueOf(arg5.getName());
            v5 = v5.length() != 0 ? v1.concat(v5) : new String(v1);
            throw new IllegalArgumentException(v5);
        }

        try {
            return zzcr.zzc(arg5.asSubclass(zzcr.class)).zza(zzd.zzla, null, null);
        }
        catch(Exception v0) {
            String v2 = "Unable to get message info for ";
            v5 = String.valueOf(arg5.getName());
            v5 = v5.length() != 0 ? v2.concat(v5) : new String(v2);
            throw new RuntimeException(v5, ((Throwable)v0));
        }
    }

    public static zzcq zzbs() {
        return zzcq.zzkq;
    }
}

