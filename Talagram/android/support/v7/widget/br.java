package android.support.v7.widget;

import android.support.v4.f.f;
import android.support.v4.f.k$b;

class br {
    class a {
        int a;
        c b;
        c c;
        static android.support.v4.f.k$a d;

        static {
            a.d = new b(20);
        }

        private a() {
            super();
        }

        static void a(a arg1) {
            arg1.a = 0;
            arg1.b = null;
            arg1.c = null;
            a.d.a(arg1);
        }

        static a a() {
            Object v0 = a.d.a();
            if(v0 == null) {
                a v0_1 = new a();
            }

            return ((a)v0);
        }

        static void b() {
            while(a.d.a() != null) {
            }
        }
    }

    interface android.support.v7.widget.br$b {
        void a(w arg1);

        void a(w arg1, c arg2, c arg3);

        void b(w arg1, c arg2, c arg3);

        void c(w arg1, c arg2, c arg3);
    }

    final android.support.v4.f.a a;
    final f b;

    br() {
        super();
        this.a = new android.support.v4.f.a();
        this.b = new f();
    }

    private c a(w arg4, int arg5) {
        c v5;
        int v4 = this.a.a(arg4);
        c v0 = null;
        if(v4 < 0) {
            return v0;
        }

        Object v1 = this.a.c(v4);
        if(v1 != null && (((a)v1).a & arg5) != 0) {
            ((a)v1).a &= arg5 ^ -1;
            if(arg5 == 4) {
                v5 = ((a)v1).b;
            }
            else if(arg5 == 8) {
                v5 = ((a)v1).c;
            }
            else {
                goto label_29;
            }

            if((((a)v1).a & 12) == 0) {
                this.a.d(v4);
                a.a(((a)v1));
            }

            return v5;
        label_29:
            throw new IllegalArgumentException("Must provide flag PRE or POST");
        }

        return v0;
    }

    w a(long arg2) {
        return this.b.a(arg2);
    }

    void a() {
        this.a.clear();
        this.b.c();
    }

    void a(long arg2, w arg4) {
        this.b.b(arg2, arg4);
    }

    void a(w arg3, c arg4) {
        Object v0 = this.a.get(arg3);
        if(v0 == null) {
            a v0_1 = a.a();
            this.a.put(arg3, v0_1);
        }

        ((a)v0).b = arg4;
        ((a)v0).a |= 4;
    }

    void a(android.support.v7.widget.br$b arg6) {
        int v0;
        for(v0 = this.a.size() - 1; v0 >= 0; --v0) {
            Object v1 = this.a.b(v0);
            Object v2 = this.a.d(v0);
            if((((a)v2).a & 3) == 3 || ((a)v2).b == null) {
                arg6.a(((w)v1));
                goto label_51;
            label_20:
                c v3 = ((a)v2).b;
                c v4 = ((a)v2).c;
                goto label_22;
            label_24:
                if((((a)v2).a & 14) != 14) {
                    if((((a)v2).a & 12) == 12) {
                        arg6.c(((w)v1), ((a)v2).b, ((a)v2).c);
                        goto label_51;
                    }
                    else if((((a)v2).a & 4) != 0) {
                        v3 = ((a)v2).b;
                        v4 = null;
                    label_22:
                        arg6.a(((w)v1), v3, v4);
                        goto label_51;
                    }
                    else if((((a)v2).a & 8) != 0) {
                    }
                    else {
                        goto label_51;
                    }
                }

                arg6.b(((w)v1), ((a)v2).b, ((a)v2).c);
            }
            else if((((a)v2).a & 1) == 0) {
                goto label_24;
            }
            else {
                goto label_20;
            }

        label_51:
            a.a(((a)v2));
        }
    }

    boolean a(w arg2) {
        Object v2 = this.a.get(arg2);
        boolean v0 = true;
        if(v2 == null || (((a)v2).a & 1) == 0) {
            v0 = false;
        }
        else {
        }

        return v0;
    }

    c b(w arg2) {
        return this.a(arg2, 4);
    }

    void b() {
        a.b();
    }

    void b(w arg3, c arg4) {
        Object v0 = this.a.get(arg3);
        if(v0 == null) {
            a v0_1 = a.a();
            this.a.put(arg3, v0_1);
        }

        ((a)v0).a |= 2;
        ((a)v0).b = arg4;
    }

    c c(w arg2) {
        return this.a(arg2, 8);
    }

    void c(w arg3, c arg4) {
        Object v0 = this.a.get(arg3);
        if(v0 == null) {
            a v0_1 = a.a();
            this.a.put(arg3, v0_1);
        }

        ((a)v0).c = arg4;
        ((a)v0).a |= 8;
    }

    boolean d(w arg2) {
        Object v2 = this.a.get(arg2);
        boolean v2_1 = v2 == null || (((a)v2).a & 4) == 0 ? false : true;
        return v2_1;
    }

    void e(w arg3) {
        Object v0 = this.a.get(arg3);
        if(v0 == null) {
            a v0_1 = a.a();
            this.a.put(arg3, v0_1);
        }

        ((a)v0).a |= 1;
    }

    void f(w arg2) {
        Object v2 = this.a.get(arg2);
        if(v2 == null) {
            return;
        }

        ((a)v2).a &= -2;
    }

    void g(w arg3) {
        int v0 = this.b.b() - 1;
        while(v0 >= 0) {
            if(arg3 == this.b.c(v0)) {
                this.b.a(v0);
            }
            else {
                --v0;
                continue;
            }

            break;
        }

        Object v3 = this.a.remove(arg3);
        if(v3 != null) {
            a.a(((a)v3));
        }
    }

    public void h(w arg1) {
        this.f(arg1);
    }
}

