package android.support.v7.widget;

import android.content.Context;
import android.os.Build$VERSION;
import android.support.v4.widget.o;
import android.support.v7.a.a$j;
import android.util.AttributeSet;
import android.view.View;
import android.widget.PopupWindow;

class p extends PopupWindow {
    private static final boolean a;
    private boolean b;

    static {
        boolean v0 = Build$VERSION.SDK_INT < 21 ? true : false;
        p.a = v0;
    }

    public p(Context arg1, AttributeSet arg2, int arg3, int arg4) {
        super(arg1, arg2, arg3, arg4);
        this.a(arg1, arg2, arg3, arg4);
    }

    private void a(Context arg2, AttributeSet arg3, int arg4, int arg5) {
        bk v2 = bk.a(arg2, arg3, j.PopupWindow, arg4, arg5);
        if(v2.g(j.PopupWindow_overlapAnchor)) {
            this.a(v2.a(j.PopupWindow_overlapAnchor, false));
        }

        this.setBackgroundDrawable(v2.a(j.PopupWindow_android_popupBackground));
        v2.a();
    }

    private void a(boolean arg2) {
        if(p.a) {
            this.b = arg2;
        }
        else {
            o.a(((PopupWindow)this), arg2);
        }
    }

    public void showAsDropDown(View arg2, int arg3, int arg4) {
        if((p.a) && (this.b)) {
            arg4 -= arg2.getHeight();
        }

        super.showAsDropDown(arg2, arg3, arg4);
    }

    public void showAsDropDown(View arg2, int arg3, int arg4, int arg5) {
        if((p.a) && (this.b)) {
            arg4 -= arg2.getHeight();
        }

        super.showAsDropDown(arg2, arg3, arg4, arg5);
    }

    public void update(View arg7, int arg8, int arg9, int arg10, int arg11) {
        if((p.a) && (this.b)) {
            arg9 -= arg7.getHeight();
        }

        super.update(arg7, arg8, arg9, arg10, arg11);
    }
}

