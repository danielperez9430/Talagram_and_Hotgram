package okhttp3;

import e.d;
import e.f;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.annotation.Nullable;
import okhttp3.internal.c;

public final class w extends ab {
    public final class a {
        private final f a;
        private v b;
        private final List c;

        public a() {
            this(UUID.randomUUID().toString());
        }

        public a(String arg2) {
            super();
            this.b = w.a;
            this.c = new ArrayList();
            this.a = f.a(arg2);
        }

        public a a(v arg4) {
            if(arg4 != null) {
                if(arg4.a().equals("multipart")) {
                    this.b = arg4;
                    return this;
                }

                StringBuilder v1 = new StringBuilder();
                v1.append("multipart != ");
                v1.append(arg4);
                throw new IllegalArgumentException(v1.toString());
            }

            throw new NullPointerException("type == null");
        }

        public w a() {
            if(!this.c.isEmpty()) {
                return new w(this.a, this.b, this.c);
            }

            throw new IllegalStateException("Multipart body must have at least one part.");
        }

        public a a(@Nullable s arg1, ab arg2) {
            return this.a(b.a(arg1, arg2));
        }

        public a a(b arg2) {
            if(arg2 != null) {
                this.c.add(arg2);
                return this;
            }

            throw new NullPointerException("part == null");
        }
    }

    public final class b {
        @Nullable final s a;
        final ab b;

        private b(@Nullable s arg1, ab arg2) {
            super();
            this.a = arg1;
            this.b = arg2;
        }

        public static b a(@Nullable s arg1, ab arg2) {
            if(arg2 != null) {
                if(arg1 != null) {
                    if(arg1.a("Content-Type") == null) {
                    }
                    else {
                        throw new IllegalArgumentException("Unexpected header: Content-Type");
                    }
                }

                if(arg1 != null) {
                    if(arg1.a("Content-Length") == null) {
                    }
                    else {
                        throw new IllegalArgumentException("Unexpected header: Content-Length");
                    }
                }

                return new b(arg1, arg2);
            }

            throw new NullPointerException("body == null");
        }
    }

    public static final v a;
    public static final v b;
    public static final v c;
    public static final v d;
    public static final v e;
    private static final byte[] f;
    private static final byte[] g;
    private static final byte[] h;
    private final f i;
    private final v j;
    private final v k;
    private final List l;
    private long m;

    static {
        w.a = v.a("multipart/mixed");
        w.b = v.a("multipart/alternative");
        w.c = v.a("multipart/digest");
        w.d = v.a("multipart/parallel");
        w.e = v.a("multipart/form-data");
        w.f = new byte[]{58, 32};
        w.g = new byte[]{13, 10};
        w.h = new byte[]{45, 45};
    }

    w(f arg3, v arg4, List arg5) {
        super();
        this.m = -1;
        this.i = arg3;
        this.j = arg4;
        StringBuilder v0 = new StringBuilder();
        v0.append(arg4);
        v0.append("; boundary=");
        v0.append(arg3.a());
        this.k = v.a(v0.toString());
        this.l = c.a(arg5);
    }

    private long a(@Nullable d arg13, boolean arg14) {
        e.c v0;
        e.c v13;
        if(arg14) {
            v13 = new e.c();
            v0 = v13;
        }
        else {
            v0 = null;
        }

        int v1 = this.l.size();
        long v4 = 0;
        int v3;
        for(v3 = 0; v3 < v1; ++v3) {
            Object v6 = this.l.get(v3);
            s v7 = ((b)v6).a;
            ab v6_1 = ((b)v6).b;
            ((d)v13).c(w.h);
            ((d)v13).b(this.i);
            ((d)v13).c(w.g);
            if(v7 != null) {
                int v8 = v7.a();
                int v9;
                for(v9 = 0; v9 < v8; ++v9) {
                    ((d)v13).b(v7.a(v9)).c(w.f).b(v7.b(v9)).c(w.g);
                }
            }

            v v7_1 = v6_1.a();
            if(v7_1 != null) {
                ((d)v13).b("Content-Type: ").b(v7_1.toString()).c(w.g);
            }

            long v7_2 = v6_1.b();
            long v9_1 = -1;
            if(v7_2 != v9_1) {
                ((d)v13).b("Content-Length: ").l(v7_2).c(w.g);
            }
            else if(arg14) {
                v0.s();
                return v9_1;
            }

            ((d)v13).c(w.g);
            if(arg14) {
                v4 += v7_2;
            }
            else {
                v6_1.a(((d)v13));
            }

            ((d)v13).c(w.g);
        }

        ((d)v13).c(w.h);
        ((d)v13).b(this.i);
        ((d)v13).c(w.h);
        ((d)v13).c(w.g);
        if(arg14) {
            v4 += v0.b();
            v0.s();
        }

        return v4;
    }

    public v a() {
        return this.k;
    }

    public void a(d arg2) {
        this.a(arg2, false);
    }

    public long b() {
        long v0 = this.m;
        if(v0 != -1) {
            return v0;
        }

        v0 = this.a(null, true);
        this.m = v0;
        return v0;
    }
}

