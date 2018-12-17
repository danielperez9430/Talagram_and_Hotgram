package com.coremedia.iso.boxes;

import java.nio.ByteBuffer;

public class NullMediaHeaderBox extends AbstractMediaHeaderBox {
    public static final String TYPE = "nmhd";

    public NullMediaHeaderBox() {
        super("nmhd");
    }

    public void _parseDetails(ByteBuffer arg1) {
        this.parseVersionAndFlags(arg1);
    }

    protected void getContent(ByteBuffer arg1) {
        this.writeVersionAndFlags(arg1);
    }

    protected long getContentSize() {
        return 4;
    }
}

