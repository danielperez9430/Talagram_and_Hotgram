package com.google.android.exoplayer2.extractor.ts;

import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.TimestampAdjuster;
import com.google.android.exoplayer2.util.Util;

public final class SectionReader implements TsPayloadReader {
    private static final int DEFAULT_SECTION_BUFFER_LENGTH = 32;
    private static final int MAX_SECTION_LENGTH = 4098;
    private static final int SECTION_HEADER_LENGTH = 3;
    private int bytesRead;
    private final SectionPayloadReader reader;
    private final ParsableByteArray sectionData;
    private boolean sectionSyntaxIndicator;
    private int totalSectionLength;
    private boolean waitingForPayloadStart;

    public SectionReader(SectionPayloadReader arg2) {
        super();
        this.reader = arg2;
        this.sectionData = new ParsableByteArray(32);
    }

    public void consume(ParsableByteArray arg8, boolean arg9) {
        ParsableByteArray v9_2;
        int v9;
        int v0 = -1;
        int v1 = arg9 ? arg8.readUnsignedByte() + arg8.getPosition() : -1;
        if(this.waitingForPayloadStart) {
            if(!arg9) {
                return;
            }
            else {
                this.waitingForPayloadStart = false;
                arg8.setPosition(v1);
            label_14:
                this.bytesRead = 0;
            }
        }

        do {
        label_15:
            if(arg8.bytesLeft() <= 0) {
                return;
            }

            boolean v1_1 = true;
            int v2 = 3;
            if(this.bytesRead < v2) {
                if(this.bytesRead == 0) {
                    v9 = arg8.readUnsignedByte();
                    arg8.setPosition(arg8.getPosition() - 1);
                    if(v9 == 255) {
                        this.waitingForPayloadStart = true;
                        return;
                    }
                }

                v9 = Math.min(arg8.bytesLeft(), 3 - this.bytesRead);
                arg8.readBytes(this.sectionData.data, this.bytesRead, v9);
                this.bytesRead += v9;
                if(this.bytesRead != v2) {
                    goto label_15;
                }

                this.sectionData.reset(v2);
                this.sectionData.skipBytes(1);
                v9 = this.sectionData.readUnsignedByte();
                int v4 = this.sectionData.readUnsignedByte();
                if((v9 & 128) != 0) {
                }
                else {
                    v1_1 = false;
                }

                this.sectionSyntaxIndicator = v1_1;
                this.totalSectionLength = ((v9 & 15) << 8 | v4) + v2;
                if(this.sectionData.capacity() >= this.totalSectionLength) {
                    goto label_15;
                }

                byte[] v9_1 = this.sectionData.data;
                this.sectionData.reset(Math.min(4098, Math.max(this.totalSectionLength, v9_1.length * 2)));
                System.arraycopy(v9_1, 0, this.sectionData.data, 0, v2);
                goto label_15;
            }

            v9 = Math.min(arg8.bytesLeft(), this.totalSectionLength - this.bytesRead);
            arg8.readBytes(this.sectionData.data, this.bytesRead, v9);
            this.bytesRead += v9;
        }
        while(this.bytesRead != this.totalSectionLength);

        if(!this.sectionSyntaxIndicator) {
            v9_2 = this.sectionData;
            v1 = this.totalSectionLength;
        }
        else if(Util.crc(this.sectionData.data, 0, this.totalSectionLength, v0) != 0) {
            this.waitingForPayloadStart = true;
            return;
        }
        else {
            v9_2 = this.sectionData;
            v1 = this.totalSectionLength - 4;
        }

        v9_2.reset(v1);
        this.reader.consume(this.sectionData);
        goto label_14;
    }

    public void init(TimestampAdjuster arg2, ExtractorOutput arg3, TrackIdGenerator arg4) {
        this.reader.init(arg2, arg3, arg4);
        this.waitingForPayloadStart = true;
    }

    public void seek() {
        this.waitingForPayloadStart = true;
    }
}

