package android.support.design.a;

import android.support.design.a$f;
import android.util.Property;
import android.view.ViewGroup;

public class d extends Property {
    public static final Property a;

    static {
        d.a = new d("childrenAlpha");
    }

    private d(String arg2) {
        super(Float.class, arg2);
    }

    public Float a(ViewGroup arg2) {
        Object v2 = arg2.getTag(f.mtrl_internal_children_alpha_tag);
        if(v2 != null) {
            return ((Float)v2);
        }

        return Float.valueOf(1f);
    }

    public void a(ViewGroup arg4, Float arg5) {
        float v5 = arg5.floatValue();
        arg4.setTag(f.mtrl_internal_children_alpha_tag, Float.valueOf(v5));
        int v0 = arg4.getChildCount();
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            arg4.getChildAt(v1).setAlpha(v5);
        }
    }

    public Object get(Object arg1) {
        return this.a(((ViewGroup)arg1));
    }

    public void set(Object arg1, Object arg2) {
        this.a(((ViewGroup)arg1), ((Float)arg2));
    }
}

