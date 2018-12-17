package com.coremedia.iso.boxes.apple;

import com.coremedia.iso.IsoFile;
import com.coremedia.iso.IsoTypeReader;
import com.coremedia.iso.IsoTypeWriter;
import com.coremedia.iso.Utf8;
import com.googlecode.mp4parser.AbstractFullBox;
import com.googlecode.mp4parser.RequiresParseDetailAspect;
import com.googlecode.mp4parser.util.CastUtils;
import java.nio.ByteBuffer;
import org.a.b.b.b;

public class AppleDataReferenceBox extends AbstractFullBox {
    public static final String TYPE = "rdrf";
    private String dataReference;
    private int dataReferenceSize;
    private String dataReferenceType;

    static {
        AppleDataReferenceBox.ajc$preClinit();
    }

    public AppleDataReferenceBox() {
        super("rdrf");
    }

    public void _parseDetails(ByteBuffer arg3) {
        this.parseVersionAndFlags(arg3);
        this.dataReferenceType = IsoTypeReader.read4cc(arg3);
        this.dataReferenceSize = CastUtils.l2i(IsoTypeReader.readUInt32(arg3));
        this.dataReference = IsoTypeReader.readString(arg3, this.dataReferenceSize);
    }

    private static void ajc$preClinit() {
        b v8 = new b("AppleDataReferenceBox.java", AppleDataReferenceBox.class);
        AppleDataReferenceBox.ajc$tjp_0 = v8.a("method-execution", v8.a("1", "getDataReferenceSize", "com.coremedia.iso.boxes.apple.AppleDataReferenceBox", "", "", "", "long"), 63);
        AppleDataReferenceBox.ajc$tjp_1 = v8.a("method-execution", v8.a("1", "getDataReferenceType", "com.coremedia.iso.boxes.apple.AppleDataReferenceBox", "", "", "", "java.lang.String"), 67);
        AppleDataReferenceBox.ajc$tjp_2 = v8.a("method-execution", v8.a("1", "getDataReference", "com.coremedia.iso.boxes.apple.AppleDataReferenceBox", "", "", "", "java.lang.String"), 71);
    }

    protected void getContent(ByteBuffer arg3) {
        this.writeVersionAndFlags(arg3);
        arg3.put(IsoFile.fourCCtoBytes(this.dataReferenceType));
        IsoTypeWriter.writeUInt32(arg3, ((long)this.dataReferenceSize));
        arg3.put(Utf8.convert(this.dataReference));
    }

    protected long getContentSize() {
        return ((long)(this.dataReferenceSize + 12));
    }

    public String getDataReference() {
        RequiresParseDetailAspect.aspectOf().before(b.a(AppleDataReferenceBox.ajc$tjp_2, this, this));
        return this.dataReference;
    }

    public long getDataReferenceSize() {
        RequiresParseDetailAspect.aspectOf().before(b.a(AppleDataReferenceBox.ajc$tjp_0, this, this));
        return ((long)this.dataReferenceSize);
    }

    public String getDataReferenceType() {
        RequiresParseDetailAspect.aspectOf().before(b.a(AppleDataReferenceBox.ajc$tjp_1, this, this));
        return this.dataReferenceType;
    }
}

