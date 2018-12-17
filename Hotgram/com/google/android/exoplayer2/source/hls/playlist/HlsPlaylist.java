package com.google.android.exoplayer2.source.hls.playlist;

import com.google.android.exoplayer2.offline.FilterableManifest;
import java.util.Collections;
import java.util.List;

public abstract class HlsPlaylist implements FilterableManifest {
    public final String baseUri;
    public final boolean hasIndependentSegments;
    public final List tags;

    protected HlsPlaylist(String arg1, List arg2, boolean arg3) {
        super();
        this.baseUri = arg1;
        this.tags = Collections.unmodifiableList(arg2);
        this.hasIndependentSegments = arg3;
    }
}

