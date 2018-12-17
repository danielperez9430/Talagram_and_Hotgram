package com.c.b;

public abstract class a extends c {
    public a(String arg2) {
        super(Float.class, arg2);
    }

    public abstract void a(Object arg1, float arg2);

    public final void a(Object arg1, Float arg2) {
        this.a(arg1, arg2.floatValue());
    }

    public void a(Object arg1, Object arg2) {
        this.a(arg1, ((Float)arg2));
    }
}

