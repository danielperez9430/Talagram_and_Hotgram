package android.support.design.g;

import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.RectF;
import java.util.List;

public class d {
    public class a extends c {
        public float a;
        public float b;
        public float c;
        public float d;
        public float e;
        public float f;
        private static final RectF h;

        static {
            a.h = new RectF();
        }

        public a(float arg1, float arg2, float arg3, float arg4) {
            super();
            this.a = arg1;
            this.b = arg2;
            this.c = arg3;
            this.d = arg4;
        }

        public void a(Matrix arg6, Path arg7) {
            Matrix v0 = this.g;
            arg6.invert(v0);
            arg7.transform(v0);
            a.h.set(this.a, this.b, this.c, this.d);
            arg7.arcTo(a.h, this.e, this.f, false);
            arg7.transform(arg6);
        }
    }

    public class b extends c {
        private float a;
        private float b;

        public b() {
            super();
        }

        static float a(b arg0, float arg1) {
            arg0.a = arg1;
            return arg1;
        }

        public void a(Matrix arg3, Path arg4) {
            Matrix v0 = this.g;
            arg3.invert(v0);
            arg4.transform(v0);
            arg4.lineTo(this.a, this.b);
            arg4.transform(arg3);
        }

        static float b(b arg0, float arg1) {
            arg0.b = arg1;
            return arg1;
        }
    }

    public abstract class c {
        protected final Matrix g;

        public c() {
            super();
            this.g = new Matrix();
        }

        public abstract void a(Matrix arg1, Path arg2);
    }

    public float a;
    public float b;
    public float c;
    public float d;
    private final List e;

    public void a(float arg1, float arg2) {
        this.a = arg1;
        this.b = arg2;
        this.c = arg1;
        this.d = arg2;
        this.e.clear();
    }

    public void a(float arg5, float arg6, float arg7, float arg8, float arg9, float arg10) {
        a v0 = new a(arg5, arg6, arg7, arg8);
        v0.e = arg9;
        v0.f = arg10;
        this.e.add(v0);
        double v9 = ((double)(arg9 + arg10));
        this.c = (arg5 + arg7) * 0.5f + (arg7 - arg5) / 2f * (((float)Math.cos(Math.toRadians(v9))));
        this.d = (arg6 + arg8) * 0.5f + (arg8 - arg6) / 2f * (((float)Math.sin(Math.toRadians(v9))));
    }

    public void a(Matrix arg4, Path arg5) {
        int v0 = this.e.size();
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            this.e.get(v1).a(arg4, arg5);
        }
    }

    public void b(float arg3, float arg4) {
        b v0 = new b();
        b.a(v0, arg3);
        b.b(v0, arg4);
        this.e.add(v0);
        this.c = arg3;
        this.d = arg4;
    }
}

