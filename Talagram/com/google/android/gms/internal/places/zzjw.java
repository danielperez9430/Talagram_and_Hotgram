package com.google.android.gms.internal.places;

import java.lang.reflect.Field;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.AccessController;
import java.util.logging.Level;
import java.util.logging.Logger;
import libcore.io.Memory;
import sun.misc.Unsafe;

final class zzjw {
    final class zzb extends zze {
        zzb(Unsafe arg1) {
            super(arg1);
        }

        public final void zzb(long arg3, byte arg5) {
            Memory.pokeByte(((int)(arg3 & -1)), arg5);
        }

        public final void zzb(Object arg7, long arg8, double arg10) {
            this.zzb(arg7, arg8, Double.doubleToLongBits(arg10));
        }

        public final void zzb(Object arg1, long arg2, float arg4) {
            ((zze)this).zzc(arg1, arg2, Float.floatToIntBits(arg4));
        }

        public final void zzb(Object arg2, long arg3, boolean arg5) {
            if(zzjw.zzct()) {
                zzjw.zze(arg2, arg3, arg5);
                return;
            }

            zzjw.zzf(arg2, arg3, arg5);
        }

        public final void zzb(byte[] arg3, long arg4, long arg6, long arg8) {
            Memory.pokeByteArray(((int)(arg6 & -1)), arg3, ((int)arg4), ((int)arg8));
        }

        public final void zzf(Object arg2, long arg3, byte arg5) {
            if(zzjw.zzct()) {
                zzjw.zzd(arg2, arg3, arg5);
                return;
            }

            zzjw.zze(arg2, arg3, arg5);
        }

        public final boolean zzn(Object arg2, long arg3) {
            if(zzjw.zzct()) {
                return zzjw.zzx(arg2, arg3);
            }

            return zzjw.zzy(arg2, arg3);
        }

        public final float zzo(Object arg1, long arg2) {
            return Float.intBitsToFloat(((zze)this).zzl(arg1, arg2));
        }

        public final double zzp(Object arg1, long arg2) {
            return Double.longBitsToDouble(((zze)this).zzm(arg1, arg2));
        }

        public final byte zzz(Object arg2, long arg3) {
            if(zzjw.zzct()) {
                return zzjw.zzv(arg2, arg3);
            }

            return zzjw.zzw(arg2, arg3);
        }
    }

    final class zzc extends zze {
        zzc(Unsafe arg1) {
            super(arg1);
        }

        public final void zzb(long arg1, byte arg3) {
            Memory.pokeByte(arg1, arg3);
        }

        public final void zzb(Object arg7, long arg8, double arg10) {
            this.zzb(arg7, arg8, Double.doubleToLongBits(arg10));
        }

        public final void zzb(Object arg1, long arg2, float arg4) {
            ((zze)this).zzc(arg1, arg2, Float.floatToIntBits(arg4));
        }

        public final void zzb(Object arg2, long arg3, boolean arg5) {
            if(zzjw.zzct()) {
                zzjw.zze(arg2, arg3, arg5);
                return;
            }

            zzjw.zzf(arg2, arg3, arg5);
        }

        public final void zzb(byte[] arg1, long arg2, long arg4, long arg6) {
            Memory.pokeByteArray(arg4, arg1, ((int)arg2), ((int)arg6));
        }

        public final void zzf(Object arg2, long arg3, byte arg5) {
            if(zzjw.zzct()) {
                zzjw.zzd(arg2, arg3, arg5);
                return;
            }

            zzjw.zze(arg2, arg3, arg5);
        }

        public final boolean zzn(Object arg2, long arg3) {
            if(zzjw.zzct()) {
                return zzjw.zzx(arg2, arg3);
            }

            return zzjw.zzy(arg2, arg3);
        }

        public final float zzo(Object arg1, long arg2) {
            return Float.intBitsToFloat(((zze)this).zzl(arg1, arg2));
        }

        public final double zzp(Object arg1, long arg2) {
            return Double.longBitsToDouble(((zze)this).zzm(arg1, arg2));
        }

        public final byte zzz(Object arg2, long arg3) {
            if(zzjw.zzct()) {
                return zzjw.zzv(arg2, arg3);
            }

            return zzjw.zzw(arg2, arg3);
        }
    }

    final class zzd extends zze {
        zzd(Unsafe arg1) {
            super(arg1);
        }

        public final void zzb(long arg2, byte arg4) {
            this.zzyr.putByte(arg2, arg4);
        }

        public final void zzb(Object arg7, long arg8, double arg10) {
            this.zzyr.putDouble(arg7, arg8, arg10);
        }

        public final void zzb(Object arg2, long arg3, float arg5) {
            this.zzyr.putFloat(arg2, arg3, arg5);
        }

