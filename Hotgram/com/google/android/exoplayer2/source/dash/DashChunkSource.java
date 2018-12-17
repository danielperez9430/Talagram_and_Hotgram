package com.google.android.exoplayer2.source.dash;

import com.google.android.exoplayer2.source.chunk.ChunkSource;
import com.google.android.exoplayer2.source.dash.manifest.DashManifest;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.upstream.LoaderErrorThrower;
import com.google.android.exoplayer2.upstream.TransferListener;

public interface DashChunkSource extends ChunkSource {
    public interface Factory {
        DashChunkSource createDashChunkSource(LoaderErrorThrower arg1, DashManifest arg2, int arg3, int[] arg4, TrackSelection arg5, int arg6, long arg7, boolean arg8, boolean arg9, PlayerTrackEmsgHandler arg10, TransferListener arg11);
    }

    void updateManifest(DashManifest arg1, int arg2);
}

