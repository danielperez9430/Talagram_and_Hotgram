package com.google.android.gms.internal.measurement;

import java.io.IOException;

public final class zzyz extends IOException {
    zzyz(int arg3, int arg4) {
        StringBuilder v0 = new StringBuilder(108);
        v0.append("CodedOutputStream was writing to a flat byte array and ran out of space (pos ");
        v0.append(arg3);
        v0.append(" limit ");
        v0.append(arg4);
        v0.append(").");
        super(v0.toString());
    }
}

