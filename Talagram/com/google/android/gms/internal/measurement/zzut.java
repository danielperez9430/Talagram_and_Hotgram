package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class zzut extends zzuc {
    class zza extends zzut {
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

        public final void zza(int arg2, long arg3) {
            ((zzut)this).zzc(arg2, 0);
            ((zzut)this).zzav(arg3);
        }

        public final void zza(int arg2, zzud arg3) {
            ((zzut)this).zzc(arg2, 2);
            ((zzut)this).zza(arg3);
        }

        public final void zza(int arg2, zzwt arg3) {
            ((zzut)this).zzc(arg2, 2);
            ((zzut)this).zzb(arg3);
        }

        final void zza(int arg3, zzwt arg4, zzxj arg5) {
            ((zzut)this).zzc(arg3, 2);
            zzwt v3 = arg4;
            int v0 = ((zztw)v3).zztu();
            if(v0 == -1) {
                v0 = arg5.zzae(v3);
                ((zztw)v3).zzah(v0);
            }

            ((zzut)this).zzay(v0);
            arg5.zza(arg4, this.zzbuw);
        }

        public final void zza(zzud arg2) {
            ((zzut)this).zzay(arg2.size());
            arg2.zza(((zzuc)this));
        }

        final void zza(zzwt arg4, zzxj arg5) {
            zzwt v0 = arg4;
            int v1 = ((zztw)v0).zztu();
            if(v1 == -1) {
                v1 = arg5.zzae(v0);
                ((zztw)v0).zzah(v1);
            }

            ((zzut)this).zzay(v1);
            arg5.zza(arg4, this.zzbuw);
        }

        public final void zza(byte[] arg1, int arg2, int arg3) {
            ((zzut)this).write(arg1, arg2, arg3);
        }

        public final void zzav(long arg10) {
            int v6;
            byte[] v0;
            int v1 = 7;
            long v2 = 0;
            long v4 = -128;
            if(zzut.zzvh()) {
                if(((zzut)this).zzvg() < 10) {
                    goto label_31;
                }

                while((arg10 & v4) != v2) {
                    v0 = this.buffer;
                    v6 = this.position;
                    this.position = v6 + 1;
                    zzyh.zza(v0, ((long)v6), ((byte)((((int)arg10)) & 127 | 128)));
                    arg10 >>>= v1;
                }

                v0 = this.buffer;
                v1 = this.position;
                this.position = v1 + 1;
                zzyh.zza(v0, ((long)v1), ((byte)(((int)arg10))));
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

        public final void zzax(int arg3) {
            if(arg3 >= 0) {
                ((zzut)this).zzay(arg3);
                return;
            }

            ((zzut)this).zzav(((long)arg3));
        }

        public final void zzax(long arg5) {
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

        public final void zzay(int arg5) {
            int v1;
            byte[] v0;
            if(zzut.zzvh()) {
                if(((zzut)this).zzvg() < 10) {
                    goto label_26;
                }

                while((arg5 & -128) != 0) {
                    v0 = this.buffer;
                    v1 = this.position;
                    this.position = v1 + 1;
                    zzyh.zza(v0, ((long)v1), ((byte)(arg5 & 127 | 128)));
                    arg5 >>>= 7;
                }

                v0 = this.buffer;
                v1 = this.position;
                this.position = v1 + 1;
                zzyh.zza(v0, ((long)v1), ((byte)arg5));
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

        public final void zzb(int arg4, zzud arg5) {
            ((zzut)this).zzc(1, 3);
            ((zzut)this).zze(2, arg4);
            ((zzut)this).zza(3, arg5);
            ((zzut)this).zzc(1, 4);
        }

        public final void zzb(int arg4, zzwt arg5) {
            ((zzut)this).zzc(1, 3);
            ((zzut)this).zze(2, arg4);
            ((zzut)this).zza(3, arg5);
            ((zzut)this).zzc(1, 4);
        }

        public final void zzb(int arg2, String arg3) {
            ((zzut)this).zzc(arg2, 2);
            ((zzut)this).zzfw(arg3);
        }

        public final void zzb(int arg2, boolean arg3) {
            ((zzut)this).zzc(arg2, 0);
            ((zzut)this).zzc(((byte)(((int)arg3))));
        }

        public final void zzb(zzwt arg2) {
            ((zzut)this).zzay(arg2.zzvu());
            arg2.zzb(((zzut)this));
        }

        public final void zzba(int arg5) {
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

        public final void zzc(byte arg5) {
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

        public final void zzc(int arg1, int arg2) {
            ((zzut)this).zzay(arg1 << 3 | arg2);
        }

        public final void zzc(int arg2, long arg3) {
            ((zzut)this).zzc(arg2, 1);
            ((zzut)this).zzax(arg3);
        }

        public final void zzd(int arg2, int arg3) {
            ((zzut)this).zzc(arg2, 0);
            ((zzut)this).zzax(arg3);
        }

        public final void zze(int arg2, int arg3) {
            ((zzut)this).zzc(arg2, 0);
            ((zzut)this).zzay(arg3);
        }

        public final void zze(byte[] arg1, int arg2, int arg3) {
            ((zzut)this).zzay(arg3);
            ((zzut)this).write(arg1, 0, arg3);
        }

        public final void zzfw(String arg6) {
            int v0 = this.position;
            try {
                int v1_1 = zza.zzbd(arg6.length() * 3);
                int v2 = zza.zzbd(arg6.length());
                if(v2 == v1_1) {
                    this.position = v0 + v2;
                    v1_1 = zzyj.zza(((CharSequence)arg6), this.buffer, this.position, ((zzut)this).zzvg());
                    this.position = v0;
                    ((zzut)this).zzay(v1_1 - v0 - v2);
                    this.position = v1_1;
                    return;
                }

                ((zzut)this).zzay(zzyj.zza(((CharSequence)arg6)));
                this.position = zzyj.zza(((CharSequence)arg6), this.buffer, this.position, ((zzut)this).zzvg());
                return;
            }
            catch(IndexOutOfBoundsException v6) {
                throw new zzc(((Throwable)v6));
            }
            catch(zzyn v1) {
                this.position = v0;
                ((zzut)this).zza(arg6, v1);
                return;
            }
        }

        public final void zzg(int arg2, int arg3) {
            ((zzut)this).zzc(arg2, 5);
            ((zzut)this).zzba(arg3);
        }

        public final int zzvg() {
            return this.limit - this.position;
        }

        public final int zzvi() {
            return this.position - this.offset;
        }
    }

    final class zzb extends zza {
        private final ByteBuffer zzbux;
        private int zzbuy;

        zzb(ByteBuffer arg4) {
            super(arg4.array(), arg4.arrayOffset() + arg4.position(), arg4.remaining());
            this.zzbux = arg4;
            this.zzbuy = arg4.position();
        }

        public final void flush() {
            this.zzbux.position(this.zzbuy + ((zza)this).zzvi());
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

    final class zzd extends zzut {
        private final int zzbuy;
        private final ByteBuffer zzbuz;
        private final ByteBuffer zzbva;

        zzd(ByteBuffer arg3) {
            super(null);
            this.zzbuz = arg3;
            this.zzbva = arg3.duplicate().order(ByteOrder.LITTLE_ENDIAN);
            this.zzbuy = arg3.position();
        }

        public final void flush() {
            this.zzbuz.position(this.zzbva.position());
        }

        public final void write(byte[] arg2, int arg3, int arg4) {
            try {
                this.zzbva.put(arg2, arg3, arg4);
                return;
            }
            catch(BufferOverflowException v2) {
                throw new zzc(((Throwable)v2));
            }
            catch(IndexOutOfBoundsException v2_1) {
                throw new zzc(((Throwable)v2_1));
            }
        }

        public final void zza(int arg2, long arg3) {
            ((zzut)this).zzc(arg2, 0);
            ((zzut)this).zzav(arg3);
        }

        public final void zza(int arg2, zzud arg3) {
            ((zzut)this).zzc(arg2, 2);
            ((zzut)this).zza(arg3);
        }

        public final void zza(int arg2, zzwt arg3) {
            ((zzut)this).zzc(arg2, 2);
            ((zzut)this).zzb(arg3);
        }

        final void zza(int arg2, zzwt arg3, zzxj arg4) {
            ((zzut)this).zzc(arg2, 2);
            ((zzut)this).zza(arg3, arg4);
        }

        public final void zza(zzud arg2) {
            ((zzut)this).zzay(arg2.size());
            arg2.zza(((zzuc)this));
        }

        final void zza(zzwt arg4, zzxj arg5) {
            zzwt v0 = arg4;
            int v1 = ((zztw)v0).zztu();
            if(v1 == -1) {
                v1 = arg5.zzae(v0);
                ((zztw)v0).zzah(v1);
            }

            ((zzut)this).zzay(v1);
            arg5.zza(arg4, this.zzbuw);
        }

        public final void zza(byte[] arg1, int arg2, int arg3) {
            ((zzut)this).write(arg1, arg2, arg3);
        }

        public final void zzav(long arg6) {
            while(true) {
                if((-128 & arg6) == 0) {
                    goto label_4;
                }

                try {
                    this.zzbva.put(((byte)((((int)arg6)) & 127 | 128)));
                    arg6 >>>= 7;
                    continue;
                label_4:
                    this.zzbva.put(((byte)(((int)arg6))));
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

        public final void zzax(int arg3) {
            if(arg3 >= 0) {
                ((zzut)this).zzay(arg3);
                return;
            }

            ((zzut)this).zzav(((long)arg3));
        }

        public final void zzax(long arg2) {
            try {
                this.zzbva.putLong(arg2);
                return;
            }
            catch(BufferOverflowException v2) {
                throw new zzc(((Throwable)v2));
            }
        }

        public final void zzay(int arg3) {
            while(true) {
                if((arg3 & -128) == 0) {
                    goto label_2;
                }

                try {
                    this.zzbva.put(((byte)(arg3 & 127 | 128)));
                    arg3 >>>= 7;
                    continue;
                label_2:
                    this.zzbva.put(((byte)arg3));
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

        public final void zzb(int arg4, zzud arg5) {
            ((zzut)this).zzc(1, 3);
            ((zzut)this).zze(2, arg4);
            ((zzut)this).zza(3, arg5);
            ((zzut)this).zzc(1, 4);
        }

        public final void zzb(int arg4, zzwt arg5) {
            ((zzut)this).zzc(1, 3);
            ((zzut)this).zze(2, arg4);
            ((zzut)this).zza(3, arg5);
            ((zzut)this).zzc(1, 4);
        }

        public final void zzb(int arg2, String arg3) {
            ((zzut)this).zzc(arg2, 2);
            ((zzut)this).zzfw(arg3);
        }

        public final void zzb(int arg2, boolean arg3) {
            ((zzut)this).zzc(arg2, 0);
            ((zzut)this).zzc(((byte)(((int)arg3))));
        }

        public final void zzb(zzwt arg2) {
            ((zzut)this).zzay(arg2.zzvu());
            arg2.zzb(((zzut)this));
        }

        public final void zzba(int arg2) {
            try {
                this.zzbva.putInt(arg2);
                return;
            }
            catch(BufferOverflowException v2) {
                throw new zzc(((Throwable)v2));
            }
        }

        public final void zzc(byte arg2) {
            try {
                this.zzbva.put(arg2);
                return;
            }
            catch(BufferOverflowException v2) {
                throw new zzc(((Throwable)v2));
            }
        }

        public final void zzc(int arg1, int arg2) {
            ((zzut)this).zzay(arg1 << 3 | arg2);
        }

        public final void zzc(int arg2, long arg3) {
            ((zzut)this).zzc(arg2, 1);
            ((zzut)this).zzax(arg3);
        }

        public final void zzd(int arg2, int arg3) {
            ((zzut)this).zzc(arg2, 0);
            ((zzut)this).zzax(arg3);
        }

        public final void zze(int arg2, int arg3) {
            ((zzut)this).zzc(arg2, 0);
            ((zzut)this).zzay(arg3);
        }

        public final void zze(byte[] arg1, int arg2, int arg3) {
            ((zzut)this).zzay(arg3);
            ((zzut)this).write(arg1, 0, arg3);
        }

        public final void zzfw(String arg5) {
            int v0 = this.zzbva.position();
            try {
                int v1_1 = zzd.zzbd(arg5.length() * 3);
                int v2 = zzd.zzbd(arg5.length());
                if(v2 == v1_1) {
                    v1_1 = this.zzbva.position() + v2;
                    this.zzbva.position(v1_1);
                    this.zzfy(arg5);
                    v2 = this.zzbva.position();
                    this.zzbva.position(v0);
                    ((zzut)this).zzay(v2 - v1_1);
                    this.zzbva.position(v2);
                    return;
                }

                ((zzut)this).zzay(zzyj.zza(((CharSequence)arg5)));
                this.zzfy(arg5);
                return;
            }
            catch(IllegalArgumentException v5) {
                throw new zzc(((Throwable)v5));
            }
            catch(zzyn v1) {
                this.zzbva.position(v0);
                ((zzut)this).zza(arg5, v1);
                return;
            }
        }

        private final void zzfy(String arg2) {
            try {
                zzyj.zza(((CharSequence)arg2), this.zzbva);
                return;
            }
            catch(IndexOutOfBoundsException v2) {
                throw new zzc(((Throwable)v2));
            }
        }

        public final void zzg(int arg2, int arg3) {
            ((zzut)this).zzc(arg2, 5);
            ((zzut)this).zzba(arg3);
        }

        public final int zzvg() {
            return this.zzbva.remaining();
        }
    }

    final class zze extends zzut {
        private final ByteBuffer zzbuz;
        private final ByteBuffer zzbva;
        private final long zzbvb;
        private final long zzbvc;
        private final long zzbvd;
        private final long zzbve;
        private long zzbvf;

        zze(ByteBuffer arg5) {
            super(null);
            this.zzbuz = arg5;
            this.zzbva = arg5.duplicate().order(ByteOrder.LITTLE_ENDIAN);
            this.zzbvb = zzyh.zzb(arg5);
            this.zzbvc = this.zzbvb + (((long)arg5.position()));
            this.zzbvd = this.zzbvb + (((long)arg5.limit()));
            this.zzbve = this.zzbvd - 10;
            this.zzbvf = this.zzbvc;
        }

        public final void flush() {
            this.zzbuz.position(((int)(this.zzbvf - this.zzbvb)));
        }

        public final void write(byte[] arg12, int arg13, int arg14) {
            if(arg12 != null && arg13 >= 0 && arg14 >= 0 && arg12.length - arg14 >= arg13) {
                long v9 = ((long)arg14);
                if(this.zzbvd - v9 < this.zzbvf) {
                }
                else {
                    zzyh.zza(arg12, ((long)arg13), this.zzbvf, v9);
                    this.zzbvf += v9;
                    return;
                }
            }

            if(arg12 == null) {
                throw new NullPointerException("value");
            }

            throw new zzc(String.format("Pos: %d, limit: %d, len: %d", Long.valueOf(this.zzbvf), Long.valueOf(this.zzbvd), Integer.valueOf(arg14)));
        }

        public final void zza(int arg2, long arg3) {
            ((zzut)this).zzc(arg2, 0);
            ((zzut)this).zzav(arg3);
        }

        public final void zza(int arg2, zzud arg3) {
            ((zzut)this).zzc(arg2, 2);
            ((zzut)this).zza(arg3);
        }

        public final void zza(int arg2, zzwt arg3) {
            ((zzut)this).zzc(arg2, 2);
            ((zzut)this).zzb(arg3);
        }

        final void zza(int arg2, zzwt arg3, zzxj arg4) {
            ((zzut)this).zzc(arg2, 2);
            ((zzut)this).zza(arg3, arg4);
        }

        public final void zza(zzud arg2) {
            ((zzut)this).zzay(arg2.size());
            arg2.zza(((zzuc)this));
        }

        final void zza(zzwt arg4, zzxj arg5) {
            zzwt v0 = arg4;
            int v1 = ((zztw)v0).zztu();
            if(v1 == -1) {
                v1 = arg5.zzae(v0);
                ((zztw)v0).zzah(v1);
            }

            ((zzut)this).zzay(v1);
            arg5.zza(arg4, this.zzbuw);
        }

        public final void zza(byte[] arg1, int arg2, int arg3) {
            ((zzut)this).write(arg1, arg2, arg3);
        }

        public final void zzav(long arg13) {
            // Method was not decompiled
        }

        public final void zzax(int arg3) {
            if(arg3 >= 0) {
                ((zzut)this).zzay(arg3);
                return;
            }

            ((zzut)this).zzav(((long)arg3));
        }

        public final void zzax(long arg6) {
            this.zzbva.putLong(((int)(this.zzbvf - this.zzbvb)), arg6);
            this.zzbvf += 8;
        }

        public final void zzay(int arg8) {
            // Method was not decompiled
        }

        public final void zzb(int arg4, zzud arg5) {
            ((zzut)this).zzc(1, 3);
            ((zzut)this).zze(2, arg4);
            ((zzut)this).zza(3, arg5);
            ((zzut)this).zzc(1, 4);
        }

        public final void zzb(int arg4, zzwt arg5) {
            ((zzut)this).zzc(1, 3);
            ((zzut)this).zze(2, arg4);
            ((zzut)this).zza(3, arg5);
            ((zzut)this).zzc(1, 4);
        }

        public final void zzb(int arg2, String arg3) {
            ((zzut)this).zzc(arg2, 2);
            ((zzut)this).zzfw(arg3);
        }

        public final void zzb(int arg2, boolean arg3) {
            ((zzut)this).zzc(arg2, 0);
            ((zzut)this).zzc(((byte)(((int)arg3))));
        }

        public final void zzb(zzwt arg2) {
            ((zzut)this).zzay(arg2.zzvu());
            arg2.zzb(((zzut)this));
        }

        public final void zzba(int arg6) {
            this.zzbva.putInt(((int)(this.zzbvf - this.zzbvb)), arg6);
            this.zzbvf += 4;
        }

        private final void zzbe(long arg4) {
            this.zzbva.position(((int)(arg4 - this.zzbvb)));
        }

        public final void zzc(byte arg6) {
            if(this.zzbvf < this.zzbvd) {
                long v0 = this.zzbvf;
                this.zzbvf = 1 + v0;
                zzyh.zza(v0, arg6);
                return;
            }

            throw new zzc(String.format("Pos: %d, limit: %d, len: %d", Long.valueOf(this.zzbvf), Long.valueOf(this.zzbvd), Integer.valueOf(1)));
        }

        public final void zzc(int arg1, int arg2) {
            ((zzut)this).zzay(arg1 << 3 | arg2);
        }

        public final void zzc(int arg2, long arg3) {
            ((zzut)this).zzc(arg2, 1);
            ((zzut)this).zzax(arg3);
        }

        public final void zzd(int arg2, int arg3) {
            ((zzut)this).zzc(arg2, 0);
            ((zzut)this).zzax(arg3);
        }

        public final void zze(int arg2, int arg3) {
            ((zzut)this).zzc(arg2, 0);
            ((zzut)this).zzay(arg3);
        }

        public final void zze(byte[] arg1, int arg2, int arg3) {
            ((zzut)this).zzay(arg3);
            ((zzut)this).write(arg1, 0, arg3);
        }

        public final void zzfw(String arg9) {
            long v0 = this.zzbvf;
            try {
                int v2_1 = zze.zzbd(arg9.length() * 3);
                int v3 = zze.zzbd(arg9.length());
                if(v3 == v2_1) {
                    v2_1 = (((int)(this.zzbvf - this.zzbvb))) + v3;
                    this.zzbva.position(v2_1);
                    zzyj.zza(((CharSequence)arg9), this.zzbva);
                    v3 = this.zzbva.position() - v2_1;
                    ((zzut)this).zzay(v3);
                    this.zzbvf += ((long)v3);
                    return;
                }

                v2_1 = zzyj.zza(((CharSequence)arg9));
                ((zzut)this).zzay(v2_1);
                this.zzbe(this.zzbvf);
                zzyj.zza(((CharSequence)arg9), this.zzbva);
                this.zzbvf += ((long)v2_1);
                return;
            }
            catch(IndexOutOfBoundsException v9) {
                throw new zzc(((Throwable)v9));
            }
            catch(IllegalArgumentException v9_1) {
                throw new zzc(((Throwable)v9_1));
            }
            catch(zzyn v2) {
                this.zzbvf = v0;
                this.zzbe(this.zzbvf);
                ((zzut)this).zza(arg9, v2);
                return;
            }
        }

        public final void zzg(int arg2, int arg3) {
            ((zzut)this).zzc(arg2, 5);
            ((zzut)this).zzba(arg3);
        }

        public final int zzvg() {
            return ((int)(this.zzbvd - this.zzbvf));
        }
    }

    private static final Logger logger;
    private static final boolean zzbuv;
    zzuv zzbuw;

    static {
        zzut.logger = Logger.getLogger(zzut.class.getName());
        zzut.zzbuv = zzyh.zzyi();
    }

    private zzut() {
        super();
    }

    zzut(zzuu arg1) {
        this();
    }

    public abstract void flush();

    public abstract void write(byte[] arg1, int arg2, int arg3);

    public abstract void zza(zzud arg1);

    public final void zza(float arg1) {
        this.zzba(Float.floatToRawIntBits(arg1));
    }

    public static int zza(zzwa arg1) {
        int v1 = arg1.zzvu();
        return zzut.zzbd(v1) + v1;
    }

    public static int zza(int arg1, zzwa arg2) {
        arg1 = zzut.zzbb(arg1);
        int v2 = arg2.zzvu();
        return arg1 + (zzut.zzbd(v2) + v2);
    }

    public static zzut zza(ByteBuffer arg1) {
        if(arg1.hasArray()) {
            return new zzb(arg1);
        }

        if((arg1.isDirect()) && !arg1.isReadOnly()) {
            if(zzyh.zzyj()) {
                return new zze(arg1);
            }
            else {
                return new zzd(arg1);
            }
        }

        throw new IllegalArgumentException("ByteBuffer is read-only");
    }

    public abstract void zza(int arg1, zzwt arg2);

    public final void zza(int arg1, double arg2) {
        this.zzc(arg1, Double.doubleToRawLongBits(arg2));
    }

    public final void zza(int arg1, float arg2) {
        this.zzg(arg1, Float.floatToRawIntBits(arg2));
    }

    public abstract void zza(int arg1, long arg2);

    public abstract void zza(int arg1, zzud arg2);

    abstract void zza(int arg1, zzwt arg2, zzxj arg3);

    abstract void zza(zzwt arg1, zzxj arg2);

    final void zza(String arg7, zzyn arg8) {
        zzut.logger.logp(Level.WARNING, "com.google.protobuf.CodedOutputStream", "inefficientWriteStringNoTag", "Converting ill-formed UTF-16. Your Protocol Buffer will not round trip correctly!", arg8);
        byte[] v7 = arg7.getBytes(zzvo.UTF_8);
        try {
            this.zzay(v7.length);
            ((zzuc)this).zza(v7, 0, v7.length);
            return;
        }
        catch(zzc v7_1) {
            throw v7_1;
        }
        catch(IndexOutOfBoundsException v7_2) {
            throw new zzc(((Throwable)v7_2));
        }
    }

    public abstract void zzav(long arg1);

    public final void zzaw(long arg1) {
        this.zzav(zzut.zzbd(arg1));
    }

    public abstract void zzax(int arg1);

    public abstract void zzax(long arg1);

    public abstract void zzay(int arg1);

    public static int zzay(long arg0) {
        return zzut.zzaz(arg0);
    }

    public final void zzaz(int arg1) {
        this.zzay(zzut.zzbi(arg1));
    }

    public static int zzaz(long arg6) {
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

    public final void zzb(double arg1) {
        this.zzax(Double.doubleToRawLongBits(arg1));
    }

    public abstract void zzb(zzwt arg1);

    public static int zzb(float arg0) {
        return 4;
    }

    public static int zzb(zzud arg1) {
        int v1 = arg1.size();
        return zzut.zzbd(v1) + v1;
    }

    public static int zzb(int arg2, zzwa arg3) {
        return (zzut.zzbb(1) << 1) + zzut.zzi(2, arg2) + zzut.zza(3, arg3);
    }

    static int zzb(int arg0, zzwt arg1, zzxj arg2) {
        return zzut.zzbb(arg0) + zzut.zzb(arg1, arg2);
    }

    static int zzb(zzwt arg2, zzxj arg3) {
        int v0 = ((zztw)arg2).zztu();
        if(v0 == -1) {
            v0 = arg3.zzae(arg2);
            ((zztw)arg2).zzah(v0);
        }

        return zzut.zzbd(v0) + v0;
    }

    public static int zzb(int arg0, double arg1) {
        return zzut.zzbb(arg0) + 8;
    }

    public static int zzb(int arg0, float arg1) {
        return zzut.zzbb(arg0) + 4;
    }

    public final void zzb(int arg1, long arg2) {
        this.zza(arg1, zzut.zzbd(arg2));
    }

    public abstract void zzb(int arg1, zzud arg2);

    public abstract void zzb(int arg1, zzwt arg2);

    public abstract void zzb(int arg1, String arg2);

    public abstract void zzb(int arg1, boolean arg2);

    public abstract void zzba(int arg1);

    public static int zzba(long arg0) {
        return zzut.zzaz(zzut.zzbd(arg0));
    }

    public static int zzbb(int arg0) {
        return zzut.zzbd(arg0 << 3);
    }

    public static int zzbb(long arg0) {
        return 8;
    }

    public static int zzbc(long arg0) {
        return 8;
    }

    public static int zzbc(int arg0) {
        if(arg0 >= 0) {
            return zzut.zzbd(arg0);
        }

        return 10;
    }

    public static int zzbd(int arg1) {
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

    private static long zzbd(long arg3) {
        return arg3 >> 63 ^ arg3 << 1;
    }

    public static int zzbe(int arg0) {
        return zzut.zzbd(zzut.zzbi(arg0));
    }

    public static int zzbf(int arg0) {
        return 4;
    }

    public static int zzbg(int arg0) {
        return 4;
    }

    public static int zzbh(int arg0) {
        return zzut.zzbc(arg0);
    }

    private static int zzbi(int arg1) {
        return arg1 >> 31 ^ arg1 << 1;
    }

    @Deprecated public static int zzbj(int arg0) {
        return zzut.zzbd(arg0);
    }

    public abstract void zzc(int arg1, int arg2);

    public static int zzc(double arg0) {
        return 8;
    }

    public static int zzc(zzwt arg1) {
        int v1 = arg1.zzvu();
        return zzut.zzbd(v1) + v1;
    }

    @Deprecated static int zzc(int arg2, zzwt arg3, zzxj arg4) {
        arg2 = zzut.zzbb(arg2) << 1;
        int v0 = ((zztw)arg3).zztu();
        if(v0 == -1) {
            v0 = arg4.zzae(arg3);
            ((zztw)arg3).zzah(v0);
        }

        return arg2 + v0;
    }

    public static int zzc(int arg0, boolean arg1) {
        return zzut.zzbb(arg0) + 1;
    }

    public static int zzc(int arg1, zzud arg2) {
        arg1 = zzut.zzbb(arg1);
        int v2 = arg2.size();
        return arg1 + (zzut.zzbd(v2) + v2);
    }

    public static int zzc(int arg0, zzwt arg1) {
        return zzut.zzbb(arg0) + zzut.zzc(arg1);
    }

    public static int zzc(int arg0, String arg1) {
        return zzut.zzbb(arg0) + zzut.zzfx(arg1);
    }

    public abstract void zzc(int arg1, long arg2);

    public abstract void zzc(byte arg1);

    @Deprecated public static int zzd(zzwt arg0) {
        return arg0.zzvu();
    }

    public static int zzd(int arg2, zzwt arg3) {
        return (zzut.zzbb(1) << 1) + zzut.zzi(2, arg2) + zzut.zzc(3, arg3);
    }

    public static int zzd(int arg2, zzud arg3) {
        return (zzut.zzbb(1) << 1) + zzut.zzi(2, arg2) + zzut.zzc(3, arg3);
    }

    public static int zzd(int arg0, long arg1) {
        return zzut.zzbb(arg0) + zzut.zzaz(arg1);
    }

    public abstract void zzd(int arg1, int arg2);

    abstract void zze(byte[] arg1, int arg2, int arg3);

    public static int zze(int arg0, long arg1) {
        return zzut.zzbb(arg0) + zzut.zzaz(arg1);
    }

    public abstract void zze(int arg1, int arg2);

    public static int zzf(int arg0, long arg1) {
        return zzut.zzbb(arg0) + zzut.zzaz(zzut.zzbd(arg1));
    }

    public final void zzf(int arg1, int arg2) {
        this.zze(arg1, zzut.zzbi(arg2));
    }

    public abstract void zzfw(String arg1);

    public static int zzfx(String arg1) {
        int v0;
        try {
            v0 = zzyj.zza(((CharSequence)arg1));
        }
        catch(zzyn ) {
            v0 = arg1.getBytes(zzvo.UTF_8).length;
        }

        return zzut.zzbd(v0) + v0;
    }

    public static int zzg(int arg0, long arg1) {
        return zzut.zzbb(arg0) + 8;
    }

    public abstract void zzg(int arg1, int arg2);

    public static int zzh(int arg0, int arg1) {
        return zzut.zzbb(arg0) + zzut.zzbc(arg1);
    }

    public static int zzh(int arg0, long arg1) {
        return zzut.zzbb(arg0) + 8;
    }

    public static int zzi(int arg0, int arg1) {
        return zzut.zzbb(arg0) + zzut.zzbd(arg1);
    }

    public static zzut zzj(byte[] arg3) {
        return new zza(arg3, 0, arg3.length);
    }

    public static int zzj(int arg0, int arg1) {
        return zzut.zzbb(arg0) + zzut.zzbd(zzut.zzbi(arg1));
    }

    public static int zzk(byte[] arg1) {
        return zzut.zzbd(arg1.length) + arg1.length;
    }

    public static int zzk(int arg0, int arg1) {
        return zzut.zzbb(arg0) + 4;
    }

    public static int zzl(int arg0, int arg1) {
        return zzut.zzbb(arg0) + 4;
    }

    public static int zzm(int arg0, int arg1) {
        return zzut.zzbb(arg0) + zzut.zzbc(arg1);
    }

    public final void zzu(boolean arg1) {
        this.zzc(((byte)(((int)arg1))));
    }

    public static int zzv(boolean arg0) {
        return 1;
    }

    public abstract int zzvg();

    static boolean zzvh() {
        return zzut.zzbuv;
    }
}

