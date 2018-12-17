package android.support.v4.app;

import android.app.Activity;
import android.arch.lifecycle.d$b;
import android.arch.lifecycle.d;
import android.arch.lifecycle.g;
import android.arch.lifecycle.h;
import android.arch.lifecycle.q;
import android.os.Bundle;
import android.support.v4.f.m;
import android.support.v4.view.e$a;
import android.support.v4.view.e;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window$Callback;

public class ag extends Activity implements g, a {
    private m a;
    private h b;

    public ag() {
        super();
        this.a = new m();
        this.b = new h(((g)this));
    }

    public boolean a(KeyEvent arg1) {
        return super.dispatchKeyEvent(arg1);
    }

    public boolean dispatchKeyEvent(KeyEvent arg3) {
        View v0 = this.getWindow().getDecorView();
        if(v0 != null && (e.a(v0, arg3))) {
            return 1;
        }

        return e.a(((a)this), v0, ((Window$Callback)this), arg3);
    }

    public boolean dispatchKeyShortcutEvent(KeyEvent arg2) {
        View v0 = this.getWindow().getDecorView();
        if(v0 != null && (e.a(v0, arg2))) {
            return 1;
        }

        return super.dispatchKeyShortcutEvent(arg2);
    }

    public d getLifecycle() {
        return this.b;
    }

    protected void onCreate(Bundle arg1) {
        super.onCreate(arg1);
        q.a(((Activity)this));
    }

    protected void onSaveInstanceState(Bundle arg3) {
        this.b.a(b.c);
        super.onSaveInstanceState(arg3);
    }
}

