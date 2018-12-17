package com.google.android.gms.internal.places;

final class zzfu extends zzfy {
    private final int zznx;
    private final int zzny;

    zzfu(byte[] arg2, int arg3, int arg4) {
        super(arg2);
        zzfu.zzc(arg3, arg3 + arg4, arg2.length);
        this.zznx = arg3;
        this.zzny = arg4;
    }

    public final int size() {
        return this.zzny;
    }

    public final byte zzaf(int arg5) {
        int v0 = ((zzfr)this).size();
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

        return this.zzoa[this.zznx + arg5];
    }

    protected final void zzb(byte[] arg2, int arg3, int arg4, int arg5) {
        System.arraycopy(this.zzoa, ((zzfy)this).zzcg(), arg2, 0, arg5);
    }

    protected final int zzcg() {
        return this.zznx;
    }
}

