package com.google.android.exoplayer2.upstream.cache;

import android.net.Uri;

final class ContentMetadataInternal {
    private static final String METADATA_NAME_CONTENT_LENGTH = "exo_len";
    private static final String METADATA_NAME_REDIRECTED_URI = "exo_redir";
    private static final String PREFIX = "exo_";

    private ContentMetadataInternal() {
        super();
    }

    public static long getContentLength(ContentMetadata arg3) {
        return arg3.get("exo_len", -1);
    }

    public static Uri getRedirectedUri(ContentMetadata arg3) {
        Uri v1 = null;
        String v3 = arg3.get("exo_redir", ((String)v1));
        if(v3 == null) {
        }
        else {
            v1 = Uri.parse(v3);
        }

        return v1;
    }

    public static void removeContentLength(ContentMetadataMutations arg1) {
        arg1.remove("exo_len");
    }

    public static void removeRedirectedUri(ContentMetadataMutations arg1) {
        arg1.remove("exo_redir");
    }

    public static void setContentLength(ContentMetadataMutations arg1, long arg2) {
        arg1.set("exo_len", arg2);
    }

    public static void setRedirectedUri(ContentMetadataMutations arg1, Uri arg2) {
        arg1.set("exo_redir", arg2.toString());
    }
}

