package com.google.firebase.iid;

import android.util.Pair;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;

final class t implements Continuation {
    private final s a;
    private final Pair b;

    t(s arg1, Pair arg2) {
        super();
        this.a = arg1;
        this.b = arg2;
    }

    public final Object then(Task arg3) {
        return this.a.a(this.b, arg3);
    }
}

