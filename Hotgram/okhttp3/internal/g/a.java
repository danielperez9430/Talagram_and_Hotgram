package okhttp3.internal.g;

import android.os.Build$VERSION;
import android.util.Log;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.security.Security;
import java.security.cert.TrustAnchor;
import java.security.cert.X509Certificate;
import java.util.List;
import javax.annotation.Nullable;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.X509TrustManager;
import okhttp3.internal.h.c;
import okhttp3.internal.h.e;

class a extends f {
    final class okhttp3.internal.g.a$a extends c {
        private final Object a;
        private final Method b;

        okhttp3.internal.g.a$a(Object arg1, Method arg2) {
            super();
            this.a = arg1;
            this.b = arg2;
        }

        public List a(List arg5, String arg6) {
            try {
                return this.b.invoke(this.a, arg5.toArray(new X509Certificate[arg5.size()]), "RSA", arg6);
            }
            catch(IllegalAccessException v5) {
                throw new AssertionError(v5);
            }
            catch(InvocationTargetException v5_1) {
                SSLPeerUnverifiedException v6 = new SSLPeerUnverifiedException(v5_1.getMessage());
                v6.initCause(((Throwable)v5_1));
                throw v6;
            }
        }

        public boolean equals(Object arg1) {
            return arg1 instanceof okhttp3.internal.g.a$a;
        }

        public int hashCode() {
            return 0;
        }
    }

    final class b implements e {
        private final X509TrustManager a;
        private final Method b;

        b(X509TrustManager arg1, Method arg2) {
            super();
            this.b = arg2;
            this.a = arg1;
        }

        public X509Certificate a(X509Certificate arg6) {
            X509Certificate v0 = null;
            try {
                Object v6_1 = this.b.invoke(this.a, arg6);
                if(v6_1 != null) {
                    arg6 = ((TrustAnchor)v6_1).getTrustedCert();
                }
                else {
                    return v0;
                }

                return arg6;
            }
            catch(InvocationTargetException ) {
                return v0;
            }
            catch(IllegalAccessException v6) {
                throw okhttp3.internal.c.a("unable to get issues and signature", ((Exception)v6));
            }

            return v0;
        }

        public boolean equals(Object arg5) {
            boolean v0 = true;
            if((((b)arg5)) == this) {
                return 1;
            }

            if(!(arg5 instanceof b)) {
                return 0;
            }

            if(!this.a.equals(((b)arg5).a) || !this.b.equals(((b)arg5).b)) {
                v0 = false;
            }
            else {
            }

            return v0;
        }

        public int hashCode() {
            return this.a.hashCode() + this.b.hashCode() * 31;
        }
    }

    final class okhttp3.internal.g.a$c {
        private final Method a;
        private final Method b;
        private final Method c;

        okhttp3.internal.g.a$c(Method arg1, Method arg2, Method arg3) {
            super();
            this.a = arg1;
            this.b = arg2;
            this.c = arg3;
        }

        static okhttp3.internal.g.a$c a() {
            Method v0;
            Method v1_1;
            Method v4;
            try {
                Class v1 = Class.forName("dalvik.system.CloseGuard");
                Method v2 = v1.getMethod("get");
                v4 = v1.getMethod("open", String.class);
                v1_1 = v1.getMethod("warnIfOpen");
                v0 = v2;
            }
            catch(Exception ) {
                v1_1 = v0;
                v4 = v1_1;
            }

            return new okhttp3.internal.g.a$c(v0, v4, v1_1);
        }

        Object a(String arg6) {
            Object v1 = null;
            if(this.a == null) {
                return v1;
            }

            try {
                Object v0 = this.a.invoke(v1);
                this.b.invoke(v0, arg6);
                return v0;
            }
            catch(Exception ) {
                return v1;
            }
        }

        boolean a(Object arg4) {
            boolean v0 = false;
            if(arg4 != null) {
                try {
                    this.c.invoke(arg4);
                    return true;
                }
                catch(Exception ) {
                    return v0;
                }
            }

            return v0;
        }
    }

    private final Class a;
    private final okhttp3.internal.g.e b;
    private final okhttp3.internal.g.e c;
    private final okhttp3.internal.g.e d;
    private final okhttp3.internal.g.e e;
    private final okhttp3.internal.g.a$c f;

    a(Class arg2, okhttp3.internal.g.e arg3, okhttp3.internal.g.e arg4, okhttp3.internal.g.e arg5, okhttp3.internal.g.e arg6) {
        super();
        this.f = okhttp3.internal.g.a$c.a();
        this.a = arg2;
        this.b = arg3;
        this.c = arg4;
        this.d = arg5;
        this.e = arg6;
    }

    public static f a() {
        a v2;
        okhttp3.internal.g.e v6;
        okhttp3.internal.g.e v7;
        Class v1;
        Class v0 = null;
        try {
            v1 = Class.forName("com.android.org.conscrypt.SSLParametersImpl");
        }
        catch(ClassNotFoundException ) {
            try {
                v1 = Class.forName("org.apache.harmony.xnet.provider.jsse.SSLParametersImpl");
            }
            catch(ClassNotFoundException ) {
                goto label_45;
            }
        }

        Class v3 = v1;
        try {
            okhttp3.internal.g.e v4 = new okhttp3.internal.g.e(v0, "setUseSessionTickets", new Class[]{Boolean.TYPE});
            okhttp3.internal.g.e v5 = new okhttp3.internal.g.e(v0, "setHostname", new Class[]{String.class});
            if(a.b()) {
                okhttp3.internal.g.e v1_1 = new okhttp3.internal.g.e(byte[].class, "getAlpnSelectedProtocol", new Class[0]);
                v7 = new okhttp3.internal.g.e(v0, "setAlpnProtocols", new Class[]{byte[].class});
                v6 = v1_1;
            }
            else {
                v6 = ((okhttp3.internal.g.e)v0);
                v7 = v6;
            }

            v2 = new a(v3, v4, v5, v6, v7);
        }
        catch(ClassNotFoundException ) {
        label_45:
            return ((f)v0);
        }

        return v2;
    }

