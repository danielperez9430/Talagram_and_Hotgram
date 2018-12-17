package com.google.android.gms.common.util;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class InputMethodUtils {
    private InputMethodUtils() {
        super();
    }

    public static boolean hideSoftInput(Context arg1, View arg2) {
        InputMethodManager v1 = InputMethodUtils.zzf(arg1);
        if(v1 != null) {
            v1.hideSoftInputFromWindow(arg2.getWindowToken(), 0);
            return 1;
        }

        return 0;
    }

    public static boolean isAcceptingText(Context arg0) {
        InputMethodManager v0 = InputMethodUtils.zzf(arg0);
        if(v0 != null) {
            return v0.isAcceptingText();
        }

        return 0;
    }

    public static void restart(Context arg0, View arg1) {
        InputMethodManager v0 = InputMethodUtils.zzf(arg0);
        if(v0 != null) {
            v0.restartInput(arg1);
        }
    }

    public static boolean showSoftInput(Context arg1, View arg2) {
        InputMethodManager v1 = InputMethodUtils.zzf(arg1);
        if(v1 != null) {
            v1.showSoftInput(arg2, 0);
            return 1;
        }

        return 0;
    }

    private static InputMethodManager zzf(Context arg1) {
        return arg1.getSystemService("input_method");
    }
}

