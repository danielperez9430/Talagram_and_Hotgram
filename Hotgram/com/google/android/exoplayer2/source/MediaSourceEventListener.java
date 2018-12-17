package com.google.android.exoplayer2.source;

import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.util.Assertions;
import java.io.IOException;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

public interface MediaSourceEventListener {
    public final class EventDispatcher {
        final class ListenerAndHandler {
            public final Handler handler;
            public final MediaSourceEventListener listener;

            public ListenerAndHandler(Handler arg1, MediaSourceEventListener arg2) {
                super();
                this.handler = arg1;
                this.listener = arg2;
            }
        }

        private final CopyOnWriteArrayList listenerAndHandlers;
        public final MediaPeriodId mediaPeriodId;
        private final long mediaTimeOffsetMs;
        public final int windowIndex;

        public EventDispatcher() {
            this(new CopyOnWriteArrayList(), 0, null, 0);
        }

        private EventDispatcher(CopyOnWriteArrayList arg1, int arg2, MediaPeriodId arg3, long arg4) {
            super();
            this.listenerAndHandlers = arg1;
            this.windowIndex = arg2;
            this.mediaPeriodId = arg3;
            this.mediaTimeOffsetMs = arg4;
        }

        public void addEventListener(Handler arg3, MediaSourceEventListener arg4) {
            boolean v0 = arg3 == null || arg4 == null ? false : true;
            Assertions.checkArgument(v0);
            this.listenerAndHandlers.add(new ListenerAndHandler(arg3, arg4));
        }

        private long adjustMediaTime(long arg4) {
            arg4 = C.usToMs(arg4);
            long v0 = -9223372036854775807L;
            if(arg4 == v0) {
            }
            else {
                v0 = this.mediaTimeOffsetMs + arg4;
            }

            return v0;
        }

        public void downstreamFormatChanged(int arg13, Format arg14, int arg15, Object arg16, long arg17) {
            this.downstreamFormatChanged(new MediaLoadData(1, arg13, arg14, arg15, arg16, this.adjustMediaTime(arg17), -9223372036854775807L));
        }

        public void downstreamFormatChanged(MediaLoadData arg5) {
            Iterator v0 = this.listenerAndHandlers.iterator();
            while(v0.hasNext()) {
                Object v1 = v0.next();
                this.postOrRun(((ListenerAndHandler)v1).handler, new -$$Lambda$MediaSourceEventListener$EventDispatcher$ES4FdQzWtupQEe6zuV_1M9-f9xU(this, ((ListenerAndHandler)v1).listener, arg5));
            }
        }

        public static void lambda$downstreamFormatChanged$8(EventDispatcher arg2, MediaSourceEventListener arg3, MediaLoadData arg4) {
            arg3.onDownstreamFormatChanged(arg2.windowIndex, arg2.mediaPeriodId, arg4);
        }

        public static void lambda$loadCanceled$4(EventDispatcher arg2, MediaSourceEventListener arg3, LoadEventInfo arg4, MediaLoadData arg5) {
            arg3.onLoadCanceled(arg2.windowIndex, arg2.mediaPeriodId, arg4, arg5);
        }

        public static void lambda$loadCompleted$3(EventDispatcher arg2, MediaSourceEventListener arg3, LoadEventInfo arg4, MediaLoadData arg5) {
            arg3.onLoadCompleted(arg2.windowIndex, arg2.mediaPeriodId, arg4, arg5);
        }

        public static void lambda$loadError$5(EventDispatcher arg7, MediaSourceEventListener arg8, LoadEventInfo arg9, MediaLoadData arg10, IOException arg11, boolean arg12) {
            arg8.onLoadError(arg7.windowIndex, arg7.mediaPeriodId, arg9, arg10, arg11, arg12);
        }

        public static void lambda$loadStarted$2(EventDispatcher arg2, MediaSourceEventListener arg3, LoadEventInfo arg4, MediaLoadData arg5) {
            arg3.onLoadStarted(arg2.windowIndex, arg2.mediaPeriodId, arg4, arg5);
        }

