package com.google.firebase.e;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.internal.config.zzk;
import com.google.android.gms.tasks.TaskCompletionSource;

final class f implements ResultCallback {
    f(a arg1, TaskCompletionSource arg2) {
        this.b = arg1;
        this.a = arg2;
        super();
    }

    public final void onResult(Result arg3) {
        this.b.a(this.a, ((zzk)arg3));
    }
}

