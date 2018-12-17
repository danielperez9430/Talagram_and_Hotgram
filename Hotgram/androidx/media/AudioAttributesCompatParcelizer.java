package androidx.media;

import android.support.v4.media.AudioAttributesCompat;
import androidx.versionedparcelable.a;

public final class AudioAttributesCompatParcelizer {
    public AudioAttributesCompatParcelizer() {
        super();
    }

    public static AudioAttributesCompat read(a arg3) {
        AudioAttributesCompat v0 = new AudioAttributesCompat();
        v0.mImpl = arg3.b(v0.mImpl, 1);
        return v0;
    }

    public static void write(AudioAttributesCompat arg1, a arg2) {
        arg2.a(false, false);
        arg2.a(arg1.mImpl, 1);
    }
}

