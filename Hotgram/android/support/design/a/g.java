package android.support.design.a;

import android.animation.TypeEvaluator;
import android.graphics.Matrix;

public class g implements TypeEvaluator {
    private final float[] a;
    private final float[] b;
    private final Matrix c;

    public g() {
        super();
        this.a = new float[9];
        this.b = new float[9];
        this.c = new Matrix();
    }

    public Matrix a(float arg3, Matrix arg4, Matrix arg5) {
        arg4.getValues(this.a);
        arg5.getValues(this.b);
        int v4;
        for(v4 = 0; v4 < 9; ++v4) {
            this.b[v4] = this.a[v4] + (this.b[v4] - this.a[v4]) * arg3;
        }

        this.c.setValues(this.b);
        return this.c;
    }

    public Object evaluate(float arg1, Object arg2, Object arg3) {
        return this.a(arg1, ((Matrix)arg2), ((Matrix)arg3));
    }
}

