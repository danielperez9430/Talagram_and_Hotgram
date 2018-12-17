package com.coremedia.iso.boxes;

import com.coremedia.iso.IsoTypeReader;
import com.coremedia.iso.Utf8;
import com.googlecode.mp4parser.AbstractFullBox;
import com.googlecode.mp4parser.RequiresParseDetailAspect;
import java.nio.ByteBuffer;
import org.a.b.b.b;

public class XmlBox extends AbstractFullBox {
    public static final String TYPE = "xml ";
    String xml;

    static {
        XmlBox.ajc$preClinit();
    }

    public XmlBox() {
        super("xml ");
        this.xml = "";
    }

    public void _parseDetails(ByteBuffer arg2) {
        this.parseVersionAndFlags(arg2);
        this.xml = IsoTypeReader.readString(arg2, arg2.remaining());
    }

    private static void ajc$preClinit() {
        b v8 = new b("XmlBox.java", XmlBox.class);
        XmlBox.ajc$tjp_0 = v8.a("method-execution", v8.a("1", "getXml", "com.coremedia.iso.boxes.XmlBox", "", "", "", "java.lang.String"), 20);
        XmlBox.ajc$tjp_1 = v8.a("method-execution", v8.a("1", "setXml", "com.coremedia.iso.boxes.XmlBox", "java.lang.String", "xml", "", "void"), 24);
        XmlBox.ajc$tjp_2 = v8.a("method-execution", v8.a("1", "toString", "com.coremedia.iso.boxes.XmlBox", "", "", "", "java.lang.String"), 46);
    }

    protected void getContent(ByteBuffer arg2) {
        this.writeVersionAndFlags(arg2);
        arg2.put(Utf8.convert(this.xml));
    }

    protected long getContentSize() {
        return ((long)(Utf8.utf8StringLengthInBytes(this.xml) + 4));
    }

    public String getXml() {
        RequiresParseDetailAspect.aspectOf().before(b.a(XmlBox.ajc$tjp_0, this, this));
        return this.xml;
    }

    public void setXml(String arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(XmlBox.ajc$tjp_1, this, this, arg3));
        this.xml = arg3;
    }

    public String toString() {
        RequiresParseDetailAspect.aspectOf().before(b.a(XmlBox.ajc$tjp_2, this, this));
        StringBuilder v0 = new StringBuilder("XmlBox{xml=\'");
        v0.append(this.xml);
        v0.append('\'');
        v0.append('}');
        return v0.toString();
    }
}

