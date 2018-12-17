package com.google.android.exoplayer2;

import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.upstream.Allocator;
import com.google.android.exoplayer2.upstream.DefaultAllocator;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.PriorityTaskManager;
import com.google.android.exoplayer2.util.Util;

public class DefaultLoadControl implements LoadControl {
    public final class Builder {
        private DefaultAllocator allocator;
        private int bufferForPlaybackAfterRebufferMs;
        private int bufferForPlaybackMs;
        private int maxBufferMs;
        private int minBufferMs;
        private boolean prioritizeTimeOverSizeThresholds;
        private PriorityTaskManager priorityTaskManager;
        private int targetBufferBytes;

        public Builder() {
            super();
            this.allocator = null;
            this.minBufferMs = 15000;
            this.maxBufferMs = 50000;
            this.bufferForPlaybackMs = 2500;
            this.bufferForPlaybackAfterRebufferMs = 5000;
            this.targetBufferBytes = -1;
            this.prioritizeTimeOverSizeThresholds = true;
            this.priorityTaskManager = null;
        }

        public DefaultLoadControl createDefaultLoadControl() {
            if(this.allocator == null) {
                this.allocator = new DefaultAllocator(true, 65536);
            }

            return new DefaultLoadControl(this.allocator, this.minBufferMs, this.maxBufferMs, this.bufferForPlaybackMs, this.bufferForPlaybackAfterRebufferMs, this.targetBufferBytes, this.prioritizeTimeOverSizeThresholds, this.priorityTaskManager);
        }

        public Builder setAllocator(DefaultAllocator arg1) {
            this.allocator = arg1;
            return this;
        }

        public Builder setBufferDurationsMs(int arg1, int arg2, int arg3, int arg4) {
            this.minBufferMs = arg1;
            this.maxBufferMs = arg2;
            this.bufferForPlaybackMs = arg3;
            this.bufferForPlaybackAfterRebufferMs = arg4;
            return this;
        }

        public Builder setPrioritizeTimeOverSizeThresholds(boolean arg1) {
            this.prioritizeTimeOverSizeThresholds = arg1;
            return this;
        }

        public Builder setPriorityTaskManager(PriorityTaskManager arg1) {
            this.priorityTaskManager = arg1;
            return this;
        }

        public Builder setTargetBufferBytes(int arg1) {
            this.targetBufferBytes = arg1;
            return this;
        }
    }

    public static final int DEFAULT_BUFFER_FOR_PLAYBACK_AFTER_REBUFFER_MS = 5000;
    public static final int DEFAULT_BUFFER_FOR_PLAYBACK_MS = 2500;
    public static final int DEFAULT_MAX_BUFFER_MS = 50000;
    public static final int DEFAULT_MIN_BUFFER_MS = 15000;
    public static final boolean DEFAULT_PRIORITIZE_TIME_OVER_SIZE_THRESHOLDS = true;
    public static final int DEFAULT_TARGET_BUFFER_BYTES = -1;
    private final DefaultAllocator allocator;
    private final long bufferForPlaybackAfterRebufferUs;
    private final long bufferForPlaybackUs;
    private boolean isBuffering;
    private final long maxBufferUs;
    private final long minBufferUs;
    private final boolean prioritizeTimeOverSizeThresholds;
    private final PriorityTaskManager priorityTaskManager;
    private final int targetBufferBytesOverwrite;
    private int targetBufferSize;

    public DefaultLoadControl() {
        this(new DefaultAllocator(true, 65536));
    }

    @Deprecated public DefaultLoadControl(DefaultAllocator arg9) {
        this(arg9, 15000, 50000, 2500, 5000, -1, true);
    }

    @Deprecated public DefaultLoadControl(DefaultAllocator arg10, int arg11, int arg12, int arg13, int arg14, int arg15, boolean arg16) {
        this(arg10, arg11, arg12, arg13, arg14, arg15, arg16, null);
    }

