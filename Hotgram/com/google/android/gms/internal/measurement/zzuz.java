package com.google.android.gms.internal.measurement;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class zzuz {
    final class zza {
        private final int number;
        private final Object object;

        zza(Object arg1, int arg2) {
            super();
            this.object = arg1;
            this.number = arg2;
        }

        public final boolean equals(Object arg4) {
            if(!(arg4 instanceof zza)) {
                return 0;
            }

            if(this.object == ((zza)arg4).object && this.number == ((zza)arg4).number) {
                return 1;
            }

            return 0;
        }

        public final int hashCode() {
            return System.identityHashCode(this.object) * 65535 + this.number;
        }
    }

    private static volatile boolean zzbvj = false;
    private static final Class zzbvk;
    private static volatile zzuz zzbvl;
    static final zzuz zzbvm;
    private final Map zzbvn;

    static {
        zzuz.zzbvk = zzuz.zzvn();
        zzuz.zzbvm = new zzuz(true);
    }

    private zzuz(boolean arg1) {
        super();
        this.zzbvn = Collections.emptyMap();
    }

    zzuz() {
        super();
        this.zzbvn = new HashMap();
    }

    public final zzd zza(zzwt arg3, int arg4) {
        return this.zzbvn.get(new zza(arg3, arg4));
    }

    static zzuz zzvm() {
        return zzvk.zzd(zzuz.class);
    }

    private static Class zzvn() {
        try {
            return Class.forName("com.google.protobuf.Extension");
        }
        catch(ClassNotFoundException ) {
            return null;
        }
    }

    public static zzuz zzvo() {
        return zzuy.zzvl();
    }

    public static zzuz zzvp() {
        zzuz v0 = zzuz.zzbvl;
        if(v0 == null) {
            Class v1 = zzuz.class;
            __monitor_enter(v1);
            try {
                v0 = zzuz.zzbvl;
                if(v0 == null) {
                    v0 = zzuy.zzvm();
                    zzuz.zzbvl = v0;
                }

                __monitor_exit(v1);
                return v0;
            label_11:
                __monitor_exit(v1);
            }
            catch(Throwable v0_1) {
                goto label_11;
            }

            throw v0_1;
        }

        return v0;
    }
}

