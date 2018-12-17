package com.google.android.exoplayer2.extractor;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.util.ParsableByteArray;
import java.io.EOFException;

public final class DummyTrackOutput implements TrackOutput {
    public DummyTrackOutput() {
        super();
    }

    public void format(Format arg1) {
    }

    public int sampleData(ExtractorInput arg1, int arg2, boolean arg3) {
        int v1 = arg1.skip(arg2);
        arg2 = -1;
        if(v1 == arg2) {
            if(arg3) {
                return arg2;
            }

            throw new EOFException();
        }

        return v1;
    }

    public void sampleData(ParsableByteArray arg1, int arg2) {
        arg1.skipBytes(arg2);
    }

    public void sampleMetadata(long arg1, int arg3, int arg4, int arg5, CryptoData arg6) {
    }
}

