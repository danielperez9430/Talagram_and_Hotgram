package com.mohamadamin.persianmaterialdatetimepicker;

import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.annotation.SuppressLint;
import android.os.Build$VERSION;
import android.view.View;

public class d {
    public static int a(int arg2, int arg3) {
        if(arg2 < 6) {
            return 31;
        }

        int v1 = 30;
        if(arg2 < 11) {
            return v1;
        }

        if(com.mohamadamin.persianmaterialdatetimepicker.a.d.a(arg3)) {
            return v1;
        }

        return 29;
    }

    public static ObjectAnimator a(View arg9, float arg10, float arg11) {
        Keyframe v1 = Keyframe.ofFloat(0f, 1f);
        Keyframe v10 = Keyframe.ofFloat(0.275f, arg10);
        Keyframe v11 = Keyframe.ofFloat(0.69f, arg11);
        Keyframe v0 = Keyframe.ofFloat(1f, 1f);
        ObjectAnimator v9 = ObjectAnimator.ofPropertyValuesHolder(arg9, new PropertyValuesHolder[]{PropertyValuesHolder.ofKeyframe("scaleX", new Keyframe[]{v1, v10, v11, v0}), PropertyValuesHolder.ofKeyframe("scaleY", new Keyframe[]{v1, v10, v11, v0})});
        v9.setDuration(544);
        return v9;
    }

    @SuppressLint(value={"NewApi"}) public static void a(View arg1, CharSequence arg2) {
        if((d.a()) && arg1 != null && arg2 != null) {
            arg1.announceForAccessibility(arg2);
        }
    }

    public static boolean a() {
        boolean v0 = Build$VERSION.SDK_INT >= 16 ? true : false;
        return v0;
    }
}

