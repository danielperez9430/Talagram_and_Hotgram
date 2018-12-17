package com.google.android.exoplayer2.extractor.mkv;

import com.google.android.exoplayer2.extractor.ExtractorInput;

final class VarintReader {
    private static final int STATE_BEGIN_READING = 0;
    private static final int STATE_READ_CONTENTS = 1;
    private static final long[] VARINT_LENGTH_MASKS;
    private int length;
    private final byte[] scratch;
    private int state;

    static {
        VarintReader.VARINT_LENGTH_MASKS = new long[]{128, 64, 32, 16, 8, 4, 2, 1};
    }

    public VarintReader() {
        super();
        this.scratch = new byte[8];
    }

    public static long assembleVarint(byte[] arg8, int arg9, boolean arg10) {
        long v2 = 255;
        long v0 = (((long)arg8[0])) & v2;
        if(arg10) {
            v0 &= VarintReader.VARINT_LENGTH_MASKS[arg9 - 1] ^ -1;
        }

        int v10;
        for(v10 = 1; v10 < arg9; ++v10) {
            v0 = v0 << 8 | (((long)arg8[v10])) & v2;
        }

        return v0;
    }

    public int getLastLength() {
        return this.length;
    }

    public static int parseUnsignedVarintLength(int arg6) {
        int v0 = 0;
        while(true) {
            if(v0 >= VarintReader.VARINT_LENGTH_MASKS.length) {
                return -1;
            }
            else if((VarintReader.VARINT_LENGTH_MASKS[v0] & (((long)arg6))) != 0) {
                ++v0;
            }
            else {
                ++v0;
                continue;
            }

            return v0;
        }

        return -1;
    }

    public long readUnsignedVarint(ExtractorInput arg4, boolean arg5, boolean arg6, int arg7) {
        if(this.state == 0) {
            if(!arg4.readFully(this.scratch, 0, 1, arg5)) {
                return -1;
            }
            else {
                this.length = VarintReader.parseUnsignedVarintLength(this.scratch[0] & 255);
                if(this.length != -1) {
                    this.state = 1;
                }
                else {
                    throw new IllegalStateException("No valid varint length mask found");
                }
            }
        }

        if(this.length > arg7) {
            this.state = 0;
            return -2;
        }

        if(this.length != 1) {
            arg4.readFully(this.scratch, 1, this.length - 1);
        }

        this.state = 0;
        return VarintReader.assembleVarint(this.scratch, this.length, arg6);
    }

    public void reset() {
        this.state = 0;
        this.length = 0;
    }
}

