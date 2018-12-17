package android.support.v4.widget;

import android.view.View;
import android.widget.ListView;

public class m extends a {
    private final ListView f;

    public m(ListView arg1) {
        super(((View)arg1));
        this.f = arg1;
    }

    public void a(int arg1, int arg2) {
        n.a(this.f, arg2);
    }

    public boolean e(int arg1) {
        return 0;
    }

    public boolean f(int arg8) {
        ListView v0 = this.f;
        int v1 = v0.getCount();
        if(v1 == 0) {
            return 0;
        }

        int v3 = v0.getChildCount();
        int v4 = v0.getFirstVisiblePosition();
        int v5 = v4 + v3;
        if(arg8 > 0) {
            if(v5 >= v1 && v0.getChildAt(v3 - 1).getBottom() <= v0.getHeight()) {
                return 0;
            }
        }
        else if(arg8 >= 0) {
            return 0;
        }
        else if(v4 <= 0 && v0.getChildAt(0).getTop() >= 0) {
            return 0;
        }

        return 1;
    }
}

