package com.google.android.exoplayer2.audio;

import android.os.Handler;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.decoder.DecoderCounters;
import com.google.android.exoplayer2.util.Assertions;

public interface AudioRendererEventListener {
    public final class EventDispatcher {
        private final Handler handler;
        private final AudioRendererEventListener listener;

        public EventDispatcher(Handler arg1, AudioRendererEventListener arg2) {
            Object v1;
            super();
            if(arg2 != null) {
                v1 = Assertions.checkNotNull(arg1);
            }
            else {
                arg1 = null;
            }

            this.handler = ((Handler)v1);
            this.listener = arg2;
        }

        static AudioRendererEventListener access$000(EventDispatcher arg0) {
            return arg0.listener;
        }

        public void audioSessionId(int arg3) {
            if(this.listener != null) {
                this.handler.post(new Runnable(arg3) {
                    public void run() {
                        EventDispatcher.this.listener.onAudioSessionId(this.val$audioSessionId);
                    }
                });
            }
        }

        public void audioTrackUnderrun(int arg10, long arg11, long arg13) {
            if(this.listener != null) {
                this.handler.post(new Runnable(arg10, arg11, arg13) {
                    public void run() {
                        EventDispatcher.this.listener.onAudioSinkUnderrun(this.val$bufferSize, this.val$bufferSizeMs, this.val$elapsedSinceLastFeedMs);
                    }
                });
            }
        }

        public void decoderInitialized(String arg10, long arg11, long arg13) {
            if(this.listener != null) {
                this.handler.post(new Runnable(arg10, arg11, arg13) {
                    public void run() {
                        EventDispatcher.this.listener.onAudioDecoderInitialized(this.val$decoderName, this.val$initializedTimestampMs, this.val$initializationDurationMs);
                    }
                });
            }
        }

        public void disabled(DecoderCounters arg3) {
            if(this.listener != null) {
                this.handler.post(new Runnable(arg3) {
                    public void run() {
                        this.val$counters.ensureUpdated();
                        EventDispatcher.this.listener.onAudioDisabled(this.val$counters);
                    }
                });
            }
        }

        public void enabled(DecoderCounters arg3) {
            if(this.listener != null) {
                this.handler.post(new Runnable(arg3) {
                    public void run() {
                        EventDispatcher.this.listener.onAudioEnabled(this.val$decoderCounters);
                    }
                });
            }
        }

        public void inputFormatChanged(Format arg3) {
            if(this.listener != null) {
                this.handler.post(new Runnable(arg3) {
                    public void run() {
                        EventDispatcher.this.listener.onAudioInputFormatChanged(this.val$format);
                    }
                });
            }
        }
    }

    void onAudioDecoderInitialized(String arg1, long arg2, long arg3);

    void onAudioDisabled(DecoderCounters arg1);

    void onAudioEnabled(DecoderCounters arg1);

    void onAudioInputFormatChanged(Format arg1);

    void onAudioSessionId(int arg1);

    void onAudioSinkUnderrun(int arg1, long arg2, long arg3);
}

