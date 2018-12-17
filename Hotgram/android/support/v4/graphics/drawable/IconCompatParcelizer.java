package android.support.v4.graphics.drawable;

import androidx.versionedparcelable.a;

public final class IconCompatParcelizer extends androidx.core.graphics.drawable.IconCompatParcelizer {
    public IconCompatParcelizer() {
        super();
    }

    public static IconCompat read(a arg0) {
        return androidx.core.graphics.drawable.IconCompatParcelizer.read(arg0);
    }

    public static void write(IconCompat arg0, a arg1) {
        androidx.core.graphics.drawable.IconCompatParcelizer.write(arg0, arg1);
    }
}

