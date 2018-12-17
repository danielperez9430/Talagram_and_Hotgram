package com.google.android.exoplayer2.extractor.rawcc;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.extractor.Extractor;
import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.PositionHolder;
import com.google.android.exoplayer2.extractor.SeekMap$Unseekable;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;
import java.io.IOException;

public final class RawCcExtractor implements Extractor {
    private static final int HEADER_ID = 0;
    private static final int HEADER_SIZE = 8;
    private static final int SCRATCH_SIZE = 9;
    private static final int STATE_READING_HEADER = 0;
    private static final int STATE_READING_SAMPLES = 2;
    private static final int STATE_READING_TIMESTAMP_AND_COUNT = 1;
    private static final int TIMESTAMP_SIZE_V0 = 4;
    private static final int TIMESTAMP_SIZE_V1 = 8;
    private final ParsableByteArray dataScratch;
    private final Format format;
    private int parserState;
    private int remainingSampleCount;
    private int sampleBytesWritten;
    private long timestampUs;
    private TrackOutput trackOutput;
    private int version;

    static {
        RawCcExtractor.HEADER_ID = Util.getIntegerCodeForString("RCC\u0001");
    }

    public RawCcExtractor(Format arg2) {
        super();
        this.format = arg2;
        this.dataScratch = new ParsableByteArray(9);
        this.parserState = 0;
    }

    public void init(ExtractorOutput arg4) {
        arg4.seekMap(new Unseekable(-9223372036854775807L));
        this.trackOutput = arg4.track(0, 3);
        arg4.endTracks();
        this.trackOutput.format(this.format);
    }

    private boolean parseHeader(ExtractorInput arg5) {
        this.dataScratch.reset();
        if(arg5.readFully(this.dataScratch.data, 0, 8, true)) {
            if(this.dataScratch.readInt() == RawCcExtractor.HEADER_ID) {
                this.version = this.dataScratch.readUnsignedByte();
                return 1;
            }

            throw new IOException("Input not RawCC");
        }

        return 0;
    }

    private void parseSamples(ExtractorInput arg8) {
        while(this.remainingSampleCount > 0) {
            this.dataScratch.reset();
            arg8.readFully(this.dataScratch.data, 0, 3);
            this.trackOutput.sampleData(this.dataScratch, 3);
            this.sampleBytesWritten += 3;
            --this.remainingSampleCount;
        }

        if(this.sampleBytesWritten > 0) {
            this.trackOutput.sampleMetadata(this.timestampUs, 1, this.sampleBytesWritten, 0, null);
        }
    }

    private boolean parseTimestampAndSampleCount(ExtractorInput arg8) {
        long v3;
        this.dataScratch.reset();
        if(this.version == 0) {
            if(!arg8.readFully(this.dataScratch.data, 0, 5, true)) {
                return 0;
            }
            else {
                v3 = this.dataScratch.readUnsignedInt() * 1000 / 45;
            }
        }
        else if(this.version != 1) {
            goto label_36;
        }
        else if(!arg8.readFully(this.dataScratch.data, 0, 9, true)) {
            return 0;
        }
        else {
            v3 = this.dataScratch.readLong();
        }

        this.timestampUs = v3;
        this.remainingSampleCount = this.dataScratch.readUnsignedByte();
        this.sampleBytesWritten = 0;
        return 1;
    label_36:
        StringBuilder v0 = new StringBuilder();
        v0.append("Unsupported version number: ");
        v0.append(this.version);
        throw new ParserException(v0.toString());
    }

    public int read(ExtractorInput arg4, PositionHolder arg5) {
        int v0;
        while(true) {
            v0 = -1;
            switch(this.parserState) {
                case 0: {
                    goto label_18;
                }
                case 1: {
                    goto label_11;
                }
                case 2: {
                    goto label_8;
                }
            }

            throw new IllegalStateException();
        label_18:
            if(this.parseHeader(arg4)) {
                this.parserState = 1;
                continue;
            }

            return v0;
        label_8:
            this.parseSamples(arg4);
            this.parserState = 1;
            return 0;
        label_11:
            if(!this.parseTimestampAndSampleCount(arg4)) {
                break;
            }

            this.parserState = 2;
        }

        this.parserState = 0;
        return v0;
    }

    public void release() {
    }

    public void seek(long arg1, long arg3) {
        this.parserState = 0;
    }

    public boolean sniff(ExtractorInput arg4) {
        this.dataScratch.reset();
        boolean v1 = false;
        arg4.peekFully(this.dataScratch.data, 0, 8);
        if(this.dataScratch.readInt() == RawCcExtractor.HEADER_ID) {
            v1 = true;
        }

        return v1;
    }
}

