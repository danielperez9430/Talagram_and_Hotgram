package com.coremedia.iso.boxes;

import com.googlecode.mp4parser.AbstractBox;
import com.googlecode.mp4parser.RequiresParseDetailAspect;
import java.nio.ByteBuffer;
import org.a.b.b.b;

public class UnknownBox extends AbstractBox {
    ByteBuffer data;

    static {
        UnknownBox.ajc$preClinit();
    }

    public UnknownBox(String arg1) {
        super(arg1);
    }

    public void _parseDetails(ByteBuffer arg3) {
        this.data = arg3;
        arg3.position(arg3.position() + arg3.remaining());
    }

    private static void ajc$preClinit() {
        b v8 = new b("UnknownBox.java", UnknownBox.class);
        UnknownBox.ajc$tjp_0 = v8.a("method-execution", v8.a("1", "getData", "com.coremedia.iso.boxes.UnknownBox", "", "", "", "java.nio.ByteBuffer"), 52);
        UnknownBox.ajc$tjp_1 = v8.a("method-execution", v8.a("1", "setData", "com.coremedia.iso.boxes.UnknownBox", "java.nio.ByteBuffer", "data", "", "void"), 56);
    }

    protected void getContent(ByteBuffer arg2) {
        this.data.rewind();
        arg2.put(this.data);
    }

    protected long getContentSize() {
        return ((long)this.data.limit());
    }

    public ByteBuffer getData() {
        RequiresParseDetailAspect.aspectOf().before(b.a(UnknownBox.ajc$tjp_0, this, this));
        return this.data;
    }

    public void setData(ByteBuffer arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(UnknownBox.ajc$tjp_1, this, this, arg3));
        this.data = arg3;
    }
}

