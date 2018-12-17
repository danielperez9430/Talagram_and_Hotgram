package com.google.android.exoplayer2.source;

import android.net.Uri;
import android.os.Handler;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.FormatHolder;
import com.google.android.exoplayer2.SeekParameters;
import com.google.android.exoplayer2.decoder.DecoderInputBuffer;
import com.google.android.exoplayer2.extractor.DefaultExtractorInput;
import com.google.android.exoplayer2.extractor.Extractor;
import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.PositionHolder;
import com.google.android.exoplayer2.extractor.SeekMap$SeekPoints;
import com.google.android.exoplayer2.extractor.SeekMap;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.upstream.Allocator;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.upstream.Loader$Callback;
import com.google.android.exoplayer2.upstream.Loader$LoadErrorAction;
import com.google.android.exoplayer2.upstream.Loader$Loadable;
import com.google.android.exoplayer2.upstream.Loader$ReleaseCallback;
import com.google.android.exoplayer2.upstream.Loader;
import com.google.android.exoplayer2.upstream.StatsDataSource;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.ConditionVariable;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.Util;
import java.io.EOFException;
import java.io.IOException;
import java.util.Arrays;

final class ExtractorMediaPeriod implements ExtractorOutput, MediaPeriod, UpstreamFormatChangedListener, Callback, ReleaseCallback {
    final class ExtractingLoadable implements Loadable {
        private final StatsDataSource dataSource;
        private DataSpec dataSpec;
        private final ExtractorHolder extractorHolder;
        private long length;
        private volatile boolean loadCanceled;
        private final ConditionVariable loadCondition;
        private boolean pendingExtractorSeek;
        private final PositionHolder positionHolder;
        private long seekTimeUs;
        private final Uri uri;

        public ExtractingLoadable(ExtractorMediaPeriod arg8, Uri arg9, DataSource arg10, ExtractorHolder arg11, ConditionVariable arg12) {
            ExtractorMediaPeriod.this = arg8;
            super();
            this.uri = Assertions.checkNotNull(arg9);
            this.dataSource = new StatsDataSource(arg10);
            this.extractorHolder = Assertions.checkNotNull(arg11);
            this.loadCondition = arg12;
            this.positionHolder = new PositionHolder();
            this.pendingExtractorSeek = true;
            this.length = -1;
            this.dataSpec = new DataSpec(arg9, this.positionHolder.position, -1, arg8.customCacheKey);
        }

        static DataSpec access$400(ExtractingLoadable arg0) {
            return arg0.dataSpec;
        }

        static StatsDataSource access$500(ExtractingLoadable arg0) {
            return arg0.dataSource;
        }

        static long access$600(ExtractingLoadable arg2) {
            return arg2.seekTimeUs;
        }

        static long access$700(ExtractingLoadable arg2) {
            return arg2.length;
        }

        public void cancelLoad() {
            this.loadCanceled = true;
        }

