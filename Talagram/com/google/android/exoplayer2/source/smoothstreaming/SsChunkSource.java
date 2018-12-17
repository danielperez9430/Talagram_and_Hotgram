package com.google.android.exoplayer2.source.smoothstreaming;

import com.google.android.exoplayer2.extractor.mp4.TrackEncryptionBox;
import com.google.android.exoplayer2.source.chunk.ChunkSource;
import com.google.android.exoplayer2.source.smoothstreaming.manifest.SsManifest;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.upstream.LoaderErrorThrower;
import com.google.android.exoplayer2.upstream.TransferListener;

public interface SsChunkSource extends ChunkSource {
    public interface Factory {
        SsChunkSource createChunkSource(LoaderErrorThrower arg1, SsManifest arg2, int arg3, TrackSelection arg4, TrackEncryptionBox[] arg5, TransferListener arg6);
    }

    void updateManifest(SsManifest arg1);
}

