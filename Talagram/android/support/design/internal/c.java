package android.support.design.internal;

import android.graphics.PorterDuff$Mode;
import android.support.v4.view.t;
import android.view.View;

public class c {
    public static PorterDuff$Mode a(int arg1, PorterDuff$Mode arg2) {
        if(arg1 == 3) {
            goto label_18;
        }

        if(arg1 == 5) {
            goto label_16;
        }

        if(arg1 == 9) {
            goto label_14;
        }

        switch(arg1) {
            case 14: {
                goto label_12;
            }
            case 15: {
                goto label_10;
            }
            case 16: {
                goto label_8;
            }
        }

        return arg2;
    label_8:
        return PorterDuff$Mode.ADD;
    label_10:
        return PorterDuff$Mode.SCREEN;
    label_12:
        return PorterDuff$Mode.MULTIPLY;
    label_14:
        return PorterDuff$Mode.SRC_ATOP;
    label_16:
        return PorterDuff$Mode.SRC_IN;
    label_18:
        return PorterDuff$Mode.SRC_OVER;
    }

    public static boolean a(View arg1) {
        boolean v0 = true;
        if(t.f(arg1) == 1) {
        }
        else {
            v0 = false;
        }

        return v0;
    }
}

