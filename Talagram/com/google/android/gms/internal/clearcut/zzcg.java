package com.google.android.gms.internal.clearcut;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class zzcg extends zzas {
    public class zza extends zzat {
        private final zzcg zzjs;
        protected zzcg zzjt;
        protected boolean zzju;

        protected zza(zzcg arg3) {
            super();
            this.zzjs = arg3;
            this.zzjt = arg3.zza(zzg.zzkg, null, null);
            this.zzju = false;
        }

        public Object clone() {
            Object v0 = this.zzjs.zza(zzg.zzkh, null, null);
            ((zza)v0).zza(this.zzbi());
            return v0;
        }

        public final boolean isInitialized() {
            return zzcg.zza(this.zzjt, false);
        }

        public final zza zza(zzcg arg2) {
            this.zzbf();
            zza.zza(this.zzjt, arg2);
            return this;
        }

        private static void zza(zzcg arg1, zzcg arg2) {
            zzea.zzcm().zzp(arg1).zzc(arg1, arg2);
        }

        protected final zzat zza(zzas arg1) {
            return this.zza(((zzcg)arg1));
        }

        public final zzdo zzbe() {
            return this.zzjs;
        }

        protected void zzbf() {
            if(this.zzju) {
                Object v0 = this.zzjt.zza(zzg.zzkg, null, null);
                zza.zza(((zzcg)v0), this.zzjt);
                this.zzjt = ((zzcg)v0);
                this.zzju = false;
            }
        }

        public zzcg zzbg() {
            if(this.zzju) {
                return this.zzjt;
            }

            zzea.zzcm().zzp(this.zzjt).zzc(this.zzjt);
            this.zzju = true;
            return this.zzjt;
        }

        public final zzcg zzbh() {
            zzdo v2_1;
            zzdo v0 = this.zzbi();
            boolean v1 = Boolean.TRUE.booleanValue();
            Object v3 = null;
            int v2 = ((zzcg)v0).zza(zzg.zzkd, v3, v3).byteValue();
            boolean v4 = true;
            if(v2 == 1) {
            }
            else if(v2 == 0) {
                v4 = false;
            }
            else {
                v4 = zzea.zzcm().zzp(v0).zzo(v0);
                if(v1) {
                    int v1_1 = zzg.zzke;
                    if(v4) {
                        v2_1 = v0;
                    }
                    else {
                        Object v2_2 = v3;
                    }

                    ((zzcg)v0).zza(v1_1, v2_1, v3);
                }
            }

            if(v4) {
                return ((zzcg)v0);
            }

            throw new zzew(v0);
        }

        public zzdo zzbi() {
            return this.zzbg();
        }

        public final zzdo zzbj() {
            zzdo v2_1;
            zzdo v0 = this.zzbi();
            boolean v1 = Boolean.TRUE.booleanValue();
            Object v3 = null;
            int v2 = ((zzcg)v0).zza(zzg.zzkd, v3, v3).byteValue();
            boolean v4 = true;
            if(v2 == 1) {
            }
            else if(v2 == 0) {
                v4 = false;
            }
            else {
                v4 = zzea.zzcm().zzp(v0).zzo(v0);
                if(v1) {
                    int v1_1 = zzg.zzke;
                    if(v4) {
                        v2_1 = v0;
                    }
                    else {
                        Object v2_2 = v3;
                    }

                    ((zzcg)v0).zza(v1_1, v2_1, v3);
                }
            }

            if(v4) {
                return v0;
            }

            throw new zzew(v0);
        }

        public final zzat zzt() {
            return ((zzat)this).clone();
        }
    }

    public final class zzb extends zzau {
        private zzcg zzjs;

        public zzb(zzcg arg1) {
            super();
            this.zzjs = arg1;
        }
    }

    public class zzc extends zza implements zzdq {
        protected zzc(zzd arg1) {
            super(((zzcg)arg1));
        }

        protected final void zzbf() {
            if(!this.zzju) {
                return;
            }

            super.zzbf();
            this.zzjt.zzjv = this.zzjt.zzjv.clone();
        }

        public final zzcg zzbg() {
            return ((zza)this).zzbi();
        }

        public final zzdo zzbi() {
            zzcg v0;
            if(this.zzju) {
                v0 = this.zzjt;
            }
            else {
                this.zzjt.zzjv.zzv();
                v0 = super.zzbg();
            }

            return ((zzdo)v0);
        }
    }

    public abstract class zzd extends zzcg implements zzdq {
        protected zzby zzjv;

        public zzd() {
            super();
            this.zzjv = zzby.zzar();
        }
    }

    final class zze implements zzca {
        final int number;
        private final zzck zzjw;
        final zzfl zzjx;
        final boolean zzjy;
        final boolean zzjz;

        zze(zzck arg1, int arg2, zzfl arg3, boolean arg4, boolean arg5) {
            super();
            this.zzjw = null;
            this.number = 66321687;
            this.zzjx = arg3;
            this.zzjy = false;
            this.zzjz = false;
        }

        public final int compareTo(Object arg2) {
            return this.number - ((zze)arg2).number;
        }

        public final zzdp zza(zzdp arg1, zzdo arg2) {
            return ((zza)arg1).zza(((zzcg)arg2));
        }

        public final zzdv zza(zzdv arg1, zzdv arg2) {
            throw new UnsupportedOperationException();
        }

        public final zzfl zzau() {
            return this.zzjx;
        }

        public final zzfq zzav() {
            return this.zzjx.zzek();
        }

        public final boolean zzaw() {
            return 0;
        }

        public final boolean zzax() {
            return 0;
        }

        public final int zzc() {
            return this.number;
        }
    }

    public final class zzf extends zzbr {
        private final Object zzdu;
        private final zzdo zzka;
        private final zzdo zzkb;
        private final zze zzkc;

        zzf(zzdo arg2, Object arg3, zzdo arg4, zze arg5, Class arg6) {
            super();
            if(arg2 != null) {
                if(arg5.zzjx == zzfl.zzqm) {
                    if(arg4 != null) {
                    }
                    else {
                        throw new IllegalArgumentException("Null messageDefaultInstance");
                    }
                }

                this.zzka = arg2;
                this.zzdu = arg3;
                this.zzkb = arg4;
                this.zzkc = arg5;
                return;
            }

            throw new IllegalArgumentException("Null containingTypeDefaultInstance");
        }
    }

    public final class zzg {
        public static final enum int zzkd = 1;
        public static final enum int zzke = 2;
        public static final enum int zzkf = 3;
        public static final enum int zzkg = 4;
        public static final enum int zzkh = 5;
        public static final enum int zzki = 6;
        public static final enum int zzkj = 7;
        public static final enum int zzkl;
        public static final enum int zzkm;
        public static final enum int zzko;
        public static final enum int zzkp;

        static {
            zzg.zzkk = new int[]{zzg.zzkd, zzg.zzke, zzg.zzkf, zzg.zzkg, zzg.zzkh, zzg.zzki, zzg.zzkj};
            zzg.zzkl = 1;
            zzg.zzkm = 2;
            zzg.zzkn = new int[]{zzg.zzkl, zzg.zzkm};
            zzg.zzko = 1;
            zzg.zzkp = 2;
            zzg.zzkq = new int[]{zzg.zzko, zzg.zzkp};
        }

        public static int[] values$50KLMJ33DTMIUPRFDTJMOP9FE1P6UT3FC9QMCBQ7CLN6ASJ1EHIM8JB5EDPM2PR59HKN8P949LIN8Q3FCHA6UIBEEPNMMP9R0() {
            // Method was not decompiled
        }
    }

    protected zzey zzjp;
    private int zzjq;
    private static Map zzjr;

    static {
        zzcg.zzjr = new ConcurrentHashMap();
    }

    public zzcg() {
        super();
        this.zzjp = zzey.zzea();
        this.zzjq = -1;
    }

    public boolean equals(Object arg3) {
        if(this == (((zzcg)arg3))) {
            return 1;
        }

        if(!this.zza(zzg.zzki, null, null).getClass().isInstance(arg3)) {
            return 0;
        }

        return zzea.zzcm().zzp(this).equals(this, arg3);
    }

    public int hashCode() {
        if(this.zzex != 0) {
            return this.zzex;
        }

        this.zzex = zzea.zzcm().zzp(this).hashCode(this);
        return this.zzex;
    }

    public final boolean isInitialized() {
        zzcg v3;
        boolean v0 = Boolean.TRUE.booleanValue();
        Object v2 = null;
        int v1 = this.zza(zzg.zzkd, v2, v2).byteValue();
        if(v1 == 1) {
            return 1;
        }

        if(v1 == 0) {
            return 0;
        }

        boolean v1_1 = zzea.zzcm().zzp(this).zzo(this);
        if(v0) {
            int v0_1 = zzg.zzke;
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
        return zzdr.zza(((zzdo)this), super.toString());
    }

    protected static void zza(Class arg1, zzcg arg2) {
        zzcg.zzjr.put(arg1, arg2);
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

    public static zzf zza(zzdo arg6, Object arg7, zzdo arg8, zzck arg9, int arg10, zzfl arg11, Class arg12) {
        return new zzf(arg6, arg7, arg8, new zze(null, 66321687, arg11, false, false), arg12);
    }

    protected abstract Object zza(int arg1, Object arg2, Object arg3);

    private static zzcg zza(zzcg arg6, byte[] arg7) {
        Object v6 = arg6.zza(zzg.zzkg, null, null);
        try {
            zzea.zzcm().zzp(v6).zza(v6, arg7, 0, arg7.length, new zzay());
            zzea.zzcm().zzp(v6).zzc(v6);
            if(((zzcg)v6).zzex == 0) {
                return ((zzcg)v6);
            }

            throw new RuntimeException();
        }
        catch(IndexOutOfBoundsException ) {
            throw zzco.zzbl().zzg(((zzdo)v6));
        }
        catch(IOException v7) {
            if((v7.getCause() instanceof zzco)) {
                throw v7.getCause();
            }

            throw new zzco(v7.getMessage()).zzg(((zzdo)v6));
        }
    }

    protected static Object zza(zzdo arg1, String arg2, Object[] arg3) {
        return new zzec(arg1, arg2, arg3);
    }

    protected static final boolean zza(zzcg arg1, boolean arg2) {
        int v2 = arg1.zza(zzg.zzkd, null, null).byteValue();
        if(v2 == 1) {
            return 1;
        }

        if(v2 == 0) {
            return 0;
        }

        return zzea.zzcm().zzp(arg1).zzo(arg1);
    }

    public final int zzas() {
        if(this.zzjq == -1) {
            this.zzjq = zzea.zzcm().zzp(this).zzm(this);
        }

        return this.zzjq;
    }

    protected static zzcl zzaz() {
        return zzch.zzbk();
    }

    protected static zzcg zzb(zzcg arg3, byte[] arg4) {
        Object v0_2;
        arg3 = zzcg.zza(arg3, arg4);
        if(arg3 != null) {
            boolean v4 = Boolean.TRUE.booleanValue();
            Object v1 = null;
            int v0 = arg3.zza(zzg.zzkd, v1, v1).byteValue();
            boolean v2 = true;
            if(v0 == 1) {
            }
            else if(v0 == 0) {
                v2 = false;
            }
            else {
                v2 = zzea.zzcm().zzp(arg3).zzo(arg3);
                if(v4) {
                    int v4_1 = zzg.zzke;
                    if(v2) {
                        zzcg v0_1 = arg3;
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

            throw new zzco(new zzew(((zzdo)arg3)).getMessage()).zzg(((zzdo)arg3));
        }

        return arg3;
    }

    public final void zzb(zzbn arg3) {
        zzea.zzcm().zze(this.getClass()).zza(this, zzbp.zza(arg3));
    }

    protected static zzcm zzba() {
        return zzdc.zzbx();
    }

    protected static zzcn zzbb() {
        return zzeb.zzcn();
    }

    public final zzdp zzbc() {
        Object v0 = this.zza(zzg.zzkh, null, null);
        ((zza)v0).zza(this);
        return ((zzdp)v0);
    }

    public final zzdp zzbd() {
        return this.zza(zzg.zzkh, null, null);
    }

    public final zzdo zzbe() {
        return this.zza(zzg.zzki, null, null);
    }

    static zzcg zzc(Class arg3) {
        Object v0 = zzcg.zzjr.get(arg3);
        if(v0 == null) {
            try {
                Class.forName(arg3.getName(), true, arg3.getClassLoader());
            }
            catch(ClassNotFoundException v3) {
                throw new IllegalStateException("Class initialization cannot fail.", ((Throwable)v3));
            }

            v0 = zzcg.zzjr.get(arg3);
        }

        if(v0 == null) {
            String v1 = "Unable to get default instance for: ";
            String v3_1 = String.valueOf(arg3.getName());
            v3_1 = v3_1.length() != 0 ? v1.concat(v3_1) : new String(v1);
            throw new IllegalStateException(v3_1);
        }

        return ((zzcg)v0);
    }

    final void zzf(int arg1) {
        this.zzjq = arg1;
    }

    final int zzs() {
        return this.zzjq;
    }
}

