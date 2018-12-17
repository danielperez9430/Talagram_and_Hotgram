package net.hockeyapp.android;

import net.hockeyapp.android.d.b;

public class i {
    private static b a;

    static {
    }

    public static void a() {
        if(i.a != null) {
            i.a.cancel(true);
            i.a.a();
            i.a = null;
        }
    }
}

