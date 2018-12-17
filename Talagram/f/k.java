package f;

import e.c;
import e.d;
import javax.annotation.Nullable;
import okhttp3.aa;
import okhttp3.ab;
import okhttp3.q;
import okhttp3.s;
import okhttp3.t;
import okhttp3.v;
import okhttp3.w$b;
import okhttp3.w;

final class k {
    class a extends ab {
        private final ab a;
        private final v b;

        a(ab arg1, v arg2) {
            super();
            this.a = arg1;
            this.b = arg2;
        }

        public v a() {
            return this.b;
        }

        public void a(d arg2) {
            this.a.a(arg2);
        }

        public long b() {
            return this.a.b();
        }
    }

    private static final char[] a;
    private final String b;
    private final t c;
    @Nullable private String d;
    @Nullable private okhttp3.t$a e;
    private final okhttp3.aa$a f;
    @Nullable private v g;
    private final boolean h;
    @Nullable private okhttp3.w$a i;
    @Nullable private okhttp3.q$a j;
    @Nullable private ab k;

    static {
        k.a = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    }

    k(String arg1, t arg2, @Nullable String arg3, @Nullable s arg4, @Nullable v arg5, boolean arg6, boolean arg7, boolean arg8) {
        super();
        this.b = arg1;
        this.c = arg2;
        this.d = arg3;
        this.f = new okhttp3.aa$a();
        this.g = arg5;
        this.h = arg6;
        if(arg4 != null) {
            this.f.a(arg4);
        }

        if(arg7) {
            this.j = new okhttp3.q$a();
        }
        else if(arg8) {
            this.i = new okhttp3.w$a();
            this.i.a(w.e);
        }
    }

    void a(ab arg1) {
        this.k = arg1;
    }

    void a(String arg3, String arg4) {
        if("Content-Type".equalsIgnoreCase(arg3)) {
            v v3 = v.a(arg4);
            if(v3 != null) {
                this.g = v3;
            }
            else {
                StringBuilder v0 = new StringBuilder();
                v0.append("Malformed content type: ");
                v0.append(arg4);
                throw new IllegalArgumentException(v0.toString());
            }
        }
        else {
            this.f.b(arg3, arg4);
        }
    }

    void a(s arg2, ab arg3) {
        this.i.a(arg2, arg3);
    }

    void a(String arg4, String arg5, boolean arg6) {
        if(this.d != null) {
            String v0 = this.d;
            StringBuilder v1 = new StringBuilder();
            v1.append("{");
            v1.append(arg4);
            v1.append("}");
            this.d = v0.replace(v1.toString(), k.a(arg5, arg6));
            return;
        }

        throw new AssertionError();
    }

    void a(b arg2) {
        this.i.a(arg2);
    }

    void a(Object arg1) {
        this.d = arg1.toString();
    }

    private static String a(String arg6, boolean arg7) {
        int v0 = arg6.length();
        int v2 = 0;
        while(true) {
            if(v2 < v0) {
                int v3 = arg6.codePointAt(v2);
                if(v3 >= 32 && v3 < 127 && " \"<>^`{}|\\?#".indexOf(v3) == -1) {
                    if(!arg7) {
                        if(v3 == 47) {
                            break;
                        }
                        else if(v3 == 37) {
                            break;
                        }
                    }

                    v2 += Character.charCount(v3);
                    continue;
                }

                break;
            }

            return arg6;
        }

        c v3_1 = new c();
        v3_1.a(arg6, 0, v2);
        k.a(v3_1, arg6, v2, v0, arg7);
        return v3_1.p();
    }

    private static void a(c arg6, String arg7, int arg8, int arg9, boolean arg10) {
        c v0 = null;
        while(arg8 < arg9) {
            int v1 = arg7.codePointAt(arg8);
            if(!arg10) {
            label_13:
                int v3 = 37;
                if(v1 >= 32 && v1 < 127 && " \"<>^`{}|\\?#".indexOf(v1) == -1) {
                    if(!arg10) {
                        if(v1 == 47) {
                            goto label_29;
                        }
                        else if(v1 == v3) {
                            goto label_29;
                        }
                    }

                    arg6.a(v1);
                    goto label_48;
                }

            label_29:
                if(v0 == null) {
                    v0 = new c();
                }

                v0.a(v1);
                while(!v0.f()) {
                    int v2 = v0.i() & 255;
                    arg6.b(v3);
                    arg6.b(k.a[v2 >> 4 & 15]);
                    arg6.b(k.a[v2 & 15]);
                }
            }
            else if(v1 != 9 && v1 != 10 && v1 != 12) {
                if(v1 == 13) {
                }
                else {
                    goto label_13;
                }
            }

        label_48:
            arg8 += Character.charCount(v1);
        }
    }

    aa a() {
        q v1_1;
        t v0_1;
        okhttp3.t$a v0 = this.e;
        if(v0 != null) {
            v0_1 = v0.c();
        }
        else {
            v0_1 = this.c.c(this.d);
            if(v0_1 != null) {
                goto label_8;
            }

            goto label_43;
        }

    label_8:
        ab v1 = this.k;
        if(v1 == null) {
            if(this.j != null) {
                v1_1 = this.j.a();
            }
            else if(this.i != null) {
                w v1_2 = this.i.a();
            }
            else if(this.h) {
                v1 = ab.a(null, new byte[0]);
            }
        }

        v v2 = this.g;
        if(v2 != null) {
            if((((ab)v1_1)) != null) {
                a v1_3 = new a(((ab)v1_1), v2);
            }
            else {
                this.f.b("Content-Type", v2.toString());
            }
        }

        return this.f.a(v0_1).a(this.b, ((ab)v1_1)).b();
    label_43:
        StringBuilder v1_4 = new StringBuilder();
        v1_4.append("Malformed URL. Base: ");
        v1_4.append(this.c);
        v1_4.append(", Relative: ");
        v1_4.append(this.d);
        throw new IllegalArgumentException(v1_4.toString());
    }

    void b(String arg3, @Nullable String arg4, boolean arg5) {
        if(this.d != null) {
            this.e = this.c.d(this.d);
            if(this.e != null) {
                this.d = null;
            }
            else {
                StringBuilder v4 = new StringBuilder();
                v4.append("Malformed URL. Base: ");
                v4.append(this.c);
                v4.append(", Relative: ");
                v4.append(this.d);
                throw new IllegalArgumentException(v4.toString());
            }
        }

        if(arg5) {
            this.e.b(arg3, arg4);
        }
        else {
            this.e.a(arg3, arg4);
        }
    }

    void c(String arg1, String arg2, boolean arg3) {
        if(arg3) {
            this.j.b(arg1, arg2);
        }
        else {
            this.j.a(arg1, arg2);
        }
    }
}

