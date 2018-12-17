package com.coremedia.iso.boxes.fragment;

import com.coremedia.iso.IsoTypeReader;
import com.coremedia.iso.IsoTypeWriter;
import com.googlecode.mp4parser.AbstractFullBox;
import com.googlecode.mp4parser.RequiresParseDetailAspect;
import com.googlecode.mp4parser.util.CastUtils;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.a.b.a.a;
import org.a.b.b.b;

public class TrackRunBox extends AbstractFullBox {
    public class Entry {
        private long sampleCompositionTimeOffset;
        private long sampleDuration;
        private SampleFlags sampleFlags;
        private long sampleSize;

        public Entry() {
            super();
        }

        public Entry(long arg1, long arg3, SampleFlags arg5, int arg6) {
            super();
            this.sampleDuration = arg1;
            this.sampleSize = arg3;
            this.sampleFlags = arg5;
            this.sampleCompositionTimeOffset = ((long)arg6);
        }

        static long access$0(Entry arg2) {
            return arg2.sampleDuration;
        }

        static long access$1(Entry arg2) {
            return arg2.sampleSize;
        }

        static SampleFlags access$2(Entry arg0) {
            return arg0.sampleFlags;
        }

        static long access$3(Entry arg2) {
            return arg2.sampleCompositionTimeOffset;
        }

        static void access$4(Entry arg0, long arg1) {
            arg0.sampleDuration = arg1;
        }

        static void access$5(Entry arg0, long arg1) {
            arg0.sampleSize = arg1;
        }

        static void access$6(Entry arg0, SampleFlags arg1) {
            arg0.sampleFlags = arg1;
        }

        static void access$7(Entry arg0, long arg1) {
            arg0.sampleCompositionTimeOffset = arg1;
        }

        public long getSampleCompositionTimeOffset() {
            return this.sampleCompositionTimeOffset;
        }

        public long getSampleDuration() {
            return this.sampleDuration;
        }

        public SampleFlags getSampleFlags() {
            return this.sampleFlags;
        }

        public long getSampleSize() {
            return this.sampleSize;
        }

        public void setSampleCompositionTimeOffset(int arg3) {
            this.sampleCompositionTimeOffset = ((long)arg3);
        }

        public void setSampleDuration(long arg1) {
            this.sampleDuration = arg1;
        }

        public void setSampleFlags(SampleFlags arg1) {
            this.sampleFlags = arg1;
        }

        public void setSampleSize(long arg1) {
            this.sampleSize = arg1;
        }

        public String toString() {
            StringBuilder v0 = new StringBuilder("Entry{duration=");
            v0.append(this.sampleDuration);
            v0.append(", size=");
            v0.append(this.sampleSize);
            v0.append(", dlags=");
            v0.append(this.sampleFlags);
            v0.append(", compTimeOffset=");
            v0.append(this.sampleCompositionTimeOffset);
            v0.append('}');
            return v0.toString();
        }
    }

    public static final String TYPE = "trun";
    private int dataOffset;
    private List entries;
    private SampleFlags firstSampleFlags;

    static {
        TrackRunBox.ajc$preClinit();
    }

    public TrackRunBox() {
        super("trun");
        this.entries = new ArrayList();
    }

    public void _parseDetails(ByteBuffer arg7) {
        this.parseVersionAndFlags(arg7);
        long v0 = IsoTypeReader.readUInt32(arg7);
        int v2 = (this.getFlags() & 1) == 1 ? CastUtils.l2i(IsoTypeReader.readUInt32(arg7)) : -1;
        this.dataOffset = v2;
        if((this.getFlags() & 4) == 4) {
            this.firstSampleFlags = new SampleFlags(arg7);
        }

        for(v2 = 0; (((long)v2)) < v0; ++v2) {
            Entry v3 = new Entry();
            if((this.getFlags() & 256) == 256) {
                Entry.access$4(v3, IsoTypeReader.readUInt32(arg7));
            }

            if((this.getFlags() & 512) == 512) {
                Entry.access$5(v3, IsoTypeReader.readUInt32(arg7));
            }

            if((this.getFlags() & 1024) == 1024) {
                Entry.access$6(v3, new SampleFlags(arg7));
            }

            if((this.getFlags() & 2048) == 2048) {
                Entry.access$7(v3, ((long)arg7.getInt()));
            }

            this.entries.add(v3);
        }
    }

