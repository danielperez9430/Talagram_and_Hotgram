package com.coremedia.iso.boxes;

import com.coremedia.iso.IsoTypeReader;
import com.coremedia.iso.IsoTypeWriter;
import com.googlecode.mp4parser.AbstractFullBox;
import com.googlecode.mp4parser.RequiresParseDetailAspect;
import com.googlecode.mp4parser.util.CastUtils;
import java.nio.ByteBuffer;
import org.a.b.b.b;

public class SyncSampleBox extends AbstractFullBox {
    public static final String TYPE = "stss";
    private long[] sampleNumber;

    static {
        SyncSampleBox.ajc$preClinit();
    }

    public SyncSampleBox() {
        super("stss");
    }

    public void _parseDetails(ByteBuffer arg6) {
        this.parseVersionAndFlags(arg6);
        int v0 = CastUtils.l2i(IsoTypeReader.readUInt32(arg6));
        this.sampleNumber = new long[v0];
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            this.sampleNumber[v1] = IsoTypeReader.readUInt32(arg6);
        }
    }

    private static void ajc$preClinit() {
        b v8 = new b("SyncSampleBox.java", SyncSampleBox.class);
        SyncSampleBox.ajc$tjp_0 = v8.a("method-execution", v8.a("1", "getSampleNumber", "com.coremedia.iso.boxes.SyncSampleBox", "", "", "", "[J"), 46);
        SyncSampleBox.ajc$tjp_1 = v8.a("method-execution", v8.a("1", "toString", "com.coremedia.iso.boxes.SyncSampleBox", "", "", "", "java.lang.String"), 77);
        SyncSampleBox.ajc$tjp_2 = v8.a("method-execution", v8.a("1", "setSampleNumber", "com.coremedia.iso.boxes.SyncSampleBox", "[J", "sampleNumber", "", "void"), 81);
    }

    protected void getContent(ByteBuffer arg6) {
        this.writeVersionAndFlags(arg6);
        IsoTypeWriter.writeUInt32(arg6, ((long)this.sampleNumber.length));
        long[] v0 = this.sampleNumber;
        int v1 = v0.length;
        int v2;
        for(v2 = 0; v2 < v1; ++v2) {
            IsoTypeWriter.writeUInt32(arg6, v0[v2]);
        }
    }

    protected long getContentSize() {
        return ((long)(this.sampleNumber.length * 4 + 8));
    }

    public long[] getSampleNumber() {
        RequiresParseDetailAspect.aspectOf().before(b.a(SyncSampleBox.ajc$tjp_0, this, this));
        return this.sampleNumber;
    }

    public void setSampleNumber(long[] arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(SyncSampleBox.ajc$tjp_2, this, this, arg3));
        this.sampleNumber = arg3;
    }

    public String toString() {
        RequiresParseDetailAspect.aspectOf().before(b.a(SyncSampleBox.ajc$tjp_1, this, this));
        StringBuilder v0 = new StringBuilder("SyncSampleBox[entryCount=");
        v0.append(this.sampleNumber.length);
        v0.append("]");
        return v0.toString();
    }
}

