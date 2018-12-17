package com.google.android.exoplayer2.source;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.FormatHolder;
import com.google.android.exoplayer2.SeekParameters;
import com.google.android.exoplayer2.decoder.DecoderInputBuffer;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.upstream.DataSource$Factory;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.upstream.Loader$Callback;
import com.google.android.exoplayer2.upstream.Loader$LoadErrorAction;
import com.google.android.exoplayer2.upstream.Loader$Loadable;
import com.google.android.exoplayer2.upstream.Loader;
import com.google.android.exoplayer2.upstream.StatsDataSource;
import com.google.android.exoplayer2.upstream.TransferListener;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.Util;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

final class SingleSampleMediaPeriod implements MediaPeriod, Callback {
    class com.google.android.exoplayer2.source.SingleSampleMediaPeriod$1 {
    }

    final class SampleStreamImpl implements SampleStream {
        private static final int STREAM_STATE_END_OF_STREAM = 2;
        private static final int STREAM_STATE_SEND_FORMAT = 0;
        private static final int STREAM_STATE_SEND_SAMPLE = 1;
        private boolean formatSent;
        private int streamState;

        SampleStreamImpl(SingleSampleMediaPeriod arg1, com.google.android.exoplayer2.source.SingleSampleMediaPeriod$1 arg2) {
            this(arg1);
        }

        private SampleStreamImpl(SingleSampleMediaPeriod arg1) {
            SingleSampleMediaPeriod.this = arg1;
            super();
        }

        public boolean isReady() {
            return SingleSampleMediaPeriod.this.loadingFinished;
        }

        public void maybeThrowError() {
            if(!SingleSampleMediaPeriod.this.treatLoadErrorsAsEndOfStream) {
                SingleSampleMediaPeriod.this.loader.maybeThrowError();
            }
        }

        public int readData(FormatHolder arg7, DecoderInputBuffer arg8, boolean arg9) {
            int v1 = -4;
            int v2 = 4;
            int v3 = 2;
            if(this.streamState == v3) {
                arg8.addFlag(v2);
                return v1;
            }

            if(!arg9) {
                if(this.streamState == 0) {
                }
                else if(SingleSampleMediaPeriod.this.loadingFinished) {
                    if(SingleSampleMediaPeriod.this.loadingSucceeded) {
                        arg8.timeUs = 0;
                        arg8.addFlag(1);
                        arg8.ensureSpaceForWrite(SingleSampleMediaPeriod.this.sampleSize);
                        arg8.data.put(SingleSampleMediaPeriod.this.sampleData, 0, SingleSampleMediaPeriod.this.sampleSize);
                        this.sendFormat();
                    }
                    else {
                        arg8.addFlag(v2);
                    }

                    this.streamState = v3;
                    return v1;
                }
                else {
                    return -3;
                }
            }

            arg7.format = SingleSampleMediaPeriod.this.format;
            this.streamState = 1;
            return -5;
        }

        public void reset() {
            if(this.streamState == 2) {
                this.streamState = 1;
            }
        }

        private void sendFormat() {
            if(!this.formatSent) {
                SingleSampleMediaPeriod.this.eventDispatcher.downstreamFormatChanged(MimeTypes.getTrackType(SingleSampleMediaPeriod.this.format.sampleMimeType), SingleSampleMediaPeriod.this.format, 0, null, 0);
                this.formatSent = true;
            }
        }

        public int skipData(long arg4) {
            if(arg4 > 0) {
                int v5 = 2;
                if(this.streamState != v5) {
                    this.streamState = v5;
                    this.sendFormat();
                    return 1;
                }
            }

            return 0;
        }
    }

    final class SourceLoadable implements Loadable {
        private final StatsDataSource dataSource;
        public final DataSpec dataSpec;
        private byte[] sampleData;

        public SourceLoadable(DataSpec arg1, DataSource arg2) {
            super();
            this.dataSpec = arg1;
            this.dataSource = new StatsDataSource(arg2);
        }

