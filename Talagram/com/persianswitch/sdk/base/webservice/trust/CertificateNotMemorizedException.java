package com.persianswitch.sdk.base.webservice.trust;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public class CertificateNotMemorizedException extends CertificateException {
    X509Certificate[] a;

    public CertificateNotMemorizedException(X509Certificate[] arg2) {
        super("Certificate not found in keystore");
        this.a = null;
        this.a = arg2;
    }
}

