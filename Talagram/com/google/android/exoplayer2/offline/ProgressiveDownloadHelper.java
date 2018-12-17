package com.google.android.exoplayer2.offline;

import android.net.Uri;
import com.google.android.exoplayer2.source.TrackGroupArray;
import java.util.List;

public final class ProgressiveDownloadHelper extends DownloadHelper {
    private final String customCacheKey;
    private final Uri uri;

    public ProgressiveDownloadHelper(Uri arg2) {
        this(arg2, null);
    }

    public ProgressiveDownloadHelper(Uri arg1, String arg2) {
        super();
        this.uri = arg1;
        this.customCacheKey = arg2;
    }

    public DownloadAction getDownloadAction(byte[] arg1, List arg2) {
        return this.getDownloadAction(arg1, arg2);
    }

    public ProgressiveDownloadAction getDownloadAction(byte[] arg2, List arg3) {
        return ProgressiveDownloadAction.createDownloadAction(this.uri, arg2, this.customCacheKey);
    }

    public int getPeriodCount() {
        return 1;
    }

    public DownloadAction getRemoveAction(byte[] arg1) {
        return this.getRemoveAction(arg1);
    }

    public ProgressiveDownloadAction getRemoveAction(byte[] arg3) {
        return ProgressiveDownloadAction.createRemoveAction(this.uri, arg3, this.customCacheKey);
    }

    public TrackGroupArray getTrackGroups(int arg1) {
        return TrackGroupArray.EMPTY;
    }

    protected void prepareInternal() {
    }
}