        static StatsDataSource access$100(SourceLoadable arg0) {
            return arg0.dataSource;
        }

        static byte[] access$200(SourceLoadable arg0) {
            return arg0.sampleData;
        }

        public void cancelLoad() {
        }

        public void load() {
            byte[] v1;
            this.dataSource.resetBytesRead();
            try {
                this.dataSource.open(this.dataSpec);
                int v0_1;
                for(v0_1 = 0; v0_1 != -1; v0_1 = this.dataSource.read(this.sampleData, v0_1, this.sampleData.length - v0_1)) {
                    v0_1 = ((int)this.dataSource.getBytesRead());
                    if(this.sampleData == null) {
                        v1 = new byte[1024];
                        goto label_15;
                    }
                    else if(v0_1 == this.sampleData.length) {
                        v1 = Arrays.copyOf(this.sampleData, this.sampleData.length * 2);
                    label_15:
                        this.sampleData = v1;
                    }
                }
            }
            catch(Throwable v0) {
                goto label_38;
            }

            Util.closeQuietly(this.dataSource);
            return;
        label_38:
            Util.closeQuietly(this.dataSource);
            throw v0;
        }
    }

    private static final int INITIAL_SAMPLE_SIZE = 1024;
    private final Factory dataSourceFactory;
    private final DataSpec dataSpec;
    private final long durationUs;
    private final EventDispatcher eventDispatcher;
    final Format format;
    final Loader loader;
    boolean loadingFinished;
    boolean loadingSucceeded;
    private final int minLoadableRetryCount;
    boolean notifiedReadingStarted;
    byte[] sampleData;
    int sampleSize;
    private final ArrayList sampleStreams;
    private final TrackGroupArray tracks;
    private final TransferListener transferListener;
    final boolean treatLoadErrorsAsEndOfStream;

    public SingleSampleMediaPeriod(DataSpec arg1, Factory arg2, TransferListener arg3, Format arg4, long arg5, int arg7, EventDispatcher arg8, boolean arg9) {
        super();
        this.dataSpec = arg1;
        this.dataSourceFactory = arg2;
        this.transferListener = arg3;
        this.format = arg4;
        this.durationUs = arg5;
        this.minLoadableRetryCount = arg7;
        this.eventDispatcher = arg8;
        this.treatLoadErrorsAsEndOfStream = arg9;
        this.tracks = new TrackGroupArray(new TrackGroup[]{new TrackGroup(new Format[]{arg4})});
        this.sampleStreams = new ArrayList();
        this.loader = new Loader("Loader:SingleSampleMediaPeriod");
        arg8.mediaPeriodCreated();
    }

    static EventDispatcher access$300(SingleSampleMediaPeriod arg0) {
        return arg0.eventDispatcher;
    }

    public boolean continueLoading(long arg19) {
        SingleSampleMediaPeriod v0 = this;
        if(!v0.loadingFinished) {
            if(v0.loader.isLoading()) {
            }
            else {
                DataSource v1 = v0.dataSourceFactory.createDataSource();
                if(v0.transferListener != null) {
                    v1.addTransferListener(v0.transferListener);
                }

                v0.eventDispatcher.loadStarted(v0.dataSpec, v0.dataSpec.uri, 1, -1, v0.format, 0, null, 0, v0.durationUs, v0.loader.startLoading(new SourceLoadable(v0.dataSpec, v1), ((Callback)v0), v0.minLoadableRetryCount));
                return 1;
            }
        }

        return 0;
    }

    public void discardBuffer(long arg1, boolean arg3) {
    }

    public long getAdjustedSeekPositionUs(long arg1, SeekParameters arg3) {
        return arg1;
    }

    public long getBufferedPositionUs() {
        long v0 = this.loadingFinished ? -9223372036854775808L : 0;
        return v0;
    }

    public long getNextLoadPositionUs() {
        long v0 = (this.loadingFinished) || (this.loader.isLoading()) ? -9223372036854775808L : 0;
        return v0;
    }

    public TrackGroupArray getTrackGroups() {
        return this.tracks;
    }

