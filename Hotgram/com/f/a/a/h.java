package com.f.a.a;

public abstract class h extends Exception {
    private String a;
    private Integer b;

    public h(String arg2, String arg3, Integer arg4) {
        super(arg2, null);
        this.a = arg3;
        this.b = arg4;
    }

    public h(String arg1, String arg2, Integer arg3, Throwable arg4) {
        super(arg1, arg4);
        this.b = arg3;
        this.a = arg2;
    }

    public String toString() {
        String v0 = "";
        if(this.a != null) {
            v0 = "; request-id: " + this.a;
        }

        return super.toString() + v0;
    }
}

