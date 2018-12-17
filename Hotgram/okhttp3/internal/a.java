package okhttp3.internal;

import java.net.Socket;
import javax.net.ssl.SSLSocket;
import okhttp3.ae;
import okhttp3.internal.b.c;
import okhttp3.internal.b.d;
import okhttp3.internal.b.g;
import okhttp3.j;
import okhttp3.k;

public abstract class a {
    public static a a;

    public a() {
        super();
    }

    public abstract void a(okhttp3.s$a arg1, String arg2, String arg3);

    public abstract boolean a(okhttp3.a arg1, okhttp3.a arg2);

    public abstract int a(okhttp3.ac$a arg1);

    public abstract Socket a(j arg1, okhttp3.a arg2, g arg3);

    public abstract c a(j arg1, okhttp3.a arg2, g arg3, ae arg4);

    public abstract d a(j arg1);

    public abstract void a(k arg1, SSLSocket arg2, boolean arg3);

    public abstract void a(okhttp3.s$a arg1, String arg2);

    public abstract boolean a(j arg1, c arg2);

    public abstract void b(j arg1, c arg2);
}