    public void maybeThrowPrepareError() {
    }

    public void onLoadCanceled(SourceLoadable arg20, long arg21, long arg23, boolean arg25) {
        this.eventDispatcher.loadCanceled(arg20.dataSpec, SourceLoadable.access$100(arg20).getLastOpenedUri(), 1, -1, null, 0, null, 0, this.durationUs, arg21, arg23, SourceLoadable.access$100(arg20).getBytesRead());
    }

    public void onLoadCanceled(Loadable arg1, long arg2, long arg4, boolean arg6) {
        this.onLoadCanceled(((SourceLoadable)arg1), arg2, arg4, arg6);
    }

    public void onLoadCompleted(SourceLoadable arg20, long arg21, long arg23) {
        this.sampleSize = ((int)SourceLoadable.access$100(arg20).getBytesRead());
        this.sampleData = SourceLoadable.access$200(arg20);
        this.loadingFinished = true;
        this.loadingSucceeded = true;
        this.eventDispatcher.loadCompleted(arg20.dataSpec, SourceLoadable.access$100(arg20).getLastOpenedUri(), 1, -1, this.format, 0, null, 0, this.durationUs, arg21, arg23, ((long)this.sampleSize));
    }

    public void onLoadCompleted(Loadable arg1, long arg2, long arg4) {
        this.onLoadCompleted(((SourceLoadable)arg1), arg2, arg4);
    }

    public LoadErrorAction onLoadError(SourceLoadable arg24, long arg25, long arg27, IOException arg29, int arg30) {
        SingleSampleMediaPeriod v0 = this;
        boolean v1 = !v0.treatLoadErrorsAsEndOfStream || arg30 < v0.minLoadableRetryCount ? false : true;
        v0.eventDispatcher.loadError(arg24.dataSpec, SourceLoadable.access$100(arg24).getLastOpenedUri(), 1, -1, v0.format, 0, null, 0, v0.durationUs, arg25, arg27, SourceLoadable.access$100(arg24).getBytesRead(), arg29, v1);
        if(v1) {
            v0.loadingFinished = true;
            return Loader.DONT_RETRY;
        }

        return Loader.RETRY;
    }

    public LoadErrorAction onLoadError(Loadable arg1, long arg2, long arg4, IOException arg6, int arg7) {
        return this.onLoadError(((SourceLoadable)arg1), arg2, arg4, arg6, arg7);
    }

    public void prepare(com.google.android.exoplayer2.source.MediaPeriod$Callback arg1, long arg2) {
        arg1.onPrepared(((MediaPeriod)this));
    }

    public long readDiscontinuity() {
        if(!this.notifiedReadingStarted) {
            this.eventDispatcher.readingStarted();
            this.notifiedReadingStarted = true;
        }

        return -9223372036854775807L;
    }

    public void reevaluateBuffer(long arg1) {
    }

    public void release() {
        this.loader.release();
        this.eventDispatcher.mediaPeriodReleased();
    }

    public long seekToUs(long arg3) {
        int v0;
        for(v0 = 0; v0 < this.sampleStreams.size(); ++v0) {
            this.sampleStreams.get(v0).reset();
        }

        return arg3;
    }

    public long selectTracks(TrackSelection[] arg5, boolean[] arg6, SampleStream[] arg7, boolean[] arg8, long arg9) {
        int v0;
        for(v0 = 0; v0 < arg5.length; ++v0) {
            SampleStream v2 = null;
            if(arg7[v0] != null && (arg5[v0] == null || !arg6[v0])) {
                this.sampleStreams.remove(arg7[v0]);
                arg7[v0] = v2;
            }

            if(arg7[v0] == null && arg5[v0] != null) {
                SampleStreamImpl v1 = new SampleStreamImpl(this, ((com.google.android.exoplayer2.source.SingleSampleMediaPeriod$1)v2));
                this.sampleStreams.add(v1);
                arg7[v0] = ((SampleStream)v1);
                arg8[v0] = true;
            }
        }

        return arg9;
    }
}

