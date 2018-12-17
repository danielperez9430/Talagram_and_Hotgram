package com.google.android.exoplayer2.extractor.ts;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.text.cea.CeaUtil;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.ParsableByteArray;
import java.util.List;

final class UserDataReader {
    private static final int USER_DATA_START_CODE = 434;
    private final List closedCaptionFormats;
    private final TrackOutput[] outputs;

    public UserDataReader(List arg1) {
        super();
        this.closedCaptionFormats = arg1;
        this.outputs = new TrackOutput[arg1.size()];
    }

    public void consume(long arg5, ParsableByteArray arg7) {
        if(arg7.bytesLeft() < 9) {
            return;
        }

        int v0 = arg7.readInt();
        int v1 = arg7.readInt();
        int v2 = arg7.readUnsignedByte();
        if(v0 == 434 && v1 == CeaUtil.USER_DATA_IDENTIFIER_GA94 && v2 == 3) {
            CeaUtil.consumeCcData(arg5, arg7, this.outputs);
        }
    }

    public void createTracks(ExtractorOutput arg18, TrackIdGenerator arg19) {
        UserDataReader v0 = this;
        int v2;
        for(v2 = 0; v2 < v0.outputs.length; ++v2) {
            arg19.generateNewId();
            TrackOutput v3 = arg18.track(arg19.getTrackId(), 3);
            Object v4 = v0.closedCaptionFormats.get(v2);
            String v7 = ((Format)v4).sampleMimeType;
            boolean v6 = ("application/cea-608".equals(v7)) || ("application/cea-708".equals(v7)) ? true : false;
            Assertions.checkArgument(v6, "Invalid closed caption mime type provided: " + v7);
            v3.format(Format.createTextSampleFormat(arg19.getFormatId(), v7, null, -1, ((Format)v4).selectionFlags, ((Format)v4).language, ((Format)v4).accessibilityChannel, null, 9223372036854775807L, ((Format)v4).initializationData));
            v0.outputs[v2] = v3;
        }
    }
}

