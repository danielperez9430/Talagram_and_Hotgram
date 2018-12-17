package com.google.android.exoplayer2.source.hls.playlist;

import com.google.android.exoplayer2.source.chunk.BaseMediaChunkIterator;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.util.UriUtil;

public final class HlsMediaPlaylistSegmentIterator extends BaseMediaChunkIterator {
    private final HlsMediaPlaylist playlist;
    private final long startOfPlaylistInPeriodUs;

    public HlsMediaPlaylistSegmentIterator(HlsMediaPlaylist arg5, long arg6, int arg8) {
        super(((long)arg8), ((long)(arg5.segments.size() - 1)));
        this.playlist = arg5;
        this.startOfPlaylistInPeriodUs = arg6;
    }

    public long getChunkEndTimeUs() {
        this.checkInBounds();
        Object v0 = this.playlist.segments.get(((int)this.getCurrentIndex()));
        return this.startOfPlaylistInPeriodUs + ((Segment)v0).relativeStartTimeUs + ((Segment)v0).durationUs;
    }

    public long getChunkStartTimeUs() {
        this.checkInBounds();
        return this.startOfPlaylistInPeriodUs + this.playlist.segments.get(((int)this.getCurrentIndex())).relativeStartTimeUs;
    }

    public DataSpec getDataSpec() {
        this.checkInBounds();
        Object v0 = this.playlist.segments.get(((int)this.getCurrentIndex()));
        return new DataSpec(UriUtil.resolveToUri(this.playlist.baseUri, ((Segment)v0).url), ((Segment)v0).byterangeOffset, ((Segment)v0).byterangeLength, null);
    }
}

