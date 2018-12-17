package com.coremedia.iso.boxes;

import com.googlecode.mp4parser.AbstractFullBox;
import com.googlecode.mp4parser.RequiresParseDetailAspect;
import java.nio.ByteBuffer;
import org.a.b.b.b;

public class DataEntryUrlBox extends AbstractFullBox {
    public static final String TYPE = "url ";

    static {
        DataEntryUrlBox.ajc$preClinit();
    }

    public DataEntryUrlBox() {
        super("url ");
    }

    public void _parseDetails(ByteBuffer arg1) {
        this.parseVersionAndFlags(arg1);
    }

    private static void ajc$preClinit() {
        b v8 = new b("DataEntryUrlBox.java", DataEntryUrlBox.class);
        DataEntryUrlBox.ajc$tjp_0 = v8.a("method-execution", v8.a("1", "toString", "com.coremedia.iso.boxes.DataEntryUrlBox", "", "", "", "java.lang.String"), 51);
    }

    protected void getContent(ByteBuffer arg1) {
        this.writeVersionAndFlags(arg1);
    }

    protected long getContentSize() {
        return 4;
    }

    public String toString() {
        RequiresParseDetailAspect.aspectOf().before(b.a(DataEntryUrlBox.ajc$tjp_0, this, this));
        return "DataEntryUrlBox[]";
    }
}

