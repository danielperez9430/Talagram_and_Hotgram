package com.google.ads.interactivemedia.v3.internal;

import android.content.Context;
import com.google.android.gms.ads.identifier.AdvertisingIdClient$Info;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class ka extends jz {
    class a {
        private String b;
        private boolean c;

        public a(ka arg1, String arg2, boolean arg3) {
            this.a = arg1;
            super();
            this.b = arg2;
            this.c = arg3;
        }

        public String a() {
            return this.b;
        }

        public boolean b() {
            return this.c;
        }
    }

    final class b implements Runnable {
        private Context a;

        public b(Context arg2) {
            super();
            this.a = arg2.getApplicationContext();
            if(this.a == null) {
                this.a = arg2;
            }
        }

        public void run() {
            Class v0 = ka.class;
            __monitor_enter(v0);
            try {
                if(ka.f() == null) {
                    AdvertisingIdClient v1_1 = new AdvertisingIdClient(this.a);
                    v1_1.start();
                    ka.a(v1_1);
                }

                goto label_9;
            }
            catch(Throwable v1) {
            }
            catch(GooglePlayServicesRepairableException ) {
            }
            catch(IOException ) {
            }
            catch(GooglePlayServicesNotAvailableException ) {
                try {
                    ka.a(true);
                }
                catch(Throwable v1) {
                    goto label_26;
                }

                try {
                    CountDownLatch v1_2 = ka.g();
                    goto label_10;
                    v1_2 = ka.g();
                    goto label_10;
                    v1_2 = ka.g();
                    goto label_10;
                label_26:
                    ka.g().countDown();
                    throw v1;
                label_9:
                    v1_2 = ka.g();
                label_10:
                    v1_2.countDown();
                    __monitor_exit(v0);
                    return;
                }
                catch(Throwable v1) {
                label_19:
                    try {
                        __monitor_exit(v0);
                    }
                    catch(Throwable v1) {
                        goto label_19;
                    }

                    throw v1;
                }
            }
        }
    }

    private static AdvertisingIdClient e;
    private static CountDownLatch f;
    private static volatile boolean g;
    private boolean h;

    static {
        ka.f = new CountDownLatch(1);
    }

    protected ka(Context arg1, kd arg2, ke arg3, boolean arg4) {
        super(arg1, arg2, arg3);
        this.h = arg4;
    }

    public static ka a(String arg1, Context arg2) {
        return ka.a(arg1, arg2, true);
    }

    public static ka a(String arg3, Context arg4, boolean arg5) {
        jv v0 = new jv();
        ka.a(arg3, arg4, ((kd)v0));
        if(arg5) {
            Class v3 = ka.class;
            __monitor_enter(v3);
            try {
                if(ka.e == null) {
                    new Thread(new b(arg4)).start();
                }

                __monitor_exit(v3);
                goto label_18;
            label_16:
                __monitor_exit(v3);
            }
            catch(Throwable v4) {
                goto label_16;
            }

            throw v4;
        }

    label_18:
        return new ka(arg4, ((kd)v0), new kg(239), arg5);
    }

    static AdvertisingIdClient a(AdvertisingIdClient arg0) {
        ka.e = arg0;
        return arg0;
    }

    static boolean a(boolean arg0) {
        ka.g = arg0;
        return arg0;
    }

    protected void b(Context arg6) {
        long v3;
        super.b(arg6);
        try {
            int v1 = 24;
            if((ka.g) || !this.h) {
                this.a(v1, ka.d(arg6));
                return;
            label_7:
                a v6 = this.e();
                String v0 = v6.a();
                if(v0 != null) {
                    int v2 = 28;
                    if(v6.b()) {
                        v3 = 1;
                    }
                    else {
                        goto label_15;
                    }

                label_16:
                    this.a(v2, v3);
                    this.a(26, 5);
                    this.a(v1, v0);
                    return;
                label_15:
                    v3 = 0;
                    goto label_16;
                }
            }
            else {
                goto label_7;
            }

            return;
        }
        catch(com.google.ads.interactivemedia.v3.internal.jz$a ) {
            return;
        }
    }

    a e() {
        Info v0_1;
        String v1 = null;
        try {
            if(ka.f.await(2, TimeUnit.SECONDS)) {
                goto label_10;
            }

            return new a(this, v1, false);
        }
        catch(InterruptedException ) {
            return new a(this, v1, false);
        }

    label_10:
        Class v2 = ka.class;
        __monitor_enter(v2);
        try {
            if(ka.e == null) {
                __monitor_exit(v2);
                return new a(this, v1, false);
            }

            v0_1 = ka.e.getInfo();
            __monitor_exit(v2);
        }
        catch(Throwable v0) {
            try {
            label_28:
                __monitor_exit(v2);
            }
            catch(Throwable v0) {
                goto label_28;
            }

            throw v0;
        }

        return new a(this, this.a(v0_1.getId()), v0_1.isLimitAdTrackingEnabled());
    }

    static AdvertisingIdClient f() {
        return ka.e;
    }

    static CountDownLatch g() {
        return ka.f;
    }
}

