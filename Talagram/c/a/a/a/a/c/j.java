package c.a.a.a.a.c;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class j implements b, i, l {
    private final List a;
    private final AtomicBoolean b;
    private final AtomicReference c;

    public j() {
        super();
        this.a = new ArrayList();
        this.b = new AtomicBoolean(false);
        this.c = new AtomicReference(null);
    }

    public static boolean a(Object arg3) {
        boolean v0 = false;
        Object v2 = arg3;
        if(arg3 != null && v2 != null && arg3 != null) {
            v0 = true;
        }

        return v0;
    }

    public void a(l arg2) {
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

    public void a(Throwable arg2) {
        this.c.set(arg2);
    }

    public e b() {
        return e.b;
    }

    public void b(boolean arg2) {
        __monitor_enter(this);
        try {
            this.b.set(arg2);
        }
        catch(Throwable v2) {
            __monitor_exit(this);
            throw v2;
        }

        __monitor_exit(this);
    }

    public Collection c() {
        Collection v0_1;
        __monitor_enter(this);
        try {
            v0_1 = Collections.unmodifiableCollection(this.a);
        }
        catch(Throwable v0) {
            __monitor_exit(this);
            throw v0;
        }

        __monitor_exit(this);
        return v0_1;
    }

    public void c(Object arg1) {
        this.a(((l)arg1));
    }

    public int compareTo(Object arg1) {
        return e.a(((i)this), arg1);
    }

    public boolean d() {
        Iterator v0 = this.c().iterator();
        do {
            if(!v0.hasNext()) {
                return 1;
            }
        }
        while(v0.next().f());

        return 0;
    }

    public boolean f() {
        return this.b.get();
    }
}

