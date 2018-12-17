package android.support.v7.d;

public final class c {
    public static final c a;
    public static final c b;
    public static final c c;
    public static final c d;
    public static final c e;
    public static final c f;
    final float[] g;
    final float[] h;
    final float[] i;
    boolean j;

    static {
        c.a = new c();
        c.c(c.a);
        c.d(c.a);
        c.b = new c();
        c.b(c.b);
        c.d(c.b);
        c.c = new c();
        c.a(c.c);
        c.d(c.c);
        c.d = new c();
        c.c(c.d);
        c.e(c.d);
        c.e = new c();
        c.b(c.e);
        c.e(c.e);
        c.f = new c();
        c.a(c.f);
        c.e(c.f);
    }

    c() {
        super();
        this.g = new float[3];
        this.h = new float[3];
        this.i = new float[3];
        this.j = true;
        c.a(this.g);
        c.a(this.h);
        this.l();
    }

    public float a() {
        return this.g[0];
    }

    private static void a(c arg3) {
        arg3.h[1] = 0.26f;
        arg3.h[2] = 0.45f;
    }

    private static void a(float[] arg2) {
        arg2[0] = 0f;
        arg2[1] = 0.5f;
        arg2[2] = 1f;
    }

    private static void b(c arg3) {
        arg3.h[0] = 0.3f;
        arg3.h[1] = 0.5f;
        arg3.h[2] = 0.7f;
    }

    public float b() {
        return this.g[1];
    }

    public float c() {
        return this.g[2];
    }

    private static void c(c arg3) {
        arg3.h[0] = 0.55f;
        arg3.h[1] = 0.74f;
    }

    public float d() {
        return this.h[0];
    }

    private static void d(c arg3) {
        arg3.g[0] = 0.35f;
        arg3.g[1] = 1f;
    }

    private static void e(c arg3) {
        arg3.g[1] = 0.3f;
        arg3.g[2] = 0.4f;
    }

    public float e() {
        return this.h[1];
    }

    public float f() {
        return this.h[2];
    }

    public float g() {
        return this.i[0];
    }

    public float h() {
        return this.i[1];
    }

    public float i() {
        return this.i[2];
    }

    public boolean j() {
        return this.j;
    }

    void k() {
        int v0 = this.i.length;
        int v1 = 0;
        int v3 = 0;
        float v4 = 0f;
        while(v3 < v0) {
            float v5 = this.i[v3];
            if(v5 > 0f) {
                v4 += v5;
            }

            ++v3;
        }

        if(v4 != 0f) {
            v0 = this.i.length;
            while(v1 < v0) {
                if(this.i[v1] > 0f) {
                    this.i[v1] /= v4;
                }

                ++v1;
            }
        }
    }

    private void l() {
        this.i[0] = 0.24f;
        this.i[1] = 0.52f;
        this.i[2] = 0.24f;
    }
}

