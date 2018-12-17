package androidx.media;

import android.support.v4.media.c;
import androidx.versionedparcelable.a;

public final class AudioAttributesImplBaseParcelizer {
    public AudioAttributesImplBaseParcelizer() {
        super();
    }

    public static c read(a arg3) {
        c v0 = new c();
        v0.mUsage = arg3.b(v0.mUsage, 1);
        v0.mContentType = arg3.b(v0.mContentType, 2);
        v0.mFlags = arg3.b(v0.mFlags, 3);
        v0.mLegacyStream = arg3.b(v0.mLegacyStream, 4);
        return v0;
    }

    public static void write(c arg2, a arg3) {
        arg3.a(false, false);
        arg3.a(arg2.mUsage, 1);
        arg3.a(arg2.mContentType, 2);
        arg3.a(arg2.mFlags, 3);
        arg3.a(arg2.mLegacyStream, 4);
    }
}

