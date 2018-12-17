package android.support.design.a;

import android.graphics.Matrix;
import android.util.Property;
import android.widget.ImageView;

public class f extends Property {
    private final Matrix a;

    public f() {
        super(Matrix.class, "imageMatrixProperty");
        this.a = new Matrix();
    }

    public Matrix a(ImageView arg2) {
        this.a.set(arg2.getImageMatrix());
        return this.a;
    }

    public void a(ImageView arg1, Matrix arg2) {
        arg1.setImageMatrix(arg2);
    }

    public Object get(Object arg1) {
        return this.a(((ImageView)arg1));
    }

    public void set(Object arg1, Object arg2) {
        this.a(((ImageView)arg1), ((Matrix)arg2));
    }
}

