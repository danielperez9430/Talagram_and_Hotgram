package com.google.android.gms.internal.measurement;

import java.lang.reflect.Field;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.AccessController;
import java.util.logging.Level;
import java.util.logging.Logger;
import libcore.io.Memory;
import sun.misc.Unsafe;

final class zzyh {
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
            ((zzd)this).zzb(arg1, arg2, Float.floatToIntBits(arg4));
        }

        public final void zza(Object arg2, long arg3, boolean arg5) {
            if(zzyh.zzvh()) {
                zzyh.zzd(arg2, arg3, arg5);
                return;
            }

            zzyh.zze(arg2, arg3, arg5);
        }

        public final void zza(byte[] arg3, long arg4, long arg6, long arg8) {
            Memory.pokeByteArray(((int)(arg6 & -1)), arg3, ((int)arg4), ((int)arg8));
        }

        public final void zze(Object arg2, long arg3, byte arg5) {
            if(zzyh.zzvh()) {
                zzyh.zzc(arg2, arg3, arg5);
                return;
            }

            zzyh.zzd(arg2, arg3, arg5);
        }

        public final boolean zzm(Object arg2, long arg3) {
            if(zzyh.zzvh()) {
                return zzyh.zzw(arg2, arg3);
            }

            return zzyh.zzx(arg2, arg3);
        }

        public final float zzn(Object arg1, long arg2) {
            return Float.intBitsToFloat(((zzd)this).zzk(arg1, arg2));
        }

        public final double zzo(Object arg1, long arg2) {
            return Double.longBitsToDouble(((zzd)this).zzl(arg1, arg2));
        }

        public final byte zzy(Object arg2, long arg3) {
            if(zzyh.zzvh()) {
                return zzyh.zzu(arg2, arg3);
            }

            return zzyh.zzv(arg2, arg3);
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
            ((zzd)this).zzb(arg1, arg2, Float.floatToIntBits(arg4));
        }

        public final void zza(Object arg2, long arg3, boolean arg5) {
            if(zzyh.zzvh()) {
                zzyh.zzd(arg2, arg3, arg5);
                return;
            }

            zzyh.zze(arg2, arg3, arg5);
        }

        public final void zza(byte[] arg1, long arg2, long arg4, long arg6) {
            Memory.pokeByteArray(arg4, arg1, ((int)arg2), ((int)arg6));
        }

        public final void zze(Object arg2, long arg3, byte arg5) {
            if(zzyh.zzvh()) {
                zzyh.zzc(arg2, arg3, arg5);
                return;
            }

            zzyh.zzd(arg2, arg3, arg5);
        }

        public final boolean zzm(Object arg2, long arg3) {
            if(zzyh.zzvh()) {
                return zzyh.zzw(arg2, arg3);
            }

            return zzyh.zzx(arg2, arg3);
        }

        public final float zzn(Object arg1, long arg2) {
            return Float.intBitsToFloat(((zzd)this).zzk(arg1, arg2));
        }

        public final double zzo(Object arg1, long arg2) {
            return Double.longBitsToDouble(((zzd)this).zzl(arg1, arg2));
        }

        public final byte zzy(Object arg2, long arg3) {
            if(zzyh.zzvh()) {
                return zzyh.zzu(arg2, arg3);
            }

            return zzyh.zzv(arg2, arg3);
        }
    }

    final class zzc extends zzd {
        zzc(Unsafe arg1) {
            super(arg1);
        }

        public final void zza(long arg2, byte arg4) {
            this.zzcdo.putByte(arg2, arg4);
        }

        public final void zza(Object arg7, long arg8, double arg10) {
            this.zzcdo.putDouble(arg7, arg8, arg10);
        }

        public final void zza(Object arg2, long arg3, float arg5) {
            this.zzcdo.putFloat(arg2, arg3, arg5);
        }

        public final void zza(Object arg2, long arg3, boolean arg5) {
            this.zzcdo.putBoolean(arg2, arg3, arg5);
        }

        public final void zza(byte[] arg11, long arg12, long arg14, long arg16) {
            this.zzcdo.copyMemory(arg11, zzyh.zzyo() + arg12, null, arg14, arg16);
        }

        public final void zze(Object arg2, long arg3, byte arg5) {
            this.zzcdo.putByte(arg2, arg3, arg5);
        }

        public final boolean zzm(Object arg2, long arg3) {
            return this.zzcdo.getBoolean(arg2, arg3);
        }

        public final float zzn(Object arg2, long arg3) {
            return this.zzcdo.getFloat(arg2, arg3);
        }

        public final double zzo(Object arg2, long arg3) {
            return this.zzcdo.getDouble(arg2, arg3);
        }

        public final byte zzy(Object arg2, long arg3) {
            return this.zzcdo.getByte(arg2, arg3);
        }
    }

    abstract class zzd {
        Unsafe zzcdo;

        zzd(Unsafe arg1) {
            super();
            this.zzcdo = arg1;
        }

        public abstract void zza(long arg1, byte arg2);

        public abstract void zza(Object arg1, long arg2, double arg3);

        public abstract void zza(Object arg1, long arg2, float arg3);

        public final void zza(Object arg7, long arg8, long arg10) {
            this.zzcdo.putLong(arg7, arg8, arg10);
        }

        public abstract void zza(Object arg1, long arg2, boolean arg3);

        public abstract void zza(byte[] arg1, long arg2, long arg3, long arg4);

        public final void zzb(Object arg2, long arg3, int arg5) {
            this.zzcdo.putInt(arg2, arg3, arg5);
        }

        public abstract void zze(Object arg1, long arg2, byte arg3);

        public final int zzk(Object arg2, long arg3) {
            return this.zzcdo.getInt(arg2, arg3);
        }

        public final long zzl(Object arg2, long arg3) {
            return this.zzcdo.getLong(arg2, arg3);
        }

        public abstract boolean zzm(Object arg1, long arg2);

        public abstract float zzn(Object arg1, long arg2);

        public abstract double zzo(Object arg1, long arg2);

        public abstract byte zzy(Object arg1, long arg2);
    }

    private static final Logger logger;
    private static final Class zzbtv;
    private static final boolean zzbuv;
    private static final Unsafe zzcay;
    private static final boolean zzccv;
    private static final boolean zzccw;
    private static final zzd zzccx;
    private static final boolean zzccy;
    private static final long zzccz;
    private static final long zzcda;
    private static final long zzcdb;
    private static final long zzcdc;
    private static final long zzcdd;
    private static final long zzcde;
    private static final long zzcdf;
    private static final long zzcdg;
    private static final long zzcdh;
    private static final long zzcdi;
    private static final long zzcdj;
    private static final long zzcdk;
    private static final long zzcdl;
    private static final long zzcdm;
    private static final boolean zzcdn;

    static {
        zza v1_2;
        zzyh.logger = Logger.getLogger(zzyh.class.getName());
        zzyh.zzcay = zzyh.zzyk();
        zzyh.zzbtv = zzua.zztz();
        zzyh.zzccv = zzyh.zzm(Long.TYPE);
        zzyh.zzccw = zzyh.zzm(Integer.TYPE);
        zzd v1 = null;
        if(zzyh.zzcay == null) {
        }
        else if(!zzua.zzty()) {
            zzc v1_3 = new zzc(zzyh.zzcay);
        }
        else if(zzyh.zzccv) {
            zzb v1_1 = new zzb(zzyh.zzcay);
        }
        else if(zzyh.zzccw) {
            v1_2 = new zza(zzyh.zzcay);
        }

        zzyh.zzccx = ((zzd)v1_2);
        zzyh.zzccy = zzyh.zzym();
        zzyh.zzbuv = zzyh.zzyl();
        zzyh.zzccz = ((long)zzyh.zzk(byte[].class));
        zzyh.zzcda = ((long)zzyh.zzk(boolean[].class));
        zzyh.zzcdb = ((long)zzyh.zzl(boolean[].class));
        zzyh.zzcdc = ((long)zzyh.zzk(int[].class));
        zzyh.zzcdd = ((long)zzyh.zzl(int[].class));
        zzyh.zzcde = ((long)zzyh.zzk(long[].class));
        zzyh.zzcdf = ((long)zzyh.zzl(long[].class));
        zzyh.zzcdg = ((long)zzyh.zzk(float[].class));
        zzyh.zzcdh = ((long)zzyh.zzl(float[].class));
        zzyh.zzcdi = ((long)zzyh.zzk(double[].class));
        zzyh.zzcdj = ((long)zzyh.zzl(double[].class));
        zzyh.zzcdk = ((long)zzyh.zzk(Object[].class));
        zzyh.zzcdl = ((long)zzyh.zzl(Object[].class));
        Field v0 = zzyh.zzyn();
        long v0_1 = v0 == null || zzyh.zzccx == null ? -1 : zzyh.zzccx.zzcdo.objectFieldOffset(v0);
        zzyh.zzcdm = v0_1;
        boolean v0_2 = ByteOrder.nativeOrder() == ByteOrder.BIG_ENDIAN ? true : false;
        zzyh.zzcdn = v0_2;
    }

    private zzyh() {
        super();
    }

    static void zza(Object arg1, long arg2, Object arg4) {
        zzyh.zzccx.zzcdo.putObject(arg1, arg2, arg4);
    }

    static byte zza(byte[] arg3, long arg4) {
        return zzyh.zzccx.zzy(arg3, zzyh.zzccz + arg4);
    }

    static void zza(long arg1, byte arg3) {
        zzyh.zzccx.zza(arg1, arg3);
    }

    private static void zza(Object arg4, long arg5, byte arg7) {
        long v0 = -4 & arg5;
        int v5 = (((((int)arg5)) ^ -1) & 3) << 3;
        zzyh.zzb(arg4, v0, (255 & arg7) << v5 | zzyh.zzk(arg4, v0) & (255 << v5 ^ -1));
    }

    static void zza(Object arg6, long arg7, double arg9) {
        zzyh.zzccx.zza(arg6, arg7, arg9);
    }

    static void zza(Object arg1, long arg2, float arg4) {
        zzyh.zzccx.zza(arg1, arg2, arg4);
    }

    static void zza(Object arg6, long arg7, long arg9) {
        zzyh.zzccx.zza(arg6, arg7, arg9);
    }

    static void zza(Object arg1, long arg2, boolean arg4) {
        zzyh.zzccx.zza(arg1, arg2, arg4);
    }

    static void zza(byte[] arg3, long arg4, byte arg6) {
        zzyh.zzccx.zze(arg3, zzyh.zzccz + arg4, arg6);
    }

    static void zza(byte[] arg8, long arg9, long arg11, long arg13) {
        zzyh.zzccx.zza(arg8, arg9, arg11, arg13);
    }

    static void zzb(Object arg1, long arg2, int arg4) {
        zzyh.zzccx.zzb(arg1, arg2, arg4);
    }

    static long zzb(ByteBuffer arg3) {
        return zzyh.zzccx.zzl(arg3, zzyh.zzcdm);
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
        zzyh.zzb(arg4, v0, (255 & arg7) << v5 | zzyh.zzk(arg4, v0) & (255 << v5 ^ -1));
    }

    private static void zzb(Object arg0, long arg1, boolean arg3) {
        zzyh.zza(arg0, arg1, ((byte)(((int)arg3))));
    }

    static void zzc(Object arg0, long arg1, byte arg3) {
        zzyh.zza(arg0, arg1, arg3);
    }

    private static void zzc(Object arg0, long arg1, boolean arg3) {
        zzyh.zzb(arg0, arg1, ((byte)(((int)arg3))));
    }

    static void zzd(Object arg0, long arg1, byte arg3) {
        zzyh.zzb(arg0, arg1, arg3);
    }

    static void zzd(Object arg0, long arg1, boolean arg3) {
        zzyh.zzb(arg0, arg1, arg3);
    }

    static void zze(Object arg0, long arg1, boolean arg3) {
        zzyh.zzc(arg0, arg1, arg3);
    }

    private static int zzk(Class arg1) {
        if(zzyh.zzbuv) {
            return zzyh.zzccx.zzcdo.arrayBaseOffset(arg1);
        }

        return -1;
    }

    static int zzk(Object arg1, long arg2) {
        return zzyh.zzccx.zzk(arg1, arg2);
    }

    private static int zzl(Class arg1) {
        if(zzyh.zzbuv) {
            return zzyh.zzccx.zzcdo.arrayIndexScale(arg1);
        }

        return -1;
    }

    static long zzl(Object arg1, long arg2) {
        return zzyh.zzccx.zzl(arg1, arg2);
    }

    private static boolean zzm(Class arg9) {
        if(!zzua.zzty()) {
            return 0;
        }

        try {
            Class v0 = zzyh.zzbtv;
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

    static boolean zzm(Object arg1, long arg2) {
        return zzyh.zzccx.zzm(arg1, arg2);
    }

    static float zzn(Object arg1, long arg2) {
        return zzyh.zzccx.zzn(arg1, arg2);
    }

    static double zzo(Object arg1, long arg2) {
        return zzyh.zzccx.zzo(arg1, arg2);
    }

    static Object zzp(Object arg1, long arg2) {
        return zzyh.zzccx.zzcdo.getObject(arg1, arg2);
    }

    private static byte zzq(Object arg2, long arg3) {
        return ((byte)(zzyh.zzk(arg2, -4 & arg3) >>> (((int)(((arg3 ^ -1) & 3) << 3)))));
    }

    private static byte zzr(Object arg2, long arg3) {
        return ((byte)(zzyh.zzk(arg2, -4 & arg3) >>> (((int)((arg3 & 3) << 3)))));
    }

    private static boolean zzs(Object arg0, long arg1) {
        if(zzyh.zzq(arg0, arg1) != 0) {
            return 1;
        }

        return 0;
    }

    private static boolean zzt(Object arg0, long arg1) {
        if(zzyh.zzr(arg0, arg1) != 0) {
            return 1;
        }

        return 0;
    }

    static byte zzu(Object arg0, long arg1) {
        return zzyh.zzq(arg0, arg1);
    }

    static byte zzv(Object arg0, long arg1) {
        return zzyh.zzr(arg0, arg1);
    }

    static boolean zzvh() {
        return zzyh.zzcdn;
    }

    static boolean zzw(Object arg0, long arg1) {
        return zzyh.zzs(arg0, arg1);
    }

    static boolean zzx(Object arg0, long arg1) {
        return zzyh.zzt(arg0, arg1);
    }

    static boolean zzyi() {
        return zzyh.zzbuv;
    }

    static boolean zzyj() {
        return zzyh.zzccy;
    }

    static Unsafe zzyk() {
        Unsafe v0_1;
        try {
            Object v0 = AccessController.doPrivileged(new zzyi());
        }
        catch(Throwable ) {
            v0_1 = null;
        }

        return v0_1;
    }

    private static boolean zzyl() {
        if(zzyh.zzcay == null) {
            return 0;
        }

        try {
            Class v0_1 = zzyh.zzcay.getClass();
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
            if(zzua.zzty()) {
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
            Logger v2 = zzyh.logger;
            Level v3 = Level.WARNING;
            String v0_2 = String.valueOf(v0);
            StringBuilder v7 = new StringBuilder(String.valueOf(v0_2).length() + 71);
            v7.append("platform method missing - proto runtime falling back to safer methods: ");
            v7.append(v0_2);
            v2.logp(v3, "com.google.protobuf.UnsafeUtil", "supportsUnsafeArrayOperations", v7.toString());
            return 0;
        }
    }

    private static boolean zzym() {
        if(zzyh.zzcay == null) {
            return 0;
        }

        try {
            Class v0_1 = zzyh.zzcay.getClass();
            v0_1.getMethod("objectFieldOffset", Field.class);
            int v4 = 2;
            v0_1.getMethod("getLong", Object.class, Long.TYPE);
            if(zzyh.zzyn() == null) {
                return 0;
            }

            if(zzua.zzty()) {
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
            Logger v2 = zzyh.logger;
            Level v3 = Level.WARNING;
            String v0_2 = String.valueOf(v0);
            StringBuilder v7 = new StringBuilder(String.valueOf(v0_2).length() + 71);
            v7.append("platform method missing - proto runtime falling back to safer methods: ");
            v7.append(v0_2);
            v2.logp(v3, "com.google.protobuf.UnsafeUtil", "supportsUnsafeByteBufferOperations", v7.toString());
            return 0;
        }
    }

    private static Field zzyn() {
        Field v0;
        if(zzua.zzty()) {
            v0 = zzyh.zzb(Buffer.class, "effectiveDirectAddress");
            if(v0 != null) {
                return v0;
            }
        }

        v0 = zzyh.zzb(Buffer.class, "address");
        if(v0 != null && v0.getType() == Long.TYPE) {
            return v0;
        }

        return null;
    }

    static long zzyo() {
        return zzyh.zzccz;
    }
}

