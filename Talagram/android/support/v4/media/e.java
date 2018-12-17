package android.support.v4.media;

import android.media.MediaDescription$Builder;
import android.media.MediaDescription;
import android.net.Uri;

class e {
    class a {
        public static void a(Object arg0, Uri arg1) {
            ((MediaDescription$Builder)arg0).setMediaUri(arg1);
        }
    }

    public static Uri a(Object arg0) {
        return ((MediaDescription)arg0).getMediaUri();
    }
}

