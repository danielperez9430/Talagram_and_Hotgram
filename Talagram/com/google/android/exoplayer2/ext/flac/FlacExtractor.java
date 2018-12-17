package com.google.android.exoplayer2.ext.flac;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.extractor.BinarySearchSeeker$OutputFrameHolder;
import com.google.android.exoplayer2.extractor.Extractor;
import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.extractor.Id3Peeker;
import com.google.android.exoplayer2.extractor.PositionHolder;
import com.google.android.exoplayer2.extractor.SeekMap$SeekPoints;
import com.google.android.exoplayer2.extractor.SeekMap$Unseekable;
import com.google.android.exoplayer2.extractor.SeekMap;
import com.google.android.exoplayer2.extractor.SeekPoint;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.metadata.id3.Id3Decoder$FramePredicate;
import com.google.android.exoplayer2.metadata.id3.Id3Decoder;
import com.google.android.exoplayer2.util.FlacStreamInfo;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.nio.ByteBuffer;
import java.util.Arrays;

public final class FlacExtractor implements Extractor {
    final class FlacSeekMap implements SeekMap {
        private final FlacDecoderJni decoderJni;
        private final long durationUs;

        public FlacSeekMap(long arg1, FlacDecoderJni arg3) {
            super();
            this.durationUs = arg1;
            this.decoderJni = arg3;
        }

        public long getDurationUs() {
            return this.durationUs;
        }

        public SeekPoints getSeekPoints(long arg5) {
            return new SeekPoints(new SeekPoint(arg5, this.decoderJni.getSeekPosition(arg5)));
        }

        public boolean isSeekable() {
            return 1;
        }
    }

    @Retention(value=RetentionPolicy.SOURCE) @public interface Flags {
    }

    public static final ExtractorsFactory FACTORY = null;
    private static final byte[] FLAC_SIGNATURE = null;
    public static final int FLAG_DISABLE_ID3_METADATA = 1;
    private FlacDecoderJni decoderJni;
    private ExtractorOutput extractorOutput;
    private FlacBinarySearchSeeker flacBinarySearchSeeker;
    private Metadata id3Metadata;
    private final Id3Peeker id3Peeker;
    private final boolean isId3MetadataDisabled;
    private ParsableByteArray outputBuffer;
    private ByteBuffer outputByteBuffer;
    private OutputFrameHolder outputFrameHolder;
    private boolean readPastStreamInfo;
    private FlacStreamInfo streamInfo;
    private TrackOutput trackOutput;

    static {
        FlacExtractor.FACTORY = -$$Lambda$FlacExtractor$hclvvK8AqHrca9y8kXj1zx0IKB4.INSTANCE;
        FlacExtractor.FLAC_SIGNATURE = new byte[]{102, 76, 97, 67, 0, 0, 0, 34};
    }

    public FlacExtractor() {
        this(0);
    }

    public FlacExtractor(int arg2) {
        super();
        this.id3Peeker = new Id3Peeker();
        boolean v0 = true;
        if((arg2 & 1) != 0) {
        }
        else {
            v0 = false;
        }

        this.isId3MetadataDisabled = v0;
    }

    private FlacStreamInfo decodeStreamInfo(ExtractorInput arg5) {
        try {
            FlacStreamInfo v0_1 = this.decoderJni.decodeMetadata();
            if(v0_1 != null) {
                return v0_1;
            }

            throw new IOException("Metadata decoding failed");
        }
        catch(IOException v0) {
            this.decoderJni.reset(0);
            arg5.setRetryPosition(0, ((Throwable)v0));
            throw v0;
        }
    }

    private SeekMap getSeekMapForNonSeekTableFlac(ExtractorInput arg8, FlacStreamInfo arg9) {
        long v4 = arg8.getLength();
        if(v4 != -1) {
            this.flacBinarySearchSeeker = new FlacBinarySearchSeeker(arg9, this.decoderJni.getDecodePosition(), v4, this.decoderJni);
            return this.flacBinarySearchSeeker.getSeekMap();
        }

        return new Unseekable(arg9.durationUs());
    }

    private int handlePendingSeek(ExtractorInput arg3, PositionHolder arg4) {
        int v3 = this.flacBinarySearchSeeker.handlePendingSeek(arg3, arg4, this.outputFrameHolder);
        ByteBuffer v4 = this.outputFrameHolder.byteBuffer;
        if(v3 == 0 && v4.limit() > 0) {
            this.writeLastSampleToOutput(v4.limit(), this.outputFrameHolder.timeUs);
        }

        return v3;
    }

    public void init(ExtractorOutput arg3) {
        this.extractorOutput = arg3;
        this.trackOutput = this.extractorOutput.track(0, 1);
        this.extractorOutput.endTracks();
        try {
            this.decoderJni = new FlacDecoderJni();
            return;
        }
        catch(FlacDecoderException v3) {
            throw new RuntimeException(((Throwable)v3));
        }
    }

    static Extractor[] lambda$static$0() {
        return new Extractor[]{new FlacExtractor()};
    }

