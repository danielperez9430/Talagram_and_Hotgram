package com.google.android.exoplayer2.source.chunk;

import android.net.Uri;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.upstream.Loader$Loadable;
import com.google.android.exoplayer2.upstream.StatsDataSource;
import com.google.android.exoplayer2.util.Assertions;
import java.util.Map;

public abstract class Chunk implements Loadable {
    protected final StatsDataSource dataSource;
    public final DataSpec dataSpec;
    public final long endTimeUs;
    public final long startTimeUs;
    public final Format trackFormat;
    public final Object trackSelectionData;
    public final int trackSelectionReason;
    public final int type;

    public Chunk(DataSource arg2, DataSpec arg3, int arg4, Format arg5, int arg6, Object arg7, long arg8, long arg10) {
        super();
        this.dataSource = new StatsDataSource(arg2);
        this.dataSpec = Assertions.checkNotNull(arg3);
        this.type = arg4;
        this.trackFormat = arg5;
        this.trackSelectionReason = arg6;
        this.trackSelectionData = arg7;
        this.startTimeUs = arg8;
        this.endTimeUs = arg10;
    }

    public final long bytesLoaded() {
        return this.dataSource.getBytesRead();
    }

    public final long getDurationUs() {
        return this.endTimeUs - this.startTimeUs;
    }

    public final Map getResponseHeaders() {
        return this.dataSource.getLastResponseHeaders();
    }

    public final Uri getUri() {
        return this.dataSource.getLastOpenedUri();
    }
}

