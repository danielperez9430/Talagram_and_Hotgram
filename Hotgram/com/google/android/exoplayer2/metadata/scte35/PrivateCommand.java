package com.google.android.exoplayer2.metadata.scte35;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.exoplayer2.util.ParsableByteArray;

public final class PrivateCommand extends SpliceCommand {
    final class com.google.android.exoplayer2.metadata.scte35.PrivateCommand$1 implements Parcelable$Creator {
        com.google.android.exoplayer2.metadata.scte35.PrivateCommand$1() {
            super();
        }

        public PrivateCommand createFromParcel(Parcel arg3) {
            return new PrivateCommand(arg3, null);
        }

        public Object createFromParcel(Parcel arg1) {
            return this.createFromParcel(arg1);
        }

        public PrivateCommand[] newArray(int arg1) {
            return new PrivateCommand[arg1];
        }

        public Object[] newArray(int arg1) {
            return this.newArray(arg1);
        }
    }

    public static final Parcelable$Creator CREATOR;
    public final byte[] commandBytes;
    public final long identifier;
    public final long ptsAdjustment;

    static {
        PrivateCommand.CREATOR = new com.google.android.exoplayer2.metadata.scte35.PrivateCommand$1();
    }

    private PrivateCommand(long arg1, byte[] arg3, long arg4) {
        super();
        this.ptsAdjustment = arg4;
        this.identifier = arg1;
        this.commandBytes = arg3;
    }

    private PrivateCommand(Parcel arg3) {
        super();
        this.ptsAdjustment = arg3.readLong();
        this.identifier = arg3.readLong();
        this.commandBytes = new byte[arg3.readInt()];
        arg3.readByteArray(this.commandBytes);
    }

    PrivateCommand(Parcel arg1, com.google.android.exoplayer2.metadata.scte35.PrivateCommand$1 arg2) {
        this(arg1);
    }

    static PrivateCommand parseFromSection(ParsableByteArray arg6, int arg7, long arg8) {
        long v1 = arg6.readUnsignedInt();
        byte[] v3 = new byte[arg7 - 4];
        arg6.readBytes(v3, 0, v3.length);
        return new PrivateCommand(v1, v3, arg8);
    }

    public void writeToParcel(Parcel arg3, int arg4) {
        arg3.writeLong(this.ptsAdjustment);
        arg3.writeLong(this.identifier);
        arg3.writeInt(this.commandBytes.length);
        arg3.writeByteArray(this.commandBytes);
    }
}

