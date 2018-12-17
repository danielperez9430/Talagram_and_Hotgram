package okhttp3;

import e.d;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;
import okhttp3.internal.c;

public final class q extends ab {
    public final class a {
        private final List a;
        private final List b;
        private final Charset c;

        public a() {
            this(null);
        }

        public a(Charset arg2) {
            super();
            this.a = new ArrayList();
            this.b = new ArrayList();
            this.c = arg2;
        }

        public q a() {
            return new q(this.a, this.b);
        }

        public a a(String arg9, String arg10) {
            if(arg9 != null) {
                if(arg10 != null) {
                    this.a.add(t.a(arg9, " \"\':;<=>@[]^`{}|/\\?#&!$(),~", false, false, true, true, this.c));
                    this.b.add(t.a(arg10, " \"\':;<=>@[]^`{}|/\\?#&!$(),~", false, false, true, true, this.c));
                    return this;
                }

                throw new NullPointerException("value == null");
            }

            throw new NullPointerException("name == null");
        }

        public a b(String arg9, String arg10) {
            if(arg9 != null) {
                if(arg10 != null) {
                    this.a.add(t.a(arg9, " \"\':;<=>@[]^`{}|/\\?#&!$(),~", true, false, true, true, this.c));
                    this.b.add(t.a(arg10, " \"\':;<=>@[]^`{}|/\\?#&!$(),~", true, false, true, true, this.c));
                    return this;
                }

                throw new NullPointerException("value == null");
            }

            throw new NullPointerException("name == null");
        }
    }

    private static final v a;
    private final List b;
    private final List c;

    static {
        q.a = v.a("application/x-www-form-urlencoded");
    }

    q(List arg1, List arg2) {
        super();
        this.b = c.a(arg1);
        this.c = c.a(arg2);
    }

    private long a(@Nullable d arg4, boolean arg5) {
        long v0_1;
        e.c v4 = arg5 ? new e.c() : arg4.c();
        int v0 = 0;
        int v1 = this.b.size();
        while(v0 < v1) {
            if(v0 > 0) {
                v4.b(38);
            }

            v4.a(this.b.get(v0));
            v4.b(61);
            v4.a(this.c.get(v0));
            ++v0;
        }

        if(arg5) {
            v0_1 = v4.b();
            v4.s();
        }
        else {
            v0_1 = 0;
        }

        return v0_1;
    }

    public v a() {
        return q.a;
    }

    public void a(d arg2) {
        this.a(arg2, false);
    }

    public long b() {
        return this.a(null, true);
    }
}

