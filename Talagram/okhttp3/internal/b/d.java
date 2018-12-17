package okhttp3.internal.b;

import java.util.LinkedHashSet;
import java.util.Set;
import okhttp3.ae;

public final class d {
    private final Set a;

    public d() {
        super();
        this.a = new LinkedHashSet();
    }

    public void a(ae arg2) {
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

    public void b(ae arg2) {
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

    public boolean c(ae arg2) {
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

