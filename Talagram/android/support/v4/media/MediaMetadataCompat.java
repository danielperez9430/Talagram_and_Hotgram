package android.support.v4.media;

import android.os.Build$VERSION;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;
import android.support.v4.f.a;
import android.support.v4.media.session.MediaSessionCompat;

public final class MediaMetadataCompat implements Parcelable {
    final class android.support.v4.media.MediaMetadataCompat$1 implements Parcelable$Creator {
        android.support.v4.media.MediaMetadataCompat$1() {
            super();
        }

        public MediaMetadataCompat a(Parcel arg2) {
            return new MediaMetadataCompat(arg2);
        }

        public MediaMetadataCompat[] a(int arg1) {
            return new MediaMetadataCompat[arg1];
        }

        public Object createFromParcel(Parcel arg1) {
            return this.a(arg1);
        }

        public Object[] newArray(int arg1) {
            return this.a(arg1);
        }
    }

    public static final Parcelable$Creator CREATOR;
    static final a a;
    final Bundle b;
    private static final String[] c;
    private static final String[] d;
    private static final String[] e;
    private Object f;

    static {
        MediaMetadataCompat.a = new a();
        MediaMetadataCompat.a.put("android.media.metadata.TITLE", Integer.valueOf(1));
        MediaMetadataCompat.a.put("android.media.metadata.ARTIST", Integer.valueOf(1));
        MediaMetadataCompat.a.put("android.media.metadata.DURATION", Integer.valueOf(0));
        MediaMetadataCompat.a.put("android.media.metadata.ALBUM", Integer.valueOf(1));
        MediaMetadataCompat.a.put("android.media.metadata.AUTHOR", Integer.valueOf(1));
        MediaMetadataCompat.a.put("android.media.metadata.WRITER", Integer.valueOf(1));
        MediaMetadataCompat.a.put("android.media.metadata.COMPOSER", Integer.valueOf(1));
        MediaMetadataCompat.a.put("android.media.metadata.COMPILATION", Integer.valueOf(1));
        MediaMetadataCompat.a.put("android.media.metadata.DATE", Integer.valueOf(1));
        MediaMetadataCompat.a.put("android.media.metadata.YEAR", Integer.valueOf(0));
        MediaMetadataCompat.a.put("android.media.metadata.GENRE", Integer.valueOf(1));
        MediaMetadataCompat.a.put("android.media.metadata.TRACK_NUMBER", Integer.valueOf(0));
        MediaMetadataCompat.a.put("android.media.metadata.NUM_TRACKS", Integer.valueOf(0));
        MediaMetadataCompat.a.put("android.media.metadata.DISC_NUMBER", Integer.valueOf(0));
        MediaMetadataCompat.a.put("android.media.metadata.ALBUM_ARTIST", Integer.valueOf(1));
        MediaMetadataCompat.a.put("android.media.metadata.ART", Integer.valueOf(2));
        MediaMetadataCompat.a.put("android.media.metadata.ART_URI", Integer.valueOf(1));
        MediaMetadataCompat.a.put("android.media.metadata.ALBUM_ART", Integer.valueOf(2));
        MediaMetadataCompat.a.put("android.media.metadata.ALBUM_ART_URI", Integer.valueOf(1));
        MediaMetadataCompat.a.put("android.media.metadata.USER_RATING", Integer.valueOf(3));
        MediaMetadataCompat.a.put("android.media.metadata.RATING", Integer.valueOf(3));
        MediaMetadataCompat.a.put("android.media.metadata.DISPLAY_TITLE", Integer.valueOf(1));
        MediaMetadataCompat.a.put("android.media.metadata.DISPLAY_SUBTITLE", Integer.valueOf(1));
        MediaMetadataCompat.a.put("android.media.metadata.DISPLAY_DESCRIPTION", Integer.valueOf(1));
        MediaMetadataCompat.a.put("android.media.metadata.DISPLAY_ICON", Integer.valueOf(2));
        MediaMetadataCompat.a.put("android.media.metadata.DISPLAY_ICON_URI", Integer.valueOf(1));
        MediaMetadataCompat.a.put("android.media.metadata.MEDIA_ID", Integer.valueOf(1));
        MediaMetadataCompat.a.put("android.media.metadata.BT_FOLDER_TYPE", Integer.valueOf(0));
        MediaMetadataCompat.a.put("android.media.metadata.MEDIA_URI", Integer.valueOf(1));
        MediaMetadataCompat.a.put("android.media.metadata.ADVERTISEMENT", Integer.valueOf(0));
        MediaMetadataCompat.a.put("android.media.metadata.DOWNLOAD_STATUS", Integer.valueOf(0));
        MediaMetadataCompat.c = new String[]{"android.media.metadata.TITLE", "android.media.metadata.ARTIST", "android.media.metadata.ALBUM", "android.media.metadata.ALBUM_ARTIST", "android.media.metadata.WRITER", "android.media.metadata.AUTHOR", "android.media.metadata.COMPOSER"};
        MediaMetadataCompat.d = new String[]{"android.media.metadata.DISPLAY_ICON", "android.media.metadata.ART", "android.media.metadata.ALBUM_ART"};
        MediaMetadataCompat.e = new String[]{"android.media.metadata.DISPLAY_ICON_URI", "android.media.metadata.ART_URI", "android.media.metadata.ALBUM_ART_URI"};
        MediaMetadataCompat.CREATOR = new android.support.v4.media.MediaMetadataCompat$1();
    }

    MediaMetadataCompat(Parcel arg2) {
        super();
        this.b = arg2.readBundle(MediaSessionCompat.class.getClassLoader());
    }

    public static MediaMetadataCompat a(Object arg2) {
        if(arg2 != null && Build$VERSION.SDK_INT >= 21) {
            Parcel v0 = Parcel.obtain();
            f.a(arg2, v0, 0);
            v0.setDataPosition(0);
            Object v1 = MediaMetadataCompat.CREATOR.createFromParcel(v0);
            v0.recycle();
            ((MediaMetadataCompat)v1).f = arg2;
            return ((MediaMetadataCompat)v1);
        }

        return null;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel arg1, int arg2) {
        arg1.writeBundle(this.b);
    }
}

