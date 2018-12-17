package com.google.android.gms.internal.vision;

import java.lang.reflect.Field;
import java.nio.Buffer;
import java.nio.ByteOrder;
import java.security.AccessController;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.misc.Unsafe;

final class zzfl {
    final class zza extends zzd {
        zza(Unsafe arg1) {
            super(arg1);
        }

        public final void zza(Object arg7, long arg8, double arg10) {
            this.zza(arg7, arg8, Double.doubleToLongBits(arg10));
        }

        public final void zza(Object arg1, long arg2, float arg4) {
            ((zzd)this).zza(arg1, arg2, Float.floatToIntBits(arg4));
        }

        public final void zza(Object arg2, long arg3, boolean arg5) {
            if(zzfl.zzbb()) {
                zzfl.zzd(arg2, arg3, arg5);
                return;
            }

            zzfl.zze(arg2, arg3, arg5);
        }

        public final void zze(Object arg2, long arg3, byte arg5) {
            if(zzfl.zzbb()) {
                zzfl.zzc(arg2, arg3, arg5);
                return;
            }

            zzfl.zzd(arg2, arg3, arg5);
        }

        public final boolean zzl(Object arg2, long arg3) {
            if(zzfl.zzbb()) {
                return zzfl.zzv(arg2, arg3);
            }

            return zzfl.zzw(arg2, arg3);
        }

        public final float zzm(Object arg1, long arg2) {
            return Float.intBitsToFloat(((zzd)this).zzj(arg1, arg2));
        }

        public final double zzn(Object arg1, long arg2) {
            return Double.longBitsToDouble(((zzd)this).zzk(arg1, arg2));
        }

        public final byte zzx(Object arg2, long arg3) {
            if(zzfl.zzbb()) {
                return zzfl.zzt(arg2, arg3);
            }

            return zzfl.zzu(arg2, arg3);
        }
    }

    final class zzb extends zzd {
        zzb(Unsafe arg1) {
            super(arg1);
        }

        public final void zza(Object arg7, long arg8, double arg10) {
            this.zza(arg7, arg8, Double.doubleToLongBits(arg10));
        }

        public final void zza(Object arg1, long arg2, float arg4) {
            ((zzd)this).zza(arg1, arg2, Float.floatToIntBits(arg4));
        }

        public final void zza(Object arg2, long arg3, boolean arg5) {
            if(zzfl.zzbb()) {
                zzfl.zzd(arg2, arg3, arg5);
                return;
            }

            zzfl.zze(arg2, arg3, arg5);
        }

        public final void zze(Object arg2, long arg3, byte arg5) {
            if(zzfl.zzbb()) {
                zzfl.zzc(arg2, arg3, arg5);
                return;
            }

            zzfl.zzd(arg2, arg3, arg5);
        }

        public final boolean zzl(Object arg2, long arg3) {
            if(zzfl.zzbb()) {
                return zzfl.zzv(arg2, arg3);
            }

            return zzfl.zzw(arg2, arg3);
        }

        public final float zzm(Object arg1, long arg2) {
            return Float.intBitsToFloat(((zzd)this).zzj(arg1, arg2));
        }

        public final double zzn(Object arg1, long arg2) {
            return Double.longBitsToDouble(((zzd)this).zzk(arg1, arg2));
        }

        public final byte zzx(Object arg2, long arg3) {
            if(zzfl.zzbb()) {
                return zzfl.zzt(arg2, arg3);
            }

            return zzfl.zzu(arg2, arg3);
        }
    }

    final class zzc extends zzd {
        zzc(Unsafe arg1) {
            super(arg1);
        }

        public final void zza(Object arg7, long arg8, double arg10) {
            this.zzpu.putDouble(arg7, arg8, arg10);
        }

        public final void zza(Object arg2, long arg3, float arg5) {
            this.zzpu.putFloat(arg2, arg3, arg5);
        }

        public final void zza(Object arg2, long arg3, boolean arg5) {
            this.zzpu.putBoolean(arg2, arg3, arg5);
        }

        public final void zze(Object arg2, long arg3, byte arg5) {
            this.zzpu.putByte(arg2, arg3, arg5);
        }

        public final boolean zzl(Object arg2, long arg3) {
            return this.zzpu.getBoolean(arg2, arg3);
        }

