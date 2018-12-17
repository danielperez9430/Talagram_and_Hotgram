package com.google.android.exoplayer2.extractor.mp3;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.drm.DrmInitData;
import com.google.android.exoplayer2.extractor.Extractor;
import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.extractor.GaplessInfoHolder;
import com.google.android.exoplayer2.extractor.Id3Peeker;
import com.google.android.exoplayer2.extractor.MpegAudioHeader;
import com.google.android.exoplayer2.extractor.PositionHolder;
import com.google.android.exoplayer2.extractor.SeekMap;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;
import java.io.EOFException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

public final class Mp3Extractor implements Extractor {
    @Retention(value=RetentionPolicy.SOURCE) @public interface Flags {
    }

    interface Seeker extends SeekMap {
        long getTimeUs(long arg1);
    }

    public static final ExtractorsFactory FACTORY = null;
    public static final int FLAG_DISABLE_ID3_METADATA = 2;
    public static final int FLAG_ENABLE_CONSTANT_BITRATE_SEEKING = 1;
    private static final int MAX_SNIFF_BYTES = 16384;
    private static final int MAX_SYNC_BYTES = 131072;
    private static final int MPEG_AUDIO_HEADER_MASK = -128000;
    private static final int SCRATCH_LENGTH = 10;
    private static final int SEEK_HEADER_INFO;
    private static final int SEEK_HEADER_UNSET;
    private static final int SEEK_HEADER_VBRI;
    private static final int SEEK_HEADER_XING;
    private long basisTimeUs;
    private ExtractorOutput extractorOutput;
    private final int flags;
    private final long forcedFirstSampleTimestampUs;
    private final GaplessInfoHolder gaplessInfoHolder;
    private final Id3Peeker id3Peeker;
    private Metadata metadata;
    private int sampleBytesRemaining;
    private long samplesRead;
    private final ParsableByteArray scratch;
    private Seeker seeker;
    private final MpegAudioHeader synchronizedHeader;
    private int synchronizedHeaderData;
    private TrackOutput trackOutput;

    static {
        Mp3Extractor.FACTORY = -$$Lambda$Mp3Extractor$6eyGfoogMVGFHZKg1gVp93FAKZA.INSTANCE;
        Mp3Extractor.SEEK_HEADER_XING = Util.getIntegerCodeForString("Xing");
        Mp3Extractor.SEEK_HEADER_INFO = Util.getIntegerCodeForString("Info");
        Mp3Extractor.SEEK_HEADER_VBRI = Util.getIntegerCodeForString("VBRI");
    }

    public Mp3Extractor(int arg3) {
        this(arg3, -9223372036854775807L);
    }

    public Mp3Extractor() {
        this(0);
    }

    public Mp3Extractor(int arg1, long arg2) {
        super();
        this.flags = arg1;
        this.forcedFirstSampleTimestampUs = arg2;
        this.scratch = new ParsableByteArray(10);
        this.synchronizedHeader = new MpegAudioHeader();
        this.gaplessInfoHolder = new GaplessInfoHolder();
        this.basisTimeUs = -9223372036854775807L;
        this.id3Peeker = new Id3Peeker();
    }

    private Seeker getConstantBitrateSeeker(ExtractorInput arg9) {
        arg9.peekFully(this.scratch.data, 0, 4);
        this.scratch.setPosition(0);
        MpegAudioHeader.populateHeader(this.scratch.readInt(), this.synchronizedHeader);
        return new ConstantBitrateSeeker(arg9.getLength(), arg9.getPosition(), this.synchronizedHeader);
    }

    private static int getSeekFrameHeader(ParsableByteArray arg2, int arg3) {
        if(arg2.limit() >= arg3 + 4) {
            arg2.setPosition(arg3);
            arg3 = arg2.readInt();
            if(arg3 != Mp3Extractor.SEEK_HEADER_XING && arg3 != Mp3Extractor.SEEK_HEADER_INFO) {
                goto label_10;
            }

            return arg3;
        }

    label_10:
        if(arg2.limit() >= 40) {
            arg2.setPosition(36);
            if(arg2.readInt() == Mp3Extractor.SEEK_HEADER_VBRI) {
                return Mp3Extractor.SEEK_HEADER_VBRI;
            }
        }

        return 0;
    }

    private static boolean headersMatch(int arg4, long arg5) {
        boolean v4 = (((long)(arg4 & -128000))) == (arg5 & -128000) ? true : false;
        return v4;
    }

    public void init(ExtractorOutput arg3) {
        this.extractorOutput = arg3;
        this.trackOutput = this.extractorOutput.track(0, 1);
        this.extractorOutput.endTracks();
    }

    static Extractor[] lambda$static$0() {
        return new Extractor[]{new Mp3Extractor()};
    }

