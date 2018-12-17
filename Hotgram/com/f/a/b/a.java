package com.f.a.b;

import com.f.a.e.d;

public class a {
    public static final String[] a;
    public static final String[] b;
    public static final String[] c;
    public static final String[] d;
    public static final String[] e;
    public static final String[] f;
    private String g;
    private String h;
    private Integer i;
    private Integer j;
    private String k;
    private String l;
    private String m;
    private String n;
    private String o;
    private String p;
    private String q;
    private String r;
    private String s;
    private String t;
    private String u;
    private String v;
    private String w;

    static {
        a.a = new String[]{"34", "37"};
        a.b = new String[]{"60", "62", "64", "65"};
        a.c = new String[]{"35"};
        a.d = new String[]{"300", "301", "302", "303", "304", "305", "309", "36", "38", "39"};
        a.e = new String[]{"4"};
        a.f = new String[]{"2221", "2222", "2223", "2224", "2225", "2226", "2227", "2228", "2229", "223", "224", "225", "226", "227", "228", "229", "23", "24", "25", "26", "270", "271", "2720", "50", "51", "52", "53", "54", "55"};
    }

    public a(String arg19, Integer arg20, Integer arg21, String arg22, String arg23, String arg24, String arg25, String arg26, String arg27, String arg28, String arg29, String arg30) {
        this(arg19, arg20, arg21, arg22, arg23, arg24, arg25, arg26, arg27, arg28, arg29, null, null, null, null, null, arg30);
    }

    public a(String arg3, Integer arg4, Integer arg5, String arg6, String arg7, String arg8, String arg9, String arg10, String arg11, String arg12, String arg13, String arg14, String arg15, String arg16, String arg17, String arg18, String arg19) {
        a v0 = this;
        super();
        v0.g = d.b(this.b(arg3));
        v0.i = arg4;
        v0.j = arg5;
        v0.h = d.b(arg6);
        v0.k = d.b(arg7);
        v0.l = d.b(arg8);
        v0.m = d.b(arg9);
        v0.n = d.b(arg10);
        v0.o = d.b(arg11);
        v0.p = d.b(arg12);
        v0.q = d.b(arg13);
        String v1 = d.d(arg14) == null ? this.t() : arg14;
        v0.s = v1;
        v1 = d.b(arg15) == null ? this.r() : arg15;
        v0.r = v1;
        v0.u = d.b(arg16);
        v0.t = d.e(arg17);
        v0.v = d.b(arg18);
        v0.w = d.b(arg19);
    }

    private boolean a(String arg9) {
        boolean v1 = true;
        int v0 = arg9.length() - 1;
        int v3 = 0;
        int v4 = 1;
        while(v0 >= 0) {
            char v5 = arg9.charAt(v0);
            if(!Character.isDigit(v5)) {
                return 0;
            }

            StringBuilder v6 = new StringBuilder();
            v6.append("");
            v6.append(v5);
            int v5_1 = Integer.parseInt(v6.toString());
            v4 ^= 1;
            if(v4 != 0) {
                v5_1 *= 2;
            }

            if(v5_1 > 9) {
                v5_1 += -9;
            }

            v3 += v5_1;
            --v0;
        }

        if(v3 % 10 == 0) {
        }
        else {
            v1 = false;
        }

        return v1;
    }

    public boolean a() {
        boolean v1 = false;
        if(d.c(this.g)) {
            return 0;
        }

        String v0 = this.g.trim().replaceAll("\\s+|-", "");
        if(!d.c(v0) && (d.a(v0))) {
            if(!this.a(v0)) {
            }
            else {
                String v2 = this.t();
                if("American Express".equals(v2)) {
                    if(v0.length() == 15) {
                        v1 = true;
                    }

                    return v1;
                }
                else {
                    if("Diners Club".equals(v2)) {
                        if(v0.length() == 14) {
                            v1 = true;
                        }

                        return v1;
                    }

                    if(v0.length() != 16) {
                        return v1;
                    }

                    v1 = true;
                }
            }
        }

        return v1;
    }

    private String b(String arg3) {
        if(arg3 == null) {
            return null;
        }

        return arg3.trim().replaceAll("\\s+|-", "");
    }

    public boolean b() {
        if(!this.d()) {
            return 0;
        }

        if(!this.e()) {
            return 0;
        }

        return com.f.a.e.a.a(this.j.intValue(), this.i.intValue()) ^ 1;
    }

    public boolean c() {
        int v2_1;
        boolean v1 = false;
        if(d.c(this.h)) {
            return 0;
        }

        String v0 = this.h.trim();
        String v2 = this.t();
        int v4 = 4;
        int v5 = 3;
        if(v2 != null || v0.length() < v5 || v0.length() > v4) {
            if(("American Express".equals(v2)) && v0.length() == v4) {
                goto label_23;
            }

            if(v0.length() == v5) {
            label_23:
                v2_1 = 1;
                goto label_26;
            }

            v2_1 = 0;
        }
        else {
            goto label_23;
        }

    label_26:
        if((d.a(v0)) && v2_1 != 0) {
            v1 = true;
        }

        return v1;
    }

    public boolean d() {
        boolean v1 = true;
        if(this.i == null || this.i.intValue() < 1 || this.i.intValue() > 12) {
            v1 = false;
        }
        else {
        }

        return v1;
    }

    public boolean e() {
        boolean v0 = this.j == null || (com.f.a.e.a.a(this.j.intValue())) ? false : true;
        return v0;
    }

    public String f() {
        return this.g;
    }

    public String g() {
        return this.h;
    }

    public Integer h() {
        return this.i;
    }

    public Integer i() {
        return this.j;
    }

    public String j() {
        return this.k;
    }

    public String k() {
        return this.l;
    }

    public String l() {
        return this.m;
    }

    public String m() {
        return this.n;
    }

    public String n() {
        return this.p;
    }

    public String o() {
        return this.o;
    }

    public String p() {
        return this.q;
    }

    public String q() {
        return this.w;
    }

    public String r() {
        if(!d.c(this.r)) {
            return this.r;
        }

        if(this.g != null) {
            int v1 = 4;
            if(this.g.length() > v1) {
                this.r = this.g.substring(this.g.length() - v1, this.g.length());
                return this.r;
            }
        }

        return null;
    }

    @Deprecated public String s() {
        return this.t();
    }

    public String t() {
        String v0;
        if((d.c(this.s)) && !d.c(this.g)) {
            if(d.a(this.g, a.a)) {
                v0 = "American Express";
            }
            else if(d.a(this.g, a.b)) {
                v0 = "Discover";
            }
            else if(d.a(this.g, a.c)) {
                v0 = "JCB";
            }
            else if(d.a(this.g, a.d)) {
                v0 = "Diners Club";
            }
            else if(d.a(this.g, a.e)) {
                v0 = "Visa";
            }
            else if(d.a(this.g, a.f)) {
                v0 = "MasterCard";
            }
            else {
                v0 = "Unknown";
            }

            this.s = v0;
        }

        return this.s;
    }
}

