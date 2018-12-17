package com.crashlytics.android.a;

import java.util.HashSet;
import java.util.Set;

class y implements p {
    final class com.crashlytics.android.a.y$1 extends HashSet {
        com.crashlytics.android.a.y$1() {
            super();
            this.add(b.a);
            this.add(b.b);
            this.add(b.c);
            this.add(b.d);
        }
    }

    final int a;
    static final Set b;

    static {
        y.b = new com.crashlytics.android.a.y$1();
    }

    public y(int arg1) {
        super();
        this.a = arg1;
    }

    public boolean a(ad arg5) {
        boolean v1 = true;
        int v0 = !y.b.contains(arg5.c) || arg5.a.g != null ? 0 : 1;
        int v5 = Math.abs(arg5.a.c.hashCode() % this.a) != 0 ? 1 : 0;
        if(v0 == 0 || v5 == 0) {
            v1 = false;
        }
        else {
        }

        return v1;
    }
}

