package com.google.android.gms.internal.places;

final class zzkb extends IllegalArgumentException {
    zzkb(int arg3, int arg4) {
        StringBuilder v0 = new StringBuilder(54);
        v0.append("Unpaired surrogate at index ");
        v0.append(arg3);
        v0.append(" of ");
        v0.append(arg4);
        super(v0.toString());
    }
}

