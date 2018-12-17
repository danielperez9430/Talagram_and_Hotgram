package android.support.v4.f;

public class f implements Cloneable {
    private static final Object a;
    private boolean b;
    private long[] c;
    private Object[] d;
    private int e;

    static {
        f.a = new Object();
    }

    public f() {
        this(10);
    }

    public f(int arg3) {
        Object[] v3;
        super();
        this.b = false;
        if(arg3 == 0) {
            this.c = c.b;
            v3 = c.c;
        }
        else {
            arg3 = c.b(arg3);
            this.c = new long[arg3];
            v3 = new Object[arg3];
        }

        this.d = v3;
        this.e = 0;
    }

    public Object a(long arg2) {
        return this.a(arg2, null);
    }

    public f a() {
        // Method was not decompiled
    }

    public Object a(long arg3, Object arg5) {
        int v3 = c.a(this.c, this.e, arg3);
        if(v3 >= 0) {
            if(this.d[v3] == f.a) {
            }
            else {
                return this.d[v3];
            }
        }

        return arg5;
    }

    public void a(int arg3) {
        if(this.d[arg3] != f.a) {
            this.d[arg3] = f.a;
            this.b = true;
        }
    }

    public void b(long arg7, Object arg9) {
        int v0 = c.a(this.c, this.e, arg7);
        if(v0 >= 0) {
            this.d[v0] = arg9;
        }
        else {
            v0 ^= -1;
            if(v0 < this.e && this.d[v0] == f.a) {
                this.c[v0] = arg7;
                this.d[v0] = arg9;
                return;
            }

            if((this.b) && this.e >= this.c.length) {
                this.d();
                v0 = c.a(this.c, this.e, arg7) ^ -1;
            }

            if(this.e >= this.c.length) {
                int v1 = c.b(this.e + 1);
                long[] v2 = new long[v1];
                Object[] v1_1 = new Object[v1];
                System.arraycopy(this.c, 0, v2, 0, this.c.length);
                System.arraycopy(this.d, 0, v1_1, 0, this.d.length);
                this.c = v2;
                this.d = v1_1;
            }

            if(this.e - v0 != 0) {
                int v3 = v0 + 1;
                System.arraycopy(this.c, v0, this.c, v3, this.e - v0);
                System.arraycopy(this.d, v0, this.d, v3, this.e - v0);
            }

            this.c[v0] = arg7;
            this.d[v0] = arg9;
            ++this.e;
        }
    }

    public int b() {
        if(this.b) {
            this.d();
        }

        return this.e;
    }

    public long b(int arg4) {
        if(this.b) {
            this.d();
        }

        return this.c[arg4];
    }

    public void b(long arg3) {
        int v3 = c.a(this.c, this.e, arg3);
        if(v3 >= 0 && this.d[v3] != f.a) {
            this.d[v3] = f.a;
            this.b = true;
        }
    }

    public int c(long arg3) {
        if(this.b) {
            this.d();
        }

        return c.a(this.c, this.e, arg3);
    }

    public Object c(int arg2) {
        if(this.b) {
            this.d();
        }

        return this.d[arg2];
    }

    public void c() {
        int v0 = this.e;
        Object[] v1 = this.d;
        int v3;
        for(v3 = 0; v3 < v0; ++v3) {
            v1[v3] = null;
        }

        this.e = 0;
        this.b = false;
    }

    public void c(long arg7, Object arg9) {
        if(this.e != 0 && arg7 <= this.c[this.e - 1]) {
            this.b(arg7, arg9);
            return;
        }

        if((this.b) && this.e >= this.c.length) {
            this.d();
        }

        int v0 = this.e;
        if(v0 >= this.c.length) {
            int v1 = c.b(v0 + 1);
            long[] v2 = new long[v1];
            Object[] v1_1 = new Object[v1];
            System.arraycopy(this.c, 0, v2, 0, this.c.length);
            System.arraycopy(this.d, 0, v1_1, 0, this.d.length);
            this.c = v2;
            this.d = v1_1;
        }

        this.c[v0] = arg7;
        this.d[v0] = arg9;
        this.e = v0 + 1;
    }

    public Object clone() {
        return this.a();
    }

    private void d() {
        int v0 = this.e;
        long[] v1 = this.c;
        Object[] v2 = this.d;
        int v4 = 0;
        int v5 = 0;
        while(v4 < v0) {
            Object v6 = v2[v4];
            if(v6 != f.a) {
                if(v4 != v5) {
                    v1[v5] = v1[v4];
                    v2[v5] = v6;
                    v2[v4] = null;
                }

                ++v5;
            }

            ++v4;
        }

        this.b = false;
        this.e = v5;
    }

    public String toString() {
        if(this.b() <= 0) {
            return "{}";
        }

        StringBuilder v0 = new StringBuilder(this.e * 28);
        v0.append('{');
        int v1;
        for(v1 = 0; v1 < this.e; ++v1) {
            if(v1 > 0) {
                v0.append(", ");
            }

            v0.append(this.b(v1));
            v0.append('=');
            Object v2 = this.c(v1);
            if((((f)v2)) != this) {
                v0.append(v2);
            }
            else {
                v0.append("(this Map)");
            }
        }

        v0.append('}');
        return v0.toString();
    }
}

