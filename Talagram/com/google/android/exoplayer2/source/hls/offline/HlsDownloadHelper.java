package com.google.android.exoplayer2.source.hls.offline;

import android.net.Uri;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.offline.DownloadAction;
import com.google.android.exoplayer2.offline.DownloadHelper;
import com.google.android.exoplayer2.offline.StreamKey;
import com.google.android.exoplayer2.offline.TrackKey;
import com.google.android.exoplayer2.source.TrackGroup;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.source.hls.playlist.HlsMasterPlaylist;
import com.google.android.exoplayer2.source.hls.playlist.HlsMediaPlaylist;
import com.google.android.exoplayer2.source.hls.playlist.HlsPlaylist;
import com.google.android.exoplayer2.source.hls.playlist.HlsPlaylistParser;
import com.google.android.exoplayer2.upstream.DataSource$Factory;
import com.google.android.exoplayer2.upstream.ParsingLoadable;
import com.google.android.exoplayer2.util.Assertions;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class HlsDownloadHelper extends DownloadHelper {
    private final Factory manifestDataSourceFactory;
    private HlsPlaylist playlist;
    private int[] renditionGroups;
    private final Uri uri;

    public HlsDownloadHelper(Uri arg1, Factory arg2) {
        super();
        this.uri = arg1;
        this.manifestDataSourceFactory = arg2;
    }

    public DownloadAction getDownloadAction(byte[] arg1, List arg2) {
        return this.getDownloadAction(arg1, arg2);
    }

    public HlsDownloadAction getDownloadAction(byte[] arg3, List arg4) {
        Assertions.checkNotNull(this.renditionGroups);
        return HlsDownloadAction.createDownloadAction(this.uri, arg3, HlsDownloadHelper.toStreamKeys(arg4, this.renditionGroups));
    }

    public int getPeriodCount() {
        Assertions.checkNotNull(this.playlist);
        return 1;
    }

    public HlsPlaylist getPlaylist() {
        Assertions.checkNotNull(this.playlist);
        return this.playlist;
    }

    public DownloadAction getRemoveAction(byte[] arg1) {
        return this.getRemoveAction(arg1);
    }

    public HlsDownloadAction getRemoveAction(byte[] arg2) {
        return HlsDownloadAction.createRemoveAction(this.uri, arg2);
    }

    public TrackGroupArray getTrackGroups(int arg6) {
        Assertions.checkNotNull(this.playlist);
        int v0 = 0;
        if((this.playlist instanceof HlsMediaPlaylist)) {
            this.renditionGroups = new int[0];
            return TrackGroupArray.EMPTY;
        }

        HlsPlaylist v6 = this.playlist;
        TrackGroup[] v2 = new TrackGroup[3];
        this.renditionGroups = new int[3];
        if(!((HlsMasterPlaylist)v6).variants.isEmpty()) {
            this.renditionGroups[0] = 0;
            v2[0] = new TrackGroup(HlsDownloadHelper.toFormats(((HlsMasterPlaylist)v6).variants));
            v0 = 1;
        }

        if(!((HlsMasterPlaylist)v6).audios.isEmpty()) {
            this.renditionGroups[v0] = 1;
            v2[v0] = new TrackGroup(HlsDownloadHelper.toFormats(((HlsMasterPlaylist)v6).audios));
            ++v0;
        }

        if(!((HlsMasterPlaylist)v6).subtitles.isEmpty()) {
            this.renditionGroups[v0] = 2;
            v2[v0] = new TrackGroup(HlsDownloadHelper.toFormats(((HlsMasterPlaylist)v6).subtitles));
            ++v0;
        }

        return new TrackGroupArray(Arrays.copyOf(((Object[])v2), v0));
    }

    protected void prepareInternal() {
        this.playlist = ParsingLoadable.load(this.manifestDataSourceFactory.createDataSource(), new HlsPlaylistParser(), this.uri, 4);
    }

    private static Format[] toFormats(List arg3) {
        Format[] v0 = new Format[arg3.size()];
        int v1;
        for(v1 = 0; v1 < arg3.size(); ++v1) {
            v0[v1] = arg3.get(v1).format;
        }

        return v0;
    }

    private static List toStreamKeys(List arg5, int[] arg6) {
        ArrayList v0 = new ArrayList(arg5.size());
        int v1;
        for(v1 = 0; v1 < arg5.size(); ++v1) {
            Object v2 = arg5.get(v1);
            ((List)v0).add(new StreamKey(arg6[((TrackKey)v2).groupIndex], ((TrackKey)v2).trackIndex));
        }

        return ((List)v0);
    }
}

