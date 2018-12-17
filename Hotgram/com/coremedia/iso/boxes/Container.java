package com.coremedia.iso.boxes;

import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;
import java.util.List;

public interface Container {
    List getBoxes();

    List getBoxes(Class arg1);

    List getBoxes(Class arg1, boolean arg2);

    ByteBuffer getByteBuffer(long arg1, long arg2);

    void setBoxes(List arg1);

    void writeContainer(WritableByteChannel arg1);
}

