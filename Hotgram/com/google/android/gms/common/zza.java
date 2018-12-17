package com.google.android.gms.common;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;

final class zza implements Continuation {
    zza(GoogleApiAvailability arg1) {
        super();
    }

    public final Object then(Task arg1) {
        arg1.getResult();
        return null;
    }
}

