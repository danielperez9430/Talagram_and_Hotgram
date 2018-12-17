package com.google.android.exoplayer2.source.smoothstreaming.offline;

import android.net.Uri;
import com.google.android.exoplayer2.offline.DownloaderConstructorHelper;
import com.google.android.exoplayer2.offline.FilterableManifest;
import com.google.android.exoplayer2.offline.SegmentDownloader$Segment;
import com.google.android.exoplayer2.offline.SegmentDownloader;
import com.google.android.exoplayer2.source.smoothstreaming.manifest.SsManifest$StreamElement;
import com.google.android.exoplayer2.source.smoothstreaming.manifest.SsManifest;
import com.google.android.exoplayer2.source.smoothstreaming.manifest.SsManifestParser;
import com.google.android.exoplayer2.source.smoothstreaming.manifest.SsUtil;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.upstream.ParsingLoadable;
import java.util.ArrayList;
import java.util.List;

public final class SsDownloader extends SegmentDownloader {
    public SsDownloader(Uri arg1, List arg2, DownloaderConstructorHelper arg3) {
        super(SsUtil.fixManifestUri(arg1), arg2, arg3);
    }

    protected FilterableManifest getManifest(DataSource arg1, Uri arg2) {
        return this.getManifest(arg1, arg2);
    }

    protected SsManifest getManifest(DataSource arg3, Uri arg4) {
        return ParsingLoadable.load(arg3, new SsManifestParser(), arg4, 4);
    }

    protected List getSegments(DataSource arg1, FilterableManifest arg2, boolean arg3) {
        return this.getSegments(arg1, ((SsManifest)arg2), arg3);
    }

    protected List getSegments(DataSource arg11, SsManifest arg12, boolean arg13) {
        ArrayList v11 = new ArrayList();
        StreamElement[] v12 = arg12.streamElements;
        int v13 = v12.length;
        int v1;
        for(v1 = 0; v1 < v13; ++v1) {
            StreamElement v2 = v12[v1];
            int v3;
            for(v3 = 0; v3 < v2.formats.length; ++v3) {
                int v4;
                for(v4 = 0; v4 < v2.chunkCount; ++v4) {
                    v11.add(new Segment(v2.getStartTimeUs(v4), new DataSpec(v2.buildRequestUri(v3, v4))));
                }
            }
        }

        return ((List)v11);
    }
}

