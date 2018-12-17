package com.persianswitch.sdk.base.utils.pdate;

import java.util.Locale;

public class Roozh {
    private int a;
    private int b;
    private int c;
    private int d;
    private int e;
    private int f;
    private int g;
    private int h;
    private int i;
    private int j;
    private int k;

    public Roozh() {
        super();
    }

    public void a(int arg2, int arg3, int arg4) {
        this.a(this.a(arg2, arg3, arg4, 0));
        this.c = this.d;
        this.b = this.e;
        this.a = this.f;
    }

    public int a() {
        return this.a;
    }

    private int a(int arg5, int arg6, int arg7, int arg8) {
        int v1 = (arg6 - 14) / 12;
        int v0 = (arg5 + 4800 + v1) * 1461 / 4 + (arg6 - 2 - v1 * 12) * 367 / 12 - (arg5 + 4900 + v1) / 100 * 3 / 4 + arg7 - 32075;
        if(arg8 == 0) {
            v0 = v0 - (arg5 + 100100 + (arg6 - 8) / 6) / 100 * 3 / 4 + 752;
        }

        return v0;
    }

    private void a(int arg5) {
        this.a(arg5, 0);
        this.d = this.g - 621;
        this.b(this.d);
        arg5 -= this.a(this.g, 3, this.k, 0);
        if(arg5 < 0) {
            --this.d;
            arg5 += 179;
            if(this.j == 1) {
                ++arg5;
                goto label_32;
            }
            else {
            label_32:
                this.e = arg5 / 30 + 7;
                arg5 %= 30;
            }
        }
        else if(arg5 <= 185) {
            this.e = arg5 / 31 + 1;
            arg5 %= 31;
        }
        else {
            arg5 += -186;
            goto label_32;
        }

        this.f = arg5 + 1;
    }

    private void a(int arg2, int arg3) {
        arg2 *= 4;
        int v0 = 139361631 + arg2;
        if(arg3 == 0) {
            v0 = v0 + (arg2 + 183187720) / 146097 * 3 / 4 * 4 - 3908;
        }

        arg2 = v0 % 1461 / 4 * 5 + 308;
        this.i = arg2 % 153 / 5 + 1;
        this.h = arg2 / 153 % 12 + 1;
        this.g = v0 / 1461 - 100100 + (8 - this.h) / 6;
    }

    public int b() {
        return this.b;
    }

    private void b(int arg10) {
        this.k = 0;
        this.j = 0;
        int v1 = 20;
        int[] v2 = new int[]{-61, 9, 38, 199, 426, 686, 756, 818, 1111, 1181, 1210, 1635, 2060, 2097, 2192, 2262, 2324, 2394, 2456, 3178};
        this.g = arg10 + 621;
        int v4 = v2[0];
        int v0 = 1;
        int v5 = -14;
        while(v0 <= 19) {
            int v6 = v2[v0];
            int v7 = v6 - v4;
            int v8 = 4;
            if(arg10 < v6) {
                arg10 -= v4;
                v5 = v5 + arg10 / 33 * 8 + (arg10 % 33 + 3) / v8;
                if(v7 % 33 == v8 && v7 - arg10 == v8) {
                    ++v5;
                }

                this.k = v5 + v1 - (this.g / v8 - (this.g / 100 + 1) * 3 / v8 - 150);
                if(v7 - arg10 < 6) {
                    arg10 = arg10 - v7 + (v7 + v8) / 33 * 33;
                }

                this.j = ((arg10 + 1) % 33 - 1) % v8;
                if(this.j != -1) {
                    return;
                }

                this.j = v8;
            }
            else {
                v5 = v5 + v7 / 33 * 8 + v7 % 33 / v8;
                ++v0;
                v4 = v6;
                continue;
            }

            return;
        }
    }

    public int c() {
        return this.c;
    }

    public String toString() {
        return String.format(Locale.US, "%04d-%02d-%02d", Integer.valueOf(this.c()), Integer.valueOf(this.b()), Integer.valueOf(this.a()));
    }
}

