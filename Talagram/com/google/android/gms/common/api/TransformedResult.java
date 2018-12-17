package com.google.android.gms.common.api;

public abstract class TransformedResult {
    public TransformedResult() {
        super();
    }

    public abstract void andFinally(ResultCallbacks arg1);

    public abstract TransformedResult then(ResultTransform arg1);
}

