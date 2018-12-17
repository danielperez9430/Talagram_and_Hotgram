package com.google.android.exoplayer2.source.hls;

import android.os.Handler;
import android.util.Log;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.FormatHolder;
import com.google.android.exoplayer2.decoder.DecoderInputBuffer;
import com.google.android.exoplayer2.extractor.DummyTrackOutput;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.SeekMap;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.source.MediaSourceEventListener$EventDispatcher;
import com.google.android.exoplayer2.source.SampleQueue$UpstreamFormatChangedListener;
import com.google.android.exoplayer2.source.SampleQueue;
import com.google.android.exoplayer2.source.SampleStream;
import com.google.android.exoplayer2.source.SequenceableLoader;
import com.google.android.exoplayer2.source.TrackGroup;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.source.chunk.Chunk;
import com.google.android.exoplayer2.source.hls.playlist.HlsMasterPlaylist$HlsUrl;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.upstream.Allocator;
import com.google.android.exoplayer2.upstream.LoadErrorHandlingPolicy;
import com.google.android.exoplayer2.upstream.Loader$Callback;
import com.google.android.exoplayer2.upstream.Loader$LoadErrorAction;
import com.google.android.exoplayer2.upstream.Loader$Loadable;
import com.google.android.exoplayer2.upstream.Loader$ReleaseCallback;
import com.google.android.exoplayer2.upstream.Loader;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.Util;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

final class HlsSampleStreamWrapper implements ExtractorOutput, UpstreamFormatChangedListener, SequenceableLoader, Callback, ReleaseCallback {
    public interface com.google.android.exoplayer2.source.hls.HlsSampleStreamWrapper$Callback extends com.google.android.exoplayer2.source.SequenceableLoader$Callback {
        void onPlaylistRefreshRequired(HlsUrl arg1);

        void onPrepared();
    }

    public static final int SAMPLE_QUEUE_INDEX_NO_MAPPING_FATAL = -2;
    public static final int SAMPLE_QUEUE_INDEX_NO_MAPPING_NON_FATAL = -3;
    public static final int SAMPLE_QUEUE_INDEX_PENDING = -1;
    private static final String TAG = "HlsSampleStreamWrapper";
    private final Allocator allocator;
    private int audioSampleQueueIndex;
    private boolean audioSampleQueueMappingDone;
    private final com.google.android.exoplayer2.source.hls.HlsSampleStreamWrapper$Callback callback;
    private final LoadErrorHandlingPolicy chunkLoadErrorHandlingPolicy;
    private final HlsChunkSource chunkSource;
    private int chunkUid;
    private Format downstreamTrackFormat;
    private int enabledTrackGroupCount;
    private final EventDispatcher eventDispatcher;
    private final Handler handler;
    private boolean haveAudioVideoSampleQueues;
    private final ArrayList hlsSampleStreams;
    private long lastSeekPositionUs;
    private final Loader loader;
    private boolean loadingFinished;
    private final Runnable maybeFinishPrepareRunnable;
    private final ArrayList mediaChunks;
    private final int minLoadableRetryCount;
    private final Format muxedAudioFormat;
    private final HlsChunkHolder nextChunkHolder;
    private final Runnable onTracksEndedRunnable;
    private TrackGroupArray optionalTrackGroups;
    private long pendingResetPositionUs;
    private boolean pendingResetUpstreamFormats;
    private boolean prepared;
    private int primarySampleQueueIndex;
    private int primarySampleQueueType;
    private int primaryTrackGroupIndex;
    private final List readOnlyMediaChunks;
    private boolean released;
    private long sampleOffsetUs;
    private boolean[] sampleQueueIsAudioVideoFlags;
    private int[] sampleQueueTrackIds;
    private SampleQueue[] sampleQueues;
    private boolean sampleQueuesBuilt;
    private boolean[] sampleQueuesEnabledStates;
    private boolean seenFirstTrackSelection;
    private int[] trackGroupToSampleQueueIndex;
    private TrackGroupArray trackGroups;
    private final int trackType;
    private boolean tracksEnded;
    private Format upstreamTrackFormat;
    private int videoSampleQueueIndex;
    private boolean videoSampleQueueMappingDone;

