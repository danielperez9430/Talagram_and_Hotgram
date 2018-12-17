package com.coremedia.iso.boxes.sampleentry;

import com.coremedia.iso.BoxParser;
import com.coremedia.iso.IsoTypeReader;
import com.coremedia.iso.IsoTypeWriter;
import com.googlecode.mp4parser.DataSource;
import com.googlecode.mp4parser.util.CastUtils;
import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;

public class Ovc1VisualSampleEntryImpl extends AbstractSampleEntry {
    public static final String TYPE = "ovc1";
    private byte[] vc1Content;

    public Ovc1VisualSampleEntryImpl() {
        super("ovc1");
        this.vc1Content = new byte[0];
    }

    public void getBox(WritableByteChannel arg3) {
        arg3.write(this.getHeader());
        ByteBuffer v0 = ByteBuffer.allocate(8);
        v0.position(6);
        IsoTypeWriter.writeUInt16(v0, this.dataReferenceIndex);
        arg3.write(v0.rewind());
        arg3.write(ByteBuffer.wrap(this.vc1Content));
    }

    public long getSize() {
        int v1 = 16;
        if(!this.largeBox) {
            if((((long)(this.vc1Content.length + v1))) >= 4294967296L) {
            }
            else {
                v1 = 8;
            }
        }

        return (((long)v1)) + (((long)this.vc1Content.length)) + 8;
    }

    public byte[] getVc1Content() {
        return this.vc1Content;
    }

    public void parse(DataSource arg1, ByteBuffer arg2, long arg3, BoxParser arg5) {
        arg2 = ByteBuffer.allocate(CastUtils.l2i(arg3));
        arg1.read(arg2);
        arg2.position(6);
        this.dataReferenceIndex = IsoTypeReader.readUInt16(arg2);
        this.vc1Content = new byte[arg2.remaining()];
        arg2.get(this.vc1Content);
    }

    public void setVc1Content(byte[] arg1) {
        this.vc1Content = arg1;
    }
}

