package android.support.v8.renderscript;

import android.util.SparseArray;

public class i extends b {
    private boolean a;
    private final SparseArray b;
    private final SparseArray c;
    private final SparseArray d;

    i(long arg1, RenderScript arg3) {
        super(arg1, arg3);
        this.b = new SparseArray();
        this.c = new SparseArray();
        this.d = new SparseArray();
        this.a = false;
    }

    long a(a arg11) {
        long v0_1;
        if(arg11 != null) {
            l v0 = arg11.b();
            v0_1 = this.r.a(arg11.a(this.r), v0.a(this.r, v0.a().h(this.r)), v0.b() * v0.a().a());
            arg11.a(v0_1);
        }
        else {
            v0_1 = 0;
        }

        return v0_1;
    }

    public void a(int arg7, float arg8) {
        this.r.a(this.a(this.r), arg7, arg8, this.a);
    }

    protected void a(int arg26, a arg27, a arg28, d arg29) {
        i v0 = this;
        a v1 = arg27;
        a v2 = arg28;
        if(v1 == null) {
            if(v2 != null) {
            }
            else {
                throw new f("At least one of ain or aout is required to be non-null.");
            }
        }

        long v3 = 0;
        long v11 = v1 != null ? v1.a(v0.r) : v3;
        if(v2 != null) {
            v3 = v2.a(v0.r);
        }

        long v13 = v3;
        byte[] v3_1 = null;
        if(arg29 != null) {
            v3_1 = arg29.a();
        }

        byte[] v23 = v3_1;
        if(v0.a) {
            v0.r.a(v0.a(v0.r), arg26, v0.a(v1), v0.a(v2), v23, v0.a);
        }
        else {
            v0.r.a(v0.a(v0.r), arg26, v11, v13, v23, v0.a);
        }
    }

    public void a(int arg22, b arg23) {
        i v0 = this;
        b v1 = arg23;
        long v3 = 0;
        if(v0.a) {
            long v5 = v0.a(v1);
            RenderScript v7 = v0.r;
            long v8 = v0.a(v0.r);
            long v11 = v1 == null ? v3 : v5;
            v7.a(v8, arg22, v11, v0.a);
        }
        else {
            RenderScript v14 = v0.r;
            long v15 = v0.a(v0.r);
            if(v1 != null) {
                v3 = v1.a(v0.r);
            }

            long v18 = v3;
            v14.a(v15, arg22, v18, v0.a);
        }
    }

    protected void a(boolean arg1) {
        this.a = arg1;
    }
}