    public HlsSampleStreamWrapper(int arg1, com.google.android.exoplayer2.source.hls.HlsSampleStreamWrapper$Callback arg2, HlsChunkSource arg3, Allocator arg4, long arg5, Format arg7, LoadErrorHandlingPolicy arg8, int arg9, EventDispatcher arg10) {
        super();
        this.trackType = arg1;
        this.callback = arg2;
        this.chunkSource = arg3;
        this.allocator = arg4;
        this.muxedAudioFormat = arg7;
        this.chunkLoadErrorHandlingPolicy = arg8;
        this.minLoadableRetryCount = arg9;
        this.eventDispatcher = arg10;
        this.loader = new Loader("Loader:HlsSampleStreamWrapper");
        this.nextChunkHolder = new HlsChunkHolder();
        this.sampleQueueTrackIds = new int[0];
        this.audioSampleQueueIndex = -1;
        this.videoSampleQueueIndex = -1;
        this.sampleQueues = new SampleQueue[0];
        this.sampleQueueIsAudioVideoFlags = new boolean[0];
        this.sampleQueuesEnabledStates = new boolean[0];
        this.mediaChunks = new ArrayList();
        this.readOnlyMediaChunks = Collections.unmodifiableList(this.mediaChunks);
        this.hlsSampleStreams = new ArrayList();
        this.maybeFinishPrepareRunnable = new -$$Lambda$HlsSampleStreamWrapper$8JyeEr0irIOShv9LlAxAmgzl5vY(this);
        this.onTracksEndedRunnable = new -$$Lambda$HlsSampleStreamWrapper$afhkI3tagC_-MAOTh7FzBWzQsno(this);
        this.handler = new Handler();
        this.lastSeekPositionUs = arg5;
        this.pendingResetPositionUs = arg5;
    }

    public int bindSampleQueueToSampleStream(int arg5) {
        int v0 = this.trackGroupToSampleQueueIndex[arg5];
        int v1 = -2;
        int v2 = -1;
        if(v0 == v2) {
            if(this.optionalTrackGroups.indexOf(this.trackGroups.get(arg5)) == v2) {
            }
            else {
                v1 = -3;
            }

            return v1;
        }

        if(this.sampleQueuesEnabledStates[v0]) {
            return v1;
        }

        this.sampleQueuesEnabledStates[v0] = true;
        return v0;
    }

    private void buildTracksFromSampleStreams() {
        int v7;
        int v0 = this.sampleQueues.length;
        int v2 = -1;
        boolean v3 = false;
        int v4 = 0;
        int v5 = 5;
        int v6 = -1;
        while(true) {
            v7 = 2;
            if(v4 >= v0) {
                break;
            }

            String v9 = this.sampleQueues[v4].getUpstreamFormat().sampleMimeType;
            if(MimeTypes.isVideo(v9)) {
            }
            else if(MimeTypes.isAudio(v9)) {
                v7 = 1;
            }
            else if(MimeTypes.isText(v9)) {
                v7 = 3;
            }
            else {
                v7 = 5;
            }

            if(HlsSampleStreamWrapper.getTrackTypeScore(v7) > HlsSampleStreamWrapper.getTrackTypeScore(v5)) {
                v6 = v4;
                v5 = v7;
            }
            else if(v7 == v5 && v6 != v2) {
                v6 = -1;
            }

            ++v4;
        }

        TrackGroup v1 = this.chunkSource.getTrackGroup();
        v4 = v1.length;
        this.primaryTrackGroupIndex = v2;
        this.trackGroupToSampleQueueIndex = new int[v0];
        for(v2 = 0; v2 < v0; ++v2) {
            this.trackGroupToSampleQueueIndex[v2] = v2;
        }

        TrackGroup[] v2_1 = new TrackGroup[v0];
        int v9_1;
        for(v9_1 = 0; v9_1 < v0; ++v9_1) {
            Format v10 = this.sampleQueues[v9_1].getUpstreamFormat();
            if(v9_1 == v6) {
                Format[] v11 = new Format[v4];
                if(v4 == 1) {
                    v11[0] = v10.copyWithManifestFormatInfo(v1.getFormat(0));
                }
                else {
                    int v12;
                    for(v12 = 0; v12 < v4; ++v12) {
                        v11[v12] = HlsSampleStreamWrapper.deriveFormat(v1.getFormat(v12), v10, true);
                    }
                }

                v2_1[v9_1] = new TrackGroup(v11);
                this.primaryTrackGroupIndex = v9_1;
            }
            else {
                Format v11_1 = v5 != v7 || !MimeTypes.isAudio(v10.sampleMimeType) ? null : this.muxedAudioFormat;
                v2_1[v9_1] = new TrackGroup(new Format[]{HlsSampleStreamWrapper.deriveFormat(v11_1, v10, false)});
            }
        }

        this.trackGroups = new TrackGroupArray(v2_1);
        if(this.optionalTrackGroups == null) {
            v3 = true;
        }

        Assertions.checkState(v3);
        this.optionalTrackGroups = TrackGroupArray.EMPTY;
    }

