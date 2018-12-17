package android.support.v7.widget;

import android.support.v4.os.d;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.TimeUnit;

final class an implements Runnable {
    final class android.support.v7.widget.an$1 implements Comparator {
        android.support.v7.widget.an$1() {
            super();
        }

        public int a(b arg6, b arg7) {
            int v2 = 1;
            int v0 = arg6.d == null ? 1 : 0;
            int v3 = arg7.d == null ? 1 : 0;
            if(v0 != v3) {
                if(arg6.d == null) {
                }
                else {
                    v2 = -1;
                }

                return v2;
            }

            if(arg6.a != arg7.a) {
                if(arg6.a) {
                    v2 = -1;
                }

                return v2;
            }

            v0 = arg7.b - arg6.b;
            if(v0 != 0) {
                return v0;
            }

            int v6 = arg6.c - arg7.c;
            if(v6 != 0) {
                return v6;
            }

            return 0;
        }

        public int compare(Object arg1, Object arg2) {
            return this.a(((b)arg1), ((b)arg2));
        }
    }

    class a implements android.support.v7.widget.RecyclerView$i$a {
        int a;
        int b;
        int[] c;
        int d;

        a() {
            super();
        }

        void a(RecyclerView arg5, boolean arg6) {
            this.d = 0;
            if(this.c != null) {
                Arrays.fill(this.c, -1);
            }

            i v0 = arg5.n;
            if(arg5.m != null && v0 != null && (v0.r())) {
                if(arg6) {
                    if(!arg5.f.d()) {
                        v0.a(arg5.m.getItemCount(), ((android.support.v7.widget.RecyclerView$i$a)this));
                    }
                }
                else if(!arg5.v()) {
                    v0.a(this.a, this.b, arg5.D, ((android.support.v7.widget.RecyclerView$i$a)this));
                }

                if(this.d <= v0.x) {
                    return;
                }

                v0.x = this.d;
                v0.y = arg6;
                arg5.e.b();
            }
        }

        void a(int arg1, int arg2) {
            this.a = arg1;
            this.b = arg2;
        }

        void a() {
            if(this.c != null) {
                Arrays.fill(this.c, -1);
            }

            this.d = 0;
        }

        boolean a(int arg5) {
            if(this.c != null) {
                int v0 = this.d * 2;
                int v2 = 0;
                while(v2 < v0) {
                    if(this.c[v2] == arg5) {
                        return 1;
                    }
                    else {
                        v2 += 2;
                        continue;
                    }

                    return 0;
                }
            }

            return 0;
        }

        public void b(int arg6, int arg7) {
            if(arg6 >= 0) {
                if(arg7 >= 0) {
                    int v0 = this.d * 2;
                    if(this.c == null) {
                        this.c = new int[4];
                        Arrays.fill(this.c, -1);
                    }
                    else if(v0 >= this.c.length) {
                        int[] v1 = this.c;
                        this.c = new int[v0 * 2];
                        System.arraycopy(v1, 0, this.c, 0, v1.length);
                    }

                    this.c[v0] = arg6;
                    this.c[v0 + 1] = arg7;
                    ++this.d;
                    return;
                }

                throw new IllegalArgumentException("Pixel distance must be non-negative");
            }

            throw new IllegalArgumentException("Layout positions must be non-negative");
        }
    }

    class b {
        public boolean a;
        public int b;
        public int c;
        public RecyclerView d;
        public int e;

        b() {
            super();
        }

        public void a() {
            this.a = false;
            this.b = 0;
            this.c = 0;
            this.d = null;
            this.e = 0;
        }
    }

    static final ThreadLocal a;
    ArrayList b;
    long c;
    long d;
    static Comparator e;
    private ArrayList f;

    static {
        an.a = new ThreadLocal();
        an.e = new android.support.v7.widget.an$1();
    }

    an() {
        super();
        this.b = new ArrayList();
        this.f = new ArrayList();
    }

    private w a(RecyclerView arg3, int arg4, long arg5) {
        w v4_1;
        if(an.a(arg3, arg4)) {
            return null;
        }

        p v0 = arg3.e;
        try {
            arg3.l();
            v4_1 = v0.a(arg4, false, arg5);
            if(v4_1 != null) {
                if((v4_1.isBound()) && !v4_1.isInvalid()) {
                    v0.a(v4_1.itemView);
                    goto label_17;
                }

                v0.a(v4_1, false);
            }
        }
        catch(Throwable v4) {
            arg3.b(false);
            throw v4;
        }

    label_17:
        arg3.b(false);
        return v4_1;
    }

