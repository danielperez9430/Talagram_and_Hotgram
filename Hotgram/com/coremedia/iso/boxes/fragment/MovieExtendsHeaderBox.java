package com.coremedia.iso.boxes.fragment;

import com.coremedia.iso.IsoTypeReader;
import com.coremedia.iso.IsoTypeWriter;
import com.googlecode.mp4parser.AbstractFullBox;
import com.googlecode.mp4parser.RequiresParseDetailAspect;
import java.nio.ByteBuffer;
import org.a.b.a.a;
import org.a.b.b.b;

public class MovieExtendsHeaderBox extends AbstractFullBox {
    public static final String TYPE = "mehd";
    private long fragmentDuration;

    static {
        MovieExtendsHeaderBox.ajc$preClinit();
    }

    public MovieExtendsHeaderBox() {
        super("mehd");
    }

    public void _parseDetails(ByteBuffer arg3) {
        this.parseVersionAndFlags(arg3);
        long v0 = this.getVersion() == 1 ? IsoTypeReader.readUInt64(arg3) : IsoTypeReader.readUInt32(arg3);
        this.fragmentDuration = v0;
    }

    private static void ajc$preClinit() {
        b v8 = new b("MovieExtendsHeaderBox.java", MovieExtendsHeaderBox.class);
        MovieExtendsHeaderBox.ajc$tjp_0 = v8.a("method-execution", v8.a("1", "getFragmentDuration", "com.coremedia.iso.boxes.fragment.MovieExtendsHeaderBox", "", "", "", "long"), 65);
        MovieExtendsHeaderBox.ajc$tjp_1 = v8.a("method-execution", v8.a("1", "setFragmentDuration", "com.coremedia.iso.boxes.fragment.MovieExtendsHeaderBox", "long", "fragmentDuration", "", "void"), 69);
    }

    protected void getContent(ByteBuffer arg3) {
        this.writeVersionAndFlags(arg3);
        if(this.getVersion() == 1) {
            IsoTypeWriter.writeUInt64(arg3, this.fragmentDuration);
        }
        else {
            IsoTypeWriter.writeUInt32(arg3, this.fragmentDuration);
        }
    }

    protected long getContentSize() {
        int v0 = this.getVersion() == 1 ? 12 : 8;
        return ((long)v0);
    }

    public long getFragmentDuration() {
        RequiresParseDetailAspect.aspectOf().before(b.a(MovieExtendsHeaderBox.ajc$tjp_0, this, this));
        return this.fragmentDuration;
    }

    public void setFragmentDuration(long arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(MovieExtendsHeaderBox.ajc$tjp_1, this, this, a.a(arg3)));
        this.fragmentDuration = arg3;
    }
}

