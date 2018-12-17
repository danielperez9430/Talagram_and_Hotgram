package android.support.f;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewOverlay;

class ab implements ac {
    private final ViewOverlay a;

    ab(View arg1) {
        super();
        this.a = arg1.getOverlay();
    }

    public void a(Drawable arg2) {
        this.a.add(arg2);
    }

    public void b(Drawable arg2) {
        this.a.remove(arg2);
    }
}

