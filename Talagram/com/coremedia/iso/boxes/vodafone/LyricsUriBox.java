package com.coremedia.iso.boxes.vodafone;

import com.coremedia.iso.IsoTypeReader;
import com.coremedia.iso.Utf8;
import com.googlecode.mp4parser.AbstractFullBox;
import com.googlecode.mp4parser.RequiresParseDetailAspect;
import java.nio.ByteBuffer;
import org.a.b.b.b;

public class LyricsUriBox extends AbstractFullBox {
    public static final String TYPE = "lrcu";
    private String lyricsUri;

    static {
        LyricsUriBox.ajc$preClinit();
    }

    public LyricsUriBox() {
        super("lrcu");
    }

    public void _parseDetails(ByteBuffer arg1) {
        this.parseVersionAndFlags(arg1);
        this.lyricsUri = IsoTypeReader.readString(arg1);
    }

    private static void ajc$preClinit() {
        b v8 = new b("LyricsUriBox.java", LyricsUriBox.class);
        LyricsUriBox.ajc$tjp_0 = v8.a("method-execution", v8.a("1", "getLyricsUri", "com.coremedia.iso.boxes.vodafone.LyricsUriBox", "", "", "", "java.lang.String"), 39);
        LyricsUriBox.ajc$tjp_1 = v8.a("method-execution", v8.a("1", "setLyricsUri", "com.coremedia.iso.boxes.vodafone.LyricsUriBox", "java.lang.String", "lyricsUri", "", "void"), 43);
        LyricsUriBox.ajc$tjp_2 = v8.a("method-execution", v8.a("1", "toString", "com.coremedia.iso.boxes.vodafone.LyricsUriBox", "", "", "", "java.lang.String"), 64);
    }

    protected void getContent(ByteBuffer arg2) {
        this.writeVersionAndFlags(arg2);
        arg2.put(Utf8.convert(this.lyricsUri));
        arg2.put(0);
    }

    protected long getContentSize() {
        return ((long)(Utf8.utf8StringLengthInBytes(this.lyricsUri) + 5));
    }

    public String getLyricsUri() {
        RequiresParseDetailAspect.aspectOf().before(b.a(LyricsUriBox.ajc$tjp_0, this, this));
        return this.lyricsUri;
    }

    public void setLyricsUri(String arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(LyricsUriBox.ajc$tjp_1, this, this, arg3));
        this.lyricsUri = arg3;
    }

    public String toString() {
        RequiresParseDetailAspect.aspectOf().before(b.a(LyricsUriBox.ajc$tjp_2, this, this));
        StringBuilder v0 = new StringBuilder("LyricsUriBox[lyricsUri=");
        v0.append(this.getLyricsUri());
        v0.append("]");
        return v0.toString();
    }
}