    public boolean continueLoading(long arg23) {
        long v3;
        List v1;
        HlsSampleStreamWrapper v0 = this;
        if(!v0.loadingFinished) {
            if(v0.loader.isLoading()) {
            }
            else {
                if(this.isPendingReset()) {
                    v1 = Collections.emptyList();
                    v3 = v0.pendingResetPositionUs;
                }
                else {
                    v1 = v0.readOnlyMediaChunks;
                    v3 = this.getLastMediaChunk().endTimeUs;
                }

                List v10 = v1;
                long v8 = v3;
                v0.chunkSource.getNextChunk(arg23, v8, v10, v0.nextChunkHolder);
                boolean v1_1 = v0.nextChunkHolder.endOfStream;
                Chunk v3_1 = v0.nextChunkHolder.chunk;
                HlsUrl v4 = v0.nextChunkHolder.playlist;
                v0.nextChunkHolder.clear();
                long v5 = -9223372036854775807L;
                if(v1_1) {
                    v0.pendingResetPositionUs = v5;
                    v0.loadingFinished = true;
                    return 1;
                }

                if(v3_1 == null) {
                    if(v4 != null) {
                        v0.callback.onPlaylistRefreshRequired(v4);
                    }

                    return 0;
                }

                if(HlsSampleStreamWrapper.isMediaChunk(v3_1)) {
                    v0.pendingResetPositionUs = v5;
                    v3_1.init(v0);
                    v0.mediaChunks.add(v3_1);
                    v0.upstreamTrackFormat = v3_1.trackFormat;
                }

                v0.eventDispatcher.loadStarted(v3_1.dataSpec, v3_1.dataSpec.uri, v3_1.type, v0.trackType, v3_1.trackFormat, v3_1.trackSelectionReason, v3_1.trackSelectionData, v3_1.startTimeUs, v3_1.endTimeUs, v0.loader.startLoading(((Loadable)v3_1), ((Callback)v0), v0.minLoadableRetryCount));
                return 1;
            }
        }

        return 0;
    }

    public void continuePreparing() {
        if(!this.prepared) {
            this.continueLoading(this.lastSeekPositionUs);
        }
    }

    private static DummyTrackOutput createDummyTrackOutput(int arg3, int arg4) {
        Log.w("HlsSampleStreamWrapper", "Unmapped track with id " + arg3 + " of type " + arg4);
        return new DummyTrackOutput();
    }

    private static Format deriveFormat(Format arg10, Format arg11, boolean arg12) {
        if(arg10 == null) {
            return arg11;
        }

        int v5 = arg12 ? arg10.bitrate : -1;
        String v4 = Util.getCodecsOfType(arg10.codecs, MimeTypes.getTrackType(arg11.sampleMimeType));
        String v12 = MimeTypes.getMediaMimeType(v4);
        if(v12 == null) {
            v12 = arg11.sampleMimeType;
        }

        return arg11.copyWithContainerInfo(arg10.id, arg10.label, v12, v4, v5, arg10.width, arg10.height, arg10.selectionFlags, arg10.language);
    }

