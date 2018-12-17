package com.google.firebase.iid;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import java.io.IOException;

final class au implements Continuation {
    au(ar arg1) {
        this.a = arg1;
        super();
    }

    public final Object then(Task arg2) {
        return ar.a(this.a, arg2.getResult(IOException.class));
    }
}

