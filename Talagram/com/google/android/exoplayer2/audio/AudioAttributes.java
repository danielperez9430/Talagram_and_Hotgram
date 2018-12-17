package com.google.android.exoplayer2.audio;

import android.annotation.TargetApi;
import android.media.AudioAttributes$Builder;

public final class AudioAttributes {
    class com.google.android.exoplayer2.audio.AudioAttributes$1 {
    }

    public final class Builder {
        private int contentType;
        private int flags;
        private int usage;

        public Builder() {
            super();
            this.contentType = 0;
            this.flags = 0;
            this.usage = 1;
        }

        public AudioAttributes build() {
            return new AudioAttributes(this.contentType, this.flags, this.usage, null);
        }

        public Builder setContentType(int arg1) {
            this.contentType = arg1;
            return this;
        }

        public Builder setFlags(int arg1) {
            this.flags = arg1;
            return this;
        }

        public Builder setUsage(int arg1) {
            this.usage = arg1;
            return this;
        }
    }

    public static final AudioAttributes DEFAULT;
    private android.media.AudioAttributes audioAttributesV21;
    public final int contentType;
    public final int flags;
    public final int usage;

    static {
        AudioAttributes.DEFAULT = new Builder().build();
    }

    private AudioAttributes(int arg1, int arg2, int arg3) {
        super();
        this.contentType = arg1;
        this.flags = arg2;
        this.usage = arg3;
    }

    AudioAttributes(int arg1, int arg2, int arg3, com.google.android.exoplayer2.audio.AudioAttributes$1 arg4) {
        this(arg1, arg2, arg3);
    }

    public boolean equals(Object arg5) {
        boolean v0 = true;
        if(this == (((AudioAttributes)arg5))) {
            return 1;
        }

        if(arg5 != null) {
            if(this.getClass() != arg5.getClass()) {
            }
            else {
                if(this.contentType != ((AudioAttributes)arg5).contentType || this.flags != ((AudioAttributes)arg5).flags || this.usage != ((AudioAttributes)arg5).usage) {
                    v0 = false;
                }
                else {
                }

                return v0;
            }
        }

        return 0;
    }

    @TargetApi(value=21) android.media.AudioAttributes getAudioAttributesV21() {
        if(this.audioAttributesV21 == null) {
            this.audioAttributesV21 = new AudioAttributes$Builder().setContentType(this.contentType).setFlags(this.flags).setUsage(this.usage).build();
        }

        return this.audioAttributesV21;
    }

    public int hashCode() {
        return ((527 + this.contentType) * 31 + this.flags) * 31 + this.usage;
    }
}

