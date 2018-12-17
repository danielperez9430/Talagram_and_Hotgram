package com.persianswitch.sdk.base.webservice.trust;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import javax.net.ssl.X509TrustManager;

public class CompositeTrustManager implements X509TrustManager {
    private ArrayList a;
    private boolean b;

    protected CompositeTrustManager(X509TrustManager[] arg2, boolean arg3) {
        super();
        this.a = new ArrayList();
        if(arg2 != null) {
            this.c(arg2);
        }

        this.a(arg3);
    }

    public void a(boolean arg3) {
        if(this.a.size() <= 1) {
            this.b = arg3;
            return;
        }

        throw new IllegalStateException("Cannot change mode once 2+ managers added");
    }

    public static CompositeTrustManager a(X509TrustManager[] arg2) {
        return new CompositeTrustManager(arg2, true);
    }

    public void a(X509TrustManager arg2) {
        this.a.add(arg2);
    }

    public boolean a() {
        return this.b;
    }

    public static CompositeTrustManager b(X509TrustManager[] arg2) {
        return new CompositeTrustManager(arg2, false);
    }

    public int b() {
        return this.a.size();
    }

    public void c(X509TrustManager[] arg5) {
        int v0 = arg5.length;
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            this.a.add(arg5[v1]);
        }
    }

    public void checkClientTrusted(X509Certificate[] arg4, String arg5) {
        Iterator v0 = this.a.iterator();
        CertificateException v1 = null;
        while(true) {
            if(!v0.hasNext()) {
                goto label_15;
            }

            Object v2 = v0.next();
            try {
                ((X509TrustManager)v2).checkClientTrusted(arg4, arg5);
                if(this.b) {
                    continue;
                }

                return;
            }
            catch(CertificateException v1) {
                if(!this.b) {
                    continue;
                }

                throw v1;
            }
        }

        return;
    label_15:
        if(v1 == null) {
            return;
        }

        throw v1;
    }

    public void checkServerTrusted(X509Certificate[] arg4, String arg5) {
        Iterator v0 = this.a.iterator();
        CertificateException v1 = null;
        while(true) {
            if(!v0.hasNext()) {
                goto label_15;
            }

            Object v2 = v0.next();
            try {
                ((X509TrustManager)v2).checkClientTrusted(arg4, arg5);
                if(this.b) {
                    continue;
                }

                return;
            }
            catch(CertificateException v1) {
                if(!this.b) {
                    continue;
                }

                throw v1;
            }
        }

        return;
    label_15:
        if(v1 == null) {
            return;
        }

        throw v1;
    }

    public X509Certificate[] getAcceptedIssuers() {
        HashSet v0 = new HashSet();
        Iterator v1 = this.a.iterator();
    label_4:
        if(v1.hasNext()) {
            X509Certificate[] v2 = v1.next().getAcceptedIssuers();
            int v3 = v2.length;
            int v4;
            for(v4 = 0; true; ++v4) {
                if(v4 >= v3) {
                    goto label_4;
                }

                v0.add(v2[v4]);
            }
        }

        return v0.toArray(new X509Certificate[v0.size()]);
    }
}

