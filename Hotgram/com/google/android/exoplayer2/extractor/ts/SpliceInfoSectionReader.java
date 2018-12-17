package com.google.android.exoplayer2.extractor.ts;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.TimestampAdjuster;

public final class SpliceInfoSectionReader implements SectionPayloadReader {
    private boolean formatDeclared;
    private TrackOutput output;
    private TimestampAdjuster timestampAdjuster;

    public SpliceInfoSectionReader() {
        super();
    }

    public void consume(ParsableByteArray arg9) {
        if(!this.formatDeclared) {
            if(this.timestampAdjuster.getTimestampOffsetUs() == -9223372036854775807L) {
                return;
            }
            else {
                this.output.format(Format.createSampleFormat(null, "application/x-scte35", this.timestampAdjuster.getTimestampOffsetUs()));
                this.formatDeclared = true;
            }
        }

        int v5 = arg9.bytesLeft();
        this.output.sampleData(arg9, v5);
        this.output.sampleMetadata(this.timestampAdjuster.getLastAdjustedTimestampUs(), 1, v5, 0, null);
    }

    public void init(TimestampAdjuster arg3, ExtractorOutput arg4, TrackIdGenerator arg5) {
        this.timestampAdjuster = arg3;
        arg5.generateNewId();
        this.output = arg4.track(arg5.getTrackId(), 4);
        this.output.format(Format.createSampleFormat(arg5.getFormatId(), "application/x-scte35", null, -1, null));
    }
}

