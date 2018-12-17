package com.google.firebase.iid;

import com.google.android.gms.tasks.Task;

final class ao implements u {
    private final FirebaseInstanceId a;
    private final String b;
    private final String c;
    private final String d;
    private final String e;

    ao(FirebaseInstanceId arg1, String arg2, String arg3, String arg4, String arg5) {
        super();
        this.a = arg1;
        this.b = arg2;
        this.c = arg3;
        this.d = arg4;
        this.e = arg5;
    }

    public final Task a() {
        return this.a.a(this.b, this.c, this.d, this.e);
    }
}

