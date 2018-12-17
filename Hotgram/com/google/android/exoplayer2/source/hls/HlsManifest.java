package com.google.android.exoplayer2.source.hls;

import com.google.android.exoplayer2.source.hls.playlist.HlsMasterPlaylist;
import com.google.android.exoplayer2.source.hls.playlist.HlsMediaPlaylist;

public final class HlsManifest {
    public final HlsMasterPlaylist masterPlaylist;
    public final HlsMediaPlaylist mediaPlaylist;

    HlsManifest(HlsMasterPlaylist arg1, HlsMediaPlaylist arg2) {
        super();
        this.masterPlaylist = arg1;
        this.mediaPlaylist = arg2;
    }
}