    public void discardBuffer(long arg5, boolean arg7) {
        if(!this.sampleQueuesBuilt) {
            return;
        }

        int v0 = this.sampleQueues.length;
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            this.sampleQueues[v1].discardTo(arg5, arg7, this.sampleQueuesEnabledStates[v1]);
        }
    }

    public void endTracks() {
        this.tracksEnded = true;
        this.handler.post(this.onTracksEndedRunnable);
    }

    private boolean finishedReadingChunk(HlsMediaChunk arg5) {
        int v5 = arg5.uid;
        int v0 = this.sampleQueues.length;
        int v2;
        for(v2 = 0; v2 < v0; ++v2) {
            if((this.sampleQueuesEnabledStates[v2]) && this.sampleQueues[v2].peekSourceId() == v5) {
                return 0;
            }
        }

        return 1;
    }

    private static boolean formatsMatch(Format arg6, Format arg7) {
        String v0 = arg6.sampleMimeType;
        String v1 = arg7.sampleMimeType;
        int v2 = MimeTypes.getTrackType(v0);
        boolean v3 = false;
        if(v2 != 3) {
            if(v2 == MimeTypes.getTrackType(v1)) {
                v3 = true;
            }

            return v3;
        }

        if(!Util.areEqual(v0, v1)) {
            return 0;
        }

        if(!"application/cea-608".equals(v0)) {
            if("application/cea-708".equals(v0)) {
            }
            else {
                return 1;
            }
        }

        if(arg6.accessibilityChannel == arg7.accessibilityChannel) {
            v3 = true;
        }

        return v3;
    }

    public long getBufferedPositionUs() {
        Object v2_1;
        if(this.loadingFinished) {
            return -9223372036854775808L;
        }

        if(this.isPendingReset()) {
            return this.pendingResetPositionUs;
        }

        long v0 = this.lastSeekPositionUs;
        HlsMediaChunk v2 = this.getLastMediaChunk();
        if(v2.isLoadCompleted()) {
        }
        else if(this.mediaChunks.size() > 1) {
            v2_1 = this.mediaChunks.get(this.mediaChunks.size() - 2);
        }
        else {
            v2 = null;
        }

        if((((HlsMediaChunk)v2_1)) != null) {
            v0 = Math.max(v0, ((HlsMediaChunk)v2_1).endTimeUs);
        }

        if(this.sampleQueuesBuilt) {
            SampleQueue[] v2_2 = this.sampleQueues;
            int v3 = v2_2.length;
            int v4;
            for(v4 = 0; v4 < v3; ++v4) {
                v0 = Math.max(v0, v2_2[v4].getLargestQueuedTimestampUs());
            }
        }

        return v0;
    }

    private HlsMediaChunk getLastMediaChunk() {
        return this.mediaChunks.get(this.mediaChunks.size() - 1);
    }

    public long getNextLoadPositionUs() {
        if(this.isPendingReset()) {
            return this.pendingResetPositionUs;
        }

        long v0 = this.loadingFinished ? -9223372036854775808L : this.getLastMediaChunk().endTimeUs;
        return v0;
    }

    public TrackGroupArray getTrackGroups() {
        return this.trackGroups;
    }

    private static int getTrackTypeScore(int arg0) {
        switch(arg0) {
            case 1: {
                return 2;
            }
            case 2: {
                return 3;
            }
            case 3: {
                return 1;
            }
        }

        return 0;
    }

    public void init(int arg5, boolean arg6, boolean arg7) {
        int v0 = 0;
        if(!arg7) {
            this.audioSampleQueueMappingDone = false;
            this.videoSampleQueueMappingDone = false;
        }

        this.chunkUid = arg5;
        SampleQueue[] v7 = this.sampleQueues;
        int v1 = v7.length;
        int v2;
        for(v2 = 0; v2 < v1; ++v2) {
            v7[v2].sourceId(arg5);
        }

        if(arg6) {
            SampleQueue[] v5 = this.sampleQueues;
            int v6 = v5.length;
            while(v0 < v6) {
                v5[v0].splice();
                ++v0;
            }
        }
    }

    private static boolean isMediaChunk(Chunk arg0) {
        return arg0 instanceof HlsMediaChunk;
    }

    private boolean isPendingReset() {
        boolean v0 = this.pendingResetPositionUs != -9223372036854775807L ? true : false;
        return v0;
    }

    public boolean isReady(int arg2) {
        boolean v2;
        if(!this.loadingFinished) {
            if(!this.isPendingReset() && (this.sampleQueues[arg2].hasNextSample())) {
                goto label_11;
            }

            v2 = false;
        }
        else {
        label_11:
            v2 = true;
        }

        return v2;
    }

    public static void lambda$8JyeEr0irIOShv9LlAxAmgzl5vY(HlsSampleStreamWrapper arg0) {
        arg0.maybeFinishPrepare();
    }

    public static void lambda$afhkI3tagC_-MAOTh7FzBWzQsno(HlsSampleStreamWrapper arg0) {
        arg0.onTracksEnded();
    }

    private void mapSampleQueuesToMatchTrackGroups() {
        int v0 = this.trackGroups.length;
        this.trackGroupToSampleQueueIndex = new int[v0];
        Arrays.fill(this.trackGroupToSampleQueueIndex, -1);
        int v2;
        for(v2 = 0; v2 < v0; ++v2) {
            int v3 = 0;
            while(v3 < this.sampleQueues.length) {
                if(HlsSampleStreamWrapper.formatsMatch(this.sampleQueues[v3].getUpstreamFormat(), this.trackGroups.get(v2).getFormat(0))) {
                    this.trackGroupToSampleQueueIndex[v2] = v3;
                }
                else {
                    ++v3;
                    continue;
                }

                break;
            }
        }

        Iterator v0_1 = this.hlsSampleStreams.iterator();
        while(v0_1.hasNext()) {
            v0_1.next().bindSampleQueue();
        }
    }

    private void maybeFinishPrepare() {
        if(!this.released && this.trackGroupToSampleQueueIndex == null) {
            if(!this.sampleQueuesBuilt) {
            }
            else {
                SampleQueue[] v0 = this.sampleQueues;
                int v1 = v0.length;
                int v2 = 0;
                while(true) {
                    if(v2 >= v1) {
                        break;
                    }
                    else if(v0[v2].getUpstreamFormat() == null) {
                        return;
                    }
                    else {
                        ++v2;
                        continue;
                    }

                    return;
                }

                if(this.trackGroups != null) {
                    this.mapSampleQueuesToMatchTrackGroups();
                }
                else {
                    this.buildTracksFromSampleStreams();
                    this.prepared = true;
                    this.callback.onPrepared();
                }
            }
        }
    }

    public void maybeThrowError() {
        this.loader.maybeThrowError();
        this.chunkSource.maybeThrowError();
    }

    public void maybeThrowPrepareError() {
        this.maybeThrowError();
    }

    public void onLoadCanceled(Chunk arg21, long arg22, long arg24, boolean arg26) {
        HlsSampleStreamWrapper v0 = this;
        v0.eventDispatcher.loadCanceled(arg21.dataSpec, arg21.getUri(), arg21.type, v0.trackType, arg21.trackFormat, arg21.trackSelectionReason, arg21.trackSelectionData, arg21.startTimeUs, arg21.endTimeUs, arg22, arg24, arg21.bytesLoaded());
        if(!arg26) {
            this.resetSampleQueues();
            if(v0.enabledTrackGroupCount > 0) {
                v0.callback.onContinueLoadingRequested(((SequenceableLoader)v0));
            }
        }
    }

    public void onLoadCanceled(Loadable arg1, long arg2, long arg4, boolean arg6) {
        this.onLoadCanceled(((Chunk)arg1), arg2, arg4, arg6);
    }

    public void onLoadCompleted(Chunk arg21, long arg22, long arg24) {
        HlsSampleStreamWrapper v0 = this;
        v0.chunkSource.onChunkLoadCompleted(arg21);
        v0.eventDispatcher.loadCompleted(arg21.dataSpec, arg21.getUri(), arg21.type, v0.trackType, arg21.trackFormat, arg21.trackSelectionReason, arg21.trackSelectionData, arg21.startTimeUs, arg21.endTimeUs, arg22, arg24, arg21.bytesLoaded());
        if(!v0.prepared) {
            v0.continueLoading(v0.lastSeekPositionUs);
        }
        else {
            v0.callback.onContinueLoadingRequested(((SequenceableLoader)v0));
        }
    }

    public void onLoadCompleted(Loadable arg1, long arg2, long arg4) {
        this.onLoadCompleted(((Chunk)arg1), arg2, arg4);
    }

    public LoadErrorAction onLoadError(Chunk arg35, long arg36, long arg38, IOException arg40, int arg41) {
        LoadErrorAction v1_1;
        boolean v12;
        HlsSampleStreamWrapper v0 = this;
        Chunk v7 = arg35;
        long v1 = arg35.bytesLoaded();
        boolean v8 = HlsSampleStreamWrapper.isMediaChunk(arg35);
        long v9 = -9223372036854775807L;
        boolean v11 = false;
        if(!v8 || v1 == 0) {
            v1 = v0.chunkLoadErrorHandlingPolicy.getBlacklistDurationMsFor(arg35, arg38, arg40, arg41);
            if(v1 != v9) {
                v12 = v0.chunkSource.maybeBlacklistTrack(v7, v1);
            }
            else {
            label_20:
                v12 = false;
            }
        }
        else {
            goto label_20;
        }

        if(v12) {
            if(v8) {
                if(v0.mediaChunks.remove(v0.mediaChunks.size() - 1) == v7) {
                    v11 = true;
                }

                Assertions.checkState(v11);
                if(!v0.mediaChunks.isEmpty()) {
                    goto label_37;
                }

                v0.pendingResetPositionUs = v0.lastSeekPositionUs;
            }

        label_37:
            v1_1 = Loader.DONT_RETRY;
        }
        else {
            v1 = v0.chunkLoadErrorHandlingPolicy.getRetryDelayMsFor(arg35, arg38, arg40, arg41);
            if(v1 != v9) {
                v1_1 = Loader.createRetryAction(false, v1);
                goto label_49;
            }

            v1_1 = Loader.DONT_RETRY_FATAL;
        }

    label_49:
        v0.eventDispatcher.loadError(v7.dataSpec, arg35.getUri(), v7.type, v0.trackType, v7.trackFormat, v7.trackSelectionReason, v7.trackSelectionData, v7.startTimeUs, v7.endTimeUs, arg36, arg38, arg35.bytesLoaded(), arg40, v1_1.isRetry() ^ 1);
        if(v12) {
            if(!v0.prepared) {
                v0.continueLoading(v0.lastSeekPositionUs);
            }
            else {
                v0.callback.onContinueLoadingRequested(((SequenceableLoader)v0));
            }
        }

        return v1_1;
    }

    public LoadErrorAction onLoadError(Loadable arg1, long arg2, long arg4, IOException arg6, int arg7) {
        return this.onLoadError(((Chunk)arg1), arg2, arg4, arg6, arg7);
    }

    public void onLoaderReleased() {
        this.resetSampleQueues();
    }

    public boolean onPlaylistError(HlsUrl arg2, boolean arg3) {
        return this.chunkSource.onPlaylistError(arg2, arg3);
    }

    private void onTracksEnded() {
        this.sampleQueuesBuilt = true;
        this.maybeFinishPrepare();
    }

    public void onUpstreamFormatChanged(Format arg2) {
        this.handler.post(this.maybeFinishPrepareRunnable);
    }

    public void prepareWithMasterPlaylistInfo(TrackGroupArray arg2, int arg3, TrackGroupArray arg4) {
        this.prepared = true;
        this.trackGroups = arg2;
        this.optionalTrackGroups = arg4;
        this.primaryTrackGroupIndex = arg3;
        this.callback.onPrepared();
    }

    public int readData(int arg11, FormatHolder arg12, DecoderInputBuffer arg13, boolean arg14) {
        if(this.isPendingReset()) {
            return -3;
        }

        int v1 = 0;
        if(!this.mediaChunks.isEmpty()) {
            int v0;
            for(v0 = 0; v0 < this.mediaChunks.size() - 1; ++v0) {
                if(!this.finishedReadingChunk(this.mediaChunks.get(v0))) {
                    break;
                }
            }

            if(v0 > 0) {
                Util.removeRange(this.mediaChunks, 0, v0);
            }

            Object v0_1 = this.mediaChunks.get(0);
            Format v9 = ((HlsMediaChunk)v0_1).trackFormat;
            if(!v9.equals(this.downstreamTrackFormat)) {
                this.eventDispatcher.downstreamFormatChanged(this.trackType, v9, ((HlsMediaChunk)v0_1).trackSelectionReason, ((HlsMediaChunk)v0_1).trackSelectionData, ((HlsMediaChunk)v0_1).startTimeUs);
            }

            this.downstreamTrackFormat = v9;
        }

        int v13 = this.sampleQueues[arg11].read(arg12, arg13, arg14, this.loadingFinished, this.lastSeekPositionUs);
        if(v13 == -5 && arg11 == this.primarySampleQueueIndex) {
            arg11 = this.sampleQueues[arg11].peekSourceId();
            while(v1 < this.mediaChunks.size()) {
                if(this.mediaChunks.get(v1).uid == arg11) {
                    break;
                }

                ++v1;
            }

            Format v11 = v1 < this.mediaChunks.size() ? this.mediaChunks.get(v1).trackFormat : this.upstreamTrackFormat;
            arg12.format = arg12.format.copyWithManifestFormatInfo(v11);
        }

        return v13;
    }

    public void reevaluateBuffer(long arg1) {
    }

    public void release() {
        if(this.prepared) {
            SampleQueue[] v0 = this.sampleQueues;
            int v1 = v0.length;
            int v2;
            for(v2 = 0; v2 < v1; ++v2) {
                v0[v2].discardToEnd();
            }
        }

        this.loader.release(((ReleaseCallback)this));
        this.handler.removeCallbacksAndMessages(null);
        this.released = true;
        this.hlsSampleStreams.clear();
    }

    private void resetSampleQueues() {
        SampleQueue[] v0 = this.sampleQueues;
        int v1 = v0.length;
        int v3;
        for(v3 = 0; v3 < v1; ++v3) {
            v0[v3].reset(this.pendingResetUpstreamFormats);
        }

        this.pendingResetUpstreamFormats = false;
    }

    private boolean seekInsideBufferUs(long arg7) {
        int v0 = this.sampleQueues.length;
        int v2;
        for(v2 = 0; true; ++v2) {
            int v3 = 1;
            if(v2 >= v0) {
                return 1;
            }

            SampleQueue v4 = this.sampleQueues[v2];
            v4.rewind();
            if(v4.advanceTo(arg7, true, false) != -1) {
            }
            else {
                v3 = 0;
            }

            if(v3 == 0 && ((this.sampleQueueIsAudioVideoFlags[v2]) || !this.haveAudioVideoSampleQueues)) {
                return 0;
            }
        }

        return 1;
    }

    public void seekMap(SeekMap arg1) {
    }

    public boolean seekToUs(long arg3, boolean arg5) {
        this.lastSeekPositionUs = arg3;
        if((this.sampleQueuesBuilt) && !arg5 && !this.isPendingReset() && (this.seekInsideBufferUs(arg3))) {
            return 0;
        }

        this.pendingResetPositionUs = arg3;
        this.loadingFinished = false;
        this.mediaChunks.clear();
        if(this.loader.isLoading()) {
            this.loader.cancelLoading();
        }
        else {
            this.resetSampleQueues();
        }

        return 1;
    }

    public boolean selectTracks(TrackSelection[] arg20, boolean[] arg21, SampleStream[] arg22, boolean[] arg23, long arg24, boolean arg26) {
        boolean v15;
        int v1_2;
        SampleQueue[] v1_1;
        boolean v4_1;
        SampleStream v7;
        HlsSampleStreamWrapper v0 = this;
        TrackSelection[] v1 = arg20;
        SampleStream[] v2 = arg22;
        long v11 = arg24;
        Assertions.checkState(v0.prepared);
        int v4 = v0.enabledTrackGroupCount;
        int v13 = 0;
        int v5;
        for(v5 = 0; true; ++v5) {
            v7 = null;
            if(v5 >= v1.length) {
                break;
            }

            if(v2[v5] != null && (v1[v5] == null || !arg21[v5])) {
                --v0.enabledTrackGroupCount;
                v2[v5].unbindSampleQueue();
                v2[v5] = v7;
            }
        }

        if(!arg26) {
            if(v0.seenFirstTrackSelection) {
                if(v4 == 0) {
                    goto label_37;
                }
            }
            else if(v11 != v0.lastSeekPositionUs) {
                goto label_37;
            }

            v4_1 = false;
        }
        else {
        label_37:
            v4_1 = true;
        }

        TrackSelection v5_1 = v0.chunkSource.getTrackSelection();
        boolean v16 = v4_1;
        TrackSelection v9 = v5_1;
        for(v4 = 0; v4 < v1.length; ++v4) {
            if(v2[v4] == null && v1[v4] != null) {
                ++v0.enabledTrackGroupCount;
                TrackSelection v6 = v1[v4];
                int v8 = v0.trackGroups.indexOf(v6.getTrackGroup());
                if(v8 == v0.primaryTrackGroupIndex) {
                    v0.chunkSource.selectTracks(v6);
                    v9 = v6;
                }

                v2[v4] = new HlsSampleStream(v0, v8);
                arg23[v4] = true;
                if(v0.trackGroupToSampleQueueIndex != null) {
                    v2[v4].bindSampleQueue();
                }

                if(!v0.sampleQueuesBuilt) {
                    goto label_85;
                }

                if(v16) {
                    goto label_85;
                }

                SampleQueue v6_1 = v0.sampleQueues[v0.trackGroupToSampleQueueIndex[v8]];
                v6_1.rewind();
                if(v6_1.advanceTo(v11, true, true) == -1 && v6_1.getReadIndex() != 0) {
                    v16 = true;
                    goto label_85;
                }

                v16 = false;
            }

        label_85:
        }

        if(v0.enabledTrackGroupCount == 0) {
            v0.chunkSource.reset();
            v0.downstreamTrackFormat = ((Format)v7);
            v0.mediaChunks.clear();
            if(v0.loader.isLoading()) {
                if(v0.sampleQueuesBuilt) {
                    v1_1 = v0.sampleQueues;
                    int v3 = v1_1.length;
                    goto label_101;
                }

                goto label_106;
            }
            else {
                this.resetSampleQueues();
            }
        }
        else {
            if((v0.mediaChunks.isEmpty()) || (Util.areEqual(v9, v5_1))) {
            label_144:
                v15 = arg26;
            }
            else {
                if(!v0.seenFirstTrackSelection) {
                    long v4_2 = 0;
                    if(v11 < v4_2) {
                        v4_2 = -v11;
                    }

                    v9.updateSelectedTrack(arg24, v4_2, -9223372036854775807L);
                    if(v9.getSelectedIndexInTrackGroup() != v0.chunkSource.getTrackGroup().indexOf(this.getLastMediaChunk().trackFormat)) {
                        goto label_138;
                    }

                    v1_2 = 0;
                }
                else {
                label_138:
                    v1_2 = 1;
                }

                if(v1_2 == 0) {
                    goto label_144;
                }

                v0.pendingResetUpstreamFormats = true;
                v15 = true;
                v16 = true;
            }

            if(!v16) {
                goto label_154;
            }

            v0.seekToUs(v11, v15);
            while(true) {
                if(v13 >= v2.length) {
                    goto label_154;
                }

                if(v2[v13] != null) {
                    arg23[v13] = true;
                }

                ++v13;
            }

        label_101:
            while(v13 < v3) {
                v1_1[v13].discardToEnd();
                ++v13;
            }

        label_106:
            v0.loader.cancelLoading();
        }

    label_154:
        v0.updateSampleStreams(v2);
        v0.seenFirstTrackSelection = true;
        return v16;
    }

    public void setIsTimestampMaster(boolean arg2) {
        this.chunkSource.setIsTimestampMaster(arg2);
    }

    public void setSampleOffsetUs(long arg5) {
        this.sampleOffsetUs = arg5;
        SampleQueue[] v0 = this.sampleQueues;
        int v1 = v0.length;
        int v2;
        for(v2 = 0; v2 < v1; ++v2) {
            v0[v2].setSampleOffsetUs(arg5);
        }
    }

    public int skipData(int arg5, long arg6) {
        if(this.isPendingReset()) {
            return 0;
        }

        SampleQueue v5 = this.sampleQueues[arg5];
        if((this.loadingFinished) && arg6 > v5.getLargestQueuedTimestampUs()) {
            return v5.advanceToEnd();
        }

        arg5 = v5.advanceTo(arg6, true, true);
        if(arg5 == -1) {
            arg5 = 0;
        }

        return arg5;
    }

    public TrackOutput track(int arg8, int arg9) {
        DummyTrackOutput v8_1;
        SampleQueue v8;
        int v0 = this.sampleQueues.length;
        boolean v1 = false;
        int v2 = -1;
        int v3 = 2;
        if(arg9 != 1) {
            if(arg9 == v3) {
                if(this.videoSampleQueueIndex != v2) {
                    if(this.videoSampleQueueMappingDone) {
                        if(this.sampleQueueTrackIds[this.videoSampleQueueIndex] == arg8) {
                            v8 = this.sampleQueues[this.videoSampleQueueIndex];
                        }
                        else {
                            v8_1 = HlsSampleStreamWrapper.createDummyTrackOutput(arg8, arg9);
                        }

                        return ((TrackOutput)v8);
                    }

                    this.videoSampleQueueMappingDone = true;
                    this.sampleQueueTrackIds[this.videoSampleQueueIndex] = arg8;
                    return this.sampleQueues[this.videoSampleQueueIndex];
                }

                if(!this.tracksEnded) {
                    goto label_74;
                }

                return HlsSampleStreamWrapper.createDummyTrackOutput(arg8, arg9);
            }

            for(v2 = 0; v2 < v0; ++v2) {
                if(this.sampleQueueTrackIds[v2] == arg8) {
                    return this.sampleQueues[v2];
                }
            }

            if(!this.tracksEnded) {
                goto label_74;
            }

            return HlsSampleStreamWrapper.createDummyTrackOutput(arg8, arg9);
        }
        else if(this.audioSampleQueueIndex == v2) {
            if(!this.tracksEnded) {
                goto label_74;
            }

            return HlsSampleStreamWrapper.createDummyTrackOutput(arg8, arg9);
        }
        else if(this.audioSampleQueueMappingDone) {
            if(this.sampleQueueTrackIds[this.audioSampleQueueIndex] == arg8) {
                v8 = this.sampleQueues[this.audioSampleQueueIndex];
            }
            else {
                v8_1 = HlsSampleStreamWrapper.createDummyTrackOutput(arg8, arg9);
            }

            return ((TrackOutput)v8);
        }
        else {
            this.audioSampleQueueMappingDone = true;
            this.sampleQueueTrackIds[this.audioSampleQueueIndex] = arg8;
            return this.sampleQueues[this.audioSampleQueueIndex];
        }

    label_74:
        SampleQueue v2_1 = new SampleQueue(this.allocator);
        v2_1.setSampleOffsetUs(this.sampleOffsetUs);
        v2_1.sourceId(this.chunkUid);
        v2_1.setUpstreamFormatChangeListener(((UpstreamFormatChangedListener)this));
        int v6 = v0 + 1;
        this.sampleQueueTrackIds = Arrays.copyOf(this.sampleQueueTrackIds, v6);
        this.sampleQueueTrackIds[v0] = arg8;
        this.sampleQueues = Arrays.copyOf(this.sampleQueues, v6);
        this.sampleQueues[v0] = v2_1;
        this.sampleQueueIsAudioVideoFlags = Arrays.copyOf(this.sampleQueueIsAudioVideoFlags, v6);
        boolean[] v8_2 = this.sampleQueueIsAudioVideoFlags;
        if(arg9 == 1 || arg9 == v3) {
            v1 = true;
        }

        v8_2[v0] = v1;
        this.haveAudioVideoSampleQueues |= this.sampleQueueIsAudioVideoFlags[v0];
        if(arg9 == 1) {
            this.audioSampleQueueMappingDone = true;
            this.audioSampleQueueIndex = v0;
        }
        else if(arg9 == v3) {
            this.videoSampleQueueMappingDone = true;
            this.videoSampleQueueIndex = v0;
        }

        if(HlsSampleStreamWrapper.getTrackTypeScore(arg9) > HlsSampleStreamWrapper.getTrackTypeScore(this.primarySampleQueueType)) {
            this.primarySampleQueueIndex = v0;
            this.primarySampleQueueType = arg9;
        }

        this.sampleQueuesEnabledStates = Arrays.copyOf(this.sampleQueuesEnabledStates, v6);
        return ((TrackOutput)v2_1);
    }

    public void unbindSampleQueue(int arg3) {
        arg3 = this.trackGroupToSampleQueueIndex[arg3];
        Assertions.checkState(this.sampleQueuesEnabledStates[arg3]);
        this.sampleQueuesEnabledStates[arg3] = false;
    }

    private void updateSampleStreams(SampleStream[] arg5) {
        this.hlsSampleStreams.clear();
        int v0 = arg5.length;
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            SampleStream v2 = arg5[v1];
            if(v2 != null) {
                this.hlsSampleStreams.add(v2);
            }
        }
    }
}

