package com.google.firebase.iid;

import android.os.Bundle;
import com.google.android.gms.tasks.TaskCompletionSource;

final class as implements Runnable {
    private final ar a;
    private final Bundle b;
    private final TaskCompletionSource c;

    as(ar arg1, Bundle arg2, TaskCompletionSource arg3) {
        super();
        this.a = arg1;
        this.b = arg2;
        this.c = arg3;
    }

    public final void run() {
        this.a.a(this.b, this.c);
    }
}

