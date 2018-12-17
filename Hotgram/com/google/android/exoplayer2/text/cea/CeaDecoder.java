package com.google.android.exoplayer2.text.cea;

import com.google.android.exoplayer2.text.Subtitle;
import com.google.android.exoplayer2.text.SubtitleDecoder;
import com.google.android.exoplayer2.text.SubtitleInputBuffer;
import com.google.android.exoplayer2.text.SubtitleOutputBuffer;
import com.google.android.exoplayer2.util.Assertions;
import java.util.ArrayDeque;
import java.util.PriorityQueue;

abstract class CeaDecoder implements SubtitleDecoder {
    class com.google.android.exoplayer2.text.cea.CeaDecoder$1 {
    }

    final class CeaInputBuffer extends SubtitleInputBuffer implements Comparable {
        private long queuedInputBufferCount;

        CeaInputBuffer(com.google.android.exoplayer2.text.cea.CeaDecoder$1 arg1) {
            this();
        }

        private CeaInputBuffer() {
            super();
        }

        static long access$202(CeaInputBuffer arg0, long arg1) {
            arg0.queuedInputBufferCount = arg1;
            return arg1;
        }

        public int compareTo(CeaInputBuffer arg9) {
            int v2 = -1;
            if(this.isEndOfStream() != arg9.isEndOfStream()) {
                if(this.isEndOfStream()) {
                    v2 = 1;
                }

                return v2;
            }

            long v0 = this.timeUs - arg9.timeUs;
            long v4 = 0;
            if(v0 == v4) {
                v0 = this.queuedInputBufferCount - arg9.queuedInputBufferCount;
                if(v0 == v4) {
                    return 0;
                }
            }

            if(v0 > v4) {
                v2 = 1;
            }

            return v2;
        }

        public int compareTo(Object arg1) {
            return this.compareTo(((CeaInputBuffer)arg1));
        }
    }

    final class CeaOutputBuffer extends SubtitleOutputBuffer {
        CeaOutputBuffer(CeaDecoder arg1, com.google.android.exoplayer2.text.cea.CeaDecoder$1 arg2) {
            this(arg1);
        }

        private CeaOutputBuffer(CeaDecoder arg1) {
            CeaDecoder.this = arg1;
            super();
        }

        public final void release() {
            CeaDecoder.this.releaseOutputBuffer(((SubtitleOutputBuffer)this));
        }
    }

    private static final int NUM_INPUT_BUFFERS = 10;
    private static final int NUM_OUTPUT_BUFFERS = 2;
    private final ArrayDeque availableInputBuffers;
    private final ArrayDeque availableOutputBuffers;
    private CeaInputBuffer dequeuedInputBuffer;
    private long playbackPositionUs;
    private long queuedInputBufferCount;
    private final PriorityQueue queuedInputBuffers;

    public CeaDecoder() {
        com.google.android.exoplayer2.text.cea.CeaDecoder$1 v3;
        super();
        this.availableInputBuffers = new ArrayDeque();
        int v0 = 0;
        int v1;
        for(v1 = 0; true; ++v1) {
            v3 = null;
            if(v1 >= 10) {
                break;
            }

            this.availableInputBuffers.add(new CeaInputBuffer(v3));
        }

        this.availableOutputBuffers = new ArrayDeque();
        while(v0 < 2) {
            this.availableOutputBuffers.add(new CeaOutputBuffer(this, v3));
            ++v0;
        }

        this.queuedInputBuffers = new PriorityQueue();
    }

    protected abstract Subtitle createSubtitle();

    protected abstract void decode(SubtitleInputBuffer arg1);

    public SubtitleInputBuffer dequeueInputBuffer() {
        boolean v0 = this.dequeuedInputBuffer == null ? true : false;
        Assertions.checkState(v0);
        if(this.availableInputBuffers.isEmpty()) {
            return null;
        }

        this.dequeuedInputBuffer = this.availableInputBuffers.pollFirst();
        return this.dequeuedInputBuffer;
    }

    public Object dequeueInputBuffer() {
        return this.dequeueInputBuffer();
    }

    public SubtitleOutputBuffer dequeueOutputBuffer() {
        Object v1_1;
        SubtitleOutputBuffer v1 = null;
        if(this.availableOutputBuffers.isEmpty()) {
            return v1;
        }

        while(!this.queuedInputBuffers.isEmpty()) {
            if(this.queuedInputBuffers.peek().timeUs > this.playbackPositionUs) {
                return v1;
            }

            Object v0 = this.queuedInputBuffers.poll();
            if(((CeaInputBuffer)v0).isEndOfStream()) {
                v1_1 = this.availableOutputBuffers.pollFirst();
                ((SubtitleOutputBuffer)v1_1).addFlag(4);
            }
            else {
                this.decode(((SubtitleInputBuffer)v0));
                if(this.isNewSubtitleDataAvailable()) {
                    Subtitle v6 = this.createSubtitle();
                    if(!((CeaInputBuffer)v0).isDecodeOnly()) {
                        v1_1 = this.availableOutputBuffers.pollFirst();
                        v1_1.setContent(((CeaInputBuffer)v0).timeUs, v6, 9223372036854775807L);
                        goto label_21;
                    }
                }

                goto label_36;
            }

        label_21:
            this.releaseInputBuffer(((CeaInputBuffer)v0));
            return ((SubtitleOutputBuffer)v1_1);
        label_36:
            this.releaseInputBuffer(((CeaInputBuffer)v0));
        }

        return v1;
    }

    public Object dequeueOutputBuffer() {
        return this.dequeueOutputBuffer();
    }

    public void flush() {
        this.queuedInputBufferCount = 0;
        this.playbackPositionUs = 0;
        while(!this.queuedInputBuffers.isEmpty()) {
            this.releaseInputBuffer(this.queuedInputBuffers.poll());
        }

        if(this.dequeuedInputBuffer != null) {
            this.releaseInputBuffer(this.dequeuedInputBuffer);
            this.dequeuedInputBuffer = null;
        }
    }

    public abstract String getName();

    protected abstract boolean isNewSubtitleDataAvailable();

    public void queueInputBuffer(SubtitleInputBuffer arg5) {
        boolean v0 = arg5 == this.dequeuedInputBuffer ? true : false;
        Assertions.checkArgument(v0);
        if(arg5.isDecodeOnly()) {
            this.releaseInputBuffer(this.dequeuedInputBuffer);
        }
        else {
            CeaInputBuffer v5 = this.dequeuedInputBuffer;
            long v0_1 = this.queuedInputBufferCount;
            this.queuedInputBufferCount = 1 + v0_1;
            CeaInputBuffer.access$202(v5, v0_1);
            this.queuedInputBuffers.add(this.dequeuedInputBuffer);
        }

        this.dequeuedInputBuffer = null;
    }

    public void queueInputBuffer(Object arg1) {
        this.queueInputBuffer(((SubtitleInputBuffer)arg1));
    }

    public void release() {
    }

    private void releaseInputBuffer(CeaInputBuffer arg2) {
        arg2.clear();
        this.availableInputBuffers.add(arg2);
    }

    protected void releaseOutputBuffer(SubtitleOutputBuffer arg2) {
        arg2.clear();
        this.availableOutputBuffers.add(arg2);
    }

    public void setPositionUs(long arg1) {
        this.playbackPositionUs = arg1;
    }
}

