package com.google.android.exoplayer2.source;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;
import java.util.Arrays;

public final class TrackGroupArray implements Parcelable {
    final class com.google.android.exoplayer2.source.TrackGroupArray$1 implements Parcelable$Creator {
        com.google.android.exoplayer2.source.TrackGroupArray$1() {
            super();
        }

        public TrackGroupArray createFromParcel(Parcel arg2) {
            return new TrackGroupArray(arg2);
        }

        public Object createFromParcel(Parcel arg1) {
            return this.createFromParcel(arg1);
        }

        public TrackGroupArray[] newArray(int arg1) {
            return new TrackGroupArray[arg1];
        }

        public Object[] newArray(int arg1) {
            return this.newArray(arg1);
        }
    }

    public static final Parcelable$Creator CREATOR;
    public static final TrackGroupArray EMPTY;
    private int hashCode;
    public final int length;
    private final TrackGroup[] trackGroups;

    static {
        TrackGroupArray.EMPTY = new TrackGroupArray(new TrackGroup[0]);
        TrackGroupArray.CREATOR = new com.google.android.exoplayer2.source.TrackGroupArray$1();
    }

    public TrackGroupArray(TrackGroup[] arg1) {
        super();
        this.trackGroups = arg1;
        this.length = arg1.length;
    }

    TrackGroupArray(Parcel arg4) {
        super();
        this.length = arg4.readInt();
        this.trackGroups = new TrackGroup[this.length];
        int v0;
        for(v0 = 0; v0 < this.length; ++v0) {
            this.trackGroups[v0] = arg4.readParcelable(TrackGroup.class.getClassLoader());
        }
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object arg5) {
        boolean v0 = true;
        if(this == (((TrackGroupArray)arg5))) {
            return 1;
        }

        if(arg5 != null) {
            if(this.getClass() != arg5.getClass()) {
            }
            else {
                if(this.length != ((TrackGroupArray)arg5).length || !Arrays.equals(this.trackGroups, ((TrackGroupArray)arg5).trackGroups)) {
                    v0 = false;
                }
                else {
                }

                return v0;
            }
        }

        return 0;
    }

    public TrackGroup get(int arg2) {
        return this.trackGroups[arg2];
    }

    public int hashCode() {
        if(this.hashCode == 0) {
            this.hashCode = Arrays.hashCode(this.trackGroups);
        }

        return this.hashCode;
    }

    public int indexOf(TrackGroup arg3) {
        int v0;
        for(v0 = 0; v0 < this.length; ++v0) {
            if(this.trackGroups[v0] == arg3) {
                return v0;
            }
        }

        return -1;
    }

    public boolean isEmpty() {
        boolean v0 = this.length == 0 ? true : false;
        return v0;
    }

    public void writeToParcel(Parcel arg3, int arg4) {
        arg3.writeInt(this.length);
        int v0;
        for(v0 = 0; v0 < this.length; ++v0) {
            arg3.writeParcelable(this.trackGroups[v0], 0);
        }
    }
}

