package com.coremedia.iso.boxes;

import com.coremedia.iso.IsoTypeReader;
import com.coremedia.iso.IsoTypeWriter;
import com.googlecode.mp4parser.AbstractBox;
import com.googlecode.mp4parser.RequiresParseDetailAspect;
import java.nio.ByteBuffer;
import org.a.b.b.b;

public class TrackReferenceTypeBox extends AbstractBox {
    public static final String TYPE1 = "hint";
    public static final String TYPE2 = "cdsc";
    private long[] trackIds;

    static {
        TrackReferenceTypeBox.ajc$preClinit();
    }

    public TrackReferenceTypeBox(String arg1) {
        super(arg1);
    }

    public void _parseDetails(ByteBuffer arg6) {
        int v0 = arg6.remaining() / 4;
        this.trackIds = new long[v0];
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            this.trackIds[v1] = IsoTypeReader.readUInt32(arg6);
        }
    }

    private static void ajc$preClinit() {
        b v8 = new b("TrackReferenceTypeBox.java", TrackReferenceTypeBox.class);
        TrackReferenceTypeBox.ajc$tjp_0 = v8.a("method-execution", v8.a("1", "getTrackIds", "com.coremedia.iso.boxes.TrackReferenceTypeBox", "", "", "", "[J"), 40);
        TrackReferenceTypeBox.ajc$tjp_1 = v8.a("method-execution", v8.a("1", "toString", "com.coremedia.iso.boxes.TrackReferenceTypeBox", "", "", "", "java.lang.String"), 65);
    }

    protected void getContent(ByteBuffer arg6) {
        long[] v0 = this.trackIds;
        int v1 = v0.length;
        int v2;
        for(v2 = 0; v2 < v1; ++v2) {
            IsoTypeWriter.writeUInt32(arg6, v0[v2]);
        }
    }

    protected long getContentSize() {
        return ((long)(this.trackIds.length * 4));
    }

    public long[] getTrackIds() {
        RequiresParseDetailAspect.aspectOf().before(b.a(TrackReferenceTypeBox.ajc$tjp_0, this, this));
        return this.trackIds;
    }

    public String toString() {
        RequiresParseDetailAspect.aspectOf().before(b.a(TrackReferenceTypeBox.ajc$tjp_1, this, this));
        StringBuilder v0 = new StringBuilder();
        v0.append("TrackReferenceTypeBox[type=");
        v0.append(this.getType());
        int v1;
        for(v1 = 0; v1 < this.trackIds.length; ++v1) {
            v0.append(";trackId");
            v0.append(v1);
            v0.append("=");
            v0.append(this.trackIds[v1]);
        }

        v0.append("]");
        return v0.toString();
    }
}

