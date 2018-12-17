package androidx.core.graphics.drawable;

import android.support.v4.graphics.drawable.IconCompat;
import androidx.versionedparcelable.a;

public class IconCompatParcelizer {
    public IconCompatParcelizer() {
        super();
    }

    public static IconCompat read(a arg3) {
        IconCompat v0 = new IconCompat();
        v0.a = arg3.b(v0.a, 1);
        v0.c = arg3.b(v0.c, 2);
        v0.d = arg3.b(v0.d, 3);
        v0.e = arg3.b(v0.e, 4);
        v0.f = arg3.b(v0.f, 5);
        v0.g = arg3.b(v0.g, 6);
        v0.j = arg3.b(v0.j, 7);
        v0.e();
        return v0;
    }

    public static void write(IconCompat arg2, a arg3) {
        arg3.a(true, true);
        arg2.a(arg3.a());
        arg3.a(arg2.a, 1);
        arg3.a(arg2.c, 2);
        arg3.a(arg2.d, 3);
        arg3.a(arg2.e, 4);
        arg3.a(arg2.f, 5);
        arg3.a(arg2.g, 6);
        arg3.a(arg2.j, 7);
    }
}

