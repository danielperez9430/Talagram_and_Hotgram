package com.google.android.gms.internal.places;

import java.util.Iterator;

final class zzje extends zzjk {
    zzje(zzjb arg1, zzjc arg2) {
        this(arg1);
    }

    private zzje(zzjb arg2) {
        this.zzxk = arg2;
        super(arg2, null);
    }

    public final Iterator iterator() {
        return new zzjd(this.zzxk, null);
    }
}