    private static void ajc$preClinit() {
        b v8 = new b("TrackRunBox.java", TrackRunBox.class);
        TrackRunBox.ajc$tjp_0 = v8.a("method-execution", v8.a("1", "getEntries", "com.coremedia.iso.boxes.fragment.TrackRunBox", "", "", "", "java.util.List"), 57);
        TrackRunBox.ajc$tjp_1 = v8.a("method-execution", v8.a("1", "setDataOffset", "com.coremedia.iso.boxes.fragment.TrackRunBox", "int", "dataOffset", "", "void"), 120);
        TrackRunBox.ajc$tjp_10 = v8.a("method-execution", v8.a("1", "setDataOffsetPresent", "com.coremedia.iso.boxes.fragment.TrackRunBox", "boolean", "v", "", "void"), 267);
        TrackRunBox.ajc$tjp_11 = v8.a("method-execution", v8.a("1", "setSampleSizePresent", "com.coremedia.iso.boxes.fragment.TrackRunBox", "boolean", "v", "", "void"), 275);
        TrackRunBox.ajc$tjp_12 = v8.a("method-execution", v8.a("1", "setSampleDurationPresent", "com.coremedia.iso.boxes.fragment.TrackRunBox", "boolean", "v", "", "void"), 283);
        TrackRunBox.ajc$tjp_13 = v8.a("method-execution", v8.a("1", "setSampleFlagsPresent", "com.coremedia.iso.boxes.fragment.TrackRunBox", "boolean", "v", "", "void"), 292);
        TrackRunBox.ajc$tjp_14 = v8.a("method-execution", v8.a("1", "setSampleCompositionTimeOffsetPresent", "com.coremedia.iso.boxes.fragment.TrackRunBox", "boolean", "v", "", "void"), 300);
        TrackRunBox.ajc$tjp_15 = v8.a("method-execution", v8.a("1", "getDataOffset", "com.coremedia.iso.boxes.fragment.TrackRunBox", "", "", "", "int"), 309);
        TrackRunBox.ajc$tjp_16 = v8.a("method-execution", v8.a("1", "getFirstSampleFlags", "com.coremedia.iso.boxes.fragment.TrackRunBox", "", "", "", "com.coremedia.iso.boxes.fragment.SampleFlags"), 313);
        TrackRunBox.ajc$tjp_17 = v8.a("method-execution", v8.a("1", "setFirstSampleFlags", "com.coremedia.iso.boxes.fragment.TrackRunBox", "com.coremedia.iso.boxes.fragment.SampleFlags", "firstSampleFlags", "", "void"), 317);
        TrackRunBox.ajc$tjp_18 = v8.a("method-execution", v8.a("1", "toString", "com.coremedia.iso.boxes.fragment.TrackRunBox", "", "", "", "java.lang.String"), 327);
        TrackRunBox.ajc$tjp_19 = v8.a("method-execution", v8.a("1", "setEntries", "com.coremedia.iso.boxes.fragment.TrackRunBox", "java.util.List", "entries", "", "void"), 342);
        TrackRunBox.ajc$tjp_2 = v8.a("method-execution", v8.a("1", "getSampleCompositionTimeOffsets", "com.coremedia.iso.boxes.fragment.TrackRunBox", "", "", "", "[J"), 129);
        TrackRunBox.ajc$tjp_3 = v8.a("method-execution", v8.a("1", "getSampleCount", "com.coremedia.iso.boxes.fragment.TrackRunBox", "", "", "", "long"), 238);
        TrackRunBox.ajc$tjp_4 = v8.a("method-execution", v8.a("1", "isDataOffsetPresent", "com.coremedia.iso.boxes.fragment.TrackRunBox", "", "", "", "boolean"), 242);
        TrackRunBox.ajc$tjp_5 = v8.a("method-execution", v8.a("1", "isFirstSampleFlagsPresent", "com.coremedia.iso.boxes.fragment.TrackRunBox", "", "", "", "boolean"), 246);
        TrackRunBox.ajc$tjp_6 = v8.a("method-execution", v8.a("1", "isSampleSizePresent", "com.coremedia.iso.boxes.fragment.TrackRunBox", "", "", "", "boolean"), 251);
        TrackRunBox.ajc$tjp_7 = v8.a("method-execution", v8.a("1", "isSampleDurationPresent", "com.coremedia.iso.boxes.fragment.TrackRunBox", "", "", "", "boolean"), 255);
        TrackRunBox.ajc$tjp_8 = v8.a("method-execution", v8.a("1", "isSampleFlagsPresent", "com.coremedia.iso.boxes.fragment.TrackRunBox", "", "", "", "boolean"), 259);
        TrackRunBox.ajc$tjp_9 = v8.a("method-execution", v8.a("1", "isSampleCompositionTimeOffsetPresent", "com.coremedia.iso.boxes.fragment.TrackRunBox", "", "", "", "boolean"), 263);
    }

