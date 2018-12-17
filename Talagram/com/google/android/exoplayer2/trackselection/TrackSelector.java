package com.google.android.exoplayer2.trackselection;

import com.google.android.exoplayer2.RendererCapabilities;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.util.Assertions;

public abstract class TrackSelector {
    public interface InvalidationListener {
        void onTrackSelectionsInvalidated();
    }

    private BandwidthMeter bandwidthMeter;
    private InvalidationListener listener;

    public TrackSelector() {
        super();
    }

    protected final BandwidthMeter getBandwidthMeter() {
        return Assertions.checkNotNull(this.bandwidthMeter);
    }

    public final void init(InvalidationListener arg1, BandwidthMeter arg2) {
        this.listener = arg1;
        this.bandwidthMeter = arg2;
    }

    protected final void invalidate() {
        if(this.listener != null) {
            this.listener.onTrackSelectionsInvalidated();
        }
    }

    public abstract void onSelectionActivated(Object arg1);

    public abstract TrackSelectorResult selectTracks(RendererCapabilities[] arg1, TrackGroupArray arg2);
}

