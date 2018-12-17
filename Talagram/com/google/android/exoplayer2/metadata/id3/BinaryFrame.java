package com.google.android.exoplayer2.metadata.id3;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.exoplayer2.util.Util;
import java.util.Arrays;

public final class BinaryFrame extends Id3Frame {
    final class com.google.android.exoplayer2.metadata.id3.BinaryFrame$1 implements Parcelable$Creator {
        com.google.android.exoplayer2.metadata.id3.BinaryFrame$1() {
            super();
        }

        public BinaryFrame createFromParcel(Parcel arg2) {
            return new BinaryFrame(arg2);
        }

        public Object createFromParcel(Parcel arg1) {
            return this.createFromParcel(arg1);
        }

        public BinaryFrame[] newArray(int arg1) {
            return new BinaryFrame[arg1];
        }

        public Object[] newArray(int arg1) {
            return this.newArray(arg1);
        }
    }

    public static final Parcelable$Creator CREATOR;
    public final byte[] data;

    static {
        BinaryFrame.CREATOR = new com.google.android.exoplayer2.metadata.id3.BinaryFrame$1();
    }

    public BinaryFrame(String arg1, byte[] arg2) {
        super(arg1);
        this.data = arg2;
    }

    BinaryFrame(Parcel arg2) {
        super(Util.castNonNull(arg2.readString()));
        this.data = Util.castNonNull(arg2.createByteArray());
    }

    public boolean equals(Object arg5) {
        boolean v0 = true;
        if(this == (((BinaryFrame)arg5))) {
            return 1;
        }

        if(arg5 != null) {
            if(this.getClass() != arg5.getClass()) {
            }
            else {
                if(!this.id.equals(((BinaryFrame)arg5).id) || !Arrays.equals(this.data, ((BinaryFrame)arg5).data)) {
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
        return (527 + this.id.hashCode()) * 31 + Arrays.hashCode(this.data);
    }

    public void writeToParcel(Parcel arg1, int arg2) {
        arg1.writeString(this.id);
        arg1.writeByteArray(this.data);
    }
}

