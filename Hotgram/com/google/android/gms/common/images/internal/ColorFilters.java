package com.google.android.gms.common.images.internal;

import android.graphics.ColorFilter;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;

public final class ColorFilters {
    public static final ColorFilter COLOR_FILTER_BW;
    private static final ColorMatrix zzpv;

    static {
        ColorMatrix v0 = new ColorMatrix();
        ColorFilters.zzpv = v0;
        v0.setSaturation(0f);
        ColorFilters.COLOR_FILTER_BW = new ColorMatrixColorFilter(ColorFilters.zzpv);
    }

    private ColorFilters() {
        super();
    }
}

