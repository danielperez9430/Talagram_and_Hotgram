package com.google.android.gms.internal.clearcut;

import java.lang.reflect.Field;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.AccessController;
import java.util.logging.Level;
import java.util.logging.Logger;
import libcore.io.Memory;
import sun.misc.Unsafe;

final class zzfd {
    final class zza extends zzd {
        zza(Unsafe arg1) {
            super(arg1);
        }

        public final void zza(long arg3, byte arg5) {
            Memory.pokeByte(((int)(arg3 & -1)), arg5);
        }

        public final void zza(Object arg7, long arg8, double arg10) {
            this.zza(arg7, arg8, Double.doubleToLongBits(arg10));
        }

        public final void zza(Object arg1, long arg2, float arg4) {
            ((zzd)this).zza(arg1, arg2, Float.floatToIntBits(arg4));
        }

        public final void zza(Object arg2, long arg3, boolean arg5) {
            if(zzfd.zzah()) {
                zzfd.zzd(arg2, arg3, arg5);
                return;
            }

            zzfd.zze(arg2, arg3, arg5);
        }

        public final void zza(byte[] arg3, long arg4, long arg6, long arg8) {
            Memory.pokeByteArray(((int)(arg6 & -1)), arg3, ((int)arg4), ((int)arg8));
        }

        public final void zze(Object arg2, long arg3, byte arg5) {
            if(zzfd.zzah()) {
                zzfd.zzc(arg2, arg3, arg5);
                return;
            }

            zzfd.zzd(arg2, arg3, arg5);
        }

        public final boolean zzl(Object arg2, long arg3) {
            if(zzfd.zzah()) {
                return zzfd.zzv(arg2, arg3);
            }

            return zzfd.zzw(arg2, arg3);
        }

        public final float zzm(Object arg1, long arg2) {
            return Float.intBitsToFloat(((zzd)this).zzj(arg1, arg2));
        }

        public final double zzn(Object arg1, long arg2) {
            return Double.longBitsToDouble(((zzd)this).zzk(arg1, arg2));
        }

        public final byte zzx(Object arg2, long arg3) {
            if(zzfd.zzah()) {
                return zzfd.zzt(arg2, arg3);
            }

            return zzfd.zzu(arg2, arg3);
        }
    }

    final class zzb extends zzd {
        zzb(Unsafe arg1) {
            super(arg1);
        }

        public final void zza(long arg1, byte arg3) {
            Memory.pokeByte(arg1, arg3);
        }

        public final void zza(Object arg7, long arg8, double arg10) {
            this.zza(arg7, arg8, Double.doubleToLongBits(arg10));
        }

        public final void zza(Object arg1, long arg2, float arg4) {
            ((zzd)this).zza(arg1, arg2, Float.floatToIntBits(arg4));
        }

        public final void zza(Object arg2, long arg3, boolean arg5) {
            if(zzfd.zzah()) {
                zzfd.zzd(arg2, arg3, arg5);
                return;
            }

            zzfd.zze(arg2, arg3, arg5);
        }

        public final void zza(byte[] arg1, long arg2, long arg4, long arg6) {
            Memory.pokeByteArray(arg4, arg1, ((int)arg2), ((int)arg6));
        }

        public final void zze(Object arg2, long arg3, byte arg5) {
            if(zzfd.zzah()) {
                zzfd.zzc(arg2, arg3, arg5);
                return;
            }

            zzfd.zzd(arg2, arg3, arg5);
        }

        public final boolean zzl(Object arg2, long arg3) {
            if(zzfd.zzah()) {
                return zzfd.zzv(arg2, arg3);
            }

            return zzfd.zzw(arg2, arg3);
        }

        public final float zzm(Object arg1, long arg2) {
            return Float.intBitsToFloat(((zzd)this).zzj(arg1, arg2));
        }

        public final double zzn(Object arg1, long arg2) {
            return Double.longBitsToDouble(((zzd)this).zzk(arg1, arg2));
        }

        public final byte zzx(Object arg2, long arg3) {
            if(zzfd.zzah()) {
                return zzfd.zzt(arg2, arg3);
            }

            return zzfd.zzu(arg2, arg3);
        }
    }

    final class zzc extends zzd {
        zzc(Unsafe arg1) {
            super(arg1);
        }

        public final void zza(long arg2, byte arg4) {
            this.zzqa.putByte(arg2, arg4);
        }

        public final void zza(Object arg7, long arg8, double arg10) {
            this.zzqa.putDouble(arg7, arg8, arg10);
        }

        public final void zza(Object arg2, long arg3, float arg5) {
            this.zzqa.putFloat(arg2, arg3, arg5);
        }

