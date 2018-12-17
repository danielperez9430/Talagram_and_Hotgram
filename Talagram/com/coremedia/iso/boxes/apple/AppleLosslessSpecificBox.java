package com.coremedia.iso.boxes.apple;

import com.coremedia.iso.IsoTypeReader;
import com.coremedia.iso.IsoTypeWriter;
import com.googlecode.mp4parser.AbstractFullBox;
import com.googlecode.mp4parser.RequiresParseDetailAspect;
import java.nio.ByteBuffer;
import org.a.b.a.a;
import org.a.b.b.b;

public final class AppleLosslessSpecificBox extends AbstractFullBox {
    public static final String TYPE = "alac";
    private long bitRate;
    private int channels;
    private int historyMult;
    private int initialHistory;
    private int kModifier;
    private long maxCodedFrameSize;
    private long maxSamplePerFrame;
    private long sampleRate;
    private int sampleSize;
    private int unknown1;
    private int unknown2;

    static {
        AppleLosslessSpecificBox.ajc$preClinit();
    }

    public AppleLosslessSpecificBox() {
        super("alac");
    }

    public void _parseDetails(ByteBuffer arg3) {
        this.parseVersionAndFlags(arg3);
        this.maxSamplePerFrame = IsoTypeReader.readUInt32(arg3);
        this.unknown1 = IsoTypeReader.readUInt8(arg3);
        this.sampleSize = IsoTypeReader.readUInt8(arg3);
        this.historyMult = IsoTypeReader.readUInt8(arg3);
        this.initialHistory = IsoTypeReader.readUInt8(arg3);
        this.kModifier = IsoTypeReader.readUInt8(arg3);
        this.channels = IsoTypeReader.readUInt8(arg3);
        this.unknown2 = IsoTypeReader.readUInt16(arg3);
        this.maxCodedFrameSize = IsoTypeReader.readUInt32(arg3);
        this.bitRate = IsoTypeReader.readUInt32(arg3);
        this.sampleRate = IsoTypeReader.readUInt32(arg3);
    }

