package com.coremedia.iso.boxes;

import com.googlecode.mp4parser.AbstractFullBox;
import com.googlecode.mp4parser.RequiresParseDetailAspect;
import org.a.b.b.b;

public abstract class ChunkOffsetBox extends AbstractFullBox {
    static {
        ChunkOffsetBox.ajc$preClinit();
    }

    public ChunkOffsetBox(String arg1) {
        super(arg1);
    }

    private static void ajc$preClinit() {
        b v8 = new b("ChunkOffsetBox.java", ChunkOffsetBox.class);
        ChunkOffsetBox.ajc$tjp_0 = v8.a("method-execution", v8.a("1", "toString", "com.coremedia.iso.boxes.ChunkOffsetBox", "", "", "", "java.lang.String"), 18);
    }

    public abstract long[] getChunkOffsets();

    public abstract void setChunkOffsets(long[] arg1);

    public String toString() {
        RequiresParseDetailAspect.aspectOf().before(b.a(ChunkOffsetBox.ajc$tjp_0, this, this));
        StringBuilder v0 = new StringBuilder(String.valueOf(this.getClass().getSimpleName()));
        v0.append("[entryCount=");
        v0.append(this.getChunkOffsets().length);
        v0.append("]");
        return v0.toString();
    }
}

