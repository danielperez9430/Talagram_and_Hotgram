package com.google.android.gms.common.api.internal;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.GoogleApi;

public final class zzbo extends zzaf {
    private final GoogleApi zzks;

    public zzbo(GoogleApi arg2) {
        super("Method is not supported by connectionless client. APIs supporting connectionless client must not call this method.");
        this.zzks = arg2;
    }

    public final ApiMethodImpl enqueue(ApiMethodImpl arg2) {
        return this.zzks.doRead(arg2);
    }

    public final ApiMethodImpl execute(ApiMethodImpl arg2) {
        return this.zzks.doWrite(arg2);
    }

    public final Context getContext() {
        return this.zzks.getApplicationContext();
    }

    public final Looper getLooper() {
        return this.zzks.getLooper();
    }

    public final void zza(zzch arg1) {
    }

    public final void zzb(zzch arg1) {
    }
}

