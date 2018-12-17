package okhttp3.internal.h;

import java.util.List;
import javax.net.ssl.X509TrustManager;
import okhttp3.internal.g.f;

public abstract class c {
    public c() {
        super();
    }

    public abstract List a(List arg1, String arg2);

    public static c a(X509TrustManager arg1) {
        return f.c().a(arg1);
    }
}

