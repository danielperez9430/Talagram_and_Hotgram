package com.crashlytics.android.c;

class y implements ai {
    private final int a;

    public y(int arg1) {
        super();
        this.a = arg1;
    }

    public StackTraceElement[] a(StackTraceElement[] arg5) {
        if(arg5.length <= this.a) {
            return arg5;
        }

        int v0 = this.a / 2;
        int v1 = this.a - v0;
        StackTraceElement[] v2 = new StackTraceElement[this.a];
        System.arraycopy(arg5, 0, v2, 0, v1);
        System.arraycopy(arg5, arg5.length - v0, v2, v1, v0);
        return v2;
    }
}

