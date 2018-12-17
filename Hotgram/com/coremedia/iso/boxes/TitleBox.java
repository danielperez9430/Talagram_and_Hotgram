package com.coremedia.iso.boxes;

import com.coremedia.iso.IsoTypeReader;
import com.coremedia.iso.IsoTypeWriter;
import com.coremedia.iso.Utf8;
import com.googlecode.mp4parser.AbstractFullBox;
import com.googlecode.mp4parser.RequiresParseDetailAspect;
import java.nio.ByteBuffer;
import org.a.b.b.b;

public class TitleBox extends AbstractFullBox {
    public static final String TYPE = "titl";
    private String language;
    private String title;

    static {
        TitleBox.ajc$preClinit();
    }

    public TitleBox() {
        super("titl");
    }

    public void _parseDetails(ByteBuffer arg2) {
        this.parseVersionAndFlags(arg2);
        this.language = IsoTypeReader.readIso639(arg2);
        this.title = IsoTypeReader.readString(arg2);
    }

    private static void ajc$preClinit() {
        b v8 = new b("TitleBox.java", TitleBox.class);
        TitleBox.ajc$tjp_0 = v8.a("method-execution", v8.a("1", "getLanguage", "com.coremedia.iso.boxes.TitleBox", "", "", "", "java.lang.String"), 46);
        TitleBox.ajc$tjp_1 = v8.a("method-execution", v8.a("1", "getTitle", "com.coremedia.iso.boxes.TitleBox", "", "", "", "java.lang.String"), 50);
        TitleBox.ajc$tjp_2 = v8.a("method-execution", v8.a("1", "setLanguage", "com.coremedia.iso.boxes.TitleBox", "java.lang.String", "language", "", "void"), 59);
        TitleBox.ajc$tjp_3 = v8.a("method-execution", v8.a("1", "setTitle", "com.coremedia.iso.boxes.TitleBox", "java.lang.String", "title", "", "void"), 63);
        TitleBox.ajc$tjp_4 = v8.a("method-execution", v8.a("1", "toString", "com.coremedia.iso.boxes.TitleBox", "", "", "", "java.lang.String"), 86);
    }

    protected void getContent(ByteBuffer arg2) {
        this.writeVersionAndFlags(arg2);
        IsoTypeWriter.writeIso639(arg2, this.language);
        arg2.put(Utf8.convert(this.title));
        arg2.put(0);
    }

    protected long getContentSize() {
        return ((long)(Utf8.utf8StringLengthInBytes(this.title) + 7));
    }

    public String getLanguage() {
        RequiresParseDetailAspect.aspectOf().before(b.a(TitleBox.ajc$tjp_0, this, this));
        return this.language;
    }

    public String getTitle() {
        RequiresParseDetailAspect.aspectOf().before(b.a(TitleBox.ajc$tjp_1, this, this));
        return this.title;
    }

    public void setLanguage(String arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(TitleBox.ajc$tjp_2, this, this, arg3));
        this.language = arg3;
    }

    public void setTitle(String arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(TitleBox.ajc$tjp_3, this, this, arg3));
        this.title = arg3;
    }

    public String toString() {
        RequiresParseDetailAspect.aspectOf().before(b.a(TitleBox.ajc$tjp_4, this, this));
        StringBuilder v0 = new StringBuilder("TitleBox[language=");
        v0.append(this.getLanguage());
        v0.append(";title=");
        v0.append(this.getTitle());
        v0.append("]");
        return v0.toString();
    }
}

