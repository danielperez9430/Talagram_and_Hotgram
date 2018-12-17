package com.google.android.exoplayer2.source;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.util.Assertions;
import java.util.Arrays;

public final class TrackGroup implements Parcelable {
    final class com.google.android.exoplayer2.source.TrackGroup$1 implements Parcelable$Creator {
        com.google.android.exoplayer2.source.TrackGroup$1() {
            super();
        }

        public TrackGroup createFromParcel(Parcel arg2) {
            return new TrackGroup(arg2);
        }

        public Object createFromParcel(Parcel arg1) {
            return this.createFromParcel(arg1);
        }

        public TrackGroup[] newArray(int arg1) {
            return new TrackGroup[arg1];
        }

        public Object[] newArray(int arg1) {
            return this.newArray(arg1);
        }
    }

    public static final Parcelable$Creator CREATOR;
    private final Format[] formats;
    private int hashCode;
    public final int length;

    static {
        TrackGroup.CREATOR = new com.google.android.exoplayer2.source.TrackGroup$1();
    }

    TrackGroup(Parcel arg4) {
        super();
        this.length = arg4.readInt();
        this.formats = new Format[this.length];
        int v0;
        for(v0 = 0; v0 < this.length; ++v0) {
            this.formats[v0] = arg4.readParcelable(Format.class.getClassLoader());
        }
    }

    public TrackGroup(Format[] arg2) {
        super();
        boolean v0 = arg2.length > 0 ? true : false;
        Assertions.checkState(v0);
        this.formats = arg2;
        this.length = arg2.length;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object arg5) {
        boolean v0 = true;
        if(this == (((TrackGroup)arg5))) {
            return 1;
        }

        if(arg5 != null) {
            if(this.getClass() != arg5.getClass()) {
            }
            else {
                if(this.length != ((TrackGroup)arg5).length || !Arrays.equals(this.formats, ((TrackGroup)arg5).formats)) {
                    v0 = false;
                }
                else {
                }

                return v0;
            }
        }

        return 0;
    }

    public Format getFormat(int arg2) {
        return this.formats[arg2];
    }

    public int hashCode() {
        if(this.hashCode == 0) {
            this.hashCode = 527 + Arrays.hashCode(this.formats);
        }

        return this.hashCode;
    }

    public int indexOf(Format arg3) {
        int v0;
        for(v0 = 0; v0 < this.formats.length; ++v0) {
            if(arg3 == this.formats[v0]) {
                return v0;
            }
        }

        return -1;
    }

    public void writeToParcel(Parcel arg3, int arg4) {
        arg3.writeInt(this.length);
        int v0;
        for(v0 = 0; v0 < this.length; ++v0) {
            arg3.writeParcelable(this.formats[v0], 0);
        }
    }
}

