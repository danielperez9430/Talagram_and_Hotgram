package com.google.android.gms.common.util.concurrent;

import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class NamedThreadFactory implements ThreadFactory {
    private final String name;
    private final int priority;
    private final ThreadFactory zzaau;

    public NamedThreadFactory(String arg2) {
        this(arg2, 0);
    }

    public NamedThreadFactory(String arg2, int arg3) {
        super();
        this.zzaau = Executors.defaultThreadFactory();
        this.name = Preconditions.checkNotNull(arg2, "Name must not be null");
        this.priority = arg3;
    }

    public Thread newThread(Runnable arg4) {
        Thread v4 = this.zzaau.newThread(new zza(arg4, this.priority));
        v4.setName(this.name);
        return v4;
    }
}

