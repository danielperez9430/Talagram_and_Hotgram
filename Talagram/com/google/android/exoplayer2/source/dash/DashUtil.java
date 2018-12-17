package com.google.android.exoplayer2.source.dash;

import android.net.Uri;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.drm.DrmInitData;
import com.google.android.exoplayer2.extractor.ChunkIndex;
import com.google.android.exoplayer2.extractor.Extractor;
import com.google.android.exoplayer2.extractor.SeekMap;
import com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor;
import com.google.android.exoplayer2.extractor.mp4.FragmentedMp4Extractor;
import com.google.android.exoplayer2.source.chunk.ChunkExtractorWrapper;
import com.google.android.exoplayer2.source.chunk.InitializationChunk;
import com.google.android.exoplayer2.source.dash.manifest.DashManifest;
import com.google.android.exoplayer2.source.dash.manifest.DashManifestParser;
import com.google.android.exoplayer2.source.dash.manifest.Period;
import com.google.android.exoplayer2.source.dash.manifest.RangedUri;
import com.google.android.exoplayer2.source.dash.manifest.Representation;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.upstream.ParsingLoadable;
import java.util.List;

public final class DashUtil {
    private DashUtil() {
        super();
    }

    private static Representation getFirstRepresentation(Period arg2, int arg3) {
        Object v0_1;
        arg3 = arg2.getAdaptationSetIndex(arg3);
        Representation v0 = null;
        if(arg3 == -1) {
            return v0;
        }

        List v2 = arg2.adaptationSets.get(arg3).representations;
        if(v2.isEmpty()) {
        }
        else {
            v0_1 = v2.get(0);
        }

        return ((Representation)v0_1);
    }

    public static ChunkIndex loadChunkIndex(DataSource arg1, int arg2, Representation arg3) {
        SeekMap v1_2;
        ChunkExtractorWrapper v1 = DashUtil.loadInitializationData(arg1, arg2, arg3, true);
        if(v1 == null) {
            ChunkIndex v1_1 = null;
        }
        else {
            v1_2 = v1.getSeekMap();
        }

        return ((ChunkIndex)v1_2);
    }

    public static DrmInitData loadDrmInitData(DataSource arg2, Period arg3) {
        int v0 = 2;
        Representation v1 = DashUtil.getFirstRepresentation(arg3, v0);
        if(v1 == null) {
            v0 = 1;
            v1 = DashUtil.getFirstRepresentation(arg3, 1);
            if(v1 == null) {
                return null;
            }
        }

        Format v3 = v1.format;
        Format v2 = DashUtil.loadSampleFormat(arg2, v0, v1);
        DrmInitData v2_1 = v2 == null ? v3.drmInitData : v2.copyWithManifestFormatInfo(v3).drmInitData;
        return v2_1;
    }

    private static ChunkExtractorWrapper loadInitializationData(DataSource arg3, int arg4, Representation arg5, boolean arg6) {
        RangedUri v0 = arg5.getInitializationUri();
        ChunkExtractorWrapper v1 = null;
        if(v0 == null) {
            return v1;
        }

        ChunkExtractorWrapper v4 = DashUtil.newWrappedExtractor(arg4, arg5.format);
        if(arg6) {
            RangedUri v6 = arg5.getIndexUri();
            if(v6 == null) {
                return v1;
            }
            else {
                RangedUri v1_1 = v0.attemptMerge(v6, arg5.baseUrl);
                if(v1_1 == null) {
                    DashUtil.loadInitializationData(arg3, arg5, v4, v0);
                    v0 = v6;
                }
                else {
                    v0 = v1_1;
                }
            }
        }

        DashUtil.loadInitializationData(arg3, arg5, v4, v0);
        return v4;
    }

    private static void loadInitializationData(DataSource arg8, Representation arg9, ChunkExtractorWrapper arg10, RangedUri arg11) {
        new InitializationChunk(arg8, new DataSpec(arg11.resolveUri(arg9.baseUrl), arg11.start, arg11.length, arg9.getCacheKey()), arg9.format, 0, null, arg10).load();
    }

    public static DashManifest loadManifest(DataSource arg2, Uri arg3) {
        return ParsingLoadable.load(arg2, new DashManifestParser(), arg3, 4);
    }

    public static Format loadSampleFormat(DataSource arg1, int arg2, Representation arg3) {
        ChunkExtractorWrapper v1 = DashUtil.loadInitializationData(arg1, arg2, arg3, false);
        Format v1_1 = v1 == null ? null : v1.getSampleFormats()[0];
        return v1_1;
    }

    private static ChunkExtractorWrapper newWrappedExtractor(int arg2, Format arg3) {
        MatroskaExtractor v0_2;
        int v0_1;
        String v0 = arg3.containerMimeType;
        if(v0 != null) {
            if(!v0.startsWith("video/webm") && !v0.startsWith("audio/webm")) {
                goto label_10;
            }

            v0_1 = 1;
        }
        else {
        label_10:
            v0_1 = 0;
        }

        if(v0_1 != 0) {
            v0_2 = new MatroskaExtractor();
        }
        else {
            FragmentedMp4Extractor v0_3 = new FragmentedMp4Extractor();
        }

        return new ChunkExtractorWrapper(((Extractor)v0_2), arg2, arg3);
    }
}

