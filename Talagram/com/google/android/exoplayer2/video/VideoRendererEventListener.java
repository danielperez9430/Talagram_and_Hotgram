package com.google.android.exoplayer2.video;

import android.os.Handler;
import android.view.Surface;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.decoder.DecoderCounters;
import com.google.android.exoplayer2.util.Assertions;

public interface VideoRendererEventListener {
    public final class EventDispatcher {
        private final Handler handler;
        private final VideoRendererEventListener listener;

        public EventDispatcher(Handler arg1, VideoRendererEventListener arg2) {
            super();
            if(arg2 != null) {
                Object v1 = Assertions.checkNotNull(arg1);
            }
            else {
                arg1 = null;
            }

            this.handler = arg1;
            this.listener = arg2;
        }

        static VideoRendererEventListener access$000(EventDispatcher arg0) {
            return arg0.listener;
        }

        public void decoderInitialized(String arg10, long arg11, long arg13) {
            if(this.listener != null) {
                this.handler.post(new Runnable(arg10, arg11, arg13) {
                    public void run() {
                        EventDispatcher.this.listener.onVideoDecoderInitialized(this.val$decoderName, this.val$initializedTimestampMs, this.val$initializationDurationMs);
                    }
                });
            }
        }

        public void disabled(DecoderCounters arg3) {
            if(this.listener != null) {
                this.handler.post(new Runnable(arg3) {
                    public void run() {
                        this.val$counters.ensureUpdated();
                        EventDispatcher.this.listener.onVideoDisabled(this.val$counters);
                    }
                });
            }
        }

        public void droppedFrames(int arg3, long arg4) {
            if(this.listener != null) {
                this.handler.post(new Runnable(arg3, arg4) {
                    public void run() {
                        EventDispatcher.this.listener.onDroppedFrames(this.val$droppedFrameCount, this.val$elapsedMs);
                    }
                });
            }
        }

        public void enabled(DecoderCounters arg3) {
            if(this.listener != null) {
                this.handler.post(new Runnable(arg3) {
                    public void run() {
                        EventDispatcher.this.listener.onVideoEnabled(this.val$decoderCounters);
                    }
                });
            }
        }

        public void inputFormatChanged(Format arg3) {
            if(this.listener != null) {
                this.handler.post(new Runnable(arg3) {
                    public void run() {
                        EventDispatcher.this.listener.onVideoInputFormatChanged(this.val$format);
                    }
                });
            }
        }

        public void renderedFirstFrame(Surface arg3) {
            if(this.listener != null) {
                this.handler.post(new Runnable(arg3) {
                    public void run() {
                        EventDispatcher.this.listener.onRenderedFirstFrame(this.val$surface);
                    }
                });
            }
        }

        public void videoSizeChanged(int arg9, int arg10, int arg11, float arg12) {
            if(this.listener != null) {
                this.handler.post(new Runnable(arg9, arg10, arg11, arg12) {
                    public void run() {
                        EventDispatcher.this.listener.onVideoSizeChanged(this.val$width, this.val$height, this.val$unappliedRotationDegrees, this.val$pixelWidthHeightRatio);
                    }
                });
            }
        }
    }

    void onDroppedFrames(int arg1, long arg2);

    void onRenderedFirstFrame(Surface arg1);

    void onVideoDecoderInitialized(String arg1, long arg2, long arg3);

    void onVideoDisabled(DecoderCounters arg1);

    void onVideoEnabled(DecoderCounters arg1);

    void onVideoInputFormatChanged(Format arg1);

    void onVideoSizeChanged(int arg1, int arg2, int arg3, float arg4);
}

