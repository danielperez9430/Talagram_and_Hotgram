package com.google.android.exoplayer2.extractor.ts;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.audio.Ac3Util$SyncFrameInfo;
import com.google.android.exoplayer2.audio.Ac3Util;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.util.ParsableBitArray;
import com.google.android.exoplayer2.util.ParsableByteArray;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class Ac3Reader implements ElementaryStreamReader {
    @Retention(value=RetentionPolicy.SOURCE) @interface State {
    }

    private static final int HEADER_SIZE = 128;
    private static final int STATE_FINDING_SYNC = 0;
    private static final int STATE_READING_HEADER = 1;
    private static final int STATE_READING_SAMPLE = 2;
    private int bytesRead;
    private Format format;
    private final ParsableBitArray headerScratchBits;
    private final ParsableByteArray headerScratchBytes;
    private final String language;
    private boolean lastByteWas0B;
    private TrackOutput output;
    private long sampleDurationUs;
    private int sampleSize;
    private int state;
    private long timeUs;
    private String trackFormatId;

    public Ac3Reader() {
        this(null);
    }

    public Ac3Reader(String arg3) {
        super();
        this.headerScratchBits = new ParsableBitArray(new byte[128]);
        this.headerScratchBytes = new ParsableByteArray(this.headerScratchBits.data);
        this.state = 0;
        this.language = arg3;
    }

    public void consume(ParsableByteArray arg11) {
        while(arg11.bytesLeft() > 0) {
            int v1 = 2;
            switch(this.state) {
                case 0: {
                    goto label_46;
                }
                case 1: {
                    goto label_33;
                }
                case 2: {
                    goto label_7;
                }
            }

            continue;
        label_33:
            int v3 = 128;
            if(!this.continueRead(arg11, this.headerScratchBytes.data, v3)) {
                continue;
            }

            this.parseHeader();
            this.headerScratchBytes.setPosition(0);
            this.output.sampleData(this.headerScratchBytes, v3);
            this.state = v1;
            continue;
        label_7:
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
        label_46:
            if(!this.skipToNextSync(arg11)) {
                continue;
            }

            this.state = 1;
            this.headerScratchBytes.data[0] = 11;
            this.headerScratchBytes.data[1] = 119;
            this.bytesRead = v1;
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
        this.trackFormatId = arg3.getFormatId();
        this.output = arg2.track(arg3.getTrackId(), 1);
    }

    public void packetFinished() {
    }

    public void packetStarted(long arg1, boolean arg3) {
        this.timeUs = arg1;
    }

    private void parseHeader() {
        this.headerScratchBits.setPosition(0);
        SyncFrameInfo v0 = Ac3Util.parseAc3SyncframeInfo(this.headerScratchBits);
        if(this.format == null || v0.channelCount != this.format.channelCount || v0.sampleRate != this.format.sampleRate || v0.mimeType != this.format.sampleMimeType) {
            this.format = Format.createAudioSampleFormat(this.trackFormatId, v0.mimeType, null, -1, -1, v0.channelCount, v0.sampleRate, null, null, 0, this.language);
            this.output.format(this.format);
        }

        this.sampleSize = v0.frameSize;
        this.sampleDurationUs = (((long)v0.sampleCount)) * 1000000 / (((long)this.format.sampleRate));
    }

    public void seek() {
        this.state = 0;
        this.bytesRead = 0;
        this.lastByteWas0B = false;
    }

    private boolean skipToNextSync(ParsableByteArray arg6) {
        while(true) {
            boolean v1 = false;
            if(arg6.bytesLeft() <= 0) {
                return 0;
            }

            int v2 = 11;
            if(!this.lastByteWas0B) {
                if(arg6.readUnsignedByte() != v2) {
                    goto label_10;
                }

                goto label_9;
            }
            else {
                int v0 = arg6.readUnsignedByte();
                if(v0 == 119) {
                    this.lastByteWas0B = false;
                    return 1;
                }
                else if(v0 == v2) {
                label_9:
                    v1 = true;
                }
            }

        label_10:
            this.lastByteWas0B = v1;
        }

        return 0;
    }
}

