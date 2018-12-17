package com.google.android.exoplayer2.source.smoothstreaming.manifest;

import android.net.Uri;
import com.google.android.exoplayer2.util.Util;

public final class SsUtil {
    private SsUtil() {
        super();
    }

    public static Uri fixManifestUri(Uri arg2) {
        if(Util.toLowerInvariant(arg2.getLastPathSegment()).matches("manifest(\\(.+\\))?")) {
            return arg2;
        }

        return Uri.withAppendedPath(arg2, "Manifest");
    }
}

