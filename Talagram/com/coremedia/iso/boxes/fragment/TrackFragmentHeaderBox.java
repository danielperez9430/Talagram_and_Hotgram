package com.coremedia.iso.boxes.fragment;

import com.coremedia.iso.IsoTypeReader;
import com.coremedia.iso.IsoTypeWriter;
import com.googlecode.mp4parser.AbstractFullBox;
import com.googlecode.mp4parser.RequiresParseDetailAspect;
import java.nio.ByteBuffer;
import org.a.b.a.a;
import org.a.b.b.b;

public class TrackFragmentHeaderBox extends AbstractFullBox {
    public static final String TYPE = "tfhd";
    private long baseDataOffset;
    private boolean defaultBaseIsMoof;
    private long defaultSampleDuration;
    private SampleFlags defaultSampleFlags;
    private long defaultSampleSize;
    private boolean durationIsEmpty;
    private long sampleDescriptionIndex;
    private long trackId;

    static {
        TrackFragmentHeaderBox.ajc$preClinit();
    }

    public TrackFragmentHeaderBox() {
        super("tfhd");
        this.baseDataOffset = -1;
        this.defaultSampleDuration = -1;
        this.defaultSampleSize = -1;
    }

    public void _parseDetails(ByteBuffer arg5) {
        this.parseVersionAndFlags(arg5);
        this.trackId = IsoTypeReader.readUInt32(arg5);
        if((this.getFlags() & 1) == 1) {
            this.baseDataOffset = IsoTypeReader.readUInt64(arg5);
        }

        if((this.getFlags() & 2) == 2) {
            this.sampleDescriptionIndex = IsoTypeReader.readUInt32(arg5);
        }

        if((this.getFlags() & 8) == 8) {
            this.defaultSampleDuration = IsoTypeReader.readUInt32(arg5);
        }

        if((this.getFlags() & 16) == 16) {
            this.defaultSampleSize = IsoTypeReader.readUInt32(arg5);
        }

        if((this.getFlags() & 32) == 32) {
            this.defaultSampleFlags = new SampleFlags(arg5);
        }

        if((this.getFlags() & 65536) == 65536) {
            this.durationIsEmpty = true;
        }

        if((this.getFlags() & 131072) == 131072) {
            this.defaultBaseIsMoof = true;
        }
    }

