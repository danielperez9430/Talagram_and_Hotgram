package com.google.android.exoplayer2.extractor.ts;

import com.google.android.exoplayer2.audio.Ac3Util;
import com.google.android.exoplayer2.extractor.Extractor;
import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.extractor.PositionHolder;
import com.google.android.exoplayer2.extractor.SeekMap$Unseekable;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;

public final class Ac3Extractor implements Extractor {
    private static final int AC3_SYNC_WORD = 2935;
    public static final ExtractorsFactory FACTORY = null;
    private static final int ID3_TAG = 0;
    private static final int MAX_SNIFF_BYTES = 8192;
    private static final int MAX_SYNC_FRAME_SIZE = 2786;
    private final long firstSampleTimestampUs;
    private final Ac3Reader reader;
    private final ParsableByteArray sampleData;
    private boolean startedPacket;

    static {
        Ac3Extractor.FACTORY = -$$Lambda$Ac3Extractor$c2Fqr1pF6vjFNOhLk9sPPtkNnGE.INSTANCE;
        Ac3Extractor.ID3_TAG = Util.getIntegerCodeForString("ID3");
    }

    public Ac3Extractor() {
        this(0);
    }

    public Ac3Extractor(long arg1) {
        super();
        this.firstSampleTimestampUs = arg1;
        this.reader = new Ac3Reader();
        this.sampleData = new ParsableByteArray(2786);
    }

    public void init(ExtractorOutput arg5) {
        this.reader.createTracks(arg5, new TrackIdGenerator(0, 1));
        arg5.endTracks();
        arg5.seekMap(new Unseekable(-9223372036854775807L));
    }

    static Extractor[] lambda$static$0() {
        return new Extractor[]{new Ac3Extractor()};
    }

    public int read(ExtractorInput arg4, PositionHolder arg5) {
        int v4 = arg4.read(this.sampleData.data, 0, 2786);
        int v5 = -1;
        if(v4 == v5) {
            return v5;
        }

        this.sampleData.setPosition(0);
        this.sampleData.setLimit(v4);
        if(!this.startedPacket) {
            this.reader.packetStarted(this.firstSampleTimestampUs, true);
            this.startedPacket = true;
        }

        this.reader.consume(this.sampleData);
        return 0;
    }

    public void release() {
    }

    public void seek(long arg1, long arg3) {
        this.startedPacket = false;
        this.reader.seek();
    }

    public boolean sniff(ExtractorInput arg8) {
        int v4;
        int v1 = 10;
        ParsableByteArray v0 = new ParsableByteArray(v1);
        int v3 = 0;
        while(true) {
            arg8.peekFully(v0.data, 0, v1);
            v0.setPosition(0);
            if(v0.readUnsignedInt24() != Ac3Extractor.ID3_TAG) {
                break;
            }

            v0.skipBytes(3);
            v4 = v0.readSynchSafeInt();
            v3 += v4 + 10;
            arg8.advancePeekPosition(v4);
        }

        arg8.resetPeekPosition();
        arg8.advancePeekPosition(v3);
        v1 = v3;
        while(true) {
            v4 = 0;
            while(true) {
                arg8.peekFully(v0.data, 0, 5);
                v0.setPosition(0);
                if(v0.readUnsignedShort() != 2935) {
                    break;
                }

                ++v4;
                if(v4 >= 4) {
                    return 1;
                }

                int v5 = Ac3Util.parseAc3SyncframeSize(v0.data);
                if(v5 == -1) {
                    return 0;
                }

                arg8.advancePeekPosition(v5 - 5);
            }

            arg8.resetPeekPosition();
            ++v1;
            if(v1 - v3 >= 8192) {
                return 0;
            }

            arg8.advancePeekPosition(v1);
        }
    }
}

