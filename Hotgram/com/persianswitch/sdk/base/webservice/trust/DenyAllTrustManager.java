package com.persianswitch.sdk.base.webservice.trust;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.X509TrustManager;

public class DenyAllTrustManager implements X509TrustManager {
    public DenyAllTrustManager() {
        super();
    }

    public void checkClientTrusted(X509Certificate[] arg1, String arg2) {
        throw new CertificateException();
    }

    public void checkServerTrusted(X509Certificate[] arg1, String arg2) {
        throw new CertificateException();
    }

    public X509Certificate[] getAcceptedIssuers() {
        return new X509Certificate[0];
    }
}

