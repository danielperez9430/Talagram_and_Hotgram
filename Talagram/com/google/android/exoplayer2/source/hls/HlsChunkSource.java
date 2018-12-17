package com.google.android.exoplayer2.source.hls;

import android.net.Uri;
import android.os.SystemClock;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.source.BehindLiveWindowException;
import com.google.android.exoplayer2.source.TrackGroup;
import com.google.android.exoplayer2.source.chunk.Chunk;
import com.google.android.exoplayer2.source.chunk.DataChunk;
import com.google.android.exoplayer2.source.hls.playlist.HlsMasterPlaylist$HlsUrl;
import com.google.android.exoplayer2.source.hls.playlist.HlsMediaPlaylist$Segment;
import com.google.android.exoplayer2.source.hls.playlist.HlsMediaPlaylist;
import com.google.android.exoplayer2.source.hls.playlist.HlsPlaylistTracker;
import com.google.android.exoplayer2.trackselection.BaseTrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.upstream.TransferListener;
import com.google.android.exoplayer2.util.TimestampAdjuster;
import com.google.android.exoplayer2.util.UriUtil;
import com.google.android.exoplayer2.util.Util;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

class HlsChunkSource {
    final class EncryptionKeyChunk extends DataChunk {
        public final String iv;
        private byte[] result;

        public EncryptionKeyChunk(DataSource arg9, DataSpec arg10, Format arg11, int arg12, Object arg13, byte[] arg14, String arg15) {
            super(arg9, arg10, 3, arg11, arg12, arg13, arg14);
            this.iv = arg15;
        }

        protected void consume(byte[] arg1, int arg2) {
            this.result = Arrays.copyOf(arg1, arg2);
        }

        public byte[] getResult() {
            return this.result;
        }
    }

    public final class HlsChunkHolder {
        public Chunk chunk;
        public boolean endOfStream;
        public HlsUrl playlist;

        public HlsChunkHolder() {
            super();
            this.clear();
        }

        public void clear() {
            this.chunk = null;
            this.endOfStream = false;
            this.playlist = null;
        }
    }

    final class InitializationTrackSelection extends BaseTrackSelection {
        private int selectedIndex;

        public InitializationTrackSelection(TrackGroup arg1, int[] arg2) {
            super(arg1, arg2);
            this.selectedIndex = this.indexOf(arg1.getFormat(0));
        }

        public int getSelectedIndex() {
            return this.selectedIndex;
        }

        public Object getSelectionData() {
            return null;
        }

        public int getSelectionReason() {
            return 0;
        }

        public void updateSelectedTrack(long arg1, long arg3, long arg5) {
            arg1 = SystemClock.elapsedRealtime();
            if(!this.isBlacklisted(this.selectedIndex, arg1)) {
                return;
            }

            int v3;
            for(v3 = this.length - 1; v3 >= 0; --v3) {
                if(!this.isBlacklisted(v3, arg1)) {
                    this.selectedIndex = v3;
                    return;
                }
            }

            throw new IllegalStateException();
        }
    }

    private final DataSource encryptionDataSource;
    private byte[] encryptionIv;
    private String encryptionIvString;
    private byte[] encryptionKey;
    private Uri encryptionKeyUri;
    private HlsUrl expectedPlaylistUrl;
    private final HlsExtractorFactory extractorFactory;
    private IOException fatalError;
    private boolean independentSegments;
    private boolean isTimestampMaster;
    private long liveEdgeInPeriodTimeUs;
    private final DataSource mediaDataSource;
    private final List muxedCaptionFormats;
    private final HlsPlaylistTracker playlistTracker;
    private byte[] scratchSpace;
    private boolean seenExpectedPlaylistError;
    private final TimestampAdjusterProvider timestampAdjusterProvider;
    private final TrackGroup trackGroup;
    private TrackSelection trackSelection;
    private final HlsUrl[] variants;