        public final void zzb(Object arg2, long arg3, boolean arg5) {
            this.zzyr.putBoolean(arg2, arg3, arg5);
        }

        public final void zzb(byte[] arg11, long arg12, long arg14, long arg16) {
            this.zzyr.copyMemory(arg11, zzjw.zzgy() + arg12, null, arg14, arg16);
        }

        public final void zzf(Object arg2, long arg3, byte arg5) {
            this.zzyr.putByte(arg2, arg3, arg5);
        }

        public final boolean zzn(Object arg2, long arg3) {
            return this.zzyr.getBoolean(arg2, arg3);
        }

        public final float zzo(Object arg2, long arg3) {
            return this.zzyr.getFloat(arg2, arg3);
        }

        public final double zzp(Object arg2, long arg3) {
            return this.zzyr.getDouble(arg2, arg3);
        }

        public final byte zzz(Object arg2, long arg3) {
            return this.zzyr.getByte(arg2, arg3);
        }
    }

    abstract class zze {
        Unsafe zzyr;

        zze(Unsafe arg1) {
            super();
            this.zzyr = arg1;
        }

        public final long zzb(Field arg3) {
            return this.zzyr.objectFieldOffset(arg3);
        }

        public abstract void zzb(long arg1, byte arg2);

        public abstract void zzb(Object arg1, long arg2, double arg3);

        public abstract void zzb(Object arg1, long arg2, float arg3);

        public final void zzb(Object arg7, long arg8, long arg10) {
            this.zzyr.putLong(arg7, arg8, arg10);
        }

        public abstract void zzb(Object arg1, long arg2, boolean arg3);

        public abstract void zzb(byte[] arg1, long arg2, long arg3, long arg4);

        public final void zzc(Object arg2, long arg3, int arg5) {
            this.zzyr.putInt(arg2, arg3, arg5);
        }

        public abstract void zzf(Object arg1, long arg2, byte arg3);

        public final int zzl(Object arg2, long arg3) {
            return this.zzyr.getInt(arg2, arg3);
        }

        public final long zzm(Object arg2, long arg3) {
            return this.zzyr.getLong(arg2, arg3);
        }

        public abstract boolean zzn(Object arg1, long arg2);

        public abstract float zzo(Object arg1, long arg2);

        public abstract double zzp(Object arg1, long arg2);

        public abstract byte zzz(Object arg1, long arg2);
    }

    private static final Logger logger;
    private static final Class zznl;
    private static final boolean zzoo;
    private static final Unsafe zzuz;
    private static final boolean zzxx;
    private static final boolean zzxy;
    private static final zze zzxz;
    private static final boolean zzya;
    private static final long zzyb;
    private static final long zzyc;
    private static final long zzyd;
    private static final long zzye;
    private static final long zzyf;
    private static final long zzyg;
    private static final long zzyh;
    private static final long zzyi;
    private static final long zzyj;
    private static final long zzyk;
    private static final long zzyl;
    private static final long zzym;
    private static final long zzyn;
    private static final long zzyo;
    private static final long zzyp;
    private static final boolean zzyq;

    static {
        zze v0_2;
        zzjw.logger = Logger.getLogger(zzjw.class.getName());
        zzjw.zzuz = zzjw.zzgu();
        zzjw.zznl = zzfl.zzbe();
        zzjw.zzxx = zzjw.zzk(Long.TYPE);
        zzjw.zzxy = zzjw.zzk(Integer.TYPE);
        zze v1 = null;
        if(zzjw.zzuz == null) {
        label_17:
            v0_2 = v1;
            goto label_36;
        label_33:
            zzd v0_3 = new zzd(zzjw.zzuz);
        }
        else if(!zzfl.zzbd()) {
            goto label_33;
        }
        else if(zzjw.zzxx) {
            zzc v0 = new zzc(zzjw.zzuz);
        }
        else if(zzjw.zzxy) {
            zzb v0_1 = new zzb(zzjw.zzuz);
        }
        else {
            goto label_17;
        }

    label_36:
        zzjw.zzxz = v0_2;
        zzjw.zzya = zzjw.zzgw();
        zzjw.zzoo = zzjw.zzgv();
        zzjw.zzyb = ((long)zzjw.zzi(byte[].class));
        zzjw.zzyc = ((long)zzjw.zzi(boolean[].class));
        zzjw.zzyd = ((long)zzjw.zzj(boolean[].class));
        zzjw.zzye = ((long)zzjw.zzi(int[].class));
        zzjw.zzyf = ((long)zzjw.zzj(int[].class));
        zzjw.zzyg = ((long)zzjw.zzi(long[].class));
        zzjw.zzyh = ((long)zzjw.zzj(long[].class));
        zzjw.zzyi = ((long)zzjw.zzi(float[].class));
        zzjw.zzyj = ((long)zzjw.zzj(float[].class));
        zzjw.zzyk = ((long)zzjw.zzi(double[].class));
        zzjw.zzyl = ((long)zzjw.zzj(double[].class));
        zzjw.zzym = ((long)zzjw.zzi(Object[].class));
        zzjw.zzyn = ((long)zzjw.zzj(Object[].class));
        zzjw.zzyo = zzjw.zzc(zzjw.zzgx());
        Field v0_4 = zzjw.zzc(String.class, "value");
        if(v0_4 == null || v0_4.getType() != char[].class) {
            v0_4 = ((Field)v1);
        }
        else {
        }

        zzjw.zzyp = zzjw.zzc(v0_4);
        boolean v0_5 = ByteOrder.nativeOrder() == ByteOrder.BIG_ENDIAN ? true : false;
        zzjw.zzyq = v0_5;
    }

