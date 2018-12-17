package android.support.v4.app;

import android.app.ActivityOptions;
import android.content.Context;
import android.os.Build$VERSION;
import android.os.Bundle;

public class c {
    class a extends c {
        private final ActivityOptions a;

        a(ActivityOptions arg1) {
            super();
            this.a = arg1;
        }

        public Bundle a() {
            return this.a.toBundle();
        }
    }

    protected c() {
        super();
    }

    public static c a(Context arg2, int arg3, int arg4) {
        if(Build$VERSION.SDK_INT >= 16) {
            return new a(ActivityOptions.makeCustomAnimation(arg2, arg3, arg4));
        }

        return new c();
    }

    public Bundle a() {
        return null;
    }
}

