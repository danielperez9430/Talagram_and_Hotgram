package com.google.android.gms.common;

import java.util.Arrays;

final class zzb extends CertData {
    private final byte[] zzbd;

    zzb(byte[] arg3) {
        super(Arrays.copyOfRange(arg3, 0, 25));
        this.zzbd = arg3;
    }

    final byte[] getBytes() {
        return this.zzbd;
    }
}

