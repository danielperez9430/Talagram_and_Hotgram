package com.google.android.exoplayer2.audio;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public interface AudioProcessor {
    public final class UnhandledFormatException extends Exception {
        public UnhandledFormatException(int arg3, int arg4, int arg5) {
            super("Unhandled format: " + arg3 + " Hz, " + arg4 + " channels in encoding " + arg5);
        }
    }

    public static final ByteBuffer EMPTY_BUFFER;

    static {
        AudioProcessor.EMPTY_BUFFER = ByteBuffer.allocateDirect(0).order(ByteOrder.nativeOrder());
    }

    boolean configure(int arg1, int arg2, int arg3);

    void flush();

    ByteBuffer getOutput();

    int getOutputChannelCount();

    int getOutputEncoding();

    int getOutputSampleRateHz();

    boolean isActive();

    boolean isEnded();

    void queueEndOfStream();

    void queueInput(ByteBuffer arg1);

    void reset();
}

