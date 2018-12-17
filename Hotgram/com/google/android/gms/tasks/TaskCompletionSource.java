package com.google.android.gms.tasks;

public class TaskCompletionSource {
    private final zzu zzafh;

    public TaskCompletionSource() {
        super();
        this.zzafh = new zzu();
    }

    public TaskCompletionSource(CancellationToken arg2) {
        super();
        this.zzafh = new zzu();
        arg2.onCanceledRequested(new zzs(this));
    }

    public Task getTask() {
        return this.zzafh;
    }

    public void setException(Exception arg2) {
        this.zzafh.setException(arg2);
    }

    public void setResult(Object arg2) {
        this.zzafh.setResult(arg2);
    }

    public boolean trySetException(Exception arg2) {
        return this.zzafh.trySetException(arg2);
    }

    public boolean trySetResult(Object arg2) {
        return this.zzafh.trySetResult(arg2);
    }

    static zzu zza(TaskCompletionSource arg0) {
        return arg0.zzafh;
    }
}

