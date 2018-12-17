package com.google.android.gms.common.api;

public abstract class OptionalPendingResult extends PendingResult {
    public OptionalPendingResult() {
        super();
    }

    public abstract Result get();

    public abstract boolean isDone();
}

