package c.a.a.a.a.e;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

public final class f {
    public static final SSLSocketFactory a(g arg4) {
        SSLContext v0 = SSLContext.getInstance("TLS");
        v0.init(null, new TrustManager[]{new h(new i(arg4.a(), arg4.b()), arg4)}, null);
        return v0.getSocketFactory();
    }
}