    public HlsChunkSource(HlsExtractorFactory arg1, HlsPlaylistTracker arg2, HlsUrl[] arg3, HlsDataSourceFactory arg4, TransferListener arg5, TimestampAdjusterProvider arg6, List arg7) {
        super();
        this.extractorFactory = arg1;
        this.playlistTracker = arg2;
        this.variants = arg3;
        this.timestampAdjusterProvider = arg6;
        this.muxedCaptionFormats = arg7;
        this.liveEdgeInPeriodTimeUs = -9223372036854775807L;
        Format[] v1 = new Format[arg3.length];
        int[] v2 = new int[arg3.length];
        int v6;
        for(v6 = 0; v6 < arg3.length; ++v6) {
            v1[v6] = arg3[v6].format;
            v2[v6] = v6;
        }

        this.mediaDataSource = arg4.createDataSource(1);
        if(arg5 != null) {
            this.mediaDataSource.addTransferListener(arg5);
        }

        this.encryptionDataSource = arg4.createDataSource(3);
        this.trackGroup = new TrackGroup(v1);
        this.trackSelection = new InitializationTrackSelection(this.trackGroup, v2);
    }

    private void clearEncryptionData() {
        this.encryptionKeyUri = null;
        this.encryptionKey = null;
        this.encryptionIvString = null;
        this.encryptionIv = null;
    }

