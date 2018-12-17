package com.google.android.exoplayer2.source.smoothstreaming.offline;

import android.net.Uri;
import com.google.android.exoplayer2.offline.DownloadAction;
import com.google.android.exoplayer2.offline.DownloadHelper;
import com.google.android.exoplayer2.offline.StreamKey;
import com.google.android.exoplayer2.offline.TrackKey;
import com.google.android.exoplayer2.source.TrackGroup;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.source.smoothstreaming.manifest.SsManifest$StreamElement;
import com.google.android.exoplayer2.source.smoothstreaming.manifest.SsManifest;
import com.google.android.exoplayer2.source.smoothstreaming.manifest.SsManifestParser;
import com.google.android.exoplayer2.upstream.DataSource$Factory;
import com.google.android.exoplayer2.upstream.ParsingLoadable;
import com.google.android.exoplayer2.util.Assertions;
import java.util.ArrayList;
import java.util.List;

public final class SsDownloadHelper extends DownloadHelper {
    private SsManifest manifest;
    private final Factory manifestDataSourceFactory;
    private final Uri uri;

    public SsDownloadHelper(Uri arg1, Factory arg2) {
        super();
        this.uri = arg1;
        this.manifestDataSourceFactory = arg2;
    }

    public DownloadAction getDownloadAction(byte[] arg1, List arg2) {
        return this.getDownloadAction(arg1, arg2);
    }

    public SsDownloadAction getDownloadAction(byte[] arg2, List arg3) {
        return SsDownloadAction.createDownloadAction(this.uri, arg2, SsDownloadHelper.toStreamKeys(arg3));
    }

    public SsManifest getManifest() {
        Assertions.checkNotNull(this.manifest);
        return this.manifest;
    }

    public int getPeriodCount() {
        Assertions.checkNotNull(this.manifest);
        return 1;
    }

    public DownloadAction getRemoveAction(byte[] arg1) {
        return this.getRemoveAction(arg1);
    }

    public SsDownloadAction getRemoveAction(byte[] arg2) {
        return SsDownloadAction.createRemoveAction(this.uri, arg2);
    }

    public TrackGroupArray getTrackGroups(int arg5) {
        Assertions.checkNotNull(this.manifest);
        StreamElement[] v5 = this.manifest.streamElements;
        TrackGroup[] v0 = new TrackGroup[v5.length];
        int v1;
        for(v1 = 0; v1 < v5.length; ++v1) {
            v0[v1] = new TrackGroup(v5[v1].formats);
        }

        return new TrackGroupArray(v0);
    }

    protected void prepareInternal() {
        this.manifest = ParsingLoadable.load(this.manifestDataSourceFactory.createDataSource(), new SsManifestParser(), this.uri, 4);
    }

    private static List toStreamKeys(List arg5) {
        ArrayList v0 = new ArrayList(arg5.size());
        int v1;
        for(v1 = 0; v1 < arg5.size(); ++v1) {
            Object v2 = arg5.get(v1);
            ((List)v0).add(new StreamKey(((TrackKey)v2).groupIndex, ((TrackKey)v2).trackIndex));
        }

        return ((List)v0);
    }
}