        public final void zza(Object arg2, long arg3, boolean arg5) {
            this.zzqa.putBoolean(arg2, arg3, arg5);
        }

        public final void zza(byte[] arg11, long arg12, long arg14, long arg16) {
            this.zzqa.copyMemory(arg11, zzfd.zzej() + arg12, null, arg14, arg16);
        }

        public final void zze(Object arg2, long arg3, byte arg5) {
            this.zzqa.putByte(arg2, arg3, arg5);
        }

        public final boolean zzl(Object arg2, long arg3) {
            return this.zzqa.getBoolean(arg2, arg3);
        }

        public final float zzm(Object arg2, long arg3) {
            return this.zzqa.getFloat(arg2, arg3);
        }

        public final double zzn(Object arg2, long arg3) {
            return this.zzqa.getDouble(arg2, arg3);
        }

        public final byte zzx(Object arg2, long arg3) {
            return this.zzqa.getByte(arg2, arg3);
        }
    }

    abstract class zzd {
        Unsafe zzqa;

        zzd(Unsafe arg1) {
            super();
            this.zzqa = arg1;
        }

        public final long zza(Field arg3) {
            return this.zzqa.objectFieldOffset(arg3);
        }

        public abstract void zza(long arg1, byte arg2);

        public abstract void zza(Object arg1, long arg2, double arg3);

        public abstract void zza(Object arg1, long arg2, float arg3);

        public final void zza(Object arg2, long arg3, int arg5) {
            this.zzqa.putInt(arg2, arg3, arg5);
        }

        public final void zza(Object arg7, long arg8, long arg10) {
            this.zzqa.putLong(arg7, arg8, arg10);
        }

        public abstract void zza(Object arg1, long arg2, boolean arg3);

        public abstract void zza(byte[] arg1, long arg2, long arg3, long arg4);

        public abstract void zze(Object arg1, long arg2, byte arg3);

        public final int zzj(Object arg2, long arg3) {
            return this.zzqa.getInt(arg2, arg3);
        }

        public final long zzk(Object arg2, long arg3) {
            return this.zzqa.getLong(arg2, arg3);
        }

        public abstract boolean zzl(Object arg1, long arg2);

        public abstract float zzm(Object arg1, long arg2);

        public abstract double zzn(Object arg1, long arg2);

        public abstract byte zzx(Object arg1, long arg2);
    }

    private static final Logger logger;
    private static final Class zzfb;
    private static final boolean zzfy;
    private static final Unsafe zzmh;
    private static final boolean zzpg;
    private static final boolean zzph;
    private static final zzd zzpi;
    private static final boolean zzpj;
    private static final long zzpk;
    private static final long zzpl;
    private static final long zzpm;
    private static final long zzpn;
    private static final long zzpo;
    private static final long zzpp;
    private static final long zzpq;
    private static final long zzpr;
    private static final long zzps;
    private static final long zzpt;
    private static final long zzpu;
    private static final long zzpv;
    private static final long zzpw;
    private static final long zzpx;
    private static final long zzpy;
    private static final boolean zzpz;

    static {
        zzb v0;
        zzfd.logger = Logger.getLogger(zzfd.class.getName());
        zzfd.zzmh = zzfd.zzef();
        zzfd.zzfb = zzaw.zzy();
        zzfd.zzpg = zzfd.zzi(Long.TYPE);
        zzfd.zzph = zzfd.zzi(Integer.TYPE);
        zzd v1 = null;
        if(zzfd.zzmh == null) {
        label_17:
            zzd v0_2 = v1;
            goto label_36;
        label_33:
            zzc v0_3 = new zzc(zzfd.zzmh);
        }
        else if(!zzaw.zzx()) {
            goto label_33;
        }
        else if(zzfd.zzpg) {
            v0 = new zzb(zzfd.zzmh);
        }
        else if(zzfd.zzph) {
            zza v0_1 = new zza(zzfd.zzmh);
        }
        else {
            goto label_17;
        }

    label_36:
        zzfd.zzpi = ((zzd)v0);
        zzfd.zzpj = zzfd.zzeh();
        zzfd.zzfy = zzfd.zzeg();
        zzfd.zzpk = ((long)zzfd.zzg(byte[].class));
        zzfd.zzpl = ((long)zzfd.zzg(boolean[].class));
        zzfd.zzpm = ((long)zzfd.zzh(boolean[].class));
        zzfd.zzpn = ((long)zzfd.zzg(int[].class));
        zzfd.zzpo = ((long)zzfd.zzh(int[].class));
        zzfd.zzpp = ((long)zzfd.zzg(long[].class));
        zzfd.zzpq = ((long)zzfd.zzh(long[].class));
        zzfd.zzpr = ((long)zzfd.zzg(float[].class));
        zzfd.zzps = ((long)zzfd.zzh(float[].class));
        zzfd.zzpt = ((long)zzfd.zzg(double[].class));
        zzfd.zzpu = ((long)zzfd.zzh(double[].class));
        zzfd.zzpv = ((long)zzfd.zzg(Object[].class));
        zzfd.zzpw = ((long)zzfd.zzh(Object[].class));
        zzfd.zzpx = zzfd.zzb(zzfd.zzei());
        Field v0_4 = zzfd.zzb(String.class, "value");
        if(v0_4 == null || v0_4.getType() != char[].class) {
            v0_4 = ((Field)v1);
        }
        else {
        }

        zzfd.zzpy = zzfd.zzb(v0_4);
        boolean v0_5 = ByteOrder.nativeOrder() == ByteOrder.BIG_ENDIAN ? true : false;
        zzfd.zzpz = v0_5;
    }

