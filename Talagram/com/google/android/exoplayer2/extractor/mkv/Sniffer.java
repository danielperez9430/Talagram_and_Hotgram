package com.google.android.exoplayer2.extractor.mkv;

import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.util.ParsableByteArray;

final class Sniffer {
    private static final int ID_EBML = 440786851;
    private static final int SEARCH_LENGTH = 1024;
    private int peekLength;
    private final ParsableByteArray scratch;

    public Sniffer() {
        super();
        this.scratch = new ParsableByteArray(8);
    }

    private long readUint(ExtractorInput arg7) {
        int v1 = 0;
        arg7.peekFully(this.scratch.data, 0, 1);
        int v0 = this.scratch.data[0] & 255;
        if(v0 == 0) {
            return -9223372036854775808L;
        }

        int v3 = 128;
        int v4;
        for(v4 = 0; (v0 & v3) == 0; ++v4) {
            v3 >>= 1;
        }

        v0 &= v3 ^ -1;
        arg7.peekFully(this.scratch.data, 1, v4);
        while(v1 < v4) {
            ++v1;
            v0 = (this.scratch.data[v1] & 255) + (v0 << 8);
        }

        this.peekLength += v4 + 1;
        return ((long)v0);
    }

    public boolean sniff(ExtractorInput arg19) {
        // Method was not decompiled
    }
}

