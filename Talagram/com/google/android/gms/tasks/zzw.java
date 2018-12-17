package com.google.android.gms.tasks;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

final class zzw implements Continuation {
    zzw(Collection arg1) {
        this.zzagk = arg1;
        super();
    }

    public final Object then(Task arg3) {
        if(this.zzagk.size() == 0) {
            return Collections.emptyList();
        }

        ArrayList v3 = new ArrayList();
        Iterator v0 = this.zzagk.iterator();
        while(v0.hasNext()) {
            ((List)v3).add(v0.next().getResult());
        }

        return v3;
    }
}

