package com.google.android.exoplayer2.extractor.ts;

import android.util.Pair;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.util.CodecSpecificDataUtil;
import com.google.android.exoplayer2.util.ParsableBitArray;
import com.google.android.exoplayer2.util.ParsableByteArray;
import java.util.Collections;

public final class LatmReader implements ElementaryStreamReader {
    private static final int INITIAL_BUFFER_SIZE = 1024;
    private static final int STATE_FINDING_SYNC_1 = 0;
    private static final int STATE_FINDING_SYNC_2 = 1;
    private static final int STATE_READING_HEADER = 2;
    private static final int STATE_READING_SAMPLE = 3;
    private static final int SYNC_BYTE_FIRST = 86;
    private static final int SYNC_BYTE_SECOND = 224;
    private int audioMuxVersionA;
    private int bytesRead;
    private int channelCount;
    private Format format;
    private String formatId;
    private int frameLengthType;
    private final String language;
    private int numSubframes;
    private long otherDataLenBits;
    private boolean otherDataPresent;
    private TrackOutput output;
    private final ParsableBitArray sampleBitArray;
    private final ParsableByteArray sampleDataBuffer;
    private long sampleDurationUs;
    private int sampleRateHz;
    private int sampleSize;
    private int secondHeaderByte;
    private int state;
    private boolean streamMuxRead;
    private long timeUs;

    public LatmReader(String arg2) {
        super();
        this.language = arg2;
        this.sampleDataBuffer = new ParsableByteArray(1024);
        this.sampleBitArray = new ParsableBitArray(this.sampleDataBuffer.data);
    }

    public void consume(ParsableByteArray arg6) {
        while(arg6.bytesLeft() > 0) {
            int v1 = 86;
            switch(this.state) {
                case 0: {
                    goto label_53;
                }
                case 1: {
                    goto label_44;
                }
                case 2: {
                    goto label_28;
                }
                case 3: {
                    goto label_7;
                }
            }

            continue;
        label_53:
            if(arg6.readUnsignedByte() != v1) {
                continue;
            }

            int v0 = 1;
            goto label_56;
        label_7:
            v0 = Math.min(arg6.bytesLeft(), this.sampleSize - this.bytesRead);
            arg6.readBytes(this.sampleBitArray.data, this.bytesRead, v0);
            this.bytesRead += v0;
            if(this.bytesRead != this.sampleSize) {
                continue;
            }

            this.sampleBitArray.setPosition(0);
            this.parseAudioMuxElement(this.sampleBitArray);
            goto label_26;
        label_44:
            v0 = arg6.readUnsignedByte();
            if((v0 & 224) == 224) {
                this.secondHeaderByte = v0;
                v0 = 2;
                goto label_56;
            }

            if(v0 == v1) {
                continue;
            }

        label_26:
            this.state = 0;
            continue;
        label_28:
            this.sampleSize = (this.secondHeaderByte & -225) << 8 | arg6.readUnsignedByte();
            if(this.sampleSize > this.sampleDataBuffer.data.length) {
                this.resetBufferForSize(this.sampleSize);
            }

            this.bytesRead = 0;
            v0 = 3;
        label_56:
            this.state = v0;
        }
    }

    public void createTracks(ExtractorOutput arg3, TrackIdGenerator arg4) {
        arg4.generateNewId();
        this.output = arg3.track(arg4.getTrackId(), 1);
        this.formatId = arg4.getFormatId();
    }

    private static long latmGetValue(ParsableBitArray arg2) {
        return ((long)arg2.readBits((arg2.readBits(2) + 1) * 8));
    }

    public void packetFinished() {
    }

    public void packetStarted(long arg1, boolean arg3) {
        this.timeUs = arg1;
    }

    private void parseAudioMuxElement(ParsableBitArray arg3) {
        if(!arg3.readBit()) {
            this.streamMuxRead = true;
            this.parseStreamMuxConfig(arg3);
        }
        else if(!this.streamMuxRead) {
            return;
        }

        if(this.audioMuxVersionA == 0) {
            if(this.numSubframes == 0) {
                this.parsePayloadMux(arg3, this.parsePayloadLengthInfo(arg3));
                if(this.otherDataPresent) {
                    arg3.skipBits(((int)this.otherDataLenBits));
                }

                return;
            }

            throw new ParserException();
        }

        throw new ParserException();
    }

