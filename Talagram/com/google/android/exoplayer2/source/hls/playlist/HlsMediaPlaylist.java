package com.google.android.exoplayer2.source.hls.playlist;

import com.google.android.exoplayer2.drm.DrmInitData;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Collections;
import java.util.List;

public final class HlsMediaPlaylist extends HlsPlaylist {
    @Retention(value=RetentionPolicy.SOURCE) @public interface PlaylistType {
    }

    public final class Segment implements Comparable {
        public final long byterangeLength;
        public final long byterangeOffset;
        public final long durationUs;
        public final String encryptionIV;
        public final String fullSegmentEncryptionKeyUri;
        public final boolean hasGapTag;
        public final Segment initializationSegment;
        public final int relativeDiscontinuitySequence;
        public final long relativeStartTimeUs;
        public final String title;
        public final String url;

        public Segment(String arg17, long arg18, long arg20) {
            this(arg17, null, "", 0, -1, -9223372036854775807L, null, null, arg18, arg20, false);
        }

        public Segment(String arg1, Segment arg2, String arg3, long arg4, int arg6, long arg7, String arg9, String arg10, long arg11, long arg13, boolean arg15) {
            super();
            this.url = arg1;
            this.initializationSegment = arg2;
            this.title = arg3;
            this.durationUs = arg4;
            this.relativeDiscontinuitySequence = arg6;
            this.relativeStartTimeUs = arg7;
            this.fullSegmentEncryptionKeyUri = arg9;
            this.encryptionIV = arg10;
            this.byterangeOffset = arg11;
            this.byterangeLength = arg13;
            this.hasGapTag = arg15;
        }

        public int compareTo(Long arg6) {
            int v6;
            if(this.relativeStartTimeUs > arg6.longValue()) {
                v6 = 1;
            }
            else if(this.relativeStartTimeUs < arg6.longValue()) {
                v6 = -1;
            }
            else {
                v6 = 0;
            }

            return v6;
        }

        public int compareTo(Object arg1) {
            return this.compareTo(((Long)arg1));
        }
    }

    public static final int PLAYLIST_TYPE_EVENT = 2;
    public static final int PLAYLIST_TYPE_UNKNOWN = 0;
    public static final int PLAYLIST_TYPE_VOD = 1;
    public final int discontinuitySequence;
    public final DrmInitData drmInitData;
    public final long durationUs;
    public final boolean hasDiscontinuitySequence;
    public final boolean hasEndTag;
    public final boolean hasProgramDateTime;
    public final long mediaSequence;
    public final int playlistType;
    public final List segments;
    public final long startOffsetUs;
    public final long startTimeUs;
    public final long targetDurationUs;
    public final int version;

    public HlsMediaPlaylist(int arg11, String arg12, List arg13, long arg14, long arg16, boolean arg18, int arg19, long arg20, int arg22, long arg23, boolean arg25, boolean arg26, boolean arg27, DrmInitData arg28, List arg29) {
        long v1;
        HlsMediaPlaylist v0 = this;
        super(arg12, arg13, arg25);
        v0.playlistType = arg11;
        v0.startTimeUs = arg16;
        v0.hasDiscontinuitySequence = arg18;
        v0.discontinuitySequence = arg19;
        v0.mediaSequence = arg20;
        v0.version = arg22;
        v0.targetDurationUs = arg23;
        v0.hasEndTag = arg26;
        v0.hasProgramDateTime = arg27;
        v0.drmInitData = arg28;
        v0.segments = Collections.unmodifiableList(arg29);
        long v4 = 0;
        if(!arg29.isEmpty()) {
            Object v3 = arg29.get(arg29.size() - 1);
            v0.durationUs = ((Segment)v3).relativeStartTimeUs + ((Segment)v3).durationUs;
        }
        else {
            v0.durationUs = v4;
        }

        long v6 = -9223372036854775807L;
        if(arg14 == v6) {
            v1 = v6;
        }
        else if(arg14 >= v4) {
            v1 = arg14;
        }
        else {
            v1 = v0.durationUs + arg14;
        }

        v0.startOffsetUs = v1;
    }

    public HlsMediaPlaylist copy(List arg1) {
        return this;
    }

    public Object copy(List arg1) {
        return this.copy(arg1);
    }

    public HlsMediaPlaylist copyWith(long arg23, int arg25) {
        return new HlsMediaPlaylist(this.playlistType, this.baseUri, this.tags, this.startOffsetUs, arg23, true, arg25, this.mediaSequence, this.version, this.targetDurationUs, this.hasIndependentSegments, this.hasEndTag, this.hasProgramDateTime, this.drmInitData, this.segments);
    }

    public HlsMediaPlaylist copyWithEndTag() {
        HlsMediaPlaylist v0 = this;
        if(v0.hasEndTag) {
            return v0;
        }

        return new HlsMediaPlaylist(v0.playlistType, v0.baseUri, v0.tags, v0.startOffsetUs, v0.startTimeUs, v0.hasDiscontinuitySequence, v0.discontinuitySequence, v0.mediaSequence, v0.version, v0.targetDurationUs, v0.hasIndependentSegments, true, v0.hasProgramDateTime, v0.drmInitData, v0.segments);
    }

    public HlsMediaPlaylist copyWithMasterPlaylistInfo(HlsMasterPlaylist arg26) {
        HlsMediaPlaylist v0 = this;
        HlsMasterPlaylist v1 = arg26;
        if(!v0.hasIndependentSegments) {
            if(!v1.hasIndependentSegments) {
            }
            else {
                HlsMediaPlaylist v2 = null;
                int v4 = v0.playlistType;
                String v5 = v0.baseUri;
                List v6 = v0.tags;
                long v7 = v0.startOffsetUs;
                long v9 = v0.startTimeUs;
                boolean v11 = v0.hasDiscontinuitySequence;
                int v12 = v0.discontinuitySequence;
                long v13 = v0.mediaSequence;
                int v15 = v0.version;
                long v23 = v13;
                v13 = v0.targetDurationUs;
                boolean v18 = (v0.hasIndependentSegments) || (v1.hasIndependentSegments) ? true : false;
                this(v4, v5, v6, v7, v9, v11, v12, v23, v15, v13, v18, v0.hasEndTag, v0.hasProgramDateTime, v0.drmInitData, v0.segments);
                return v2;
            }
        }

        return v0;
    }

    public long getEndTimeUs() {
        return this.startTimeUs + this.durationUs;
    }

    public boolean isNewerThan(HlsMediaPlaylist arg7) {
        // Method was not decompiled
    }
}

