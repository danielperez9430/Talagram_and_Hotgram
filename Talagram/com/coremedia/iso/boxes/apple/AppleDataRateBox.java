package com.coremedia.iso.boxes.apple;

import com.coremedia.iso.IsoTypeReader;
import com.coremedia.iso.IsoTypeWriter;
import com.googlecode.mp4parser.AbstractFullBox;
import com.googlecode.mp4parser.RequiresParseDetailAspect;
import java.nio.ByteBuffer;
import org.a.b.b.b;

public class AppleDataRateBox extends AbstractFullBox {
    public static final String TYPE = "rmdr";
    private long dataRate;

    static {
        AppleDataRateBox.ajc$preClinit();
    }

    public AppleDataRateBox() {
        super("rmdr");
    }

    public void _parseDetails(ByteBuffer arg3) {
        this.parseVersionAndFlags(arg3);
        this.dataRate = IsoTypeReader.readUInt32(arg3);
    }

    private static void ajc$preClinit() {
        b v8 = new b("AppleDataRateBox.java", AppleDataRateBox.class);
        AppleDataRateBox.ajc$tjp_0 = v8.a("method-execution", v8.a("1", "getDataRate", "com.coremedia.iso.boxes.apple.AppleDataRateBox", "", "", "", "long"), 53);
    }

    protected void getContent(ByteBuffer arg3) {
        this.writeVersionAndFlags(arg3);
        IsoTypeWriter.writeUInt32(arg3, this.dataRate);
    }

    protected long getContentSize() {
        return 8;
    }

    public long getDataRate() {
        RequiresParseDetailAspect.aspectOf().before(b.a(AppleDataRateBox.ajc$tjp_0, this, this));
        return this.dataRate;
    }
}

