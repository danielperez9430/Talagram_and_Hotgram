package com.google.android.exoplayer2;

import android.content.Context;
import android.os.Looper;
import com.google.android.exoplayer2.analytics.AnalyticsCollector$Factory;
import com.google.android.exoplayer2.drm.DrmSessionManager;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter$Builder;
import com.google.android.exoplayer2.util.Clock;
import com.google.android.exoplayer2.util.Util;

public final class ExoPlayerFactory {
    private static BandwidthMeter singletonBandwidthMeter;

    private ExoPlayerFactory() {
        super();
    }

    private static BandwidthMeter getDefaultBandwidthMeter() {
        BandwidthMeter v1_1;
        Class v0 = ExoPlayerFactory.class;
        __monitor_enter(v0);
        try {
            if(ExoPlayerFactory.singletonBandwidthMeter == null) {
                ExoPlayerFactory.singletonBandwidthMeter = new Builder().build();
            }

            v1_1 = ExoPlayerFactory.singletonBandwidthMeter;
        }
        catch(Throwable v1) {
            __monitor_exit(v0);
            throw v1;
        }

        __monitor_exit(v0);
        return v1_1;
    }

    public static ExoPlayer newInstance(Renderer[] arg1, TrackSelector arg2) {
        return ExoPlayerFactory.newInstance(arg1, arg2, new DefaultLoadControl());
    }

    public static ExoPlayer newInstance(Renderer[] arg1, TrackSelector arg2, LoadControl arg3) {
        return ExoPlayerFactory.newInstance(arg1, arg2, arg3, Util.getLooper());
    }

    public static ExoPlayer newInstance(Renderer[] arg1, TrackSelector arg2, LoadControl arg3, Looper arg4) {
        return ExoPlayerFactory.newInstance(arg1, arg2, arg3, ExoPlayerFactory.getDefaultBandwidthMeter(), arg4);
    }

    public static ExoPlayer newInstance(Renderer[] arg8, TrackSelector arg9, LoadControl arg10, BandwidthMeter arg11, Looper arg12) {
        return new ExoPlayerImpl(arg8, arg9, arg10, arg11, Clock.DEFAULT, arg12);
    }

    public static SimpleExoPlayer newSimpleInstance(Context arg1, TrackSelector arg2) {
        return ExoPlayerFactory.newSimpleInstance(new DefaultRenderersFactory(arg1), arg2);
    }

    public static SimpleExoPlayer newSimpleInstance(RenderersFactory arg1, TrackSelector arg2) {
        return ExoPlayerFactory.newSimpleInstance(arg1, arg2, new DefaultLoadControl());
    }

    @Deprecated public static SimpleExoPlayer newSimpleInstance(Context arg1, TrackSelector arg2, LoadControl arg3) {
        return ExoPlayerFactory.newSimpleInstance(new DefaultRenderersFactory(arg1), arg2, arg3);
    }

    public static SimpleExoPlayer newSimpleInstance(RenderersFactory arg2, TrackSelector arg3, LoadControl arg4) {
        return ExoPlayerFactory.newSimpleInstance(arg2, arg3, arg4, null, Util.getLooper());
    }

    @Deprecated public static SimpleExoPlayer newSimpleInstance(Context arg1, TrackSelector arg2, LoadControl arg3, DrmSessionManager arg4) {
        return ExoPlayerFactory.newSimpleInstance(new DefaultRenderersFactory(arg1), arg2, arg3, arg4);
    }

    public static SimpleExoPlayer newSimpleInstance(RenderersFactory arg1, TrackSelector arg2, LoadControl arg3, DrmSessionManager arg4) {
        return ExoPlayerFactory.newSimpleInstance(arg1, arg2, arg3, arg4, Util.getLooper());
    }

    @Deprecated public static SimpleExoPlayer newSimpleInstance(Context arg1, TrackSelector arg2, LoadControl arg3, DrmSessionManager arg4, int arg5) {
        return ExoPlayerFactory.newSimpleInstance(new DefaultRenderersFactory(arg1, arg5), arg2, arg3, arg4);
    }

    @Deprecated public static SimpleExoPlayer newSimpleInstance(Context arg1, TrackSelector arg2, LoadControl arg3, DrmSessionManager arg4, int arg5, long arg6) {
        return ExoPlayerFactory.newSimpleInstance(new DefaultRenderersFactory(arg1, arg5, arg6), arg2, arg3, arg4);
    }

    public static SimpleExoPlayer newSimpleInstance(RenderersFactory arg6, TrackSelector arg7, LoadControl arg8, DrmSessionManager arg9, Looper arg10) {
        return ExoPlayerFactory.newSimpleInstance(arg6, arg7, arg8, arg9, new Factory(), arg10);
    }

    public static SimpleExoPlayer newSimpleInstance(RenderersFactory arg7, TrackSelector arg8, LoadControl arg9, DrmSessionManager arg10, Factory arg11, Looper arg12) {
        return ExoPlayerFactory.newSimpleInstance(arg7, arg8, arg9, arg10, ExoPlayerFactory.getDefaultBandwidthMeter(), arg11, arg12);
    }

    public static SimpleExoPlayer newSimpleInstance(RenderersFactory arg6, TrackSelector arg7, LoadControl arg8, DrmSessionManager arg9, Factory arg10) {
        return ExoPlayerFactory.newSimpleInstance(arg6, arg7, arg8, arg9, arg10, Util.getLooper());
    }

    public static SimpleExoPlayer newSimpleInstance(RenderersFactory arg9, TrackSelector arg10, LoadControl arg11, DrmSessionManager arg12, BandwidthMeter arg13, Factory arg14, Looper arg15) {
        return new SimpleExoPlayer(arg9, arg10, arg11, arg12, arg13, arg14, arg15);
    }

    public static SimpleExoPlayer newSimpleInstance(RenderersFactory arg7, TrackSelector arg8, LoadControl arg9, DrmSessionManager arg10, BandwidthMeter arg11) {
        return ExoPlayerFactory.newSimpleInstance(arg7, arg8, arg9, arg10, arg11, new Factory(), Util.getLooper());
    }

    public static SimpleExoPlayer newSimpleInstance(RenderersFactory arg1, TrackSelector arg2, DrmSessionManager arg3) {
        return ExoPlayerFactory.newSimpleInstance(arg1, arg2, new DefaultLoadControl(), arg3);
    }
}

