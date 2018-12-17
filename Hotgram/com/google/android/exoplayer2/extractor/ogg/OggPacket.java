package com.google.android.exoplayer2.extractor.ogg;

import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.ParsableByteArray;
import java.util.Arrays;

final class OggPacket {
    private int currentSegmentIndex;
    private final ParsableByteArray packetArray;
    private final OggPageHeader pageHeader;
    private boolean populated;
    private int segmentCount;

    OggPacket() {
        super();
        this.pageHeader = new OggPageHeader();
        this.packetArray = new ParsableByteArray(new byte[65025], 0);
        this.currentSegmentIndex = -1;
    }

    private int calculatePacketSize(int arg5) {
        int v0 = 0;
        this.segmentCount = 0;
        do {
            if(this.segmentCount + arg5 >= this.pageHeader.pageSegmentCount) {
                return v0;
            }

            int[] v1 = this.pageHeader.laces;
            int v2 = this.segmentCount;
            this.segmentCount = v2 + 1;
            v0 += v1[v2 + arg5];
        }
        while(v1[v2 + arg5] == 255);

        return v0;
    }

    public OggPageHeader getPageHeader() {
        return this.pageHeader;
    }

    public ParsableByteArray getPayload() {
        return this.packetArray;
    }

    public boolean populate(ExtractorInput arg8) {
        int v3;
        int v2_1;
        boolean v2 = arg8 != null ? true : false;
        Assertions.checkState(v2);
        if(this.populated) {
            this.populated = false;
            this.packetArray.reset();
        }

        while(!this.populated) {
            if(this.currentSegmentIndex < 0) {
                if(!this.pageHeader.populate(arg8, true)) {
                    return 0;
                }
                else {
                    v2_1 = this.pageHeader.headerSize;
                    if((this.pageHeader.type & 1) != 1 || this.packetArray.limit() != 0) {
                        v3 = 0;
                    }
                    else {
                        v2_1 += this.calculatePacketSize(0);
                        v3 = this.segmentCount;
                    }

                    arg8.skipFully(v2_1);
                    this.currentSegmentIndex = v3;
                }
            }

            v2_1 = this.calculatePacketSize(this.currentSegmentIndex);
            v3 = this.currentSegmentIndex + this.segmentCount;
            if(v2_1 > 0) {
                if(this.packetArray.capacity() < this.packetArray.limit() + v2_1) {
                    this.packetArray.data = Arrays.copyOf(this.packetArray.data, this.packetArray.limit() + v2_1);
                }

                arg8.readFully(this.packetArray.data, this.packetArray.limit(), v2_1);
                this.packetArray.setLimit(this.packetArray.limit() + v2_1);
                v2 = this.pageHeader.laces[v3 - 1] != 255 ? true : false;
                this.populated = v2;
            }

            if(v3 == this.pageHeader.pageSegmentCount) {
                v3 = -1;
            }

            this.currentSegmentIndex = v3;
        }

        return 1;
    }

    public void reset() {
        this.pageHeader.reset();
        this.packetArray.reset();
        this.currentSegmentIndex = -1;
        this.populated = false;
    }

    public void trimPayload() {
        int v1 = 65025;
        if(this.packetArray.data.length == v1) {
            return;
        }

        this.packetArray.data = Arrays.copyOf(this.packetArray.data, Math.max(v1, this.packetArray.limit()));
    }
}

