package okhttp3;

import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import okhttp3.internal.c.e;

public final class d {
    public final class a {
        boolean a;
        boolean b;
        int c;
        int d;
        int e;
        boolean f;
        boolean g;
        boolean h;

        public a() {
            super();
            this.c = -1;
            this.d = -1;
            this.e = -1;
        }

        public a a() {
            this.a = true;
            return this;
        }

        public a a(int arg4, TimeUnit arg5) {
            if(arg4 >= 0) {
                long v4 = arg5.toSeconds(((long)arg4));
                arg4 = v4 > 2147483647 ? 2147483647 : ((int)v4);
                this.d = arg4;
                return this;
            }

            StringBuilder v0 = new StringBuilder();
            v0.append("maxStale < 0: ");
            v0.append(arg4);
            throw new IllegalArgumentException(v0.toString());
        }

        public a b() {
            this.f = true;
            return this;
        }

        public d c() {
            return new d(this);
        }
    }

    public static final d a;
    public static final d b;
    @Nullable String c;
    private final boolean d;
    private final boolean e;
    private final int f;
    private final int g;
    private final boolean h;
    private final boolean i;
    private final boolean j;
    private final int k;
    private final int l;
    private final boolean m;
    private final boolean n;
    private final boolean o;

    static {
        d.a = new a().a().c();
        d.b = new a().b().a(2147483647, TimeUnit.SECONDS).c();
    }

    d(a arg2) {
        super();
        this.d = arg2.a;
        this.e = arg2.b;
        this.f = arg2.c;
        this.g = -1;
        this.h = false;
        this.i = false;
        this.j = false;
        this.k = arg2.d;
        this.l = arg2.e;
        this.m = arg2.f;
        this.n = arg2.g;
        this.o = arg2.h;
    }

    private d(boolean arg1, boolean arg2, int arg3, int arg4, boolean arg5, boolean arg6, boolean arg7, int arg8, int arg9, boolean arg10, boolean arg11, boolean arg12, @Nullable String arg13) {
        super();
        this.d = arg1;
        this.e = arg2;
        this.f = arg3;
        this.g = arg4;
        this.h = arg5;
        this.i = arg6;
        this.j = arg7;
        this.k = arg8;
        this.l = arg9;
        this.m = arg10;
        this.n = arg11;
        this.o = arg12;
        this.c = arg13;
    }

    public static d a(s arg23) {
        String v3_1;
        int v5;
        s v0 = arg23;
        int v1 = arg23.a();
        int v6 = 0;
        int v7 = 1;
        String v8 = null;
        boolean v10 = false;
        boolean v11 = false;
        int v12 = -1;
        int v13 = -1;
        boolean v14 = false;
        boolean v15 = false;
        boolean v16 = false;
        int v17 = -1;
        int v18 = -1;
        boolean v19 = false;
        boolean v20 = false;
        boolean v21 = false;
        while(v6 < v1) {
            String v9 = v0.a(v6);
            String v2 = v0.b(v6);
            if(v9.equalsIgnoreCase("Cache-Control")) {
                if(v8 == null) {
                    v8 = v2;
                    goto label_32;
                }
                else {
                    goto label_24;
                }
            }
            else if(v9.equalsIgnoreCase("Pragma")) {
            label_24:
                v7 = 0;
            label_32:
                int v4;
                for(v4 = 0; v4 < v2.length(); v4 = v5) {
                    int v9_1 = e.a(v2, v4, "=,;");
                    String v4_1 = v2.substring(v4, v9_1).trim();
                    if(v9_1 == v2.length() || v2.charAt(v9_1) == 44 || v2.charAt(v9_1) == 59) {
                        v5 = v9_1 + 1;
                        v3_1 = null;
                    }
                    else {
                        int v3 = e.a(v2, v9_1 + 1);
                        if(v3 < v2.length() && v2.charAt(v3) == 34) {
                            ++v3;
                            v5 = e.a(v2, v3, "\"");
                            v3_1 = v2.substring(v3, v5);
                            ++v5;
                            goto label_72;
                        }

                        v5 = e.a(v2, v3, ",;");
                        v3_1 = v2.substring(v3, v5).trim();
                    }

                label_72:
                    if("no-cache".equalsIgnoreCase(v4_1)) {
                        v10 = true;
                    }
                    else if("no-store".equalsIgnoreCase(v4_1)) {
                        v11 = true;
                    }
                    else if("max-age".equalsIgnoreCase(v4_1)) {
                        v12 = e.b(v3_1, -1);
                    }
                    else if("s-maxage".equalsIgnoreCase(v4_1)) {
                        v13 = e.b(v3_1, -1);
                    }
                    else if("private".equalsIgnoreCase(v4_1)) {
                        v14 = true;
                    }
                    else if("public".equalsIgnoreCase(v4_1)) {
                        v15 = true;
                    }
                    else if("must-revalidate".equalsIgnoreCase(v4_1)) {
                        v16 = true;
                    }
                    else if("max-stale".equalsIgnoreCase(v4_1)) {
                        v17 = e.b(v3_1, 2147483647);
                    }
                    else if("min-fresh".equalsIgnoreCase(v4_1)) {
                        v18 = e.b(v3_1, -1);
                    }
                    else if("only-if-cached".equalsIgnoreCase(v4_1)) {
                        v19 = true;
                    }
                    else if("no-transform".equalsIgnoreCase(v4_1)) {
                        v20 = true;
                    }
                    else if("immutable".equalsIgnoreCase(v4_1)) {
                        v21 = true;
                    }
                }
            }

            ++v6;
        }

        String v22 = v7 == 0 ? null : v8;
        return new d(v10, v11, v12, v13, v14, v15, v16, v17, v18, v19, v20, v21, v22);
    }

    public boolean a() {
        return this.d;
    }

    public boolean b() {
        return this.e;
    }

    public int c() {
        return this.f;
    }

    public boolean d() {
        return this.h;
    }

    public boolean e() {
        return this.i;
    }

    public boolean f() {
        return this.j;
    }

    public int g() {
        return this.k;
    }

    public int h() {
        return this.l;
    }

    public boolean i() {
        return this.m;
    }

    public boolean j() {
        return this.o;
    }

    private String k() {
        StringBuilder v0 = new StringBuilder();
        if(this.d) {
            v0.append("no-cache, ");
        }

        if(this.e) {
            v0.append("no-store, ");
        }

        int v2 = -1;
        if(this.f != v2) {
            v0.append("max-age=");
            v0.append(this.f);
            v0.append(", ");
        }

        if(this.g != v2) {
            v0.append("s-maxage=");
            v0.append(this.g);
            v0.append(", ");
        }

        if(this.h) {
            v0.append("private, ");
        }

        if(this.i) {
            v0.append("public, ");
        }

        if(this.j) {
            v0.append("must-revalidate, ");
        }

        if(this.k != v2) {
            v0.append("max-stale=");
            v0.append(this.k);
            v0.append(", ");
        }

        if(this.l != v2) {
            v0.append("min-fresh=");
            v0.append(this.l);
            v0.append(", ");
        }

        if(this.m) {
            v0.append("only-if-cached, ");
        }

        if(this.n) {
            v0.append("no-transform, ");
        }

        if(this.o) {
            v0.append("immutable, ");
        }

        if(v0.length() == 0) {
            return "";
        }

        v0.delete(v0.length() - 2, v0.length());
        return v0.toString();
    }

    public String toString() {
        String v0 = this.c;
        if(v0 != null) {
        }
        else {
            v0 = this.k();
            this.c = v0;
        }

        return v0;
    }
}

