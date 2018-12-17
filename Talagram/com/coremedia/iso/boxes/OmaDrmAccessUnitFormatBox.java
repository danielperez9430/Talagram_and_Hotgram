package com.coremedia.iso.boxes;

import com.coremedia.iso.IsoTypeReader;
import com.coremedia.iso.IsoTypeWriter;
import com.googlecode.mp4parser.AbstractFullBox;
import com.googlecode.mp4parser.RequiresParseDetailAspect;
import java.nio.ByteBuffer;
import org.a.b.a.a;
import org.a.b.b.b;

public final class OmaDrmAccessUnitFormatBox extends AbstractFullBox {
    public static final String TYPE = "odaf";
    private byte allBits;
    private int initVectorLength;
    private int keyIndicatorLength;
    private boolean selectiveEncryption;

    static {
        OmaDrmAccessUnitFormatBox.ajc$preClinit();
    }

    public OmaDrmAccessUnitFormatBox() {
        super("odaf");
    }

    public void _parseDetails(ByteBuffer arg3) {
        this.parseVersionAndFlags(arg3);
        this.allBits = ((byte)IsoTypeReader.readUInt8(arg3));
        boolean v0 = (this.allBits & 128) == 128 ? true : false;
        this.selectiveEncryption = v0;
        this.keyIndicatorLength = IsoTypeReader.readUInt8(arg3);
        this.initVectorLength = IsoTypeReader.readUInt8(arg3);
    }

    private static void ajc$preClinit() {
        b v8 = new b("OmaDrmAccessUnitFormatBox.java", OmaDrmAccessUnitFormatBox.class);
        OmaDrmAccessUnitFormatBox.ajc$tjp_0 = v8.a("method-execution", v8.a("1", "isSelectiveEncryption", "com.coremedia.iso.boxes.OmaDrmAccessUnitFormatBox", "", "", "", "boolean"), 46);
        OmaDrmAccessUnitFormatBox.ajc$tjp_1 = v8.a("method-execution", v8.a("1", "getKeyIndicatorLength", "com.coremedia.iso.boxes.OmaDrmAccessUnitFormatBox", "", "", "", "int"), 50);
        OmaDrmAccessUnitFormatBox.ajc$tjp_2 = v8.a("method-execution", v8.a("1", "getInitVectorLength", "com.coremedia.iso.boxes.OmaDrmAccessUnitFormatBox", "", "", "", "int"), 54);
        OmaDrmAccessUnitFormatBox.ajc$tjp_3 = v8.a("method-execution", v8.a("1", "setInitVectorLength", "com.coremedia.iso.boxes.OmaDrmAccessUnitFormatBox", "int", "initVectorLength", "", "void"), 58);
        OmaDrmAccessUnitFormatBox.ajc$tjp_4 = v8.a("method-execution", v8.a("1", "setKeyIndicatorLength", "com.coremedia.iso.boxes.OmaDrmAccessUnitFormatBox", "int", "keyIndicatorLength", "", "void"), 62);
        OmaDrmAccessUnitFormatBox.ajc$tjp_5 = v8.a("method-execution", v8.a("1", "setAllBits", "com.coremedia.iso.boxes.OmaDrmAccessUnitFormatBox", "byte", "allBits", "", "void"), 66);
    }

    protected void getContent(ByteBuffer arg2) {
        this.writeVersionAndFlags(arg2);
        IsoTypeWriter.writeUInt8(arg2, this.allBits);
        IsoTypeWriter.writeUInt8(arg2, this.keyIndicatorLength);
        IsoTypeWriter.writeUInt8(arg2, this.initVectorLength);
    }

    protected long getContentSize() {
        return 7;
    }

    public int getInitVectorLength() {
        RequiresParseDetailAspect.aspectOf().before(b.a(OmaDrmAccessUnitFormatBox.ajc$tjp_2, this, this));
        return this.initVectorLength;
    }

    public int getKeyIndicatorLength() {
        RequiresParseDetailAspect.aspectOf().before(b.a(OmaDrmAccessUnitFormatBox.ajc$tjp_1, this, this));
        return this.keyIndicatorLength;
    }

    public boolean isSelectiveEncryption() {
        RequiresParseDetailAspect.aspectOf().before(b.a(OmaDrmAccessUnitFormatBox.ajc$tjp_0, this, this));
        return this.selectiveEncryption;
    }

    public void setAllBits(byte arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(OmaDrmAccessUnitFormatBox.ajc$tjp_5, this, this, a.a(arg3)));
        this.allBits = arg3;
        boolean v3 = (arg3 & 128) == 128 ? true : false;
        this.selectiveEncryption = v3;
    }

    public void setInitVectorLength(int arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(OmaDrmAccessUnitFormatBox.ajc$tjp_3, this, this, a.a(arg3)));
        this.initVectorLength = arg3;
    }

    public void setKeyIndicatorLength(int arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(OmaDrmAccessUnitFormatBox.ajc$tjp_4, this, this, a.a(arg3)));
        this.keyIndicatorLength = arg3;
    }
}

