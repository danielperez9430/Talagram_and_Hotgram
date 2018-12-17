package com.google.ads.interactivemedia.v3.internal;

import java.util.Arrays;
import java.util.Collections;

final class dh extends dm {
    private fi e;
    private fh f;
    private boolean g;

    dh() {
        super();
    }

    static boolean a(fp arg4) {
        boolean v4 = arg4.f() != 127 || arg4.k() != 1179402563 ? false : true;
        return v4;
    }

    public int a(cd arg18, ch arg19) {
        dh v0 = this;
        long v1 = arg18.c();
        int v4 = -1;
        if(!v0.b.a(arg18, v0.a)) {
            return v4;
        }

        byte[] v3 = v0.a.a;
        if(v0.e == null) {
            v0.e = new fi(v3, 17);
            byte[] v1_1 = Arrays.copyOfRange(v3, 9, v0.a.c());
            v1_1[4] = -128;
            v0.c.a(bj.a(null, "audio/x-flac", v0.e.a(), -1, v0.e.b(), v0.e.f, v0.e.e, Collections.singletonList(v1_1), null));
        }
        else if(v3[0] == v4) {
            if(!v0.g) {
                if(v0.f != null) {
                    v0.d.a(v0.f.a(v1, ((long)v0.e.e)));
                    v0.f = null;
                }
                else {
                    v0.d.a(cj.f);
                }

                v0.g = true;
            }

            v0.c.a(v0.a, v0.a.c());
            v0.a.c(0);
            v0.c.a(fj.a(v0.e, v0.a), 1, v0.a.c(), 0, null);
        }
        else {
            if((v3[0] & 127) != 3) {
                goto label_90;
            }

            if(v0.f != null) {
                goto label_90;
            }

            v0.f = fh.a(v0.a);
        }

    label_90:
        v0.a.a();
        return 0;
    }
}

