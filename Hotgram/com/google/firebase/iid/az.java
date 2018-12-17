package com.google.firebase.iid;

import android.util.Base64;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.util.VisibleForTesting;
import java.security.KeyPair;

final class az {
    private final KeyPair a;
    private final long b;

    @VisibleForTesting az(KeyPair arg1, long arg2) {
        super();
        this.a = arg1;
        this.b = arg2;
    }

    final KeyPair a() {
        return this.a;
    }

    static String a(az arg0) {
        return arg0.b();
    }

    static String b(az arg0) {
        return arg0.c();
    }

    private final String b() {
        return Base64.encodeToString(this.a.getPublic().getEncoded(), 11);
    }

    static long c(az arg2) {
        return arg2.b;
    }

    private final String c() {
        return Base64.encodeToString(this.a.getPrivate().getEncoded(), 11);
    }

    public final boolean equals(Object arg7) {
        if(!(arg7 instanceof az)) {
            return 0;
        }

        if(this.b == ((az)arg7).b && (this.a.getPublic().equals(((az)arg7).a.getPublic())) && (this.a.getPrivate().equals(((az)arg7).a.getPrivate()))) {
            return 1;
        }

        return 0;
    }

    public final int hashCode() {
        return Objects.hashCode(new Object[]{this.a.getPublic(), this.a.getPrivate(), Long.valueOf(this.b)});
    }
}

