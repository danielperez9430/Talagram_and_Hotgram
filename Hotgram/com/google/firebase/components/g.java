package com.google.firebase.components;

import com.google.android.gms.common.annotation.KeepForSdk;
import java.util.Arrays;
import java.util.List;

@KeepForSdk public class g extends h {
    private final List a;

    @KeepForSdk public g(List arg3) {
        StringBuilder v0 = new StringBuilder("Dependency cycle detected: ");
        v0.append(Arrays.toString(arg3.toArray()));
        super(v0.toString());
        this.a = arg3;
    }
}

