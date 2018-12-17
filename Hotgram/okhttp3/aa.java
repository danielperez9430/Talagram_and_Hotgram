package okhttp3;

import javax.annotation.Nullable;
import okhttp3.internal.c.f;

public final class aa {
    public class a {
        t a;
        String b;
        okhttp3.s$a c;
        ab d;
        Object e;

        public a() {
            super();
            this.b = "GET";
            this.c = new okhttp3.s$a();
        }

        a(aa arg2) {
            super();
            this.a = arg2.a;
            this.b = arg2.b;
            this.d = arg2.d;
            this.e = arg2.e;
            this.c = arg2.c.b();
        }

        public a a(s arg1) {
            this.c = arg1.b();
            return this;
        }

        public a a(String arg3, @Nullable ab arg4) {
            StringBuilder v0;
            if(arg3 != null) {
                if(arg3.length() != 0) {
                    if(arg4 != null) {
                        if(f.c(arg3)) {
                        }
                        else {
                            v0 = new StringBuilder();
                            v0.append("method ");
                            v0.append(arg3);
                            v0.append(" must not have a request body.");
                            throw new IllegalArgumentException(v0.toString());
                        }
                    }

                    if(arg4 == null) {
                        if(!f.b(arg3)) {
                        }
                        else {
                            v0 = new StringBuilder();
                            v0.append("method ");
                            v0.append(arg3);
                            v0.append(" must have a request body.");
                            throw new IllegalArgumentException(v0.toString());
                        }
                    }

                    this.b = arg3;
                    this.d = arg4;
                    return this;
                }

                throw new IllegalArgumentException("method.length() == 0");
            }

            throw new NullPointerException("method == null");
        }

        public a a(t arg2) {
            if(arg2 != null) {
                this.a = arg2;
                return this;
            }

            throw new NullPointerException("url == null");
        }

        public a a() {
            return this.a("GET", null);
        }

        public a a(String arg7) {
            int v1;
            StringBuilder v0;
            if(arg7 != null) {
                if(arg7.regionMatches(true, 0, "ws:", 0, 3)) {
                    v0 = new StringBuilder();
                    v0.append("http:");
                    v1 = 3;
                    goto label_14;
                }
                else if(arg7.regionMatches(true, 0, "wss:", 0, 4)) {
                    v0 = new StringBuilder();
                    v0.append("https:");
                    v1 = 4;
                label_14:
                    v0.append(arg7.substring(v1));
                    arg7 = v0.toString();
                }

                t v0_1 = t.e(arg7);
                if(v0_1 != null) {
                    return this.a(v0_1);
                }

                StringBuilder v1_1 = new StringBuilder();
                v1_1.append("unexpected url: ");
                v1_1.append(arg7);
                throw new IllegalArgumentException(v1_1.toString());
            }

            throw new NullPointerException("url == null");
        }

        public a a(String arg2, String arg3) {
            this.c.c(arg2, arg3);
            return this;
        }

        public a b(String arg2, String arg3) {
            this.c.a(arg2, arg3);
            return this;
        }

        public aa b() {
            if(this.a != null) {
                return new aa(this);
            }

            throw new IllegalStateException("url == null");
        }

        public a b(String arg2) {
            this.c.b(arg2);
            return this;
        }
    }

    final t a;
    final String b;
    final s c;
    @Nullable final ab d;
    final Object e;
    private volatile d f;

    aa(a arg2) {
        Object v2;
        super();
        this.a = arg2.a;
        this.b = arg2.b;
        this.c = arg2.c.a();
        this.d = arg2.d;
        if(arg2.e != null) {
            v2 = arg2.e;
        }
        else {
            aa v2_1 = this;
        }

        this.e = v2;
    }

    @Nullable public String a(String arg2) {
        return this.c.a(arg2);
    }

    public t a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public s c() {
        return this.c;
    }

    @Nullable public ab d() {
        return this.d;
    }

    public a e() {
        return new a(this);
    }

    public d f() {
        d v0 = this.f;
        if(v0 != null) {
        }
        else {
            v0 = d.a(this.c);
            this.f = v0;
        }

        return v0;
    }

    public boolean g() {
        return this.a.c();
    }

    public String toString() {
        StringBuilder v0 = new StringBuilder();
        v0.append("Request{method=");
        v0.append(this.b);
        v0.append(", url=");
        v0.append(this.a);
        v0.append(", tag=");
        Object v1 = this.e != this ? this.e : null;
        v0.append(v1);
        v0.append('}');
        return v0.toString();
    }
}