    private static void ajc$preClinit() {
        b v8 = new b("AppleLosslessSpecificBox.java", AppleLosslessSpecificBox.class);
        AppleLosslessSpecificBox.ajc$tjp_0 = v8.a("method-execution", v8.a("1", "getMaxSamplePerFrame", "com.coremedia.iso.boxes.apple.AppleLosslessSpecificBox", "", "", "", "long"), 34);
        AppleLosslessSpecificBox.ajc$tjp_1 = v8.a("method-execution", v8.a("1", "setMaxSamplePerFrame", "com.coremedia.iso.boxes.apple.AppleLosslessSpecificBox", "int", "maxSamplePerFrame", "", "void"), 38);
        AppleLosslessSpecificBox.ajc$tjp_10 = v8.a("method-execution", v8.a("1", "getKModifier", "com.coremedia.iso.boxes.apple.AppleLosslessSpecificBox", "", "", "", "int"), 74);
        AppleLosslessSpecificBox.ajc$tjp_11 = v8.a("method-execution", v8.a("1", "setKModifier", "com.coremedia.iso.boxes.apple.AppleLosslessSpecificBox", "int", "kModifier", "", "void"), 78);
        AppleLosslessSpecificBox.ajc$tjp_12 = v8.a("method-execution", v8.a("1", "getChannels", "com.coremedia.iso.boxes.apple.AppleLosslessSpecificBox", "", "", "", "int"), 82);
        AppleLosslessSpecificBox.ajc$tjp_13 = v8.a("method-execution", v8.a("1", "setChannels", "com.coremedia.iso.boxes.apple.AppleLosslessSpecificBox", "int", "channels", "", "void"), 86);
        AppleLosslessSpecificBox.ajc$tjp_14 = v8.a("method-execution", v8.a("1", "getUnknown2", "com.coremedia.iso.boxes.apple.AppleLosslessSpecificBox", "", "", "", "int"), 90);
        AppleLosslessSpecificBox.ajc$tjp_15 = v8.a("method-execution", v8.a("1", "setUnknown2", "com.coremedia.iso.boxes.apple.AppleLosslessSpecificBox", "int", "unknown2", "", "void"), 94);
        AppleLosslessSpecificBox.ajc$tjp_16 = v8.a("method-execution", v8.a("1", "getMaxCodedFrameSize", "com.coremedia.iso.boxes.apple.AppleLosslessSpecificBox", "", "", "", "long"), 98);
        AppleLosslessSpecificBox.ajc$tjp_17 = v8.a("method-execution", v8.a("1", "setMaxCodedFrameSize", "com.coremedia.iso.boxes.apple.AppleLosslessSpecificBox", "int", "maxCodedFrameSize", "", "void"), 102);
        AppleLosslessSpecificBox.ajc$tjp_18 = v8.a("method-execution", v8.a("1", "getBitRate", "com.coremedia.iso.boxes.apple.AppleLosslessSpecificBox", "", "", "", "long"), 106);
        AppleLosslessSpecificBox.ajc$tjp_19 = v8.a("method-execution", v8.a("1", "setBitRate", "com.coremedia.iso.boxes.apple.AppleLosslessSpecificBox", "int", "bitRate", "", "void"), 110);
        AppleLosslessSpecificBox.ajc$tjp_2 = v8.a("method-execution", v8.a("1", "getUnknown1", "com.coremedia.iso.boxes.apple.AppleLosslessSpecificBox", "", "", "", "int"), 42);
        AppleLosslessSpecificBox.ajc$tjp_20 = v8.a("method-execution", v8.a("1", "getSampleRate", "com.coremedia.iso.boxes.apple.AppleLosslessSpecificBox", "", "", "", "long"), 114);
        AppleLosslessSpecificBox.ajc$tjp_21 = v8.a("method-execution", v8.a("1", "setSampleRate", "com.coremedia.iso.boxes.apple.AppleLosslessSpecificBox", "int", "sampleRate", "", "void"), 118);
        AppleLosslessSpecificBox.ajc$tjp_3 = v8.a("method-execution", v8.a("1", "setUnknown1", "com.coremedia.iso.boxes.apple.AppleLosslessSpecificBox", "int", "unknown1", "", "void"), 46);
        AppleLosslessSpecificBox.ajc$tjp_4 = v8.a("method-execution", v8.a("1", "getSampleSize", "com.coremedia.iso.boxes.apple.AppleLosslessSpecificBox", "", "", "", "int"), 50);
        AppleLosslessSpecificBox.ajc$tjp_5 = v8.a("method-execution", v8.a("1", "setSampleSize", "com.coremedia.iso.boxes.apple.AppleLosslessSpecificBox", "int", "sampleSize", "", "void"), 54);
        AppleLosslessSpecificBox.ajc$tjp_6 = v8.a("method-execution", v8.a("1", "getHistoryMult", "com.coremedia.iso.boxes.apple.AppleLosslessSpecificBox", "", "", "", "int"), 58);
        AppleLosslessSpecificBox.ajc$tjp_7 = v8.a("method-execution", v8.a("1", "setHistoryMult", "com.coremedia.iso.boxes.apple.AppleLosslessSpecificBox", "int", "historyMult", "", "void"), 62);
        AppleLosslessSpecificBox.ajc$tjp_8 = v8.a("method-execution", v8.a("1", "getInitialHistory", "com.coremedia.iso.boxes.apple.AppleLosslessSpecificBox", "", "", "", "int"), 66);
        AppleLosslessSpecificBox.ajc$tjp_9 = v8.a("method-execution", v8.a("1", "setInitialHistory", "com.coremedia.iso.boxes.apple.AppleLosslessSpecificBox", "int", "initialHistory", "", "void"), 70);
    }

    public long getBitRate() {
        RequiresParseDetailAspect.aspectOf().before(b.a(AppleLosslessSpecificBox.ajc$tjp_18, this, this));
        return this.bitRate;
    }

    public int getChannels() {
        RequiresParseDetailAspect.aspectOf().before(b.a(AppleLosslessSpecificBox.ajc$tjp_12, this, this));
        return this.channels;
    }

    protected void getContent(ByteBuffer arg3) {
        this.writeVersionAndFlags(arg3);
        IsoTypeWriter.writeUInt32(arg3, this.maxSamplePerFrame);
        IsoTypeWriter.writeUInt8(arg3, this.unknown1);
        IsoTypeWriter.writeUInt8(arg3, this.sampleSize);
        IsoTypeWriter.writeUInt8(arg3, this.historyMult);
        IsoTypeWriter.writeUInt8(arg3, this.initialHistory);
        IsoTypeWriter.writeUInt8(arg3, this.kModifier);
        IsoTypeWriter.writeUInt8(arg3, this.channels);
        IsoTypeWriter.writeUInt16(arg3, this.unknown2);
        IsoTypeWriter.writeUInt32(arg3, this.maxCodedFrameSize);
        IsoTypeWriter.writeUInt32(arg3, this.bitRate);
        IsoTypeWriter.writeUInt32(arg3, this.sampleRate);
    }

