package androidx.media;

import android.support.v4.media.b;
import androidx.versionedparcelable.a;

public final class AudioAttributesImplApi21Parcelizer {
    public AudioAttributesImplApi21Parcelizer() {
        super();
    }

    public static b read(a arg3) {
        b v0 = new b();
        v0.mAudioAttributes = arg3.b(v0.mAudioAttributes, 1);
        v0.mLegacyStreamType = arg3.b(v0.mLegacyStreamType, 2);
        return v0;
    }

    public static void write(b arg2, a arg3) {
        arg3.a(false, false);
        arg3.a(arg2.mAudioAttributes, 1);
        arg3.a(arg2.mLegacyStreamType, 2);
    }
}