        public final float zzm(Object arg2, long arg3) {
            return this.zzpu.getFloat(arg2, arg3);
        }

        public final double zzn(Object arg2, long arg3) {
            return this.zzpu.getDouble(arg2, arg3);
        }

        public final byte zzx(Object arg2, long arg3) {
            return this.zzpu.getByte(arg2, arg3);
        }
    }

    abstract class zzd {
        Unsafe zzpu;

        zzd(Unsafe arg1) {
            super();
            this.zzpu = arg1;
        }

        public abstract void zza(Object arg1, long arg2, double arg3);

        public abstract void zza(Object arg1, long arg2, float arg3);

        public final void zza(Object arg2, long arg3, int arg5) {
            this.zzpu.putInt(arg2, arg3, arg5);
        }

        public final void zza(Object arg7, long arg8, long arg10) {
            this.zzpu.putLong(arg7, arg8, arg10);
        }

        public abstract void zza(Object arg1, long arg2, boolean arg3);

        public abstract void zze(Object arg1, long arg2, byte arg3);

        public final int zzj(Object arg2, long arg3) {
            return this.zzpu.getInt(arg2, arg3);
        }

        public final long zzk(Object arg2, long arg3) {
            return this.zzpu.getLong(arg2, arg3);
        }

        public abstract boolean zzl(Object arg1, long arg2);

        public abstract float zzm(Object arg1, long arg2);

        public abstract double zzn(Object arg1, long arg2);

        public abstract byte zzx(Object arg1, long arg2);
    }

    private static final Logger logger;
    private static final Class zzgm;
    private static final boolean zzhj;
    private static final Unsafe zznd;
    private static final boolean zzpa;
    private static final boolean zzpb;
    private static final zzd zzpc;
    private static final boolean zzpd;
    private static final long zzpe;
    private static final long zzpf;
    private static final long zzpg;
    private static final long zzph;
    private static final long zzpi;
    private static final long zzpj;
    private static final long zzpk;
    private static final long zzpl;
    private static final long zzpm;
    private static final long zzpn;
    private static final long zzpo;
    private static final long zzpp;
    private static final long zzpq;
    private static final long zzpr;
    private static final long zzps;
    private static final boolean zzpt;

    static {
        zzc v0_3;
        zzfl.logger = Logger.getLogger(zzfl.class.getName());
        zzfl.zznd = zzfl.zzdz();
        zzfl.zzgm = zzbj.zzar();
        zzfl.zzpa = zzfl.zzi(Long.TYPE);
        zzfl.zzpb = zzfl.zzi(Integer.TYPE);
        zzd v1 = null;
        if(zzfl.zznd == null) {
        label_17:
            zzd v0_2 = v1;
            goto label_36;
        label_33:
            v0_3 = new zzc(zzfl.zznd);
        }
        else if(!zzbj.zzaq()) {
            goto label_33;
        }
        else if(zzfl.zzpa) {
            zzb v0 = new zzb(zzfl.zznd);
        }
        else if(zzfl.zzpb) {
            zza v0_1 = new zza(zzfl.zznd);
        }
        else {
            goto label_17;
        }

    label_36:
        zzfl.zzpc = ((zzd)v0_3);
        zzfl.zzpd = zzfl.zzeb();
        zzfl.zzhj = zzfl.zzea();
        zzfl.zzpe = ((long)zzfl.zzg(byte[].class));
        zzfl.zzpf = ((long)zzfl.zzg(boolean[].class));
        zzfl.zzpg = ((long)zzfl.zzh(boolean[].class));
        zzfl.zzph = ((long)zzfl.zzg(int[].class));
        zzfl.zzpi = ((long)zzfl.zzh(int[].class));
        zzfl.zzpj = ((long)zzfl.zzg(long[].class));
        zzfl.zzpk = ((long)zzfl.zzh(long[].class));
        zzfl.zzpl = ((long)zzfl.zzg(float[].class));
        zzfl.zzpm = ((long)zzfl.zzh(float[].class));
        zzfl.zzpn = ((long)zzfl.zzg(double[].class));
        zzfl.zzpo = ((long)zzfl.zzh(double[].class));
        zzfl.zzpp = ((long)zzfl.zzg(Object[].class));
        zzfl.zzpq = ((long)zzfl.zzh(Object[].class));
        zzfl.zzpr = zzfl.zza(zzfl.zzec());
        Field v0_4 = zzfl.zzb(String.class, "value");
        if(v0_4 == null || v0_4.getType() != char[].class) {
            v0_4 = ((Field)v1);
        }
        else {
        }

        zzfl.zzps = zzfl.zza(v0_4);
        boolean v0_5 = ByteOrder.nativeOrder() == ByteOrder.BIG_ENDIAN ? true : false;
        zzfl.zzpt = v0_5;
    }

