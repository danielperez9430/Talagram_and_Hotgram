package com.google.android.gms.internal.clearcut;

final class zzcf implements zzdn {
    private static final zzcf zzjo;

    static {
        zzcf.zzjo = new zzcf();
    }

    private zzcf() {
        super();
    }

    public final boolean zza(Class arg2) {
        return zzcg.class.isAssignableFrom(arg2);
    }

    public static zzcf zzay() {
        return zzcf.zzjo;
    }

    public final zzdm zzb(Class arg5) {
        String v5;
        if(!zzcg.class.isAssignableFrom(arg5)) {
            String v1 = "Unsupported message type: ";
            v5 = String.valueOf(arg5.getName());
            v5 = v5.length() != 0 ? v1.concat(v5) : new String(v1);
            throw new IllegalArgumentException(v5);
        }

        try {
            return zzcg.zzc(arg5.asSubclass(zzcg.class)).zza(zzg.zzkf, null, null);
        }
        catch(Exception v0) {
            String v2 = "Unable to get message info for ";
            v5 = String.valueOf(arg5.getName());
            v5 = v5.length() != 0 ? v2.concat(v5) : new String(v2);
            throw new RuntimeException(v5, ((Throwable)v0));
        }
    }
}