    private void outputFormat(FlacStreamInfo arg17) {
        FlacExtractor v0 = this;
        String v2 = "audio/raw";
        int v4 = arg17.bitRate();
        int v5 = arg17.maxDecodedFrameSize();
        int v6 = arg17.channels;
        int v7 = arg17.sampleRate;
        int v8 = Util.getPcmEncoding(arg17.bitsPerSample);
        Metadata v1 = v0.isId3MetadataDisabled ? null : v0.id3Metadata;
        Metadata v15 = v1;
        v0.trackOutput.format(Format.createAudioSampleFormat(null, v2, null, v4, v5, v6, v7, v8, 0, 0, null, null, 0, null, v15));
    }

    private void outputSeekMap(ExtractorInput arg6, FlacStreamInfo arg7) {
        FlacSeekMap v6;
        int v0 = this.decoderJni.getSeekPosition(0) != -1 ? 1 : 0;
        if(v0 != 0) {
            v6 = new FlacSeekMap(arg7.durationUs(), this.decoderJni);
        }
        else {
            SeekMap v6_1 = this.getSeekMapForNonSeekTableFlac(arg6, arg7);
        }

        this.extractorOutput.seekMap(((SeekMap)v6));
    }

    private boolean peekFlacSignature(ExtractorInput arg4) {
        byte[] v0 = new byte[FlacExtractor.FLAC_SIGNATURE.length];
        arg4.peekFully(v0, 0, FlacExtractor.FLAC_SIGNATURE.length);
        return Arrays.equals(v0, FlacExtractor.FLAC_SIGNATURE);
    }

    private Metadata peekId3Data(ExtractorInput arg3) {
        arg3.resetPeekPosition();
        FramePredicate v0 = this.isId3MetadataDisabled ? Id3Decoder.NO_FRAMES_PREDICATE : null;
        return this.id3Peeker.peekId3Data(arg3, v0);
    }

    public int read(ExtractorInput arg6, PositionHolder arg7) {
        if(arg6.getPosition() == 0 && !this.isId3MetadataDisabled && this.id3Metadata == null) {
            this.id3Metadata = this.peekId3Data(arg6);
        }

        this.decoderJni.setData(arg6);
        this.readPastStreamInfo(arg6);
        if(this.flacBinarySearchSeeker != null && (this.flacBinarySearchSeeker.isSeeking())) {
            return this.handlePendingSeek(arg6, arg7);
        }

        long v6 = this.decoderJni.getDecodePosition();
        try {
            this.decoderJni.decodeSampleWithBacktrackPosition(this.outputByteBuffer, v6);
        }
        catch(FlacFrameDecodeException v0) {
            StringBuilder v2 = new StringBuilder();
            v2.append("Cannot read frame at position ");
            v2.append(v6);
            throw new IOException(v2.toString(), ((Throwable)v0));
        }

        int v6_1 = this.outputByteBuffer.limit();
        int v7 = -1;
        if(v6_1 == 0) {
            return v7;
        }

        this.writeLastSampleToOutput(v6_1, this.decoderJni.getLastFrameTimestamp());
        if(this.decoderJni.isEndOfData()) {
        }
        else {
            v7 = 0;
        }

        return v7;
    }

    private void readPastStreamInfo(ExtractorInput arg3) {
        if(this.readPastStreamInfo) {
            return;
        }

        FlacStreamInfo v0 = this.decodeStreamInfo(arg3);
        this.readPastStreamInfo = true;
        if(this.streamInfo == null) {
            this.updateFlacStreamInfo(arg3, v0);
        }
    }

    public void release() {
        FlacBinarySearchSeeker v0 = null;
        this.flacBinarySearchSeeker = v0;
        if(this.decoderJni != null) {
            this.decoderJni.release();
            this.decoderJni = ((FlacDecoderJni)v0);
        }
    }

    public void seek(long arg4, long arg6) {
        if(arg4 == 0) {
            this.readPastStreamInfo = false;
        }

        if(this.decoderJni != null) {
            this.decoderJni.reset(arg4);
        }

        if(this.flacBinarySearchSeeker != null) {
            this.flacBinarySearchSeeker.setSeekTargetUs(arg6);
        }
    }

    public boolean sniff(ExtractorInput arg6) {
        if(arg6.getPosition() == 0) {
            this.id3Metadata = this.peekId3Data(arg6);
        }

        return this.peekFlacSignature(arg6);
    }

    private void updateFlacStreamInfo(ExtractorInput arg1, FlacStreamInfo arg2) {
        this.streamInfo = arg2;
        this.outputSeekMap(arg1, arg2);
        this.outputFormat(arg2);
        this.outputBuffer = new ParsableByteArray(arg2.maxDecodedFrameSize());
        this.outputByteBuffer = ByteBuffer.wrap(this.outputBuffer.data);
        this.outputFrameHolder = new OutputFrameHolder(this.outputByteBuffer);
    }

    private void writeLastSampleToOutput(int arg10, long arg11) {
        this.outputBuffer.setPosition(0);
        this.trackOutput.sampleData(this.outputBuffer, arg10);
        this.trackOutput.sampleMetadata(arg11, 1, arg10, 0, null);
    }
}

