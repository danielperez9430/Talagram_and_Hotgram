package android.support.f;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroupOverlay;

class v implements w {
    private final ViewGroupOverlay a;

    v(ViewGroup arg1) {
        super();
        this.a = arg1.getOverlay();
    }

    public void a(Drawable arg2) {
        this.a.add(arg2);
    }

    public void a(View arg2) {
        this.a.add(arg2);
    }

    public void b(Drawable arg2) {
        this.a.remove(arg2);
    }

    public void b(View arg2) {
        this.a.remove(arg2);
    }
}

