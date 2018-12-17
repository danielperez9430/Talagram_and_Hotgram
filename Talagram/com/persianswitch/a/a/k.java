package com.persianswitch.a.a;

import com.persianswitch.a.ab;
import java.util.LinkedHashSet;
import java.util.Set;

public final class k {
    private final Set a;

    public k() {
        super();
        this.a = new LinkedHashSet();
    }

    public void a(ab arg2) {
        __monitor_enter(this);
        try {
            this.a.add(arg2);
        }
        catch(Throwable v2) {
            __monitor_exit(this);
            throw v2;
        }

        __monitor_exit(this);
    }

    public void b(ab arg2) {
        __monitor_enter(this);
        try {
            this.a.remove(arg2);
        }
        catch(Throwable v2) {
            __monitor_exit(this);
            throw v2;
        }

        __monitor_exit(this);
    }

    public boolean c(ab arg2) {
        boolean v2_1;
        __monitor_enter(this);
        try {
            v2_1 = this.a.contains(arg2);
        }
        catch(Throwable v2) {
            __monitor_exit(this);
            throw v2;
        }

        __monitor_exit(this);
        return v2_1;
    }
}

