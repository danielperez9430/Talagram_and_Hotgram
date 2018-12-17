package android.support.v7.widget;

import android.view.View;
import android.view.ViewParent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;

class m {
    static InputConnection a(InputConnection arg1, EditorInfo arg2, View arg3) {
        if(arg1 != null && arg2.hintText == null) {
            ViewParent v3 = arg3.getParent();
            while((v3 instanceof View)) {
                if((v3 instanceof bt)) {
                    arg2.hintText = ((bt)v3).a();
                }
                else {
                    v3 = v3.getParent();
                    continue;
                }

                return arg1;
            }
        }

        return arg1;
    }
}

