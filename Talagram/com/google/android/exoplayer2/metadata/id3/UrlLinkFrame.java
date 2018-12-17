package com.google.android.exoplayer2.metadata.id3;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.exoplayer2.util.Util;

public final class UrlLinkFrame extends Id3Frame {
    final class com.google.android.exoplayer2.metadata.id3.UrlLinkFrame$1 implements Parcelable$Creator {
        com.google.android.exoplayer2.metadata.id3.UrlLinkFrame$1() {
            super();
        }

        public UrlLinkFrame createFromParcel(Parcel arg2) {
            return new UrlLinkFrame(arg2);
        }

        public Object createFromParcel(Parcel arg1) {
            return this.createFromParcel(arg1);
        }

        public UrlLinkFrame[] newArray(int arg1) {
            return new UrlLinkFrame[arg1];
        }

        public Object[] newArray(int arg1) {
            return this.newArray(arg1);
        }
    }

    public static final Parcelable$Creator CREATOR;
    public final String description;
    public final String url;

    static {
        UrlLinkFrame.CREATOR = new com.google.android.exoplayer2.metadata.id3.UrlLinkFrame$1();
    }

    public UrlLinkFrame(String arg1, String arg2, String arg3) {
        super(arg1);
        this.description = arg2;
        this.url = arg3;
    }

    UrlLinkFrame(Parcel arg2) {
        super(Util.castNonNull(arg2.readString()));
        this.description = arg2.readString();
        this.url = Util.castNonNull(arg2.readString());
    }

    public boolean equals(Object arg5) {
        boolean v0 = true;
        if(this == (((UrlLinkFrame)arg5))) {
            return 1;
        }

        if(arg5 != null) {
            if(this.getClass() != arg5.getClass()) {
            }
            else {
                if(!this.id.equals(((UrlLinkFrame)arg5).id) || !Util.areEqual(this.description, ((UrlLinkFrame)arg5).description) || !Util.areEqual(this.url, ((UrlLinkFrame)arg5).url)) {
                    v0 = false;
                }
                else {
                }

                return v0;
            }
        }

        return 0;
    }

    public int hashCode() {
        int v1 = (527 + this.id.hashCode()) * 31;
        int v2 = 0;
        int v0 = this.description != null ? this.description.hashCode() : 0;
        v1 = (v1 + v0) * 31;
        if(this.url != null) {
            v2 = this.url.hashCode();
        }

        return v1 + v2;
    }

    public String toString() {
        return this.id + ": url=" + this.url;
    }

    public void writeToParcel(Parcel arg1, int arg2) {
        arg1.writeString(this.id);
        arg1.writeString(this.description);
        arg1.writeString(this.url);
    }
}

