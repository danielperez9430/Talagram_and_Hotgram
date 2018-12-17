package com.google.android.exoplayer2.source.hls.playlist;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.offline.StreamKey;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class HlsMasterPlaylist extends HlsPlaylist {
    public final class HlsUrl {
        public final Format format;
        public final String url;

        public HlsUrl(String arg1, Format arg2) {
            super();
            this.url = arg1;
            this.format = arg2;
        }

        public static HlsUrl createMediaPlaylistHlsUrl(String arg8) {
            return new HlsUrl(arg8, Format.createContainerFormat("0", null, "application/x-mpegURL", null, null, -1, 0, null));
        }
    }

    public static final int GROUP_INDEX_AUDIO = 1;
    public static final int GROUP_INDEX_SUBTITLE = 2;
    public static final int GROUP_INDEX_VARIANT;
    public final List audios;
    public final Format muxedAudioFormat;
    public final List muxedCaptionFormats;
    public final List subtitles;
    public final List variants;

    public HlsMasterPlaylist(String arg1, List arg2, List arg3, List arg4, List arg5, Format arg6, List arg7, boolean arg8) {
        super(arg1, arg2, arg8);
        this.variants = Collections.unmodifiableList(arg3);
        this.audios = Collections.unmodifiableList(arg4);
        this.subtitles = Collections.unmodifiableList(arg5);
        this.muxedAudioFormat = arg6;
        List v1 = arg7 != null ? Collections.unmodifiableList(arg7) : null;
        this.muxedCaptionFormats = v1;
    }

    public HlsMasterPlaylist copy(List arg11) {
        return new HlsMasterPlaylist(this.baseUri, this.tags, HlsMasterPlaylist.copyRenditionsList(this.variants, 0, arg11), HlsMasterPlaylist.copyRenditionsList(this.audios, 1, arg11), HlsMasterPlaylist.copyRenditionsList(this.subtitles, 2, arg11), this.muxedAudioFormat, this.muxedCaptionFormats, this.hasIndependentSegments);
    }

    public Object copy(List arg1) {
        return this.copy(arg1);
    }

    private static List copyRenditionsList(List arg7, int arg8, List arg9) {
        ArrayList v0 = new ArrayList(arg9.size());
        int v2;
        for(v2 = 0; v2 < arg7.size(); ++v2) {
            Object v3 = arg7.get(v2);
            int v4;
            for(v4 = 0; v4 < arg9.size(); ++v4) {
                Object v5 = arg9.get(v4);
                if(((StreamKey)v5).groupIndex == arg8 && ((StreamKey)v5).trackIndex == v2) {
                    ((List)v0).add(v3);
                    break;
                }
            }
        }

        return ((List)v0);
    }

    public static HlsMasterPlaylist createSingleVariantMasterPlaylist(String arg9) {
        List v3 = Collections.singletonList(HlsUrl.createMediaPlaylistHlsUrl(arg9));
        List v5 = Collections.emptyList();
        return new HlsMasterPlaylist(null, Collections.emptyList(), v3, v5, v5, null, null, false);
    }
}

