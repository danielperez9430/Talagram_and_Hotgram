package com.google.ads.interactivemedia.v3.api.player;

import com.google.ads.interactivemedia.v3.internal.ko;

public final class VideoProgressUpdate {
    public static final VideoProgressUpdate VIDEO_TIME_NOT_READY;
    private float currentTime;
    private float duration;

    static {
        VideoProgressUpdate.VIDEO_TIME_NOT_READY = new VideoProgressUpdate(-1, -1);
    }

    public VideoProgressUpdate(long arg1, long arg3) {
        super();
        this.currentTime = (((float)arg1)) / 1000f;
        this.duration = (((float)arg3)) / 1000f;
    }

    public boolean equals(Object arg5) {
        if(this == (((VideoProgressUpdate)arg5))) {
            return 1;
        }

        if(arg5 == null) {
            return 0;
        }

        if(this.getClass() != arg5.getClass()) {
            return 0;
        }

        if(Float.floatToIntBits(this.currentTime) != Float.floatToIntBits(((VideoProgressUpdate)arg5).currentTime)) {
            return 0;
        }

        if(Float.floatToIntBits(this.duration) != Float.floatToIntBits(((VideoProgressUpdate)arg5).duration)) {
            return 0;
        }

        return 1;
    }

    public float getCurrentTime() {
        return this.currentTime;
    }

    public float getDuration() {
        return this.duration;
    }

    public int hashCode() {
        return ko.a(new Object[]{Float.valueOf(this.currentTime), Float.valueOf(this.duration)});
    }

    public String toString() {
        float v0 = this.currentTime;
        float v1 = this.duration;
        StringBuilder v2 = new StringBuilder(75);
        v2.append("VideoProgressUpdate [currentTime=");
        v2.append(v0);
        v2.append(", duration=");
        v2.append(v1);
        v2.append("]");
        return v2.toString();
    }
}