    static boolean a(RecyclerView arg5, int arg6) {
        int v0 = arg5.g.c();
        int v2;
        for(v2 = 0; v2 < v0; ++v2) {
            w v3 = RecyclerView.e(arg5.g.d(v2));
            if(v3.mPosition == arg6 && !v3.isInvalid()) {
                return 1;
            }
        }

        return 0;
    }

    private void a() {
        Object v8_1;
        Object v4;
        int v0 = this.b.size();
        int v2 = 0;
        int v3 = 0;
        while(v2 < v0) {
            v4 = this.b.get(v2);
            if(((RecyclerView)v4).getWindowVisibility() == 0) {
                ((RecyclerView)v4).C.a(((RecyclerView)v4), false);
                v3 += ((RecyclerView)v4).C.d;
            }

            ++v2;
        }

        this.f.ensureCapacity(v3);
        v2 = 0;
        v3 = 0;
        while(v2 < v0) {
            v4 = this.b.get(v2);
            if(((RecyclerView)v4).getWindowVisibility() != 0) {
            }
            else {
                a v5 = ((RecyclerView)v4).C;
                int v6 = Math.abs(v5.a) + Math.abs(v5.b);
                int v7 = v3;
                for(v3 = 0; v3 < v5.d * 2; v3 += 2) {
                    if(v7 >= this.f.size()) {
                        b v8 = new b();
                        this.f.add(v8);
                    }
                    else {
                        v8_1 = this.f.get(v7);
                    }

                    int v9 = v5.c[v3 + 1];
                    boolean v10 = v9 <= v6 ? true : false;
                    ((b)v8_1).a = v10;
                    ((b)v8_1).b = v6;
                    ((b)v8_1).c = v9;
                    ((b)v8_1).d = ((RecyclerView)v4);
                    ((b)v8_1).e = v5.c[v3];
                    ++v7;
                }

                v3 = v7;
            }

            ++v2;
        }

        Collections.sort(this.f, an.e);
    }

    private void a(RecyclerView arg4, long arg5) {
        if(arg4 == null) {
            return;
        }

        if((arg4.x) && arg4.g.c() != 0) {
            arg4.c();
        }

        a v0 = arg4.C;
        v0.a(arg4, true);
        if(v0.d != 0) {
            try {
                d.a("RV Nested Prefetch");
                arg4.D.a(arg4.m);
                int v1;
                for(v1 = 0; v1 < v0.d * 2; v1 += 2) {
                    this.a(arg4, v0.c[v1], arg5);
                }
            }
            catch(Throwable v4) {
                goto label_30;
            }

            d.a();
            return;
        label_30:
            d.a();
            throw v4;
        }
    }

    private void a(b arg4, long arg5) {
        long v0 = arg4.a ? 9223372036854775807L : arg5;
        w v4 = this.a(arg4.d, arg4.e, v0);
        if(v4 != null && v4.mNestedRecyclerView != null && (v4.isBound()) && !v4.isInvalid()) {
            this.a(v4.mNestedRecyclerView.get(), arg5);
        }
    }

    void a(long arg1) {
        this.a();
        this.b(arg1);
    }

    public void a(RecyclerView arg2) {
        this.b.add(arg2);
    }

    void a(RecyclerView arg6, int arg7, int arg8) {
        if((arg6.isAttachedToWindow()) && this.c == 0) {
            this.c = arg6.getNanoTime();
            arg6.post(((Runnable)this));
        }

        arg6.C.a(arg7, arg8);
    }

    private void b(long arg4) {
        int v0 = 0;
        while(v0 < this.f.size()) {
            Object v1 = this.f.get(v0);
            if(((b)v1).d == null) {
            }
            else {
                this.a(((b)v1), arg4);
                ((b)v1).a();
                ++v0;
                continue;
            }

            return;
        }
    }

    public void b(RecyclerView arg2) {
        this.b.remove(arg2);
    }

    public void run() {
        long v0 = 0;
        try {
            d.a("RV Prefetch");
            if(!this.b.isEmpty()) {
                int v2_1 = this.b.size();
                int v3 = 0;
                long v4 = v0;
                while(v3 < v2_1) {
                    Object v6 = this.b.get(v3);
                    if(((RecyclerView)v6).getWindowVisibility() == 0) {
                        v4 = Math.max(((RecyclerView)v6).getDrawingTime(), v4);
                    }

                    ++v3;
                }

                if(v4 == v0) {
                    goto label_6;
                }

                this.a(TimeUnit.MILLISECONDS.toNanos(v4) + this.d);
                goto label_30;
            }

            goto label_6;
        }
        catch(Throwable v2) {
            goto label_34;
        }

    label_30:
        this.c = v0;
        d.a();
        return;
    label_6:
        this.c = v0;
        d.a();
        return;
    label_34:
        this.c = v0;
        d.a();
        throw v2;
    }
}