        public void load() {
            int v5_1;
            DefaultExtractorInput v4;
            DefaultExtractorInput v5;
            long v12;
            int v1 = 0;
            while(true) {
                if(v1 == 0 && !this.loadCanceled) {
                    DefaultExtractorInput v2 = null;
                    try {
                        v12 = this.positionHolder.position;
                        this.dataSpec = new DataSpec(this.uri, v12, -1, ExtractorMediaPeriod.this.customCacheKey);
                        this.length = this.dataSource.open(this.dataSpec);
                        if(this.length != -1) {
                            this.length += v12;
                        }

                        v5 = new DefaultExtractorInput(this.dataSource, v12, this.length);
                    }
                    catch(Throwable v0) {
                        v4 = v2;
                        break;
                    }

                    v4 = v5;
                    try {
                        Extractor v2_1 = this.extractorHolder.selectExtractor(((ExtractorInput)v4), this.dataSource.getUri());
                        if(this.pendingExtractorSeek) {
                            v2_1.seek(v12, this.seekTimeUs);
                            this.pendingExtractorSeek = false;
                        }

                        while(true) {
                        label_46:
                            if(v1 == 0 && !this.loadCanceled) {
                                this.loadCondition.block();
                                v5_1 = v2_1.read(((ExtractorInput)v4), this.positionHolder);
                                break;
                            }

                            goto label_72;
                        }
                    }
                    catch(Throwable v0) {
                        break;
                    }

                    try {
                        if(((ExtractorInput)v4).getPosition() > ExtractorMediaPeriod.this.continueLoadingCheckIntervalBytes + v12) {
                            v12 = ((ExtractorInput)v4).getPosition();
                            this.loadCondition.close();
                            ExtractorMediaPeriod.this.handler.post(ExtractorMediaPeriod.this.onContinueLoadingRequestedRunnable);
                        }
                    }
                    catch(Throwable v0) {
                        v1 = v5_1;
                        break;
                    }

                    v1 = v5_1;
                    goto label_46;
                label_72:
                    if(v1 == 1) {
                        v1 = 0;
                    }
                    else {
                        this.positionHolder.position = ((ExtractorInput)v4).getPosition();
                    }

                    Util.closeQuietly(this.dataSource);
                    continue;
                }

                return;
            }

            if(v1 != 1 && v4 != null) {
                this.positionHolder.position = ((ExtractorInput)v4).getPosition();
            }

            Util.closeQuietly(this.dataSource);
            throw v0;
        }

        public void setLoadPosition(long arg2, long arg4) {
            this.positionHolder.position = arg2;
            this.seekTimeUs = arg4;
            this.pendingExtractorSeek = true;
        }
    }

    final class ExtractorHolder {
        private Extractor extractor;
        private final ExtractorOutput extractorOutput;
        private final Extractor[] extractors;

        public ExtractorHolder(Extractor[] arg1, ExtractorOutput arg2) {
            super();
            this.extractors = arg1;
            this.extractorOutput = arg2;
        }

        public void release() {
            if(this.extractor != null) {
                this.extractor.release();
                this.extractor = null;
            }
        }

        public Extractor selectExtractor(ExtractorInput arg6, Uri arg7) {
            if(this.extractor != null) {
                return this.extractor;
            }

            Extractor[] v0 = this.extractors;
            int v1 = v0.length;
            int v2 = 0;
            while(v2 < v1) {
                Extractor v3 = v0[v2];
                try {
                    if(v3.sniff(arg6)) {
                        this.extractor = v3;
                        goto label_12;
                    }
                    else {
                        goto label_17;
                    }
                }
                catch(EOFException ) {
                label_17:
                    arg6.resetPeekPosition();
                    ++v2;
                    continue;
                }
                catch(Throwable v7) {
                    arg6.resetPeekPosition();
                    throw v7;
                label_12:
                    arg6.resetPeekPosition();
                    break;
                }

                break;
            }

            if(this.extractor != null) {
                this.extractor.init(this.extractorOutput);
                return this.extractor;
            }

            StringBuilder v0_1 = new StringBuilder();
            v0_1.append("None of the available extractors (");
            v0_1.append(Util.getCommaDelimitedSimpleClassNames(this.extractors));
            v0_1.append(") could read the stream.");
            throw new UnrecognizedInputFormatException(v0_1.toString(), arg7);
        }
    }

    interface Listener {
        void onSourceInfoRefreshed(long arg1, boolean arg2);
    }

    final class SampleStreamImpl implements SampleStream {
        private final int track;

        public SampleStreamImpl(ExtractorMediaPeriod arg1, int arg2) {
            ExtractorMediaPeriod.this = arg1;
            super();
            this.track = arg2;
        }

        static int access$300(SampleStreamImpl arg0) {
            return arg0.track;
        }

        public boolean isReady() {
            return ExtractorMediaPeriod.this.isReady(this.track);
        }

        public void maybeThrowError() {
            ExtractorMediaPeriod.this.maybeThrowError();
        }

        public int readData(FormatHolder arg3, DecoderInputBuffer arg4, boolean arg5) {
            return ExtractorMediaPeriod.this.readData(this.track, arg3, arg4, arg5);
        }

        public int skipData(long arg3) {
            return ExtractorMediaPeriod.this.skipData(this.track, arg3);
        }
    }