        public static void lambda$mediaPeriodCreated$0(EventDispatcher arg1, MediaSourceEventListener arg2, MediaPeriodId arg3) {
            arg2.onMediaPeriodCreated(arg1.windowIndex, arg3);
        }

        public static void lambda$mediaPeriodReleased$1(EventDispatcher arg1, MediaSourceEventListener arg2, MediaPeriodId arg3) {
            arg2.onMediaPeriodReleased(arg1.windowIndex, arg3);
        }

        public static void lambda$readingStarted$6(EventDispatcher arg1, MediaSourceEventListener arg2, MediaPeriodId arg3) {
            arg2.onReadingStarted(arg1.windowIndex, arg3);
        }

        public static void lambda$upstreamDiscarded$7(EventDispatcher arg1, MediaSourceEventListener arg2, MediaPeriodId arg3, MediaLoadData arg4) {
            arg2.onUpstreamDiscarded(arg1.windowIndex, arg3, arg4);
        }

        public void loadCanceled(LoadEventInfo arg5, MediaLoadData arg6) {
            Iterator v0 = this.listenerAndHandlers.iterator();
            while(v0.hasNext()) {
                Object v1 = v0.next();
                this.postOrRun(((ListenerAndHandler)v1).handler, new -$$Lambda$MediaSourceEventListener$EventDispatcher$1-VoN1d1C8yHbFOrB_mXtUwAn3M(this, ((ListenerAndHandler)v1).listener, arg5, arg6));
            }
        }

        public void loadCanceled(DataSpec arg12, Uri arg13, int arg14, int arg15, Format arg16, int arg17, Object arg18, long arg19, long arg21, long arg23, long arg25, long arg27) {
            this.loadCanceled(new LoadEventInfo(arg12, arg13, arg23, arg25, arg27), new MediaLoadData(arg14, arg15, arg16, arg17, arg18, this.adjustMediaTime(arg19), this.adjustMediaTime(arg21)));
        }

        public void loadCanceled(DataSpec arg19, Uri arg20, int arg21, long arg22, long arg24, long arg26) {
            this.loadCanceled(arg19, arg20, arg21, -1, null, 0, null, -9223372036854775807L, -9223372036854775807L, arg22, arg24, arg26);
        }

        public void loadCompleted(LoadEventInfo arg5, MediaLoadData arg6) {
            Iterator v0 = this.listenerAndHandlers.iterator();
            while(v0.hasNext()) {
                Object v1 = v0.next();
                this.postOrRun(((ListenerAndHandler)v1).handler, new -$$Lambda$MediaSourceEventListener$EventDispatcher$IejPnkXyHgj2V1iyO1dqtBKfihI(this, ((ListenerAndHandler)v1).listener, arg5, arg6));
            }
        }

        public void loadCompleted(DataSpec arg12, Uri arg13, int arg14, int arg15, Format arg16, int arg17, Object arg18, long arg19, long arg21, long arg23, long arg25, long arg27) {
            this.loadCompleted(new LoadEventInfo(arg12, arg13, arg23, arg25, arg27), new MediaLoadData(arg14, arg15, arg16, arg17, arg18, this.adjustMediaTime(arg19), this.adjustMediaTime(arg21)));
        }

        public void loadCompleted(DataSpec arg19, Uri arg20, int arg21, long arg22, long arg24, long arg26) {
            this.loadCompleted(arg19, arg20, arg21, -1, null, 0, null, -9223372036854775807L, -9223372036854775807L, arg22, arg24, arg26);
        }

