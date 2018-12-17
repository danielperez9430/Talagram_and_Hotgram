package okhttp3.internal.e;

import java.util.Arrays;

public final class m {
    private int a;
    private final int[] b;

    public m() {
        super();
        this.b = new int[10];
    }

    m a(int arg3, int arg4) {
        if(arg3 >= 0) {
            if(arg3 >= this.b.length) {
            }
            else {
                this.a |= 1 << arg3;
                this.b[arg3] = arg4;
            }
        }

        return this;
    }

    void a() {
        this.a = 0;
        Arrays.fill(this.b, 0);
    }

    void a(m arg3) {
        int v0;
        for(v0 = 0; v0 < 10; ++v0) {
            if(!arg3.a(v0)) {
            }
            else {
                this.a(v0, arg3.b(v0));
            }
        }
    }

    boolean a(int arg3) {
        boolean v0 = true;
        if((1 << arg3 & this.a) != 0) {
        }
        else {
            v0 = false;
        }

        return v0;
    }

    int b() {
        return Integer.bitCount(this.a);
    }

    int b(int arg2) {
        return this.b[arg2];
    }

    int c(int arg2) {
        if((this.a & 16) != 0) {
            arg2 = this.b[4];
        }

        return arg2;
    }

    int c() {
        int v0 = (this.a & 2) != 0 ? this.b[1] : -1;
        return v0;
    }

    int d() {
        int v0 = (this.a & 128) != 0 ? this.b[7] : 65535;
        return v0;
    }

    int d(int arg2) {
        if((this.a & 32) != 0) {
            arg2 = this.b[5];
        }

        return arg2;
    }
}

