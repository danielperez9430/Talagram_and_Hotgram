package com.coremedia.iso.boxes;

import com.coremedia.iso.IsoTypeReader;
import com.coremedia.iso.IsoTypeWriter;
import com.coremedia.iso.Utf8;
import com.googlecode.mp4parser.AbstractFullBox;
import com.googlecode.mp4parser.RequiresParseDetailAspect;
import java.nio.ByteBuffer;
import org.a.b.b.b;

public class CopyrightBox extends AbstractFullBox {
    public static final String TYPE = "cprt";
    private String copyright;
    private String language;

    static {
        CopyrightBox.ajc$preClinit();
    }

    public CopyrightBox() {
        super("cprt");
    }

    public void _parseDetails(ByteBuffer arg2) {
        this.parseVersionAndFlags(arg2);
        this.language = IsoTypeReader.readIso639(arg2);
        this.copyright = IsoTypeReader.readString(arg2);
    }

    private static void ajc$preClinit() {
        b v8 = new b("CopyrightBox.java", CopyrightBox.class);
        CopyrightBox.ajc$tjp_0 = v8.a("method-execution", v8.a("1", "getLanguage", "com.coremedia.iso.boxes.CopyrightBox", "", "", "", "java.lang.String"), 46);
        CopyrightBox.ajc$tjp_1 = v8.a("method-execution", v8.a("1", "getCopyright", "com.coremedia.iso.boxes.CopyrightBox", "", "", "", "java.lang.String"), 50);
        CopyrightBox.ajc$tjp_2 = v8.a("method-execution", v8.a("1", "setLanguage", "com.coremedia.iso.boxes.CopyrightBox", "java.lang.String", "language", "", "void"), 54);
        CopyrightBox.ajc$tjp_3 = v8.a("method-execution", v8.a("1", "setCopyright", "com.coremedia.iso.boxes.CopyrightBox", "java.lang.String", "copyright", "", "void"), 58);
        CopyrightBox.ajc$tjp_4 = v8.a("method-execution", v8.a("1", "toString", "com.coremedia.iso.boxes.CopyrightBox", "", "", "", "java.lang.String"), 81);
    }

    protected void getContent(ByteBuffer arg2) {
        this.writeVersionAndFlags(arg2);
        IsoTypeWriter.writeIso639(arg2, this.language);
        arg2.put(Utf8.convert(this.copyright));
        arg2.put(0);
    }

    protected long getContentSize() {
        return ((long)(Utf8.utf8StringLengthInBytes(this.copyright) + 7));
    }

    public String getCopyright() {
        RequiresParseDetailAspect.aspectOf().before(b.a(CopyrightBox.ajc$tjp_1, this, this));
        return this.copyright;
    }

    public String getLanguage() {
        RequiresParseDetailAspect.aspectOf().before(b.a(CopyrightBox.ajc$tjp_0, this, this));
        return this.language;
    }

    public void setCopyright(String arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(CopyrightBox.ajc$tjp_3, this, this, arg3));
        this.copyright = arg3;
    }

    public void setLanguage(String arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(CopyrightBox.ajc$tjp_2, this, this, arg3));
        this.language = arg3;
    }

    public String toString() {
        RequiresParseDetailAspect.aspectOf().before(b.a(CopyrightBox.ajc$tjp_4, this, this));
        StringBuilder v0 = new StringBuilder("CopyrightBox[language=");
        v0.append(this.getLanguage());
        v0.append(";copyright=");
        v0.append(this.getCopyright());
        v0.append("]");
        return v0.toString();
    }
}

