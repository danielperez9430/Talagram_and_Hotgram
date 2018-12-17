package com.coremedia.iso.boxes;

import com.coremedia.iso.IsoTypeReader;
import com.coremedia.iso.IsoTypeWriter;
import com.coremedia.iso.Utf8;
import com.googlecode.mp4parser.AbstractFullBox;
import com.googlecode.mp4parser.RequiresParseDetailAspect;
import java.nio.ByteBuffer;
import org.a.b.b.b;

public class DescriptionBox extends AbstractFullBox {
    public static final String TYPE = "dscp";
    private String description;
    private String language;

    static {
        DescriptionBox.ajc$preClinit();
    }

    public DescriptionBox() {
        super("dscp");
    }

    public void _parseDetails(ByteBuffer arg2) {
        this.parseVersionAndFlags(arg2);
        this.language = IsoTypeReader.readIso639(arg2);
        this.description = IsoTypeReader.readString(arg2);
    }

    private static void ajc$preClinit() {
        b v8 = new b("DescriptionBox.java", DescriptionBox.class);
        DescriptionBox.ajc$tjp_0 = v8.a("method-execution", v8.a("1", "getLanguage", "com.coremedia.iso.boxes.DescriptionBox", "", "", "", "java.lang.String"), 40);
        DescriptionBox.ajc$tjp_1 = v8.a("method-execution", v8.a("1", "getDescription", "com.coremedia.iso.boxes.DescriptionBox", "", "", "", "java.lang.String"), 44);
        DescriptionBox.ajc$tjp_2 = v8.a("method-execution", v8.a("1", "toString", "com.coremedia.iso.boxes.DescriptionBox", "", "", "", "java.lang.String"), 67);
        DescriptionBox.ajc$tjp_3 = v8.a("method-execution", v8.a("1", "setLanguage", "com.coremedia.iso.boxes.DescriptionBox", "java.lang.String", "language", "", "void"), 71);
        DescriptionBox.ajc$tjp_4 = v8.a("method-execution", v8.a("1", "setDescription", "com.coremedia.iso.boxes.DescriptionBox", "java.lang.String", "description", "", "void"), 75);
    }

    protected void getContent(ByteBuffer arg2) {
        this.writeVersionAndFlags(arg2);
        IsoTypeWriter.writeIso639(arg2, this.language);
        arg2.put(Utf8.convert(this.description));
        arg2.put(0);
    }

    protected long getContentSize() {
        return ((long)(Utf8.utf8StringLengthInBytes(this.description) + 7));
    }

    public String getDescription() {
        RequiresParseDetailAspect.aspectOf().before(b.a(DescriptionBox.ajc$tjp_1, this, this));
        return this.description;
    }

    public String getLanguage() {
        RequiresParseDetailAspect.aspectOf().before(b.a(DescriptionBox.ajc$tjp_0, this, this));
        return this.language;
    }

    public void setDescription(String arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(DescriptionBox.ajc$tjp_4, this, this, arg3));
        this.description = arg3;
    }

    public void setLanguage(String arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(DescriptionBox.ajc$tjp_3, this, this, arg3));
        this.language = arg3;
    }

    public String toString() {
        RequiresParseDetailAspect.aspectOf().before(b.a(DescriptionBox.ajc$tjp_2, this, this));
        StringBuilder v0 = new StringBuilder("DescriptionBox[language=");
        v0.append(this.getLanguage());
        v0.append(";description=");
        v0.append(this.getDescription());
        v0.append("]");
        return v0.toString();
    }
}

