package com.google.android.exoplayer2;

import com.google.android.exoplayer2.util.Assertions;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class ExoPlaybackException extends Exception {
    @Retention(value=RetentionPolicy.SOURCE) @public interface Type {
    }

    public static final int TYPE_RENDERER = 1;
    public static final int TYPE_SOURCE = 0;
    public static final int TYPE_UNEXPECTED = 2;
    public final int rendererIndex;
    public final int type;

    private ExoPlaybackException(int arg1, String arg2, Throwable arg3, int arg4) {
        super(arg2, arg3);
        this.type = arg1;
        this.rendererIndex = arg4;
    }

    public static ExoPlaybackException createForRenderer(Exception arg3, int arg4) {
        return new ExoPlaybackException(1, null, ((Throwable)arg3), arg4);
    }

    public static ExoPlaybackException createForSource(IOException arg4) {
        return new ExoPlaybackException(0, null, ((Throwable)arg4), -1);
    }

    static ExoPlaybackException createForUnexpected(RuntimeException arg4) {
        return new ExoPlaybackException(2, null, ((Throwable)arg4), -1);
    }

    public Exception getRendererException() {
        boolean v1 = true;
        if(this.type == 1) {
        }
        else {
            v1 = false;
        }

        Assertions.checkState(v1);
        return this.getCause();
    }

    public IOException getSourceException() {
        boolean v0 = this.type == 0 ? true : false;
        Assertions.checkState(v0);
        return this.getCause();
    }

    public RuntimeException getUnexpectedException() {
        boolean v0 = this.type == 2 ? true : false;
        Assertions.checkState(v0);
        return this.getCause();
    }
}

