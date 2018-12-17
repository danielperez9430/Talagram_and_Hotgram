package com.google.android.exoplayer2.source.dash.offline;

import android.net.Uri;
import com.google.android.exoplayer2.offline.DownloadAction$Deserializer;
import com.google.android.exoplayer2.offline.DownloadAction;
import com.google.android.exoplayer2.offline.Downloader;
import com.google.android.exoplayer2.offline.DownloaderConstructorHelper;
import com.google.android.exoplayer2.offline.SegmentDownloadAction$SegmentDownloadActionDeserializer;
import com.google.android.exoplayer2.offline.SegmentDownloadAction;
import java.util.Collections;
import java.util.List;

public final class DashDownloadAction extends SegmentDownloadAction {
    final class com.google.android.exoplayer2.source.dash.offline.DashDownloadAction$1 extends SegmentDownloadActionDeserializer {
        com.google.android.exoplayer2.source.dash.offline.DashDownloadAction$1(String arg1, int arg2) {
            super(arg1, arg2);
        }

        protected DownloadAction createDownloadAction(Uri arg2, boolean arg3, byte[] arg4, List arg5) {
            return new DashDownloadAction(arg2, arg3, arg4, arg5);
        }
    }

    public static final Deserializer DESERIALIZER = null;
    private static final String TYPE = "dash";
    private static final int VERSION;

    static {
        DashDownloadAction.DESERIALIZER = new com.google.android.exoplayer2.source.dash.offline.DashDownloadAction$1("dash", 0);
    }

    @Deprecated public DashDownloadAction(Uri arg8, boolean arg9, byte[] arg10, List arg11) {
        super("dash", 0, arg8, arg9, arg10, arg11);
    }

    public static DashDownloadAction createDownloadAction(Uri arg2, byte[] arg3, List arg4) {
        return new DashDownloadAction(arg2, false, arg3, arg4);
    }

    public Downloader createDownloader(DownloaderConstructorHelper arg1) {
        return this.createDownloader(arg1);
    }

    public DashDownloader createDownloader(DownloaderConstructorHelper arg4) {
        return new DashDownloader(this.uri, this.keys, arg4);
    }

    public static DashDownloadAction createRemoveAction(Uri arg3, byte[] arg4) {
        return new DashDownloadAction(arg3, true, arg4, Collections.emptyList());
    }
}