    private static void ajc$preClinit() {
        b v8 = new b("TrackFragmentHeaderBox.java", TrackFragmentHeaderBox.class);
        TrackFragmentHeaderBox.ajc$tjp_0 = v8.a("method-execution", v8.a("1", "hasBaseDataOffset", "com.coremedia.iso.boxes.fragment.TrackFragmentHeaderBox", "", "", "", "boolean"), 126);
        TrackFragmentHeaderBox.ajc$tjp_1 = v8.a("method-execution", v8.a("1", "hasSampleDescriptionIndex", "com.coremedia.iso.boxes.fragment.TrackFragmentHeaderBox", "", "", "", "boolean"), 130);
        TrackFragmentHeaderBox.ajc$tjp_10 = v8.a("method-execution", v8.a("1", "getDefaultSampleFlags", "com.coremedia.iso.boxes.fragment.TrackFragmentHeaderBox", "", "", "", "com.coremedia.iso.boxes.fragment.SampleFlags"), 166);
        TrackFragmentHeaderBox.ajc$tjp_11 = v8.a("method-execution", v8.a("1", "isDurationIsEmpty", "com.coremedia.iso.boxes.fragment.TrackFragmentHeaderBox", "", "", "", "boolean"), 170);
        TrackFragmentHeaderBox.ajc$tjp_12 = v8.a("method-execution", v8.a("1", "isDefaultBaseIsMoof", "com.coremedia.iso.boxes.fragment.TrackFragmentHeaderBox", "", "", "", "boolean"), 174);
        TrackFragmentHeaderBox.ajc$tjp_13 = v8.a("method-execution", v8.a("1", "setTrackId", "com.coremedia.iso.boxes.fragment.TrackFragmentHeaderBox", "long", "trackId", "", "void"), 178);
        TrackFragmentHeaderBox.ajc$tjp_14 = v8.a("method-execution", v8.a("1", "setBaseDataOffset", "com.coremedia.iso.boxes.fragment.TrackFragmentHeaderBox", "long", "baseDataOffset", "", "void"), 182);
        TrackFragmentHeaderBox.ajc$tjp_15 = v8.a("method-execution", v8.a("1", "setSampleDescriptionIndex", "com.coremedia.iso.boxes.fragment.TrackFragmentHeaderBox", "long", "sampleDescriptionIndex", "", "void"), 191);
        TrackFragmentHeaderBox.ajc$tjp_16 = v8.a("method-execution", v8.a("1", "setDefaultSampleDuration", "com.coremedia.iso.boxes.fragment.TrackFragmentHeaderBox", "long", "defaultSampleDuration", "", "void"), 200);
        TrackFragmentHeaderBox.ajc$tjp_17 = v8.a("method-execution", v8.a("1", "setDefaultSampleSize", "com.coremedia.iso.boxes.fragment.TrackFragmentHeaderBox", "long", "defaultSampleSize", "", "void"), 205);
        TrackFragmentHeaderBox.ajc$tjp_18 = v8.a("method-execution", v8.a("1", "setDefaultSampleFlags", "com.coremedia.iso.boxes.fragment.TrackFragmentHeaderBox", "com.coremedia.iso.boxes.fragment.SampleFlags", "defaultSampleFlags", "", "void"), 210);
        TrackFragmentHeaderBox.ajc$tjp_19 = v8.a("method-execution", v8.a("1", "setDurationIsEmpty", "com.coremedia.iso.boxes.fragment.TrackFragmentHeaderBox", "boolean", "durationIsEmpty", "", "void"), 215);
        TrackFragmentHeaderBox.ajc$tjp_2 = v8.a("method-execution", v8.a("1", "hasDefaultSampleDuration", "com.coremedia.iso.boxes.fragment.TrackFragmentHeaderBox", "", "", "", "boolean"), 134);
        TrackFragmentHeaderBox.ajc$tjp_20 = v8.a("method-execution", v8.a("1", "setDefaultBaseIsMoof", "com.coremedia.iso.boxes.fragment.TrackFragmentHeaderBox", "boolean", "defaultBaseIsMoof", "", "void"), 220);
        TrackFragmentHeaderBox.ajc$tjp_21 = v8.a("method-execution", v8.a("1", "toString", "com.coremedia.iso.boxes.fragment.TrackFragmentHeaderBox", "", "", "", "java.lang.String"), 226);
        TrackFragmentHeaderBox.ajc$tjp_3 = v8.a("method-execution", v8.a("1", "hasDefaultSampleSize", "com.coremedia.iso.boxes.fragment.TrackFragmentHeaderBox", "", "", "", "boolean"), 138);
        TrackFragmentHeaderBox.ajc$tjp_4 = v8.a("method-execution", v8.a("1", "hasDefaultSampleFlags", "com.coremedia.iso.boxes.fragment.TrackFragmentHeaderBox", "", "", "", "boolean"), 142);
        TrackFragmentHeaderBox.ajc$tjp_5 = v8.a("method-execution", v8.a("1", "getTrackId", "com.coremedia.iso.boxes.fragment.TrackFragmentHeaderBox", "", "", "", "long"), 146);
        TrackFragmentHeaderBox.ajc$tjp_6 = v8.a("method-execution", v8.a("1", "getBaseDataOffset", "com.coremedia.iso.boxes.fragment.TrackFragmentHeaderBox", "", "", "", "long"), 150);
        TrackFragmentHeaderBox.ajc$tjp_7 = v8.a("method-execution", v8.a("1", "getSampleDescriptionIndex", "com.coremedia.iso.boxes.fragment.TrackFragmentHeaderBox", "", "", "", "long"), 154);
        TrackFragmentHeaderBox.ajc$tjp_8 = v8.a("method-execution", v8.a("1", "getDefaultSampleDuration", "com.coremedia.iso.boxes.fragment.TrackFragmentHeaderBox", "", "", "", "long"), 158);
        TrackFragmentHeaderBox.ajc$tjp_9 = v8.a("method-execution", v8.a("1", "getDefaultSampleSize", "com.coremedia.iso.boxes.fragment.TrackFragmentHeaderBox", "", "", "", "long"), 162);
    }