    public void getNextChunk(long arg38, long arg40, List arg42, HlsChunkHolder arg43) {
        HlsUrl v13_1;
        long v21;
        long v3_1;
        HlsMediaPlaylist v1_2;
        long v13;
        HlsChunkSource v6 = this;
        HlsChunkHolder v7 = arg43;
        Object v0 = arg42.isEmpty() ? null : arg42.get(arg42.size() - 1);
        int v3 = v0 == null ? -1 : v6.trackGroup.indexOf(((HlsMediaChunk)v0).trackFormat);
        long v8 = arg40 - arg38;
        long v10 = this.resolveTimeToLiveEdgeUs(arg38);
        if(v0 == null || (v6.independentSegments)) {
        label_33:
            v13 = v10;
        }
        else {
            long v12 = ((HlsMediaChunk)v0).getDurationUs();
            long v1 = 0;
            v8 = Math.max(v1, v8 - v12);
            if(v10 != -9223372036854775807L) {
                v13 = Math.max(v1, v10 - v12);
            }
            else {
                goto label_33;
            }
        }

        v6.trackSelection.updateSelectedTrack(arg38, v8, v13);
        int v1_1 = v6.trackSelection.getSelectedIndexInTrackGroup();
        int v4 = v3 != v1_1 ? 1 : 0;
        HlsUrl v5 = v6.variants[v1_1];
        if(!v6.playlistTracker.isSnapshotValid(v5)) {
            v7.playlist = v5;
            boolean v0_1 = v6.seenExpectedPlaylistError;
            int v32 = v6.expectedPlaylistUrl == v5 ? 1 : 0;
            v6.seenExpectedPlaylistError = (((int)v0_1)) & v32;
            v6.expectedPlaylistUrl = v5;
            return;
        }

        HlsMediaPlaylist v8_1 = v6.playlistTracker.getPlaylistSnapshot(v5);
        v6.independentSegments = v8_1.hasIndependentSegments;
        v6.updateLiveEdgeTimeUs(v8_1);
        long v9 = v8_1.startTimeUs - v6.playlistTracker.getInitialStartTimeUs();
        if(v0 == null || v4 != 0) {
            long v11 = v8_1.durationUs + v9;
            v13 = v0 == null || (v6.independentSegments) ? arg40 : ((HlsMediaChunk)v0).startTimeUs;
            if((v8_1.hasEndTag) || v13 < v11) {
                List v4_1 = v8_1.segments;
                Long v11_1 = Long.valueOf(v13 - v9);
                boolean v12_1 = !v6.playlistTracker.isLive() || v0 == null ? true : false;
                v11 = (((long)Util.binarySearchFloor(v4_1, ((Comparable)v11_1), true, v12_1))) + v8_1.mediaSequence;
                if(v11 < v8_1.mediaSequence && v0 != null) {
                    v5 = v6.variants[v3];
                    v1_2 = v6.playlistTracker.getPlaylistSnapshot(v5);
                    v8 = v1_2.startTimeUs - v6.playlistTracker.getInitialStartTimeUs();
                    v10 = ((HlsMediaChunk)v0).getNextChunkIndex();
                    long v35 = v8;
                    v8_1 = v1_2;
                    v1_1 = v3;
                    v3_1 = v35;
                    goto label_136;
                }

                v3_1 = v9;
                v10 = v11;
            }
            else {
                v3_1 = v9;
                v10 = v8_1.mediaSequence + (((long)v8_1.segments.size()));
            }

        label_136:
            v13_1 = v5;
            v21 = v10;
            v9 = v3_1;
        }
        else {
            v21 = ((HlsMediaChunk)v0).getNextChunkIndex();
            v13_1 = v5;
        }

        v3 = v1_1;
        v1_2 = v8_1;
        if(v21 < v1_2.mediaSequence) {
            v6.fatalError = new BehindLiveWindowException();
            return;
        }

        v4 = ((int)(v21 - v1_2.mediaSequence));
        if(v4 >= v1_2.segments.size()) {
            if(v1_2.hasEndTag) {
                v7.endOfStream = true;
            }
            else {
                int v0_2 = 1;
                v7.playlist = v13_1;
                boolean v1_3 = v6.seenExpectedPlaylistError;
                if(v6.expectedPlaylistUrl == v13_1) {
                }
                else {
                    v0_2 = 0;
                }

                v6.seenExpectedPlaylistError = v0_2 & (((int)v1_3));
                v6.expectedPlaylistUrl = v13_1;
            }

            return;
        }

        v6.seenExpectedPlaylistError = false;
        HlsUrl v2 = null;
        v6.expectedPlaylistUrl = v2;
        Object v4_2 = v1_2.segments.get(v4);
        if(((Segment)v4_2).fullSegmentEncryptionKeyUri != null) {
            Uri v5_1 = UriUtil.resolveToUri(v1_2.baseUri, ((Segment)v4_2).fullSegmentEncryptionKeyUri);
            if(!v5_1.equals(v6.encryptionKeyUri)) {
                v7.chunk = this.newEncryptionKeyChunk(v5_1, ((Segment)v4_2).encryptionIV, v3, v6.trackSelection.getSelectionReason(), v6.trackSelection.getSelectionData());
                return;
            }
            else if(!Util.areEqual(((Segment)v4_2).encryptionIV, v6.encryptionIvString)) {
                v6.setEncryptionData(v5_1, ((Segment)v4_2).encryptionIV, v6.encryptionKey);
            }
        }
        else {
            this.clearEncryptionData();
        }

        Segment v3_2 = ((Segment)v4_2).initializationSegment;
        if(v3_2 != null) {
            DataSpec v2_1 = new DataSpec(UriUtil.resolveToUri(v1_2.baseUri, v3_2.url), v3_2.byterangeOffset, v3_2.byterangeLength, null);
        }

        DataSpec v12_2 = ((DataSpec)v2);
        long v2_2 = ((Segment)v4_2).relativeStartTimeUs + v9;
        int v5_2 = v1_2.discontinuitySequence + ((Segment)v4_2).relativeDiscontinuitySequence;
        TimestampAdjuster v26 = v6.timestampAdjusterProvider.getAdjuster(v5_2);
        new DataSpec(UriUtil.resolveToUri(v1_2.baseUri, ((Segment)v4_2).url), ((Segment)v4_2).byterangeOffset, ((Segment)v4_2).byterangeLength, null);
        arg43.chunk = new HlsMediaChunk(v6.extractorFactory, v6.mediaDataSource, null, v12_2, v13_1, v6.muxedCaptionFormats, v6.trackSelection.getSelectionReason(), v6.trackSelection.getSelectionData(), v2_2, v2_2 + ((Segment)v4_2).durationUs, v21, v5_2, ((Segment)v4_2).hasGapTag, v6.isTimestampMaster, v26, v0, v1_2.drmInitData, v6.encryptionKey, v6.encryptionIv);
    }

