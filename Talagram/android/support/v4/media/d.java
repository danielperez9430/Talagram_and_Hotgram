package android.support.v4.media;

import android.graphics.Bitmap;
import android.media.MediaDescription$Builder;
import android.media.MediaDescription;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;

class d {
    class a {
        public static Object a() {
            return new MediaDescription$Builder();
        }

        public static void a(Object arg0, String arg1) {
            ((MediaDescription$Builder)arg0).setMediaId(arg1);
        }

        public static void a(Object arg0, CharSequence arg1) {
            ((MediaDescription$Builder)arg0).setTitle(arg1);
        }

        public static void a(Object arg0, Bitmap arg1) {
            ((MediaDescription$Builder)arg0).setIconBitmap(arg1);
        }

        public static void a(Object arg0, Uri arg1) {
            ((MediaDescription$Builder)arg0).setIconUri(arg1);
        }

        public static void a(Object arg0, Bundle arg1) {
            ((MediaDescription$Builder)arg0).setExtras(arg1);
        }

        public static Object a(Object arg0) {
            return ((MediaDescription$Builder)arg0).build();
        }

        public static void b(Object arg0, CharSequence arg1) {
            ((MediaDescription$Builder)arg0).setSubtitle(arg1);
        }

        public static void c(Object arg0, CharSequence arg1) {
            ((MediaDescription$Builder)arg0).setDescription(arg1);
        }
    }

    public static String a(Object arg0) {
        return ((MediaDescription)arg0).getMediaId();
    }

    public static void a(Object arg0, Parcel arg1, int arg2) {
        ((MediaDescription)arg0).writeToParcel(arg1, arg2);
    }

    public static Object a(Parcel arg1) {
        return MediaDescription.CREATOR.createFromParcel(arg1);
    }

    public static CharSequence b(Object arg0) {
        return ((MediaDescription)arg0).getTitle();
    }

    public static CharSequence c(Object arg0) {
        return ((MediaDescription)arg0).getSubtitle();
    }

    public static CharSequence d(Object arg0) {
        return ((MediaDescription)arg0).getDescription();
    }

    public static Bitmap e(Object arg0) {
        return ((MediaDescription)arg0).getIconBitmap();
    }

    public static Uri f(Object arg0) {
        return ((MediaDescription)arg0).getIconUri();
    }

    public static Bundle g(Object arg0) {
        return ((MediaDescription)arg0).getExtras();
    }
}

