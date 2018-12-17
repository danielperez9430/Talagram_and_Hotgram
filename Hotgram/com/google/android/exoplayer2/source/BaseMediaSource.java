package com.google.android.exoplayer2.source;

import android.os.Handler;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.upstream.TransferListener;
import com.google.android.exoplayer2.util.Assertions;
import java.util.ArrayList;
import java.util.Iterator;

public abstract class BaseMediaSource implements MediaSource {
    private final EventDispatcher eventDispatcher;
    private Object manifest;
    private ExoPlayer player;
    private final ArrayList sourceInfoListeners;
    private Timeline timeline;

    public BaseMediaSource() {
        super();
        this.sourceInfoListeners = new ArrayList(1);
        this.eventDispatcher = new EventDispatcher();
    }

    public final void addEventListener(Handler arg2, MediaSourceEventListener arg3) {
        this.eventDispatcher.addEventListener(arg2, arg3);
    }

    protected final EventDispatcher createEventDispatcher(int arg2, MediaPeriodId arg3, long arg4) {
        return this.eventDispatcher.withParameters(arg2, arg3, arg4);
    }

    protected final EventDispatcher createEventDispatcher(MediaPeriodId arg5) {
        return this.eventDispatcher.withParameters(0, arg5, 0);
    }

    protected final EventDispatcher createEventDispatcher(MediaPeriodId arg3, long arg4) {
        boolean v1 = arg3 != null ? true : false;
        Assertions.checkArgument(v1);
        return this.eventDispatcher.withParameters(0, arg3, arg4);
    }

    public final void prepareSource(ExoPlayer arg2, boolean arg3, SourceInfoRefreshListener arg4) {
        this.prepareSource(arg2, arg3, arg4, null);
    }

    public final void prepareSource(ExoPlayer arg2, boolean arg3, SourceInfoRefreshListener arg4, TransferListener arg5) {
        boolean v0 = this.player == null || this.player == arg2 ? true : false;
        Assertions.checkArgument(v0);
        this.sourceInfoListeners.add(arg4);
        if(this.player == null) {
            this.player = arg2;
            this.prepareSourceInternal(arg2, arg3, arg5);
        }
        else if(this.timeline != null) {
            arg4.onSourceInfoRefreshed(((MediaSource)this), this.timeline, this.manifest);
        }
    }

    protected abstract void prepareSourceInternal(ExoPlayer arg1, boolean arg2, TransferListener arg3);

    protected final void refreshSourceInfo(Timeline arg3, Object arg4) {
        this.timeline = arg3;
        this.manifest = arg4;
        Iterator v0 = this.sourceInfoListeners.iterator();
        while(v0.hasNext()) {
            v0.next().onSourceInfoRefreshed(((MediaSource)this), arg3, arg4);
        }
    }

    public final void releaseSource(SourceInfoRefreshListener arg2) {
        this.sourceInfoListeners.remove(arg2);
        if(this.sourceInfoListeners.isEmpty()) {
            this.player = null;
            this.timeline = null;
            this.manifest = null;
            this.releaseSourceInternal();
        }
    }

    protected abstract void releaseSourceInternal();

    public final void removeEventListener(MediaSourceEventListener arg2) {
        this.eventDispatcher.removeEventListener(arg2);
    }
}

