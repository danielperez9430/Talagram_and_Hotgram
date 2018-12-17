package android.support.v4.graphics.drawable;

import android.content.res.ColorStateList;
import android.content.res.Resources$Theme;
import android.content.res.Resources;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff$Mode;
import android.graphics.drawable.Drawable$ConstantState;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer$DrawableContainerState;
import android.graphics.drawable.DrawableContainer;
import android.graphics.drawable.InsetDrawable;
import android.os.Build$VERSION;
import android.util.AttributeSet;
import android.util.Log;
import java.lang.reflect.Method;
import org.xmlpull.v1.XmlPullParser;

public final class a {
    private static Method a;
    private static boolean b;
    private static Method c;
    private static boolean d;

    public static void a(Drawable arg2, ColorStateList arg3) {
        if(Build$VERSION.SDK_INT >= 21) {
            arg2.setTintList(arg3);
        }
        else if((arg2 instanceof b)) {
            ((b)arg2).setTintList(arg3);
        }
    }

    public static void a(Drawable arg2, PorterDuff$Mode arg3) {
        if(Build$VERSION.SDK_INT >= 21) {
            arg2.setTintMode(arg3);
        }
        else if((arg2 instanceof b)) {
            ((b)arg2).setTintMode(arg3);
        }
    }

    public static void a(Drawable arg2, int arg3) {
        if(Build$VERSION.SDK_INT >= 21) {
            arg2.setTint(arg3);
        }
        else if((arg2 instanceof b)) {
            ((b)arg2).setTint(arg3);
        }
    }

    @Deprecated public static void a(Drawable arg0) {
        arg0.jumpToCurrentState();
    }

    public static void a(Drawable arg2, float arg3, float arg4) {
        if(Build$VERSION.SDK_INT >= 21) {
            arg2.setHotspot(arg3, arg4);
        }
    }

    public static void a(Drawable arg2, int arg3, int arg4, int arg5, int arg6) {
        if(Build$VERSION.SDK_INT >= 21) {
            arg2.setHotspotBounds(arg3, arg4, arg5, arg6);
        }
    }

    public static void a(Drawable arg2, Resources$Theme arg3) {
        if(Build$VERSION.SDK_INT >= 21) {
            arg2.applyTheme(arg3);
        }
    }

    public static void a(Drawable arg2, Resources arg3, XmlPullParser arg4, AttributeSet arg5, Resources$Theme arg6) {
        if(Build$VERSION.SDK_INT >= 21) {
            arg2.inflate(arg3, arg4, arg5, arg6);
        }
        else {
            arg2.inflate(arg3, arg4, arg5);
        }
    }

    public static void a(Drawable arg2, boolean arg3) {
        if(Build$VERSION.SDK_INT >= 19) {
            arg2.setAutoMirrored(arg3);
        }
    }

    public static boolean b(Drawable arg2) {
        if(Build$VERSION.SDK_INT >= 19) {
            return arg2.isAutoMirrored();
        }

        return 0;
    }

    public static boolean b(Drawable arg6, int arg7) {
        if(Build$VERSION.SDK_INT >= 23) {
            return arg6.setLayoutDirection(arg7);
        }

        if(Build$VERSION.SDK_INT >= 17) {
            if(!a.b) {
                try {
                    a.a = Drawable.class.getDeclaredMethod("setLayoutDirection", Integer.TYPE);
                    a.a.setAccessible(true);
                }
                catch(NoSuchMethodException v0) {
                    Log.i("DrawableCompat", "Failed to retrieve setLayoutDirection(int) method", ((Throwable)v0));
                }

                a.b = true;
            }

            if(a.a == null) {
                return 0;
            }

            try {
                a.a.invoke(arg6, Integer.valueOf(arg7));
                return 1;
            }
            catch(Exception v6) {
                Log.i("DrawableCompat", "Failed to invoke setLayoutDirection(int) via reflection", ((Throwable)v6));
                a.a = null;
            }
        }

        return 0;
    }

    public static int c(Drawable arg2) {
        if(Build$VERSION.SDK_INT >= 19) {
            return arg2.getAlpha();
        }

        return 0;
    }

    public static boolean d(Drawable arg2) {
        if(Build$VERSION.SDK_INT >= 21) {
            return arg2.canApplyTheme();
        }

        return 0;
    }

    public static ColorFilter e(Drawable arg2) {
        if(Build$VERSION.SDK_INT >= 21) {
            return arg2.getColorFilter();
        }

        return null;
    }

    public static void f(Drawable arg3) {
        if(Build$VERSION.SDK_INT >= 23 || Build$VERSION.SDK_INT < 21) {
            arg3.clearColorFilter();
        }
        else {
            arg3.clearColorFilter();
            if((arg3 instanceof InsetDrawable)) {
                arg3 = ((InsetDrawable)arg3).getDrawable();
            }
            else if((arg3 instanceof c)) {
                arg3 = ((c)arg3).a();
            }
            else {
                goto label_18;
            }

            a.f(arg3);
            return;
        label_18:
            if(!(arg3 instanceof DrawableContainer)) {
                return;
            }

            Drawable$ConstantState v3 = ((DrawableContainer)arg3).getConstantState();
            if(v3 == null) {
                return;
            }

            int v0 = 0;
            int v1 = ((DrawableContainer$DrawableContainerState)v3).getChildCount();
            while(v0 < v1) {
                Drawable v2 = ((DrawableContainer$DrawableContainerState)v3).getChild(v0);
                if(v2 != null) {
                    a.f(v2);
                }

                ++v0;
            }
        }
    }

    public static Drawable g(Drawable arg2) {
        if(Build$VERSION.SDK_INT >= 23) {
            return arg2;
        }

        if(Build$VERSION.SDK_INT >= 21) {
            if(!(arg2 instanceof b)) {
                return new e(arg2);
            }

            return arg2;
        }

        if(!(arg2 instanceof b)) {
            return new d(arg2);
        }

        return arg2;
    }

    public static int h(Drawable arg5) {
        if(Build$VERSION.SDK_INT >= 23) {
            return arg5.getLayoutDirection();
        }

        if(Build$VERSION.SDK_INT >= 17) {
            if(!a.d) {
                try {
                    a.c = Drawable.class.getDeclaredMethod("getLayoutDirection");
                    a.c.setAccessible(true);
                }
                catch(NoSuchMethodException v1) {
                    Log.i("DrawableCompat", "Failed to retrieve getLayoutDirection() method", ((Throwable)v1));
                }

                a.d = true;
            }

            if(a.c == null) {
                return 0;
            }

            try {
                return a.c.invoke(arg5).intValue();
            }
            catch(Exception v5) {
                Log.i("DrawableCompat", "Failed to invoke getLayoutDirection() via reflection", ((Throwable)v5));
                a.c = null;
            }
        }

        return 0;
    }
}

