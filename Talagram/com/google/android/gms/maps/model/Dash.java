package com.google.android.gms.maps.model;

public final class Dash extends PatternItem {
    public final float length;

    public Dash(float arg4) {
        super(0, Float.valueOf(Math.max(arg4, 0f)));
        this.length = Math.max(arg4, 0f);
    }

    public final String toString() {
        float v0 = this.length;
        StringBuilder v1 = new StringBuilder(30);
        v1.append("[Dash: length=");
        v1.append(v0);
        v1.append("]");
        return v1.toString();
    }
}

