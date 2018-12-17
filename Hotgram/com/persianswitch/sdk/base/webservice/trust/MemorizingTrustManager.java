package com.persianswitch.sdk.base.webservice.trust;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

public class MemorizingTrustManager implements X509TrustManager {
    public class Options {
        File a;
        String b;
        boolean c;

    }

    private KeyStore a;
    private Options b;
    private X509TrustManager c;
    private KeyStore d;
    private X509TrustManager e;

    private void a() {
        TrustManagerFactory v0 = TrustManagerFactory.getInstance("X509");
        v0.init(this.a);
        TrustManager[] v0_1 = v0.getTrustManagers();
        int v1 = v0_1.length;
        int v2 = 0;
        int v3 = 0;
        while(v3 < v1) {
            TrustManager v4 = v0_1[v3];
            if((v4 instanceof X509TrustManager)) {
                this.c = ((X509TrustManager)v4);
            }
            else {
                ++v3;
                continue;
            }

            break;
        }

        v0 = TrustManagerFactory.getInstance("X509");
        v0.init(this.d);
        v0_1 = v0.getTrustManagers();
        v1 = v0_1.length;
        while(v2 < v1) {
            TrustManager v3_1 = v0_1[v2];
            if((v3_1 instanceof X509TrustManager)) {
                this.e = ((X509TrustManager)v3_1);
            }
            else {
                ++v2;
                continue;
            }

            return;
        }
    }

    public void a(X509Certificate[] arg6) {
        __monitor_enter(this);
        try {
            int v0 = arg6.length;
            int v1;
            for(v1 = 0; v1 < v0; ++v1) {
                this.a.setCertificateEntry(arg6[v1].getSubjectDN().getName(), arg6[v1]);
            }

            this.a();
            FileOutputStream v6_1 = new FileOutputStream(this.b.a);
            this.a.store(((OutputStream)v6_1), this.b.b.toCharArray());
            v6_1.close();
        }
        catch(Throwable v6) {
            goto label_25;
        }

        __monitor_exit(this);
        return;
    label_25:
        __monitor_exit(this);
        throw v6;
    }

    public void checkClientTrusted(X509Certificate[] arg2, String arg3) {
        __monitor_enter(this);
        try {
            this.c.checkClientTrusted(arg2, arg3);
        }
        catch(Throwable v2) {
        }
        catch(CertificateException ) {
            try {
                this.e.checkClientTrusted(arg2, arg3);
            }
            catch(CertificateException ) {
                if((this.b.c) && !this.b.a.exists()) {
                    try {
                        this.a(arg2);
                        goto label_17;
                    }
                    catch(Exception v2_1) {
                        try {
                            throw new CertificateMemorizationException(((Throwable)v2_1));
                        label_23:
                            throw new CertificateNotMemorizedException(arg2);
                        }
                        catch(Throwable v2) {
                            __monitor_exit(this);
                            throw v2;
                        }
                    }
                }

                goto label_23;
            }
        }

    label_17:
        __monitor_exit(this);
    }

    public void checkServerTrusted(X509Certificate[] arg2, String arg3) {
        __monitor_enter(this);
        try {
            this.c.checkServerTrusted(arg2, arg3);
        }
        catch(Throwable v2) {
        }
        catch(CertificateException ) {
            try {
                this.e.checkServerTrusted(arg2, arg3);
            }
            catch(CertificateException ) {
                if((this.b.c) && !this.b.a.exists()) {
                    try {
                        this.a(arg2);
                        goto label_17;
                    }
                    catch(Exception v2_1) {
                        try {
                            throw new CertificateMemorizationException(((Throwable)v2_1));
                        label_23:
                            throw new CertificateNotMemorizedException(arg2);
                        }
                        catch(Throwable v2) {
                            __monitor_exit(this);
                            throw v2;
                        }
                    }
                }

                goto label_23;
            }
        }

    label_17:
        __monitor_exit(this);
    }

    public X509Certificate[] getAcceptedIssuers() {
        return new X509Certificate[0];
    }
}

