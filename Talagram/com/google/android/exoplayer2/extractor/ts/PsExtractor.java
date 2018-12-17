package com.google.android.exoplayer2.extractor.ts;

import android.util.SparseArray;
import com.google.android.exoplayer2.extractor.Extractor;
import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.extractor.PositionHolder;
import com.google.android.exoplayer2.extractor.SeekMap$Unseekable;
import com.google.android.exoplayer2.util.ParsableBitArray;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.TimestampAdjuster;

public final class PsExtractor implements Extractor {
    final class PesReader {
        private static final int PES_SCRATCH_SIZE = 64;
        private boolean dtsFlag;
        private int extendedHeaderLength;
        private final ElementaryStreamReader pesPayloadReader;
        private final ParsableBitArray pesScratch;
        private boolean ptsFlag;
        private boolean seenFirstDts;
        private long timeUs;
        private final TimestampAdjuster timestampAdjuster;

        public PesReader(ElementaryStreamReader arg1, TimestampAdjuster arg2) {
            super();
            this.pesPayloadReader = arg1;
            this.timestampAdjuster = arg2;
            this.pesScratch = new ParsableBitArray(new byte[64]);
        }

        public void consume(ParsableByteArray arg5) {
            arg5.readBytes(this.pesScratch.data, 0, 3);
            this.pesScratch.setPosition(0);
            this.parseHeader();
            arg5.readBytes(this.pesScratch.data, 0, this.extendedHeaderLength);
            this.pesScratch.setPosition(0);
            this.parseHeaderExtension();
            this.pesPayloadReader.packetStarted(this.timeUs, true);
            this.pesPayloadReader.consume(arg5);
            this.pesPayloadReader.packetFinished();
        }

        private void parseHeader() {
            this.pesScratch.skipBits(8);
            this.ptsFlag = this.pesScratch.readBit();
            this.dtsFlag = this.pesScratch.readBit();
            this.pesScratch.skipBits(6);
            this.extendedHeaderLength = this.pesScratch.readBits(8);
        }

        private void parseHeaderExtension() {
            this.timeUs = 0;
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

        public void seek() {
            this.seenFirstDts = false;
            this.pesPayloadReader.seek();
        }
    }

    public static final int AUDIO_STREAM = 192;
    public static final int AUDIO_STREAM_MASK = 224;
    public static final ExtractorsFactory FACTORY = null;
    private static final long MAX_SEARCH_LENGTH = 1048576;
    private static final long MAX_SEARCH_LENGTH_AFTER_AUDIO_AND_VIDEO_FOUND = 8192;
    private static final int MAX_STREAM_ID_PLUS_ONE = 256;
    private static final int MPEG_PROGRAM_END_CODE = 441;
    private static final int PACKET_START_CODE_PREFIX = 1;
    static final int PACK_START_CODE = 442;
    public static final int PRIVATE_STREAM_1 = 189;
    private static final int SYSTEM_HEADER_START_CODE = 443;
    public static final int VIDEO_STREAM = 224;
    public static final int VIDEO_STREAM_MASK = 240;
    private final PsDurationReader durationReader;
    private boolean foundAllTracks;
    private boolean foundAudioTrack;
    private boolean foundVideoTrack;
    private boolean hasOutputSeekMap;
    private long lastTrackPosition;
    private ExtractorOutput output;
    private final ParsableByteArray psPacketBuffer;
    private final SparseArray psPayloadReaders;
    private final TimestampAdjuster timestampAdjuster;

    static {
        PsExtractor.FACTORY = -$$Lambda$PsExtractor$U8l9TedlJUwsYwV9EOSFo_ngcXY.INSTANCE;
    }

    public PsExtractor() {
        this(new TimestampAdjuster(0));
    }

    public PsExtractor(TimestampAdjuster arg2) {
        super();
        this.timestampAdjuster = arg2;
        this.psPacketBuffer = new ParsableByteArray(4096);
        this.psPayloadReaders = new SparseArray();
        this.durationReader = new PsDurationReader();
    }

    public void init(ExtractorOutput arg1) {
        this.output = arg1;
    }

    static Extractor[] lambda$static$0() {
        return new Extractor[]{new PsExtractor()};
    }

    private void maybeOutputSeekMap() {
        if(!this.hasOutputSeekMap) {
            this.hasOutputSeekMap = true;
            this.output.seekMap(new Unseekable(this.durationReader.getDurationUs()));
        }
    }

    public int read(ExtractorInput arg10, PositionHolder arg11) {
        // Method was not decompiled
    }

    public void release() {
    }

    public void seek(long arg1, long arg3) {
        this.timestampAdjuster.reset();
        int v1;
        for(v1 = 0; v1 < this.psPayloadReaders.size(); ++v1) {
            this.psPayloadReaders.valueAt(v1).seek();
        }
    }

    public boolean sniff(ExtractorInput arg10) {
        byte[] v1 = new byte[14];
        boolean v2 = false;
        arg10.peekFully(v1, 0, 14);
        int v4 = 2;
        int v6 = 8;
        int v5 = 3;
        if(442 != ((v1[0] & 255) << 24 | (v1[1] & 255) << 16 | (v1[v4] & 255) << v6 | v1[v5] & 255)) {
            return 0;
        }

        int v0 = 4;
        if((v1[v0] & 196) != 68) {
            return 0;
        }

        if((v1[6] & v0) != v0) {
            return 0;
        }

        if((v1[v6] & v0) != v0) {
            return 0;
        }

        if((v1[9] & 1) != 1) {
            return 0;
        }

        if((v1[12] & v5) != v5) {
            return 0;
        }

        arg10.advancePeekPosition(v1[13] & 7);
        arg10.peekFully(v1, 0, v5);
        if(1 == ((v1[0] & 255) << 16 | (v1[1] & 255) << v6 | v1[v4] & 255)) {
            v2 = true;
        }

        return v2;
    }
}

