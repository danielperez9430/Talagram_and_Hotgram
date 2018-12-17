package com.google.android.exoplayer2.extractor.ts;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.util.ParsableByteArray;
import java.util.Collections;
import java.util.List;

public final class DvbSubtitleReader implements ElementaryStreamReader {
    private int bytesToCheck;
    private final TrackOutput[] outputs;
    private int sampleBytesWritten;
    private long sampleTimeUs;
    private final List subtitleInfos;
    private boolean writingSample;

    public DvbSubtitleReader(List arg1) {
        super();
        this.subtitleInfos = arg1;
        this.outputs = new TrackOutput[arg1.size()];
    }

    private boolean checkNextByte(ParsableByteArray arg3, int arg4) {
        if(arg3.bytesLeft() == 0) {
            return 0;
        }

        if(arg3.readUnsignedByte() != arg4) {
            this.writingSample = false;
        }

        --this.bytesToCheck;
        return this.writingSample;
    }

    public void consume(ParsableByteArray arg7) {
        if(this.writingSample) {
            if(this.bytesToCheck == 2 && !this.checkNextByte(arg7, 32)) {
                return;
            }

            int v1 = 0;
            if(this.bytesToCheck == 1 && !this.checkNextByte(arg7, 0)) {
                return;
            }

            int v0 = arg7.getPosition();
            int v2 = arg7.bytesLeft();
            TrackOutput[] v3 = this.outputs;
            int v4 = v3.length;
            while(v1 < v4) {
                TrackOutput v5 = v3[v1];
                arg7.setPosition(v0);
                v5.sampleData(arg7, v2);
                ++v1;
            }

            this.sampleBytesWritten += v2;
        }
    }

    public void createTracks(ExtractorOutput arg12, TrackIdGenerator arg13) {
        int v0;
        for(v0 = 0; v0 < this.outputs.length; ++v0) {
            Object v1 = this.subtitleInfos.get(v0);
            arg13.generateNewId();
            TrackOutput v2 = arg12.track(arg13.getTrackId(), 3);
            v2.format(Format.createImageSampleFormat(arg13.getFormatId(), "application/dvbsubs", null, -1, 0, Collections.singletonList(((DvbSubtitleInfo)v1).initializationData), ((DvbSubtitleInfo)v1).language, null));
            this.outputs[v0] = v2;
        }
    }

    public void packetFinished() {
        if(this.writingSample) {
            TrackOutput[] v0 = this.outputs;
            int v1 = v0.length;
            int v3;
            for(v3 = 0; v3 < v1; ++v3) {
                v0[v3].sampleMetadata(this.sampleTimeUs, 1, this.sampleBytesWritten, 0, null);
            }

            this.writingSample = false;
        }
    }

    public void packetStarted(long arg1, boolean arg3) {
        if(!arg3) {
            return;
        }

        this.writingSample = true;
        this.sampleTimeUs = arg1;
        this.sampleBytesWritten = 0;
        this.bytesToCheck = 2;
    }

    public void seek() {
        this.writingSample = false;
    }
}

