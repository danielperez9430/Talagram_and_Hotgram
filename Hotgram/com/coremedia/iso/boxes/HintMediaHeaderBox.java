package com.coremedia.iso.boxes;

import com.coremedia.iso.IsoTypeReader;
import com.coremedia.iso.IsoTypeWriter;
import com.googlecode.mp4parser.RequiresParseDetailAspect;
import java.nio.ByteBuffer;
import org.a.b.b.b;

public class HintMediaHeaderBox extends AbstractMediaHeaderBox {
    public static final String TYPE = "hmhd";
    private long avgBitrate;
    private int avgPduSize;
    private long maxBitrate;
    private int maxPduSize;

    static {
        HintMediaHeaderBox.ajc$preClinit();
    }

    public HintMediaHeaderBox() {
        super("hmhd");
    }

    public void _parseDetails(ByteBuffer arg3) {
        this.parseVersionAndFlags(arg3);
        this.maxPduSize = IsoTypeReader.readUInt16(arg3);
        this.avgPduSize = IsoTypeReader.readUInt16(arg3);
        this.maxBitrate = IsoTypeReader.readUInt32(arg3);
        this.avgBitrate = IsoTypeReader.readUInt32(arg3);
        IsoTypeReader.readUInt32(arg3);
    }

    private static void ajc$preClinit() {
        b v8 = new b("HintMediaHeaderBox.java", HintMediaHeaderBox.class);
        HintMediaHeaderBox.ajc$tjp_0 = v8.a("method-execution", v8.a("1", "getMaxPduSize", "com.coremedia.iso.boxes.HintMediaHeaderBox", "", "", "", "int"), 42);
        HintMediaHeaderBox.ajc$tjp_1 = v8.a("method-execution", v8.a("1", "getAvgPduSize", "com.coremedia.iso.boxes.HintMediaHeaderBox", "", "", "", "int"), 46);
        HintMediaHeaderBox.ajc$tjp_2 = v8.a("method-execution", v8.a("1", "getMaxBitrate", "com.coremedia.iso.boxes.HintMediaHeaderBox", "", "", "", "long"), 50);
        HintMediaHeaderBox.ajc$tjp_3 = v8.a("method-execution", v8.a("1", "getAvgBitrate", "com.coremedia.iso.boxes.HintMediaHeaderBox", "", "", "", "long"), 54);
        HintMediaHeaderBox.ajc$tjp_4 = v8.a("method-execution", v8.a("1", "toString", "com.coremedia.iso.boxes.HintMediaHeaderBox", "", "", "", "java.lang.String"), 84);
    }

    public long getAvgBitrate() {
        RequiresParseDetailAspect.aspectOf().before(b.a(HintMediaHeaderBox.ajc$tjp_3, this, this));
        return this.avgBitrate;
    }

    public int getAvgPduSize() {
        RequiresParseDetailAspect.aspectOf().before(b.a(HintMediaHeaderBox.ajc$tjp_1, this, this));
        return this.avgPduSize;
    }

    protected void getContent(ByteBuffer arg3) {
        this.writeVersionAndFlags(arg3);
        IsoTypeWriter.writeUInt16(arg3, this.maxPduSize);
        IsoTypeWriter.writeUInt16(arg3, this.avgPduSize);
        IsoTypeWriter.writeUInt32(arg3, this.maxBitrate);
        IsoTypeWriter.writeUInt32(arg3, this.avgBitrate);
        IsoTypeWriter.writeUInt32(arg3, 0);
    }

    protected long getContentSize() {
        return 20;
    }

    public long getMaxBitrate() {
        RequiresParseDetailAspect.aspectOf().before(b.a(HintMediaHeaderBox.ajc$tjp_2, this, this));
        return this.maxBitrate;
    }

    public int getMaxPduSize() {
        RequiresParseDetailAspect.aspectOf().before(b.a(HintMediaHeaderBox.ajc$tjp_0, this, this));
        return this.maxPduSize;
    }

    public String toString() {
        RequiresParseDetailAspect.aspectOf().before(b.a(HintMediaHeaderBox.ajc$tjp_4, this, this));
        StringBuilder v0 = new StringBuilder("HintMediaHeaderBox{maxPduSize=");
        v0.append(this.maxPduSize);
        v0.append(", avgPduSize=");
        v0.append(this.avgPduSize);
        v0.append(", maxBitrate=");
        v0.append(this.maxBitrate);
        v0.append(", avgBitrate=");
        v0.append(this.avgBitrate);
        v0.append('}');
        return v0.toString();
    }
}

