package c.a.a.a.a.e;

import java.security.GeneralSecurityException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.LinkedList;

final class a {
    private static boolean a(X509Certificate arg2, X509Certificate arg3) {
        if(!arg2.getSubjectX500Principal().equals(arg3.getIssuerX500Principal())) {
            return 0;
        }

        try {
            arg3.verify(arg2.getPublicKey());
            return 1;
        }
        catch(GeneralSecurityException ) {
            return 0;
        }
    }

    public static X509Certificate[] a(X509Certificate[] arg6, i arg7) {
        LinkedList v0 = new LinkedList();
        boolean v2 = arg7.a(arg6[0]);
        v0.add(arg6[0]);
        boolean v1 = true;
        boolean v3 = v2;
        int v2_1;
        for(v2_1 = 1; v2_1 < arg6.length; ++v2_1) {
            if(arg7.a(arg6[v2_1])) {
                v3 = true;
            }

            if(!a.a(arg6[v2_1], arg6[v2_1 - 1])) {
                break;
            }

            v0.add(arg6[v2_1]);
        }

        X509Certificate v6 = arg7.b(arg6[v2_1 - 1]);
        if(v6 != null) {
            v0.add(v6);
        }
        else {
            v1 = v3;
        }

        if(v1) {
            return v0.toArray(new X509Certificate[v0.size()]);
        }

        throw new CertificateException("Didn\'t find a trust anchor in chain cleanup!");
    }
}

