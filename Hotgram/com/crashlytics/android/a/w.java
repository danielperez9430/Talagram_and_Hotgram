package com.crashlytics.android.a;

import c.a.a.a.a.c.a.a;
import java.util.Random;

class w implements a {
    final a a;
    final Random b;
    final double c;

    public w(a arg2, double arg3) {
        this(arg2, arg3, new Random());
    }

    public w(a arg4, double arg5, Random arg7) {
        super();
        if(arg5 >= 0 && arg5 <= 1) {
            if(arg4 == null) {
                throw new NullPointerException("backoff must not be null");
            }
            else if(arg7 != null) {
                this.a = arg4;
                this.c = arg5;
                this.b = arg7;
                return;
            }
            else {
                throw new NullPointerException("random must not be null");
            }
        }

        throw new IllegalArgumentException("jitterPercent must be between 0.0 and 1.0");
    }

    double a() {
        double v0 = 1 - this.c;
        return v0 + (this.c + 1 - v0) * this.b.nextDouble();
    }

    public long a(int arg5) {
        double v0 = this.a();
        double v2 = ((double)this.a.a(arg5));
        Double.isNaN(v2);
        return ((long)(v0 * v2));
    }
}

