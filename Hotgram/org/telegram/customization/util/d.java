package org.telegram.customization.util;

import android.os.AsyncTask;
import android.os.Build$VERSION;

public final class d {
    public static AsyncTask a(AsyncTask arg2, Object[] arg3) {
        if(arg2 != null) {
            if(Build$VERSION.SDK_INT >= 11) {
                e.a(arg2, arg3);
            }
            else {
                arg2.execute(arg3);
            }

            return arg2;
        }

        throw new IllegalArgumentException("task can not be null");
    }
}

