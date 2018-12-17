package com.google.android.exoplayer2.extractor.ts;

import com.google.android.exoplayer2.extractor.Extractor;
import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.extractor.PositionHolder;
import com.google.android.exoplayer2.extractor.SeekMap$Unseekable;
import com.google.android.exoplayer2.util.ParsableBitArray;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;

public final class AdtsExtractor implements Extractor {
    public static final ExtractorsFactory FACTORY = null;
    private static final int ID3_TAG = 0;
    private static final int MAX_PACKET_SIZE = 200;
    private static final int MAX_SNIFF_BYTES = 8192;
    private final long firstSampleTimestampUs;
    private final ParsableByteArray packetBuffer;
    private final AdtsReader reader;
    private boolean startedPacket;

    static {
        AdtsExtractor.FACTORY = -$$Lambda$AdtsExtractor$cqGYwjddB4W6E3ogPGiWfjTa23c.INSTANCE;
        AdtsExtractor.ID3_TAG = Util.getIntegerCodeForString("ID3");
    }

    public AdtsExtractor() {
        this(0);
    }

    public AdtsExtractor(long arg1) {
        super();
        this.firstSampleTimestampUs = arg1;
        this.reader = new AdtsReader(true);
        this.packetBuffer = new ParsableByteArray(200);
    }

    public void init(ExtractorOutput arg5) {
        this.reader.createTracks(arg5, new TrackIdGenerator(0, 1));
        arg5.endTracks();
        arg5.seekMap(new Unseekable(-9223372036854775807L));
    }

    static Extractor[] lambda$static$0() {
        return new Extractor[]{new AdtsExtractor()};
    }

    public int read(ExtractorInput arg4, PositionHolder arg5) {
        int v4 = arg4.read(this.packetBuffer.data, 0, 200);
        int v5 = -1;
        if(v4 == v5) {
            return v5;
        }

        this.packetBuffer.setPosition(0);
        this.packetBuffer.setLimit(v4);
        if(!this.startedPacket) {
            this.reader.packetStarted(this.firstSampleTimestampUs, true);
            this.startedPacket = true;
        }

        this.reader.consume(this.packetBuffer);
        return 0;
    }

    public void release() {
    }

    public void seek(long arg1, long arg3) {
        this.startedPacket = false;
        this.reader.seek();
    }

    public boolean sniff(ExtractorInput arg11) {
        int v5;
        int v1 = 10;
        ParsableByteArray v0 = new ParsableByteArray(v1);
        ParsableBitArray v2 = new ParsableBitArray(v0.data);
        int v4 = 0;
        while(true) {
            arg11.peekFully(v0.data, 0, v1);
            v0.setPosition(0);
            if(v0.readUnsignedInt24() != AdtsExtractor.ID3_TAG) {
                break;
            }

            v0.skipBytes(3);
            v5 = v0.readSynchSafeInt();
            v4 += v5 + 10;
            arg11.advancePeekPosition(v5);
        }

        arg11.resetPeekPosition();
        arg11.advancePeekPosition(v4);
        v1 = v4;
        while(true) {
            v5 = 0;
            int v6;
            for(v6 = 0; true; v6 += v7) {
                arg11.peekFully(v0.data, 0, 2);
                v0.setPosition(0);
                if((v0.readUnsignedShort() & 65526) != 65520) {
                    break;
                }

                ++v5;
                int v8 = 4;
                if(v5 >= v8 && v6 > 188) {
                    return 1;
                }

                arg11.peekFully(v0.data, 0, v8);
                v2.setPosition(14);
                int v7 = v2.readBits(13);
                if(v7 <= 6) {
                    return 0;
                }

                arg11.advancePeekPosition(v7 - 6);
            }

            arg11.resetPeekPosition();
            ++v1;
            if(v1 - v4 >= 8192) {
                return 0;
            }

            arg11.advancePeekPosition(v1);
        }
    }
}

