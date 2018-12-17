package com.persianswitch.a.a.d;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.PublicKey;
import java.security.cert.TrustAnchor;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.net.ssl.X509TrustManager;
import javax.security.auth.x500.X500Principal;

public abstract class e {
    final class a extends e {
        private final X509TrustManager a;
        private final Method b;

        a(X509TrustManager arg1, Method arg2) {
            super();
            this.b = arg2;
            this.a = arg1;
        }

        public X509Certificate a(X509Certificate arg6) {
            X509Certificate v0 = null;
            try {
                Object v6 = this.b.invoke(this.a, arg6);
                if(v6 != null) {
                    arg6 = ((TrustAnchor)v6).getTrustedCert();
                }
                else {
                    return v0;
                }

                return arg6;
            }
            catch(InvocationTargetException ) {
                return v0;
            }
            catch(IllegalAccessException ) {
                throw new AssertionError();
            }

            return v0;
        }
    }

    final class b extends e {
        private final Map a;

        public b(X509Certificate[] arg7) {
            super();
            this.a = new LinkedHashMap();
            int v0 = arg7.length;
            int v1;
            for(v1 = 0; v1 < v0; ++v1) {
                X509Certificate v2 = arg7[v1];
                X500Principal v3 = v2.getSubjectX500Principal();
                Object v4 = this.a.get(v3);
                if(v4 == null) {
                    ArrayList v4_1 = new ArrayList(1);
                    this.a.put(v3, v4_1);
                }

                ((List)v4).add(v2);
            }
        }

        public X509Certificate a(X509Certificate arg5) {
            Object v0 = this.a.get(arg5.getIssuerX500Principal());
            X509Certificate v1 = null;
            if(v0 == null) {
                return v1;
            }

            Iterator v0_1 = ((List)v0).iterator();
        label_7:
            if(v0_1.hasNext()) {
                Object v2 = v0_1.next();
                PublicKey v3 = ((X509Certificate)v2).getPublicKey();
                try {
                    arg5.verify(v3);
                    return ((X509Certificate)v2);
                }
                catch(Exception ) {
                    goto label_7;
                }
            }

            return v1;
        }
    }

    public e() {
        super();
    }

    public static e a(X509TrustManager arg6) {
        try {
            Method v0 = arg6.getClass().getDeclaredMethod("findTrustAnchorByIssuerAndSignature", X509Certificate.class);
            v0.setAccessible(true);
            return new a(arg6, v0);
        }
        catch(NoSuchMethodException ) {
            return e.a(arg6.getAcceptedIssuers());
        }
    }

    public static e a(X509Certificate[] arg1) {
        return new b(arg1);
    }

    public abstract X509Certificate a(X509Certificate arg1);
}

