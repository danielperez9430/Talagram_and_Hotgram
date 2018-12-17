package com.google.android.gms.internal.measurement;

final class zzuh extends zzum {
    private final int zzbud;
    private final int zzbue;

    zzuh(byte[] arg2, int arg3, int arg4) {
        super(arg2);
        zzuh.zzb(arg3, arg3 + arg4, arg2.length);
        this.zzbud = arg3;
        this.zzbue = arg4;
    }

    public final int size() {
        return this.zzbue;
    }

    public final byte zzal(int arg5) {
        int v0 = ((zzud)this).size();
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

        return this.zzbug[this.zzbud + arg5];
    }

    protected final int zzud() {
        return this.zzbud;
    }
}

