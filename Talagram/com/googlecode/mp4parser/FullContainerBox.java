package com.googlecode.mp4parser;

import com.coremedia.iso.BoxParser;
import com.coremedia.iso.IsoTypeReader;
import com.coremedia.iso.IsoTypeWriter;
import com.coremedia.iso.boxes.FullBox;
import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;
import java.util.List;
import java.util.logging.Logger;

public abstract class FullContainerBox extends AbstractContainerBox implements FullBox {
    private static Logger LOG;
    private int flags;
    private int version;

    static {
        FullContainerBox.LOG = Logger.getLogger(FullContainerBox.class.getName());
    }

    public FullContainerBox(String arg1) {
        super(arg1);
    }

    public void getBox(WritableByteChannel arg1) {
        super.getBox(arg1);
    }

    public List getBoxes(Class arg2) {
        return this.getBoxes(arg2, false);
    }

    public int getFlags() {
        return this.flags;
    }

    protected ByteBuffer getHeader() {
        ByteBuffer v0_1;
        byte[] v0;
        int v1 = 8;
        int v2 = 7;
        int v3 = 2;
        int v4 = 6;
        int v5 = 5;
        int v7 = 4;
        int v9 = 3;
        if((this.largeBox) || this.getSize() >= 4294967296L) {
            v0 = new byte[20];
            v0[v9] = 1;
            v0[v7] = this.type.getBytes()[0];
            v0[v5] = this.type.getBytes()[1];
            v0[v4] = this.type.getBytes()[v3];
            v0[v2] = this.type.getBytes()[v9];
            v0_1 = ByteBuffer.wrap(v0);
            v0_1.position(v1);
            IsoTypeWriter.writeUInt64(v0_1, this.getSize());
        }
        else {
            v0 = new byte[12];
            v0[v7] = this.type.getBytes()[0];
            v0[v5] = this.type.getBytes()[1];
            v0[v4] = this.type.getBytes()[v3];
            v0[v2] = this.type.getBytes()[v9];
            v0_1 = ByteBuffer.wrap(v0);
            IsoTypeWriter.writeUInt32(v0_1, this.getSize());
            v0_1.position(v1);
        }

        this.writeVersionAndFlags(v0_1);
        v0_1.rewind();
        return v0_1;
    }

    public int getVersion() {
        return this.version;
    }

    public void parse(DataSource arg2, ByteBuffer arg3, long arg4, BoxParser arg6) {
        ByteBuffer v0 = ByteBuffer.allocate(4);
        arg2.read(v0);
        this.parseVersionAndFlags(v0.rewind());
        super.parse(arg2, arg3, arg4, arg6);
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

    public String toString() {
        StringBuilder v0 = new StringBuilder(String.valueOf(this.getClass().getSimpleName()));
        v0.append("[childBoxes]");
        return v0.toString();
    }

    protected final void writeVersionAndFlags(ByteBuffer arg2) {
        IsoTypeWriter.writeUInt8(arg2, this.version);
        IsoTypeWriter.writeUInt24(arg2, this.flags);
    }
}

