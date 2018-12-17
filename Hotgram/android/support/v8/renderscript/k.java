package android.support.v8.renderscript;

import android.os.Build$VERSION;

public class k extends j {
    private final float[] a;
    private a b;

    protected k(long arg1, RenderScript arg3) {
        super(arg1, arg3);
        this.a = new float[9];
    }

    public static k a(RenderScript arg4, c arg5) {
        if(!arg5.a(c.g(arg4))) {
            if(arg5.a(c.b(arg4))) {
            }
            else {
                throw new f("Unsupported element type.");
            }
        }

        boolean v0 = !arg4.a() || Build$VERSION.SDK_INT >= 19 ? false : true;
        k v5 = new k(arg4.a(5, arg5.a(arg4), v0), arg4);
        v5.a(v0);
        v5.a(5f);
        return v5;
    }

    public void a(float arg2) {
        if(arg2 > 0f && arg2 <= 25f) {
            this.a(0, arg2);
            return;
        }

        throw new f("Radius out of range (0 < r <= 25).");
    }

    public void b(a arg2) {
        if(arg2.b().e() != 0) {
            this.b = arg2;
            this.a(1, ((b)arg2));
            return;
        }

        throw new f("Input set to a 1D Allocation");
    }

    public void c(a arg4) {
        if(arg4.b().e() != 0) {
            this.a(0, null, arg4, null);
            return;
        }

        throw new f("Output is a 1D Allocation");
    }
}

