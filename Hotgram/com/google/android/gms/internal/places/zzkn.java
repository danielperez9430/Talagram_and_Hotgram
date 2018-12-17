package com.google.android.gms.internal.places;

import java.io.IOException;

public final class zzkn extends IOException {
    zzkn(int arg3, int arg4) {
        StringBuilder v0 = new StringBuilder(108);
        v0.append("CodedOutputStream was writing to a flat byte array and ran out of space (pos ");
        v0.append(arg3);
        v0.append(" limit ");
        v0.append(arg4);
        v0.append(").");
        super(v0.toString());
    }
}

