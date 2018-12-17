package androidx.work;

import android.net.Network;
import android.net.Uri;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.Executor;

public final class u {
    public class a {
        public String[] a;
        public Uri[] b;
        public Network c;

        public a() {
            super();
        }
    }

    private UUID a;
    private e b;
    private Set c;
    private a d;
    private int e;
    private Executor f;
    private t g;

    public u(UUID arg1, e arg2, Collection arg3, a arg4, int arg5, Executor arg6, t arg7) {
        super();
        this.a = arg1;
        this.b = arg2;
        this.c = new HashSet(arg3);
        this.d = arg4;
        this.e = arg5;
        this.f = arg6;
        this.g = arg7;
    }

    public UUID a() {
        return this.a;
    }

    public e b() {
        return this.b;
    }

    public Set c() {
        return this.c;
    }

    public int d() {
        return this.e;
    }

    public a e() {
        return this.d;
    }

    public Executor f() {
        return this.f;
    }

    public t g() {
        return this.g;
    }
}