    public long getBaseDataOffset() {
        RequiresParseDetailAspect.aspectOf().before(b.a(TrackFragmentHeaderBox.ajc$tjp_6, this, this));
        return this.baseDataOffset;
    }

    protected void getContent(ByteBuffer arg3) {
        this.writeVersionAndFlags(arg3);
        IsoTypeWriter.writeUInt32(arg3, this.trackId);
        if((this.getFlags() & 1) == 1) {
            IsoTypeWriter.writeUInt64(arg3, this.getBaseDataOffset());
        }

        if((this.getFlags() & 2) == 2) {
            IsoTypeWriter.writeUInt32(arg3, this.getSampleDescriptionIndex());
        }

        if((this.getFlags() & 8) == 8) {
            IsoTypeWriter.writeUInt32(arg3, this.getDefaultSampleDuration());
        }

        if((this.getFlags() & 16) == 16) {
            IsoTypeWriter.writeUInt32(arg3, this.getDefaultSampleSize());
        }

        if((this.getFlags() & 32) == 32) {
            this.defaultSampleFlags.getContent(arg3);
        }
    }

    protected long getContentSize() {
        int v0 = this.getFlags();
        long v1 = (v0 & 1) == 1 ? 16 : 8;
        long v5 = 4;
        if((v0 & 2) == 2) {
            v1 += v5;
        }

        if((v0 & 8) == 8) {
            v1 += v5;
        }

        if((v0 & 16) == 16) {
            v1 += v5;
        }

        if((v0 & 32) == 32) {
            v1 += v5;
        }

        return v1;
    }

    public long getDefaultSampleDuration() {
        RequiresParseDetailAspect.aspectOf().before(b.a(TrackFragmentHeaderBox.ajc$tjp_8, this, this));
        return this.defaultSampleDuration;
    }

    public SampleFlags getDefaultSampleFlags() {
        RequiresParseDetailAspect.aspectOf().before(b.a(TrackFragmentHeaderBox.ajc$tjp_10, this, this));
        return this.defaultSampleFlags;
    }

    public long getDefaultSampleSize() {
        RequiresParseDetailAspect.aspectOf().before(b.a(TrackFragmentHeaderBox.ajc$tjp_9, this, this));
        return this.defaultSampleSize;
    }

    public long getSampleDescriptionIndex() {
        RequiresParseDetailAspect.aspectOf().before(b.a(TrackFragmentHeaderBox.ajc$tjp_7, this, this));
        return this.sampleDescriptionIndex;
    }

    public long getTrackId() {
        RequiresParseDetailAspect.aspectOf().before(b.a(TrackFragmentHeaderBox.ajc$tjp_5, this, this));
        return this.trackId;
    }

    public boolean hasBaseDataOffset() {
        RequiresParseDetailAspect.aspectOf().before(b.a(TrackFragmentHeaderBox.ajc$tjp_0, this, this));
        if((this.getFlags() & 1) != 0) {
            return 1;
        }

        return 0;
    }

    public boolean hasDefaultSampleDuration() {
        RequiresParseDetailAspect.aspectOf().before(b.a(TrackFragmentHeaderBox.ajc$tjp_2, this, this));
        if((this.getFlags() & 8) != 0) {
            return 1;
        }

        return 0;
    }

    public boolean hasDefaultSampleFlags() {
        RequiresParseDetailAspect.aspectOf().before(b.a(TrackFragmentHeaderBox.ajc$tjp_4, this, this));
        if((this.getFlags() & 32) != 0) {
            return 1;
        }

        return 0;
    }

