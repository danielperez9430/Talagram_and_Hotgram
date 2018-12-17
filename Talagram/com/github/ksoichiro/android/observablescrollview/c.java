package com.github.ksoichiro.android.observablescrollview;

import android.os.Build$VERSION;
import android.view.View;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;

public final class c {
    public static void a(View arg2, Runnable arg3) {
        arg2.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver$OnGlobalLayoutListener(arg2, arg3) {
            public void onGlobalLayout() {
                if(Build$VERSION.SDK_INT < 16) {
                    this.a.getViewTreeObserver().removeGlobalOnLayoutListener(((ViewTreeObserver$OnGlobalLayoutListener)this));
                }
                else {
                    this.a.getViewTreeObserver().removeOnGlobalLayoutListener(((ViewTreeObserver$OnGlobalLayoutListener)this));
                }

                this.b.run();
            }
        });
    }
}

