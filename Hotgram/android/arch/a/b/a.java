package android.arch.a.b;

import java.util.HashMap;
import java.util.Map$Entry;

public class a extends b {
    private HashMap a;

    public a() {
        super();
        this.a = new HashMap();
    }

    protected c a(Object arg2) {
        return this.a.get(arg2);
    }

    public Object a(Object arg2, Object arg3) {
        c v0 = this.a(arg2);
        if(v0 != null) {
            return v0.b;
        }

        this.a.put(arg2, this.b(arg2, arg3));
        return null;
    }

    public Object b(Object arg3) {
        Object v0 = super.b(arg3);
        this.a.remove(arg3);
        return v0;
    }

    public boolean c(Object arg2) {
        return this.a.containsKey(arg2);
    }

    public Map$Entry d(Object arg2) {
        if(this.c(arg2)) {
            return this.a.get(arg2).d;
        }

        return null;
    }
}

