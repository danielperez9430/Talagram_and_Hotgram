package com.google.android.exoplayer2.extractor;

public interface ExtractorOutput {
    void endTracks();

    void seekMap(SeekMap arg1);

    TrackOutput track(int arg1, int arg2);
}

