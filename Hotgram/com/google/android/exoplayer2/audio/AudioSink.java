package com.google.android.exoplayer2.audio;

import com.google.android.exoplayer2.PlaybackParameters;
import java.nio.ByteBuffer;

public interface AudioSink {
    public final class ConfigurationException extends Exception {
        public ConfigurationException(String arg1) {
            super(arg1);
        }

        public ConfigurationException(Throwable arg1) {
            super(arg1);
        }
    }

    public final class InitializationException extends Exception {
        public final int audioTrackState;

        public InitializationException(int arg3, int arg4, int arg5, int arg6) {
            super("AudioTrack init failed: " + arg3 + ", Config(" + arg4 + ", " + arg5 + ", " + arg6 + ")");
            this.audioTrackState = arg3;
        }
    }

    public interface Listener {
        void onAudioSessionId(int arg1);

        void onPositionDiscontinuity();

        void onUnderrun(int arg1, long arg2, long arg3);
    }

    public final class WriteException extends Exception {
        public final int errorCode;

        public WriteException(int arg3) {
            super("AudioTrack write failed: " + arg3);
            this.errorCode = arg3;
        }
    }

    public static final long CURRENT_POSITION_NOT_SET = -9223372036854775808L;

    void configure(int arg1, int arg2, int arg3, int arg4, int[] arg5, int arg6, int arg7);

    void disableTunneling();

    void enableTunnelingV21(int arg1);

    long getCurrentPositionUs(boolean arg1);

    PlaybackParameters getPlaybackParameters();

    boolean handleBuffer(ByteBuffer arg1, long arg2);

    void handleDiscontinuity();

    boolean hasPendingData();

    boolean isEncodingSupported(int arg1);

    boolean isEnded();

    void pause();

    void play();

    void playToEndOfStream();

    void release();

    void reset();

    void setAudioAttributes(AudioAttributes arg1);

    void setAudioSessionId(int arg1);

    void setListener(Listener arg1);

    PlaybackParameters setPlaybackParameters(PlaybackParameters arg1);

    void setVolume(float arg1);
}