        public void loadError(LoadEventInfo arg11, MediaLoadData arg12, IOException arg13, boolean arg14) {
            Iterator v0 = this.listenerAndHandlers.iterator();
            while(v0.hasNext()) {
                Object v1 = v0.next();
                this.postOrRun(((ListenerAndHandler)v1).handler, new -$$Lambda$MediaSourceEventListener$EventDispatcher$0X-TAsNqR4TUW1yA_ZD1_p3oT84(this, ((ListenerAndHandler)v1).listener, arg11, arg12, arg13, arg14));
            }
        }

        public void loadError(DataSpec arg12, Uri arg13, int arg14, int arg15, Format arg16, int arg17, Object arg18, long arg19, long arg21, long arg23, long arg25, long arg27, IOException arg29, boolean arg30) {
            this.loadError(new LoadEventInfo(arg12, arg13, arg23, arg25, arg27), new MediaLoadData(arg14, arg15, arg16, arg17, arg18, this.adjustMediaTime(arg19), this.adjustMediaTime(arg21)), arg29, arg30);
        }

        public void loadError(DataSpec arg21, Uri arg22, int arg23, long arg24, long arg26, long arg28, IOException arg30, boolean arg31) {
            this.loadError(arg21, arg22, arg23, -1, null, 0, null, -9223372036854775807L, -9223372036854775807L, arg24, arg26, arg28, arg30, arg31);
        }

        public void loadStarted(LoadEventInfo arg5, MediaLoadData arg6) {
            Iterator v0 = this.listenerAndHandlers.iterator();
            while(v0.hasNext()) {
                Object v1 = v0.next();
                this.postOrRun(((ListenerAndHandler)v1).handler, new -$$Lambda$MediaSourceEventListener$EventDispatcher$WQKVpIh5ilpOizOGmbnyUThugMU(this, ((ListenerAndHandler)v1).listener, arg5, arg6));
            }
        }

        public void loadStarted(DataSpec arg22, Uri arg23, int arg24, int arg25, Format arg26, int arg27, Object arg28, long arg29, long arg31, long arg33) {
            this.loadStarted(new LoadEventInfo(arg22, arg23, arg33, 0, 0), new MediaLoadData(arg24, arg25, arg26, arg27, arg28, this.adjustMediaTime(arg29), this.adjustMediaTime(arg31)));
        }

        public void loadStarted(DataSpec arg15, Uri arg16, int arg17, long arg18) {
            this.loadStarted(arg15, arg16, arg17, -1, null, 0, null, -9223372036854775807L, -9223372036854775807L, arg18);
        }

        public void mediaPeriodCreated() {
            Object v0 = Assertions.checkNotNull(this.mediaPeriodId);
            Iterator v1 = this.listenerAndHandlers.iterator();
            while(v1.hasNext()) {
                Object v2 = v1.next();
                this.postOrRun(((ListenerAndHandler)v2).handler, new -$$Lambda$MediaSourceEventListener$EventDispatcher$N-EOPAK5UK0--YMNjezq7UM3UNI(this, ((ListenerAndHandler)v2).listener, ((MediaPeriodId)v0)));
            }
        }

        public void mediaPeriodReleased() {
            Object v0 = Assertions.checkNotNull(this.mediaPeriodId);
            Iterator v1 = this.listenerAndHandlers.iterator();
            while(v1.hasNext()) {
                Object v2 = v1.next();
                this.postOrRun(((ListenerAndHandler)v2).handler, new -$$Lambda$MediaSourceEventListener$EventDispatcher$zyck4ebRbqvR6eQIjdzRcIBkRbI(this, ((ListenerAndHandler)v2).listener, ((MediaPeriodId)v0)));
            }
        }

        private void postOrRun(Handler arg3, Runnable arg4) {
            if(arg3.getLooper() == Looper.myLooper()) {
                arg4.run();
            }
            else {
                arg3.post(arg4);
            }
        }

