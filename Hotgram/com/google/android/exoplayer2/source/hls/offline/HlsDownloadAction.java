package com.google.android.exoplayer2.source.hls.offline;

import android.net.Uri;
import com.google.android.exoplayer2.offline.DownloadAction$Deserializer;
import com.google.android.exoplayer2.offline.DownloadAction;
import com.google.android.exoplayer2.offline.Downloader;
import com.google.android.exoplayer2.offline.DownloaderConstructorHelper;
import com.google.android.exoplayer2.offline.SegmentDownloadAction$SegmentDownloadActionDeserializer;
import com.google.android.exoplayer2.offline.SegmentDownloadAction;
import com.google.android.exoplayer2.offline.StreamKey;
import java.io.DataInputStream;
import java.util.Collections;
import java.util.List;

public final class HlsDownloadAction extends SegmentDownloadAction {
    final class com.google.android.exoplayer2.source.hls.offline.HlsDownloadAction$1 extends SegmentDownloadActionDeserializer {
        com.google.android.exoplayer2.source.hls.offline.HlsDownloadAction$1(String arg1, int arg2) {
            super(arg1, arg2);
        }

        protected DownloadAction createDownloadAction(Uri arg2, boolean arg3, byte[] arg4, List arg5) {
            return new HlsDownloadAction(arg2, arg3, arg4, arg5);
        }

        protected StreamKey readKey(int arg2, DataInputStream arg3) {
            if(arg2 > 0) {
                return super.readKey(arg2, arg3);
            }

            return new StreamKey(arg3.readInt(), arg3.readInt());
        }
    }

    public static final Deserializer DESERIALIZER = null;
    private static final String TYPE = "hls";
    private static final int VERSION = 1;

    static {
        HlsDownloadAction.DESERIALIZER = new com.google.android.exoplayer2.source.hls.offline.HlsDownloadAction$1("hls", 1);
    }

    @Deprecated public HlsDownloadAction(Uri arg8, boolean arg9, byte[] arg10, List arg11) {
        super("hls", 1, arg8, arg9, arg10, arg11);
    }

    public static HlsDownloadAction createDownloadAction(Uri arg2, byte[] arg3, List arg4) {
        return new HlsDownloadAction(arg2, false, arg3, arg4);
    }

    public Downloader createDownloader(DownloaderConstructorHelper arg1) {
        return this.createDownloader(arg1);
    }

    public HlsDownloader createDownloader(DownloaderConstructorHelper arg4) {
        return new HlsDownloader(this.uri, this.keys, arg4);
    }

    public static HlsDownloadAction createRemoveAction(Uri arg3, byte[] arg4) {
        return new HlsDownloadAction(arg3, true, arg4, Collections.emptyList());
    }
}

