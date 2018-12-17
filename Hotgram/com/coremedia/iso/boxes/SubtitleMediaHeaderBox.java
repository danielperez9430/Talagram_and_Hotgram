package com.coremedia.iso.boxes;

import com.googlecode.mp4parser.RequiresParseDetailAspect;
import java.nio.ByteBuffer;
import org.a.b.b.b;

public class SubtitleMediaHeaderBox extends AbstractMediaHeaderBox {
    public static final String TYPE = "sthd";

    static {
        SubtitleMediaHeaderBox.ajc$preClinit();
    }

    public SubtitleMediaHeaderBox() {
        super("sthd");
    }

    public void _parseDetails(ByteBuffer arg1) {
        this.parseVersionAndFlags(arg1);
    }

    private static void ajc$preClinit() {
        b v8 = new b("SubtitleMediaHeaderBox.java", SubtitleMediaHeaderBox.class);
        SubtitleMediaHeaderBox.ajc$tjp_0 = v8.a("method-execution", v8.a("1", "toString", "com.coremedia.iso.boxes.SubtitleMediaHeaderBox", "", "", "", "java.lang.String"), 30);
    }

    protected void getContent(ByteBuffer arg1) {
        this.writeVersionAndFlags(arg1);
    }

    protected long getContentSize() {
        return 4;
    }

    public String toString() {
        RequiresParseDetailAspect.aspectOf().before(b.a(SubtitleMediaHeaderBox.ajc$tjp_0, this, this));
        return "SubtitleMediaHeaderBox";
    }
}