    private zzfl() {
        super();
    }

    static void zza(Object arg1, long arg2, Object arg4) {
        zzfl.zzpc.zzpu.putObject(arg1, arg2, arg4);
    }

    private static long zza(Field arg2) {
        if(arg2 != null) {
            if(zzfl.zzpc == null) {
            }
            else {
                return zzfl.zzpc.zzpu.objectFieldOffset(arg2);
            }
        }

        return -1;
    }

    static byte zza(byte[] arg3, long arg4) {
        return zzfl.zzpc.zzx(arg3, zzfl.zzpe + arg4);
    }

    private static void zza(Object arg4, long arg5, byte arg7) {
        long v0 = -4 & arg5;
        int v5 = (((((int)arg5)) ^ -1) & 3) << 3;
        zzfl.zza(arg4, v0, (255 & arg7) << v5 | zzfl.zzj(arg4, v0) & (255 << v5 ^ -1));
    }

    static void zza(Object arg1, long arg2, int arg4) {
        zzfl.zzpc.zza(arg1, arg2, arg4);
    }

    static void zza(Object arg6, long arg7, double arg9) {
        zzfl.zzpc.zza(arg6, arg7, arg9);
    }

    static void zza(Object arg1, long arg2, float arg4) {
        zzfl.zzpc.zza(arg1, arg2, arg4);
    }

    static void zza(Object arg6, long arg7, long arg9) {
        zzfl.zzpc.zza(arg6, arg7, arg9);
    }

    static void zza(Object arg1, long arg2, boolean arg4) {
        zzfl.zzpc.zza(arg1, arg2, arg4);
    }

    static void zza(byte[] arg3, long arg4, byte arg6) {
        zzfl.zzpc.zze(arg3, zzfl.zzpe + arg4, arg6);
    }

    private static Field zzb(Class arg0, String arg1) {
        Field v0;
        try {
            v0 = arg0.getDeclaredField(arg1);
            v0.setAccessible(true);
        }
        catch(Throwable ) {
            v0 = null;
        }

        return v0;
    }

    private static void zzb(Object arg4, long arg5, byte arg7) {
        long v0 = -4 & arg5;
        int v5 = ((((int)arg5)) & 3) << 3;
        zzfl.zza(arg4, v0, (255 & arg7) << v5 | zzfl.zzj(arg4, v0) & (255 << v5 ^ -1));
    }

    private static void zzb(Object arg0, long arg1, boolean arg3) {
        zzfl.zza(arg0, arg1, ((byte)(((int)arg3))));
    }

    static boolean zzbb() {
        return zzfl.zzpt;
    }

    static void zzc(Object arg0, long arg1, byte arg3) {
        zzfl.zza(arg0, arg1, arg3);
    }

    private static void zzc(Object arg0, long arg1, boolean arg3) {
        zzfl.zzb(arg0, arg1, ((byte)(((int)arg3))));
    }

    static void zzd(Object arg0, long arg1, byte arg3) {
        zzfl.zzb(arg0, arg1, arg3);
    }

    static void zzd(Object arg0, long arg1, boolean arg3) {
        zzfl.zzb(arg0, arg1, arg3);
    }

    static boolean zzdx() {
        return zzfl.zzhj;
    }

    static boolean zzdy() {
        return zzfl.zzpd;
    }

    static Unsafe zzdz() {
        Object v0;
        try {
            v0 = AccessController.doPrivileged(new zzfm());
        }
        catch(Throwable ) {
            Unsafe v0_1 = null;
        }

        return ((Unsafe)v0);
    }

    static void zze(Object arg0, long arg1, boolean arg3) {
        zzfl.zzc(arg0, arg1, arg3);
    }

