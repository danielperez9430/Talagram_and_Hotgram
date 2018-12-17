package androidx.work.impl.a.a;

import androidx.work.impl.a.a;
import androidx.work.impl.a.b.d;
import androidx.work.impl.b.j;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class c implements a {
    public interface androidx.work.impl.a.a.c$a {
        void b(List arg1);

        void c(List arg1);
    }

    private final List a;
    private Object b;
    private d c;
    private androidx.work.impl.a.a.c$a d;

    c(d arg2) {
        super();
        this.a = new ArrayList();
        this.c = arg2;
    }

    public void a() {
        if(!this.a.isEmpty()) {
            this.a.clear();
            this.c.b(((a)this));
        }
    }

    public void a(androidx.work.impl.a.a.c$a arg2) {
        if(this.d != arg2) {
            this.d = arg2;
            this.b();
        }
    }

    public void a(Object arg1) {
        this.b = arg1;
        this.b();
    }

    public void a(List arg3) {
        this.a.clear();
        Iterator v3 = arg3.iterator();
        while(v3.hasNext()) {
            Object v0 = v3.next();
            if(!this.a(((j)v0))) {
                continue;
            }

            this.a.add(((j)v0).a);
        }

        if(this.a.isEmpty()) {
            this.c.b(((a)this));
        }
        else {
            this.c.a(((a)this));
        }

        this.b();
    }

    abstract boolean a(j arg1);

    public boolean a(String arg2) {
        boolean v2 = this.b == null || !this.b(this.b) || !this.a.contains(arg2) ? false : true;
        return v2;
    }

    private void b() {
        if(!this.a.isEmpty()) {
            if(this.d == null) {
            }
            else {
                if(this.b != null) {
                    if(this.b(this.b)) {
                    }
                    else {
                        this.d.b(this.a);
                        return;
                    }
                }

                this.d.c(this.a);
            }
        }
    }

    abstract boolean b(Object arg1);
}

