package com.d.a.b.a;

public class e {
    private final int a;
    private final int b;

    public e(int arg1, int arg2) {
        super();
        this.a = arg1;
        this.b = arg2;
    }

    public e(int arg1, int arg2, int arg3) {
        super();
        if(arg3 % 180 == 0) {
            this.a = arg1;
            this.b = arg2;
        }
        else {
            this.a = arg2;
            this.b = arg1;
        }
    }

    public int a() {
        return this.a;
    }

    public e a(float arg4) {
        return new e(((int)((((float)this.a)) * arg4)), ((int)((((float)this.b)) * arg4)));
    }

    public e a(int arg4) {
        return new e(this.a / arg4, this.b / arg4);
    }

    public int b() {
        return this.b;
    }

    public String toString() {
        StringBuilder v0 = new StringBuilder(9);
        v0.append(this.a);
        v0.append("x");
        v0.append(this.b);
        return v0.toString();
    }
}

