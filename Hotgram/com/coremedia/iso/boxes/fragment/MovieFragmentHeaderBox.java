package com.coremedia.iso.boxes.fragment;

import com.coremedia.iso.IsoTypeReader;
import com.coremedia.iso.IsoTypeWriter;
import com.googlecode.mp4parser.AbstractFullBox;
import com.googlecode.mp4parser.RequiresParseDetailAspect;
import java.nio.ByteBuffer;
import org.a.b.a.a;
import org.a.b.b.b;

public class MovieFragmentHeaderBox extends AbstractFullBox {
    public static final String TYPE = "mfhd";
    private long sequenceNumber;

    static {
        MovieFragmentHeaderBox.ajc$preClinit();
    }

    public MovieFragmentHeaderBox() {
        super("mfhd");
    }

    public void _parseDetails(ByteBuffer arg3) {
        this.parseVersionAndFlags(arg3);
        this.sequenceNumber = IsoTypeReader.readUInt32(arg3);
    }

    private static void ajc$preClinit() {
        b v8 = new b("MovieFragmentHeaderBox.java", MovieFragmentHeaderBox.class);
        MovieFragmentHeaderBox.ajc$tjp_0 = v8.a("method-execution", v8.a("1", "getSequenceNumber", "com.coremedia.iso.boxes.fragment.MovieFragmentHeaderBox", "", "", "", "long"), 59);
        MovieFragmentHeaderBox.ajc$tjp_1 = v8.a("method-execution", v8.a("1", "setSequenceNumber", "com.coremedia.iso.boxes.fragment.MovieFragmentHeaderBox", "long", "sequenceNumber", "", "void"), 63);
        MovieFragmentHeaderBox.ajc$tjp_2 = v8.a("method-execution", v8.a("1", "toString", "com.coremedia.iso.boxes.fragment.MovieFragmentHeaderBox", "", "", "", "java.lang.String"), 68);
    }

    protected void getContent(ByteBuffer arg3) {
        this.writeVersionAndFlags(arg3);
        IsoTypeWriter.writeUInt32(arg3, this.sequenceNumber);
    }

    protected long getContentSize() {
        return 8;
    }

    public long getSequenceNumber() {
        RequiresParseDetailAspect.aspectOf().before(b.a(MovieFragmentHeaderBox.ajc$tjp_0, this, this));
        return this.sequenceNumber;
    }

    public void setSequenceNumber(long arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(MovieFragmentHeaderBox.ajc$tjp_1, this, this, a.a(arg3)));
        this.sequenceNumber = arg3;
    }

    public String toString() {
        RequiresParseDetailAspect.aspectOf().before(b.a(MovieFragmentHeaderBox.ajc$tjp_2, this, this));
        StringBuilder v0 = new StringBuilder("MovieFragmentHeaderBox{sequenceNumber=");
        v0.append(this.sequenceNumber);
        v0.append('}');
        return v0.toString();
    }
}

