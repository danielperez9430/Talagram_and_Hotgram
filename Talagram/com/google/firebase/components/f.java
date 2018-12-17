package com.google.firebase.components;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;

@KeepForSdk public final class f {
    private final Class a;
    private final int b;
    private final int c;

    private f(Class arg2, int arg3, int arg4) {
        super();
        this.a = Preconditions.checkNotNull(arg2, "Null dependency anInterface.");
        this.b = arg3;
        this.c = arg4;
    }

    public final Class a() {
        return this.a;
    }

    @KeepForSdk public static f a(Class arg3) {
        return new f(arg3, 1, 0);
    }

    public final boolean b() {
        if(this.b == 1) {
            return 1;
        }

        return 0;
    }

    public final boolean c() {
        if(this.c == 0) {
            return 1;
        }

        return 0;
    }

    public final boolean equals(Object arg4) {
        if(((arg4 instanceof f)) && this.a == ((f)arg4).a && this.b == ((f)arg4).b && this.c == ((f)arg4).c) {
            return 1;
        }

        return 0;
    }

    public final int hashCode() {
        return ((this.a.hashCode() ^ 1000003) * 1000003 ^ this.b) * 1000003 ^ this.c;
    }

    public final String toString() {
        StringBuilder v0 = new StringBuilder("Dependency{anInterface=");
        v0.append(this.a);
        v0.append(", required=");
        boolean v2 = false;
        boolean v1 = this.b == 1 ? true : false;
        v0.append(v1);
        v0.append(", direct=");
        if(this.c == 0) {
            v2 = true;
        }

        v0.append(v2);
        v0.append("}");
        return v0.toString();
    }
}

