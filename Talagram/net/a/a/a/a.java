package net.a.a.a;

import c.b.d.d;
import java.util.HashMap;
import java.util.Map;

public class a {
    private c.b.g.a a;
    private Map b;

    public a() {
        super();
        this.b = new HashMap();
        this.a = c.b.g.a.b();
        this.a.a(c.b.a.b.a.a());
    }

    public void a(d arg2) {
        this.b(arg2).a(this.a.a(arg2));
    }

    public void a(Object arg2) {
        this.a.a(arg2);
    }

    public void b(d arg2) {
        Object v2 = this.b.remove(arg2);
        if(v2 != null) {
            ((c.b.b.a)v2).a();
        }
    }

    private c.b.b.a b(Object arg3) {
        c.b.b.a v0_1;
        Object v0 = this.b.get(arg3);
        if(v0 == null) {
            v0_1 = new c.b.b.a();
            this.b.put(arg3, v0_1);
        }

        return v0_1;
    }
}

