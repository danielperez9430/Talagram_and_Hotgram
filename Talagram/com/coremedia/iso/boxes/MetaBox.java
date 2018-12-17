package com.coremedia.iso.boxes;

import com.coremedia.iso.BoxParser;
import com.coremedia.iso.IsoTypeReader;
import com.coremedia.iso.IsoTypeWriter;
import com.googlecode.mp4parser.AbstractContainerBox;
import com.googlecode.mp4parser.DataSource;
import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;

public class MetaBox extends AbstractContainerBox {
    public static final String TYPE = "meta";
    private int flags;
    private int version;

    public MetaBox() {
        super("meta");
    }

    public void getBox(WritableByteChannel arg2) {
        arg2.write(this.getHeader());
        ByteBuffer v0 = ByteBuffer.allocate(4);
        this.writeVersionAndFlags(v0);
        arg2.write(v0.rewind());
        this.writeContainer(arg2);
    }

    public int getFlags() {
        return this.flags;
    }

    public long getSize() {
        long v0 = this.getContainerSize() + 4;
        int v2 = (this.largeBox) || v0 >= 4294967296L ? 16 : 8;
        return v0 + (((long)v2));
    }

    public int getVersion() {
        return this.version;
    }

    public void parse(DataSource arg3, ByteBuffer arg4, long arg5, BoxParser arg7) {
        arg4 = ByteBuffer.allocate(4);
        arg3.read(arg4);
        this.parseVersionAndFlags(arg4.rewind());
        this.initContainer(arg3, arg5 - 4, arg7);
    }

    protected final long parseVersionAndFlags(ByteBuffer arg3) {
        this.version = IsoTypeReader.readUInt8(arg3);
        this.flags = IsoTypeReader.readUInt24(arg3);
        return 4;
    }

    public void setFlags(int arg1) {
        this.flags = arg1;
    }

    public void setVersion(int arg1) {
        this.version = arg1;
    }

    protected final void writeVersionAndFlags(ByteBuffer arg2) {
        IsoTypeWriter.writeUInt8(arg2, this.version);
        IsoTypeWriter.writeUInt24(arg2, this.flags);
    }
}

