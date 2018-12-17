package org.telegram.customization.fetch.b;

public final class b extends RuntimeException {
    private int a;

    public b(String arg1, int arg2) {
        super(arg1);
        this.a = arg2;
    }

    public int a() {
        return this.a;
    }
}

