package com.c.b;

public abstract class b extends c {
    public b(String arg2) {
        super(Integer.class, arg2);
    }

    public abstract void a(Object arg1, int arg2);

    public final void a(Object arg1, Integer arg2) {
        this.a(arg1, Integer.valueOf(arg2.intValue()));
    }

    public void a(Object arg1, Object arg2) {
        this.a(arg1, ((Integer)arg2));
    }
}

