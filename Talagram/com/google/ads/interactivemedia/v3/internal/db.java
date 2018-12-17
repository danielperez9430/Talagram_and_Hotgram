package com.google.ads.interactivemedia.v3.internal;

import android.util.Log;
import android.util.Pair;
import java.util.UUID;

public final class db {
    public static UUID a(byte[] arg0) {
        Pair v0 = db.b(arg0);
        if(v0 == null) {
            return null;
        }

        return v0.first;
    }

    private static Pair b(byte[] arg9) {
        fp v0 = new fp(arg9);
        Pair v1 = null;
        if(v0.c() < 32) {
            return v1;
        }

        v0.c(0);
        if(v0.m() != v0.b() + 4) {
            return v1;
        }

        if(v0.m() != cv.U) {
            return v1;
        }

        int v2 = cv.a(v0.m());
        if(v2 > 1) {
            StringBuilder v3 = new StringBuilder(37);
            v3.append("Unsupported pssh version: ");
            v3.append(v2);
            Log.w("PsshAtomUtil", v3.toString());
            return v1;
        }

        UUID v4 = new UUID(v0.o(), v0.o());
        if(v2 == 1) {
            v0.d(v0.s() * 16);
        }

        v2 = v0.s();
        if(v2 != v0.b()) {
            return v1;
        }

        byte[] v1_1 = new byte[v2];
        v0.a(v1_1, 0, v2);
        return Pair.create(v4, v1_1);
    }
}

