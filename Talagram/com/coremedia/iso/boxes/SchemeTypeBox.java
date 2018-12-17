package com.coremedia.iso.boxes;

import com.coremedia.iso.IsoFile;
import com.coremedia.iso.IsoTypeReader;
import com.coremedia.iso.IsoTypeWriter;
import com.coremedia.iso.Utf8;
import com.googlecode.mp4parser.AbstractFullBox;
import com.googlecode.mp4parser.RequiresParseDetailAspect;
import java.nio.ByteBuffer;
import org.a.b.a.a;
import org.a.b.b.b;

public class SchemeTypeBox extends AbstractFullBox {
    public static final String TYPE = "schm";
    String schemeType;
    String schemeUri;
    long schemeVersion;

    static {
        SchemeTypeBox.ajc$preClinit();
    }

    public SchemeTypeBox() {
        super("schm");
        this.schemeType = "    ";
        this.schemeUri = null;
    }

    public void _parseDetails(ByteBuffer arg3) {
        this.parseVersionAndFlags(arg3);
        this.schemeType = IsoTypeReader.read4cc(arg3);
        this.schemeVersion = IsoTypeReader.readUInt32(arg3);
        if((this.getFlags() & 1) == 1) {
            this.schemeUri = IsoTypeReader.readString(arg3);
        }
    }

    private static void ajc$preClinit() {
        b v8 = new b("SchemeTypeBox.java", SchemeTypeBox.class);
        SchemeTypeBox.ajc$tjp_0 = v8.a("method-execution", v8.a("1", "getSchemeType", "com.coremedia.iso.boxes.SchemeTypeBox", "", "", "", "java.lang.String"), 44);
        SchemeTypeBox.ajc$tjp_1 = v8.a("method-execution", v8.a("1", "getSchemeVersion", "com.coremedia.iso.boxes.SchemeTypeBox", "", "", "", "long"), 48);
        SchemeTypeBox.ajc$tjp_2 = v8.a("method-execution", v8.a("1", "getSchemeUri", "com.coremedia.iso.boxes.SchemeTypeBox", "", "", "", "java.lang.String"), 52);
        SchemeTypeBox.ajc$tjp_3 = v8.a("method-execution", v8.a("1", "setSchemeType", "com.coremedia.iso.boxes.SchemeTypeBox", "java.lang.String", "schemeType", "", "void"), 56);
        SchemeTypeBox.ajc$tjp_4 = v8.a("method-execution", v8.a("1", "setSchemeVersion", "com.coremedia.iso.boxes.SchemeTypeBox", "int", "schemeVersion", "", "void"), 61);
        SchemeTypeBox.ajc$tjp_5 = v8.a("method-execution", v8.a("1", "setSchemeUri", "com.coremedia.iso.boxes.SchemeTypeBox", "java.lang.String", "schemeUri", "", "void"), 65);
        SchemeTypeBox.ajc$tjp_6 = v8.a("method-execution", v8.a("1", "toString", "com.coremedia.iso.boxes.SchemeTypeBox", "", "", "", "java.lang.String"), 93);
    }

    protected void getContent(ByteBuffer arg3) {
        this.writeVersionAndFlags(arg3);
        arg3.put(IsoFile.fourCCtoBytes(this.schemeType));
        IsoTypeWriter.writeUInt32(arg3, this.schemeVersion);
        if((this.getFlags() & 1) == 1) {
            arg3.put(Utf8.convert(this.schemeUri));
        }
    }

    protected long getContentSize() {
        int v0 = (this.getFlags() & 1) == 1 ? Utf8.utf8StringLengthInBytes(this.schemeUri) + 1 : 0;
        return ((long)(v0 + 12));
    }

    public String getSchemeType() {
        RequiresParseDetailAspect.aspectOf().before(b.a(SchemeTypeBox.ajc$tjp_0, this, this));
        return this.schemeType;
    }

    public String getSchemeUri() {
        RequiresParseDetailAspect.aspectOf().before(b.a(SchemeTypeBox.ajc$tjp_2, this, this));
        return this.schemeUri;
    }

    public long getSchemeVersion() {
        RequiresParseDetailAspect.aspectOf().before(b.a(SchemeTypeBox.ajc$tjp_1, this, this));
        return this.schemeVersion;
    }

    public void setSchemeType(String arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(SchemeTypeBox.ajc$tjp_3, this, this, arg3));
        this.schemeType = arg3;
    }

    public void setSchemeUri(String arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(SchemeTypeBox.ajc$tjp_5, this, this, arg3));
        this.schemeUri = arg3;
    }

    public void setSchemeVersion(int arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(SchemeTypeBox.ajc$tjp_4, this, this, a.a(arg3)));
        this.schemeVersion = ((long)arg3);
    }

    public String toString() {
        RequiresParseDetailAspect.aspectOf().before(b.a(SchemeTypeBox.ajc$tjp_6, this, this));
        return "Schema Type Box[" + "schemeUri=" + this.schemeUri + "; " + "schemeType=" + this.schemeType + "; " + "schemeVersion=" + this.schemeVersion + "; " + "]";
    }
}

