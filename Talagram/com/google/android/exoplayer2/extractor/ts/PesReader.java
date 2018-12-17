package com.google.android.exoplayer2.extractor.ts;

import android.util.Log;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.util.ParsableBitArray;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.TimestampAdjuster;

public final class PesReader implements TsPayloadReader {
    private static final int HEADER_SIZE = 9;
    private static final int MAX_HEADER_EXTENSION_SIZE = 10;
    private static final int PES_SCRATCH_SIZE = 10;
    private static final int STATE_FINDING_HEADER = 0;
    private static final int STATE_READING_BODY = 3;
    private static final int STATE_READING_HEADER = 1;
    private static final int STATE_READING_HEADER_EXTENSION = 2;
    private static final String TAG = "PesReader";
    private int bytesRead;
    private boolean dataAlignmentIndicator;
    private boolean dtsFlag;
    private int extendedHeaderLength;
    private int payloadSize;
    private final ParsableBitArray pesScratch;
    private boolean ptsFlag;
    private final ElementaryStreamReader reader;
    private boolean seenFirstDts;
    private int state;
    private long timeUs;
    private TimestampAdjuster timestampAdjuster;

    public PesReader(ElementaryStreamReader arg2) {
        super();
        this.reader = arg2;
        this.pesScratch = new ParsableBitArray(new byte[10]);
        this.state = 0;
    }

    public final void consume(ParsableByteArray arg6, boolean arg7) {
        int v1 = -1;
        if(arg7) {
            switch(this.state) {
                case 2: {
                    goto label_20;
                }
                case 3: {
                    goto label_6;
                }
            }

            goto label_23;
        label_6:
            if(this.payloadSize != v1) {
                Log.w("PesReader", "Unexpected start indicator: expected " + this.payloadSize + " more bytes");
                goto label_50;
            label_20:
                Log.w("PesReader", "Unexpected start indicator reading extended header");
            label_23:
                this.setState(1);
                goto label_24;
            }
        }
        else {
            do {
            label_24:
                if(arg6.bytesLeft() > 0) {
                    int v2_1 = 0;
                    switch(this.state) {
                        case 0: {
                            goto label_82;
                        }
                        case 1: {
                            goto label_72;
                        }
                        case 2: {
                            goto label_53;
                        }
                        case 3: {
                            goto label_30;
                        }
                    }

                    continue;
                label_82:
                    arg6.skipBytes(arg6.bytesLeft());
                    continue;
                label_53:
                    if(!this.continueRead(arg6, this.pesScratch.data, Math.min(10, this.extendedHeaderLength))) {
                        continue;
                    }

                    if(!this.continueRead(arg6, null, this.extendedHeaderLength)) {
                        continue;
                    }

                    this.parseHeaderExtension();
                    this.reader.packetStarted(this.timeUs, this.dataAlignmentIndicator);
                    this.setState(3);
                    continue;
                label_72:
                    if(!this.continueRead(arg6, this.pesScratch.data, 9)) {
                        continue;
                    }

                    if(this.parseHeader()) {
                        v2_1 = 2;
                    }

                    this.setState(v2_1);
                    continue;
                label_30:
                    int v7 = arg6.bytesLeft();
                    if(this.payloadSize == v1) {
                    }
                    else {
                        v2_1 = v7 - this.payloadSize;
                    }

                    if(v2_1 > 0) {
                        v7 -= v2_1;
                        arg6.setLimit(arg6.getPosition() + v7);
                    }

                    this.reader.consume(arg6);
                    if(this.payloadSize == v1) {
                        continue;
                    }

                    this.payloadSize -= v7;
                    if(this.payloadSize != 0) {
                        continue;
                    }
                }
                else {
                    return;
                }

                goto label_50;
            }
            while(true);

            return;
        }

    label_50:
        this.reader.packetFinished();
        goto label_23;
    }

    private boolean continueRead(ParsableByteArray arg4, byte[] arg5, int arg6) {
        int v0 = Math.min(arg4.bytesLeft(), arg6 - this.bytesRead);
        boolean v1 = true;
        if(v0 <= 0) {
            return 1;
        }

        if(arg5 == null) {
            arg4.skipBytes(v0);
        }
        else {
            arg4.readBytes(arg5, this.bytesRead, v0);
        }

        this.bytesRead += v0;
        if(this.bytesRead == arg6) {
        }
        else {
            v1 = false;
        }

        return v1;
    }

    public void init(TimestampAdjuster arg1, ExtractorOutput arg2, TrackIdGenerator arg3) {
        this.timestampAdjuster = arg1;
        this.reader.createTracks(arg2, arg3);
    }

    private boolean parseHeader() {
        this.pesScratch.setPosition(0);
        int v0 = this.pesScratch.readBits(24);
        int v2 = -1;
        if(v0 != 1) {
            Log.w("PesReader", "Unexpected start code prefix: " + v0);
            this.payloadSize = v2;
            return 0;
        }

        this.pesScratch.skipBits(8);
        v0 = this.pesScratch.readBits(16);
        this.pesScratch.skipBits(5);
        this.dataAlignmentIndicator = this.pesScratch.readBit();
        this.pesScratch.skipBits(2);
        this.ptsFlag = this.pesScratch.readBit();
        this.dtsFlag = this.pesScratch.readBit();
        int v5 = 6;
        this.pesScratch.skipBits(v5);
        this.extendedHeaderLength = this.pesScratch.readBits(8);
        this.payloadSize = v0 == 0 ? v2 : v0 + v5 - 9 - this.extendedHeaderLength;
        return 1;
    }

    private void parseHeaderExtension() {
        this.pesScratch.setPosition(0);
        this.timeUs = -9223372036854775807L;
        if(this.ptsFlag) {
            int v1 = 4;
            this.pesScratch.skipBits(v1);
            int v2 = 3;
            int v0 = 30;
            long v3 = (((long)this.pesScratch.readBits(v2))) << v0;
            this.pesScratch.skipBits(1);
            int v7 = 15;
            v3 |= ((long)(this.pesScratch.readBits(v7) << v7));
            this.pesScratch.skipBits(1);
            v3 |= ((long)this.pesScratch.readBits(v7));
            this.pesScratch.skipBits(1);
            if(!this.seenFirstDts && (this.dtsFlag)) {
                this.pesScratch.skipBits(v1);
                long v0_1 = (((long)this.pesScratch.readBits(v2))) << v0;
                this.pesScratch.skipBits(1);
                v0_1 |= ((long)(this.pesScratch.readBits(v7) << v7));
                this.pesScratch.skipBits(1);
                v0_1 |= ((long)this.pesScratch.readBits(v7));
                this.pesScratch.skipBits(1);
                this.timestampAdjuster.adjustTsTimestamp(v0_1);
                this.seenFirstDts = true;
            }

            this.timeUs = this.timestampAdjuster.adjustTsTimestamp(v3);
        }
    }

    public final void seek() {
        this.state = 0;
        this.bytesRead = 0;
        this.seenFirstDts = false;
        this.reader.seek();
    }

    private void setState(int arg1) {
        this.state = arg1;
        this.bytesRead = 0;
    }
}