    private int parseAudioSpecificConfig(ParsableBitArray arg4) {
        int v0 = arg4.bitsLeft();
        Pair v1 = CodecSpecificDataUtil.parseAacAudioSpecificConfig(arg4, true);
        this.sampleRateHz = v1.first.intValue();
        this.channelCount = v1.second.intValue();
        return v0 - arg4.bitsLeft();
    }

    private void parseFrameLength(ParsableBitArray arg2) {
        this.frameLengthType = arg2.readBits(3);
        switch(this.frameLengthType) {
            case 0: {
                goto label_12;
            }
            case 1: {
                goto label_10;
            }
            case 3: 
            case 4: 
            case 5: {
                goto label_8;
            }
            case 6: 
            case 7: {
                goto label_6;
            }
        }

        return;
    label_6:
        int v0 = 1;
        goto label_13;
    label_8:
        v0 = 6;
        goto label_13;
    label_10:
        v0 = 9;
        goto label_13;
    label_12:
        v0 = 8;
    label_13:
        arg2.skipBits(v0);
    }

    private int parsePayloadLengthInfo(ParsableBitArray arg4) {
        if(this.frameLengthType == 0) {
            int v0 = 0;
            do {
                int v1 = arg4.readBits(8);
                v0 += v1;
            }
            while(v1 == 255);

            return v0;
        }

        throw new ParserException();
    }

    private void parsePayloadMux(ParsableBitArray arg9, int arg10) {
        int v0 = arg9.getPosition();
        if((v0 & 7) == 0) {
            this.sampleDataBuffer.setPosition(v0 >> 3);
        }
        else {
            arg9.readBits(this.sampleDataBuffer.data, 0, arg10 * 8);
            this.sampleDataBuffer.setPosition(0);
        }

        this.output.sampleData(this.sampleDataBuffer, arg10);
        this.output.sampleMetadata(this.timeUs, 1, arg10, 0, null);
        this.timeUs += this.sampleDurationUs;
    }

    private void parseStreamMuxConfig(ParsableBitArray arg20) {
        LatmReader v0 = this;
        ParsableBitArray v1 = arg20;
        int v3 = v1.readBits(1);
        int v5 = v3 == 1 ? v1.readBits(1) : 0;
        v0.audioMuxVersionA = v5;
        if(v0.audioMuxVersionA == 0) {
            if(v3 == 1) {
                LatmReader.latmGetValue(arg20);
            }

            if(arg20.readBit()) {
                v0.numSubframes = v1.readBits(6);
                v5 = v1.readBits(4);
                int v6 = v1.readBits(3);
                if(v5 == 0 && v6 == 0) {
                    v5 = 8;
                    if(v3 == 0) {
                        v6 = arg20.getPosition();
                        int v7 = this.parseAudioSpecificConfig(arg20);
                        v1.setPosition(v6);
                        byte[] v6_1 = new byte[(v7 + 7) / v5];
                        v1.readBits(v6_1, 0, v7);
                        Format v4 = Format.createAudioSampleFormat(v0.formatId, "audio/mp4a-latm", null, -1, -1, v0.channelCount, v0.sampleRateHz, Collections.singletonList(v6_1), null, 0, v0.language);
                        if(!v4.equals(v0.format)) {
                            v0.format = v4;
                            v0.sampleDurationUs = 1024000000 / (((long)v4.sampleRate));
                            v0.output.format(v4);
                        }
                    }
                    else {
                        v1.skipBits((((int)LatmReader.latmGetValue(arg20))) - this.parseAudioSpecificConfig(arg20));
                    }

                    this.parseFrameLength(arg20);
                    v0.otherDataPresent = arg20.readBit();
                    v0.otherDataLenBits = 0;
                    if(v0.otherDataPresent) {
                        if(v3 == 1) {
                            v0.otherDataLenBits = LatmReader.latmGetValue(arg20);
                            goto label_83;
                        }

                        do {
                            boolean v2 = arg20.readBit();
                            v0.otherDataLenBits = (v0.otherDataLenBits << v5) + (((long)v1.readBits(v5)));
                            if(v2) {
                                continue;
                            }

                            break;
                        }
                        while(true);
                    }

                label_83:
                    if(arg20.readBit()) {
                        v1.skipBits(v5);
                    }

                    return;
                }

                throw new ParserException();
            }

            throw new ParserException();
        }

        throw new ParserException();
    }

    private void resetBufferForSize(int arg2) {
        this.sampleDataBuffer.reset(arg2);
        this.sampleBitArray.reset(this.sampleDataBuffer.data);
    }

    public void seek() {
        this.state = 0;
        this.streamMuxRead = false;
    }
}

