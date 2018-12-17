package com.persianswitch.a;

import com.persianswitch.a.a.b.i;

public final class x {
    class com.persianswitch.a.x$1 {
    }

    public class a {
        private r a;
        private String b;
        private com.persianswitch.a.q$a c;
        private y d;
        private Object e;

        a(x arg1, com.persianswitch.a.x$1 arg2) {
            this(arg1);
        }

        public a() {
            super();
            this.b = "GET";
            this.c = new com.persianswitch.a.q$a();
        }

        private a(x arg2) {
            super();
            this.a = x.a(arg2);
            this.b = x.b(arg2);
            this.d = x.c(arg2);
            this.e = x.d(arg2);
            this.c = x.e(arg2).b();
        }

        public a a(String arg2, String arg3) {
            this.c.c(arg2, arg3);
            return this;
        }

        public x a() {
            if(this.a != null) {
                return new x(this, null);
            }

            throw new IllegalStateException("url == null");
        }

        static r a(a arg0) {
            return arg0.a;
        }

        public a a(r arg2) {
            if(arg2 != null) {
                this.a = arg2;
                return this;
            }

            throw new NullPointerException("url == null");
        }

        public a a(y arg2) {
            return this.a("POST", arg2);
        }

        public a a(String arg3, y arg4) {
            StringBuilder v0;
            if(arg3 != null) {
                if(arg3.length() != 0) {
                    if(arg4 != null) {
                        if(i.c(arg3)) {
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
                        if(!i.b(arg3)) {
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

                r v0_1 = r.e(arg7);
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

        static String b(a arg0) {
            return arg0.b;
        }

        public a b(String arg2) {
            this.c.b(arg2);
            return this;
        }

        static com.persianswitch.a.q$a c(a arg0) {
            return arg0.c;
        }

        static y d(a arg0) {
            return arg0.d;
        }

        static Object e(a arg0) {
            return arg0.e;
        }
    }

    private final r a;
    private final String b;
    private final q c;
    private final y d;
    private final Object e;
    private volatile d f;

    private x(a arg2) {
        Object v2;
        super();
        this.a = a.a(arg2);
        this.b = a.b(arg2);
        this.c = a.c(arg2).a();
        this.d = a.d(arg2);
        if(a.e(arg2) != null) {
            v2 = a.e(arg2);
        }
        else {
            x v2_1 = this;
        }

        this.e = v2;
    }

    x(a arg1, com.persianswitch.a.x$1 arg2) {
        this(arg1);
    }

    public String a(String arg2) {
        return this.c.a(arg2);
    }

    public r a() {
        return this.a;
    }

    static r a(x arg0) {
        return arg0.a;
    }

    public String b() {
        return this.b;
    }

    static String b(x arg0) {
        return arg0.b;
    }

    static y c(x arg0) {
        return arg0.d;
    }

    public q c() {
        return this.c;
    }

    static Object d(x arg0) {
        return arg0.e;
    }

    public y d() {
        return this.d;
    }

    public a e() {
        return new a(this, null);
    }

    static q e(x arg0) {
        return arg0.c;
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

