package c.a.a.a.a.e;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Enumeration;
import java.util.HashMap;

class i {
    final KeyStore a;
    private final HashMap b;

    public i(InputStream arg1, String arg2) {
        super();
        KeyStore v1 = this.a(arg1, arg2);
        this.b = this.a(v1);
        this.a = v1;
    }

    public boolean a(X509Certificate arg3) {
        Object v0 = this.b.get(arg3.getSubjectX500Principal());
        boolean v3 = v0 == null || !((X509Certificate)v0).getPublicKey().equals(arg3.getPublicKey()) ? false : true;
        return v3;
    }

    private KeyStore a(InputStream arg3, String arg4) {
        BufferedInputStream v1;
        KeyStore v0;
        try {
            v0 = KeyStore.getInstance("BKS");
            v1 = new BufferedInputStream(arg3);
        }
        catch(NoSuchAlgorithmException v3) {
            goto label_22;
        }
        catch(KeyStoreException v3_1) {
            goto label_26;
        }
        catch(IOException v3_2) {
            goto label_14;
        }
        catch(CertificateException v3_3) {
            goto label_18;
        }

        try {
            v0.load(((InputStream)v1), arg4.toCharArray());
            goto label_6;
        }
        catch(Throwable v3_4) {
            try {
                v1.close();
                throw v3_4;
            label_6:
                v1.close();
                return v0;
            }
            catch(IOException v3_2) {
            label_14:
                throw new AssertionError(v3_2);
            }
            catch(CertificateException v3_3) {
            label_18:
                throw new AssertionError(v3_3);
            }
            catch(NoSuchAlgorithmException v3) {
            label_22:
                throw new AssertionError(v3);
            }
            catch(KeyStoreException v3_1) {
            label_26:
                throw new AssertionError(v3_1);
            }
        }
    }

    private HashMap a(KeyStore arg5) {
        HashMap v0;
        try {
            v0 = new HashMap();
            Enumeration v1 = arg5.aliases();
            while(v1.hasMoreElements()) {
                Certificate v2 = arg5.getCertificate(v1.nextElement());
                if(v2 == null) {
                    continue;
                }

                v0.put(((X509Certificate)v2).getSubjectX500Principal(), v2);
            }
        }
        catch(KeyStoreException v5) {
            goto label_15;
        }

        return v0;
    label_15:
        throw new AssertionError(v5);
    }

    public X509Certificate b(X509Certificate arg5) {
        Object v0 = this.b.get(arg5.getIssuerX500Principal());
        X509Certificate v1 = null;
        if(v0 == null) {
            return v1;
        }

        if(((X509Certificate)v0).getSubjectX500Principal().equals(arg5.getSubjectX500Principal())) {
            return v1;
        }

        try {
            arg5.verify(((X509Certificate)v0).getPublicKey());
            return ((X509Certificate)v0);
        }
        catch(GeneralSecurityException ) {
            return v1;
        }
    }
}

