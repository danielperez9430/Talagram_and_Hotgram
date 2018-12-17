package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v4.content.a.f;
import android.support.v7.c.a.a;
import android.util.AttributeSet;
import android.util.TypedValue;

public class bk {
    private final Context a;
    private final TypedArray b;
    private TypedValue c;

    private bk(Context arg1, TypedArray arg2) {
        super();
        this.a = arg1;
        this.b = arg2;
    }

    public static bk a(Context arg1, AttributeSet arg2, int[] arg3, int arg4, int arg5) {
        return new bk(arg1, arg1.obtainStyledAttributes(arg2, arg3, arg4, arg5));
    }

    public boolean a(int arg2, boolean arg3) {
        return this.b.getBoolean(arg2, arg3);
    }

    public int a(int arg2, int arg3) {
        return this.b.getInt(arg2, arg3);
    }

    public Drawable a(int arg3) {
        if(this.b.hasValue(arg3)) {
            int v0 = this.b.getResourceId(arg3, 0);
            if(v0 != 0) {
                return a.b(this.a, v0);
            }
        }

        return this.b.getDrawable(arg3);
    }

    public void a() {
        this.b.recycle();
    }

    public static bk a(Context arg1, int arg2, int[] arg3) {
        return new bk(arg1, arg1.obtainStyledAttributes(arg2, arg3));
    }

    public float a(int arg2, float arg3) {
        return this.b.getFloat(arg2, arg3);
    }

    public static bk a(Context arg1, AttributeSet arg2, int[] arg3) {
        return new bk(arg1, arg1.obtainStyledAttributes(arg2, arg3));
    }

    public Typeface a(int arg3, int arg4, android.support.v4.content.a.f$a arg5) {
        arg3 = this.b.getResourceId(arg3, 0);
        if(arg3 == 0) {
            return null;
        }

        if(this.c == null) {
            this.c = new TypedValue();
        }

        return f.a(this.a, arg3, this.c, arg4, arg5);
    }

    public float b(int arg2, float arg3) {
        return this.b.getDimension(arg2, arg3);
    }

    public int b(int arg2, int arg3) {
        return this.b.getColor(arg2, arg3);
    }

    public Drawable b(int arg4) {
        if(this.b.hasValue(arg4)) {
            arg4 = this.b.getResourceId(arg4, 0);
            if(arg4 != 0) {
                return k.a().a(this.a, arg4, true);
            }
        }

        return null;
    }

    public CharSequence c(int arg2) {
        return this.b.getText(arg2);
    }

    public int c(int arg2, int arg3) {
        return this.b.getInteger(arg2, arg3);
    }

    public int d(int arg2, int arg3) {
        return this.b.getDimensionPixelOffset(arg2, arg3);
    }

    public String d(int arg2) {
        return this.b.getString(arg2);
    }

    public ColorStateList e(int arg3) {
        if(this.b.hasValue(arg3)) {
            int v0 = this.b.getResourceId(arg3, 0);
            if(v0 != 0) {
                ColorStateList v0_1 = a.a(this.a, v0);
                if(v0_1 != null) {
                    return v0_1;
                }
            }
        }

        return this.b.getColorStateList(arg3);
    }

    public int e(int arg2, int arg3) {
        return this.b.getDimensionPixelSize(arg2, arg3);
    }

    public int f(int arg2, int arg3) {
        return this.b.getLayoutDimension(arg2, arg3);
    }

    public CharSequence[] f(int arg2) {
        return this.b.getTextArray(arg2);
    }

    public boolean g(int arg2) {
        return this.b.hasValue(arg2);
    }

    public int g(int arg2, int arg3) {
        return this.b.getResourceId(arg2, arg3);
    }
}