    private zzfd() {
        super();
    }

    static void zza(Object arg1, long arg2, Object arg4) {
        zzfd.zzpi.zzqa.putObject(arg1, arg2, arg4);
    }

    static byte zza(byte[] arg3, long arg4) {
        return zzfd.zzpi.zzx(arg3, zzfd.zzpk + arg4);
    }

    static long zza(Field arg2) {
        return zzfd.zzpi.zza(arg2);
    }

    static void zza(long arg1, byte arg3) {
        zzfd.zzpi.zza(arg1, arg3);
    }

    private static void zza(Object arg4, long arg5, byte arg7) {
        long v0 = -4 & arg5;
        int v5 = (((((int)arg5)) ^ -1) & 3) << 3;
        zzfd.zza(arg4, v0, (255 & arg7) << v5 | zzfd.zzj(arg4, v0) & (255 << v5 ^ -1));
    }

    static void zza(Object arg1, long arg2, int arg4) {
        zzfd.zzpi.zza(arg1, arg2, arg4);
    }

    static void zza(Object arg6, long arg7, double arg9) {
        zzfd.zzpi.zza(arg6, arg7, arg9);
    }

    static void zza(Object arg1, long arg2, float arg4) {
        zzfd.zzpi.zza(arg1, arg2, arg4);
    }

    static void zza(Object arg6, long arg7, long arg9) {
        zzfd.zzpi.zza(arg6, arg7, arg9);
    }

    static void zza(Object arg1, long arg2, boolean arg4) {
        zzfd.zzpi.zza(arg1, arg2, arg4);
    }

    static void zza(byte[] arg3, long arg4, byte arg6) {
        zzfd.zzpi.zze(arg3, zzfd.zzpk + arg4, arg6);
    }

    static void zza(byte[] arg8, long arg9, long arg11, long arg13) {
        zzfd.zzpi.zza(arg8, arg9, arg11, arg13);
    }

    static boolean zzah() {
        return zzfd.zzpz;
    }

