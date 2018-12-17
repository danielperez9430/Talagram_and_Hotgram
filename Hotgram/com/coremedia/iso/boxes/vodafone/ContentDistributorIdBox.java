package com.coremedia.iso.boxes.vodafone;

import com.coremedia.iso.IsoTypeReader;
import com.coremedia.iso.IsoTypeWriter;
import com.coremedia.iso.Utf8;
import com.googlecode.mp4parser.AbstractFullBox;
import com.googlecode.mp4parser.RequiresParseDetailAspect;
import java.nio.ByteBuffer;
import org.a.b.b.b;

public class ContentDistributorIdBox extends AbstractFullBox {
    public static final String TYPE = "cdis";
    private String contentDistributorId;
    private String language;

    static {
        ContentDistributorIdBox.ajc$preClinit();
    }

    public ContentDistributorIdBox() {
        super("cdis");
    }

    public void _parseDetails(ByteBuffer arg2) {
        this.parseVersionAndFlags(arg2);
        this.language = IsoTypeReader.readIso639(arg2);
        this.contentDistributorId = IsoTypeReader.readString(arg2);
    }

    private static void ajc$preClinit() {
        b v8 = new b("ContentDistributorIdBox.java", ContentDistributorIdBox.class);
        ContentDistributorIdBox.ajc$tjp_0 = v8.a("method-execution", v8.a("1", "getLanguage", "com.coremedia.iso.boxes.vodafone.ContentDistributorIdBox", "", "", "", "java.lang.String"), 40);
        ContentDistributorIdBox.ajc$tjp_1 = v8.a("method-execution", v8.a("1", "getContentDistributorId", "com.coremedia.iso.boxes.vodafone.ContentDistributorIdBox", "", "", "", "java.lang.String"), 44);
        ContentDistributorIdBox.ajc$tjp_2 = v8.a("method-execution", v8.a("1", "toString", "com.coremedia.iso.boxes.vodafone.ContentDistributorIdBox", "", "", "", "java.lang.String"), 68);
    }

    protected void getContent(ByteBuffer arg2) {
        this.writeVersionAndFlags(arg2);
        IsoTypeWriter.writeIso639(arg2, this.language);
        arg2.put(Utf8.convert(this.contentDistributorId));
        arg2.put(0);
    }

    public String getContentDistributorId() {
        RequiresParseDetailAspect.aspectOf().before(b.a(ContentDistributorIdBox.ajc$tjp_1, this, this));
        return this.contentDistributorId;
    }

    protected long getContentSize() {
        return ((long)(Utf8.utf8StringLengthInBytes(this.contentDistributorId) + 7));
    }

    public String getLanguage() {
        RequiresParseDetailAspect.aspectOf().before(b.a(ContentDistributorIdBox.ajc$tjp_0, this, this));
        return this.language;
    }

    public String toString() {
        RequiresParseDetailAspect.aspectOf().before(b.a(ContentDistributorIdBox.ajc$tjp_2, this, this));
        StringBuilder v0 = new StringBuilder("ContentDistributorIdBox[language=");
        v0.append(this.getLanguage());
        v0.append(";contentDistributorId=");
        v0.append(this.getContentDistributorId());
        v0.append("]");
        return v0.toString();
    }
}

