package android.support.design.a;

import android.graphics.drawable.Drawable;
import android.os.Build$VERSION;
import android.util.Property;
import java.util.WeakHashMap;

public class e extends Property {
    public static final Property a;
    private final WeakHashMap b;

    static {
        e.a = new e();
    }

    private e() {
        super(Integer.class, "drawableAlphaCompat");
        this.b = new WeakHashMap();
    }

    public Integer a(Drawable arg3) {
        if(Build$VERSION.SDK_INT >= 19) {
            return Integer.valueOf(arg3.getAlpha());
        }

        if(this.b.containsKey(arg3)) {
            return this.b.get(arg3);
        }

        return Integer.valueOf(255);
    }

    public void a(Drawable arg3, Integer arg4) {
        if(Build$VERSION.SDK_INT < 19) {
            this.b.put(arg3, arg4);
        }

        arg3.setAlpha(arg4.intValue());
    }

    public Object get(Object arg1) {
        return this.a(((Drawable)arg1));
    }

    public void set(Object arg1, Object arg2) {
        this.a(((Drawable)arg1), ((Integer)arg2));
    }
}

