package androidx.work.impl.utils;

import androidx.work.impl.g;
import androidx.work.u$a;

public class f implements Runnable {
    private g a;
    private String b;
    private a c;

    public f(g arg1, String arg2, a arg3) {
        super();
        this.a = arg1;
        this.b = arg2;
        this.c = arg3;
    }

    public void run() {
        this.a.g().a(this.b, this.c);
    }
}

