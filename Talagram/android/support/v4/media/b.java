package android.support.v4.media;

import android.annotation.TargetApi;
import android.media.AudioAttributes;

@TargetApi(value=21) class b implements a {
    AudioAttributes a;
    int b;

    b() {
        super();
        this.b = -1;
    }

    public boolean equals(Object arg2) {
        if(!(arg2 instanceof b)) {
            return 0;
        }

        return this.a.equals(((b)arg2).a);
    }

    public int hashCode() {
        return this.a.hashCode();
    }

    public String toString() {
        return "AudioAttributesCompat: audioattributes=" + this.a;
    }
}

