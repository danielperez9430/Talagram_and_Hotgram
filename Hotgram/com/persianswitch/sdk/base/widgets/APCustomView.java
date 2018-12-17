package com.persianswitch.sdk.base.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public abstract class APCustomView extends LinearLayout {
    public APCustomView(Context arg1) {
        super(arg1);
        this.b(null);
    }

    public APCustomView(Context arg1, AttributeSet arg2) {
        super(arg1, arg2);
        this.b(arg2);
    }

    public APCustomView(Context arg1, AttributeSet arg2, int arg3) {
        super(arg1, arg2, arg3);
        this.b(arg2);
    }

    protected abstract void a(AttributeSet arg1);

    public void a() {
    }

    private void b(AttributeSet arg4) {
        LayoutInflater.from(this.getContext()).inflate(this.getViewLayoutResourceId(), ((ViewGroup)this), true);
        this.a(arg4);
        this.b();
    }

    public abstract void b();

    protected abstract int getViewLayoutResourceId();

    public boolean onTouchEvent(MotionEvent arg4) {
        boolean v0 = super.onTouchEvent(arg4);
        if(!v0 && arg4.getAction() == 0) {
            return 1;
        }

        if(!v0 && arg4.getAction() == 1) {
            this.a();
            return 1;
        }

        return v0;
    }
}

