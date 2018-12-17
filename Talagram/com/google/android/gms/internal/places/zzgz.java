package com.google.android.gms.internal.places;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class zzgz extends zzfh {
    public class zzb extends zzfi {
        private final zzgz zzsj;
        protected zzgz zzsk;
        protected boolean zzsl;

        protected zzb(zzgz arg3) {
            super();
            this.zzsj = arg3;
            this.zzsk = arg3.zzb(zzh.zzsy, null, null);
            this.zzsl = false;
        }

        public Object clone() {
            Object v0 = this.zzsj.zzb(zzh.zzsz, null, null);
            ((zzb)v0).zzb(this.zzdw());
            return v0;
        }

        public final boolean isInitialized() {
            return zzgz.zzb(this.zzsk, false);
        }

        public final zzfi zzaz() {
            return ((zzfi)this).clone();
        }

        public final zzb zzb(zzgz arg2) {
            this.zzdt();
            zzb.zzb(this.zzsk, arg2);
            return this;
        }

        private static void zzb(zzgz arg1, zzgz arg2) {
            zzis.zzfc().zzp(arg1).zzd(arg1, arg2);
        }

        protected final zzfi zzb(zzfh arg1) {
            return this.zzb(((zzgz)arg1));
        }

        public final zzih zzds() {
            return this.zzsj;
        }

        protected void zzdt() {
            if(this.zzsl) {
                Object v0 = this.zzsk.zzb(zzh.zzsy, null, null);
                zzb.zzb(((zzgz)v0), this.zzsk);
                this.zzsk = ((zzgz)v0);
                this.zzsl = false;
            }
        }

        public zzgz zzdu() {
            if(this.zzsl) {
                return this.zzsk;
            }

            zzis.zzfc().zzp(this.zzsk).zzd(this.zzsk);
            this.zzsl = true;
            return this.zzsk;
        }

        public final zzgz zzdv() {
            zzih v2_1;
            zzih v0 = this.zzdw();
            boolean v1 = Boolean.TRUE.booleanValue();
            Object v3 = null;
            int v2 = ((zzgz)v0).zzb(zzh.zzsv, v3, v3).byteValue();
            boolean v4 = true;
            if(v2 == 1) {
            }
            else if(v2 == 0) {
                v4 = false;
            }
            else {
                v4 = zzis.zzfc().zzp(v0).zzo(v0);
                if(v1) {
                    int v1_1 = zzh.zzsw;
                    if(v4) {
                        v2_1 = v0;
                    }
                    else {
                        Object v2_2 = v3;
                    }

                    ((zzgz)v0).zzb(v1_1, v2_1, v3);
                }
            }

            if(v4) {
                return ((zzgz)v0);
            }

            throw new zzjp(v0);
        }

        public zzih zzdw() {
            return this.zzdu();
        }

        public final zzih zzdx() {
            Object v2_2;
            zzih v0 = this.zzdw();
            boolean v1 = Boolean.TRUE.booleanValue();
            Object v3 = null;
            int v2 = ((zzgz)v0).zzb(zzh.zzsv, v3, v3).byteValue();
            boolean v4 = true;
            if(v2 == 1) {
            }
            else if(v2 == 0) {
                v4 = false;
            }
            else {
                v4 = zzis.zzfc().zzp(v0).zzo(v0);
                if(v1) {
                    int v1_1 = zzh.zzsw;
                    if(v4) {
                        zzih v2_1 = v0;
                    }
                    else {
                        v2_2 = v3;
                    }

                    ((zzgz)v0).zzb(v1_1, v2_2, v3);
                }
            }

            if(v4) {
                return v0;
            }

            throw new zzjp(v0);
        }
    }

    public final class zzc extends zzfj {
        private zzgz zzsj;

        public zzc(zzgz arg1) {
            super();
            this.zzsj = arg1;
        }

        public final Object zzb(zzga arg2, zzgl arg3) {
            return zzgz.zzb(this.zzsj, arg2, arg3);
        }
    }

    public class zzd extends zzb implements zzij {
        protected zzd(zze arg1) {
            super(((zzgz)arg1));
        }

        protected final void zzdt() {
            if(!this.zzsl) {
                return;
            }

            super.zzdt();
            this.zzsk.zzsm = this.zzsk.zzsm.clone();
        }

        public final zzgz zzdu() {
            return ((zzb)this).zzdw();
        }

        public final zzih zzdw() {
            zzgz v0;
            if(this.zzsl) {
                v0 = this.zzsk;
            }
            else {
                this.zzsk.zzsm.zzbb();
                v0 = super.zzdu();
            }

            return ((zzih)v0);
        }
    }

    public abstract class zze extends zzgz implements zzij {
        protected zzgq zzsm;

        public zze() {
            super();
            this.zzsm = zzgq.zzdf();
        }
    }

    final class zzf implements zzgs {
        final int number;
        final zzhd zzsn;
        final zzke zzso;
        final boolean zzsp;
        final boolean zzsq;

        zzf(zzhd arg1, int arg2, zzke arg3, boolean arg4, boolean arg5) {
            super();
            this.zzsn = null;
            this.number = 77815057;
            this.zzso = arg3;
            this.zzsp = false;
            this.zzsq = false;
        }

        public final int compareTo(Object arg2) {
            return this.number - ((zzf)arg2).number;
        }

        public final int zzap() {
            return this.number;
        }

        public final zzii zzb(zzii arg1, zzih arg2) {
            return ((zzb)arg1).zzb(((zzgz)arg2));
        }

        public final zzin zzb(zzin arg1, zzin arg2) {
            throw new UnsupportedOperationException();
        }

        public final zzke zzdi() {
            return this.zzso;
        }

        public final zzkj zzdj() {
            return this.zzso.zzgz();
        }

        public final boolean zzdk() {
            return 0;
        }

        public final boolean zzdl() {
            return 0;
        }
    }

    public final class zzg extends zzgj {
        private final zzih zzsr;
        private final Object zzss;
        final zzih zzst;
        final zzf zzsu;

        zzg(zzih arg2, Object arg3, zzih arg4, zzf arg5, Class arg6) {
            super();
            if(arg2 != null) {
                if(arg5.zzso == zzke.zzzd) {
                    if(arg4 != null) {
                    }
                    else {
                        throw new IllegalArgumentException("Null messageDefaultInstance");
                    }
                }

                this.zzsr = arg2;
                this.zzss = arg3;
                this.zzst = arg4;
                this.zzsu = arg5;
                return;
            }

            throw new IllegalArgumentException("Null containingTypeDefaultInstance");
        }
    }

    public final class zzh {
        public static final enum int zzsv = 1;
        public static final enum int zzsw = 2;
        public static final enum int zzsx = 3;
        public static final enum int zzsy = 4;
        public static final enum int zzsz = 5;
        public static final enum int zzta = 6;
        public static final enum int zztb = 7;
        public static final enum int zztd;
        public static final enum int zzte;
        public static final enum int zztg;
        public static final enum int zzth;

        static {
            zzh.zztc = new int[]{zzh.zzsv, zzh.zzsw, zzh.zzsx, zzh.zzsy, zzh.zzsz, zzh.zzta, zzh.zztb};
            zzh.zztd = 1;
            zzh.zzte = 2;
            zzh.zztf = new int[]{zzh.zztd, zzh.zzte};
            zzh.zztg = 1;
            zzh.zzth = 2;
            zzh.zzti = new int[]{zzh.zztg, zzh.zzth};
        }

        public static int[] values$50KLMJ33DTMIUPRFDTJMOP9FE1P6UT3FC9QMCBQ7CLN6ASJ1EHIM8JB5EDPM2PR59HKN8P949LIN8Q3FCHA6UIBEEPNMMP9R0() {
            // Method was not decompiled
        }
    }

    protected zzjr zzsg;
    private int zzsh;
    private static Map zzsi;

    static {
        zzgz.zzsi = new ConcurrentHashMap();
    }

    public zzgz() {
        super();
        this.zzsg = zzjr.zzgp();
        this.zzsh = -1;
    }

    public boolean equals(Object arg3) {
        if(this == (((zzgz)arg3))) {
            return 1;
        }

        if(!this.zzb(zzh.zzta, null, null).getClass().isInstance(arg3)) {
            return 0;
        }

        return zzis.zzfc().zzp(this).equals(this, arg3);
    }

    public int hashCode() {
        if(this.zznh != 0) {
            return this.zznh;
        }

        this.zznh = zzis.zzfc().zzp(this).hashCode(this);
        return this.zznh;
    }

    public final boolean isInitialized() {
        Object v3_1;
        boolean v0 = Boolean.TRUE.booleanValue();
        Object v2 = null;
        int v1 = this.zzb(zzh.zzsv, v2, v2).byteValue();
        if(v1 == 1) {
            return 1;
        }

        if(v1 == 0) {
            return 0;
        }

        boolean v1_1 = zzis.zzfc().zzp(this).zzo(this);
        if(v0) {
            int v0_1 = zzh.zzsw;
            if(v1_1) {
                zzgz v3 = this;
            }
            else {
                v3_1 = v2;
            }

            this.zzb(v0_1, v3_1, v2);
        }

        return v1_1;
    }

    public String toString() {
        return zzik.zzb(((zzih)this), super.toString());
    }

    final int zzay() {
        return this.zzsh;
    }

    static Object zzb(Method arg0, Object arg1, Object[] arg2) {
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

    protected abstract Object zzb(int arg1, Object arg2, Object arg3);

    public static zzg zzb(zzih arg6, Object arg7, zzih arg8, zzhd arg9, int arg10, zzke arg11, Class arg12) {
        return new zzg(arg6, arg7, arg8, new zzf(null, 77815057, arg11, false, false), arg12);
    }

    static zzgz zzb(zzgz arg2, zzga arg3, zzgl arg4) {
        Object v2 = arg2.zzb(zzh.zzsy, null, null);
        try {
            zzis.zzfc().zzp(v2).zzb(v2, zzgd.zzb(arg3), arg4);
            zzis.zzfc().zzp(v2).zzd(v2);
            return ((zzgz)v2);
        }
        catch(RuntimeException v2_1) {
            if((v2_1.getCause() instanceof zzhh)) {
                throw v2_1.getCause();
            }

            throw v2_1;
        }
        catch(IOException v3) {
            if((v3.getCause() instanceof zzhh)) {
                throw v3.getCause();
            }

            throw new zzhh(v3.getMessage()).zzh(((zzih)v2));
        }
    }

    protected static Object zzb(zzih arg1, String arg2, Object[] arg3) {
        return new zziu(arg1, arg2, arg3);
    }

    protected static void zzb(Class arg1, zzgz arg2) {
        zzgz.zzsi.put(arg1, arg2);
    }

    protected static final boolean zzb(zzgz arg1, boolean arg2) {
        int v2 = arg1.zzb(zzh.zzsv, null, null).byteValue();
        if(v2 == 1) {
            return 1;
        }

        if(v2 == 0) {
            return 0;
        }

        return zzis.zzfc().zzp(arg1).zzo(arg1);
    }

    public final void zzc(zzgf arg3) {
        zzis.zzfc().zzg(this.getClass()).zzb(this, zzgh.zzb(arg3));
    }

    public final int zzdg() {
        if(this.zzsh == -1) {
            this.zzsh = zzis.zzfc().zzp(this).zzn(this);
        }

        return this.zzsh;
    }

    protected static zzhe zzdo() {
        return zzha.zzdy();
    }

    protected static zzhg zzdp() {
        return zzit.zzfd();
    }

    public final zzii zzdq() {
        Object v0 = this.zzb(zzh.zzsz, null, null);
        ((zzb)v0).zzb(this);
        return ((zzii)v0);
    }

    public final zzii zzdr() {
        return this.zzb(zzh.zzsz, null, null);
    }

    public final zzih zzds() {
        return this.zzb(zzh.zzta, null, null);
    }

    static zzgz zze(Class arg3) {
        Object v0 = zzgz.zzsi.get(arg3);
        if(v0 == null) {
            try {
                Class.forName(arg3.getName(), true, arg3.getClassLoader());
            }
            catch(ClassNotFoundException v3) {
                throw new IllegalStateException("Class initialization cannot fail.", ((Throwable)v3));
            }

            v0 = zzgz.zzsi.get(arg3);
        }

        if(v0 == null) {
            String v1 = "Unable to get default instance for: ";
            String v3_1 = String.valueOf(arg3.getName());
            v3_1 = v3_1.length() != 0 ? v1.concat(v3_1) : new String(v1);
            throw new IllegalStateException(v3_1);
        }

        return ((zzgz)v0);
    }

    final void zzv(int arg1) {
        this.zzsh = arg1;
    }
}

