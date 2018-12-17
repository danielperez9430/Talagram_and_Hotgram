package com.google.firebase.b;

import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk public class a {
    private final Class a;
    private final Object b;

    @KeepForSdk public Class a() {
        return this.a;
    }

    @KeepForSdk public Object b() {
        return this.b;
    }

    public String toString() {
        return String.format("Event{type: %s, payload: %s}", this.a, this.b);
    }
}

