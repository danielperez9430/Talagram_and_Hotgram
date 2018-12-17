package android.support.v4.os;

import android.os.Build$VERSION;
import android.os.Trace;

public final class d {
    public static void a() {
        if(Build$VERSION.SDK_INT >= 18) {
            Trace.endSection();
        }
    }

    public static void a(String arg2) {
        if(Build$VERSION.SDK_INT >= 18) {
            Trace.beginSection(arg2);
        }
    }
}

