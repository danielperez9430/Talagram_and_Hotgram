package com.google.android.exoplayer2.metadata.scte35;

import android.os.Parcel;
import android.os.Parcelable$Creator;

public final class SpliceNullCommand extends SpliceCommand {
    final class com.google.android.exoplayer2.metadata.scte35.SpliceNullCommand$1 implements Parcelable$Creator {
        com.google.android.exoplayer2.metadata.scte35.SpliceNullCommand$1() {
            super();
        }

        public SpliceNullCommand createFromParcel(Parcel arg1) {
            return new SpliceNullCommand();
        }

        public Object createFromParcel(Parcel arg1) {
            return this.createFromParcel(arg1);
        }

        public SpliceNullCommand[] newArray(int arg1) {
            return new SpliceNullCommand[arg1];
        }

        public Object[] newArray(int arg1) {
            return this.newArray(arg1);
        }
    }

    public static final Parcelable$Creator CREATOR;

    static {
        SpliceNullCommand.CREATOR = new com.google.android.exoplayer2.metadata.scte35.SpliceNullCommand$1();
    }

    public SpliceNullCommand() {
        super();
    }

    public void writeToParcel(Parcel arg1, int arg2) {
    }
}

