package com.coremedia.iso.boxes;

import com.coremedia.iso.IsoTypeReader;
import com.coremedia.iso.IsoTypeWriter;
import com.coremedia.iso.Utf8;
import com.googlecode.mp4parser.AbstractFullBox;
import com.googlecode.mp4parser.RequiresParseDetailAspect;
import java.nio.ByteBuffer;
import org.a.b.b.b;

public class GenreBox extends AbstractFullBox {
    public static final String TYPE = "gnre";
    private String genre;
    private String language;

    static {
        GenreBox.ajc$preClinit();
    }

    public GenreBox() {
        super("gnre");
    }

    public void _parseDetails(ByteBuffer arg2) {
        this.parseVersionAndFlags(arg2);
        this.language = IsoTypeReader.readIso639(arg2);
        this.genre = IsoTypeReader.readString(arg2);
    }

    private static void ajc$preClinit() {
        b v8 = new b("GenreBox.java", GenreBox.class);
        GenreBox.ajc$tjp_0 = v8.a("method-execution", v8.a("1", "getLanguage", "com.coremedia.iso.boxes.GenreBox", "", "", "", "java.lang.String"), 42);
        GenreBox.ajc$tjp_1 = v8.a("method-execution", v8.a("1", "getGenre", "com.coremedia.iso.boxes.GenreBox", "", "", "", "java.lang.String"), 46);
        GenreBox.ajc$tjp_2 = v8.a("method-execution", v8.a("1", "setLanguage", "com.coremedia.iso.boxes.GenreBox", "java.lang.String", "language", "", "void"), 50);
        GenreBox.ajc$tjp_3 = v8.a("method-execution", v8.a("1", "setGenre", "com.coremedia.iso.boxes.GenreBox", "java.lang.String", "genre", "", "void"), 54);
        GenreBox.ajc$tjp_4 = v8.a("method-execution", v8.a("1", "toString", "com.coremedia.iso.boxes.GenreBox", "", "", "", "java.lang.String"), 77);
    }

    protected void getContent(ByteBuffer arg2) {
        this.writeVersionAndFlags(arg2);
        IsoTypeWriter.writeIso639(arg2, this.language);
        arg2.put(Utf8.convert(this.genre));
        arg2.put(0);
    }

    protected long getContentSize() {
        return ((long)(Utf8.utf8StringLengthInBytes(this.genre) + 7));
    }

    public String getGenre() {
        RequiresParseDetailAspect.aspectOf().before(b.a(GenreBox.ajc$tjp_1, this, this));
        return this.genre;
    }

    public String getLanguage() {
        RequiresParseDetailAspect.aspectOf().before(b.a(GenreBox.ajc$tjp_0, this, this));
        return this.language;
    }

    public void setGenre(String arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(GenreBox.ajc$tjp_3, this, this, arg3));
        this.genre = arg3;
    }

    public void setLanguage(String arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(GenreBox.ajc$tjp_2, this, this, arg3));
        this.language = arg3;
    }

    public String toString() {
        RequiresParseDetailAspect.aspectOf().before(b.a(GenreBox.ajc$tjp_4, this, this));
        StringBuilder v0 = new StringBuilder("GenreBox[language=");
        v0.append(this.getLanguage());
        v0.append(";genre=");
        v0.append(this.getGenre());
        v0.append("]");
        return v0.toString();
    }
}

