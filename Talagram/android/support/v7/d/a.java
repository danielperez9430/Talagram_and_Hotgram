package android.support.v7.d;

import android.graphics.Color;
import android.util.TimingLogger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

final class a {
    final class android.support.v7.d.a$1 implements Comparator {
        android.support.v7.d.a$1() {
            super();
        }

        public int a(android.support.v7.d.a$a arg1, android.support.v7.d.a$a arg2) {
            return arg2.a() - arg1.a();
        }

        public int compare(Object arg1, Object arg2) {
            return this.a(((android.support.v7.d.a$a)arg1), ((android.support.v7.d.a$a)arg2));
        }
    }

    class android.support.v7.d.a$a {
        private int b;
        private int c;
        private int d;
        private int e;
        private int f;
        private int g;
        private int h;
        private int i;
        private int j;

        android.support.v7.d.a$a(a arg1, int arg2, int arg3) {
            this.a = arg1;
            super();
            this.b = arg2;
            this.c = arg3;
            this.d();
        }

        final int a() {
            return (this.f - this.e + 1) * (this.h - this.g + 1) * (this.j - this.i + 1);
        }

        final boolean b() {
            boolean v1 = true;
            if(this.c() > 1) {
            }
            else {
                v1 = false;
            }

            return v1;
        }

        final int c() {
            return this.c + 1 - this.b;
        }

        final void d() {
            int[] v0 = this.a.a;
            int[] v1 = this.a.b;
            int v2 = this.b;
            int v3 = 2147483647;
            int v4 = -2147483648;
            int v5 = 0;
            int v6 = 2147483647;
            int v7 = -2147483648;
            int v8 = 2147483647;
            int v9 = -2147483648;
            while(v2 <= this.c) {
                int v10 = v0[v2];
                v5 += v1[v10];
                int v11 = a.a(v10);
                int v12 = a.b(v10);
                v10 = a.c(v10);
                if(v11 > v4) {
                    v4 = v11;
                }

                if(v11 < v3) {
                    v3 = v11;
                }

                if(v12 > v7) {
                    v7 = v12;
                }

                if(v12 < v6) {
                    v6 = v12;
                }

                if(v10 > v9) {
                    v9 = v10;
                }

                if(v10 < v8) {
                    v8 = v10;
                }

                ++v2;
            }

            this.e = v3;
            this.f = v4;
            this.g = v6;
            this.h = v7;
            this.i = v8;
            this.j = v9;
            this.d = v5;
        }

        final android.support.v7.d.a$a e() {
            if(this.b()) {
                int v0 = this.g();
                android.support.v7.d.a$a v1 = new android.support.v7.d.a$a(this.a, v0 + 1, this.c);
                this.c = v0;
                this.d();
                return v1;
            }

            throw new IllegalStateException("Can not split a box with only 1 color");
        }

        final int f() {
            int v0 = this.f - this.e;
            int v1 = this.h - this.g;
            int v2 = this.j - this.i;
            if(v0 >= v1 && v0 >= v2) {
                return -3;
            }

            if(v1 >= v0 && v1 >= v2) {
                return -2;
            }

            return -1;
        }

        final int g() {
            int v0 = this.f();
            int[] v1 = this.a.a;
            int[] v2 = this.a.b;
            a.a(v1, v0, this.b, this.c);
            Arrays.sort(v1, this.b, this.c + 1);
            a.a(v1, v0, this.b, this.c);
            v0 = this.d / 2;
            int v3 = this.b;
            int v4 = 0;
            while(v3 <= this.c) {
                v4 += v2[v1[v3]];
                if(v4 >= v0) {
                    return Math.min(this.c - 1, v3);
                }

                ++v3;
            }

            return this.b;
        }

        final c h() {
            int[] v0 = this.a.a;
            int[] v1 = this.a.b;
            int v2 = this.b;
            int v3 = 0;
            int v4 = 0;
            int v5 = 0;
            int v6 = 0;
            while(v2 <= this.c) {
                int v7 = v0[v2];
                int v8 = v1[v7];
                v3 += v8;
                v4 += a.a(v7) * v8;
                v5 += a.b(v7) * v8;
                v6 += v8 * a.c(v7);
                ++v2;
            }

            float v1_1 = ((float)v3);
            return new c(a.a(Math.round((((float)v4)) / v1_1), Math.round((((float)v5)) / v1_1), Math.round((((float)v6)) / v1_1)), v3);
        }
    }

    final int[] a;
    final int[] b;
    final List c;
    final TimingLogger d;
    final b[] e;
    private final float[] f;
    private static final Comparator g;

    static {
        a.g = new android.support.v7.d.a$1();
    }

