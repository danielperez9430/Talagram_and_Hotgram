package com.google.android.gms.tasks;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

final class zzx implements Continuation {
    zzx(Collection arg1) {
        this.zzagk = arg1;
        super();
    }

    public final Object then(Task arg2) {
        ArrayList v2 = new ArrayList();
        ((List)v2).addAll(this.zzagk);
        return Tasks.forResult(v2);
    }
}

