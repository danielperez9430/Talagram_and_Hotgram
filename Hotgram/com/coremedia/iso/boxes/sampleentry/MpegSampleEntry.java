package com.coremedia.iso.boxes.sampleentry;

import com.coremedia.iso.BoxParser;
import com.coremedia.iso.IsoTypeReader;
import com.coremedia.iso.IsoTypeWriter;
import com.googlecode.mp4parser.DataSource;
import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;
import java.util.Arrays;
import java.util.List;

public class MpegSampleEntry extends AbstractSampleEntry {
    public MpegSampleEntry() {
        super("mp4s");
    }

    public MpegSampleEntry(String arg1) {
        super(arg1);
    }

    public void getBox(WritableByteChannel arg3) {
        arg3.write(this.getHeader());
        ByteBuffer v0 = ByteBuffer.allocate(8);
        v0.position(6);
        IsoTypeWriter.writeUInt16(v0, this.dataReferenceIndex);
        arg3.write(v0.rewind());
        this.writeContainer(arg3);
    }

    public long getSize() {
        long v0 = this.getContainerSize() + 8;
        int v2 = (this.largeBox) || v0 >= 4294967296L ? 16 : 8;
        return v0 + (((long)v2));
    }

    public void parse(DataSource arg3, ByteBuffer arg4, long arg5, BoxParser arg7) {
        arg4 = ByteBuffer.allocate(8);
        arg3.read(arg4);
        arg4.position(6);
        this.dataReferenceIndex = IsoTypeReader.readUInt16(arg4);
        this.initContainer(arg3, arg5 - 8, arg7);
    }

    public String toString() {
        StringBuilder v0 = new StringBuilder("MpegSampleEntry");
        v0.append(Arrays.asList(new List[]{this.getBoxes()}));
        return v0.toString();
    }
}

