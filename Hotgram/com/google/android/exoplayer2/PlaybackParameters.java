package com.google.android.exoplayer2;

public final class PlaybackParameters {
    public static final PlaybackParameters DEFAULT;
    public final float pitch;
    private final int scaledUsPerMs;
    public final boolean skipSilence;
    public final float speed;

    static {
        PlaybackParameters.DEFAULT = new PlaybackParameters(1f);
    }

    public PlaybackParameters(float arg3) {
        this(arg3, 1f, false);
    }

    public PlaybackParameters(float arg5, float arg6, boolean arg7) {
        // Method was not decompiled
    }

    public PlaybackParameters(float arg2, float arg3) {
        this(arg2, arg3, false);
    }

    public boolean equals(Object arg5) {
        boolean v0 = true;
        if(this == (((PlaybackParameters)arg5))) {
            return 1;
        }

        if(arg5 != null) {
            if(this.getClass() != arg5.getClass()) {
            }
            else {
                if(this.speed != ((PlaybackParameters)arg5).speed || this.pitch != ((PlaybackParameters)arg5).pitch || this.skipSilence != ((PlaybackParameters)arg5).skipSilence) {
                    v0 = false;
                }
                else {
                }

                return v0;
            }
        }

        return 0;
    }

    public long getMediaTimeUsForPlayoutTimeMs(long arg3) {
        return arg3 * (((long)this.scaledUsPerMs));
    }

    public int hashCode() {
        return ((527 + Float.floatToRawIntBits(this.speed)) * 31 + Float.floatToRawIntBits(this.pitch)) * 31 + this.skipSilence;
    }
}

