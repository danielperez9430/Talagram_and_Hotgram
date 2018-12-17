package com.google.android.exoplayer2.extractor.ts;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.text.cea.CeaUtil;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.ParsableByteArray;
import java.util.List;

final class SeiReader {
    private final List closedCaptionFormats;
    private final TrackOutput[] outputs;

    public SeiReader(List arg1) {
        super();
        this.closedCaptionFormats = arg1;
        this.outputs = new TrackOutput[arg1.size()];
    }

    public void consume(long arg2, ParsableByteArray arg4) {
        CeaUtil.consume(arg2, arg4, this.outputs);
    }

    public void createTracks(ExtractorOutput arg18, TrackIdGenerator arg19) {
        SeiReader v0 = this;
        int v2;
        for(v2 = 0; v2 < v0.outputs.length; ++v2) {
            arg19.generateNewId();
            TrackOutput v3 = arg18.track(arg19.getTrackId(), 3);
            Object v4 = v0.closedCaptionFormats.get(v2);
            String v7 = ((Format)v4).sampleMimeType;
            boolean v6 = ("application/cea-608".equals(v7)) || ("application/cea-708".equals(v7)) ? true : false;
            Assertions.checkArgument(v6, "Invalid closed caption mime type provided: " + v7);
            String v6_1 = ((Format)v4).id != null ? ((Format)v4).id : arg19.getFormatId();
            v3.format(Format.createTextSampleFormat(v6_1, v7, null, -1, ((Format)v4).selectionFlags, ((Format)v4).language, ((Format)v4).accessibilityChannel, null, 9223372036854775807L, ((Format)v4).initializationData));
            v0.outputs[v2] = v3;
        }
    }
}

