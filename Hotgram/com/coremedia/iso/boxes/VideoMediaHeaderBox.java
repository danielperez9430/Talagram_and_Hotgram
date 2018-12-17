package com.coremedia.iso.boxes;

import com.coremedia.iso.IsoTypeReader;
import com.coremedia.iso.IsoTypeWriter;
import com.googlecode.mp4parser.RequiresParseDetailAspect;
import java.nio.ByteBuffer;
import org.a.b.a.a;
import org.a.b.b.b;

public class VideoMediaHeaderBox extends AbstractMediaHeaderBox {
    public static final String TYPE = "vmhd";
    private int graphicsmode;
    private int[] opcolor;

    static {
        VideoMediaHeaderBox.ajc$preClinit();
    }

    public VideoMediaHeaderBox() {
        super("vmhd");
        this.graphicsmode = 0;
        this.opcolor = new int[3];
        this.setFlags(1);
    }

    public void _parseDetails(ByteBuffer arg5) {
        this.parseVersionAndFlags(arg5);
        this.graphicsmode = IsoTypeReader.readUInt16(arg5);
        int v0 = 3;
        this.opcolor = new int[v0];
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            this.opcolor[v1] = IsoTypeReader.readUInt16(arg5);
        }
    }

    private static void ajc$preClinit() {
        b v8 = new b("VideoMediaHeaderBox.java", VideoMediaHeaderBox.class);
        VideoMediaHeaderBox.ajc$tjp_0 = v8.a("method-execution", v8.a("1", "getGraphicsmode", "com.coremedia.iso.boxes.VideoMediaHeaderBox", "", "", "", "int"), 39);
        VideoMediaHeaderBox.ajc$tjp_1 = v8.a("method-execution", v8.a("1", "getOpcolor", "com.coremedia.iso.boxes.VideoMediaHeaderBox", "", "", "", "[I"), 43);
        VideoMediaHeaderBox.ajc$tjp_2 = v8.a("method-execution", v8.a("1", "toString", "com.coremedia.iso.boxes.VideoMediaHeaderBox", "", "", "", "java.lang.String"), 71);
        VideoMediaHeaderBox.ajc$tjp_3 = v8.a("method-execution", v8.a("1", "setOpcolor", "com.coremedia.iso.boxes.VideoMediaHeaderBox", "[I", "opcolor", "", "void"), 75);
        VideoMediaHeaderBox.ajc$tjp_4 = v8.a("method-execution", v8.a("1", "setGraphicsmode", "com.coremedia.iso.boxes.VideoMediaHeaderBox", "int", "graphicsmode", "", "void"), 79);
    }

    protected void getContent(ByteBuffer arg5) {
        this.writeVersionAndFlags(arg5);
        IsoTypeWriter.writeUInt16(arg5, this.graphicsmode);
        int[] v0 = this.opcolor;
        int v1 = v0.length;
        int v2;
        for(v2 = 0; v2 < v1; ++v2) {
            IsoTypeWriter.writeUInt16(arg5, v0[v2]);
        }
    }

    protected long getContentSize() {
        return 12;
    }

    public int getGraphicsmode() {
        RequiresParseDetailAspect.aspectOf().before(b.a(VideoMediaHeaderBox.ajc$tjp_0, this, this));
        return this.graphicsmode;
    }

    public int[] getOpcolor() {
        RequiresParseDetailAspect.aspectOf().before(b.a(VideoMediaHeaderBox.ajc$tjp_1, this, this));
        return this.opcolor;
    }

    public void setGraphicsmode(int arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(VideoMediaHeaderBox.ajc$tjp_4, this, this, a.a(arg3)));
        this.graphicsmode = arg3;
    }

    public void setOpcolor(int[] arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(VideoMediaHeaderBox.ajc$tjp_3, this, this, arg3));
        this.opcolor = arg3;
    }

    public String toString() {
        RequiresParseDetailAspect.aspectOf().before(b.a(VideoMediaHeaderBox.ajc$tjp_2, this, this));
        StringBuilder v0 = new StringBuilder("VideoMediaHeaderBox[graphicsmode=");
        v0.append(this.getGraphicsmode());
        v0.append(";opcolor0=");
        v0.append(this.getOpcolor()[0]);
        v0.append(";opcolor1=");
        v0.append(this.getOpcolor()[1]);
        v0.append(";opcolor2=");
        v0.append(this.getOpcolor()[2]);
        v0.append("]");
        return v0.toString();
    }
}

