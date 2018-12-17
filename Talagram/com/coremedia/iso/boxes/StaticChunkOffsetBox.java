package com.coremedia.iso.boxes;

import com.coremedia.iso.IsoTypeReader;
import com.coremedia.iso.IsoTypeWriter;
import com.googlecode.mp4parser.RequiresParseDetailAspect;
import com.googlecode.mp4parser.util.CastUtils;
import java.nio.ByteBuffer;
import org.a.b.b.b;

public class StaticChunkOffsetBox extends ChunkOffsetBox {
    public static final String TYPE = "stco";
    private long[] chunkOffsets;

    static {
        StaticChunkOffsetBox.ajc$preClinit();
    }

    public StaticChunkOffsetBox() {
        super("stco");
        this.chunkOffsets = new long[0];
    }

    public void _parseDetails(ByteBuffer arg6) {
        this.parseVersionAndFlags(arg6);
        int v0 = CastUtils.l2i(IsoTypeReader.readUInt32(arg6));
        this.chunkOffsets = new long[v0];
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            this.chunkOffsets[v1] = IsoTypeReader.readUInt32(arg6);
        }
    }

    private static void ajc$preClinit() {
        b v8 = new b("StaticChunkOffsetBox.java", StaticChunkOffsetBox.class);
        StaticChunkOffsetBox.ajc$tjp_0 = v8.a("method-execution", v8.a("1", "getChunkOffsets", "com.coremedia.iso.boxes.StaticChunkOffsetBox", "", "", "", "[J"), 39);
        StaticChunkOffsetBox.ajc$tjp_1 = v8.a("method-execution", v8.a("1", "setChunkOffsets", "com.coremedia.iso.boxes.StaticChunkOffsetBox", "[J", "chunkOffsets", "", "void"), 48);
    }

    public long[] getChunkOffsets() {
        RequiresParseDetailAspect.aspectOf().before(b.a(StaticChunkOffsetBox.ajc$tjp_0, this, this));
        return this.chunkOffsets;
    }

    protected void getContent(ByteBuffer arg6) {
        this.writeVersionAndFlags(arg6);
        IsoTypeWriter.writeUInt32(arg6, ((long)this.chunkOffsets.length));
        long[] v0 = this.chunkOffsets;
        int v1 = v0.length;
        int v2;
        for(v2 = 0; v2 < v1; ++v2) {
            IsoTypeWriter.writeUInt32(arg6, v0[v2]);
        }
    }

    protected long getContentSize() {
        return ((long)(this.chunkOffsets.length * 4 + 8));
    }

    public void setChunkOffsets(long[] arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(StaticChunkOffsetBox.ajc$tjp_1, this, this, arg3));
        this.chunkOffsets = arg3;
    }
}

