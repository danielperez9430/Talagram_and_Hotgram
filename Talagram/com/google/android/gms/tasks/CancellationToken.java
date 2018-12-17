package com.google.android.gms.tasks;

public abstract class CancellationToken {
    public CancellationToken() {
        super();
    }

    public abstract boolean isCancellationRequested();

    public abstract CancellationToken onCanceledRequested(OnTokenCanceledListener arg1);
}

