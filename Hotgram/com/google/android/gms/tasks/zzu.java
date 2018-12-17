package com.google.android.gms.tasks;

import android.app.Activity;
import com.google.android.gms.common.api.internal.LifecycleCallback;
import com.google.android.gms.common.api.internal.LifecycleFragment;
import com.google.android.gms.common.internal.Preconditions;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.Executor;
import javax.annotation.concurrent.GuardedBy;

final class zzu extends Task {
    class zza extends LifecycleCallback {
        private final List zzagi;

        private zza(LifecycleFragment arg2) {
            super(arg2);
            this.zzagi = new ArrayList();
            this.mLifecycleFragment.addCallback("TaskOnStopCallback", ((LifecycleCallback)this));
        }

        public void onStop() {
            List v0 = this.zzagi;
            __monitor_enter(v0);
            try {
                Iterator v1_1 = this.zzagi.iterator();
                while(v1_1.hasNext()) {
                    Object v2 = v1_1.next().get();
                    if(v2 == null) {
                        continue;
                    }

                    ((zzq)v2).cancel();
                }

                this.zzagi.clear();
                __monitor_exit(v0);
                return;
            label_16:
                __monitor_exit(v0);
            }
            catch(Throwable v1) {
                goto label_16;
            }

            throw v1;
        }

        public final void zzb(zzq arg4) {
            List v0 = this.zzagi;
            __monitor_enter(v0);
            try {
                this.zzagi.add(new WeakReference(arg4));
                __monitor_exit(v0);
                return;
            label_9:
                __monitor_exit(v0);
            }
            catch(Throwable v4) {
                goto label_9;
            }

            throw v4;
        }

        public static zza zze(Activity arg2) {
            zza v0_1;
            LifecycleFragment v2 = zza.getFragment(arg2);
            LifecycleCallback v0 = v2.getCallbackOrNull("TaskOnStopCallback", zza.class);
            if(v0 == null) {
                v0_1 = new zza(v2);
            }

            return v0_1;
        }
    }

    private final Object mLock;
    private final zzr zzage;
    @GuardedBy(value="mLock") private boolean zzagf;
    @GuardedBy(value="mLock") private Object zzagg;
    @GuardedBy(value="mLock") private Exception zzagh;
    private volatile boolean zzfi;

    zzu() {
        super();
        this.mLock = new Object();
        this.zzage = new zzr();
    }

    public final Task addOnCanceledListener(Activity arg3, OnCanceledListener arg4) {
        zzg v0 = new zzg(TaskExecutors.MAIN_THREAD, arg4);
        this.zzage.zza(((zzq)v0));
        zza.zze(arg3).zzb(((zzq)v0));
        this.zzdt();
        return this;
    }

    public final Task addOnCanceledListener(OnCanceledListener arg2) {
        return ((Task)this).addOnCanceledListener(TaskExecutors.MAIN_THREAD, arg2);
    }

    public final Task addOnCanceledListener(Executor arg3, OnCanceledListener arg4) {
        this.zzage.zza(new zzg(arg3, arg4));
        this.zzdt();
        return this;
    }

    public final Task addOnCompleteListener(Activity arg3, OnCompleteListener arg4) {
        zzi v0 = new zzi(TaskExecutors.MAIN_THREAD, arg4);
        this.zzage.zza(((zzq)v0));
        zza.zze(arg3).zzb(((zzq)v0));
        this.zzdt();
        return this;
    }

    public final Task addOnCompleteListener(OnCompleteListener arg2) {
        return ((Task)this).addOnCompleteListener(TaskExecutors.MAIN_THREAD, arg2);
    }

    public final Task addOnCompleteListener(Executor arg3, OnCompleteListener arg4) {
        this.zzage.zza(new zzi(arg3, arg4));
        this.zzdt();
        return this;
    }