    a(int[] arg6, int arg7, b[] arg8) {
        int v2;
        super();
        this.f = new float[3];
        this.d = null;
        this.e = arg8;
        int[] v8 = new int[32768];
        this.b = v8;
        int v0 = 0;
        int v1;
        for(v1 = 0; v1 < arg6.length; ++v1) {
            v2 = a.f(arg6[v1]);
            arg6[v1] = v2;
            ++v8[v2];
        }

        int v6 = 0;
        v1 = 0;
        while(v6 < v8.length) {
            if(v8[v6] > 0 && (this.e(v6))) {
                v8[v6] = 0;
            }

            if(v8[v6] > 0) {
                ++v1;
            }

            ++v6;
        }

        arg6 = new int[v1];
        this.a = arg6;
        v2 = 0;
        int v3 = 0;
        while(v2 < v8.length) {
            if(v8[v2] > 0) {
                arg6[v3] = v2;
                ++v3;
            }

            ++v2;
        }

        if(v1 <= arg7) {
            this.c = new ArrayList();
            arg7 = arg6.length;
            goto label_54;
        }
        else {
            this.c = this.d(arg7);
            return;
        label_54:
            while(v0 < arg7) {
                this.c.add(new c(a.g(arg6[v0]), v8[arg6[v0]]));
                ++v0;
            }
        }
    }

    static int a(int arg0) {
        return arg0 >> 10 & 31;
    }

    static int a(int arg2, int arg3, int arg4) {
        return Color.rgb(a.b(arg2, 5, 8), a.b(arg3, 5, 8), a.b(arg4, 5, 8));
    }

    private List a(Collection arg4) {
        ArrayList v0 = new ArrayList(arg4.size());
        Iterator v4 = arg4.iterator();
        while(v4.hasNext()) {
            c v1 = v4.next().h();
            if(this.a(v1)) {
                continue;
            }

            v0.add(v1);
        }

        return ((List)v0);
    }

    private boolean a(c arg2) {
        return this.a(arg2.a(), arg2.b());
    }

    private void a(PriorityQueue arg3, int arg4) {
        while(arg3.size() < arg4) {
            Object v0 = arg3.poll();
            if(v0 == null) {
                return;
            }

            if(!((android.support.v7.d.a$a)v0).b()) {
                return;
            }

            arg3.offer(((android.support.v7.d.a$a)v0).e());
            arg3.offer(v0);
        }
    }

    static void a(int[] arg2, int arg3, int arg4, int arg5) {
        switch(arg3) {
            case -2: {
                while(arg4 <= arg5) {
                    arg2[arg4] = a.c(arg2[arg4]) | (a.b(arg2[arg4]) << 10 | a.a(arg2[arg4]) << 5);
                    ++arg4;
                }
            }
            case -1: {
                while(arg4 <= arg5) {
                    arg2[arg4] = a.a(arg2[arg4]) | (a.c(arg2[arg4]) << 10 | a.b(arg2[arg4]) << 5);
                    ++arg4;
                }
            }
            default: {
                break;
            }
        }
    }

    private boolean a(int arg5, float[] arg6) {
        if(this.e != null && this.e.length > 0) {
            int v0 = this.e.length;
            int v2 = 0;
            while(v2 < v0) {
                if(!this.e[v2].a(arg5, arg6)) {
                    return 1;
                }
                else {
                    ++v2;
                    continue;
                }

                return 0;
            }
        }

        return 0;
    }

    List a() {
        return this.c;
    }

    private static int b(int arg0, int arg1, int arg2) {
        if(arg2 > arg1) {
            arg0 <<= arg2 - arg1;
        }
        else {
            arg0 >>= arg1 - arg2;
        }

        return arg0 & (1 << arg2) - 1;
    }

    static int b(int arg0) {
        return arg0 >> 5 & 31;
    }

    static int c(int arg0) {
        return arg0 & 31;
    }

    private List d(int arg5) {
        PriorityQueue v0 = new PriorityQueue(arg5, a.g);
        v0.offer(new android.support.v7.d.a$a(this, 0, this.a.length - 1));
        this.a(v0, arg5);
        return this.a(((Collection)v0));
    }

    private boolean e(int arg2) {
        arg2 = a.g(arg2);
        android.support.v4.graphics.a.a(arg2, this.f);
        return this.a(arg2, this.f);
    }

    private static int f(int arg4) {
        return a.b(Color.blue(arg4), 8, 5) | (a.b(Color.red(arg4), 8, 5) << 10 | a.b(Color.green(arg4), 8, 5) << 5);
    }

    private static int g(int arg2) {
        return a.a(a.a(arg2), a.b(arg2), a.c(arg2));
    }
}

