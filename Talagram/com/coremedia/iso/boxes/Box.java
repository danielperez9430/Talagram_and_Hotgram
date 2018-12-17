package com.coremedia.iso.boxes;

import com.coremedia.iso.BoxParser;
import com.googlecode.mp4parser.DataSource;
import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;

public interface Box {
    void getBox(WritableByteChannel arg1);

    long getOffset();

    Container getParent();

    long getSize();

    String getType();

    void parse(DataSource arg1, ByteBuffer arg2, long arg3, BoxParser arg4);

    void setParent(Container arg1);
}

