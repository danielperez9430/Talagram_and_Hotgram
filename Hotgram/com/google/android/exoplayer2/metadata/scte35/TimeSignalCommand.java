package com.google.android.exoplayer2.metadata.scte35;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.TimestampAdjuster;

public final class TimeSignalCommand extends SpliceCommand {
    final class com.google.android.exoplayer2.metadata.scte35.TimeSignalCommand$1 implements Parcelable$Creator {
        com.google.android.exoplayer2.metadata.scte35.TimeSignalCommand$1() {
            super();
        }

        public TimeSignalCommand createFromParcel(Parcel arg8) {
            return new TimeSignalCommand(arg8.readLong(), arg8.readLong(), null);
        }

        public Object createFromParcel(Parcel arg1) {
            return this.createFromParcel(arg1);
        }

        public TimeSignalCommand[] newArray(int arg1) {
            return new TimeSignalCommand[arg1];
        }

        public Object[] newArray(int arg1) {
            return this.newArray(arg1);
        }
    }

    public static final Parcelable$Creator CREATOR;
    public final long playbackPositionUs;
    public final long ptsTime;

    static {
        TimeSignalCommand.CREATOR = new com.google.android.exoplayer2.metadata.scte35.TimeSignalCommand$1();
    }

    private TimeSignalCommand(long arg1, long arg3) {
        super();
        this.ptsTime = arg1;
        this.playbackPositionUs = arg3;
    }

    TimeSignalCommand(long arg1, long arg3, com.google.android.exoplayer2.metadata.scte35.TimeSignalCommand$1 arg5) {
        this(arg1, arg3);
    }

    static TimeSignalCommand parseFromSection(ParsableByteArray arg1, long arg2, TimestampAdjuster arg4) {
        long v1 = TimeSignalCommand.parseSpliceTime(arg1, arg2);
        return new TimeSignalCommand(v1, arg4.adjustTsTimestamp(v1));
    }

    static long parseSpliceTime(ParsableByteArray arg7, long arg8) {
        long v0 = ((long)arg7.readUnsignedByte());
        long v7 = (128 & v0) != 0 ? 8589934591L & ((v0 & 1) << 32 | arg7.readUnsignedInt()) + arg8 : -9223372036854775807L;
        return v7;
    }

    public void writeToParcel(Parcel arg3, int arg4) {
        arg3.writeLong(this.ptsTime);
        arg3.writeLong(this.playbackPositionUs);
    }
}

