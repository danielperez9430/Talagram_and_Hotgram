package com.google.android.exoplayer2.extractor.amr;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.extractor.ConstantBitrateSeekMap;
import com.google.android.exoplayer2.extractor.Extractor;
import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.extractor.PositionHolder;
import com.google.android.exoplayer2.extractor.SeekMap$Unseekable;
import com.google.android.exoplayer2.extractor.SeekMap;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.util.Util;
import java.io.EOFException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Arrays;

public final class AmrExtractor implements Extractor {
    final class com.google.android.exoplayer2.extractor.amr.AmrExtractor$1 implements ExtractorsFactory {
        com.google.android.exoplayer2.extractor.amr.AmrExtractor$1() {
            super();
        }

        public Extractor[] createExtractors() {
            return new Extractor[]{new AmrExtractor()};
        }
    }

    @Retention(value=RetentionPolicy.SOURCE) @public interface Flags {
    }

    public static final ExtractorsFactory FACTORY = null;
    public static final int FLAG_ENABLE_CONSTANT_BITRATE_SEEKING = 1;
    private static final int MAX_FRAME_SIZE_BYTES = 0;
    private static final int NUM_SAME_SIZE_CONSTANT_BIT_RATE_THRESHOLD = 20;
    private static final int SAMPLE_RATE_NB = 8000;
    private static final int SAMPLE_RATE_WB = 16000;
    private static final int SAMPLE_TIME_PER_FRAME_US = 20000;
    private static final byte[] amrSignatureNb;
    private static final byte[] amrSignatureWb;
    private int currentSampleBytesRemaining;
    private int currentSampleSize;
    private long currentSampleTimeUs;
    private ExtractorOutput extractorOutput;
    private long firstSamplePosition;
    private int firstSampleSize;
    private final int flags;
    private static final int[] frameSizeBytesByTypeNb;
    private static final int[] frameSizeBytesByTypeWb;
    private boolean hasOutputFormat;
    private boolean hasOutputSeekMap;
    private boolean isWideBand;
    private int numSamplesWithSameSize;
    private final byte[] scratch;
    private SeekMap seekMap;
    private long timeOffsetUs;
    private TrackOutput trackOutput;

    static {
        AmrExtractor.FACTORY = new com.google.android.exoplayer2.extractor.amr.AmrExtractor$1();
        AmrExtractor.frameSizeBytesByTypeNb = new int[]{13, 14, 16, 18, 20, 21, 27, 32, 6, 7, 6, 6, 1, 1, 1, 1};
        AmrExtractor.frameSizeBytesByTypeWb = new int[]{18, 24, 33, 37, 41, 47, 51, 59, 61, 6, 1, 1, 1, 1, 1, 1};
        AmrExtractor.amrSignatureNb = Util.getUtf8Bytes("#!AMR\n");
        AmrExtractor.amrSignatureWb = Util.getUtf8Bytes("#!AMR-WB\n");
        AmrExtractor.MAX_FRAME_SIZE_BYTES = AmrExtractor.frameSizeBytesByTypeWb[8];
    }

    public AmrExtractor() {
        this(0);
    }

    public AmrExtractor(int arg1) {
        super();
        this.flags = arg1;
        this.scratch = new byte[1];
        this.firstSampleSize = -1;
    }

    static byte[] amrSignatureNb() {
        return Arrays.copyOf(AmrExtractor.amrSignatureNb, AmrExtractor.amrSignatureNb.length);
    }

    static byte[] amrSignatureWb() {
        return Arrays.copyOf(AmrExtractor.amrSignatureWb, AmrExtractor.amrSignatureWb.length);
    }

    static int frameSizeBytesByTypeNb(int arg1) {
        return AmrExtractor.frameSizeBytesByTypeNb[arg1];
    }

    static int frameSizeBytesByTypeWb(int arg1) {
        return AmrExtractor.frameSizeBytesByTypeWb[arg1];
    }

    private static int getBitrateFromFrameSize(int arg4, long arg5) {
        return ((int)((((long)(arg4 * 8))) * 1000000 / arg5));
    }

    private SeekMap getConstantBitrateSeekMap(long arg11) {
        return new ConstantBitrateSeekMap(arg11, this.firstSamplePosition, AmrExtractor.getBitrateFromFrameSize(this.firstSampleSize, 20000), this.firstSampleSize);
    }

    private int getFrameSizeInBytes(int arg4) {
        if(!this.isValidFrameType(arg4)) {
            StringBuilder v1 = new StringBuilder();
            v1.append("Illegal AMR ");
            String v2 = this.isWideBand ? "WB" : "NB";
            v1.append(v2);
            v1.append(" frame type ");
            v1.append(arg4);
            throw new ParserException(v1.toString());
        }

        return this.isWideBand ? AmrExtractor.frameSizeBytesByTypeWb[arg4] : AmrExtractor.frameSizeBytesByTypeNb[arg4];
    }

    public void init(ExtractorOutput arg3) {
        this.extractorOutput = arg3;
        this.trackOutput = arg3.track(0, 1);
        arg3.endTracks();
    }

    private boolean isNarrowBandValidFrameType(int arg2) {
        boolean v2;
        if(!this.isWideBand) {
            if(arg2 >= 12 && arg2 <= 14) {
                goto label_8;
            }

            v2 = true;
        }
        else {
        label_8:
            v2 = false;
        }

        return v2;
    }

