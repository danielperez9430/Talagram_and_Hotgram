package android.support.f;

import android.graphics.Matrix;
import android.graphics.Rect;
import android.os.Build$VERSION;
import android.support.v4.view.t;
import android.util.Log;
import android.util.Property;
import android.view.View;
import java.lang.reflect.Field;

class ad {
    final class android.support.f.ad$1 extends Property {
        android.support.f.ad$1(Class arg1, String arg2) {
            super(arg1, arg2);
        }

        public Float a(View arg1) {
            return Float.valueOf(ad.c(arg1));
        }

        public void a(View arg1, Float arg2) {
            ad.a(arg1, arg2.floatValue());
        }

        public Object get(Object arg1) {
            return this.a(((View)arg1));
        }

        public void set(Object arg1, Object arg2) {
            this.a(((View)arg1), ((Float)arg2));
        }
    }

    final class android.support.f.ad$2 extends Property {
        android.support.f.ad$2(Class arg1, String arg2) {
            super(arg1, arg2);
        }

        public Rect a(View arg1) {
            return t.C(arg1);
        }

        public void a(View arg1, Rect arg2) {
            t.a(arg1, arg2);
        }

        public Object get(Object arg1) {
            return this.a(((View)arg1));
        }

        public void set(Object arg1, Object arg2) {
            this.a(((View)arg1), ((Rect)arg2));
        }
    }

    static final Property a;
    static final Property b;
    private static final ah c;
    private static Field d;
    private static boolean e;

    static {
        ae v0_2;
        if(Build$VERSION.SDK_INT >= 22) {
            ag v0 = new ag();
        }
        else if(Build$VERSION.SDK_INT >= 21) {
            af v0_1 = new af();
        }
        else if(Build$VERSION.SDK_INT >= 19) {
            v0_2 = new ae();
        }
        else {
            ah v0_3 = new ah();
        }

        ad.c = ((ah)v0_2);
        ad.a = new android.support.f.ad$1(Float.class, "translationAlpha");
        ad.b = new android.support.f.ad$2(Rect.class, "clipBounds");
    }

    static ac a(View arg2) {
        if(Build$VERSION.SDK_INT >= 18) {
            return new ab(arg2);
        }

        return aa.d(arg2);
    }

    private static void a() {
        if(!ad.e) {
            try {
                ad.d = View.class.getDeclaredField("mViewFlags");
                ad.d.setAccessible(true);
            }
            catch(NoSuchFieldException ) {
                Log.i("ViewUtils", "fetchViewFlagsField: ");
            }

            ad.e = true;
        }
    }

    static void a(View arg1, float arg2) {
        ad.c.a(arg1, arg2);
    }

    static void a(View arg2, int arg3) {
        ad.a();
        if(ad.d != null) {
            try {
                ad.d.setInt(arg2, arg3 | ad.d.getInt(arg2) & -13);
                return;
            }
            catch(IllegalAccessException ) {
                return;
            }
        }
    }

    static void a(View arg6, int arg7, int arg8, int arg9, int arg10) {
        ad.c.a(arg6, arg7, arg8, arg9, arg10);
    }

    static void a(View arg1, Matrix arg2) {
        ad.c.a(arg1, arg2);
    }

    static al b(View arg2) {
        if(Build$VERSION.SDK_INT >= 18) {
            return new ak(arg2);
        }

        return new aj(arg2.getWindowToken());
    }

    static void b(View arg1, Matrix arg2) {
        ad.c.b(arg1, arg2);
    }

    static float c(View arg1) {
        return ad.c.a(arg1);
    }

    static void d(View arg1) {
        ad.c.b(arg1);
    }

    static void e(View arg1) {
        ad.c.c(arg1);
    }
}

