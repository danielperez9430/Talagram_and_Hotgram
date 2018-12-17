package org.telegram.news.b;

import android.text.TextUtils;
import android.widget.TextView;
import com.google.a.a.c;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;

public class b {
    private ArrayList A;
    long a;
    String b;
    String c;
    long d;
    @c(a="irCreationDate") String e;
    String f;
    String g;
    int h;
    int i;
    ArrayList j;
    a[] k;
    a[] l;
    String m;
    public boolean n;
    int o;
    private String p;
    private String q;
    private String r;
    private int s;
    private String t;
    private String u;
    private boolean v;
    private String w;
    private int x;
    private ArrayList y;
    private d z;

    public b() {
        super();
        this.v = false;
        this.n = false;
    }

    public static void a(TextView arg6, b arg7) {
        if(!TextUtils.isEmpty(arg7.w())) {
            arg6.setText(arg7.w() + "");
            return;
        }

        try {
            arg6.setText(new org.ocpsoft.prettytime.c(new Locale("FA")).b(new Date(((long)(Double.valueOf(((double)arg7.j())).doubleValue() * 1000)))));
        }
        catch(Exception v6) {
            v6.printStackTrace();
        }
    }

    public String a() {
        return this.m;
    }

    public void a(int arg1) {
        this.o = arg1;
    }

    public void a(long arg1) {
        this.d = arg1;
    }

    public void a(String arg1) {
        this.m = arg1;
    }

    public void a(ArrayList arg1) {
        this.A = arg1;
    }

    public void a(d arg1) {
        this.z = arg1;
    }

    public void a(a[] arg1) {
        this.l = arg1;
    }

    public int b() {
        return this.o;
    }

    public void b(int arg1) {
        this.i = arg1;
    }

    public void b(long arg1) {
        this.a = arg1;
    }

    public void b(String arg1) {
        this.g = arg1;
    }

    public void b(ArrayList arg1) {
        this.j = arg1;
    }

    public void b(a[] arg1) {
        this.k = arg1;
    }

    public ArrayList c() {
        return this.A;
    }

    public void c(int arg1) {
        this.h = arg1;
    }

    public void c(String arg1) {
        this.b = arg1;
    }

    public void c(ArrayList arg1) {
        this.y = arg1;
    }

    public void d(int arg1) {
        this.s = arg1;
    }

    public void d(String arg1) {
        this.c = arg1;
    }

    public a[] d() {
        if(this.l == null) {
            this.l = new a[0];
        }

        return this.l;
    }

    public void e(int arg1) {
        this.x = arg1;
    }

    public void e(String arg1) {
        this.f = arg1;
    }

    public a[] e() {
        if(this.k == null) {
            this.k = new a[0];
        }

        return this.k;
    }

    public int f() {
        if(this.i == 0 && this.e().length > 0) {
            a[] v0 = this.e();
            int v1 = v0.length;
            int v2;
            for(v2 = 0; v2 < v1; ++v2) {
                if(v0[v2].a() == 3) {
                    ++this.i;
                }
            }
        }

        return this.i;
    }

    public void f(String arg1) {
        this.t = arg1;
    }

    public String g() {
        return this.g;
    }

    public void g(String arg1) {
        this.q = arg1;
    }

    public String h() {
        return this.b;
    }

    public void h(String arg1) {
        this.r = arg1;
    }

    public String i() {
        return this.c;
    }

    public void i(String arg1) {
        this.p = arg1;
    }

    public long j() {
        return this.d;
    }

    public void j(String arg1) {
        this.u = arg1;
    }

    public String k() {
        return this.f;
    }

    public void k(String arg1) {
        this.w = arg1;
    }

    public String l() {
        return this.t;
    }

    public ArrayList m() {
        if(this.j == null) {
            this.j = new ArrayList();
            if(this.j.size() == 0 && this.e().length > 0) {
                a[] v0 = this.e();
                int v1 = v0.length;
                int v2;
                for(v2 = 0; v2 < v1; ++v2) {
                    a v3 = v0[v2];
                    if(v3.a() == 2) {
                        Collections.addAll(this.j, v3.b());
                    }
                }
            }
        }

        return this.j;
    }

    public int n() {
        if(this.h == 0 && this.e().length > 0) {
            a[] v0 = this.e();
            int v1 = v0.length;
            int v2;
            for(v2 = 0; v2 < v1; ++v2) {
                a v3 = v0[v2];
                if(v3.a() == 2) {
                    this.h += v3.b().length;
                }
            }
        }

        return this.h;
    }

    public String o() {
        return this.q;
    }

    public String p() {
        return this.r;
    }

    public String q() {
        return this.p;
    }

    public ArrayList r() {
        if(this.y == null) {
            this.y = new ArrayList();
        }

        return this.y;
    }

    public String s() {
        return this.u;
    }

    public int t() {
        return this.s;
    }

    public d u() {
        return this.z;
    }

    public int v() {
        return this.x;
    }

    public String w() {
        return this.e;
    }
}

