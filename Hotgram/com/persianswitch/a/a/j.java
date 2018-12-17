package com.persianswitch.a.a;

import com.persianswitch.a.a.d.a;
import com.persianswitch.a.a.d.b;
import com.persianswitch.a.a.d.e;
import com.persianswitch.a.u;
import com.persianswitch.a.v;
import com.persianswitch.b.c;
import java.io.IOException;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.X509TrustManager;

public class j {
    private static final j a;
    private static final Logger b;

    static {
        j.a = j.b();
        j.b = Logger.getLogger(u.class.getName());
    }

    public j() {
        super();
    }

    public void a(int arg2, String arg3, Throwable arg4) {
        Level v2 = arg2 == 5 ? Level.WARNING : Level.INFO;
        j.b.log(v2, arg3, arg4);
    }

    public b a(X509TrustManager arg2) {
        return new a(e.a(arg2));
    }

    public static List a(List arg5) {
        ArrayList v0 = new ArrayList(arg5.size());
        int v1 = arg5.size();
        int v2;
        for(v2 = 0; v2 < v1; ++v2) {
            Object v3 = arg5.get(v2);
            if(v3 == v.a) {
            }
            else {
                ((List)v0).add(((v)v3).toString());
            }
        }

        return ((List)v0);
    }

    public String a(SSLSocket arg1) {
        return null;
    }

    public void a(Socket arg1, InetSocketAddress arg2, int arg3) {
        try {
            arg1.connect(((SocketAddress)arg2), arg3);
            return;
        }
        catch(IOException v1) {
            new ConnectException(v1.getMessage()).initCause(((Throwable)v1));
            throw v1;
        }
    }

    public void a(SSLSocket arg1, String arg2, List arg3) {
    }

    public boolean a() {
        return 1;
    }

    private static j b() {
        j v0 = com.persianswitch.a.a.a.b();
        if(v0 != null) {
            return v0;
        }

        f v0_1 = f.b();
        if(v0_1 != null) {
            return ((j)v0_1);
        }

        v0 = g.b();
        if(v0 != null) {
            return v0;
        }

        return new j();
    }

    static byte[] b(List arg5) {
        c v0 = new c();
        int v1 = arg5.size();
        int v2;
        for(v2 = 0; v2 < v1; ++v2) {
            Object v3 = arg5.get(v2);
            if(v3 == v.a) {
            }
            else {
                v0.b(((v)v3).toString().length());
                v0.a(((v)v3).toString());
            }
        }

        return v0.q();
    }

    public void b(SSLSocket arg1) {
    }

    public static j c() {
        return j.a;
    }
}

