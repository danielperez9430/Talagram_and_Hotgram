package com.google.android.exoplayer2.source;

import android.os.Handler;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.upstream.TransferListener;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

public abstract class CompositeMediaSource extends BaseMediaSource {
    final class ForwardingEventListener implements MediaSourceEventListener {
        private EventDispatcher eventDispatcher;
        private final Object id;

        public ForwardingEventListener(CompositeMediaSource arg2, Object arg3) {
            CompositeMediaSource.this = arg2;
            super();
            this.eventDispatcher = arg2.createEventDispatcher(null);
            this.id = arg3;
        }

        private boolean maybeUpdateEventDispatcher(int arg4, MediaPeriodId arg5) {
            Object v5;
            if(arg5 != null) {
                arg5 = CompositeMediaSource.this.getMediaPeriodIdForChildMediaPeriodId(this.id, arg5);
                if(arg5 == null) {
                    return 0;
                }
            }
            else {
                v5 = null;
            }

            arg4 = CompositeMediaSource.this.getWindowIndexForChildWindowIndex(this.id, arg4);
            if(this.eventDispatcher.windowIndex != arg4 || !Util.areEqual(this.eventDispatcher.mediaPeriodId, v5)) {
                this.eventDispatcher = CompositeMediaSource.this.createEventDispatcher(arg4, ((MediaPeriodId)v5), 0);
            }

            return 1;
        }

        private MediaLoadData maybeUpdateMediaLoadData(MediaLoadData arg15) {
            long v10 = CompositeMediaSource.this.getMediaTimeForChildMediaTime(this.id, arg15.mediaStartTimeMs);
            long v12 = CompositeMediaSource.this.getMediaTimeForChildMediaTime(this.id, arg15.mediaEndTimeMs);
            if(v10 == arg15.mediaStartTimeMs && v12 == arg15.mediaEndTimeMs) {
                return arg15;
            }

            return new MediaLoadData(arg15.dataType, arg15.trackType, arg15.trackFormat, arg15.trackSelectionReason, arg15.trackSelectionData, v10, v12);
        }

        public void onDownstreamFormatChanged(int arg1, MediaPeriodId arg2, MediaLoadData arg3) {
            if(this.maybeUpdateEventDispatcher(arg1, arg2)) {
                this.eventDispatcher.downstreamFormatChanged(this.maybeUpdateMediaLoadData(arg3));
            }
        }

        public void onLoadCanceled(int arg1, MediaPeriodId arg2, LoadEventInfo arg3, MediaLoadData arg4) {
            if(this.maybeUpdateEventDispatcher(arg1, arg2)) {
                this.eventDispatcher.loadCanceled(arg3, this.maybeUpdateMediaLoadData(arg4));
            }
        }

        public void onLoadCompleted(int arg1, MediaPeriodId arg2, LoadEventInfo arg3, MediaLoadData arg4) {
            if(this.maybeUpdateEventDispatcher(arg1, arg2)) {
                this.eventDispatcher.loadCompleted(arg3, this.maybeUpdateMediaLoadData(arg4));
            }
        }

        public void onLoadError(int arg1, MediaPeriodId arg2, LoadEventInfo arg3, MediaLoadData arg4, IOException arg5, boolean arg6) {
            if(this.maybeUpdateEventDispatcher(arg1, arg2)) {
                this.eventDispatcher.loadError(arg3, this.maybeUpdateMediaLoadData(arg4), arg5, arg6);
            }
        }

        public void onLoadStarted(int arg1, MediaPeriodId arg2, LoadEventInfo arg3, MediaLoadData arg4) {
            if(this.maybeUpdateEventDispatcher(arg1, arg2)) {
                this.eventDispatcher.loadStarted(arg3, this.maybeUpdateMediaLoadData(arg4));
            }
        }

        public void onMediaPeriodCreated(int arg1, MediaPeriodId arg2) {
            if(this.maybeUpdateEventDispatcher(arg1, arg2)) {
                this.eventDispatcher.mediaPeriodCreated();
            }
        }

        public void onMediaPeriodReleased(int arg1, MediaPeriodId arg2) {
            if(this.maybeUpdateEventDispatcher(arg1, arg2)) {
                this.eventDispatcher.mediaPeriodReleased();
            }
        }

