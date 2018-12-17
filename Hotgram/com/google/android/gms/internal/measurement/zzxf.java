package com.google.android.gms.internal.measurement;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

final class zzxf {
    private static final zzxf zzcbs;
    private final zzxk zzcbt;
    private final ConcurrentMap zzcbu;

    static {
        zzxf.zzcbs = new zzxf();
    }

    private zzxf() {
        zzwi v3_1;
        super();
        this.zzcbu = new ConcurrentHashMap();
        String[] v0 = new String[]{"com.google.protobuf.AndroidProto3SchemaFactory"};
        zzxk v3 = null;
        int v1;
        for(v1 = 0; v1 <= 0; ++v1) {
            v3 = zzxf.zzgb(v0[0]);
            if(v3 != null) {
                break;
            }
        }

        if(v3 == null) {
            v3_1 = new zzwi();
        }

        this.zzcbt = ((zzxk)v3_1);
    }

    public final zzxj zzag(Object arg1) {
        return this.zzi(arg1.getClass());
    }

    private static zzxk zzgb(String arg2) {
        try {
            return Class.forName(arg2).getConstructor().newInstance();
        }
        catch(Throwable ) {
            return null;
        }
    }

    public final zzxj zzi(Class arg3) {
        zzvo.zza(arg3, "messageType");
        Object v0 = this.zzcbu.get(arg3);
        if(v0 == null) {
            zzxj v0_1 = this.zzcbt.zzh(arg3);
            zzvo.zza(arg3, "messageType");
            zzvo.zza(v0_1, "schema");
            Object v3 = this.zzcbu.putIfAbsent(arg3, v0_1);
            if(v3 != null) {
                v0 = v3;
            }
        }

        return ((zzxj)v0);
    }

    public static zzxf zzxn() {
        return zzxf.zzcbs;
    }
}

