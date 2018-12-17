package com.google.android.exoplayer2.source.hls;

import android.net.Uri;
import android.util.Pair;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.drm.DrmInitData;
import com.google.android.exoplayer2.extractor.Extractor;
import com.google.android.exoplayer2.util.TimestampAdjuster;
import java.util.List;
import java.util.Map;

public interface HlsExtractorFactory {
    public static final HlsExtractorFactory DEFAULT;

    static {
        HlsExtractorFactory.DEFAULT = new DefaultHlsExtractorFactory();
    }

    Pair createExtractor(Extractor arg1, Uri arg2, Format arg3, List arg4, DrmInitData arg5, TimestampAdjuster arg6, Map arg7);
}

