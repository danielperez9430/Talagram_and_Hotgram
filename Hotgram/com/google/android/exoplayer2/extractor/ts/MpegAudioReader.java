package com.google.android.exoplayer2.extractor.ts;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.MpegAudioHeader;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.util.ParsableByteArray;

public final class MpegAudioReader implements ElementaryStreamReader {
    private static final int HEADER_SIZE = 4;
    private static final int STATE_FINDING_HEADER = 0;
    private static final int STATE_READING_FRAME = 2;
    private static final int STATE_READING_HEADER = 1;
    private String formatId;
    private int frameBytesRead;
    private long frameDurationUs;
    private int frameSize;
    private boolean hasOutputFormat;
    private final MpegAudioHeader header;
    private final ParsableByteArray headerScratch;
    private final String language;
    private boolean lastByteWasFF;
    private TrackOutput output;
    private int state;
    private long timeUs;

    public MpegAudioReader(String arg4) {
        super();
        this.state = 0;
        this.headerScratch = new ParsableByteArray(4);
        this.headerScratch.data[0] = -1;
        this.header = new MpegAudioHeader();
        this.language = arg4;
    }

    public MpegAudioReader() {
        this(null);
    }

    public void consume(ParsableByteArray arg2) {
        while(arg2.bytesLeft() > 0) {
            switch(this.state) {
                case 0: {
                    goto label_9;
                }
                case 1: {
                    goto label_7;
                }
                case 2: {
                    goto label_5;
                }
            }

            continue;
        label_5:
            this.readFrameRemainder(arg2);
            continue;
        label_7:
            this.readHeaderRemainder(arg2);
            continue;
        label_9:
            this.findHeader(arg2);
        }
    }

    public void createTracks(ExtractorOutput arg2, TrackIdGenerator arg3) {
        arg3.generateNewId();
        this.formatId = arg3.getFormatId();
        this.output = arg2.track(arg3.getTrackId(), 1);
    }

    private void findHeader(ParsableByteArray arg9) {
        byte[] v0 = arg9.data;
        int v1 = arg9.getPosition();
        int v2 = arg9.limit();
        while(v1 < v2) {
            boolean v3 = (v0[v1] & 255) == 255 ? true : false;
            int v4 = !this.lastByteWasFF || (v0[v1] & 224) != 224 ? 0 : 1;
            this.lastByteWasFF = v3;
            if(v4 != 0) {
                arg9.setPosition(v1 + 1);
                this.lastByteWasFF = false;
                this.headerScratch.data[1] = v0[v1];
                this.frameBytesRead = 2;
                this.state = 1;
                return;
            }

            ++v1;
        }

        arg9.setPosition(v2);
    }

    public void packetFinished() {
    }

    public void packetStarted(long arg1, boolean arg3) {
        this.timeUs = arg1;
    }

    private void readFrameRemainder(ParsableByteArray arg9) {
        int v0 = Math.min(arg9.bytesLeft(), this.frameSize - this.frameBytesRead);
        this.output.sampleData(arg9, v0);
        this.frameBytesRead += v0;
        if(this.frameBytesRead < this.frameSize) {
            return;
        }

        this.output.sampleMetadata(this.timeUs, 1, this.frameSize, 0, null);
        this.timeUs += this.frameDurationUs;
        this.frameBytesRead = 0;
        this.state = 0;
    }

    private void readHeaderRemainder(ParsableByteArray arg21) {
        MpegAudioReader v0 = this;
        int v3 = 4;
        int v1 = Math.min(arg21.bytesLeft(), 4 - v0.frameBytesRead);
        arg21.readBytes(v0.headerScratch.data, v0.frameBytesRead, v1);
        v0.frameBytesRead += v1;
        if(v0.frameBytesRead < v3) {
            return;
        }

        v0.headerScratch.setPosition(0);
        if(!MpegAudioHeader.populateHeader(v0.headerScratch.readInt(), v0.header)) {
            v0.frameBytesRead = 0;
            v0.state = 1;
            return;
        }

        v0.frameSize = v0.header.frameSize;
        if(!v0.hasOutputFormat) {
            v0.frameDurationUs = (((long)v0.header.samplesPerFrame)) * 1000000 / (((long)v0.header.sampleRate));
            v0.output.format(Format.createAudioSampleFormat(v0.formatId, v0.header.mimeType, null, -1, 4096, v0.header.channels, v0.header.sampleRate, null, null, 0, v0.language));
            v0.hasOutputFormat = true;
        }

        v0.headerScratch.setPosition(0);
        v0.output.sampleData(v0.headerScratch, v3);
        v0.state = 2;
    }

    public void seek() {
        this.state = 0;
        this.frameBytesRead = 0;
        this.lastByteWasFF = false;
    }
}

