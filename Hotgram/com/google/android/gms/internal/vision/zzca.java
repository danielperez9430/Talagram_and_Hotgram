package com.google.android.gms.internal.vision;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class zzca extends zzbn {
    final class zza extends zzca {
        private final byte[] buffer;
        private final int limit;
        private final int offset;
        private int position;

        zza(byte[] arg3, int arg4, int arg5) {
            super(null);
            if(arg3 != null) {
                int v1 = arg5;
                if((arg5 | 0 | arg3.length - v1) >= 0) {
                    this.buffer = arg3;
                    this.offset = 0;
                    this.position = 0;
                    this.limit = v1;
                    return;
                }

                throw new IllegalArgumentException(String.format("Array range is invalid. Buffer.length=%d, offset=%d, length=%d", Integer.valueOf(arg3.length), Integer.valueOf(0), Integer.valueOf(arg5)));
            }

            throw new NullPointerException("buffer");
        }

        private final void write(byte[] arg4, int arg5, int arg6) {
            try {
                System.arraycopy(arg4, arg5, this.buffer, this.position, arg6);
                this.position += arg6;
                return;
            }
            catch(IndexOutOfBoundsException v4) {
                throw new zzb(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.position), Integer.valueOf(this.limit), Integer.valueOf(arg6)), ((Throwable)v4));
            }
        }

        public final void zza(byte arg5) {
            try {
                byte[] v0 = this.buffer;
                int v1 = this.position;
                this.position = v1 + 1;
                v0[v1] = arg5;
                return;
            }
            catch(IndexOutOfBoundsException v5) {
                throw new zzb(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.position), Integer.valueOf(this.limit), Integer.valueOf(1)), ((Throwable)v5));
            }
        }

        public final void zza(int arg2, long arg3) {
            ((zzca)this).zzd(arg2, 0);
            ((zzca)this).zzb(arg3);
        }

        public final void zza(int arg2, zzbo arg3) {
            ((zzca)this).zzd(arg2, 2);
            ((zzca)this).zza(arg3);
        }

        public final void zza(int arg4, zzdx arg5) {
            ((zzca)this).zzd(1, 3);
            ((zzca)this).zzf(2, arg4);
            ((zzca)this).zzd(3, 2);
            ((zzca)this).zzb(arg5);
            ((zzca)this).zzd(1, 4);
        }

        final void zza(int arg3, zzdx arg4, zzen arg5) {
            ((zzca)this).zzd(arg3, 2);
            zzdx v3 = arg4;
            int v0 = ((zzbf)v3).zzal();
            if(v0 == -1) {
                v0 = arg5.zzn(v3);
                ((zzbf)v3).zzh(v0);
            }

            ((zzca)this).zzq(v0);
            arg5.zza(arg4, this.zzhk);
        }

        public final void zza(int arg2, String arg3) {
            ((zzca)this).zzd(arg2, 2);
            ((zzca)this).zzh(arg3);
        }

        public final void zza(zzbo arg2) {
            ((zzca)this).zzq(arg2.size());
            arg2.zza(((zzbn)this));
        }

        public final void zza(byte[] arg1, int arg2, int arg3) {
            this.write(arg1, arg2, arg3);
        }

        public final int zzaz() {
            return this.limit - this.position;
        }

        public final void zzb(int arg4, zzbo arg5) {
            ((zzca)this).zzd(1, 3);
            ((zzca)this).zzf(2, arg4);
            ((zzca)this).zza(3, arg5);
            ((zzca)this).zzd(1, 4);
        }

        public final void zzb(int arg2, boolean arg3) {
            ((zzca)this).zzd(arg2, 0);
            ((zzca)this).zza(((byte)(((int)arg3))));
        }

        public final void zzb(long arg10) {
            int v6;
            byte[] v0;
            int v1 = 7;
            long v2 = 0;
            long v4 = -128;
            if(zzca.zzbb()) {
                if(((zzca)this).zzaz() < 10) {
                    goto label_31;
                }

                while((arg10 & v4) != v2) {
                    v0 = this.buffer;
                    v6 = this.position;
                    this.position = v6 + 1;
                    zzfl.zza(v0, ((long)v6), ((byte)((((int)arg10)) & 127 | 128)));
                    arg10 >>>= v1;
                }

                v0 = this.buffer;
                v1 = this.position;
                this.position = v1 + 1;
                zzfl.zza(v0, ((long)v1), ((byte)(((int)arg10))));
                return;
            }

            while(true) {
            label_31:
                if((arg10 & v4) == v2) {
                    goto label_33;
                }

                try {
                    v0 = this.buffer;
                    v6 = this.position;
                    this.position = v6 + 1;
                    v0[v6] = ((byte)((((int)arg10)) & 127 | 128));
                    arg10 >>>= v1;
                    continue;
                label_33:
                    v0 = this.buffer;
                    v1 = this.position;
                    this.position = v1 + 1;
                    v0[v1] = ((byte)(((int)arg10)));
                    return;
                label_42:
                    break;
                }
                catch(IndexOutOfBoundsException v10) {
                    goto label_42;
                }
            }

            throw new zzb(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.position), Integer.valueOf(this.limit), Integer.valueOf(1)), ((Throwable)v10));
        }

        public final void zzb(zzdx arg2) {
            ((zzca)this).zzq(arg2.zzbl());
            arg2.zzb(((zzca)this));
        }

        public final void zzc(int arg2, long arg3) {
            ((zzca)this).zzd(arg2, 1);
            ((zzca)this).zzd(arg3);
        }

        public final void zzd(int arg1, int arg2) {
            ((zzca)this).zzq(arg1 << 3 | arg2);
        }

        public final void zzd(long arg5) {
            try {
                byte[] v0 = this.buffer;
                int v1 = this.position;
                this.position = v1 + 1;
                v0[v1] = ((byte)(((int)arg5)));
                v0 = this.buffer;
                v1 = this.position;
                this.position = v1 + 1;
                v0[v1] = ((byte)(((int)(arg5 >> 8))));
                v0 = this.buffer;
                v1 = this.position;
                this.position = v1 + 1;
                v0[v1] = ((byte)(((int)(arg5 >> 16))));
                v0 = this.buffer;
                v1 = this.position;
                this.position = v1 + 1;
                v0[v1] = ((byte)(((int)(arg5 >> 24))));
                v0 = this.buffer;
                v1 = this.position;
                this.position = v1 + 1;
                v0[v1] = ((byte)(((int)(arg5 >> 32))));
                v0 = this.buffer;
                v1 = this.position;
                this.position = v1 + 1;
                v0[v1] = ((byte)(((int)(arg5 >> 40))));
                v0 = this.buffer;
                v1 = this.position;
                this.position = v1 + 1;
                v0[v1] = ((byte)(((int)(arg5 >> 48))));
                v0 = this.buffer;
                v1 = this.position;
                this.position = v1 + 1;
                v0[v1] = ((byte)(((int)(arg5 >> 56))));
                return;
            }
            catch(IndexOutOfBoundsException v5) {
                throw new zzb(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.position), Integer.valueOf(this.limit), Integer.valueOf(1)), ((Throwable)v5));
            }
        }

        public final void zzd(byte[] arg1, int arg2, int arg3) {
            ((zzca)this).zzq(arg3);
            this.write(arg1, 0, arg3);
        }

        public final void zze(int arg2, int arg3) {
            ((zzca)this).zzd(arg2, 0);
            ((zzca)this).zzp(arg3);
        }

        public final void zzf(int arg2, int arg3) {
            ((zzca)this).zzd(arg2, 0);
            ((zzca)this).zzq(arg3);
        }

        public final void zzh(int arg2, int arg3) {
            ((zzca)this).zzd(arg2, 5);
            ((zzca)this).zzs(arg3);
        }

        public final void zzh(String arg6) {
            int v0 = this.position;
            try {
                int v1_1 = zza.zzv(arg6.length() * 3);
                int v2 = zza.zzv(arg6.length());
                if(v2 == v1_1) {
                    this.position = v0 + v2;
                    v1_1 = zzfn.zza(((CharSequence)arg6), this.buffer, this.position, ((zzca)this).zzaz());
                    this.position = v0;
                    ((zzca)this).zzq(v1_1 - v0 - v2);
                    this.position = v1_1;
                    return;
                }

                ((zzca)this).zzq(zzfn.zza(((CharSequence)arg6)));
                this.position = zzfn.zza(((CharSequence)arg6), this.buffer, this.position, ((zzca)this).zzaz());
                return;
            }
            catch(IndexOutOfBoundsException v6) {
                throw new zzb(((Throwable)v6));
            }
            catch(zzfq v1) {
                this.position = v0;
                ((zzca)this).zza(arg6, v1);
                return;
            }
        }

        public final void zzp(int arg3) {
            if(arg3 >= 0) {
                ((zzca)this).zzq(arg3);
                return;
            }

            ((zzca)this).zzb(((long)arg3));
        }

        public final void zzq(int arg5) {
            int v1;
            byte[] v0;
            if(zzca.zzbb()) {
                if(((zzca)this).zzaz() < 10) {
                    goto label_26;
                }

                while((arg5 & -128) != 0) {
                    v0 = this.buffer;
                    v1 = this.position;
                    this.position = v1 + 1;
                    zzfl.zza(v0, ((long)v1), ((byte)(arg5 & 127 | 128)));
                    arg5 >>>= 7;
                }

                v0 = this.buffer;
                v1 = this.position;
                this.position = v1 + 1;
                zzfl.zza(v0, ((long)v1), ((byte)arg5));
                return;
            }

            while(true) {
            label_26:
                if((arg5 & -128) == 0) {
                    goto label_28;
                }

                try {
                    v0 = this.buffer;
                    v1 = this.position;
                    this.position = v1 + 1;
                    v0[v1] = ((byte)(arg5 & 127 | 128));
                    arg5 >>>= 7;
                    continue;
                label_28:
                    v0 = this.buffer;
                    v1 = this.position;
                    this.position = v1 + 1;
                    v0[v1] = ((byte)arg5);
                    return;
                label_36:
                    break;
                }
                catch(IndexOutOfBoundsException v5) {
                    goto label_36;
                }
            }

            throw new zzb(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.position), Integer.valueOf(this.limit), Integer.valueOf(1)), ((Throwable)v5));
        }

        public final void zzs(int arg5) {
            try {
                byte[] v0 = this.buffer;
                int v1 = this.position;
                this.position = v1 + 1;
                v0[v1] = ((byte)arg5);
                v0 = this.buffer;
                v1 = this.position;
                this.position = v1 + 1;
                v0[v1] = ((byte)(arg5 >> 8));
                v0 = this.buffer;
                v1 = this.position;
                this.position = v1 + 1;
                v0[v1] = ((byte)(arg5 >> 16));
                v0 = this.buffer;
                v1 = this.position;
                this.position = v1 + 1;
                v0[v1] = arg5 >> 24;
                return;
            }
            catch(IndexOutOfBoundsException v5) {
                throw new zzb(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.position), Integer.valueOf(this.limit), Integer.valueOf(1)), ((Throwable)v5));
            }
        }
    }

    public final class zzb extends IOException {
        zzb(Throwable arg2) {
            super("CodedOutputStream was writing to a flat byte array and ran out of space.", arg2);
        }

        zzb() {
            super("CodedOutputStream was writing to a flat byte array and ran out of space.");
        }

        zzb(String arg3, Throwable arg4) {
            String v0 = String.valueOf("CodedOutputStream was writing to a flat byte array and ran out of space.: ");
            arg3 = String.valueOf(arg3);
            arg3 = arg3.length() != 0 ? v0.concat(arg3) : new String(v0);
            super(arg3, arg4);
        }
    }

    private static final Logger logger;
    private static final boolean zzhj;
    zzcc zzhk;

    static {
        zzca.logger = Logger.getLogger(zzca.class.getName());
        zzca.zzhj = zzfl.zzdx();
    }

    private zzca() {
        super();
    }

    zzca(zzcb arg1) {
        this();
    }

    public abstract void zza(zzbo arg1);

    public final void zza(double arg1) {
        this.zzd(Double.doubleToRawLongBits(arg1));
    }

    public final void zza(boolean arg1) {
        this.zza(((byte)(((int)arg1))));
    }

    public static int zza(zzde arg1) {
        int v1 = arg1.zzbl();
        return zzca.zzv(v1) + v1;
    }

    public static int zza(int arg1, zzde arg2) {
        arg1 = zzca.zzt(arg1);
        int v2 = arg2.zzbl();
        return arg1 + (zzca.zzv(v2) + v2);
    }

    static int zza(zzdx arg2, zzen arg3) {
        int v0 = ((zzbf)arg2).zzal();
        if(v0 == -1) {
            v0 = arg3.zzn(arg2);
            ((zzbf)arg2).zzh(v0);
        }

        return zzca.zzv(v0) + v0;
    }

    public abstract void zza(byte arg1);

    public final void zza(int arg1, double arg2) {
        this.zzc(arg1, Double.doubleToRawLongBits(arg2));
    }

    public final void zza(int arg1, float arg2) {
        this.zzh(arg1, Float.floatToRawIntBits(arg2));
    }

    public abstract void zza(int arg1, long arg2);

    public abstract void zza(int arg1, zzbo arg2);

    public abstract void zza(int arg1, zzdx arg2);

    abstract void zza(int arg1, zzdx arg2, zzen arg3);

    public abstract void zza(int arg1, String arg2);

    final void zza(String arg7, zzfq arg8) {
        zzca.logger.logp(Level.WARNING, "com.google.protobuf.CodedOutputStream", "inefficientWriteStringNoTag", "Converting ill-formed UTF-16. Your Protocol Buffer will not round trip correctly!", arg8);
        byte[] v7 = arg7.getBytes(zzct.UTF_8);
        try {
            this.zzq(v7.length);
            ((zzbn)this).zza(v7, 0, v7.length);
            return;
        }
        catch(zzb v7_1) {
            throw v7_1;
        }
        catch(IndexOutOfBoundsException v7_2) {
            throw new zzb(((Throwable)v7_2));
        }
    }

    private static int zzaa(int arg1) {
        return arg1 >> 31 ^ arg1 << 1;
    }

    @Deprecated public static int zzab(int arg0) {
        return zzca.zzv(arg0);
    }

    public abstract int zzaz();

    public abstract void zzb(long arg1);

    public abstract void zzb(zzdx arg1);

    public static int zzb(zzbo arg1) {
        int v1 = arg1.size();
        return zzca.zzv(v1) + v1;
    }

    public static int zzb(double arg0) {
        return 8;
    }

    public static int zzb(boolean arg0) {
        return 1;
    }

    public static int zzb(int arg2, zzde arg3) {
        return (zzca.zzt(1) << 1) + zzca.zzj(2, arg2) + zzca.zza(3, arg3);
    }

    public static int zzb(int arg2, zzdx arg3) {
        return (zzca.zzt(1) << 1) + zzca.zzj(2, arg2) + (zzca.zzt(3) + zzca.zzc(arg3));
    }

    static int zzb(int arg0, zzdx arg1, zzen arg2) {
        return zzca.zzt(arg0) + zzca.zza(arg1, arg2);
    }

    public static int zzb(int arg0, double arg1) {
        return zzca.zzt(arg0) + 8;
    }

    public static int zzb(int arg0, float arg1) {
        return zzca.zzt(arg0) + 4;
    }

    public static int zzb(int arg0, String arg1) {
        return zzca.zzt(arg0) + zzca.zzi(arg1);
    }

    public final void zzb(int arg1, long arg2) {
        this.zza(arg1, zzca.zzj(arg2));
    }

    public abstract void zzb(int arg1, zzbo arg2);

    public abstract void zzb(int arg1, boolean arg2);

    public final void zzba() {
        if(this.zzaz() == 0) {
            return;
        }

        throw new IllegalStateException("Did not write as much data as expected.");
    }

    static boolean zzbb() {
        return zzca.zzhj;
    }

    public final void zzc(float arg1) {
        this.zzs(Float.floatToRawIntBits(arg1));
    }

    public final void zzc(long arg1) {
        this.zzb(zzca.zzj(arg1));
    }

    public static int zzc(zzdx arg1) {
        int v1 = arg1.zzbl();
        return zzca.zzv(v1) + v1;
    }

    @Deprecated static int zzc(int arg2, zzdx arg3, zzen arg4) {
        arg2 = zzca.zzt(arg2) << 1;
        int v0 = ((zzbf)arg3).zzal();
        if(v0 == -1) {
            v0 = arg4.zzn(arg3);
            ((zzbf)arg3).zzh(v0);
        }

        return arg2 + v0;
    }

    public static int zzc(int arg0, boolean arg1) {
        return zzca.zzt(arg0) + 1;
    }

    public static int zzc(int arg1, zzbo arg2) {
        arg1 = zzca.zzt(arg1);
        int v2 = arg2.size();
        return arg1 + (zzca.zzv(v2) + v2);
    }

    public abstract void zzc(int arg1, long arg2);

    public static zzca zzd(byte[] arg3) {
        return new zza(arg3, 0, arg3.length);
    }

    public abstract void zzd(int arg1, int arg2);

    abstract void zzd(byte[] arg1, int arg2, int arg3);

    public abstract void zzd(long arg1);

    public static int zzd(float arg0) {
        return 4;
    }

    @Deprecated public static int zzd(zzdx arg0) {
        return arg0.zzbl();
    }

    public static int zzd(int arg2, zzbo arg3) {
        return (zzca.zzt(1) << 1) + zzca.zzj(2, arg2) + zzca.zzc(3, arg3);
    }

    public static int zzd(int arg0, long arg1) {
        return zzca.zzt(arg0) + zzca.zzf(arg1);
    }

    public static int zze(long arg0) {
        return zzca.zzf(arg0);
    }

    public static int zze(byte[] arg1) {
        return zzca.zzv(arg1.length) + arg1.length;
    }

    public static int zze(int arg0, long arg1) {
        return zzca.zzt(arg0) + zzca.zzf(arg1);
    }

    public abstract void zze(int arg1, int arg2);

    public static int zzf(long arg6) {
        int v0;
        long v2 = 0;
        if((-128 & arg6) == v2) {
            return 1;
        }

        if(arg6 < v2) {
            return 10;
        }

        if((-34359738368L & arg6) != v2) {
            v0 = 6;
            arg6 >>>= 28;
        }
        else {
            v0 = 2;
        }

        if((-2097152 & arg6) != v2) {
            v0 += 2;
            arg6 >>>= 14;
        }

        if((arg6 & -16384) != v2) {
            ++v0;
        }

        return v0;
    }

    public static int zzf(int arg0, long arg1) {
        return zzca.zzt(arg0) + zzca.zzf(zzca.zzj(arg1));
    }

    public abstract void zzf(int arg1, int arg2);

    public static int zzg(long arg0) {
        return zzca.zzf(zzca.zzj(arg0));
    }

    public static int zzg(int arg0, long arg1) {
        return zzca.zzt(arg0) + 8;
    }

    public final void zzg(int arg1, int arg2) {
        this.zzf(arg1, zzca.zzaa(arg2));
    }

    public abstract void zzh(String arg1);

    public static int zzh(long arg0) {
        return 8;
    }

    public static int zzh(int arg0, long arg1) {
        return zzca.zzt(arg0) + 8;
    }

    public abstract void zzh(int arg1, int arg2);

    public static int zzi(String arg1) {
        int v0;
        try {
            v0 = zzfn.zza(((CharSequence)arg1));
        }
        catch(zzfq ) {
            v0 = arg1.getBytes(zzct.UTF_8).length;
        }

        return zzca.zzv(v0) + v0;
    }

    public static int zzi(long arg0) {
        return 8;
    }

    public static int zzi(int arg0, int arg1) {
        return zzca.zzt(arg0) + zzca.zzu(arg1);
    }

    public static int zzj(int arg0, int arg1) {
        return zzca.zzt(arg0) + zzca.zzv(arg1);
    }

    private static long zzj(long arg3) {
        return arg3 >> 63 ^ arg3 << 1;
    }

    public static int zzk(int arg0, int arg1) {
        return zzca.zzt(arg0) + zzca.zzv(zzca.zzaa(arg1));
    }

    public static int zzl(int arg0, int arg1) {
        return zzca.zzt(arg0) + 4;
    }

    public static int zzm(int arg0, int arg1) {
        return zzca.zzt(arg0) + 4;
    }

    public static int zzn(int arg0, int arg1) {
        return zzca.zzt(arg0) + zzca.zzu(arg1);
    }

    public abstract void zzp(int arg1);

    public abstract void zzq(int arg1);

    public final void zzr(int arg1) {
        this.zzq(zzca.zzaa(arg1));
    }

    public abstract void zzs(int arg1);

    public static int zzt(int arg0) {
        return zzca.zzv(arg0 << 3);
    }

    public static int zzu(int arg0) {
        if(arg0 >= 0) {
            return zzca.zzv(arg0);
        }

        return 10;
    }

    public static int zzv(int arg1) {
        if((arg1 & -128) == 0) {
            return 1;
        }

        if((arg1 & -16384) == 0) {
            return 2;
        }

        if((-2097152 & arg1) == 0) {
            return 3;
        }

        if((arg1 & -268435456) == 0) {
            return 4;
        }

        return 5;
    }

    public static int zzw(int arg0) {
        return zzca.zzv(zzca.zzaa(arg0));
    }

    public static int zzx(int arg0) {
        return 4;
    }

    public static int zzy(int arg0) {
        return 4;
    }

    public static int zzz(int arg0) {
        return zzca.zzu(arg0);
    }
}

