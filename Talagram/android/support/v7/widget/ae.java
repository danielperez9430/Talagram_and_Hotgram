package android.support.v7.widget;

import android.view.View;
import android.view.ViewGroup$LayoutParams;
import java.util.ArrayList;
import java.util.List;

class ae {
    class a {
        long a;
        a b;

        a() {
            super();
            this.a = 0;
        }

        void a() {
            this.a = 0;
            if(this.b != null) {
                this.b.a();
            }
        }

        void a(int arg12, boolean arg13) {
            // Method was not decompiled
        }

        void a(int arg5) {
            int v0 = 64;
            if(arg5 >= v0) {
                this.b();
                this.b.a(arg5 - v0);
            }
            else {
                this.a |= 1 << arg5;
            }
        }

        void b(int arg7) {
            int v0 = 64;
            if(arg7 < v0) {
                this.a &= 1 << arg7 ^ -1;
            }
            else if(this.b != null) {
                this.b.b(arg7 - v0);
            }
        }

        private void b() {
            if(this.b == null) {
                this.b = new a();
            }
        }

        boolean c(int arg5) {
            int v0 = 64;
            if(arg5 >= v0) {
                this.b();
                return this.b.c(arg5 - v0);
            }

            boolean v5 = (this.a & 1 << arg5) != 0 ? true : false;
            return v5;
        }

        boolean d(int arg13) {
            // Method was not decompiled
        }

        int e(int arg7) {
            int v1 = 64;
            long v2 = 1;
            if(this.b == null) {
                if(arg7 >= v1) {
                    return Long.bitCount(this.a);
                }

                return Long.bitCount(this.a & (v2 << arg7) - v2);
            }

            if(arg7 < v1) {
                return Long.bitCount(this.a & (v2 << arg7) - v2);
            }

            return this.b.e(arg7 - v1) + Long.bitCount(this.a);
        }

        public String toString() {
            String v0 = this.b == null ? Long.toBinaryString(this.a) : this.b.toString() + "xx" + Long.toBinaryString(this.a);
            return v0;
        }
    }

    interface b {
        int a();

        void a(int arg1);

        int a(View arg1);

        void a(View arg1, int arg2, ViewGroup$LayoutParams arg3);

        void a(View arg1, int arg2);

        void b();

        View b(int arg1);

        w b(View arg1);

        void c(View arg1);

        void c(int arg1);

        void d(View arg1);
    }

    final b a;
    final a b;
    final List c;

    ae(b arg1) {
        super();
        this.a = arg1;
        this.b = new a();
        this.c = new ArrayList();
    }

    void a() {
        this.b.a();
        int v0;
        for(v0 = this.c.size() - 1; v0 >= 0; --v0) {
            this.a.d(this.c.get(v0));
            this.c.remove(v0);
        }

        this.a.b();
    }

    void a(int arg3) {
        arg3 = this.f(arg3);
        View v0 = this.a.b(arg3);
        if(v0 == null) {
            return;
        }

        if(this.b.d(arg3)) {
            this.h(v0);
        }

        this.a.a(arg3);
    }

    void a(View arg3) {
        int v0 = this.a.a(arg3);
        if(v0 < 0) {
            return;
        }

        if(this.b.d(v0)) {
            this.h(arg3);
        }

        this.a.a(v0);
    }

    void a(View arg2, int arg3, ViewGroup$LayoutParams arg4, boolean arg5) {
        arg3 = arg3 < 0 ? this.a.a() : this.f(arg3);
        this.b.a(arg3, arg5);
        if(arg5) {
            this.g(arg2);
        }

        this.a.a(arg2, arg3, arg4);
    }

    void a(View arg2, int arg3, boolean arg4) {
        arg3 = arg3 < 0 ? this.a.a() : this.f(arg3);
        this.b.a(arg3, arg4);
        if(arg4) {
            this.g(arg2);
        }

        this.a.a(arg2, arg3);
    }

    void a(View arg2, boolean arg3) {
        this.a(arg2, -1, arg3);
    }

    int b() {
        return this.a.a() - this.c.size();
    }

    int b(View arg3) {
        int v3 = this.a.a(arg3);
        int v0 = -1;
        if(v3 == v0) {
            return v0;
        }

        if(this.b.c(v3)) {
            return v0;
        }

        return v3 - this.b.e(v3);
    }

    View b(int arg2) {
        return this.a.b(this.f(arg2));
    }

    int c() {
        return this.a.a();
    }

    View c(int arg6) {
        int v0 = this.c.size();
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            Object v2 = this.c.get(v1);
            w v3 = this.a.b(((View)v2));
            if(v3.getLayoutPosition() == arg6 && !v3.isInvalid() && !v3.isRemoved()) {
                return ((View)v2);
            }
        }

        return null;
    }

    boolean c(View arg2) {
        return this.c.contains(arg2);
    }

    View d(int arg2) {
        return this.a.b(arg2);
    }

    void d(View arg4) {
        int v0 = this.a.a(arg4);
        if(v0 >= 0) {
            this.b.a(v0);
            this.g(arg4);
            return;
        }

        StringBuilder v1 = new StringBuilder();
        v1.append("view is not a child, cannot hide ");
        v1.append(arg4);
        throw new IllegalArgumentException(v1.toString());
    }

    void e(int arg2) {
        arg2 = this.f(arg2);
        this.b.d(arg2);
        this.a.c(arg2);
    }

    void e(View arg4) {
        StringBuilder v1;
        int v0 = this.a.a(arg4);
        if(v0 >= 0) {
            if(this.b.c(v0)) {
                this.b.b(v0);
                this.h(arg4);
                return;
            }

            v1 = new StringBuilder();
            v1.append("trying to unhide a view that was not hidden");
            v1.append(arg4);
            throw new RuntimeException(v1.toString());
        }

        v1 = new StringBuilder();
        v1.append("view is not a child, cannot hide ");
        v1.append(arg4);
        throw new IllegalArgumentException(v1.toString());
    }

    private int f(int arg5) {
        int v0 = -1;
        if(arg5 < 0) {
            return v0;
        }

        int v1 = this.a.a();
        int v2 = arg5;
        while(v2 < v1) {
            int v3 = arg5 - (v2 - this.b.e(v2));
            if(v3 != 0) {
                v2 += v3;
                continue;
            }

            goto label_12;
        }

        return v0;
    label_12:
        while(this.b.c(v2)) {
            ++v2;
        }

        return v2;
    }

    boolean f(View arg4) {
        int v0 = this.a.a(arg4);
        if(v0 == -1) {
            this.h(arg4);
            return 1;
        }

        if(this.b.c(v0)) {
            this.b.d(v0);
            this.h(arg4);
            this.a.a(v0);
            return 1;
        }

        return 0;
    }

    private void g(View arg2) {
        this.c.add(arg2);
        this.a.c(arg2);
    }

    private boolean h(View arg2) {
        if(this.c.remove(arg2)) {
            this.a.d(arg2);
            return 1;
        }

        return 0;
    }

    public String toString() {
        return this.b.toString() + ", hidden list:" + this.c.size();
    }
}