    protected long getContentSize() {
        return 28;
    }

    public int getHistoryMult() {
        RequiresParseDetailAspect.aspectOf().before(b.a(AppleLosslessSpecificBox.ajc$tjp_6, this, this));
        return this.historyMult;
    }

    public int getInitialHistory() {
        RequiresParseDetailAspect.aspectOf().before(b.a(AppleLosslessSpecificBox.ajc$tjp_8, this, this));
        return this.initialHistory;
    }

    public int getKModifier() {
        RequiresParseDetailAspect.aspectOf().before(b.a(AppleLosslessSpecificBox.ajc$tjp_10, this, this));
        return this.kModifier;
    }

    public long getMaxCodedFrameSize() {
        RequiresParseDetailAspect.aspectOf().before(b.a(AppleLosslessSpecificBox.ajc$tjp_16, this, this));
        return this.maxCodedFrameSize;
    }

    public long getMaxSamplePerFrame() {
        RequiresParseDetailAspect.aspectOf().before(b.a(AppleLosslessSpecificBox.ajc$tjp_0, this, this));
        return this.maxSamplePerFrame;
    }

    public long getSampleRate() {
        RequiresParseDetailAspect.aspectOf().before(b.a(AppleLosslessSpecificBox.ajc$tjp_20, this, this));
        return this.sampleRate;
    }

    public int getSampleSize() {
        RequiresParseDetailAspect.aspectOf().before(b.a(AppleLosslessSpecificBox.ajc$tjp_4, this, this));
        return this.sampleSize;
    }

    public int getUnknown1() {
        RequiresParseDetailAspect.aspectOf().before(b.a(AppleLosslessSpecificBox.ajc$tjp_2, this, this));
        return this.unknown1;
    }

    public int getUnknown2() {
        RequiresParseDetailAspect.aspectOf().before(b.a(AppleLosslessSpecificBox.ajc$tjp_14, this, this));
        return this.unknown2;
    }

    public void setBitRate(int arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(AppleLosslessSpecificBox.ajc$tjp_19, this, this, a.a(arg3)));
        this.bitRate = ((long)arg3);
    }

    public void setChannels(int arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(AppleLosslessSpecificBox.ajc$tjp_13, this, this, a.a(arg3)));
        this.channels = arg3;
    }

    public void setHistoryMult(int arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(AppleLosslessSpecificBox.ajc$tjp_7, this, this, a.a(arg3)));
        this.historyMult = arg3;
    }

    public void setInitialHistory(int arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(AppleLosslessSpecificBox.ajc$tjp_9, this, this, a.a(arg3)));
        this.initialHistory = arg3;
    }

    public void setKModifier(int arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(AppleLosslessSpecificBox.ajc$tjp_11, this, this, a.a(arg3)));
        this.kModifier = arg3;
    }

    public void setMaxCodedFrameSize(int arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(AppleLosslessSpecificBox.ajc$tjp_17, this, this, a.a(arg3)));
        this.maxCodedFrameSize = ((long)arg3);
    }

    public void setMaxSamplePerFrame(int arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(AppleLosslessSpecificBox.ajc$tjp_1, this, this, a.a(arg3)));
        this.maxSamplePerFrame = ((long)arg3);
    }

    public void setSampleRate(int arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(AppleLosslessSpecificBox.ajc$tjp_21, this, this, a.a(arg3)));
        this.sampleRate = ((long)arg3);
    }

    public void setSampleSize(int arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(AppleLosslessSpecificBox.ajc$tjp_5, this, this, a.a(arg3)));
        this.sampleSize = arg3;
    }

    public void setUnknown1(int arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(AppleLosslessSpecificBox.ajc$tjp_3, this, this, a.a(arg3)));
        this.unknown1 = arg3;
    }

    public void setUnknown2(int arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(AppleLosslessSpecificBox.ajc$tjp_15, this, this, a.a(arg3)));
        this.unknown2 = arg3;
    }
}

