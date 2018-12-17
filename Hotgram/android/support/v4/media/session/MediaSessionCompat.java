package android.support.v4.media.session;

import android.os.Build$VERSION;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;
import android.os.ResultReceiver;
import android.support.v4.media.MediaDescriptionCompat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MediaSessionCompat {
    public final class QueueItem implements Parcelable {
        final class android.support.v4.media.session.MediaSessionCompat$QueueItem$1 implements Parcelable$Creator {
            android.support.v4.media.session.MediaSessionCompat$QueueItem$1() {
                super();
            }

            public QueueItem a(Parcel arg2) {
                return new QueueItem(arg2);
            }

            public QueueItem[] a(int arg1) {
                return new QueueItem[arg1];
            }

            public Object createFromParcel(Parcel arg1) {
                return this.a(arg1);
            }

            public Object[] newArray(int arg1) {
                return this.a(arg1);
            }
        }

        public static final Parcelable$Creator CREATOR;
        private final MediaDescriptionCompat a;
        private final long b;
        private Object c;

        static {
            QueueItem.CREATOR = new android.support.v4.media.session.MediaSessionCompat$QueueItem$1();
        }

        QueueItem(Parcel arg3) {
            super();
            this.a = MediaDescriptionCompat.CREATOR.createFromParcel(arg3);
            this.b = arg3.readLong();
        }

        private QueueItem(Object arg4, MediaDescriptionCompat arg5, long arg6) {
            super();
            if(arg5 != null) {
                if(arg6 != -1) {
                    this.a = arg5;
                    this.b = arg6;
                    this.c = arg4;
                    return;
                }

                throw new IllegalArgumentException("Id cannot be QueueItem.UNKNOWN_ID");
            }

            throw new IllegalArgumentException("Description cannot be null.");
        }

        public static List a(List arg2) {
            if(arg2 != null) {
                if(Build$VERSION.SDK_INT < 21) {
                }
                else {
                    ArrayList v0 = new ArrayList();
                    Iterator v2 = arg2.iterator();
                    while(v2.hasNext()) {
                        ((List)v0).add(QueueItem.a(v2.next()));
                    }

                    return ((List)v0);
                }
            }

            return null;
        }

        public static QueueItem a(Object arg4) {
            if(arg4 != null) {
                if(Build$VERSION.SDK_INT < 21) {
                }
                else {
                    return new QueueItem(arg4, MediaDescriptionCompat.a(a.a(arg4)), a.b(arg4));
                }
            }

            return null;
        }

        public int describeContents() {
            return 0;
        }

        public String toString() {
            return "MediaSession.QueueItem {Description=" + this.a + ", Id=" + this.b + " }";
        }

        public void writeToParcel(Parcel arg3, int arg4) {
            this.a.writeToParcel(arg3, arg4);
            arg3.writeLong(this.b);
        }
    }

    public final class ResultReceiverWrapper implements Parcelable {
        final class android.support.v4.media.session.MediaSessionCompat$ResultReceiverWrapper$1 implements Parcelable$Creator {
            android.support.v4.media.session.MediaSessionCompat$ResultReceiverWrapper$1() {
                super();
            }

            public ResultReceiverWrapper a(Parcel arg2) {
                return new ResultReceiverWrapper(arg2);
            }

            public ResultReceiverWrapper[] a(int arg1) {
                return new ResultReceiverWrapper[arg1];
            }

            public Object createFromParcel(Parcel arg1) {
                return this.a(arg1);
            }

            public Object[] newArray(int arg1) {
                return this.a(arg1);
            }
        }

        public static final Parcelable$Creator CREATOR;
        ResultReceiver a;

        static {
            ResultReceiverWrapper.CREATOR = new android.support.v4.media.session.MediaSessionCompat$ResultReceiverWrapper$1();
        }

        ResultReceiverWrapper(Parcel arg2) {
            super();
            this.a = ResultReceiver.CREATOR.createFromParcel(arg2);
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel arg2, int arg3) {
            this.a.writeToParcel(arg2, arg3);
        }
    }

    public final class Token implements Parcelable {
        final class android.support.v4.media.session.MediaSessionCompat$Token$1 implements Parcelable$Creator {
            android.support.v4.media.session.MediaSessionCompat$Token$1() {
                super();
            }

            public Token a(Parcel arg3) {
                IBinder v3_1;
                if(Build$VERSION.SDK_INT >= 21) {
                    Parcelable v3 = arg3.readParcelable(null);
                }
                else {
                    v3_1 = arg3.readStrongBinder();
                }

                return new Token(v3_1);
            }

            public Token[] a(int arg1) {
                return new Token[arg1];
            }

            public Object createFromParcel(Parcel arg1) {
                return this.a(arg1);
            }

            public Object[] newArray(int arg1) {
                return this.a(arg1);
            }
        }

        public static final Parcelable$Creator CREATOR;
        private final Object a;
        private b b;
        private Bundle c;

        static {
            Token.CREATOR = new android.support.v4.media.session.MediaSessionCompat$Token$1();
        }

        Token(Object arg2) {
            this(arg2, null, null);
        }

        Token(Object arg1, b arg2, Bundle arg3) {
            super();
            this.a = arg1;
            this.b = arg2;
            this.c = arg3;
        }

        public b a() {
            return this.b;
        }

        public void a(b arg1) {
            this.b = arg1;
        }

        public void a(Bundle arg1) {
            this.c = arg1;
        }

        public int describeContents() {
            return 0;
        }

        public boolean equals(Object arg4) {
            boolean v0 = true;
            if(this == (((Token)arg4))) {
                return 1;
            }

            if(!(arg4 instanceof Token)) {
                return 0;
            }

            if(this.a == null) {
                if(((Token)arg4).a == null) {
                }
                else {
                    v0 = false;
                }

                return v0;
            }

            if(((Token)arg4).a == null) {
                return 0;
            }

            return this.a.equals(((Token)arg4).a);
        }

        public int hashCode() {
            if(this.a == null) {
                return 0;
            }

            return this.a.hashCode();
        }

        public void writeToParcel(Parcel arg3, int arg4) {
            if(Build$VERSION.SDK_INT >= 21) {
                arg3.writeParcelable(this.a, arg4);
            }
            else {
                arg3.writeStrongBinder(this.a);
            }
        }
    }

    public static void a(Bundle arg1) {
        if(arg1 != null) {
            arg1.setClassLoader(MediaSessionCompat.class.getClassLoader());
        }
    }
}

