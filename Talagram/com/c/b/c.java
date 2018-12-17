package com.c.b;

public abstract class c {
    private final String a;
    private final Class b;

    public c(Class arg1, String arg2) {
        super();
        this.a = arg2;
        this.b = arg1;
    }

    public String a() {
        return this.a;
    }

    public abstract Object a(Object arg1);

    public void a(Object arg2, Object arg3) {
        StringBuilder v3 = new StringBuilder();
        v3.append("Property ");
        v3.append(this.a());
        v3.append(" is read-only");
        throw new UnsupportedOperationException(v3.toString());
    }
}

