package org.telegram.customization.fetch;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.telegram.customization.fetch.d.b;

public final class c {
    final class org.telegram.customization.fetch.c$1 implements a {
        org.telegram.customization.fetch.c$1() {
            super();
        }

        public void a(b arg2) {
            c.c().remove(arg2);
        }
    }

    class org.telegram.customization.fetch.c$2 extends BroadcastReceiver {
        private long b;
        private int c;
        private int d;
        private long e;
        private long f;
        private int g;

        org.telegram.customization.fetch.c$2(c arg1) {
            this.a = arg1;
            super();
        }

        public void onReceive(Context arg11, Intent arg12) {
            if(arg12 == null) {
                return;
            }

            this.b = arg12.getLongExtra("com.tonyodev.fetch.extra_id", -1);
            this.c = arg12.getIntExtra("com.tonyodev.fetch.extra_status", -1);
            this.d = arg12.getIntExtra("com.tonyodev.fetch.extra_progress", -1);
            this.e = arg12.getLongExtra("com.tonyodev.fetch.extra_downloaded_bytes", -1);
            this.f = arg12.getLongExtra("com.tonyodev.fetch.extra_file_size", -1);
            this.g = arg12.getIntExtra("com.tonyodev.fetch.extra_error", -1);
            try {
                Iterator v11 = c.a(this.a).iterator();
                while(true) {
                    if(!v11.hasNext()) {
                        return;
                    }

                    v11.next().onUpdate(this.b, this.c, this.d, this.e, this.f, this.g);
                }
            }
            catch(Exception ) {
                return;
            }
        }
    }

    class org.telegram.customization.fetch.c$3 extends BroadcastReceiver {
        org.telegram.customization.fetch.c$3(c arg1) {
            this.a = arg1;
            super();
        }

        public void onReceive(Context arg1, Intent arg2) {
            FetchService.a(arg1);
        }
    }

    public class org.telegram.customization.fetch.c$a {
        private final Context a;
        private final List b;

        public org.telegram.customization.fetch.c$a(Context arg2) {
            super();
            this.b = new ArrayList();
            if(arg2 != null) {
                this.a = arg2;
                return;
            }

            throw new NullPointerException("Context cannot be null");
        }

        public org.telegram.customization.fetch.c$a a(int arg4) {
            int v0 = 201;
            if(arg4 == v0) {
            }
            else {
                v0 = 200;
            }

            Bundle v4 = new Bundle();
            v4.putInt("com.tonyodev.fetch.action_type", 314);
            v4.putInt("com.tonyodev.fetch.extra_network_id", v0);
            this.b.add(v4);
            return this;
        }

        public org.telegram.customization.fetch.c$a a(boolean arg4) {
            Bundle v0 = new Bundle();
            v0.putInt("com.tonyodev.fetch.action_type", 320);
            v0.putBoolean("com.tonyodev.fetch.extra_logging_id", arg4);
            this.b.add(v0);
            return this;
        }

        public void a() {
            Iterator v0 = this.b.iterator();
            while(v0.hasNext()) {
                FetchService.a(this.a, v0.next());
            }
        }

        public org.telegram.customization.fetch.c$a b(int arg4) {
            Bundle v0 = new Bundle();
            v0.putInt("com.tonyodev.fetch.action_type", 321);
            v0.putInt("com.tonyodev.fetch.extra_concurrent_download_limit", arg4);
            this.b.add(v0);
            return this;
        }
    }

    private static final Handler a;
    private static final ConcurrentMap b;
    private final Context c;
    private final android.support.v4.content.c d;
    private final List e;
    private final org.telegram.customization.fetch.a f;
    private volatile boolean g;
    private static final a h;
    private final BroadcastReceiver i;
    private final BroadcastReceiver j;

    static {
        c.a = new Handler(Looper.getMainLooper());
        c.b = new ConcurrentHashMap();
        c.h = new org.telegram.customization.fetch.c$1();
    }

    private c(Context arg4) {
        super();
        this.e = new ArrayList();
        this.g = false;
        this.i = new org.telegram.customization.fetch.c$2(this);
        this.j = new org.telegram.customization.fetch.c$3(this);
        this.c = arg4.getApplicationContext();
        this.d = android.support.v4.content.c.a(this.c);
        this.f = org.telegram.customization.fetch.a.a(this.c);
        this.f.a(this.d());
        this.d.a(this.i, FetchService.a());
        this.c.registerReceiver(this.j, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
        c.a(this.c);
    }

    public static void a(Context arg0) {
        FetchService.a(arg0);
    }

    static List a(c arg0) {
        return arg0.e;
    }

    public long a(b arg18, long arg19) {
        c v1 = this;
        f.a(this);
        if(arg18 != null) {
            long v15 = f.a();
            try {
                String v5 = arg18.a();
                String v6 = arg18.b();
                int v13 = arg18.d();
                String v8 = f.a(arg18.c(), this.d());
                long v2 = 0;
                File v0_1 = f.f(v6);
                if(v0_1.exists()) {
                    v2 = v0_1.length();
                }

                if(v1.f.a(v15, v5, v6, 900, v8, v2, arg19, v13, -1)) {
                    c.a(v1.c);
                    return v15;
                }

                throw new org.telegram.customization.fetch.b.b("could not insert request", -117);
            }
            catch(org.telegram.customization.fetch.b.b v0) {
                if(this.d()) {
                    v0.printStackTrace();
                }

                v15 = -1;
            }

            return v15;
        }

        throw new NullPointerException("Request cannot be null");
    }

    public void a() {
        f.a(this);
        Bundle v0 = new Bundle();
        v0.putInt("com.tonyodev.fetch.action_type", 325);
        FetchService.a(this.c, v0);
    }

    public void a(long arg4) {
        f.a(this);
        Bundle v0 = new Bundle();
        v0.putInt("com.tonyodev.fetch.action_type", 324);
        v0.putLong("com.tonyodev.fetch.extra_id", arg4);
        FetchService.a(this.c, v0);
    }

    public void a(org.telegram.customization.fetch.c.a arg2) {
        f.a(this);
        if(arg2 != null) {
            if(this.e.contains(arg2)) {
                return;
            }

            this.e.add(arg2);
            return;
        }

        throw new NullPointerException("fetchListener cannot be null");
    }

    public static c b(Context arg1) {
        if(arg1 != null) {
            return new c(arg1);
        }

        throw new NullPointerException("Context cannot be null");
    }

    public void b(org.telegram.customization.fetch.c.a arg2) {
        f.a(this);
        if(arg2 == null) {
            return;
        }

        this.e.remove(arg2);
    }

    boolean b() {
        return this.g;
    }

    static ConcurrentMap c() {
        return c.b;
    }

    private boolean d() {
        return FetchService.b(this.c);
    }
}

