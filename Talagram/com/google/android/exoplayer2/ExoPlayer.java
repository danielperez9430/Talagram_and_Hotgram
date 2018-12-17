package com.google.android.exoplayer2;

import android.os.Looper;
import com.google.android.exoplayer2.source.MediaSource;

public interface ExoPlayer extends Player {
    @Deprecated public interface EventListener extends com.google.android.exoplayer2.Player$EventListener {
    }

    @Deprecated public interface ExoPlayerComponent extends Target {
    }

    @Deprecated public final class ExoPlayerMessage {
        public final Object message;
        public final int messageType;
        public final Target target;

        @Deprecated public ExoPlayerMessage(Target arg1, int arg2, Object arg3) {
            super();
            this.target = arg1;
            this.messageType = arg2;
            this.message = arg3;
        }
    }

    @Deprecated public static final int REPEAT_MODE_ALL = 2;
    @Deprecated public static final int REPEAT_MODE_OFF = 0;
    @Deprecated public static final int REPEAT_MODE_ONE = 1;
    @Deprecated public static final int STATE_BUFFERING = 2;
    @Deprecated public static final int STATE_ENDED = 4;
    @Deprecated public static final int STATE_IDLE = 1;
    @Deprecated public static final int STATE_READY = 3;

    @Deprecated void blockingSendMessages(ExoPlayerMessage[] arg1);

    PlayerMessage createMessage(Target arg1);

    Looper getApplicationLooper();

    Looper getPlaybackLooper();

    SeekParameters getSeekParameters();

    void prepare(MediaSource arg1);

    void prepare(MediaSource arg1, boolean arg2, boolean arg3);

    @Deprecated void sendMessages(ExoPlayerMessage[] arg1);

    void setSeekParameters(SeekParameters arg1);
}

