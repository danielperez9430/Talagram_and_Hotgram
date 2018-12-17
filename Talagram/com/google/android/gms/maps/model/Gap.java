package com.google.android.gms.maps.model;

public final class Gap extends PatternItem {
    public final float length;

    public Gap(float arg4) {
        super(2, Float.valueOf(Math.max(arg4, 0f)));
        this.length = Math.max(arg4, 0f);
    }

    public final String toString() {
        float v0 = this.length;
        StringBuilder v1 = new StringBuilder(29);
        v1.append("[Gap: length=");
        v1.append(v0);
        v1.append("]");
        return v1.toString();
    }
}

