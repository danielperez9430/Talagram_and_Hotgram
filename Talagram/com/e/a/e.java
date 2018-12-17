package com.e.a;

import android.view.ViewTreeObserver$OnPreDrawListener;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import java.lang.ref.WeakReference;

class e implements ViewTreeObserver$OnPreDrawListener {
    final p a;
    final WeakReference b;
    d c;

    e(p arg1, ImageView arg2, d arg3) {
        super();
        this.a = arg1;
        this.b = new WeakReference(arg2);
        this.c = arg3;
        arg2.getViewTreeObserver().addOnPreDrawListener(((ViewTreeObserver$OnPreDrawListener)this));
    }

    void a() {
        this.c = null;
        Object v0 = this.b.get();
        if(v0 == null) {
            return;
        }

        ViewTreeObserver v0_1 = ((ImageView)v0).getViewTreeObserver();
        if(!v0_1.isAlive()) {
            return;
        }

        v0_1.removeOnPreDrawListener(((ViewTreeObserver$OnPreDrawListener)this));
    }

    public boolean onPreDraw() {
        Object v0 = this.b.get();
        if(v0 == null) {
            return 1;
        }

        ViewTreeObserver v2 = ((ImageView)v0).getViewTreeObserver();
        if(!v2.isAlive()) {
            return 1;
        }

        int v3 = ((ImageView)v0).getWidth();
        int v4 = ((ImageView)v0).getHeight();
        if(v3 > 0) {
            if(v4 <= 0) {
            }
            else {
                v2.removeOnPreDrawListener(((ViewTreeObserver$OnPreDrawListener)this));
                this.a.a().a(v3, v4).a(((ImageView)v0), this.c);
            }
        }

        return 1;
    }
}

