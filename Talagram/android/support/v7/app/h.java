package android.support.v7.app;

import android.content.res.Resources;
import android.os.Build$VERSION;
import android.util.Log;
import android.util.LongSparseArray;
import java.lang.reflect.Field;
import java.util.Map;

class h {
    private static Field a;
    private static boolean b;
    private static Class c;
    private static boolean d;
    private static Field e;
    private static boolean f;
    private static Field g;
    private static boolean h;

    static void a(Resources arg2) {
        if(Build$VERSION.SDK_INT >= 28) {
            return;
        }

        if(Build$VERSION.SDK_INT >= 24) {
            h.d(arg2);
        }
        else if(Build$VERSION.SDK_INT >= 23) {
            h.c(arg2);
        }
        else if(Build$VERSION.SDK_INT >= 21) {
            h.b(arg2);
        }
    }

    private static void a(Object arg4) {
        if(!h.d) {
            try {
                h.c = Class.forName("android.content.res.ThemedResourceCache");
            }
            catch(ClassNotFoundException v0) {
                Log.e("ResourcesFlusher", "Could not find ThemedResourceCache class", ((Throwable)v0));
            }

            h.d = true;
        }

        if(h.c == null) {
            return;
        }

        if(!h.f) {
            try {
                h.e = h.c.getDeclaredField("mUnthemedEntries");
                h.e.setAccessible(true);
            }
            catch(NoSuchFieldException v0_1) {
                Log.e("ResourcesFlusher", "Could not retrieve ThemedResourceCache#mUnthemedEntries field", ((Throwable)v0_1));
            }

            h.f = true;
        }

        if(h.e == null) {
            return;
        }

        Object v0_2 = null;
        try {
            arg4 = h.e.get(arg4);
        }
        catch(IllegalAccessException v4) {
            Log.e("ResourcesFlusher", "Could not retrieve value from ThemedResourceCache#mUnthemedEntries", ((Throwable)v4));
            arg4 = v0_2;
        }

        if(arg4 != null) {
            ((LongSparseArray)arg4).clear();
        }
    }

    private static void b(Resources arg4) {
        Object v4_1;
        if(!h.b) {
            try {
                h.a = Resources.class.getDeclaredField("mDrawableCache");
                h.a.setAccessible(true);
            }
            catch(NoSuchFieldException v1) {
                Log.e("ResourcesFlusher", "Could not retrieve Resources#mDrawableCache field", ((Throwable)v1));
            }

            h.b = true;
        }

        if(h.a != null) {
            Object v0 = null;
            try {
                v4_1 = h.a.get(arg4);
            }
            catch(IllegalAccessException v4) {
                Log.e("ResourcesFlusher", "Could not retrieve value from Resources#mDrawableCache", ((Throwable)v4));
                v4_1 = v0;
            }

            if(v4_1 == null) {
                return;
            }

            ((Map)v4_1).clear();
        }
    }

    private static void c(Resources arg4) {
        Object v4_1;
        if(!h.b) {
            try {
                h.a = Resources.class.getDeclaredField("mDrawableCache");
                h.a.setAccessible(true);
            }
            catch(NoSuchFieldException v1) {
                Log.e("ResourcesFlusher", "Could not retrieve Resources#mDrawableCache field", ((Throwable)v1));
            }

            h.b = true;
        }

        Object v0 = null;
        if(h.a != null) {
            try {
                v4_1 = h.a.get(arg4);
                goto label_26;
            }
            catch(IllegalAccessException v4) {
                Log.e("ResourcesFlusher", "Could not retrieve value from Resources#mDrawableCache", ((Throwable)v4));
            }
        }

        v4_1 = v0;
    label_26:
        if(v4_1 == null) {
            return;
        }

        h.a(v4_1);
    }

    private static void d(Resources arg5) {
        Object v5_1;
        if(!h.h) {
            try {
                h.g = Resources.class.getDeclaredField("mResourcesImpl");
                h.g.setAccessible(true);
            }
            catch(NoSuchFieldException v0) {
                Log.e("ResourcesFlusher", "Could not retrieve Resources#mResourcesImpl field", ((Throwable)v0));
            }

            h.h = true;
        }

        if(h.g == null) {
            return;
        }

        Object v0_1 = null;
        try {
            v5_1 = h.g.get(arg5);
        }
        catch(IllegalAccessException v5) {
            Log.e("ResourcesFlusher", "Could not retrieve value from Resources#mResourcesImpl", ((Throwable)v5));
            v5_1 = v0_1;
        }

        if(v5_1 == null) {
            return;
        }

        if(!h.b) {
            try {
                h.a = v5_1.getClass().getDeclaredField("mDrawableCache");
                h.a.setAccessible(true);
            }
            catch(NoSuchFieldException v2) {
                Log.e("ResourcesFlusher", "Could not retrieve ResourcesImpl#mDrawableCache field", ((Throwable)v2));
            }

            h.b = true;
        }

        if(h.a != null) {
            try {
                v5_1 = h.a.get(v5_1);
                goto label_53;
            }
            catch(IllegalAccessException v5) {
                Log.e("ResourcesFlusher", "Could not retrieve value from ResourcesImpl#mDrawableCache", ((Throwable)v5));
            }
        }

        v5_1 = v0_1;
    label_53:
        if(v5_1 != null) {
            h.a(v5_1);
        }
    }
}