    private static final long DEFAULT_LAST_SAMPLE_DURATION_US = 10000;
    private int actualMinLoadableRetryCount;
    private final Allocator allocator;
    private com.google.android.exoplayer2.source.MediaPeriod$Callback callback;
    private final long continueLoadingCheckIntervalBytes;
    private final String customCacheKey;
    private final DataSource dataSource;
    private long durationUs;
    private int enabledTrackCount;
    private final EventDispatcher eventDispatcher;
    private int extractedSamplesCountAtStartOfLoad;
    private final ExtractorHolder extractorHolder;
    private final Handler handler;
    private boolean haveAudioVideoTracks;
    private long lastSeekPositionUs;
    private long length;
    private final Listener listener;
    private final ConditionVariable loadCondition;
    private final Loader loader;
    private boolean loadingFinished;
    private final Runnable maybeFinishPrepareRunnable;
    private final int minLoadableRetryCount;
    private boolean notifiedReadingStarted;
    private boolean notifyDiscontinuity;
    private final Runnable onContinueLoadingRequestedRunnable;
    private boolean pendingDeferredRetry;
    private long pendingResetPositionUs;
    private boolean prepared;
    private boolean released;
    private int[] sampleQueueTrackIds;
    private SampleQueue[] sampleQueues;
    private boolean sampleQueuesBuilt;
    private SeekMap seekMap;
    private boolean seenFirstTrackSelection;
    private boolean[] trackEnabledStates;
    private boolean[] trackFormatNotificationSent;
    private boolean[] trackIsAudioVideoFlags;
    private TrackGroupArray tracks;
    private final Uri uri;

    public ExtractorMediaPeriod(Uri arg1, DataSource arg2, Extractor[] arg3, int arg4, EventDispatcher arg5, Listener arg6, Allocator arg7, String arg8, int arg9) {
        super();
        this.uri = arg1;
        this.dataSource = arg2;
        this.minLoadableRetryCount = arg4;
        this.eventDispatcher = arg5;
        this.listener = arg6;
        this.allocator = arg7;
        this.customCacheKey = arg8;
        this.continueLoadingCheckIntervalBytes = ((long)arg9);
        this.loader = new Loader("Loader:ExtractorMediaPeriod");
        this.extractorHolder = new ExtractorHolder(arg3, ((ExtractorOutput)this));
        this.loadCondition = new ConditionVariable();
        this.maybeFinishPrepareRunnable = new Runnable() {
            public void run() {
                ExtractorMediaPeriod.access$000(ExtractorMediaPeriod.this);
            }
        };
        this.onContinueLoadingRequestedRunnable = new Runnable() {
            public void run() {
                if(!ExtractorMediaPeriod.access$100(ExtractorMediaPeriod.this)) {
                    ExtractorMediaPeriod.access$200(ExtractorMediaPeriod.this).onContinueLoadingRequested(ExtractorMediaPeriod.this);
                }
            }
        };
        this.handler = new Handler();
        this.sampleQueueTrackIds = new int[0];
        this.sampleQueues = new SampleQueue[0];
        this.pendingResetPositionUs = -9223372036854775807L;
        this.length = -1;
        this.durationUs = -9223372036854775807L;
        if(arg4 == -1) {
            arg4 = 3;
        }

        this.actualMinLoadableRetryCount = arg4;
        arg5.mediaPeriodCreated();
    }

    static void access$000(ExtractorMediaPeriod arg0) {
        arg0.maybeFinishPrepare();
    }

    static boolean access$100(ExtractorMediaPeriod arg0) {
        return arg0.released;
    }

    static Runnable access$1000(ExtractorMediaPeriod arg0) {
        return arg0.onContinueLoadingRequestedRunnable;
    }

    static Handler access$1100(ExtractorMediaPeriod arg0) {
        return arg0.handler;
    }

    static com.google.android.exoplayer2.source.MediaPeriod$Callback access$200(ExtractorMediaPeriod arg0) {
        return arg0.callback;
    }

    static String access$800(ExtractorMediaPeriod arg0) {
        return arg0.customCacheKey;
    }

