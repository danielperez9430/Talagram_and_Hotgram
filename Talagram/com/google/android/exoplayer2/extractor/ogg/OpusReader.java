package com.google.android.exoplayer2.extractor.ogg;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

final class OpusReader extends StreamReader {
    private static final int DEFAULT_SEEK_PRE_ROLL_SAMPLES = 3840;
    private static final int OPUS_CODE = 0;
    private static final byte[] OPUS_SIGNATURE = null;
    private static final int SAMPLE_RATE = 48000;
    private boolean headerRead;

    static {
        OpusReader.OPUS_CODE = Util.getIntegerCodeForString("Opus");
        OpusReader.OPUS_SIGNATURE = new byte[]{79, 112, 117, 115, 72, 101, 97, 100};
    }

    OpusReader() {
        super();
    }

    private long getPacketDurationUs(byte[] arg7) {
        int v7;
        int v0 = arg7[0] & 255;
        switch(v0 & 3) {
            case 0: {
                v7 = 1;
                break;
            }
            case 1: 
            case 2: {
                v7 = 2;
                break;
            }
            default: {
                v7 = arg7[1] & 63;
                break;
            }
        }

        int v1 = 3;
        v0 >>= v1;
        int v3 = v0 & 3;
        if(v0 >= 16) {
            v0 = 2500 << v3;
        }
        else {
            int v5 = 10000;
            if(v0 >= 12) {
                v0 = v5 << (v3 & 1);
            }
            else if(v3 == v1) {
                v0 = 60000;
            }
            else {
                v0 = v5 << v3;
            }
        }

        return (((long)v7)) * (((long)v0));
    }

    protected long preparePayload(ParsableByteArray arg3) {
        return this.convertTimeToGranule(this.getPacketDurationUs(arg3.data));
    }

    private void putNativeOrderLong(List arg5, int arg6) {
        arg5.add(ByteBuffer.allocate(8).order(ByteOrder.nativeOrder()).putLong((((long)arg6)) * 1000000000 / 48000).array());
    }

    protected boolean readHeaders(ParsableByteArray arg12, long arg13, SetupData arg15) {
        boolean v14 = true;
        if(!this.headerRead) {
            byte[] v12 = Arrays.copyOf(arg12.data, arg12.limit());
            int v5 = v12[9] & 255;
            int v13 = (v12[11] & 255) << 8 | v12[10] & 255;
            ArrayList v7 = new ArrayList(3);
            ((List)v7).add(v12);
            this.putNativeOrderLong(((List)v7), v13);
            this.putNativeOrderLong(((List)v7), 3840);
            arg15.format = Format.createAudioSampleFormat(null, "audio/opus", null, -1, -1, v5, 48000, ((List)v7), null, 0, null);
            this.headerRead = true;
            return 1;
        }

        if(arg12.readInt() == OpusReader.OPUS_CODE) {
        }
        else {
            v14 = false;
        }

        arg12.setPosition(0);
        return v14;
    }

    protected void reset(boolean arg1) {
        super.reset(arg1);
        if(arg1) {
            this.headerRead = false;
        }
    }

    public static boolean verifyBitstreamType(ParsableByteArray arg3) {
        if(arg3.bytesLeft() < OpusReader.OPUS_SIGNATURE.length) {
            return 0;
        }

        byte[] v0 = new byte[OpusReader.OPUS_SIGNATURE.length];
        arg3.readBytes(v0, 0, OpusReader.OPUS_SIGNATURE.length);
        return Arrays.equals(v0, OpusReader.OPUS_SIGNATURE);
    }
}

