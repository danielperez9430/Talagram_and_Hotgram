package com.google.android.exoplayer2.extractor.ts;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.audio.DtsUtil;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.util.ParsableByteArray;

public final class DtsReader implements ElementaryStreamReader {
    private static final int HEADER_SIZE = 18;
    private static final int STATE_FINDING_SYNC = 0;
    private static final int STATE_READING_HEADER = 1;
    private static final int STATE_READING_SAMPLE = 2;
    private int bytesRead;
    private Format format;
    private String formatId;
    private final ParsableByteArray headerScratchBytes;
    private final String language;
    private TrackOutput output;
    private long sampleDurationUs;
    private int sampleSize;
    private int state;
    private int syncBytes;
    private long timeUs;

    public DtsReader(String arg3) {
        super();
        this.headerScratchBytes = new ParsableByteArray(new byte[18]);
        this.state = 0;
        this.language = arg3;
    }

    public void consume(ParsableByteArray arg11) {
        while(arg11.bytesLeft() > 0) {
            switch(this.state) {
                case 0: {
                    goto label_45;
                }
                case 1: {
                    goto label_32;
                }
                case 2: {
                    goto label_6;
                }
            }

            continue;
        label_6:
            int v0 = Math.min(arg11.bytesLeft(), this.sampleSize - this.bytesRead);
            this.output.sampleData(arg11, v0);
            this.bytesRead += v0;
            if(this.bytesRead != this.sampleSize) {
                continue;
            }

            this.output.sampleMetadata(this.timeUs, 1, this.sampleSize, 0, null);
            this.timeUs += this.sampleDurationUs;
            this.state = 0;
            continue;
        label_45:
            if(!this.skipToNextSync(arg11)) {
                continue;
            }

            v0 = 1;
            goto label_48;
        label_32:
            int v2 = 18;
            if(!this.continueRead(arg11, this.headerScratchBytes.data, v2)) {
                continue;
            }

            this.parseHeader();
            this.headerScratchBytes.setPosition(0);
            this.output.sampleData(this.headerScratchBytes, v2);
            v0 = 2;
        label_48:
            this.state = v0;
        }
    }

    private boolean continueRead(ParsableByteArray arg3, byte[] arg4, int arg5) {
        int v0 = Math.min(arg3.bytesLeft(), arg5 - this.bytesRead);
        arg3.readBytes(arg4, this.bytesRead, v0);
        this.bytesRead += v0;
        boolean v3 = this.bytesRead == arg5 ? true : false;
        return v3;
    }

    public void createTracks(ExtractorOutput arg2, TrackIdGenerator arg3) {
        arg3.generateNewId();
        this.formatId = arg3.getFormatId();
        this.output = arg2.track(arg3.getTrackId(), 1);
    }

    public void packetFinished() {
    }

    public void packetStarted(long arg1, boolean arg3) {
        this.timeUs = arg1;
    }

    private void parseHeader() {
        byte[] v0 = this.headerScratchBytes.data;
        if(this.format == null) {
            this.format = DtsUtil.parseDtsFormat(v0, this.formatId, this.language, null);
            this.output.format(this.format);
        }

        this.sampleSize = DtsUtil.getDtsFrameSize(v0);
        this.sampleDurationUs = ((long)(((int)((((long)DtsUtil.parseDtsAudioSampleCount(v0))) * 1000000 / (((long)this.format.sampleRate))))));
    }

    public void seek() {
        this.state = 0;
        this.bytesRead = 0;
        this.syncBytes = 0;
    }

    private boolean skipToNextSync(ParsableByteArray arg5) {
        do {
            if(arg5.bytesLeft() <= 0) {
                return 0;
            }

            this.syncBytes <<= 8;
            this.syncBytes |= arg5.readUnsignedByte();
        }
        while(!DtsUtil.isSyncWord(this.syncBytes));

        this.headerScratchBytes.data[0] = ((byte)(this.syncBytes >> 24 & 255));
        this.headerScratchBytes.data[1] = ((byte)(this.syncBytes >> 16 & 255));
        this.headerScratchBytes.data[2] = ((byte)(this.syncBytes >> 8 & 255));
        this.headerScratchBytes.data[3] = ((byte)(this.syncBytes & 255));
        this.bytesRead = 4;
        this.syncBytes = 0;
        return 1;
    }
}

