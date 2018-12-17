package com.google.android.exoplayer2.decoder;

import com.google.android.exoplayer2.util.Assertions;
import java.util.ArrayDeque;

public abstract class SimpleDecoder implements Decoder {
    private int availableInputBufferCount;
    private final DecoderInputBuffer[] availableInputBuffers;
    private int availableOutputBufferCount;
    private final OutputBuffer[] availableOutputBuffers;
    private final Thread decodeThread;
    private DecoderInputBuffer dequeuedInputBuffer;
    private Exception exception;
    private boolean flushed;
    private final Object lock;
    private final ArrayDeque queuedInputBuffers;
    private final ArrayDeque queuedOutputBuffers;
    private boolean released;
    private int skippedOutputBufferCount;

    protected SimpleDecoder(DecoderInputBuffer[] arg4, OutputBuffer[] arg5) {
        super();
        this.lock = new Object();
        this.queuedInputBuffers = new ArrayDeque();
        this.queuedOutputBuffers = new ArrayDeque();
        this.availableInputBuffers = arg4;
        this.availableInputBufferCount = arg4.length;
        int v4 = 0;
        int v0;
        for(v0 = 0; v0 < this.availableInputBufferCount; ++v0) {
            this.availableInputBuffers[v0] = this.createInputBuffer();
        }

        this.availableOutputBuffers = arg5;
        this.availableOutputBufferCount = arg5.length;
        while(v4 < this.availableOutputBufferCount) {
            this.availableOutputBuffers[v4] = this.createOutputBuffer();
            ++v4;
        }

        this.decodeThread = new Thread() {
            public void run() {
                SimpleDecoder.access$000(SimpleDecoder.this);
            }
        };
        this.decodeThread.start();
    }

    static void access$000(SimpleDecoder arg0) {
        arg0.run();
    }

    private boolean canDecodeBuffer() {
        boolean v0 = (this.queuedInputBuffers.isEmpty()) || this.availableOutputBufferCount <= 0 ? false : true;
        return v0;
    }

    protected abstract DecoderInputBuffer createInputBuffer();

    protected abstract OutputBuffer createOutputBuffer();

    protected abstract Exception createUnexpectedDecodeException(Throwable arg1);

    private boolean decode() {
        boolean v4_1;
        OutputBuffer v3_1;
        Object v1_1;
        Object v0 = this.lock;
        __monitor_enter(v0);
        try {
            while(!this.released) {
                if(this.canDecodeBuffer()) {
                    break;
                }

                this.lock.wait();
            }

            if(this.released) {
                __monitor_exit(v0);
                return 0;
            }

            v1_1 = this.queuedInputBuffers.removeFirst();
            OutputBuffer[] v3 = this.availableOutputBuffers;
            int v4 = this.availableOutputBufferCount - 1;
            this.availableOutputBufferCount = v4;
            v3_1 = v3[v4];
            v4_1 = this.flushed;
            this.flushed = false;
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_73;
        }

        if(((DecoderInputBuffer)v1_1).isEndOfStream()) {
            v3_1.addFlag(4);
            goto label_49;
        }

        if(((DecoderInputBuffer)v1_1).isDecodeOnly()) {
            v3_1.addFlag(-2147483648);
        }

        try {
            this.exception = this.decode(((DecoderInputBuffer)v1_1), v3_1, v4_1);
        }
        catch(OutOfMemoryError v0_1) {
            this.exception = this.createUnexpectedDecodeException(((Throwable)v0_1));
        }

        if(this.exception != null) {
            v0 = this.lock;
            __monitor_enter(v0);
            try {
                __monitor_exit(v0);
                return 0;
            }
            catch(Throwable v1) {
                goto label_47;
            }
        }

    label_49:
        Object v4_2 = this.lock;
        __monitor_enter(v4_2);
        try {
            if(this.flushed) {
            label_53:
                v3_1.release();
                goto label_66;
            label_61:
                v3_1.skippedOutputBufferCount = this.skippedOutputBufferCount;
                this.skippedOutputBufferCount = 0;
                this.queuedOutputBuffers.addLast(v3_1);
            }
            else if(v3_1.isDecodeOnly()) {
                ++this.skippedOutputBufferCount;
                goto label_53;
            }
            else {
                goto label_61;
            }

        label_66:
            this.releaseInputBufferInternal(((DecoderInputBuffer)v1_1));
            __monitor_exit(v4_2);
            return 1;
        label_70:
            __monitor_exit(v4_2);
        }
        catch(Throwable v0_2) {
            goto label_70;
        }

        throw v0_2;
        try {
        label_47:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_47;
        }

        throw v1;
        try {
        label_73:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_73;
        }

        throw v1;
    }

    protected abstract Exception decode(DecoderInputBuffer arg1, OutputBuffer arg2, boolean arg3);

