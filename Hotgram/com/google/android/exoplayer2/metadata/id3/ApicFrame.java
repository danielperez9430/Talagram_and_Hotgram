package com.google.android.exoplayer2.metadata.id3;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.exoplayer2.util.Util;
import java.util.Arrays;

public final class ApicFrame extends Id3Frame {
    final class com.google.android.exoplayer2.metadata.id3.ApicFrame$1 implements Parcelable$Creator {
        com.google.android.exoplayer2.metadata.id3.ApicFrame$1() {
            super();
        }

        public ApicFrame createFromParcel(Parcel arg2) {
            return new ApicFrame(arg2);
        }

        public Object createFromParcel(Parcel arg1) {
            return this.createFromParcel(arg1);
        }

        public ApicFrame[] newArray(int arg1) {
            return new ApicFrame[arg1];
        }

        public Object[] newArray(int arg1) {
            return this.newArray(arg1);
        }
    }

    public static final Parcelable$Creator CREATOR = null;
    public static final String ID = "APIC";
    public final String description;
    public final String mimeType;
    public final byte[] pictureData;
    public final int pictureType;

    static {
        ApicFrame.CREATOR = new com.google.android.exoplayer2.metadata.id3.ApicFrame$1();
    }

    public ApicFrame(String arg2, String arg3, int arg4, byte[] arg5) {
        super("APIC");
        this.mimeType = arg2;
        this.description = arg3;
        this.pictureType = arg4;
        this.pictureData = arg5;
    }

    ApicFrame(Parcel arg2) {
        super("APIC");
        this.mimeType = Util.castNonNull(arg2.readString());
        this.description = Util.castNonNull(arg2.readString());
        this.pictureType = arg2.readInt();
        this.pictureData = Util.castNonNull(arg2.createByteArray());
    }

    public boolean equals(Object arg5) {
        boolean v0 = true;
        if(this == (((ApicFrame)arg5))) {
            return 1;
        }

        if(arg5 != null) {
            if(this.getClass() != arg5.getClass()) {
            }
            else {
                if(this.pictureType != ((ApicFrame)arg5).pictureType || !Util.areEqual(this.mimeType, ((ApicFrame)arg5).mimeType) || !Util.areEqual(this.description, ((ApicFrame)arg5).description) || !Arrays.equals(this.pictureData, ((ApicFrame)arg5).pictureData)) {
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
        int v1 = (527 + this.pictureType) * 31;
        int v2 = 0;
        int v0 = this.mimeType != null ? this.mimeType.hashCode() : 0;
        v1 = (v1 + v0) * 31;
        if(this.description != null) {
            v2 = this.description.hashCode();
        }

        return (v1 + v2) * 31 + Arrays.hashCode(this.pictureData);
    }

    public String toString() {
        return this.id + ": mimeType=" + this.mimeType + ", description=" + this.description;
    }

    public void writeToParcel(Parcel arg1, int arg2) {
        arg1.writeString(this.mimeType);
        arg1.writeString(this.description);
        arg1.writeInt(this.pictureType);
        arg1.writeByteArray(this.pictureData);
    }
}