    private boolean a(String arg6, Class arg7, Object arg8) {
        try {
            return arg7.getMethod("isCleartextTrafficPermitted", String.class).invoke(arg8, arg6).booleanValue();
        }
        catch(NoSuchMethodException ) {
            return this.b(arg6, arg7, arg8);
        }
    }

    public Object a(String arg2) {
        return this.f.a(arg2);
    }

    @Nullable public String a(SSLSocket arg4) {
        String v1 = null;
        if(this.d == null) {
            return v1;
        }

        if(!this.d.a(arg4)) {
            return v1;
        }

        Object v4 = this.d.d(arg4, new Object[0]);
        if(v4 != null) {
            v1 = new String(((byte[])v4), okhttp3.internal.c.e);
        }

        return v1;
    }

    public c a(X509TrustManager arg8) {
        try {
            Class v0 = Class.forName("android.net.http.X509TrustManagerExtensions");
            return new okhttp3.internal.g.a$a(v0.getConstructor(X509TrustManager.class).newInstance(arg8), v0.getMethod("checkServerTrusted", X509Certificate[].class, String.class, String.class));
        }
        catch(Exception ) {
            return super.a(arg8);
        }
    }

    public void a(int arg6, String arg7, Throwable arg8) {
        int v3;
        int v0 = 5;
        if(arg6 == v0) {
        }
        else {
            v0 = 3;
        }

        char v6 = '\n';
        if(arg8 != null) {
            arg7 = arg7 + v6 + Log.getStackTraceString(arg8);
        }

        int v8 = 0;
        int v1_1 = arg7.length();
        while(v8 < v1_1) {
            int v2 = arg7.indexOf(v6, v8);
            if(v2 != -1) {
            }
            else {
                v2 = v1_1;
            }

            while(true) {
                v3 = Math.min(v2, v8 + 4000);
                Log.println(v0, "OkHttp", arg7.substring(v8, v3));
                if(v3 >= v2) {
                    break;
                }

                v8 = v3;
            }

            v8 = v3 + 1;
        }
    }

    public void a(String arg2, Object arg3) {
        if(!this.f.a(arg3)) {
            this.a(5, arg2, null);
        }
    }

    public void a(Socket arg1, InetSocketAddress arg2, int arg3) {
        IOException v2;
        try {
            arg1.connect(((SocketAddress)arg2), arg3);
            return;
        }
        catch(ClassCastException v1) {
            if(Build$VERSION.SDK_INT == 26) {
                v2 = new IOException("Exception in connect");
                v2.initCause(((Throwable)v1));
                throw v2;
            }

            throw v1;
        }
        catch(SecurityException v1_1) {
            v2 = new IOException("Exception in connect");
            v2.initCause(((Throwable)v1_1));
            throw v2;
        }
        catch(AssertionError v1_2) {
            if(okhttp3.internal.c.a(v1_2)) {
                throw new IOException(((Throwable)v1_2));
            }

            throw v1_2;
        }
    }

    public void a(SSLSocket arg6, String arg7, List arg8) {
        if(arg7 != null) {
            this.b.b(arg6, new Object[]{Boolean.valueOf(true)});
            this.c.b(arg6, new Object[]{arg7});
        }

        if(this.e != null && (this.e.a(arg6))) {
            this.e.d(arg6, new Object[]{a.b(arg8)});
        }
    }

    private static boolean b() {
        if(Security.getProvider("GMSCore_OpenSSL") != null) {
            return 1;
        }

        try {
            Class.forName("android.net.Network");
            return 1;
        }
        catch(ClassNotFoundException ) {
            return 0;
        }
    }

    private boolean b(String arg4, Class arg5, Object arg6) {
        try {
            return arg5.getMethod("isCleartextTrafficPermitted").invoke(arg6).booleanValue();
        }
        catch(NoSuchMethodException ) {
            return super.b(arg4);
        }
    }

    public e b(X509TrustManager arg7) {
        try {
            Method v0 = arg7.getClass().getDeclaredMethod("findTrustAnchorByIssuerAndSignature", X509Certificate.class);
            v0.setAccessible(true);
            return new b(arg7, v0);
        }
        catch(NoSuchMethodException ) {
            return super.b(arg7);
        }
    }

    public boolean b(String arg5) {
        try {
            Class v0 = Class.forName("android.security.NetworkSecurityPolicy");
            return this.a(arg5, v0, v0.getMethod("getInstance").invoke(null));
        }
        catch(InvocationTargetException v5) {
            throw okhttp3.internal.c.a("unable to determine cleartext support", ((Exception)v5));
        }
        catch(NoSuchMethodException ) {
            return super.b(arg5);
        }
    }
}

