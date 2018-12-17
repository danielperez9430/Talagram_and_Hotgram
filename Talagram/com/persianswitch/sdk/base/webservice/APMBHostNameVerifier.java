package com.persianswitch.sdk.base.webservice;

import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;

public class APMBHostNameVerifier implements HostnameVerifier {
    private HostnameVerifier a;

    public APMBHostNameVerifier(HostnameVerifier arg1) {
        super();
        this.a = arg1;
    }

    private List a(X509Certificate arg5, int arg6) {
        ArrayList v0 = new ArrayList();
        try {
            Collection v5 = arg5.getSubjectAlternativeNames();
            if(v5 == null) {
                return Collections.emptyList();
            }

            Iterator v5_1 = v5.iterator();
            while(v5_1.hasNext()) {
                Object v1 = v5_1.next();
                if(v1 == null) {
                    continue;
                }

                if(((List)v1).size() < 2) {
                    continue;
                }

                Object v2 = ((List)v1).get(0);
                if(v2 == null) {
                    continue;
                }

                if(((Integer)v2).intValue() != arg6) {
                    continue;
                }

                v1 = ((List)v1).get(1);
                if(v1 == null) {
                    continue;
                }

                ((List)v0).add(v1);
            }
        }
        catch(CertificateParsingException ) {
            goto label_27;
        }

        return ((List)v0);
    label_27:
        return Collections.emptyList();
    }

    public static boolean a(String arg1) {
        return Pattern.compile("^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$").matcher(((CharSequence)arg1)).find();
    }

    public boolean a(String arg2, X509Certificate arg3) {
        Iterator v3 = this.a(arg3, 7).iterator();
        do {
            if(!v3.hasNext()) {
                return 0;
            }
        }
        while(!arg2.equals(v3.next()));

        return 1;
    }

    public boolean verify(String arg3, SSLSession arg4) {
        boolean v3;
        try {
            arg3 = arg3.trim();
            v3 = !APMBHostNameVerifier.a(arg3) ? this.a.verify(arg3, arg4) : this.a(arg3, arg4.getPeerCertificates()[0]);
        }
        catch(SSLException ) {
            return 0;
        }

        return v3;
    }
}

