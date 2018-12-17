package com.google.firebase.iid;

import com.google.android.gms.tasks.TaskCompletionSource;

final class an implements Runnable {
    private final FirebaseInstanceId a;
    private final String b;
    private final String c;
    private final TaskCompletionSource d;
    private final String e;

    an(FirebaseInstanceId arg1, String arg2, String arg3, TaskCompletionSource arg4, String arg5) {
        super();
        this.a = arg1;
        this.b = arg2;
        this.c = arg3;
        this.d = arg4;
        this.e = arg5;
    }

    public final void run() {
        this.a.a(this.b, this.c, this.d, this.e);
    }
}

