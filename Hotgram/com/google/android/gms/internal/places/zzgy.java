package com.google.android.gms.internal.places;

final class zzgy implements zzig {
    private static final zzgy zzsf;

    static {
        zzgy.zzsf = new zzgy();
    }

    private zzgy() {
        super();
    }

    public final boolean zzc(Class arg2) {
        return zzgz.class.isAssignableFrom(arg2);
    }

    public final zzif zzd(Class arg5) {
        String v5;
        if(!zzgz.class.isAssignableFrom(arg5)) {
            String v1 = "Unsupported message type: ";
            v5 = String.valueOf(arg5.getName());
            v5 = v5.length() != 0 ? v1.concat(v5) : new String(v1);
            throw new IllegalArgumentException(v5);
        }

        try {
            return zzgz.zze(arg5.asSubclass(zzgz.class)).zzb(zzh.zzsx, null, null);
        }
        catch(Exception v0) {
            String v2 = "Unable to get message info for ";
            v5 = String.valueOf(arg5.getName());
            v5 = v5.length() != 0 ? v2.concat(v5) : new String(v2);
            throw new RuntimeException(v5, ((Throwable)v0));
        }
    }

    public static zzgy zzdn() {
        return zzgy.zzsf;
    }
}

