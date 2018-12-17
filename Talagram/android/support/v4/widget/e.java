package android.support.v4.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff$Mode;
import android.graphics.drawable.Drawable;
import android.os.Build$VERSION;
import android.util.Log;
import android.widget.CompoundButton;
import java.lang.reflect.Field;

public final class e {
    private static Field a;
    private static boolean b;

    public static Drawable a(CompoundButton arg4) {
        if(Build$VERSION.SDK_INT >= 23) {
            return arg4.getButtonDrawable();
        }

        if(!e.b) {
            try {
                e.a = CompoundButton.class.getDeclaredField("mButtonDrawable");
                e.a.setAccessible(true);
            }
            catch(NoSuchFieldException v1) {
                Log.i("CompoundButtonCompat", "Failed to retrieve mButtonDrawable field", ((Throwable)v1));
            }

            e.b = true;
        }

        Field v1_1 = null;
        if(e.a != null) {
            try {
                return e.a.get(arg4);
            }
            catch(IllegalAccessException v4) {
                Log.i("CompoundButtonCompat", "Failed to get button drawable via reflection", ((Throwable)v4));
                e.a = v1_1;
            }
        }

        return ((Drawable)v1_1);
    }

    public static void a(CompoundButton arg2, ColorStateList arg3) {
        if(Build$VERSION.SDK_INT >= 21) {
            arg2.setButtonTintList(arg3);
        }
        else if((arg2 instanceof r)) {
            ((r)arg2).setSupportButtonTintList(arg3);
        }
    }

    public static void a(CompoundButton arg2, PorterDuff$Mode arg3) {
        if(Build$VERSION.SDK_INT >= 21) {
            arg2.setButtonTintMode(arg3);
        }
        else if((arg2 instanceof r)) {
            ((r)arg2).setSupportButtonTintMode(arg3);
        }
    }
}

