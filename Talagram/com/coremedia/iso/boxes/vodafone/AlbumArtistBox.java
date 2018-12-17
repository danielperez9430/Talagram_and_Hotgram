package com.coremedia.iso.boxes.vodafone;

import com.coremedia.iso.IsoTypeReader;
import com.coremedia.iso.IsoTypeWriter;
import com.coremedia.iso.Utf8;
import com.googlecode.mp4parser.AbstractFullBox;
import com.googlecode.mp4parser.RequiresParseDetailAspect;
import java.nio.ByteBuffer;
import org.a.b.b.b;

public class AlbumArtistBox extends AbstractFullBox {
    public static final String TYPE = "albr";
    private String albumArtist;
    private String language;

    static {
        AlbumArtistBox.ajc$preClinit();
    }

    public AlbumArtistBox() {
        super("albr");
    }

    public void _parseDetails(ByteBuffer arg2) {
        this.parseVersionAndFlags(arg2);
        this.language = IsoTypeReader.readIso639(arg2);
        this.albumArtist = IsoTypeReader.readString(arg2);
    }

    private static void ajc$preClinit() {
        b v8 = new b("AlbumArtistBox.java", AlbumArtistBox.class);
        AlbumArtistBox.ajc$tjp_0 = v8.a("method-execution", v8.a("1", "getLanguage", "com.coremedia.iso.boxes.vodafone.AlbumArtistBox", "", "", "", "java.lang.String"), 42);
        AlbumArtistBox.ajc$tjp_1 = v8.a("method-execution", v8.a("1", "getAlbumArtist", "com.coremedia.iso.boxes.vodafone.AlbumArtistBox", "", "", "", "java.lang.String"), 46);
        AlbumArtistBox.ajc$tjp_2 = v8.a("method-execution", v8.a("1", "setLanguage", "com.coremedia.iso.boxes.vodafone.AlbumArtistBox", "java.lang.String", "language", "", "void"), 50);
        AlbumArtistBox.ajc$tjp_3 = v8.a("method-execution", v8.a("1", "setAlbumArtist", "com.coremedia.iso.boxes.vodafone.AlbumArtistBox", "java.lang.String", "albumArtist", "", "void"), 54);
        AlbumArtistBox.ajc$tjp_4 = v8.a("method-execution", v8.a("1", "toString", "com.coremedia.iso.boxes.vodafone.AlbumArtistBox", "", "", "", "java.lang.String"), 76);
    }

    public String getAlbumArtist() {
        RequiresParseDetailAspect.aspectOf().before(b.a(AlbumArtistBox.ajc$tjp_1, this, this));
        return this.albumArtist;
    }

    protected void getContent(ByteBuffer arg2) {
        this.writeVersionAndFlags(arg2);
        IsoTypeWriter.writeIso639(arg2, this.language);
        arg2.put(Utf8.convert(this.albumArtist));
        arg2.put(0);
    }

    protected long getContentSize() {
        return ((long)(Utf8.utf8StringLengthInBytes(this.albumArtist) + 7));
    }

    public String getLanguage() {
        RequiresParseDetailAspect.aspectOf().before(b.a(AlbumArtistBox.ajc$tjp_0, this, this));
        return this.language;
    }

    public void setAlbumArtist(String arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(AlbumArtistBox.ajc$tjp_3, this, this, arg3));
        this.albumArtist = arg3;
    }

    public void setLanguage(String arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(AlbumArtistBox.ajc$tjp_2, this, this, arg3));
        this.language = arg3;
    }

    public String toString() {
        RequiresParseDetailAspect.aspectOf().before(b.a(AlbumArtistBox.ajc$tjp_4, this, this));
        StringBuilder v0 = new StringBuilder("AlbumArtistBox[language=");
        v0.append(this.getLanguage());
        v0.append(";albumArtist=");
        v0.append(this.getAlbumArtist());
        v0.append("]");
        return v0.toString();
    }
}

