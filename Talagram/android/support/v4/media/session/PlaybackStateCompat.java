package android.support.v4.media.session;

import android.os.Build$VERSION;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class PlaybackStateCompat implements Parcelable {
    final class android.support.v4.media.session.PlaybackStateCompat$1 implements Parcelable$Creator {
        android.support.v4.media.session.PlaybackStateCompat$1() {
            super();
        }

        public PlaybackStateCompat a(Parcel arg2) {
            return new PlaybackStateCompat(arg2);
        }

        public PlaybackStateCompat[] a(int arg1) {
            return new PlaybackStateCompat[arg1];
        }

        public Object createFromParcel(Parcel arg1) {
            return this.a(arg1);
        }

        public Object[] newArray(int arg1) {
            return this.a(arg1);
        }
    }

    public final class CustomAction implements Parcelable {
        final class android.support.v4.media.session.PlaybackStateCompat$CustomAction$1 implements Parcelable$Creator {
            android.support.v4.media.session.PlaybackStateCompat$CustomAction$1() {
                super();
            }

            public CustomAction a(Parcel arg2) {
                return new CustomAction(arg2);
            }

            public CustomAction[] a(int arg1) {
                return new CustomAction[arg1];
            }

            public Object createFromParcel(Parcel arg1) {
                return this.a(arg1);
            }

            public Object[] newArray(int arg1) {
                return this.a(arg1);
            }
        }

        public static final Parcelable$Creator CREATOR;
        private final String a;
        private final CharSequence b;
        private final int c;
        private final Bundle d;
        private Object e;

        static {
            CustomAction.CREATOR = new android.support.v4.media.session.PlaybackStateCompat$CustomAction$1();
        }

        CustomAction(Parcel arg2) {
            super();
            this.a = arg2.readString();
            this.b = TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(arg2);
            this.c = arg2.readInt();
            this.d = arg2.readBundle(MediaSessionCompat.class.getClassLoader());
        }

        CustomAction(String arg1, CharSequence arg2, int arg3, Bundle arg4) {
            super();
            this.a = arg1;
            this.b = arg2;
            this.c = arg3;
            this.d = arg4;
        }

        public static CustomAction a(Object arg5) {
            if(arg5 != null) {
                if(Build$VERSION.SDK_INT < 21) {
                }
                else {
                    CustomAction v0 = new CustomAction(a.a(arg5), a.b(arg5), a.c(arg5), a.d(arg5));
                    v0.e = arg5;
                    return v0;
                }
            }

            return null;
        }

        public int describeContents() {
            return 0;
        }

        public String toString() {
            return "Action:mName=\'" + this.b + ", mIcon=" + this.c + ", mExtras=" + this.d;
        }

        public void writeToParcel(Parcel arg2, int arg3) {
            arg2.writeString(this.a);
            TextUtils.writeToParcel(this.b, arg2, arg3);
            arg2.writeInt(this.c);
            arg2.writeBundle(this.d);
        }
    }

    public static final Parcelable$Creator CREATOR;
    final int a;
    final long b;
    final long c;
    final float d;
    final long e;
    final int f;
    final CharSequence g;
    final long h;
    List i;
    final long j;
    final Bundle k;
    private Object l;

    static {
        PlaybackStateCompat.CREATOR = new android.support.v4.media.session.PlaybackStateCompat$1();
    }

    PlaybackStateCompat(int arg4, long arg5, long arg7, float arg9, long arg10, int arg12, CharSequence arg13, long arg14, List arg16, long arg17, Bundle arg19) {
        super();
        this.a = arg4;
        this.b = arg5;
        this.c = arg7;
        this.d = arg9;
        this.e = arg10;
        this.f = arg12;
        this.g = arg13;
        this.h = arg14;
        this.i = new ArrayList(arg16);
        this.j = arg17;
        this.k = arg19;
    }

    PlaybackStateCompat(Parcel arg3) {
        super();
        this.a = arg3.readInt();
        this.b = arg3.readLong();
        this.d = arg3.readFloat();
        this.h = arg3.readLong();
        this.c = arg3.readLong();
        this.e = arg3.readLong();
        this.g = TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(arg3);
        this.i = arg3.createTypedArrayList(CustomAction.CREATOR);
        this.j = arg3.readLong();
        this.k = arg3.readBundle(MediaSessionCompat.class.getClassLoader());
        this.f = arg3.readInt();
    }

    public static PlaybackStateCompat a(Object arg22) {
        PlaybackStateCompat v1_1;
        ArrayList v18;
        Object v0 = arg22;
        Bundle v1 = null;
        if(v0 != null && Build$VERSION.SDK_INT >= 21) {
            List v2 = e.h(arg22);
            if(v2 != null) {
                ArrayList v3 = new ArrayList(v2.size());
                Iterator v2_1 = v2.iterator();
                while(v2_1.hasNext()) {
                    ((List)v3).add(CustomAction.a(v2_1.next()));
                }

                v18 = v3;
            }
            else {
                List v18_1 = ((List)v1);
            }

            if(Build$VERSION.SDK_INT >= 22) {
                v1 = f.a(arg22);
            }

            v1_1 = new PlaybackStateCompat(e.a(arg22), e.b(arg22), e.c(arg22), e.d(arg22), e.e(arg22), 0, e.f(arg22), e.g(arg22), ((List)v18), e.i(arg22), v1);
            v1_1.l = v0;
        }

        return v1_1;
    }

    public int describeContents() {
        return 0;
    }

    public String toString() {
        StringBuilder v0 = new StringBuilder("PlaybackState {");
        v0.append("state=");
        v0.append(this.a);
        v0.append(", position=");
        v0.append(this.b);
        v0.append(", buffered position=");
        v0.append(this.c);
        v0.append(", speed=");
        v0.append(this.d);
        v0.append(", updated=");
        v0.append(this.h);
        v0.append(", actions=");
        v0.append(this.e);
        v0.append(", error code=");
        v0.append(this.f);
        v0.append(", error message=");
        v0.append(this.g);
        v0.append(", custom actions=");
        v0.append(this.i);
        v0.append(", active item id=");
        v0.append(this.j);
        v0.append("}");
        return v0.toString();
    }

    public void writeToParcel(Parcel arg3, int arg4) {
        arg3.writeInt(this.a);
        arg3.writeLong(this.b);
        arg3.writeFloat(this.d);
        arg3.writeLong(this.h);
        arg3.writeLong(this.c);
        arg3.writeLong(this.e);
        TextUtils.writeToParcel(this.g, arg3, arg4);
        arg3.writeTypedList(this.i);
        arg3.writeLong(this.j);
        arg3.writeBundle(this.k);
        arg3.writeInt(this.f);
    }
}

