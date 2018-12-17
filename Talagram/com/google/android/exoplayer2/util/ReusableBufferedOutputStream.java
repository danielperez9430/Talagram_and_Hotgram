package com.google.android.exoplayer2.util;

import java.io.BufferedOutputStream;
import java.io.OutputStream;

public final class ReusableBufferedOutputStream extends BufferedOutputStream {
    private boolean closed;

    public ReusableBufferedOutputStream(OutputStream arg1) {
        super(arg1);
    }

    public ReusableBufferedOutputStream(OutputStream arg1, int arg2) {
        super(arg1, arg2);
    }

    public void close() {
        this.closed = true;
        try {
            this.flush();
            v0 = null;
        }
        catch(Throwable v0) {
        }

        try {
            this.out.close();
        }
        catch(Throwable v1) {
            if(v0 != null) {
                goto label_12;
            }

            v0 = v1;
        }

    label_12:
        if(v0 != null) {
            Util.sneakyThrow(v0);
        }
    }

    public void reset(OutputStream arg2) {
        Assertions.checkState(this.closed);
        this.out = arg2;
        this.count = 0;
        this.closed = false;
    }
}

