package android.support.v4.media;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build$VERSION;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;
import android.support.v4.media.session.MediaSessionCompat;
import android.text.TextUtils;

public final class MediaDescriptionCompat implements Parcelable {
    final class android.support.v4.media.MediaDescriptionCompat$1 implements Parcelable$Creator {
        android.support.v4.media.MediaDescriptionCompat$1() {
            super();
        }

        public MediaDescriptionCompat a(Parcel arg3) {
            if(Build$VERSION.SDK_INT < 21) {
                return new MediaDescriptionCompat(arg3);
            }

            return MediaDescriptionCompat.a(d.a(arg3));
        }

        public MediaDescriptionCompat[] a(int arg1) {
            return new MediaDescriptionCompat[arg1];
        }

        public Object createFromParcel(Parcel arg1) {
            return this.a(arg1);
        }

        public Object[] newArray(int arg1) {
            return this.a(arg1);
        }
    }

    public final class a {
        private String a;
        private CharSequence b;
        private CharSequence c;
        private CharSequence d;
        private Bitmap e;
        private Uri f;
        private Bundle g;
        private Uri h;

        public a() {
            super();
        }

        public a a(String arg1) {
            this.a = arg1;
            return this;
        }

        public a a(CharSequence arg1) {
            this.b = arg1;
            return this;
        }

        public a a(Bitmap arg1) {
            this.e = arg1;
            return this;
        }

        public a a(Uri arg1) {
            this.f = arg1;
            return this;
        }

        public a a(Bundle arg1) {
            this.g = arg1;
            return this;
        }

        public MediaDescriptionCompat a() {
            return new MediaDescriptionCompat(this.a, this.b, this.c, this.d, this.e, this.f, this.g, this.h);
        }

        public a b(CharSequence arg1) {
            this.c = arg1;
            return this;
        }

        public a b(Uri arg1) {
            this.h = arg1;
            return this;
        }

        public a c(CharSequence arg1) {
            this.d = arg1;
            return this;
        }
    }

    public static final Parcelable$Creator CREATOR;
    private final String a;
    private final CharSequence b;
    private final CharSequence c;
    private final CharSequence d;
    private final Bitmap e;
    private final Uri f;
    private final Bundle g;
    private final Uri h;
    private Object i;

    static {
        MediaDescriptionCompat.CREATOR = new android.support.v4.media.MediaDescriptionCompat$1();
    }

    MediaDescriptionCompat(Parcel arg3) {
        super();
        this.a = arg3.readString();
        this.b = TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(arg3);
        this.c = TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(arg3);
        this.d = TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(arg3);
        ClassLoader v0 = this.getClass().getClassLoader();
        this.e = arg3.readParcelable(v0);
        this.f = arg3.readParcelable(v0);
        this.g = arg3.readBundle(v0);
        this.h = arg3.readParcelable(v0);
    }

    MediaDescriptionCompat(String arg1, CharSequence arg2, CharSequence arg3, CharSequence arg4, Bitmap arg5, Uri arg6, Bundle arg7, Uri arg8) {
        super();
        this.a = arg1;
        this.b = arg2;
        this.c = arg3;
        this.d = arg4;
        this.e = arg5;
        this.f = arg6;
        this.g = arg7;
        this.h = arg8;
    }

    public static MediaDescriptionCompat a(Object arg6) {
        MediaDescriptionCompat v0_1;
        Parcelable v3;
        Bundle v0 = null;
        if(arg6 != null && Build$VERSION.SDK_INT >= 21) {
            a v1 = new a();
            v1.a(d.a(arg6));
            v1.a(d.b(arg6));
            v1.b(d.c(arg6));
            v1.c(d.d(arg6));
            v1.a(d.e(arg6));
            v1.a(d.f(arg6));
            Bundle v2 = d.g(arg6);
            if(v2 != null) {
                MediaSessionCompat.a(v2);
                v3 = v2.getParcelable("android.support.v4.media.description.MEDIA_URI");
            }
            else {
                v3 = ((Parcelable)v0);
            }

            if(v3 != null) {
                if((v2.containsKey("android.support.v4.media.description.NULL_BUNDLE_FLAG")) && v2.size() == 2) {
                    goto label_39;
                }

                v2.remove("android.support.v4.media.description.MEDIA_URI");
                v2.remove("android.support.v4.media.description.NULL_BUNDLE_FLAG");
                goto label_38;
            }
            else {
            label_38:
                v0 = v2;
            }

        label_39:
            v1.a(v0);
            if(v3 != null) {
                v1.b(((Uri)v3));
            }
            else if(Build$VERSION.SDK_INT >= 23) {
                v1.b(e.a(arg6));
            }

            v0_1 = v1.a();
            v0_1.i = arg6;
        }

        return v0_1;
    }

    public Object a() {
        if(this.i == null) {
            if(Build$VERSION.SDK_INT < 21) {
            }
            else {
                Object v0 = android.support.v4.media.d$a.a();
                android.support.v4.media.d$a.a(v0, this.a);
                android.support.v4.media.d$a.a(v0, this.b);
                android.support.v4.media.d$a.b(v0, this.c);
                android.support.v4.media.d$a.c(v0, this.d);
                android.support.v4.media.d$a.a(v0, this.e);
                android.support.v4.media.d$a.a(v0, this.f);
                Bundle v1 = this.g;
                int v3 = 23;
                if(Build$VERSION.SDK_INT < v3 && this.h != null) {
                    if(v1 == null) {
                        v1 = new Bundle();
                        v1.putBoolean("android.support.v4.media.description.NULL_BUNDLE_FLAG", true);
                    }

                    v1.putParcelable("android.support.v4.media.description.MEDIA_URI", this.h);
                }

                android.support.v4.media.d$a.a(v0, v1);
                if(Build$VERSION.SDK_INT >= v3) {
                    android.support.v4.media.e$a.a(v0, this.h);
                }

                this.i = android.support.v4.media.d$a.a(v0);
                return this.i;
            }
        }

        return this.i;
    }

    public int describeContents() {
        return 0;
    }

    public String toString() {
        return this.b + ", " + this.c + ", " + this.d;
    }

    public void writeToParcel(Parcel arg3, int arg4) {
        if(Build$VERSION.SDK_INT < 21) {
            arg3.writeString(this.a);
            TextUtils.writeToParcel(this.b, arg3, arg4);
            TextUtils.writeToParcel(this.c, arg3, arg4);
            TextUtils.writeToParcel(this.d, arg3, arg4);
            arg3.writeParcelable(this.e, arg4);
            arg3.writeParcelable(this.f, arg4);
            arg3.writeBundle(this.g);
            arg3.writeParcelable(this.h, arg4);
        }
        else {
            d.a(this.a(), arg3, arg4);
        }
    }
}

