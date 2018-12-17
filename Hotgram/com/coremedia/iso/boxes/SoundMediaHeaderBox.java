package com.coremedia.iso.boxes;

import com.coremedia.iso.IsoTypeReader;
import com.coremedia.iso.IsoTypeWriter;
import com.googlecode.mp4parser.RequiresParseDetailAspect;
import java.nio.ByteBuffer;
import org.a.b.b.b;

public class SoundMediaHeaderBox extends AbstractMediaHeaderBox {
    public static final String TYPE = "smhd";
    private float balance;

    static {
        SoundMediaHeaderBox.ajc$preClinit();
    }

    public SoundMediaHeaderBox() {
        super("smhd");
    }

    public void _parseDetails(ByteBuffer arg2) {
        this.parseVersionAndFlags(arg2);
        this.balance = IsoTypeReader.readFixedPoint88(arg2);
        IsoTypeReader.readUInt16(arg2);
    }

    private static void ajc$preClinit() {
        b v8 = new b("SoundMediaHeaderBox.java", SoundMediaHeaderBox.class);
        SoundMediaHeaderBox.ajc$tjp_0 = v8.a("method-execution", v8.a("1", "getBalance", "com.coremedia.iso.boxes.SoundMediaHeaderBox", "", "", "", "float"), 36);
        SoundMediaHeaderBox.ajc$tjp_1 = v8.a("method-execution", v8.a("1", "toString", "com.coremedia.iso.boxes.SoundMediaHeaderBox", "", "", "", "java.lang.String"), 58);
    }

    public float getBalance() {
        RequiresParseDetailAspect.aspectOf().before(b.a(SoundMediaHeaderBox.ajc$tjp_0, this, this));
        return this.balance;
    }

    protected void getContent(ByteBuffer arg3) {
        this.writeVersionAndFlags(arg3);
        IsoTypeWriter.writeFixedPoint88(arg3, ((double)this.balance));
        IsoTypeWriter.writeUInt16(arg3, 0);
    }

    protected long getContentSize() {
        return 8;
    }

    public String toString() {
        RequiresParseDetailAspect.aspectOf().before(b.a(SoundMediaHeaderBox.ajc$tjp_1, this, this));
        StringBuilder v0 = new StringBuilder("SoundMediaHeaderBox[balance=");
        v0.append(this.getBalance());
        v0.append("]");
        return v0.toString();
    }
}

