package com.coremedia.iso.boxes;

import com.coremedia.iso.BoxParser;
import com.coremedia.iso.IsoTypeReader;
import com.coremedia.iso.IsoTypeWriter;
import com.googlecode.mp4parser.AbstractContainerBox;
import com.googlecode.mp4parser.DataSource;
import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;

public class ItemProtectionBox extends AbstractContainerBox implements FullBox {
    public static final String TYPE = "ipro";
    private int flags;
    private int version;

    public ItemProtectionBox() {
        super("ipro");
    }

    public void getBox(WritableByteChannel arg3) {
        arg3.write(this.getHeader());
        ByteBuffer v0 = ByteBuffer.allocate(6);
        IsoTypeWriter.writeUInt8(v0, this.version);
        IsoTypeWriter.writeUInt24(v0, this.flags);
        IsoTypeWriter.writeUInt16(v0, this.getBoxes().size());
        arg3.write(v0.rewind());
        this.writeContainer(arg3);
    }

    public int getFlags() {
        return this.flags;
    }

    public SchemeInformationBox getItemProtectionScheme() {
        if(!this.getBoxes(SchemeInformationBox.class).isEmpty()) {
            return this.getBoxes(SchemeInformationBox.class).get(0);
        }

        return null;
    }

    public long getSize() {
        long v0 = this.getContainerSize() + 6;
        int v2 = (this.largeBox) || v0 >= 4294967296L ? 16 : 8;
        return v0 + (((long)v2));
    }

    public int getVersion() {
        return this.version;
    }

    public void parse(DataSource arg3, ByteBuffer arg4, long arg5, BoxParser arg7) {
        arg4 = ByteBuffer.allocate(6);
        arg3.read(arg4);
        arg4.rewind();
        this.version = IsoTypeReader.readUInt8(arg4);
        this.flags = IsoTypeReader.readUInt24(arg4);
        this.initContainer(arg3, arg5 - 6, arg7);
    }

    public void setFlags(int arg1) {
        this.flags = arg1;
    }

    public void setVersion(int arg1) {
        this.version = arg1;
    }
}

