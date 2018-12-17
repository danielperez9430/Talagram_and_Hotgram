package com.google.android.exoplayer2.source.hls.offline;

import android.net.Uri;
import com.google.android.exoplayer2.offline.DownloaderConstructorHelper;
import com.google.android.exoplayer2.offline.FilterableManifest;
import com.google.android.exoplayer2.offline.SegmentDownloader;
import com.google.android.exoplayer2.source.hls.playlist.HlsMasterPlaylist;
import com.google.android.exoplayer2.source.hls.playlist.HlsMediaPlaylist$Segment;
import com.google.android.exoplayer2.source.hls.playlist.HlsMediaPlaylist;
import com.google.android.exoplayer2.source.hls.playlist.HlsPlaylist;
import com.google.android.exoplayer2.source.hls.playlist.HlsPlaylistParser;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.upstream.ParsingLoadable;
import com.google.android.exoplayer2.util.UriUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public final class HlsDownloader extends SegmentDownloader {
    public HlsDownloader(Uri arg1, List arg2, DownloaderConstructorHelper arg3) {
        super(arg1, arg2, arg3);
    }

    private static void addResolvedUris(String arg2, List arg3, List arg4) {
        int v0;
        for(v0 = 0; v0 < arg3.size(); ++v0) {
            arg4.add(UriUtil.resolveToUri(arg2, arg3.get(v0).url));
        }
    }

    private static void addSegment(ArrayList arg9, HlsMediaPlaylist arg10, Segment arg11, HashSet arg12) {
        long v0 = arg10.startTimeUs + arg11.relativeStartTimeUs;
        if(arg11.fullSegmentEncryptionKeyUri != null) {
            Uri v2 = UriUtil.resolveToUri(arg10.baseUri, arg11.fullSegmentEncryptionKeyUri);
            if(arg12.add(v2)) {
                arg9.add(new com.google.android.exoplayer2.offline.SegmentDownloader$Segment(v0, new DataSpec(v2)));
            }
        }

        arg9.add(new com.google.android.exoplayer2.offline.SegmentDownloader$Segment(v0, new DataSpec(UriUtil.resolveToUri(arg10.baseUri, arg11.url), arg11.byterangeOffset, arg11.byterangeLength, null)));
    }

    protected FilterableManifest getManifest(DataSource arg1, Uri arg2) {
        return this.getManifest(arg1, arg2);
    }

    protected HlsPlaylist getManifest(DataSource arg1, Uri arg2) {
        return HlsDownloader.loadManifest(arg1, arg2);
    }

    protected List getSegments(DataSource arg1, FilterableManifest arg2, boolean arg3) {
        return this.getSegments(arg1, ((HlsPlaylist)arg2), arg3);
    }

    protected List getSegments(DataSource arg9, HlsPlaylist arg10, boolean arg11) {
        HlsPlaylist v3_1;
        ArrayList v0 = new ArrayList();
        if((arg10 instanceof HlsMasterPlaylist)) {
            HlsDownloader.addResolvedUris(((HlsMasterPlaylist)arg10).baseUri, ((HlsMasterPlaylist)arg10).variants, ((List)v0));
            HlsDownloader.addResolvedUris(((HlsMasterPlaylist)arg10).baseUri, ((HlsMasterPlaylist)arg10).audios, ((List)v0));
            HlsDownloader.addResolvedUris(((HlsMasterPlaylist)arg10).baseUri, ((HlsMasterPlaylist)arg10).subtitles, ((List)v0));
        }
        else {
            v0.add(Uri.parse(arg10.baseUri));
        }

        ArrayList v10 = new ArrayList();
        HashSet v1 = new HashSet();
        Iterator v0_1 = v0.iterator();
        while(true) {
        label_22:
            if(!v0_1.hasNext()) {
                goto label_56;
            }

            Object v2 = v0_1.next();
            try {
                v3_1 = HlsDownloader.loadManifest(arg9, ((Uri)v2));
                v10.add(new com.google.android.exoplayer2.offline.SegmentDownloader$Segment(((HlsMediaPlaylist)v3_1).startTimeUs, new DataSpec(((Uri)v2))));
                break;
            }
            catch(IOException v3) {
                if(arg11) {
                    v10.add(new com.google.android.exoplayer2.offline.SegmentDownloader$Segment(0, new DataSpec(((Uri)v2))));
                    continue;
                }

                throw v3;
            }
        }

        Segment v2_1 = null;
        List v4 = ((HlsMediaPlaylist)v3_1).segments;
        int v5 = 0;
        goto label_35;
    label_56:
        return ((List)v10);
        while(true) {
        label_35:
            if(v5 >= v4.size()) {
                goto label_22;
            }

            Object v6 = v4.get(v5);
            Segment v7 = ((Segment)v6).initializationSegment;
            if(v7 != null && v7 != v2_1) {
                HlsDownloader.addSegment(v10, ((HlsMediaPlaylist)v3_1), v7, v1);
                v2_1 = v7;
            }

            HlsDownloader.addSegment(v10, ((HlsMediaPlaylist)v3_1), ((Segment)v6), v1);
            ++v5;
        }
    }

    private static HlsPlaylist loadManifest(DataSource arg2, Uri arg3) {
        return ParsingLoadable.load(arg2, new HlsPlaylistParser(), arg3, 4);
    }
}

