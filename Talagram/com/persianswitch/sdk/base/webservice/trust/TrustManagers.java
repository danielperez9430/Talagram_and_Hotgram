package com.persianswitch.sdk.base.webservice.trust;

import java.io.InputStream;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;

public class TrustManagers {
    public TrustManagers() {
        super();
    }

    public static TrustManager[] a(InputStream arg1, String arg2) {
        Certificate v2_2;
        CertificateFactory v2 = CertificateFactory.getInstance(arg2);
        try {
            v2_2 = v2.generateCertificate(arg1);
        }
        catch(Throwable v2_1) {
            arg1.close();
            throw v2_1;
        }

        arg1.close();
        KeyStore v1 = KeyStore.getInstance(KeyStore.getDefaultType());
        v1.load(null, null);
        v1.setCertificateEntry("ca", v2_2);
        TrustManagerFactory v2_3 = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        v2_3.init(v1);
        return v2_3.getTrustManagers();
    }
}