    private zzjw() {
        super();
    }

    static void zzb(Object arg1, long arg2, Object arg4) {
        zzjw.zzxz.zzyr.putObject(arg1, arg2, arg4);
    }

    static byte zzb(byte[] arg3, long arg4) {
        return zzjw.zzxz.zzz(arg3, zzjw.zzyb + arg4);
    }

    static long zzb(Field arg2) {
        return zzjw.zzxz.zzb(arg2);
    }

    static void zzb(long arg1, byte arg3) {
        zzjw.zzxz.zzb(arg1, arg3);
    }

    private static void zzb(Object arg4, long arg5, byte arg7) {
        long v0 = -4 & arg5;
        int v5 = (((((int)arg5)) ^ -1) & 3) << 3;
        zzjw.zzc(arg4, v0, (255 & arg7) << v5 | zzjw.zzl(arg4, v0) & (255 << v5 ^ -1));
    }

    static void zzb(Object arg6, long arg7, double arg9) {
        zzjw.zzxz.zzb(arg6, arg7, arg9);
    }

    static void zzb(Object arg1, long arg2, float arg4) {
        zzjw.zzxz.zzb(arg1, arg2, arg4);
    }

    static void zzb(Object arg6, long arg7, long arg9) {
        zzjw.zzxz.zzb(arg6, arg7, arg9);
    }

    static void zzb(Object arg1, long arg2, boolean arg4) {
        zzjw.zzxz.zzb(arg1, arg2, arg4);
    }

    static void zzb(byte[] arg3, long arg4, byte arg6) {
        zzjw.zzxz.zzf(arg3, zzjw.zzyb + arg4, arg6);
    }

    static void zzb(byte[] arg8, long arg9, long arg11, long arg13) {
        zzjw.zzxz.zzb(arg8, arg9, arg11, arg13);
    }

    private static long zzc(Field arg2) {
        if(arg2 != null) {
            if(zzjw.zzxz == null) {
            }
            else {
                return zzjw.zzxz.zzb(arg2);
            }
        }

        return -1;
    }

