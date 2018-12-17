package com.coremedia.iso.boxes;

import com.coremedia.iso.IsoTypeReader;
import com.coremedia.iso.IsoTypeWriter;
import com.coremedia.iso.Utf8;
import com.googlecode.mp4parser.AbstractFullBox;
import com.googlecode.mp4parser.RequiresParseDetailAspect;
import java.nio.ByteBuffer;
import org.a.b.b.b;

public class KeywordsBox extends AbstractFullBox {
    public static final String TYPE = "kywd";
    private String[] keywords;
    private String language;

    static {
        KeywordsBox.ajc$preClinit();
    }

    public KeywordsBox() {
        super("kywd");
    }

    public void _parseDetails(ByteBuffer arg5) {
        this.parseVersionAndFlags(arg5);
        this.language = IsoTypeReader.readIso639(arg5);
        int v0 = IsoTypeReader.readUInt8(arg5);
        this.keywords = new String[v0];
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            IsoTypeReader.readUInt8(arg5);
            this.keywords[v1] = IsoTypeReader.readString(arg5);
        }
    }

    private static void ajc$preClinit() {
        b v8 = new b("KeywordsBox.java", KeywordsBox.class);
        KeywordsBox.ajc$tjp_0 = v8.a("method-execution", v8.a("1", "getLanguage", "com.coremedia.iso.boxes.KeywordsBox", "", "", "", "java.lang.String"), 40);
        KeywordsBox.ajc$tjp_1 = v8.a("method-execution", v8.a("1", "getKeywords", "com.coremedia.iso.boxes.KeywordsBox", "", "", "", "[Ljava.lang.String;"), 44);
        KeywordsBox.ajc$tjp_2 = v8.a("method-execution", v8.a("1", "setLanguage", "com.coremedia.iso.boxes.KeywordsBox", "java.lang.String", "language", "", "void"), 48);
        KeywordsBox.ajc$tjp_3 = v8.a("method-execution", v8.a("1", "setKeywords", "com.coremedia.iso.boxes.KeywordsBox", "[Ljava.lang.String;", "keywords", "", "void"), 52);
        KeywordsBox.ajc$tjp_4 = v8.a("method-execution", v8.a("1", "toString", "com.coremedia.iso.boxes.KeywordsBox", "", "", "", "java.lang.String"), 87);
    }

    protected void getContent(ByteBuffer arg6) {
        this.writeVersionAndFlags(arg6);
        IsoTypeWriter.writeIso639(arg6, this.language);
        IsoTypeWriter.writeUInt8(arg6, this.keywords.length);
        String[] v0 = this.keywords;
        int v1 = v0.length;
        int v2;
        for(v2 = 0; v2 < v1; ++v2) {
            String v3 = v0[v2];
            IsoTypeWriter.writeUInt8(arg6, Utf8.utf8StringLengthInBytes(v3) + 1);
            arg6.put(Utf8.convert(v3));
        }
    }

    protected long getContentSize() {
        String[] v0 = this.keywords;
        int v1 = v0.length;
        long v2 = 7;
        int v4;
        for(v4 = 0; v4 < v1; ++v4) {
            v2 += ((long)(Utf8.utf8StringLengthInBytes(v0[v4]) + 2));
        }

        return v2;
    }

    public String[] getKeywords() {
        RequiresParseDetailAspect.aspectOf().before(b.a(KeywordsBox.ajc$tjp_1, this, this));
        return this.keywords;
    }

    public String getLanguage() {
        RequiresParseDetailAspect.aspectOf().before(b.a(KeywordsBox.ajc$tjp_0, this, this));
        return this.language;
    }

    public void setKeywords(String[] arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(KeywordsBox.ajc$tjp_3, this, this, arg3));
        this.keywords = arg3;
    }

    public void setLanguage(String arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(KeywordsBox.ajc$tjp_2, this, this, arg3));
        this.language = arg3;
    }

    public String toString() {
        RequiresParseDetailAspect.aspectOf().before(b.a(KeywordsBox.ajc$tjp_4, this, this));
        StringBuffer v0 = new StringBuffer();
        v0.append("KeywordsBox[language=");
        v0.append(this.getLanguage());
        int v1;
        for(v1 = 0; v1 < this.keywords.length; ++v1) {
            v0.append(";keyword");
            v0.append(v1);
            v0.append("=");
            v0.append(this.keywords[v1]);
        }

        v0.append("]");
        return v0.toString();
    }
}

