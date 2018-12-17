package com.google.android.gms.common.util.concurrent;

import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class NumberedThreadFactory implements ThreadFactory {
    private final int priority;
    private final ThreadFactory zzaau;
    private final String zzaav;
    private final AtomicInteger zzaaw;

    public NumberedThreadFactory(String arg2) {
        this(arg2, 0);
    }

    public NumberedThreadFactory(String arg2, int arg3) {
        super();
        this.zzaaw = new AtomicInteger();
        this.zzaau = Executors.defaultThreadFactory();
        this.zzaav = Preconditions.checkNotNull(arg2, "Name must not be null");
        this.priority = arg3;
    }

    public Thread newThread(Runnable arg5) {
        Thread v5 = this.zzaau.newThread(new zza(arg5, this.priority));
        String v0 = this.zzaav;
        int v1 = this.zzaaw.getAndIncrement();
        StringBuilder v3 = new StringBuilder(String.valueOf(v0).length() + 13);
        v3.append(v0);
        v3.append("[");
        v3.append(v1);
        v3.append("]");
        v5.setName(v3.toString());
        return v5;
    }
}

