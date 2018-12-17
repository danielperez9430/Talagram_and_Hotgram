package com.google.android.gms.internal.places;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

final class zzis {
    private static final zzis zzvu;
    private final zziz zzvv;
    private final ConcurrentMap zzvw;

    static {
        zzis.zzvu = new zzis();
    }

    private zzis() {
        super();
        this.zzvw = new ConcurrentHashMap();
        String[] v0 = new String[]{"com.google.protobuf.AndroidProto3SchemaFactory"};
        zziz v3 = null;
        int v1;
        for(v1 = 0; v1 <= 0; ++v1) {
            v3 = zzis.zzp(v0[0]);
            if(v3 != null) {
                break;
            }
        }

        if(v3 == null) {
            zzhw v3_1 = new zzhw();
        }

        this.zzvv = v3;
    }

    public static zzis zzfc() {
        return zzis.zzvu;
    }

    public final zziy zzg(Class arg3) {
        zzhb.zzb(arg3, "messageType");
        Object v0 = this.zzvw.get(arg3);
        if(v0 == null) {
            zziy v0_1 = this.zzvv.zzf(arg3);
            zzhb.zzb(arg3, "messageType");
            zzhb.zzb(v0_1, "schema");
            Object v3 = this.zzvw.putIfAbsent(arg3, v0_1);
            if(v3 != null) {
                v0 = v3;
            }
        }

        return ((zziy)v0);
    }

    private static zziz zzp(String arg2) {
        try {
            return Class.forName(arg2).getConstructor().newInstance();
        }
        catch(Throwable ) {
            return null;
        }
    }

    public final zziy zzp(Object arg1) {
        return this.zzg(arg1.getClass());
    }
}

