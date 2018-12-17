package android.support.v4.f;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map$Entry;
import java.util.Map;
import java.util.Set;

public class a extends m implements Map {
    h a;

    public a() {
        super();
    }

    public a(m arg1) {
        super(arg1);
    }

    public a(int arg1) {
        super(arg1);
    }

    public boolean a(Collection arg1) {
        return h.c(((Map)this), arg1);
    }

    private h b() {
        if(this.a == null) {
            this.a = new h() {
                protected int a() {
                    return this.a.h;
                }

                protected int a(Object arg2) {
                    return this.a.a(arg2);
                }

                protected Object a(int arg2, int arg3) {
                    return this.a.g[(arg2 << 1) + arg3];
                }

                protected Object a(int arg2, Object arg3) {
                    return this.a.a(arg2, arg3);
                }

                protected void a(int arg2) {
                    this.a.d(arg2);
                }

                protected void a(Object arg2, Object arg3) {
                    this.a.put(arg2, arg3);
                }

                protected int b(Object arg2) {
                    return this.a.b(arg2);
                }

                protected Map b() {
                    return this.a;
                }

                protected void c() {
                    this.a.clear();
                }
            };
        }

        return this.a;
    }

    public Set entrySet() {
        return this.b().d();
    }

    public Set keySet() {
        return this.b().e();
    }

    public void putAll(Map arg3) {
        this.a(this.h + arg3.size());
        Iterator v3 = arg3.entrySet().iterator();
        while(v3.hasNext()) {
            Object v0 = v3.next();
            this.put(((Map$Entry)v0).getKey(), ((Map$Entry)v0).getValue());
        }
    }

    public Collection values() {
        return this.b().f();
    }
}

