package c.b.e.g;

public final class b {
    final float a;
    int b;
    int c;
    int d;
    Object[] e;

    public b() {
        this(16, 0.75f);
    }

    public b(int arg2, float arg3) {
        super();
        this.a = arg3;
        arg2 = c.a(arg2);
        this.b = arg2 - 1;
        this.d = ((int)(arg3 * (((float)arg2))));
        this.e = new Object[arg2];
    }

    static int a(int arg1) {
        arg1 *= -1640531527;
        return arg1 ^ arg1 >>> 16;
    }

    void a() {
        Object[] v0 = this.e;
        int v1 = v0.length;
        int v2 = v1 << 1;
        int v3 = v2 - 1;
        Object[] v4 = new Object[v2];
        int v5;
        for(v5 = this.c; true; v5 = v6) {
            int v6 = v5 - 1;
            if(v5 == 0) {
                break;
            }

            while(true) {
                --v1;
                if(v0[v1] != null) {
                    break;
                }
            }

            v5 = b.a(v0[v1].hashCode()) & v3;
            if(v4[v5] != null) {
                do {
                    v5 = v5 + 1 & v3;
                    if(v4[v5] != null) {
                        continue;
                    }

                    break;
                }
                while(true);
            }

            v4[v5] = v0[v1];
        }

        this.b = v3;
        this.d = ((int)((((float)v2)) * this.a));
        this.e = v4;
    }

    boolean a(int arg5, Object[] arg6, int arg7) {
        int v0;
        --this.c;
        while(true) {
        label_4:
            v0 = arg5 + 1;
            while(true) {
            label_5:
                v0 &= arg7;
                Object v2 = arg6[v0];
                if(v2 == null) {
                    goto label_8;
                }

                int v3 = b.a(v2.hashCode()) & arg7;
                if(arg5 <= v0) {
                    if(arg5 >= v3) {
                        goto label_20;
                    }
                    else if(v3 > v0) {
                        goto label_20;
                    }
                }
                else if(arg5 >= v3 && v3 > v0) {
                label_20:
                    arg6[arg5] = v2;
                    arg5 = v0;
                    goto label_4;
                }

                break;
            }
        }

        ++v0;
        goto label_5;
    label_8:
        arg6[arg5] = null;
        return 1;
    }

    public boolean a(Object arg7) {
        Object[] v0 = this.e;
        int v1 = this.b;
        int v2 = b.a(arg7.hashCode()) & v1;
        Object v3 = v0[v2];
        if(v3 != null) {
            if(v3.equals(arg7)) {
                return 0;
            }

            do {
                v2 = v2 + 1 & v1;
                v3 = v0[v2];
                if(v3 == null) {
                    goto label_16;
                }
            }
            while(!v3.equals(arg7));

            return 0;
        label_16:
        }

        v0[v2] = arg7;
        int v7 = this.c + 1;
        this.c = v7;
        if(v7 >= this.d) {
            this.a();
        }

        return 1;
    }

    public boolean b(Object arg6) {
        Object[] v0 = this.e;
        int v1 = this.b;
        int v2 = b.a(arg6.hashCode()) & v1;
        Object v3 = v0[v2];
        if(v3 == null) {
            return 0;
        }

        if(v3.equals(arg6)) {
            return this.a(v2, v0, v1);
        }

        do {
            v2 = v2 + 1 & v1;
            v3 = v0[v2];
            if(v3 == null) {
                return 0;
            }
        }
        while(!v3.equals(arg6));

        return this.a(v2, v0, v1);
    }

    public Object[] b() {
        return this.e;
    }
}

