package com.google.android.gms.measurement.internal;

import android.os.Process;
import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.BlockingQueue;

final class zzbs extends Thread {
    private final Object zzapj;
    private final BlockingQueue zzapk;

    public zzbs(zzbo arg1, String arg2, BlockingQueue arg3) {
        this.zzapg = arg1;
        super();
        Preconditions.checkNotNull(arg2);
        Preconditions.checkNotNull(arg3);
        this.zzapj = new Object();
        this.zzapk = arg3;
        this.setName(arg2);
    }

    public final void run() {
        Object v2;
        int v0 = 0;
        while(v0 == 0) {
            try {
                zzbo.zza(this.zzapg).acquire();
                v0 = 1;
            }
            catch(InterruptedException v1) {
                this.zza(v1);
            }
        }

        zzbs v0_1 = null;
        try {
            int v1_2 = Process.getThreadPriority(Process.myTid());
            while(true) {
            label_13:
                v2 = this.zzapk.poll();
                if(v2 != null) {
                    int v3 = ((zzbr)v2).zzapi ? v1_2 : 10;
                    Process.setThreadPriority(v3);
                    ((zzbr)v2).run();
                    continue;
                }

                v2 = this.zzapj;
                __monitor_enter(v2);
                break;
            }
        }
        catch(Throwable v1_1) {
            goto label_87;
        }

        try {
            if(this.zzapk.peek() != null) {
                goto label_38;
            }

            if(zzbo.zzb(this.zzapg)) {
                goto label_38;
            }

            try {
                this.zzapj.wait(30000);
                goto label_38;
            }
            catch(InterruptedException v3_1) {
                try {
                    this.zza(v3_1);
                label_38:
                    __monitor_exit(v2);
                }
                catch(Throwable v1_1) {
                    goto label_83;
                }
            }
        }
        catch(Throwable v1_1) {
            goto label_83;
        }

        try {
            v2 = zzbo.zzc(this.zzapg);
            __monitor_enter(v2);
        }
        catch(Throwable v1_1) {
            goto label_87;
        }

        try {
            if(this.zzapk.peek() != null) {
                goto label_77;
            }

            __monitor_exit(v2);
        }
        catch(Throwable v1_1) {
            goto label_80;
        }

        Object v1_3 = zzbo.zzc(this.zzapg);
        __monitor_enter(v1_3);
        try {
            zzbo.zza(this.zzapg).release();
            zzbo.zzc(this.zzapg).notifyAll();
            if(this == zzbo.zzd(this.zzapg)) {
                zzbo.zza(this.zzapg, v0_1);
            }
            else if(this == zzbo.zze(this.zzapg)) {
                zzbo.zzb(this.zzapg, v0_1);
            }
            else {
                this.zzapg.zzgo().zzjd().zzbx("Current scheduler thread is neither worker nor network");
            }

            __monitor_exit(v1_3);
            return;
        }
        catch(Throwable v0_2) {
            goto label_75;
        }

        try {
        label_77:
            __monitor_exit(v2);
            goto label_13;
        }
        catch(Throwable v1_1) {
            goto label_80;
        }

        try {
        label_75:
            __monitor_exit(v1_3);
        }
        catch(Throwable v0_2) {
            goto label_75;
        }

        throw v0_2;
        try {
        label_80:
            __monitor_exit(v2);
        }
        catch(Throwable v1_1) {
            goto label_80;
        }

        try {
            throw v1_1;
        }
        catch(Throwable v1_1) {
            goto label_87;
        }

        try {
        label_83:
            __monitor_exit(v2);
        }
        catch(Throwable v1_1) {
            goto label_83;
        }

        try {
            throw v1_1;
        }
        catch(Throwable v1_1) {
        label_87:
            v2 = zzbo.zzc(this.zzapg);
            __monitor_enter(v2);
            try {
                zzbo.zza(this.zzapg).release();
                zzbo.zzc(this.zzapg).notifyAll();
                if(this == zzbo.zzd(this.zzapg)) {
                    zzbo.zza(this.zzapg, v0_1);
                }
                else if(this == zzbo.zze(this.zzapg)) {
                    zzbo.zzb(this.zzapg, v0_1);
                }
                else {
                    this.zzapg.zzgo().zzjd().zzbx("Current scheduler thread is neither worker nor network");
                }

                __monitor_exit(v2);
            }
            catch(Throwable v0_2) {
                try {
                label_115:
                    __monitor_exit(v2);
                }
                catch(Throwable v0_2) {
                    goto label_115;
                }

                throw v0_2;
            }

            throw v1_1;
        }
    }

    private final void zza(InterruptedException arg4) {
        this.zzapg.zzgo().zzjg().zzg(String.valueOf(this.getName()).concat(" was interrupted"), arg4);
    }

    public final void zzke() {
        Object v0 = this.zzapj;
        __monitor_enter(v0);
        try {
            this.zzapj.notifyAll();
            __monitor_exit(v0);
            return;
        label_7:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_7;
        }

        throw v1;
    }
}

