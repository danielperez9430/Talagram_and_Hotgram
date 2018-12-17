package okhttp3;

import java.net.InetSocketAddress;
import java.net.Proxy$Type;
import java.net.Proxy;
import javax.annotation.Nullable;

public final class ae {
    final a a;
    final Proxy b;
    final InetSocketAddress c;

    public ae(a arg1, Proxy arg2, InetSocketAddress arg3) {
        super();
        if(arg1 != null) {
            if(arg2 != null) {
                if(arg3 != null) {
                    this.a = arg1;
                    this.b = arg2;
                    this.c = arg3;
                    return;
                }

                throw new NullPointerException("inetSocketAddress == null");
            }

            throw new NullPointerException("proxy == null");
        }

        throw new NullPointerException("address == null");
    }

    public a a() {
        return this.a;
    }

    public Proxy b() {
        return this.b;
    }

    public InetSocketAddress c() {
        return this.c;
    }

    public boolean d() {
        boolean v0 = this.a.i == null || this.b.type() != Proxy$Type.HTTP ? false : true;
        return v0;
    }

    public boolean equals(@Nullable Object arg3) {
        boolean v3 = !(arg3 instanceof ae) || !((ae)arg3).a.equals(this.a) || !((ae)arg3).b.equals(this.b) || !((ae)arg3).c.equals(this.c) ? false : true;
        return v3;
    }

    public int hashCode() {
        return ((527 + this.a.hashCode()) * 31 + this.b.hashCode()) * 31 + this.c.hashCode();
    }

    public String toString() {
        return "Route{" + this.c + "}";
    }
}

