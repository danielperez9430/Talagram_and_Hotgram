package android.support.v4.view;

import android.content.Context;
import android.util.Log;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;

public abstract class b {
    public interface a {
        void a(boolean arg1);
    }

    public interface android.support.v4.view.b$b {
        void a(boolean arg1);
    }

    private final Context a;
    private a b;
    private android.support.v4.view.b$b c;

    public b(Context arg1) {
        super();
        this.a = arg1;
    }

    public abstract View a();

    public View a(MenuItem arg1) {
        return this.a();
    }

    public void a(a arg1) {
        this.b = arg1;
    }

    public void a(android.support.v4.view.b$b arg4) {
        if(this.c != null && arg4 != null) {
            Log.w("ActionProvider(support)", "setVisibilityListener: Setting a new ActionProvider.VisibilityListener when one is already set. Are you reusing this " + this.getClass().getSimpleName() + " instance while it is still in use somewhere else?");
        }

        this.c = arg4;
    }

    public void a(SubMenu arg1) {
    }

    public void a(boolean arg2) {
        if(this.b != null) {
            this.b.a(arg2);
        }
    }

    public boolean b() {
        return 0;
    }

    public boolean c() {
        return 1;
    }

    public boolean d() {
        return 0;
    }

    public boolean e() {
        return 0;
    }

    public void f() {
        this.c = null;
        this.b = null;
    }
}