    private static boolean zzea() {
        if(zzfl.zznd == null) {
            return 0;
        }

        try {
            Class v0_1 = zzfl.zznd.getClass();
            v0_1.getMethod("objectFieldOffset", Field.class);
            v0_1.getMethod("arrayBaseOffset", Class.class);
            v0_1.getMethod("arrayIndexScale", Class.class);
            int v4 = 2;
            v0_1.getMethod("getInt", Object.class, Long.TYPE);
            int v5_1 = 3;
            Class[] v6 = new Class[v5_1];
            v6[0] = Object.class;
            v6[1] = Long.TYPE;
            v6[v4] = Integer.TYPE;
            v0_1.getMethod("putInt", v6);
            v0_1.getMethod("getLong", Object.class, Long.TYPE);
            v6 = new Class[v5_1];
            v6[0] = Object.class;
            v6[1] = Long.TYPE;
            v6[v4] = Long.TYPE;
            v0_1.getMethod("putLong", v6);
            v0_1.getMethod("getObject", Object.class, Long.TYPE);
            v6 = new Class[v5_1];
            v6[0] = Object.class;
            v6[1] = Long.TYPE;
            v6[v4] = Object.class;
            v0_1.getMethod("putObject", v6);
            if(zzbj.zzaq()) {
                return 1;
            }

            v0_1.getMethod("getByte", Object.class, Long.TYPE);
            v6 = new Class[v5_1];
            v6[0] = Object.class;
            v6[1] = Long.TYPE;
            v6[v4] = Byte.TYPE;
            v0_1.getMethod("putByte", v6);
            v0_1.getMethod("getBoolean", Object.class, Long.TYPE);
            v6 = new Class[v5_1];
            v6[0] = Object.class;
            v6[1] = Long.TYPE;
            v6[v4] = Boolean.TYPE;
            v0_1.getMethod("putBoolean", v6);
            v0_1.getMethod("getFloat", Object.class, Long.TYPE);
            v6 = new Class[v5_1];
            v6[0] = Object.class;
            v6[1] = Long.TYPE;
            v6[v4] = Float.TYPE;
            v0_1.getMethod("putFloat", v6);
            v0_1.getMethod("getDouble", Object.class, Long.TYPE);
            v5 = new Class[v5_1];
            v5[0] = Object.class;
            v5[1] = Long.TYPE;
            v5[v4] = Double.TYPE;
            v0_1.getMethod("putDouble", v5);
            return 1;
        }
        catch(Throwable v0) {
            Logger v2 = zzfl.logger;
            Level v3 = Level.WARNING;
            String v0_2 = String.valueOf(v0);
            StringBuilder v7 = new StringBuilder(String.valueOf(v0_2).length() + 71);
            v7.append("platform method missing - proto runtime falling back to safer methods: ");
            v7.append(v0_2);
            v2.logp(v3, "com.google.protobuf.UnsafeUtil", "supportsUnsafeArrayOperations", v7.toString());
            return 0;
        }
    }

    private static boolean zzeb() {
        if(zzfl.zznd == null) {
            return 0;
        }

        try {
            Class v0_1 = zzfl.zznd.getClass();
            v0_1.getMethod("objectFieldOffset", Field.class);
            int v4 = 2;
            v0_1.getMethod("getLong", Object.class, Long.TYPE);
            if(zzfl.zzec() == null) {
                return 0;
            }

            if(zzbj.zzaq()) {
                return 1;
            }

            v0_1.getMethod("getByte", Long.TYPE);
            v0_1.getMethod("putByte", Long.TYPE, Byte.TYPE);
            v0_1.getMethod("getInt", Long.TYPE);
            v0_1.getMethod("putInt", Long.TYPE, Integer.TYPE);
            v0_1.getMethod("getLong", Long.TYPE);
            v0_1.getMethod("putLong", Long.TYPE, Long.TYPE);
            Class[] v6 = new Class[3];
            v6[0] = Long.TYPE;
            v6[1] = Long.TYPE;
            v6[v4] = Long.TYPE;
            v0_1.getMethod("copyMemory", v6);
            v6 = new Class[5];
            v6[0] = Object.class;
            v6[1] = Long.TYPE;
            v6[v4] = Object.class;
            v6[3] = Long.TYPE;
            v6[4] = Long.TYPE;
            v0_1.getMethod("copyMemory", v6);
            return 1;
        }
        catch(Throwable v0) {
            Logger v2 = zzfl.logger;
            Level v3 = Level.WARNING;
            String v0_2 = String.valueOf(v0);
            StringBuilder v7 = new StringBuilder(String.valueOf(v0_2).length() + 71);
            v7.append("platform method missing - proto runtime falling back to safer methods: ");
            v7.append(v0_2);
            v2.logp(v3, "com.google.protobuf.UnsafeUtil", "supportsUnsafeByteBufferOperations", v7.toString());
            return 0;
        }
    }

