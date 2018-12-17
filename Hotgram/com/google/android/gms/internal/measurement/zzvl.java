package com.google.android.gms.internal.measurement;

final class zzvl implements zzws {
    private static final zzvl zzbyl;

    static {
        zzvl.zzbyl = new zzvl();
    }

    private zzvl() {
        super();
    }

    public final boolean zze(Class arg2) {
        return zzvm.class.isAssignableFrom(arg2);
    }

    public final zzwr zzf(Class arg5) {
        String v5;
        if(!zzvm.class.isAssignableFrom(arg5)) {
            String v1 = "Unsupported message type: ";
            v5 = String.valueOf(arg5.getName());
            v5 = v5.length() != 0 ? v1.concat(v5) : new String(v1);
            throw new IllegalArgumentException(v5);
        }

        try {
            return zzvm.zzg(arg5.asSubclass(zzvm.class)).zza(zze.zzbyv, null, null);
        }
        catch(Exception v0) {
            String v2 = "Unable to get message info for ";
            v5 = String.valueOf(arg5.getName());
            v5 = v5.length() != 0 ? v2.concat(v5) : new String(v2);
            throw new RuntimeException(v5, ((Throwable)v0));
        }
    }

    public static zzvl zzwb() {
        return zzvl.zzbyl;
    }
}