    private Seeker maybeReadSeekFrame(ExtractorInput arg10) {
        XingSeeker v0_2;
        int v7;
        ParsableByteArray v5 = new ParsableByteArray(this.synchronizedHeader.frameSize);
        arg10.peekFully(v5.data, 0, this.synchronizedHeader.frameSize);
        if((this.synchronizedHeader.version & 1) != 0) {
            if(this.synchronizedHeader.channels != 1) {
                v7 = 36;
            }
            else {
                goto label_21;
            }
        }
        else if(this.synchronizedHeader.channels != 1) {
        label_21:
            v7 = 21;
        }
        else {
            v7 = 13;
        }

        int v8 = Mp3Extractor.getSeekFrameHeader(v5, v7);
        if(v8 == Mp3Extractor.SEEK_HEADER_XING || v8 == Mp3Extractor.SEEK_HEADER_INFO) {
            v0_2 = XingSeeker.create(arg10.getLength(), arg10.getPosition(), this.synchronizedHeader, v5);
            if(v0_2 != null && !this.gaplessInfoHolder.hasGaplessInfo()) {
                arg10.resetPeekPosition();
                arg10.advancePeekPosition(v7 + 141);
                arg10.peekFully(this.scratch.data, 0, 3);
                this.scratch.setPosition(0);
                this.gaplessInfoHolder.setFromXingHeaderValue(this.scratch.readUnsignedInt24());
            }

            arg10.skipFully(this.synchronizedHeader.frameSize);
            if(v0_2 == null) {
                goto label_79;
            }

            if(((Seeker)v0_2).isSeekable()) {
                goto label_79;
            }

            if(v8 != Mp3Extractor.SEEK_HEADER_INFO) {
                goto label_79;
            }

            return this.getConstantBitrateSeeker(arg10);
        }
        else if(v8 == Mp3Extractor.SEEK_HEADER_VBRI) {
            VbriSeeker v0 = VbriSeeker.create(arg10.getLength(), arg10.getPosition(), this.synchronizedHeader, v5);
            arg10.skipFully(this.synchronizedHeader.frameSize);
        }
        else {
            Seeker v0_1 = null;
            arg10.resetPeekPosition();
        }

    label_79:
        return ((Seeker)v0_2);
    }

    public int read(ExtractorInput arg19, PositionHolder arg20) {
        Mp3Extractor v0 = this;
        if(v0.synchronizedHeaderData == 0) {
            ExtractorInput v2 = arg19;
            try {
                v0.synchronize(v2, false);
            }
            catch(EOFException ) {
                return -1;
            }
        }

        if(v0.seeker == null) {
            v0.seeker = this.maybeReadSeekFrame(arg19);
            if(v0.seeker == null || !v0.seeker.isSeekable() && (v0.flags & 1) != 0) {
                v0.seeker = this.getConstantBitrateSeeker(arg19);
            }

            v0.extractorOutput.seekMap(v0.seeker);
            TrackOutput v1 = v0.trackOutput;
            String v3 = null;
            String v4 = v0.synchronizedHeader.mimeType;
            String v5 = null;
            int v6 = -1;
            int v7 = 4096;
            int v8 = v0.synchronizedHeader.channels;
            int v9 = v0.synchronizedHeader.sampleRate;
            int v10 = -1;
            int v11 = v0.gaplessInfoHolder.encoderDelay;
            int v12 = v0.gaplessInfoHolder.encoderPadding;
            List v13 = null;
            DrmInitData v14 = null;
            String v16 = null;
            Metadata v15 = (v0.flags & 2) != 0 ? null : v0.metadata;
            Metadata v17 = v15;
            v1.format(Format.createAudioSampleFormat(v3, v4, v5, v6, v7, v8, v9, v10, v11, v12, v13, v14, 0, v16, v17));
        }

        return this.readSample(arg19);
    }

    private int readSample(ExtractorInput arg14) {
        int v2 = -1;
        if(this.sampleBytesRemaining == 0) {
            arg14.resetPeekPosition();
            if(!arg14.peekFully(this.scratch.data, 0, 4, true)) {
                return v2;
            }
            else {
                this.scratch.setPosition(0);
                int v0 = this.scratch.readInt();
                if(Mp3Extractor.headersMatch(v0, ((long)this.synchronizedHeaderData))) {
                    if(MpegAudioHeader.getFrameSize(v0) == v2) {
                    }
                    else {
                        MpegAudioHeader.populateHeader(v0, this.synchronizedHeader);
                        long v6 = -9223372036854775807L;
                        if(this.basisTimeUs == v6) {
                            this.basisTimeUs = this.seeker.getTimeUs(arg14.getPosition());
                            if(this.forcedFirstSampleTimestampUs != v6) {
                                this.basisTimeUs += this.forcedFirstSampleTimestampUs - this.seeker.getTimeUs(0);
                            }
                        }

                        this.sampleBytesRemaining = this.synchronizedHeader.frameSize;
                        goto label_49;
                    }
                }

                arg14.skipFully(1);
                this.synchronizedHeaderData = 0;
                return 0;
            }
        }

    label_49:
        int v14 = this.trackOutput.sampleData(arg14, this.sampleBytesRemaining, true);
        if(v14 == v2) {
            return v2;
        }

        this.sampleBytesRemaining -= v14;
        if(this.sampleBytesRemaining > 0) {
            return 0;
        }

        this.trackOutput.sampleMetadata(this.basisTimeUs + this.samplesRead * 1000000 / (((long)this.synchronizedHeader.sampleRate)), 1, this.synchronizedHeader.frameSize, 0, null);
        this.samplesRead += ((long)this.synchronizedHeader.samplesPerFrame);
        this.sampleBytesRemaining = 0;
        return 0;
    }

    public void release() {
    }

    public void seek(long arg1, long arg3) {
        this.synchronizedHeaderData = 0;
        this.basisTimeUs = -9223372036854775807L;
        this.samplesRead = 0;
        this.sampleBytesRemaining = 0;
    }

    public boolean sniff(ExtractorInput arg2) {
        return this.synchronize(arg2, true);
    }

    private boolean synchronize(ExtractorInput arg13, boolean arg14) {
        // Method was not decompiled
    }
}

