package com.google.android.gms.measurement.internal;

import android.content.Context;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

public final class zzbo extends zzcp {
    private ExecutorService zzaea;
    private zzbs zzaov;
    private zzbs zzaow;
    private final PriorityBlockingQueue zzaox;
    private final BlockingQueue zzaoy;
    private final Thread$UncaughtExceptionHandler zzaoz;
    private final Thread$UncaughtExceptionHandler zzapa;
    private final Object zzapb;
    private final Semaphore zzapc;
    private volatile boolean zzapd;
    private static final AtomicLong zzape;

    static {
        zzbo.zzape = new AtomicLong(-9223372036854775808L);
    }

    zzbo(zzbt arg2) {
        super(arg2);
        this.zzapb = new Object();
        this.zzapc = new Semaphore(2);
        this.zzaox = new PriorityBlockingQueue();
        this.zzaoy = new LinkedBlockingQueue();
        this.zzaoz = new zzbq(this, "Thread death: Uncaught exception on worker thread");
        this.zzapa = new zzbq(this, "Thread death: Uncaught exception on network thread");
    }

    public final Context getContext() {
        return super.getContext();
    }

    static Semaphore zza(zzbo arg0) {
        return arg0.zzapc;
    }

    static zzbs zza(zzbo arg0, zzbs arg1) {
        arg0.zzaov = null;
        return null;
    }

    private final void zza(zzbr arg4) {
        Object v0 = this.zzapb;
        __monitor_enter(v0);
        try {
            this.zzaox.add(arg4);
            if(this.zzaov == null) {
                this.zzaov = new zzbs(this, "Measurement Worker", this.zzaox);
                this.zzaov.setUncaughtExceptionHandler(this.zzaoz);
                this.zzaov.start();
            }
            else {
                this.zzaov.zzke();
            }

            __monitor_exit(v0);
            return;
        label_22:
            __monitor_exit(v0);
        }
        catch(Throwable v4) {
            goto label_22;
        }

        throw v4;
    }

    final Object zza(AtomicReference arg1, long arg2, String arg4, Runnable arg5) {
        String v3;
        zzar v2_1;
        __monitor_enter(arg1);
        try {
            ((zzco)this).zzgn().zzc(arg5);
            arg2 = 15000;
            try {
                arg1.wait(arg2);
                goto label_5;
            }
            catch(InterruptedException ) {
                try {
                    v2_1 = ((zzco)this).zzgo().zzjg();
                    v3 = "Interrupted waiting for ";
                    arg4 = String.valueOf(arg4);
                    v3 = arg4.length() != 0 ? v3.concat(arg4) : new String(v3);
                    v2_1.zzbx(v3);
                    __monitor_exit(arg1);
                    return null;
                label_5:
                    __monitor_exit(arg1);
                    goto label_6;
                label_37:
                    __monitor_exit(arg1);
                }
                catch(Throwable v2) {
                    goto label_37;
                }
            }
        }
        catch(Throwable v2) {
            goto label_37;
        }

        throw v2;
    label_6:
        Object v1 = arg1.get();
        if(v1 == null) {
            v2_1 = ((zzco)this).zzgo().zzjg();
            v3 = "Timed out waiting for ";
            arg4 = String.valueOf(arg4);
            v3 = arg4.length() != 0 ? v3.concat(arg4) : new String(v3);
            v2_1.zzbx(v3);
        }

        return v1;
    }

    public final void zzaf() {
        if(Thread.currentThread() == this.zzaov) {
            return;
        }

        throw new IllegalStateException("Call expected from worker thread");
    }

    static boolean zzb(zzbo arg0) {
        return arg0.zzapd;
    }

    static zzbs zzb(zzbo arg0, zzbs arg1) {
        arg0.zzaow = null;
        return null;
    }

    public final Future zzb(Callable arg4) {
        ((zzcp)this).zzcl();
        Preconditions.checkNotNull(arg4);
        zzbr v0 = new zzbr(this, arg4, false, "Task exception on worker thread");
        if(Thread.currentThread() == this.zzaov) {
            if(!this.zzaox.isEmpty()) {
                ((zzco)this).zzgo().zzjg().zzbx("Callable skipped the worker queue.");
            }

            v0.run();
        }
        else {
            this.zza(v0);
        }

        return ((Future)v0);
    }

    public final Clock zzbx() {
        return super.zzbx();
    }

    public final void zzc(Runnable arg4) {
        ((zzcp)this).zzcl();
        Preconditions.checkNotNull(arg4);
        this.zza(new zzbr(this, arg4, false, "Task exception on worker thread"));
    }

    static Object zzc(zzbo arg0) {
        return arg0.zzapb;
    }

    public final Future zzc(Callable arg4) {
        ((zzcp)this).zzcl();
        Preconditions.checkNotNull(arg4);
        zzbr v0 = new zzbr(this, arg4, true, "Task exception on worker thread");
        if(Thread.currentThread() == this.zzaov) {
            v0.run();
        }
        else {
            this.zza(v0);
        }

        return ((Future)v0);
    }

    static zzbs zzd(zzbo arg0) {
        return arg0.zzaov;
    }

    public final void zzd(Runnable arg4) {
        ((zzcp)this).zzcl();
        Preconditions.checkNotNull(arg4);
        zzbr v0 = new zzbr(this, arg4, false, "Task exception on network thread");
        Object v4 = this.zzapb;
        __monitor_enter(v4);
        try {
            this.zzaoy.add(v0);
            if(this.zzaow == null) {
                this.zzaow = new zzbs(this, "Measurement Network", this.zzaoy);
                this.zzaow.setUncaughtExceptionHandler(this.zzapa);
                this.zzaow.start();
            }
            else {
                this.zzaow.zzke();
            }

            __monitor_exit(v4);
            return;
        label_28:
            __monitor_exit(v4);
        }
        catch(Throwable v0_1) {
            goto label_28;
        }

        throw v0_1;
    }

    static zzbs zze(zzbo arg0) {
        return arg0.zzaow;
    }

    public final void zzga() {
        super.zzga();
    }

    public final void zzgb() {
        super.zzgb();
    }

    public final void zzgc() {
        if(Thread.currentThread() == this.zzaow) {
            return;
        }

        throw new IllegalStateException("Call expected from network thread");
    }

    public final zzx zzgk() {
        return super.zzgk();
    }

    public final zzan zzgl() {
        return super.zzgl();
    }

    public final zzfk zzgm() {
        return super.zzgm();
    }

    public final zzbo zzgn() {
        return super.zzgn();
    }

    public final zzap zzgo() {
        return super.zzgo();
    }

    public final zzba zzgp() {
        return super.zzgp();
    }

    public final zzn zzgq() {
        return super.zzgq();
    }

    public final zzk zzgr() {
        return super.zzgr();
    }

    protected final boolean zzgt() {
        return 0;
    }

    public final boolean zzkb() {
        if(Thread.currentThread() == this.zzaov) {
            return 1;
        }

        return 0;
    }

    public final ExecutorService zzkc() {
        Object v0 = this.zzapb;
        __monitor_enter(v0);
        try {
            if(this.zzaea == null) {
                this.zzaea = new ThreadPoolExecutor(0, 1, 30, TimeUnit.SECONDS, new ArrayBlockingQueue(100));
            }

            __monitor_exit(v0);
            return this.zzaea;
        label_20:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_20;
        }

        throw v1;
    }

    static AtomicLong zzkd() {
        return zzbo.zzape;
    }
}