    public TrackGroup getTrackGroup() {
        return this.trackGroup;
    }

    public TrackSelection getTrackSelection() {
        return this.trackSelection;
    }

    public boolean maybeBlacklistTrack(Chunk arg4, long arg5) {
        return this.trackSelection.blacklist(this.trackSelection.indexOf(this.trackGroup.indexOf(arg4.trackFormat)), arg5);
    }

    public void maybeThrowError() {
        if(this.fatalError == null) {
            if(this.expectedPlaylistUrl != null && (this.seenExpectedPlaylistError)) {
                this.playlistTracker.maybeThrowPlaylistRefreshError(this.expectedPlaylistUrl);
            }

            return;
        }

        throw this.fatalError;
    }

    private EncryptionKeyChunk newEncryptionKeyChunk(Uri arg10, String arg11, int arg12, int arg13, Object arg14) {
        return new EncryptionKeyChunk(this.encryptionDataSource, new DataSpec(arg10, 0, -1, null, 1), this.variants[arg12].format, arg13, arg14, this.scratchSpace, arg11);
    }

    public void onChunkLoadCompleted(Chunk arg3) {
        if((arg3 instanceof EncryptionKeyChunk)) {
            this.scratchSpace = ((EncryptionKeyChunk)arg3).getDataHolder();
            this.setEncryptionData(((EncryptionKeyChunk)arg3).dataSpec.uri, ((EncryptionKeyChunk)arg3).iv, ((EncryptionKeyChunk)arg3).getResult());
        }
    }

    public boolean onPlaylistError(HlsUrl arg8, boolean arg9) {
        int v0 = this.trackGroup.indexOf(arg8.format);
        int v1 = -1;
        boolean v2 = true;
        if(v0 == v1) {
            return 1;
        }

        v0 = this.trackSelection.indexOf(v0);
        if(v0 == v1) {
            return 1;
        }

        boolean v1_1 = this.seenExpectedPlaylistError;
        int v8 = this.expectedPlaylistUrl == arg8 ? 1 : 0;
        this.seenExpectedPlaylistError = v8 | (((int)v1_1));
        if(arg9) {
            if(this.trackSelection.blacklist(v0, 60000)) {
            }
            else {
                v2 = false;
            }
        }

        return v2;
    }

    public void reset() {
        this.fatalError = null;
    }

    private long resolveTimeToLiveEdgeUs(long arg6) {
        long v2 = -9223372036854775807L;
        int v0 = this.liveEdgeInPeriodTimeUs != v2 ? 1 : 0;
        if(v0 != 0) {
            v2 = this.liveEdgeInPeriodTimeUs - arg6;
        }

        return v2;
    }

    public void selectTracks(TrackSelection arg1) {
        this.trackSelection = arg1;
    }

    private void setEncryptionData(Uri arg6, String arg7, byte[] arg8) {
        String v0 = Util.toLowerInvariant(arg7).startsWith("0x") ? arg7.substring(2) : arg7;
        int v2 = 16;
        byte[] v0_1 = new BigInteger(v0, v2).toByteArray();
        byte[] v1 = new byte[v2];
        int v3 = v0_1.length > v2 ? v0_1.length - v2 : 0;
        System.arraycopy(v0_1, v3, v1, v1.length - v0_1.length + v3, v0_1.length - v3);
        this.encryptionKeyUri = arg6;
        this.encryptionKey = arg8;
        this.encryptionIvString = arg7;
        this.encryptionIv = v1;
    }

    public void setIsTimestampMaster(boolean arg1) {
        this.isTimestampMaster = arg1;
    }

    private void updateLiveEdgeTimeUs(HlsMediaPlaylist arg5) {
        long v0 = arg5.hasEndTag ? -9223372036854775807L : arg5.getEndTimeUs() - this.playlistTracker.getInitialStartTimeUs();
        this.liveEdgeInPeriodTimeUs = v0;
    }
}