    protected void getContent(ByteBuffer arg6) {
        this.writeVersionAndFlags(arg6);
        IsoTypeWriter.writeUInt32(arg6, ((long)this.entries.size()));
        int v0 = this.getFlags();
        if((v0 & 1) == 1) {
            IsoTypeWriter.writeUInt32(arg6, ((long)this.dataOffset));
        }

        if((v0 & 4) == 4) {
            this.firstSampleFlags.getContent(arg6);
        }

        Iterator v1 = this.entries.iterator();
        while(v1.hasNext()) {
            Object v2 = v1.next();
            if((v0 & 256) == 256) {
                IsoTypeWriter.writeUInt32(arg6, Entry.access$0(((Entry)v2)));
            }

            if((v0 & 512) == 512) {
                IsoTypeWriter.writeUInt32(arg6, Entry.access$1(((Entry)v2)));
            }

            if((v0 & 1024) == 1024) {
                Entry.access$2(((Entry)v2)).getContent(arg6);
            }

            if((v0 & 2048) != 2048) {
                continue;
            }

            if(this.getVersion() == 0) {
                IsoTypeWriter.writeUInt32(arg6, Entry.access$3(((Entry)v2)));
                continue;
            }

            arg6.putInt(((int)Entry.access$3(((Entry)v2))));
        }
    }

    protected long getContentSize() {
        int v0 = this.getFlags();
        long v1 = (v0 & 1) == 1 ? 12 : 8;
        long v5 = 4;
        if((v0 & 4) == 4) {
            v1 += v5;
        }

        long v3 = 0;
        if((v0 & 256) == 256) {
            v3 = v5;
        }

        if((v0 & 512) == 512) {
            v3 += v5;
        }

        if((v0 & 1024) == 1024) {
            v3 += v5;
        }

        if((v0 & 2048) == 2048) {
            v3 += v5;
        }

        return v1 + v3 * (((long)this.entries.size()));
    }

    public int getDataOffset() {
        RequiresParseDetailAspect.aspectOf().before(b.a(TrackRunBox.ajc$tjp_15, this, this));
        return this.dataOffset;
    }

    public List getEntries() {
        RequiresParseDetailAspect.aspectOf().before(b.a(TrackRunBox.ajc$tjp_0, this, this));
        return this.entries;
    }

    public SampleFlags getFirstSampleFlags() {
        RequiresParseDetailAspect.aspectOf().before(b.a(TrackRunBox.ajc$tjp_16, this, this));
        return this.firstSampleFlags;
    }

    public long[] getSampleCompositionTimeOffsets() {
        RequiresParseDetailAspect.aspectOf().before(b.a(TrackRunBox.ajc$tjp_2, this, this));
        if(this.isSampleCompositionTimeOffsetPresent()) {
            long[] v0 = new long[this.entries.size()];
            int v1;
            for(v1 = 0; v1 < v0.length; ++v1) {
                v0[v1] = this.entries.get(v1).getSampleCompositionTimeOffset();
            }

            return v0;
        }

        return null;
    }

    public long getSampleCount() {
        RequiresParseDetailAspect.aspectOf().before(b.a(TrackRunBox.ajc$tjp_3, this, this));
        return ((long)this.entries.size());
    }

    public boolean isDataOffsetPresent() {
        RequiresParseDetailAspect.aspectOf().before(b.a(TrackRunBox.ajc$tjp_4, this, this));
        if((this.getFlags() & 1) == 1) {
            return 1;
        }

        return 0;
    }

