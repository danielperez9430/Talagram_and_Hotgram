package com.google.android.gms.internal.vision;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class zzcr extends zzbf {
    public class zza extends zzbg {
        private final zzcr zzku;
        protected zzcr zzkv;
        private boolean zzkw;

        protected zza(zzcr arg3) {
            super();
            this.zzku = arg3;
            this.zzkv = arg3.zza(zzd.zzlb, null, null);
            this.zzkw = false;
        }

        public Object clone() {
            Object v0 = this.zzku.zza(zzd.zzlc, null, null);
            if(!this.zzkw) {
                zzek.zzdc().zzq(this.zzkv).zzd(this.zzkv);
                this.zzkw = true;
            }

            zzcr v1 = this.zzkv;
            ((zza)v0).zza(v1);
            return v0;
        }

        public final boolean isInitialized() {
            return zzcr.zza(this.zzkv, false);
        }

        public final zza zza(zzcr arg2) {
            this.zzbx();
            zza.zza(this.zzkv, arg2);
            return this;
        }

        private static void zza(zzcr arg1, zzcr arg2) {
            zzek.zzdc().zzq(arg1).zzc(arg1, arg2);
        }

        protected final zzbg zza(zzbf arg1) {
            return this.zza(((zzcr)arg1));
        }

        public final zzbg zzam() {
            return ((zzbg)this).clone();
        }

        public final zzdx zzbw() {
            return this.zzku;
        }

        protected final void zzbx() {
            if(this.zzkw) {
                Object v0 = this.zzkv.zza(zzd.zzlb, null, null);
                zza.zza(((zzcr)v0), this.zzkv);
                this.zzkv = ((zzcr)v0);
                this.zzkw = false;
            }
        }

        public final zzcr zzby() {
            Object v3_2;
            boolean v1 = true;
            if(!this.zzkw) {
                zzek.zzdc().zzq(this.zzkv).zzd(this.zzkv);
                this.zzkw = true;
            }

            zzcr v0 = this.zzkv;
            boolean v2 = Boolean.TRUE.booleanValue();
            Object v4 = null;
            int v3 = v0.zza(zzd.zzky, v4, v4).byteValue();
            if(v3 == 1) {
            }
            else if(v3 == 0) {
                v1 = false;
            }
            else {
                v1 = zzek.zzdc().zzq(v0).zzp(v0);
                if(v2) {
                    int v2_1 = zzd.zzkz;
                    if(v1) {
                        zzcr v3_1 = v0;
                    }
                    else {
                        v3_2 = v4;
                    }

                    v0.zza(v2_1, v3_2, v4);
                }
            }

            if(v1) {
                return v0;
            }

            throw new zzfe(((zzdx)v0));
        }

        public final zzdx zzbz() {
            if(this.zzkw) {
                return this.zzkv;
            }

            zzek.zzdc().zzq(this.zzkv).zzd(this.zzkv);
            this.zzkw = true;
            return this.zzkv;
        }

        public final zzdx zzca() {
            Object v3_2;
            boolean v1 = true;
            if(!this.zzkw) {
                zzek.zzdc().zzq(this.zzkv).zzd(this.zzkv);
                this.zzkw = true;
            }

            zzcr v0 = this.zzkv;
            boolean v2 = Boolean.TRUE.booleanValue();
            Object v4 = null;
            int v3 = v0.zza(zzd.zzky, v4, v4).byteValue();
            if(v3 == 1) {
            }
            else if(v3 == 0) {
                v1 = false;
            }
            else {
                v1 = zzek.zzdc().zzq(v0).zzp(v0);
                if(v2) {
                    int v2_1 = zzd.zzkz;
                    if(v1) {
                        zzcr v3_1 = v0;
                    }
                    else {
                        v3_2 = v4;
                    }

                    v0.zza(v2_1, v3_2, v4);
                }
            }

            if(v1) {
                return ((zzdx)v0);
            }

            throw new zzfe(((zzdx)v0));
        }
    }

    public final class zzb extends zzbh {
        private zzcr zzku;

        public zzb(zzcr arg1) {
            super();
            this.zzku = arg1;
        }
    }

    public abstract class zzc extends zzcr implements zzdz {
        protected zzcj zzkx;

        public zzc() {
            super();
            this.zzkx = zzcj.zzbk();
        }
    }

    public final class zzd {
        public static final enum int zzky = 1;
        public static final enum int zzkz = 2;
        public static final enum int zzla = 3;
        public static final enum int zzlb = 4;
        public static final enum int zzlc = 5;
        public static final enum int zzld = 6;
        public static final enum int zzle = 7;
        public static final enum int zzlg;
        public static final enum int zzlh;
        public static final enum int zzlj;
        public static final enum int zzlk;

        static {
            zzd.zzlf = new int[]{zzd.zzky, zzd.zzkz, zzd.zzla, zzd.zzlb, zzd.zzlc, zzd.zzld, zzd.zzle};
            zzd.zzlg = 1;
            zzd.zzlh = 2;
            zzd.zzli = new int[]{zzd.zzlg, zzd.zzlh};
            zzd.zzlj = 1;
            zzd.zzlk = 2;
            zzd.zzll = new int[]{zzd.zzlj, zzd.zzlk};
        }

        public static int[] values$50KLMJ33DTMIUPRFDTJMOP9FE1P6UT3FC9QMCBQ7CLN6ASJ1EHIM8JB5EDPM2PR59HKN8P949LIN8Q3FCHA6UIBEEPNMMP9R0() {
            // Method was not decompiled
        }
    }

    protected zzfg zzkr;
    private int zzks;
    private static Map zzkt;

    static {
        zzcr.zzkt = new ConcurrentHashMap();
    }

    public zzcr() {
        super();
        this.zzkr = zzfg.zzdu();
        this.zzks = -1;
    }

    public boolean equals(Object arg3) {
        if(this == (((zzcr)arg3))) {
            return 1;
        }

        if(!this.zza(zzd.zzld, null, null).getClass().isInstance(arg3)) {
            return 0;
        }

        return zzek.zzdc().zzq(this).equals(this, arg3);
    }

    public int hashCode() {
        if(this.zzgi != 0) {
            return this.zzgi;
        }

        this.zzgi = zzek.zzdc().zzq(this).hashCode(this);
        return this.zzgi;
    }

    public final boolean isInitialized() {
        zzcr v3;
        boolean v0 = Boolean.TRUE.booleanValue();
        Object v2 = null;
        int v1 = this.zza(zzd.zzky, v2, v2).byteValue();
        if(v1 == 1) {
            return 1;
        }

        if(v1 == 0) {
            return 0;
        }

        boolean v1_1 = zzek.zzdc().zzq(this).zzp(this);
        if(v0) {
            int v0_1 = zzd.zzkz;
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
        return zzea.zza(((zzdx)this), super.toString());
    }

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

    protected abstract Object zza(int arg1, Object arg2, Object arg3);

    private static zzcr zza(zzcr arg6, byte[] arg7) {
        Object v6 = arg6.zza(zzd.zzlb, null, null);
        try {
            zzek.zzdc().zzq(v6).zza(v6, arg7, 0, arg7.length, new zzbl());
            zzek.zzdc().zzq(v6).zzd(v6);
            if(((zzcr)v6).zzgi == 0) {
                return ((zzcr)v6);
            }

            throw new RuntimeException();
        }
        catch(IndexOutOfBoundsException ) {
            throw zzcx.zzcb().zzg(((zzdx)v6));
        }
        catch(IOException v7) {
            if((v7.getCause() instanceof zzcx)) {
                throw v7.getCause();
            }

            throw new zzcx(v7.getMessage()).zzg(((zzdx)v6));
        }
    }

    protected static Object zza(zzdx arg1, String arg2, Object[] arg3) {
        return new zzem(arg1, arg2, arg3);
    }

    protected static void zza(Class arg1, zzcr arg2) {
        zzcr.zzkt.put(arg1, arg2);
    }

    protected static final boolean zza(zzcr arg1, boolean arg2) {
        int v2 = arg1.zza(zzd.zzky, null, null).byteValue();
        if(v2 == 1) {
            return 1;
        }

        if(v2 == 0) {
            return 0;
        }

        return zzek.zzdc().zzq(arg1).zzp(arg1);
    }

    final int zzal() {
        return this.zzks;
    }

    protected static zzcr zzb(zzcr arg3, byte[] arg4) {
        Object v0_2;
        arg3 = zzcr.zza(arg3, arg4);
        if(arg3 != null) {
            boolean v4 = Boolean.TRUE.booleanValue();
            Object v1 = null;
            int v0 = arg3.zza(zzd.zzky, v1, v1).byteValue();
            boolean v2 = true;
            if(v0 == 1) {
            }
            else if(v0 == 0) {
                v2 = false;
            }
            else {
                v2 = zzek.zzdc().zzq(arg3).zzp(arg3);
                if(v4) {
                    int v4_1 = zzd.zzkz;
                    if(v2) {
                        zzcr v0_1 = arg3;
                    }
                    else {
                        v0_2 = v1;
                    }

                    arg3.zza(v4_1, v0_2, v1);
                }
            }

            if(v2) {
                return arg3;
            }

            throw new zzcx(new zzfe(((zzdx)arg3)).getMessage()).zzg(((zzdx)arg3));
        }

        return arg3;
    }

    public final void zzb(zzca arg3) {
        zzek.zzdc().zze(this.getClass()).zza(this, zzcc.zza(arg3));
    }

    public final int zzbl() {
        if(this.zzks == -1) {
            this.zzks = zzek.zzdc().zzq(this).zzn(this);
        }

        return this.zzks;
    }

    protected static zzcw zzbt() {
        return zzel.zzdd();
    }

    public final zzdy zzbu() {
        Object v0 = this.zza(zzd.zzlc, null, null);
        ((zza)v0).zza(this);
        return ((zzdy)v0);
    }

    public final zzdy zzbv() {
        return this.zza(zzd.zzlc, null, null);
    }

    public final zzdx zzbw() {
        return this.zza(zzd.zzld, null, null);
    }

    static zzcr zzc(Class arg3) {
        Object v0 = zzcr.zzkt.get(arg3);
        if(v0 == null) {
            try {
                Class.forName(arg3.getName(), true, arg3.getClassLoader());
            }
            catch(ClassNotFoundException v3) {
                throw new IllegalStateException("Class initialization cannot fail.", ((Throwable)v3));
            }

            v0 = zzcr.zzkt.get(arg3);
        }

        if(v0 == null) {
            String v1 = "Unable to get default instance for: ";
            String v3_1 = String.valueOf(arg3.getName());
            v3_1 = v3_1.length() != 0 ? v1.concat(v3_1) : new String(v1);
            throw new IllegalStateException(v3_1);
        }

        return ((zzcr)v0);
    }

    final void zzh(int arg1) {
        this.zzks = arg1;
    }
}

