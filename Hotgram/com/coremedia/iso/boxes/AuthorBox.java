package com.coremedia.iso.boxes;

import com.coremedia.iso.IsoTypeReader;
import com.coremedia.iso.IsoTypeWriter;
import com.coremedia.iso.Utf8;
import com.googlecode.mp4parser.AbstractFullBox;
import com.googlecode.mp4parser.RequiresParseDetailAspect;
import java.nio.ByteBuffer;
import org.a.b.b.b;

public class AuthorBox extends AbstractFullBox {
    public static final String TYPE = "auth";
    private String author;
    private String language;

    static {
        AuthorBox.ajc$preClinit();
    }

    public AuthorBox() {
        super("auth");
    }

    public void _parseDetails(ByteBuffer arg2) {
        this.parseVersionAndFlags(arg2);
        this.language = IsoTypeReader.readIso639(arg2);
        this.author = IsoTypeReader.readString(arg2);
    }

    private static void ajc$preClinit() {
        b v8 = new b("AuthorBox.java", AuthorBox.class);
        AuthorBox.ajc$tjp_0 = v8.a("method-execution", v8.a("1", "getLanguage", "com.coremedia.iso.boxes.AuthorBox", "", "", "", "java.lang.String"), 51);
        AuthorBox.ajc$tjp_1 = v8.a("method-execution", v8.a("1", "getAuthor", "com.coremedia.iso.boxes.AuthorBox", "", "", "", "java.lang.String"), 60);
        AuthorBox.ajc$tjp_2 = v8.a("method-execution", v8.a("1", "setLanguage", "com.coremedia.iso.boxes.AuthorBox", "java.lang.String", "language", "", "void"), 64);
        AuthorBox.ajc$tjp_3 = v8.a("method-execution", v8.a("1", "setAuthor", "com.coremedia.iso.boxes.AuthorBox", "java.lang.String", "author", "", "void"), 68);
        AuthorBox.ajc$tjp_4 = v8.a("method-execution", v8.a("1", "toString", "com.coremedia.iso.boxes.AuthorBox", "", "", "", "java.lang.String"), 92);
    }

    public String getAuthor() {
        RequiresParseDetailAspect.aspectOf().before(b.a(AuthorBox.ajc$tjp_1, this, this));
        return this.author;
    }

    protected void getContent(ByteBuffer arg2) {
        this.writeVersionAndFlags(arg2);
        IsoTypeWriter.writeIso639(arg2, this.language);
        arg2.put(Utf8.convert(this.author));
        arg2.put(0);
    }

    protected long getContentSize() {
        return ((long)(Utf8.utf8StringLengthInBytes(this.author) + 7));
    }

    public String getLanguage() {
        RequiresParseDetailAspect.aspectOf().before(b.a(AuthorBox.ajc$tjp_0, this, this));
        return this.language;
    }

    public void setAuthor(String arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(AuthorBox.ajc$tjp_3, this, this, arg3));
        this.author = arg3;
    }

    public void setLanguage(String arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(AuthorBox.ajc$tjp_2, this, this, arg3));
        this.language = arg3;
    }

    public String toString() {
        RequiresParseDetailAspect.aspectOf().before(b.a(AuthorBox.ajc$tjp_4, this, this));
        StringBuilder v0 = new StringBuilder("AuthorBox[language=");
        v0.append(this.getLanguage());
        v0.append(";author=");
        v0.append(this.getAuthor());
        v0.append("]");
        return v0.toString();
    }
}

