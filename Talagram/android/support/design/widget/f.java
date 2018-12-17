package android.support.design.widget;

import android.graphics.drawable.Drawable$ConstantState;
import android.graphics.drawable.DrawableContainer$DrawableContainerState;
import android.graphics.drawable.DrawableContainer;
import android.util.Log;
import java.lang.reflect.Method;

public class f {
    private static Method a;
    private static boolean b;

    public static boolean a(DrawableContainer arg0, Drawable$ConstantState arg1) {
        return f.b(arg0, arg1);
    }

    private static boolean b(DrawableContainer arg6, Drawable$ConstantState arg7) {
        if(!f.b) {
            try {
                f.a = DrawableContainer.class.getDeclaredMethod("setConstantState", DrawableContainer$DrawableContainerState.class);
                f.a.setAccessible(true);
            }
            catch(NoSuchMethodException ) {
                Log.e("DrawableUtils", "Could not fetch setConstantState(). Oh well.");
            }

            f.b = true;
        }

        if(f.a != null) {
            try {
                f.a.invoke(arg6, arg7);
                return 1;
            }
            catch(Exception ) {
                Log.e("DrawableUtils", "Could not invoke setConstantState(). Oh well.");
            }
        }

        return 0;
    }
}

