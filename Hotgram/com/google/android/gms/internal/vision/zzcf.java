package com.google.android.gms.internal.vision;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class zzcf {
    private static volatile boolean zzho = false;
    private static final Class zzhp;
    static final zzcf zzhq;
    private final Map zzhr;

    static {
        zzcf.zzhp = zzcf.zzbf();
        zzcf.zzhq = new zzcf(true);
    }

    private zzcf(boolean arg1) {
        super();
        this.zzhr = Collections.emptyMap();
    }

    zzcf() {
        super();
        this.zzhr = new HashMap();
    }

    private static Class zzbf() {
        try {
            return Class.forName("com.google.protobuf.Extension");
        }
        catch(ClassNotFoundException ) {
            return null;
        }
    }

    public static zzcf zzbg() {
        return zzce.zzbe();
    }
}

