package android.support.design.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageButton;

public class w extends ImageButton {
    private int a;

    public w(Context arg2) {
        this(arg2, null);
    }

    public w(Context arg2, AttributeSet arg3) {
        this(arg2, arg3, 0);
    }

    public w(Context arg1, AttributeSet arg2, int arg3) {
        super(arg1, arg2, arg3);
        this.a = this.getVisibility();
    }

    public final void a(int arg1, boolean arg2) {
        super.setVisibility(arg1);
        if(arg2) {
            this.a = arg1;
        }
    }

    public final int getUserSetVisibility() {
        return this.a;
    }

    public void setVisibility(int arg2) {
        this.a(arg2, true);
    }
}

