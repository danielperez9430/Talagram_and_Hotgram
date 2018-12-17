package com.googlecode.mp4parser;

import com.coremedia.iso.BoxParser;
import com.coremedia.iso.IsoTypeWriter;
import com.coremedia.iso.boxes.Box;
import com.coremedia.iso.boxes.Container;
import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;

public class AbstractContainerBox extends BasicContainer implements Box {
    protected boolean largeBox;
    private long offset;
    Container parent;
    protected String type;

    public AbstractContainerBox(String arg1) {
        super();
        this.type = arg1;
    }

    public void getBox(WritableByteChannel arg2) {
        arg2.write(this.getHeader());
        this.writeContainer(arg2);
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
            v0 = new byte[16];
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
            v0 = new byte[v1];
            v0[v7] = this.type.getBytes()[0];
            v0[v5] = this.type.getBytes()[1];
            v0[v4] = this.type.getBytes()[v3];
            v0[v2] = this.type.getBytes()[v9];
            v0_1 = ByteBuffer.wrap(v0);
            IsoTypeWriter.writeUInt32(v0_1, this.getSize());
        }

        v0_1.rewind();
        return v0_1;
    }

    public long getOffset() {
        return this.offset;
    }

    public Container getParent() {
        return this.parent;
    }

    public long getSize() {
        long v0 = this.getContainerSize();
        int v2 = (this.largeBox) || 8 + v0 >= 4294967296L ? 16 : 8;
        return v0 + (((long)v2));
    }

    public String getType() {
        return this.type;
    }

    public void initContainer(DataSource arg8, long arg9, BoxParser arg11) {
        this.dataSource = arg8;
        this.parsePosition = arg8.position();
        long v0 = this.parsePosition;
        int v2 = (this.largeBox) || 8 + arg9 >= 4294967296L ? 16 : 8;
        this.startPosition = v0 - (((long)v2));
        arg8.position(arg8.position() + arg9);
        this.endPosition = arg8.position();
        this.boxParser = arg11;
    }

    public void parse(DataSource arg5, ByteBuffer arg6, long arg7, BoxParser arg9) {
        this.offset = arg5.position() - (((long)arg6.remaining()));
        boolean v6 = arg6.remaining() == 16 ? true : false;
        this.largeBox = v6;
        this.initContainer(arg5, arg7, arg9);
    }

    public void setParent(Container arg1) {
        this.parent = arg1;
    }
}

