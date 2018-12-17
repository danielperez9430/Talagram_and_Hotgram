package net.hockeyapp.android.c;

import java.io.Serializable;

public class b implements Serializable {
    private int a;
    private int b;
    private String c;
    private String d;
    private String e;
    private String f;

    public b() {
        super();
    }

    public String a() {
        return this.c;
    }

    public void a(int arg1) {
        this.a = arg1;
    }

    public void a(String arg1) {
        this.c = arg1;
    }

    public String b() {
        return this.d;
    }

    public void b(int arg1) {
        this.b = arg1;
    }

    public void b(String arg1) {
        this.d = arg1;
    }

    public String c() {
        return "" + this.b + this.a;
    }

    public void c(String arg1) {
        this.e = arg1;
    }

    public void d(String arg1) {
        this.f = arg1;
    }

    public String toString() {
        return "\n" + b.class.getSimpleName() + "\nid         " + this.a + "\nmessage id " + this.b + "\nfilename   " + this.c + "\nurl        " + this.d + "\ncreatedAt  " + this.e + "\nupdatedAt  " + this.f;
    }
}

