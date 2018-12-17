package com.google.android.gms.common.net;

import java.security.cert.X509Certificate;
import javax.net.ssl.X509TrustManager;

final class zza implements X509TrustManager {
    zza() {
        super();
    }

    public final void checkClientTrusted(X509Certificate[] arg1, String arg2) {
    }

    public final void checkServerTrusted(X509Certificate[] arg1, String arg2) {
    }

    public final X509Certificate[] getAcceptedIssuers() {
        return null;
    }
}

