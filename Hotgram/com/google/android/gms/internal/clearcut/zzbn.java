package com.google.android.gms.internal.clearcut;

import java.io.IOException;
import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class zzbn extends zzba {
    class zza extends zzbn {
        private final byte[] buffer;
        private final int limit;
        private final int offset;
        private int position;

        zza(byte[] arg4, int arg5, int arg6) {
            super(null);
            if(arg4 != null) {
                int v2 = arg5 + arg6;
                if((arg5 | arg6 | arg4.length - v2) >= 0) {
                    this.buffer = arg4;
                    this.offset = arg5;
                    this.position = arg5;
                    this.limit = v2;
                    return;
                }

                throw new IllegalArgumentException(String.format("Array range is invalid. Buffer.length=%d, offset=%d, length=%d", Integer.valueOf(arg4.length), Integer.valueOf(arg5), Integer.valueOf(arg6)));
            }

            throw new NullPointerException("buffer");
        }

        public void flush() {
        }

        public final void write(byte[] arg4, int arg5, int arg6) {
            try {
                System.arraycopy(arg4, arg5, this.buffer, this.position, arg6);
                this.position += arg6;
                return;
            }
            catch(IndexOutOfBoundsException v4) {
                throw new zzc(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.position), Integer.valueOf(this.limit), Integer.valueOf(arg6)), ((Throwable)v4));
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
                throw new zzc(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.position), Integer.valueOf(this.limit), Integer.valueOf(1)), ((Throwable)v5));
            }
        }

        public final void zza(int arg2, long arg3) {
            ((zzbn)this).zzb(arg2, 0);
            ((zzbn)this).zzb(arg3);
        }

        public final void zza(int arg2, zzbb arg3) {
            ((zzbn)this).zzb(arg2, 2);
            ((zzbn)this).zza(arg3);
        }

        public final void zza(int arg2, zzdo arg3) {
            ((zzbn)this).zzb(arg2, 2);
            ((zzbn)this).zzb(arg3);
        }

        final void zza(int arg3, zzdo arg4, zzef arg5) {
            ((zzbn)this).zzb(arg3, 2);
            zzdo v3 = arg4;
            int v0 = ((zzas)v3).zzs();
            if(v0 == -1) {
                v0 = arg5.zzm(v3);
                ((zzas)v3).zzf(v0);
            }

            ((zzbn)this).zzo(v0);
            arg5.zza(arg4, this.zzfz);
        }

        public final void zza(int arg2, String arg3) {
            ((zzbn)this).zzb(arg2, 2);
            ((zzbn)this).zzg(arg3);
        }

        public final void zza(zzbb arg2) {
            ((zzbn)this).zzo(arg2.size());
            arg2.zza(((zzba)this));
        }

        final void zza(zzdo arg4, zzef arg5) {
            zzdo v0 = arg4;
            int v1 = ((zzas)v0).zzs();
            if(v1 == -1) {
                v1 = arg5.zzm(v0);
                ((zzas)v0).zzf(v1);
            }

            ((zzbn)this).zzo(v1);
            arg5.zza(arg4, this.zzfz);
        }

        public final void zza(byte[] arg1, int arg2, int arg3) {
            ((zzbn)this).write(arg1, arg2, arg3);
        }

        public final int zzag() {
            return this.limit - this.position;
        }

        public final int zzai() {
            return this.position - this.offset;
        }

        public final void zzb(int arg1, int arg2) {
            ((zzbn)this).zzo(arg1 << 3 | arg2);
        }

        public final void zzb(int arg4, zzbb arg5) {
            ((zzbn)this).zzb(1, 3);
            ((zzbn)this).zzd(2, arg4);
            ((zzbn)this).zza(3, arg5);
            ((zzbn)this).zzb(1, 4);
        }

        public final void zzb(int arg4, zzdo arg5) {
            ((zzbn)this).zzb(1, 3);
            ((zzbn)this).zzd(2, arg4);
            ((zzbn)this).zza(3, arg5);
            ((zzbn)this).zzb(1, 4);
        }

        public final void zzb(int arg2, boolean arg3) {
            ((zzbn)this).zzb(arg2, 0);
            ((zzbn)this).zza(((byte)(((int)arg3))));
        }

        public final void zzb(long arg10) {
            int v6;
            byte[] v0;
            int v1 = 7;
            long v2 = 0;
            long v4 = -128;
            if(zzbn.zzah()) {
                if(((zzbn)this).zzag() < 10) {
                    goto label_31;
                }

                while((arg10 & v4) != v2) {
                    v0 = this.buffer;
                    v6 = this.position;
                    this.position = v6 + 1;
                    zzfd.zza(v0, ((long)v6), ((byte)((((int)arg10)) & 127 | 128)));
                    arg10 >>>= v1;
                }

                v0 = this.buffer;
                v1 = this.position;
                this.position = v1 + 1;
                zzfd.zza(v0, ((long)v1), ((byte)(((int)arg10))));
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

            throw new zzc(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.position), Integer.valueOf(this.limit), Integer.valueOf(1)), ((Throwable)v10));
        }

        public final void zzb(zzdo arg2) {
            ((zzbn)this).zzo(arg2.zzas());
            arg2.zzb(((zzbn)this));
        }

        public final void zzc(int arg2, int arg3) {
            ((zzbn)this).zzb(arg2, 0);
            ((zzbn)this).zzn(arg3);
        }

        public final void zzc(int arg2, long arg3) {
            ((zzbn)this).zzb(arg2, 1);
            ((zzbn)this).zzd(arg3);
        }

        public final void zzd(int arg2, int arg3) {
            ((zzbn)this).zzb(arg2, 0);
            ((zzbn)this).zzo(arg3);
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
                throw new zzc(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.position), Integer.valueOf(this.limit), Integer.valueOf(1)), ((Throwable)v5));
            }
        }

        public final void zzd(byte[] arg1, int arg2, int arg3) {
            ((zzbn)this).zzo(arg3);
            ((zzbn)this).write(arg1, 0, arg3);
        }

        public final void zzf(int arg2, int arg3) {
            ((zzbn)this).zzb(arg2, 5);
            ((zzbn)this).zzq(arg3);
        }

        public final void zzg(String arg6) {
            int v0 = this.position;
            try {
                int v1_1 = zza.zzt(arg6.length() * 3);
                int v2 = zza.zzt(arg6.length());
                if(v2 == v1_1) {
                    this.position = v0 + v2;
                    v1_1 = zzff.zza(((CharSequence)arg6), this.buffer, this.position, ((zzbn)this).zzag());
                    this.position = v0;
                    ((zzbn)this).zzo(v1_1 - v0 - v2);
                    this.position = v1_1;
                    return;
                }

                ((zzbn)this).zzo(zzff.zza(((CharSequence)arg6)));
                this.position = zzff.zza(((CharSequence)arg6), this.buffer, this.position, ((zzbn)this).zzag());
                return;
            }
            catch(IndexOutOfBoundsException v6) {
                throw new zzc(((Throwable)v6));
            }
            catch(zzfi v1) {
                this.position = v0;
                ((zzbn)this).zza(arg6, v1);
                return;
            }
        }

        public final void zzn(int arg3) {
            if(arg3 >= 0) {
                ((zzbn)this).zzo(arg3);
                return;
            }

            ((zzbn)this).zzb(((long)arg3));
        }

        public final void zzo(int arg5) {
            int v1;
            byte[] v0;
            if(zzbn.zzah()) {
                if(((zzbn)this).zzag() < 10) {
                    goto label_26;
                }

                while((arg5 & -128) != 0) {
                    v0 = this.buffer;
                    v1 = this.position;
                    this.position = v1 + 1;
                    zzfd.zza(v0, ((long)v1), ((byte)(arg5 & 127 | 128)));
                    arg5 >>>= 7;
                }

                v0 = this.buffer;
                v1 = this.position;
                this.position = v1 + 1;
                zzfd.zza(v0, ((long)v1), ((byte)arg5));
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

            throw new zzc(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.position), Integer.valueOf(this.limit), Integer.valueOf(1)), ((Throwable)v5));
        }

        public final void zzq(int arg5) {
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
                throw new zzc(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.position), Integer.valueOf(this.limit), Integer.valueOf(1)), ((Throwable)v5));
            }
        }
    }

    final class zzb extends zza {
        private final ByteBuffer zzga;
        private int zzgb;

        zzb(ByteBuffer arg4) {
            super(arg4.array(), arg4.arrayOffset() + arg4.position(), arg4.remaining());
            this.zzga = arg4;
            this.zzgb = arg4.position();
        }

        public final void flush() {
            this.zzga.position(this.zzgb + ((zza)this).zzai());
        }
    }

    public final class zzc extends IOException {
        zzc(Throwable arg2) {
            super("CodedOutputStream was writing to a flat byte array and ran out of space.", arg2);
        }

        zzc() {
            super("CodedOutputStream was writing to a flat byte array and ran out of space.");
        }

        zzc(String arg3) {
            String v0 = String.valueOf("CodedOutputStream was writing to a flat byte array and ran out of space.: ");
            arg3 = String.valueOf(arg3);
            arg3 = arg3.length() != 0 ? v0.concat(arg3) : new String(v0);
            super(arg3);
        }

        zzc(String arg3, Throwable arg4) {
            String v0 = String.valueOf("CodedOutputStream was writing to a flat byte array and ran out of space.: ");
            arg3 = String.valueOf(arg3);
            arg3 = arg3.length() != 0 ? v0.concat(arg3) : new String(v0);
            super(arg3, arg4);
        }
    }

    final class zzd extends zzbn {
        private final int zzgb;
        private final ByteBuffer zzgc;
        private final ByteBuffer zzgd;

        zzd(ByteBuffer arg3) {
            super(null);
            this.zzgc = arg3;
            this.zzgd = arg3.duplicate().order(ByteOrder.LITTLE_ENDIAN);
            this.zzgb = arg3.position();
        }

        public final void flush() {
            this.zzgc.position(this.zzgd.position());
        }

        public final void write(byte[] arg2, int arg3, int arg4) {
            try {
                this.zzgd.put(arg2, arg3, arg4);
                return;
            }
            catch(BufferOverflowException v2) {
                throw new zzc(((Throwable)v2));
            }
            catch(IndexOutOfBoundsException v2_1) {
                throw new zzc(((Throwable)v2_1));
            }
        }

        public final void zza(byte arg2) {
            try {
                this.zzgd.put(arg2);
                return;
            }
            catch(BufferOverflowException v2) {
                throw new zzc(((Throwable)v2));
            }
        }

        public final void zza(int arg2, long arg3) {
            ((zzbn)this).zzb(arg2, 0);
            ((zzbn)this).zzb(arg3);
        }

        public final void zza(int arg2, zzbb arg3) {
            ((zzbn)this).zzb(arg2, 2);
            ((zzbn)this).zza(arg3);
        }

        public final void zza(int arg2, zzdo arg3) {
            ((zzbn)this).zzb(arg2, 2);
            ((zzbn)this).zzb(arg3);
        }

        final void zza(int arg2, zzdo arg3, zzef arg4) {
            ((zzbn)this).zzb(arg2, 2);
            ((zzbn)this).zza(arg3, arg4);
        }

        public final void zza(int arg2, String arg3) {
            ((zzbn)this).zzb(arg2, 2);
            ((zzbn)this).zzg(arg3);
        }

        public final void zza(zzbb arg2) {
            ((zzbn)this).zzo(arg2.size());
            arg2.zza(((zzba)this));
        }

        final void zza(zzdo arg4, zzef arg5) {
            zzdo v0 = arg4;
            int v1 = ((zzas)v0).zzs();
            if(v1 == -1) {
                v1 = arg5.zzm(v0);
                ((zzas)v0).zzf(v1);
            }

            ((zzbn)this).zzo(v1);
            arg5.zza(arg4, this.zzfz);
        }

        public final void zza(byte[] arg1, int arg2, int arg3) {
            ((zzbn)this).write(arg1, arg2, arg3);
        }

        public final int zzag() {
            return this.zzgd.remaining();
        }

        public final void zzb(int arg1, int arg2) {
            ((zzbn)this).zzo(arg1 << 3 | arg2);
        }

        public final void zzb(int arg4, zzbb arg5) {
            ((zzbn)this).zzb(1, 3);
            ((zzbn)this).zzd(2, arg4);
            ((zzbn)this).zza(3, arg5);
            ((zzbn)this).zzb(1, 4);
        }

        public final void zzb(int arg4, zzdo arg5) {
            ((zzbn)this).zzb(1, 3);
            ((zzbn)this).zzd(2, arg4);
            ((zzbn)this).zza(3, arg5);
            ((zzbn)this).zzb(1, 4);
        }

        public final void zzb(int arg2, boolean arg3) {
            ((zzbn)this).zzb(arg2, 0);
            ((zzbn)this).zza(((byte)(((int)arg3))));
        }

        public final void zzb(long arg6) {
            while(true) {
                if((-128 & arg6) == 0) {
                    goto label_4;
                }

                try {
                    this.zzgd.put(((byte)((((int)arg6)) & 127 | 128)));
                    arg6 >>>= 7;
                    continue;
                label_4:
                    this.zzgd.put(((byte)(((int)arg6))));
                    return;
                label_10:
                    break;
                }
                catch(BufferOverflowException v6) {
                    goto label_10;
                }
            }

            throw new zzc(((Throwable)v6));
        }

        public final void zzb(zzdo arg2) {
            ((zzbn)this).zzo(arg2.zzas());
            arg2.zzb(((zzbn)this));
        }

        public final void zzc(int arg2, int arg3) {
            ((zzbn)this).zzb(arg2, 0);
            ((zzbn)this).zzn(arg3);
        }

        public final void zzc(int arg2, long arg3) {
            ((zzbn)this).zzb(arg2, 1);
            ((zzbn)this).zzd(arg3);
        }

        public final void zzd(int arg2, int arg3) {
            ((zzbn)this).zzb(arg2, 0);
            ((zzbn)this).zzo(arg3);
        }

        public final void zzd(long arg2) {
            try {
                this.zzgd.putLong(arg2);
                return;
            }
            catch(BufferOverflowException v2) {
                throw new zzc(((Throwable)v2));
            }
        }

        public final void zzd(byte[] arg1, int arg2, int arg3) {
            ((zzbn)this).zzo(arg3);
            ((zzbn)this).write(arg1, 0, arg3);
        }

        public final void zzf(int arg2, int arg3) {
            ((zzbn)this).zzb(arg2, 5);
            ((zzbn)this).zzq(arg3);
        }

        public final void zzg(String arg5) {
            int v0 = this.zzgd.position();
            try {
                int v1_1 = zzd.zzt(arg5.length() * 3);
                int v2 = zzd.zzt(arg5.length());
                if(v2 == v1_1) {
                    v1_1 = this.zzgd.position() + v2;
                    this.zzgd.position(v1_1);
                    this.zzi(arg5);
                    v2 = this.zzgd.position();
                    this.zzgd.position(v0);
                    ((zzbn)this).zzo(v2 - v1_1);
                    this.zzgd.position(v2);
                    return;
                }

                ((zzbn)this).zzo(zzff.zza(((CharSequence)arg5)));
                this.zzi(arg5);
                return;
            }
            catch(IllegalArgumentException v5) {
                throw new zzc(((Throwable)v5));
            }
            catch(zzfi v1) {
                this.zzgd.position(v0);
                ((zzbn)this).zza(arg5, v1);
                return;
            }
        }

        private final void zzi(String arg2) {
            try {
                zzff.zza(((CharSequence)arg2), this.zzgd);
                return;
            }
            catch(IndexOutOfBoundsException v2) {
                throw new zzc(((Throwable)v2));
            }
        }

        public final void zzn(int arg3) {
            if(arg3 >= 0) {
                ((zzbn)this).zzo(arg3);
                return;
            }

            ((zzbn)this).zzb(((long)arg3));
        }

        public final void zzo(int arg3) {
            while(true) {
                if((arg3 & -128) == 0) {
                    goto label_2;
                }

                try {
                    this.zzgd.put(((byte)(arg3 & 127 | 128)));
                    arg3 >>>= 7;
                    continue;
                label_2:
                    this.zzgd.put(((byte)arg3));
                    return;
                label_7:
                    break;
                }
                catch(BufferOverflowException v3) {
                    goto label_7;
                }
            }

            throw new zzc(((Throwable)v3));
        }

        public final void zzq(int arg2) {
            try {
                this.zzgd.putInt(arg2);
                return;
            }
            catch(BufferOverflowException v2) {
                throw new zzc(((Throwable)v2));
            }
        }
    }

    final class zze extends zzbn {
        private final ByteBuffer zzgc;
        private final ByteBuffer zzgd;
        private final long zzge;
        private final long zzgf;
        private final long zzgg;
        private final long zzgh;
        private long zzgi;

        zze(ByteBuffer arg5) {
            super(null);
            this.zzgc = arg5;
            this.zzgd = arg5.duplicate().order(ByteOrder.LITTLE_ENDIAN);
            this.zzge = zzfd.zzb(arg5);
            this.zzgf = this.zzge + (((long)arg5.position()));
            this.zzgg = this.zzge + (((long)arg5.limit()));
            this.zzgh = this.zzgg - 10;
            this.zzgi = this.zzgf;
        }

        public final void flush() {
            this.zzgc.position(((int)(this.zzgi - this.zzge)));
        }

        public final void write(byte[] arg12, int arg13, int arg14) {
            if(arg12 != null && arg13 >= 0 && arg14 >= 0 && arg12.length - arg14 >= arg13) {
                long v9 = ((long)arg14);
                if(this.zzgg - v9 < this.zzgi) {
                }
                else {
                    zzfd.zza(arg12, ((long)arg13), this.zzgi, v9);
                    this.zzgi += v9;
                    return;
                }
            }

            if(arg12 == null) {
                throw new NullPointerException("value");
            }

            throw new zzc(String.format("Pos: %d, limit: %d, len: %d", Long.valueOf(this.zzgi), Long.valueOf(this.zzgg), Integer.valueOf(arg14)));
        }

        public final void zza(byte arg6) {
            if(this.zzgi < this.zzgg) {
                long v0 = this.zzgi;
                this.zzgi = 1 + v0;
                zzfd.zza(v0, arg6);
                return;
            }

            throw new zzc(String.format("Pos: %d, limit: %d, len: %d", Long.valueOf(this.zzgi), Long.valueOf(this.zzgg), Integer.valueOf(1)));
        }

        public final void zza(int arg2, long arg3) {
            ((zzbn)this).zzb(arg2, 0);
            ((zzbn)this).zzb(arg3);
        }

        public final void zza(int arg2, zzbb arg3) {
            ((zzbn)this).zzb(arg2, 2);
            ((zzbn)this).zza(arg3);
        }

        public final void zza(int arg2, zzdo arg3) {
            ((zzbn)this).zzb(arg2, 2);
            ((zzbn)this).zzb(arg3);
        }

        final void zza(int arg2, zzdo arg3, zzef arg4) {
            ((zzbn)this).zzb(arg2, 2);
            ((zzbn)this).zza(arg3, arg4);
        }

        public final void zza(int arg2, String arg3) {
            ((zzbn)this).zzb(arg2, 2);
            ((zzbn)this).zzg(arg3);
        }

        public final void zza(zzbb arg2) {
            ((zzbn)this).zzo(arg2.size());
            arg2.zza(((zzba)this));
        }

        final void zza(zzdo arg4, zzef arg5) {
            zzdo v0 = arg4;
            int v1 = ((zzas)v0).zzs();
            if(v1 == -1) {
                v1 = arg5.zzm(v0);
                ((zzas)v0).zzf(v1);
            }

            ((zzbn)this).zzo(v1);
            arg5.zza(arg4, this.zzfz);
        }

        public final void zza(byte[] arg1, int arg2, int arg3) {
            ((zzbn)this).write(arg1, arg2, arg3);
        }

        public final int zzag() {
            return ((int)(this.zzgg - this.zzgi));
        }

        public final void zzb(int arg1, int arg2) {
            ((zzbn)this).zzo(arg1 << 3 | arg2);
        }

        public final void zzb(int arg4, zzbb arg5) {
            ((zzbn)this).zzb(1, 3);
            ((zzbn)this).zzd(2, arg4);
            ((zzbn)this).zza(3, arg5);
            ((zzbn)this).zzb(1, 4);
        }

        public final void zzb(int arg4, zzdo arg5) {
            ((zzbn)this).zzb(1, 3);
            ((zzbn)this).zzd(2, arg4);
            ((zzbn)this).zza(3, arg5);
            ((zzbn)this).zzb(1, 4);
        }

        public final void zzb(int arg2, boolean arg3) {
            ((zzbn)this).zzb(arg2, 0);
            ((zzbn)this).zza(((byte)(((int)arg3))));
        }

        public final void zzb(long arg13) {
            // Method was not decompiled
        }

        public final void zzb(zzdo arg2) {
            ((zzbn)this).zzo(arg2.zzas());
            arg2.zzb(((zzbn)this));
        }

        public final void zzc(int arg2, int arg3) {
            ((zzbn)this).zzb(arg2, 0);
            ((zzbn)this).zzn(arg3);
        }

        public final void zzc(int arg2, long arg3) {
            ((zzbn)this).zzb(arg2, 1);
            ((zzbn)this).zzd(arg3);
        }

        public final void zzd(int arg2, int arg3) {
            ((zzbn)this).zzb(arg2, 0);
            ((zzbn)this).zzo(arg3);
        }

        public final void zzd(long arg6) {
            this.zzgd.putLong(((int)(this.zzgi - this.zzge)), arg6);
            this.zzgi += 8;
        }

        public final void zzd(byte[] arg1, int arg2, int arg3) {
            ((zzbn)this).zzo(arg3);
            ((zzbn)this).write(arg1, 0, arg3);
        }

        public final void zzf(int arg2, int arg3) {
            ((zzbn)this).zzb(arg2, 5);
            ((zzbn)this).zzq(arg3);
        }

        public final void zzg(String arg9) {
            long v0 = this.zzgi;
            try {
                int v2_1 = zze.zzt(arg9.length() * 3);
                int v3 = zze.zzt(arg9.length());
                if(v3 == v2_1) {
                    v2_1 = (((int)(this.zzgi - this.zzge))) + v3;
                    this.zzgd.position(v2_1);
                    zzff.zza(((CharSequence)arg9), this.zzgd);
                    v3 = this.zzgd.position() - v2_1;
                    ((zzbn)this).zzo(v3);
                    this.zzgi += ((long)v3);
                    return;
                }

                v2_1 = zzff.zza(((CharSequence)arg9));
                ((zzbn)this).zzo(v2_1);
                this.zzk(this.zzgi);
                zzff.zza(((CharSequence)arg9), this.zzgd);
                this.zzgi += ((long)v2_1);
                return;
            }
            catch(IndexOutOfBoundsException v9) {
                throw new zzc(((Throwable)v9));
            }
            catch(IllegalArgumentException v9_1) {
                throw new zzc(((Throwable)v9_1));
            }
            catch(zzfi v2) {
                this.zzgi = v0;
                this.zzk(this.zzgi);
                ((zzbn)this).zza(arg9, v2);
                return;
            }
        }

        private final void zzk(long arg4) {
            this.zzgd.position(((int)(arg4 - this.zzge)));
        }

        public final void zzn(int arg3) {
            if(arg3 >= 0) {
                ((zzbn)this).zzo(arg3);
                return;
            }

            ((zzbn)this).zzb(((long)arg3));
        }

        public final void zzo(int arg8) {
            // Method was not decompiled
        }

        public final void zzq(int arg6) {
            this.zzgd.putInt(((int)(this.zzgi - this.zzge)), arg6);
            this.zzgi += 4;
        }
    }

    private static final Logger logger;
    private static final boolean zzfy;
    zzbp zzfz;

    static {
        zzbn.logger = Logger.getLogger(zzbn.class.getName());
        zzbn.zzfy = zzfd.zzed();
    }

    private zzbn() {
        super();
    }

    zzbn(zzbo arg1) {
        this();
    }

    public abstract void flush();

    public abstract void write(byte[] arg1, int arg2, int arg3);

    public abstract void zza(zzbb arg1);

    public final void zza(float arg1) {
        this.zzq(Float.floatToRawIntBits(arg1));
    }

    public final void zza(double arg1) {
        this.zzd(Double.doubleToRawLongBits(arg1));
    }

    public final void zza(boolean arg1) {
        this.zza(((byte)(((int)arg1))));
    }

    public static int zza(zzcv arg1) {
        int v1 = arg1.zzas();
        return zzbn.zzt(v1) + v1;
    }

    public static int zza(int arg1, zzcv arg2) {
        arg1 = zzbn.zzr(arg1);
        int v2 = arg2.zzas();
        return arg1 + (zzbn.zzt(v2) + v2);
    }

    public static zzbn zza(ByteBuffer arg1) {
        if(arg1.hasArray()) {
            return new zzb(arg1);
        }

        if((arg1.isDirect()) && !arg1.isReadOnly()) {
            if(zzfd.zzee()) {
                return new zze(arg1);
            }
            else {
                return new zzd(arg1);
            }
        }

        throw new IllegalArgumentException("ByteBuffer is read-only");
    }

    public abstract void zza(int arg1, zzdo arg2);

    public abstract void zza(byte arg1);

    public final void zza(int arg1, double arg2) {
        this.zzc(arg1, Double.doubleToRawLongBits(arg2));
    }

    public final void zza(int arg1, float arg2) {
        this.zzf(arg1, Float.floatToRawIntBits(arg2));
    }

    public abstract void zza(int arg1, long arg2);

    public abstract void zza(int arg1, zzbb arg2);

    abstract void zza(int arg1, zzdo arg2, zzef arg3);

    public abstract void zza(int arg1, String arg2);

    abstract void zza(zzdo arg1, zzef arg2);

    final void zza(String arg7, zzfi arg8) {
        zzbn.logger.logp(Level.WARNING, "com.google.protobuf.CodedOutputStream", "inefficientWriteStringNoTag", "Converting ill-formed UTF-16. Your Protocol Buffer will not round trip correctly!", arg8);
        byte[] v7 = arg7.getBytes(zzci.UTF_8);
        try {
            this.zzo(v7.length);
            ((zzba)this).zza(v7, 0, v7.length);
            return;
        }
        catch(zzc v7_1) {
            throw v7_1;
        }
        catch(IndexOutOfBoundsException v7_2) {
            throw new zzc(((Throwable)v7_2));
        }
    }

    public abstract int zzag();

    static boolean zzah() {
        return zzbn.zzfy;
    }

    public abstract void zzb(int arg1, int arg2);

    public abstract void zzb(long arg1);

    public abstract void zzb(zzdo arg1);

    public static int zzb(float arg0) {
        return 4;
    }

    public static int zzb(zzbb arg1) {
        int v1 = arg1.size();
        return zzbn.zzt(v1) + v1;
    }

    public static int zzb(double arg0) {
        return 8;
    }

    public static int zzb(boolean arg0) {
        return 1;
    }

    public static int zzb(int arg2, zzcv arg3) {
        return (zzbn.zzr(1) << 1) + zzbn.zzh(2, arg2) + zzbn.zza(3, arg3);
    }

    static int zzb(int arg0, zzdo arg1, zzef arg2) {
        return zzbn.zzr(arg0) + zzbn.zzb(arg1, arg2);
    }

    static int zzb(zzdo arg2, zzef arg3) {
        int v0 = ((zzas)arg2).zzs();
        if(v0 == -1) {
            v0 = arg3.zzm(arg2);
            ((zzas)arg2).zzf(v0);
        }

        return zzbn.zzt(v0) + v0;
    }

    public static int zzb(int arg0, double arg1) {
        return zzbn.zzr(arg0) + 8;
    }

    public static int zzb(int arg0, float arg1) {
        return zzbn.zzr(arg0) + 4;
    }

    public static int zzb(int arg0, String arg1) {
        return zzbn.zzr(arg0) + zzbn.zzh(arg1);
    }

    public final void zzb(int arg1, long arg2) {
        this.zza(arg1, zzbn.zzj(arg2));
    }

    public abstract void zzb(int arg1, zzbb arg2);

    public abstract void zzb(int arg1, zzdo arg2);

    public abstract void zzb(int arg1, boolean arg2);

    public static zzbn zzc(byte[] arg3) {
        return new zza(arg3, 0, arg3.length);
    }

    public final void zzc(long arg1) {
        this.zzb(zzbn.zzj(arg1));
    }

    public static int zzc(zzdo arg1) {
        int v1 = arg1.zzas();
        return zzbn.zzt(v1) + v1;
    }

    @Deprecated static int zzc(int arg2, zzdo arg3, zzef arg4) {
        arg2 = zzbn.zzr(arg2) << 1;
        int v0 = ((zzas)arg3).zzs();
        if(v0 == -1) {
            v0 = arg4.zzm(arg3);
            ((zzas)arg3).zzf(v0);
        }

        return arg2 + v0;
    }

    public static int zzc(int arg0, boolean arg1) {
        return zzbn.zzr(arg0) + 1;
    }

    public static int zzc(int arg1, zzbb arg2) {
        arg1 = zzbn.zzr(arg1);
        int v2 = arg2.size();
        return arg1 + (zzbn.zzt(v2) + v2);
    }

    public static int zzc(int arg0, zzdo arg1) {
        return zzbn.zzr(arg0) + zzbn.zzc(arg1);
    }

    public abstract void zzc(int arg1, long arg2);

    public abstract void zzc(int arg1, int arg2);

    abstract void zzd(byte[] arg1, int arg2, int arg3);

    public abstract void zzd(long arg1);

    @Deprecated public static int zzd(zzdo arg0) {
        return arg0.zzas();
    }

    public static int zzd(byte[] arg1) {
        return zzbn.zzt(arg1.length) + arg1.length;
    }

    public static int zzd(int arg2, zzdo arg3) {
        return (zzbn.zzr(1) << 1) + zzbn.zzh(2, arg2) + zzbn.zzc(3, arg3);
    }

    public static int zzd(int arg2, zzbb arg3) {
        return (zzbn.zzr(1) << 1) + zzbn.zzh(2, arg2) + zzbn.zzc(3, arg3);
    }

    public static int zzd(int arg0, long arg1) {
        return zzbn.zzr(arg0) + zzbn.zzf(arg1);
    }

    public abstract void zzd(int arg1, int arg2);

    public static int zze(long arg0) {
        return zzbn.zzf(arg0);
    }

    public static int zze(int arg0, long arg1) {
        return zzbn.zzr(arg0) + zzbn.zzf(arg1);
    }

    public final void zze(int arg1, int arg2) {
        this.zzd(arg1, zzbn.zzy(arg2));
    }

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
        return zzbn.zzr(arg0) + zzbn.zzf(zzbn.zzj(arg1));
    }

    public abstract void zzf(int arg1, int arg2);

    public abstract void zzg(String arg1);

    public static int zzg(long arg0) {
        return zzbn.zzf(zzbn.zzj(arg0));
    }

    public static int zzg(int arg0, long arg1) {
        return zzbn.zzr(arg0) + 8;
    }

    public static int zzg(int arg0, int arg1) {
        return zzbn.zzr(arg0) + zzbn.zzs(arg1);
    }

    public static int zzh(String arg1) {
        int v0;
        try {
            v0 = zzff.zza(((CharSequence)arg1));
        }
        catch(zzfi ) {
            v0 = arg1.getBytes(zzci.UTF_8).length;
        }

        return zzbn.zzt(v0) + v0;
    }

    public static int zzh(long arg0) {
        return 8;
    }

    public static int zzh(int arg0, int arg1) {
        return zzbn.zzr(arg0) + zzbn.zzt(arg1);
    }

    public static int zzh(int arg0, long arg1) {
        return zzbn.zzr(arg0) + 8;
    }

    public static int zzi(long arg0) {
        return 8;
    }

    public static int zzi(int arg0, int arg1) {
        return zzbn.zzr(arg0) + zzbn.zzt(zzbn.zzy(arg1));
    }

    public static int zzj(int arg0, int arg1) {
        return zzbn.zzr(arg0) + 4;
    }

    private static long zzj(long arg3) {
        return arg3 >> 63 ^ arg3 << 1;
    }

    public static int zzk(int arg0, int arg1) {
        return zzbn.zzr(arg0) + 4;
    }

    public static int zzl(int arg0, int arg1) {
        return zzbn.zzr(arg0) + zzbn.zzs(arg1);
    }

    public abstract void zzn(int arg1);

    public abstract void zzo(int arg1);

    public final void zzp(int arg1) {
        this.zzo(zzbn.zzy(arg1));
    }

    public abstract void zzq(int arg1);

    public static int zzr(int arg0) {
        return zzbn.zzt(arg0 << 3);
    }

    public static int zzs(int arg0) {
        if(arg0 >= 0) {
            return zzbn.zzt(arg0);
        }

        return 10;
    }

    public static int zzt(int arg1) {
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

    public static int zzu(int arg0) {
        return zzbn.zzt(zzbn.zzy(arg0));
    }

    public static int zzv(int arg0) {
        return 4;
    }

    public static int zzw(int arg0) {
        return 4;
    }

    public static int zzx(int arg0) {
        return zzbn.zzs(arg0);
    }

    private static int zzy(int arg1) {
        return arg1 >> 31 ^ arg1 << 1;
    }

    @Deprecated public static int zzz(int arg0) {
        return zzbn.zzt(arg0);
    }
}