    @Deprecated public DefaultLoadControl(DefaultAllocator arg4, int arg5, int arg6, int arg7, int arg8, int arg9, boolean arg10, PriorityTaskManager arg11) {
        super();
        DefaultLoadControl.assertGreaterOrEqual(arg7, 0, "bufferForPlaybackMs", "0");
        DefaultLoadControl.assertGreaterOrEqual(arg8, 0, "bufferForPlaybackAfterRebufferMs", "0");
        DefaultLoadControl.assertGreaterOrEqual(arg5, arg7, "minBufferMs", "bufferForPlaybackMs");
        DefaultLoadControl.assertGreaterOrEqual(arg5, arg8, "minBufferMs", "bufferForPlaybackAfterRebufferMs");
        DefaultLoadControl.assertGreaterOrEqual(arg6, arg5, "maxBufferMs", "minBufferMs");
        this.allocator = arg4;
        this.minBufferUs = (((long)arg5)) * 1000;
        this.maxBufferUs = (((long)arg6)) * 1000;
        this.bufferForPlaybackUs = (((long)arg7)) * 1000;
        this.bufferForPlaybackAfterRebufferUs = (((long)arg8)) * 1000;
        this.targetBufferBytesOverwrite = arg9;
        this.prioritizeTimeOverSizeThresholds = arg10;
        this.priorityTaskManager = arg11;
    }

    private static void assertGreaterOrEqual(int arg0, int arg1, String arg2, String arg3) {
        boolean v0 = arg0 >= arg1 ? true : false;
        Assertions.checkArgument(v0, arg2 + " cannot be less than " + arg3);
    }

    protected int calculateTargetBufferSize(Renderer[] arg4, TrackSelectionArray arg5) {
        int v0 = 0;
        int v1 = 0;
        while(v0 < arg4.length) {
            if(arg5.get(v0) != null) {
                v1 += Util.getDefaultBufferSize(arg4[v0].getTrackType());
            }

            ++v0;
        }

        return v1;
    }

    public Allocator getAllocator() {
        return this.allocator;
    }

    public long getBackBufferDurationUs() {
        return 0;
    }

    public void onPrepared() {
        this.reset(false);
    }

    public void onReleased() {
        this.reset(true);
    }

    public void onStopped() {
        this.reset(true);
    }

    public void onTracksSelected(Renderer[] arg2, TrackGroupArray arg3, TrackSelectionArray arg4) {
        int v2 = this.targetBufferBytesOverwrite == -1 ? this.calculateTargetBufferSize(arg2, arg4) : this.targetBufferBytesOverwrite;
        this.targetBufferSize = v2;
        this.allocator.setTargetBufferSize(this.targetBufferSize);
    }

    private void reset(boolean arg3) {
        this.targetBufferSize = 0;
        if(this.priorityTaskManager != null && (this.isBuffering)) {
            this.priorityTaskManager.remove(0);
        }

        this.isBuffering = false;
        if(arg3) {
            this.allocator.reset();
        }
    }

    public boolean retainBackBufferFromKeyframe() {
        return 0;
    }

    public boolean shouldContinueLoading(long arg9, float arg11) {
        boolean v2 = true;
        int v0 = this.allocator.getTotalBytesAllocated() >= this.targetBufferSize ? 1 : 0;
        boolean v1 = this.isBuffering;
        long v4 = this.minBufferUs;
        if(arg11 > 1f) {
            v4 = Math.min(Util.getMediaDurationForPlayoutDuration(v4, arg11), this.maxBufferUs);
        }

        if(arg9 < v4) {
            if(!this.prioritizeTimeOverSizeThresholds) {
                if(v0 == 0) {
                }
                else {
                    v2 = false;
                }
            }

            this.isBuffering = v2;
        }
        else {
            if(arg9 <= this.maxBufferUs && v0 == 0) {
                goto label_28;
            }

            this.isBuffering = false;
        }

    label_28:
        if(this.priorityTaskManager != null && this.isBuffering != v1) {
            if(this.isBuffering) {
                this.priorityTaskManager.add(0);
            }
            else {
                this.priorityTaskManager.remove(0);
            }
        }

        return this.isBuffering;
    }

    public boolean shouldStartPlayback(long arg4, float arg6, boolean arg7) {
        boolean v4;
        arg4 = Util.getPlayoutDurationForMediaDuration(arg4, arg6);
        long v6 = arg7 ? this.bufferForPlaybackAfterRebufferUs : this.bufferForPlaybackUs;
        if(v6 <= 0 || arg4 >= v6) {
        label_17:
            v4 = true;
        }
        else {
            if(!this.prioritizeTimeOverSizeThresholds && this.allocator.getTotalBytesAllocated() >= this.targetBufferSize) {
                goto label_17;
            }

            v4 = false;
        }

        return v4;
    }
}

