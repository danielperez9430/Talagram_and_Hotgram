package com.crashlytics.android.c;

final class an {
    static final int a;
    static final int b;
    static final int c;
    static final int d;

    static {
        an.a = an.a(1, 3);
        an.b = an.a(1, 4);
        an.c = an.a(2, 0);
        an.d = an.a(3, 2);
    }

    static int a(int arg0, int arg1) {
        return arg0 << 3 | arg1;
    }
}

