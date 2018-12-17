package com.coremedia.iso.boxes.fragment;

import com.coremedia.iso.IsoTypeReader;
import com.coremedia.iso.IsoTypeWriter;
import com.googlecode.mp4parser.AbstractFullBox;
import com.googlecode.mp4parser.RequiresParseDetailAspect;
import java.nio.ByteBuffer;
import org.a.b.a.a;
import org.a.b.b.b;

public class TrackFragmentBaseMediaDecodeTimeBox extends AbstractFullBox {
    public static final String TYPE = "tfdt";
    private long baseMediaDecodeTime;

    static {
        TrackFragmentBaseMediaDecodeTimeBox.ajc$preClinit();
    }

    public TrackFragmentBaseMediaDecodeTimeBox() {
        super("tfdt");
    }

    public void _parseDetails(ByteBuffer arg3) {
        this.parseVersionAndFlags(arg3);
        long v0 = this.getVersion() == 1 ? IsoTypeReader.readUInt64(arg3) : IsoTypeReader.readUInt32(arg3);
        this.baseMediaDecodeTime = v0;
    }

    private static void ajc$preClinit() {
        b v8 = new b("TrackFragmentBaseMediaDecodeTimeBox.java", TrackFragmentBaseMediaDecodeTimeBox.class);
        TrackFragmentBaseMediaDecodeTimeBox.ajc$tjp_0 = v8.a("method-execution", v8.a("1", "getBaseMediaDecodeTime", "com.coremedia.iso.boxes.fragment.TrackFragmentBaseMediaDecodeTimeBox", "", "", "", "long"), 65);
        TrackFragmentBaseMediaDecodeTimeBox.ajc$tjp_1 = v8.a("method-execution", v8.a("1", "setBaseMediaDecodeTime", "com.coremedia.iso.boxes.fragment.TrackFragmentBaseMediaDecodeTimeBox", "long", "baseMediaDecodeTime", "", "void"), 69);
        TrackFragmentBaseMediaDecodeTimeBox.ajc$tjp_2 = v8.a("method-execution", v8.a("1", "toString", "com.coremedia.iso.boxes.fragment.TrackFragmentBaseMediaDecodeTimeBox", "", "", "", "java.lang.String"), 74);
    }

    public long getBaseMediaDecodeTime() {
        RequiresParseDetailAspect.aspectOf().before(b.a(TrackFragmentBaseMediaDecodeTimeBox.ajc$tjp_0, this, this));
        return this.baseMediaDecodeTime;
    }

    protected void getContent(ByteBuffer arg3) {
        this.writeVersionAndFlags(arg3);
        if(this.getVersion() == 1) {
            IsoTypeWriter.writeUInt64(arg3, this.baseMediaDecodeTime);
        }
        else {
            IsoTypeWriter.writeUInt32(arg3, this.baseMediaDecodeTime);
        }
    }

    protected long getContentSize() {
        int v0 = this.getVersion() == 0 ? 8 : 12;
        return ((long)v0);
    }

    public void setBaseMediaDecodeTime(long arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(TrackFragmentBaseMediaDecodeTimeBox.ajc$tjp_1, this, this, a.a(arg3)));
        this.baseMediaDecodeTime = arg3;
    }

    public String toString() {
        RequiresParseDetailAspect.aspectOf().before(b.a(TrackFragmentBaseMediaDecodeTimeBox.ajc$tjp_2, this, this));
        StringBuilder v0 = new StringBuilder("TrackFragmentBaseMediaDecodeTimeBox{baseMediaDecodeTime=");
        v0.append(this.baseMediaDecodeTime);
        v0.append('}');
        return v0.toString();
    }
}

