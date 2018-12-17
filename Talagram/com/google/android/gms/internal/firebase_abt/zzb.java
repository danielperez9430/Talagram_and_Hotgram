package com.google.android.gms.internal.firebase_abt;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public final class zzb {
    private final ByteBuffer zzr;

    private zzb(ByteBuffer arg2) {
        super();
        this.zzr = arg2;
        this.zzr.order(ByteOrder.LITTLE_ENDIAN);
    }

    private zzb(byte[] arg1, int arg2, int arg3) {
        this(ByteBuffer.wrap(arg1, 0, arg3));
    }

    public static zzb zzb(byte[] arg3) {
        return new zzb(arg3, 0, arg3.length);
    }

    public final void zzc(byte[] arg4) {
        int v0 = arg4.length;
        if(this.zzr.remaining() >= v0) {
            this.zzr.put(arg4, 0, v0);
            return;
        }

        throw new zzc(this.zzr.position(), this.zzr.limit());
    }

    private final void zzd(int arg3) {
        byte v3 = ((byte)arg3);
        if(this.zzr.hasRemaining()) {
            this.zzr.put(v3);
            return;
        }

        throw new zzc(this.zzr.position(), this.zzr.limit());
    }

    public final void zze(int arg2) {
        while((arg2 & -128) != 0) {
            this.zzd(arg2 & 127 | 128);
            arg2 >>>= 7;
        }

        this.zzd(arg2);
    }

    public static int zzf(int arg1) {
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
}