    public boolean hasDefaultSampleSize() {
        RequiresParseDetailAspect.aspectOf().before(b.a(TrackFragmentHeaderBox.ajc$tjp_3, this, this));
        if((this.getFlags() & 16) != 0) {
            return 1;
        }

        return 0;
    }

    public boolean hasSampleDescriptionIndex() {
        RequiresParseDetailAspect.aspectOf().before(b.a(TrackFragmentHeaderBox.ajc$tjp_1, this, this));
        if((this.getFlags() & 2) != 0) {
            return 1;
        }

        return 0;
    }

    public boolean isDefaultBaseIsMoof() {
        RequiresParseDetailAspect.aspectOf().before(b.a(TrackFragmentHeaderBox.ajc$tjp_12, this, this));
        return this.defaultBaseIsMoof;
    }

    public boolean isDurationIsEmpty() {
        RequiresParseDetailAspect.aspectOf().before(b.a(TrackFragmentHeaderBox.ajc$tjp_11, this, this));
        return this.durationIsEmpty;
    }

    public void setBaseDataOffset(long arg4) {
        RequiresParseDetailAspect.aspectOf().before(b.a(TrackFragmentHeaderBox.ajc$tjp_14, this, this, a.a(arg4)));
        int v0 = arg4 == -1 ? this.getFlags() & 2147483646 : this.getFlags() | 1;
        this.setFlags(v0);
        this.baseDataOffset = arg4;
    }

    public void setDefaultBaseIsMoof(boolean arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(TrackFragmentHeaderBox.ajc$tjp_20, this, this, a.a(arg3)));
        this.setFlags(this.getFlags() | 131072);
        this.defaultBaseIsMoof = arg3;
    }

    public void setDefaultSampleDuration(long arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(TrackFragmentHeaderBox.ajc$tjp_16, this, this, a.a(arg3)));
        this.setFlags(this.getFlags() | 8);
        this.defaultSampleDuration = arg3;
    }

    public void setDefaultSampleFlags(SampleFlags arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(TrackFragmentHeaderBox.ajc$tjp_18, this, this, arg3));
        this.setFlags(this.getFlags() | 32);
        this.defaultSampleFlags = arg3;
    }

    public void setDefaultSampleSize(long arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(TrackFragmentHeaderBox.ajc$tjp_17, this, this, a.a(arg3)));
        this.setFlags(this.getFlags() | 16);
        this.defaultSampleSize = arg3;
    }

    public void setDurationIsEmpty(boolean arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(TrackFragmentHeaderBox.ajc$tjp_19, this, this, a.a(arg3)));
        this.setFlags(this.getFlags() | 65536);
        this.durationIsEmpty = arg3;
    }

    public void setSampleDescriptionIndex(long arg4) {
        RequiresParseDetailAspect.aspectOf().before(b.a(TrackFragmentHeaderBox.ajc$tjp_15, this, this, a.a(arg4)));
        int v0 = arg4 == -1 ? this.getFlags() & 2147483645 : this.getFlags() | 2;
        this.setFlags(v0);
        this.sampleDescriptionIndex = arg4;
    }

    public void setTrackId(long arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(TrackFragmentHeaderBox.ajc$tjp_13, this, this, a.a(arg3)));
        this.trackId = arg3;
    }

    public String toString() {
        RequiresParseDetailAspect.aspectOf().before(b.a(TrackFragmentHeaderBox.ajc$tjp_21, this, this));
        return "TrackFragmentHeaderBox" + "{trackId=" + this.trackId + ", baseDataOffset=" + this.baseDataOffset + ", sampleDescriptionIndex=" + this.sampleDescriptionIndex + ", defaultSampleDuration=" + this.defaultSampleDuration + ", defaultSampleSize=" + this.defaultSampleSize + ", defaultSampleFlags=" + this.defaultSampleFlags + ", durationIsEmpty=" + this.durationIsEmpty + ", defaultBaseIsMoof=" + this.defaultBaseIsMoof + '}';
    }
}

