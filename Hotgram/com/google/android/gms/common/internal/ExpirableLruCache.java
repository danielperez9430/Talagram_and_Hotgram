package com.google.android.gms.common.internal;

import android.support.v4.f.g;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class ExpirableLruCache {
    public static int TIME_UNSET = -1;
    private final Object mLock;
    private final g zzsn;
    private final long zzso;
    private final long zzsp;
    private HashMap zzsq;
    private HashMap zzsr;

    static {
    }

    public ExpirableLruCache(int arg2, long arg3, long arg5, TimeUnit arg7) {
        super();
        this.mLock = new Object();
        this.zzso = TimeUnit.NANOSECONDS.convert(arg3, arg7);
        this.zzsp = TimeUnit.NANOSECONDS.convert(arg5, arg7);
        boolean v3 = (this.zzct()) || (this.zzcu()) ? true : false;
        Preconditions.checkArgument(v3, "ExpirableLruCache has both access and write expiration negative");
        this.zzsn = new zze(this, arg2);
        if(this.zzct()) {
            this.zzsq = new HashMap();
        }

        if(this.zzcu()) {
            this.zzsr = new HashMap();
        }
    }

    protected Object create(Object arg1) {
        return null;
    }

    protected void entryRemoved(boolean arg1, Object arg2, Object arg3, Object arg4) {
    }

    public void evictAll() {
        this.zzsn.evictAll();
    }

    public Object get(Object arg8) {
        Object v0 = this.mLock;
        __monitor_enter(v0);
        try {
            if(this.zza(arg8)) {
                this.zzsn.remove(arg8);
            }

            Object v1 = this.zzsn.get(arg8);
            if(v1 != null && this.zzso > 0) {
                this.zzsq.put(arg8, Long.valueOf(System.nanoTime()));
            }

            __monitor_exit(v0);
            return v1;
        label_19:
            __monitor_exit(v0);
        }
        catch(Throwable v8) {
            goto label_19;
        }

        throw v8;
    }

    public Object put(Object arg5, Object arg6) {
        if(this.zzcu()) {
            long v0 = System.nanoTime();
            Object v2 = this.mLock;
            __monitor_enter(v2);
            try {
                this.zzsr.put(arg5, Long.valueOf(v0));
                __monitor_exit(v2);
                goto label_13;
            label_11:
                __monitor_exit(v2);
            }
            catch(Throwable v5) {
                goto label_11;
            }

            throw v5;
        }

    label_13:
        return this.zzsn.put(arg5, arg6);
    }

    public Object remove(Object arg2) {
        return this.zzsn.remove(arg2);
    }

    public void removeExpired() {
        Object v2;
        Iterator v0 = this.zzsn.snapshot().keySet().iterator();
        while(v0.hasNext()) {
            Object v1 = v0.next();
            v2 = this.mLock;
            __monitor_enter(v2);
            try {
                if(this.zza(v1)) {
                    this.zzsn.remove(v1);
                }

                __monitor_exit(v2);
                continue;
            }
            catch(Throwable v0_1) {
                goto label_16;
            }
        }

        return;
        try {
        label_16:
            __monitor_exit(v2);
        }
        catch(Throwable v0_1) {
            goto label_16;
        }

        throw v0_1;
    }

    protected int sizeOf(Object arg1, Object arg2) {
        return 1;
    }

    public Map snapshot() {
        this.removeExpired();
        return this.zzsn.snapshot();
    }

    static Object zza(ExpirableLruCache arg0) {
        return arg0.mLock;
    }

    private final boolean zza(Object arg9) {
        long v0 = System.nanoTime();
        if((this.zzct()) && (this.zzsq.containsKey(arg9)) && v0 - this.zzsq.get(arg9).longValue() > this.zzso) {
            return 1;
        }

        if((this.zzcu()) && (this.zzsr.containsKey(arg9)) && v0 - this.zzsr.get(arg9).longValue() > this.zzsp) {
            return 1;
        }

        return 0;
    }

    static boolean zzb(ExpirableLruCache arg0) {
        return arg0.zzct();
    }

    static HashMap zzc(ExpirableLruCache arg0) {
        return arg0.zzsq;
    }

    private final boolean zzct() {
        if(this.zzso >= 0) {
            return 1;
        }

        return 0;
    }

    private final boolean zzcu() {
        if(this.zzsp >= 0) {
            return 1;
        }

        return 0;
    }

    static boolean zzd(ExpirableLruCache arg0) {
        return arg0.zzcu();
    }

    static HashMap zze(ExpirableLruCache arg0) {
        return arg0.zzsr;
    }
}

