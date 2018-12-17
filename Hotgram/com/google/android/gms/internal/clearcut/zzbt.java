package com.google.android.gms.internal.clearcut;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class zzbt {
    private static volatile boolean zzgm = false;
    private static final Class zzgn;
    static final zzbt zzgo;
    private final Map zzgp;

    static {
        zzbt.zzgn = zzbt.zzam();
        zzbt.zzgo = new zzbt(true);
    }

    private zzbt(boolean arg1) {
        super();
        this.zzgp = Collections.emptyMap();
    }

    zzbt() {
        super();
        this.zzgp = new HashMap();
    }

    private static Class zzam() {
        try {
            return Class.forName("com.google.protobuf.Extension");
        }
        catch(ClassNotFoundException ) {
            return null;
        }
    }

    public static zzbt zzan() {
        return zzbs.zzal();
    }
}

