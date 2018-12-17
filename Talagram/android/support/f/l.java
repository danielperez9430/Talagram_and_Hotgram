package android.support.f;

import android.view.View;
import android.view.ViewGroup;

public class l {
    private ViewGroup a;
    private Runnable b;

    static l a(View arg1) {
        return arg1.getTag(a.transition_current_scene);
    }

    static void a(View arg1, l arg2) {
        arg1.setTag(a.transition_current_scene, arg2);
    }

    public void a() {
        if(l.a(this.a) == this && this.b != null) {
            this.b.run();
        }
    }
}

