package com.google.android.exoplayer2.metadata.scte35;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.exoplayer2.util.ParsableByteArray;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class SpliceScheduleCommand extends SpliceCommand {
    final class com.google.android.exoplayer2.metadata.scte35.SpliceScheduleCommand$1 implements Parcelable$Creator {
        com.google.android.exoplayer2.metadata.scte35.SpliceScheduleCommand$1() {
            super();
        }

        public SpliceScheduleCommand createFromParcel(Parcel arg3) {
            return new SpliceScheduleCommand(arg3, null);
        }

        public Object createFromParcel(Parcel arg1) {
            return this.createFromParcel(arg1);
        }

        public SpliceScheduleCommand[] newArray(int arg1) {
            return new SpliceScheduleCommand[arg1];
        }

        public Object[] newArray(int arg1) {
            return this.newArray(arg1);
        }
    }

    public final class ComponentSplice {
        public final int componentTag;
        public final long utcSpliceTime;

        private ComponentSplice(int arg1, long arg2) {
            super();
            this.componentTag = arg1;
            this.utcSpliceTime = arg2;
        }

        ComponentSplice(int arg1, long arg2, com.google.android.exoplayer2.metadata.scte35.SpliceScheduleCommand$1 arg4) {
            this(arg1, arg2);
        }

        static ComponentSplice access$000(Parcel arg0) {
            return ComponentSplice.createFromParcel(arg0);
        }

        static void access$200(ComponentSplice arg0, Parcel arg1) {
            arg0.writeToParcel(arg1);
        }

        private static ComponentSplice createFromParcel(Parcel arg4) {
            return new ComponentSplice(arg4.readInt(), arg4.readLong());
        }

        private void writeToParcel(Parcel arg3) {
            arg3.writeInt(this.componentTag);
            arg3.writeLong(this.utcSpliceTime);
        }
    }

    public final class Event {
        public final boolean autoReturn;
        public final int availNum;
        public final int availsExpected;
        public final long breakDurationUs;
        public final List componentSpliceList;
        public final boolean outOfNetworkIndicator;
        public final boolean programSpliceFlag;
        public final boolean spliceEventCancelIndicator;
        public final long spliceEventId;
        public final int uniqueProgramId;
        public final long utcSpliceTime;

        private Event(long arg1, boolean arg3, boolean arg4, boolean arg5, List arg6, long arg7, boolean arg9, long arg10, int arg12, int arg13, int arg14) {
            super();
            this.spliceEventId = arg1;
            this.spliceEventCancelIndicator = arg3;
            this.outOfNetworkIndicator = arg4;
            this.programSpliceFlag = arg5;
            this.componentSpliceList = Collections.unmodifiableList(arg6);
            this.utcSpliceTime = arg7;
            this.autoReturn = arg9;
            this.breakDurationUs = arg10;
            this.uniqueProgramId = arg12;
            this.availNum = arg13;
            this.availsExpected = arg14;
        }

        private Event(Parcel arg7) {
            super();
            this.spliceEventId = arg7.readLong();
            boolean v1 = false;
            boolean v0 = arg7.readByte() == 1 ? true : false;
            this.spliceEventCancelIndicator = v0;
            v0 = arg7.readByte() == 1 ? true : false;
            this.outOfNetworkIndicator = v0;
            v0 = arg7.readByte() == 1 ? true : false;
            this.programSpliceFlag = v0;
            int v0_1 = arg7.readInt();
            ArrayList v3 = new ArrayList(v0_1);
            int v4;
            for(v4 = 0; v4 < v0_1; ++v4) {
                v3.add(ComponentSplice.createFromParcel(arg7));
            }

            this.componentSpliceList = Collections.unmodifiableList(((List)v3));
            this.utcSpliceTime = arg7.readLong();
            if(arg7.readByte() == 1) {
                v1 = true;
            }

            this.autoReturn = v1;
            this.breakDurationUs = arg7.readLong();
            this.uniqueProgramId = arg7.readInt();
            this.availNum = arg7.readInt();
            this.availsExpected = arg7.readInt();
        }

        static Event access$300(Parcel arg0) {
            return Event.createFromParcel(arg0);
        }

        static Event access$400(ParsableByteArray arg0) {
            return Event.parseFromSection(arg0);
        }

        static void access$500(Event arg0, Parcel arg1) {
            arg0.writeToParcel(arg1);
        }

        private static Event createFromParcel(Parcel arg1) {
            return new Event(arg1);
        }

        private static Event parseFromSection(ParsableByteArray arg20) {
            boolean v4_1;
            int v13;
            int v12_1;
            ArrayList v6;
            long v10_1;
            long v7;
            boolean v19;
            int v14_1;
            long v17;
            boolean v16;
            boolean v9;
            long v1 = arg20.readUnsignedInt();
            boolean v5 = (arg20.readUnsignedByte() & 128) != 0 ? true : false;
            ArrayList v0 = new ArrayList();
            if(!v5) {
                int v8 = arg20.readUnsignedByte();
                v9 = (v8 & 128) != 0 ? true : false;
                boolean v10 = (v8 & 64) != 0 ? true : false;
                v8 = (v8 & 32) != 0 ? 1 : 0;
                long v12 = v10 ? arg20.readUnsignedInt() : -9223372036854775807L;
                if(!v10) {
                    int v0_1 = arg20.readUnsignedByte();
                    ArrayList v14 = new ArrayList(v0_1);
                    int v15;
                    for(v15 = 0; v15 < v0_1; ++v15) {
                        v14.add(new ComponentSplice(arg20.readUnsignedByte(), arg20.readUnsignedInt(), null));
                    }

                    v0 = v14;
                }

                if(v8 != 0) {
                    long v3 = ((long)arg20.readUnsignedByte());
                    v16 = (128 & v3) != 0 ? true : false;
                    v17 = ((v3 & 1) << 32 | arg20.readUnsignedInt()) * 1000 / 90;
                }
                else {
                    v16 = false;
                    v17 = -9223372036854775807L;
                }

                int v3_1 = arg20.readUnsignedShort();
                int v4 = arg20.readUnsignedByte();
                v14_1 = arg20.readUnsignedByte();
                v19 = v10;
                v7 = v12;
                v10_1 = v17;
                v6 = v0;
                v12_1 = v3_1;
                v13 = v4;
                v4_1 = v9;
                v9 = v16;
            }
            else {
                v6 = v0;
                v4_1 = false;
                v7 = -9223372036854775807L;
                v9 = false;
                v10_1 = -9223372036854775807L;
                v12_1 = 0;
                v13 = 0;
                v14_1 = 0;
                v19 = false;
            }

            return new Event(v1, v5, v4_1, v19, ((List)v6), v7, v9, v10_1, v12_1, v13, v14_1);
        }

        private void writeToParcel(Parcel arg4) {
            arg4.writeLong(this.spliceEventId);
            arg4.writeByte(((byte)this.spliceEventCancelIndicator));
            arg4.writeByte(((byte)this.outOfNetworkIndicator));
            arg4.writeByte(((byte)this.programSpliceFlag));
            int v0 = this.componentSpliceList.size();
            arg4.writeInt(v0);
            int v1;
            for(v1 = 0; v1 < v0; ++v1) {
                this.componentSpliceList.get(v1).writeToParcel(arg4);
            }

            arg4.writeLong(this.utcSpliceTime);
            arg4.writeByte(((byte)this.autoReturn));
            arg4.writeLong(this.breakDurationUs);
            arg4.writeInt(this.uniqueProgramId);
            arg4.writeInt(this.availNum);
            arg4.writeInt(this.availsExpected);
        }
    }

    public static final Parcelable$Creator CREATOR;
    public final List events;

    static {
        SpliceScheduleCommand.CREATOR = new com.google.android.exoplayer2.metadata.scte35.SpliceScheduleCommand$1();
    }

    private SpliceScheduleCommand(Parcel arg5) {
        super();
        int v0 = arg5.readInt();
        ArrayList v1 = new ArrayList(v0);
        int v2;
        for(v2 = 0; v2 < v0; ++v2) {
            v1.add(Event.access$300(arg5));
        }

        this.events = Collections.unmodifiableList(((List)v1));
    }

    SpliceScheduleCommand(Parcel arg1, com.google.android.exoplayer2.metadata.scte35.SpliceScheduleCommand$1 arg2) {
        this(arg1);
    }

    private SpliceScheduleCommand(List arg1) {
        super();
        this.events = Collections.unmodifiableList(arg1);
    }

    static SpliceScheduleCommand parseFromSection(ParsableByteArray arg4) {
        int v0 = arg4.readUnsignedByte();
        ArrayList v1 = new ArrayList(v0);
        int v2;
        for(v2 = 0; v2 < v0; ++v2) {
            v1.add(Event.access$400(arg4));
        }

        return new SpliceScheduleCommand(((List)v1));
    }

    public void writeToParcel(Parcel arg3, int arg4) {
        arg4 = this.events.size();
        arg3.writeInt(arg4);
        int v0;
        for(v0 = 0; v0 < arg4; ++v0) {
            Event.access$500(this.events.get(v0), arg3);
        }
    }
}

