package com.google.android.exoplayer2.metadata.scte35;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.TimestampAdjuster;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class SpliceInsertCommand extends SpliceCommand {
    final class com.google.android.exoplayer2.metadata.scte35.SpliceInsertCommand$1 implements Parcelable$Creator {
        com.google.android.exoplayer2.metadata.scte35.SpliceInsertCommand$1() {
            super();
        }

        public SpliceInsertCommand createFromParcel(Parcel arg3) {
            return new SpliceInsertCommand(arg3, null);
        }

        public Object createFromParcel(Parcel arg1) {
            return this.createFromParcel(arg1);
        }

        public SpliceInsertCommand[] newArray(int arg1) {
            return new SpliceInsertCommand[arg1];
        }

        public Object[] newArray(int arg1) {
            return this.newArray(arg1);
        }
    }

    public final class ComponentSplice {
        public final long componentSplicePlaybackPositionUs;
        public final long componentSplicePts;
        public final int componentTag;

        ComponentSplice(int arg1, long arg2, long arg4, com.google.android.exoplayer2.metadata.scte35.SpliceInsertCommand$1 arg6) {
            this(arg1, arg2, arg4);
        }

        private ComponentSplice(int arg1, long arg2, long arg4) {
            super();
            this.componentTag = arg1;
            this.componentSplicePts = arg2;
            this.componentSplicePlaybackPositionUs = arg4;
        }

        public static ComponentSplice createFromParcel(Parcel arg7) {
            return new ComponentSplice(arg7.readInt(), arg7.readLong(), arg7.readLong());
        }

        public void writeToParcel(Parcel arg3) {
            arg3.writeInt(this.componentTag);
            arg3.writeLong(this.componentSplicePts);
            arg3.writeLong(this.componentSplicePlaybackPositionUs);
        }
    }

    public static final Parcelable$Creator CREATOR;
    public final boolean autoReturn;
    public final int availNum;
    public final int availsExpected;
    public final long breakDurationUs;
    public final List componentSpliceList;
    public final boolean outOfNetworkIndicator;
    public final boolean programSpliceFlag;
    public final long programSplicePlaybackPositionUs;
    public final long programSplicePts;
    public final boolean spliceEventCancelIndicator;
    public final long spliceEventId;
    public final boolean spliceImmediateFlag;
    public final int uniqueProgramId;

    static {
        SpliceInsertCommand.CREATOR = new com.google.android.exoplayer2.metadata.scte35.SpliceInsertCommand$1();
    }

    private SpliceInsertCommand(long arg4, boolean arg6, boolean arg7, boolean arg8, boolean arg9, long arg10, long arg12, List arg14, boolean arg15, long arg16, int arg18, int arg19, int arg20) {
        super();
        this.spliceEventId = arg4;
        this.spliceEventCancelIndicator = arg6;
        this.outOfNetworkIndicator = arg7;
        this.programSpliceFlag = arg8;
        this.spliceImmediateFlag = arg9;
        this.programSplicePts = arg10;
        this.programSplicePlaybackPositionUs = arg12;
        this.componentSpliceList = Collections.unmodifiableList(arg14);
        this.autoReturn = arg15;
        this.breakDurationUs = arg16;
        this.uniqueProgramId = arg18;
        this.availNum = arg19;
        this.availsExpected = arg20;
    }

    private SpliceInsertCommand(Parcel arg7) {
        super();
        this.spliceEventId = arg7.readLong();
        boolean v1 = false;
        boolean v0 = arg7.readByte() == 1 ? true : false;
        this.spliceEventCancelIndicator = v0;
        v0 = arg7.readByte() == 1 ? true : false;
        this.outOfNetworkIndicator = v0;
        v0 = arg7.readByte() == 1 ? true : false;
        this.programSpliceFlag = v0;
        v0 = arg7.readByte() == 1 ? true : false;
        this.spliceImmediateFlag = v0;
        this.programSplicePts = arg7.readLong();
        this.programSplicePlaybackPositionUs = arg7.readLong();
        int v0_1 = arg7.readInt();
        ArrayList v3 = new ArrayList(v0_1);
        int v4;
        for(v4 = 0; v4 < v0_1; ++v4) {
            ((List)v3).add(ComponentSplice.createFromParcel(arg7));
        }

        this.componentSpliceList = Collections.unmodifiableList(((List)v3));
        if(arg7.readByte() == 1) {
            v1 = true;
        }

        this.autoReturn = v1;
        this.breakDurationUs = arg7.readLong();
        this.uniqueProgramId = arg7.readInt();
        this.availNum = arg7.readInt();
        this.availsExpected = arg7.readInt();
    }

    SpliceInsertCommand(Parcel arg1, com.google.android.exoplayer2.metadata.scte35.SpliceInsertCommand$1 arg2) {
        this(arg1);
    }

    static SpliceInsertCommand parseFromSection(ParsableByteArray arg30, long arg31, TimestampAdjuster arg33) {
        List v12_2;
        long v14;
        boolean v13_1;
        long v8;
        boolean v7_1;
        boolean v5;
        int v18;
        int v17;
        int v16;
        boolean v27;
        long v7;
        ArrayList v15;
        TimestampAdjuster v0 = arg33;
        long v2 = arg30.readUnsignedInt();
        boolean v6 = (arg30.readUnsignedByte() & 128) != 0 ? true : false;
        List v1 = Collections.emptyList();
        if(!v6) {
            int v9 = arg30.readUnsignedByte();
            boolean v10 = (v9 & 128) != 0 ? true : false;
            boolean v11 = (v9 & 64) != 0 ? true : false;
            int v12 = (v9 & 32) != 0 ? 1 : 0;
            boolean v9_1 = (v9 & 16) != 0 ? true : false;
            long v13 = !v11 || (v9_1) ? -9223372036854775807L : TimeSignalCommand.parseSpliceTime(arg30, arg31);
            if(!v11) {
                int v1_1 = arg30.readUnsignedByte();
                v15 = new ArrayList(v1_1);
                int v4;
                for(v4 = 0; v4 < v1_1; ++v4) {
                    int v21 = arg30.readUnsignedByte();
                    v7 = !v9_1 ? TimeSignalCommand.parseSpliceTime(arg30, arg31) : -9223372036854775807L;
                    ((List)v15).add(new ComponentSplice(v21, v7, v0.adjustTsTimestamp(v7), null));
                }
            }
            else {
                List v15_1 = v1;
            }

            if(v12 != 0) {
                long v4_1 = ((long)arg30.readUnsignedByte());
                v27 = (128 & v4_1) != 0 ? true : false;
                v7 = ((v4_1 & 1) << 32 | arg30.readUnsignedInt()) * 1000 / 90;
            }
            else {
                v7 = -9223372036854775807L;
                v27 = false;
            }

            v16 = arg30.readUnsignedShort();
            v17 = arg30.readUnsignedByte();
            v18 = arg30.readUnsignedByte();
            v5 = v10;
            ArrayList v12_1 = v15;
            long v28 = v7;
            v7_1 = v9_1;
            v8 = v13;
            v13_1 = v27;
            v14 = v28;
            v27 = v11;
        }
        else {
            v12_2 = v1;
            v5 = false;
            v7_1 = false;
            v8 = -9223372036854775807L;
            v13_1 = false;
            v14 = -9223372036854775807L;
            v16 = 0;
            v17 = 0;
            v18 = 0;
            v27 = false;
        }

        return new SpliceInsertCommand(v2, v6, v5, v27, v7_1, v8, v0.adjustTsTimestamp(v8), v12_2, v13_1, v14, v16, v17, v18);
    }

    public void writeToParcel(Parcel arg3, int arg4) {
        arg3.writeLong(this.spliceEventId);
        arg3.writeByte(((byte)this.spliceEventCancelIndicator));
        arg3.writeByte(((byte)this.outOfNetworkIndicator));
        arg3.writeByte(((byte)this.programSpliceFlag));
        arg3.writeByte(((byte)this.spliceImmediateFlag));
        arg3.writeLong(this.programSplicePts);
        arg3.writeLong(this.programSplicePlaybackPositionUs);
        arg4 = this.componentSpliceList.size();
        arg3.writeInt(arg4);
        int v0;
        for(v0 = 0; v0 < arg4; ++v0) {
            this.componentSpliceList.get(v0).writeToParcel(arg3);
        }

        arg3.writeByte(((byte)this.autoReturn));
        arg3.writeLong(this.breakDurationUs);
        arg3.writeInt(this.uniqueProgramId);
        arg3.writeInt(this.availNum);
        arg3.writeInt(this.availsExpected);
    }
}

