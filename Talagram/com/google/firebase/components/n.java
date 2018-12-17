package com.google.firebase.components;

import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.b.a;
import com.google.firebase.b.b;
import com.google.firebase.b.c;
import com.google.firebase.b.d;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map$Entry;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;

class n implements c, d {
    private final Map a;
    private Queue b;
    private final Executor c;

    n(Executor arg2) {
        super();
        this.a = new HashMap();
        this.b = new ArrayDeque();
        this.c = arg2;
    }

    static final void a(Map$Entry arg0, a arg1) {
        arg0.getKey().a(arg1);
    }

    final void a() {
        Queue v0_1;
        __monitor_enter(this);
        try {
            Queue v1 = null;
            if(this.b != null) {
                v0_1 = this.b;
                this.b = v1;
            }
            else {
                v0_1 = v1;
            }

            __monitor_exit(this);
            if(v0_1 == null) {
                return;
            }
        }
        catch(Throwable v0) {
            try {
            label_18:
                __monitor_exit(this);
            }
            catch(Throwable v0) {
                goto label_18;
            }

            throw v0;
        }

        Iterator v0_2 = v0_1.iterator();
        while(v0_2.hasNext()) {
            this.a(v0_2.next());
        }
    }

    public void a(a arg5) {
        Preconditions.checkNotNull(arg5);
        __monitor_enter(this);
        try {
            if(this.b != null) {
                this.b.add(arg5);
                __monitor_exit(this);
                return;
            }

            __monitor_exit(this);
        }
        catch(Throwable v5) {
            try {
            label_21:
                __monitor_exit(this);
            }
            catch(Throwable v5) {
                goto label_21;
            }

            throw v5;
        }

        Iterator v0 = this.b(arg5).iterator();
        while(v0.hasNext()) {
            Object v1 = v0.next();
            ((Map$Entry)v1).getValue().execute(new o(((Map$Entry)v1), arg5));
        }
    }

    public void a(Class arg2, b arg3) {
        this.a(arg2, this.c, arg3);
    }

    public void a(Class arg3, Executor arg4, b arg5) {
        __monitor_enter(this);
        try {
            Preconditions.checkNotNull(arg3);
            Preconditions.checkNotNull(arg5);
            Preconditions.checkNotNull(arg4);
            if(!this.a.containsKey(arg3)) {
                this.a.put(arg3, new ConcurrentHashMap());
            }

            this.a.get(arg3).put(arg5, arg4);
        }
        catch(Throwable v3) {
            __monitor_exit(this);
            throw v3;
        }

        __monitor_exit(this);
    }

    private Set b(a arg2) {
        Set v2_2;
        Object v2_1;
        __monitor_enter(this);
        try {
            v2_1 = this.a.get(arg2.a());
            if(v2_1 != null) {
                goto label_8;
            }

            v2_2 = Collections.emptySet();
        }
        catch(Throwable v2) {
            goto label_12;
        }

        __monitor_exit(this);
        return v2_2;
        try {
        label_8:
            v2_2 = ((Map)v2_1).entrySet();
        }
        catch(Throwable v2) {
        label_12:
            __monitor_exit(this);
            throw v2;
        }

        __monitor_exit(this);
        return v2_2;
    }
}

