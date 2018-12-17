package com.google.android.exoplayer2.metadata.id3;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.exoplayer2.util.Util;
import java.util.Arrays;

public final class GeobFrame extends Id3Frame {
    final class com.google.android.exoplayer2.metadata.id3.GeobFrame$1 implements Parcelable$Creator {
        com.google.android.exoplayer2.metadata.id3.GeobFrame$1() {
            super();
        }

        public GeobFrame createFromParcel(Parcel arg2) {
            return new GeobFrame(arg2);
        }

        public Object createFromParcel(Parcel arg1) {
            return this.createFromParcel(arg1);
        }

        public GeobFrame[] newArray(int arg1) {
            return new GeobFrame[arg1];
        }

        public Object[] newArray(int arg1) {
            return this.newArray(arg1);
        }
    }

    public static final Parcelable$Creator CREATOR = null;
    public static final String ID = "GEOB";
    public final byte[] data;
    public final String description;
    public final String filename;
    public final String mimeType;

    static {
        GeobFrame.CREATOR = new com.google.android.exoplayer2.metadata.id3.GeobFrame$1();
    }

    public GeobFrame(String arg2, String arg3, String arg4, byte[] arg5) {
        super("GEOB");
        this.mimeType = arg2;
        this.filename = arg3;
        this.description = arg4;
        this.data = arg5;
    }

    GeobFrame(Parcel arg2) {
        super("GEOB");
        this.mimeType = Util.castNonNull(arg2.readString());
        this.filename = Util.castNonNull(arg2.readString());
        this.description = Util.castNonNull(arg2.readString());
        this.data = Util.castNonNull(arg2.createByteArray());
    }

    public boolean equals(Object arg5) {
        boolean v0 = true;
        if(this == (((GeobFrame)arg5))) {
            return 1;
        }

        if(arg5 != null) {
            if(this.getClass() != arg5.getClass()) {
            }
            else {
                if(!Util.areEqual(this.mimeType, ((GeobFrame)arg5).mimeType) || !Util.areEqual(this.filename, ((GeobFrame)arg5).filename) || !Util.areEqual(this.description, ((GeobFrame)arg5).description) || !Arrays.equals(this.data, ((GeobFrame)arg5).data)) {
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
        int v0 = this.mimeType != null ? this.mimeType.hashCode() : 0;
        int v2 = (527 + v0) * 31;
        v0 = this.filename != null ? this.filename.hashCode() : 0;
        v2 = (v2 + v0) * 31;
        if(this.description != null) {
            v1 = this.description.hashCode();
        }

        return (v2 + v1) * 31 + Arrays.hashCode(this.data);
    }

    public String toString() {
        return this.id + ": mimeType=" + this.mimeType + ", filename=" + this.filename + ", description=" + this.description;
    }

    public void writeToParcel(Parcel arg1, int arg2) {
        arg1.writeString(this.mimeType);
        arg1.writeString(this.filename);
        arg1.writeString(this.description);
        arg1.writeByteArray(this.data);
    }
}