    public boolean isFirstSampleFlagsPresent() {
        RequiresParseDetailAspect.aspectOf().before(b.a(TrackRunBox.ajc$tjp_5, this, this));
        if((this.getFlags() & 4) == 4) {
            return 1;
        }

        return 0;
    }

    public boolean isSampleCompositionTimeOffsetPresent() {
        RequiresParseDetailAspect.aspectOf().before(b.a(TrackRunBox.ajc$tjp_9, this, this));
        if((this.getFlags() & 2048) == 2048) {
            return 1;
        }

        return 0;
    }

    public boolean isSampleDurationPresent() {
        RequiresParseDetailAspect.aspectOf().before(b.a(TrackRunBox.ajc$tjp_7, this, this));
        if((this.getFlags() & 256) == 256) {
            return 1;
        }

        return 0;
    }

    public boolean isSampleFlagsPresent() {
        RequiresParseDetailAspect.aspectOf().before(b.a(TrackRunBox.ajc$tjp_8, this, this));
        if((this.getFlags() & 1024) == 1024) {
            return 1;
        }

        return 0;
    }

    public boolean isSampleSizePresent() {
        RequiresParseDetailAspect.aspectOf().before(b.a(TrackRunBox.ajc$tjp_6, this, this));
        if((this.getFlags() & 512) == 512) {
            return 1;
        }

        return 0;
    }

    public void setDataOffset(int arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(TrackRunBox.ajc$tjp_1, this, this, a.a(arg3)));
        int v0 = arg3 == -1 ? this.getFlags() & 16777214 : this.getFlags() | 1;
        this.setFlags(v0);
        this.dataOffset = arg3;
    }

    public void setDataOffsetPresent(boolean arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(TrackRunBox.ajc$tjp_10, this, this, a.a(arg3)));
        int v3 = arg3 ? this.getFlags() | 1 : this.getFlags() & 16777214;
        this.setFlags(v3);
    }

    public void setEntries(List arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(TrackRunBox.ajc$tjp_19, this, this, arg3));
        this.entries = arg3;
    }

    public void setFirstSampleFlags(SampleFlags arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(TrackRunBox.ajc$tjp_17, this, this, arg3));
        int v0 = arg3 == null ? this.getFlags() & 16777211 : this.getFlags() | 4;
        this.setFlags(v0);
        this.firstSampleFlags = arg3;
    }

    public void setSampleCompositionTimeOffsetPresent(boolean arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(TrackRunBox.ajc$tjp_14, this, this, a.a(arg3)));
        int v3 = arg3 ? this.getFlags() | 2048 : this.getFlags() & 16775167;
        this.setFlags(v3);
    }

    public void setSampleDurationPresent(boolean arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(TrackRunBox.ajc$tjp_12, this, this, a.a(arg3)));
        int v3 = arg3 ? this.getFlags() | 256 : this.getFlags() & 16776959;
        this.setFlags(v3);
    }

    public void setSampleFlagsPresent(boolean arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(TrackRunBox.ajc$tjp_13, this, this, a.a(arg3)));
        int v3 = arg3 ? this.getFlags() | 1024 : this.getFlags() & 16776191;
        this.setFlags(v3);
    }

    public void setSampleSizePresent(boolean arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(TrackRunBox.ajc$tjp_11, this, this, a.a(arg3)));
        int v3 = arg3 ? this.getFlags() | 512 : this.getFlags() & 16776703;
        this.setFlags(v3);
    }

    public String toString() {
        RequiresParseDetailAspect.aspectOf().before(b.a(TrackRunBox.ajc$tjp_18, this, this));
        return "TrackRunBox" + "{sampleCount=" + this.entries.size() + ", dataOffset=" + this.dataOffset + ", dataOffsetPresent=" + this.isDataOffsetPresent() + ", sampleSizePresent=" + this.isSampleSizePresent() + ", sampleDurationPresent=" + this.isSampleDurationPresent() + ", sampleFlagsPresentPresent=" + this.isSampleFlagsPresent() + ", sampleCompositionTimeOffsetPresent=" + this.isSampleCompositionTimeOffsetPresent() + ", firstSampleFlags=" + this.firstSampleFlags + '}';
    }
}

