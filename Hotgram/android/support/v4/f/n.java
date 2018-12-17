package android.support.v4.f;

public class n implements Cloneable {
    private static final Object a;
    private boolean b;
    private int[] c;
    private Object[] d;
    private int e;

    static {
        n.a = new Object();
    }

    public n() {
        this(10);
    }

    public n(int arg3) {
        Object[] v3;
        super();
        this.b = false;
        if(arg3 == 0) {
            this.c = c.a;
            v3 = c.c;
        }
        else {
            arg3 = c.a(arg3);
            this.c = new int[arg3];
            v3 = new Object[arg3];
        }

        this.d = v3;
        this.e = 0;
    }

    public Object a(int arg2) {
        return this.a(arg2, null);
    }

    public n a() {
        // Method was not decompiled
    }

    public Object a(int arg3, Object arg4) {
        arg3 = c.a(this.c, this.e, arg3);
        if(arg3 >= 0) {
            if(this.d[arg3] == n.a) {
            }
            else {
                return this.d[arg3];
            }
        }

        return arg4;
    }

    public int b() {
        if(this.b) {
            this.d();
        }

        return this.e;
    }

    public void b(int arg7, Object arg8) {
        int v0 = c.a(this.c, this.e, arg7);
        if(v0 >= 0) {
            this.d[v0] = arg8;
        }
        else {
            v0 ^= -1;
            if(v0 < this.e && this.d[v0] == n.a) {
                this.c[v0] = arg7;
                this.d[v0] = arg8;
                return;
            }

            if((this.b) && this.e >= this.c.length) {
                this.d();
                v0 = c.a(this.c, this.e, arg7) ^ -1;
            }

            if(this.e >= this.c.length) {
                int v1 = c.a(this.e + 1);
                int[] v2 = new int[v1];
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
            this.d[v0] = arg8;
            ++this.e;
        }
    }

    public void b(int arg3) {
        arg3 = c.a(this.c, this.e, arg3);
        if(arg3 >= 0 && this.d[arg3] != n.a) {
            this.d[arg3] = n.a;
            this.b = true;
        }
    }

    public void c(int arg7, Object arg8) {
        if(this.e != 0 && arg7 <= this.c[this.e - 1]) {
            this.b(arg7, arg8);
            return;
        }

        if((this.b) && this.e >= this.c.length) {
            this.d();
        }

        int v0 = this.e;
        if(v0 >= this.c.length) {
            int v1 = c.a(v0 + 1);
            int[] v2 = new int[v1];
            Object[] v1_1 = new Object[v1];
            System.arraycopy(this.c, 0, v2, 0, this.c.length);
            System.arraycopy(this.d, 0, v1_1, 0, this.d.length);
            this.c = v2;
            this.d = v1_1;
        }

        this.c[v0] = arg7;
        this.d[v0] = arg8;
        this.e = v0 + 1;
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

    public void c(int arg1) {
        this.b(arg1);
    }

    public Object clone() {
        return this.a();
    }

    public int d(int arg2) {
        if(this.b) {
            this.d();
        }

        return this.c[arg2];
    }

    private void d() {
        int v0 = this.e;
        int[] v1 = this.c;
        Object[] v2 = this.d;
        int v4 = 0;
        int v5 = 0;
        while(v4 < v0) {
            Object v6 = v2[v4];
            if(v6 != n.a) {
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

    public Object e(int arg2) {
        if(this.b) {
            this.d();
        }

        return this.d[arg2];
    }

    public int f(int arg3) {
        if(this.b) {
            this.d();
        }

        return c.a(this.c, this.e, arg3);
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

            v0.append(this.d(v1));
            v0.append('=');
            Object v2 = this.e(v1);
            if((((n)v2)) != this) {
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

