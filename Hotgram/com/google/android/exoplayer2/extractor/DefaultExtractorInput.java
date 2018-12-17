package com.google.android.exoplayer2.extractor;

import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import java.io.EOFException;
import java.util.Arrays;

public final class DefaultExtractorInput implements ExtractorInput {
    private static final int PEEK_MAX_FREE_SPACE = 524288;
    private static final int PEEK_MIN_FREE_SPACE_AFTER_RESIZE = 65536;
    private static final int SCRATCH_SPACE_SIZE = 4096;
    private final DataSource dataSource;
    private byte[] peekBuffer;
    private int peekBufferLength;
    private int peekBufferPosition;
    private long position;
    private final byte[] scratchSpace;
    private final long streamLength;

    public DefaultExtractorInput(DataSource arg1, long arg2, long arg4) {
        super();
        this.dataSource = arg1;
        this.position = arg2;
        this.streamLength = arg4;
        this.peekBuffer = new byte[65536];
        this.scratchSpace = new byte[4096];
    }

    public void advancePeekPosition(int arg2) {
        this.advancePeekPosition(arg2, false);
    }

    public boolean advancePeekPosition(int arg8, boolean arg9) {
        this.ensureSpaceForPeek(arg8);
        int v5 = Math.min(this.peekBufferLength - this.peekBufferPosition, arg8);
        do {
            if(v5 >= arg8) {
                goto label_17;
            }

            v5 = this.readFromDataSource(this.peekBuffer, this.peekBufferPosition, arg8, v5, arg9);
        }
        while(v5 != -1);

        return 0;
    label_17:
        this.peekBufferPosition += arg8;
        this.peekBufferLength = Math.max(this.peekBufferLength, this.peekBufferPosition);
        return 1;
    }

    private void commitBytesRead(int arg5) {
        if(arg5 != -1) {
            this.position += ((long)arg5);
        }
    }

    private void ensureSpaceForPeek(int arg4) {
        int v0 = this.peekBufferPosition + arg4;
        if(v0 > this.peekBuffer.length) {
            this.peekBuffer = Arrays.copyOf(this.peekBuffer, Util.constrainValue(this.peekBuffer.length * 2, 65536 + v0, v0 + 524288));
        }
    }

    public long getLength() {
        return this.streamLength;
    }

    public long getPeekPosition() {
        return this.position + (((long)this.peekBufferPosition));
    }

    public long getPosition() {
        return this.position;
    }

    public void peekFully(byte[] arg2, int arg3, int arg4) {
        this.peekFully(arg2, arg3, arg4, false);
    }

    public boolean peekFully(byte[] arg2, int arg3, int arg4, boolean arg5) {
        if(!this.advancePeekPosition(arg4, arg5)) {
            return 0;
        }

        System.arraycopy(this.peekBuffer, this.peekBufferPosition - arg4, arg2, arg3, arg4);
        return 1;
    }

    public int read(byte[] arg8, int arg9, int arg10) {
        int v0 = this.readFromPeekBuffer(arg8, arg9, arg10);
        if(v0 == 0) {
            v0 = this.readFromDataSource(arg8, arg9, arg10, 0, true);
        }

        this.commitBytesRead(v0);
        return v0;
    }

    private int readFromDataSource(byte[] arg2, int arg3, int arg4, int arg5, boolean arg6) {
        if(!Thread.interrupted()) {
            int v2 = this.dataSource.read(arg2, arg3 + arg5, arg4 - arg5);
            arg3 = -1;
            if(v2 == arg3) {
                if(arg5 == 0 && (arg6)) {
                    return arg3;
                }

                throw new EOFException();
            }

            return arg5 + v2;
        }

        throw new InterruptedException();
    }

    private int readFromPeekBuffer(byte[] arg3, int arg4, int arg5) {
        if(this.peekBufferLength == 0) {
            return 0;
        }

        arg5 = Math.min(this.peekBufferLength, arg5);
        System.arraycopy(this.peekBuffer, 0, arg3, arg4, arg5);
        this.updatePeekBuffer(arg5);
        return arg5;
    }

    public void readFully(byte[] arg2, int arg3, int arg4) {
        this.readFully(arg2, arg3, arg4, false);
    }

    public boolean readFully(byte[] arg8, int arg9, int arg10, boolean arg11) {
        int v0;
        int v5 = this.readFromPeekBuffer(arg8, arg9, arg10);
        while(true) {
            v0 = -1;
            if(v5 < arg10 && v5 != v0) {
                v5 = this.readFromDataSource(arg8, arg9, arg10, v5, arg11);
                continue;
            }

            break;
        }

        this.commitBytesRead(v5);
        boolean v8 = v5 != v0 ? true : false;
        return v8;
    }

    public void resetPeekPosition() {
        this.peekBufferPosition = 0;
    }

    public void setRetryPosition(long arg4, Throwable arg6) {
        boolean v0 = arg4 >= 0 ? true : false;
        Assertions.checkArgument(v0);
        this.position = arg4;
        throw arg6;
    }

    public int skip(int arg8) {
        int v0 = this.skipFromPeekBuffer(arg8);
        if(v0 == 0) {
            v0 = this.readFromDataSource(this.scratchSpace, 0, Math.min(arg8, this.scratchSpace.length), 0, true);
        }

        this.commitBytesRead(v0);
        return v0;
    }

    private int skipFromPeekBuffer(int arg2) {
        arg2 = Math.min(this.peekBufferLength, arg2);
        this.updatePeekBuffer(arg2);
        return arg2;
    }

    public void skipFully(int arg2) {
        this.skipFully(arg2, false);
    }

    public boolean skipFully(int arg8, boolean arg9) {
        int v0;
        int v5 = this.skipFromPeekBuffer(arg8);
        while(true) {
            v0 = -1;
            if(v5 < arg8 && v5 != v0) {
                v5 = this.readFromDataSource(this.scratchSpace, -v5, Math.min(arg8, this.scratchSpace.length + v5), v5, arg9);
                continue;
            }

            break;
        }

        this.commitBytesRead(v5);
        boolean v8 = v5 != v0 ? true : false;
        return v8;
    }

    private void updatePeekBuffer(int arg6) {
        this.peekBufferLength -= arg6;
        this.peekBufferPosition = 0;
        byte[] v1 = this.peekBuffer;
        if(this.peekBufferLength < this.peekBuffer.length - 524288) {
            v1 = new byte[this.peekBufferLength + 65536];
        }

        System.arraycopy(this.peekBuffer, arg6, v1, 0, this.peekBufferLength);
        this.peekBuffer = v1;
    }
}

