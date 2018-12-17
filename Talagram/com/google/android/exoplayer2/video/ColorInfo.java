package com.google.android.exoplayer2.video;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;
import com.google.android.exoplayer2.util.Util;
import java.util.Arrays;

public final class ColorInfo implements Parcelable {
    final class com.google.android.exoplayer2.video.ColorInfo$1 implements Parcelable$Creator {
        com.google.android.exoplayer2.video.ColorInfo$1() {
            super();
        }

        public ColorInfo createFromParcel(Parcel arg2) {
            return new ColorInfo(arg2);
        }

        public Object createFromParcel(Parcel arg1) {
            return this.createFromParcel(arg1);
        }

        public ColorInfo[] newArray(int arg1) {
            return new ColorInfo[0];
        }

        public Object[] newArray(int arg1) {
            return this.newArray(arg1);
        }
    }

    public static final Parcelable$Creator CREATOR;
    public final int colorRange;
    public final int colorSpace;
    public final int colorTransfer;
    private int hashCode;
    public final byte[] hdrStaticInfo;

    static {
        ColorInfo.CREATOR = new com.google.android.exoplayer2.video.ColorInfo$1();
    }

    public ColorInfo(int arg1, int arg2, int arg3, byte[] arg4) {
        super();
        this.colorSpace = arg1;
        this.colorRange = arg2;
        this.colorTransfer = arg3;
        this.hdrStaticInfo = arg4;
    }

    ColorInfo(Parcel arg2) {
        super();
        this.colorSpace = arg2.readInt();
        this.colorRange = arg2.readInt();
        this.colorTransfer = arg2.readInt();
        byte[] v2 = Util.readBoolean(arg2) ? arg2.createByteArray() : null;
        this.hdrStaticInfo = v2;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object arg5) {
        boolean v0 = true;
        if(this == (((ColorInfo)arg5))) {
            return 1;
        }

        if(arg5 != null) {
            if(this.getClass() != arg5.getClass()) {
            }
            else {
                if(this.colorSpace != ((ColorInfo)arg5).colorSpace || this.colorRange != ((ColorInfo)arg5).colorRange || this.colorTransfer != ((ColorInfo)arg5).colorTransfer || !Arrays.equals(this.hdrStaticInfo, ((ColorInfo)arg5).hdrStaticInfo)) {
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
        if(this.hashCode == 0) {
            this.hashCode = (((527 + this.colorSpace) * 31 + this.colorRange) * 31 + this.colorTransfer) * 31 + Arrays.hashCode(this.hdrStaticInfo);
        }

        return this.hashCode;
    }

    public String toString() {
        StringBuilder v0 = new StringBuilder();
        v0.append("ColorInfo(");
        v0.append(this.colorSpace);
        v0.append(", ");
        v0.append(this.colorRange);
        v0.append(", ");
        v0.append(this.colorTransfer);
        v0.append(", ");
        boolean v1 = this.hdrStaticInfo != null ? true : false;
        v0.append(v1);
        v0.append(")");
        return v0.toString();
    }

    public void writeToParcel(Parcel arg1, int arg2) {
        arg1.writeInt(this.colorSpace);
        arg1.writeInt(this.colorRange);
        arg1.writeInt(this.colorTransfer);
        boolean v2 = this.hdrStaticInfo != null ? true : false;
        Util.writeBoolean(arg1, v2);
        if(this.hdrStaticInfo != null) {
            arg1.writeByteArray(this.hdrStaticInfo);
        }
    }
}

