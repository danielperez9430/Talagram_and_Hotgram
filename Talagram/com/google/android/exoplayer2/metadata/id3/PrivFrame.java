package com.google.android.exoplayer2.metadata.id3;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.exoplayer2.util.Util;
import java.util.Arrays;

public final class PrivFrame extends Id3Frame {
    final class com.google.android.exoplayer2.metadata.id3.PrivFrame$1 implements Parcelable$Creator {
        com.google.android.exoplayer2.metadata.id3.PrivFrame$1() {
            super();
        }

        public PrivFrame createFromParcel(Parcel arg2) {
            return new PrivFrame(arg2);
        }

        public Object createFromParcel(Parcel arg1) {
            return this.createFromParcel(arg1);
        }

        public PrivFrame[] newArray(int arg1) {
            return new PrivFrame[arg1];
        }

        public Object[] newArray(int arg1) {
            return this.newArray(arg1);
        }
    }

    public static final Parcelable$Creator CREATOR = null;
    public static final String ID = "PRIV";
    public final String owner;
    public final byte[] privateData;

    static {
        PrivFrame.CREATOR = new com.google.android.exoplayer2.metadata.id3.PrivFrame$1();
    }

    public PrivFrame(String arg2, byte[] arg3) {
        super("PRIV");
        this.owner = arg2;
        this.privateData = arg3;
    }

    PrivFrame(Parcel arg2) {
        super("PRIV");
        this.owner = Util.castNonNull(arg2.readString());
        this.privateData = Util.castNonNull(arg2.createByteArray());
    }

    public boolean equals(Object arg5) {
        boolean v0 = true;
        if(this == (((PrivFrame)arg5))) {
            return 1;
        }

        if(arg5 != null) {
            if(this.getClass() != arg5.getClass()) {
            }
            else {
                if(!Util.areEqual(this.owner, ((PrivFrame)arg5).owner) || !Arrays.equals(this.privateData, ((PrivFrame)arg5).privateData)) {
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
        int v0 = this.owner != null ? this.owner.hashCode() : 0;
        return (527 + v0) * 31 + Arrays.hashCode(this.privateData);
    }

    public String toString() {
        return this.id + ": owner=" + this.owner;
    }

    public void writeToParcel(Parcel arg1, int arg2) {
        arg1.writeString(this.owner);
        arg1.writeByteArray(this.privateData);
    }
}

