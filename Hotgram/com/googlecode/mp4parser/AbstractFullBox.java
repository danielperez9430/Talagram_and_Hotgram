package com.googlecode.mp4parser;

import com.coremedia.iso.IsoTypeReader;
import com.coremedia.iso.IsoTypeWriter;
import com.coremedia.iso.boxes.FullBox;
import com.googlecode.mp4parser.annotations.DoNotParseDetail;
import java.nio.ByteBuffer;
import org.a.b.a.a;
import org.a.b.b.b;

public abstract class AbstractFullBox extends AbstractBox implements FullBox {
    private int flags;
    private int version;

    static {
        AbstractFullBox.ajc$preClinit();
    }

    protected AbstractFullBox(String arg1) {
        super(arg1);
    }

    protected AbstractFullBox(String arg1, byte[] arg2) {
        super(arg1, arg2);
    }

    private static void ajc$preClinit() {
        b v8 = new b("AbstractFullBox.java", AbstractFullBox.class);
        AbstractFullBox.ajc$tjp_0 = v8.a("method-execution", v8.a("1", "setVersion", "com.googlecode.mp4parser.AbstractFullBox", "int", "version", "", "void"), 51);
        AbstractFullBox.ajc$tjp_1 = v8.a("method-execution", v8.a("1", "setFlags", "com.googlecode.mp4parser.AbstractFullBox", "int", "flags", "", "void"), 64);
    }

    @DoNotParseDetail public int getFlags() {
        if(!this.isParsed) {
            this.parseDetails();
        }

        return this.flags;
    }

    @DoNotParseDetail public int getVersion() {
        if(!this.isParsed) {
            this.parseDetails();
        }

        return this.version;
    }

    protected final long parseVersionAndFlags(ByteBuffer arg3) {
        this.version = IsoTypeReader.readUInt8(arg3);
        this.flags = IsoTypeReader.readUInt24(arg3);
        return 4;
    }

    public void setFlags(int arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(AbstractFullBox.ajc$tjp_1, this, this, a.a(arg3)));
        this.flags = arg3;
    }

    public void setVersion(int arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(AbstractFullBox.ajc$tjp_0, this, this, a.a(arg3)));
        this.version = arg3;
    }

    protected final void writeVersionAndFlags(ByteBuffer arg2) {
        IsoTypeWriter.writeUInt8(arg2, this.version);
        IsoTypeWriter.writeUInt24(arg2, this.flags);
    }
}

