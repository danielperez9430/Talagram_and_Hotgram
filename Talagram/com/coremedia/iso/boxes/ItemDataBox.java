package com.coremedia.iso.boxes;

import com.googlecode.mp4parser.AbstractBox;
import com.googlecode.mp4parser.RequiresParseDetailAspect;
import java.nio.ByteBuffer;
import org.a.b.b.b;

public class ItemDataBox extends AbstractBox {
    public static final String TYPE = "idat";
    ByteBuffer data;

    static {
        ItemDataBox.ajc$preClinit();
    }

    public ItemDataBox() {
        super("idat");
        this.data = ByteBuffer.allocate(0);
    }

    public void _parseDetails(ByteBuffer arg3) {
        this.data = arg3.slice();
        arg3.position(arg3.position() + arg3.remaining());
    }

    private static void ajc$preClinit() {
        b v8 = new b("ItemDataBox.java", ItemDataBox.class);
        ItemDataBox.ajc$tjp_0 = v8.a("method-execution", v8.a("1", "getData", "com.coremedia.iso.boxes.ItemDataBox", "", "", "", "java.nio.ByteBuffer"), 19);
        ItemDataBox.ajc$tjp_1 = v8.a("method-execution", v8.a("1", "setData", "com.coremedia.iso.boxes.ItemDataBox", "java.nio.ByteBuffer", "data", "", "void"), 23);
    }

    protected void getContent(ByteBuffer arg2) {
        arg2.put(this.data);
    }

    protected long getContentSize() {
        return ((long)this.data.limit());
    }

    public ByteBuffer getData() {
        RequiresParseDetailAspect.aspectOf().before(b.a(ItemDataBox.ajc$tjp_0, this, this));
        return this.data;
    }

    public void setData(ByteBuffer arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(ItemDataBox.ajc$tjp_1, this, this, arg3));
        this.data = arg3;
    }
}

