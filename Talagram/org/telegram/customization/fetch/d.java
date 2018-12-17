package org.telegram.customization.fetch;

import android.os.Handler;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import org.telegram.customization.fetch.d.b;

final class d implements Runnable {
    interface a {
        void a(b arg1);
    }

    private final b a;
    private final org.telegram.customization.fetch.a.a b;
    private final a c;
    private final Handler d;
    private volatile boolean e;
    private HttpURLConnection f;
    private InputStream g;
    private BufferedReader h;
    private String i;

    static String a(d arg0) {
        return arg0.i;
    }

    private void a() {
        this.f = new URL(this.a.a()).openConnection();
        this.f.setRequestMethod("GET");
        this.f.setReadTimeout(15000);
        this.f.setConnectTimeout(10000);
        this.f.setUseCaches(true);
        this.f.setDefaultUseCaches(true);
        this.f.setInstanceFollowRedirects(true);
        this.f.setDoInput(true);
        Iterator v0 = this.a.c().iterator();
        while(v0.hasNext()) {
            Object v1 = v0.next();
            this.f.addRequestProperty(((org.telegram.customization.fetch.d.a)v1).a(), ((org.telegram.customization.fetch.d.a)v1).b());
        }
    }

    private String b() {
        StringBuilder v0 = new StringBuilder();
        this.h = new BufferedReader(new InputStreamReader(this.g));
        while(true) {
            String v1 = this.h.readLine();
            if(v1 != null && !this.d()) {
                v0.append(v1);
                continue;
            }

            break;
        }

        if(this.d()) {
            return null;
        }

        return v0.toString();
    }

    static b b(d arg0) {
        return arg0.a;
    }

    static org.telegram.customization.fetch.a.a c(d arg0) {
        return arg0.b;
    }

    private void c() {
        try {
            if(this.g == null) {
                goto label_7;
            }

            this.g.close();
        }
        catch(IOException v0) {
            v0.printStackTrace();
        }

        try {
        label_7:
            if(this.h == null) {
                goto label_14;
            }

            this.h.close();
        }
        catch(IOException v0) {
            v0.printStackTrace();
        }

    label_14:
        if(this.f != null) {
            this.f.disconnect();
        }
    }

    private boolean d() {
        return this.e;
    }

    public void run() {
        int v0_2;
        try {
            this.a();
            this.f.connect();
            v0_2 = this.f.getResponseCode();
            if(v0_2 == 200) {
                if(!this.d()) {
                    this.g = this.f.getInputStream();
                    this.i = this.b();
                    if(this.d()) {
                        goto label_47;
                    }

                    this.d.post(new Runnable() {
                        public void run() {
                            d.c(this.a).a(d.a(this.a), d.b(this.a));
                        }
                    });
                    goto label_47;
                }

                throw new org.telegram.customization.fetch.b.a("DIE", -118);
            }

            StringBuilder v2 = new StringBuilder();
            v2.append("SSRV:");
            v2.append(v0_2);
            throw new IllegalStateException(v2.toString());
        }
        catch(Throwable v0) {
        label_52:
            this.c();
            this.c.a(this.a);
            throw v0;
        }
        catch(Exception v0_1) {
            try {
                v0_1.printStackTrace();
                v0_2 = org.telegram.customization.fetch.b.a(v0_1.getMessage());
                if(this.d()) {
                    goto label_47;
                }

                this.d.post(new Runnable(v0_2) {
                    public void run() {
                        d.c(this.b).a(this.a, d.b(this.b));
                    }
                });
            }
            catch(Throwable v0) {
                goto label_52;
            }

        label_47:
            this.c();
            this.c.a(this.a);
            return;
        }
    }
}

