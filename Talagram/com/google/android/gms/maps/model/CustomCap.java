package com.google.android.gms.maps.model;

import com.google.android.gms.common.internal.Preconditions;

public final class CustomCap extends Cap {
    public final BitmapDescriptor bitmapDescriptor;
    public final float refWidth;

    public CustomCap(BitmapDescriptor arg3, float arg4) {
        Object v0 = Preconditions.checkNotNull(arg3, "bitmapDescriptor must not be null");
        if(arg4 > 0f) {
            super(((BitmapDescriptor)v0), arg4);
            this.bitmapDescriptor = arg3;
            this.refWidth = arg4;
            return;
        }

        throw new IllegalArgumentException("refWidth must be positive");
    }

    public CustomCap(BitmapDescriptor arg2) {
        this(arg2, 10f);
    }

    public final String toString() {
        String v0 = String.valueOf(this.bitmapDescriptor);
        float v1 = this.refWidth;
        StringBuilder v3 = new StringBuilder(String.valueOf(v0).length() + 55);
        v3.append("[CustomCap: bitmapDescriptor=");
        v3.append(v0);
        v3.append(" refWidth=");
        v3.append(v1);
        v3.append("]");
        return v3.toString();
    }
}

