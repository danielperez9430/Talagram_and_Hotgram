package org.telegram.messenger.audioinfo.util;

import java.io.FilterInputStream;
import java.io.InputStream;

public class PositionInputStream extends FilterInputStream {
    private long position;
    private long positionMark;

    public PositionInputStream(InputStream arg3) {
        this(arg3, 0);
    }

    public PositionInputStream(InputStream arg1, long arg2) {
        super(arg1);
        this.position = arg2;
    }

    public long getPosition() {
        return this.position;
    }

    public void mark(int arg3) {
        __monitor_enter(this);
        try {
            this.positionMark = this.position;
            super.mark(arg3);
        }
        catch(Throwable v3) {
            __monitor_exit(this);
            throw v3;
        }

        __monitor_exit(this);
    }

    public int read() {
        int v0 = super.read();
        if(v0 >= 0) {
            ++this.position;
        }

        return v0;
    }

    public final int read(byte[] arg3) {
        return this.read(arg3, 0, arg3.length);
    }

    public int read(byte[] arg3, int arg4, int arg5) {
        long v0 = this.position;
        int v3 = super.read(arg3, arg4, arg5);
        if(v3 > 0) {
            this.position = v0 + (((long)v3));
        }

        return v3;
    }

    public void reset() {
        __monitor_enter(this);
        try {
            super.reset();
            this.position = this.positionMark;
        }
        catch(Throwable v0) {
            __monitor_exit(this);
            throw v0;
        }

        __monitor_exit(this);
    }

    public long skip(long arg3) {
        long v0 = this.position;
        arg3 = super.skip(arg3);
        this.position = v0 + arg3;
        return arg3;
    }
}

