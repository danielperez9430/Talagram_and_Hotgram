package com.google.ads.interactivemedia.v3.internal;

import android.content.Context;
import android.net.Uri;
import android.os.Handler;

public class jp {
    private final Context a;
    private final String b;
    private final Uri c;

    public jp(Context arg1, String arg2, Uri arg3) {
        super();
        this.a = arg1;
        this.b = arg2;
        this.c = arg3;
    }

    public bq[] a(iy arg19, Handler arg20) {
        cf v17 = new cf(this.c, new ex(this.a, null, this.b, true), new ev(65536), 16777216, arg20, arg19.d(), 0, new cc[0]);
        bi v1 = new bi(this.a, v17, bf.a, 1, 5000, arg20, arg19.e(), 50);
        be v2 = new be(v17, bf.a, null, true, arg20, arg19.f(), bs.a(this.a), 3);
        bq[] v3 = new bq[2];
        v3[g.a.a()] = v1;
        v3[g.b.a()] = v2;
        return v3;
    }
}

