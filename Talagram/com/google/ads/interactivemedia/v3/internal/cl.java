package com.google.ads.interactivemedia.v3.internal;

import android.util.Pair;
import java.util.Collections;

final class cl extends co {
    private static final int[] b;
    private boolean c;
    private boolean d;

    static {
        cl.b = new int[]{5500, 11000, 22000, 44000};
    }

    public cl(ck arg1) {
        super(arg1);
    }

    protected void a(fp arg13, long arg14) {
        int v0 = arg13.f();
        if(v0 == 0 && !this.d) {
            byte[] v14 = new byte[arg13.b()];
            arg13.a(v14, 0, v14.length);
            Pair v13 = ff.a(v14);
            this.a.a(bj.a(null, "audio/mp4a-latm", -1, -1, this.a(), v13.second.intValue(), v13.first.intValue(), Collections.singletonList(v14), null));
            this.d = true;
        }
        else if(v0 == 1) {
            int v6 = arg13.b();
            this.a.a(arg13, v6);
            this.a.a(arg14, 1, v6, 0, null);
        }
    }

    protected boolean a(fp arg4) {
        StringBuilder v2;
        if(!this.c) {
            int v4 = arg4.f();
            int v0 = v4 >> 4 & 15;
            v4 = v4 >> 2 & 3;
            if(v4 >= 0 && v4 < cl.b.length) {
                if(v0 == 10) {
                    this.c = true;
                    return 1;
                }
                else {
                    v2 = new StringBuilder(39);
                    v2.append("Audio format not supported: ");
                    v2.append(v0);
                    throw new a(v2.toString());
                }
            }

            v2 = new StringBuilder(38);
            v2.append("Invalid sample rate index: ");
            v2.append(v4);
            throw new a(v2.toString());
        }
        else {
            arg4.d(1);
        }

        return 1;
    }
}

