package android.support.v7.widget;

import android.view.View;

class bq {
    class a {
        int a;
        int b;
        int c;
        int d;
        int e;

        a() {
            super();
            this.a = 0;
        }

        void a(int arg1, int arg2, int arg3, int arg4) {
            this.b = arg1;
            this.c = arg2;
            this.d = arg3;
            this.e = arg4;
        }

        void a() {
            this.a = 0;
        }

        void a(int arg2) {
            this.a |= arg2;
        }

        int a(int arg1, int arg2) {
            if(arg1 > arg2) {
                return 1;
            }

            if(arg1 == arg2) {
                return 2;
            }

            return 4;
        }

        boolean b() {
            if((this.a & 7) != 0 && (this.a & this.a(this.d, this.b) << 0) == 0) {
                return 0;
            }

            if((this.a & 112) != 0 && (this.a & this.a(this.d, this.c) << 4) == 0) {
                return 0;
            }

            if((this.a & 1792) != 0 && (this.a & this.a(this.e, this.b) << 8) == 0) {
                return 0;
            }

            if((this.a & 28672) != 0 && (this.a & this.a(this.e, this.c) << 12) == 0) {
                return 0;
            }

            return 1;
        }
    }

    interface b {
        int a();

        View a(int arg1);

        int a(View arg1);

        int b();

        int b(View arg1);
    }

    final b a;
    a b;

    bq(b arg1) {
        super();
        this.a = arg1;
        this.b = new a();
    }

    View a(int arg9, int arg10, int arg11, int arg12) {
        int v0 = this.a.a();
        int v1 = this.a.b();
        int v2 = arg10 > arg9 ? 1 : -1;
        View v3 = null;
        while(arg9 != arg10) {
            View v4 = this.a.a(arg9);
            this.b.a(v0, v1, this.a.a(v4), this.a.b(v4));
            if(arg11 != 0) {
                this.b.a();
                this.b.a(arg11);
                if(this.b.b()) {
                    return v4;
                }
            }

            if(arg12 != 0) {
                this.b.a();
                this.b.a(arg12);
                if(this.b.b()) {
                    v3 = v4;
                }
            }

            arg9 += v2;
        }

        return v3;
    }

    boolean a(View arg6, int arg7) {
        this.b.a(this.a.a(), this.a.b(), this.a.a(arg6), this.a.b(arg6));
        if(arg7 != 0) {
            this.b.a();
            this.b.a(arg7);
            return this.b.b();
        }

        return 0;
    }
}