    private boolean isValidFrameType(int arg2) {
        boolean v2;
        if(arg2 < 0 || arg2 > 15) {
        label_9:
            v2 = false;
        }
        else {
            if(!this.isWideBandValidFrameType(arg2) && !this.isNarrowBandValidFrameType(arg2)) {
                goto label_9;
            }

            v2 = true;
        }

        return v2;
    }

    private boolean isWideBandValidFrameType(int arg2) {
        boolean v2;
        if(this.isWideBand) {
            if(arg2 >= 10 && arg2 <= 13) {
                goto label_8;
            }

            v2 = true;
        }
        else {
        label_8:
            v2 = false;
        }

        return v2;
    }

    private void maybeOutputFormat() {
        if(!this.hasOutputFormat) {
            this.hasOutputFormat = true;
            String v0 = this.isWideBand ? "audio/amr-wb" : "audio/3gpp";
            String v2 = v0;
            int v7 = this.isWideBand ? 16000 : 8000;
            this.trackOutput.format(Format.createAudioSampleFormat(null, v2, null, -1, AmrExtractor.MAX_FRAME_SIZE_BYTES, 1, v7, -1, null, null, 0, null));
        }
    }

    private void maybeOutputSeekMap(long arg5, int arg7) {
        if(this.hasOutputSeekMap) {
            return;
        }

        if((this.flags & 1) == 0 || arg5 == -1) {
        label_22:
            Unseekable v5_1 = new Unseekable(-9223372036854775807L);
        label_25:
            this.seekMap = ((SeekMap)v5_1);
            this.extractorOutput.seekMap(this.seekMap);
            this.hasOutputSeekMap = true;
        }
        else {
            int v2 = -1;
            if(this.firstSampleSize != v2 && this.firstSampleSize != this.currentSampleSize) {
                goto label_22;
            }

            if(this.numSamplesWithSameSize < 20 && arg7 != v2) {
                return;
            }

            SeekMap v5 = this.getConstantBitrateSeekMap(arg5);
            goto label_25;
        }
    }

    private boolean peekAmrSignature(ExtractorInput arg4, byte[] arg5) {
        arg4.resetPeekPosition();
        byte[] v0 = new byte[arg5.length];
        arg4.peekFully(v0, 0, arg5.length);
        return Arrays.equals(v0, arg5);
    }

    private int peekNextSampleSize(ExtractorInput arg4) {
        arg4.resetPeekPosition();
        arg4.peekFully(this.scratch, 0, 1);
        int v4 = this.scratch[0];
        if((v4 & 131) <= 0) {
            return this.getFrameSizeInBytes(v4 >> 3 & 15);
        }

        StringBuilder v1 = new StringBuilder();
        v1.append("Invalid padding bits for frame header ");
        v1.append(v4);
        throw new ParserException(v1.toString());
    }

    public int read(ExtractorInput arg5, PositionHolder arg6) {
        if(arg5.getPosition() == 0) {
            if(this.readAmrHeader(arg5)) {
            }
            else {
                throw new ParserException("Could not find AMR header.");
            }
        }

        this.maybeOutputFormat();
        int v6 = this.readSample(arg5);
        this.maybeOutputSeekMap(arg5.getLength(), v6);
        return v6;
    }

    private boolean readAmrHeader(ExtractorInput arg4) {
        int v0;
        if(this.peekAmrSignature(arg4, AmrExtractor.amrSignatureNb)) {
            this.isWideBand = false;
            v0 = AmrExtractor.amrSignatureNb.length;
        }
        else if(this.peekAmrSignature(arg4, AmrExtractor.amrSignatureWb)) {
            this.isWideBand = true;
            v0 = AmrExtractor.amrSignatureWb.length;
        }
        else {
            return 0;
        }

        arg4.skipFully(v0);
        return 1;
    }

    private int readSample(ExtractorInput arg9) {
        int v2 = -1;
        if(this.currentSampleBytesRemaining == 0) {
            try {
                this.currentSampleSize = this.peekNextSampleSize(arg9);
            }
            catch(EOFException ) {
                return v2;
            }

            this.currentSampleBytesRemaining = this.currentSampleSize;
            if(this.firstSampleSize == v2) {
                this.firstSamplePosition = arg9.getPosition();
                this.firstSampleSize = this.currentSampleSize;
            }

            if(this.firstSampleSize != this.currentSampleSize) {
                goto label_22;
            }

            ++this.numSamplesWithSameSize;
        }

    label_22:
        int v9 = this.trackOutput.sampleData(arg9, this.currentSampleBytesRemaining, true);
        if(v9 == v2) {
            return v2;
        }

        this.currentSampleBytesRemaining -= v9;
        if(this.currentSampleBytesRemaining > 0) {
            return 0;
        }

        this.trackOutput.sampleMetadata(this.timeOffsetUs + this.currentSampleTimeUs, 1, this.currentSampleSize, 0, null);
        this.currentSampleTimeUs += 20000;
        return 0;
    }

    public void release() {
    }

    public void seek(long arg2, long arg4) {
        arg4 = 0;
        this.currentSampleTimeUs = arg4;
        this.currentSampleSize = 0;
        this.currentSampleBytesRemaining = 0;
        this.timeOffsetUs = arg2 == arg4 || !(this.seekMap instanceof ConstantBitrateSeekMap) ? arg4 : this.seekMap.getTimeUsAtPosition(arg2);
    }

    public boolean sniff(ExtractorInput arg1) {
        return this.readAmrHeader(arg1);
    }
}

