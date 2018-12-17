package com.coremedia.iso.boxes;

import com.coremedia.iso.BoxParser;
import com.coremedia.iso.IsoTypeReader;
import com.coremedia.iso.IsoTypeWriter;
import com.coremedia.iso.boxes.sampleentry.AbstractSampleEntry;
import com.googlecode.mp4parser.AbstractContainerBox;
import com.googlecode.mp4parser.DataSource;
import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;
import java.util.Iterator;

public class SampleDescriptionBox extends AbstractContainerBox implements FullBox {
    public static final String TYPE = "stsd";
    private int flags;
    private int version;

    public SampleDescriptionBox() {
        super("stsd");
    }

    public void getBox(WritableByteChannel arg4) {
        arg4.write(this.getHeader());
        ByteBuffer v0 = ByteBuffer.allocate(8);
        IsoTypeWriter.writeUInt8(v0, this.version);
        IsoTypeWriter.writeUInt24(v0, this.flags);
        IsoTypeWriter.writeUInt32(v0, ((long)this.getBoxes().size()));
        arg4.write(v0.rewind());
        this.writeContainer(arg4);
    }

    public int getFlags() {
        return this.flags;
    }

    public AbstractSampleEntry getSampleEntry() {
        Iterator v0 = this.getBoxes(AbstractSampleEntry.class).iterator();
        if(v0.hasNext()) {
            return v0.next();
        }

        return null;
    }

    public long getSize() {
        long v2 = 8;
        long v0 = this.getContainerSize() + v2;
        int v2_1 = (this.largeBox) || v2 + v0 >= 4294967296L ? 16 : 8;
        return v0 + (((long)v2_1));
    }

    public int getVersion() {
        return this.version;
    }

    public void parse(DataSource arg3, ByteBuffer arg4, long arg5, BoxParser arg7) {
        arg4 = ByteBuffer.allocate(8);
        arg3.read(arg4);
        arg4.rewind();
        this.version = IsoTypeReader.readUInt8(arg4);
        this.flags = IsoTypeReader.readUInt24(arg4);
        this.initContainer(arg3, arg5 - 8, arg7);
    }

    public void setFlags(int arg1) {
        this.flags = arg1;
    }

    public void setVersion(int arg1) {
        this.version = arg1;
    }
}

