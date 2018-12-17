package com.google.android.gms.wearable.internal;

import com.google.android.gms.wearable.CapabilityInfo;
import java.util.Set;

public final class zzw implements CapabilityInfo {
    private final String name;
    private final Set zzbt;

    public zzw(CapabilityInfo arg2) {
        this(arg2.getName(), arg2.getNodes());
    }

    private zzw(String arg1, Set arg2) {
        super();
        this.name = arg1;
        this.zzbt = arg2;
    }

    public final String getName() {
        return this.name;
    }

    public final Set getNodes() {
        return this.zzbt;
    }
}

