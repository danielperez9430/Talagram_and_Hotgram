package com.google.android.exoplayer2.extractor.flv;

import com.google.android.exoplayer2.extractor.Extractor;
import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.extractor.PositionHolder;
import com.google.android.exoplayer2.extractor.SeekMap$Unseekable;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class FlvExtractor implements Extractor {
    @Retention(value=RetentionPolicy.SOURCE) @interface States {
    }

    public static final ExtractorsFactory FACTORY = null;
    private static final int FLV_HEADER_SIZE = 9;
    private static final int FLV_TAG = 0;
    private static final int FLV_TAG_HEADER_SIZE = 11;
    private static final int STATE_READING_FLV_HEADER = 1;
    private static final int STATE_READING_TAG_DATA = 4;
    private static final int STATE_READING_TAG_HEADER = 3;
    private static final int STATE_SKIPPING_TO_TAG_HEADER = 2;
    private static final int TAG_TYPE_AUDIO = 8;
    private static final int TAG_TYPE_SCRIPT_DATA = 18;
    private static final int TAG_TYPE_VIDEO = 9;
    private AudioTagPayloadReader audioReader;
    private int bytesToNextTagHeader;
    private ExtractorOutput extractorOutput;
    private final ParsableByteArray headerBuffer;
    private long mediaTagTimestampOffsetUs;
    private final ScriptTagPayloadReader metadataReader;
    private boolean outputSeekMap;
    private final ParsableByteArray scratch;
    private int state;
    private final ParsableByteArray tagData;
    private int tagDataSize;
    private final ParsableByteArray tagHeaderBuffer;
    private long tagTimestampUs;
    private int tagType;
    private VideoTagPayloadReader videoReader;

    static {
        FlvExtractor.FACTORY = -$$Lambda$FlvExtractor$bd1zICO7f-FQot_hbozdu7LjVyE.INSTANCE;
        FlvExtractor.FLV_TAG = Util.getIntegerCodeForString("FLV");
    }

    public FlvExtractor() {
        super();
        this.scratch = new ParsableByteArray(4);
        this.headerBuffer = new ParsableByteArray(9);
        this.tagHeaderBuffer = new ParsableByteArray(11);
        this.tagData = new ParsableByteArray();
        this.metadataReader = new ScriptTagPayloadReader();
        this.state = 1;
        this.mediaTagTimestampOffsetUs = -9223372036854775807L;
    }

    private void ensureReadyForMediaOutput() {
        long v1 = -9223372036854775807L;
        if(!this.outputSeekMap) {
            this.extractorOutput.seekMap(new Unseekable(v1));
            this.outputSeekMap = true;
        }

        if(this.mediaTagTimestampOffsetUs == v1) {
            long v0 = this.metadataReader.getDurationUs() == v1 ? -this.tagTimestampUs : 0;
            this.mediaTagTimestampOffsetUs = v0;
        }
    }

    public void init(ExtractorOutput arg1) {
        this.extractorOutput = arg1;
    }

    static Extractor[] lambda$static$0() {
        return new Extractor[]{new FlvExtractor()};
    }

    private ParsableByteArray prepareTagData(ExtractorInput arg5) {
        if(this.tagDataSize > this.tagData.capacity()) {
            this.tagData.reset(new byte[Math.max(this.tagData.capacity() * 2, this.tagDataSize)], 0);
        }
        else {
            this.tagData.setPosition(0);
        }

        this.tagData.setLimit(this.tagDataSize);
        arg5.readFully(this.tagData.data, 0, this.tagDataSize);
        return this.tagData;
    }

    public int read(ExtractorInput arg2, PositionHolder arg3) {
        int v0;
        do {
        label_0:
            v0 = -1;
            switch(this.state) {
                case 1: {
                    goto label_15;
                }
                case 2: {
                    goto label_13;
                }
                case 3: {
                    goto label_10;
                }
                case 4: {
                    goto label_6;
                }
            }

            throw new IllegalStateException();
        label_6:
            if(!this.readTagData(arg2)) {
                goto label_0;
            }

            return 0;
        label_10:
            if(this.readTagHeader(arg2)) {
                goto label_0;
            }

            return v0;
        label_13:
            this.skipToTagHeader(arg2);
            goto label_0;
        label_15:
        }
        while(this.readFlvHeader(arg2));

        return v0;
    }

    private boolean readFlvHeader(ExtractorInput arg7) {
        int v1 = 9;
        int v2 = 0;
        if(!arg7.readFully(this.headerBuffer.data, 0, v1, true)) {
            return 0;
        }

        this.headerBuffer.setPosition(0);
        int v0 = 4;
        this.headerBuffer.skipBytes(v0);
        int v7 = this.headerBuffer.readUnsignedByte();
        int v4 = (v7 & 4) != 0 ? 1 : 0;
        if((v7 & 1) != 0) {
            v2 = 1;
        }

        if(v4 != 0 && this.audioReader == null) {
            this.audioReader = new AudioTagPayloadReader(this.extractorOutput.track(8, 1));
        }

        v7 = 2;
        if(v2 != 0 && this.videoReader == null) {
            this.videoReader = new VideoTagPayloadReader(this.extractorOutput.track(v1, v7));
        }

        this.extractorOutput.endTracks();
        this.bytesToNextTagHeader = this.headerBuffer.readInt() - v1 + v0;
        this.state = v7;
        return 1;
    }

    private boolean readTagData(ExtractorInput arg7) {
        boolean v1 = true;
        if(this.tagType != 8 || this.audioReader == null) {
            if(this.tagType == 9 && this.videoReader != null) {
                this.ensureReadyForMediaOutput();
                this.videoReader.consume(this.prepareTagData(arg7), this.mediaTagTimestampOffsetUs + this.tagTimestampUs);
                goto label_49;
            }

            if(this.tagType == 18 && !this.outputSeekMap) {
                this.metadataReader.consume(this.prepareTagData(arg7), this.tagTimestampUs);
                long v2 = this.metadataReader.getDurationUs();
                if(v2 != -9223372036854775807L) {
                    this.extractorOutput.seekMap(new Unseekable(v2));
                    this.outputSeekMap = true;
                }
                else {
                }

                goto label_49;
            }

            arg7.skipFully(this.tagDataSize);
            v1 = false;
        }
        else {
            this.ensureReadyForMediaOutput();
            this.audioReader.consume(this.prepareTagData(arg7), this.mediaTagTimestampOffsetUs + this.tagTimestampUs);
        }

    label_49:
        this.bytesToNextTagHeader = 4;
        this.state = 2;
        return v1;
    }

    private boolean readTagHeader(ExtractorInput arg7) {
        if(!arg7.readFully(this.tagHeaderBuffer.data, 0, 11, true)) {
            return 0;
        }

        this.tagHeaderBuffer.setPosition(0);
        this.tagType = this.tagHeaderBuffer.readUnsignedByte();
        this.tagDataSize = this.tagHeaderBuffer.readUnsignedInt24();
        this.tagTimestampUs = ((long)this.tagHeaderBuffer.readUnsignedInt24());
        this.tagTimestampUs = ((((long)(this.tagHeaderBuffer.readUnsignedByte() << 24))) | this.tagTimestampUs) * 1000;
        this.tagHeaderBuffer.skipBytes(3);
        this.state = 4;
        return 1;
    }

    public void release() {
    }

    public void seek(long arg1, long arg3) {
        this.state = 1;
        this.mediaTagTimestampOffsetUs = -9223372036854775807L;
        this.bytesToNextTagHeader = 0;
    }

    private void skipToTagHeader(ExtractorInput arg2) {
        arg2.skipFully(this.bytesToNextTagHeader);
        this.bytesToNextTagHeader = 0;
        this.state = 3;
    }

    public boolean sniff(ExtractorInput arg4) {
        boolean v1 = false;
        arg4.peekFully(this.scratch.data, 0, 3);
        this.scratch.setPosition(0);
        if(this.scratch.readUnsignedInt24() != FlvExtractor.FLV_TAG) {
            return 0;
        }

        arg4.peekFully(this.scratch.data, 0, 2);
        this.scratch.setPosition(0);
        if((this.scratch.readUnsignedShort() & 250) != 0) {
            return 0;
        }

        arg4.peekFully(this.scratch.data, 0, 4);
        this.scratch.setPosition(0);
        int v0 = this.scratch.readInt();
        arg4.resetPeekPosition();
        arg4.advancePeekPosition(v0);
        arg4.peekFully(this.scratch.data, 0, 4);
        this.scratch.setPosition(0);
        if(this.scratch.readInt() == 0) {
            v1 = true;
        }

        return v1;
    }
}

