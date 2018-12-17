package okhttp3.internal.g;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nullable;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.X509TrustManager;
import okhttp3.internal.h.a;
import okhttp3.internal.h.c;
import okhttp3.internal.h.e;
import okhttp3.x;
import okhttp3.y;

public class f {
    private static final f a;
    private static final Logger b;

    static {
        f.a = f.b();
        f.b = Logger.getLogger(x.class.getName());
    }

    public f() {
        super();
    }

    public static List a(List arg5) {
        ArrayList v0 = new ArrayList(arg5.size());
        int v1 = arg5.size();
        int v2;
        for(v2 = 0; v2 < v1; ++v2) {
            Object v3 = arg5.get(v2);
            if(v3 == y.a) {
            }
            else {
                ((List)v0).add(((y)v3).toString());
            }
        }

        return ((List)v0);
    }

    public Object a(String arg3) {
        if(f.b.isLoggable(Level.FINE)) {
            return new Throwable(arg3);
        }

        return null;
    }

    @Nullable public String a(SSLSocket arg1) {
        return null;
    }

    public c a(X509TrustManager arg2) {
        return new a(this.b(arg2));
    }

    public void a(int arg2, String arg3, Throwable arg4) {
        Level v2 = arg2 == 5 ? Level.WARNING : Level.INFO;
        f.b.log(v2, arg3, arg4);
    }

    public void a(String arg2, Object arg3) {
        if(arg3 == null) {
            arg2 = arg2 + " To see where this was allocated, set the OkHttpClient logger level to FINE: Logger.getLogger(OkHttpClient.class.getName()).setLevel(Level.FINE);";
        }

        this.a(5, arg2, ((Throwable)arg3));
    }

    public void a(Socket arg1, InetSocketAddress arg2, int arg3) {
        arg1.connect(((SocketAddress)arg2), arg3);
    }

    public void a(SSLSocket arg1, String arg2, List arg3) {
    }

    private static f b() {
        f v0 = okhttp3.internal.g.a.a();
        if(v0 != null) {
            return v0;
        }

        if(f.d()) {
            v0 = b.b();
            if(v0 != null) {
                return v0;
            }
        }

        okhttp3.internal.g.c v0_1 = okhttp3.internal.g.c.b();
        if(v0_1 != null) {
            return ((f)v0_1);
        }

        v0 = d.b();
        if(v0 != null) {
            return v0;
        }

        return new f();
    }

    static byte[] b(List arg5) {
        e.c v0 = new e.c();
        int v1 = arg5.size();
        int v2;
        for(v2 = 0; v2 < v1; ++v2) {
            Object v3 = arg5.get(v2);
            if(v3 == y.a) {
            }
            else {
                v0.b(((y)v3).toString().length());
                v0.a(((y)v3).toString());
            }
        }

        return v0.r();
    }

    public e b(X509TrustManager arg2) {
        return new okhttp3.internal.h.b(arg2.getAcceptedIssuers());
    }

    public void b(SSLSocket arg1) {
    }

    public boolean b(String arg1) {
        return 1;
    }

    public static f c() {
        return f.a;
    }

    public static boolean d() {
        if("conscrypt".equals(System.getProperty("okhttp.platform"))) {
            return 1;
        }

        return "Conscrypt".equals(Security.getProviders()[0].getName());
    }

    public SSLContext n_() {
        try {
            return SSLContext.getInstance("TLS");
        }
        catch(NoSuchAlgorithmException v0) {
            throw new IllegalStateException("No TLS provider", ((Throwable)v0));
        }
    }
}

