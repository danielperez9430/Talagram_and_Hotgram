package com.google.android.gms.internal.clearcut;

final class zzbe extends zzbi {
    private final int zzfm;
    private final int zzfn;

    zzbe(byte[] arg2, int arg3, int arg4) {
        super(arg2);
        zzbe.zzb(arg3, arg3 + arg4, arg2.length);
        this.zzfm = arg3;
        this.zzfn = arg4;
    }

    public final int size() {
        return this.zzfn;
    }

    protected final int zzac() {
        return this.zzfm;
    }

    public final byte zzj(int arg5) {
        int v0 = ((zzbb)this).size();
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

        return this.zzfp[this.zzfm + arg5];
    }
}

