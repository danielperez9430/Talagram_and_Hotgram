package android.support.design.d;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.view.View;
import android.view.ViewParent;

public final class c {
    private final View a;
    private boolean b;
    private int c;

    public c(b arg2) {
        super();
        this.b = false;
        this.c = 0;
        this.a = ((View)arg2);
    }

    public void a(int arg1) {
        this.c = arg1;
    }

    public void a(Bundle arg3) {
        this.b = arg3.getBoolean("expanded", false);
        this.c = arg3.getInt("expandedComponentIdHint", 0);
        if(this.b) {
            this.d();
        }
    }

    public boolean a() {
        return this.b;
    }

    public Bundle b() {
        Bundle v0 = new Bundle();
        v0.putBoolean("expanded", this.b);
        v0.putInt("expandedComponentIdHint", this.c);
        return v0;
    }

    public int c() {
        return this.c;
    }

    private void d() {
        ViewParent v0 = this.a.getParent();
        if((v0 instanceof CoordinatorLayout)) {
            ((CoordinatorLayout)v0).b(this.a);
        }
    }
}

