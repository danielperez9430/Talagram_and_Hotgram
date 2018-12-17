package com.google.android.gms.internal.clearcut;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

final class zzea {
    private static final zzea zznc;
    private final zzeg zznd;
    private final ConcurrentMap zzne;

    static {
        zzea.zznc = new zzea();
    }

    private zzea() {
        zzdd v3_1;
        super();
        this.zzne = new ConcurrentHashMap();
        String[] v0 = new String[]{"com.google.protobuf.AndroidProto3SchemaFactory"};
        zzeg v3 = null;
        int v1;
        for(v1 = 0; v1 <= 0; ++v1) {
            v3 = zzea.zzk(v0[0]);
            if(v3 != null) {
                break;
            }
        }

        if(v3 == null) {
            v3_1 = new zzdd();
        }

        this.zznd = ((zzeg)v3_1);
    }

    public static zzea zzcm() {
        return zzea.zznc;
    }

    public final zzef zze(Class arg3) {
        zzci.zza(arg3, "messageType");
        Object v0 = this.zzne.get(arg3);
        if(v0 == null) {
            zzef v0_1 = this.zznd.zzd(arg3);
            zzci.zza(arg3, "messageType");
            zzci.zza(v0_1, "schema");
            Object v3 = this.zzne.putIfAbsent(arg3, v0_1);
            if(v3 != null) {
                v0 = v3;
            }
        }

        return ((zzef)v0);
    }

    private static zzeg zzk(String arg2) {
        try {
            return Class.forName(arg2).getConstructor().newInstance();
        }
        catch(Throwable ) {
            return null;
        }
    }

    public final zzef zzp(Object arg1) {
        return this.zze(arg1.getClass());
    }
}

