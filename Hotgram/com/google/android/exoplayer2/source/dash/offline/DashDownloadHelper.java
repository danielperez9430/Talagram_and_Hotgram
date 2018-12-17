package com.google.android.exoplayer2.source.dash.offline;

import android.net.Uri;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.offline.DownloadAction;
import com.google.android.exoplayer2.offline.DownloadHelper;
import com.google.android.exoplayer2.offline.StreamKey;
import com.google.android.exoplayer2.offline.TrackKey;
import com.google.android.exoplayer2.source.TrackGroup;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.source.dash.manifest.DashManifest;
import com.google.android.exoplayer2.source.dash.manifest.DashManifestParser;
import com.google.android.exoplayer2.upstream.DataSource$Factory;
import com.google.android.exoplayer2.upstream.ParsingLoadable;
import com.google.android.exoplayer2.util.Assertions;
import java.util.ArrayList;
import java.util.List;

public final class DashDownloadHelper extends DownloadHelper {
    private DashManifest manifest;
    private final Factory manifestDataSourceFactory;
    private final Uri uri;

    public DashDownloadHelper(Uri arg1, Factory arg2) {
        super();
        this.uri = arg1;
        this.manifestDataSourceFactory = arg2;
    }

    public DownloadAction getDownloadAction(byte[] arg1, List arg2) {
        return this.getDownloadAction(arg1, arg2);
    }

    public DashDownloadAction getDownloadAction(byte[] arg2, List arg3) {
        return DashDownloadAction.createDownloadAction(this.uri, arg2, DashDownloadHelper.toStreamKeys(arg3));
    }

    public DashManifest getManifest() {
        Assertions.checkNotNull(this.manifest);
        return this.manifest;
    }

    public int getPeriodCount() {
        Assertions.checkNotNull(this.manifest);
        return this.manifest.getPeriodCount();
    }

    public DownloadAction getRemoveAction(byte[] arg1) {
        return this.getRemoveAction(arg1);
    }

    public DashDownloadAction getRemoveAction(byte[] arg2) {
        return DashDownloadAction.createRemoveAction(this.uri, arg2);
    }

    public TrackGroupArray getTrackGroups(int arg9) {
        Assertions.checkNotNull(this.manifest);
        List v9 = this.manifest.getPeriod(arg9).adaptationSets;
        TrackGroup[] v0 = new TrackGroup[v9.size()];
        int v2;
        for(v2 = 0; v2 < v0.length; ++v2) {
            List v3 = v9.get(v2).representations;
            Format[] v4 = new Format[v3.size()];
            int v5 = v3.size();
            int v6;
            for(v6 = 0; v6 < v5; ++v6) {
                v4[v6] = v3.get(v6).format;
            }

            v0[v2] = new TrackGroup(v4);
        }

        return new TrackGroupArray(v0);
    }

    protected void prepareInternal() {
        this.manifest = ParsingLoadable.load(this.manifestDataSourceFactory.createDataSource(), new DashManifestParser(), this.uri, 4);
    }

    private static List toStreamKeys(List arg6) {
        ArrayList v0 = new ArrayList(arg6.size());
        int v1;
        for(v1 = 0; v1 < arg6.size(); ++v1) {
            Object v2 = arg6.get(v1);
            ((List)v0).add(new StreamKey(((TrackKey)v2).periodIndex, ((TrackKey)v2).groupIndex, ((TrackKey)v2).trackIndex));
        }

        return ((List)v0);
    }
}

