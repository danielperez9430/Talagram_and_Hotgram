package com.google.android.exoplayer2.metadata.id3;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.exoplayer2.util.Util;

public final class InternalFrame extends Id3Frame {
    final class com.google.android.exoplayer2.metadata.id3.InternalFrame$1 implements Parcelable$Creator {
        com.google.android.exoplayer2.metadata.id3.InternalFrame$1() {
            super();
        }

        public InternalFrame createFromParcel(Parcel arg2) {
            return new InternalFrame(arg2);
        }

        public Object createFromParcel(Parcel arg1) {
            return this.createFromParcel(arg1);
        }

        public InternalFrame[] newArray(int arg1) {
            return new InternalFrame[arg1];
        }

        public Object[] newArray(int arg1) {
            return this.newArray(arg1);
        }
    }

    public static final Parcelable$Creator CREATOR = null;
    public static final String ID = "----";
    public final String description;
    public final String domain;
    public final String text;

    static {
        InternalFrame.CREATOR = new com.google.android.exoplayer2.metadata.id3.InternalFrame$1();
    }

    public InternalFrame(String arg2, String arg3, String arg4) {
        super("----");
        this.domain = arg2;
        this.description = arg3;
        this.text = arg4;
    }

    InternalFrame(Parcel arg2) {
        super("----");
        this.domain = Util.castNonNull(arg2.readString());
        this.description = Util.castNonNull(arg2.readString());
        this.text = Util.castNonNull(arg2.readString());
    }

    public boolean equals(Object arg5) {
        boolean v0 = true;
        if(this == (((InternalFrame)arg5))) {
            return 1;
        }

        if(arg5 != null) {
            if(this.getClass() != arg5.getClass()) {
            }
            else {
                if(!Util.areEqual(this.description, ((InternalFrame)arg5).description) || !Util.areEqual(this.domain, ((InternalFrame)arg5).domain) || !Util.areEqual(this.text, ((InternalFrame)arg5).text)) {
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
        int v1 = 0;
        int v0 = this.domain != null ? this.domain.hashCode() : 0;
        int v2 = (527 + v0) * 31;
        v0 = this.description != null ? this.description.hashCode() : 0;
        v2 = (v2 + v0) * 31;
        if(this.text != null) {
            v1 = this.text.hashCode();
        }

        return v2 + v1;
    }

    public String toString() {
        return this.id + ": domain=" + this.domain + ", description=" + this.description;
    }

    public void writeToParcel(Parcel arg1, int arg2) {
        arg1.writeString(this.id);
        arg1.writeString(this.domain);
        arg1.writeString(this.text);
    }
}

