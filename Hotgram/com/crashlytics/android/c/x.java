package com.crashlytics.android.c;

class x implements ai {
    private final int a;
    private final ai[] b;
    private final y c;

    public x(int arg1, ai[] arg2) {
        super();
        this.a = arg1;
        this.b = arg2;
        this.c = new y(arg1);
    }

    public StackTraceElement[] a(StackTraceElement[] arg8) {
        if(arg8.length <= this.a) {
            return arg8;
        }

        ai[] v0 = this.b;
        int v1 = v0.length;
        int v2 = 0;
        StackTraceElement[] v3 = arg8;
        while(v2 < v1) {
            ai v4 = v0[v2];
            if(v3.length <= this.a) {
            }
            else {
                v3 = v4.a(arg8);
                ++v2;
                continue;
            }

            break;
        }

        if(v3.length > this.a) {
            v3 = this.c.a(v3);
        }

        return v3;
    }
}

