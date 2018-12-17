package android.support.v7.d;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Rect;
import android.util.SparseBooleanArray;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public final class b {
    final class android.support.v7.d.b$1 implements android.support.v7.d.b$b {
        android.support.v7.d.b$1() {
            super();
        }

        private boolean a(float[] arg2) {
            boolean v2 = arg2[2] <= 0.05f ? true : false;
            return v2;
        }

        public boolean a(int arg1, float[] arg2) {
            boolean v1 = (this.b(arg2)) || (this.a(arg2)) || (this.c(arg2)) ? false : true;
            return v1;
        }

        private boolean b(float[] arg2) {
            boolean v2 = arg2[2] >= 0.95f ? true : false;
            return v2;
        }

        private boolean c(float[] arg5) {
            // Method was not decompiled
        }
    }

    public final class a {
        private final List a;
        private final Bitmap b;
        private final List c;
        private int d;
        private int e;
        private int f;
        private final List g;
        private Rect h;

        public a(Bitmap arg3) {
            super();
            this.c = new ArrayList();
            this.d = 16;
            this.e = 12544;
            this.f = -1;
            this.g = new ArrayList();
            if(arg3 != null && !arg3.isRecycled()) {
                this.g.add(b.a);
                this.b = arg3;
                this.a = null;
                this.c.add(c.a);
                this.c.add(c.b);
                this.c.add(c.c);
                this.c.add(c.d);
                this.c.add(c.e);
                this.c.add(c.f);
                return;
            }

            throw new IllegalArgumentException("Bitmap is not valid");
        }

        private int[] a(Bitmap arg11) {
            int v8 = arg11.getWidth();
            int v7 = arg11.getHeight();
            int[] v9 = new int[v8 * v7];
            arg11.getPixels(v9, 0, v8, 0, 0, v8, v7);
            if(this.h == null) {
                return v9;
            }

            int v11 = this.h.width();
            int v0 = this.h.height();
            int[] v1 = new int[v11 * v0];
            int v2;
            for(v2 = 0; v2 < v0; ++v2) {
                System.arraycopy(v9, (this.h.top + v2) * v8 + this.h.left, v1, v2 * v11, v11);
            }

            return v1;
        }

        public b a() {
            List v0_1;
            Object[] v4_2;
            if(this.b != null) {
                Bitmap v0 = this.b(this.b);
                Rect v1 = this.h;
                if(v0 != this.b && v1 != null) {
                    double v2 = ((double)v0.getWidth());
                    double v4 = ((double)this.b.getWidth());
                    Double.isNaN(v2);
                    Double.isNaN(v4);
                    v2 /= v4;
                    v4 = ((double)v1.left);
                    Double.isNaN(v4);
                    v1.left = ((int)Math.floor(v4 * v2));
                    v4 = ((double)v1.top);
                    Double.isNaN(v4);
                    v1.top = ((int)Math.floor(v4 * v2));
                    v4 = ((double)v1.right);
                    Double.isNaN(v4);
                    v1.right = Math.min(((int)Math.ceil(v4 * v2)), v0.getWidth());
                    v4 = ((double)v1.bottom);
                    Double.isNaN(v4);
                    v1.bottom = Math.min(((int)Math.ceil(v4 * v2)), v0.getHeight());
                }

                int[] v2_1 = this.a(v0);
                int v3 = this.d;
                if(this.g.isEmpty()) {
                    android.support.v7.d.b$b[] v4_1 = null;
                }
                else {
                    v4_2 = this.g.toArray(new android.support.v7.d.b$b[this.g.size()]);
                }

                android.support.v7.d.a v1_1 = new android.support.v7.d.a(v2_1, v3, ((android.support.v7.d.b$b[])v4_2));
                if(v0 != this.b) {
                    v0.recycle();
                }

                v0_1 = v1_1.a();
            }
            else {
                if(this.a == null) {
                    goto label_75;
                }

                v0_1 = this.a;
            }

            b v1_2 = new b(v0_1, this.c);
            v1_2.a();
            return v1_2;
        label_75:
            throw new AssertionError();
        }

        private Bitmap b(Bitmap arg6) {
            double v3;
            int v0;
            double v1 = -1;
            if(this.e > 0) {
                v0 = arg6.getWidth() * arg6.getHeight();
                if(v0 > this.e) {
                    v1 = ((double)this.e);
                    v3 = ((double)v0);
                    Double.isNaN(v1);
                    Double.isNaN(v3);
                    v1 = Math.sqrt(v1 / v3);
                }
            }
            else if(this.f > 0) {
                v0 = Math.max(arg6.getWidth(), arg6.getHeight());
                if(v0 > this.f) {
                    v1 = ((double)this.f);
                    v3 = ((double)v0);
                    Double.isNaN(v1);
                    Double.isNaN(v3);
                    v1 /= v3;
                }
            }

            if(v1 <= 0) {
                return arg6;
            }

            v3 = ((double)arg6.getWidth());
            Double.isNaN(v3);
            v0 = ((int)Math.ceil(v3 * v1));
            v3 = ((double)arg6.getHeight());
            Double.isNaN(v3);
            return Bitmap.createScaledBitmap(arg6, v0, ((int)Math.ceil(v3 * v1)), false);
        }
    }

    public interface android.support.v7.d.b$b {
        boolean a(int arg1, float[] arg2);
    }

    public final class android.support.v7.d.b$c {
        private final int a;
        private final int b;
        private final int c;
        private final int d;
        private final int e;
        private boolean f;
        private int g;
        private int h;
        private float[] i;

        public android.support.v7.d.b$c(int arg2, int arg3) {
            super();
            this.a = Color.red(arg2);
            this.b = Color.green(arg2);
            this.c = Color.blue(arg2);
            this.d = arg2;
            this.e = arg3;
        }

        public int a() {
            return this.d;
        }

        public float[] b() {
            if(this.i == null) {
                this.i = new float[3];
            }

            android.support.v4.graphics.a.a(this.a, this.b, this.c, this.i);
            return this.i;
        }

        public int c() {
            return this.e;
        }

        public int d() {
            this.f();
            return this.g;
        }

        public int e() {
            this.f();
            return this.h;
        }

        public boolean equals(Object arg5) {
            boolean v0 = true;
            if(this == (((android.support.v7.d.b$c)arg5))) {
                return 1;
            }

            if(arg5 != null) {
                if(this.getClass() != arg5.getClass()) {
                }
                else {
                    if(this.e != ((android.support.v7.d.b$c)arg5).e || this.d != ((android.support.v7.d.b$c)arg5).d) {
                        v0 = false;
                    }
                    else {
                    }

                    return v0;
                }
            }

            return 0;
        }

        private void f() {
            int v4_1;
            int v1_1;
            int v7;
            if(!this.f) {
                float v1 = 4.5f;
                int v2 = -1;
                int v0 = android.support.v4.graphics.a.a(v2, this.d, v1);
                float v4 = 3f;
                int v3 = android.support.v4.graphics.a.a(v2, this.d, v4);
                if(v0 == v2 || v3 == v2) {
                    v7 = -16777216;
                    v1_1 = android.support.v4.graphics.a.a(v7, this.d, v1);
                    v4_1 = android.support.v4.graphics.a.a(v7, this.d, v4);
                    if(v1_1 != v2 && v4_1 != v2) {
                        this.h = android.support.v4.graphics.a.c(v7, v1_1);
                        v0 = android.support.v4.graphics.a.c(v7, v4_1);
                        goto label_15;
                    }

                    goto label_29;
                }
                else {
                    this.h = android.support.v4.graphics.a.c(v2, v0);
                    v0 = android.support.v4.graphics.a.c(v2, v3);
                }

            label_15:
                this.g = v0;
                this.f = true;
                return;
            label_29:
                v0 = v0 != v2 ? android.support.v4.graphics.a.c(v2, v0) : android.support.v4.graphics.a.c(v7, v1_1);
                this.h = v0;
                v0 = v3 != v2 ? android.support.v4.graphics.a.c(v2, v3) : android.support.v4.graphics.a.c(v7, v4_1);
                this.g = v0;
                this.f = true;
            }
        }

        public int hashCode() {
            return this.d * 31 + this.e;
        }

        public String toString() {
            StringBuilder v0 = new StringBuilder(this.getClass().getSimpleName());
            v0.append(" [RGB: #");
            v0.append(Integer.toHexString(this.a()));
            v0.append(']');
            v0.append(" [HSL: ");
            v0.append(Arrays.toString(this.b()));
            v0.append(']');
            v0.append(" [Population: ");
            v0.append(this.e);
            v0.append(']');
            v0.append(" [Title Text: #");
            v0.append(Integer.toHexString(this.d()));
            v0.append(']');
            v0.append(" [Body Text: #");
            v0.append(Integer.toHexString(this.e()));
            v0.append(']');
            return v0.toString();
        }
    }

    static final android.support.v7.d.b$b a;
    private final List b;
    private final List c;
    private final Map d;
    private final SparseBooleanArray e;
    private final android.support.v7.d.b$c f;

    static {
        b.a = new android.support.v7.d.b$1();
    }

    b(List arg1, List arg2) {
        super();
        this.b = arg1;
        this.c = arg2;
        this.e = new SparseBooleanArray();
        this.d = new android.support.v4.f.a();
        this.f = this.b();
    }

    public static a a(Bitmap arg1) {
        return new a(arg1);
    }

    private boolean a(android.support.v7.d.b$c arg6, c arg7) {
        float[] v0 = arg6.b();
        boolean v1 = true;
        if(v0[1] < arg7.a() || v0[1] > arg7.c()) {
        label_20:
            v1 = false;
        }
        else {
            int v2 = 2;
            if(v0[v2] < arg7.d()) {
                goto label_20;
            }
            else if(v0[v2] > arg7.f()) {
                goto label_20;
            }
            else if(!this.e.get(arg6.a())) {
            }
            else {
                goto label_20;
            }
        }

        return v1;
    }

    public int a(int arg2) {
        return this.a(c.f, arg2);
    }

    public int a(c arg1, int arg2) {
        android.support.v7.d.b$c v1 = this.a(arg1);
        if(v1 != null) {
            arg2 = v1.a();
        }

        return arg2;
    }

    public android.support.v7.d.b$c a(c arg2) {
        return this.d.get(arg2);
    }

    void a() {
        int v0 = this.c.size();
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            Object v2 = this.c.get(v1);
            ((c)v2).k();
            this.d.put(v2, this.b(((c)v2)));
        }

        this.e.clear();
    }

    private android.support.v7.d.b$c b() {
        int v0 = this.b.size();
        int v1 = -2147483648;
        android.support.v7.d.b$c v2 = null;
        int v3;
        for(v3 = 0; v3 < v0; ++v3) {
            Object v4 = this.b.get(v3);
            if(((android.support.v7.d.b$c)v4).c() > v1) {
                v1 = ((android.support.v7.d.b$c)v4).c();
                Object v2_1 = v4;
            }
        }

        return v2;
    }

    private float b(android.support.v7.d.b$c arg8, c arg9) {
        // Method was not decompiled
    }

    private android.support.v7.d.b$c b(c arg4) {
        android.support.v7.d.b$c v0 = this.c(arg4);
        if(v0 != null && (arg4.j())) {
            this.e.append(v0.a(), true);
        }

        return v0;
    }

    private android.support.v7.d.b$c c(c arg8) {
        int v0 = this.b.size();
        float v1 = 0f;
        Object v2 = null;
        int v3;
        for(v3 = 0; v3 < v0; ++v3) {
            Object v4 = this.b.get(v3);
            if(this.a(((android.support.v7.d.b$c)v4), arg8)) {
                float v5 = this.b(((android.support.v7.d.b$c)v4), arg8);
                if(v2 != null && v5 <= v1) {
                    goto label_15;
                }

                v2 = v4;
                v1 = v5;
            }

        label_15:
        }

        return ((android.support.v7.d.b$c)v2);
    }
}

