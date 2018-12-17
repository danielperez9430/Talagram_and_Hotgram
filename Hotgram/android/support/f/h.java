package android.support.f;

import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PointF;
import android.util.Property;

class h extends Property {
    private final Property a;
    private final PathMeasure b;
    private final float c;
    private final float[] d;
    private final PointF e;
    private float f;

    h(Property arg3, Path arg4) {
        super(Float.class, arg3.getName());
        this.d = new float[2];
        this.e = new PointF();
        this.a = arg3;
        this.b = new PathMeasure(arg4, false);
        this.c = this.b.getLength();
    }

    public Float a(Object arg1) {
        return Float.valueOf(this.f);
    }

    public void a(Object arg4, Float arg5) {
        this.f = arg5.floatValue();
        this.b.getPosTan(this.c * arg5.floatValue(), this.d, null);
        this.e.x = this.d[0];
        this.e.y = this.d[1];
        this.a.set(arg4, this.e);
    }

    public Object get(Object arg1) {
        return this.a(arg1);
    }

    public void set(Object arg1, Object arg2) {
        this.a(arg1, ((Float)arg2));
    }
}

