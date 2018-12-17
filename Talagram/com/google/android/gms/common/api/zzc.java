package com.google.android.gms.common.api;

import java.util.Map;
import java.util.WeakHashMap;
import javax.annotation.concurrent.GuardedBy;

public abstract class zzc {
    private static final Object sLock;
    @GuardedBy(value="sLock") private static final Map zzdo;

    static {
        zzc.zzdo = new WeakHashMap();
        zzc.sLock = new Object();
    }

    public zzc() {
        super();
    }

    public abstract void remove(int arg1);
}

