package com.google.android.gms.common.api;

import com.google.android.gms.common.annotation.KeepForSdk;
import java.util.concurrent.TimeUnit;

@KeepForSdk public abstract class PendingResult {
    @KeepForSdk public interface StatusListener {
        @KeepForSdk void onComplete(Status arg1);
    }

    public PendingResult() {
        super();
    }

    @KeepForSdk public void addStatusListener(StatusListener arg1) {
        throw new UnsupportedOperationException();
    }

    public abstract Result await();

    public abstract Result await(long arg1, TimeUnit arg2);

    public abstract void cancel();

    public abstract boolean isCanceled();

    public abstract void setResultCallback(ResultCallback arg1);

    public abstract void setResultCallback(ResultCallback arg1, long arg2, TimeUnit arg3);

    public TransformedResult then(ResultTransform arg1) {
        throw new UnsupportedOperationException();
    }

    public Integer zzo() {
        throw new UnsupportedOperationException();
    }
}

