package com.google.android.gms.internal.vision;

final class zzbr extends zzbv {
    private final int zzgx;
    private final int zzgy;

    zzbr(byte[] arg2, int arg3, int arg4) {
        super(arg2);
        zzbr.zzb(arg3, arg3 + arg4, arg2.length);
        this.zzgx = arg3;
        this.zzgy = arg4;
    }

    public final int size() {
        return this.zzgy;
    }

    protected final int zzav() {
        return this.zzgx;
    }

    public final byte zzl(int arg5) {
        int v0 = ((zzbo)this).size();
        if((v0 - (arg5 + 1) | arg5) < 0) {
            if(arg5 < 0) {
                StringBuilder v2 = new StringBuilder(22);
                v2.append("Index < 0: ");
                v2.append(arg5);
                throw new ArrayIndexOutOfBoundsException(v2.toString());
            }

            StringBuilder v3 = new StringBuilder(40);
            v3.append("Index > length: ");
            v3.append(arg5);
            v3.append(", ");
            v3.append(v0);
            throw new ArrayIndexOutOfBoundsException(v3.toString());
        }

        return this.zzha[this.zzgx + arg5];
    }
}

