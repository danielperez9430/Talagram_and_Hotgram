package c.a.a.a.a.g;

import android.content.Context;
import android.graphics.BitmapFactory$Options;
import android.graphics.BitmapFactory;
import c.a.a.a.a.b.i;
import c.a.a.a.c;
import c.a.a.a.l;

public class n {
    public final String a;
    public final int b;
    public final int c;
    public final int d;

    public n(String arg1, int arg2, int arg3, int arg4) {
        super();
        this.a = arg1;
        this.b = arg2;
        this.c = arg3;
        this.d = arg4;
    }

    public static n a(Context arg5, String arg6) {
        if(arg6 != null) {
            try {
                int v0 = i.l(arg5);
                l v1 = c.h();
                v1.a("Fabric", "App icon resource ID is " + v0);
                BitmapFactory$Options v1_1 = new BitmapFactory$Options();
                v1_1.inJustDecodeBounds = true;
                BitmapFactory.decodeResource(arg5.getResources(), v0, v1_1);
                n v5_1 = new n(arg6, v0, v1_1.outWidth, v1_1.outHeight);
                return v5_1;
            }
            catch(Exception v5) {
                c.h().e("Fabric", "Failed to load icon", ((Throwable)v5));
            }
        }

        return null;
    }
}

