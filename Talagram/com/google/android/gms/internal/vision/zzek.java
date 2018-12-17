package com.google.android.gms.internal.vision;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

final class zzek {
    private static final zzek zznx;
    private final zzeo zzny;
    private final ConcurrentMap zznz;

    static {
        zzek.zznx = new zzek();
    }

    private zzek() {
        zzdm v3_1;
        super();
        this.zznz = new ConcurrentHashMap();
        String[] v0 = new String[]{"com.google.protobuf.AndroidProto3SchemaFactory"};
        zzeo v3 = null;
        int v1;
        for(v1 = 0; v1 <= 0; ++v1) {
            v3 = zzek.zzk(v0[0]);
            if(v3 != null) {
                break;
            }
        }

        if(v3 == null) {
            v3_1 = new zzdm();
        }

        this.zzny = ((zzeo)v3_1);
    }

    public static zzek zzdc() {
        return zzek.zznx;
    }

    public final zzen zze(Class arg3) {
        zzct.zza(arg3, "messageType");
        Object v0 = this.zznz.get(arg3);
        if(v0 == null) {
            zzen v0_1 = this.zzny.zzd(arg3);
            zzct.zza(arg3, "messageType");
            zzct.zza(v0_1, "schema");
            Object v3 = this.zznz.putIfAbsent(arg3, v0_1);
            if(v3 != null) {
                v0 = v3;
            }
        }

        return ((zzen)v0);
    }

    private static zzeo zzk(String arg2) {
        try {
            return Class.forName(arg2).getConstructor().newInstance();
        }
        catch(Throwable ) {
            return null;
        }
    }

    public final zzen zzq(Object arg1) {
        return this.zze(arg1.getClass());
    }
}

