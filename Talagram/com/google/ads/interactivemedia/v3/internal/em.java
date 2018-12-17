package com.google.ads.interactivemedia.v3.internal;

final class em {
    private static final long[] a;
    private final byte[] b;
    private int c;
    private int d;

    static {
        em.a = new long[]{128, 64, 32, 16, 8, 4, 2, 1};
    }

    public em() {
        super();
        this.b = new byte[8];
    }

    public static int a(int arg6) {
        int v0 = 0;
        while(true) {
            if(v0 >= em.a.length) {
                return -1;
            }
            else if((em.a[v0] & (((long)arg6))) != 0) {
                ++v0;
            }
            else {
                ++v0;
                continue;
            }

            return v0;
        }

        return -1;
    }

    public static long a(byte[] arg8, int arg9, boolean arg10) {
        long v2 = 255;
        long v0 = (((long)arg8[0])) & v2;
        if(arg10) {
            v0 &= em.a[arg9 - 1] ^ -1;
        }

        int v10;
        for(v10 = 1; v10 < arg9; ++v10) {
            v0 = v0 << 8 | (((long)arg8[v10])) & v2;
        }

        return v0;
    }

    public long a(cd arg4, boolean arg5, boolean arg6, int arg7) {
        if(this.c == 0) {
            if(!arg4.a(this.b, 0, 1, arg5)) {
                return -1;
            }
            else {
                this.d = em.a(this.b[0] & 255);
                if(this.d != -1) {
                    this.c = 1;
                }
                else {
                    throw new IllegalStateException("No valid varint length mask found");
                }
            }
        }

        if(this.d > arg7) {
            this.c = 0;
            return -2;
        }

        if(this.d != 1) {
            arg4.b(this.b, 1, this.d - 1);
        }

        this.c = 0;
        return em.a(this.b, this.d, arg6);
    }

    public void a() {
        this.c = 0;
        this.d = 0;
    }

    public int b() {
        return this.d;
    }
}

