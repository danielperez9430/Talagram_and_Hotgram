package com.coremedia.iso.boxes.sampleentry;

import com.coremedia.iso.BoxParser;
import com.googlecode.mp4parser.AbstractContainerBox;
import com.googlecode.mp4parser.DataSource;
import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;

public abstract class AbstractSampleEntry extends AbstractContainerBox implements SampleEntry {
    protected int dataReferenceIndex;

    protected AbstractSampleEntry(String arg1) {
        super(arg1);
        this.dataReferenceIndex = 1;
    }

    public abstract void getBox(WritableByteChannel arg1);

    public int getDataReferenceIndex() {
        return this.dataReferenceIndex;
    }

    public abstract void parse(DataSource arg1, ByteBuffer arg2, long arg3, BoxParser arg4);

    public void setDataReferenceIndex(int arg1) {
        this.dataReferenceIndex = arg1;
    }
}

