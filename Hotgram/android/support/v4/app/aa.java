package android.support.v4.app;

import android.view.View$OnAttachStateChangeListener;
import android.view.View;
import android.view.ViewTreeObserver$OnPreDrawListener;
import android.view.ViewTreeObserver;

class aa implements View$OnAttachStateChangeListener, ViewTreeObserver$OnPreDrawListener {
    private final View a;
    private ViewTreeObserver b;
    private final Runnable c;

    private aa(View arg1, Runnable arg2) {
        super();
        this.a = arg1;
        this.b = arg1.getViewTreeObserver();
        this.c = arg2;
    }

    public static aa a(View arg1, Runnable arg2) {
        aa v0 = new aa(arg1, arg2);
        arg1.getViewTreeObserver().addOnPreDrawListener(((ViewTreeObserver$OnPreDrawListener)v0));
        arg1.addOnAttachStateChangeListener(((View$OnAttachStateChangeListener)v0));
        return v0;
    }

    public void a() {
        ViewTreeObserver v0 = this.b.isAlive() ? this.b : this.a.getViewTreeObserver();
        v0.removeOnPreDrawListener(((ViewTreeObserver$OnPreDrawListener)this));
        this.a.removeOnAttachStateChangeListener(((View$OnAttachStateChangeListener)this));
    }

    public boolean onPreDraw() {
        this.a();
        this.c.run();
        return 1;
    }

    public void onViewAttachedToWindow(View arg1) {
        this.b = arg1.getViewTreeObserver();
    }

    public void onViewDetachedFromWindow(View arg1) {
        this.a();
    }
}

