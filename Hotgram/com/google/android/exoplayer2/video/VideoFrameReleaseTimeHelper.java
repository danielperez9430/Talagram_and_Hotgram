package com.google.android.exoplayer2.video;

import android.annotation.TargetApi;
import android.content.Context;
import android.hardware.display.DisplayManager$DisplayListener;
import android.hardware.display.DisplayManager;
import android.os.Handler$Callback;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.view.Choreographer$FrameCallback;
import android.view.Choreographer;
import android.view.Display;
import android.view.WindowManager;
import com.google.android.exoplayer2.util.Util;

@TargetApi(value=16) public final class VideoFrameReleaseTimeHelper {
    @TargetApi(value=17) final class DefaultDisplayListener implements DisplayManager$DisplayListener {
        private final DisplayManager displayManager;

        public DefaultDisplayListener(VideoFrameReleaseTimeHelper arg1, DisplayManager arg2) {
            VideoFrameReleaseTimeHelper.this = arg1;
            super();
            this.displayManager = arg2;
        }

        public void onDisplayAdded(int arg1) {
        }

        public void onDisplayChanged(int arg1) {
            if(arg1 == 0) {
                VideoFrameReleaseTimeHelper.this.updateDefaultDisplayRefreshRateParams();
            }
        }

        public void onDisplayRemoved(int arg1) {
        }

        public void register() {
            this.displayManager.registerDisplayListener(((DisplayManager$DisplayListener)this), null);
        }

        public void unregister() {
            this.displayManager.unregisterDisplayListener(((DisplayManager$DisplayListener)this));
        }
    }

    final class VSyncSampler implements Handler$Callback, Choreographer$FrameCallback {
        private static final int CREATE_CHOREOGRAPHER = 0;
        private static final VSyncSampler INSTANCE = null;
        private static final int MSG_ADD_OBSERVER = 1;
        private static final int MSG_REMOVE_OBSERVER = 2;
        private Choreographer choreographer;
        private final HandlerThread choreographerOwnerThread;
        private final Handler handler;
        private int observerCount;
        public volatile long sampledVsyncTimeNs;

        static {
            VSyncSampler.INSTANCE = new VSyncSampler();
        }

        private VSyncSampler() {
            super();
            this.sampledVsyncTimeNs = -9223372036854775807L;
            this.choreographerOwnerThread = new HandlerThread("ChoreographerOwner:Handler");
            this.choreographerOwnerThread.start();
            this.handler = Util.createHandler(this.choreographerOwnerThread.getLooper(), ((Handler$Callback)this));
            this.handler.sendEmptyMessage(0);
        }

        public void addObserver() {
            this.handler.sendEmptyMessage(1);
        }

        private void addObserverInternal() {
            ++this.observerCount;
            if(this.observerCount == 1) {
                this.choreographer.postFrameCallback(((Choreographer$FrameCallback)this));
            }
        }

        private void createChoreographerInstanceInternal() {
            this.choreographer = Choreographer.getInstance();
        }

        public void doFrame(long arg3) {
            this.sampledVsyncTimeNs = arg3;
            this.choreographer.postFrameCallbackDelayed(((Choreographer$FrameCallback)this), 500);
        }

        public static VSyncSampler getInstance() {
            return VSyncSampler.INSTANCE;
        }

        public boolean handleMessage(Message arg2) {
            switch(arg2.what) {
                case 0: {
                    goto label_9;
                }
                case 1: {
                    goto label_7;
                }
                case 2: {
                    goto label_5;
                }
            }

            return 0;
        label_5:
            this.removeObserverInternal();
            return 1;
        label_7:
            this.addObserverInternal();
            return 1;
        label_9:
            this.createChoreographerInstanceInternal();
            return 1;
        }

        public void removeObserver() {
            this.handler.sendEmptyMessage(2);
        }

        private void removeObserverInternal() {
            --this.observerCount;
            if(this.observerCount == 0) {
                this.choreographer.removeFrameCallback(((Choreographer$FrameCallback)this));
                this.sampledVsyncTimeNs = -9223372036854775807L;
            }
        }
    }