    public final Task addOnFailureListener(Activity arg3, OnFailureListener arg4) {
        zzk v0 = new zzk(TaskExecutors.MAIN_THREAD, arg4);
        this.zzage.zza(((zzq)v0));
        zza.zze(arg3).zzb(((zzq)v0));
        this.zzdt();
        return this;
    }

    public final Task addOnFailureListener(OnFailureListener arg2) {
        return ((Task)this).addOnFailureListener(TaskExecutors.MAIN_THREAD, arg2);
    }

    public final Task addOnFailureListener(Executor arg3, OnFailureListener arg4) {
        this.zzage.zza(new zzk(arg3, arg4));
        this.zzdt();
        return this;
    }

    public final Task addOnSuccessListener(Activity arg3, OnSuccessListener arg4) {
        zzm v0 = new zzm(TaskExecutors.MAIN_THREAD, arg4);
        this.zzage.zza(((zzq)v0));
        zza.zze(arg3).zzb(((zzq)v0));
        this.zzdt();
        return this;
    }

    public final Task addOnSuccessListener(OnSuccessListener arg2) {
        return ((Task)this).addOnSuccessListener(TaskExecutors.MAIN_THREAD, arg2);
    }

    public final Task addOnSuccessListener(Executor arg3, OnSuccessListener arg4) {
        this.zzage.zza(new zzm(arg3, arg4));
        this.zzdt();
        return this;
    }

    public final Task continueWith(Continuation arg2) {
        return ((Task)this).continueWith(TaskExecutors.MAIN_THREAD, arg2);
    }

    public final Task continueWith(Executor arg4, Continuation arg5) {
        zzu v0 = new zzu();
        this.zzage.zza(new zzc(arg4, arg5, v0));
        this.zzdt();
        return ((Task)v0);
    }

    public final Task continueWithTask(Continuation arg2) {
        return ((Task)this).continueWithTask(TaskExecutors.MAIN_THREAD, arg2);
    }

    public final Task continueWithTask(Executor arg4, Continuation arg5) {
        zzu v0 = new zzu();
        this.zzage.zza(new zze(arg4, arg5, v0));
        this.zzdt();
        return ((Task)v0);
    }

    public final Exception getException() {
        Object v0 = this.mLock;
        __monitor_enter(v0);
        try {
            __monitor_exit(v0);
            return this.zzagh;
        label_6:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_6;
        }

        throw v1;
    }

    public final Object getResult() {
        Object v0 = this.mLock;
        __monitor_enter(v0);
        try {
            this.zzdq();
            this.zzds();
            if(this.zzagh == null) {
                __monitor_exit(v0);
                return this.zzagg;
            }

            throw new RuntimeExecutionException(this.zzagh);
        label_14:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_14;
        }

        throw v1;
    }

    public final Object getResult(Class arg3) {
        Object v0 = this.mLock;
        __monitor_enter(v0);
        try {
            this.zzdq();
            this.zzds();
            if(!arg3.isInstance(this.zzagh)) {
                if(this.zzagh == null) {
                    __monitor_exit(v0);
                    return this.zzagg;
                }

                throw new RuntimeExecutionException(this.zzagh);
            }

            throw arg3.cast(this.zzagh);
        label_20:
            __monitor_exit(v0);
        }
        catch(Throwable v3) {
            goto label_20;
        }

        throw v3;
    }

    public final boolean isCanceled() {
        return this.zzfi;
    }

    public final boolean isComplete() {
        Object v0 = this.mLock;
        __monitor_enter(v0);
        try {
            __monitor_exit(v0);
            return this.zzagf;
        label_6:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_6;
        }

        throw v1;
    }

    public final boolean isSuccessful() {
        Object v0 = this.mLock;
        __monitor_enter(v0);
        try {
            boolean v1_1 = !this.zzagf || (this.zzfi) || this.zzagh != null ? false : true;
            __monitor_exit(v0);
            return v1_1;
        label_14:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_14;
        }

        throw v1;
    }

