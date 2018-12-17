package com.google.android.exoplayer2.source.dash.offline;

import android.net.Uri;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.extractor.ChunkIndex;
import com.google.android.exoplayer2.offline.DownloadException;
import com.google.android.exoplayer2.offline.DownloaderConstructorHelper;
import com.google.android.exoplayer2.offline.FilterableManifest;
import com.google.android.exoplayer2.offline.SegmentDownloader$Segment;
import com.google.android.exoplayer2.offline.SegmentDownloader;
import com.google.android.exoplayer2.source.dash.DashSegmentIndex;
import com.google.android.exoplayer2.source.dash.DashUtil;
import com.google.android.exoplayer2.source.dash.DashWrappingSegmentIndex;
import com.google.android.exoplayer2.source.dash.manifest.AdaptationSet;
import com.google.android.exoplayer2.source.dash.manifest.DashManifest;
import com.google.android.exoplayer2.source.dash.manifest.Period;
import com.google.android.exoplayer2.source.dash.manifest.RangedUri;
import com.google.android.exoplayer2.source.dash.manifest.Representation;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSpec;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class DashDownloader extends SegmentDownloader {
    public DashDownloader(Uri arg1, List arg2, DownloaderConstructorHelper arg3) {
        super(arg1, arg2, arg3);
    }

    private static void addSegment(long arg8, String arg10, RangedUri arg11, ArrayList arg12) {
        arg12.add(new Segment(arg8, new DataSpec(arg11.resolveUri(arg10), arg11.start, arg11.length, null)));
    }

    private static void addSegmentsForAdaptationSet(DataSource arg20, AdaptationSet arg21, long arg22, long arg24, boolean arg26, ArrayList arg27) {
        DashSegmentIndex v6_1;
        DataSource v7;
        int v6;
        AdaptationSet v1 = arg21;
        long v2 = arg22;
        ArrayList v4 = arg27;
        int v5 = 0;
        while(true) {
            if(v5 >= v1.representations.size()) {
                return;
            }

            Object v0 = v1.representations.get(v5);
            try {
                v6 = v1.type;
                v7 = arg20;
            }
            catch(IOException v0_1) {
                goto label_54;
            }

            try {
                v6_1 = DashDownloader.getSegmentIndex(v7, v6, ((Representation)v0));
                if(v6_1 == null) {
                    goto label_43;
                }
            }
            catch(IOException v0_1) {
                goto label_54;
            }

            int v10 = v6_1.getSegmentCount(arg24);
            if(v10 != -1) {
                String v11 = ((Representation)v0).baseUrl;
                RangedUri v12 = ((Representation)v0).getInitializationUri();
                if(v12 != null) {
                    DashDownloader.addSegment(v2, v11, v12, v4);
                }

                RangedUri v0_2 = ((Representation)v0).getIndexUri();
                if(v0_2 != null) {
                    DashDownloader.addSegment(v2, v11, v0_2, v4);
                }

                long v12_1 = v6_1.getFirstSegmentNum();
                long v16 = 1;
                long v14 = (((long)v10)) + v12_1 - v16;
                while(true) {
                    if(v12_1 > v14) {
                        goto label_55;
                    }

                    DashDownloader.addSegment(v2 + v6_1.getTimeUs(v12_1), v11, v6_1.getSegmentUrl(v12_1), v4);
                    v12_1 += v16;
                }
            }

            throw new DownloadException("Unbounded segment index");
            try {
            label_43:
                throw new DownloadException("Missing segment index");
            }
            catch(IOException v0_1) {
            }

        label_54:
            if(!arg26) {
                break;
            }

        label_55:
            ++v5;
            v1 = arg21;
        }

        throw v0_1;
    }

    protected FilterableManifest getManifest(DataSource arg1, Uri arg2) {
        return this.getManifest(arg1, arg2);
    }

    protected DashManifest getManifest(DataSource arg1, Uri arg2) {
        return DashUtil.loadManifest(arg1, arg2);
    }

    private static DashSegmentIndex getSegmentIndex(DataSource arg2, int arg3, Representation arg4) {
        DashSegmentIndex v2_1;
        DashSegmentIndex v0 = arg4.getIndex();
        if(v0 != null) {
            return v0;
        }

        ChunkIndex v2 = DashUtil.loadChunkIndex(arg2, arg3, arg4);
        if(v2 == null) {
            v2_1 = null;
        }
        else {
            DashWrappingSegmentIndex v2_2 = new DashWrappingSegmentIndex(v2, arg4.presentationTimeOffsetUs);
        }

        return v2_1;
    }

    protected List getSegments(DataSource arg1, FilterableManifest arg2, boolean arg3) {
        return this.getSegments(arg1, ((DashManifest)arg2), arg3);
    }

    protected List getSegments(DataSource arg19, DashManifest arg20, boolean arg21) {
        DashManifest v0 = arg20;
        ArrayList v9 = new ArrayList();
        int v11;
        for(v11 = 0; v11 < arg20.getPeriodCount(); ++v11) {
            Period v1 = v0.getPeriod(v11);
            long v12 = C.msToUs(v1.startMs);
            long v14 = v0.getPeriodDurationUs(v11);
            List v8 = v1.adaptationSets;
            int v7 = 0;
            while(v7 < v8.size()) {
                DashDownloader.addSegmentsForAdaptationSet(arg19, v8.get(v7), v12, v14, arg21, v9);
                ++v7;
                v8 = v8;
            }
        }

        return ((List)v9);
    }
}