    private static final long CHOREOGRAPHER_SAMPLE_DELAY_MILLIS = 500;
    private static final long MAX_ALLOWED_DRIFT_NS = 20000000;
    private static final int MIN_FRAMES_FOR_ADJUSTMENT = 6;
    private static final long VSYNC_OFFSET_PERCENTAGE = 80;
    private long adjustedLastFrameTimeNs;
    private final DefaultDisplayListener displayListener;
    private long frameCount;
    private boolean haveSync;
    private long lastFramePresentationTimeUs;
    private long pendingAdjustedFrameTimeNs;
    private long syncFramePresentationTimeNs;
    private long syncUnadjustedReleaseTimeNs;
    private long vsyncDurationNs;
    private long vsyncOffsetNs;
    private final VSyncSampler vsyncSampler;
    private final WindowManager windowManager;

    public VideoFrameReleaseTimeHelper() {
        this(null);
    }

    public VideoFrameReleaseTimeHelper(Context arg4) {
        DefaultDisplayListener v0_1;
        super();
        WindowManager v0 = null;
        if(arg4 != null) {
            arg4 = arg4.getApplicationContext();
            this.windowManager = arg4.getSystemService("window");
        }
        else {
            this.windowManager = v0;
        }

        if(this.windowManager != null) {
            if(Util.SDK_INT >= 17) {
                v0_1 = this.maybeBuildDefaultDisplayListenerV17(arg4);
            }

            this.displayListener = v0_1;
            this.vsyncSampler = VSyncSampler.getInstance();
        }
        else {
            this.displayListener = ((DefaultDisplayListener)v0);
            this.vsyncSampler = ((VSyncSampler)v0);
        }

        this.vsyncDurationNs = -9223372036854775807L;
        this.vsyncOffsetNs = -9223372036854775807L;
    }

    static void access$000(VideoFrameReleaseTimeHelper arg0) {
        arg0.updateDefaultDisplayRefreshRateParams();
    }

    public long adjustReleaseTime(long arg19, long arg21) {
        // Method was not decompiled
    }

    private static long closestVsync(long arg5, long arg7, long arg9) {
        arg7 += (arg5 - arg7) / arg9 * arg9;
        if(arg5 <= arg7) {
            arg9 = arg7 - arg9;
        }
        else {
            long v3 = arg7;
            arg7 = arg9 + arg7;
            arg9 = v3;
        }

        if(arg7 - arg5 < arg5 - arg9) {
        }
        else {
            arg7 = arg9;
        }

        return arg7;
    }

    public void disable() {
        if(this.windowManager != null) {
            if(this.displayListener != null) {
                this.displayListener.unregister();
            }

            this.vsyncSampler.removeObserver();
        }
    }

    public void enable() {
        this.haveSync = false;
        if(this.windowManager != null) {
            this.vsyncSampler.addObserver();
            if(this.displayListener != null) {
                this.displayListener.register();
            }

            this.updateDefaultDisplayRefreshRateParams();
        }
    }

    private boolean isDriftTooLarge(long arg3, long arg5) {
        boolean v3 = Math.abs(arg5 - this.syncUnadjustedReleaseTimeNs - (arg3 - this.syncFramePresentationTimeNs)) > 20000000 ? true : false;
        return v3;
    }

    @TargetApi(value=17) private DefaultDisplayListener maybeBuildDefaultDisplayListenerV17(Context arg2) {
        Object v2 = arg2.getSystemService("display");
        DefaultDisplayListener v2_1 = v2 == null ? null : new DefaultDisplayListener(this, ((DisplayManager)v2));
        return v2_1;
    }

    private void updateDefaultDisplayRefreshRateParams() {
        Display v0 = this.windowManager.getDefaultDisplay();
        if(v0 != null) {
            double v0_1 = ((double)v0.getRefreshRate());
            Double.isNaN(v0_1);
            this.vsyncDurationNs = ((long)(1000000000 / v0_1));
            this.vsyncOffsetNs = this.vsyncDurationNs * 80 / 100;
        }
    }
}

