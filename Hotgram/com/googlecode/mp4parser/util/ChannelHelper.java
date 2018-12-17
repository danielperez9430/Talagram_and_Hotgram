package com.googlecode.mp4parser.util;

import java.io.EOFException;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;

public class ChannelHelper {
    private static ByteBuffer empty;

    static {
        ChannelHelper.empty = ByteBuffer.allocate(0).asReadOnlyBuffer();
    }

    public ChannelHelper() {
        super();
    }

    public static int readFully(ReadableByteChannel arg3, ByteBuffer arg4, int arg5) {
        int v0 = 0;
        do {
            int v1 = arg3.read(arg4);
            int v2 = -1;
            if(v2 != v1) {
                v0 += v1;
                if(v0 != arg5) {
                    continue;
                }
            }
            else {
            }

            break;
        }
        while(true);

        if(v1 != v2) {
            return v0;
        }

        throw new EOFException("End of file. No more boxes.");
    }

    public static void readFully(ReadableByteChannel arg1, ByteBuffer arg2) {
        ChannelHelper.readFully(arg1, arg2, arg2.remaining());
    }
}

