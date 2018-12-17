package org.telegram.messenger.voip;

import java.nio.ByteBuffer;

public class Resampler {
    public Resampler() {
        super();
    }

    public static native int convert44to48(ByteBuffer arg0, ByteBuffer arg1) {
    }

    public static native int convert48to44(ByteBuffer arg0, ByteBuffer arg1) {
    }
}