    private static Field zzc(Class arg0, String arg1) {
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

    static void zzc(Object arg1, long arg2, int arg4) {
        zzjw.zzxz.zzc(arg1, arg2, arg4);
    }

    static long zzc(ByteBuffer arg3) {
        return zzjw.zzxz.zzm(arg3, zzjw.zzyo);
    }

    private static void zzc(Object arg4, long arg5, byte arg7) {
        long v0 = -4 & arg5;
        int v5 = ((((int)arg5)) & 3) << 3;
        zzjw.zzc(arg4, v0, (255 & arg7) << v5 | zzjw.zzl(arg4, v0) & (255 << v5 ^ -1));
    }

    private static void zzc(Object arg0, long arg1, boolean arg3) {
        zzjw.zzb(arg0, arg1, ((byte)(((int)arg3))));
    }

    static boolean zzct() {
        return zzjw.zzyq;
    }

    static void zzd(Object arg0, long arg1, byte arg3) {
        zzjw.zzb(arg0, arg1, arg3);
    }

    private static void zzd(Object arg0, long arg1, boolean arg3) {
        zzjw.zzc(arg0, arg1, ((byte)(((int)arg3))));
    }

    static void zze(Object arg0, long arg1, byte arg3) {
        zzjw.zzc(arg0, arg1, arg3);
    }

    static void zze(Object arg0, long arg1, boolean arg3) {
        zzjw.zzc(arg0, arg1, arg3);
    }

    static void zzf(Object arg0, long arg1, boolean arg3) {
        zzjw.zzd(arg0, arg1, arg3);
    }

    static boolean zzgs() {
        return zzjw.zzoo;
    }

    static boolean zzgt() {
        return zzjw.zzya;
    }

    static Unsafe zzgu() {
        Object v0;
        try {
            v0 = AccessController.doPrivileged(new zzjx());
        }
        catch(Throwable ) {
            Unsafe v0_1 = null;
        }

        return ((Unsafe)v0);
    }

    private static boolean zzgv() {
        if(zzjw.zzuz == null) {
            return 0;
        }

        try {
            Class v0_1 = zzjw.zzuz.getClass();
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
            if(zzfl.zzbd()) {
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
            Logger v2 = zzjw.logger;
            Level v3 = Level.WARNING;
            String v0_2 = String.valueOf(v0);
            StringBuilder v7 = new StringBuilder(String.valueOf(v0_2).length() + 71);
            v7.append("platform method missing - proto runtime falling back to safer methods: ");
            v7.append(v0_2);
            v2.logp(v3, "com.google.protobuf.UnsafeUtil", "supportsUnsafeArrayOperations", v7.toString());
            return 0;
        }
    }

    private static boolean zzgw() {
        if(zzjw.zzuz == null) {
            return 0;
        }

        try {
            Class v0_1 = zzjw.zzuz.getClass();
            v0_1.getMethod("objectFieldOffset", Field.class);
            int v4 = 2;
            v0_1.getMethod("getLong", Object.class, Long.TYPE);
            if(zzjw.zzgx() == null) {
                return 0;
            }

            if(zzfl.zzbd()) {
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
            Logger v2 = zzjw.logger;
            Level v3 = Level.WARNING;
            String v0_2 = String.valueOf(v0);
            StringBuilder v7 = new StringBuilder(String.valueOf(v0_2).length() + 71);
            v7.append("platform method missing - proto runtime falling back to safer methods: ");
            v7.append(v0_2);
            v2.logp(v3, "com.google.protobuf.UnsafeUtil", "supportsUnsafeByteBufferOperations", v7.toString());
            return 0;
        }
    }

    private static Field zzgx() {
        Field v0;
        if(zzfl.zzbd()) {
            v0 = zzjw.zzc(Buffer.class, "effectiveDirectAddress");
            if(v0 != null) {
                return v0;
            }
        }

        v0 = zzjw.zzc(Buffer.class, "address");
        if(v0 != null && v0.getType() == Long.TYPE) {
            return v0;
        }

        return null;
    }

    static long zzgy() {
        return zzjw.zzyb;
    }

    private static int zzi(Class arg1) {
        if(zzjw.zzoo) {
            return zzjw.zzxz.zzyr.arrayBaseOffset(arg1);
        }

        return -1;
    }

    private static int zzj(Class arg1) {
        if(zzjw.zzoo) {
            return zzjw.zzxz.zzyr.arrayIndexScale(arg1);
        }

        return -1;
    }

    private static boolean zzk(Class arg9) {
        if(!zzfl.zzbd()) {
            return 0;
        }

        try {
            Class v0 = zzjw.zznl;
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

    static int zzl(Object arg1, long arg2) {
        return zzjw.zzxz.zzl(arg1, arg2);
    }

    static long zzm(Object arg1, long arg2) {
        return zzjw.zzxz.zzm(arg1, arg2);
    }

    static boolean zzn(Object arg1, long arg2) {
        return zzjw.zzxz.zzn(arg1, arg2);
    }

    static float zzo(Object arg1, long arg2) {
        return zzjw.zzxz.zzo(arg1, arg2);
    }

    static double zzp(Object arg1, long arg2) {
        return zzjw.zzxz.zzp(arg1, arg2);
    }

    static Object zzq(Object arg1, long arg2) {
        return zzjw.zzxz.zzyr.getObject(arg1, arg2);
    }

    private static byte zzr(Object arg2, long arg3) {
        return ((byte)(zzjw.zzl(arg2, -4 & arg3) >>> (((int)(((arg3 ^ -1) & 3) << 3)))));
    }

    private static byte zzs(Object arg2, long arg3) {
        return ((byte)(zzjw.zzl(arg2, -4 & arg3) >>> (((int)((arg3 & 3) << 3)))));
    }

    private static boolean zzt(Object arg0, long arg1) {
        if(zzjw.zzr(arg0, arg1) != 0) {
            return 1;
        }

        return 0;
    }

    private static boolean zzu(Object arg0, long arg1) {
        if(zzjw.zzs(arg0, arg1) != 0) {
            return 1;
        }

        return 0;
    }

    static byte zzv(Object arg0, long arg1) {
        return zzjw.zzr(arg0, arg1);
    }

    static byte zzw(Object arg0, long arg1) {
        return zzjw.zzs(arg0, arg1);
    }

    static boolean zzx(Object arg0, long arg1) {
        return zzjw.zzt(arg0, arg1);
    }

    static boolean zzy(Object arg0, long arg1) {
        return zzjw.zzu(arg0, arg1);
    }
}