        public void onReadingStarted(int arg1, MediaPeriodId arg2) {
            if(this.maybeUpdateEventDispatcher(arg1, arg2)) {
                this.eventDispatcher.readingStarted();
            }
        }

        public void onUpstreamDiscarded(int arg1, MediaPeriodId arg2, MediaLoadData arg3) {
            if(this.maybeUpdateEventDispatcher(arg1, arg2)) {
                this.eventDispatcher.upstreamDiscarded(this.maybeUpdateMediaLoadData(arg3));
            }
        }
    }

    final class MediaSourceAndListener {
        public final MediaSourceEventListener eventListener;
        public final SourceInfoRefreshListener listener;
        public final MediaSource mediaSource;

        public MediaSourceAndListener(MediaSource arg1, SourceInfoRefreshListener arg2, MediaSourceEventListener arg3) {
            super();
            this.mediaSource = arg1;
            this.listener = arg2;
            this.eventListener = arg3;
        }
    }

    private final HashMap childSources;
    private Handler eventHandler;
    private TransferListener mediaTransferListener;
    private ExoPlayer player;

    protected CompositeMediaSource() {
        super();
        this.childSources = new HashMap();
    }

    protected MediaPeriodId getMediaPeriodIdForChildMediaPeriodId(Object arg1, MediaPeriodId arg2) {
        return arg2;
    }

    protected long getMediaTimeForChildMediaTime(Object arg1, long arg2) {
        return arg2;
    }

    protected int getWindowIndexForChildWindowIndex(Object arg1, int arg2) {
        return arg2;
    }

    public void maybeThrowSourceInfoRefreshError() {
        Iterator v0 = this.childSources.values().iterator();
        while(v0.hasNext()) {
            v0.next().mediaSource.maybeThrowSourceInfoRefreshError();
        }
    }

    protected abstract void onChildSourceInfoRefreshed(Object arg1, MediaSource arg2, Timeline arg3, Object arg4);

    protected final void prepareChildSource(Object arg5, MediaSource arg6) {
        Assertions.checkArgument(this.childSources.containsKey(arg5) ^ 1);
        com.google.android.exoplayer2.source.CompositeMediaSource$1 v0 = new SourceInfoRefreshListener(arg5) {
            public void onSourceInfoRefreshed(MediaSource arg3, Timeline arg4, Object arg5) {
                CompositeMediaSource.this.onChildSourceInfoRefreshed(this.val$id, arg3, arg4, arg5);
            }
        };
        ForwardingEventListener v1 = new ForwardingEventListener(this, arg5);
        this.childSources.put(arg5, new MediaSourceAndListener(arg6, ((SourceInfoRefreshListener)v0), ((MediaSourceEventListener)v1)));
        arg6.addEventListener(Assertions.checkNotNull(this.eventHandler), ((MediaSourceEventListener)v1));
        arg6.prepareSource(Assertions.checkNotNull(this.player), false, ((SourceInfoRefreshListener)v0), this.mediaTransferListener);
    }

    public void prepareSourceInternal(ExoPlayer arg1, boolean arg2, TransferListener arg3) {
        this.player = arg1;
        this.mediaTransferListener = arg3;
        this.eventHandler = new Handler();
    }

    protected final void releaseChildSource(Object arg3) {
        arg3 = Assertions.checkNotNull(this.childSources.remove(arg3));
        ((MediaSourceAndListener)arg3).mediaSource.releaseSource(((MediaSourceAndListener)arg3).listener);
        ((MediaSourceAndListener)arg3).mediaSource.removeEventListener(((MediaSourceAndListener)arg3).eventListener);
    }

    public void releaseSourceInternal() {
        Iterator v0 = this.childSources.values().iterator();
        while(v0.hasNext()) {
            Object v1 = v0.next();
            ((MediaSourceAndListener)v1).mediaSource.releaseSource(((MediaSourceAndListener)v1).listener);
            ((MediaSourceAndListener)v1).mediaSource.removeEventListener(((MediaSourceAndListener)v1).eventListener);
        }

        this.childSources.clear();
        this.player = null;
    }
}

