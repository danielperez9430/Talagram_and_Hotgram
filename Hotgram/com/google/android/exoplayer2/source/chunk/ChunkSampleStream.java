package com.google.android.exoplayer2.source.chunk;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.FormatHolder;
import com.google.android.exoplayer2.SeekParameters;
import com.google.android.exoplayer2.decoder.DecoderInputBuffer;
import com.google.android.exoplayer2.source.MediaSourceEventListener$EventDispatcher;
import com.google.android.exoplayer2.source.SampleQueue;
import com.google.android.exoplayer2.source.SampleStream;
import com.google.android.exoplayer2.source.SequenceableLoader;
import com.google.android.exoplayer2.upstream.Allocator;
import com.google.android.exoplayer2.upstream.Loader$Callback;
import com.google.android.exoplayer2.upstream.Loader$LoadErrorAction;
import com.google.android.exoplayer2.upstream.Loader$Loadable;
import com.google.android.exoplayer2.upstream.Loader$ReleaseCallback;
import com.google.android.exoplayer2.upstream.Loader;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ChunkSampleStream implements SampleStream, SequenceableLoader, Callback, ReleaseCallback {
    public final class EmbeddedSampleStream implements SampleStream {
        private boolean formatNotificationSent;
        private final int index;
        public final ChunkSampleStream parent;
        private final SampleQueue sampleQueue;

        public EmbeddedSampleStream(ChunkSampleStream arg1, ChunkSampleStream arg2, SampleQueue arg3, int arg4) {
            ChunkSampleStream.this = arg1;
            super();
            this.parent = arg2;
            this.sampleQueue = arg3;
            this.index = arg4;
        }

        public boolean isReady() {
            boolean v0;
            if(!ChunkSampleStream.this.loadingFinished) {
                if(!ChunkSampleStream.this.isPendingReset() && (this.sampleQueue.hasNextSample())) {
                    goto label_12;
                }

                v0 = false;
            }
            else {
            label_12:
                v0 = true;
            }

            return v0;
        }

        private void maybeNotifyTrackFormatChanged() {
            if(!this.formatNotificationSent) {
                ChunkSampleStream.this.eventDispatcher.downstreamFormatChanged(ChunkSampleStream.this.embeddedTrackTypes[this.index], ChunkSampleStream.this.embeddedTrackFormats[this.index], 0, null, ChunkSampleStream.this.lastSeekPositionUs);
                this.formatNotificationSent = true;
            }
        }

        public void maybeThrowError() {
        }

        public int readData(FormatHolder arg8, DecoderInputBuffer arg9, boolean arg10) {
            if(ChunkSampleStream.this.isPendingReset()) {
                return -3;
            }

            int v8 = this.sampleQueue.read(arg8, arg9, arg10, ChunkSampleStream.this.loadingFinished, ChunkSampleStream.this.decodeOnlyUntilPositionUs);
            if(v8 == -4) {
                this.maybeNotifyTrackFormatChanged();
            }

            return v8;
        }

        public void release() {
            Assertions.checkState(ChunkSampleStream.this.embeddedTracksSelected[this.index]);
            ChunkSampleStream.this.embeddedTracksSelected[this.index] = false;
        }

        public int skipData(long arg4) {
            int v4;
            if(!ChunkSampleStream.this.loadingFinished || arg4 <= this.sampleQueue.getLargestQueuedTimestampUs()) {
                v4 = this.sampleQueue.advanceTo(arg4, true, true);
                if(v4 == -1) {
                    v4 = 0;
                }
            }
            else {
                v4 = this.sampleQueue.advanceToEnd();
            }

            if(v4 > 0) {
                this.maybeNotifyTrackFormatChanged();
            }

            return v4;
        }
    }

    public interface com.google.android.exoplayer2.source.chunk.ChunkSampleStream$ReleaseCallback {
        void onSampleStreamReleased(ChunkSampleStream arg1);
    }

    private static final String TAG = "ChunkSampleStream";
    private final com.google.android.exoplayer2.source.SequenceableLoader$Callback callback;
    private final ChunkSource chunkSource;
    long decodeOnlyUntilPositionUs;
    private final SampleQueue[] embeddedSampleQueues;
    private final Format[] embeddedTrackFormats;
    private final int[] embeddedTrackTypes;
    private final boolean[] embeddedTracksSelected;
    private final EventDispatcher eventDispatcher;
    private long lastSeekPositionUs;
    private final Loader loader;
    boolean loadingFinished;
    private final BaseMediaChunkOutput mediaChunkOutput;
    private final ArrayList mediaChunks;
    private final int minLoadableRetryCount;
    private final ChunkHolder nextChunkHolder;
    private long pendingResetPositionUs;
    private Format primaryDownstreamTrackFormat;
    private final SampleQueue primarySampleQueue;
    public final int primaryTrackType;
    private final List readOnlyMediaChunks;
    private com.google.android.exoplayer2.source.chunk.ChunkSampleStream$ReleaseCallback releaseCallback;

    public ChunkSampleStream(int arg1, int[] arg2, Format[] arg3, ChunkSource arg4, com.google.android.exoplayer2.source.SequenceableLoader$Callback arg5, Allocator arg6, long arg7, int arg9, EventDispatcher arg10) {
        super();
        this.primaryTrackType = arg1;
        this.embeddedTrackTypes = arg2;
        this.embeddedTrackFormats = arg3;
        this.chunkSource = arg4;
        this.callback = arg5;
        this.eventDispatcher = arg10;
        this.minLoadableRetryCount = arg9;
        this.loader = new Loader("Loader:ChunkSampleStream");
        this.nextChunkHolder = new ChunkHolder();
        this.mediaChunks = new ArrayList();
        this.readOnlyMediaChunks = Collections.unmodifiableList(this.mediaChunks);
        int v3 = 0;
        int v4 = arg2 == null ? 0 : arg2.length;
        this.embeddedSampleQueues = new SampleQueue[v4];
        this.embeddedTracksSelected = new boolean[v4];
        int v5 = v4 + 1;
        int[] v9 = new int[v5];
        SampleQueue[] v5_1 = new SampleQueue[v5];
        this.primarySampleQueue = new SampleQueue(arg6);
        v9[0] = arg1;
        v5_1[0] = this.primarySampleQueue;
        while(v3 < v4) {
            SampleQueue v1 = new SampleQueue(arg6);
            this.embeddedSampleQueues[v3] = v1;
            int v10 = v3 + 1;
            v5_1[v10] = v1;
            v9[v10] = arg2[v3];
            v3 = v10;
        }

        this.mediaChunkOutput = new BaseMediaChunkOutput(v9, v5_1);
        this.pendingResetPositionUs = arg7;
        this.lastSeekPositionUs = arg7;
    }

    static boolean[] access$000(ChunkSampleStream arg0) {
        return arg0.embeddedTracksSelected;
    }

    static int[] access$100(ChunkSampleStream arg0) {
        return arg0.embeddedTrackTypes;
    }

    static Format[] access$200(ChunkSampleStream arg0) {
        return arg0.embeddedTrackFormats;
    }

    static long access$300(ChunkSampleStream arg2) {
        return arg2.lastSeekPositionUs;
    }

    static EventDispatcher access$400(ChunkSampleStream arg0) {
        return arg0.eventDispatcher;
    }

    public boolean continueLoading(long arg23) {
        long v4;
        List v3;
        ChunkSampleStream v0 = this;
        int v2 = 0;
        if(!v0.loadingFinished) {
            if(v0.loader.isLoading()) {
            }
            else {
                boolean v1 = this.isPendingReset();
                if(v1) {
                    v3 = Collections.emptyList();
                    v4 = v0.pendingResetPositionUs;
                }
                else {
                    v3 = v0.readOnlyMediaChunks;
                    v4 = this.getLastMediaChunk().endTimeUs;
                }

                List v11 = v3;
                long v9 = v4;
                v0.chunkSource.getNextChunk(arg23, v9, v11, v0.nextChunkHolder);
                boolean v3_1 = v0.nextChunkHolder.endOfStream;
                Chunk v4_1 = v0.nextChunkHolder.chunk;
                v0.nextChunkHolder.clear();
                long v5 = -9223372036854775807L;
                if(v3_1) {
                    v0.pendingResetPositionUs = v5;
                    v0.loadingFinished = true;
                    return 1;
                }

                if(v4_1 == null) {
                    return 0;
                }

                if(v0.isMediaChunk(v4_1)) {
                    Chunk v3_2 = v4_1;
                    if(v1) {
                        if(((BaseMediaChunk)v3_2).startTimeUs == v0.pendingResetPositionUs) {
                            v2 = 1;
                        }

                        long v1_1 = v2 != 0 ? -9223372036854775808L : v0.pendingResetPositionUs;
                        v0.decodeOnlyUntilPositionUs = v1_1;
                        v0.pendingResetPositionUs = v5;
                    }

                    ((BaseMediaChunk)v3_2).init(v0.mediaChunkOutput);
                    v0.mediaChunks.add(v3_2);
                }

                v0.eventDispatcher.loadStarted(v4_1.dataSpec, v4_1.dataSpec.uri, v4_1.type, v0.primaryTrackType, v4_1.trackFormat, v4_1.trackSelectionReason, v4_1.trackSelectionData, v4_1.startTimeUs, v4_1.endTimeUs, v0.loader.startLoading(((Loadable)v4_1), ((Callback)v0), v0.minLoadableRetryCount));
                return 1;
            }
        }

        return 0;
    }

    public void discardBuffer(long arg5, boolean arg7) {
        int v0 = this.primarySampleQueue.getFirstIndex();
        this.primarySampleQueue.discardTo(arg5, arg7, true);
        int v5 = this.primarySampleQueue.getFirstIndex();
        if(v5 > v0) {
            long v0_1 = this.primarySampleQueue.getFirstTimestampUs();
            int v6;
            for(v6 = 0; v6 < this.embeddedSampleQueues.length; ++v6) {
                this.embeddedSampleQueues[v6].discardTo(v0_1, arg7, this.embeddedTracksSelected[v6]);
            }

            this.discardDownstreamMediaChunks(v5);
        }
    }

    private void discardDownstreamMediaChunks(int arg3) {
        arg3 = this.primaryStreamIndexToMediaChunkIndex(arg3, 0);
        if(arg3 > 0) {
            Util.removeRange(this.mediaChunks, 0, arg3);
        }
    }

    private BaseMediaChunk discardUpstreamMediaChunksFromIndex(int arg4) {
        Object v0 = this.mediaChunks.get(arg4);
        Util.removeRange(this.mediaChunks, arg4, this.mediaChunks.size());
        SampleQueue v4 = this.primarySampleQueue;
        int v1;
        for(v1 = 0; true; ++v1) {
            v4.discardUpstreamSamples(((BaseMediaChunk)v0).getFirstSampleIndex(v1));
            if(v1 >= this.embeddedSampleQueues.length) {
                break;
            }

            v4 = this.embeddedSampleQueues[v1];
        }

        return ((BaseMediaChunk)v0);
    }

    public long getAdjustedSeekPositionUs(long arg2, SeekParameters arg4) {
        return this.chunkSource.getAdjustedSeekPositionUs(arg2, arg4);
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
        BaseMediaChunk v2 = this.getLastMediaChunk();
        if(v2.isLoadCompleted()) {
        }
        else if(this.mediaChunks.size() > 1) {
            v2_1 = this.mediaChunks.get(this.mediaChunks.size() - 2);
        }
        else {
            v2 = null;
        }

        if((((BaseMediaChunk)v2_1)) != null) {
            v0 = Math.max(v0, ((BaseMediaChunk)v2_1).endTimeUs);
        }

        return Math.max(v0, this.primarySampleQueue.getLargestQueuedTimestampUs());
    }

    public ChunkSource getChunkSource() {
        return this.chunkSource;
    }

    private BaseMediaChunk getLastMediaChunk() {
        return this.mediaChunks.get(this.mediaChunks.size() - 1);
    }

    public long getNextLoadPositionUs() {
        if(this.isPendingReset()) {
            return this.pendingResetPositionUs;
        }

        long v0 = this.loadingFinished ? -9223372036854775808L : this.getLastMediaChunk().endTimeUs;
        return v0;
    }

    private boolean haveReadFromMediaChunk(int arg6) {
        Object v6 = this.mediaChunks.get(arg6);
        if(this.primarySampleQueue.getReadIndex() > ((BaseMediaChunk)v6).getFirstSampleIndex(0)) {
            return 1;
        }

        int v0 = 0;
        do {
            if(v0 >= this.embeddedSampleQueues.length) {
                return 0;
            }

            int v2 = this.embeddedSampleQueues[v0].getReadIndex();
            ++v0;
        }
        while(v2 <= ((BaseMediaChunk)v6).getFirstSampleIndex(v0));

        return 1;
    }

    private boolean isMediaChunk(Chunk arg1) {
        return arg1 instanceof BaseMediaChunk;
    }

    boolean isPendingReset() {
        boolean v0 = this.pendingResetPositionUs != -9223372036854775807L ? true : false;
        return v0;
    }

    public boolean isReady() {
        boolean v0;
        if(!this.loadingFinished) {
            if(!this.isPendingReset() && (this.primarySampleQueue.hasNextSample())) {
                goto label_10;
            }

            v0 = false;
        }
        else {
        label_10:
            v0 = true;
        }

        return v0;
    }

    private void maybeNotifyPrimaryTrackFormatChanged(int arg9) {
        Object v9 = this.mediaChunks.get(arg9);
        Format v7 = ((BaseMediaChunk)v9).trackFormat;
        if(!v7.equals(this.primaryDownstreamTrackFormat)) {
            this.eventDispatcher.downstreamFormatChanged(this.primaryTrackType, v7, ((BaseMediaChunk)v9).trackSelectionReason, ((BaseMediaChunk)v9).trackSelectionData, ((BaseMediaChunk)v9).startTimeUs);
        }

        this.primaryDownstreamTrackFormat = v7;
    }

    private void maybeNotifyPrimaryTrackFormatChanged(int arg3, int arg4) {
        int v0 = this.primaryStreamIndexToMediaChunkIndex(arg3 - arg4, 0);
        arg3 = arg4 == 1 ? v0 : this.primaryStreamIndexToMediaChunkIndex(arg3 - 1, v0);
        while(v0 <= arg3) {
            this.maybeNotifyPrimaryTrackFormatChanged(v0);
            ++v0;
        }
    }

    public void maybeThrowError() {
        this.loader.maybeThrowError();
        if(!this.loader.isLoading()) {
            this.chunkSource.maybeThrowError();
        }
    }

    public void onLoadCanceled(Chunk arg21, long arg22, long arg24, boolean arg26) {
        ChunkSampleStream v0 = this;
        v0.eventDispatcher.loadCanceled(arg21.dataSpec, arg21.getUri(), arg21.type, v0.primaryTrackType, arg21.trackFormat, arg21.trackSelectionReason, arg21.trackSelectionData, arg21.startTimeUs, arg21.endTimeUs, arg22, arg24, arg21.bytesLoaded());
        if(!arg26) {
            v0.primarySampleQueue.reset();
            SampleQueue[] v1 = v0.embeddedSampleQueues;
            int v2 = v1.length;
            int v3;
            for(v3 = 0; v3 < v2; ++v3) {
                v1[v3].reset();
            }

            v0.callback.onContinueLoadingRequested(((SequenceableLoader)v0));
        }
    }

    public void onLoadCanceled(Loadable arg1, long arg2, long arg4, boolean arg6) {
        this.onLoadCanceled(((Chunk)arg1), arg2, arg4, arg6);
    }

    public void onLoadCompleted(Chunk arg21, long arg22, long arg24) {
        this.chunkSource.onChunkLoadCompleted(arg21);
        this.eventDispatcher.loadCompleted(arg21.dataSpec, arg21.getUri(), arg21.type, this.primaryTrackType, arg21.trackFormat, arg21.trackSelectionReason, arg21.trackSelectionData, arg21.startTimeUs, arg21.endTimeUs, arg22, arg24, arg21.bytesLoaded());
        this.callback.onContinueLoadingRequested(this);
    }

    public void onLoadCompleted(Loadable arg1, long arg2, long arg4) {
        this.onLoadCompleted(((Chunk)arg1), arg2, arg4);
    }

    public LoadErrorAction onLoadError(Chunk arg24, long arg25, long arg27, IOException arg29, int arg30) {
        // Method was not decompiled
    }

    public LoadErrorAction onLoadError(Loadable arg1, long arg2, long arg4, IOException arg6, int arg7) {
        return this.onLoadError(((Chunk)arg1), arg2, arg4, arg6, arg7);
    }

    public void onLoaderReleased() {
        this.primarySampleQueue.reset();
        SampleQueue[] v0 = this.embeddedSampleQueues;
        int v1 = v0.length;
        int v2;
        for(v2 = 0; v2 < v1; ++v2) {
            v0[v2].reset();
        }

        if(this.releaseCallback != null) {
            this.releaseCallback.onSampleStreamReleased(this);
        }
    }

    private int primaryStreamIndexToMediaChunkIndex(int arg3, int arg4) {
        do {
            ++arg4;
            if(arg4 >= this.mediaChunks.size()) {
                goto label_11;
            }
        }
        while(this.mediaChunks.get(arg4).getFirstSampleIndex(0) <= arg3);

        return arg4 - 1;
    label_11:
        return this.mediaChunks.size() - 1;
    }

    public int readData(FormatHolder arg8, DecoderInputBuffer arg9, boolean arg10) {
        if(this.isPendingReset()) {
            return -3;
        }

        int v8 = this.primarySampleQueue.read(arg8, arg9, arg10, this.loadingFinished, this.decodeOnlyUntilPositionUs);
        if(v8 == -4) {
            this.maybeNotifyPrimaryTrackFormatChanged(this.primarySampleQueue.getReadIndex(), 1);
        }

        return v8;
    }

    public void reevaluateBuffer(long arg7) {
        if(!this.loader.isLoading()) {
            if(this.isPendingReset()) {
            }
            else {
                int v0 = this.mediaChunks.size();
                int v7 = this.chunkSource.getPreferredQueueSize(arg7, this.readOnlyMediaChunks);
                if(v0 <= v7) {
                    return;
                }

                while(true) {
                    if(v7 >= v0) {
                        break;
                    }
                    else if(!this.haveReadFromMediaChunk(v7)) {
                    }
                    else {
                        ++v7;
                        continue;
                    }

                    goto label_20;
                }

                v7 = v0;
            label_20:
                if(v7 == v0) {
                    return;
                }

                long v4 = this.getLastMediaChunk().endTimeUs;
                BaseMediaChunk v7_1 = this.discardUpstreamMediaChunksFromIndex(v7);
                if(this.mediaChunks.isEmpty()) {
                    this.pendingResetPositionUs = this.lastSeekPositionUs;
                }

                this.loadingFinished = false;
                this.eventDispatcher.upstreamDiscarded(this.primaryTrackType, v7_1.startTimeUs, v4);
            }
        }
    }

    public void release() {
        this.release(null);
    }

    public void release(com.google.android.exoplayer2.source.chunk.ChunkSampleStream$ReleaseCallback arg4) {
        this.releaseCallback = arg4;
        this.primarySampleQueue.discardToEnd();
        SampleQueue[] v4 = this.embeddedSampleQueues;
        int v0 = v4.length;
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            v4[v1].discardToEnd();
        }

        this.loader.release(((ReleaseCallback)this));
    }

    public void seekToUs(long arg13) {
        long v3_1;
        boolean v0;
        this.lastSeekPositionUs = arg13;
        this.primarySampleQueue.rewind();
        int v2 = 0;
        if(this.isPendingReset()) {
            v0 = false;
        }
        else {
            Object v0_1 = null;
            int v3;
            for(v3 = 0; v3 < this.mediaChunks.size(); ++v3) {
                Object v4 = this.mediaChunks.get(v3);
                long v5 = ((BaseMediaChunk)v4).startTimeUs;
                if(v5 == arg13 && ((BaseMediaChunk)v4).seekTimeUs == -9223372036854775807L) {
                    v0_1 = v4;
                    break;
                }

                if(v5 > arg13) {
                    break;
                }
            }

            if(v0_1 != null) {
                v0 = this.primarySampleQueue.setReadPosition(((BaseMediaChunk)v0_1).getFirstSampleIndex(0));
                v3_1 = -9223372036854775808L;
            }
            else {
                SampleQueue v0_2 = this.primarySampleQueue;
                boolean v3_2 = arg13 < this.getNextLoadPositionUs() ? true : false;
                v0 = v0_2.advanceTo(arg13, true, v3_2) != -1 ? true : false;
                v3_1 = this.lastSeekPositionUs;
            }

            this.decodeOnlyUntilPositionUs = v3_1;
        }

        if(v0) {
            SampleQueue[] v0_3 = this.embeddedSampleQueues;
            v3 = v0_3.length;
            int v4_1;
            for(v4_1 = 0; v4_1 < v3; ++v4_1) {
                SampleQueue v5_1 = v0_3[v4_1];
                v5_1.rewind();
                v5_1.advanceTo(arg13, true, false);
            }
        }
        else {
            this.pendingResetPositionUs = arg13;
            this.loadingFinished = false;
            this.mediaChunks.clear();
            if(this.loader.isLoading()) {
                this.loader.cancelLoading();
            }
            else {
                this.primarySampleQueue.reset();
                SampleQueue[] v13 = this.embeddedSampleQueues;
                int v14 = v13.length;
                while(v2 < v14) {
                    v13[v2].reset();
                    ++v2;
                }
            }
        }
    }

    public EmbeddedSampleStream selectEmbeddedTrack(long arg3, int arg5) {
        int v0;
        for(v0 = 0; v0 < this.embeddedSampleQueues.length; ++v0) {
            if(this.embeddedTrackTypes[v0] == arg5) {
                Assertions.checkState(this.embeddedTracksSelected[v0] ^ 1);
                this.embeddedTracksSelected[v0] = true;
                this.embeddedSampleQueues[v0].rewind();
                this.embeddedSampleQueues[v0].advanceTo(arg3, true, true);
                return new EmbeddedSampleStream(this, this, this.embeddedSampleQueues[v0], v0);
            }
        }

        throw new IllegalStateException();
    }

    public int skipData(long arg5) {
        int v1 = 0;
        if(this.isPendingReset()) {
            return 0;
        }

        if(!this.loadingFinished || arg5 <= this.primarySampleQueue.getLargestQueuedTimestampUs()) {
            int v5 = this.primarySampleQueue.advanceTo(arg5, true, true);
            if(v5 == -1) {
            }
            else {
                v1 = v5;
            }
        }
        else {
            v1 = this.primarySampleQueue.advanceToEnd();
        }

        if(v1 > 0) {
            this.maybeNotifyPrimaryTrackFormatChanged(this.primarySampleQueue.getReadIndex(), v1);
        }

        return v1;
    }
}

