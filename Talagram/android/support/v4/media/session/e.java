package android.support.v4.media.session;

import android.media.session.PlaybackState$CustomAction;
import android.media.session.PlaybackState;
import android.os.Bundle;
import java.util.List;

class e {
    final class a {
        public static String a(Object arg0) {
            return ((PlaybackState$CustomAction)arg0).getAction();
        }

        public static CharSequence b(Object arg0) {
            return ((PlaybackState$CustomAction)arg0).getName();
        }

        public static int c(Object arg0) {
            return ((PlaybackState$CustomAction)arg0).getIcon();
        }

        public static Bundle d(Object arg0) {
            return ((PlaybackState$CustomAction)arg0).getExtras();
        }
    }

    public static int a(Object arg0) {
        return ((PlaybackState)arg0).getState();
    }

    public static long b(Object arg2) {
        return ((PlaybackState)arg2).getPosition();
    }

    public static long c(Object arg2) {
        return ((PlaybackState)arg2).getBufferedPosition();
    }

    public static float d(Object arg0) {
        return ((PlaybackState)arg0).getPlaybackSpeed();
    }

    public static long e(Object arg2) {
        return ((PlaybackState)arg2).getActions();
    }

    public static CharSequence f(Object arg0) {
        return ((PlaybackState)arg0).getErrorMessage();
    }

    public static long g(Object arg2) {
        return ((PlaybackState)arg2).getLastPositionUpdateTime();
    }

    public static List h(Object arg0) {
        return ((PlaybackState)arg0).getCustomActions();
    }

    public static long i(Object arg2) {
        return ((PlaybackState)arg2).getActiveQueueItemId();
    }
}

