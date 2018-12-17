package com.c.a;

import java.util.ArrayList;

public abstract class a implements Cloneable {
    public interface com.c.a.a$a {
        void a(a arg1);

        void b(a arg1);

        void c(a arg1);
    }

    ArrayList a;

    public a() {
        super();
        this.a = null;
    }

    public void a() {
    }

    public void b() {
    }

    public a c() {
        Object v0;
        try {
            v0 = super.clone();
            if(this.a != null) {
                ArrayList v1 = this.a;
                ((a)v0).a = new ArrayList();
                int v2 = v1.size();
                int v3;
                for(v3 = 0; v3 < v2; ++v3) {
                    ((a)v0).a.add(v1.get(v3));
                }
            }
        }
        catch(CloneNotSupportedException ) {
            throw new AssertionError();
        }

        return ((a)v0);
    }

    public Object clone() {
        return this.c();
    }
}

