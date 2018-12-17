package com.googlecode.mp4parser.authoring;

public class Edit {
    private double mediaRate;
    private long mediaTime;
    private double segmentDuration;
    private long timeScale;

    public Edit(long arg1, long arg3, double arg5, double arg7) {
        super();
        this.timeScale = arg3;
        this.segmentDuration = arg7;
        this.mediaTime = arg1;
        this.mediaRate = arg5;
    }

    public double getMediaRate() {
        return this.mediaRate;
    }

    public long getMediaTime() {
        return this.mediaTime;
    }

    public double getSegmentDuration() {
        return this.segmentDuration;
    }

    public long getTimeScale() {
        return this.timeScale;
    }
}

