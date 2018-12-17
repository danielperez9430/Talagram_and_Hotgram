package com.persianswitch.b;

import java.io.Serializable;
import java.util.Arrays;

public class f implements Serializable, Comparable {
    static final char[] a;
    public static final f b;
    final byte[] c;
    transient int d;
    transient String e;

    static {
        f.a = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        f.b = f.a(new byte[0]);
    }

    f(byte[] arg1) {
        super();
        this.c = arg1;
    }

    public static f a(String arg2) {
        if(arg2 != null) {
            f v0 = new f(arg2.getBytes(u.a));
            v0.e = arg2;
            return v0;
        }

        throw new IllegalArgumentException("s == null");
    }

    public String a() {
        String v0 = this.e;
        if(v0 != null) {
        }
        else {
            v0 = new String(this.c, u.a);
            this.e = v0;
        }

        return v0;
    }

    public byte a(int arg2) {
        return this.c[arg2];
    }

    public static f a(byte[] arg1) {
        // Method was not decompiled
    }

    static int a(String arg5, int arg6) {
        int v0 = arg5.length();
        int v1 = 0;
        int v2 = 0;
        while(v1 < v0) {
            if(v2 == arg6) {
                return v1;
            }

            int v3 = arg5.codePointAt(v1);
            if((Character.isISOControl(v3)) && v3 != 10 && v3 != 13 || v3 == 65533) {
                return -1;
            }

            ++v2;
            v1 += Character.charCount(v3);
        }

        return arg5.length();
    }

    public int a(f arg10) {
        int v5;
        int v0 = this.e();
        int v1 = arg10.e();
        int v2 = Math.min(v0, v1);
        int v4;
        for(v4 = 0; true; ++v4) {
            v5 = -1;
            if(v4 >= v2) {
                goto label_19;
            }

            int v7 = this.a(v4) & 255;
            int v8 = arg10.a(v4) & 255;
            if(v7 != v8) {
                break;
            }
        }

        if(v7 < v8) {
        }
        else {
            v5 = 1;
        }

        return v5;
    label_19:
        if(v0 == v1) {
            return 0;
        }

        if(v0 < v1) {
        }
        else {
            v5 = 1;
        }

        return v5;
    }

    public f a(int arg4, int arg5) {
        if(arg4 >= 0) {
            if(arg5 <= this.c.length) {
                int v0 = arg5 - arg4;
                if(v0 >= 0) {
                    if(arg4 == 0 && arg5 == this.c.length) {
                        return this;
                    }

                    byte[] v5 = new byte[v0];
                    System.arraycopy(this.c, arg4, v5, 0, v0);
                    return new f(v5);
                }

                throw new IllegalArgumentException("endIndex < beginIndex");
            }

            StringBuilder v5_1 = new StringBuilder();
            v5_1.append("endIndex > length(");
            v5_1.append(this.c.length);
            v5_1.append(")");
            throw new IllegalArgumentException(v5_1.toString());
        }

        throw new IllegalArgumentException("beginIndex < 0");
    }

    void a(c arg4) {
        arg4.b(this.c, 0, this.c.length);
    }

    public boolean a(int arg2, f arg3, int arg4, int arg5) {
        return arg3.a(arg4, this.c, arg2, arg5);
    }

    public boolean a(int arg2, byte[] arg3, int arg4, int arg5) {
        boolean v2 = arg2 < 0 || arg2 > this.c.length - arg5 || arg4 < 0 || arg4 > arg3.length - arg5 || !u.a(this.c, arg2, arg3, arg4, arg5) ? false : true;
        return v2;
    }

    public String b() {
        return b.a(this.c);
    }

    public String c() {
        char[] v0 = new char[this.c.length * 2];
        byte[] v1 = this.c;
        int v2 = v1.length;
        int v3 = 0;
        int v4 = 0;
        while(v3 < v2) {
            int v5 = v1[v3];
            int v6 = v4 + 1;
            v0[v4] = f.a[v5 >> 4 & 15];
            v4 = v6 + 1;
            v0[v6] = f.a[v5 & 15];
            ++v3;
        }

        return new String(v0);
    }

    public int compareTo(Object arg1) {
        return this.a(((f)arg1));
    }

    public f d() {
        // Method was not decompiled
    }

    public int e() {
        return this.c.length;
    }

    public boolean equals(Object arg5) {
        boolean v0 = true;
        if((((f)arg5)) == this) {
            return 1;
        }

        if(!(arg5 instanceof f) || ((f)arg5).e() != this.c.length || !((f)arg5).a(0, this.c, 0, this.c.length)) {
            v0 = false;
        }
        else {
        }

        return v0;
    }

    public byte[] f() {
        // Method was not decompiled
    }

    public int hashCode() {
        int v0 = this.d;
        if(v0 != 0) {
        }
        else {
            v0 = Arrays.hashCode(this.c);
            this.d = v0;
        }

        return v0;
    }

    public String toString() {
        String v1_1;
        StringBuilder v0_1;
        if(this.c.length == 0) {
            return "[size=0]";
        }

        String v0 = this.a();
        int v1 = 64;
        int v2 = f.a(v0, v1);
        if(v2 == -1) {
            if(this.c.length <= v1) {
                v0_1 = new StringBuilder();
                v0_1.append("[hex=");
                v0_1.append(this.c());
                v1_1 = "]";
            }
            else {
                v0_1 = new StringBuilder();
                v0_1.append("[size=");
                v0_1.append(this.c.length);
                v0_1.append(" hex=");
                v0_1.append(this.a(0, v1).c());
                v1_1 = "…]";
            }

            v0_1.append(v1_1);
            return v0_1.toString();
        }

        v1_1 = v0.substring(0, v2).replace("\\", "\\\\").replace("\n", "\\n").replace("\r", "\\r");
        if(v2 < v0.length()) {
            v0_1 = new StringBuilder();
            v0_1.append("[size=");
            v0_1.append(this.c.length);
            v0_1.append(" text=");
            v0_1.append(v1_1);
            v1_1 = "…]";
        }
        else {
            v0_1 = new StringBuilder();
            v0_1.append("[text=");
            v0_1.append(v1_1);
            v1_1 = "]";
        }

        v0_1.append(v1_1);
        return v0_1.toString();
    }
}

