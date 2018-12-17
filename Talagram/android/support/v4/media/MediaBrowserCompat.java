package android.support.v4.media;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.os.ResultReceiver;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

public final class MediaBrowserCompat {
    class CustomActionResultReceiver extends ResultReceiver {
        private final String d;
        private final Bundle e;
        private final a f;

        protected void a(int arg4, Bundle arg5) {
            if(this.f == null) {
                return;
            }

            MediaSessionCompat.a(arg5);
            switch(arg4) {
                case -1: {
                    this.f.c(this.d, this.e, arg5);
                    break;
                }
                case 0: {
                    this.f.b(this.d, this.e, arg5);
                    break;
                }
                case 1: {
                    this.f.a(this.d, this.e, arg5);
                    break;
                }
                default: {
                    Log.w("MediaBrowserCompat", "Unknown result code: " + arg4 + " (extras=" + this.e + ", resultData=" + arg5 + ")");
                    break;
                }
            }
        }
    }

    class ItemReceiver extends ResultReceiver {
        private final String d;
        private final b e;

        protected void a(int arg1, Bundle arg2) {
            MediaSessionCompat.a(arg2);
            if(arg1 == 0 && arg2 != null) {
                if(!arg2.containsKey("media_item")) {
                }
                else {
                    Parcelable v1 = arg2.getParcelable("media_item");
                    if(v1 == null || ((v1 instanceof MediaItem))) {
                        this.e.a(((MediaItem)v1));
                    }
                    else {
                        this.e.a(this.d);
                    }

                    return;
                }
            }

            this.e.a(this.d);
        }
    }

    public class MediaItem implements Parcelable {
        final class android.support.v4.media.MediaBrowserCompat$MediaItem$1 implements Parcelable$Creator {
            android.support.v4.media.MediaBrowserCompat$MediaItem$1() {
                super();
            }

            public MediaItem a(Parcel arg2) {
                return new MediaItem(arg2);
            }

            public MediaItem[] a(int arg1) {
                return new MediaItem[arg1];
            }

            public Object createFromParcel(Parcel arg1) {
                return this.a(arg1);
            }

            public Object[] newArray(int arg1) {
                return this.a(arg1);
            }
        }

        public static final Parcelable$Creator CREATOR;
        private final int a;
        private final MediaDescriptionCompat b;

        static {
            MediaItem.CREATOR = new android.support.v4.media.MediaBrowserCompat$MediaItem$1();
        }

        MediaItem(Parcel arg2) {
            super();
            this.a = arg2.readInt();
            this.b = MediaDescriptionCompat.CREATOR.createFromParcel(arg2);
        }

        public int describeContents() {
            return 0;
        }

        public String toString() {
            StringBuilder v0 = new StringBuilder("MediaItem{");
            v0.append("mFlags=");
            v0.append(this.a);
            v0.append(", mDescription=");
            v0.append(this.b);
            v0.append('}');
            return v0.toString();
        }

        public void writeToParcel(Parcel arg2, int arg3) {
            arg2.writeInt(this.a);
            this.b.writeToParcel(arg2, arg3);
        }
    }

    class SearchResultReceiver extends ResultReceiver {
        private final String d;
        private final Bundle e;
        private final c f;

        protected void a(int arg4, Bundle arg5) {
            MediaSessionCompat.a(arg5);
            if(arg4 == 0 && arg5 != null) {
                if(!arg5.containsKey("search_results")) {
                }
                else {
                    Parcelable[] v4 = arg5.getParcelableArray("search_results");
                    List v5 = null;
                    if(v4 != null) {
                        ArrayList v5_1 = new ArrayList();
                        int v0 = v4.length;
                        int v1;
                        for(v1 = 0; v1 < v0; ++v1) {
                            ((List)v5_1).add(v4[v1]);
                        }
                    }

                    this.f.a(this.d, this.e, v5);
                    return;
                }
            }

            this.f.a(this.d, this.e);
        }
    }

    public abstract class a {
        public void a(String arg1, Bundle arg2, Bundle arg3) {
        }

        public void b(String arg1, Bundle arg2, Bundle arg3) {
        }

        public void c(String arg1, Bundle arg2, Bundle arg3) {
        }
    }

    public abstract class b {
        public void a(MediaItem arg1) {
        }

        public void a(String arg1) {
        }
    }

    public abstract class c {
        public void a(String arg1, Bundle arg2) {
        }

        public void a(String arg1, Bundle arg2, List arg3) {
        }
    }

    static final boolean a;

    static {
        MediaBrowserCompat.a = Log.isLoggable("MediaBrowserCompat", 3);
    }
}

