package com.google.android.gms.internal.places;

import java.io.IOException;
import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class zzgf extends zzfq {
    class zzb extends zzgf {
        private final byte[] buffer;
        private final int limit;
        private final int offset;
        private int position;

        zzb(byte[] arg4, int arg5, int arg6) {
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
                throw new zzd(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.position), Integer.valueOf(this.limit), Integer.valueOf(arg6)), ((Throwable)v4));
            }
        }

        public final void zzao(int arg3) {
            if(arg3 >= 0) {
                ((zzgf)this).zzap(arg3);
                return;
            }

            ((zzgf)this).zze(((long)arg3));
        }

        public final void zzap(int arg5) {
            int v1;
            byte[] v0;
            if(zzgf.zzct()) {
                if(((zzgf)this).zzcs() < 10) {
                    goto label_26;
                }

                while((arg5 & -128) != 0) {
                    v0 = this.buffer;
                    v1 = this.position;
                    this.position = v1 + 1;
                    zzjw.zzb(v0, ((long)v1), ((byte)(arg5 & 127 | 128)));
                    arg5 >>>= 7;
                }

                v0 = this.buffer;
                v1 = this.position;
                this.position = v1 + 1;
                zzjw.zzb(v0, ((long)v1), ((byte)arg5));
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

            throw new zzd(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.position), Integer.valueOf(this.limit), Integer.valueOf(1)), ((Throwable)v5));
        }

        public final void zzar(int arg5) {
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
                throw new zzd(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.position), Integer.valueOf(this.limit), Integer.valueOf(1)), ((Throwable)v5));
            }
        }

        public final void zzb(byte arg5) {
            try {
                byte[] v0 = this.buffer;
                int v1 = this.position;
                this.position = v1 + 1;
                v0[v1] = arg5;
                return;
            }
            catch(IndexOutOfBoundsException v5) {
                throw new zzd(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.position), Integer.valueOf(this.limit), Integer.valueOf(1)), ((Throwable)v5));
            }
        }

        public final void zzb(int arg2, long arg3) {
            ((zzgf)this).zzd(arg2, 0);
            ((zzgf)this).zze(arg3);
        }

        public final void zzb(int arg2, zzfr arg3) {
            ((zzgf)this).zzd(arg2, 2);
            ((zzgf)this).zzb(arg3);
        }

        public final void zzb(int arg2, zzih arg3) {
            ((zzgf)this).zzd(arg2, 2);
            ((zzgf)this).zzc(arg3);
        }

        final void zzb(int arg3, zzih arg4, zziy arg5) {
            ((zzgf)this).zzd(arg3, 2);
            zzih v3 = arg4;
            int v0 = ((zzfh)v3).zzay();
            if(v0 == -1) {
                v0 = arg5.zzn(v3);
                ((zzfh)v3).zzv(v0);
            }

            ((zzgf)this).zzap(v0);
            arg5.zzb(arg4, this.zzop);
        }

        public final void zzb(int arg2, String arg3) {
            ((zzgf)this).zzd(arg2, 2);
            ((zzgf)this).zzk(arg3);
        }

        public final void zzb(zzfr arg2) {
            ((zzgf)this).zzap(arg2.size());
            arg2.zzb(((zzfq)this));
        }

        final void zzb(zzih arg4, zziy arg5) {
            zzih v0 = arg4;
            int v1 = ((zzfh)v0).zzay();
            if(v1 == -1) {
                v1 = arg5.zzn(v0);
                ((zzfh)v0).zzv(v1);
            }

            ((zzgf)this).zzap(v1);
            arg5.zzb(arg4, this.zzop);
        }

        public final void zzb(byte[] arg1, int arg2, int arg3) {
            ((zzgf)this).write(arg1, arg2, arg3);
        }

        public final void zzc(int arg4, zzfr arg5) {
            ((zzgf)this).zzd(1, 3);
            ((zzgf)this).zzf(2, arg4);
            ((zzgf)this).zzb(3, arg5);
            ((zzgf)this).zzd(1, 4);
        }

        public final void zzc(int arg4, zzih arg5) {
            ((zzgf)this).zzd(1, 3);
            ((zzgf)this).zzf(2, arg4);
            ((zzgf)this).zzb(3, arg5);
            ((zzgf)this).zzd(1, 4);
        }

        public final void zzc(int arg2, boolean arg3) {
            ((zzgf)this).zzd(arg2, 0);
            ((zzgf)this).zzb(((byte)(((int)arg3))));
        }

        public final void zzc(zzih arg2) {
            ((zzgf)this).zzap(arg2.zzdg());
            arg2.zzc(((zzgf)this));
        }

        public final int zzcs() {
            return this.limit - this.position;
        }

        public final int zzcu() {
            return this.position - this.offset;
        }

        public final void zzd(int arg1, int arg2) {
            ((zzgf)this).zzap(arg1 << 3 | arg2);
        }

        public final void zzd(int arg2, long arg3) {
            ((zzgf)this).zzd(arg2, 1);
            ((zzgf)this).zzg(arg3);
        }

        public final void zze(int arg2, int arg3) {
            ((zzgf)this).zzd(arg2, 0);
            ((zzgf)this).zzao(arg3);
        }

        public final void zze(long arg10) {
            int v6;
            byte[] v0;
            int v1 = 7;
            long v2 = 0;
            long v4 = -128;
            if(zzgf.zzct()) {
                if(((zzgf)this).zzcs() < 10) {
                    goto label_31;
                }

                while((arg10 & v4) != v2) {
                    v0 = this.buffer;
                    v6 = this.position;
                    this.position = v6 + 1;
                    zzjw.zzb(v0, ((long)v6), ((byte)((((int)arg10)) & 127 | 128)));
                    arg10 >>>= v1;
                }

                v0 = this.buffer;
                v1 = this.position;
                this.position = v1 + 1;
                zzjw.zzb(v0, ((long)v1), ((byte)(((int)arg10))));
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

            throw new zzd(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.position), Integer.valueOf(this.limit), Integer.valueOf(1)), ((Throwable)v10));
        }

        public final void zzf(int arg2, int arg3) {
            ((zzgf)this).zzd(arg2, 0);
            ((zzgf)this).zzap(arg3);
        }

        public final void zzg(long arg5) {
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
                throw new zzd(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.position), Integer.valueOf(this.limit), Integer.valueOf(1)), ((Throwable)v5));
            }
        }

        public final void zzg(byte[] arg1, int arg2, int arg3) {
            ((zzgf)this).zzap(arg3);
            ((zzgf)this).write(arg1, 0, arg3);
        }

        public final void zzh(int arg2, int arg3) {
            ((zzgf)this).zzd(arg2, 5);
            ((zzgf)this).zzar(arg3);
        }

        public final void zzk(String arg6) {
            int v0 = this.position;
            try {
                int v1_1 = zzb.zzau(arg6.length() * 3);
                int v2 = zzb.zzau(arg6.length());
                if(v2 == v1_1) {
                    this.position = v0 + v2;
                    v1_1 = zzjy.zzb(((CharSequence)arg6), this.buffer, this.position, ((zzgf)this).zzcs());
                    this.position = v0;
                    ((zzgf)this).zzap(v1_1 - v0 - v2);
                    this.position = v1_1;
                    return;
                }

                ((zzgf)this).zzap(zzjy.zzb(((CharSequence)arg6)));
                this.position = zzjy.zzb(((CharSequence)arg6), this.buffer, this.position, ((zzgf)this).zzcs());
                return;
            }
            catch(IndexOutOfBoundsException v6) {
                throw new zzd(((Throwable)v6));
            }
            catch(zzkb v1) {
                this.position = v0;
                ((zzgf)this).zzb(arg6, v1);
                return;
            }
        }
    }

    final class zzc extends zzb {
        private final ByteBuffer zzoq;
        private int zzor;

        zzc(ByteBuffer arg4) {
            super(arg4.array(), arg4.arrayOffset() + arg4.position(), arg4.remaining());
            this.zzoq = arg4;
            this.zzor = arg4.position();
        }

        public final void flush() {
            this.zzoq.position(this.zzor + ((zzb)this).zzcu());
        }
    }

    public final class zzd extends IOException {
        zzd(Throwable arg2) {
            super("CodedOutputStream was writing to a flat byte array and ran out of space.", arg2);
        }

        zzd() {
            super("CodedOutputStream was writing to a flat byte array and ran out of space.");
        }

        zzd(String arg3) {
            String v0 = String.valueOf("CodedOutputStream was writing to a flat byte array and ran out of space.: ");
            arg3 = String.valueOf(arg3);
            arg3 = arg3.length() != 0 ? v0.concat(arg3) : new String(v0);
            super(arg3);
        }

        zzd(String arg3, Throwable arg4) {
            String v0 = String.valueOf("CodedOutputStream was writing to a flat byte array and ran out of space.: ");
            arg3 = String.valueOf(arg3);
            arg3 = arg3.length() != 0 ? v0.concat(arg3) : new String(v0);
            super(arg3, arg4);
        }
    }

    final class zze extends zzgf {
        private final int zzor;
        private final ByteBuffer zzos;
        private final ByteBuffer zzot;

        zze(ByteBuffer arg3) {
            super(null);
            this.zzos = arg3;
            this.zzot = arg3.duplicate().order(ByteOrder.LITTLE_ENDIAN);
            this.zzor = arg3.position();
        }

        public final void flush() {
            this.zzos.position(this.zzot.position());
        }

        public final void write(byte[] arg2, int arg3, int arg4) {
            try {
                this.zzot.put(arg2, arg3, arg4);
                return;
            }
            catch(BufferOverflowException v2) {
                throw new zzd(((Throwable)v2));
            }
            catch(IndexOutOfBoundsException v2_1) {
                throw new zzd(((Throwable)v2_1));
            }
        }

        public final void zzao(int arg3) {
            if(arg3 >= 0) {
                ((zzgf)this).zzap(arg3);
                return;
            }

            ((zzgf)this).zze(((long)arg3));
        }

        public final void zzap(int arg3) {
            while(true) {
                if((arg3 & -128) == 0) {
                    goto label_2;
                }

                try {
                    this.zzot.put(((byte)(arg3 & 127 | 128)));
                    arg3 >>>= 7;
                    continue;
                label_2:
                    this.zzot.put(((byte)arg3));
                    return;
                label_7:
                    break;
                }
                catch(BufferOverflowException v3) {
                    goto label_7;
                }
            }

            throw new zzd(((Throwable)v3));
        }

        public final void zzar(int arg2) {
            try {
                this.zzot.putInt(arg2);
                return;
            }
            catch(BufferOverflowException v2) {
                throw new zzd(((Throwable)v2));
            }
        }

        public final void zzb(byte arg2) {
            try {
                this.zzot.put(arg2);
                return;
            }
            catch(BufferOverflowException v2) {
                throw new zzd(((Throwable)v2));
            }
        }

        public final void zzb(int arg2, long arg3) {
            ((zzgf)this).zzd(arg2, 0);
            ((zzgf)this).zze(arg3);
        }

        public final void zzb(int arg2, zzfr arg3) {
            ((zzgf)this).zzd(arg2, 2);
            ((zzgf)this).zzb(arg3);
        }

        public final void zzb(int arg2, zzih arg3) {
            ((zzgf)this).zzd(arg2, 2);
            ((zzgf)this).zzc(arg3);
        }

        final void zzb(int arg2, zzih arg3, zziy arg4) {
            ((zzgf)this).zzd(arg2, 2);
            ((zzgf)this).zzb(arg3, arg4);
        }

        public final void zzb(int arg2, String arg3) {
            ((zzgf)this).zzd(arg2, 2);
            ((zzgf)this).zzk(arg3);
        }

        public final void zzb(zzfr arg2) {
            ((zzgf)this).zzap(arg2.size());
            arg2.zzb(((zzfq)this));
        }

        final void zzb(zzih arg4, zziy arg5) {
            zzih v0 = arg4;
            int v1 = ((zzfh)v0).zzay();
            if(v1 == -1) {
                v1 = arg5.zzn(v0);
                ((zzfh)v0).zzv(v1);
            }

            ((zzgf)this).zzap(v1);
            arg5.zzb(arg4, this.zzop);
        }

        public final void zzb(byte[] arg1, int arg2, int arg3) {
            ((zzgf)this).write(arg1, arg2, arg3);
        }

        public final void zzc(int arg4, zzfr arg5) {
            ((zzgf)this).zzd(1, 3);
            ((zzgf)this).zzf(2, arg4);
            ((zzgf)this).zzb(3, arg5);
            ((zzgf)this).zzd(1, 4);
        }

        public final void zzc(int arg4, zzih arg5) {
            ((zzgf)this).zzd(1, 3);
            ((zzgf)this).zzf(2, arg4);
            ((zzgf)this).zzb(3, arg5);
            ((zzgf)this).zzd(1, 4);
        }

        public final void zzc(int arg2, boolean arg3) {
            ((zzgf)this).zzd(arg2, 0);
            ((zzgf)this).zzb(((byte)(((int)arg3))));
        }

        public final void zzc(zzih arg2) {
            ((zzgf)this).zzap(arg2.zzdg());
            arg2.zzc(((zzgf)this));
        }

        public final int zzcs() {
            return this.zzot.remaining();
        }

        public final void zzd(int arg1, int arg2) {
            ((zzgf)this).zzap(arg1 << 3 | arg2);
        }

        public final void zzd(int arg2, long arg3) {
            ((zzgf)this).zzd(arg2, 1);
            ((zzgf)this).zzg(arg3);
        }

        public final void zze(int arg2, int arg3) {
            ((zzgf)this).zzd(arg2, 0);
            ((zzgf)this).zzao(arg3);
        }

        public final void zze(long arg6) {
            while(true) {
                if((-128 & arg6) == 0) {
                    goto label_4;
                }

                try {
                    this.zzot.put(((byte)((((int)arg6)) & 127 | 128)));
                    arg6 >>>= 7;
                    continue;
                label_4:
                    this.zzot.put(((byte)(((int)arg6))));
                    return;
                label_10:
                    break;
                }
                catch(BufferOverflowException v6) {
                    goto label_10;
                }
            }

            throw new zzd(((Throwable)v6));
        }

        public final void zzf(int arg2, int arg3) {
            ((zzgf)this).zzd(arg2, 0);
            ((zzgf)this).zzap(arg3);
        }

        public final void zzg(long arg2) {
            try {
                this.zzot.putLong(arg2);
                return;
            }
            catch(BufferOverflowException v2) {
                throw new zzd(((Throwable)v2));
            }
        }

        public final void zzg(byte[] arg1, int arg2, int arg3) {
            ((zzgf)this).zzap(arg3);
            ((zzgf)this).write(arg1, 0, arg3);
        }

        public final void zzh(int arg2, int arg3) {
            ((zzgf)this).zzd(arg2, 5);
            ((zzgf)this).zzar(arg3);
        }

        public final void zzk(String arg5) {
            int v0 = this.zzot.position();
            try {
                int v1_1 = zze.zzau(arg5.length() * 3);
                int v2 = zze.zzau(arg5.length());
                if(v2 == v1_1) {
                    v1_1 = this.zzot.position() + v2;
                    this.zzot.position(v1_1);
                    this.zzm(arg5);
                    v2 = this.zzot.position();
                    this.zzot.position(v0);
                    ((zzgf)this).zzap(v2 - v1_1);
                    this.zzot.position(v2);
                    return;
                }

                ((zzgf)this).zzap(zzjy.zzb(((CharSequence)arg5)));
                this.zzm(arg5);
                return;
            }
            catch(IllegalArgumentException v5) {
                throw new zzd(((Throwable)v5));
            }
            catch(zzkb v1) {
                this.zzot.position(v0);
                ((zzgf)this).zzb(arg5, v1);
                return;
            }
        }

        private final void zzm(String arg2) {
            try {
                zzjy.zzb(((CharSequence)arg2), this.zzot);
                return;
            }
            catch(IndexOutOfBoundsException v2) {
                throw new zzd(((Throwable)v2));
            }
        }
    }

    final class zzf extends zzgf {
        private final ByteBuffer zzos;
        private final ByteBuffer zzot;
        private final long zzou;
        private final long zzov;
        private final long zzow;
        private final long zzox;
        private long zzoy;

        zzf(ByteBuffer arg5) {
            super(null);
            this.zzos = arg5;
            this.zzot = arg5.duplicate().order(ByteOrder.LITTLE_ENDIAN);
            this.zzou = zzjw.zzc(arg5);
            this.zzov = this.zzou + (((long)arg5.position()));
            this.zzow = this.zzou + (((long)arg5.limit()));
            this.zzox = this.zzow - 10;
            this.zzoy = this.zzov;
        }

        public final void flush() {
            this.zzos.position(((int)(this.zzoy - this.zzou)));
        }

        public final void write(byte[] arg12, int arg13, int arg14) {
            if(arg12 != null && arg13 >= 0 && arg14 >= 0 && arg12.length - arg14 >= arg13) {
                long v9 = ((long)arg14);
                if(this.zzow - v9 < this.zzoy) {
                }
                else {
                    zzjw.zzb(arg12, ((long)arg13), this.zzoy, v9);
                    this.zzoy += v9;
                    return;
                }
            }

            if(arg12 == null) {
                throw new NullPointerException("value");
            }

            throw new zzd(String.format("Pos: %d, limit: %d, len: %d", Long.valueOf(this.zzoy), Long.valueOf(this.zzow), Integer.valueOf(arg14)));
        }

        public final void zzao(int arg3) {
            if(arg3 >= 0) {
                ((zzgf)this).zzap(arg3);
                return;
            }

            ((zzgf)this).zze(((long)arg3));
        }

        public final void zzap(int arg8) {
            // Method was not decompiled
        }

        public final void zzar(int arg6) {
            this.zzot.putInt(((int)(this.zzoy - this.zzou)), arg6);
            this.zzoy += 4;
        }

        public final void zzb(byte arg6) {
            if(this.zzoy < this.zzow) {
                long v0 = this.zzoy;
                this.zzoy = 1 + v0;
                zzjw.zzb(v0, arg6);
                return;
            }

            throw new zzd(String.format("Pos: %d, limit: %d, len: %d", Long.valueOf(this.zzoy), Long.valueOf(this.zzow), Integer.valueOf(1)));
        }

        public final void zzb(int arg2, long arg3) {
            ((zzgf)this).zzd(arg2, 0);
            ((zzgf)this).zze(arg3);
        }

        public final void zzb(int arg2, zzfr arg3) {
            ((zzgf)this).zzd(arg2, 2);
            ((zzgf)this).zzb(arg3);
        }

        public final void zzb(int arg2, zzih arg3) {
            ((zzgf)this).zzd(arg2, 2);
            ((zzgf)this).zzc(arg3);
        }

        final void zzb(int arg2, zzih arg3, zziy arg4) {
            ((zzgf)this).zzd(arg2, 2);
            ((zzgf)this).zzb(arg3, arg4);
        }

        public final void zzb(int arg2, String arg3) {
            ((zzgf)this).zzd(arg2, 2);
            ((zzgf)this).zzk(arg3);
        }

        public final void zzb(zzfr arg2) {
            ((zzgf)this).zzap(arg2.size());
            arg2.zzb(((zzfq)this));
        }

        final void zzb(zzih arg4, zziy arg5) {
            zzih v0 = arg4;
            int v1 = ((zzfh)v0).zzay();
            if(v1 == -1) {
                v1 = arg5.zzn(v0);
                ((zzfh)v0).zzv(v1);
            }

            ((zzgf)this).zzap(v1);
            arg5.zzb(arg4, this.zzop);
        }

        public final void zzb(byte[] arg1, int arg2, int arg3) {
            ((zzgf)this).write(arg1, arg2, arg3);
        }

        public final void zzc(int arg4, zzfr arg5) {
            ((zzgf)this).zzd(1, 3);
            ((zzgf)this).zzf(2, arg4);
            ((zzgf)this).zzb(3, arg5);
            ((zzgf)this).zzd(1, 4);
        }

        public final void zzc(int arg4, zzih arg5) {
            ((zzgf)this).zzd(1, 3);
            ((zzgf)this).zzf(2, arg4);
            ((zzgf)this).zzb(3, arg5);
            ((zzgf)this).zzd(1, 4);
        }

        public final void zzc(int arg2, boolean arg3) {
            ((zzgf)this).zzd(arg2, 0);
            ((zzgf)this).zzb(((byte)(((int)arg3))));
        }

        public final void zzc(zzih arg2) {
            ((zzgf)this).zzap(arg2.zzdg());
            arg2.zzc(((zzgf)this));
        }

        public final int zzcs() {
            return ((int)(this.zzow - this.zzoy));
        }

        public final void zzd(int arg1, int arg2) {
            ((zzgf)this).zzap(arg1 << 3 | arg2);
        }

        public final void zzd(int arg2, long arg3) {
            ((zzgf)this).zzd(arg2, 1);
            ((zzgf)this).zzg(arg3);
        }

        public final void zze(int arg2, int arg3) {
            ((zzgf)this).zzd(arg2, 0);
            ((zzgf)this).zzao(arg3);
        }

        public final void zze(long arg13) {
            // Method was not decompiled
        }

        public final void zzf(int arg2, int arg3) {
            ((zzgf)this).zzd(arg2, 0);
            ((zzgf)this).zzap(arg3);
        }

        public final void zzg(long arg6) {
            this.zzot.putLong(((int)(this.zzoy - this.zzou)), arg6);
            this.zzoy += 8;
        }

        public final void zzg(byte[] arg1, int arg2, int arg3) {
            ((zzgf)this).zzap(arg3);
            ((zzgf)this).write(arg1, 0, arg3);
        }

        public final void zzh(int arg2, int arg3) {
            ((zzgf)this).zzd(arg2, 5);
            ((zzgf)this).zzar(arg3);
        }

        public final void zzk(String arg9) {
            long v0 = this.zzoy;
            try {
                int v2_1 = zzf.zzau(arg9.length() * 3);
                int v3 = zzf.zzau(arg9.length());
                if(v3 == v2_1) {
                    v2_1 = (((int)(this.zzoy - this.zzou))) + v3;
                    this.zzot.position(v2_1);
                    zzjy.zzb(((CharSequence)arg9), this.zzot);
                    v3 = this.zzot.position() - v2_1;
                    ((zzgf)this).zzap(v3);
                    this.zzoy += ((long)v3);
                    return;
                }

                v2_1 = zzjy.zzb(((CharSequence)arg9));
                ((zzgf)this).zzap(v2_1);
                this.zzn(this.zzoy);
                zzjy.zzb(((CharSequence)arg9), this.zzot);
                this.zzoy += ((long)v2_1);
                return;
            }
            catch(IndexOutOfBoundsException v9) {
                throw new zzd(((Throwable)v9));
            }
            catch(IllegalArgumentException v9_1) {
                throw new zzd(((Throwable)v9_1));
            }
            catch(zzkb v2) {
                this.zzoy = v0;
                this.zzn(this.zzoy);
                ((zzgf)this).zzb(arg9, v2);
                return;
            }
        }

        private final void zzn(long arg4) {
            this.zzot.position(((int)(arg4 - this.zzou)));
        }
    }

    private static final Logger logger;
    private static final boolean zzoo;
    zzgh zzop;

    static {
        zzgf.logger = Logger.getLogger(zzgf.class.getName());
        zzgf.zzoo = zzjw.zzgs();
    }

    private zzgf() {
        super();
    }

    zzgf(zzgg arg1) {
        this();
    }

    public abstract void flush();

    public abstract void write(byte[] arg1, int arg2, int arg3);

    public abstract void zzao(int arg1);

    public abstract void zzap(int arg1);

    public final void zzaq(int arg1) {
        this.zzap(zzgf.zzaz(arg1));
    }

    public abstract void zzar(int arg1);

    public static int zzas(int arg0) {
        return zzgf.zzau(arg0 << 3);
    }

    public static int zzat(int arg0) {
        if(arg0 >= 0) {
            return zzgf.zzau(arg0);
        }

        return 10;
    }

    public static int zzau(int arg1) {
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

    public static int zzav(int arg0) {
        return zzgf.zzau(zzgf.zzaz(arg0));
    }

    public static int zzaw(int arg0) {
        return 4;
    }

    public static int zzax(int arg0) {
        return 4;
    }

    public static int zzay(int arg0) {
        return zzgf.zzat(arg0);
    }

    private static int zzaz(int arg1) {
        return arg1 >> 31 ^ arg1 << 1;
    }

    public abstract void zzb(zzfr arg1);

    public final void zzb(double arg1) {
        this.zzg(Double.doubleToRawLongBits(arg1));
    }

    public static int zzb(zzho arg1) {
        int v1 = arg1.zzdg();
        return zzgf.zzau(v1) + v1;
    }

    public static int zzb(int arg1, zzho arg2) {
        arg1 = zzgf.zzas(arg1);
        int v2 = arg2.zzdg();
        return arg1 + (zzgf.zzau(v2) + v2);
    }

    public static zzgf zzb(ByteBuffer arg1) {
        if(arg1.hasArray()) {
            return new zzc(arg1);
        }

        if((arg1.isDirect()) && !arg1.isReadOnly()) {
            if(zzjw.zzgt()) {
                return new zzf(arg1);
            }
            else {
                return new zze(arg1);
            }
        }

        throw new IllegalArgumentException("ByteBuffer is read-only");
    }

    public abstract void zzb(int arg1, zzih arg2);

    public abstract void zzb(byte arg1);

    public final void zzb(int arg1, double arg2) {
        this.zzd(arg1, Double.doubleToRawLongBits(arg2));
    }

    public abstract void zzb(int arg1, long arg2);

    public abstract void zzb(int arg1, zzfr arg2);

    abstract void zzb(int arg1, zzih arg2, zziy arg3);

    public abstract void zzb(int arg1, String arg2);

    abstract void zzb(zzih arg1, zziy arg2);

    final void zzb(String arg7, zzkb arg8) {
        zzgf.logger.logp(Level.WARNING, "com.google.protobuf.CodedOutputStream", "inefficientWriteStringNoTag", "Converting ill-formed UTF-16. Your Protocol Buffer will not round trip correctly!", arg8);
        byte[] v7 = arg7.getBytes(zzhb.UTF_8);
        try {
            this.zzap(v7.length);
            ((zzfq)this).zzb(v7, 0, v7.length);
            return;
        }
        catch(zzd v7_1) {
            throw v7_1;
        }
        catch(IndexOutOfBoundsException v7_2) {
            throw new zzd(((Throwable)v7_2));
        }
    }

    @Deprecated public static int zzba(int arg0) {
        return zzgf.zzau(arg0);
    }

    public abstract void zzc(zzih arg1);

    public static int zzc(zzfr arg1) {
        int v1 = arg1.size();
        return zzgf.zzau(v1) + v1;
    }

    public static int zzc(double arg0) {
        return 8;
    }

    public static int zzc(int arg2, zzho arg3) {
        return (zzgf.zzas(1) << 1) + zzgf.zzj(2, arg2) + zzgf.zzb(3, arg3);
    }

    static int zzc(int arg0, zzih arg1, zziy arg2) {
        return zzgf.zzas(arg0) + zzgf.zzc(arg1, arg2);
    }

    static int zzc(zzih arg2, zziy arg3) {
        int v0 = ((zzfh)arg2).zzay();
        if(v0 == -1) {
            v0 = arg3.zzn(arg2);
            ((zzfh)arg2).zzv(v0);
        }

        return zzgf.zzau(v0) + v0;
    }

    public static int zzc(int arg0, double arg1) {
        return zzgf.zzas(arg0) + 8;
    }

    public static int zzc(int arg0, String arg1) {
        return zzgf.zzas(arg0) + zzgf.zzl(arg1);
    }

    public final void zzc(int arg1, float arg2) {
        this.zzh(arg1, Float.floatToRawIntBits(arg2));
    }

    public final void zzc(int arg1, long arg2) {
        this.zzb(arg1, zzgf.zzm(arg2));
    }

    public abstract void zzc(int arg1, zzfr arg2);

    public abstract void zzc(int arg1, zzih arg2);

    public abstract void zzc(int arg1, boolean arg2);

    public abstract int zzcs();

    static boolean zzct() {
        return zzgf.zzoo;
    }

    public static zzgf zzd(byte[] arg3) {
        return new zzb(arg3, 0, arg3.length);
    }

    public abstract void zzd(int arg1, int arg2);

    public final void zzd(float arg1) {
        this.zzar(Float.floatToRawIntBits(arg1));
    }

    public final void zzd(boolean arg1) {
        this.zzb(((byte)(((int)arg1))));
    }

    public static int zzd(zzih arg1) {
        int v1 = arg1.zzdg();
        return zzgf.zzau(v1) + v1;
    }

    @Deprecated static int zzd(int arg2, zzih arg3, zziy arg4) {
        arg2 = zzgf.zzas(arg2) << 1;
        int v0 = ((zzfh)arg3).zzay();
        if(v0 == -1) {
            v0 = arg4.zzn(arg3);
            ((zzfh)arg3).zzv(v0);
        }

        return arg2 + v0;
    }

    public static int zzd(int arg0, boolean arg1) {
        return zzgf.zzas(arg0) + 1;
    }

    public static int zzd(int arg1, zzfr arg2) {
        arg1 = zzgf.zzas(arg1);
        int v2 = arg2.size();
        return arg1 + (zzgf.zzau(v2) + v2);
    }

    public static int zzd(int arg0, float arg1) {
        return zzgf.zzas(arg0) + 4;
    }

    public static int zzd(int arg0, zzih arg1) {
        return zzgf.zzas(arg0) + zzgf.zzd(arg1);
    }

    public abstract void zzd(int arg1, long arg2);

    public abstract void zze(long arg1);

    public static int zze(float arg0) {
        return 4;
    }

    @Deprecated public static int zze(zzih arg0) {
        return arg0.zzdg();
    }

    public static int zze(boolean arg0) {
        return 1;
    }

    public static int zze(byte[] arg1) {
        return zzgf.zzau(arg1.length) + arg1.length;
    }

    public static int zze(int arg2, zzih arg3) {
        return (zzgf.zzas(1) << 1) + zzgf.zzj(2, arg2) + zzgf.zzd(3, arg3);
    }

    public static int zze(int arg2, zzfr arg3) {
        return (zzgf.zzas(1) << 1) + zzgf.zzj(2, arg2) + zzgf.zzd(3, arg3);
    }

    public static int zze(int arg0, long arg1) {
        return zzgf.zzas(arg0) + zzgf.zzi(arg1);
    }

    public abstract void zze(int arg1, int arg2);

    public final void zzf(long arg1) {
        this.zze(zzgf.zzm(arg1));
    }

    public static int zzf(int arg0, long arg1) {
        return zzgf.zzas(arg0) + zzgf.zzi(arg1);
    }

    public abstract void zzf(int arg1, int arg2);

    abstract void zzg(byte[] arg1, int arg2, int arg3);

    public abstract void zzg(long arg1);

    public static int zzg(int arg0, long arg1) {
        return zzgf.zzas(arg0) + zzgf.zzi(zzgf.zzm(arg1));
    }

    public final void zzg(int arg1, int arg2) {
        this.zzf(arg1, zzgf.zzaz(arg2));
    }

    public static int zzh(long arg0) {
        return zzgf.zzi(arg0);
    }

    public static int zzh(int arg0, long arg1) {
        return zzgf.zzas(arg0) + 8;
    }

    public abstract void zzh(int arg1, int arg2);

    public static int zzi(long arg6) {
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

    public static int zzi(int arg0, int arg1) {
        return zzgf.zzas(arg0) + zzgf.zzat(arg1);
    }

    public static int zzi(int arg0, long arg1) {
        return zzgf.zzas(arg0) + 8;
    }

    public static int zzj(long arg0) {
        return zzgf.zzi(zzgf.zzm(arg0));
    }

    public static int zzj(int arg0, int arg1) {
        return zzgf.zzas(arg0) + zzgf.zzau(arg1);
    }

    public abstract void zzk(String arg1);

    public static int zzk(long arg0) {
        return 8;
    }

    public static int zzk(int arg0, int arg1) {
        return zzgf.zzas(arg0) + zzgf.zzau(zzgf.zzaz(arg1));
    }

    public static int zzl(String arg1) {
        int v0;
        try {
            v0 = zzjy.zzb(((CharSequence)arg1));
        }
        catch(zzkb ) {
            v0 = arg1.getBytes(zzhb.UTF_8).length;
        }

        return zzgf.zzau(v0) + v0;
    }

    public static int zzl(long arg0) {
        return 8;
    }

    public static int zzl(int arg0, int arg1) {
        return zzgf.zzas(arg0) + 4;
    }

    private static long zzm(long arg3) {
        return arg3 >> 63 ^ arg3 << 1;
    }

    public static int zzm(int arg0, int arg1) {
        return zzgf.zzas(arg0) + 4;
    }

    public static int zzn(int arg0, int arg1) {
        return zzgf.zzas(arg0) + zzgf.zzat(arg1);
    }
}

