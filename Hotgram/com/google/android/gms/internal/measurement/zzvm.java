package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class zzvm extends zztw {
    public class zza extends zztx {
        private final zzvm zzbyp;
        private zzvm zzbyq;
        private boolean zzbyr;

        protected zza(zzvm arg3) {
            super();
            this.zzbyp = arg3;
            this.zzbyq = arg3.zza(zze.zzbyw, null, null);
            this.zzbyr = false;
        }

        public Object clone() {
            Object v0 = this.zzbyp.zza(zze.zzbyx, null, null);
            ((zza)v0).zza(this.zzwi());
            return v0;
        }

        public final boolean isInitialized() {
            return zzvm.zza(this.zzbyq, false);
        }

        public final zza zza(zzvm arg4) {
            if(this.zzbyr) {
                Object v0 = this.zzbyq.zza(zze.zzbyw, null, null);
                zza.zza(((zzvm)v0), this.zzbyq);
                this.zzbyq = ((zzvm)v0);
                this.zzbyr = false;
            }

            zza.zza(this.zzbyq, arg4);
            return this;
        }

        private static void zza(zzvm arg1, zzvm arg2) {
            zzxf.zzxn().zzag(arg1).zzd(arg1, arg2);
        }

        protected final zztx zza(zztw arg1) {
            return this.zza(((zzvm)arg1));
        }

        public final zztx zztv() {
            return ((zztx)this).clone();
        }

        public final zzwt zzwf() {
            return this.zzbyp;
        }

        public zzvm zzwg() {
            if(this.zzbyr) {
                return this.zzbyq;
            }

            zzxf.zzxn().zzag(this.zzbyq).zzu(this.zzbyq);
            this.zzbyr = true;
            return this.zzbyq;
        }

        public final zzvm zzwh() {
            zzwt v2_1;
            zzwt v0 = this.zzwi();
            boolean v1 = Boolean.TRUE.booleanValue();
            Object v3 = null;
            int v2 = ((zzvm)v0).zza(zze.zzbyt, v3, v3).byteValue();
            boolean v4 = true;
            if(v2 == 1) {
            }
            else if(v2 == 0) {
                v4 = false;
            }
            else {
                v4 = zzxf.zzxn().zzag(v0).zzaf(v0);
                if(v1) {
                    int v1_1 = zze.zzbyu;
                    if(v4) {
                        v2_1 = v0;
                    }
                    else {
                        Object v2_2 = v3;
                    }

                    ((zzvm)v0).zza(v1_1, v2_1, v3);
                }
            }

            if(v4) {
                return ((zzvm)v0);
            }

            throw new zzya(v0);
        }

        public zzwt zzwi() {
            return this.zzwg();
        }

        public zzwt zzwj() {
            return this.zzwh();
        }
    }

    public final class zzb extends zzty {
        private final zzvm zzbyp;

        public zzb(zzvm arg1) {
            super();
            this.zzbyp = arg1;
        }

        public final Object zza(zzuo arg2, zzuz arg3) {
            return zzvm.zza(this.zzbyp, arg2, arg3);
        }
    }

    public abstract class zzc extends zzvm implements zzwv {
        protected zzvd zzbys;

        public zzc() {
            super();
            this.zzbys = zzvd.zzvt();
        }
    }

    public final class zzd extends zzux {
    }

    public final class zze {
        public static final enum int zzbyt = 1;
        public static final enum int zzbyu = 2;
        public static final enum int zzbyv = 3;
        public static final enum int zzbyw = 4;
        public static final enum int zzbyx = 5;
        public static final enum int zzbyy = 6;
        public static final enum int zzbyz = 7;
        public static final enum int zzbzb;
        public static final enum int zzbzc;
        public static final enum int zzbze;
        public static final enum int zzbzf;

        static {
            zze.zzbza = new int[]{zze.zzbyt, zze.zzbyu, zze.zzbyv, zze.zzbyw, zze.zzbyx, zze.zzbyy, zze.zzbyz};
            zze.zzbzb = 1;
            zze.zzbzc = 2;
            zze.zzbzd = new int[]{zze.zzbzb, zze.zzbzc};
            zze.zzbze = 1;
            zze.zzbzf = 2;
            zze.zzbzg = new int[]{zze.zzbze, zze.zzbzf};
        }

        public static int[] values$50KLMJ33DTMIUPRFDTJMOP9FE1P6UT3FC9QMCBQ7CLN6ASJ1EHIM8JB5EDPM2PR59HKN8P949LIN8Q3FCHA6UIBEEPNMMP9R0() {
            // Method was not decompiled
        }
    }

    protected zzyc zzbym;
    private int zzbyn;
    private static Map zzbyo;

    static {
        zzvm.zzbyo = new ConcurrentHashMap();
    }

    public zzvm() {
        super();
        this.zzbym = zzyc.zzyf();
        this.zzbyn = -1;
    }

    public boolean equals(Object arg3) {
        if(this == (((zzvm)arg3))) {
            return 1;
        }

        if(!this.zza(zze.zzbyy, null, null).getClass().isInstance(arg3)) {
            return 0;
        }

        return zzxf.zzxn().zzag(this).equals(this, arg3);
    }

    public int hashCode() {
        if(this.zzbtr != 0) {
            return this.zzbtr;
        }

        this.zzbtr = zzxf.zzxn().zzag(this).hashCode(this);
        return this.zzbtr;
    }

    public final boolean isInitialized() {
        zzvm v3;
        boolean v0 = Boolean.TRUE.booleanValue();
        Object v2 = null;
        int v1 = this.zza(zze.zzbyt, v2, v2).byteValue();
        if(v1 == 1) {
            return 1;
        }

        if(v1 == 0) {
            return 0;
        }

        boolean v1_1 = zzxf.zzxn().zzag(this).zzaf(this);
        if(v0) {
            int v0_1 = zze.zzbyu;
            if(v1_1) {
                v3 = this;
            }
            else {
                Object v3_1 = v2;
            }

            this.zza(v0_1, v3, v2);
        }

        return v1_1;
    }

    public String toString() {
        return zzww.zza(((zzwt)this), super.toString());
    }

    protected static void zza(Class arg1, zzvm arg2) {
        zzvm.zzbyo.put(arg1, arg2);
    }

    protected abstract Object zza(int arg1, Object arg2, Object arg3);

    static Object zza(Method arg0, Object arg1, Object[] arg2) {
        try {
            return arg0.invoke(arg1, arg2);
        }
        catch(InvocationTargetException v0) {
            Throwable v0_2 = v0.getCause();
            if(!(v0_2 instanceof RuntimeException)) {
                if((v0_2 instanceof Error)) {
                    throw v0_2;
                }

                throw new RuntimeException("Unexpected exception thrown by generated accessor method.", v0_2);
            }

            throw v0_2;
        }
        catch(IllegalAccessException v0_1) {
            throw new RuntimeException("Couldn\'t use Java reflection to implement protocol message reflection.", ((Throwable)v0_1));
        }
    }

    static zzvm zza(zzvm arg2, zzuo arg3, zzuz arg4) {
        Object v2 = arg2.zza(zze.zzbyw, null, null);
        try {
            zzxf.zzxn().zzag(v2).zza(v2, zzur.zza(arg3), arg4);
            zzxf.zzxn().zzag(v2).zzu(v2);
            return ((zzvm)v2);
        }
        catch(RuntimeException v2_1) {
            if((v2_1.getCause() instanceof zzvt)) {
                throw v2_1.getCause();
            }

            throw v2_1;
        }
        catch(IOException v3) {
            if((v3.getCause() instanceof zzvt)) {
                throw v3.getCause();
            }

            throw new zzvt(v3.getMessage()).zzg(((zzwt)v2));
        }
    }

    protected static Object zza(zzwt arg1, String arg2, Object[] arg3) {
        return new zzxh(arg1, arg2, arg3);
    }

    protected static final boolean zza(zzvm arg1, boolean arg2) {
        int v2 = arg1.zza(zze.zzbyt, null, null).byteValue();
        if(v2 == 1) {
            return 1;
        }

        if(v2 == 0) {
            return 0;
        }

        return zzxf.zzxn().zzag(arg1).zzaf(arg1);
    }

    final void zzah(int arg1) {
        this.zzbyn = arg1;
    }

    public final void zzb(zzut arg3) {
        zzxf.zzxn().zzi(this.getClass()).zza(this, zzuv.zza(arg3));
    }

    static zzvm zzg(Class arg3) {
        Object v0 = zzvm.zzbyo.get(arg3);
        if(v0 == null) {
            try {
                Class.forName(arg3.getName(), true, arg3.getClassLoader());
            }
            catch(ClassNotFoundException v3) {
                throw new IllegalStateException("Class initialization cannot fail.", ((Throwable)v3));
            }

            v0 = zzvm.zzbyo.get(arg3);
        }

        if(v0 == null) {
            String v1 = "Unable to get default instance for: ";
            String v3_1 = String.valueOf(arg3.getName());
            v3_1 = v3_1.length() != 0 ? v1.concat(v3_1) : new String(v1);
            throw new IllegalStateException(v3_1);
        }

        return ((zzvm)v0);
    }

    final int zztu() {
        return this.zzbyn;
    }

    public final int zzvu() {
        if(this.zzbyn == -1) {
            this.zzbyn = zzxf.zzxn().zzag(this).zzae(this);
        }

        return this.zzbyn;
    }

    protected static zzvs zzwc() {
        return zzxg.zzxo();
    }

    public final zzwu zzwd() {
        Object v0 = this.zza(zze.zzbyx, null, null);
        ((zza)v0).zza(this);
        return ((zzwu)v0);
    }

    public final zzwu zzwe() {
        return this.zza(zze.zzbyx, null, null);
    }

    public final zzwt zzwf() {
        return this.zza(zze.zzbyy, null, null);
    }
}