    static long access$900(ExtractorMediaPeriod arg2) {
        return arg2.continueLoadingCheckIntervalBytes;
    }

    private boolean configureRetry(ExtractingLoadable arg7, int arg8) {
        // Method was not decompiled
    }

    public boolean continueLoading(long arg1) {
        if(!this.loadingFinished && !this.pendingDeferredRetry && (!this.prepared || this.enabledTrackCount != 0)) {
            boolean v1 = this.loadCondition.open();
            if(!this.loader.isLoading()) {
                this.startLoading();
                v1 = true;
            }

            return v1;
        }

        return 0;
    }

    private void copyLengthFromLoader(ExtractingLoadable arg6) {
        if(this.length == -1) {
            this.length = ExtractingLoadable.access$700(arg6);
        }
    }

    public void discardBuffer(long arg5, boolean arg7) {
        int v0 = this.sampleQueues.length;
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            this.sampleQueues[v1].discardTo(arg5, arg7, this.trackEnabledStates[v1]);
        }
    }

    public void endTracks() {
        this.sampleQueuesBuilt = true;
        this.handler.post(this.maybeFinishPrepareRunnable);
    }

    public long getAdjustedSeekPositionUs(long arg10, SeekParameters arg12) {
        if(!this.seekMap.isSeekable()) {
            return 0;
        }

        SeekPoints v0 = this.seekMap.getSeekPoints(arg10);
        return Util.resolveSeekPositionUs(arg10, arg12, v0.first.timeUs, v0.second.timeUs);
    }

    public long getBufferedPositionUs() {
        long v3;
        long v1 = -9223372036854775808L;
        if(this.loadingFinished) {
            return v1;
        }

        if(this.isPendingReset()) {
            return this.pendingResetPositionUs;
        }

        if(this.haveAudioVideoTracks) {
            v3 = 9223372036854775807L;
            int v0 = this.sampleQueues.length;
            int v5;
            for(v5 = 0; v5 < v0; ++v5) {
                if(this.trackIsAudioVideoFlags[v5]) {
                    v3 = Math.min(v3, this.sampleQueues[v5].getLargestQueuedTimestampUs());
                }
            }
        }
        else {
            v3 = this.getLargestQueuedTimestampUs();
        }

        if(v3 == v1) {
            v3 = this.lastSeekPositionUs;
        }

        return v3;
    }

    private int getExtractedSamplesCount() {
        SampleQueue[] v0 = this.sampleQueues;
        int v1 = v0.length;
        int v2 = 0;
        int v3 = 0;
        while(v2 < v1) {
            v3 += v0[v2].getWriteIndex();
            ++v2;
        }

        return v3;
    }

    private long getLargestQueuedTimestampUs() {
        SampleQueue[] v0 = this.sampleQueues;
        int v1 = v0.length;
        long v2 = -9223372036854775808L;
        int v4;
        for(v4 = 0; v4 < v1; ++v4) {
            v2 = Math.max(v2, v0[v4].getLargestQueuedTimestampUs());
        }

        return v2;
    }

    public long getNextLoadPositionUs() {
        long v0 = this.enabledTrackCount == 0 ? -9223372036854775808L : this.getBufferedPositionUs();
        return v0;
    }

    public TrackGroupArray getTrackGroups() {
        return this.tracks;
    }

    private static boolean isLoadableExceptionFatal(IOException arg0) {
        return arg0 instanceof UnrecognizedInputFormatException;
    }

    private boolean isPendingReset() {
        boolean v0 = this.pendingResetPositionUs != -9223372036854775807L ? true : false;
        return v0;
    }

    boolean isReady(int arg2) {
        boolean v2;
        if(!this.suppressRead()) {
            if(!this.loadingFinished && !this.sampleQueues[arg2].hasNextSample()) {
                goto label_10;
            }

            v2 = true;
        }
        else {
        label_10:
            v2 = false;
        }

        return v2;
    }

    private void maybeFinishPrepare() {
        if(!this.released && !this.prepared && this.seekMap != null) {
            if(!this.sampleQueuesBuilt) {
            }
            else {
                SampleQueue[] v0 = this.sampleQueues;
                int v1 = v0.length;
                int v3 = 0;
                while(true) {
                    if(v3 >= v1) {
                        break;
                    }
                    else if(v0[v3].getUpstreamFormat() == null) {
                        return;
                    }
                    else {
                        ++v3;
                        continue;
                    }

                    return;
                }

                this.loadCondition.close();
                int v0_1 = this.sampleQueues.length;
                TrackGroup[] v1_1 = new TrackGroup[v0_1];
                this.trackIsAudioVideoFlags = new boolean[v0_1];
                this.trackEnabledStates = new boolean[v0_1];
                this.trackFormatNotificationSent = new boolean[v0_1];
                this.durationUs = this.seekMap.getDurationUs();
                for(v3 = 0; true; ++v3) {
                    boolean v4 = true;
                    if(v3 >= v0_1) {
                        break;
                    }

                    Format v5 = this.sampleQueues[v3].getUpstreamFormat();
                    v1_1[v3] = new TrackGroup(new Format[]{v5});
                    String v5_1 = v5.sampleMimeType;
                    if(!MimeTypes.isVideo(v5_1)) {
                        if(MimeTypes.isAudio(v5_1)) {
                        }
                        else {
                            v4 = false;
                        }
                    }

                    this.trackIsAudioVideoFlags[v3] = v4;
                    this.haveAudioVideoTracks |= ((int)v4);
                }

                this.tracks = new TrackGroupArray(v1_1);
                if(this.minLoadableRetryCount == -1 && this.length == -1 && this.seekMap.getDurationUs() == -9223372036854775807L) {
                    this.actualMinLoadableRetryCount = 6;
                }

                this.prepared = true;
                this.listener.onSourceInfoRefreshed(this.durationUs, this.seekMap.isSeekable());
                this.callback.onPrepared(((MediaPeriod)this));
            }
        }
    }

    private void maybeNotifyTrackFormat(int arg10) {
        if(!this.trackFormatNotificationSent[arg10]) {
            Format v4 = this.tracks.get(arg10).getFormat(0);
            this.eventDispatcher.downstreamFormatChanged(MimeTypes.getTrackType(v4.sampleMimeType), v4, 0, null, this.lastSeekPositionUs);
            this.trackFormatNotificationSent[arg10] = true;
        }
    }

    private void maybeStartDeferredRetry(int arg4) {
        if((this.pendingDeferredRetry) && (this.trackIsAudioVideoFlags[arg4])) {
            if(this.sampleQueues[arg4].hasNextSample()) {
            }
            else {
                this.pendingResetPositionUs = 0;
                arg4 = 0;
                this.pendingDeferredRetry = false;
                this.notifyDiscontinuity = true;
                this.lastSeekPositionUs = 0;
                this.extractedSamplesCountAtStartOfLoad = 0;
                SampleQueue[] v0 = this.sampleQueues;
                int v1 = v0.length;
                while(arg4 < v1) {
                    v0[arg4].reset();
                    ++arg4;
                }

                this.callback.onContinueLoadingRequested(((SequenceableLoader)this));
            }
        }
    }

    void maybeThrowError() {
        this.loader.maybeThrowError(this.actualMinLoadableRetryCount);
    }

    public void maybeThrowPrepareError() {
        this.maybeThrowError();
    }

    public void onLoadCanceled(ExtractingLoadable arg20, long arg21, long arg23, boolean arg25) {
        ExtractorMediaPeriod v0 = this;
        v0.eventDispatcher.loadCanceled(ExtractingLoadable.access$400(arg20), ExtractingLoadable.access$500(arg20).getLastOpenedUri(), 1, -1, null, 0, null, ExtractingLoadable.access$600(arg20), v0.durationUs, arg21, arg23, ExtractingLoadable.access$500(arg20).getBytesRead());
        if(!arg25) {
            this.copyLengthFromLoader(arg20);
            SampleQueue[] v1 = v0.sampleQueues;
            int v2 = v1.length;
            int v3;
            for(v3 = 0; v3 < v2; ++v3) {
                v1[v3].reset();
            }

            if(v0.enabledTrackCount > 0) {
                v0.callback.onContinueLoadingRequested(((SequenceableLoader)v0));
            }
        }
    }

    public void onLoadCanceled(Loadable arg1, long arg2, long arg4, boolean arg6) {
        this.onLoadCanceled(((ExtractingLoadable)arg1), arg2, arg4, arg6);
    }

    public void onLoadCompleted(ExtractingLoadable arg24, long arg25, long arg27) {
        ExtractorMediaPeriod v0 = this;
        if(v0.durationUs == -9223372036854775807L) {
            long v1 = this.getLargestQueuedTimestampUs();
            if(v1 == -9223372036854775808L) {
                v1 = 0;
            }
            else {
                v1 += 10000;
            }

            v0.durationUs = v1;
            v0.listener.onSourceInfoRefreshed(v0.durationUs, v0.seekMap.isSeekable());
        }

        v0.eventDispatcher.loadCompleted(ExtractingLoadable.access$400(arg24), ExtractingLoadable.access$500(arg24).getLastOpenedUri(), 1, -1, null, 0, null, ExtractingLoadable.access$600(arg24), v0.durationUs, arg25, arg27, ExtractingLoadable.access$500(arg24).getBytesRead());
        this.copyLengthFromLoader(arg24);
        v0.loadingFinished = true;
        v0.callback.onContinueLoadingRequested(((SequenceableLoader)v0));
    }

    public void onLoadCompleted(Loadable arg1, long arg2, long arg4) {
        this.onLoadCompleted(((ExtractingLoadable)arg1), arg2, arg4);
    }

    public LoadErrorAction onLoadError(ExtractingLoadable arg27, long arg28, long arg30, IOException arg32, int arg33) {
        int v5;
        ExtractingLoadable v4;
        LoadErrorAction v1;
        ExtractorMediaPeriod v0 = this;
        this.copyLengthFromLoader(arg27);
        if(ExtractorMediaPeriod.isLoadableExceptionFatal(arg32)) {
            v1 = Loader.DONT_RETRY_FATAL;
        }
        else {
            int v1_1 = this.getExtractedSamplesCount();
            if(v1_1 > v0.extractedSamplesCountAtStartOfLoad) {
                v4 = arg27;
                v5 = 1;
            }
            else {
                v4 = arg27;
                v5 = 0;
            }

            if(v0.configureRetry(v4, v1_1)) {
                if(v5 != 0) {
                    v1 = Loader.RETRY_RESET_ERROR_COUNT;
                    goto label_25;
                }

                v1 = Loader.RETRY;
                goto label_25;
            }

            v1 = Loader.DONT_RETRY;
        }

    label_25:
        boolean v25 = v1 == Loader.DONT_RETRY || v1 == Loader.DONT_RETRY_FATAL ? true : false;
        v0.eventDispatcher.loadError(ExtractingLoadable.access$400(arg27), ExtractingLoadable.access$500(arg27).getLastOpenedUri(), 1, -1, null, 0, null, ExtractingLoadable.access$600(arg27), v0.durationUs, arg28, arg30, ExtractingLoadable.access$500(arg27).getBytesRead(), arg32, v25);
        return v1;
    }

    public LoadErrorAction onLoadError(Loadable arg1, long arg2, long arg4, IOException arg6, int arg7) {
        return this.onLoadError(((ExtractingLoadable)arg1), arg2, arg4, arg6, arg7);
    }

    public void onLoaderReleased() {
        SampleQueue[] v0 = this.sampleQueues;
        int v1 = v0.length;
        int v2;
        for(v2 = 0; v2 < v1; ++v2) {
            v0[v2].reset();
        }

        this.extractorHolder.release();
    }

    public void onUpstreamFormatChanged(Format arg2) {
        this.handler.post(this.maybeFinishPrepareRunnable);
    }

    public void prepare(com.google.android.exoplayer2.source.MediaPeriod$Callback arg1, long arg2) {
        this.callback = arg1;
        this.loadCondition.open();
        this.startLoading();
    }

    int readData(int arg10, FormatHolder arg11, DecoderInputBuffer arg12, boolean arg13) {
        int v1 = -3;
        if(this.suppressRead()) {
            return v1;
        }

        int v11 = this.sampleQueues[arg10].read(arg11, arg12, arg13, this.loadingFinished, this.lastSeekPositionUs);
        if(v11 == -4) {
            this.maybeNotifyTrackFormat(arg10);
        }
        else if(v11 == v1) {
            this.maybeStartDeferredRetry(arg10);
        }

        return v11;
    }

    public long readDiscontinuity() {
        if(!this.notifiedReadingStarted) {
            this.eventDispatcher.readingStarted();
            this.notifiedReadingStarted = true;
        }

        if((this.notifyDiscontinuity) && ((this.loadingFinished) || this.getExtractedSamplesCount() > this.extractedSamplesCountAtStartOfLoad)) {
            this.notifyDiscontinuity = false;
            return this.lastSeekPositionUs;
        }

        return -9223372036854775807L;
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
        this.callback = null;
        this.released = true;
        this.eventDispatcher.mediaPeriodReleased();
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

            if(v3 == 0 && ((this.trackIsAudioVideoFlags[v2]) || !this.haveAudioVideoTracks)) {
                return 0;
            }
        }

        return 1;
    }

    public void seekMap(SeekMap arg2) {
        this.seekMap = arg2;
        this.handler.post(this.maybeFinishPrepareRunnable);
    }

    public long seekToUs(long arg5) {
        if(this.seekMap.isSeekable()) {
        }
        else {
            arg5 = 0;
        }

        this.lastSeekPositionUs = arg5;
        int v0 = 0;
        this.notifyDiscontinuity = false;
        if(!this.isPendingReset() && (this.seekInsideBufferUs(arg5))) {
            return arg5;
        }

        this.pendingDeferredRetry = false;
        this.pendingResetPositionUs = arg5;
        this.loadingFinished = false;
        if(this.loader.isLoading()) {
            this.loader.cancelLoading();
        }
        else {
            SampleQueue[] v1 = this.sampleQueues;
            int v2 = v1.length;
            while(v0 < v2) {
                v1[v0].reset();
                ++v0;
            }
        }

        return arg5;
    }

    public long selectTracks(TrackSelection[] arg7, boolean[] arg8, SampleStream[] arg9, boolean[] arg10, long arg11) {
        SampleQueue[] v7;
        int v8;
        Assertions.checkState(this.prepared);
        int v0 = this.enabledTrackCount;
        int v1 = 0;
        int v2;
        for(v2 = 0; v2 < arg7.length; ++v2) {
            if(arg9[v2] != null && (arg7[v2] == null || !arg8[v2])) {
                int v3 = SampleStreamImpl.access$300(arg9[v2]);
                Assertions.checkState(this.trackEnabledStates[v3]);
                --this.enabledTrackCount;
                this.trackEnabledStates[v3] = false;
                arg9[v2] = null;
            }
        }

        if(this.seenFirstTrackSelection) {
            if(v0 != 0) {
                goto label_33;
            }

            goto label_31;
        }
        else if(arg11 != 0) {
        label_31:
            v8 = 1;
        }
        else {
        label_33:
            v8 = 0;
        }

        v0 = v8;
        for(v8 = 0; v8 < arg7.length; ++v8) {
            if(arg9[v8] == null && arg7[v8] != null) {
                TrackSelection v2_1 = arg7[v8];
                boolean v3_1 = v2_1.length() == 1 ? true : false;
                Assertions.checkState(v3_1);
                v3_1 = v2_1.getIndexInTrackGroup(0) == 0 ? true : false;
                Assertions.checkState(v3_1);
                v2 = this.tracks.indexOf(v2_1.getTrackGroup());
                Assertions.checkState(this.trackEnabledStates[v2] ^ 1);
                ++this.enabledTrackCount;
                this.trackEnabledStates[v2] = true;
                arg9[v8] = new SampleStreamImpl(this, v2);
                arg10[v8] = true;
                if(v0 != 0) {
                    goto label_87;
                }

                SampleQueue v0_1 = this.sampleQueues[v2];
                v0_1.rewind();
                if(v0_1.advanceTo(arg11, true, true) == -1 && v0_1.getReadIndex() != 0) {
                    v0 = 1;
                    goto label_87;
                }

                v0 = 0;
            }

        label_87:
        }

        if(this.enabledTrackCount == 0) {
            this.pendingDeferredRetry = false;
            this.notifyDiscontinuity = false;
            if(this.loader.isLoading()) {
                v7 = this.sampleQueues;
                v8 = v7.length;
                goto label_98;
            }
            else {
                v7 = this.sampleQueues;
                v8 = v7.length;
                goto label_108;
            }
        }
        else if(v0 != 0) {
            arg11 = this.seekToUs(arg11);
            while(true) {
                if(v1 < arg9.length) {
                    if(arg9[v1] != null) {
                        arg10[v1] = true;
                    }

                    ++v1;
                    continue;
                }

                goto label_122;
            }

            while(true) {
            label_108:
                if(v1 >= v8) {
                    goto label_122;
                }

                v7[v1].reset();
                ++v1;
            }

        label_98:
            while(v1 < v8) {
                v7[v1].discardToEnd();
                ++v1;
            }

            this.loader.cancelLoading();
        }

    label_122:
        this.seenFirstTrackSelection = true;
        return arg11;
    }

    int skipData(int arg6, long arg7) {
        int v1 = 0;
        if(this.suppressRead()) {
            return 0;
        }

        SampleQueue v0 = this.sampleQueues[arg6];
        if(!this.loadingFinished || arg7 <= v0.getLargestQueuedTimestampUs()) {
            int v7 = v0.advanceTo(arg7, true, true);
            if(v7 == -1) {
            }
            else {
                v1 = v7;
            }
        }
        else {
            v1 = v0.advanceToEnd();
        }

        if(v1 > 0) {
            this.maybeNotifyTrackFormat(arg6);
        }
        else {
            this.maybeStartDeferredRetry(arg6);
        }

        return v1;
    }

    private void startLoading() {
        ExtractorMediaPeriod v6 = this;
        ExtractingLoadable v7 = new ExtractingLoadable(this, v6.uri, v6.dataSource, v6.extractorHolder, v6.loadCondition);
        if(v6.prepared) {
            Assertions.checkState(this.isPendingReset());
            long v2 = -9223372036854775807L;
            if(v6.durationUs != v2 && v6.pendingResetPositionUs >= v6.durationUs) {
                v6.loadingFinished = true;
                v6.pendingResetPositionUs = v2;
                return;
            }

            v7.setLoadPosition(v6.seekMap.getSeekPoints(v6.pendingResetPositionUs).first.position, v6.pendingResetPositionUs);
            v6.pendingResetPositionUs = v2;
        }

        v6.extractedSamplesCountAtStartOfLoad = this.getExtractedSamplesCount();
        v6.eventDispatcher.loadStarted(ExtractingLoadable.access$400(v7), ExtractingLoadable.access$400(v7).uri, 1, -1, null, 0, null, ExtractingLoadable.access$600(v7), v6.durationUs, v6.loader.startLoading(((Loadable)v7), ((Callback)v6), v6.actualMinLoadableRetryCount));
    }

    private boolean suppressRead() {
        boolean v0 = (this.notifyDiscontinuity) || (this.isPendingReset()) ? true : false;
        return v0;
    }

    public TrackOutput track(int arg4, int arg5) {
        arg5 = this.sampleQueues.length;
        int v0;
        for(v0 = 0; v0 < arg5; ++v0) {
            if(this.sampleQueueTrackIds[v0] == arg4) {
                return this.sampleQueues[v0];
            }
        }

        SampleQueue v0_1 = new SampleQueue(this.allocator);
        v0_1.setUpstreamFormatChangeListener(((UpstreamFormatChangedListener)this));
        int v2 = arg5 + 1;
        this.sampleQueueTrackIds = Arrays.copyOf(this.sampleQueueTrackIds, v2);
        this.sampleQueueTrackIds[arg5] = arg4;
        this.sampleQueues = Arrays.copyOf(this.sampleQueues, v2);
        this.sampleQueues[arg5] = v0_1;
        return ((TrackOutput)v0_1);
    }
}

