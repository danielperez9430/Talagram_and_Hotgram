package com.coremedia.iso.boxes;

import com.coremedia.iso.IsoTypeReader;
import com.coremedia.iso.IsoTypeWriter;
import com.coremedia.iso.Utf8;
import com.googlecode.mp4parser.AbstractFullBox;
import com.googlecode.mp4parser.RequiresParseDetailAspect;
import java.nio.ByteBuffer;
import org.a.b.b.b;

public class PerformerBox extends AbstractFullBox {
    public static final String TYPE = "perf";
    private String language;
    private String performer;

    static {
        PerformerBox.ajc$preClinit();
    }

    public PerformerBox() {
        super("perf");
    }

    public void _parseDetails(ByteBuffer arg2) {
        this.parseVersionAndFlags(arg2);
        this.language = IsoTypeReader.readIso639(arg2);
        this.performer = IsoTypeReader.readString(arg2);
    }

    private static void ajc$preClinit() {
        b v8 = new b("PerformerBox.java", PerformerBox.class);
        PerformerBox.ajc$tjp_0 = v8.a("method-execution", v8.a("1", "getLanguage", "com.coremedia.iso.boxes.PerformerBox", "", "", "", "java.lang.String"), 41);
        PerformerBox.ajc$tjp_1 = v8.a("method-execution", v8.a("1", "getPerformer", "com.coremedia.iso.boxes.PerformerBox", "", "", "", "java.lang.String"), 45);
        PerformerBox.ajc$tjp_2 = v8.a("method-execution", v8.a("1", "setLanguage", "com.coremedia.iso.boxes.PerformerBox", "java.lang.String", "language", "", "void"), 49);
        PerformerBox.ajc$tjp_3 = v8.a("method-execution", v8.a("1", "setPerformer", "com.coremedia.iso.boxes.PerformerBox", "java.lang.String", "performer", "", "void"), 53);
        PerformerBox.ajc$tjp_4 = v8.a("method-execution", v8.a("1", "toString", "com.coremedia.iso.boxes.PerformerBox", "", "", "", "java.lang.String"), 76);
    }

    protected void getContent(ByteBuffer arg2) {
        this.writeVersionAndFlags(arg2);
        IsoTypeWriter.writeIso639(arg2, this.language);
        arg2.put(Utf8.convert(this.performer));
        arg2.put(0);
    }

    protected long getContentSize() {
        return ((long)(Utf8.utf8StringLengthInBytes(this.performer) + 7));
    }

    public String getLanguage() {
        RequiresParseDetailAspect.aspectOf().before(b.a(PerformerBox.ajc$tjp_0, this, this));
        return this.language;
    }

    public String getPerformer() {
        RequiresParseDetailAspect.aspectOf().before(b.a(PerformerBox.ajc$tjp_1, this, this));
        return this.performer;
    }

    public void setLanguage(String arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(PerformerBox.ajc$tjp_2, this, this, arg3));
        this.language = arg3;
    }

    public void setPerformer(String arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(PerformerBox.ajc$tjp_3, this, this, arg3));
        this.performer = arg3;
    }

    public String toString() {
        RequiresParseDetailAspect.aspectOf().before(b.a(PerformerBox.ajc$tjp_4, this, this));
        StringBuilder v0 = new StringBuilder("PerformerBox[language=");
        v0.append(this.getLanguage());
        v0.append(";performer=");
        v0.append(this.getPerformer());
        v0.append("]");
        return v0.toString();
    }
}