    private static long zzb(Field arg2) {
        if(arg2 != null) {
            if(zzfd.zzpi == null) {
            }
            else {
                return zzfd.zzpi.zza(arg2);
            }
        }

        return -1;
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

    static long zzb(ByteBuffer arg3) {
        return zzfd.zzpi.zzk(arg3, zzfd.zzpx);
    }

    private static void zzb(Object arg4, long arg5, byte arg7) {
        long v0 = -4 & arg5;
        int v5 = ((((int)arg5)) & 3) << 3;
        zzfd.zza(arg4, v0, (255 & arg7) << v5 | zzfd.zzj(arg4, v0) & (255 << v5 ^ -1));
    }

    private static void zzb(Object arg0, long arg1, boolean arg3) {
        zzfd.zza(arg0, arg1, ((byte)(((int)arg3))));
    }

    static void zzc(Object arg0, long arg1, byte arg3) {
        zzfd.zza(arg0, arg1, arg3);
    }

    private static void zzc(Object arg0, long arg1, boolean arg3) {
        zzfd.zzb(arg0, arg1, ((byte)(((int)arg3))));
    }

    static void zzd(Object arg0, long arg1, byte arg3) {
        zzfd.zzb(arg0, arg1, arg3);
    }

    static void zzd(Object arg0, long arg1, boolean arg3) {
        zzfd.zzb(arg0, arg1, arg3);
    }

    static void zze(Object arg0, long arg1, boolean arg3) {
        zzfd.zzc(arg0, arg1, arg3);
    }

    static boolean zzed() {
        return zzfd.zzfy;
    }

    static boolean zzee() {
        return zzfd.zzpj;
    }

    static Unsafe zzef() {
        Object v0;
        try {
            v0 = AccessController.doPrivileged(new zzfe());
        }
        catch(Throwable ) {
            Unsafe v0_1 = null;
        }

        return ((Unsafe)v0);
    }

    private static boolean zzeg() {
        if(zzfd.zzmh == null) {
            return 0;
        }

        try {
            Class v0_1 = zzfd.zzmh.getClass();
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
            if(zzaw.zzx()) {
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
            Logger v2 = zzfd.logger;
            Level v3 = Level.WARNING;
            String v0_2 = String.valueOf(v0);
            StringBuilder v7 = new StringBuilder(String.valueOf(v0_2).length() + 71);
            v7.append("platform method missing - proto runtime falling back to safer methods: ");
            v7.append(v0_2);
            v2.logp(v3, "com.google.protobuf.UnsafeUtil", "supportsUnsafeArrayOperations", v7.toString());
            return 0;
        }
    }

    private static boolean zzeh() {
        if(zzfd.zzmh == null) {
            return 0;
        }

        try {
            Class v0_1 = zzfd.zzmh.getClass();
            v0_1.getMethod("objectFieldOffset", Field.class);
            int v4 = 2;
            v0_1.getMethod("getLong", Object.class, Long.TYPE);
            if(zzfd.zzei() == null) {
                return 0;
            }

            if(zzaw.zzx()) {
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
            Logger v2 = zzfd.logger;
            Level v3 = Level.WARNING;
            String v0_2 = String.valueOf(v0);
            StringBuilder v7 = new StringBuilder(String.valueOf(v0_2).length() + 71);
            v7.append("platform method missing - proto runtime falling back to safer methods: ");
            v7.append(v0_2);
            v2.logp(v3, "com.google.protobuf.UnsafeUtil", "supportsUnsafeByteBufferOperations", v7.toString());
            return 0;
        }
    }

    private static Field zzei() {
        Field v0;
        if(zzaw.zzx()) {
            v0 = zzfd.zzb(Buffer.class, "effectiveDirectAddress");
            if(v0 != null) {
                return v0;
            }
        }

        v0 = zzfd.zzb(Buffer.class, "address");
        if(v0 != null && v0.getType() == Long.TYPE) {
            return v0;
        }

        return null;
    }

    static long zzej() {
        return zzfd.zzpk;
    }

    private static int zzg(Class arg1) {
        if(zzfd.zzfy) {
            return zzfd.zzpi.zzqa.arrayBaseOffset(arg1);
        }

        return -1;
    }

    private static int zzh(Class arg1) {
        if(zzfd.zzfy) {
            return zzfd.zzpi.zzqa.arrayIndexScale(arg1);
        }

        return -1;
    }

    private static boolean zzi(Class arg9) {
        if(!zzaw.zzx()) {
            return 0;
        }

        try {
            Class v0 = zzfd.zzfb;
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
        return zzfd.zzpi.zzj(arg1, arg2);
    }

    static long zzk(Object arg1, long arg2) {
        return zzfd.zzpi.zzk(arg1, arg2);
    }

    static boolean zzl(Object arg1, long arg2) {
        return zzfd.zzpi.zzl(arg1, arg2);
    }

    static float zzm(Object arg1, long arg2) {
        return zzfd.zzpi.zzm(arg1, arg2);
    }

    static double zzn(Object arg1, long arg2) {
        return zzfd.zzpi.zzn(arg1, arg2);
    }

    static Object zzo(Object arg1, long arg2) {
        return zzfd.zzpi.zzqa.getObject(arg1, arg2);
    }

    private static byte zzp(Object arg2, long arg3) {
        return ((byte)(zzfd.zzj(arg2, -4 & arg3) >>> (((int)(((arg3 ^ -1) & 3) << 3)))));
    }

    private static byte zzq(Object arg2, long arg3) {
        return ((byte)(zzfd.zzj(arg2, -4 & arg3) >>> (((int)((arg3 & 3) << 3)))));
    }

    private static boolean zzr(Object arg0, long arg1) {
        if(zzfd.zzp(arg0, arg1) != 0) {
            return 1;
        }

        return 0;
    }

    private static boolean zzs(Object arg0, long arg1) {
        if(zzfd.zzq(arg0, arg1) != 0) {
            return 1;
        }

        return 0;
    }

    static byte zzt(Object arg0, long arg1) {
        return zzfd.zzp(arg0, arg1);
    }

    static byte zzu(Object arg0, long arg1) {
        return zzfd.zzq(arg0, arg1);
    }

    static boolean zzv(Object arg0, long arg1) {
        return zzfd.zzr(arg0, arg1);
    }

    static boolean zzw(Object arg0, long arg1) {
        return zzfd.zzs(arg0, arg1);
    }
}

