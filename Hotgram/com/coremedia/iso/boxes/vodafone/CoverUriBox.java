package com.coremedia.iso.boxes.vodafone;

import com.coremedia.iso.IsoTypeReader;
import com.coremedia.iso.Utf8;
import com.googlecode.mp4parser.AbstractFullBox;
import com.googlecode.mp4parser.RequiresParseDetailAspect;
import java.nio.ByteBuffer;
import org.a.b.b.b;

public class CoverUriBox extends AbstractFullBox {
    public static final String TYPE = "cvru";
    private String coverUri;

    static {
        CoverUriBox.ajc$preClinit();
    }

    public CoverUriBox() {
        super("cvru");
    }

    public void _parseDetails(ByteBuffer arg1) {
        this.parseVersionAndFlags(arg1);
        this.coverUri = IsoTypeReader.readString(arg1);
    }

    private static void ajc$preClinit() {
        b v8 = new b("CoverUriBox.java", CoverUriBox.class);
        CoverUriBox.ajc$tjp_0 = v8.a("method-execution", v8.a("1", "getCoverUri", "com.coremedia.iso.boxes.vodafone.CoverUriBox", "", "", "", "java.lang.String"), 38);
        CoverUriBox.ajc$tjp_1 = v8.a("method-execution", v8.a("1", "setCoverUri", "com.coremedia.iso.boxes.vodafone.CoverUriBox", "java.lang.String", "coverUri", "", "void"), 42);
        CoverUriBox.ajc$tjp_2 = v8.a("method-execution", v8.a("1", "toString", "com.coremedia.iso.boxes.vodafone.CoverUriBox", "", "", "", "java.lang.String"), 64);
    }

    protected void getContent(ByteBuffer arg2) {
        this.writeVersionAndFlags(arg2);
        arg2.put(Utf8.convert(this.coverUri));
        arg2.put(0);
    }

    protected long getContentSize() {
        return ((long)(Utf8.utf8StringLengthInBytes(this.coverUri) + 5));
    }

    public String getCoverUri() {
        RequiresParseDetailAspect.aspectOf().before(b.a(CoverUriBox.ajc$tjp_0, this, this));
        return this.coverUri;
    }

    public void setCoverUri(String arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(CoverUriBox.ajc$tjp_1, this, this, arg3));
        this.coverUri = arg3;
    }

    public String toString() {
        RequiresParseDetailAspect.aspectOf().before(b.a(CoverUriBox.ajc$tjp_2, this, this));
        StringBuilder v0 = new StringBuilder("CoverUriBox[coverUri=");
        v0.append(this.getCoverUri());
        v0.append("]");
        return v0.toString();
    }
}

