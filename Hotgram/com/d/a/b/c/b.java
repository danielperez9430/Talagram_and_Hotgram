package com.d.a.b.c;

import android.graphics.Bitmap;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import com.d.a.b.a.f;

public class b implements a {
    private final int a;
    private final boolean b;
    private final boolean c;
    private final boolean d;

    public static void a(View arg3, int arg4) {
        if(arg3 != null) {
            AlphaAnimation v0 = new AlphaAnimation(0f, 1f);
            v0.setDuration(((long)arg4));
            v0.setInterpolator(new DecelerateInterpolator());
            arg3.startAnimation(((Animation)v0));
        }
    }

    public void a(Bitmap arg1, com.d.a.b.e.a arg2, f arg3) {
        arg2.a(arg1);
        if(!this.b || arg3 != f.a) {
            if(!this.c || arg3 != f.b) {
                if(!this.d) {
                    return;
                }

                if(arg3 != f.c) {
                    return;
                }
            }

        label_13:
            b.a(arg2.d(), this.a);
        }
        else {
            goto label_13;
        }
    }
}

