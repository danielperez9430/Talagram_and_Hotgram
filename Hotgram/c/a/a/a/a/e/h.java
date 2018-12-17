package c.a.a.a.a.e;

import c.a.a.a.c;
import c.a.a.a.l;
import java.security.KeyStoreException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

class h implements X509TrustManager {
    private static final X509Certificate[] a;
    private final TrustManager[] b;
    private final i c;
    private final long d;
    private final List e;
    private final Set f;

    static {
        h.a = new X509Certificate[0];
    }

    public h(i arg4, g arg5) {
        super();
        this.e = new LinkedList();
        this.f = Collections.synchronizedSet(new HashSet());
        this.b = this.a(arg4);
        this.c = arg4;
        this.d = arg5.d();
        String[] v4 = arg5.c();
        int v5 = v4.length;
        int v0;
        for(v0 = 0; v0 < v5; ++v0) {
            this.e.add(this.a(v4[v0]));
        }
    }

    private TrustManager[] a(i arg2) {
        try {
            TrustManagerFactory v0 = TrustManagerFactory.getInstance("X509");
            v0.init(arg2.a);
            return v0.getTrustManagers();
        }
        catch(KeyStoreException v2) {
            throw new AssertionError(v2);
        }
        catch(NoSuchAlgorithmException v2_1) {
            throw new AssertionError(v2_1);
        }
    }

    private byte[] a(String arg8) {
        int v0 = arg8.length();
        byte[] v1 = new byte[v0 / 2];
        int v2;
        for(v2 = 0; v2 < v0; v2 += 2) {
            v1[v2 / 2] = ((byte)((Character.digit(arg8.charAt(v2), 16) << 4) + Character.digit(arg8.charAt(v2 + 1), 16)));
        }

        return v1;
    }

    private void a(X509Certificate[] arg9) {
        if(this.d != -1) {
            long v2 = 15552000000L;
            if(System.currentTimeMillis() - this.d > v2) {
                l v9 = c.h();
                v9.d("Fabric", "Certificate pins are stale, (" + (System.currentTimeMillis() - this.d) + " millis vs " + v2 + " millis) falling back to system trust.");
                return;
            }
        }

        arg9 = a.a(arg9, this.c);
        int v0 = arg9.length;
        int v1_1;
        for(v1_1 = 0; v1_1 < v0; ++v1_1) {
            if(this.a(arg9[v1_1])) {
                return;
            }
        }

        throw new CertificateException("No valid pins found in chain!");
    }

    private boolean a(X509Certificate arg3) {
        try {
            byte[] v3_1 = MessageDigest.getInstance("SHA1").digest(arg3.getPublicKey().getEncoded());
            Iterator v0 = this.e.iterator();
            do {
                if(!v0.hasNext()) {
                    return 0;
                }

                if(!Arrays.equals(v0.next(), v3_1)) {
                    continue;
                }

                return 1;
            }
            while(true);
        }
        catch(NoSuchAlgorithmException v3) {
            goto label_19;
        }

        return 1;
    label_19:
        throw new CertificateException(((Throwable)v3));
    }

    private void a(X509Certificate[] arg5, String arg6) {
        TrustManager[] v0 = this.b;
        int v1 = v0.length;
        int v2;
        for(v2 = 0; v2 < v1; ++v2) {
            v0[v2].checkServerTrusted(arg5, arg6);
        }
    }

    public void checkClientTrusted(X509Certificate[] arg1, String arg2) {
        throw new CertificateException("Client certificates not supported!");
    }

    public void checkServerTrusted(X509Certificate[] arg4, String arg5) {
        if(this.f.contains(arg4[0])) {
            return;
        }

        this.a(arg4, arg5);
        this.a(arg4);
        this.f.add(arg4[0]);
    }

    public X509Certificate[] getAcceptedIssuers() {
        return h.a;
    }
}

