package com.google.android.gms.internal.places;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class zzgl {
    final class zzb {
        private final int number;
        private final Object object;

        zzb(Object arg1, int arg2) {
            super();
            this.object = arg1;
            this.number = arg2;
        }

        public final boolean equals(Object arg4) {
            if(!(arg4 instanceof zzb)) {
                return 0;
            }

            if(this.object == ((zzb)arg4).object && this.number == ((zzb)arg4).number) {
                return 1;
            }

            return 0;
        }

        public final int hashCode() {
            return System.identityHashCode(this.object) * 65535 + this.number;
        }
    }

    private static volatile boolean zzpc = false;
    private static final Class zzpd;
    private static volatile zzgl zzpe;
    static final zzgl zzpf;
    private final Map zzpg;

    static {
        zzgl.zzpd = zzgl.zzcz();
        zzgl.zzpf = new zzgl(true);
    }

    private zzgl(boolean arg1) {
        super();
        this.zzpg = Collections.emptyMap();
    }

    zzgl() {
        super();
        this.zzpg = new HashMap();
    }

    public final zzg zzb(zzih arg3, int arg4) {
        return this.zzpg.get(new zzb(arg3, arg4));
    }

    static zzgl zzcy() {
        return zzgx.zzb(zzgl.class);
    }

    private static Class zzcz() {
        try {
            return Class.forName("com.google.protobuf.Extension");
        }
        catch(ClassNotFoundException ) {
            return null;
        }
    }

    public static zzgl zzda() {
        return zzgk.zzcx();
    }

    public static zzgl zzdb() {
        zzgl v0 = zzgl.zzpe;
        if(v0 == null) {
            Class v1 = zzgl.class;
            __monitor_enter(v1);
            try {
                v0 = zzgl.zzpe;
                if(v0 == null) {
                    v0 = zzgk.zzcy();
                    zzgl.zzpe = v0;
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