    public final Task onSuccessTask(SuccessContinuation arg2) {
        return ((Task)this).onSuccessTask(TaskExecutors.MAIN_THREAD, arg2);
    }

    public final Task onSuccessTask(Executor arg4, SuccessContinuation arg5) {
        zzu v0 = new zzu();
        this.zzage.zza(new zzo(arg4, arg5, v0));
        this.zzdt();
        return ((Task)v0);
    }

    public final void setException(Exception arg3) {
        Preconditions.checkNotNull(arg3, "Exception must not be null");
        Object v0 = this.mLock;
        __monitor_enter(v0);
        try {
            this.zzdr();
            this.zzagf = true;
            this.zzagh = arg3;
            __monitor_exit(v0);
        }
        catch(Throwable v3) {
            try {
            label_13:
                __monitor_exit(v0);
            }
            catch(Throwable v3) {
                goto label_13;
            }

            throw v3;
        }

        this.zzage.zza(((Task)this));
    }

    public final void setResult(Object arg3) {
        Object v0 = this.mLock;
        __monitor_enter(v0);
        try {
            this.zzdr();
            this.zzagf = true;
            this.zzagg = arg3;
            __monitor_exit(v0);
        }
        catch(Throwable v3) {
            try {
            label_11:
                __monitor_exit(v0);
            }
            catch(Throwable v3) {
                goto label_11;
            }

            throw v3;
        }

        this.zzage.zza(((Task)this));
    }

    public final boolean trySetException(Exception arg3) {
        Preconditions.checkNotNull(arg3, "Exception must not be null");
        Object v0 = this.mLock;
        __monitor_enter(v0);
        try {
            if(this.zzagf) {
                __monitor_exit(v0);
                return 0;
            }

            this.zzagf = true;
            this.zzagh = arg3;
            __monitor_exit(v0);
        }
        catch(Throwable v3) {
            try {
            label_17:
                __monitor_exit(v0);
            }
            catch(Throwable v3) {
                goto label_17;
            }

            throw v3;
        }

        this.zzage.zza(((Task)this));
        return 1;
    }

    public final boolean trySetResult(Object arg3) {
        Object v0 = this.mLock;
        __monitor_enter(v0);
        try {
            if(this.zzagf) {
                __monitor_exit(v0);
                return 0;
            }

            this.zzagf = true;
            this.zzagg = arg3;
            __monitor_exit(v0);
        }
        catch(Throwable v3) {
            try {
            label_15:
                __monitor_exit(v0);
            }
            catch(Throwable v3) {
                goto label_15;
            }

            throw v3;
        }

        this.zzage.zza(((Task)this));
        return 1;
    }

    public final boolean zzdp() {
        Object v0 = this.mLock;
        __monitor_enter(v0);
        try {
            if(this.zzagf) {
                __monitor_exit(v0);
                return 0;
            }

            this.zzagf = true;
            this.zzfi = true;
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            try {
            label_15:
                __monitor_exit(v0);
            }
            catch(Throwable v1) {
                goto label_15;
            }

            throw v1;
        }

        this.zzage.zza(((Task)this));
        return 1;
    }

    @GuardedBy(value="mLock") private final void zzdq() {
        Preconditions.checkState(this.zzagf, "Task is not yet complete");
    }

    @GuardedBy(value="mLock") private final void zzdr() {
        Preconditions.checkState(this.zzagf ^ 1, "Task is already complete");
    }

    @GuardedBy(value="mLock") private final void zzds() {
        if(!this.zzfi) {
            return;
        }

        throw new CancellationException("Task is already canceled.");
    }

    private final void zzdt() {
        Object v0 = this.mLock;
        __monitor_enter(v0);
        try {
            if(!this.zzagf) {
                __monitor_exit(v0);
                return;
            }

            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            try {
            label_11:
                __monitor_exit(v0);
            }
            catch(Throwable v1) {
                goto label_11;
            }

            throw v1;
        }

        this.zzage.zza(((Task)this));
    }
}

