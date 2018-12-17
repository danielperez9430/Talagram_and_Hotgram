package com.coremedia.iso.boxes;

import com.coremedia.iso.IsoTypeReader;
import com.coremedia.iso.IsoTypeWriter;
import com.googlecode.mp4parser.RequiresParseDetailAspect;
import com.googlecode.mp4parser.util.CastUtils;
import java.nio.ByteBuffer;
import org.a.b.b.b;

public class ChunkOffset64BitBox extends ChunkOffsetBox {
    public static final String TYPE = "co64";
    private long[] chunkOffsets;

    static {
        ChunkOffset64BitBox.ajc$preClinit();
    }

    public ChunkOffset64BitBox() {
        super("co64");
    }

    public void _parseDetails(ByteBuffer arg6) {
        this.parseVersionAndFlags(arg6);
        int v0 = CastUtils.l2i(IsoTypeReader.readUInt32(arg6));
        this.chunkOffsets = new long[v0];
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            this.chunkOffsets[v1] = IsoTypeReader.readUInt64(arg6);
        }
    }

    private static void ajc$preClinit() {
        b v8 = new b("ChunkOffset64BitBox.java", ChunkOffset64BitBox.class);
        ChunkOffset64BitBox.ajc$tjp_0 = v8.a("method-execution", v8.a("1", "getChunkOffsets", "com.coremedia.iso.boxes.ChunkOffset64BitBox", "", "", "", "[J"), 23);
        ChunkOffset64BitBox.ajc$tjp_1 = v8.a("method-execution", v8.a("1", "setChunkOffsets", "com.coremedia.iso.boxes.ChunkOffset64BitBox", "[J", "chunkOffsets", "", "void"), 28);
    }

    public long[] getChunkOffsets() {
        RequiresParseDetailAspect.aspectOf().before(b.a(ChunkOffset64BitBox.ajc$tjp_0, this, this));
        return this.chunkOffsets;
    }

    protected void getContent(ByteBuffer arg6) {
        this.writeVersionAndFlags(arg6);
        IsoTypeWriter.writeUInt32(arg6, ((long)this.chunkOffsets.length));
        long[] v0 = this.chunkOffsets;
        int v1 = v0.length;
        int v2;
        for(v2 = 0; v2 < v1; ++v2) {
            IsoTypeWriter.writeUInt64(arg6, v0[v2]);
        }
    }

    protected long getContentSize() {
        return ((long)(this.chunkOffsets.length * 8 + 8));
    }

    public void setChunkOffsets(long[] arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(ChunkOffset64BitBox.ajc$tjp_1, this, this, arg3));
        this.chunkOffsets = arg3;
    }
}

