package com.coremedia.iso.boxes.fragment;

import com.coremedia.iso.IsoTypeReader;
import com.coremedia.iso.IsoTypeWriter;
import com.googlecode.mp4parser.AbstractFullBox;
import com.googlecode.mp4parser.RequiresParseDetailAspect;
import java.nio.ByteBuffer;
import org.a.b.a.a;
import org.a.b.b.b;

public class MovieFragmentRandomAccessOffsetBox extends AbstractFullBox {
    public static final String TYPE = "mfro";
    private long mfraSize;

    static {
        MovieFragmentRandomAccessOffsetBox.ajc$preClinit();
    }

    public MovieFragmentRandomAccessOffsetBox() {
        super("mfro");
    }

    public void _parseDetails(ByteBuffer arg3) {
        this.parseVersionAndFlags(arg3);
        this.mfraSize = IsoTypeReader.readUInt32(arg3);
    }

    private static void ajc$preClinit() {
        b v8 = new b("MovieFragmentRandomAccessOffsetBox.java", MovieFragmentRandomAccessOffsetBox.class);
        MovieFragmentRandomAccessOffsetBox.ajc$tjp_0 = v8.a("method-execution", v8.a("1", "getMfraSize", "com.coremedia.iso.boxes.fragment.MovieFragmentRandomAccessOffsetBox", "", "", "", "long"), 56);
        MovieFragmentRandomAccessOffsetBox.ajc$tjp_1 = v8.a("method-execution", v8.a("1", "setMfraSize", "com.coremedia.iso.boxes.fragment.MovieFragmentRandomAccessOffsetBox", "long", "mfraSize", "", "void"), 60);
    }

    protected void getContent(ByteBuffer arg3) {
        this.writeVersionAndFlags(arg3);
        IsoTypeWriter.writeUInt32(arg3, this.mfraSize);
    }

    protected long getContentSize() {
        return 8;
    }

    public long getMfraSize() {
        RequiresParseDetailAspect.aspectOf().before(b.a(MovieFragmentRandomAccessOffsetBox.ajc$tjp_0, this, this));
        return this.mfraSize;
    }

    public void setMfraSize(long arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(MovieFragmentRandomAccessOffsetBox.ajc$tjp_1, this, this, a.a(arg3)));
        this.mfraSize = arg3;
    }
}

