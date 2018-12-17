package android.support.v7.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.support.v7.a.a$a;
import android.util.AttributeSet;
import android.widget.SeekBar;

public class t extends SeekBar {
    private final u a;

    public t(Context arg2, AttributeSet arg3) {
        this(arg2, arg3, a.seekBarStyle);
    }

    public t(Context arg1, AttributeSet arg2, int arg3) {
        super(arg1, arg2, arg3);
        this.a = new u(((SeekBar)this));
        this.a.a(arg2, arg3);
    }

    protected void drawableStateChanged() {
        super.drawableStateChanged();
        this.a.c();
    }

    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        this.a.b();
    }

    protected void onDraw(Canvas arg2) {
        __monitor_enter(this);
        try {
            super.onDraw(arg2);
            this.a.a(arg2);
        }
        catch(Throwable v2) {
            __monitor_exit(this);
            throw v2;
        }

        __monitor_exit(this);
    }
}

