package android.support.v7.widget;

import android.graphics.PorterDuff$Mode;
import android.graphics.Rect;
import android.graphics.drawable.Drawable$ConstantState;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer$DrawableContainerState;
import android.graphics.drawable.DrawableContainer;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ScaleDrawable;
import android.os.Build$VERSION;
import android.support.v4.graphics.drawable.c;

public class ai {
    public static final Rect a;
    private static Class b;

    static {
        ai.a = new Rect();
        if(Build$VERSION.SDK_INT >= 18) {
            try {
                ai.b = Class.forName("android.graphics.Insets");
                return;
            }
            catch(ClassNotFoundException ) {
                return;
            }
        }
    }

    public static PorterDuff$Mode a(int arg1, PorterDuff$Mode arg2) {
        if(arg1 == 3) {
            goto label_18;
        }

        if(arg1 == 5) {
            goto label_16;
        }

        if(arg1 == 9) {
            goto label_14;
        }

        switch(arg1) {
            case 14: {
                goto label_12;
            }
            case 15: {
                goto label_10;
            }
            case 16: {
                goto label_8;
            }
        }

        return arg2;
    label_8:
        return PorterDuff$Mode.ADD;
    label_10:
        return PorterDuff$Mode.SCREEN;
    label_12:
        return PorterDuff$Mode.MULTIPLY;
    label_14:
        return PorterDuff$Mode.SRC_ATOP;
    label_16:
        return PorterDuff$Mode.SRC_IN;
    label_18:
        return PorterDuff$Mode.SRC_OVER;
    }

    static void a(Drawable arg2) {
        if(Build$VERSION.SDK_INT == 21 && ("android.graphics.drawable.VectorDrawable".equals(arg2.getClass().getName()))) {
            ai.c(arg2);
        }
    }

    public static boolean b(Drawable arg4) {
        int v1 = 15;
        if(Build$VERSION.SDK_INT < v1 && ((arg4 instanceof InsetDrawable))) {
            return 0;
        }

        if(Build$VERSION.SDK_INT < v1 && ((arg4 instanceof GradientDrawable))) {
            return 0;
        }

        if(Build$VERSION.SDK_INT < 17 && ((arg4 instanceof LayerDrawable))) {
            return 0;
        }

        if((arg4 instanceof DrawableContainer)) {
            Drawable$ConstantState v4 = arg4.getConstantState();
            if((v4 instanceof DrawableContainer$DrawableContainerState)) {
                Drawable[] v4_1 = ((DrawableContainer$DrawableContainerState)v4).getChildren();
                int v0 = v4_1.length;
                v1 = 0;
                while(v1 < v0) {
                    if(!ai.b(v4_1[v1])) {
                        return 0;
                    }
                    else {
                        ++v1;
                        continue;
                    }

                    return 1;
                }
            }
        }
        else {
            if((arg4 instanceof c)) {
                arg4 = ((c)arg4).a();
            }
            else if((arg4 instanceof android.support.v7.d.a.c)) {
                arg4 = ((android.support.v7.d.a.c)arg4).b();
            }
            else if((arg4 instanceof ScaleDrawable)) {
                arg4 = ((ScaleDrawable)arg4).getDrawable();
            }
            else {
                return 1;
            }

            return ai.b(arg4);
        }

        return 1;
    }

    private static void c(Drawable arg2) {
        int[] v0 = arg2.getState();
        int[] v1 = v0 == null || v0.length == 0 ? bf.e : bf.h;
        arg2.setState(v1);
        arg2.setState(v0);
    }
}

