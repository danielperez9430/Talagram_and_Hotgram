package com.coremedia.iso.boxes;

import com.coremedia.iso.IsoTypeReader;
import com.coremedia.iso.IsoTypeWriter;
import com.coremedia.iso.Utf8;
import com.googlecode.mp4parser.AbstractFullBox;
import com.googlecode.mp4parser.RequiresParseDetailAspect;
import java.nio.ByteBuffer;
import org.a.b.a.a;
import org.a.b.b.b;

public class AlbumBox extends AbstractFullBox {
    public static final String TYPE = "albm";
    private String albumTitle;
    private String language;
    private int trackNumber;

    static {
        AlbumBox.ajc$preClinit();
    }

    public AlbumBox() {
        super("albm");
    }

    public void _parseDetails(ByteBuffer arg2) {
        this.parseVersionAndFlags(arg2);
        this.language = IsoTypeReader.readIso639(arg2);
        this.albumTitle = IsoTypeReader.readString(arg2);
        int v2 = arg2.remaining() > 0 ? IsoTypeReader.readUInt8(arg2) : -1;
        this.trackNumber = v2;
    }

    private static void ajc$preClinit() {
        b v8 = new b("AlbumBox.java", AlbumBox.class);
        AlbumBox.ajc$tjp_0 = v8.a("method-execution", v8.a("1", "getLanguage", "com.coremedia.iso.boxes.AlbumBox", "", "", "", "java.lang.String"), 51);
        AlbumBox.ajc$tjp_1 = v8.a("method-execution", v8.a("1", "getAlbumTitle", "com.coremedia.iso.boxes.AlbumBox", "", "", "", "java.lang.String"), 55);
        AlbumBox.ajc$tjp_2 = v8.a("method-execution", v8.a("1", "getTrackNumber", "com.coremedia.iso.boxes.AlbumBox", "", "", "", "int"), 59);
        AlbumBox.ajc$tjp_3 = v8.a("method-execution", v8.a("1", "setLanguage", "com.coremedia.iso.boxes.AlbumBox", "java.lang.String", "language", "", "void"), 63);
        AlbumBox.ajc$tjp_4 = v8.a("method-execution", v8.a("1", "setAlbumTitle", "com.coremedia.iso.boxes.AlbumBox", "java.lang.String", "albumTitle", "", "void"), 67);
        AlbumBox.ajc$tjp_5 = v8.a("method-execution", v8.a("1", "setTrackNumber", "com.coremedia.iso.boxes.AlbumBox", "int", "trackNumber", "", "void"), 71);
        AlbumBox.ajc$tjp_6 = v8.a("method-execution", v8.a("1", "toString", "com.coremedia.iso.boxes.AlbumBox", "", "", "", "java.lang.String"), 103);
    }

    public String getAlbumTitle() {
        RequiresParseDetailAspect.aspectOf().before(b.a(AlbumBox.ajc$tjp_1, this, this));
        return this.albumTitle;
    }

    protected void getContent(ByteBuffer arg3) {
        this.writeVersionAndFlags(arg3);
        IsoTypeWriter.writeIso639(arg3, this.language);
        arg3.put(Utf8.convert(this.albumTitle));
        arg3.put(0);
        if(this.trackNumber != -1) {
            IsoTypeWriter.writeUInt8(arg3, this.trackNumber);
        }
    }

    protected long getContentSize() {
        int v1 = 1;
        int v0 = Utf8.utf8StringLengthInBytes(this.albumTitle) + 7;
        if(this.trackNumber == -1) {
            v1 = 0;
        }

        return ((long)(v0 + v1));
    }

    public String getLanguage() {
        RequiresParseDetailAspect.aspectOf().before(b.a(AlbumBox.ajc$tjp_0, this, this));
        return this.language;
    }

    public int getTrackNumber() {
        RequiresParseDetailAspect.aspectOf().before(b.a(AlbumBox.ajc$tjp_2, this, this));
        return this.trackNumber;
    }

    public void setAlbumTitle(String arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(AlbumBox.ajc$tjp_4, this, this, arg3));
        this.albumTitle = arg3;
    }

    public void setLanguage(String arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(AlbumBox.ajc$tjp_3, this, this, arg3));
        this.language = arg3;
    }

    public void setTrackNumber(int arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(AlbumBox.ajc$tjp_5, this, this, a.a(arg3)));
        this.trackNumber = arg3;
    }

    public String toString() {
        RequiresParseDetailAspect.aspectOf().before(b.a(AlbumBox.ajc$tjp_6, this, this));
        StringBuilder v0 = new StringBuilder();
        v0.append("AlbumBox[language=");
        v0.append(this.getLanguage());
        v0.append(";");
        v0.append("albumTitle=");
        v0.append(this.getAlbumTitle());
        if(this.trackNumber >= 0) {
            v0.append(";trackNumber=");
            v0.append(this.getTrackNumber());
        }

        v0.append("]");
        return v0.toString();
    }
}

