package com.google.ads.interactivemedia.v3.internal;

import android.content.Context;

public final class ex implements fc {
    private final fc a;
    private final fc b;
    private final fc c;
    private final fc d;
    private fc e;

    public ex(Context arg9, fb arg10, String arg11, boolean arg12) {
        this(arg9, arg10, new ew(arg11, null, arg10, 8000, 8000, arg12));
    }

    public ex(Context arg1, fb arg2, fc arg3) {
        super();
        this.a = fe.a(arg3);
        this.b = new ey(arg2);
        this.c = new er(arg1, arg2);
        this.d = new es(arg1, arg2);
    }

    public int a(byte[] arg2, int arg3, int arg4) {
        return this.e.a(arg2, arg3, arg4);
    }

    public long a(eu arg3) {
        fc v0_2;
        boolean v0 = this.e == null ? true : false;
        fe.b(v0);
        String v0_1 = arg3.a.getScheme();
        if(ft.a(arg3.a)) {
            if(!arg3.a.getPath().startsWith("/android_asset/")) {
                v0_2 = this.b;
            }
            else {
                goto label_16;
            }
        }
        else if("asset".equals(v0_1)) {
        label_16:
            v0_2 = this.c;
        }
        else if("content".equals(v0_1)) {
            v0_2 = this.d;
        }
        else {
            v0_2 = this.a;
        }

        this.e = v0_2;
        return this.e.a(arg3);
    }

    public void a() {
        if(this.e != null) {
            fc v0 = null;
            try {
                this.e.a();
            }
            catch(Throwable v1) {
                this.e = v0;
                throw v1;
            }

            this.e = v0;
        }
    }
}