    public final DecoderInputBuffer dequeueInputBuffer() {
        DecoderInputBuffer v1_2;
        Object v0 = this.lock;
        __monitor_enter(v0);
        try {
            this.maybeThrowException();
            boolean v1_1 = this.dequeuedInputBuffer == null ? true : false;
            Assertions.checkState(v1_1);
            if(this.availableInputBufferCount == 0) {
                v1_2 = null;
            }
            else {
                DecoderInputBuffer[] v1_3 = this.availableInputBuffers;
                int v3 = this.availableInputBufferCount - 1;
                this.availableInputBufferCount = v3;
                v1_2 = v1_3[v3];
            }

            this.dequeuedInputBuffer = v1_2;
            __monitor_exit(v0);
            return this.dequeuedInputBuffer;
        label_24:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_24;
        }

        throw v1;
    }

    public Object dequeueInputBuffer() {
        return this.dequeueInputBuffer();
    }

    public final OutputBuffer dequeueOutputBuffer() {
        Object v0 = this.lock;
        __monitor_enter(v0);
        try {
            this.maybeThrowException();
            if(this.queuedOutputBuffers.isEmpty()) {
                __monitor_exit(v0);
                return null;
            }

            __monitor_exit(v0);
            return this.queuedOutputBuffers.removeFirst();
        label_14:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_14;
        }

        throw v1;
    }

    public Object dequeueOutputBuffer() {
        return this.dequeueOutputBuffer();
    }

    public final void flush() {
        Object v0 = this.lock;
        __monitor_enter(v0);
        try {
            this.flushed = true;
            this.skippedOutputBufferCount = 0;
            if(this.dequeuedInputBuffer != null) {
                this.releaseInputBufferInternal(this.dequeuedInputBuffer);
                this.dequeuedInputBuffer = null;
            }

            while(!this.queuedInputBuffers.isEmpty()) {
                this.releaseInputBufferInternal(this.queuedInputBuffers.removeFirst());
            }

            while(!this.queuedOutputBuffers.isEmpty()) {
                this.queuedOutputBuffers.removeFirst().release();
            }

            __monitor_exit(v0);
            return;
        label_29:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_29;
        }

        throw v1;
    }

    private void maybeNotifyDecodeLoop() {
        if(this.canDecodeBuffer()) {
            this.lock.notify();
        }
    }

    private void maybeThrowException() {
        if(this.exception == null) {
            return;
        }

        throw this.exception;
    }

    public final void queueInputBuffer(DecoderInputBuffer arg3) {
        Object v0 = this.lock;
        __monitor_enter(v0);
        try {
            this.maybeThrowException();
            boolean v1 = arg3 == this.dequeuedInputBuffer ? true : false;
            Assertions.checkArgument(v1);
            this.queuedInputBuffers.addLast(arg3);
            this.maybeNotifyDecodeLoop();
            this.dequeuedInputBuffer = null;
            __monitor_exit(v0);
            return;
        label_17:
            __monitor_exit(v0);
        }
        catch(Throwable v3) {
            goto label_17;
        }

        throw v3;
    }

    public void queueInputBuffer(Object arg1) {
        this.queueInputBuffer(((DecoderInputBuffer)arg1));
    }

    public void release() {
        Object v0 = this.lock;
        __monitor_enter(v0);
        try {
            this.released = true;
            this.lock.notify();
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            try {
            label_14:
                __monitor_exit(v0);
            }
            catch(Throwable v1) {
                goto label_14;
            }

            throw v1;
        }

        try {
            this.decodeThread.join();
        }
        catch(InterruptedException ) {
            Thread.currentThread().interrupt();
        }
    }

    private void releaseInputBufferInternal(DecoderInputBuffer arg4) {
        arg4.clear();
        DecoderInputBuffer[] v0 = this.availableInputBuffers;
        int v1 = this.availableInputBufferCount;
        this.availableInputBufferCount = v1 + 1;
        v0[v1] = arg4;
    }

    protected void releaseOutputBuffer(OutputBuffer arg2) {
        Object v0 = this.lock;
        __monitor_enter(v0);
        try {
            this.releaseOutputBufferInternal(arg2);
            this.maybeNotifyDecodeLoop();
            __monitor_exit(v0);
            return;
        label_7:
            __monitor_exit(v0);
        }
        catch(Throwable v2) {
            goto label_7;
        }

        throw v2;
    }

    private void releaseOutputBufferInternal(OutputBuffer arg4) {
        arg4.clear();
        OutputBuffer[] v0 = this.availableOutputBuffers;
        int v1 = this.availableOutputBufferCount;
        this.availableOutputBufferCount = v1 + 1;
        v0[v1] = arg4;
    }

    private void run() {
        try {
        label_0:
            while(!this.decode()) {
                return;
            }
        }
        catch(InterruptedException v0) {
            throw new IllegalStateException(((Throwable)v0));
        }

        goto label_0;
    }

    protected final void setInitialInputBufferSize(int arg5) {
        int v2 = 0;
        boolean v0 = this.availableInputBufferCount == this.availableInputBuffers.length ? true : false;
        Assertions.checkState(v0);
        DecoderInputBuffer[] v0_1 = this.availableInputBuffers;
        int v1 = v0_1.length;
        while(v2 < v1) {
            v0_1[v2].ensureSpaceForWrite(arg5);
            ++v2;
        }
    }
}