    private static Field zzec() {
        Field v0;
        if(zzbj.zzaq()) {
            v0 = zzfl.zzb(Buffer.class, "effectiveDirectAddress");
            if(v0 != null) {
                return v0;
            }
        }

        v0 = zzfl.zzb(Buffer.class, "address");
        if(v0 != null && v0.getType() == Long.TYPE) {
            return v0;
        }

        return null;
    }

    private static int zzg(Class arg1) {
        if(zzfl.zzhj) {
            return zzfl.zzpc.zzpu.arrayBaseOffset(arg1);
        }

        return -1;
    }

    private static int zzh(Class arg1) {
        if(zzfl.zzhj) {
            return zzfl.zzpc.zzpu.arrayIndexScale(arg1);
        }

        return -1;
    }

    private static boolean zzi(Class arg9) {
        if(!zzbj.zzaq()) {
            return 0;
        }

        try {
            Class v0 = zzfl.zzgm;
            v0.getMethod("peekLong", arg9, Boolean.TYPE);
            v0.getMethod("pokeLong", arg9, Long.TYPE, Boolean.TYPE);
            v0.getMethod("pokeInt", arg9, Integer.TYPE, Boolean.TYPE);
            v0.getMethod("peekInt", arg9, Boolean.TYPE);
            v0.getMethod("pokeByte", arg9, Byte.TYPE);
            v0.getMethod("peekByte", arg9);
            v0.getMethod("pokeByteArray", arg9, byte[].class, Integer.TYPE, Integer.TYPE);
            v0.getMethod("peekByteArray", arg9, byte[].class, Integer.TYPE, Integer.TYPE);
            return 1;
        }
        catch(Throwable ) {
            return 0;
        }
    }

    static int zzj(Object arg1, long arg2) {
        return zzfl.zzpc.zzj(arg1, arg2);
    }

    static long zzk(Object arg1, long arg2) {
        return zzfl.zzpc.zzk(arg1, arg2);
    }

    static boolean zzl(Object arg1, long arg2) {
        return zzfl.zzpc.zzl(arg1, arg2);
    }

    static float zzm(Object arg1, long arg2) {
        return zzfl.zzpc.zzm(arg1, arg2);
    }

    static double zzn(Object arg1, long arg2) {
        return zzfl.zzpc.zzn(arg1, arg2);
    }

    static Object zzo(Object arg1, long arg2) {
        return zzfl.zzpc.zzpu.getObject(arg1, arg2);
    }

    private static byte zzp(Object arg2, long arg3) {
        return ((byte)(zzfl.zzj(arg2, -4 & arg3) >>> (((int)(((arg3 ^ -1) & 3) << 3)))));
    }

    private static byte zzq(Object arg2, long arg3) {
        return ((byte)(zzfl.zzj(arg2, -4 & arg3) >>> (((int)((arg3 & 3) << 3)))));
    }

    private static boolean zzr(Object arg0, long arg1) {
        if(zzfl.zzp(arg0, arg1) != 0) {
            return 1;
        }

        return 0;
    }

    private static boolean zzs(Object arg0, long arg1) {
        if(zzfl.zzq(arg0, arg1) != 0) {
            return 1;
        }

        return 0;
    }

    static byte zzt(Object arg0, long arg1) {
        return zzfl.zzp(arg0, arg1);
    }

    static byte zzu(Object arg0, long arg1) {
        return zzfl.zzq(arg0, arg1);
    }

    static boolean zzv(Object arg0, long arg1) {
        return zzfl.zzr(arg0, arg1);
    }

    static boolean zzw(Object arg0, long arg1) {
        return zzfl.zzs(arg0, arg1);
    }
}