        public void readingStarted() {
            Object v0 = Assertions.checkNotNull(this.mediaPeriodId);
            Iterator v1 = this.listenerAndHandlers.iterator();
            while(v1.hasNext()) {
                Object v2 = v1.next();
                this.postOrRun(((ListenerAndHandler)v2).handler, new -$$Lambda$MediaSourceEventListener$EventDispatcher$PV8wmqGm7vRMJNlt--V3zhXfxiE(this, ((ListenerAndHandler)v2).listener, ((MediaPeriodId)v0)));
            }
        }

        public void removeEventListener(MediaSourceEventListener arg4) {
            Iterator v0 = this.listenerAndHandlers.iterator();
            while(v0.hasNext()) {
                Object v1 = v0.next();
                if(((ListenerAndHandler)v1).listener != arg4) {
                    continue;
                }

                this.listenerAndHandlers.remove(v1);
            }
        }

        public void upstreamDiscarded(int arg13, long arg14, long arg16) {
            this.upstreamDiscarded(new MediaLoadData(1, arg13, null, 3, null, this.adjustMediaTime(arg14), this.adjustMediaTime(arg16)));
        }

        public void upstreamDiscarded(MediaLoadData arg6) {
            Object v0 = Assertions.checkNotNull(this.mediaPeriodId);
            Iterator v1 = this.listenerAndHandlers.iterator();
            while(v1.hasNext()) {
                Object v2 = v1.next();
                this.postOrRun(((ListenerAndHandler)v2).handler, new -$$Lambda$MediaSourceEventListener$EventDispatcher$BtPa14lQQTv1oUeMy_9QaCysWHY(this, ((ListenerAndHandler)v2).listener, ((MediaPeriodId)v0), arg6));
            }
        }

        public EventDispatcher withParameters(int arg8, MediaPeriodId arg9, long arg10) {
            return new EventDispatcher(this.listenerAndHandlers, arg8, arg9, arg10);
        }
    }

    public final class LoadEventInfo {
        public final long bytesLoaded;
        public final DataSpec dataSpec;
        public final long elapsedRealtimeMs;
        public final long loadDurationMs;
        public final Uri uri;

        public LoadEventInfo(DataSpec arg1, Uri arg2, long arg3, long arg5, long arg7) {
            super();
            this.dataSpec = arg1;
            this.uri = arg2;
            this.elapsedRealtimeMs = arg3;
            this.loadDurationMs = arg5;
            this.bytesLoaded = arg7;
        }
    }

    public final class MediaLoadData {
        public final int dataType;
        public final long mediaEndTimeMs;
        public final long mediaStartTimeMs;
        public final Format trackFormat;
        public final Object trackSelectionData;
        public final int trackSelectionReason;
        public final int trackType;

        public MediaLoadData(int arg1, int arg2, Format arg3, int arg4, Object arg5, long arg6, long arg8) {
            super();
            this.dataType = arg1;
            this.trackType = arg2;
            this.trackFormat = arg3;
            this.trackSelectionReason = arg4;
            this.trackSelectionData = arg5;
            this.mediaStartTimeMs = arg6;
            this.mediaEndTimeMs = arg8;
        }
    }

    void onDownstreamFormatChanged(int arg1, MediaPeriodId arg2, MediaLoadData arg3);

    void onLoadCanceled(int arg1, MediaPeriodId arg2, LoadEventInfo arg3, MediaLoadData arg4);

    void onLoadCompleted(int arg1, MediaPeriodId arg2, LoadEventInfo arg3, MediaLoadData arg4);

    void onLoadError(int arg1, MediaPeriodId arg2, LoadEventInfo arg3, MediaLoadData arg4, IOException arg5, boolean arg6);

    void onLoadStarted(int arg1, MediaPeriodId arg2, LoadEventInfo arg3, MediaLoadData arg4);

    void onMediaPeriodCreated(int arg1, MediaPeriodId arg2);

    void onMediaPeriodReleased(int arg1, MediaPeriodId arg2);

    void onReadingStarted(int arg1, MediaPeriodId arg2);

    void onUpstreamDiscarded(int arg1, MediaPeriodId arg2, MediaLoadData arg3);
}

