package com.coremedia.iso.boxes;

import com.coremedia.iso.IsoFile;
import com.coremedia.iso.IsoTypeReader;
import com.googlecode.mp4parser.AbstractBox;
import com.googlecode.mp4parser.RequiresParseDetailAspect;
import java.nio.ByteBuffer;
import org.a.b.b.b;

public class OriginalFormatBox extends AbstractBox {
    public static final String TYPE = "frma";
    private String dataFormat;

    static {
        OriginalFormatBox.ajc$preClinit();
    }

    public OriginalFormatBox() {
        super("frma");
        this.dataFormat = "    ";
    }

    public void _parseDetails(ByteBuffer arg1) {
        this.dataFormat = IsoTypeReader.read4cc(arg1);
    }

    private static void ajc$preClinit() {
        b v8 = new b("OriginalFormatBox.java", OriginalFormatBox.class);
        OriginalFormatBox.ajc$tjp_0 = v8.a("method-execution", v8.a("1", "getDataFormat", "com.coremedia.iso.boxes.OriginalFormatBox", "", "", "", "java.lang.String"), 42);
        OriginalFormatBox.ajc$tjp_1 = v8.a("method-execution", v8.a("1", "setDataFormat", "com.coremedia.iso.boxes.OriginalFormatBox", "java.lang.String", "dataFormat", "", "void"), 47);
        OriginalFormatBox.ajc$tjp_2 = v8.a("method-execution", v8.a("1", "toString", "com.coremedia.iso.boxes.OriginalFormatBox", "", "", "", "java.lang.String"), 67);
    }

    protected void getContent(ByteBuffer arg2) {
        arg2.put(IsoFile.fourCCtoBytes(this.dataFormat));
    }

    protected long getContentSize() {
        return 4;
    }

    public String getDataFormat() {
        RequiresParseDetailAspect.aspectOf().before(b.a(OriginalFormatBox.ajc$tjp_0, this, this));
        return this.dataFormat;
    }

    public void setDataFormat(String arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(OriginalFormatBox.ajc$tjp_1, this, this, arg3));
        this.dataFormat = arg3;
    }

    public String toString() {
        RequiresParseDetailAspect.aspectOf().before(b.a(OriginalFormatBox.ajc$tjp_2, this, this));
        StringBuilder v0 = new StringBuilder("OriginalFormatBox[dataFormat=");
        v0.append(this.getDataFormat());
        v0.append("]");
        return v0.toString();
    }
}

