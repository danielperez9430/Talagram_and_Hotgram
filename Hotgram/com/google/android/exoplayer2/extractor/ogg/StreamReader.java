package com.google.android.exoplayer2.extractor.ogg;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.PositionHolder;
import com.google.android.exoplayer2.extractor.SeekMap$Unseekable;
import com.google.android.exoplayer2.extractor.SeekMap;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.util.ParsableByteArray;

abstract class StreamReader {
    class com.google.android.exoplayer2.extractor.ogg.StreamReader$1 {
    }

    class SetupData {
        Format format;
        OggSeeker oggSeeker;

        SetupData() {
            super();
        }
    }

    final class UnseekableOggSeeker implements OggSeeker {
        UnseekableOggSeeker(com.google.android.exoplayer2.extractor.ogg.StreamReader$1 arg1) {
            this();
        }

        private UnseekableOggSeeker() {
            super();
        }

        public SeekMap createSeekMap() {
            return new Unseekable(-9223372036854775807L);
        }

        public long read(ExtractorInput arg3) {
            return -1;
        }

        public long startSeek(long arg1) {
            return 0;
        }
    }

    private static final int STATE_END_OF_INPUT = 3;
    private static final int STATE_READ_HEADERS = 0;
    private static final int STATE_READ_PAYLOAD = 2;
    private static final int STATE_SKIP_HEADERS = 1;
    private long currentGranule;
    private ExtractorOutput extractorOutput;
    private boolean formatSet;
    private long lengthOfReadPacket;
    private final OggPacket oggPacket;
    private OggSeeker oggSeeker;
    private long payloadStartPosition;
    private int sampleRate;
    private boolean seekMapSet;
    private SetupData setupData;
    private int state;
    private long targetGranule;
    private TrackOutput trackOutput;

    public StreamReader() {
        super();
        this.oggPacket = new OggPacket();
    }

    protected long convertGranuleToTime(long arg3) {
        return arg3 * 1000000 / (((long)this.sampleRate));
    }

    protected long convertTimeToGranule(long arg3) {
        return (((long)this.sampleRate)) * arg3 / 1000000;
    }

    void init(ExtractorOutput arg1, TrackOutput arg2) {
        this.extractorOutput = arg1;
        this.trackOutput = arg2;
        this.reset(true);
    }

    protected void onSeekEnd(long arg1) {
        this.currentGranule = arg1;
    }

    protected abstract long preparePayload(ParsableByteArray arg1);

    final int read(ExtractorInput arg3, PositionHolder arg4) {
        switch(this.state) {
            case 0: {
                goto label_14;
            }
            case 1: {
                goto label_7;
            }
            case 2: {
                goto label_5;
            }
        }

        throw new IllegalStateException();
    label_5:
        return this.readPayload(arg3, arg4);
    label_7:
        arg3.skipFully(((int)this.payloadStartPosition));
        this.state = 2;
        return 0;
    label_14:
        return this.readHeaders(arg3);
    }

    private int readHeaders(ExtractorInput arg13) {
        OggSeeker v13;
        boolean v1 = true;
        while(v1) {
            if(!this.oggPacket.populate(arg13)) {
                this.state = 3;
                return -1;
            }

            this.lengthOfReadPacket = arg13.getPosition() - this.payloadStartPosition;
            v1 = this.readHeaders(this.oggPacket.getPayload(), this.payloadStartPosition, this.setupData);
            if(!v1) {
                continue;
            }

            this.payloadStartPosition = arg13.getPosition();
        }

        this.sampleRate = this.setupData.format.sampleRate;
        if(!this.formatSet) {
            this.trackOutput.format(this.setupData.format);
            this.formatSet = true;
        }

        com.google.android.exoplayer2.extractor.ogg.StreamReader$1 v1_1 = null;
        if(this.setupData.oggSeeker != null) {
            v13 = this.setupData.oggSeeker;
            goto label_40;
        }
        else if(arg13.getLength() == -1) {
            UnseekableOggSeeker v13_1 = new UnseekableOggSeeker(v1_1);
        label_40:
            this.oggSeeker = v13;
        }
        else {
            OggPageHeader v0 = this.oggPacket.getPageHeader();
            this.oggSeeker = new DefaultOggSeeker(this.payloadStartPosition, arg13.getLength(), this, v0.headerSize + v0.bodySize, v0.granulePosition);
        }

        this.setupData = ((SetupData)v1_1);
        this.state = 2;
        this.oggPacket.trimPayload();
        return 0;
    }

    protected abstract boolean readHeaders(ParsableByteArray arg1, long arg2, SetupData arg3);

    private int readPayload(ExtractorInput arg18, PositionHolder arg19) {
        StreamReader v0 = this;
        ExtractorInput v1 = arg18;
        long v2 = v0.oggSeeker.read(v1);
        long v4 = 0;
        if(Long.compare(v2, v4) >= 0) {
            arg19.position = v2;
            return 1;
        }

        long v8 = -1;
        if(v2 < v8) {
            v0.onSeekEnd(-(v2 + 2));
        }

        if(!v0.seekMapSet) {
            v0.extractorOutput.seekMap(v0.oggSeeker.createSeekMap());
            v0.seekMapSet = true;
        }

        if(v0.lengthOfReadPacket <= v4) {
            if(v0.oggPacket.populate(v1)) {
            }
            else {
                v0.state = 3;
                return -1;
            }
        }

        v0.lengthOfReadPacket = v4;
        ParsableByteArray v1_1 = v0.oggPacket.getPayload();
        v2 = v0.preparePayload(v1_1);
        if(v2 >= v4 && v0.currentGranule + v2 >= v0.targetGranule) {
            long v11 = v0.convertGranuleToTime(v0.currentGranule);
            v0.trackOutput.sampleData(v1_1, v1_1.limit());
            v0.trackOutput.sampleMetadata(v11, 1, v1_1.limit(), 0, null);
            v0.targetGranule = v8;
        }

        v0.currentGranule += v2;
        return 0;
    }

    protected void reset(boolean arg5) {
        int v5;
        long v0 = 0;
        if(arg5) {
            this.setupData = new SetupData();
            this.payloadStartPosition = v0;
            v5 = 0;
        }
        else {
            v5 = 1;
        }

        this.state = v5;
        this.targetGranule = -1;
        this.currentGranule = v0;
    }

    final void seek(long arg4, long arg6) {
        this.oggPacket.reset();
        if(arg4 == 0) {
            this.reset(this.seekMapSet ^ 1);
        }
        else if(this.state != 0) {
            this.targetGranule = this.oggSeeker.startSeek(arg6);
            this.state = 2;
        }
    }
}

