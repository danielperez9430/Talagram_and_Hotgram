package com.google.android.exoplayer2.upstream;

import com.google.android.exoplayer2.util.Assertions;
import java.io.ByteArrayOutputStream;

public final class ByteArrayDataSink implements DataSink {
    private ByteArrayOutputStream stream;

    public ByteArrayDataSink() {
        super();
    }

    public void close() {
        this.stream.close();
    }

    public byte[] getData() {
        byte[] v0 = this.stream == null ? null : this.stream.toByteArray();
        return v0;
    }

    public void open(DataSpec arg6) {
        if(arg6.length == -1) {
            this.stream = new ByteArrayOutputStream();
        }
        else {
            boolean v0 = arg6.length <= 2147483647 ? true : false;
            Assertions.checkArgument(v0);
            this.stream = new ByteArrayOutputStream(((int)arg6.length));
        }
    }

    public void write(byte[] arg2, int arg3, int arg4) {
        this.stream.write(arg2, arg3, arg4);
    }
}

