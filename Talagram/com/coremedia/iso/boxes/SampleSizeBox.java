package com.coremedia.iso.boxes;

import com.coremedia.iso.IsoTypeReader;
import com.coremedia.iso.IsoTypeWriter;
import com.googlecode.mp4parser.AbstractFullBox;
import com.googlecode.mp4parser.RequiresParseDetailAspect;
import com.googlecode.mp4parser.util.CastUtils;
import java.nio.ByteBuffer;
import org.a.b.a.a;
import org.a.b.b.b;

public class SampleSizeBox extends AbstractFullBox {
    public static final String TYPE = "stsz";
    int sampleCount;
    private long sampleSize;
    private long[] sampleSizes;

    static {
        SampleSizeBox.ajc$preClinit();
    }

    public SampleSizeBox() {
        super("stsz");
        this.sampleSizes = new long[0];
    }

    public void _parseDetails(ByteBuffer arg6) {
        this.parseVersionAndFlags(arg6);
        this.sampleSize = IsoTypeReader.readUInt32(arg6);
        this.sampleCount = CastUtils.l2i(IsoTypeReader.readUInt32(arg6));
        if(this.sampleSize == 0) {
            this.sampleSizes = new long[this.sampleCount];
            int v0;
            for(v0 = 0; v0 < this.sampleCount; ++v0) {
                this.sampleSizes[v0] = IsoTypeReader.readUInt32(arg6);
            }
        }
    }

    private static void ajc$preClinit() {
        b v8 = new b("SampleSizeBox.java", SampleSizeBox.class);
        SampleSizeBox.ajc$tjp_0 = v8.a("method-execution", v8.a("1", "getSampleSize", "com.coremedia.iso.boxes.SampleSizeBox", "", "", "", "long"), 50);
        SampleSizeBox.ajc$tjp_1 = v8.a("method-execution", v8.a("1", "setSampleSize", "com.coremedia.iso.boxes.SampleSizeBox", "long", "sampleSize", "", "void"), 54);
        SampleSizeBox.ajc$tjp_2 = v8.a("method-execution", v8.a("1", "getSampleSizeAtIndex", "com.coremedia.iso.boxes.SampleSizeBox", "int", "index", "", "long"), 59);
        SampleSizeBox.ajc$tjp_3 = v8.a("method-execution", v8.a("1", "getSampleCount", "com.coremedia.iso.boxes.SampleSizeBox", "", "", "", "long"), 67);
        SampleSizeBox.ajc$tjp_4 = v8.a("method-execution", v8.a("1", "getSampleSizes", "com.coremedia.iso.boxes.SampleSizeBox", "", "", "", "[J"), 76);
        SampleSizeBox.ajc$tjp_5 = v8.a("method-execution", v8.a("1", "setSampleSizes", "com.coremedia.iso.boxes.SampleSizeBox", "[J", "sampleSizes", "", "void"), 80);
        SampleSizeBox.ajc$tjp_6 = v8.a("method-execution", v8.a("1", "toString", "com.coremedia.iso.boxes.SampleSizeBox", "", "", "", "java.lang.String"), 119);
    }

    protected void getContent(ByteBuffer arg6) {
        this.writeVersionAndFlags(arg6);
        IsoTypeWriter.writeUInt32(arg6, this.sampleSize);
        if(this.sampleSize == 0) {
            IsoTypeWriter.writeUInt32(arg6, ((long)this.sampleSizes.length));
            long[] v0 = this.sampleSizes;
            int v1 = v0.length;
            int v2;
            for(v2 = 0; v2 < v1; ++v2) {
                IsoTypeWriter.writeUInt32(arg6, v0[v2]);
            }
        }
        else {
            IsoTypeWriter.writeUInt32(arg6, ((long)this.sampleCount));
        }
    }

    protected long getContentSize() {
        int v0 = this.sampleSize == 0 ? this.sampleSizes.length * 4 : 0;
        return ((long)(v0 + 12));
    }

    public long getSampleCount() {
        RequiresParseDetailAspect.aspectOf().before(b.a(SampleSizeBox.ajc$tjp_3, this, this));
        int v0 = this.sampleSize > 0 ? this.sampleCount : this.sampleSizes.length;
        return ((long)v0);
    }

    public long getSampleSize() {
        RequiresParseDetailAspect.aspectOf().before(b.a(SampleSizeBox.ajc$tjp_0, this, this));
        return this.sampleSize;
    }

    public long getSampleSizeAtIndex(int arg6) {
        RequiresParseDetailAspect.aspectOf().before(b.a(SampleSizeBox.ajc$tjp_2, this, this, a.a(arg6)));
        if(this.sampleSize > 0) {
            return this.sampleSize;
        }

        return this.sampleSizes[arg6];
    }

    public long[] getSampleSizes() {
        RequiresParseDetailAspect.aspectOf().before(b.a(SampleSizeBox.ajc$tjp_4, this, this));
        return this.sampleSizes;
    }

    public void setSampleSize(long arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(SampleSizeBox.ajc$tjp_1, this, this, a.a(arg3)));
        this.sampleSize = arg3;
    }

    public void setSampleSizes(long[] arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(SampleSizeBox.ajc$tjp_5, this, this, arg3));
        this.sampleSizes = arg3;
    }

    public String toString() {
        RequiresParseDetailAspect.aspectOf().before(b.a(SampleSizeBox.ajc$tjp_6, this, this));
        StringBuilder v0 = new StringBuilder("SampleSizeBox[sampleSize=");
        v0.append(this.getSampleSize());
        v0.append(";sampleCount=");
        v0.append(this.getSampleCount());
        v0.append("]");
        return v0.toString();
    }
}

