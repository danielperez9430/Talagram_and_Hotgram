package android.arch.lifecycle;

import java.util.HashMap;
import java.util.Iterator;

public class u {
    private final HashMap a;

    public u() {
        super();
        this.a = new HashMap();
    }

    final s a(String arg2) {
        return this.a.get(arg2);
    }

    final void a(String arg2, s arg3) {
        Object v2 = this.a.put(arg2, arg3);
        if(v2 != null) {
            ((s)v2).a();
        }
    }

    public final void a() {
        Iterator v0 = this.a.values().iterator();
        while(v0.hasNext()) {
            v0.next().a();
        }

        this.a.clear();
    }
}

