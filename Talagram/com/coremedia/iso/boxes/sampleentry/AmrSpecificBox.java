package com.coremedia.iso.boxes.sampleentry;

import com.coremedia.iso.IsoFile;
import com.coremedia.iso.IsoTypeReader;
import com.coremedia.iso.IsoTypeWriter;
import com.googlecode.mp4parser.AbstractBox;
import com.googlecode.mp4parser.RequiresParseDetailAspect;
import java.nio.ByteBuffer;
import org.a.b.b.b;

public class AmrSpecificBox extends AbstractBox {
    public static final String TYPE = "damr";
    private int decoderVersion;
    private int framesPerSample;
    private int modeChangePeriod;
    private int modeSet;
    private String vendor;

    static {
        AmrSpecificBox.ajc$preClinit();
    }

    public AmrSpecificBox() {
        super("damr");
    }

    public void _parseDetails(ByteBuffer arg2) {
        byte[] v0 = new byte[4];
        arg2.get(v0);
        this.vendor = IsoFile.bytesToFourCC(v0);
        this.decoderVersion = IsoTypeReader.readUInt8(arg2);
        this.modeSet = IsoTypeReader.readUInt16(arg2);
        this.modeChangePeriod = IsoTypeReader.readUInt8(arg2);
        this.framesPerSample = IsoTypeReader.readUInt8(arg2);
    }

    private static void ajc$preClinit() {
        b v8 = new b("AmrSpecificBox.java", AmrSpecificBox.class);
        AmrSpecificBox.ajc$tjp_0 = v8.a("method-execution", v8.a("1", "getVendor", "com.coremedia.iso.boxes.sampleentry.AmrSpecificBox", "", "", "", "java.lang.String"), 46);
        AmrSpecificBox.ajc$tjp_1 = v8.a("method-execution", v8.a("1", "getDecoderVersion", "com.coremedia.iso.boxes.sampleentry.AmrSpecificBox", "", "", "", "int"), 50);
        AmrSpecificBox.ajc$tjp_2 = v8.a("method-execution", v8.a("1", "getModeSet", "com.coremedia.iso.boxes.sampleentry.AmrSpecificBox", "", "", "", "int"), 54);
        AmrSpecificBox.ajc$tjp_3 = v8.a("method-execution", v8.a("1", "getModeChangePeriod", "com.coremedia.iso.boxes.sampleentry.AmrSpecificBox", "", "", "", "int"), 58);
        AmrSpecificBox.ajc$tjp_4 = v8.a("method-execution", v8.a("1", "getFramesPerSample", "com.coremedia.iso.boxes.sampleentry.AmrSpecificBox", "", "", "", "int"), 62);
        AmrSpecificBox.ajc$tjp_5 = v8.a("method-execution", v8.a("1", "getContent", "com.coremedia.iso.boxes.sampleentry.AmrSpecificBox", "java.nio.ByteBuffer", "byteBuffer", "", "void"), 84);
        AmrSpecificBox.ajc$tjp_6 = v8.a("method-execution", v8.a("1", "toString", "com.coremedia.iso.boxes.sampleentry.AmrSpecificBox", "", "", "", "java.lang.String"), 92);
    }

    public void getContent(ByteBuffer arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(AmrSpecificBox.ajc$tjp_5, this, this, arg3));
        arg3.put(IsoFile.fourCCtoBytes(this.vendor));
        IsoTypeWriter.writeUInt8(arg3, this.decoderVersion);
        IsoTypeWriter.writeUInt16(arg3, this.modeSet);
        IsoTypeWriter.writeUInt8(arg3, this.modeChangePeriod);
        IsoTypeWriter.writeUInt8(arg3, this.framesPerSample);
    }

    protected long getContentSize() {
        return 9;
    }

    public int getDecoderVersion() {
        RequiresParseDetailAspect.aspectOf().before(b.a(AmrSpecificBox.ajc$tjp_1, this, this));
        return this.decoderVersion;
    }

    public int getFramesPerSample() {
        RequiresParseDetailAspect.aspectOf().before(b.a(AmrSpecificBox.ajc$tjp_4, this, this));
        return this.framesPerSample;
    }

    public int getModeChangePeriod() {
        RequiresParseDetailAspect.aspectOf().before(b.a(AmrSpecificBox.ajc$tjp_3, this, this));
        return this.modeChangePeriod;
    }

    public int getModeSet() {
        RequiresParseDetailAspect.aspectOf().before(b.a(AmrSpecificBox.ajc$tjp_2, this, this));
        return this.modeSet;
    }

    public String getVendor() {
        RequiresParseDetailAspect.aspectOf().before(b.a(AmrSpecificBox.ajc$tjp_0, this, this));
        return this.vendor;
    }

    public String toString() {
        RequiresParseDetailAspect.aspectOf().before(b.a(AmrSpecificBox.ajc$tjp_6, this, this));
        return "AmrSpecificBox[vendor=" + this.getVendor() + ";decoderVersion=" + this.getDecoderVersion() + ";modeSet=" + this.getModeSet() + ";modeChangePeriod=" + this.getModeChangePeriod() + ";framesPerSample=" + this.getFramesPerSample() + "]";
    }
}

