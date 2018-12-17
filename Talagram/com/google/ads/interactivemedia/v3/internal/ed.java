package com.google.ads.interactivemedia.v3.internal;

final class ed {
    private final ck a;

    public ed(ck arg7) {
        super();
        this.a = arg7;
        arg7.a(bj.a(null, "application/eia-608", -1, -1, null));
    }

    public void a(long arg12, fp arg14) {
        int v8;
        int v2;
        while(arg14.b() > 1) {
            int v0 = 0;
            int v1 = 0;
            do {
                v2 = arg14.f();
                v1 += v2;
                int v3 = 255;
            }
            while(v2 == v3);

            while(true) {
                v2 = arg14.f();
                v8 = v0 + v2;
                if(v2 != v3) {
                    break;
                }

                v0 = v8;
            }

            if(eo.a(v1, v8, arg14)) {
                this.a.a(arg14, v8);
                this.a.a(arg12, 1, v8, 0, null);
                continue;
            }

            arg14.d(v8);
        }
    }
}

